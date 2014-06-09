package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenBOPCoral extends WorldGeneratorBOP
{
    public Block flora;
    public int floraMeta;
    private boolean randomMeta;
    private int groupCount = 64;

    public WorldGenBOPCoral() {}
    
    public WorldGenBOPCoral(Block flora, int floraMeta)
    {
        this.flora = flora;
        this.floraMeta = floraMeta;
		this.randomMeta = this.floraMeta == -1;
    }
    
    public WorldGenBOPCoral(Block flora, int floraMeta, int groupCount)
    {
        this.flora = flora;
        this.floraMeta = floraMeta;
        this.groupCount = groupCount;
		this.randomMeta = this.floraMeta == -1;
    }

    @Override
	public boolean generate(World world, Random random, int x, int y, int z)
    {
        for (int l = 0; l < groupCount; ++l)
        {
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            int j1 = y + random.nextInt(4) - random.nextInt(4);
            int k1 = z + random.nextInt(8) - random.nextInt(8);

            if (randomMeta) floraMeta = 12 + random.nextInt(4);
            
            if (world.getBlock(i1, j1, k1) == Blocks.water && world.getBlock(i1, j1 + 1, k1) == Blocks.water && (!world.provider.hasNoSky || j1 < 255) && 
            this.flora.canReplace(world, i1, j1, k1, 0, new ItemStack(flora, 1, floraMeta)))
            {
                world.setBlock(i1, j1, k1, this.flora, this.floraMeta, 2);
            }
        }

        return true;
    }
    
	@Override
    public void setupGeneration(World world, Random random, BOPBiome biome, String featureName, int x, int z)
	{
		for (int i = 0; i < (Integer)biome.theBiomeDecorator.bopFeatures.getFeature(featureName); i++)
		{
			int randX = x + random.nextInt(16) + 8;
			int randZ = z + random.nextInt(16) + 8;
			int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

			this.generate(world, random, randX, randY, randZ);
		}
	}
}