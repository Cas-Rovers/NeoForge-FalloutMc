package dev.cassis2310.falloutmc.client;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.datagen.codecs.items.Consumables.*;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import static dev.cassis2310.falloutmc.datagen.datamaps.FalloutMcDataMaps.*;

@EventBusSubscriber(modid = FalloutMc.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientEventHandler
{

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event)
    {
        ItemStack stack = event.getItemStack();
        Holder<Item> holder = stack.getItemHolder();
        ItemAttributes itemData = holder.getData(ITEM_ATTRIBUTES);
        FoodAttributes foodData = holder.getData(FOOD_ATTRIBUTES);
        SoupAttributes soupData = holder.getData(SOUP_ATTRIBUTES);


        if (itemData != null)
        {
            event.getToolTip().add(Component.translatable("tooltip.item.weight", itemData.weight())
                    .withStyle(ChatFormatting.GRAY));
        }

        if (foodData != null)
        {
            event.getToolTip().add(Component.translatable("tooltip.item.weight", foodData.weight())
                    .withStyle(ChatFormatting.GRAY));
        }

        if (soupData != null)
        {
            event.getToolTip().add(Component.translatable("tooltip.item.weight", soupData.weight())
                    .withStyle(ChatFormatting.GRAY));
        }
    }
}
