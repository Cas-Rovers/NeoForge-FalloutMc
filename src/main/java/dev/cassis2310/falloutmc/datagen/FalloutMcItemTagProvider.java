package dev.cassis2310.falloutmc.datagen;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.items.FalloutMcItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FalloutMcItemTagProvider extends ItemTagsProvider
{

    public FalloutMcItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTags, FalloutMc.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        // Example 1 (Custom tags):
        //tag(FalloutMcTags.Items.TRANSFORMABLE_ITEMS)
        //        .add(FalloutMcItems.NUKA_COLA.get()) // Mod Item
        //        .add(Items.COAL); // Vanilla Item
    }
}
