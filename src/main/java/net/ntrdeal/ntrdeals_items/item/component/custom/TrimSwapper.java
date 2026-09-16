package net.ntrdeal.ntrdeals_items.item.component.custom;

import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.equipment.trim.ArmorTrim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public record TrimSwapper(Map<EquipmentSlot, ArmorTrim> trimMap) implements TooltipProvider {
    private static final Component UPGRADE_TEXT = Component.translatable(
            Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.upgrade"))
    ).withStyle(ChatFormatting.GRAY);

    public static final TrimSwapper EMPTY = new TrimSwapper(Map.of());
    public static final List<EquipmentSlot> REVERSE_VALUES = EquipmentSlot.VALUES.reversed();

    public static final Codec<TrimSwapper> CODEC = Codec.unboundedMap(EquipmentSlot.CODEC, ArmorTrim.CODEC).xmap(TrimSwapper::new, TrimSwapper::trimMap);
    public static final StreamCodec<RegistryFriendlyByteBuf, TrimSwapper> STREAM_CODEC = ByteBufCodecs.map(
            HashMap::new, EquipmentSlot.STREAM_CODEC, ArmorTrim.STREAM_CODEC
    ).map(TrimSwapper::fromHashMap, TrimSwapper::toHashMap);

    public static TrimSwapper fromHashMap(HashMap<EquipmentSlot, ArmorTrim> hashMap) {
        return new TrimSwapper(Map.copyOf(hashMap));
    }

    public HashMap<EquipmentSlot, ArmorTrim> toHashMap() {
        return new HashMap<>(this.trimMap);
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        consumer.accept(UPGRADE_TEXT);

        for (EquipmentSlot slot : REVERSE_VALUES) {
            if (this.trimMap.containsKey(slot)) appendData(consumer, this.trimMap.get(slot), Component.translatable(
                    "item.modifiers." + slot.getSerializedName()
            ).withStyle(ChatFormatting.GRAY));
        }
    }

    private static void appendData(Consumer<Component> tooltip, ArmorTrim trim, Component translatable){
        tooltip.accept(CommonComponents.space().append(translatable));
        tooltip.accept(CommonComponents.space().append(CommonComponents.space().append(trim.pattern().value().copyWithStyle(trim.material()))));
        tooltip.accept(CommonComponents.space().append(CommonComponents.space().append(trim.material().value().description())));
    }
}
