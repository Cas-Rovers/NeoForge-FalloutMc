package dev.cassis2310.falloutmc.datagen;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.items.FalloutMcItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class FalloutMcItemModelProvider extends ItemModelProvider
{

    public FalloutMcItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, FalloutMc.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        basicItem(FalloutMcItems.NUKA_COLA.get());
    }
}
