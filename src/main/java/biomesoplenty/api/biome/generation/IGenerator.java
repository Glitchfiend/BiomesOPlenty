/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import java.util.Random;

import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IGenerator
{
    public void scatter(World world, Random random, BlockPos pos);
    public boolean generate(World world, Random random, BlockPos pos);
    
    public void setStage(GeneratorStage stage);
    public void setName(String name);
    
    /**A unique name used to classify the purpose of a generator. For example, emeralds and ruby use the
     * same generator (and thus, have the same identifier) but have differing names.
     */
    public String getName();
    /**The identifier for this generator should be consistent across all instances of the same type*/
    public String getIdentifier();
    public GeneratorStage getStage();
    
    public static interface IGeneratorBuilder<T extends IGenerator>
    {
        public T create();
    }
    
    public void configure(IConfigObj conf);
}
