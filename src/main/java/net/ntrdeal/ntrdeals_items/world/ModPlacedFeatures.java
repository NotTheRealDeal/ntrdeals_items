package net.ntrdeal.ntrdeals_items.world;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.realapi.util.registry.ResourceCreator;

import java.util.List;

public final class ModPlacedFeatures {
    private ModPlacedFeatures(){}

    private static final ResourceCreator<PlacedFeature> CREATOR = ResourceCreator.of(Registries.PLACED_FEATURE, NTRDealsItems::id);

    public static final ResourceKey<PlacedFeature> LUNARITE_GEODE = CREATOR.create("lunarite_geode");
    public static final ResourceKey<PlacedFeature> COSMOLITE_GEODE = CREATOR.create("cosmolite_geode");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<Feature> getter = context.lookup(Registries.FEATURE);

        register(context, LUNARITE_GEODE, getter.getOrThrow(ModConfiguredFeatures.LUNARITE_GEODE),
                RarityFilter.onAverageOnceEvery(85), InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(64), VerticalAnchor.aboveBottom(94))
        );

        register(context, COSMOLITE_GEODE, getter.getOrThrow(ModConfiguredFeatures.COSMOLITE_GEODE),
                RarityFilter.onAverageOnceEvery(45), InSquarePlacement.spread(), BiomeFilter.biome(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(14), VerticalAnchor.aboveBottom(44))
        );
    }

    private static void register(
            BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
            Holder<Feature> feature, PlacementModifier... modifiers
    ) {
        context.register(key, new PlacedFeature(feature, List.of(modifiers)));
    }
}