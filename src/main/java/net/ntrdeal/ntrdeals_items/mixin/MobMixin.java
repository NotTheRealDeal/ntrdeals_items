package net.ntrdeal.ntrdeals_items.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import net.ntrdeal.ntrdeals_items.item.component.custom.InfuseAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity implements Targeting, EquipmentUser, Leashable {
    protected MobMixin(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @WrapOperation(method = "getApproximateAttributeWith", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/ItemAttributeModifiers;compute(Lnet/minecraft/core/Holder;DLnet/minecraft/world/entity/EquipmentSlot;)D"))
    private double ntrdeal$addInfusions(ItemAttributeModifiers modifiers, Holder<Attribute> attribute, double baseValue, EquipmentSlot slot, Operation<Double> original, @Local(argsOnly = true, name = "itemStack") ItemStack itemStack) {
        double infusions = itemStack.getOrDefault(ModDataComponents.INFUSE_ATTRIBUTES, InfuseAttributes.EMPTY).attributes().compute(attribute, baseValue, slot) - baseValue;
        return original.call(modifiers, attribute, baseValue, slot) + infusions;
    }
}