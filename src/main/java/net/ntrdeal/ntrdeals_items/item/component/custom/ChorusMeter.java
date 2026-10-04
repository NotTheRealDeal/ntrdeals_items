package net.ntrdeal.ntrdeals_items.item.component.custom;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.event.player.BlockEvents;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.references.BlockItemIds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.item.consume_effects.TeleportRandomlyConsumeEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.ntrdeal.ntrdeals_items.compat.EnchancementCompat;
import net.ntrdeal.ntrdeals_items.item.ModItems;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import net.ntrdeal.realapi.item.component.type.ConsumableModifier;
import net.ntrdeal.realapi.item.component.type.InventoryTicker;
import net.ntrdeal.realapi.item.component.type.PostHurtListener;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public record ChorusMeter(int chorus) implements ConsumableListener, PostHurtListener, InventoryTicker, ConsumableModifier, TooltipProvider {
    private static final TextColor COLOR_1 = TextColor.fromRgb(0xf7e9a3), COLOR_2 = TextColor.fromRgb(0x8e678d);
    private static final ConsumeEffect TELEPORT = new TeleportRandomlyConsumeEffect();
    private static final HolderGetter<Block> HOLDER_GETTER = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);

    public static final ChorusMeter EMPTY = new ChorusMeter(24);
    public static final Codec<ChorusMeter> CODEC = Codec.INT.xmap(ChorusMeter::new, ChorusMeter::chorus);
    public static final StreamCodec<ByteBuf, ChorusMeter> STREAM_CODEC = ByteBufCodecs.VAR_INT.map(ChorusMeter::new, ChorusMeter::chorus);

    public static final Consumable CONSUMABLE = Consumables.CHORUS_FRUIT;
    public static final FoodProperties FOOD = Foods.CHORUS_FRUIT;
    public static final Tool TOOL = new Tool(
            List.of(
                    Tool.Rule.minesAndDrops(HolderSet.direct(BuiltInRegistries.BLOCK.getOrThrow(BlockItemIds.COBWEB.block())), 15.0F),
                    Tool.Rule.overrideSpeed(HOLDER_GETTER.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
                    Tool.Rule.overrideSpeed(HOLDER_GETTER.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
            ),
            1f,
            0,
            false
    );
    public static final ItemAttributeModifiers ATTRIBUTE_MODIFIERS = ToolMaterial.NETHERITE.createSwordAttributes(3f, -2.4f);
    public static final Weapon WEAPON = new Weapon(0);

    public void add(ItemStack stack, int addingChorus) {
        if (addingChorus < 1) return;
        stack.set(ModDataComponents.CHORUS_METER, new ChorusMeter(Math.clamp(this.chorus + addingChorus, 0, EMPTY.chorus())));
        if (this.chorus != 0) return;
        stack.set(DataComponents.CONSUMABLE, CONSUMABLE);
        stack.set(DataComponents.FOOD, FOOD);
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, ATTRIBUTE_MODIFIERS);
        stack.set(DataComponents.TOOL, TOOL);
        stack.set(DataComponents.WEAPON, WEAPON);
    }

    public void decrement(LivingEntity entity, ItemStack stack) {
        if (entity.hasInfiniteMaterials()) return;
        int decrement = EnchancementCompat.decrementAmount(stack, entity.registryAccess());
        stack.set(ModDataComponents.CHORUS_METER, new ChorusMeter(Math.clamp(this.chorus - decrement, 0, EMPTY.chorus())));
        if (this.chorus != decrement) return;
        stack.remove(DataComponents.CONSUMABLE);
        stack.remove(DataComponents.FOOD);
        stack.remove(DataComponents.ATTRIBUTE_MODIFIERS);
        stack.remove(DataComponents.TOOL);
        stack.remove(DataComponents.WEAPON);
    }

    @Override
    public void onConsume(Level level, LivingEntity user, ItemStack stack, Consumable consumable) {
        if (this.chorus < 1) return;
        this.decrement(user, stack);
    }

    @Override
    public void postHurt(ItemStack stack, LivingEntity attacker, LivingEntity attacked) {
        Level level = attacker.level();
        this.decrement(attacker, stack);
        if (this.chorus < 1 || level.isClientSide()) return;
        TELEPORT.apply(level, stack, attacked);
    }

    @Override
    public void tick(ItemStack stack, Level level, Entity owner, @Nullable EquipmentSlot slot) {
        if (this.chorus >= EMPTY.chorus() || level.isClientSide() || !stack.isEnchanted()) return;
        int mendingLevel = EnchancementCompat.mendingLevel(stack, level.registryAccess());
        if (mendingLevel <= 0 || (level.getGameTime() % (200 / mendingLevel)) != 0) return;
        this.add(stack, 1);
    }

    @Override
    public boolean decrementsStack(ItemStack stack, LivingEntity entity) {
        return false;
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        consumer.accept(Component.literal("Chorus: ").withColor(COLOR_1).append(
                Component.literal(getChorus(components) + "/" + EMPTY.chorus()).withColor(COLOR_2)
        ));
    }

    public static int getChorus(DataComponentGetter getter) {
        return Math.clamp(getter.getOrDefault(ModDataComponents.CHORUS_METER, EMPTY).chorus(), 0, EMPTY.chorus());
    }

    public static void register() {
        ItemComponentTooltipProviderRegistry.addBefore(DataComponents.JUKEBOX_PLAYABLE, ModDataComponents.CHORUS_METER);

        BlockEvents.USE_ITEM_ON.register((
                stack, state, level, pos,
                player, hand, _
        ) -> {
            if (!state.is(Blocks.CHORUS_FLOWER) || !stack.is(Items.DIAMOND_SWORD) || state.getValue(ChorusFlowerBlock.AGE) < 5) return null;

            ItemStack newStack = stack.transmuteCopy(ModItems.CHORUS_SWORD);
            newStack.remove(DataComponents.DAMAGE);
            player.setItemInHand(hand, newStack);
            player.playSound(SoundEvents.ITEM_PICKUP, 1f, 1f);
            level.destroyBlock(pos, false, player);
            return InteractionResult.SUCCESS;
        });
    }
}
