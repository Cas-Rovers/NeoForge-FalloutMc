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

/**
 * Provides item tag generation for the Fallout Minecraft mod.
 * This class handles the creation and registration of item tags,
 * which can be used to group items for various purposes, such as crafting or block interactions.
 */
public class FalloutMcItemTagProvider extends ItemTagsProvider
{
    /**
     * Creates a new item tag provider.
     *
     * @param output the output pack for the generated item tags
     * @param lookupProvider a CompletableFuture for the lookup provider
     * @param blockTags a CompletableFuture for block tags to use in the item tagging process
     * @param existingFileHelper a helper for managing existing files can be null
     */
    public FalloutMcItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTags, FalloutMc.MOD_ID, existingFileHelper);
    }

    /**
     * Adds item tags to the provider.
     * This method is overridden to define custom item tags for the mod's items.
     *
     * <h3>Usage Examples:</h3>
     * <p>
     * <h4>Example 1 (Custom tags):</h4>
     * <pre>{@code
     * tag(FalloutMcTags.Items.TRANSFORMABLE_ITEMS)
     *   .add(FalloutMcItems.NUKA_COLA.get()) // Mod Item
     *   .add(Items.COAL); // Vanilla Item
     * }</pre>
     * </p>
     *
     * @param provider the provider for item tags, used for adding items to tags
     */
    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        //
    }
}
