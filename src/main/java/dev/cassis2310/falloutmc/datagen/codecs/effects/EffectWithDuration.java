package dev.cassis2310.falloutmc.datagen.codecs.effects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
/**
 * A record representing a {@link MobEffect} with its duration.
 *
 * <p>This record is used to represent effects with a duration in the game.
 * It contains a {@link MobEffect} and its duration in ticks.
 */
public record EffectWithDuration(MobEffect effect, int duration) {
    /**
     * A codec that serializes and deserializes {@link EffectWithDuration} objects.
     */
    public static final Codec<EffectWithDuration> EFFECT_WITH_DURATION_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.MOB_EFFECT.byNameCodec().fieldOf("effect").forGetter(EffectWithDuration::effect),
            Codec.INT.fieldOf("duration").forGetter(EffectWithDuration::duration)
    ).apply(instance, EffectWithDuration::new));
}