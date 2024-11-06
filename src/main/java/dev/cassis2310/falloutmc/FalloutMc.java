package dev.cassis2310.falloutmc;

import dev.cassis2310.falloutmc.Entities.FalloutMcEntities;
import dev.cassis2310.falloutmc.blocks.FalloutMcBlocks;
import dev.cassis2310.falloutmc.components.FalloutMcDataComponents;
import dev.cassis2310.falloutmc.effects.FalloutMcEffects;
import dev.cassis2310.falloutmc.items.FalloutMcCreativeModeTabs;
import dev.cassis2310.falloutmc.items.FalloutMcItems;
import dev.cassis2310.falloutmc.utils.ValidationSuite;
import dev.cassis2310.falloutmc.utils.helpers.ExceptionHelpers;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import org.jetbrains.annotations.Nullable;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(FalloutMc.MOD_ID)
public class FalloutMc
{
    public static final String MOD_ID = "falloutmc";
    public static final String MOD_NAME = "FalloutMC";
    private static final Logger LOGGER = LogUtils.getLogger();

    private @Nullable Throwable syncLoadError;

    public FalloutMc(IEventBus bus, ModContainer container)
    {
        initializeLogger();
        registerComponents(bus, container);
        NeoForgeMod.enableMilkFluid();
    }

    // Logger initialization for debugging and production environment details
    private void initializeLogger() {
        LOGGER.info("[{}]: Initializing", MOD_NAME);
        LOGGER.info("""
                    
                        ==== MOD OPTIONS ====
                        ASSERTIONS: {}
                        DEBUG MODE: {}
                        PRODUCTION MODE: {}
                        DISTRIBUTION: {}
                        SELF TESTS: {} (Fatal: {})
                        =====================
                    """,
                areAssertionsEnabled(),
                LOGGER.isDebugEnabled(),
                FMLEnvironment.production,
                FMLEnvironment.dist,
                ValidationSuite.ENABLED,
                ValidationSuite.THROW_ON_FAILURE
        );
    }

    // Registering components and listeners
    private void registerComponents(IEventBus bus, ModContainer container) {
        // Main Event Listeners
        bus.addListener(this::commonSetup);
        bus.addListener(this::loadComplete);
        bus.addListener(this::addCreative);

        // Register your items, blocks, entities, and so on
        FalloutMcItems.register(bus);
        FalloutMcBlocks.register(bus);
        FalloutMcEntities.register(bus);
        FalloutMcDataComponents.register(bus);
        FalloutMcCreativeModeTabs.register(bus);
        FalloutMcEffects.register(bus);

        // Register Configurations
        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // NeoForge Events
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("[{}]: Performing common setup", MOD_NAME);

        // Some common setup code (not synchronized)

        event.enqueueWork(() -> {
            // Some common setup code (synchronized)
        }).exceptionally(e -> {
            LOGGER.error("[{}]: An unhandled exception was thrown during synchronous mod loading:", MOD_NAME, e);
            syncLoadError = e;
            return null;
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        // Add the block to the creative tab
    }

    /**
     * Handles the FMLLoadCompleteEvent, checking for any synchronization load errors.
     * <p>
     * If a synchronization load error occurred, it is re-thrown as an unchecked exception.
     *
     * @param event the FMLLoadCompleteEvent instance
     */
    public void loadComplete(FMLLoadCompleteEvent event)
    {
        if (syncLoadError != null) {
            ExceptionHelpers.throwAsUnchecked(syncLoadError);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("[{}]: Starting server", MOD_NAME);
        // Do something when the server starts
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("[{}]: Performing client setup", MOD_NAME);
            // Some client setup code
        }
    }

    @SuppressWarnings({"AssertWithSideEffects", "ConstantConditions"})
    private boolean areAssertionsEnabled()
    {
        boolean enabled = false;
        assert enabled = true;
        return enabled;
    }
}
