/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPTreeEnums.AllTrees;
import biomesoplenty.api.block.BOPTreeEnums.EightTrees;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BlockBOPSapling extends BlockDecoration implements IGrowable {
    
    // add properties
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);    
    // stage requires one bit, so we have 3 bits left for the VARIANT which means we can have eight per instance
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EightTrees.class );
    protected int pageNum;
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { STAGE, VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] {STAGE}; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((EightTrees) state.getValue(VARIANT)).map(this.pageNum).getName() + "_sapling";
    }
    
    
    public BlockBOPSapling(int pageNum)
    {
        super();
        this.pageNum = pageNum;
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockBoundsByRadiusAndHeight(0.4F, 0.8F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
    }
    

    // map from meta to state and vice verca.  Use the highest bit for STAGE and the other 3 for VARIANT (so we can have 8 per instance)
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EightTrees.values()[meta & 7]).withProperty(STAGE, Integer.valueOf(meta >> 3));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(STAGE)).intValue() * 8 + ((EightTrees)state.getValue(VARIANT)).ordinal();
    }   
    
    // which types of block allow trees
    // TODO: override for loftwood
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState groundState = world.getBlockState(pos.down());
        Block groundBlock = groundState.getBlock();
        boolean onFertile = (groundBlock == Blocks.dirt || groundBlock == BOPBlocks.dirt || groundBlock == Blocks.mycelium || groundBlock == Blocks.grass);        
        if (groundBlock instanceof BlockBOPGrass)
        {
            switch ((BlockBOPGrass.BOPGrassType) groundState.getValue(BlockBOPGrass.VARIANT))
            {
                case SPECTRAL_MOSS: case SMOLDERING:
                    break;
                case LOAMY: case SANDY: case SILTY: case ORIGIN: default:
                    onFertile = true;
                    break;
            }
        }
        return onFertile;
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
        return 0;
    }
    
    
    
    
    
    /***** stuff for growing trees *****/
    
    
    // TODO: specify generator for each sapling
    protected WorldGenAbstractTree getSmallTreeGenerator(AllTrees treeType)
    {
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
                 return new WorldGenTrees(true);
        }
    }
 
    // TODO: specify generator for each sapling
    protected WorldGenAbstractTree getBigTreeGenerator(AllTrees treeType)
    {
        return null;
    }
    
    // TODO: specify generator for each sapling
    protected WorldGenAbstractTree getMegaTreeGenerator(AllTrees treeType)
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
        
        AllTrees treeType = ((EightTrees) state.getValue(VARIANT)).map(this.pageNum);        
        WorldGenAbstractTree smallTreeGenerator = this.getSmallTreeGenerator(treeType);
        WorldGenAbstractTree bigTreeGenerator = this.getBigTreeGenerator(treeType);
        WorldGenAbstractTree megaTreeGenerator = this.getMegaTreeGenerator(treeType);

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
    
    public boolean generateSmallOrBigTree(World worldIn, BlockPos pos, IBlockState state, Random rand, WorldGenAbstractTree generator)
    {        
        // remove the sapling
        worldIn.setBlockState(pos, Blocks.air.getDefaultState(), 4);
        // try to grow the tree
        boolean success = ((WorldGenerator)generator).generate(worldIn, rand, pos);
        // put the sapling back if we failed
        if (!success) {worldIn.setBlockState(pos, state, 4);}
        return success;
    }
    
    public boolean generateMegaTree(World worldIn, BlockPos pos, IBlockState state, Random rand, WorldGenAbstractTree generator)
    {        
        // remove the saplings
        IBlockState air = Blocks.air.getDefaultState();
        worldIn.setBlockState(pos, air, 4);
        worldIn.setBlockState(pos.add(1, 0, 0), air, 4);
        worldIn.setBlockState(pos.add(0, 0, 1), air, 4);
        worldIn.setBlockState(pos.add(1, 0, 1), air, 4);
        // try to grow the tree
        boolean success = ((WorldGenerator)generator).generate(worldIn, rand, pos);
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