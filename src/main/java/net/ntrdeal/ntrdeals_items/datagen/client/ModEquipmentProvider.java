package net.ntrdeal.ntrdeals_items.datagen.client;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.ntrdeal.ntrdeals_items.item.equipment.ModEquipmentAssets;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEquipmentProvider extends FabricCodecDataProvider<EquipmentClientInfo> {
    public ModEquipmentProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, PackOutput.Target.RESOURCE_PACK, "equipment", EquipmentClientInfo.CODEC);
    }

    @Override
    protected void configure(BiConsumer<Identifier, EquipmentClientInfo> provider, HolderLookup.Provider lookup) {
        EquipmentClientInfo.Layer lunarite = EquipmentClientInfo.Layer.leatherDyeable(ModEquipmentAssets.LUNARITE.identifier(), false);
        EquipmentClientInfo.Layer cosmolite = EquipmentClientInfo.Layer.leatherDyeable(ModEquipmentAssets.COSMOLITE.identifier(), false);

        provider.accept(ModEquipmentAssets.LUNARITE.identifier(), EquipmentClientInfo.builder()
                .addLayers(EquipmentClientInfo.LayerType.HUMANOID, lunarite).addLayers(EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS, lunarite)
            .build()
        );

        provider.accept(ModEquipmentAssets.COSMOLITE.identifier(), EquipmentClientInfo.builder()
                .addLayers(EquipmentClientInfo.LayerType.HUMANOID, cosmolite).addLayers(EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS, cosmolite)
            .build()
        );
    }

    @Override public String getName() {return "equipment";}
}
