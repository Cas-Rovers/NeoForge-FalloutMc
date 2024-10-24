package dev.cassis2310.falloutmc.datagen;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.blocks.FalloutMcBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FalloutMcBlockTagProvider extends BlockTagsProvider
{

    public FalloutMcBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, FalloutMc.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        // Example 1:
        //tag(BlockTags.MINEABLE_WITH_AXE)
        //        .add(FalloutMcBlocks.NUCLEAR_WASTE.get());
    }
}
