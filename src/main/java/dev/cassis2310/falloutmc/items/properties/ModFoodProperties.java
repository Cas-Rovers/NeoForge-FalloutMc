package dev.cassis2310.falloutmc.items.properties;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

/**
 * Contains all the food properties for items in the mod.
 */
public class ModFoodProperties
{
    /**
     * The food properties for Nuka-Cola.
     */
    public static final FoodProperties NUKA_COLA = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 1.0f).build();
}