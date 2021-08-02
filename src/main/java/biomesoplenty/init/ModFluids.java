package biomesoplenty.init;

import biomesoplenty.common.block.BloodFluid;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

import static biomesoplenty.api.block.BOPFluids.BLOOD;
import static biomesoplenty.api.block.BOPFluids.FLOWING_BLOOD;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFluids
{
    @SubscribeEvent
    public static void registerFluids(RegistryEvent.Register<Fluid> event)
    {
        FLOWING_BLOOD = registerFluid(new BloodFluid.Flowing(), "flowing_blood");
        BLOOD = registerFluid(new BloodFluid.Source(), "blood");
    }

    public static <T extends Fluid> T registerFluid(T fluid, String name)
    {
        fluid.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, name));
        ForgeRegistries.FLUIDS.register(fluid);
        return fluid;
    }
}