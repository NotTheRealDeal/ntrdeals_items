package net.ntrdeal.ntrdeals_items.config;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.Equippable;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public record InfusionEntry(Map<Holder<Attribute>, InfusionData> attributes) {
    @Nullable
    public ItemAttributeModifiers getAttributes(ItemStack stack) {
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        InfusibleEntry entry = InfusibleConfig.getEntry(stack);
        if (equippable == null || entry == null) return null;
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        this.attributes.forEach((holder, data) -> builder.add(holder, new AttributeModifier(
                NTRDealsItems.id(equippable.slot().getSerializedName() + "_infusion"), entry.multiply(data), AttributeModifier.Operation.ADD_VALUE
        ), EquipmentSlotGroup.bySlot(equippable.slot())));

        return builder.build();
    }

    public enum TYPE implements StringRepresentable {
        POSITIVE("positive"),
        NEGATIVE("negative");

        public static final Codec<TYPE> CODEC = StringRepresentable.fromEnum(TYPE::values);

        private final String id;

        TYPE(String id) {
            this.id = id;
        }

        @Override
        public String getSerializedName() {
            return this.id;
        }
    }
}
