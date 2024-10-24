package dev.cassis2310.falloutmc.datagen;

import dev.cassis2310.falloutmc.blocks.FalloutMcBlocks;
import dev.cassis2310.falloutmc.items.FalloutMcItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class FalloutMcBlockLootTableProvider extends BlockLootSubProvider
{

    protected FalloutMcBlockLootTableProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        // Example 1:
        //dropSelf(FalloutMcBlocks.NUCLEAR_WASTE.get);

        // Example 2:
        //add(FalloutMcBlocks.URANIUM_ORE.get(),
        //        block -> createOreDrop(FalloutMcBlocks.URANIUM_ORE.get(), FalloutMcItems.RAW_URANIUM.get()));

        // Example 3:
        //add(FalloutMcBlocks.URANIUM_DEEPSLATE_ORE.get(),
        //        block -> createMultipleOreDrops(FalloutMcBlocks.URANIUM_DEEPSLATE_ORE.get(), FalloutMcItems.RAW_URANIUM.get(), 2, 5));
    }

    // Helper
    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops)
    {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return FalloutMcBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
