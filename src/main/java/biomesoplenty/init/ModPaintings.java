package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPaintings
{
    public static final PaintingType FIRST_WORLD = new PaintingType(32, 32);
    public static final PaintingType NETHER_WASP = new PaintingType(32, 32);
    public static final PaintingType PROMISED_LAND = new PaintingType(64, 32);
    public static final PaintingType JADE_CLIFFS = new PaintingType(64, 64);

    @SubscribeEvent
    public static void registerPaintingTypes(RegistryEvent.Register<PaintingType> event)
    {
        registerPaintingType(FIRST_WORLD, "first_world");
        registerPaintingType(NETHER_WASP, "nether_wasp");
        registerPaintingType(PROMISED_LAND, "promised_land");
        registerPaintingType(JADE_CLIFFS, "jade_cliffs");
    }

    public static PaintingType registerPaintingType(PaintingType entry, String name)
    {
        entry.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, name));
        ForgeRegistries.PAINTING_TYPES.register(entry);
        return entry;
    }
}
