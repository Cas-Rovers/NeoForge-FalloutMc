package dev.cassis2310.falloutmc.utils.helpers;

import com.mojang.logging.LogUtils;
import dev.cassis2310.falloutmc.FalloutMc;
import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;

public class ResourceHelpers
{
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LogUtils.getLogger();

    private ResourceHelpers()
    {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Returns a {@link ResourceLocation} with the "falloutmc" namespace.
     * This method is commonly used to create resource paths specific to the FalloutMC mod.
     *
     * @param name The path of the resource.
     * @return     A {@link ResourceLocation} with the "falloutmc" namespace.
     */
    public static ResourceLocation falloutMcResource(String name)
    {
        return resourceWithNamespace(FalloutMc.MOD_ID, name);
    }

    /**
     * Returns a {@link ResourceLocation} with the "minecraft" namespace.
     * This method is useful when referencing vanilla Minecraft resources.
     *
     * @param name The path of the resource.
     * @return     A {@link ResourceLocation} with the "minecraft" namespace.
     */
    public static ResourceLocation minecraftResource(String name)
    {
        return resourceWithNamespace("minecraft", name);
    }

    /**
     * Returns a {@link ResourceLocation} by inferring the namespace from the provided name.
     * If no namespace is specified, "minecraft" will be used by default.
     *
     * @param name The full name of the resource, including optional namespace.
     * @return     A {@link ResourceLocation} with the inferred namespace.
     */
    public static ResourceLocation resourceWithNamespace(String name)
    {
        return ResourceLocation.parse(name);
    }

    /**
     * Returns a {@link ResourceLocation} with the specified namespace and path.
     * This method allows for explicit control over the namespace and path of a resource.
     *
     * @param namespace The namespace for the resource (for example, "minecraft" or "falloutmc").
     * @param path The path of the resource within the namespace.
     * @return     A {@link ResourceLocation} with the given namespace and path.
     */
    public static ResourceLocation resourceWithNamespace(String namespace, String path)
    {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }

}
