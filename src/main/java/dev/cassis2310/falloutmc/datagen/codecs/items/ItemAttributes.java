package dev.cassis2310.falloutmc.datagen.codecs.items;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

/**
 * A record representing the attributes of an item.
 *
 * <p>This record contains a list of fields, such as {@code weight}, {@code value}, etc.
 * The record provides codecs to facilitate serialization and deserialization of item attributes data.</p>
 *
 * <p>Two codecs are provided:</p>
 * <ul>
 *   <li>{@link #ITEM_ATTRIBUTES_CODEC} - A simple codec that directly maps to the weight value.</li>
 *   <li>{@link #CODEC} - An alternative codec that allows for flexible serialization, supporting both
 *       a named weight field and the simple double value representation.</li>
 * </ul>
 *
 * @param weight the weight of the item
 * @param value the value of the item
 */
public record ItemAttributes(double weight, int value)
{
    /** A codec for serializing and deserializing item data. */
    public static final Codec<ItemAttributes> ITEM_ATTRIBUTES_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.DOUBLE.fieldOf("weight").forGetter(ItemAttributes::weight),
            Codec.INT.fieldOf("value").forGetter(ItemAttributes::value)
    ).apply(instance, ItemAttributes::new));

    /** A codec for serializing and deserializing item data, supporting both named field and raw formats. */
    public static final Codec<ItemAttributes> CODEC = Codec.withAlternative(
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.DOUBLE.fieldOf("weight").forGetter(ItemAttributes::weight),
                    Codec.INT.fieldOf("value").forGetter(ItemAttributes::value)
            ).apply(instance, ItemAttributes::new)),
            ITEM_ATTRIBUTES_CODEC
    );
}
