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

/**
 * Provides recipe generation for the Fallout Minecraft mod.
 * This class handles the creation and registration of various crafting recipes,
 * including shaped, shapeless, and smelting recipes.
 */
public class FalloutMcRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    /**
     * Creates a new recipe provider.
     *
     * @param output    the output pack for the generated recipes
     * @param registries a CompletableFuture for the registry provider
     */
    public FalloutMcRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries);
    }

    /**
     * Builds the crafting recipes for the mod.
     * This method defines how recipes are created and added to the recipe output.
     *
     * <h3>Usage Examples:</h3>
     * <p>
     * <h4>Example 4 (Shaped recipe):</h4>
     * <pre>{@code
     * ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FalloutMcBlocks.NUCLEAR_WASTE.get())
     *    .pattern("NNN")
     *    .pattern("NNN")
     *    .pattern("NNN")
     *    .define("N", FalloutMcItems.NUCLEAR_SCRAP.get())
     *    .unlockedBy("has_nuclear_scrap", has(FalloutMcItems.NUCLEAR_SCRAP))
     *    .save(recipeOutput);
     * }</pre>
     *
     * <h4>Example 5 (Shapeless recipe):</h4>
     * <pre>{@code
     * ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FalloutMcItems.NUCLEAR_SCRAP.get(), 9)
     *    .requires(FalloutMcBlocks.NUCLEAR_WASTE.get())
     *    .unlockedBy("has_nuclear_waste", has(FalloutMcBlocks.NUCLEAR_WASTE))
     *    .save(recipeOutput);
     * }</pre>
     *
     * <h4>Example 6 (Shapeless recipe with custom name):</h4>
     * <pre>{@code
     * ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FalloutMcItems.NUCLEAR_SCRAP.get(), 16)
     *    .requires(FalloutMcBlocks.COMPACT_NUCLEAR_WASTE.get())
     *    .unlockedBy("has_nuclear_waste", has(FalloutMcBlocks.COMPACT_NUCLEAR_WASTE))
     *    .save(recipeOutput, "falloutmc:nuclear_waste_from_compact_nuclear_waste");
     * }</pre>
     *
     * <h4>Example 7 (Smelting):</h4>
     * <pre>{@code
     * oreSmelting(recipeOutput, NUCLEAR_SMELTABLES, RecipeCategory.MISC,
     *      FalloutMcItems.NUCLEAR_SCRAP.get(), 0.25f, 200, "nuclear_crafts");
     * }</pre>
     *
     * <h4>Example 8 (Blasting):</h4>
     * <pre>{@code
     * oreBlasting(recipeOutput, NUCLEAR_SMELTABLES, RecipeCategory.MISC,
     *      FalloutMcItems.NUCLEAR_SCRAP.get(), 0.25f, 200, "nuclear_crafts");
     * }</pre>
     * </p>
     * @param recipeOutput the output for saving the generated recipes
     */
    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput)
    {
        //List<ItemLike> NUCLEAR_SMELTABLES = List.of(
        //        // List of blocks
        //);
    }

    /**
     * Adds smelting recipes for the specified ingredients.
     *
     * @param recipeOutput  the output for saving the generated recipes
     * @param pIngredients  a list of ingredients to smelt
     * @param pCategory     the category for the resulting item
     * @param pResult       the resulting item after smelting
     * @param pExperience   the experience gained from smelting
     * @param pCookingTime  the time taken to smelt
     * @param pGroup        the group name for the recipe
     */
    protected static void oreSmelting(@NotNull RecipeOutput recipeOutput, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    /**
     * Adds blasting recipes for the specified ingredients.
     *
     * @param recipeOutput  the output for saving the generated recipes
     * @param pIngredients  a list of ingredients to blast
     * @param pCategory     the category for the resulting item
     * @param pResult       the resulting item after blasting
     * @param pExperience   the experience gained from blasting
     * @param pCookingTime  the time taken to blast
     * @param pGroup        the group name for the recipe
     */
    protected static void oreBlasting(@NotNull RecipeOutput recipeOutput, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    /**
     * A generic method to handle the creation of cooking recipes.
     *
     * @param recipeOutput       the output for saving the generated recipes
     * @param pCookingSerializer the serializer for the cooking recipe
     * @param factory            the factory for creating recipe instances
     * @param pIngredients       a list of ingredients for the recipe
     * @param pCategory          the category for the resulting item
     * @param pResult            the resulting item after cooking
     * @param pExperience        the experience gained from cooking
     * @param pCookingTime       the time taken to cook
     * @param pGroup             the group name for the recipe
     * @param pRecipeName        the suffix to add to the recipe name
     * @param <T>               the type of cooking recipe
     */
    protected static <T extends AbstractCookingRecipe> void oreCooking(@NotNull RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.@NotNull Factory<T> factory, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, FalloutMc.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
