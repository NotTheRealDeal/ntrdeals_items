package net.ntrdeal.ntrdeals_items.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.entity.ModAttributes;
import net.ntrdeal.ntrdeals_items.item.ModCreativeTabs;
import net.ntrdeal.ntrdeals_items.item.ModItems;
import net.ntrdeal.ntrdeals_items.tags.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModEnglishProvider extends FabricLanguageProvider {
    public ModEnglishProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider lookup, TranslationBuilder builder) {
        builder.add(ModItems.RAW_LUNARITE, "Raw Lunarite");
        builder.add(ModItems.RAW_COSMOLITE, "Raw Cosmolite");

        builder.add(ModItems.LUNARITE_INGOT, "Lunarite Ingot");
        builder.add(ModItems.COSMOLITE_INGOT, "Cosmolite Ingot");

        builder.add(ModItems.LUNARITE_HELMET, "Lunarite Helmet");
        builder.add(ModItems.LUNARITE_CHESTPLATE, "Lunarite Chestplate");
        builder.add(ModItems.LUNARITE_LEGGINGS, "Lunarite Leggings");
        builder.add(ModItems.LUNARITE_BOOTS, "Lunarite Boots");

        builder.add(ModItems.COSMOLITE_HELMET, "Cosmolite Helmet");
        builder.add(ModItems.COSMOLITE_CHESTPLATE, "Cosmolite Chestplate");
        builder.add(ModItems.COSMOLITE_LEGGINGS, "Cosmolite Leggings");
        builder.add(ModItems.COSMOLITE_BOOTS, "Cosmolite Boots");

        builder.add(ModItems.TRIM_SWAPPER, "Trim Swapper");

        builder.add(ModBlocks.LUNARITE_ORE, "Lunarite Ore");
        builder.add(ModBlocks.COSMOLITE_ORE.asItem(), "Cosmolite Ore");
        builder.add(ModBlocks.RAW_LUNARITE_BLOCK.asItem(), "Block of Raw Lunarite");
        builder.add(ModBlocks.RAW_COSMOLITE_BLOCK.asItem(), "Block of Raw Cosmolite");
        builder.add(ModBlocks.LUNARITE_BLOCK.asItem(), "Block of Lunarite");
        builder.add(ModBlocks.COSMOLITE_BLOCK.asItem(), "Block of Cosmolite");
        builder.add(ModBlocks.DRIED_CHORUS_FLOWER.asItem(), "Dried Chorus Flower");

        builder.add(ModAttributes.PASSIVE_REGENERATION, "Passive Regeneration");
        builder.add(ModAttributes.SANITY, "Sanity");

        builder.add(ModItemTags.LUNARITE_ARMOR, "Lunarite Armor");
        builder.add(ModItemTags.COSMOLITE_ARMOR, "Cosmolite Armor");
        builder.add(ModItemTags.REPAIRS_LUNARITE_ARMOR, "Repairs Lunarite Armor");
        builder.add(ModItemTags.REPAIRS_COSMOLITE_ARMOR, "Repairs Cosmolite Armor");
        builder.add(ModItemTags.COSMOLITE, "Cosmolite");

        builder.add(ModCreativeTabs.NTRDEALS_ITEMS.getDisplayName().getString(), "NTRDeal's Items");

        builder.add("tooltip.ntrdeals_items.error.incomplete", "§cIncomplete trim!");
        builder.add("advancements.adventure.ntrdeals_items.extremely_dedicated.title", "Extreme Dedication");
        builder.add("advancements.adventure.ntrdeals_items.extremely_dedicated.description", "Trim a full cosmolite set with netherite silence");
    }
}