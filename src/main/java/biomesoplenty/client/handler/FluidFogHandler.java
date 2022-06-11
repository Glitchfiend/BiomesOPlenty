package biomesoplenty.client.handler;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModTags;
import net.minecraft.client.Camera;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BiomesOPlenty.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FluidFogHandler
{
    @SubscribeEvent
    public static void getFogColor(EntityViewRenderEvent.FogColors event)
    {
        Camera camera = event.getCamera();

        FluidState fluidstate = camera.level.getFluidState(camera.blockPosition);
        if (fluidstate.is(ModTags.Fluids.BLOOD))
        {
            event.setRed(0.443F);
            event.setGreen(0.141F);
            event.setBlue(0.149F);
        }
    }
}