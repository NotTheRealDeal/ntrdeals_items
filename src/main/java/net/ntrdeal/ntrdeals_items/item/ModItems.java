package net.ntrdeal.ntrdeals_items.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.config.InfusibleEntry;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import net.ntrdeal.ntrdeals_items.item.component.custom.ChorusMeter;
import net.ntrdeal.ntrdeals_items.item.custom.ChorusSwordItem;
import net.ntrdeal.ntrdeals_items.item.custom.TrimSwapperItem;
import net.ntrdeal.ntrdeals_items.item.equipment.ModArmorMaterials;
import net.ntrdeal.ntrdeals_items.reference.ModBlockItemIds;
import net.ntrdeal.ntrdeals_items.reference.ModItemIds;
import net.ntrdeal.realapi.item.component.RealDataComponents;

public final class ModItems {
    private ModItems(){}

    public static final InfusibleEntry LUNARITE_INFUSIBLE = new InfusibleEntry(0.5f, 1f);
    public static final InfusibleEntry COSMOLITE_INFUSIBLE = new InfusibleEntry(1f, 1f);

    public static final Item RAW_LUNARITE = Items.registerItem(ModItemIds.RAW_LUNARITE);
    public static final Item LUNARITE_INGOT = Items.registerItem(ModItemIds.LUNARITE_INGOT);

    public static final Item RAW_COSMOLITE = Items.registerItem(ModItemIds.RAW_COSMOLITE, cosmolite(new Item.Properties()));
    public static final Item COSMOLITE_INGOT = Items.registerItem(ModItemIds.COSMOLITE_INGOT, cosmolite(new Item.Properties()));

    public static final Item LUNARITE_HELMET = Items.registerItem(
            ModItemIds.LUNARITE_HELMET, lunariteArmor(new Item.Properties().humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.HELMET))
    );
    public static final Item LUNARITE_CHESTPLATE = Items.registerItem(
            ModItemIds.LUNARITE_CHESTPLATE, lunariteArmor(new Item.Properties().humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.CHESTPLATE))
    );
    public static final Item LUNARITE_LEGGINGS = Items.registerItem(
            ModItemIds.LUNARITE_LEGGINGS, lunariteArmor(new Item.Properties().humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.LEGGINGS))
    );
    public static final Item LUNARITE_BOOTS = Items.registerItem(
            ModItemIds.LUNARITE_BOOTS, lunariteArmor(new Item.Properties().humanoidArmor(ModArmorMaterials.LUNARITE, ArmorType.BOOTS))
    );

    public static final Item COSMOLITE_HELMET = Items.registerItem(
            ModItemIds.COSMOLITE_HELMET, cosmoliteArmor(new Item.Properties().humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.HELMET))
    );
    public static final Item COSMOLITE_CHESTPLATE = Items.registerItem(
            ModItemIds.COSMOLITE_CHESTPLATE, cosmoliteArmor(new Item.Properties().humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.CHESTPLATE))
    );
    public static final Item COSMOLITE_LEGGINGS = Items.registerItem(
            ModItemIds.COSMOLITE_LEGGINGS, cosmoliteArmor(new Item.Properties().humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.LEGGINGS))
    );
    public static final Item COSMOLITE_BOOTS = Items.registerItem(
            ModItemIds.COSMOLITE_BOOTS, cosmoliteArmor(new Item.Properties().humanoidArmor(ModArmorMaterials.COSMOLITE, ArmorType.BOOTS))
    );

    public static final Item TRIM_SWAPPER = Items.registerItem(
            ModItemIds.TRIM_SWAPPER, properties -> new TrimSwapperItem(cosmolite(properties.stacksTo(1)))
    );

    public static final Item CHORUS_SWORD = Items.registerItem(
            ModItemIds.CHORUS_SWORD, properties -> new ChorusSwordItem(properties
                    .component(ModDataComponents.CHORUS_METER, ChorusMeter.EMPTY)
                    .component(RealDataComponents.PREVENT_CONSUMPTION, Unit.INSTANCE)
                    .stacksTo(1)
                    .enchantable(ToolMaterial.NETHERITE.enchantmentValue())
                    .food(ChorusMeter.FOOD, ChorusMeter.CONSUMABLE)
                    .attributes(ChorusMeter.ATTRIBUTE_MODIFIERS)
                    .component(DataComponents.TOOL, ChorusMeter.TOOL)
                    .component(DataComponents.WEAPON, ChorusMeter.WEAPON)
                    .useCooldown(1f)
            )
    );

    public static final Item LUNARITE_ORE = Items.registerBlock(
            ModBlockItemIds.LUNARITE_ORE, ModBlocks.LUNARITE_ORE
    );
    public static final Item COSMOLITE_ORE = Items.registerBlock(
            ModBlockItemIds.COSMOLITE_ORE, ModBlocks.COSMOLITE_ORE, ModItems::cosmolite
    );

    public static final Item RAW_LUNARITE_BLOCK = Items.registerBlock(
            ModBlockItemIds.RAW_LUNARITE_BLOCK, ModBlocks.RAW_LUNARITE_BLOCK
    );
    public static final Item RAW_COSMOLITE_BLOCK = Items.registerBlock(
            ModBlockItemIds.RAW_COSMOLITE_BLOCK, ModBlocks.RAW_COSMOLITE_BLOCK, ModItems::cosmolite
    );

    public static final Item LUNARITE_BLOCK = Items.registerBlock(
            ModBlockItemIds.LUNARITE_BLOCK, ModBlocks.LUNARITE_BLOCK
    );
    public static final Item COSMOLITE_BLOCK = Items.registerBlock(
            ModBlockItemIds.COSMOLITE_BLOCK, ModBlocks.COSMOLITE_BLOCK, ModItems::cosmolite
    );

    public static final Item DRIED_CHORUS_FLOWER = Items.registerBlock(
            ModBlockItemIds.DRIED_CHORUS_FLOWER, ModBlocks.DRIED_CHORUS_FLOWER
    );

    private static Item.Properties lunariteArmor(Item.Properties properties) {
        return properties.component(ModDataComponents.INFUSIBLE, LUNARITE_INFUSIBLE);
    }

    private static Item.Properties cosmolite(Item.Properties properties) {
        return properties.fireResistant().rarity(Rarity.EPIC);
    }

    private static Item.Properties cosmoliteArmor(Item.Properties properties) {
        return cosmolite(properties).component(ModDataComponents.INFUSIBLE, COSMOLITE_INFUSIBLE);
    }

    public static void register() {
        ModCreativeTabs.register();
        ModDataComponents.register();
    }
}
