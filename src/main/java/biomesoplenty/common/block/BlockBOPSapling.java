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
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BlockBOPSapling extends BlockDecoration implements IGrowable {
    
    // add properties
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { STAGE });}
    
    protected WorldGenAbstractTree smallTreeGenerator;
    protected WorldGenAbstractTree bigTreeGenerator;
    protected WorldGenAbstractTree megaTreeGenerator;
    
    public BlockBOPSapling(WorldGenAbstractTree smallTreeGenerator)
    {
        this(smallTreeGenerator, null, null);
    }

    public BlockBOPSapling(WorldGenAbstractTree smallTreeGenerator, WorldGenAbstractTree bigTreeGenerator)
    {
        this(smallTreeGenerator, bigTreeGenerator, null);
    }
    
    public BlockBOPSapling(WorldGenAbstractTree smallTreeGenerator, WorldGenAbstractTree bigTreeGenerator, WorldGenAbstractTree megaTreeGenerator)
    {
        super();
        
        this.smallTreeGenerator = smallTreeGenerator;
        this.bigTreeGenerator = bigTreeGenerator;
        this.megaTreeGenerator = megaTreeGenerator;

        this.setStepSound(Block.soundTypeGrass);
        this.setBlockBoundsByRadiusAndHeight(0.4F, 0.8F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
    }
    
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(STAGE, Integer.valueOf(meta));
    }
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(STAGE)).intValue();
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

        if (this.megaTreeGenerator != null)
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
                        if (this.generateMegaTree(worldIn, pos.add(i, 0, j), state, rand, this.megaTreeGenerator)) {return true;}
                    }
                }
            }
        }
        if (this.bigTreeGenerator != null)
        {
            // with a one in 10 chance, try to grow a big tree
            if (rand.nextInt(10) == 0)
            {
                if (this.generateSmallOrBigTree(worldIn, pos, state, rand, this.bigTreeGenerator)) {return true;}
            }
        }
        // otherwise, try to grow a small tree
        if (this.smallTreeGenerator != null)
        {
            if (this.generateSmallOrBigTree(worldIn, pos, state, rand, this.smallTreeGenerator)) {return true;}
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