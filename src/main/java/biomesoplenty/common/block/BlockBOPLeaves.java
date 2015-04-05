/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.List;
import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.block.BOPTreeEnums.FourTrees;
import biomesoplenty.api.block.BOPTreeEnums.AllTrees;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// TODO: sort out proper base color when using fast graphics
// TODO: flowers look tinted when using fast graphics
public class BlockBOPLeaves extends BlockLeaves implements IBOPBlock
{
    
    // add properties - note CHECK_DECAY and DECAYABLE are both inherited from BlockLeaves
    // both are boolean, requiring one bit each, so we have 2 bits left for the VARIANT which means we can have four per instance
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", FourTrees.class );
    protected int pageNum;
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { CHECK_DECAY, DECAYABLE, VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getRenderProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((FourTrees) state.getValue(VARIANT)).map(this.pageNum).getName() + "_leaves";
    }
    
    public BlockBOPLeaves(int pageNum)
    {
        super();
        
        this.pageNum = pageNum;
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, FourTrees.A).withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    }
    
    
    // map from meta to state and vice verca.  Use the same scheme as for the vanilla leaf blocks
    // highest bit is for CHECK_DECAY  true=>1 false=>0
    // next bit is for DECAYABLE  true=>0  false=>1  (other way round this time!  cheers Mojang)
    // low 2 bits for VARIANT
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, FourTrees.values()[meta & 3]).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = ((FourTrees)state.getValue(VARIANT)).ordinal();
        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            i |= 4;
        }
        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            i |= 8;
        }
        return i;
    }
    
    
    @Override
    protected int getSaplingDropChance(IBlockState state)
    {
        return 20;
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        AllTrees treeType = ((FourTrees) state.getValue(VARIANT)).map(this.pageNum);
        int saplingPage = treeType.ordinal() / 8;
        if (saplingPage == 2) {return Item.getItemFromBlock(BOPBlocks.sapling_2);}
        if (saplingPage == 1) {return Item.getItemFromBlock(BOPBlocks.sapling_1);}
        return Item.getItemFromBlock(BOPBlocks.sapling_0);
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        AllTrees treeType = ((FourTrees) state.getValue(VARIANT)).map(this.pageNum);
        return treeType.ordinal() % 8;
    }
    
    // TODO: different fruits for different trees?
    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
        AllTrees treeType = ((FourTrees) state.getValue(VARIANT)).map(this.pageNum);
        ItemStack fruit;
        switch (treeType)
        {
            case YELLOW_AUTUMN:
            case ORANGE_AUTUMN:
            case BAMBOO:
            case MAGIC:
            case DARK:
            case DEAD:
            case FIR:
            case ETHEREAL:
            case ORIGIN:
            case PINK_CHERRY:
            case WHITE_CHERRY:
            case MAPLE:
            case HELLBARK:
            case FLOWERING:
            case JACARANDA:
            case SACRED_OAK:
            case MANGROVE:
            case PALM:
            case REDWOOD:
            case WILLOW:
            case PINE:
            case MAHOGANY:
            default:
                 fruit = new ItemStack(Items.apple, 1, 0);
        }        
        if (fruit != null) {
            spawnAsEntity(worldIn, pos, fruit);
        }
    }
    

    @Override
    public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {       
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        int meta = this.getMetaFromState(this.getDefaultState().withProperty(VARIANT, world.getBlockState(pos).getValue(VARIANT))); 
        ret.add(new ItemStack(this, 1, meta));
        return ret;
    }
    
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        AllTrees tree = ((FourTrees) world.getBlockState(pos).getValue(VARIANT)).map(this.pageNum);
        switch (tree)
        {
            case HELLBARK:
                return 0;
            default:
                return Blocks.leaves.getFlammability(world, pos, face);
        }
    }
    
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        AllTrees tree = ((FourTrees) world.getBlockState(pos).getValue(VARIANT)).map(this.pageNum);
        switch (tree)
        {
            case HELLBARK:
                return 0;
            default:
                return Blocks.leaves.getFireSpreadSpeed(world, pos, face);
        }
    }
    
    
    //The fields used by getBlockLayer() and isOpaqueCube() are set externally for Blocks.leaves *specifically*. As a result, we do not inherit
    //it simply be extending BlockLeaves.
    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return Blocks.leaves.getBlockLayer();
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return Blocks.leaves.isOpaqueCube();
    }
    
    
    // We are forced to implement the method below in order to extend the BlockLeaves abstract class
    // ...however, we don't actually use it anywhere so it's safe to just return null
    // it makes no sense in our context
    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {return null;}
    
    
}
    
  