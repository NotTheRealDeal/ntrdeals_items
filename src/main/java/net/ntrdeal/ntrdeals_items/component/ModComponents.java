package net.ntrdeal.ntrdeals_items.component;

import net.minecraft.world.entity.LivingEntity;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<PassiveRegenComponent> PASSIVE_REGEN = ComponentRegistry.getOrCreate(NTRDealsItems.id("passive_regen"), PassiveRegenComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(LivingEntity.class, PASSIVE_REGEN, PassiveRegenComponent::new);
    }

    public static void register() {
        PassiveRegenComponent.register();
    }
}