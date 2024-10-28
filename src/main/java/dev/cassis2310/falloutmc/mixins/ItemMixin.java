package dev.cassis2310.falloutmc.mixins;

import dev.cassis2310.falloutmc.datagen.records.ItemWeightData;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static dev.cassis2310.falloutmc.datagen.datamaps.FalloutMcDataMaps.ITEM_WEIGHT;

@Mixin(Item.class)
public class ItemMixin
{
 //
}
