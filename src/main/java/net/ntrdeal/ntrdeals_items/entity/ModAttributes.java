package net.ntrdeal.ntrdeals_items.entity;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.ntrdeal.ntrdeals_items.reference.ModAttributeIds;
import net.ntrdeal.realapi.entity.AttributeBuilder;

public class ModAttributes {
    public static final Holder<Attribute> PASSIVE_REGENERATION = AttributeBuilder.of(ModAttributeIds.PASSIVE_REGENERATE)
            .range(0d, 0d, 1024d).sync().sentiment(Attribute.Sentiment.POSITIVE).buildAndRegister();

    public static final Holder<Attribute> CLAMPED_SCALE = AttributeBuilder.of(ModAttributeIds.CLAMPED_SCALE)
            .range(0d, -0.75d, 0d).sync().sentiment(Attribute.Sentiment.NEUTRAL).languageKey(Attributes.SCALE.value().getDescriptionId()).buildAndRegister();

    public static final Holder<Attribute> SANITY = AttributeBuilder.of(ModAttributeIds.SANITY)
            .range(0d, -1024d, 1024d).sync().sentiment(Attribute.Sentiment.POSITIVE).buildAndRegister();

    public static void register() {
    }
}
