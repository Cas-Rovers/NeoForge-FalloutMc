package dev.cassis2310.falloutmc.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

/**
 * An example mob effect.
 *
 * <p>This mob effect will slowly cause the entity to climb upwards
 * if it is not moving horizontally. The effect is applied on every tick,
 * and the entity's horizontal movement is ignored.</p>
 *
 * @author Cassis2310
 */
public class ExampleEffect extends MobEffect
{
    public ExampleEffect(MobEffectCategory category, int color)
    {
        super(category, color);
    }

    /**
     * Applies the effect to the given entity.
     *
     * <p>If the entity is not moving horizontally, the entity will
     * slowly climb upwards.</p>
     *
     * @param livingEntity The entity to apply the effect to.
     * @param amplifier The amplifier of the effect.
     * @return Whether the effect was applied successfully.
     */
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

    /**
     * Returns whether the effect should be applied on this tick.
     *
     * <p>This method will always return true, since the effect should
     * be applied on every tick.</p>
     *
     * @param duration The duration of the effect.
     * @param amplifier The amplifier of the effect.
     * @return Whether the effect should be applied on this tick.
     */
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
        return true;
    }
}
