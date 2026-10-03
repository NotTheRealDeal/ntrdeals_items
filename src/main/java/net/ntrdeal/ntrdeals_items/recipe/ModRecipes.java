package net.ntrdeal.ntrdeals_items.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.ntrdeal.ntrdeals_items.reference.ModRecipeSerializerIds;

public final class ModRecipes {
    private ModRecipes(){}

    public static final RecipeSerializer<SmithingWithComponentsRecipe> SMITHING_WITH_COMPONENTS = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER, ModRecipeSerializerIds.SMITHING_WITH_COMPONENTS, new RecipeSerializer<>(
                    SmithingWithComponentsRecipe.CODEC, SmithingWithComponentsRecipe.STREAM_CODEC
            )
    );

    public static void register() {
    }
}
