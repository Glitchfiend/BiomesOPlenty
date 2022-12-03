/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class ModPaintings
{
    public static final PaintingVariant FIRST_WORLD = new PaintingVariant(32, 32);
    public static final PaintingVariant NETHER_WASP = new PaintingVariant(32, 32);
    public static final PaintingVariant PROMISED_LAND = new PaintingVariant(64, 32);
    public static final PaintingVariant COAST = new PaintingVariant(64, 64);

    public static void setup()
    {
        registerPaintingTypes();
    }

    public static void registerPaintingTypes()
    {
        registerPaintingType(FIRST_WORLD, "first_world");
        registerPaintingType(NETHER_WASP, "nether_wasp");
        registerPaintingType(PROMISED_LAND, "promised_land");
        registerPaintingType(COAST, "coast");
    }

    public static void registerPaintingType(PaintingVariant painting, String name)
    {
        BiomesOPlenty.PAINTING_VARIANT_REGISTER.register(name, () -> painting);
    }
}
