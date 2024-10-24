package dev.cassis2310.falloutmc.Entities;

import dev.cassis2310.falloutmc.FalloutMc;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FalloutMcEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, FalloutMc.MOD_ID);

    // Register all entities here.

    public static void register(IEventBus bus)
    {

        ENTITIES.register(bus);
    }
}
