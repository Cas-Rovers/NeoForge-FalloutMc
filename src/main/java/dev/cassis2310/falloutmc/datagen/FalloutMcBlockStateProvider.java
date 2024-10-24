package dev.cassis2310.falloutmc.datagen;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.blocks.FalloutMcBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FalloutMcBlockStateProvider extends BlockStateProvider
{

    public FalloutMcBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, FalloutMc.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        // Example 1:
        //blockWithItem(FalloutMcBlocks.NUCLEAR_WASTE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock)
    {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
