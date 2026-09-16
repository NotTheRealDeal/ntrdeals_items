package net.ntrdeal.ntrdeals_items.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.waypoints.WaypointTransmitter;
import net.ntrdeal.ntrdeals_items.entity.ModAttributes;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import net.ntrdeal.ntrdeals_items.item.component.custom.InfuseAttributes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, WaypointTransmitter {
    @Shadow @Final private AttributeMap attributes;
    @Shadow public abstract double getAttributeValue(Holder<Attribute> attribute);

    public LivingEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @WrapMethod(method = "createLivingAttributes")
    private static AttributeSupplier.Builder ntrdeal$addAttributes(Operation<AttributeSupplier.Builder> original) {
        return original.call()
                .add(ModAttributes.PASSIVE_REGENERATION)
                .add(ModAttributes.CLAMPED_SCALE);
    }

    @WrapMethod(method = "sanitizeScale")
    private float ntrdeal$clampedScale(float scale, Operation<Float> original) {
        return original.call(scale) + (float) this.getAttributeValue(ModAttributes.CLAMPED_SCALE);
    }

    @WrapOperation(method = "collectEquipmentChanges", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;forEachModifier(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V"))
    private void ntrdeal$addAttributes(ItemStack stack, EquipmentSlot slot, BiConsumer<Holder<Attribute>, AttributeModifier> consumer, Operation<Void> original) {
        original.call(stack, slot, consumer);
        if (stack.get(ModDataComponents.INFUSE_ATTRIBUTES) instanceof InfuseAttributes(ItemAttributeModifiers attributeModifiers)) {
            attributeModifiers.forEach(slot, (attribute, modifier) -> {
                AttributeInstance instance = this.attributes.getInstance(attribute);
                if (instance != null) {
                    instance.removeModifier(modifier.id());
                    instance.addTransientModifier(modifier);
                }
            });
        }
    }

    @Inject(method = "stopLocationBasedEffects", at = @At("TAIL"))
    private void ntrdeal$removeAttributes(ItemStack previous, EquipmentSlot inSlot, AttributeMap attributes, CallbackInfo ci) {
        if (previous.get(ModDataComponents.INFUSE_ATTRIBUTES) instanceof InfuseAttributes(ItemAttributeModifiers attributeModifiers)) {
            attributeModifiers.forEach(inSlot, (attribute, modifier) -> {
                if (attributes.getInstance(attribute) instanceof AttributeInstance instance) {
                    instance.removeModifier(modifier);
                }
            });
        }
    }
}