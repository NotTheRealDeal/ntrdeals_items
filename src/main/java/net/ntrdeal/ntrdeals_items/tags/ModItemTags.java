package net.ntrdeal.ntrdeals_items.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.realapi.util.registry.TagCreator;

public final class ModItemTags {
    private ModItemTags(){}

    private static final TagCreator<Item> CREATOR = TagCreator.of(Registries.ITEM, NTRDealsItems::id);

    public static final TagKey<Item> COSMOLITE_ARMOR = CREATOR.create("cosmolite_armor");
    public static final TagKey<Item> LUNARITE_ARMOR = CREATOR.create("lunarite_armor");
    public static final TagKey<Item> REPAIRS_LUNARITE_ARMOR = CREATOR.create("repairs_lunarite_armor");
    public static final TagKey<Item> REPAIRS_COSMOLITE_ARMOR = CREATOR.create("repairs_cosmolite_armor");
    public static final TagKey<Item> LUNARITE = CREATOR.create("lunarite");
    public static final TagKey<Item> COSMOLITE = CREATOR.create("cosmolite");
}