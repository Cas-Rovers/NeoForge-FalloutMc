package dev.cassis2310.falloutmc.datagen.records;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

/**
 * A record representing the weight of an item.
 *
 * <p>This record contains a single field, {@code weight}, which stores the weight of an item as a double value.
 * The record provides codecs to facilitate serialization and deserialization of item weight data.</p>
 *
 * <p>Two codecs are provided:</p>
 * <ul>
 *   <li>{@link #ITEM_WEIGHT_CODEC} - A simple codec that directly maps to the weight value.</li>
 *   <li>{@link #CODEC} - An alternative codec that allows for flexible serialization, supporting both
 *       a named weight field and the simple double value representation.</li>
 * </ul>
 *
 * @param weight the weight of the item
 */
public record ItemWeightData(double weight)
{
    /** A codec for serializing and deserializing item weight data as a double. */
    public static final Codec<ItemWeightData> ITEM_WEIGHT_CODEC = Codec.DOUBLE.xmap(
      ItemWeightData::new, ItemWeightData::weight
    );

    /** A codec for serializing and deserializing item weight data, supporting both named field and raw double. */
    public static final Codec<ItemWeightData> CODEC = Codec.withAlternative(
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.DOUBLE.fieldOf("weight").forGetter(ItemWeightData::weight)).apply(instance, ItemWeightData::new)),
            ITEM_WEIGHT_CODEC
    );
}
