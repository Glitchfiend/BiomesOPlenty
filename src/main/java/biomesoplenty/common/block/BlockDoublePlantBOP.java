/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockDoublePlantBOP extends BlockDoublePlant implements IPlantable
{
   public static final EnumProperty<DoubleBlockHalf> field_208063_b = BlockDoublePlant.HALF;
   private final Block field_196392_b;
	   
    public BlockDoublePlantBOP(Block p_i48335_1_, Block.Properties properties)
    {
        super(properties);
        this.field_196392_b = p_i48335_1_;
    }
    
    @Override
    public boolean isReplaceable(IBlockState state, BlockItemUseContext useContext) {
        boolean flag = super.isReplaceable(state, useContext);
        return flag && useContext.getItem().getItem() == this.asItem() ? false : flag;
     }

    @Override
     protected void harvest(IBlockState p_196391_1_, World p_196391_2_, BlockPos p_196391_3_, ItemStack p_196391_4_) {
        {
           super.harvest(p_196391_1_, p_196391_2_, p_196391_3_, p_196391_4_);
        }

     }

    @Override
     public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
        return state.get(field_208063_b) == DoubleBlockHalf.LOWER && this == Blocks.TALL_GRASS && worldIn.rand.nextInt(8) == 0 ? Items.WHEAT_SEEDS : Items.AIR;
     }

     public boolean isShearable(ItemStack item, net.minecraft.world.IWorldReader world, BlockPos pos) {
        return false;
     }

     public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IWorld world, BlockPos pos, int fortune) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
        return java.util.Arrays.asList(new ItemStack(this.field_196392_b, 2));
     }
     
     @Override
     public EnumPlantType getPlantType(IBlockReader world, BlockPos pos)
     {
     	Block block = world.getBlockState(pos).getBlock();
     	
     	return EnumPlantType.Plains;
     }
}
