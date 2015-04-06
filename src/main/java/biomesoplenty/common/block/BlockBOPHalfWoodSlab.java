package biomesoplenty.common.block;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;

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


public abstract class BlockBOPHalfWoodSlab extends BlockSlab implements IBOPBlock
{
    
    
    // store the variant properties for each page in this array
    private static PropertyEnum[] variantProperties;
    // number of variants per page - HALF requires 1 meta bit, so we have 3 left for the VARIANT, which means we can have eight woods per instance
    public static final int variantsPerPage = 8;
    // fetch a particular page's variant property
    // the first time this is called, it also sets up the array of variant properties
    protected static PropertyEnum getVariantProperty(int pageNum)
    {
        int len = AllWoods.values().length;
        int numPages = (int) Math.ceil( (double)len / variantsPerPage);
        if (variantProperties == null)
        {
            variantProperties = new PropertyEnum[numPages];
        }
        pageNum = Math.max(0, Math.min(pageNum, numPages - 1));
        if (variantProperties[pageNum] == null)
        {
            variantProperties[pageNum] = PropertyEnum.create("variant", AllWoods.class, getVariantEnumFilter(pageNum));
        }
        return variantProperties[pageNum];
    }
    // define the filter function used to reduce the set of enum values to the subset for the given page
    protected static Predicate<AllWoods> getVariantEnumFilter(final int pageNum)
    {
        return new Predicate<AllWoods>()
        {
            @Override
            public boolean apply(AllWoods wood)
            {
                switch (wood)
                {
                    case GIANT_FLOWER: case DEAD:
                        return false;
                    default:
                        return (wood.ordinal() >= (variantsPerPage * pageNum)) && (wood.ordinal() < (variantsPerPage * (pageNum+1)));
                }                
            }
        };
    }
    // child classes must implement to define their page number
    abstract public int getPageNum();
    // fetch the current instance's variant property
    public PropertyEnum getMyVariantProperty()
    {
        return getVariantProperty(this.getPageNum());
    }
    public int metaFromVariant(AllWoods wood)
    {
        return wood.ordinal() % variantsPerPage;
    }
    public AllWoods variantFromMeta(int meta)
    {
        int i = Math.max(0, Math.min(meta + (this.getPageNum() * variantsPerPage), AllWoods.values().length)); // clamp to 
        return AllWoods.values()[i];
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
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((AllWoods) state.getValue(getMyVariantProperty())).getName() + "_wood_slab";
    }  
    
    public BlockBOPHalfWoodSlab()
    {
        super(Material.wood);
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
        return this.getDefaultState().withProperty(getMyVariantProperty(), variantFromMeta(meta & 7)).withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP ? 8 : 0) + metaFromVariant((AllWoods) state.getValue(getMyVariantProperty()));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        // always drop a bottom slab
        return this.getMetaFromState(state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM));
    }

    @Override
    public boolean isDouble() {
        return false;
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
