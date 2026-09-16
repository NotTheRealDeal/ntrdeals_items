package net.ntrdeal.ntrdeals_items.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.AbstractEndPortalRenderer;
import net.minecraft.client.renderer.rendertype.LayeringTransform;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public final class ModRenderTypes {
    private ModRenderTypes(){}

    public static final RenderType COSMOLITE = RenderType.create("cosmolite", RenderSetup.builder(ModRenderPipelines.COSMOLITE)
            .withTexture("Sampler0", AbstractEndPortalRenderer.END_SKY_LOCATION)
            .withTexture("Sampler1", AbstractEndPortalRenderer.END_PORTAL_LOCATION)
            .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
            .withForcedSolidModelPhase()
            .createRenderSetup()
    );

    private static final Function<Identifier, RenderType> ARMOR_TRIM_TRANSPARENT = Util.memoize(texture -> RenderType.create(
            "armor_trim_transparent", RenderSetup.builder(ModRenderPipelines.ARMOR_TRIM_TRANSPARENT)
                    .withTexture("Sampler0", texture)
                    .useLightmap().useOverlay()
                    .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
                    .affectsCrumbling()
                    .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                    .withForcedSolidModelPhase()
                    .createRenderSetup()
    ));

    private static final Function<Identifier, RenderType> ARMOR_TRIM_DECAL_TRANSPARENT = Util.memoize(texture -> RenderType.create(
            "armor_trim_transparent", RenderSetup.builder(ModRenderPipelines.ARMOR_TRIM_DECAL_TRANSPARENT)
                    .withTexture("Sampler0", texture)
                    .useLightmap().useOverlay()
                    .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
                    .affectsCrumbling()
                    .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                    .withForcedSolidModelPhase()
                    .createRenderSetup()
    ));

    public static RenderType armorTrimsSheet(Identifier texture, boolean decal) {
        return (decal ? ARMOR_TRIM_DECAL_TRANSPARENT : ARMOR_TRIM_TRANSPARENT).apply(texture);
    }

    public static void register() {}
}