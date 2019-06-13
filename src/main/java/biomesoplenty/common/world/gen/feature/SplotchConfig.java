package biomesoplenty.common.world.gen.feature;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.List;

public class SplotchConfig implements IFeatureConfig
{
	public final BlockState state;
	public final int radius;
	public final int ySize;
	public final List<BlockState> targets;

	public SplotchConfig(BlockState state, int radius, int ySize, List<BlockState> targets)
	{
		this.state = state;
		this.radius = radius;
		this.ySize = ySize;
		this.targets = targets;
	}

	@Override
	public <T> Dynamic<T> serialize(DynamicOps<T> dynamicOps)
	{
		return new Dynamic<>(dynamicOps, dynamicOps.createMap(ImmutableMap.of(dynamicOps.createString("state"), BlockState.serialize(dynamicOps, this.state).getValue(), dynamicOps.createString("radius"), dynamicOps.createInt(this.radius), dynamicOps.createString("y_size"), dynamicOps.createInt(this.ySize), dynamicOps.createString("targets"), dynamicOps.createList(this.targets.stream().map((p_214692_1_) -> {
			return BlockState.serialize(dynamicOps, p_214692_1_).getValue();
		})))));
	}
}