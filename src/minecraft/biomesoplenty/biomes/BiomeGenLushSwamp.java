package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenSwampTall;

public class BiomeGenLushSwamp extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    @SuppressWarnings("unchecked")
	public BiomeGenLushSwamp(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 10;
		this.customBiomeDecorator.grassPerChunk = 4;
        this.customBiomeDecorator.mushroomsPerChunk = 8;
        this.customBiomeDecorator.reedsPerChunk = 16;
		this.customBiomeDecorator.cattailsPerChunk = 10;
		this.customBiomeDecorator.highCattailsPerChunk = 5;
        this.customBiomeDecorator.waterlilyPerChunk = 3;
		this.customBiomeDecorator.hydrangeasPerChunk = 1;
		this.customBiomeDecorator.reedsBOPPerChunk = 5;
		this.customBiomeDecorator.poisonWaterPerChunk = 2;
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenSwampTall();
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16);
            int var8 = par2Random.nextInt(28) + 4;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);

            if (var10 == Block.stone.blockID)
            {
                par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
            }
        }
    }
}
