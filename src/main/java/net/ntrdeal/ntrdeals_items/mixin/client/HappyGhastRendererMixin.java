package net.ntrdeal.ntrdeals_items.mixin.client;

import net.minecraft.client.model.animal.ghast.HappyGhastModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HappyGhastRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.client.CosmoliteRenderLayer;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import net.ntrdeal.realapi.data.mixin.RealMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HappyGhastRenderer.class)
public abstract class HappyGhastRendererMixin extends MobRenderer<HappyGhast, HappyGhastRenderState, HappyGhastModel> implements RealMixin<HappyGhastRenderer> {
    @Unique private static final Identifier MASK = NTRDealsItems.id(
            "textures/entity/equipment/happy_ghast_body/harness_mask.png"
    );

    public HappyGhastRendererMixin(EntityRendererProvider.Context context, HappyGhastModel adultModel, float shadow) {
        super(context, adultModel, shadow);
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/animal/happyghast/HappyGhast;Lnet/minecraft/client/renderer/entity/state/HappyGhastRenderState;F)V", at = @At("RETURN"))
    private void ntrdeal$addCosmolite(HappyGhast entity, HappyGhastRenderState state, float partialTicks, CallbackInfo ci) {
        if (!state.bodyItem.isEmpty() && state.bodyItem.has(ModDataComponents.COSMOLITE)) state.setData(CosmoliteRenderLayer.MASK, MASK);
    }
}
