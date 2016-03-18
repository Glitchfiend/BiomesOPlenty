/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.item.ItemBOPBlock;
import biomesoplenty.common.util.block.VariantPagingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBOPPlanks extends Block implements IBOPBlock
{
    
    // setup paged variant property
    
    // All 4 meta bits available for the VARIANT which means we can have sixteen per instance
    public static VariantPagingHelper<BlockBOPPlanks, BOPWoods> paging = new VariantPagingHelper<BlockBOPPlanks, BOPWoods>(16, BOPWoods.class, BOPWoods.withPlanks);
    
    // Slightly naughty hackery here
    // The constructor of Block() calls createBlockState() which needs to know the particular instance's variant property
    // There is no way to set the individual block instance's variant property before this, because the super() has to be first
    // So, we use the static variable currentVariantProperty to provide each instance access to its variant property during creation
    private static IProperty currentVariantProperty;
    
    // Create an instance for each page
    public static void createAllPages()
    {        
        int numPages = paging.getNumPages();        
        for (int i = 0; i < numPages; ++i)
        {
            currentVariantProperty = paging.getVariantProperty(i);
            paging.addBlock(i, new BlockBOPPlanks());
        }
        
    }
    
    // Each instance has a reference to its own variant property
    public IProperty variantProperty;
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        this.variantProperty = currentVariantProperty; // get from static variable
        return new BlockStateContainer(this, new IProperty[] { this.variantProperty });
    }
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {this.variantProperty}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((BOPWoods) state.getValue(this.variantProperty)).getName() + "_planks";
    }
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPPlanks()
    {
        super(Material.wood);
        this.setDefaultState(this.blockState.getBaseState());
        this.setHardness(2.0F).setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
        this.setHarvestLevel("axe", 0);
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(this.variantProperty, paging.getVariant(this, meta));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        BOPWoods wood = (BOPWoods) state.getValue(this.variantProperty);
        return paging.getIndex(wood);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }
    
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        BOPWoods wood = ((BOPWoods) world.getBlockState(pos).getValue(this.variantProperty));
        switch (wood)
        {
            case HELLBARK:
                return 0;
            default:
                return Blocks.planks.getFlammability(world, pos, face);
        }
    }
    
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
    	BOPWoods wood = ((BOPWoods) world.getBlockState(pos).getValue(this.variantProperty));
        switch (wood)
        {
            case HELLBARK:
                return 0;
            default:
                return Blocks.planks.getFireSpreadSpeed(world, pos, face);
        }
    }
    
    
}