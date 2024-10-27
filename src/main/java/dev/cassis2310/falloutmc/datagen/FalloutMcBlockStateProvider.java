package dev.cassis2310.falloutmc.datagen;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.blocks.FalloutMcBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

/**
 * Provides block state generation for blocks in the Fallout Minecraft mod.
 * This class defines how blocks are represented in the game, including their
 * states and models.
 */
public class FalloutMcBlockStateProvider extends BlockStateProvider
{
    /**
     * Creates a new block state provider.
     *
     * @param output the output pack for the generated block states
     * @param exFileHelper the helper for managing existing files
     */
    public FalloutMcBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, FalloutMc.MOD_ID, exFileHelper);
    }

    /**
     * Registers the block states and models for the blocks defined in this provider.
     *
     * <p>
     * <h3>Usage Examples:</h3>
     * <div>
     * <h4>Example 1 (Block with an item):</h4>
     * <pre>{@code
     * blockWithItem(FalloutMcBlocks.NUCLEAR_WASTE);
     * }</pre>
     * </div>
     * </p>
     */
    @Override
    protected void registerStatesAndModels()
    {
        //
    }

    /**
     * Registers a block and its corresponding item model for a deferred block.
     *
     * @param deferredBlock the deferred block to register
     */
    private void blockWithItem(DeferredBlock<?> deferredBlock)
    {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
