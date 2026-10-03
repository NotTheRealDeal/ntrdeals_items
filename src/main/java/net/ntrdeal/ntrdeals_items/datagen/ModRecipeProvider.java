package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.impl.recipe.ingredient.builtin.ComponentsIngredient;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.recipes.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.item.ModItems;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import net.ntrdeal.ntrdeals_items.recipe.SmithingWithComponentsRecipe;
import net.ntrdeal.ntrdeals_items.reference.ModRecipeIds;
import net.ntrdeal.ntrdeals_items.tags.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override @SuppressWarnings("UnstableApiUsage")
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
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

                RecipeUnlockAdvancementBuilder cosmoliteHarnessAdvancement = new RecipeUnlockAdvancementBuilder();
                cosmoliteHarnessAdvancement.unlockedBy(RecipeProvider.getHasName(ModItems.COSMOLITE_INGOT), this.has(ModItems.COSMOLITE_INGOT));

                this.output.accept(ModRecipeIds.COSMOLITE_HARNESS, new SmithingWithComponentsRecipe(
                        RecipeBuilder.createCraftingCommonInfo(true), DataComponentPatch.builder()
                                .set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                                        .add(Attributes.FLYING_SPEED, new AttributeModifier(
                                                NTRDealsItems.id("cosmolite_harness"), 1d, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                                        ), EquipmentSlotGroup.BODY)
                                        .add(Attributes.ARMOR, new AttributeModifier(
                                                NTRDealsItems.id("cosmolite_harness"), 10d, AttributeModifier.Operation.ADD_VALUE
                                        ), EquipmentSlotGroup.BODY)
                                        .build()
                                ).set(ModDataComponents.COSMOLITE, Unit.INSTANCE)
                        .build(),
                        new ComponentsIngredient(
                                Ingredient.of(provider.getOrThrow(ItemTags.HARNESSES)),
                                DataComponentPatch.builder().remove(ModDataComponents.COSMOLITE).build()
                        ).toVanilla(), Ingredient.of(ModItems.COSMOLITE_INGOT), Ingredient.of(ModItems.COSMOLITE_INGOT)
                ), cosmoliteHarnessAdvancement.build(this.output, ModRecipeIds.COSMOLITE_HARNESS, RecipeCategory.MISC));
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

}
