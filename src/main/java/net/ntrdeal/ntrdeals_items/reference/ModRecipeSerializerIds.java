package net.ntrdeal.ntrdeals_items.reference;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.realapi.util.registry.ResourceCreator;

public final class ModRecipeSerializerIds {
    private ModRecipeSerializerIds(){}

    private static final ResourceCreator<RecipeSerializer<?>> CREATOR = ResourceCreator.of(Registries.RECIPE_SERIALIZER, NTRDealsItems::id);

    public static final ResourceKey<RecipeSerializer<?>> SMITHING_WITH_COMPONENTS = CREATOR.create("smithing_with_components");
}
