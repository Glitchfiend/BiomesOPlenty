package biomesoplenty.init;

import biomesoplenty.api.damagesource.BOPDamageTypes;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes
{
    public static void bootstrap(BootstapContext<DamageType> context)
    {
        context.register(BOPDamageTypes.BRAMBLE, new DamageType("biomesoplenty.bramble", 0.1F));
        context.register(BOPDamageTypes.FUMAROLE, new DamageType("biomesoplenty.fumarole", 0.1F));
    }
}
