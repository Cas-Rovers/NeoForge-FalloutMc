package dev.cassis2310.falloutmc.datagen.datamaps;

import com.mojang.serialization.Codec;
import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.datagen.codecs.items.ItemAttributes;
import dev.cassis2310.falloutmc.datagen.codecs.items.consumables.*;
import dev.cassis2310.falloutmc.utils.helpers.ResourceHelpers;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

/**
 * This class manages data maps for the Fallout Minecraft mod.
 */
@EventBusSubscriber(modid = FalloutMc.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class FalloutMcDataMaps
{
    /**
     * Creates a {@link DataMapType} for item attributes in the Fallout Minecraft mod.
     *
     * <p>This method constructs a new {@link DataMapType} specifically for item attributes.
     * It utilizes the specified main codec for serialization and a separate syncing codec
     * for client-server synchronization, ensuring that item attributes are correctly
     * serialized and transmitted within the mod's data maps.</p>
     *
     * <p>The {@link DataMapType} is associated with the {@link Item} class in the Minecraft
     * registry and is uniquely identified by the provided name. The name is also prefixed
     * with the mod's namespace to prevent conflicts with other mods.</p>
     *
     * @param name      The unique name for the data map type, identifying it within the mod.
     * @param mainCodec The codec used for the primary serialization of item attributes.
     * @param syncCodec The codec used for syncing item attributes between client and server.
     * @param <T>       The type of data being serialized and synchronized in the data map.
     * @return A {@link DataMapType} instance built with the specified codecs and name, ready for use.
     */
    private static <T> DataMapType<Item, T> createItemDataMapType(String name, Codec<T> mainCodec, Codec<T> syncCodec)
    {
        return DataMapType.builder(
                ResourceHelpers.resourceWithNamespace(FalloutMc.MOD_ID, name),
                Registries.ITEM,
                mainCodec
        ).synced(syncCodec, false).build();
    }

    /**
     * A data map type for normal item attributes.
     *
     * <p>This data map type links item attributes to the {@link Item} class and uses
     * {@link ItemAttributes#CODEC} for serialization.</p>
     *
     * <p>The data map is marked as synced, meaning its values will be transmitted to clients.
     * The syncing codec can be the same as the main codec or a simplified version that
     * excludes unnecessary fields for the client. In this case, it is specified using
     * {@link ItemAttributes#ITEM_ATTRIBUTES_CODEC} as the syncing codec.</p>
     */
    public static final DataMapType<Item, ItemAttributes> ITEM_ATTRIBUTES = createItemDataMapType(
            "item_attributes", ItemAttributes.CODEC, ItemAttributes.ITEM_ATTRIBUTES_CODEC
    );

    /**
     * A data map type for food item attributes.
     *
     * <p>This data map type links food attributes to the {@link Item} class and uses
     * {@link FoodAttributes#CODEC} for serialization.</p>
     *
     * <p>The data map is marked as synced, meaning its values will be transmitted to clients.
     * The syncing codec can be the same as the main codec or a simplified version that
     * excludes unnecessary fields for the client. In this case, it is specified using
     * {@link FoodAttributes#FOOD_ATTRIBUTES_CODEC} as the syncing codec.</p>
     */
    public static final DataMapType<Item, FoodAttributes> FOOD_ATTRIBUTES = createItemDataMapType(
            "food_attributes", FoodAttributes.CODEC, FoodAttributes.FOOD_ATTRIBUTES_CODEC
    );

    /**
     * A data map type for soup item attributes.
     *
     * <p>This data map type links soup item attributes to the {@link Item} class and uses
     * {@link SoupAttributes#CODEC} for serialization.</p>
     *
     * <p>The data map is marked as synced, meaning its values will be transmitted to clients.
     * The syncing codec can be the same as the main codec or a simplified version that
     * excludes unnecessary fields for the client. In this case, it is specified using
     * {@link SoupAttributes#SOUP_ATTRIBUTES_CODEC} as the syncing codec.</p>
     */
    public static final DataMapType<Item, SoupAttributes> SOUP_ATTRIBUTES = createItemDataMapType(
            "soup_attributes", SoupAttributes.CODEC, SoupAttributes.SOUP_ATTRIBUTES_CODEC
    );

    /**
     * A data map type for drink item attributes.
     *
     * <p>This data map type links drink item attributes to the {@link Item} class and uses
     * {@link DrinkAttributes#CODEC} for serialization.</p>
     *
     * <p>The data map is marked as synced, meaning its values will be transmitted to clients.
     * The syncing codec can be the same as the main codec or a simplified version that
     * excludes unnecessary fields for the client. In this case, it is specified using
     * {@link DrinkAttributes#DRINK_ATTRIBUTES_CODEC} as the syncing codec.</p>
     */
    public static final DataMapType<Item, DrinkAttributes> DRINK_ATTRIBUTES = createItemDataMapType(
            "drink_attributes", DrinkAttributes.CODEC, DrinkAttributes.DRINK_ATTRIBUTES_CODEC
    );

    /**
     * A data map type for chem item attributes.
     *
     * <p>This data map type links chem item attributes to the {@link Item} class and uses
     * {@link ChemAttributes#CODEC} for serialization.</p>
     *
     * <p>The data map is marked as synced, meaning its values will be transmitted to clients.
     * The syncing codec can be the same as the main codec or a simplified version that
     * excludes unnecessary fields for the client. In this case, it is specified using
     * {@link ChemAttributes#CHEM_ATTRIBUTES_CODEC} as the syncing codec.</p>
     */
    public static final DataMapType<Item, ChemAttributes> CHEM_ATTRIBUTES = createItemDataMapType(
            "chem_attributes", ChemAttributes.CODEC, ChemAttributes.CHEM_ATTRIBUTES_CODEC
    );

    /**
     * A data map type for miscellaneous item attributes.
     *
     * <p>This data map type links miscellaneous item attributes to the {@link Item} class and uses
     * {@link MiscellaneousAttributes#CODEC} for serialization.</p>
     *
     * <p>The data map is marked as synced, meaning its values will be transmitted to clients.
     * The syncing codec can be the same as the main codec or a simplified version that
     * excludes unnecessary fields for the client. In this case, it is specified using
     * {@link MiscellaneousAttributes#MISCELLANEOUS_ATTRIBUTES_CODEC} as the syncing codec.</p>
     */
    public static final DataMapType<Item, MiscellaneousAttributes> MISCELLANEOUS_ATTRIBUTES = createItemDataMapType(
            "miscellaneous_attributes", MiscellaneousAttributes.CODEC, MiscellaneousAttributes.MISCELLANEOUS_ATTRIBUTES_CODEC
    );

    /**
     * Registers the custom data map types for the Fallout Minecraft mod.
     *
     * <p>This method is subscribed to the {@link RegisterDataMapTypesEvent}
     * and is automatically called to register the defined data maps.</p>
     *
     * @param event the event that triggers data map registration
     */
    @SubscribeEvent
    private static void registerDataMapTypes(RegisterDataMapTypesEvent event)
    {
        // General item DataMap.
        event.register(ITEM_ATTRIBUTES);

        // Consumable DataMaps...
        event.register(FOOD_ATTRIBUTES);
        event.register(SOUP_ATTRIBUTES);
        event.register(DRINK_ATTRIBUTES);
        event.register(CHEM_ATTRIBUTES);
        event.register(MISCELLANEOUS_ATTRIBUTES);
    }
}
