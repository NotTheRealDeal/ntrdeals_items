package net.ntrdeal.ntrdeals_items.item.component;

import net.minecraft.core.component.DataComponentType;
import net.ntrdeal.ntrdeals_items.config.InfusibleEntry;
import net.ntrdeal.ntrdeals_items.reference.ModDataComponentIds;
import net.ntrdeal.realapi.util.RegistryUtil;

public class ModDataComponents {
    public static final DataComponentType<TrimSwapperComponent> TRIM_SWAPPER = RegistryUtil.ComponentUtil.register(
            ModDataComponentIds.TRIM_SWAPPER, builder -> builder.persistent(TrimSwapperComponent.CODEC).networkSynchronized(TrimSwapperComponent.STREAM_CODEC).ignoreSwapAnimation().cacheEncoding()
    );
    public static final DataComponentType<InfuseAttributes> INFUSE_ATTRIBUTES = RegistryUtil.ComponentUtil.register(
            ModDataComponentIds.INFUSE_ATTRIBUTES, builder -> builder.persistent(InfuseAttributes.CODEC).networkSynchronized(InfuseAttributes.STREAM_CODEC).ignoreSwapAnimation().cacheEncoding()
    );
    public static final DataComponentType<InfusibleEntry> INFUSIBLE = RegistryUtil.ComponentUtil.register(
            ModDataComponentIds.INFUSIBLE, builder -> builder.persistent(InfusibleEntry.CODEC).networkSynchronized(InfusibleEntry.STREAM_CODEC).cacheEncoding()
    );

    public static void register() {
        InfuseAttributes.registerTooltip();
        TrimSwapperComponent.registerTooltip();
    }
}
