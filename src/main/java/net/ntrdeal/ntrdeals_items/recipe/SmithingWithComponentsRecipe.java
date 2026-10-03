package net.ntrdeal.ntrdeals_items.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.List;
import java.util.Optional;

public class SmithingWithComponentsRecipe extends SimpleSmithingRecipe {
    public static final MapCodec<SmithingWithComponentsRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            CommonInfo.MAP_CODEC.forGetter(recipe -> recipe.commonInfo),
            DataComponentPatch.CODEC.fieldOf("components").forGetter(recipe -> recipe.patch),
            Ingredient.CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
            Ingredient.CODEC.fieldOf("addition-1").forGetter(recipe -> recipe.addition1),
            Ingredient.CODEC.fieldOf("addition-2").forGetter(recipe -> recipe.addition2)
    ).apply(instance, SmithingWithComponentsRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SmithingWithComponentsRecipe> STREAM_CODEC = StreamCodec.composite(
            CommonInfo.STREAM_CODEC, recipe -> recipe.commonInfo,
            DataComponentPatch.STREAM_CODEC, recipe -> recipe.patch,
            Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.base,
            Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.addition1,
            Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.addition2,
            SmithingWithComponentsRecipe::new
    );

    private final DataComponentPatch patch;
    private final Ingredient base;
    private final Ingredient addition1;
    private final Ingredient addition2;

    public SmithingWithComponentsRecipe(
            CommonInfo commonInfo, DataComponentPatch patch,
            Ingredient base, Ingredient addition1, Ingredient addition2
    ) {
        super(commonInfo);
        this.patch = patch;
        this.base = base;
        this.addition1 = addition1;
        this.addition2 = addition2;
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input) {
        ItemStack stack = input.base().copy();
        stack.applyComponents(this.patch);
        return stack;
    }

    @Override public RecipeSerializer<SmithingWithComponentsRecipe> getSerializer() {return ModRecipes.SMITHING_WITH_COMPONENTS;}
    @Override public Optional<Ingredient> templateIngredient() {return Optional.of(this.addition1);}
    @Override public Ingredient baseIngredient() {return this.base;}
    @Override public Optional<Ingredient> additionIngredient() {return Optional.of(this.addition2);}

    @Override
    protected PlacementInfo createPlacementInfo() {
        return PlacementInfo.create(List.of(this.addition1, this.base, this.addition2));
    }
}
