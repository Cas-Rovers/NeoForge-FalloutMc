package dev.cassis2310.falloutmc.effects;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.utils.helpers.ResourceHelpers;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * The {@code FalloutMcEffects} class is responsible for registering custom mob effects.
 * It uses the {@link DeferredRegister} to manage mob effect types and register them
 * with the Minecraft built-in registries.
 * <p>
 * This class should be used to define and register custom mob effects within the mod.
 */
public class FalloutMcEffects
{
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, FalloutMc.MOD_ID);

    public static Holder<MobEffect> EXAMPLE_EFFECT = MOB_EFFECTS.register("example_effect",
            () -> new ExampleEffect(MobEffectCategory.NEUTRAL, 0x00FF00)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            ResourceHelpers.resourceWithNamespace(FalloutMc.MOD_ID, "example_effect"),
                            -0.25f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    /**
     * Registers the mob effects with the provided event bus.
     * <p>
     * @param bus The event bus used for registering mob effects.
     */
    public static void register(IEventBus bus)
    {
        MOB_EFFECTS.register(bus);
    }
}
