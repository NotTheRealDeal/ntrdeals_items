package net.ntrdeal.ntrdeals_items.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.reference.ModCreativeModeTabIds;
import net.ntrdeal.realapi.util.RegistryUtil;

public class ModCreativeTabs {
    public static final CreativeModeTab NTRDEALS_ITEMS = RegistryUtil.TabUtil.register(ModCreativeModeTabIds.NTRDEALS_ITEMS, builder ->
            builder.icon(() -> new ItemStack(ModItems.RAW_COSMOLITE)).title(Component.translatable("itemGroup.ntrdeals_items.ntrdeals_items"))
                    .displayItems((_, output) -> {
                        output.accept(ModItems.RAW_LUNARITE);
                        output.accept(ModItems.LUNARITE_INGOT);
                        output.accept(ModBlocks.LUNARITE_ORE);
                        output.accept(ModBlocks.RAW_LUNARITE_BLOCK);
                        output.accept(ModBlocks.LUNARITE_BLOCK);
                        output.accept(ModItems.LUNARITE_HELMET);
                        output.accept(ModItems.LUNARITE_CHESTPLATE);
                        output.accept(ModItems.LUNARITE_LEGGINGS);
                        output.accept(ModItems.LUNARITE_BOOTS);

                        output.accept(ModItems.RAW_COSMOLITE);
                        output.accept(ModItems.COSMOLITE_INGOT);
                        output.accept(ModBlocks.COSMOLITE_ORE);
                        output.accept(ModBlocks.RAW_COSMOLITE_BLOCK);
                        output.accept(ModBlocks.COSMOLITE_BLOCK);
                        output.accept(ModItems.COSMOLITE_HELMET);
                        output.accept(ModItems.COSMOLITE_CHESTPLATE);
                        output.accept(ModItems.COSMOLITE_LEGGINGS);
                        output.accept(ModItems.COSMOLITE_BOOTS);

                        output.accept(ModItems.TRIM_SWAPPER);
                        output.accept(ModItems.CHORUS_SWORD);
                        output.accept(ModBlocks.DRIED_CHORUS_FLOWER);
                    })
    );

    public static void register() {}
}
