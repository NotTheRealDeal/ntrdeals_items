package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.ntrdeal.ntrdeals_items.config.InfusionConfigProvider;
import net.ntrdeal.ntrdeals_items.config.InfusionConfiguration;
import net.ntrdeal.ntrdeals_items.config.InfusionData;
import net.ntrdeal.ntrdeals_items.config.InfusionEntry;
import net.ntrdeal.ntrdeals_items.entity.ModAttributes;
import net.ntrdeal.realapi.entity.RealAttributes;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModInfusionProvider extends InfusionConfigProvider {
    public ModInfusionProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateInfusions(HolderLookup.Provider provider, Consumer<InfusionConfiguration> consumer) {
        consumer.accept(new InfusionConfiguration(TrimMaterials.AMETHYST, Map.of(
                ModAttributes.PASSIVE_REGENERATION, new InfusionData(0.25, InfusionEntry.TYPE.POSITIVE),
                RealAttributes.APPETITE, new InfusionData(-0.15d, InfusionEntry.TYPE.POSITIVE)
        )));

        consumer.accept(new InfusionConfiguration(TrimMaterials.COPPER, Map.of(
                Attributes.SCALE, new InfusionData(0.25d, InfusionEntry.TYPE.NEGATIVE),
                Attributes.MAX_HEALTH, new InfusionData(5d, InfusionEntry.TYPE.POSITIVE)
        )));

        consumer.accept(new InfusionConfiguration(TrimMaterials.DIAMOND, Map.of(
                Attributes.ARMOR, new InfusionData(2d, InfusionEntry.TYPE.POSITIVE),
                RealAttributes.SHIELD_FRAGILITY, new InfusionData(-0.15d, InfusionEntry.TYPE.POSITIVE)
        )));

        consumer.accept(new InfusionConfiguration(TrimMaterials.EMERALD, Map.of(
                Attributes.WATER_MOVEMENT_EFFICIENCY, new InfusionData(0.25d, InfusionEntry.TYPE.POSITIVE),
                Attributes.SUBMERGED_MINING_SPEED, new InfusionData(0.2d, InfusionEntry.TYPE.POSITIVE),
                Attributes.OXYGEN_BONUS, new InfusionData(0.75d, InfusionEntry.TYPE.POSITIVE)
        )));

        consumer.accept(new InfusionConfiguration(TrimMaterials.GOLD, Map.of(
                Attributes.BLOCK_BREAK_SPEED, new InfusionData(0.25d, InfusionEntry.TYPE.POSITIVE),
                Attributes.ATTACK_SPEED, new InfusionData(0.6d, InfusionEntry.TYPE.POSITIVE),
                Attributes.ATTACK_DAMAGE, new InfusionData(-2d, InfusionEntry.TYPE.NEGATIVE)
        )));

        consumer.accept(new InfusionConfiguration(TrimMaterials.IRON, Map.of(
                RealAttributes.ARMOR_PENETRATION, new InfusionData(1.5d, InfusionEntry.TYPE.POSITIVE),
                Attributes.ARMOR, new InfusionData(-1.5d, InfusionEntry.TYPE.NEGATIVE),
                RealAttributes.SHIELD_FRAGILITY, new InfusionData(0.15d, InfusionEntry.TYPE.NEGATIVE)
        )));

        consumer.accept(new InfusionConfiguration(TrimMaterials.LAPIS, Map.of(
                ModAttributes.CLAMPED_SCALE, new InfusionData(-0.25d, InfusionEntry.TYPE.POSITIVE),
                Attributes.MAX_HEALTH, new InfusionData(-5d, InfusionEntry.TYPE.NEGATIVE)
        )));

        consumer.accept(new InfusionConfiguration(TrimMaterials.NETHERITE, Map.of(
                Attributes.KNOCKBACK_RESISTANCE, new InfusionData(0.1d, InfusionEntry.TYPE.POSITIVE),
                RealAttributes.FIRE_DAMAGE_MULTIPLIER, new InfusionData(-0.25d, InfusionEntry.TYPE.POSITIVE),
                Attributes.ARMOR_TOUGHNESS, new InfusionData(2d, InfusionEntry.TYPE.POSITIVE),
                RealAttributes.BANE_OF_ADOLESCENCE, new InfusionData(2d, InfusionEntry.TYPE.POSITIVE)
        )));

        consumer.accept(new InfusionConfiguration(TrimMaterials.QUARTZ, Map.of(
                Attributes.ATTACK_KNOCKBACK, new InfusionData(0.5d, InfusionEntry.TYPE.POSITIVE),
                Attributes.FALL_DAMAGE_MULTIPLIER, new InfusionData(-0.25d, InfusionEntry.TYPE.POSITIVE)
        )));

        consumer.accept(new InfusionConfiguration(TrimMaterials.REDSTONE, Map.of(
                RealAttributes.MOVEMENT_SCALE, new InfusionData(0.125d, InfusionEntry.TYPE.POSITIVE),
                Attributes.STEP_HEIGHT, new InfusionData(0.25d, InfusionEntry.TYPE.POSITIVE)
        )));

        consumer.accept(new InfusionConfiguration(TrimMaterials.RESIN, Map.of(
                Attributes.ATTACK_DAMAGE, new InfusionData(-2d, InfusionEntry.TYPE.NEGATIVE),
                RealAttributes.CHARGE_TIME, new InfusionData(0.125d, InfusionEntry.TYPE.NEGATIVE),
                RealAttributes.RANGED_ATTACK_MULTIPLIER, new InfusionData(0.25d, InfusionEntry.TYPE.POSITIVE)
        )));
    }
}
