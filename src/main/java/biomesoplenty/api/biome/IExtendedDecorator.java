/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import java.util.Map;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;

public interface IExtendedDecorator
{
    public void addGenerator(String key, IGenerator<?> generator, Decorate.EventType nextFeature);
    public void addGenerator(String key, IGenerator<?> generator);

    public void configureGenerators(Map<String, IGenerator<?>> generatorMap);

    public Map<String, IGenerator<?>> getGeneratorMap();
    public Decorate.EventType getGeneratorStage(String key);
}
