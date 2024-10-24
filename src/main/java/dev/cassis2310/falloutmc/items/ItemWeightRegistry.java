package dev.cassis2310.falloutmc.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

public class ItemWeightRegistry
{
    private static final Map<Item, Double> itemWeights = new HashMap<>();

    static {
        // Assign weights for vanilla items
        itemWeights.put(Items.APPLE, 0.1);
        itemWeights.put(Items.STONE, 0.5);
        itemWeights.put(Items.DIAMOND, 1.0);
        // Add more items and their weights as needed
    }

    public static Double getWeight(Item item) {
        return itemWeights.getOrDefault(item, 0.0); // Default weight if not set
    }
}
