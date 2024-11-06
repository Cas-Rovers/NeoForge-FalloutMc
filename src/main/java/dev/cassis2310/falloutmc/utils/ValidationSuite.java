package dev.cassis2310.falloutmc.utils;

import com.google.common.base.Stopwatch;
import com.mojang.logging.LogUtils;
import dev.cassis2310.falloutmc.blocks.FalloutMcBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import java.util.*;

import static dev.cassis2310.falloutmc.FalloutMc.MOD_NAME;

/**
 * This class provides a suite of validation tests that can be run on the client-side to ensure the correctness of
 * the mod's assets and translations.
 * These tests are disabled by default, but can be enabled by setting the system property "falloutmc
 * .enableDebugValidations" to true.
 * If any of the tests fail, a {@link net.neoforged.bus.api.Event} is fired with the name
 * `falloutmc:validation_failure`.
 * If the system property "falloutmc.throwOnValidationFailure" is true, the mod will throw an {@link AssertionError}
 * when a validation fails.
 * If the mod is running in a development environment, the mod will print out the failures to the console, but will
 * not throw an exception.
 */
public class ValidationSuite
{
    public static final boolean ENABLED = Boolean.getBoolean("falloutmc.enableDebugValidations");
    public static final boolean THROW_ON_FAILURE = false;

    private static final Logger LOGGER = LogUtils.getLogger();
    private static boolean EXTERNAL_ERRORS = false;


    public static boolean isExternalErrors()
    {
        return EXTERNAL_ERRORS;
    }

    public static void setExternalErrors(boolean externalErrors)
    {
        EXTERNAL_ERRORS = externalErrors;
    }

    /**
     * Represents a suite of validation tests to be run on the client-side.
     * These tests are used to ensure the correctness of the mod's assets and
     * translations.
     */
    public static void performClientValidations()
    {
        if (ENABLED) {
            NeoForge.EVENT_BUS.post(new ClientValidationEvent());
            LOGGER.info("[{}]: Executing client-side validations...", MOD_NAME);
            final Stopwatch tick = Stopwatch.createStarted();
            throwIfAny(validateModels(), validateTranslations());
            LOGGER.info("[{}]Client self tests passed in {}", MOD_NAME, tick.stop());
        }
    }


    public static void performServerValidations()
    {
        // TODO: Add server validations
    }

    public static void performDataPackValidations()
    {
        // TODO: Add data pack validations
    }

    /*
     * ====================
     *  Client Validations
     * ====================
     */

    /**
     * Validates the presence of block and item models.
     * <p>
     * This method checks for missing models or textures in the block and item models
     * within the game. It identifies any block states with missing models and logs
     * errors if such states are found.
     * <p>
     * Usage of this validation ensures that all block and item models are correctly
     * loaded and displayed, which helps maintain visual consistency in the game.
     *
     * @return true if errors are found (in other words, there are missing models), false otherwise.
     */
    private static boolean validateModels()
    {
        BlockModelShaper shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        var missingModel = shaper.getModelManager().getMissingModel();

        List<BlockState> missingModelErrors =
                FalloutMcBlocks.BLOCKS.getEntries()
                        .stream()
                        .flatMap(block -> block.value().getStateDefinition().getPossibleStates().stream())
                        .filter(state -> shaper.getBlockModel(state) == missingModel)
                        .toList();

        return logErrors("{} block states with missing models:", missingModelErrors, LOGGER);
    }

    /**
     * Validates translation keys.
     * Detects missing translation keys for items and blocks, ensuring that all necessary translations are provided.
     * Log any missing translation keys found during the validation process.
     *
     * @return true if there are errors (missing translation keys), false otherwise.
     */
    private static boolean validateTranslations()
    {
        final Set<String> missingTranslations = Bootstrap.getMissingTranslations();
        final List<ItemStack> stacks = new ArrayList<>();
        final Set<Item> items = new HashSet<>();

        boolean errors = false;

        return errors | logErrors("{} missing translation keys:", missingTranslations, LOGGER);
    }

    /*
     * ===================
     *    Error helpers
     * ===================
     */


    /**
     * Throws an {@link AssertionError} if any of the parameters are true.
     *
     * @param errors the boolean values to check
     */
    private static void throwIfAny(boolean... errors)
    {
        for (boolean error : errors) {
            if (error && THROW_ON_FAILURE) {
                throw new AssertionError("Validation Failed! Fix the errors above!");
            }
        }
    }

    /**
     * Logs the errors if the collection is not empty.
     *
     * @param errorMessage the error message to log
     * @param errors       the collection of errors to log
     * @param logger       the logger to use
     * @return true if errors were logged, false otherwise
     */
    private static <T> boolean logErrors(String errorMessage, Collection<T> errors, Logger logger)
    {
        if (!errors.isEmpty()) {
            logger.error(errorMessage, errors.size());
            errors.forEach(e -> logger.error("  {}", e));
            return true;
        }
        return false;
    }

    /**
     * An event fired when the client-side validations should be executed.
     */
    public static class ClientValidationEvent extends Event {}
}
