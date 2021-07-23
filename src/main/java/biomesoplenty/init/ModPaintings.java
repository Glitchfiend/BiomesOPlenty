package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPaintings
{
    public static final Motive FIRST_WORLD = new Motive(32, 32);
    public static final Motive NETHER_WASP = new Motive(32, 32);
    public static final Motive PROMISED_LAND = new Motive(64, 32);
    public static final Motive COAST = new Motive(64, 64);

    @SubscribeEvent
    public static void registerPaintingTypes(RegistryEvent.Register<Motive> event)
    {
        registerPaintingType(FIRST_WORLD, "first_world");
        registerPaintingType(NETHER_WASP, "nether_wasp");
        registerPaintingType(PROMISED_LAND, "promised_land");
        registerPaintingType(COAST, "coast");
    }

    public static Motive registerPaintingType(Motive entry, String name)
    {
        entry.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, name));
        ForgeRegistries.PAINTING_TYPES.register(entry);
        return entry;
    }
}
