package dev.cassis2310.falloutmc.datagen.codecs.items;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.lang.reflect.Array;
import java.util.List;

public class Consumables
{
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

    public record FoodAttributes(int radiation, int hpRestore, List<MobEffect> effect, int effectDuration, List<MobEffect> deceaseEffect, int deceaseChance, int hungerRestore, int thirstRestore, double weight, boolean addiction, boolean recipe, boolean plantable, int value)
    {
        public static final Codec<FoodAttributes> FOOD_ATTRIBUTES_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("radiation").forGetter(FoodAttributes::radiation),
                Codec.INT.fieldOf("hp_restore").forGetter(FoodAttributes::hpRestore),
                BuiltInRegistries.MOB_EFFECT.byNameCodec().listOf().fieldOf("effect").forGetter(FoodAttributes::effect),
                Codec.INT.fieldOf("effect_duration").forGetter(FoodAttributes::effectDuration),
                BuiltInRegistries.MOB_EFFECT.byNameCodec().listOf().fieldOf("decease_effect").forGetter(FoodAttributes::deceaseEffect),
                Codec.INT.fieldOf("decease_chance").forGetter(FoodAttributes::deceaseChance),
                Codec.INT.fieldOf("hunger").forGetter(FoodAttributes::hungerRestore),
                Codec.INT.fieldOf("thirst").forGetter(FoodAttributes::thirstRestore),
                Codec.DOUBLE.fieldOf("weight").forGetter(FoodAttributes::weight),
                Codec.BOOL.fieldOf("addiction").forGetter(FoodAttributes::addiction),
                Codec.BOOL.fieldOf("recipe").forGetter(FoodAttributes::recipe),
                Codec.BOOL.fieldOf("plantable").forGetter(FoodAttributes::plantable),
                Codec.INT.fieldOf("value").forGetter(FoodAttributes::value)
        ).apply(instance, FoodAttributes::new));

        public static final Codec<FoodAttributes> CODEC = Codec.withAlternative(
                RecordCodecBuilder.create(instance -> instance.group(
                        Codec.INT.fieldOf("radiation").forGetter(FoodAttributes::radiation),
                        Codec.INT.fieldOf("hp_restore").forGetter(FoodAttributes::hpRestore),
                        BuiltInRegistries.MOB_EFFECT.byNameCodec().listOf().fieldOf("effect").forGetter(FoodAttributes::effect),
                        Codec.INT.fieldOf("effect_duration").forGetter(FoodAttributes::effectDuration),
                        BuiltInRegistries.MOB_EFFECT.byNameCodec().listOf().fieldOf("decease_effect").forGetter(FoodAttributes::deceaseEffect),
                        Codec.INT.fieldOf("decease_chance").forGetter(FoodAttributes::deceaseChance),
                        Codec.INT.fieldOf("hunger").forGetter(FoodAttributes::hungerRestore),
                        Codec.INT.fieldOf("thirst").forGetter(FoodAttributes::thirstRestore),
                        Codec.DOUBLE.fieldOf("weight").forGetter(FoodAttributes::weight),
                        Codec.BOOL.fieldOf("addiction").forGetter(FoodAttributes::addiction),
                        Codec.BOOL.fieldOf("recipe").forGetter(FoodAttributes::recipe),
                        Codec.BOOL.fieldOf("plantable").forGetter(FoodAttributes::plantable),
                        Codec.INT.fieldOf("value").forGetter(FoodAttributes::value)
                ).apply(instance, FoodAttributes::new)),
                FOOD_ATTRIBUTES_CODEC
        );
    }

    public record SoupAttributes(double weight,int radiation, int hpRestore, String effect, int effectDuration, int deceaseChance, int foodRestore, int thirstRestore)
    {
        public static final Codec<SoupAttributes> SOUP_ATTRIBUTES_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.DOUBLE.fieldOf("weight").forGetter(SoupAttributes::weight),
                Codec.INT.fieldOf("radiation").forGetter(SoupAttributes::radiation),
                Codec.INT.fieldOf("hp_restore").forGetter(SoupAttributes::hpRestore),
                Codec.STRING.fieldOf("effect").forGetter(SoupAttributes::effect),
                Codec.INT.fieldOf("effect_duration").forGetter(SoupAttributes::effectDuration),
                Codec.INT.fieldOf("decease_chance").forGetter(SoupAttributes::deceaseChance),
                Codec.INT.fieldOf("food_restore").forGetter(SoupAttributes::foodRestore),
                Codec.INT.fieldOf("thirst_restore").forGetter(SoupAttributes::thirstRestore)
        ).apply(instance, SoupAttributes::new));

        public static final Codec<SoupAttributes> CODEC = Codec.withAlternative(
                RecordCodecBuilder.create(instance -> instance.group(
                        Codec.DOUBLE.fieldOf("weight").forGetter(SoupAttributes::weight),
                        Codec.INT.fieldOf("radiation").forGetter(SoupAttributes::radiation),
                        Codec.INT.fieldOf("hp_restore").forGetter(SoupAttributes::hpRestore),
                        Codec.STRING.fieldOf("effect").forGetter(SoupAttributes::effect),
                        Codec.INT.fieldOf("effect_duration").forGetter(SoupAttributes::effectDuration),
                        Codec.INT.fieldOf("decease_chance").forGetter(SoupAttributes::deceaseChance),
                        Codec.INT.fieldOf("food_restore").forGetter(SoupAttributes::foodRestore),
                        Codec.INT.fieldOf("stamina_restore").forGetter(SoupAttributes::thirstRestore)
                ).apply(instance, SoupAttributes::new)),
                SOUP_ATTRIBUTES_CODEC
        );
    }
}
