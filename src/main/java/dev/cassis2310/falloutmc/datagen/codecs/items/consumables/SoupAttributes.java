package dev.cassis2310.falloutmc.datagen.codecs.items.consumables;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.cassis2310.falloutmc.datagen.codecs.effects.EffectWithDuration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

import static dev.cassis2310.falloutmc.datagen.codecs.effects.EffectWithDuration.EFFECT_WITH_DURATION_CODEC;

/**
 * A record representing the attributes of soup items in the mod.
 *
 * <p>This record encapsulates the various properties associated with soup consumables,
 * including their effects on player attributes such as health, hunger, and thirst.
 * It is designed to facilitate the integration of soup items into the gameplay by detailing
 * their characteristics and effects.</p>
 *
 * <p>Two codecs are provided to support serialization and deserialization of soup item attributes:</p>
 * <ul>
 *   <li>{@link #SOUP_ATTRIBUTES_CODEC} - A standard codec that directly maps to the attributes defined in this record.</li>
 *   <li>{@link #CODEC} - An alternative codec that provides flexibility in data input.</li>
 * </ul>
 *
 * @param radiation      The radiation level emitted by the soup when consumed.
 * @param hpRestore      The health points restored by consuming the soup.
 * @param effects        A list of beneficial effects granted by the soup.
 * @param effectDuration The duration for which beneficial effects last after consumption.
 * @param deceaseEffects A list of adverse effects that might occur upon consumption.
 * @param deceaseChance  The probability of experiencing adverse effects after consumption.
 * @param hungerRestore  The hunger points replenished by the soup.
 * @param thirstRestore  The thirst points replenished by the soup.
 * @param weight         The item's weight, affecting inventory management and mechanics.
 */
public record SoupAttributes(int radiation, int hpRestore, List<EffectWithDuration> effects, List<MobEffect> deceaseEffects, int deceaseChance, int hungerRestore, int thirstRestore, double weight, boolean recipe, int value)
{
    /**
     * A codec that directly maps to the attributes defined in this record.
     */
    public static final Codec<SoupAttributes> SOUP_ATTRIBUTES_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("radiation").forGetter(SoupAttributes::radiation),
            Codec.INT.fieldOf("hpRestore").forGetter(SoupAttributes::hpRestore),
            EFFECT_WITH_DURATION_CODEC.listOf().fieldOf("effects").forGetter(SoupAttributes::effects),
            BuiltInRegistries.MOB_EFFECT.byNameCodec().listOf().fieldOf("deceaseEffect").forGetter(SoupAttributes::deceaseEffects),
            Codec.INT.fieldOf("deceaseChance").forGetter(SoupAttributes::deceaseChance),
            Codec.INT.fieldOf("hungerRestore").forGetter(SoupAttributes::hungerRestore),
            Codec.INT.fieldOf("thirstRestore").forGetter(SoupAttributes::thirstRestore),
            Codec.DOUBLE.fieldOf("weight").forGetter(SoupAttributes::weight),
            Codec.BOOL.fieldOf("recipe").forGetter(SoupAttributes::recipe),
            Codec.INT.fieldOf("value").forGetter(SoupAttributes::value)
    ).apply(instance, SoupAttributes::new));

    /**
     * An alternative codec that provides flexibility in data input.
     * It allows for both a named weight field and the simple double value representation.
     */
    public static final Codec<SoupAttributes> CODEC = Codec.withAlternative(
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT.fieldOf("radiation").forGetter(SoupAttributes::radiation),
                    Codec.INT.fieldOf("hpRestore").forGetter(SoupAttributes::hpRestore),
                    EFFECT_WITH_DURATION_CODEC.listOf().fieldOf("effects").forGetter(SoupAttributes::effects),
                    BuiltInRegistries.MOB_EFFECT.byNameCodec().listOf().fieldOf("deceaseEffects").forGetter(SoupAttributes::deceaseEffects),
                    Codec.INT.fieldOf("deceaseChance").forGetter(SoupAttributes::deceaseChance),
                    Codec.INT.fieldOf("hungerRestore").forGetter(SoupAttributes::hungerRestore),
                    Codec.INT.fieldOf("thirstRestore").forGetter(SoupAttributes::thirstRestore),
                    Codec.DOUBLE.fieldOf("weight").forGetter(SoupAttributes::weight),
                    Codec.BOOL.fieldOf("recipe").forGetter(SoupAttributes::recipe),
                    Codec.INT.fieldOf("value").forGetter(SoupAttributes::value)
            ).apply(instance, SoupAttributes::new)),
            SOUP_ATTRIBUTES_CODEC
    );
}
