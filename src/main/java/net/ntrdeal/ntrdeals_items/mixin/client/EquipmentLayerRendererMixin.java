package net.ntrdeal.ntrdeals_items.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.ntrdeal.ntrdeals_items.client.ModRenderTypes;
import net.ntrdeal.ntrdeals_items.item.equipment.ModEquipmentAssets;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(EquipmentLayerRenderer.class)
public class EquipmentLayerRendererMixin {
    @ModifyVariable(
            method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;II)V",
            at = @At("LOAD"), slice = @Slice(from = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;")), name = "hasTrim"
    ) private <S> boolean injectBeforeTrimCheck(
            boolean hasTrim,
            @Local(argsOnly = true, name = "submitNodeCollector") SubmitNodeCollector submitNodeCollector, @Local(argsOnly = true, name = "model") Model<S> model,
            @Local(argsOnly = true, name = "state") S state, @Local(argsOnly = true, name = "poseStack") PoseStack poseStack,
            @Local(argsOnly = true, name = "lightCoords") int lightCoords, @Local(argsOnly = true, name = "outlineColor") int outlineColor,
            @Local(argsOnly = true, name = "equipmentAssetId") ResourceKey<EquipmentAsset> equipmentAssetId, @Local(name = "nextOrder") LocalIntRef nextOrder
    ) {
        if (equipmentAssetId.equals(ModEquipmentAssets.COSMOLITE)) {
            int order = nextOrder.get();

            submitNodeCollector.order(order).submitModel(
                    model, state, poseStack, ModRenderTypes.COSMOLITE, lightCoords,
                    OverlayTexture.NO_OVERLAY, 0, null, outlineColor
            );

            nextOrder.set(order + 1);
        }

        return hasTrim;
    }

    @WrapOperation(
            method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;II)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;armorTrim(Lnet/minecraft/resources/Identifier;Z)Lnet/minecraft/client/renderer/rendertype/RenderType;")
    ) private RenderType ntrdeal$replaceType(
            Identifier texture, boolean decal, Operation<RenderType> original,
            EquipmentClientInfo.LayerType layerType, ResourceKey<EquipmentAsset> equipmentAssetId
    ) {
        return equipmentAssetId.equals(ModEquipmentAssets.COSMOLITE) ? ModRenderTypes.armorTrimsSheet(texture, decal) : original.call(texture, decal);
    }
}