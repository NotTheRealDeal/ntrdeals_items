package net.ntrdeal.ntrdeals_items.config;

import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.ntrdeal.ntrdeals_items.item.component.ModDataComponents;
import org.jspecify.annotations.Nullable;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InfusibleConfig implements ResourceManagerReloadListener {
    public static final Map<Item, InfusibleEntry> ENTRIES = new HashMap<>();
    public static final Map<TagKey<Item>, InfusibleEntry> TAG_ENTRIES = new HashMap<>();

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        ENTRIES.clear();
        TAG_ENTRIES.clear();

        manager.listResources(ModConfig.INFUSIBLE_KEY.getPath(), path -> path.getPath().endsWith(".json")).forEach((_, resource) -> {
            try (InputStreamReader reader = new InputStreamReader(resource.open(), StandardCharsets.UTF_8)) {
                InfusibleConfiguration.CODEC.parse(JsonOps.INSTANCE, JsonParser.parseReader(reader)).result().ifPresent(infusible -> {
                    InfusibleEntry entry = new InfusibleEntry(infusible.positiveMultiplier(), infusible.negativeMultiplier());
                    infusible.tag().ifPresent(tag -> TAG_ENTRIES.put(tag, entry));
                    infusible.items().ifPresent(items -> items.forEach(item -> ENTRIES.put(item, entry)));
                });
            } catch (Exception ignore){}
        });
    }

    public static void lazyInitialize() {
        if (TAG_ENTRIES.isEmpty()) return;
        Map<Item, InfusibleEntry> initialized = new HashMap<>();

        Iterator<Map.Entry<TagKey<Item>, InfusibleEntry>> iterator = TAG_ENTRIES.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<TagKey<Item>, InfusibleEntry> entry = iterator.next();
            BuiltInRegistries.ITEM.get(entry.getKey()).ifPresent(holders -> {
                holders.forEach(holder -> initialized.put(holder.value(), entry.getValue()));
                iterator.remove();
            });
        }

        initialized.forEach(ENTRIES::putIfAbsent);
    }

    @Nullable
    public static InfusibleEntry getEntry(ItemStack stack) {
        if (stack.isEmpty()) return null;
        InfusibleEntry entry = stack.get(ModDataComponents.INFUSIBLE);
        if (entry != null) return entry;
        else if (stack.hasNonDefault(ModDataComponents.INFUSIBLE)) return null;
        lazyInitialize();
        return ENTRIES.get(stack.getItem());
    }
}
