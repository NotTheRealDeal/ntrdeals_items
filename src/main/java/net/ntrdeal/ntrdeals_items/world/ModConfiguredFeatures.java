package net.ntrdeal.ntrdeals_items.world;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.GeodeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.realapi.util.registry.ResourceCreator;

import java.util.List;

public final class ModConfiguredFeatures {
    private ModConfiguredFeatures(){}

    private static final ResourceCreator<Feature> CREATOR = ResourceCreator.of(Registries.FEATURE, NTRDealsItems::id);

    public static final ResourceKey<Feature> LUNARITE_GEODE = CREATOR.create("lunarite_geode");
    public static final ResourceKey<Feature> COSMOLITE_GEODE = CREATOR.create("cosmolite_geode");

    public static void bootstrap(BootstrapContext<Feature> context) {
        HolderGetter<Block> getter = context.lookup(Registries.BLOCK);
        HolderSet<Block> featuresCannotReplace = getter.getOrThrow(BlockTags.FEATURES_CANNOT_REPLACE);
        HolderSet<Block> geodeInvalid = getter.getOrThrow(BlockTags.GEODE_INVALID_BLOCKS);

        context.register(LUNARITE_GEODE, new GeodeFeature(
                new GeodeBlockSettings(
                        BlockStateProvider.holderOf(Blocks.AIR), BlockStateProvider.holderOf(Blocks.END_STONE),
                        BlockStateProvider.holderOf(ModBlocks.LUNARITE_ORE), BlockStateProvider.holderOf(Blocks.END_STONE_BRICKS),
                        BlockStateProvider.holderOf(Blocks.PURPUR_BLOCK), List.of(ModBlocks.DRIED_CHORUS_FLOWER.defaultBlockState()),
                        featuresCannotReplace, geodeInvalid
                ),
                new GeodeLayerSettings(1.7d, 2.2d, 3.2d, 4.2d),
                new GeodeCrackSettings(0.95d, 2.0d, 2),
                0.05d, 0.0355d, false,
                UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2),
                -16, 16, 0.05d, 1
        ));

        context.register(COSMOLITE_GEODE, new GeodeFeature(
                new GeodeBlockSettings(
                        BlockStateProvider.holderOf(Blocks.AIR), BlockStateProvider.holderOf(Blocks.BLACKSTONE),
                        BlockStateProvider.holderOf(ModBlocks.COSMOLITE_ORE), BlockStateProvider.holderOf(Blocks.MAGMA_BLOCK),
                        BlockStateProvider.holderOf(Blocks.SMOOTH_BASALT), List.of(Blocks.LAVA.defaultBlockState()),
                        featuresCannotReplace, geodeInvalid
                ),
                new GeodeLayerSettings(1.7d, 2.2d, 3.2d, 4.2d),
                new GeodeCrackSettings(0d, 2.0d, 2),
                0.25d, 0.0215d, false,
                UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2),
                -16, 16, 0.05d, 1
        ));
    }
}