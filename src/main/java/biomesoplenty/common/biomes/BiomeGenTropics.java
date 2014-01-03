package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenTropics extends BOPBiome
{

    @SuppressWarnings("unchecked")
    public BiomeGenTropics(int par1)
    {
        super(par1);
        /*
        theBiomeDecorator = new BiomeDecoratorBOP(this);
        customBiomeDecorator = (BiomeDecoratorBOP) theBiomeDecorator;
        customBiomeDecorator.treesPerChunk = 12;
        customBiomeDecorator.grassPerChunk = 7;
        customBiomeDecorator.wheatGrassPerChunk = 4;
        customBiomeDecorator.flowersPerChunk = 10;
        customBiomeDecorator.sandPerChunk = 50;
        customBiomeDecorator.sandPerChunk2 = 50;
        customBiomeDecorator.orangeFlowersPerChunk = 10;
        customBiomeDecorator.whiteFlowersPerChunk = 4;
        customBiomeDecorator.sunflowersPerChunk = 2;
        customBiomeDecorator.hibiscusPerChunk = 45;
        customBiomeDecorator.shrubsPerChunk = 4;
        customBiomeDecorator.generatePumpkins = false;
        spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
        spawnableCreatureList.clear();
        */
    }

    /*
    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 12 + par2Random.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16);
            int var8 = par2Random.nextInt(28) + 4;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);

            Block block = Block.blocksList[var10];
            if (block != null
                    && block.isGenMineableReplaceable(par1World, var7, var8,
                            var9, Block.stone.blockID))
            {
                par1World.setBlock(var7, var8, var9,
                        Blocks.amethystOre.get().blockID, 6, 2);
            }
        }
    }
    */

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    /*
    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return par1Random.nextInt(2) == 0 ? new WorldGenPalmTree1()
                : (par1Random.nextInt(2) == 0 ? new WorldGenTropicsShrub()
                        : new WorldGenShrub(0, 0));
    }
    */

    /**
     * Fog Color
     */
    /*
    @Override
    public int getFogColour()
    {
        return 7724287;
    }
    */

    /**
     * takes temperature, returns color
     */
    /*
    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors)
        {
            return 507391;
        }
        else
        {
            par1 /= 3.0F;

            if (par1 < -1.0F)
            {
                par1 = -1.0F;
            }

            if (par1 > 1.0F)
            {
                par1 = 1.0F;
            }

            return Color.getHSBColor(0.62222224F - par1 * 0.05F,
                    0.5F + par1 * 0.1F, 1.0F).getRGB();
        }
    }

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 1.0F;
    }
    */
}
