package net.ntrdeal.ntrdeals_items.reference;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.realapi.util.RegistryUtil;

public final class ModDataComponentIds {
    private ModDataComponentIds(){}

    private static final RegistryUtil.ResourceCreator<DataComponentType<?>> CREATOR = RegistryUtil.resourceCreator(Registries.DATA_COMPONENT_TYPE, NTRDealsItems::id);

    public static final ResourceKey<DataComponentType<?>> TRIM_SWAPPER = CREATOR.create("trim_swapper");
    public static final ResourceKey<DataComponentType<?>> INFUSE_ATTRIBUTES = CREATOR.create("infuse_attributes");
    public static final ResourceKey<DataComponentType<?>> INFUSIBLE = CREATOR.create("infusible");
    public static final ResourceKey<DataComponentType<?>> CHORUS_METER = CREATOR.create("chorus_meter");
}
