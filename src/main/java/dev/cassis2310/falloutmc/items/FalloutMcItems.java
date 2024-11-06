package dev.cassis2310.falloutmc.items;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.items.custom.WeightedItem;
import dev.cassis2310.falloutmc.items.enums.ItemType;
import dev.cassis2310.falloutmc.items.properties.ModFoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * The {@code FalloutMcItems} class is responsible for registering custom items in the
 * Fallout Minecraft mod. This class uses the {@link DeferredRegister} to manage item types
 * and register them with the Minecraft built-in registries. This class should be used to
 * define and register custom items within the mod.
 */
public class FalloutMcItems
{
    /**
     * A deferred register for items in the Fallout Minecraft mod. This object is used to
     * register custom items with the Minecraft built-in registries.
     */
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FalloutMc.MOD_ID);

    // Register all items here.

    /**
     * A custom item for a Nuka-Cola bottle. This item is a weighted item that has food
     * properties and is classified as a drink.
     */
    public static final DeferredItem<Item> NUKA_COLA = ITEMS.register("nuka_cola",
            () -> new WeightedItem(new Item.Properties().food(ModFoodProperties.NUKA_COLA), 0.5, ItemType.DRINK));

    /**
     * Registers all items in this class with the provided event bus.
     * <p>
     * @param bus The event bus used for registering items.
     */
    public static void register(IEventBus bus)
    {
        ITEMS.register(bus);
    }
}
