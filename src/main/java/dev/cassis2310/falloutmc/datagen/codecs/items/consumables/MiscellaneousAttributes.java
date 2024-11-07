package dev.cassis2310.falloutmc.datagen.codecs.items.consumables;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.cassis2310.falloutmc.datagen.codecs.effects.EffectWithDuration;

import java.util.List;

import static dev.cassis2310.falloutmc.datagen.codecs.effects.EffectWithDuration.EFFECT_WITH_DURATION_CODEC;

/**
 * A record representing the attributes of miscellaneous items in the mod.
 *
 * <p>This record contains the information about the item's effects, radiation, weight, value, and addiction properties.</p>
 *
 * <p>Two codecs are provided to support serialization and deserialization of miscellaneous item attributes:</p>
 * <ul>
 *   <li>{@link #MISCELLANEOUS_ATTRIBUTES_CODEC} - A standard codec that directly maps to the attributes defined in this record.</li>
 *   <li>{@link #CODEC} - An alternative codec that provides flexibility in data input.</li>
 * </ul>
 *
 * @param effects      The list of beneficial effects granted by the item.
 * @param radiation    The radiation level emitted by the item when used.
 * @param weight       The item's weight, affecting inventory management and mechanics.
 * @param value        The item's monetary value, used in trading and economic systems.
 * @param addiction    Whether the item has an addictive property (true) or not (false).
 */
public record MiscellaneousAttributes(List<EffectWithDuration> effects, int radiation, double weight, int value, boolean addiction)
{
    /**
     * A codec that directly maps to the attributes defined in this record.
     */
    public static final Codec<MiscellaneousAttributes> MISCELLANEOUS_ATTRIBUTES_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EFFECT_WITH_DURATION_CODEC.listOf().fieldOf("effects").forGetter(MiscellaneousAttributes::effects),
            Codec.INT.fieldOf("radiation").forGetter(MiscellaneousAttributes::radiation),
            Codec.DOUBLE.fieldOf("weight").forGetter(MiscellaneousAttributes::weight),
            Codec.INT.fieldOf("value").forGetter(MiscellaneousAttributes::value),
            Codec.BOOL.fieldOf("addiction").forGetter(MiscellaneousAttributes::addiction)
    ).apply(instance, MiscellaneousAttributes::new));

    /**
     * An alternative codec that provides flexibility in data input.
     * It allows for both a named weight field and the simple double value representation.
     */
    public static final Codec<MiscellaneousAttributes> CODEC = Codec.withAlternative(
            RecordCodecBuilder.create(instance -> instance.group(
                    EFFECT_WITH_DURATION_CODEC.listOf().fieldOf("effects").forGetter(MiscellaneousAttributes::effects),
                    Codec.INT.fieldOf("radiation").forGetter(MiscellaneousAttributes::radiation),
                    Codec.DOUBLE.fieldOf("weight").forGetter(MiscellaneousAttributes::weight),
                    Codec.INT.fieldOf("value").forGetter(MiscellaneousAttributes::value),
                    Codec.BOOL.fieldOf("addiction").forGetter(MiscellaneousAttributes::addiction)
            ).apply(instance, MiscellaneousAttributes::new)),
            MISCELLANEOUS_ATTRIBUTES_CODEC
    );
}
