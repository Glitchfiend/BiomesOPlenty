/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.List;

import com.google.common.collect.ImmutableSet;

import biomesoplenty.api.block.BOPBlocks;
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
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class BlockBOPHalfOtherSlab extends BlockSlab implements IBOPBlock
{
    
    // add properties
    public static enum SlabType implements IStringSerializable
    {
        LIMESTONE, SILTSTONE, SHALE, POLISHED_LIMESTONE, POLISHED_SILTSTONE, POLISHED_SHALE, CRAG_ROCK, MUD_BRICK;
        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
                
        // get this slab's corresponding full block blockstate
        public IBlockState fullBlockState()
        {
            IBlockState state = null;
            switch (this)
            {
                case LIMESTONE:
                    state = BOPBlocks.stone.getDefaultState().withProperty(BlockBOPStone.POLISHED, Boolean.valueOf(false)).withProperty(BlockBOPStone.VARIANT, BlockBOPStone.StoneType.LIMESTONE);
                    break;
                case SILTSTONE:
                    state = BOPBlocks.stone.getDefaultState().withProperty(BlockBOPStone.POLISHED, Boolean.valueOf(false)).withProperty(BlockBOPStone.VARIANT, BlockBOPStone.StoneType.SILTSTONE);
                    break;
                case SHALE:
                    state = BOPBlocks.stone.getDefaultState().withProperty(BlockBOPStone.POLISHED, Boolean.valueOf(false)).withProperty(BlockBOPStone.VARIANT, BlockBOPStone.StoneType.SHALE);
                    break;
                case POLISHED_LIMESTONE:
                    state = BOPBlocks.stone.getDefaultState().withProperty(BlockBOPStone.POLISHED, Boolean.valueOf(true)).withProperty(BlockBOPStone.VARIANT, BlockBOPStone.StoneType.LIMESTONE);
                    break;
                case POLISHED_SILTSTONE:
                    state = BOPBlocks.stone.getDefaultState().withProperty(BlockBOPStone.POLISHED, Boolean.valueOf(true)).withProperty(BlockBOPStone.VARIANT, BlockBOPStone.StoneType.SILTSTONE);
                    break;
                case POLISHED_SHALE:
                    state = BOPBlocks.stone.getDefaultState().withProperty(BlockBOPStone.POLISHED, Boolean.valueOf(true)).withProperty(BlockBOPStone.VARIANT, BlockBOPStone.StoneType.SHALE);
                    break;
                case CRAG_ROCK:
                    state = BOPBlocks.crag_rock.getDefaultState();
                    break;
                case MUD_BRICK:
                    state = BOPBlocks.mud_brick_block.getDefaultState();
                    break;
                default:
                    throw new RuntimeException("No full block state defined for SlabType " + this.name());
            }
            return state;
        }
    };
    
    // add properties (note we inherit HALF property from parent BlockSlab)
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", SlabType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { HALF, VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return null; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] { VARIANT }; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((SlabType) state.getValue(VARIANT)).getName() + "_slab";
    }
  
    
    public IBlockState getFullBlockVariantState(SlabType type)
    {
        return type.fullBlockState();
    }
    
    public ItemStack getFullBlockVariantItem(SlabType type)
    {
        return this.getFullBlockVariantItem(type, 1);
    }
    
    public ItemStack getFullBlockVariantItem(SlabType type, int howMany)
    {
        IBlockState state = type.fullBlockState();
        return new ItemStack(state.getBlock(), howMany, state.getBlock().damageDropped(state));
    }
    
    public ItemStack getVariantItem(SlabType type)
    {
        return this.getVariantItem(type, 1);
    }
    
    public ItemStack getVariantItem(SlabType type, int howMany)
    {
        IBlockState state = this.getDefaultState().withProperty(VARIANT, type);
        return new ItemStack(this, howMany, this.damageDropped(state));
    }
    
    
    public BlockBOPHalfOtherSlab()
    {
        super(Material.rock);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeStone);
        // TODO: should depend on variant really, but that's quite hard to achieve...
        this.setHardness(2.0F).setResistance(7.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM));
    }
    
    @Override
    public String getHarvestTool(IBlockState state)
    {
        IBlockState fullBlockState = ((SlabType)state.getValue(VARIANT)).fullBlockState();
        return fullBlockState.getBlock().getHarvestTool(fullBlockState);
    }
    @Override
    public int getHarvestLevel(IBlockState state)
    {
        IBlockState fullBlockState = ((SlabType)state.getValue(VARIANT)).fullBlockState();
        return fullBlockState.getBlock().getHarvestLevel(fullBlockState);
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
        return VARIANT;
    }
    @Override
    public Object getVariant(ItemStack stack)
    {
        return SlabType.values()[stack.getMetadata() & 7];
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, SlabType.values()[meta & 7]).withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        SlabType type = (SlabType)state.getValue(VARIANT);
        return (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP ? 8 : 0) + type.ordinal();
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
