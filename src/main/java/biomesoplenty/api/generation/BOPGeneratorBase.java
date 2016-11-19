/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.generation;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class BOPGeneratorBase extends WorldGenerator implements IGenerator
{ 
    protected final String identifier;
    protected String name;
    protected GeneratorStage stage;
    protected float amountPerChunk;
    
    protected BOPGeneratorBase(float amountPerChunk)
    {
        this.identifier = Generators.registry.getIdentifier((Class<? extends IGenerator>)this.getClass());
        
        if (this.identifier == null)
        {
            throw new RuntimeException("The identifier for " + this.getClass().getCanonicalName() + " cannot be null!");
        }
        
        this.amountPerChunk = amountPerChunk;
    }
    
    
    protected static abstract class InnerBuilder<T extends InnerBuilder<T, G>, G extends BOPGeneratorBase>
    {
        protected T self() {return (T)this;}
        protected float amountPerChunk;
        public T amountPerChunk(float amountPerChunk) {this.amountPerChunk = amountPerChunk; return this.self();}
        public abstract G create();
    }
    
    
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public void setStage(GeneratorStage stage)
    {
        this.stage = stage;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public GeneratorStage getStage()
    {
        return this.stage;
    }
    
    @Override
    public final String getIdentifier()
    {
        return this.identifier;
    }
    
    public abstract BlockPos getScatterY(World world, Random random, int x, int z);
    
    public int getAmountToScatter(Random random)
    {
        int amount = MathHelper.floor(this.amountPerChunk);
        float remainder = this.amountPerChunk - amount;
        if (random.nextFloat() < remainder) {amount++;}
        return amount;
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        int amount = this.getAmountToScatter(random);        
        for (int i = 0; i < amount; i++)
        {
            int x = pos.getX() + random.nextInt(16) + 8;
            int z = pos.getZ() + random.nextInt(16) + 8;
            generate(world, random, this.getScatterY(world, random, x, z));
        }
    }
}
