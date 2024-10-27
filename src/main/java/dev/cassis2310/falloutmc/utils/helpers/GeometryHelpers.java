package dev.cassis2310.falloutmc.utils.helpers;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;
import org.slf4j.Logger;

public class GeometryHelpers
{
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LogUtils.getLogger();

    private GeometryHelpers()
    {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Converts a quart position to a block position.
     * @see net.minecraft.core.QuartPos#toBlock(int)
     *
     * @param x the x-coordinate of the quart position.
     * @param y the y-coordinate of the quart position.
     * @param z the z-coordinate of the quart position.
     * @return  the corresponding block position.
     */
    public static BlockPos quartToBlock(int x, int y, int z)
    {
        return new BlockPos(x << 2, y << 2, z << 2);
    }

    /**
     * Rotates a VoxelShape by 90 degrees around the specified axis.
     *
     * @param direction the direction of rotation (must be NORTH, EAST, SOUTH, or WEST).
     * @param x1        the minimum x-coordinate of the shape.
     * @param y1        the minimum y-coordinate of the shape.
     * @param z1        the minimum z-coordinate of the shape.
     * @param x2        the maximum x-coordinate of the shape.
     * @param y2        the maximum y-coordinate of the shape.
     * @param z2        the maximum z-coordinate of the shape.
     * @return          the rotated shape.
     * @throws          IllegalArgumentException if the direction is not horizontal.
     */
    public static VoxelShape rotateShape(Direction direction, double x1, double y1, double z1, double x2, double y2, double z2)
    {
        return switch (direction)
        {
            case NORTH -> Block.box(x1, y1, z1, x2, y2, z2);
            case EAST -> Block.box(16 - z2, y1, x1, 16 - z1, y2, x2);
            case SOUTH -> Block.box(16 - x2, y1, 16 - z2, 16 - x1, y2, 16 - z1);
            case WEST -> Block.box(z1, y1, 16 - x2, z2, y2, 16 - x1);
            default -> throw new IllegalArgumentException("Not Horizontal!");
        };
    }

    /**
     * Computes the horizontal shapes for a given shape getter function.
     * Follows indexes for {@link Direction#get2DDataValue()}.
     *
     * @param shapeGetter a function that returns a shape for a given direction.
     * @return            an array of shapes for the four horizontal directions (SOUTH, WEST, NORTH, EAST).
     */
    public static VoxelShape[] computeHorizontalShapes(Function<Direction, VoxelShape> shapeGetter)
    {
        return new VoxelShape[] {
                shapeGetter.apply(Direction.SOUTH),
                shapeGetter.apply(Direction.WEST),
                shapeGetter.apply(Direction.NORTH),
                shapeGetter.apply(Direction.EAST)
        };
    }

}
