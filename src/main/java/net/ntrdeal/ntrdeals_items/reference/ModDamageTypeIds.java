package net.ntrdeal.ntrdeals_items.reference;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.compat.EnchancementCompat;
import net.ntrdeal.realapi.util.registry.ResourceCreator;

public final class ModDamageTypeIds {
    private ModDamageTypeIds(){}

    private static final ResourceCreator<DamageType> CREATOR = ResourceCreator.of(Registries.DAMAGE_TYPE, NTRDealsItems::id);
    private static final ResourceCreator<DamageType> ENCHANCEMENT_CREATOR = ResourceCreator.of(Registries.DAMAGE_TYPE, EnchancementCompat::id);

    public static final ResourceKey<DamageType> ENCHANCEMENT_BRIMSTONE = ENCHANCEMENT_CREATOR.create("brimstone");
}
