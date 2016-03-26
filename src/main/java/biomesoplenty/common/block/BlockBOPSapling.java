/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.item.ItemBOPSapling;
import biomesoplenty.common.util.block.VariantPagingHelper;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
import biomesoplenty.common.world.feature.tree.GeneratorBulbTree;
import biomesoplenty.common.world.feature.tree.GeneratorMahoganyTree;
import biomesoplenty.common.world.feature.tree.GeneratorPalmTree;
import biomesoplenty.common.world.feature.tree.GeneratorPineTree;
import biomesoplenty.common.world.feature.tree.GeneratorRedwoodTree;
import biomesoplenty.common.world.feature.tree.GeneratorTaigaTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPSapling extends BlockBOPDecoration implements IGrowable {
	
	protected static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    // setup paged variant property
    
    // STAGE requires one bit, so we have 3 bits left for the VARIANT which means we can have eight per instance
    public static VariantPagingHelper<BlockBOPSapling, BOPTrees> paging = new VariantPagingHelper<BlockBOPSapling, BOPTrees>(8, BOPTrees.class, BOPTrees.withSaplings);
    
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
            paging.addBlock(i, new BlockBOPSapling());
        }   
    }
    
    // Each instance has a reference to its own variant property
    public IProperty variantProperty;
    
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1); 
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        this.variantProperty = currentVariantProperty; // get from static variable
        return new BlockStateContainer(this, new IProperty[] { STAGE, this.variantProperty });
    }
       
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPSapling.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {this.variantProperty}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] {STAGE}; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((BOPTrees) state.getValue(this.variantProperty)).getName() + "_sapling";
    }
    
    
    public BlockBOPSapling()
    {
        super();
        this.setSoundType(SoundType.PLANT);
        //this.setBlockBoundsByRadiusAndHeight(0.4F, 0.8F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
    }
    

    // map from meta to state and vice verca.  Use the highest bit for STAGE and the other 3 for VARIANT (so we can have 8 per instance)
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(this.variantProperty, paging.getVariant(this, meta & 7)).withProperty(STAGE, Integer.valueOf(meta >> 3));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        BOPTrees tree = (BOPTrees)state.getValue(this.variantProperty);
        return ((Integer)state.getValue(STAGE)).intValue() * 8 + paging.getIndex(tree);
    }
        
    // TODO: override for loftwood - what is that?
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        return BlockQueries.fertile.matches(world, pos.down());
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDING_BOX;
    }
    
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            super.updateTick(worldIn, pos, state, rand);
            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(worldIn, rand, pos, state);
            }
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        //We don't want item drops to have metadata for the stage
        return this.getMetaFromState(state) & 7;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.NONE;
    }
    
    
    
    /***** stuff for growing trees *****/
    
    
    // TODO: specify generator for each sapling
    protected WorldGenerator getSmallTreeGenerator(BOPTrees treeType)
    {
        switch (treeType)
        {
            case YELLOW_AUTUMN:
            	return new GeneratorBasicTree.Builder().log(BlockPlanks.EnumType.BIRCH).leaves(BOPTrees.YELLOW_AUTUMN).minHeight(5).maxHeight(8).create();
            case ORANGE_AUTUMN:
            	return new GeneratorBasicTree.Builder().log(BlockPlanks.EnumType.DARK_OAK).leaves(BOPTrees.ORANGE_AUTUMN).minHeight(5).maxHeight(8).create();
            case BAMBOO:
            	return new GeneratorBulbTree.Builder().minHeight(6).maxHeight(18).log(BOPBlocks.bamboo.getDefaultState()).leaves(BOPTrees.BAMBOO).create();
            case MAGIC:
                return new GeneratorBasicTree.Builder().log(BOPWoods.MAGIC).leaves(BOPTrees.MAGIC).create();
            case UMBRAN:
                return new GeneratorTaigaTree.Builder().log(BOPWoods.UMBRAN).leaves(BOPTrees.UMBRAN).maxHeight(20).create();
            case DEAD:
            	return new GeneratorBasicTree.Builder().log(BlockPlanks.EnumType.OAK).leaves(BOPTrees.DEAD).minHeight(5).maxHeight(8).create();
            case FIR:
            	return new GeneratorTaigaTree.Builder().log(BOPWoods.FIR).leaves(BOPTrees.FIR).minHeight(5).maxHeight(28).create();
            case ETHEREAL: //Not implemented
                return new WorldGenTrees(true);
            case ORIGIN:
            	return new GeneratorBasicTree.Builder().minHeight(5).maxHeight(8).leaves(BOPTrees.ORIGIN).create();
            case PINK_CHERRY:
            	return new GeneratorBasicTree.Builder().log(BOPWoods.CHERRY).leaves(BOPTrees.PINK_CHERRY).create();
            case WHITE_CHERRY:
            	return new GeneratorBasicTree.Builder().log(BOPWoods.CHERRY).leaves(BOPTrees.WHITE_CHERRY).create();
            case MAPLE:
                return new GeneratorBasicTree.Builder().log(BlockPlanks.EnumType.OAK).leaves(BOPTrees.MAPLE).minHeight(5).maxHeight(10).create();
            case HELLBARK: //Not implemented
                return new WorldGenTrees(true);
            case FLOWERING:
                return new GeneratorBasicTree.Builder().altLeaves(BOPTrees.FLOWERING).create();
            case JACARANDA:
            	return new GeneratorBasicTree.Builder().minHeight(4).maxHeight(7).log(BOPWoods.JACARANDA).leaves(BOPTrees.JACARANDA).create();
            case SACRED_OAK:
                return new GeneratorBigTree.Builder().log(BOPWoods.SACRED_OAK).leaves(BOPTrees.SACRED_OAK).minHeight(35).maxHeight(45).trunkWidth(2).foliageDensity(1.25D).create();
            case MANGROVE: //Not implemented
                return new WorldGenTrees(true);
            case PALM:
                return new GeneratorPalmTree.Builder().log(BOPWoods.PALM).leaves(BOPTrees.PALM).create();
            case REDWOOD:
                return new GeneratorRedwoodTree.Builder().create();
            case WILLOW:
            	return new GeneratorBasicTree.Builder().log(BOPWoods.WILLOW).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.WILLOW).withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false))).minHeight(8).maxHeight(12).maxLeavesRadius(2).vine(BlockBOPLeaves.paging.getVariantState(BOPTrees.WILLOW).withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false))).leavesOffset(0).create();
            case PINE:
            	return new GeneratorPineTree.Builder().minHeight(6).maxHeight(18).log(BOPWoods.PINE).leaves(BOPTrees.PINE).create();
            case MAHOGANY:
                return new GeneratorMahoganyTree.Builder().create();
            case EBONY:
                return new GeneratorBigTree.Builder().log(BOPWoods.EBONY).leaves(BOPTrees.EBONY).minHeight(4).maxHeight(10).foliageHeight(1).create();
            case EUCALYPTUS:
                return new GeneratorBulbTree.Builder().minHeight(15).maxHeight(30).log(BOPWoods.EUCALYPTUS).leaves(BOPTrees.EUCALYPTUS).create();
            default:
            	return null;
        }
    }
 
    // TODO: specify generator for each sapling
    protected WorldGenerator getBigTreeGenerator(BOPTrees treeType)
    {
        return null;
    }
    
    // TODO: specify generator for each sapling
    protected WorldGenerator getMegaTreeGenerator(BOPTrees treeType)
    {
        return null;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return (double)worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        if (((Integer)state.getValue(STAGE)).intValue() == 0)
        {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            this.generateTree(worldIn, pos, state, rand);
        }    
    }
    
    public boolean thisSaplingHere(World world, BlockPos pos)
    {
        return world.getBlockState(pos).getBlock() == this;
    }
    
    // try to generate a tree at the specified location, return true on success and false on failure
    public boolean generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) {return false;}

        BOPTrees treeType = ((BOPTrees) state.getValue(this.variantProperty));
        WorldGenerator smallTreeGenerator = this.getSmallTreeGenerator(treeType);
        WorldGenerator bigTreeGenerator = this.getBigTreeGenerator(treeType);
        WorldGenerator megaTreeGenerator = this.getMegaTreeGenerator(treeType);

        if (megaTreeGenerator != null)
        {
            // if we have 4 saplings in a square, then try to grow a mega tree
            int i;
            int j;
            for (i = 0; i >= -1; --i)
            {
                for (j = 0; j >= -1; --j)
                {
                    if (this.thisSaplingHere(worldIn, pos.add(i, 0, j)) && this.thisSaplingHere(worldIn, pos.add(i + 1, 0, j)) && this.thisSaplingHere(worldIn, pos.add(i, 0, j + 1)) && this.thisSaplingHere(worldIn, pos.add(i + 1, 0, j + 1)))
                    {
                        if (this.generateMegaTree(worldIn, pos.add(i, 0, j), state, rand, megaTreeGenerator)) {return true;}
                    }
                }
            }
        }
        if (bigTreeGenerator != null)
        {
            // with a one in 10 chance, try to grow a big tree
            if (rand.nextInt(10) == 0)
            {
                if (this.generateSmallOrBigTree(worldIn, pos, state, rand, bigTreeGenerator)) {return true;}
            }
        }
        // otherwise, try to grow a small tree
        if (smallTreeGenerator != null)
        {
            if (this.generateSmallOrBigTree(worldIn, pos, state, rand, smallTreeGenerator)) {return true;}
        }
        return false;
    }
    
    public boolean generateSmallOrBigTree(World worldIn, BlockPos pos, IBlockState state, Random rand, WorldGenerator generator)
    {        
        // remove the sapling
        worldIn.setBlockState(pos, Blocks.air.getDefaultState(), 4);
        // try to grow the tree
        boolean success = generator.generate(worldIn, rand, pos);
        // put the sapling back if we failed
        if (!success) {worldIn.setBlockState(pos, state, 4);}
        return success;
    }
    
    public boolean generateMegaTree(World worldIn, BlockPos pos, IBlockState state, Random rand, WorldGenerator generator)
    {        
        // remove the saplings
        IBlockState air = Blocks.air.getDefaultState();
        worldIn.setBlockState(pos, air, 4);
        worldIn.setBlockState(pos.add(1, 0, 0), air, 4);
        worldIn.setBlockState(pos.add(0, 0, 1), air, 4);
        worldIn.setBlockState(pos.add(1, 0, 1), air, 4);
        // try to grow the tree
        boolean success = generator.generate(worldIn, rand, pos);
        if (!success)
        {
            // put the saplings back if we failed
            worldIn.setBlockState(pos, air, 4);
            worldIn.setBlockState(pos.add(1, 0, 0), state, 4);
            worldIn.setBlockState(pos.add(0, 0, 1), state, 4);
            worldIn.setBlockState(pos.add(1, 0, 1), state, 4);
        }
        return success;
    }

    
    
}