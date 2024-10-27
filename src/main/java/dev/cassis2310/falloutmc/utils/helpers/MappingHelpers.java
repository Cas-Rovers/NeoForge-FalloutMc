package dev.cassis2310.falloutmc.utils.helpers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.mojang.logging.LogUtils;
import net.minecraft.util.RandomSource;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import org.slf4j.Logger;
import java.util.stream.Collectors;

public class MappingHelpers
{
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LogUtils.getLogger();

    private MappingHelpers() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Creates a map from an enum class to values provided by a value mapper.
     * This method ensures consistent iteration order in the map.
     *
     * @param enumClass   The enum class to map.
     * @param valueMapper A function that maps each enum constant to a value.
     * @param <E>         The enum type.
     * @param <V>         The value type.
     * @return            A map of enum constants to values.
     */
    public static <E extends Enum<E>, V> Map<E, V> mapOf(Class<E> enumClass, Function<E, V> valueMapper)
    {
        return mapOf(enumClass, key -> true, valueMapper);
    }

    /**
     * Creates a map from an enum class to values provided by a value mapper, filtering by a predicate.
     * This method allows creating a map with only selected enum constants.
     *
     * @param enumClass    The enum class to map.
     * @param keyPredicate A predicate to filter enum constants.
     * @param valueMapper  A function that maps each enum constant to a value.
     * @param <E>          The enum type.
     * @param <V>          The value type.
     * @return             A map of selected enum constants to values.
     */
    public static <E extends Enum<E>, V> Map<E, V> mapOf(Class<E> enumClass, Predicate<E> keyPredicate, Function<E, V> valueMapper)
    {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(keyPredicate)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                valueMapper,
                                (v, v2) -> ExceptionHelpers.throwAsUnchecked(new AssertionError("Merging elements not allowed!")),
                                () -> new EnumMap<>(enumClass)));
    }

    /**
     * Transforms a map's values using a provided function and returns a new immutable map.
     * This method is useful for converting or transforming data stored in maps.
     *
     * @param map  The original map to transform.
     * @param func The function to apply to each value.
     * @param <K>  The key type.
     * @param <V1> The original value type.
     * @param <V2> The new value type.
     * @return     A new map with transformed values.
     */
    public static <K, V1, V2> Map<K, V2> mapValue(Map<K, V1> map, Function<V1, V2> func)
    {
        final ImmutableMap.Builder<K, V2> builder = ImmutableMap.builderWithExpectedSize(map.size());
        for (Map.Entry<K, V1> entry : map.entrySet())
        {
            builder.put(entry.getKey(), func.apply(entry.getValue()));
        }
        return builder.build();
    }

    /**
     * Returns a random value from a map, using the provided random source.
     * This method is useful for randomly selecting a value from a collection of mapped data.
     *
     * @param map    The map to select from.
     * @param random The random source to use.
     * @param <K>    The key type.
     * @param <V>    The value type.
     * @return       A random value from the map.
     */
    public static <K, V> V getRandomValue(Map<K, V> map, RandomSource random)
    {
        return Iterators.get(map.values().iterator(), random.nextInt(map.size()));
    }
}
