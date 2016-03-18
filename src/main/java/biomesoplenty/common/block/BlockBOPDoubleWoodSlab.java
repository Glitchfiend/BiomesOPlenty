/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableSet;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.block.BlockStateUtils;
import biomesoplenty.common.util.block.VariantPagingHelper;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class BlockBOPDoubleWoodSlab extends BlockSlab implements IBOPBlock
{
    
    // setup paged variant property
    
    // HALF requires one bit, so we have 3 bits left for the VARIANT which means we can have eight per instance
    public static VariantPagingHelper<BlockBOPDoubleWoodSlab, BOPWoods> paging = new VariantPagingHelper<BlockBOPDoubleWoodSlab, BOPWoods>(8, BOPWoods.class, BOPWoods.withPlanks);
    
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
            paging.addBlock(i, new BlockBOPDoubleWoodSlab());
        }
        
    }
    
    // Each instance has a reference to its own variant property
    public IProperty variantProperty;
    
    // add properties (note we inherit HALF property from parent BlockSlab)
    @Override
    protected BlockStateContainer createBlockState()
    {
        this.variantProperty = currentVariantProperty; // get from static variable
        return new BlockStateContainer(this, new IProperty[] { HALF, this.variantProperty });
    }

    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return null; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {this.variantProperty}; }
    // HALF isn't used in double slab because both halves are present
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] {HALF}; }
    @Override
    public String getStateName(IBlockState state)
    {
        return "double_" + ((BOPWoods)state.getValue(this.variantProperty)).getName() + "_wood_slab";
    }
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    private BlockBOPDoubleWoodSlab()
    {
        super(Material.wood);
        this.useNeighborBrightness = true;
        this.setHardness(2.0F).setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
        this.setHarvestLevel("axe", 0);
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM));
    }


    // these 3 functions are required by BlockSlab for setting up the corresponding ItemSlab
    @Override
    public String getUnlocalizedName(int meta)
    {
        return this.getStateName(this.getStateFromMeta(meta));
    }
    @Override
    public IProperty getVariantProperty()
    {
        return this.variantProperty;
    }
    @Override
    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return paging.getVariant(this, stack.getMetadata() & 7);
    }
    

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(this.variantProperty, paging.getVariant(this, meta & 7));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        BOPWoods wood = (BOPWoods) state.getValue(this.variantProperty);
        return paging.getIndex(wood);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        BOPWoods wood = (BOPWoods) state.getValue(this.variantProperty);
        IBlockState halfState = BlockBOPHalfWoodSlab.paging.getVariantState(wood);
        
        //Drop two of the corresponding half slab for this block
        ret.add(new ItemStack(halfState.getBlock(), 2, halfState.getBlock().getMetaFromState(halfState)));
        
        return ret;
    }

    @Override
    public boolean isDouble() {
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        // get the preset blocks variants
        ImmutableSet<IBlockState> presets = BlockStateUtils.getBlockPresets(this);
        // register all the sub-blocks
        for (IBlockState state : presets)
        {
            list.add(new ItemStack(itemIn, 1, this.getMetaFromState(state)));
        }
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
                return Blocks.double_wooden_slab.getFlammability(world, pos, face);
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
                return Blocks.double_wooden_slab.getFireSpreadSpeed(world, pos, face);
        }
    }
    
    
}