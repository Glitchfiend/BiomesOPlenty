/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.HashMap;
import java.util.Map;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class BlockBOPWoodStairs extends BlockStairs implements IBOPBlock
{
    
    // implement IBOPBlock
    // inherited properties from BlockStairs : FACING, HALF, SHAPE
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {return "";}

    // Map from woods to BlockBOPStairs instance and back
    private static Map<BOPWoods, BlockBOPWoodStairs> variantToBlock = new HashMap<BOPWoods, BlockBOPWoodStairs>();
    public static BlockBOPWoodStairs getBlock(BOPWoods wood)
    {
        return variantToBlock.get(wood);
    }
    protected BOPWoods wood;
    public BOPWoods getWood()
    {
        return this.wood;
    }
    
    public BlockBOPWoodStairs(BOPWoods wood)
    {
        super(BlockBOPPlanks.paging.getVariantState(wood));
        this.setHarvestLevel("axe", 0);
        this.useNeighborBrightness = true;
        
        this.wood = wood;
        variantToBlock.put(wood, this);
    }
}
    