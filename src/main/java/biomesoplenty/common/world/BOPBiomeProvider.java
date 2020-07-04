/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import biomesoplenty.api.enums.BOPClimates;
import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.layer.Layer;

import java.util.Set;

public class BOPBiomeProvider extends OverworldBiomeProvider
{
    private final Layer noiseBiomeLayer;
    protected final Set<Biome> possibleBiomes;

    public BOPBiomeProvider(long seed)
    {
        super(seed, false, false);
        this.possibleBiomes = Sets.newHashSet(super.possibleBiomes);
        this.possibleBiomes.addAll(BOPClimates.getOverworldBiomes());
        this.noiseBiomeLayer = BOPLayerUtil.createGenLayers(seed, new BOPOverworldGenSettings());
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z)
    {
        return this.noiseBiomeLayer.get(x, z);
    }

    @Override
    public boolean canGenerateStructure(Structure<?> structure)
    {
        return this.supportedStructures.computeIfAbsent(structure, (p_226839_1_) -> this.possibleBiomes.stream().anyMatch((biome) -> biome.isValidStart(p_226839_1_)));
    }

    @Override
    public Set<BlockState> getSurfaceBlocks()
    {
        if (this.surfaceBlocks.isEmpty())
        {
            for(Biome biome : this.possibleBiomes)
            {
                this.surfaceBlocks.add(biome.getSurfaceBuilderConfig().getTopMaterial());
            }
        }

        return this.surfaceBlocks;
    }
}