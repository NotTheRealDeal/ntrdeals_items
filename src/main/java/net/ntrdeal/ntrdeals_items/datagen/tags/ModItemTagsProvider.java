package net.ntrdeal.ntrdeals_items.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.ntrdeal.ntrdeals_items.reference.ModItemIds;
import net.ntrdeal.ntrdeals_items.tags.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.HEAD_ARMOR).add(ModItemIds.COSMOLITE_HELMET);
        this.tag(ItemTags.CHEST_ARMOR).add(ModItemIds.COSMOLITE_CHESTPLATE);
        this.tag(ItemTags.LEG_ARMOR).add(ModItemIds.COSMOLITE_LEGGINGS);
        this.tag(ItemTags.FOOT_ARMOR).add(ModItemIds.COSMOLITE_BOOTS);

        this.tag(ModItemTags.LUNARITE_ARMOR)
                .add(ModItemIds.LUNARITE_HELMET)
                .add(ModItemIds.LUNARITE_CHESTPLATE)
                .add(ModItemIds.LUNARITE_LEGGINGS)
                .add(ModItemIds.LUNARITE_BOOTS);

        this.tag(ModItemTags.COSMOLITE_ARMOR)
                .add(ModItemIds.COSMOLITE_HELMET)
                .add(ModItemIds.COSMOLITE_CHESTPLATE)
                .add(ModItemIds.COSMOLITE_LEGGINGS)
                .add(ModItemIds.COSMOLITE_BOOTS);

        this.tag(ItemTags.TRIMMABLE_ARMOR).addTag(ModItemTags.LUNARITE_ARMOR);
        this.tag(ConventionalItemTags.ARMORS).addTag(ModItemTags.LUNARITE_ARMOR).addTag(ModItemTags.COSMOLITE_ARMOR);

        this.tag(ModItemTags.REPAIRS_LUNARITE_ARMOR).add(ModItemIds.LUNARITE_INGOT);
        this.tag(ModItemTags.REPAIRS_COSMOLITE_ARMOR).add(ModItemIds.COSMOLITE_INGOT);

        this.tag(ModItemTags.COSMOLITE)
                .forceAddTag(ModItemTags.COSMOLITE_ARMOR)
                .add(ModItemIds.RAW_LUNARITE)
                .add(ModItemIds.COSMOLITE_INGOT);
    }
}
