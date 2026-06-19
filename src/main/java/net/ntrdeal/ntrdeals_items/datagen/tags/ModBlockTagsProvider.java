package net.ntrdeal.ntrdeals_items.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.ntrdeal.ntrdeals_items.reference.ModBlockItemIds;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.builder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlockItemIds.LUNARITE_ORE)
                .add(ModBlockItemIds.COSMOLITE_ORE)
                .add(ModBlockItemIds.RAW_LUNARITE_BLOCK)
                .add(ModBlockItemIds.RAW_COSMOLITE_BLOCK)
                .add(ModBlockItemIds.LUNARITE_BLOCK)
                .add(ModBlockItemIds.COSMOLITE_BLOCK)
                .add(ModBlockItemIds.DRIED_CHORUS_FLOWER);

        this.builder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlockItemIds.LUNARITE_ORE)
                .add(ModBlockItemIds.RAW_LUNARITE_BLOCK)
                .add(ModBlockItemIds.LUNARITE_BLOCK);

        this.builder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlockItemIds.COSMOLITE_ORE)
                .add(ModBlockItemIds.RAW_COSMOLITE_BLOCK)
                .add(ModBlockItemIds.COSMOLITE_BLOCK);

        this.builder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .add(ModBlockItemIds.COSMOLITE_ORE)
                .add(ModBlockItemIds.RAW_COSMOLITE_BLOCK)
                .add(ModBlockItemIds.COSMOLITE_BLOCK);
    }
}
