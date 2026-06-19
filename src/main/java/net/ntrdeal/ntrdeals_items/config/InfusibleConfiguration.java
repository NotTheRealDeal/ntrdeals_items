package net.ntrdeal.ntrdeals_items.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.Optional;

public record InfusibleConfiguration(Optional<List<Item>> items, Optional<TagKey<Item>> tag, double positiveMultiplier, double negativeMultiplier) {
    public static final Codec<InfusibleConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ITEM.byNameCodec().listOf().optionalFieldOf("items").forGetter(InfusibleConfiguration::items),
            TagKey.codec(Registries.ITEM).optionalFieldOf("tag").forGetter(InfusibleConfiguration::tag),
            Codec.DOUBLE.fieldOf("positive-multiplier").forGetter(InfusibleConfiguration::positiveMultiplier),
            Codec.DOUBLE.fieldOf("negative-multiplier").forGetter(InfusibleConfiguration::negativeMultiplier)
    ).apply(instance, InfusibleConfiguration::new));

    public InfusibleConfiguration(TagKey<Item> key, double positiveMultiplier, double negativeMultiplier) {
        this(Optional.empty(), Optional.of(key), positiveMultiplier, negativeMultiplier);
    }

    public InfusibleConfiguration(List<Item> items, double positiveMultiplier, double negativeMultiplier) {
        this(Optional.of(items), Optional.empty(), positiveMultiplier, negativeMultiplier);
    }

    public InfusibleConfiguration(List<Item> items, TagKey<Item> key, double positiveMultiplier, double negativeMultiplier) {
        this(Optional.ofNullable(items), Optional.ofNullable(key), positiveMultiplier, negativeMultiplier);
    }
}
