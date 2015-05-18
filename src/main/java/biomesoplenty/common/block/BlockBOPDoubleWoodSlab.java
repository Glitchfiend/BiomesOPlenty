/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableSet;

import biomesoplenty.api.block.BOPWoodEnums;
import biomesoplenty.api.block.BOPWoodEnums.AllWoods;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.util.block.BlockStateUtils;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public abstract class BlockBOPDoubleWoodSlab extends BlockSlab implements IBOPBlock
{
    
    
    // setup paged variant property
    
    // HALF requires one bit, so we have 3 bits left for the VARIANT which means we can have eight per instance
    public static final int VARIANTS_PER_PAGE = 8;
    // child classes must implement to define their page number
    abstract public int getPageNum();
    // fetch the variant property for a given page
    public static PropertyEnum getVariantProperty(int pageNum)
    {
        return BOPWoodEnums.getVariantProperty(pageNum, VARIANTS_PER_PAGE, BOPWoodEnums.WoodsFilterType.WITH_PLANKS);
    }
    // fetch the current instance's variant property
    public PropertyEnum getMyVariantProperty()
    {
        return getVariantProperty(getPageNum());
    }
    // get the meta bits from the variant
    public int metaFromVariant(AllWoods wood)
    {
        return wood.ordinal() % VARIANTS_PER_PAGE;
    }
    // get the variant from meta bits (safely)
    public AllWoods variantFromMeta(int meta)
    {
        int i = Math.max(0, Math.min(meta + (this.getPageNum() * VARIANTS_PER_PAGE), AllWoods.values().length));
        return AllWoods.values()[i];
    }

    
    // store reference to each created instance, indexed by page num, so that later we can look up the right BlockBOPHalfWoodSlab instance for a particular variant
    private static Map<Integer, BlockBOPDoubleWoodSlab> instances = new HashMap<Integer, BlockBOPDoubleWoodSlab>();
    // get the BlockBOPDoubleWoodSlab instance for the given variant
    public static BlockBOPDoubleWoodSlab getVariantBlock(AllWoods wood)
    {
        int pageNum = wood.ordinal() / VARIANTS_PER_PAGE;
        BlockBOPDoubleWoodSlab block = instances.get(pageNum);
        if (block == null) {throw new IllegalArgumentException("No BlockBOPDoubleWoodSlab instance created yet for page "+pageNum);}
        return block;
    }
    // get the default block state for the given variant
    public static IBlockState getVariantState(AllWoods wood)
    {
        BlockBOPDoubleWoodSlab block = getVariantBlock(wood);
        return block.getDefaultState().withProperty(block.getMyVariantProperty() , wood);
    }
    // get the item representation of the given variant
    public static ItemStack getVariantItem(AllWoods wood, int howMany)
    {
        return new ItemStack(getVariantBlock(wood), howMany, getVariantBlock(wood).getMetaFromState(getVariantState(wood)));
    }
    public static ItemStack getVariantItem(AllWoods wood)
    {
        return getVariantItem(wood, 1);
    }
    
    
    // add properties (note we inherit HALF property from parent BlockSlab)
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] {HALF, getMyVariantProperty()});}

    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return null; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {getMyVariantProperty()}; }
    // HALF isn't used in double slab because both halves are present
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] {HALF}; }
    @Override
    public String getStateName(IBlockState state)
    {
        return "double_" + ((AllWoods)state.getValue(getMyVariantProperty())).getName() + "_wood_slab";
    }
    
    public BlockBOPDoubleWoodSlab()
    {
        super(Material.wood);
        // save a reference to this instance so that later we can look up the right BlockBOPLog instance for a particular variant
        instances.put(this.getPageNum(), this);
        this.useNeighborBrightness = true;
        this.setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood);
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
        return getMyVariantProperty();
    }
    @Override
    public Object getVariant(ItemStack stack)
    {
        return variantFromMeta(stack.getMetadata() & 7);
    }
    

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(getMyVariantProperty(), variantFromMeta(meta & 7));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return metaFromVariant((AllWoods) state.getValue(getMyVariantProperty()));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        // always drop a bottom slab
        return this.getMetaFromState(state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM));
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
    
}