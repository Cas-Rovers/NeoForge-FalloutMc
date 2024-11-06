package dev.cassis2310.falloutmc.utils.helpers;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import org.slf4j.Logger;

public class WorldHelpers
{
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LogUtils.getLogger();

    private WorldHelpers()
    {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Safely retrieves the Level object from an unknown type.
     * This method is useful when dealing with code that may interact with Level objects in unconventional ways.
     *
     * @param maybeLevel An object that might be a Level or related type.
     * @return           The Level, or null if the object is not a Level.
     */
    @Nullable
    @SuppressWarnings("deprecation")
    public static Level getUnsafeLevel(Object maybeLevel)
    {
        if (maybeLevel instanceof Level level)
        {
            return level; // Most obvious case, if we can directly cast up to level.
        }
        if (maybeLevel instanceof WorldGenRegion level)
        {
            return level.getLevel(); // Special case for world gen, when we can access the level unsafely
        }
        return null; // A modder has done a strange ass thing
    }

    /**
     * Attempts to spread fire in a random direction around a specified position in the world.
     * The fire spread is controlled by game rules and can be influenced by neighboring blocks.
     *
     * @param level  The server-level where the fire spread should occur.
     * @param pos    The starting position for fire spreading.
     * @param random A random source for determining fire spread direction and chance.
     * @param radius The radius within which the fire can spread.
     */
    public static void fireSpreaderTick(ServerLevel level, BlockPos pos, RandomSource random, int radius)
    {
        if (level.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK))
        {
            for (int i = 0; i < radius; i++)
            {
                pos = pos.relative(Direction.Plane.HORIZONTAL.getRandomDirection(random));
                if (level.getRandom().nextFloat() < 0.25F)
                {
                    pos = pos.above();
                }
                final BlockState state = level.getBlockState(pos);
                if (!state.isAir())
                {
                    return;
                }
                if (hasFlammableNeighbours(level, pos))
                {
                    level.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
                    return;
                }
            }
        }
    }

    /**
     * Checks if there are any flammable blocks adjacent to the specified position.
     *
     * @param level The level reader to check the blocks.
     * @param pos   The position to check for flammable neighbors.
     * @return      {@code true} if there are flammable blocks adjacent to the position, otherwise {@code false}.
     */
    private static boolean hasFlammableNeighbours(LevelReader level, BlockPos pos)
    {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (Direction direction : DirectionHelpers.DIRECTIONS)
        {
            mutable.setWithOffset(pos, direction);
            if (level.getBlockState(mutable).isFlammable(level, mutable, direction.getOpposite()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Seeds large features to avoid clustering issues during world generation.
     * This method ensures that features with a chance placement are distributed more evenly across the world.
     *
     * @param random      the RandomSource to modify
     * @param baseSeed    the base seed to use for generating
     * @param index       the index of the feature being generated
     * @param decoration   an additional decoration value that influences the seed
     */
    public static void seedLargeFeatures(RandomSource random, long baseSeed, int index, int decoration)
    {
        random.setSeed(baseSeed);
        final long seed = (index * random.nextLong() * 203704237L) ^ (decoration * random.nextLong() * 758031792L) ^ baseSeed;
        random.setSeed(seed);
    }
}
