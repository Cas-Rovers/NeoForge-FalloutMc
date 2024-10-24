package dev.cassis2310.falloutmc.utils.helpers;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Direction;

import java.util.logging.Logger;

public class DirectionHelpers
{
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = (Logger) LogUtils.getLogger();

    private DirectionHelpers()
    {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * An array containing all possible directions in Minecraft, useful for iterating through all directions.
     */
    public static final Direction[] DIRECTIONS = Direction.values();

    /**
     * An array containing all directions except DOWN, useful for operations that need to exclude the downward direction.
     */
    public static final Direction[] DIRECTIONS_NOT_DOWN = new Direction[] { Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST };
}
