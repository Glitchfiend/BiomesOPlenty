package biomesoplenty.init;

import biomesoplenty.common.block.BloodFluid;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static biomesoplenty.api.block.BOPFluids.BLOOD;
import static biomesoplenty.api.block.BOPFluids.FLOWING_BLOOD;

public class ModFluids
{
    public static void setup()
    {
        registerFluids();
    }

    public static void registerFluids()
    {
        FLOWING_BLOOD = registerFluid(() -> new BloodFluid.Flowing(), "flowing_blood");
        BLOOD = registerFluid(() -> new BloodFluid.Source(), "blood");
    }

    public static RegistryObject<Fluid> registerFluid(Supplier<Fluid> fluidSupplier, String name)
    {
        return BiomesOPlenty.FLUID_REGISTER.register(name, fluidSupplier);
    }
}