package dev.cassis2310.falloutmc.Entities;

import dev.cassis2310.falloutmc.FalloutMc;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * The {@code FalloutMcEntities} class is responsible for registering
 * all entities in the Fallout Minecraft mod.
 * <p>
 * It utilizes the {@link DeferredRegister} to manage entity types and
 * register them with the Minecraft built-in registries. This class should
 * be used to define and register custom entities within the mod.
 * </p>
 */
public class FalloutMcEntities
{
    /**
     * A deferred register for entity types. This register allows for
     * the dynamic registration of entity types in the Minecraft mod.
     */
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, FalloutMc.MOD_ID);

    // Register all entities here.

    /**
     * Registers the entity types with the provided event bus.
     *
     * @param bus The event bus used for registering entities.
     */
    public static void register(IEventBus bus)
    {

        ENTITIES.register(bus);
    }
}
