package net.ntrdeal.ntrdeals_items;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.ntrdeal.ntrdeals_items.datagen.*;
import net.ntrdeal.ntrdeals_items.datagen.client.ModEquipmentProvider;
import net.ntrdeal.ntrdeals_items.datagen.client.ModModelProvider;
import net.ntrdeal.ntrdeals_items.datagen.client.language.ModEnglishProvider;
import net.ntrdeal.ntrdeals_items.datagen.tags.ModAttributeTagProvider;
import net.ntrdeal.ntrdeals_items.datagen.tags.ModBlockTagProvider;
import net.ntrdeal.ntrdeals_items.datagen.tags.ModDamageTypeTagProvider;
import net.ntrdeal.ntrdeals_items.datagen.tags.ModItemTagProvider;
import net.ntrdeal.ntrdeals_items.world.ModConfiguredFeatures;
import net.ntrdeal.ntrdeals_items.world.ModPlacedFeatures;
import org.jspecify.annotations.NonNull;

public class NTRDealsItemsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(@NonNull FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(ModAttributeTagProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModDamageTypeTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModAdvancementProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModInfusionProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModRegistryProvider::new);

		pack.addProvider(ModEnglishProvider::new);
		pack.addProvider(ModEquipmentProvider::new);
		pack.addProvider(ModModelProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder builder) {
		builder.add(Registries.FEATURE, ModConfiguredFeatures::bootstrap);
		builder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}