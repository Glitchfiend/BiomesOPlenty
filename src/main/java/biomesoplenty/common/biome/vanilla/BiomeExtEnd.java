package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorCrystals;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

public class BiomeExtEnd extends ExtendedBiomeWrapper
{
    public BiomeExtEnd()
    {
        super(Biomes.SKY);
        
        //celestial crystals
        IBlockPosQuery emptyEndstone = BlockQuery.buildAnd().withAltitudeBetween(0, 50).withAirBelow().states(Blocks.END_STONE.getDefaultState()).create();
        this.addGenerator("crystals", GeneratorStage.ORE_PRE, (new GeneratorCrystals.Builder()).amountPerChunk(12.0F).placeOn(emptyEndstone).with(BOPBlocks.crystal.getDefaultState()).create());
    
        // gem
        this.addGenerator("amethyst", GeneratorStage.ORE_PRE, (new GeneratorOreSingle.Builder()).replace(Blocks.END_STONE.getDefaultState()).amountPerChunk(24).with(BOPGems.AMETHYST).create());
        
        // biome essence
        this.addGenerator("biome_essence", GeneratorStage.ORE_PRE, (new GeneratorOreSingle.Builder()).replace(Blocks.END_STONE.getDefaultState()).amountPerChunk(24).with(BOPBlocks.biome_block.getDefaultState()).create());
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.GEMS)) {this.removeGenerator("amethyst");}
        if (!settings.isEnabled(GeneratorType.END_FEATURES)) {this.removeGenerator("crystals"); this.removeGenerator("biome_essence");}
    }
}
