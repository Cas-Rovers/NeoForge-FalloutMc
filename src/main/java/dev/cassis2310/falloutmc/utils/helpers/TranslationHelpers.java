package dev.cassis2310.falloutmc.utils.helpers;

import com.mojang.logging.LogUtils;
import dev.cassis2310.falloutmc.FalloutMc;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Locale;
import java.util.logging.Logger;

public class TranslationHelpers
{
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = (Logger) LogUtils.getLogger();

    private TranslationHelpers()
    {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Translates an enum into a localized text component.
     * This method is useful for displaying enum names in the user's language.
     *
     * @param anEnum The enum to translate.
     *
     * @return A localized text component representing the enum.
     */
    public static MutableComponent translateEnum(Enum<?> anEnum)
    {
        return Component.translatable(getEnumTranslationKey(anEnum));
    }

    /**
     * Translates an enum into a localized text component using a custom name.
     * This allows for custom enum translations, which may differ from the enum's class name.
     *
     * @param anEnum   The enum to translate.
     * @param enumName The custom name to use in the translation key.
     *
     * @return A localized text component representing the enum.
     */
    public static MutableComponent translateEnum(Enum<?> anEnum, String enumName)
    {
        return Component.translatable(getEnumTranslationKey(anEnum, enumName));
    }

    /**
     * Returns the translation key for an enum, using the enum's class name as the base.
     * This is typically used for creating localized strings for enum values.
     *
     * @param anEnum The enum to get the translation key for.
     *
     * @return The translation key for the enum.
     */
    public static String getEnumTranslationKey(Enum<?> anEnum)
    {
        return getEnumTranslationKey(anEnum, anEnum.getDeclaringClass().getSimpleName());
    }

    /**
     * Returns the translation key for an enum, using a custom name instead of the enum's class name.
     * This method allows for more flexibility in creating translation keys for enums.
     *
     * @param anEnum   The enum to get the translation key for.
     * @param enumName The custom name to use in the translation key.
     *
     * @return The translation key for the enum.
     */
    public static String getEnumTranslationKey(Enum<?> anEnum, String enumName)
    {
        return String.join(".", FalloutMc.MOD_ID, "enum", enumName, anEnum.name()).toLowerCase(Locale.ROOT);
    }
}
