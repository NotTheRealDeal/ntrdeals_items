package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.item.ModItems;
import net.ntrdeal.ntrdeals_items.tags.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                this.nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RAW_LUNARITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_LUNARITE_BLOCK);
                this.nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RAW_COSMOLITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_COSMOLITE_BLOCK);

                this.shapeless(RecipeCategory.MISC, ModItems.LUNARITE_INGOT)
                        .requires(ModItems.RAW_LUNARITE, 2)
                        .unlockedBy(RecipeProvider.getHasName(ModItems.RAW_LUNARITE), this.has(ModItems.RAW_LUNARITE))
                        .save(this.output);

                this.shapeless(RecipeCategory.MISC, ModItems.COSMOLITE_INGOT)
                        .requires(ModItems.RAW_COSMOLITE, 2).requires(ModItems.LUNARITE_INGOT)
                        .unlockedBy("has_lunarite_armor", this.has(ModItemTags.LUNARITE_ARMOR))
                        .save(this.output);

                this.nineBlockStorageRecipesRecipesWithCustomUnpacking(
                        RecipeCategory.MISC, ModItems.LUNARITE_INGOT,
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUNARITE_BLOCK,
                        "lunarite_ingot_from_lunarite_block", "lunarite_ingot"
                );

                this.nineBlockStorageRecipesRecipesWithCustomUnpacking(
                        RecipeCategory.MISC, ModItems.COSMOLITE_INGOT,
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.COSMOLITE_BLOCK,
                        "cosmolite_ingot_from_cosmolite_block", "cosmolite_ingot"
                );

                makeArmorRecipes(this, this.output, ModItems.LUNARITE_INGOT, ModItems.LUNARITE_INGOT,
                        ModItems.LUNARITE_HELMET, ModItems.LUNARITE_CHESTPLATE, ModItems.LUNARITE_LEGGINGS, ModItems.LUNARITE_BOOTS);

                makeArmorRecipes(this, this.output, ModItems.COSMOLITE_INGOT, ModItems.COSMOLITE_INGOT,
                        ModItems.COSMOLITE_HELMET, ModItems.COSMOLITE_CHESTPLATE, ModItems.COSMOLITE_LEGGINGS, ModItems.COSMOLITE_BOOTS);

                this.shaped(RecipeCategory.TOOLS, ModItems.TRIM_SWAPPER)
                        .pattern("CCC")
                        .pattern("CSC")
                        .pattern("CCC")
                        .define('C', ModItems.COSMOLITE_INGOT)
                        .define('S', Blocks.SMITHING_TABLE)
                        .unlockedBy("has_cosmolite_armor", this.has(ModItemTags.COSMOLITE_ARMOR))
                        .save(this.output);
            }
        };
    }

    public static void makeArmorRecipes(
            RecipeProvider provider, RecipeOutput output,
            ItemLike craftingItem, ItemLike conditionItem,
            ItemLike helmet, ItemLike chestplate,
            ItemLike leggings, ItemLike boots) {
        String advancementName = RecipeProvider.getHasName(conditionItem);
        Criterion<InventoryChangeTrigger.TriggerInstance> criterion = provider.has(conditionItem);

        provider.shaped(RecipeCategory.COMBAT, helmet)
                .pattern("III")
                .pattern("I I")
                .define('I', craftingItem)
                .unlockedBy(advancementName, criterion)
                .save(output);

        provider.shaped(RecipeCategory.COMBAT, chestplate)
                .pattern("I I")
                .pattern("III")
                .pattern("III")
                .define('I', craftingItem)
                .unlockedBy(advancementName, criterion)
                .save(output);

        provider.shaped(RecipeCategory.COMBAT, leggings)
                .pattern("III")
                .pattern("I I")
                .pattern("I I")
                .define('I', craftingItem)
                .unlockedBy(advancementName, criterion)
                .save(output);

        provider.shaped(RecipeCategory.COMBAT, boots)
                .pattern("I I")
                .pattern("I I")
                .define('I', craftingItem)
                .unlockedBy(advancementName, criterion)
                .save(output);
    }

    @Override
    public String getName() {
        return "Recipes";
    }
}
