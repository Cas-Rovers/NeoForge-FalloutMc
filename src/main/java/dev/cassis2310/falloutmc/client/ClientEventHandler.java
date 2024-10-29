package dev.cassis2310.falloutmc.client;

import dev.cassis2310.falloutmc.FalloutMc;
import dev.cassis2310.falloutmc.datagen.records.ItemAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import static dev.cassis2310.falloutmc.datagen.datamaps.FalloutMcDataMaps.ITEM_WEIGHT;

@EventBusSubscriber(modid = FalloutMc.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientEventHandler
{

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event)
    {
        ItemStack stack = event.getItemStack();
        Holder<Item> holder = stack.getItemHolder();
        ItemAttributes data = holder.getData(ITEM_WEIGHT);

        if (data != null)
        {
            event.getToolTip().add(Component.translatable("tooltip.item.weight", data.weight())
                    .withStyle(ChatFormatting.GRAY));
        }
    }
}
