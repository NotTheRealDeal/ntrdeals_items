package net.ntrdeal.ntrdeals_items.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.ArmorType;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.config.InfusibleEntry;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import net.ntrdeal.ntrdeals_items.item.custom.TrimSwapperItem;
import net.ntrdeal.ntrdeals_items.item.equipment.ModArmorMaterials;
import net.ntrdeal.ntrdeals_items.reference.ModBlockItemIds;
import net.ntrdeal.ntrdeals_items.reference.ModItemIds;
import net.ntrdeal.realapi.util.RegistryUtil;

public class ModItems {
    public static final InfusibleEntry LUNARITE_INFUSIBLE = new InfusibleEntry(1f, 1f);
    public static final InfusibleEntry COSMOLITE_INFUSIBLE = new InfusibleEntry(1f, 1f);

    public static final Item RAW_LUNARITE = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.RAW_LUNARITE, properties -> new Item(lunarite(properties))
    );
    public static final Item RAW_COSMOLITE = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.RAW_COSMOLITE, properties -> new Item(cosmolite(properties))
    );

    public static final Item LUNARITE_INGOT = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.LUNARITE_INGOT, properties -> new Item(cosmolite(properties))
    );
    public static final Item COSMOLITE_INGOT = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.COSMOLITE_INGOT, properties -> new Item(cosmolite(properties))
    );

    public static final Item LUNARITE_HELMET = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.LUNARITE_HELMET, properties -> new Item(lunariteArmor(properties.humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.HELMET)))
    );
    public static final Item LUNARITE_CHESTPLATE = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.LUNARITE_CHESTPLATE, properties -> new Item(lunariteArmor(properties.humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.CHESTPLATE)))
    );
    public static final Item LUNARITE_LEGGINGS = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.LUNARITE_LEGGINGS, properties -> new Item(lunariteArmor(properties.humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.LEGGINGS)))
    );
    public static final Item LUNARITE_BOOTS = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.LUNARITE_BOOTS, properties -> new Item(lunariteArmor(properties.humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.BOOTS)))
    );

    public static final Item COSMOLITE_HELMET = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.COSMOLITE_HELMET, properties -> new Item(cosmoliteArmor(properties.humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.HELMET)))
    );
    public static final Item COSMOLITE_CHESTPLATE = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.COSMOLITE_CHESTPLATE, properties -> new Item(cosmoliteArmor(properties.humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.CHESTPLATE)))
    );
    public static final Item COSMOLITE_LEGGINGS = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.COSMOLITE_LEGGINGS, properties -> new Item(cosmoliteArmor(properties.humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.LEGGINGS)))
    );
    public static final Item COSMOLITE_BOOTS = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.COSMOLITE_BOOTS, properties -> new Item(cosmoliteArmor(properties.humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.BOOTS)))
    );

    public static final Item TRIM_SWAPPER = RegistryUtil.ItemUtil.registerItem(
            ModItemIds.TRIM_SWAPPER, properties -> new TrimSwapperItem(cosmolite(properties.stacksTo(1)))
    );

    public static final Item LUNARITE_ORE = RegistryUtil.ItemUtil.registerBlock(
            ModBlockItemIds.LUNARITE_ORE, ModBlocks.LUNARITE_ORE, ModItems::lunarite
    );
    public static final Item COSMOLITE_ORE = RegistryUtil.ItemUtil.registerBlock(
            ModBlockItemIds.COSMOLITE_ORE, ModBlocks.COSMOLITE_ORE, ModItems::cosmolite
    );

    public static final Item RAW_LUNARITE_BLOCK = RegistryUtil.ItemUtil.registerBlock(
            ModBlockItemIds.RAW_LUNARITE_BLOCK, ModBlocks.RAW_LUNARITE_BLOCK, ModItems::lunarite
    );
    public static final Item RAW_COSMOLITE_BLOCK = RegistryUtil.ItemUtil.registerBlock(
            ModBlockItemIds.RAW_COSMOLITE_BLOCK, ModBlocks.RAW_COSMOLITE_BLOCK, ModItems::cosmolite
    );

    public static final Item LUNARITE_BLOCK = RegistryUtil.ItemUtil.registerBlock(
            ModBlockItemIds.LUNARITE_BLOCK, ModBlocks.LUNARITE_BLOCK, ModItems::lunarite
    );
    public static final Item COSMOLITE_BLOCK = RegistryUtil.ItemUtil.registerBlock(
            ModBlockItemIds.COSMOLITE_BLOCK, ModBlocks.COSMOLITE_BLOCK, ModItems::cosmolite
    );

    public static final Item DRIED_CHORUS_FLOWER = RegistryUtil.ItemUtil.registerBlock(
            ModBlockItemIds.DRIED_CHORUS_FLOWER, ModBlocks.DRIED_CHORUS_FLOWER
    );

    public static Item.Properties lunarite(Item.Properties properties) {
        return properties.component(DataComponents.ENCHANTABLE, null);
    }

    public static Item.Properties lunariteArmor(Item.Properties properties) {
        return lunarite(properties).component(ModDataComponents.INFUSIBLE, LUNARITE_INFUSIBLE);
    }

    public static Item.Properties cosmolite(Item.Properties properties) {
        return properties.fireResistant().rarity(Rarity.EPIC);
    }

    public static Item.Properties cosmoliteArmor(Item.Properties properties) {
        return cosmolite(properties).component(ModDataComponents.INFUSIBLE, COSMOLITE_INFUSIBLE);
    }

    public static void register() {
        ModCreativeTabs.register();
        ModDataComponents.register();
    }
}
