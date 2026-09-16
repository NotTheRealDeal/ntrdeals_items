package net.ntrdeal.ntrdeals_items.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SimpleSmithingRecipe;
import net.minecraft.world.item.crafting.SmithingTrimRecipe;
import net.ntrdeal.ntrdeals_items.config.InfusionConfig;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SmithingTrimRecipe.class)
public abstract class SmithingTrimRecipeMixin extends SimpleSmithingRecipe {
    protected SmithingTrimRecipeMixin(CommonInfo commonInfo) {
        super(commonInfo);
    }

    @WrapOperation(method = "applyTrim", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;set(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;"))
    private static <T> T ntrdeal$addInfusions(ItemStack stack, DataComponentType<T> type, @Nullable T value, Operation<T> original) {
        T trim = original.call(stack, type, value);
        InfusionConfig.refreshInfusion(stack);
        return trim;
    }
}