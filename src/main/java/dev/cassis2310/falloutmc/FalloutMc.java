package dev.cassis2310.falloutmc;

import dev.cassis2310.falloutmc.Entities.FalloutMcEntities;
import dev.cassis2310.falloutmc.blocks.FalloutMcBlocks;
import dev.cassis2310.falloutmc.components.FalloutMcDataComponents;
import dev.cassis2310.falloutmc.effects.FalloutMcEffects;
import dev.cassis2310.falloutmc.items.FalloutMcItems;
import dev.cassis2310.falloutmc.utils.helpers.ExceptionHelpers;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(FalloutMc.MOD_ID)
public class FalloutMc
{
    public static final String MOD_ID = "falloutmc";
    public static final String MOD_NAME = "FalloutMC";
    private static final Logger LOGGER = LogUtils.getLogger();

    private @Nullable Throwable syncLoadError;

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public FalloutMc(IEventBus bus, ModContainer container)
    {
        bus.addListener(this::commonSetup);
        bus.addListener(this::loadComplete);

        // Register your items, blocks, entities, and so on
        FalloutMcItems.register(bus);
        FalloutMcBlocks.register(bus);
        FalloutMcEntities.register(bus);
        FalloutMcDataComponents.register(bus);
        FalloutMcEffects.register(bus);

        NeoForge.EVENT_BUS.register(this);

        bus.addListener(this::addCreative);
        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        NeoForgeMod.enableMilkFluid();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("[{}]: Performing common setup", MOD_NAME);

        // Some common setup code

        event.enqueueWork(() -> {
            // Some common setup code
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
}
