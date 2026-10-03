package net.ntrdeal.ntrdeals_items;

import net.fabricmc.api.ClientModInitializer;
import net.ntrdeal.ntrdeals_items.client.CosmoliteRenderLayer;
import net.ntrdeal.ntrdeals_items.client.ModRenderTypes;

public class NTRDealsItemsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModRenderTypes.register();
        CosmoliteRenderLayer.register();
    }
}