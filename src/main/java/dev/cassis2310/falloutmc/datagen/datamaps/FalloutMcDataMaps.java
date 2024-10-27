package dev.cassis2310.falloutmc.datagen.datamaps;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.datagen.records.ItemWeightData;
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
     * A data map type for item weights.
     *
     * <p>This data map type links item weights to the {@link Item} class and uses
     * {@link ItemWeightData#CODEC} for serialization.</p>
     *
     * <p>The data map is marked as synced, meaning its values will be transmitted to clients.
     * The syncing codec can be the same as the main codec or a simplified version that
     * excludes unnecessary fields for the client. In this case, it is specified using
     * {@link ItemWeightData#ITEM_WEIGHT_CODEC} as the syncing codec.</p>
     */
    public static final DataMapType<Item, ItemWeightData> ITEM_WEIGHT = DataMapType.builder(
            ResourceHelpers.resourceLocation(FalloutMc.MOD_ID, "item_weights"),
            Registries.ITEM,
            ItemWeightData.CODEC
    ).synced(ItemWeightData.ITEM_WEIGHT_CODEC, false).build();

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
        event.register(ITEM_WEIGHT);
    }
}
