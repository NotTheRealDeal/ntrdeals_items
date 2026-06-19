package net.ntrdeal.ntrdeals_items.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.ntrdeal.ntrdeals_items.config.InfusibleConfig;
import net.ntrdeal.ntrdeals_items.config.InfusionConfig;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import net.ntrdeal.ntrdeals_items.item.component.TrimData;
import net.ntrdeal.ntrdeals_items.item.component.TrimSwapperComponent;

import java.util.Map;

public class TrimSwapperItem extends Item {
    public TrimSwapperItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack trimSwapper = player.getItemInHand(hand);
        Map<EquipmentSlot, TrimData> slotMap = trimSwapper.getOrDefault(ModDataComponents.TRIM_SWAPPER, TrimSwapperComponent.DEFAULT).getMap();

        slotMap.forEach((slot, data) -> {
            ItemStack stack = player.getItemBySlot(slot);
            if (data.isComplete() && (stack.is(ItemTags.TRIMMABLE_ARMOR) || InfusibleConfig.isInfusible(stack) != null)) {
                slotMap.put(slot, TrimData.of(stack.get(DataComponents.TRIM)));
                stack.set(DataComponents.TRIM, data.getTrim());
                InfusionConfig.refreshInfusion(stack);
            }
        });

        TrimSwapperComponent component = TrimSwapperComponent.of(slotMap);
        trimSwapper.set(ModDataComponents.TRIM_SWAPPER, component.isEmpty() ? null : component);

        return InteractionResult.SUCCESS;
    }
}
