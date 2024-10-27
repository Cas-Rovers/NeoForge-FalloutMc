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

/**
 * Provides block tag generation for the Fallout Minecraft mod.
 * This class is responsible for defining how blocks are categorized into tags,
 * which can be used for various game mechanics such as mining and crafting.
 */
public class FalloutMcBlockTagProvider extends BlockTagsProvider
{
    /**
     * Creates a new block tag provider.
     *
     * @param output the output pack for the generated block tags
     * @param lookupProvider the provider for looking up holders
     * @param existingFileHelper a helper for managing existing files; may be null
     */
    public FalloutMcBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, FalloutMc.MOD_ID, existingFileHelper);
    }

    /**
     * Adds block tags to the provider.
     * This method should be overridden to define specific tags for the mod's blocks.
     *
     * <h3>Usage Examples:</h3>
     * <p>
     * <h4>Example 1 (Mining):</h4>
     * <pre>{@code
     * tag(BlockTags.MINEABLE_WITH_AXE)
     *    .add(FalloutMcBlocks.NUCLEAR_WASTE.get());
     * }</pre>
     * </p>
     *
     * @param provider the provider for looking up holders
     */
    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        //
    }
}
