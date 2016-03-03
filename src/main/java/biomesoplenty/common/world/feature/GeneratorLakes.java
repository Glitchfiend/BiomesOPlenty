/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class GeneratorLakes extends BOPGeneratorBase
{
    
    public static class Builder extends BOPGeneratorBase.InnerBuilder<Builder, GeneratorLakes> implements IGeneratorBuilder<GeneratorLakes>
    {        
        protected IBlockState liquid;
        protected IBlockState frozenLiquid;
        protected IBlockState grassBorderWith;
        protected IBlockPosQuery grassReplace;
        protected IBlockState lineWith;        
        
        public Builder liquid(IBlockState a) {this.liquid = a; return this.self();}
        public Builder liquid(Block a) {this.liquid = a.getDefaultState(); return this.self();}
        public Builder frozenLiquid(IBlockState a) {this.frozenLiquid = a; return this.self();}
        public Builder frozenLiquid(Block a) {this.frozenLiquid = a.getDefaultState(); return this.self();}
        public Builder grassBorderWith(IBlockState a) {this.grassBorderWith = a; return this.self();}
        public Builder grassBorderWith(Block a) {this.grassBorderWith = a.getDefaultState(); return this.self();}
        public Builder grassReplace(IBlockPosQuery a) {this.grassReplace = a; return this.self();}
        public Builder grassReplace(String a) throws BlockQueryParseException {this.grassReplace = BlockQuery.parseQueryString(a); return this.self();}
        public Builder grassReplace(Block a) {this.grassReplace = new BlockQueryBlock(a); return this.self();}
        public Builder grassReplace(IBlockState a) {this.grassReplace = new BlockQueryState(a); return this.self();}
        public Builder lineWith(IBlockState a) {this.lineWith = a; return this.self();}
        public Builder lineWith(Block a) {this.lineWith = a.getDefaultState(); return this.self();}
        
        public Builder waterLakeForBiome(BiomeGenBase a)
        {
            this.liquid = Blocks.water.getDefaultState();
            this.frozenLiquid = Blocks.ice.getDefaultState();
            this.grassBorderWith = a.topBlock;
            this.grassReplace = new BlockQueryBlock(a.fillerBlock.getBlock());
            this.lineWith = null;
            return this;
        }
        public Builder lavaLake()
        {
            this.liquid = Blocks.lava.getDefaultState();
            this.frozenLiquid = null;
            this.grassBorderWith = null;
            this.grassReplace = null;
            this.lineWith = Blocks.stone.getDefaultState();
            return this;
        }
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.liquid = Blocks.water.getDefaultState();
            this.frozenLiquid = Blocks.ice.getDefaultState();
            this.grassBorderWith = Blocks.grass.getDefaultState();
            this.grassReplace = new BlockQueryBlock(Blocks.dirt);
            this.lineWith = null;
        }
        
        @Override
        public GeneratorLakes create() {
            return new GeneratorLakes(this.amountPerChunk, this.liquid, this.frozenLiquid, this.grassBorderWith, this.grassReplace, this.lineWith);
        }
    }
    
    protected IBlockState liquid;
    protected IBlockState frozenLiquid;
    protected IBlockState grassBorderWith;
    protected IBlockPosQuery grassReplace;
    protected IBlockState lineWith;

    
    public GeneratorLakes(float amountPerChunk, IBlockState liquid,  IBlockState frozenLiquid, IBlockState grassBorderWith, IBlockPosQuery grassReplace, IBlockState lineWith)
    {
        super(amountPerChunk);
        this.liquid = liquid;
        this.frozenLiquid = frozenLiquid;
        this.grassBorderWith = grassBorderWith;
        this.grassReplace = grassReplace;
        this.lineWith = lineWith;
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // always at world surface
        return GeneratorUtils.ScatterYMethod.AT_SURFACE.getBlockPos(world, random, x, z);
    }
    
    public boolean[] getCavityShape(Random rand)
    {
        boolean[] cavityShape = new boolean[2048];
        int numPasses = rand.nextInt(4) + 4;
        int pass;
        
        for (pass = 0; pass < numPasses; ++pass)
        {
            double scaleX = rand.nextDouble() * 6.0D + 3.0D; // between 3 and 9
            double scaleY = rand.nextDouble() * 4.0D + 2.0D; // between 2 and 6
            double scaleZ = rand.nextDouble() * 6.0D + 3.0D; // between 3 and 9
            
            double pointX = rand.nextDouble() * (16.0D - scaleX - 2.0D) + 1.0D + scaleX / 2.0D; // between 2.5 and 13.5
            double pointY = rand.nextDouble() * (8.0D - scaleY - 4.0D) + 2.0D + scaleY / 2.0D; // between 3 and 5
            double pointZ = rand.nextDouble() * (16.0D - scaleZ - 2.0D) + 1.0D + scaleZ / 2.0D; // between 2.5 and 13.5

            for (int x = 1; x < 15; ++x)
            {
                for (int z = 1; z < 15; ++z)
                {
                    for (int y = 1; y < 7; ++y)
                    {
                        double dx = ((double)x - pointX) / (scaleX / 2.0D);
                        double dy = ((double)y - pointY) / (scaleY / 2.0D);
                        double dz = ((double)z - pointZ) / (scaleZ / 2.0D);
                        
                        double r = dx * dx + dy * dy + dz * dz;

                        if (r < 1.0D)
                        {
                            cavityShape[(x * 16 + z) * 8 + y] = true;
                        }
                    }
                }
            }
        }
        
        return cavityShape;
    }
    
    public boolean isCavityEdge(int x, int y, int z, boolean[] cavityShape)
    {
        if (cavityShape[(x * 16 + z) * 8 + y]) {return false;}
        return x < 15 && cavityShape[((x + 1) * 16 + z) * 8 + y] || x > 0 && cavityShape[((x - 1) * 16 + z) * 8 + y] || z < 15 && cavityShape[(x * 16 + z + 1) * 8 + y] || z > 0 && cavityShape[(x * 16 + (z - 1)) * 8 + y] || y < 7 && cavityShape[(x * 16 + z) * 8 + y + 1] || y > 0 && cavityShape[(x * 16 + z) * 8 + (y - 1)];
    }
    
    public boolean isCavityViable(World world, BlockPos startPos, boolean[] cavityShape)
    {        
        for (int x = 0; x < 16; ++x)
        {
            for (int z = 0; z < 16; ++z)
            {
                for (int y = 0; y < 8; ++y)
                {
                    if (this.isCavityEdge(x, y, z, cavityShape)) {

                        Material material = world.getBlockState(startPos.add(x, y, z)).getMaterial();

                        // abandon if there's liquid at the edge of the cavity above the water level
                        if (y >= 4 && material.isLiquid())
                        {
                            return false;
                        }

                        // abandon if there's a different liquid at the edge of the cavity below the water level
                        if (y < 4 && !material.isSolid() && world.getBlockState(startPos.add(x, y, z)).getBlock() != this.liquid)
                        {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }   
    

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        // move to start of square
        pos = pos.add(-8, 0, -8);
        
        // move downwards to the first non-air block
        while (pos.getY() > 5 && world.isAirBlock(pos))
        {
            pos = pos.down();
        }

        // abandon if there isn't 4 blocks beneath pos
        if (pos.getY() <= 4)
        {
            return false;
        }

        // move down 4 blocks (to bottom of lake-to-be)
        pos = pos.down(4);

        // create a random cavity
        boolean[] cavityShape = this.getCavityShape(rand);
        
        // check it's viable
        if (!this.isCavityViable(world, pos, cavityShape))
        {
            return false;
        }
        
        

        int x;
        int y;
        int z;

        // create the lake - bottom 4 layers of turned to water, top 4 turned to air
        for (x = 0; x < 16; ++x)
        {
            for (z = 0; z < 16; ++z)
            {
                for (y = 0; y < 8; ++y)
                {
                    if (cavityShape[(x * 16 + z) * 8 + y])
                    {
                        world.setBlockState(pos.add(x, y, z), y >= 4 ? Blocks.air.getDefaultState() : this.liquid, 2);
                    }
                }
            }
        }

        if (this.grassBorderWith != null)
        {
            // replace newly exposed dirt at the surface edges of the lake with grass
            for (x = 0; x < 16; ++x)
            {
                for (z = 0; z < 16; ++z)
                {
                    for (y = 4; y < 8; ++y)
                    {
                        if (cavityShape[(x * 16 + z) * 8 + y])
                        {
                            BlockPos blockBelow = pos.add(x, y - 1, z);
                            if (this.grassReplace.matches(world, blockBelow) && world.getLightFor(EnumSkyBlock.SKY, pos.add(x, y, z)) > 0)
                            {
                                world.setBlockState(blockBelow, this.grassBorderWith, 2);
                            }
                        }
                    }
                }
            }
        }

        if (this.lineWith != null)
        {
            // line interior of lakes (used for instance with lava lakes to turn edge to stone)
            for (x = 0; x < 16; ++x)
            {
                for (z = 0; z < 16; ++z)
                {
                    for (y = 0; y < 8; ++y)
                    {
                        if (this.isCavityEdge(x, y, z, cavityShape))
                        {
                            if ((y < 4 || rand.nextInt(2) != 0) && world.getBlockState(pos.add(x, y, z)).getMaterial().isSolid())
                            {
                                world.setBlockState(pos.add(x, y, z), this.lineWith, 2);
                            }
                        }
                    }
                }
            }
        }

        // use frozen liquid if the temperature is low
        if (this.frozenLiquid != null)
        {
            y = 4;
            for (x = 0; x < 16; ++x)
            {
                for (z = 0; z < 16; ++z)
                {
                    if (world.canBlockFreezeWater(pos.add(x, y, z)))
                    {
                        world.setBlockState(pos.add(x, y, z), this.frozenLiquid, 2);
                    }
                }
            }
        }

        return true;
    }
    
    
    @Override
    public void configure(IConfigObj conf)
    {        
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        
        this.liquid = conf.getBlockState("liquid", this.liquid);
        this.lineWith = conf.getBlockState("lineWith", this.lineWith);
        this.grassBorderWith = conf.getBlockState("grassBorderWith", this.grassBorderWith);
        this.frozenLiquid = conf.getBlockState("frozenLiquid", this.frozenLiquid);
        this.grassReplace = conf.getBlockPosQuery("grassReplace", this.grassReplace);
    }
    
}