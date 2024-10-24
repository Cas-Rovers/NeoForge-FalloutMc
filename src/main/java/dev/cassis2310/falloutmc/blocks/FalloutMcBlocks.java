package dev.cassis2310.falloutmc.blocks;

import dev.cassis2310.falloutmc.FalloutMc;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FalloutMcBlocks
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FalloutMc.MOD_ID);

    // Register all your blocks here.

    public static void register(IEventBus bus)
    {
        BLOCKS.register(bus);
    }
}
