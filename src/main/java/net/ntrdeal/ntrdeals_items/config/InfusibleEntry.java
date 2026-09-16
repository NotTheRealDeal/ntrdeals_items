package net.ntrdeal.ntrdeals_items.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record InfusibleEntry(double positiveMultiplier, double negativeMultiplier) {
    public static final Codec<InfusibleEntry> CODEC = RecordCodecBuilder.create(entry -> entry.group(
            Codec.DOUBLE.optionalFieldOf("positive-multiplier", 1d).forGetter(InfusibleEntry::positiveMultiplier),
            Codec.DOUBLE.optionalFieldOf("negative-multiplier", 1d).forGetter(InfusibleEntry::negativeMultiplier)
    ).apply(entry, InfusibleEntry::new));

    public static final StreamCodec<ByteBuf, InfusibleEntry> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, InfusibleEntry::positiveMultiplier,
            ByteBufCodecs.DOUBLE, InfusibleEntry::negativeMultiplier,
            InfusibleEntry::new
    );

    public double multiply(InfusionData data) {
        return data.value() * (data.type() == InfusionEntry.TYPE.POSITIVE ? this.positiveMultiplier : this.negativeMultiplier);
    }
}
