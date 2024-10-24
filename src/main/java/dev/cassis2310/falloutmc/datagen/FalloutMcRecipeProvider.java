package dev.cassis2310.falloutmc.datagen;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.blocks.FalloutMcBlocks;
import dev.cassis2310.falloutmc.items.FalloutMcItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FalloutMcRecipeProvider extends RecipeProvider implements IConditionBuilder
{

    public FalloutMcRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput)
    {
        List<ItemLike> NUCLEAR_SMELTABLES = List.of(
                // List of blocks
        );

        // Example 1 (Shaped):
        //ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FalloutMcBlocks.NUCLEAR_WASTE.get())
        //        .pattern("NNN")
        //        .pattern("NNN")
        //        .pattern("NNN")
        //        .define("N", FalloutMcItems.NUCLEAR_SCRAP.get())
        //        .unlockedBy("has_nuclear_scrap", has(FalloutMcItems.NUCLEAR_SCRAP)).save(recipeOutput);

        // Example 2 (Shapeless):
        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FalloutMcItems.NUCLEAR_SCRAP.get(), 9)
        //        .requires(FalloutMcBlocks.NUCLEAR_WASTE.get())
        //        .unlockedBy("has_nuclear_waste", has(FalloutMcBlocks.NUCLEAR_WASTE)).save(recipeOutput);

        // Example 3 (Shapeless with custom name):
        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FalloutMcItems.NUCLEAR_SCRAP.get(), 16)
        //        .requires(FalloutMcBlocks.COMPACT_NUCLEAR_WASTE.get())
        //        .unlockedBy("has_nuclear_waste", has(FalloutMcBlocks.COMPACT_NUCLEAR_WASTE))
        //        .save(recipeOutput, "falloutmc:nuclear_waste_from_compact_nuclear_waste");

        // Example 4 (smelting and blasting using the smeltables list):
        //oreSmelting(recipeOutput, NUCLEAR_SMELTABLES, RecipeCategory.MISC, FalloutMcItems.NUCLEAR_SCRAP.get(), 0.25f, 200, "nuclear_crafts");
        //oreBlasting(recipeOutput, NUCLEAR_SMELTABLES, RecipeCategory.MISC, FalloutMcItems.NUCLEAR_SCRAP.get(), 0.25f, 200, "nuclear_crafts");
    }

    protected static void oreSmelting(@NotNull RecipeOutput recipeOutput, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult,
                                      float pExperience, int pCookingTIme, @NotNull String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(@NotNull RecipeOutput recipeOutput, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult,
                                      float pExperience, int pCookingTime, @NotNull String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(@NotNull RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.@NotNull Factory<T> factory,
                                                                       List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, FalloutMc.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}