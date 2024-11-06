package dev.cassis2310.falloutmc.items;

import dev.cassis2310.falloutmc.FalloutMc;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * This class is responsible for registering and managing the
 * CreativeModeTabs for the FalloutMc mod. The main purpose of this
 * class is to provide a central location for registering custom tabs
 * for the mod's items.
 * <p>
 * The class provides a {@link DeferredRegister} for {@link CreativeModeTab}
 * instances, which can be used to register custom tabs. The tabs are
 * registered using the {@link #register(IEventBus)} method.
 * <p>
 * The class also provides a pre-defined {@link CreativeModeTab} instance
 * for the mod's consumables, which can be accessed using the
 * {@link #FALLOUTMC_CONSUMABLES_TAB} field.
 * @author cassis2310
 */
public class FalloutMcCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, FalloutMc.MOD_ID);

    /**
     * A pre-defined {@link CreativeModeTab} instance for the mod's consumables.
     * This tab is registered automatically when the mod is initialized.
     * <p>
     * The tab is named "falloutmc_consumables_tab", and it displays the items
     * registered by the mod.
     * <p>
     * The tab is translated using the "creativetab.falloutmc.consumables" key.
     */
    public static final Supplier<CreativeModeTab> FALLOUTMC_CONSUMABLES_TAB = CREATIVE_MODE_TAB.register(
            "falloutmc_consumables_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(FalloutMcItems.NUKA_COLA.get()))
                    .title(Component.translatable("creativetab.falloutmc.consumables"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(FalloutMcItems.NUKA_COLA.get());
                    })
                    .build()
    );


    /**
     * Registers the CreativeModeTabs with the provided event bus.
     * <p>
     * This method should be called once, during the mod's initialization.
     * <p>
     * The method registers the pre-defined tab {@link #FALLOUTMC_CONSUMABLES_TAB}
     * with the CreativeModeTab registry.
     *
     * @param bus The event bus used for registering the tabs.
     */
    public static void register(IEventBus bus)
    {
        CREATIVE_MODE_TAB.register(bus);
    }
}
