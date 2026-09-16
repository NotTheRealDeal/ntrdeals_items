package net.ntrdeal.ntrdeals_items.reference;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.realapi.util.RegistryUtil;

public final class ModAttributeIds {
    private ModAttributeIds(){}

    private static final RegistryUtil.ResourceCreator<Attribute> CREATOR = RegistryUtil.resourceCreator(Registries.ATTRIBUTE, NTRDealsItems::id);

    public static final ResourceKey<Attribute> PASSIVE_REGENERATE = CREATOR.create("passive_regeneration");
    public static final ResourceKey<Attribute> CLAMPED_SCALE = CREATOR.create("clamped_scale");
    public static final ResourceKey<Attribute> SANITY = CREATOR.create("sanity");
}
