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
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Provides loot table generation for blocks in the Fallout Minecraft mod.
 * This class defines how different blocks drop items when broken, including
 * configurations for ores and other block types.
 */
public class FalloutMcBlockLootTableProvider extends BlockLootSubProvider
{
    /**
     * Creates a new block loot table provider.
     *
     * @param registries the registry lookup provider for accessing various registries.
     */
    protected FalloutMcBlockLootTableProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    /**
     * Generates the loot tables for the blocks defined in this provider.
     * This method should be overridden to add specific block loot configurations.
     *
     * <h3>Usage Examples:</h3>
     * <p>
     * <h4>Example 1 (Drop self):</h4>
     * <pre>{@code
     * dropSelf(FalloutMcBlocks.NUCLEAR_WASTE.get());
     * }</pre>
     *
     *<h4>Example 2 (Ore drop):</h4>
     *<pre>{@code
     * add(FalloutMcBlocks.URANIUM_ORE.get(),
     *   block -> createOreDrop(FalloutMcBlocks.URANIUM_ORE.get(), FalloutMcItems.RAW_URANIUM.get()));
     * }</pre>
     *
     *<h4>Example 3 (Multiple ore drops):</h4>
     *<pre>{@code
     *add(FalloutMcBlocks.URANIUM_DEEPSLATE_ORE.get(),
     *     block -> createMultipleOreDrops(
     *         FalloutMcBlocks.URANIUM_DEEPSLATE_ORE.get(),
     *         FalloutMcItems.RAW_URANIUM.get(),
     *         2,
     *         5
     *     )
     *);
     *}</pre>
     *</p>
     */
    @Override
    protected void generate()
    {
        //
    }

    /**
     * Creates a loot table builder for blocks that drop multiple items when mined,
     * based on the enchantment fortune level.
     *
     * @param pBlock    the block to create drops for
     * @param item      the item to drop
     * @param minDrops  the minimum number of items to drop
     * @param maxDrops  the maximum number of items to drop
     * @return a loot table builder for the specified block and item
     */
    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops)
    {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    /**
     * Returns an iterable of known blocks that this provider can generate loot tables for.
     *
     * @return an iterable of blocks known to this provider
     */
    @Override
    protected @NotNull Iterable<Block> getKnownBlocks()
    {
        return FalloutMcBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
