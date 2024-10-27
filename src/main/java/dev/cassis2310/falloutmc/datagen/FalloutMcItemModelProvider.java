package dev.cassis2310.falloutmc.datagen;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.items.FalloutMcItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

/**
 * Provides item model generation for the Fallout Minecraft mod.
 * This class handles the creation and registration of item models,
 * allowing for custom visual representations of items in-game.
 */
public class FalloutMcItemModelProvider extends ItemModelProvider
{
    /**
     * Creates a new item model provider.
     *
     * @param output the output pack for the generated item models
     * @param existingFileHelper a helper for managing existing files
     */
    public FalloutMcItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, FalloutMc.MOD_ID, existingFileHelper);
    }

    /**
     * Registers item models for the mod's items.
     * This method is overridden to define the models for specific items.
     *
     * <h3>Usage Examples:</h3>
     * <p>
     * <h4>Example 1 (Basic Item):</h4>
     * <pre>{@code
     * basicItem(FalloutMcItems.NUKA_COLA.get());
     * }</pre>
     * </p>
     */
    @Override
    protected void registerModels()
    {
        basicItem(FalloutMcItems.NUKA_COLA.get());
    }
}
