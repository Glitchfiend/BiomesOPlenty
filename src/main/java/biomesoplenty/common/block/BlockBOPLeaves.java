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
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.item.ItemBOPBlock;
import biomesoplenty.common.util.block.VariantPagingHelper;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
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
// TODO: inside surface not visible with fast graphics
public class BlockBOPLeaves extends BlockLeaves implements IBOPBlock
{
    
    // setup paged variant property
    
    // CHECK_DECAY and DECAYABLE require one bit each, so we have 2 bits left for the VARIANT which means we can have four per instance
    public static VariantPagingHelper<BlockBOPLeaves, BOPTrees> paging = new VariantPagingHelper<BlockBOPLeaves, BOPTrees>(4, BOPTrees.class);
    
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
            paging.addBlock(i, new BlockBOPLeaves());
        }
        
    }
    
    // Each instance has a reference to its own variant property
    public IProperty variantProperty;
    
    @Override
    protected BlockState createBlockState()
    {
        this.variantProperty = currentVariantProperty; // get from static variable
        return new BlockState(this, new IProperty[] { CHECK_DECAY, DECAYABLE, this.variantProperty });
    }
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {this.variantProperty}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] {CHECK_DECAY, DECAYABLE}; }
    @Override
    public String getStateName(IBlockState state)
    {
        BOPTrees tree = ((BOPTrees) state.getValue(this.variantProperty));
        switch (tree)
        {
            case RED_BIG_FLOWER: case YELLOW_BIG_FLOWER:
                return tree.getName() + "_petal";
            default:
                return tree.getName() + "_leaves";
        }
    }
    
    private BlockBOPLeaves()
    {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    }
    
    
    // map from meta to state and vice verca.  Use the same scheme as for the vanilla leaf blocks
    // highest bit is for CHECK_DECAY  true=>1 false=>0
    // next bit is for DECAYABLE  true=>0  false=>1  (other way round this time!  cheers Mojang)
    // low 2 bits for VARIANT
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(this.variantProperty, paging.getVariant(this, meta & 3)).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        BOPTrees tree = (BOPTrees) state.getValue(this.variantProperty);
        int meta = paging.getIndex(tree);
        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            meta |= 4;
        }
        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            meta |= 8;
        }
        return meta;
    }
    
    @Override
    protected int getSaplingDropChance(IBlockState state)
    {
        return 20;
    }
    
    // TODO: use some kind of mapping to saplings
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        BOPTrees treeType = ((BOPTrees) state.getValue(this.variantProperty));
        int saplingPage = treeType.ordinal() / 8;
        if (saplingPage == 2) {return Item.getItemFromBlock(BOPBlocks.sapling_2);}
        if (saplingPage == 1) {return Item.getItemFromBlock(BOPBlocks.sapling_1);}
        return Item.getItemFromBlock(BOPBlocks.sapling_0);
    }
    
    // TODO: use some kind of mapping to saplings
    @Override
    public int damageDropped(IBlockState state)
    {
        //Ignore all other properties, only the variant is important to ensure different variants use different stacks
        BOPTrees tree = ((BOPTrees) state.getValue(this.variantProperty));
        return this.getMetaFromState(this.getDefaultState().withProperty(this.variantProperty, tree));
    }
    
    // TODO: different fruits for different trees?
    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
        // chance will initially be related to fortune as follows:  0 => 200, 1 => 180, 2 => 160, 3 => 120, 4+ => 40
        ItemStack fruit = null;
        
        BOPTrees tree = ((BOPTrees) state.getValue(this.variantProperty));
        switch (tree)
        {
            case RED_BIG_FLOWER: case YELLOW_BIG_FLOWER:
                break;
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
                if (worldIn.rand.nextInt(chance) == 0)
                {
                    fruit = new ItemStack(Items.apple, 1, 0);
                }
                break;
        }
        
        if (fruit != null) {
            spawnAsEntity(worldIn, pos, fruit);
        }
    }
    

    @Override
    public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == this)
        {
            ret.add(new ItemStack(this, 1, this.damageDropped(state)));
        }
        return ret;
    }
    
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        BOPTrees tree = ((BOPTrees) world.getBlockState(pos).getValue(this.variantProperty));
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
        BOPTrees tree = ((BOPTrees) world.getBlockState(pos).getValue(this.variantProperty));
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
    
  