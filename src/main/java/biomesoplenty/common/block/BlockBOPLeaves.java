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

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.block.BlockBOPDoubleDecoration.Half;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.item.ItemBOPBlock;
import biomesoplenty.common.util.block.VariantPagingHelper;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// TODO: using fast graphics - transparent color is always rendered as black - can we override this somehow?
// TODO: using fast graphics - flowering leaves overlay seems to be tinted green - I think that is because it doesn't use distinct tintindexes on fast graphics
// In older versions a completely different single texture could be used for fast graphics, but I'm not sure that's an option any more
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
    
    
    public enum ColoringType {PLAIN, TINTED, OVERLAY};
    
    public static ColoringType getColoringType(BOPTrees tree)
    {
        switch (tree)
        {
            case BAMBOO: case DEAD: case ETHEREAL: case FIR: case HELLBARK: case JACARANDA: case MAGIC: case MAPLE: case ORANGE_AUTUMN: case ORIGIN: case PINK_CHERRY: case WHITE_CHERRY: case YELLOW_AUTUMN: case RED_BIG_FLOWER: case YELLOW_BIG_FLOWER:
                return ColoringType.PLAIN;
            case FLOWERING:
                return ColoringType.OVERLAY;
            case DARK: case MAHOGANY: case MANGROVE: case PALM: case PINE: case REDWOOD: case SACRED_OAK: case WILLOW: default:
                return ColoringType.TINTED;
        }
    }
 
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 0xFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        switch (getColoringType((BOPTrees) state.getValue(this.variantProperty)))
        {
            case TINTED:
                return ColorizerFoliage.getFoliageColorBasic();    
            case OVERLAY:
                return ColorizerFoliage.getFoliageColorBasic();
            case PLAIN: default:
                return 0xFFFFFF;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int tintIndex)
    {
        switch (getColoringType((BOPTrees) worldIn.getBlockState(pos).getValue(this.variantProperty)))
        {
            case TINTED:
                return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
            case OVERLAY:
                switch (tintIndex)
                {
                    case 0:
                        return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
                    case 1: default:
                        return 0xFFFFFF;
                }
            case PLAIN: default:
                return 0xFFFFFF;
        }
    }
    
    // blocks that are not placed during generation should not decay
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getStateFromMeta(meta).withProperty(CHECK_DECAY, Boolean.valueOf(false)).withProperty(DECAYABLE, Boolean.valueOf(false));
    }
    
    // Inventory models are set only for the default states of leaves. Consequently, we modify the states for player placed leaves when they are
    // actually placed, not when they are in the inventory. We cannot change the default properties whilst reusing code from BlockLeaves.
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromState(this.getDefaultState().withProperty(this.variantProperty, world.getBlockState(pos).getValue(this.variantProperty))));
    }
    
    // leaves in the inventory should not be decayable
    //TODO REMOVE
    @Override
    protected ItemStack createStackedBlock(IBlockState state)
    {
    	IBlockState newState = state.withProperty(CHECK_DECAY, Boolean.valueOf(false)).withProperty(DECAYABLE, Boolean.valueOf(false));
    	
    	return super.createStackedBlock(newState);
    }
    
    @Override
    protected int getSaplingDropChance(IBlockState state)
    {
        return 20;
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        BOPTrees treeType = ((BOPTrees) state.getValue(this.variantProperty));
        return treeType.hasSapling() ? Item.getItemFromBlock( BlockBOPSapling.paging.getBlock(treeType) ) : null;
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        BOPTrees treeType = ((BOPTrees) state.getValue(this.variantProperty));
        return treeType.hasSapling() ? BlockBOPSapling.paging.getVariantItem(treeType).getItemDamage() : 0;
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
            // get default state corresponding to this tree (IE discard CHECK_DECAY and DECAYABLE bits for item meta)
            BOPTrees tree = (BOPTrees)state.getValue(this.variantProperty);
            int meta = this.getMetaFromState(this.getDefaultState().withProperty(this.variantProperty, tree));
            ret.add(new ItemStack(this, 1, meta));
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
    
  