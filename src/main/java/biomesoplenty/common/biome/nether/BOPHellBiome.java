/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.nether;

import java.util.Random;

import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.biome.BOPBiome;
import biomesoplenty.common.init.ModBiomes;
import biomesoplenty.common.world.generator.GeneratorHive;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

public class BOPHellBiome extends BOPBiome
{
    public IBlockState wallBlock = Blocks.NETHERRACK.getDefaultState();
    public IBlockState roofTopBlock = Blocks.NETHERRACK.getDefaultState();
    public IBlockState roofFillerBlock = Blocks.NETHERRACK.getDefaultState();

    public BOPHellBiome(String idName, PropsBuilder defaultBuilder)
    {
        super(idName, defaultBuilder);

        this.topBlock = Blocks.NETHERRACK.getDefaultState();
        this.fillerBlock = Blocks.NETHERRACK.getDefaultState();

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGhast.class, 50, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityMagmaCube.class, 2, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 1, 4, 4));

        this.addGenerator("hive", GeneratorStage.PRE, (new GeneratorHive.Builder()).amountPerChunk(0.2F).create());
    }

    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);

        // Allow basic properties to be overridden
        this.wallBlock = conf.getBlockState("wallBlock", this.wallBlock);
        this.roofTopBlock = conf.getBlockState("roofTopBlock", this.roofTopBlock);
        this.roofFillerBlock = conf.getBlockState("roofFillerBlock", this.roofFillerBlock);

        // log any warnings from parsing the config file
        for (String msg : conf.flushMessages())
            BiomesOPlenty.logger.info(msg);
        // write default values to a file
        ModBiomes.writeDefaultConfigFile(ModBiomes.BOP_DEFAULTS_DIR, this.getResourceLocation().getResourcePath(), conf);
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.GEMS)) {this.removeGenerator("ruby"); this.removeGenerator("topaz");
        this.removeGenerator("amber"); this.removeGenerator("peridot"); this.removeGenerator("malachite");
        this.removeGenerator("sapphire"); this.removeGenerator("tanzanite"); this.removeGenerator("amethyst");}
         
        if (!settings.isEnabled(GeneratorType.POISON_IVY)) {this.removeGenerator("poison_ivy");}
        
        if (!settings.isEnabled(GeneratorType.BERRY_BUSHES)) {this.removeGenerator("berry_bushes");}
        
        if (!settings.isEnabled(GeneratorType.NETHER_HIVES)) {this.removeGenerator("hive");}
        
        if (!settings.isEnabled(GeneratorType.THORNS)) {this.removeGenerator("thorns");}
        
        if (!settings.isEnabled(GeneratorType.QUICKSAND)) {this.removeGenerator("quicksand");}
        
        if (!settings.isEnabled(GeneratorType.HOT_SPRINGS)) {this.removeGenerator("hot_springs");}
        
        if (!settings.isEnabled(GeneratorType.LIQUID_POISON)) {this.removeGenerator("poison_lakes");}
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double stoneNoiseVal)
    {
        IBlockState roof = this.roofTopBlock;
        IBlockState roofFiller = this.roofFillerBlock;
        IBlockState wall = this.wallBlock;
        IBlockState floor = this.topBlock;
        IBlockState floorFiller = this.fillerBlock;

        int roofDepth = 4;
        int floorDepth = 4;

        int localX = x & 15;
        int localZ = z & 15;
        int localY = 127;
        boolean lastSolid = true;

        while (localY >= 0)
        {
            int blockSkip = 1;
            IBlockState currentState = primer.getBlockState(localZ, localY, localX);

            if (currentState.getBlock() == Blocks.NETHERRACK)
            {
                // initially set to the wall material. this may be replaced later by a roof
                primer.setBlockState(localZ, localY, localX, wall);

                // this must be a floor
                if (!lastSolid)
                {
                    primer.setBlockState(localZ, localY, localX, floor);

                    // iterate over the next few blocks and replace them with the floor material. skip those
                    // blocks too as we've already checked and modified them
                    for (int floorOffset = 1; floorOffset <= floorDepth - 1 && localY - floorOffset >= 0; floorOffset++)
                    {
                        IBlockState state = primer.getBlockState(localZ, localY - floorOffset, localX);
                        blockSkip = floorOffset + 1;

                        if (state.getBlock() == Blocks.NETHERRACK)
                        {
                            primer.setBlockState(localZ, localY - floorOffset, localX, floorFiller);
                        }
                        else break;
                    }
                }

                // update lastSolid for next time
                lastSolid = true;
            }
            else if (currentState.getMaterial() == Material.AIR)
            {
                // previous blocks must be a roof
                if (lastSolid)
                {
                    primer.setBlockState(localZ, localY + 1, localX, roof);

                    // iterate over the previous few blocks to replace them with the roof material
                    for (int roofOffset = 2; roofOffset <= roofDepth && localY + roofOffset <= 127; roofOffset++)
                    {
                        IBlockState state = primer.getBlockState(localZ, localY + roofOffset, localX);

                        // onllocalY replace netherrack or walls, if it's air or anything else then don't continue
                        if (state.getBlock() == Blocks.NETHERRACK || state == wall)
                        {
                            primer.setBlockState(localZ, localY + roofOffset, localX, roofFiller);
                        }
                        else break;
                    }
                }

                // update lastSolid for next time
                lastSolid = false;
            }

            localY -= blockSkip;
        }
    }

    @Override
    public ResourceLocation getBeachLocation()
    {
        return null;
    }
}
