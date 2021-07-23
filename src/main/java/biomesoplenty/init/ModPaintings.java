package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Motive;

public class ModPaintings
{
    public static final Motive FIRST_WORLD = new Motive(32, 32);
    public static final Motive NETHER_WASP = new Motive(32, 32);
    public static final Motive PROMISED_LAND = new Motive(64, 32);
    public static final Motive COAST = new Motive(64, 64);

    public static void registerPaintingTypes()
    {
        registerPaintingType(FIRST_WORLD, "first_world");
        registerPaintingType(NETHER_WASP, "nether_wasp");
        registerPaintingType(PROMISED_LAND, "promised_land");
        registerPaintingType(COAST, "coast");
    }

    public static Motive registerPaintingType(Motive entry, String name)
    {
        Registry.register(Registry.MOTIVE, new ResourceLocation(BiomesOPlenty.MOD_ID, name), entry);
        return entry;
    }
}
