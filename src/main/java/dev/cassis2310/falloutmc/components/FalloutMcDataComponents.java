package dev.cassis2310.falloutmc.components;

import dev.cassis2310.falloutmc.FalloutMc;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

/**
 * The {@code FalloutMcDataComponents} class is responsible for registering and managing
 * {@link DataComponentType} instances for the FalloutMc mod. These data components can be
 * used to store additional metadata on various objects (such as blocks or entities) within the mod.
 *
 * This class leverages the {@link DeferredRegister} to ensure that data component types are
 * registered correctly within the mod's lifecycle.
 *
 * <p>Example usage:</p>
 * <pre>
 * // Register a new DataComponentType for coordinates that uses BlockPos.CODEC for serialization.
 * public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES = register("coordinates",
 *             builder -> builder.persistent(BlockPos.CODEC));
 * </pre>
 *
 * <p>Components registered in this class can be accessed and utilized elsewhere in the mod to
 * store and manipulate additional data for custom behavior, such as keeping track of block
 * coordinates, entity states, or other mod-specific information.</p>
 *
 * <p>Usage:</p>
 * <ul>
 *     <li>{@code DATA_COMPONENTS_TYPES} is the deferred registry for {@link DataComponentType}.</li>
 *     <li>{@code COORDINATES} is a registered data component that can store and handle {@link BlockPos}
 *     coordinates persistently.</li>
 * </ul>
 *
 * <p>To register new component types, use the {@code register()} method which takes a name and
 * a builder function to customize the component type's behavior. The builder allows configuration
 * of persistence, codecs for serialization, and other options.</p>
 *
 * @see DataComponentType
 * @see DeferredRegister
 * @see DeferredHolder
 */
public class FalloutMcDataComponents
{
    /**
     * The deferred registry for DataComponentType objects in FalloutMc mod.
     */
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS_TYPES = DeferredRegister.createDataComponents(FalloutMc.MOD_ID);

    /**
     * A sample data component type that stores BlockPos data persistently.
     */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES = register("coordinates",
                        builder -> builder.persistent(BlockPos.CODEC));

    // Register all your data component types here.

    /**
     * Registers a DataComponentType with the given name and builder configuration.
     *
     * @param name the registry name of the data component
     * @param builderOperator a function to configure the DataComponentType's builder
     * @param <T> the type of data stored by the component
     * @return a DeferredHolder containing the registered DataComponentType
     */
    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator)
    {
        return DATA_COMPONENTS_TYPES.register(name, () -> builderOperator.apply(new DataComponentType.Builder<>()).build());
    }

    /**
     * Registers the DataComponentTypes with the given event bus.
     *
     * @param bus the event bus to register with (typically the mod event bus)
     */
    public static void register(IEventBus bus)
    {
        DATA_COMPONENTS_TYPES.register(bus);
    }
}
