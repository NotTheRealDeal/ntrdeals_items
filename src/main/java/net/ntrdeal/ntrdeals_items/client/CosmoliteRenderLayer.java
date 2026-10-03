package net.ntrdeal.ntrdeals_items.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityRenderLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.animal.ghast.HappyGhastHarnessModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.HappyGhastRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class CosmoliteRenderLayer<S extends LivingEntityRenderState, RM extends EntityModel<? super S>, EM extends EntityModel<? super S>> extends RenderLayer<S, RM> {
    public static final RenderStateDataKey<Identifier> MASK = RenderStateDataKey.create();

    private final EM model;


    public CosmoliteRenderLayer(RenderLayerParent<S, RM> renderer, EM model) {
        super(renderer);
        this.model = model;
    }

    @Override
    public void submit(
            PoseStack stack, SubmitNodeCollector collector,
            int light, S state, float yRot, float xRot
    ) {
        if (state.isBaby || state.isInvisible) return;
        Identifier mask = state.getData(MASK);
        if (mask != null) collector.order(1).submitModel(
                this.model, state, stack, ModRenderTypes.cosmolite(mask, true),
                light, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor
        );
    }

    public static void register() {
        LivingEntityRenderLayerRegistrationCallback.EVENT.register((
                _, entityRenderer,
                helper, context
        ) -> {
            if (entityRenderer instanceof HappyGhastRenderer renderer) helper.register(new CosmoliteRenderLayer<>(
                    renderer, new HappyGhastHarnessModel(context.bakeLayer(ModelLayers.HAPPY_GHAST_HARNESS))
            ));
        });
    }
}
