package net.ntrdeal.ntrdeals_items.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.AbstractEndPortalRenderer;
import net.minecraft.client.renderer.rendertype.LayeringTransform;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;

@Environment(EnvType.CLIENT)
public interface ModRenderTypes {
    RenderType COSMOLITE = RenderType.create("cosmolite", RenderSetup.builder(ModRenderPipelines.COSMOLITE)
            .withTexture("Sampler0", AbstractEndPortalRenderer.END_SKY_LOCATION)
            .withTexture("Sampler1", AbstractEndPortalRenderer.END_PORTAL_LOCATION)
            .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
            .createRenderSetup()
    );

    RenderType TRANSPARENT_TRIMS_DECAL = RenderType.create("transparent_trims_decal", RenderSetup.builder(ModRenderPipelines.TRANSPARENT_TRIMS_DECAL)
            .withTexture("Sampler0", Sheets.ARMOR_TRIMS_SHEET)
            .useLightmap()
            .useOverlay()
            .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
            .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
            .createRenderSetup()
    );

    RenderType TRANSPARENT_TRIMS = RenderType.create("transparent_trims", RenderSetup.builder(ModRenderPipelines.TRANSPARENT_TRIMS)
            .withTexture("Sampler0", Sheets.ARMOR_TRIMS_SHEET)
            .useLightmap()
            .useOverlay()
            .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
            .affectsCrumbling()
            .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
            .createRenderSetup()
    );

    static RenderType armorTrimsSheet(final boolean decal) {
        return decal ? TRANSPARENT_TRIMS_DECAL : TRANSPARENT_TRIMS;
    }

    static void register() {}
}