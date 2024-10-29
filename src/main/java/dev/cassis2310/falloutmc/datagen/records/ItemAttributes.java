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
 *   <li>{@link #ITEM_ATTRIBUTES_CODEC} - A simple codec that directly maps to the weight value.</li>
 *   <li>{@link #CODEC} - An alternative codec that allows for flexible serialization, supporting both
 *       a named weight field and the simple double value representation.</li>
 * </ul>
 *
 * @param weight the weight of the item
 */
public record ItemAttributes(double weight)
{
    /** A codec for serializing and deserializing item weight data as a double. */
    public static final Codec<ItemAttributes> ITEM_ATTRIBUTES_CODEC = Codec.DOUBLE.xmap(
      ItemAttributes::new, ItemAttributes::weight
    );

    /** A codec for serializing and deserializing item weight data, supporting both named field and raw double. */
    public static final Codec<ItemAttributes> CODEC = Codec.withAlternative(
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.DOUBLE.fieldOf("weight").forGetter(ItemAttributes::weight)).apply(instance, ItemAttributes::new)),
            ITEM_ATTRIBUTES_CODEC
    );
}
