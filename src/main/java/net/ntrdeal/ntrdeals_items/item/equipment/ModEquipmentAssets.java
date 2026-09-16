package net.ntrdeal.ntrdeals_items.item.equipment;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public final class ModEquipmentAssets {
    public static final ResourceKey<EquipmentAsset> LUNARITE = createId("lunarite");
    public static final ResourceKey<EquipmentAsset> COSMOLITE = createId("cosmolite");

    private static ResourceKey<EquipmentAsset> createId(final String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, NTRDealsItems.id(name));
    }
}
