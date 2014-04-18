package biomesoplenty.common.world.features;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class WorldGenBOPFlora extends WorldGeneratorBOP
{
    public Block flora;
    public int floraMeta;
    private int groupCount = 64;

    public WorldGenBOPFlora() {}
    
    public WorldGenBOPFlora(Block flora, int floraMeta)
    {
        this.flora = flora;
        this.floraMeta = floraMeta;
    }
    
    public WorldGenBOPFlora(Block flora, int floraMeta, int groupCount)
    {
        this.flora = flora;
        this.floraMeta = floraMeta;
        this.groupCount = groupCount;
    }

    @Override
	public boolean generate(World world, Random random, int x, int y, int z)
    {
        for (int l = 0; l < groupCount; ++l)
        {
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            int j1 = y + random.nextInt(4) - random.nextInt(4);
            int k1 = z + random.nextInt(8) - random.nextInt(8);

            if (world.isAirBlock(i1, j1, k1) && (!world.provider.hasNoSky || j1 < 255) && 
            (flora == Blocks.web ? world.getBlock(i1, j1 + 1, k1) == BOPBlockHelper.get("leaves2") || world.getBlock(i1, j1 + 1, k1) == BOPBlockHelper.get("colorizedLeaves2") || world.getBlock(i1, j1 - 1, k1) == Blocks.grass : 
            this.flora.canReplace(world, i1, j1, k1, 0, new ItemStack(flora, 1, floraMeta))))
            {
                world.setBlock(i1, j1, k1, this.flora, this.floraMeta, 2);
            }
        }

        return true;
    }
    
	@Override
    public void setupGeneration(World world, Random random, BiomeGenBase biome, String featureName, int x, int z)
	{
		for (int i = 0; i < (Integer)BOPDecorationManager.getBiomeFeatures(biome.biomeID).getFeature(featureName); i++)
		{
			int randX = x + random.nextInt(16) + 8;
			int randZ = z + random.nextInt(16) + 8;
			int randY = random.nextInt(256);

            this.generate(world, random, randX, randY, randZ);
		}
	}
}