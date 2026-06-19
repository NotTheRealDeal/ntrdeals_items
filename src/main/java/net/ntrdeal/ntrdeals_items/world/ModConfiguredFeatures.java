package net.ntrdeal.ntrdeals_items.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LUNARITE_GEODE = resourceKey("lunarite_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COSMOLITE_GEODE = resourceKey("cosmolite_geode");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, LUNARITE_GEODE, Feature.GEODE, new GeodeConfiguration(
                new GeodeBlockSettings(
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(Blocks.END_STONE),
                        BlockStateProvider.simple(ModBlocks.LUNARITE_ORE),
                        BlockStateProvider.simple(Blocks.END_STONE_BRICKS),
                        BlockStateProvider.simple(Blocks.PURPUR_BLOCK),
                        List.of(ModBlocks.DRIED_CHORUS_FLOWER.defaultBlockState()),
                        context.lookup(Registries.BLOCK).getOrThrow(BlockTags.FEATURES_CANNOT_REPLACE),
                        context.lookup(Registries.BLOCK).getOrThrow(BlockTags.GEODE_INVALID_BLOCKS)
                ),
                new GeodeLayerSettings(1.7d, 2.2d, 3.2d, 4.2d),
                new GeodeCrackSettings(0.95d, 2.0d, 2),
                0.05d,
                0.0355d,
                false,
                UniformInt.of(4, 6),
                UniformInt.of(3, 4),
                UniformInt.of(1, 2),
                -16,
                16,
                0.05d,
                1
        ));

        register(context, COSMOLITE_GEODE, Feature.GEODE, new GeodeConfiguration(
                new GeodeBlockSettings(
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(Blocks.BLACKSTONE),
                        BlockStateProvider.simple(ModBlocks.COSMOLITE_ORE),
                        BlockStateProvider.simple(Blocks.MAGMA_BLOCK),
                        BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(Blocks.LAVA.defaultBlockState()),
                        context.lookup(Registries.BLOCK).getOrThrow(BlockTags.FEATURES_CANNOT_REPLACE),
                        context.lookup(Registries.BLOCK).getOrThrow(BlockTags.GEODE_INVALID_BLOCKS)
                ),
                new GeodeLayerSettings(1.7d, 2.2d, 3.2d, 4.2d),
                new GeodeCrackSettings(0d, 2.0d, 2),
                0.25d,
                0.0215d,
                false,
                UniformInt.of(4, 6),
                UniformInt.of(3, 4),
                UniformInt.of(1, 2),
                -16,
                16,
                0.05d,
                1
        ));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> resourceKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, NTRDealsItems.id(name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
