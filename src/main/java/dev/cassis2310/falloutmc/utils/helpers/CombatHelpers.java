package dev.cassis2310.falloutmc.utils.helpers;

import com.mojang.logging.LogUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;

import org.slf4j.Logger;

public class CombatHelpers
{
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LogUtils.getLogger();

    private CombatHelpers()
    {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Checks if the given DamageSource matches the specified Tag.
     *
     * @param source the DamageSource to check
     * @param tag    the Tag to match
     * @return       true if the DamageSource matches the Tag, false otherwise
     */
    public static boolean isDamageSource(DamageSource source, TagKey<DamageType> tag)
    {
        return source.is(tag);
    }
}
