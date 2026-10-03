package net.ntrdeal.ntrdeals_items.reference;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.realapi.util.registry.ResourceCreator;

public final class ModItemIds {
    private ModItemIds(){}

    private static final ResourceCreator<Item> CREATOR = ResourceCreator.of(Registries.ITEM, NTRDealsItems::id);

    public static final ResourceKey<Item> RAW_LUNARITE = CREATOR.create("raw_lunarite");
    public static final ResourceKey<Item> RAW_COSMOLITE = CREATOR.create("raw_cosmolite");

    public static final ResourceKey<Item> LUNARITE_INGOT = CREATOR.create("lunarite_ingot");
    public static final ResourceKey<Item> COSMOLITE_INGOT = CREATOR.create("cosmolite_ingot");

    public static final ResourceKey<Item> LUNARITE_HELMET = CREATOR.create("lunarite_helmet");
    public static final ResourceKey<Item> LUNARITE_CHESTPLATE = CREATOR.create("lunarite_chestplate");
    public static final ResourceKey<Item> LUNARITE_LEGGINGS = CREATOR.create("lunarite_leggings");
    public static final ResourceKey<Item> LUNARITE_BOOTS = CREATOR.create("lunarite_boots");

    public static final ResourceKey<Item> COSMOLITE_HELMET = CREATOR.create("cosmolite_helmet");
    public static final ResourceKey<Item> COSMOLITE_CHESTPLATE = CREATOR.create("cosmolite_chestplate");
    public static final ResourceKey<Item> COSMOLITE_LEGGINGS = CREATOR.create("cosmolite_leggings");
    public static final ResourceKey<Item> COSMOLITE_BOOTS = CREATOR.create("cosmolite_boots");

    public static final ResourceKey<Item> TRIM_SWAPPER = CREATOR.create("trim_swapper");

    public static final ResourceKey<Item> CHORUS_SWORD = CREATOR.create("chorus_sword");
}
