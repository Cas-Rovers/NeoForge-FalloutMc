package dev.cassis2310.falloutmc.utils;

import com.mojang.blaze3d.vertex.PoseStack;

/**
 * Represents an offset in 3D space.
 *
 * <p>This record is immutable and provides various convenience methods
 * for working with offsets in 3D space.
 */
public record Offset(double x, double y, double z)
{
    /**
     * A zero offset.
     */
    public static final Offset ZERO = new Offset(0, 0, 0);

    /**
     * Applies this offset to the specified pose stack.
     *
     * @param pose the pose stack to apply the offset to
     */
    public void apply(PoseStack pose)
    {
        pose.translate(x, y, z);
    }

    /**
     * Creates a copy of this offset.
     *
     * @return a new offset with the same values
     */
    public Offset copy()
    {
        return new Offset(x, y, z);
    }
}
