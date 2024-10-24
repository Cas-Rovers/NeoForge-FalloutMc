    package dev.cassis2310.falloutmc.items.custom;

    import dev.cassis2310.falloutmc.items.ItemWeightRegistry;
    import dev.cassis2310.falloutmc.items.enums.ItemType;
    import net.minecraft.ChatFormatting;
    import net.minecraft.network.chat.Component;
    import net.minecraft.world.item.Item;
    import net.minecraft.world.item.ItemStack;
    import net.minecraft.world.item.TooltipFlag;
    import net.minecraft.world.item.UseAnim;
    import org.jetbrains.annotations.NotNull;

    import java.util.List;

    public class WeightedItem extends Item
    {
        private final double weight;
        private final ItemType itemType;

        public WeightedItem(Properties properties, double weight, ItemType itemType)
        {
            super(properties);
            this.weight = weight;
            this.itemType = itemType;
        }

        public double getWeight()
        {
            return this.weight;
        }

        public ItemType getItemType()
        {
            return this.itemType;
        }

        @Override
        public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag)
        {
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

            tooltipComponents.add(Component.translatable("tooltip.item.item_type", getItemType().toString()).withStyle(ChatFormatting.BLUE));
            tooltipComponents.add(Component.translatable("tooltip.item.weight", getWeight()).withStyle(ChatFormatting.GRAY));
        }

        @Override
        public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack)
        {
            if (getItemType() == ItemType.DRINK) {
                return UseAnim.DRINK;
            }

            if (getItemType() == ItemType.FOOD) {
                return UseAnim.EAT;
            }

            return super.getUseAnimation(stack);
        }
    }
