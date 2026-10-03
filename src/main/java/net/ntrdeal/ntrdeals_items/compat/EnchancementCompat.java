package net.ntrdeal.ntrdeals_items.compat;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.ntrdeal.ntrdeals_items.item.component.custom.ChorusMeter;

public class EnchancementCompat {
    public static final String MOD_ID = "enchancement";
    public static final boolean LOADED = FabricLoader.getInstance().isModLoaded(MOD_ID);

    public static int decrementAmount(ItemStack stack, RegistryAccess access) {
        return Math.clamp(ChorusMeter.getChorus(stack), 1, stack.isEnchanted() ? LOADED ? 1 : Math.clamp(4 - access.get(Enchantments.UNBREAKING).map(
                stack.getEnchantments()::getLevel
        ).orElse(0), 1, 4) : 4);
    }

    public static int mendingLevel(ItemStack stack, RegistryAccess access) {
        return stack.isEnchanted() ? LOADED ? 1 : access.get(Enchantments.MENDING).map(stack.getEnchantments()::getLevel).orElse(0) : 0;
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
