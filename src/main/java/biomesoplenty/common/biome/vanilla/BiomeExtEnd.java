package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.generation.GeneratorStage;
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
        this.removeGenerator("stone_formations");
        this.removeGenerator("glowshrooms");
        this.removeGenerator("miners_delight");
        this.removeGenerator("roots");
        
        if (!settings.isEnabled(GeneratorType.END_FEATURES)) {this.removeGenerator("crystals"); this.removeGenerator("biome_essence");}
    
        if (!settings.isEnabled(GeneratorType.ROCK_FORMATIONS)) {this.removeGenerator("stone_formations");}
        
        if (!settings.isEnabled(GeneratorType.GEMS)) {this.removeGenerator("ruby"); this.removeGenerator("topaz");
        this.removeGenerator("amber"); this.removeGenerator("peridot"); this.removeGenerator("malachite");
        this.removeGenerator("sapphire"); this.removeGenerator("tanzanite"); this.removeGenerator("amethyst");}
         
        if (!settings.isEnabled(GeneratorType.POISON_IVY)) {this.removeGenerator("poison_ivy");}
        
        if (!settings.isEnabled(GeneratorType.BERRY_BUSHES)) {this.removeGenerator("berry_bushes");}
        
        if (!settings.isEnabled(GeneratorType.NETHER_HIVES)) {this.removeGenerator("hive");}
        
        if (!settings.isEnabled(GeneratorType.THORNS)) {this.removeGenerator("thorns");}
        
        if (!settings.isEnabled(GeneratorType.QUICKSAND)) {this.removeGenerator("quicksand");}
        
        if (!settings.isEnabled(GeneratorType.HOT_SPRINGS)) {this.removeGenerator("hot_springs");}
        
        if (!settings.isEnabled(GeneratorType.LIQUID_POISON)) {this.removeGenerator("poison_lakes");}
    }
}
