package biomesoplenty.common.world.gen.feature;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class SplotchConfig implements IFeatureConfig {
	   public final Block block;
	   public final int radius;
	   public final int ySize;
	   public final List<Block> targets;

	   public SplotchConfig(Block p_i48684_1_, int p_i48684_2_, int p_i48684_3_, List<Block> p_i48684_4_) {
	      this.block = p_i48684_1_;
	      this.radius = p_i48684_2_;
	      this.ySize = p_i48684_3_;
	      this.targets = p_i48684_4_;
	   }
	}