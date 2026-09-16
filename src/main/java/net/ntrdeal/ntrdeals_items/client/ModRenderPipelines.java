package net.ntrdeal.ntrdeals_items.client;

import com.mojang.renderpearl.api.pipeline.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderPipelines;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

@Environment(EnvType.CLIENT)
public final class ModRenderPipelines {
    private ModRenderPipelines(){}

    public static final RenderPipeline COSMOLITE = RenderPipelines.register(RenderPipeline.builder(RenderPipelines.END_PORTAL_SNIPPET)
        .withLocation(NTRDealsItems.id("pipeline/cosmolite"))
        .withDepthStencilState(new DepthStencilState(CompareOp.EQUAL, false))
        .withColorTargetState(new ColorTargetState(BlendFunction.LIGHTNING))
        .withShaderDefine("PORTAL_LAYERS", 16)
        .withCull(false)
        .build()
    );

    public static final RenderPipeline ARMOR_TRIM_TRANSPARENT = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.ENTITY_SNIPPET)
                    .withLocation(NTRDealsItems.id("pipeline/armor_trim_transparent"))
                    .withShaderDefine("ALPHA_CUTOUT", 0.1F)
                    .withShaderDefine("NO_OVERLAY")
                    .withShaderDefine("PER_FACE_LIGHTING")
                    .withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
                    .withCull(false)
                    .build()
    );

    public static final RenderPipeline ARMOR_TRIM_DECAL_TRANSPARENT = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.ENTITY_SNIPPET)
                    .withLocation(NTRDealsItems.id("pipeline/armor_trim_decal_transparent"))
                    .withShaderDefine("ALPHA_CUTOUT", 0.1F)
                    .withShaderDefine("NO_OVERLAY")
                    .withShaderDefine("PER_FACE_LIGHTING")
                    .withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
                    .withCull(false)
                    .withDepthStencilState(new DepthStencilState(CompareOp.EQUAL, false))
                    .build()
    );
}