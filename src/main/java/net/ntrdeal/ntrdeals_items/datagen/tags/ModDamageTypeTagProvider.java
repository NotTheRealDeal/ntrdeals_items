package net.ntrdeal.ntrdeals_items.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageType;
import net.ntrdeal.ntrdeals_items.reference.ModDamageTypeIds;
import net.ntrdeal.realapi.tag.RealDamageTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagProvider extends FabricTagsProvider<DamageType> {
    public ModDamageTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, Registries.DAMAGE_TYPE, provider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(RealDamageTypeTags.RANGED_ATTACK_MULTIPLIED).addOptional(ModDamageTypeIds.ENCHANCEMENT_BRIMSTONE);
    }
}
