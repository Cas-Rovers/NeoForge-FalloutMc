package dev.cassis2310.falloutmc.blocks;

import dev.cassis2310.falloutmc.FalloutMc;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * The {@code FalloutMcBlocks} class is responsible for registering
 * all blocks in the Fallout Minecraft mod.
 * <p>
 * It utilizes the {@link DeferredRegister} to manage block types and
 * register them within the mod's namespace. This class is where custom
 * blocks are defined and registered to be recognized by the game.
 * </p>
 */
public class FalloutMcBlocks
{
    /** The {@link DeferredRegister} used to register blocks. */
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FalloutMc.MOD_ID);

    // Register all your blocks here.

    /**
     * Registers the blocks with the provided event bus.
     *
     * @param bus The event bus used for registering blocks.
     */
    public static void register(IEventBus bus)
    {
        BLOCKS.register(bus);
    }
}
