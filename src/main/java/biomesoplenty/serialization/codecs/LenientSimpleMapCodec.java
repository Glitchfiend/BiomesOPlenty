/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.serialization.codecs;

import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.mojang.datafixers.util.Unit;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.BaseMapCodec;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public final class LenientSimpleMapCodec<K, V> extends MapCodec<Map<K, V>> implements BaseMapCodec<K, V>
{
    private final Codec<K> keyCodec;
    private final Codec<V> elementCodec;
    private final Keyable keys;

    public LenientSimpleMapCodec(final Codec<K> keyCodec, final Codec<V> elementCodec, final Keyable keys) {
        this.keyCodec = keyCodec;
        this.elementCodec = elementCodec;
        this.keys = keys;
    }

    @Override
    public Codec<K> keyCodec() {
        return keyCodec;
    }

    @Override
    public Codec<V> elementCodec() {
        return elementCodec;
    }

    @Override
    public <T> Stream<T> keys(final DynamicOps<T> ops) {
        return keys.keys(ops);
    }

    @Override // Modified from decode() in BaseMapCodec, based on Forge's LenientUnboundedMapCodec
    public <T> DataResult<Map<K, V>> decode(DynamicOps<T> ops, MapLike<T> input)
    {
        final ImmutableMap.Builder<K, V> read = ImmutableMap.builder();
        final ImmutableList.Builder<Pair<T, T>> failed = ImmutableList.builder();

        final DataResult<Unit> result = input.entries().reduce(
                DataResult.success(Unit.INSTANCE, Lifecycle.stable()),
                (r, pair) -> {
                    final DataResult<K> k = keyCodec().parse(ops, pair.getFirst());
                    final DataResult<V> v = elementCodec().parse(ops, pair.getSecond());

                    final DataResult<Pair<K, V>> entry = k.apply2stable(Pair::of, v);

                    // Skip any errored entries
                    if (entry.error().isPresent())
                        return r;

                    entry.result().ifPresent(e -> read.put(e.getFirst(), e.getSecond())); // FORGE: This line moved outside the below apply2stable condition
                    return r.apply2stable((u, p) -> u, entry);
                },
                (r1, r2) -> r1.apply2stable((u1, u2) -> u1, r2)
        );

        final Map<K, V> elements = read.build();
        return result.map(unit -> elements).setPartial(elements);
    }

    @Override
    public <T> RecordBuilder<T> encode(final Map<K, V> input, final DynamicOps<T> ops, final RecordBuilder<T> prefix) {
        return BaseMapCodec.super.encode(input, ops, prefix);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LenientSimpleMapCodec<?, ?> that = (LenientSimpleMapCodec<?, ?>) o;
        return Objects.equals(keyCodec, that.keyCodec) && Objects.equals(elementCodec, that.elementCodec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyCodec, elementCodec);
    }

    @Override
    public String toString() {
        return "SimpleMapCodec[" + keyCodec + " -> " + elementCodec + ']';
    }
}
