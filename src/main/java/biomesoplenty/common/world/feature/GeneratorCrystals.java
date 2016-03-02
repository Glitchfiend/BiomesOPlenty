package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorCrystals extends GeneratorReplacing
{
    public static class Builder extends GeneratorReplacing.InnerBuilder<Builder, GeneratorCrystals> implements IGeneratorBuilder<GeneratorCrystals>
    {
        protected int generationAttempts;
        protected int maxRadius;
        protected int maxDepth;
        
        public Builder generationAttempts(int a) {this.generationAttempts = a; return this.self();}
        public Builder maxRadius(int a) {this.maxRadius = a; return this.self();}
        public Builder maxDepth(int a) {this.maxDepth = a; return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQueries.hellish;
            this.replace = new BlockQueryMaterial(Material.air);
            this.with = Blocks.glowstone.getDefaultState();
            this.scatterYMethod = ScatterYMethod.BELOW_GROUND;
            this.generationAttempts = 1500;
            this.maxRadius = 7;
            this.maxDepth = 11;
        }
        
        @Override
        public GeneratorCrystals create() 
        {
            return new GeneratorCrystals(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.generationAttempts, this.maxRadius, this.maxDepth);
        }   
    }
    
    protected int generationAttempts;
    protected int maxRadius;
    protected int maxDepth;
    
    public GeneratorCrystals(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int generationAttempts, int maxRadius, int maxDepth)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.generationAttempts = generationAttempts;
        this.maxRadius = maxRadius;
        this.maxDepth = maxDepth;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) 
    {
        //Ensure the current position isn't occupied
        if (this.replace.matches(world, pos))
        {
            return false;
        }
        else if (this.placeOn.matches(world, pos.up())) //Crystals hang down, ensure the block above is suitable
        {
            return false;
        }
        else
        {
            world.setBlockState(pos, this.with, 2);
            
            for (int i = 0; i < this.generationAttempts; ++i)
            {
                //Randomly choose a position within the max depth and radius. Subtracting random numbers adds a bias towards choosing positions closer to the origin
                BlockPos randPos = pos.add(rand.nextInt(this.maxRadius + 1) - rand.nextInt(this.maxRadius + 1), -rand.nextInt(this.maxDepth + 1), rand.nextInt(this.maxRadius + 1) - rand.nextInt(this.maxRadius + 1));

                //Ensure the random position isn't occupied
                if (this.replace.matches(world, randPos))
                {
                    boolean adjacentCrystals = false;

                    //Iterate through each side adjacent to the random position and check for existing crystal blocks
                    for (EnumFacing face : EnumFacing.values())
                    {
                        if (world.getBlockState(randPos.offset(face)) == this.with)
                        {
                           adjacentCrystals = true;
                           break;
                        }
                    }

                    if (adjacentCrystals)
                    {
                        //Set random position to crystal block
                        world.setBlockState(randPos, this.with, 2);
                    }
                }
            }

            return true;
        }
    }

    @Override
    public void configure(IConfigObj conf) 
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.placeOn = conf.getBlockPosQuery("placeUnder", this.placeOn);
        this.replace = conf.getBlockPosQuery("replace", this.replace);
        this.with = conf.getBlockState("with", this.with);
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
        this.scatterYMethod = conf.getEnum("scatterYMethod", this.scatterYMethod, ScatterYMethod.class);
        this.maxRadius = conf.getInt("maxRadius", this.maxRadius);
        this.maxDepth = conf.getInt("maxDepth", this.maxDepth);
    }
}
