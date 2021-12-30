/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.serialization;

import biomesoplenty.serialization.codecs.LenientSimpleMapCodec;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;

public class BOPCodec
{
    public static <K, V> LenientSimpleMapCodec<K, V> lenientSimpleMap(final Codec<K> keyCodec, final Codec<V> elementCodec, final Keyable keys) {
        return new LenientSimpleMapCodec<>(keyCodec, elementCodec, keys);
    }
}
