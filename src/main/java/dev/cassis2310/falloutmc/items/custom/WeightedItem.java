    package dev.cassis2310.falloutmc.items.custom;

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
        private final ItemType itemType;
        private final double defaultWeight;

        public WeightedItem(Properties properties, double defaultWeight, ItemType itemType)
        {
            super(properties);
            this.defaultWeight = defaultWeight;
            this.itemType = itemType;
        }

        public double getWeight()
        {
            return this.defaultWeight;
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
