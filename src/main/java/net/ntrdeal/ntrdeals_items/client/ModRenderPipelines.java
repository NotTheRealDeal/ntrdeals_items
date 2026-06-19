package net.ntrdeal.ntrdeals_items.client;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.CompareOp;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderPipelines;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

@Environment(EnvType.CLIENT)
public interface ModRenderPipelines {
    RenderPipeline COSMOLITE = RenderPipelines.register(RenderPipeline.builder(RenderPipelines.END_PORTAL_SNIPPET)
            .withDepthStencilState(new DepthStencilState(CompareOp.EQUAL, false))
            .withColorTargetState(new ColorTargetState(BlendFunction.LIGHTNING))
            .withLocation(NTRDealsItems.id("pipline/cosmolite"))
            .withShaderDefine("PORTAL_LAYERS", 15)
            .withCull(false)
            .build()
    );

    RenderPipeline TRANSPARENT_TRIMS = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.ENTITY_SNIPPET)
                    .withLocation("pipeline/armor_cutout_no_cull")
                    .withShaderDefine("ALPHA_CUTOUT", 0.1F)
                    .withShaderDefine("NO_OVERLAY")
                    .withShaderDefine("PER_FACE_LIGHTING")
                    .withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
                    .withCull(false)
                    .build()
    );

    RenderPipeline TRANSPARENT_TRIMS_DECAL = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.ENTITY_SNIPPET)
                    .withLocation("pipeline/armor_decal_cutout_no_cull")
                    .withShaderDefine("ALPHA_CUTOUT", 0.1F)
                    .withShaderDefine("NO_OVERLAY")
                    .withShaderDefine("PER_FACE_LIGHTING")
                    .withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
                    .withCull(false)
                    .withDepthStencilState(new DepthStencilState(CompareOp.EQUAL, false))
                    .build()
    );
}