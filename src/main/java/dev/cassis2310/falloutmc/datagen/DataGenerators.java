package dev.cassis2310.falloutmc.datagen;

import dev.cassis2310.falloutmc.FalloutMc;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This class manages the data generation process for the Fallout Minecraft mod.
 * It registers various data providers for loot tables, recipes, block tags, item tags,
 * data maps, and models, ensuring that all necessary data is generated for the mod.
 */
@EventBusSubscriber(modid = FalloutMc.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
    /**
     * Gathers data by registering various providers when the {@link GatherDataEvent} is fired.
     *
     * @param event the event containing the context for data generation, including
     *              the generator, output location, existing file helper, and lookup provider.
     */
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(FalloutMcBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        generator.addProvider(event.includeServer(), new FalloutMcRecipeProvider(packOutput, lookupProvider));

        BlockTagsProvider blockTagsProvider = new FalloutMcBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new FalloutMcItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new FalloutMcDataMapProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeClient(), new FalloutMcItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new FalloutMcBlockStateProvider(packOutput, existingFileHelper));
    }

}
