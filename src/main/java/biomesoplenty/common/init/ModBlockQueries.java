/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import static biomesoplenty.api.block.BlockQueries.*;

public class ModBlockQueries
{
   
    public static void init()
    {
        
        anything = BlockQuery.anything;
        nothing = BlockQuery.nothing;
        
        // Match block positions adjacent to water
        hasWater = new IBlockPosQuery()
        {
            @Override
            public boolean matches(World world, BlockPos pos)
            {
                return (world.getBlockState(pos.west()).getMaterial() == Material.WATER || world.getBlockState(pos.east()).getMaterial() == Material.WATER || world.getBlockState(pos.north()).getMaterial() == Material.WATER || world.getBlockState(pos.south()).getMaterial() == Material.WATER);
            }
        };
        
        // Match block positions with air above
        airAbove = new IBlockPosQuery()
        {
            @Override
            public boolean matches(World world, BlockPos pos)
            {
                return world.isAirBlock(pos.up());
            }
        };
        
        // Match block positions with air below
        airBelow = new IBlockPosQuery()
        {
            @Override
            public boolean matches(World world, BlockPos pos)
            {
                return world.isAirBlock(pos.down());
            }
        };
        
        // Match block positions with water above
        waterCovered = new IBlockPosQuery()
        {
            @Override
            public boolean matches(World world, BlockPos pos)
            {
                return world.getBlockState(pos).getMaterial() == Material.WATER && world.getBlockState(pos.up()).getMaterial() == Material.WATER;
            }
        };
        
        // Match blocks which are not unbreakable - IE not bedrock, barrier, command blocks
        breakable = new IBlockPosQuery()
        {
            // Block.setBlockUnbreakable sets the hardness value to -1.0F
            @Override
            public boolean matches(World world, BlockPos pos)
            {
                return world.getBlockState(pos).getBlockHardness(world, pos) >= 0.0F;
            }
        }; 
        
        //Match blocks with a solid top
        solid = new IBlockPosQuery()
        {
            // Block.setBlockUnbreakable sets the hardness value to -1.0F
            @Override
            public boolean matches(World world, BlockPos pos)
            {
                return world.getBlockState(pos).isSideSolid(world, pos, EnumFacing.UP);
            }
        }; 
        
        //Match replacable blocks
        replaceable = new IBlockPosQuery()
        {
            // Block.setBlockUnbreakable sets the hardness value to -1.0F
            @Override
            public boolean matches(World world, BlockPos pos)
            {
                return world.getBlockState(pos).getBlock().isReplaceable(world, pos);
            }
        }; 
        
        air = new BlockQueryMaterial(Material.AIR);
        airOrLeaves = new BlockQueryMaterial(Material.AIR, Material.LEAVES);
        
        // Match blocks which count as 'the surface' - useful for finding places to put plants, trees, lilypads etc - note plants, trees, snow all excluded because they sit or grow 'on' the surface
        surfaceBlocks = new BlockQueryMaterial(Material.BARRIER, Material.CLAY, Material.GRASS, Material.GROUND, Material.ICE, Material.LAVA, Material.PACKED_ICE, Material.ROCK, Material.SAND, Material.WATER);
        // As above but without the liquids - useful for placing stuff on the sea floor
        groundBlocks = new BlockQueryMaterial(Material.BARRIER, Material.CLAY, Material.GRASS, Material.GROUND, Material.PACKED_ICE, Material.ROCK, Material.SAND);
        
        fertile = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Plains).create();
        fertileOrNetherrack = BlockQuery.buildOr().sustainsPlant(EnumPlantType.Plains).blocks(Blocks.NETHERRACK).states(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK)).create();
        sustainsCave = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Cave).create();
        sustainsNether = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Nether).create();
        endish = BlockQuery.buildOr().blocks(Blocks.END_STONE).states(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SPECTRAL_MOSS)).create();
        hellish = BlockQuery.buildOr().blocks(Blocks.NETHERRACK, BOPBlocks.flesh).states(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK)).create();
        litBeach = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Beach).withLightAboveAtLeast(8).create();
        litFertileWaterside = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Plains).byWater().create();
        litFertile = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Plains).withLightAboveAtLeast(8).create();
        litSand = BlockQuery.buildAnd().materials(Material.SAND).withLightAboveAtLeast(8).create();
        litDry = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Desert).withLightAboveAtLeast(8).create();
        litFertileOrDry = BlockQuery.buildOr().add(litFertile).add(litDry).create();
        spectralMoss = new BlockQueryState(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SPECTRAL_MOSS));
        underwater = new BlockQueryMaterial(Material.WATER);
        fertileSeaBed = new BlockQueryMaterial(Material.GROUND, Material.SAND, Material.CLAY, Material.SPONGE);
        suitableForReed = BlockQuery.buildAnd().add(new IBlockPosQuery() {
            // reed needs the ground block to be water, but the block below that to NOT be water
            @Override public boolean matches(World world, BlockPos pos) {
                BlockPos groundPos = pos.down();
                return (world.getBlockState(pos).getMaterial() == Material.WATER && ((Integer)world.getBlockState(pos).getValue(BlockLiquid.LEVEL)).intValue() == 0 || world.getBlockState(pos).getMaterial() == Material.ICE) &&
                        (world.getBlockState(groundPos).getBlock() != Blocks.WATER && world.getBlockState(groundPos).isSideSolid(world, groundPos, EnumFacing.UP));
            }
        }).withLightAboveAtLeast(8).create();
        rootsCanDigThrough = new BlockQueryMaterial(Material.AIR, Material.WATER, Material.GROUND, Material.GRASS, Material.SAND, Material.CLAY, Material.PLANTS, Material.LEAVES);
    
    }
}