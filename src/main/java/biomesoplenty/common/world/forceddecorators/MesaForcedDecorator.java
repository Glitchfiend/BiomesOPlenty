package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class MesaForcedDecorator extends ForcedDecorator
{
	public MesaForcedDecorator()
	{
		this.bopWorldFeatures.tinyCactiPerChunk = 10;
		this.bopWorldFeatures.bromeliadsPerChunk = 10;
	}
	
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();
        
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("plants"), 1), 1D);
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        
        return grassMap;
    }
}
