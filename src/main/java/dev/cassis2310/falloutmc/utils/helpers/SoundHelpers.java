package dev.cassis2310.falloutmc.utils.helpers;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

import org.slf4j.Logger;

public class SoundHelpers
{
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LogUtils.getLogger();

    private SoundHelpers()
    {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Plays a sound at the specified position in the given level.
     *
     * @param level The level to play the sound in.
     * @param pos   The position to play the sound at.
     * @param sound The sound event to play.
     */
    public static void playSound(Level level, BlockPos pos, SoundEvent sound)
    {
        var rand = level.getRandom();
        level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0f + rand.nextFloat(), rand.nextFloat() + 0.7f + 0.3f);
    }

    /**
     * Plays the placement sound for the given block state at the specified position.
     *
     * @param level The level to play the sound in.
     * @param pos   The position to play the sound at.
     * @param state The block state to play the sound for.
     */
    public static void playPlaceSound(LevelAccessor level, BlockPos pos, BlockState state)
    {
        playPlaceSound(level, pos, state.getSoundType(level, pos, null));
    }

    /**
     * Plays the placement sound for the given sound type at the specified position.
     *
     * @param level The level to play the sound in.
     * @param pos   The position to play the sound at.
     * @param st    The sound type to play the sound for.
     */
    public static void playPlaceSound(LevelAccessor level, BlockPos pos, SoundType st)
    {
        level.playSound(null, pos, st.getPlaceSound(), SoundSource.BLOCKS, (st.getVolume() + 1.0f) / 2.0f, st.getPitch() * 0.8f);
    }


}
