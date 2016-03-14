/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.block.BlockQueries.air;
import static biomesoplenty.api.block.BlockQueries.airAbove;
import static biomesoplenty.api.block.BlockQueries.airBelow;
import static biomesoplenty.api.block.BlockQueries.airOrLeaves;
import static biomesoplenty.api.block.BlockQueries.anything;
import static biomesoplenty.api.block.BlockQueries.breakable;
import static biomesoplenty.api.block.BlockQueries.endish;
import static biomesoplenty.api.block.BlockQueries.fertile;
import static biomesoplenty.api.block.BlockQueries.fertileOrNetherrack;
import static biomesoplenty.api.block.BlockQueries.fertileSeaBed;
import static biomesoplenty.api.block.BlockQueries.groundBlocks;
import static biomesoplenty.api.block.BlockQueries.hasWater;
import static biomesoplenty.api.block.BlockQueries.hellish;
import static biomesoplenty.api.block.BlockQueries.litBeach;
import static biomesoplenty.api.block.BlockQueries.litDry;
import static biomesoplenty.api.block.BlockQueries.litFertile;
import static biomesoplenty.api.block.BlockQueries.litFertileOrDry;
import static biomesoplenty.api.block.BlockQueries.litFertileWaterside;
import static biomesoplenty.api.block.BlockQueries.litSand;
import static biomesoplenty.api.block.BlockQueries.nothing;
import static biomesoplenty.api.block.BlockQueries.replaceable;
import static biomesoplenty.api.block.BlockQueries.rootsCanDigThrough;
import static biomesoplenty.api.block.BlockQueries.solid;
import static biomesoplenty.api.block.BlockQueries.spectralMoss;
import static biomesoplenty.api.block.BlockQueries.suitableForReed;
import static biomesoplenty.api.block.BlockQueries.surfaceBlocks;
import static biomesoplenty.api.block.BlockQueries.sustainsCave;
import static biomesoplenty.api.block.BlockQueries.sustainsNether;
import static biomesoplenty.api.block.BlockQueries.underwater;
import static biomesoplenty.api.block.BlockQueries.waterCovered;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

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
                return (world.getBlockState(pos.west()).getMaterial() == Material.water || world.getBlockState(pos.east()).getMaterial() == Material.water || world.getBlockState(pos.north()).getMaterial() == Material.water || world.getBlockState(pos.south()).getMaterial() == Material.water);
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
                return world.getBlockState(pos).getMaterial() == Material.water && world.getBlockState(pos.up()).getMaterial() == Material.water;
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
        
        air = new BlockQueryMaterial(Material.air);  
        airOrLeaves = new BlockQueryMaterial(Material.air, Material.leaves);  
        
        // Match blocks which count as 'the surface' - useful for finding places to put plants, trees, lilypads etc - note plants, trees, snow all excluded because they sit or grow 'on' the surface
        surfaceBlocks = new BlockQueryMaterial(Material.barrier, Material.clay, Material.grass, Material.ground, Material.ice, Material.lava, Material.packedIce, Material.rock, Material.sand, Material.water);
        // As above but without the liquids - useful for placing stuff on the sea floor
        groundBlocks = new BlockQueryMaterial(Material.barrier, Material.clay, Material.grass, Material.ground, Material.packedIce, Material.rock, Material.sand);
        
        fertile = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Plains).create();
        fertileOrNetherrack = BlockQuery.buildOr().sustainsPlant(EnumPlantType.Plains).blocks(Blocks.netherrack).states(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK)).create();
        sustainsCave = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Cave).create();
        sustainsNether = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Nether).create();
        endish = BlockQuery.buildOr().blocks(Blocks.end_stone).states(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SPECTRAL_MOSS)).create();
        hellish = BlockQuery.buildOr().blocks(Blocks.netherrack, BOPBlocks.flesh).states(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK)).create();
        litBeach = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Beach).withLightAboveAtLeast(8).create();
        litFertileWaterside = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Plains).byWater().create();
        litFertile = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Plains).withLightAboveAtLeast(8).create();
        litSand = BlockQuery.buildAnd().materials(Material.sand).withLightAboveAtLeast(8).create();
        litDry = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Desert).withLightAboveAtLeast(8).create();
        litFertileOrDry = BlockQuery.buildOr().add(litFertile).add(litDry).create();
        spectralMoss = new BlockQueryState(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SPECTRAL_MOSS));
        underwater = new BlockQueryMaterial(Material.water);
        fertileSeaBed = new BlockQueryMaterial(Material.ground, Material.sand, Material.clay, Material.sponge);
        suitableForReed = BlockQuery.buildAnd().add(new IBlockPosQuery() {
            // reed needs the ground block to be water, but the block below that to NOT be water
            @Override public boolean matches(World world, BlockPos pos) {
                BlockPos groundPos = pos.down();
                return world.getBlockState(pos).getBlock() == Blocks.water && 
                        (world.getBlockState(groundPos).getBlock() != Blocks.water && world.getBlockState(groundPos).isSideSolid(world, groundPos, EnumFacing.UP));
            }
        }).withLightAboveAtLeast(8).create();
        rootsCanDigThrough = new BlockQueryMaterial(Material.air, Material.water, Material.ground, Material.grass, Material.sand, Material.clay, Material.plants, Material.leaves);
    
    }
}