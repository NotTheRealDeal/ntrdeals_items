package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.TrimPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.item.ModItems;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider lookup, Consumer<AdvancementHolder> consumer) {
        String requirement = "netherite_silenced";
        HolderLookup<Item> itemLookup = lookup.lookupOrThrow(Registries.ITEM);
        ArmorTrim netherite_silenced = new ArmorTrim(lookup.getOrThrow(TrimMaterials.NETHERITE), lookup.getOrThrow(TrimPatterns.SILENCE));
        DataComponentMatchers matchers = DataComponentMatchers.Builder.components().partial(
                DataComponentPredicates.ARMOR_TRIM, new TrimPredicate(
                        Optional.of(HolderSet.direct(netherite_silenced.material())),
                        Optional.of(HolderSet.direct(netherite_silenced.pattern()))
                )
        ).build();

        consumer.accept(Advancement.Builder.advancement()
                .display(
                        new ItemStackTemplate(ModItems.COSMOLITE_CHESTPLATE, DataComponentPatch.builder().set(DataComponents.TRIM, netherite_silenced).build()),
                        Component.translatable("advancements.adventure.ntrdeals_items.extremely_dedicated.title"),
                        Component.translatable("advancements.adventure.ntrdeals_items.extremely_dedicated.description"),
                        AdvancementType.CHALLENGE, true, true, true
                )
                .parent(FabricAdvancementProvider.createPlaceholder(Identifier.withDefaultNamespace("adventure/trim_with_any_armor_pattern")))
                .addCriterion(requirement, InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(itemLookup, ModItems.COSMOLITE_HELMET).withComponents(matchers),
                        ItemPredicate.Builder.item().of(itemLookup, ModItems.COSMOLITE_CHESTPLATE).withComponents(matchers),
                        ItemPredicate.Builder.item().of(itemLookup, ModItems.COSMOLITE_LEGGINGS).withComponents(matchers),
                        ItemPredicate.Builder.item().of(itemLookup, ModItems.COSMOLITE_BOOTS).withComponents(matchers)
                ))
                .requirements(AdvancementRequirements.allOf(List.of(requirement)))
                .rewards(AdvancementRewards.Builder.experience(100).build())
                .build(NTRDealsItems.id("extremely_dedicated"))
        );
    }
}
