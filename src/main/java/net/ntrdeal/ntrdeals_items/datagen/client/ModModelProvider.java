package net.ntrdeal.ntrdeals_items.datagen.client;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.item.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public static final ModelTemplate FULL_CHORUS_FLOWER_TEMPLATE = new ModelTemplate(
            Optional.of(NTRDealsItems.id("block/full_chorus_flower_template")), Optional.empty(), TextureSlot.TEXTURE
    );
    public TexturedModel.Provider FULL_CHORUS_FLOWER_MODEL = TexturedModel.createDefault(TextureMapping::defaultTexture, FULL_CHORUS_FLOWER_TEMPLATE);

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators generator) {
        generator.createTrivialCube(ModBlocks.LUNARITE_ORE);
        generator.createTrivialCube(ModBlocks.COSMOLITE_ORE);
        generator.createTrivialCube(ModBlocks.RAW_LUNARITE_BLOCK);
        generator.createTrivialCube(ModBlocks.RAW_COSMOLITE_BLOCK);
        generator.createTrivialCube(ModBlocks.LUNARITE_BLOCK);
        generator.createTrivialCube(ModBlocks.COSMOLITE_BLOCK);

        generator.createTrivialBlock(ModBlocks.DRIED_CHORUS_FLOWER, FULL_CHORUS_FLOWER_MODEL);
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators generator) {
        generator.generateFlatItem(ModItems.RAW_LUNARITE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.RAW_COSMOLITE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.LUNARITE_INGOT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.COSMOLITE_INGOT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.TRIM_SWAPPER, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(ModItems.CHORUS_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);

        generator.generateTrimmableArmorSet(
                ModItems.LUNARITE_HELMET, ModItems.LUNARITE_CHESTPLATE,
                ModItems.LUNARITE_LEGGINGS, ModItems.LUNARITE_BOOTS,
                false, Map.of()
        );

        generator.generateTrimmableArmorSet(
                ModItems.COSMOLITE_HELMET, ModItems.COSMOLITE_CHESTPLATE,
                ModItems.COSMOLITE_LEGGINGS, ModItems.COSMOLITE_BOOTS,
                false, Map.of()
        );
    }
}
