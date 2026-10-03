package net.ntrdeal.ntrdeals_items.reference;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.realapi.util.registry.ResourceCreator;

public final class ModCreativeTabIds {
    private ModCreativeTabIds(){}

    private static final ResourceCreator<CreativeModeTab> CREATOR = ResourceCreator.of(Registries.CREATIVE_MODE_TAB, NTRDealsItems::id);

    public static final ResourceKey<CreativeModeTab> NTRDEALS_ITEMS = CREATOR.create("ntrdeals_items");
}
