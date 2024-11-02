package dev.cassis2310.falloutmc.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class ExampleEffect extends MobEffect
{
    public ExampleEffect(MobEffectCategory category, int color)
    {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier)
    {
        if (livingEntity.horizontalCollision)
        {
            Vec3 initialVector = livingEntity.getDeltaMovement();
            Vec3 climbVector = new Vec3(initialVector.x, 0.2D, initialVector.z);
            livingEntity.setDeltaMovement(climbVector.scale(0.85D));

            return true;
        }

        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
        return true;
    }
}
