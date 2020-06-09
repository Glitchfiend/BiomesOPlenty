/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.enums.BOPClimates;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.ObjectHolderRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class BiomeBOP extends Biome
{
    protected Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();
    
	public boolean canSpawnInBiome;
	public int beachBiomeId;
	public int riverBiomeId;
	
	private Optional<Supplier<RegistryObject<Biome>>> beachSupplier = Optional.empty();
	private Optional<Supplier<RegistryObject<Biome>>> riverSupplier = Optional.empty();

    public BiomeBOP(Builder builder)
    {
        super(builder);
        this.canSpawnInBiome = true;
        
        /*
         * Attach a handler which is invoked after the Biome registry is updated and injected into the mod.
         * Done to ensure RegistryObject fields are propagated with biomes prior to access.
         * Improves mod compatibility as one can pass a non-populated biome or null fields
         * that will be propagated properly after registry injection.
         */
        ObjectHolderRegistry.addHandler(pred -> {
            if(pred.test(ForgeRegistries.BIOMES.getRegistryName())) {
                
                //set defaults here in order to use the forge registry for compatibility concerns
                beachBiomeId = BiomeRegistry.getId(Biomes.BEACH);
                riverBiomeId = BiomeRegistry.getId(Biomes.RIVER);
                
                //reassign from defaults if suppliers present
                beachSupplier.ifPresent(this::beachBiomeConsumer);
                riverSupplier.ifPresent(this::riverBiomeConsumer);
            }
        });
    }

    public void addWeight(BOPClimates climate, int weight)
    {
        this.weightMap.put(climate, weight);
    }

    private void beachBiomeConsumer(Supplier<RegistryObject<Biome>> biomeSupplier) {
        RegistryObject<Biome> biome = biomeSupplier.get();
        this.beachBiomeId = BiomeRegistry.getId(biome.orElse((Biome)null)); //returns -1 for null biome
    }
    
    private void riverBiomeConsumer(Supplier<RegistryObject<Biome>> biomeSupplier) {
        RegistryObject<Biome> biome = biomeSupplier.get();
        this.riverBiomeId = BiomeRegistry.getId(biome.orElse((Biome)null)); //returns -1 for null biome
    }


    /**
     * sets beachBiomeId to -1, use setBeachBiome( RegistryObject{@literal <}Biome{@literal >} ) instead
     */
    @Deprecated
    public void setBeachBiome(Biome biome) {
        this.beachBiomeId = -1;
    }
    
    /**
     * sets riverBiomeId to -1, use setRiverBiome( RegistryObject{@literal <}Biome{@literal >} ) instead
     */
    @Deprecated
    public void setRiverBiome(Biome biome) {
        this.riverBiomeId = -1;
    }

    // Create an optional biome supplier to defer setting of beachBiomeId until post-biome registration.
    public void setBeachBiome(RegistryObject<Biome> biome)
    {
        this.beachSupplier = Optional.of(() -> biome);
    }
    
    // Create an optional biome supplier to defer setting of riverBiomeId until post-biome registration.
    public void setRiverBiome(RegistryObject<Biome> biome)
    {
        this.riverSupplier = Optional.of(() -> biome);
    }

    public Map<BOPClimates, Integer> getWeightMap()
    {
        return this.weightMap;
    }

    public boolean hasWeights()
    {
        return !this.weightMap.isEmpty() && !this.weightMap.entrySet().stream().allMatch((entry) -> entry.getValue().equals(0));
    }
}
