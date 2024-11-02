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

public class FalloutMcEffects
{
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, FalloutMc.MOD_ID);

    public static Holder<MobEffect> EXAMPLE_EFFECT = MOB_EFFECTS.register("example_effect",
            () -> new ExampleEffect(MobEffectCategory.NEUTRAL, 0x00FF00)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            ResourceHelpers.resourceLocation(FalloutMc.MOD_ID, "example_effect"),
                            -0.25f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static void register(IEventBus bus)
    {
        MOB_EFFECTS.register(bus);
    }
}
