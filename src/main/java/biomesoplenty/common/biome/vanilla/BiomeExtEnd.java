package biomesoplenty.common.biome.vanilla;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.biome.ExtendedBiomeWrapper;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.world.feature.GeneratorCrystals;
import biomesoplenty.common.world.feature.GeneratorOreCluster;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeExtEnd extends ExtendedBiomeWrapper
{
    public BiomeExtEnd()
    {
        super(BiomeGenBase.sky);
        
        //celestial crystals
        IBlockPosQuery emptyEndstone = BlockQuery.buildAnd().withAltitudeBetween(0, 50).withAirBelow().states(Blocks.end_stone.getDefaultState()).create();
        this.addGenerator("crystals", GeneratorStage.ORE_PRE, (new GeneratorCrystals.Builder()).amountPerChunk(12.0F).placeOn(emptyEndstone).with(BOPBlocks.crystal.getDefaultState()).create());
    
        // gem
        this.addGenerator("amethyst", GeneratorStage.ORE_PRE, (new GeneratorOreSingle.Builder()).replace(Blocks.end_stone.getDefaultState()).amountPerChunk(24).with(BOPGems.AMETHYST).create());
        
        // biome essence
        this.addGenerator("biome_essence", GeneratorStage.ORE_PRE, (new GeneratorOreSingle.Builder()).replace(Blocks.end_stone.getDefaultState()).amountPerChunk(24).with(BOPBlocks.biome_block.getDefaultState()).create());
    }
}
