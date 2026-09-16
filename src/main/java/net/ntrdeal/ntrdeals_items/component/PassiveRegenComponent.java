package net.ntrdeal.ntrdeals_items.component;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.ntrdeal.ntrdeals_items.command.ModGameRules;
import net.ntrdeal.ntrdeals_items.entity.ModAttributes;
import net.ntrdeal.realapi.compat.cardinal.CardinalUtil;
import org.ladysnake.cca.api.v3.component.TransientComponent;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

@SuppressWarnings("UnstableApiUsage")
public class PassiveRegenComponent implements TransientComponent, ServerTickingComponent {
    private final LivingEntity entity;
    private int attackedTicks;

    public PassiveRegenComponent(LivingEntity entity) {
        this.entity = entity;
        if (entity.level() instanceof ServerLevel level) {
            this.attackedTicks = level.getGameRules().get(ModGameRules.COMBAT_TICKS);
        }
    }

    public void attacked() {
        if (this.entity.level() instanceof ServerLevel level) {
            this.attackedTicks = level.getGameRules().get(ModGameRules.COMBAT_TICKS);
        }
    }

    @Override
    public void serverTick() {
        if (this.attackedTicks > 0) attackedTicks--;
        else this.entity.heal((float) (this.entity.getAttributeValue(ModAttributes.PASSIVE_REGENERATION)/20f));
    }

    public static void register() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register((
                entity, source, _,
                damageTaken, blocked
        ) -> {
            if (!blocked && damageTaken > 0 && source.getEntity() != null) CardinalUtil.ifPresent(ModComponents.PASSIVE_REGEN, entity, PassiveRegenComponent::attacked);
        });
    }
}
