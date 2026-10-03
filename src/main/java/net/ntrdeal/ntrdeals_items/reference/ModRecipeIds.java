package net.ntrdeal.ntrdeals_items.reference;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.realapi.util.registry.ResourceCreator;

public final class ModRecipeIds {
    private ModRecipeIds(){}

    private static final ResourceCreator<Recipe<?>> CREATOR = ResourceCreator.of(Registries.RECIPE, NTRDealsItems::id);

    public static final ResourceKey<Recipe<?>> COSMOLITE_HARNESS = CREATOR.create("cosmolite_harness");
}
