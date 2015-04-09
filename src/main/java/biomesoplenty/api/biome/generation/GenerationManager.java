/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;

public class GenerationManager
{
    private Table<GeneratorStage, String, IGeneratorController> generatorTable = HashBasedTable.create();

    public void addGenerator(String name, GeneratorStage stage, IGeneratorController generator)
    {
        if (!this.generatorTable.containsColumn(name))
        {
            generator.setName(name);
            generator.setStage(stage);

            this.generatorTable.put(stage, name, generator);
        }
        else
        {
            throw new RuntimeException("A generator with name " + name + " already exists!");
        }
    }
    
    public ImmutableCollection<IGeneratorController> getGeneratorsForStage(GeneratorStage stage)
    {
        Map<String, IGeneratorController> columnMap = this.generatorTable.rowMap().get(stage);
        Collection<IGeneratorController> result = columnMap == null ? null : columnMap.values();
        
        return result == null ? ImmutableList.<IGeneratorController>of() : ImmutableList.<IGeneratorController>copyOf(result);
    }
    
    public Map<String, IGeneratorController> createGeneratorMap()
    {
        Map<String, IGeneratorController> result = new HashMap<String, IGeneratorController>();
        
        for (IGeneratorController generator : this.generatorTable.values())
        {
            result.put(generator.getName(), generator);
        }
        
        return result;
    }
    
    public void createGeneratorTable(Map<String, IGeneratorController> generators)
    {
        Table<GeneratorStage, String, IGeneratorController> result = HashBasedTable.create();
         
        for (Entry<String, IGeneratorController> entry : generators.entrySet())
        {
            String name = entry.getKey();
            IGeneratorController generator = entry.getValue();

            generator.setName(name);
            result.put(generator.getStage(), generator.getName(), generator);
        }
        
        this.generatorTable = result;
    }
}