package net.ntrdeal.ntrdeals_items.item.equipment;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.ntrdeal.ntrdeals_items.tags.ModItemTags;

public interface ModArmorMaterials {
    public static final ArmorMaterial LUNARITE = new ArmorMaterial(
            23, ArmorMaterials.IRON.defense(),
            1, SoundEvents.ARMOR_EQUIP_GENERIC,
            0, 0, ModItemTags.REPAIRS_LUNARITE_ARMOR,
            ModEquipmentAssets.LUNARITE
    );

    public static final ArmorMaterial COSMOLITE = new ArmorMaterial(
            40, ArmorMaterials.IRON.defense(),
            25, SoundEvents.ARMOR_EQUIP_NETHERITE,
            1, 0, ModItemTags.REPAIRS_COSMOLITE_ARMOR,
            ModEquipmentAssets.COSMOLITE
    );
}
