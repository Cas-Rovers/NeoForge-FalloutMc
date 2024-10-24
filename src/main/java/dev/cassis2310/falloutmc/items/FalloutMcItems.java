package dev.cassis2310.falloutmc.items;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.items.custom.WeightedItem;
import dev.cassis2310.falloutmc.items.enums.ItemType;
import dev.cassis2310.falloutmc.items.properties.ModFoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FalloutMcItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FalloutMc.MOD_ID);

    // Register all items here.

    public static final DeferredItem<Item> NUKA_COLA = ITEMS.register("nuka_cola",
            () -> new WeightedItem(new Item.Properties().food(ModFoodProperties.NUKA_COLA), 0.5, ItemType.DRINK));

    public static void register(IEventBus bus)
    {
        ITEMS.register(bus);
    }
}
