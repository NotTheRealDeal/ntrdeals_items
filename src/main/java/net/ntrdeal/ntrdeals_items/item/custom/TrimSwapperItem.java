package net.ntrdeal.ntrdeals_items.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.level.Level;
import net.ntrdeal.ntrdeals_items.config.InfusibleConfig;
import net.ntrdeal.ntrdeals_items.config.InfusionConfig;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import net.ntrdeal.ntrdeals_items.item.component.custom.TrimSwapper;

import java.util.HashMap;
import java.util.Objects;

public class TrimSwapperItem extends Item {
    public TrimSwapperItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack trimSwapper = player.getItemInHand(hand);
        HashMap<EquipmentSlot, ArmorTrim> slotMap = trimSwapper.getOrDefault(ModDataComponents.TRIM_SWAPPER, TrimSwapper.EMPTY).toHashMap();

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = player.getItemBySlot(slot);
            if (stack.isEmpty()) continue;

            if (stack.is(ItemTags.TRIMMABLE_ARMOR) || InfusibleConfig.getEntry(stack) != null) {
                ArmorTrim itemsTrim = stack.get(DataComponents.TRIM);
                ArmorTrim slotsTrim = slotMap.get(slot);
                if (Objects.equals(itemsTrim, slotsTrim)) continue;
                stack.set(DataComponents.TRIM, slotsTrim);
                if (itemsTrim == null) slotMap.remove(slot);
                else slotMap.put(slot, itemsTrim);
                InfusionConfig.refreshInfusion(stack);
            }
        }

        if (slotMap.isEmpty()) trimSwapper.remove(ModDataComponents.TRIM_SWAPPER);
        else trimSwapper.set(ModDataComponents.TRIM_SWAPPER, TrimSwapper.fromHashMap(slotMap));

        return InteractionResult.SUCCESS;
    }
}
