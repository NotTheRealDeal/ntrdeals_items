package net.ntrdeal.ntrdeals_items.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public final class ModWorldGen {
    private ModWorldGen(){}

    public static void register() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                ModPlacedFeatures.LUNARITE_GEODE
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                ModPlacedFeatures.COSMOLITE_GEODE
        );
    }
}