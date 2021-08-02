package biomesoplenty.init;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ModMaterials
{
    public static final Material BLOOD = (new Material.Builder(MaterialColor.COLOR_RED)).noCollider().notSolidBlocking().nonSolid().destroyOnPush().replaceable().liquid().build();
}
