package biomesoplenty.init;

import biomesoplenty.api.damagesource.BOPDamageTypes;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes
{
    public static void bootstrap(BootstapContext<DamageType> context)
    {
        context.register(BOPDamageTypes.BRAMBLE, new DamageType("bramble", 0.1F));
        context.register(BOPDamageTypes.FUMAROLE, new DamageType("fumarole", 0.1F));
    }
}
