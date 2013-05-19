package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.worldgen.WorldGenNetherMushroom;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;

public class BiomeGenNetherGarden extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;
	
    public BiomeGenNetherGarden(int par1)
    {
        super(par1);
		this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.topBlock = (byte)Block.netherrack.blockID;
        this.fillerBlock = (byte)Block.netherrack.blockID;
		this.customBiomeDecorator.treesPerChunk = 60;
		this.customBiomeDecorator.netherVinesPerChunk = 60;
		this.customBiomeDecorator.mushroomsPerChunk = 30;
		this.customBiomeDecorator.bigMushroomsPerChunk = 30;
		this.customBiomeDecorator.netherWartPerChunk = 3;
		this.customBiomeDecorator.netherGrassPerChunk = 8;
		this.customBiomeDecorator.glowshroomsPerChunk = 3;
		this.customBiomeDecorator.toadstoolsPerChunk = 5;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenNetherMushroom();
    }
}
