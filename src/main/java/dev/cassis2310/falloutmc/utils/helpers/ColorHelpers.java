package dev.cassis2310.falloutmc.utils.helpers;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.DyeColor;

import java.util.logging.Logger;

public class ColorHelpers
{
    private static final Logger LOGGER = (Logger) LogUtils.getLogger();

    private ColorHelpers() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * An array of all dye colors available in Minecraft.
     */
    public static final DyeColor[] DYE_COLORS = DyeColor.values();
}
