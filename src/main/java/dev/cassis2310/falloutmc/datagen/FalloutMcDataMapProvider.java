package dev.cassis2310.falloutmc.datagen;

import dev.cassis2310.falloutmc.items.FalloutMcItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class FalloutMcDataMapProvider extends DataMapProvider
{

    /**
     * Create a new provider.
     *
     * @param packOutput     the output location
     * @param lookupProvider a {@linkplain CompletableFuture} supplying the registries
     */
    protected FalloutMcDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(packOutput, lookupProvider);
    }

    /**
     * Generate data map entries.
     */
    @Override
    protected void gather()
    {
        // Example 1 (Furnace fuel):
        //this.builder(NeoForgeDataMaps.FURNACE_FUELS)
        //        .add(FalloutMcItems.NUCLEAR_SCRAP.getId(), new FurnaceFuel(1200), false);
    }
}
