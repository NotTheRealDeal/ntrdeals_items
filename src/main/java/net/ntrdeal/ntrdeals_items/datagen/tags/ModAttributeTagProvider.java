package net.ntrdeal.ntrdeals_items.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.ntrdeal.ntrdeals_items.reference.ModAttributeIds;
import net.ntrdeal.realapi.tag.RealAttributeTags;

import java.util.concurrent.CompletableFuture;

public class ModAttributeTagProvider extends FabricTagsProvider<Attribute> {
    public ModAttributeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, Registries.ATTRIBUTE, provider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(RealAttributeTags.DIMENSIONS_REFRESHER).add(ModAttributeIds.CLAMPED_SCALE);
    }
}