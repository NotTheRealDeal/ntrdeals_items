package net.ntrdeal.ntrdeals_items.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.config.InfusibleConfiguration;
import net.ntrdeal.ntrdeals_items.config.InfusionConfiguration;

public final class ModRegistries {
    private ModRegistries(){}

    public static final ResourceKey<Registry<InfusionConfiguration>> INFUSION = create("infusion");
    public static final ResourceKey<Registry<InfusibleConfiguration>> INFUSIBLE = create("infusible");

    private static <T> ResourceKey<Registry<T>> create(final String name) {
        return ResourceKey.createRegistryKey(NTRDealsItems.id(name));
    }
}