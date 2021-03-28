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
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.common.block.BlockBOPMushroom.MushroomType;
import biomesoplenty.common.item.ItemBOPSapling;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.VariantPagingHelper;
import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import biomesoplenty.common.world.generator.tree.GeneratorBulbTree;
import biomesoplenty.common.world.generator.tree.GeneratorMahoganyTree;
import biomesoplenty.common.world.generator.tree.GeneratorMangroveTree;
import biomesoplenty.common.world.generator.tree.GeneratorPalmTree;
import biomesoplenty.common.world.generator.tree.GeneratorPineTree;
import biomesoplenty.common.world.generator.tree.GeneratorRedwoodTree;
import biomesoplenty.common.world.generator.tree.GeneratorTaigaTree;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
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
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPSapling extends BlockBOPDecoration implements IGrowable, IPlantable {
	
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
        return new BlockStateContainer(this, STAGE, this.variantProperty);
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
        return state.getValue(STAGE).intValue() * 8 + paging.getIndex(tree);
    }
    
    // which types of mushroom can live on which types of block
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {        
        switch ((BOPTrees) state.getValue(this.variantProperty))
        {
            case PALM: case MANGROVE:
                return BlockQueries.fertileOrSand.matches(world, pos.down());
            default:
                return BlockQueries.fertile.matches(world, pos.down());
        }
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
            	return new GeneratorBasicTree.Builder().log(BlockPlanks.EnumType.BIRCH).leaves(BOPTrees.YELLOW_AUTUMN).minHeight(5).maxHeight(8).updateNeighbours(true).create();
            case ORANGE_AUTUMN:
            	return new GeneratorBasicTree.Builder().log(BlockPlanks.EnumType.DARK_OAK).leaves(BOPTrees.ORANGE_AUTUMN).minHeight(5).maxHeight(8).updateNeighbours(true).create();
            case BAMBOO:
            	return new GeneratorBulbTree.Builder().minHeight(6).maxHeight(18).log(BOPBlocks.bamboo.getDefaultState()).leaves(BOPTrees.BAMBOO).updateNeighbours(true).create();
            case MAGIC:
                return new GeneratorBasicTree.Builder().log(BOPWoods.MAGIC).leaves(BOPTrees.MAGIC).updateNeighbours(true).create();
            case UMBRAN:
                return new GeneratorTaigaTree.Builder().log(BOPWoods.UMBRAN).leaves(BOPTrees.UMBRAN).maxHeight(20).updateNeighbours(true).create();
            case DEAD:
            	return new GeneratorBasicTree.Builder().log(BlockPlanks.EnumType.OAK).leaves(BOPTrees.DEAD).minHeight(5).maxHeight(8).updateNeighbours(true).create();
            case FIR:
            	return new GeneratorTaigaTree.Builder().log(BOPWoods.FIR).leaves(BOPTrees.FIR).minHeight(5).maxHeight(28).updateNeighbours(true).create();
            case ETHEREAL: //Not implemented
                return new WorldGenTrees(true);
            case ORIGIN:
            	return new GeneratorBasicTree.Builder().minHeight(5).maxHeight(8).leaves(BOPTrees.ORIGIN).updateNeighbours(true).create();
            case PINK_CHERRY:
            	return new GeneratorBasicTree.Builder().log(BOPWoods.CHERRY).leaves(BOPTrees.PINK_CHERRY).updateNeighbours(true).create();
            case WHITE_CHERRY:
            	return new GeneratorBasicTree.Builder().log(BOPWoods.CHERRY).leaves(BOPTrees.WHITE_CHERRY).updateNeighbours(true).create();
            case MAPLE:
                return new GeneratorBasicTree.Builder().log(BlockPlanks.EnumType.OAK).leaves(BOPTrees.MAPLE).minHeight(5).maxHeight(10).updateNeighbours(true).create();
            case HELLBARK:
                return new GeneratorTwigletTree.Builder().scatterYMethod(ScatterYMethod.NETHER_SURFACE).minHeight(2).maxHeight(2).log(BlockBOPLog.paging.getVariantState(BOPWoods.HELLBARK)).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.HELLBARK)).create();
            case FLOWERING:
                return new GeneratorBasicTree.Builder().altLeaves(BOPTrees.FLOWERING).updateNeighbours(true).create();
            case JACARANDA:
            	return new GeneratorBasicTree.Builder().minHeight(4).maxHeight(7).log(BOPWoods.JACARANDA).leaves(BOPTrees.JACARANDA).updateNeighbours(true).create();
            case SACRED_OAK:
                return new GeneratorBigTree.Builder().log(BOPWoods.SACRED_OAK).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.SACRED_OAK).withProperty(BlockOldLeaf.DECAYABLE, Boolean.valueOf(false))).minHeight(35).maxHeight(45).trunkWidth(2).foliageDensity(1.25D).updateNeighbours(true).create();
            case MANGROVE:
                return new GeneratorMangroveTree.Builder().log(BOPWoods.MANGROVE).leaves(BOPTrees.MANGROVE).create();
            case PALM:
                return new GeneratorPalmTree.Builder().log(BOPWoods.PALM).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.PALM).withProperty(BlockOldLeaf.DECAYABLE, Boolean.valueOf(false))).updateNeighbours(true).create();
            case REDWOOD:
                return new GeneratorRedwoodTree.Builder().create();
            case WILLOW:
            	return new GeneratorBasicTree.Builder().log(BOPWoods.WILLOW).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.WILLOW).withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false))).minHeight(8).maxHeight(12).maxLeavesRadius(2).vine(BOPBlocks.willow_vine.getDefaultState()).leavesOffset(0).updateNeighbours(true).create();
            case PINE:
            	return new GeneratorPineTree.Builder().minHeight(6).maxHeight(18).log(BOPWoods.PINE).leaves(BOPTrees.PINE).updateNeighbours(true).create();
            case MAHOGANY:
                return new GeneratorMahoganyTree.Builder().updateNeighbours(true).create();
            case EBONY:
                return new GeneratorBigTree.Builder().log(BOPWoods.EBONY).leaves(BOPTrees.EBONY).minHeight(4).maxHeight(10).foliageHeight(1).updateNeighbours(true).create();
            case EUCALYPTUS:
                return new GeneratorBulbTree.Builder().minHeight(15).maxHeight(30).log(BOPWoods.EUCALYPTUS).leaves(BOPTrees.EUCALYPTUS).updateNeighbours(true).create();
            default:
            	return null;
        }
    }
 
    // TODO: specify generator for each sapling
    protected WorldGenerator getBigTreeGenerator(BOPTrees treeType)
    {
        switch (treeType)
        {
            case REDWOOD:
                return (new GeneratorRedwoodTree.Builder()).log(BOPWoods.REDWOOD).leaves(BOPTrees.REDWOOD).minHeight(25).maxHeight(40).trunkWidth(2).create();
            default:
                return null;
        }
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
        if (state.getValue(STAGE).intValue() == 0)
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
            return this.generateSmallOrBigTree(worldIn, pos, state, rand, smallTreeGenerator);
        }
        return false;
    }
    
    public boolean generateSmallOrBigTree(World worldIn, BlockPos pos, IBlockState state, Random rand, WorldGenerator generator)
    {        
        // remove the sapling
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
        // try to grow the tree
        boolean success = generator.generate(worldIn, rand, pos);
        // put the sapling back if we failed
        if (!success) {worldIn.setBlockState(pos, state, 4);}
        return success;
    }
    
    public boolean generateMegaTree(World worldIn, BlockPos pos, IBlockState state, Random rand, WorldGenerator generator)
    {        
        // remove the saplings
        IBlockState air = Blocks.AIR.getDefaultState();
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

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return getDefaultState();
        return state;
    }
}