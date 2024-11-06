package dev.cassis2310.falloutmc.mixins.client;

import dev.cassis2310.falloutmc.utils.ValidationSuite;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for Minecraft client initialization.
 * This class injects code into the Minecraft client to execute self-tests
 * when resources are reloaded, specifically during game initialization.
 */
@Mixin(Minecraft.class)
public class MinecraftMixin
{
    /**
     * Injects self-tests execution into Minecraft's resource reload sequence.
     * <p>
     * Method target: lambda$new$7(Lnet/minecraft/client/Minecraft$GameLoadCookie;)V
     * Injection point: Just before calling finishReload() in ResourceLoadStateTracker.
     *
     * @param ci Callback info, required for injection method signatures.
     */
    @Inject(
            // Specifies the target method in the Minecraft class where the injection should occur.
            // "lambda$new$7(Lnet/minecraft/client/Minecraft$GameLoadCookie;)V" refers to an anonymous lambda method
            // generated during Minecraft client initialization, specifically one with the parameter type
            // Minecraft.GameLoadCookie (Lnet/minecraft/client/Minecraft$GameLoadCookie;).
            // The V at the end indicates that this method returns void.
            method = "lambda$new$7(Lnet/minecraft/client/Minecraft$GameLoadCookie;)V",

            // Specifies the precise injection point within the target method.
            // @At(value = "INVOKE", target = "Lnet/minecraft/client/ResourceLoadStateTracker;finishReload()V"):
            // - `@At(value = "INVOKE")`: This means the injection will happen at a method invocation within the target method.
            // - `target = "Lnet/minecraft/client/ResourceLoadStateTracker;finishReload()V"`:
            //   This is the specific method to look for within the target lambda method.
            //   The finishReload() method in the ResourceLoadStateTracker class is invoked when resource loading completes.
            // The code inside this injection method will be executed right before finishReload() is called.
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/ResourceLoadStateTracker;finishReload()V"))
    private void runSelfTests(CallbackInfo ci)
    {
        ValidationSuite.performClientValidations(); // Executes self-tests when resource reload finishes
    }
}
