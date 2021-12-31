/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.noise;

public interface AreaTransformer0
{
    default AreaFactory run(AreaContext context) {
        return () -> {
            return context.createResult((x, y) -> {
                context.initRandom((long)x, (long)y);
                return this.apply(context, x, y);
            });
        };
    }

    int apply(AreaContext context, int x, int y);
}
