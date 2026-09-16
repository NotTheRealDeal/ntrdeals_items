package net.ntrdeal.ntrdeals_items.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import net.ntrdeal.ntrdeals_items.item.component.custom.ChorusMeter;

public class ChorusSwordItem extends Item {
    public ChorusSwordItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        ChorusMeter meter = self.getOrDefault(ModDataComponents.CHORUS_METER, ChorusMeter.EMPTY);
        if (meter.chorus() >= ChorusMeter.EMPTY.chorus() || !other.is(Items.CHORUS_FRUIT)) return super.overrideOtherStackedOnMe(self, other, slot, action, player, access);
        ItemStack used = other.split(1);
        if (used.isEmpty()) return super.overrideOtherStackedOnMe(self, other, slot, action, player, access);
        player.playSound(SoundEvents.CHORUS_FLOWER_GROW, 1f, 1f);
        meter.add(self, 4);
        return true;
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack self, Slot slot, ClickAction action, Player player) {
        ItemStack other = slot.getItem();
        ChorusMeter meter = self.getOrDefault(ModDataComponents.CHORUS_METER, ChorusMeter.EMPTY);
        if (meter.chorus() >= ChorusMeter.EMPTY.chorus() || !other.is(Items.CHORUS_FRUIT)) return super.overrideStackedOnOther(self, slot, action, player);
        ItemStack used = other.split(1);
        if (used.isEmpty()) return super.overrideStackedOnOther(self, slot, action, player);
        player.playSound(SoundEvents.CHORUS_FLOWER_GROW, 1f, 1f);
        meter.add(self, 4);
        return true;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0x8e678d;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return ChorusMeter.getChorus(stack) < ChorusMeter.EMPTY.chorus();
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(((float) ChorusMeter.getChorus(stack) / ChorusMeter.EMPTY.chorus()) * 13);
    }
}
