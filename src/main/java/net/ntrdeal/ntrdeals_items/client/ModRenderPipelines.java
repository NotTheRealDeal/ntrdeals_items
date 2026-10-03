package net.ntrdeal.ntrdeals_items.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.renderpearl.api.pipeline.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.RenderPipelines;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

@Environment(EnvType.CLIENT)
public final class ModRenderPipelines {
    private ModRenderPipelines(){}

    public static final RenderPipeline.Snippet COSMOLITE_SNIPPET = RenderPipeline.builder(RenderPipelines.END_PORTAL_SNIPPET)
            .withVertexShader(NTRDealsItems.id("core/cosmolite"))
            .withFragmentShader(NTRDealsItems.id("core/cosmolite"))
            .withDepthStencilState(new DepthStencilState(CompareOp.EQUAL, false))
            .withColorTargetState(new ColorTargetState(BlendFunction.LIGHTNING))
            .withShaderDefine("PORTAL_LAYERS", 16)
            .withCull(false)
            .buildSnippet();

    public static final RenderPipeline COSMOLITE = RenderPipelines.register(RenderPipeline.builder(COSMOLITE_SNIPPET)
            .withLocation(NTRDealsItems.id("pipeline/cosmolite"))
            .withVertexBinding(0, DefaultVertexFormat.POSITION)
            .build()
    );

    public static final RenderPipeline FLAT_COSMOLITE = RenderPipelines.register(RenderPipeline.builder(COSMOLITE_SNIPPET)
            .withLocation(NTRDealsItems.id("pipeline/flat_cosmolite"))
            .withVertexBinding(0, DefaultVertexFormat.POSITION_TEX)
            .withShaderDefine("FLAT")
            .build()
    );

    public static final RenderPipeline MASKED_COSMOLITE = RenderPipelines.register(RenderPipeline.builder(COSMOLITE_SNIPPET)
            .withLocation(NTRDealsItems.id("pipeline/masked_cosmolite"))
            .withVertexBinding(0, DefaultVertexFormat.POSITION)
            .withBindGroupLayout(BindGroupLayouts.SAMPLER2)
            .withShaderDefine("MASK")
            .build()
    );

    public static final RenderPipeline FLAT_MASKED_COSMOLITE = RenderPipelines.register(RenderPipeline.builder(COSMOLITE_SNIPPET)
            .withLocation(NTRDealsItems.id("pipeline/flat_masked_cosmolite"))
            .withVertexBinding(0, DefaultVertexFormat.POSITION_TEX)
            .withShaderDefine("FLAT")
            .withBindGroupLayout(BindGroupLayouts.SAMPLER2)
            .withShaderDefine("MASK")
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