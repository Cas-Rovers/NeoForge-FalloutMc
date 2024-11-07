package dev.cassis2310.falloutmc.datagen.codecs.items.consumables;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.cassis2310.falloutmc.datagen.codecs.effects.EffectWithDuration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

import static dev.cassis2310.falloutmc.datagen.codecs.effects.EffectWithDuration.EFFECT_WITH_DURATION_CODEC;

/**
 * A record representing the attributes of consumable items in the mod.
 *
 * <p>This record encapsulates various properties that describe the effects and characteristics
 * of consumable items, such as food and drinks. The fields include effects related to health,
 * hunger, thirst, radiation, and other attributes relevant to gameplay.</p>
 *
 * <p>Two codecs are provided to facilitate serialization and deserialization of item attributes data:</p>
 * <ul>
 *   <li>{@link #FOOD_ATTRIBUTES_CODEC} - A standard codec that directly maps to the attributes defined in this record.</li>
 *   <li>{@link #CODEC} - An alternative codec that supports both a named weight field and the simple double value representation, allowing for flexible data input.</li>
 * </ul>
 *
 * @param radiation       The radiation level the item emits when consumed.
 * @param hpRestore       The health points restored upon consuming the item.
 * @param effects         The list of beneficial effects granted by the item.
 * @param deceaseEffects  The list of adverse effects that might occur upon consumption.
 * @param deceaseChance   The probability of experiencing adverse effects after consumption.
 * @param hungerRestore   The hunger points replenished by the item.
 * @param thirstRestore   The thirst points replenished by the item.
 * @param weight          The item's weight, impacting inventory management and mechanics.
 * @param addiction       Whether consuming the item causes addiction (true) or not (false).
 * @param recipe          Whether the item can be used in crafting recipes (true) or not (false).
 * @param plantable       Whether the item can be planted (true) or not (false).
 * @param value           The item's monetary value, used in trading and economic systems.
 */
public record FoodAttributes(int radiation, int hpRestore, List<EffectWithDuration> effects, List<MobEffect> deceaseEffects, int deceaseChance, int hungerRestore, int thirstRestore, double weight, boolean addiction, boolean recipe, boolean plantable, int value)
{
    /**
     * A codec that maps the attributes defined in this record to the data.
     */
    public static final Codec<FoodAttributes> FOOD_ATTRIBUTES_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("radiation").forGetter(FoodAttributes::radiation),
            Codec.INT.fieldOf("hpRestore").forGetter(FoodAttributes::hpRestore),
            EFFECT_WITH_DURATION_CODEC.listOf().fieldOf("effects").forGetter(FoodAttributes::effects),
            BuiltInRegistries.MOB_EFFECT.byNameCodec().listOf().fieldOf("deceaseEffects").forGetter(FoodAttributes::deceaseEffects),
            Codec.INT.fieldOf("deceaseChance").forGetter(FoodAttributes::deceaseChance),
            Codec.INT.fieldOf("hungerRestore").forGetter(FoodAttributes::hungerRestore),
            Codec.INT.fieldOf("thirstRestore").forGetter(FoodAttributes::thirstRestore),
            Codec.DOUBLE.fieldOf("weight").forGetter(FoodAttributes::weight),
            Codec.BOOL.fieldOf("addiction").forGetter(FoodAttributes::addiction),
            Codec.BOOL.fieldOf("recipe").forGetter(FoodAttributes::recipe),
            Codec.BOOL.fieldOf("plantable").forGetter(FoodAttributes::plantable),
            Codec.INT.fieldOf("value").forGetter(FoodAttributes::value)
    ).apply(instance, FoodAttributes::new));

    /**
     * A codec that provides flexibility in data input.
     * <p>
     * This codec supports both a named weight field and the simple double value representation.
     * It is the recommended codec for use in data generation.
     */
    public static final Codec<FoodAttributes> CODEC = Codec.withAlternative(
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT.fieldOf("radiation").forGetter(FoodAttributes::radiation),
                    Codec.INT.fieldOf("hpRestore").forGetter(FoodAttributes::hpRestore),
                    EFFECT_WITH_DURATION_CODEC.listOf().fieldOf("effects").forGetter(FoodAttributes::effects),
                    BuiltInRegistries.MOB_EFFECT.byNameCodec().listOf().fieldOf("deceaseEffects").forGetter(FoodAttributes::deceaseEffects),
                    Codec.INT.fieldOf("deceaseChance").forGetter(FoodAttributes::deceaseChance),
                    Codec.INT.fieldOf("hungerRestore").forGetter(FoodAttributes::hungerRestore),
                    Codec.INT.fieldOf("thirstRestore").forGetter(FoodAttributes::thirstRestore),
                    Codec.DOUBLE.fieldOf("weight").forGetter(FoodAttributes::weight),
                    Codec.BOOL.fieldOf("addiction").forGetter(FoodAttributes::addiction),
                    Codec.BOOL.fieldOf("recipe").forGetter(FoodAttributes::recipe),
                    Codec.BOOL.fieldOf("plantable").forGetter(FoodAttributes::plantable),
                    Codec.INT.fieldOf("value").forGetter(FoodAttributes::value)
            ).apply(instance, FoodAttributes::new)),
            FOOD_ATTRIBUTES_CODEC
    );
}
