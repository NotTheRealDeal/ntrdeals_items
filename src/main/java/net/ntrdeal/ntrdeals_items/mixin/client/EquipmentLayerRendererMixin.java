package net.ntrdeal.ntrdeals_items.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.OrderedSubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.ntrdeal.ntrdeals_items.client.ModRenderTypes;
import net.ntrdeal.ntrdeals_items.item.equipment.ModEquipmentAssets;
import net.ntrdeal.realapi.data.mixin.RealMixin;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EquipmentLayerRenderer.class)
public class EquipmentLayerRendererMixin implements RealMixin<EquipmentLayerRenderer> {
    @WrapOperation(method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;II)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/OrderedSubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;IIILnet/minecraft/client/renderer/texture/TextureAtlasSprite;ILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V", ordinal = 0))
    private <S> void ntrdeal$renderCosmolite(OrderedSubmitNodeCollector collector, Model<? super S> model, S state, PoseStack poseStack, RenderType type, int light, int overlay, int color, @Nullable TextureAtlasSprite sprite, int outlineColor, ModelFeatureRenderer.@Nullable CrumblingOverlay crumbling, Operation<Void> original, @Local(argsOnly = true, name = "equipmentAssetId") ResourceKey<EquipmentAsset> equipmentAssetId) {
        original.call(collector, model, state, poseStack, type, light, overlay, color, sprite, outlineColor, crumbling);
        if (equipmentAssetId.equals(ModEquipmentAssets.COSMOLITE)) original.call(collector, model, state, poseStack, ModRenderTypes.COSMOLITE, light, overlay, color, sprite, outlineColor, crumbling);
    }

    @WrapOperation(method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;II)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/OrderedSubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;IIILnet/minecraft/client/renderer/texture/TextureAtlasSprite;ILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V", ordinal = 2))
    private <S> void  ntrdeal$bigBrainTime(OrderedSubmitNodeCollector collector, Model<? super S> model, S state, PoseStack poseStack, RenderType type, int light, int overlay, int color, @Nullable TextureAtlasSprite sprite, int outlineColor, ModelFeatureRenderer.@Nullable CrumblingOverlay crumbling, Operation<Void> original, @Local(argsOnly = true, name = "equipmentAssetId") ResourceKey<EquipmentAsset> equipmentAssetId, @Local(name = "trim") ArmorTrim trim) {
        if (equipmentAssetId.equals(ModEquipmentAssets.COSMOLITE)) original.call(collector, model, state, poseStack, ModRenderTypes.armorTrimsSheet(trim.pattern().value().decal()), light, overlay, color, sprite, outlineColor, crumbling);
        else original.call(collector, model, state, poseStack, type, light, overlay, color, sprite, outlineColor, crumbling);
    }
}
