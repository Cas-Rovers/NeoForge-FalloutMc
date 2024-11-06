package dev.cassis2310.falloutmc.datagen.codecs.items.consumables;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.cassis2310.falloutmc.datagen.codecs.effects.EffectWithDuration;

import java.util.List;

import static dev.cassis2310.falloutmc.datagen.codecs.effects.EffectWithDuration.EFFECT_WITH_DURATION_CODEC;

/**
 * A record representing the attributes of chems in the mod.
 *
 * <p>This record encapsulates the various properties associated with chems,
 * including their effects on player attributes such as health, hunger, thirst,
 * radiation, and other attributes relevant to gameplay.</p>
 *
 * <p>Two codecs are provided to support serialization and deserialization of chem
 * item attributes:</p>
 * <ul>
 *   <li>{@link #CHEM_ATTRIBUTES_CODEC} - A standard codec that directly maps to the attributes defined in this record.</li>
 *   <li>{@link #CODEC} - An alternative codec that provides flexibility in data input.</li>
 * </ul>
 *
 * @param effects       The list of beneficial effects granted by the chem.
 * @param hungerRestore The hunger points replenished by consuming the chem.
 * @param thirstRestore The thirst points replenished by the chem.
 * @param weight        The item's weight, affecting inventory management and mechanics.
 * @param value         The item's monetary value, used in trading and economic systems.
 * @param addiction     Whether consuming the chem causes addiction (true) or not (false).
 * @param recipe        Whether the item can be used in crafting recipes (true) or not (false).
 */
public record ChemAttributes(List<EffectWithDuration> effects, int hungerRestore, int thirstRestore, double weight, int value, boolean addiction, boolean recipe)
{
    public static final Codec<ChemAttributes> CHEM_ATTRIBUTES_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EFFECT_WITH_DURATION_CODEC.listOf().fieldOf("effects").forGetter(ChemAttributes::effects),
            Codec.INT.fieldOf("hungerRestore").forGetter(ChemAttributes::hungerRestore),
            Codec.INT.fieldOf("thirstRestore").forGetter(ChemAttributes::thirstRestore),
            Codec.DOUBLE.fieldOf("weight").forGetter(ChemAttributes::weight),
            Codec.INT.fieldOf("value").forGetter(ChemAttributes::value),
            Codec.BOOL.fieldOf("addiction").forGetter(ChemAttributes::addiction),
            Codec.BOOL.fieldOf("recipe").forGetter(ChemAttributes::recipe)
    ).apply(instance, ChemAttributes::new));

    public static final Codec<ChemAttributes> CODEC = Codec.withAlternative(
            RecordCodecBuilder.create(instance -> instance.group(
                    EFFECT_WITH_DURATION_CODEC.listOf().fieldOf("effects").forGetter(ChemAttributes::effects),
                    Codec.INT.fieldOf("hungerRestore").forGetter(ChemAttributes::hungerRestore),
                    Codec.INT.fieldOf("thirstRestore").forGetter(ChemAttributes::thirstRestore),
                    Codec.DOUBLE.fieldOf("weight").forGetter(ChemAttributes::weight),
                    Codec.INT.fieldOf("value").forGetter(ChemAttributes::value),
                    Codec.BOOL.fieldOf("addiction").forGetter(ChemAttributes::addiction),
                    Codec.BOOL.fieldOf("recipe").forGetter(ChemAttributes::recipe)
            ).apply(instance, ChemAttributes::new)),
            CHEM_ATTRIBUTES_CODEC
    );
}
