package net.ntrdeal.ntrdeals_items;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.command.ModCommands;
import net.ntrdeal.ntrdeals_items.component.ModComponents;
import net.ntrdeal.ntrdeals_items.config.ModConfig;
import net.ntrdeal.ntrdeals_items.entity.ModAttributes;
import net.ntrdeal.ntrdeals_items.item.ModItems;
import net.ntrdeal.ntrdeals_items.world.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NTRDealsItems implements ModInitializer {
	public static final String MOD_ID = "ntrdeals_items";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.register();
		ModBlocks.register();
		ModAttributes.register();
		ModConfig.register();
		ModCommands.register();
		ModWorldGen.register();
		ModComponents.register();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}