package net.ntrdeal.ntrdeals_items.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.ntrdeal.ntrdeals_items.reference.ModBlockItemIds;

public final class ModBlocks {
    private ModBlocks(){}

    public static final Block LUNARITE_ORE = Blocks.register(ModBlockItemIds.LUNARITE_ORE.block(), properties ->
            new DropExperienceBlock(UniformInt.of(5, 7), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)
    );

    public static final Block COSMOLITE_ORE = Blocks.register(ModBlockItemIds.COSMOLITE_ORE.block(), properties ->
            new DropExperienceBlock(UniformInt.of(5, 7), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)
    );

    public static final Block RAW_LUNARITE_BLOCK = Blocks.register(
            ModBlockItemIds.RAW_LUNARITE_BLOCK.block(), BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)
    );

    public static final Block RAW_COSMOLITE_BLOCK = Blocks.register(
            ModBlockItemIds.RAW_COSMOLITE_BLOCK.block(), BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).mapColor(MapColor.COLOR_BLUE)
    );

    public static final Block LUNARITE_BLOCK = Blocks.register(
            ModBlockItemIds.LUNARITE_BLOCK.block(), BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)
    );

    public static final Block COSMOLITE_BLOCK = Blocks.register(
            ModBlockItemIds.COSMOLITE_BLOCK.block(), BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).mapColor(MapColor.COLOR_BLUE)
    );

    public static final Block DRIED_CHORUS_FLOWER = Blocks.register(
            ModBlockItemIds.DRIED_CHORUS_FLOWER.block(), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).noOcclusion()
    );

    public static void register() {}
}
