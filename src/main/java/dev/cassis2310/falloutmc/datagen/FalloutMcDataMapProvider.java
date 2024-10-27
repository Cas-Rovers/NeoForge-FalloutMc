package dev.cassis2310.falloutmc.datagen;

import dev.cassis2310.falloutmc.datagen.datamaps.FalloutMcDataMaps;
import dev.cassis2310.falloutmc.datagen.records.ItemWeightData;
import dev.cassis2310.falloutmc.items.FalloutMcItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.DataMapProvider;

import java.util.concurrent.CompletableFuture;

/**
 * This class is responsible for providing data maps.
 * It extends the {@link DataMapProvider} class to gather and register all the relevant data.
 */
public class FalloutMcDataMapProvider extends DataMapProvider
{

    /**
     * Creates a new instance of {@code FalloutMcDataMapProvider}.
     *
     * @param packOutput     the output location where data maps will be saved
     * @param lookupProvider a {@link CompletableFuture} that supplies the registries needed for data generation
     */
    protected FalloutMcDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(packOutput, lookupProvider);
    }

    /**
     * Gathers data map entries related to item weights.
     * This method populates the data map with item IDs and their corresponding weights.
     * The weights are used for various purposes, such as inventory management in the game.
     *
     * <h3>Usage Examples:</h3>
     * <p>
     * <h4>Example 1 (Furnace fuels):</h4>
     * <pre>{@code
     * this.builder(NeoForgeDataMaps.FURNACE_FUELS)
     *         .add(FalloutMcItems.NUCLEAR_SCRAP.getId(), new FurnaceFuel(1200), false);
     * }</pre>
     * </p>
     */
    @Override
    protected void gather()
    {
        this.builder(FalloutMcDataMaps.ITEM_WEIGHT)
                .add(FalloutMcItems.NUKA_COLA.getId(), new ItemWeightData(1), false)
                .add(BuiltInRegistries.ITEM.wrapAsHolder(Items.APPLE), new ItemWeightData(1), false)
                .add(BuiltInRegistries.ITEM.wrapAsHolder(Items.GOLDEN_APPLE), new ItemWeightData(10), false);
    }
}
