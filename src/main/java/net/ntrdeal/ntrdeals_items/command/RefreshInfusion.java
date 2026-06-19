package net.ntrdeal.ntrdeals_items.command;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.StringRepresentableArgument;
import net.minecraft.commands.arguments.selector.EntitySelector;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.ntrdeal.ntrdeals_items.config.InfusionConfig;

import java.util.ArrayList;
import java.util.List;

public class RefreshInfusion {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> refreshInfusion = dispatcher.register(Commands.literal("refresh-infusion"));

        ArgumentCommandNode<CommandSourceStack, RefreshType> refreshType = Commands
                .argument("type", RefreshTypeArgument.type())
                .executes(context -> refresh(context, RefreshTypeArgument.getType(context, "type"), false))
                .build();

        ArgumentCommandNode<CommandSourceStack, EntitySelector> players = Commands
                .argument("players", EntityArgument.players())
                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(context -> refresh(context, RefreshTypeArgument.getType(context, "type"), true))
                .build();

        refreshInfusion.addChild(refreshType);
        refreshType.addChild(players);
    }

    public static int refresh(CommandContext<CommandSourceStack> context, RefreshType type, boolean selector) throws CommandSyntaxException {
        List<ServerPlayer> source = !selector ? context.getSource().getPlayer() instanceof ServerPlayer player ? List.of(player) : List.of() : List.of();
        List<ServerPlayer> players = selector ? EntityArgument.getPlayers(context, "players").stream().toList() : source;
        List<ItemStack> unfilteredStacks = new ArrayList<>();
        switch (type) {
            case INVENTORY -> players.forEach(player -> unfilteredStacks.addAll(Lists.newArrayList(player.getInventory())));
            case ENDER_CHEST -> players.forEach(player -> unfilteredStacks.addAll(player.getEnderChestInventory().getItems()));
            case ALL -> players.forEach(player -> {
                unfilteredStacks.addAll(Lists.newArrayList(player.getInventory()));
                unfilteredStacks.addAll(player.getEnderChestInventory().getItems());
            });
        }

        List<ItemStack> stacks = unfilteredStacks.stream().filter(InfusionConfig::refreshInfusion).toList();

        if (!stacks.isEmpty()) {
            context.getSource().sendSuccess(() -> Component.literal("Successfully refresh %s!".formatted(stacks)).withStyle(ChatFormatting.GREEN), false);
            return 1;
        } else {
            context.getSource().sendFailure(Component.literal("Could not refresh any items!"));
            return 0;
        }
    }

    public enum RefreshType implements StringRepresentable {
        INVENTORY("inventory"),
        ENDER_CHEST("ender_chest"),
        ALL("all");

        public static final Codec<RefreshType> CODEC = StringRepresentable.fromEnum(RefreshType::values);

        private final String name;

        RefreshType(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }

    public static class RefreshTypeArgument extends StringRepresentableArgument<RefreshType> {
        public RefreshTypeArgument() {
            super(RefreshType.CODEC, RefreshType::values);
        }

        public static RefreshTypeArgument type() {
            return new RefreshTypeArgument();
        }

        public static RefreshType getType(CommandContext<CommandSourceStack> context, String name) {
            return context.getArgument(name, RefreshType.class);
        }
    }
}
