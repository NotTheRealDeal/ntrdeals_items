package net.ntrdeal.ntrdeals_items.item.component;

import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.ntrdeal.ntrdeals_items.config.InfusibleEntry;
import net.ntrdeal.ntrdeals_items.item.component.custom.ChorusMeter;
import net.ntrdeal.ntrdeals_items.item.component.custom.InfuseAttributes;
import net.ntrdeal.ntrdeals_items.item.component.custom.TrimSwapper;
import net.ntrdeal.ntrdeals_items.reference.ModDataComponentIds;
import net.ntrdeal.realapi.util.RegistryUtil;

public class ModDataComponents {
    public static final DataComponentType<TrimSwapper> TRIM_SWAPPER = RegistryUtil.ComponentUtil.register(
            ModDataComponentIds.TRIM_SWAPPER, builder -> builder.persistent(TrimSwapper.CODEC)
                    .networkSynchronized(TrimSwapper.STREAM_CODEC).ignoreSwapAnimation().cacheEncoding()
    );
    public static final DataComponentType<InfuseAttributes> INFUSE_ATTRIBUTES = RegistryUtil.ComponentUtil.register(
            ModDataComponentIds.INFUSE_ATTRIBUTES, builder -> builder.persistent(InfuseAttributes.CODEC)
                    .networkSynchronized(InfuseAttributes.STREAM_CODEC).ignoreSwapAnimation().cacheEncoding()
    );
    public static final DataComponentType<InfusibleEntry> INFUSIBLE = RegistryUtil.ComponentUtil.register(
            ModDataComponentIds.INFUSIBLE, builder -> builder.persistent(InfusibleEntry.CODEC)
                    .networkSynchronized(InfusibleEntry.STREAM_CODEC).cacheEncoding()
    );
    public static final DataComponentType<ChorusMeter> CHORUS_METER = RegistryUtil.ComponentUtil.register(
            ModDataComponentIds.CHORUS_METER, builder -> builder.persistent(ChorusMeter.CODEC)
                    .networkSynchronized(ChorusMeter.STREAM_CODEC).ignoreSwapAnimation().cacheEncoding()
    );

    public static void register() {
        ItemComponentTooltipProviderRegistry.addAfter(DataComponents.TRIM, ModDataComponents.INFUSE_ATTRIBUTES);
        ItemComponentTooltipProviderRegistry.addAfter(DataComponents.TRIM, ModDataComponents.TRIM_SWAPPER);
        ChorusMeter.register();
    }
}