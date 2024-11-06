package dev.cassis2310.falloutmc.datagen.datamaps;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.datagen.codecs.items.ItemAttributes;
import dev.cassis2310.falloutmc.datagen.codecs.items.consumables.FoodAttributes;
import dev.cassis2310.falloutmc.datagen.codecs.items.consumables.SoupAttributes;
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
    public static final DataMapType<Item, ItemAttributes> ITEM_ATTRIBUTES = DataMapType.builder(
            ResourceHelpers.resourceWithNamespace(FalloutMc.MOD_ID, "item_attributes"),
            Registries.ITEM,
            ItemAttributes.CODEC
    ).synced(ItemAttributes.ITEM_ATTRIBUTES_CODEC, false).build();

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
    public static final DataMapType<Item, FoodAttributes> FOOD_ATTRIBUTES = DataMapType.builder(
            ResourceHelpers.resourceWithNamespace(FalloutMc.MOD_ID, "food_attributes"),
            Registries.ITEM,
            FoodAttributes.CODEC
    ).synced(FoodAttributes.FOOD_ATTRIBUTES_CODEC, false).build();

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
    public static final DataMapType<Item, SoupAttributes> SOUP_ATTRIBUTES = DataMapType.builder(
            ResourceHelpers.resourceWithNamespace(FalloutMc.MOD_ID, "soup_attributes"),
            Registries.ITEM,
            SoupAttributes.CODEC
    ).synced(SoupAttributes.SOUP_ATTRIBUTES_CODEC, false).build();

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
        event.register(ITEM_ATTRIBUTES);
        event.register(FOOD_ATTRIBUTES);
        event.register(SOUP_ATTRIBUTES);
    }
}
