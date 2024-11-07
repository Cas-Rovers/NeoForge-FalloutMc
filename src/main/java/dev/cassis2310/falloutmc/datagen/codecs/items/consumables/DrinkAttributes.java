package dev.cassis2310.falloutmc.datagen.codecs.items.consumables;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.cassis2310.falloutmc.datagen.codecs.effects.EffectWithDuration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

import static dev.cassis2310.falloutmc.datagen.codecs.effects.EffectWithDuration.EFFECT_WITH_DURATION_CODEC;

/**
 * A record representing the attributes of drinks in the mod.
 *
 * <p>This record encapsulates the various properties associated with drinks,
 * including their effects on player attributes such as health, action points,
 * thirst, radiation, and other attributes relevant to gameplay.</p>
 *
 * <p>Two codecs are provided to support serialization and deserialization of drink
 * item attributes:</p>
 * <ul>
 *   <li>{@link #DRINK_ATTRIBUTES_CODEC} - A standard codec that directly maps to the attributes defined in this record.</li>
 *   <li>{@link #CODEC} - An alternative codec that provides flexibility in data input.</li>
 * </ul>
 *
 * @param radiation      The radiation level emitted by the drink when consumed.
 * @param hpRestore      The health points restored by consuming the drink.
 * @param apBoost        The action points restored by consuming the drink.
 * @param effects        A list of beneficial effects granted by the drink.
 * @param deceaseEffects A list of adverse effects that might occur upon consumption.
 * @param deceaseChance  The probability of experiencing adverse effects after consumption.
 * @param thirstRestore  The thirst points replenished by the drink.
 * @param weight         The item's weight, affecting inventory management and mechanics.
 * @param value          The item's monetary value, used in trading and economic systems.
 * @param addiction      Whether consuming the drink causes addiction (true) or not (false).
 * @param recipe         Whether the item can be used in crafting recipes (true) or not (false).
 */
public record DrinkAttributes(int radiation, int hpRestore, int apBoost, List<EffectWithDuration> effects, List<MobEffect> deceaseEffects, int deceaseChance, int thirstRestore, double weight, int value, boolean addiction, boolean recipe)
{
    /**
     * A standard codec that directly maps to the attributes defined in this record.
     */
    public static final Codec<DrinkAttributes> DRINK_ATTRIBUTES_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("radiation").forGetter(DrinkAttributes::radiation),
            Codec.INT.fieldOf("hpRestore").forGetter(DrinkAttributes::hpRestore),
            Codec.INT.fieldOf("apBoost").forGetter(DrinkAttributes::apBoost),
            EFFECT_WITH_DURATION_CODEC.listOf().fieldOf("effects").forGetter(DrinkAttributes::effects),
            BuiltInRegistries.MOB_EFFECT.byNameCodec().listOf().fieldOf("deceaseEffects").forGetter(DrinkAttributes::deceaseEffects),
            Codec.INT.fieldOf("deceaseChance").forGetter(DrinkAttributes::deceaseChance),
            Codec.INT.fieldOf("thirstRestore").forGetter(DrinkAttributes::thirstRestore),
            Codec.DOUBLE.fieldOf("weight").forGetter(DrinkAttributes::weight),
            Codec.INT.fieldOf("value").forGetter(DrinkAttributes::value),
            Codec.BOOL.fieldOf("addiction").forGetter(DrinkAttributes::addiction),
            Codec.BOOL.fieldOf("recipe").forGetter(DrinkAttributes::recipe)
    ).apply(instance, DrinkAttributes::new));

    /**
     * An alternative codec that provides flexibility in data input. This codec first tries to use the standard
     * codec, and if that fails, it uses the standard codec but without the named fields.
     */
    public static final Codec<DrinkAttributes> CODEC = Codec.withAlternative(
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT.fieldOf("radiation").forGetter(DrinkAttributes::radiation),
                    Codec.INT.fieldOf("hpRestore").forGetter(DrinkAttributes::hpRestore),
                    Codec.INT.fieldOf("apBoost").forGetter(DrinkAttributes::apBoost),
                    EFFECT_WITH_DURATION_CODEC.listOf().fieldOf("effects").forGetter(DrinkAttributes::effects),
                    BuiltInRegistries.MOB_EFFECT.byNameCodec().listOf().fieldOf("deceaseEffects").forGetter(DrinkAttributes::deceaseEffects),
                    Codec.INT.fieldOf("deceaseChance").forGetter(DrinkAttributes::deceaseChance),
                    Codec.INT.fieldOf("thirstRestore").forGetter(DrinkAttributes::thirstRestore),
                    Codec.DOUBLE.fieldOf("weight").forGetter(DrinkAttributes::weight),
                    Codec.INT.fieldOf("value").forGetter(DrinkAttributes::value),
                    Codec.BOOL.fieldOf("addiction").forGetter(DrinkAttributes::addiction),
                    Codec.BOOL.fieldOf("recipe").forGetter(DrinkAttributes::recipe)
            ).apply(instance, DrinkAttributes::new)),
            DRINK_ATTRIBUTES_CODEC
    );
}
