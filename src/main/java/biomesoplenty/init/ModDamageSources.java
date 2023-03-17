package biomesoplenty.init;

import biomesoplenty.api.damagesource.BOPDamageTypes;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageSources
{
    public static void setup()
    {
        register(BOPDamageTypes.BRAMBLE, new DamageType("bramble", 0.1F));
        register(BOPDamageTypes.FUMAROLE, new DamageType("fumarole", 0.1F));
    }

    private static void register(ResourceKey<DamageType> key, DamageType type)
    {
        BiomesOPlenty.DAMAGE_TYPE_REGISTER.register(key.location().getPath().toString(), () -> type);
    }
}
