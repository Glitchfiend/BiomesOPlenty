/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.util.SimpleBlockPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class EndscraperFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.END_STONE;
    protected SimpleBlockPredicate replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).is(BlockTags.REPLACEABLE_BY_TREES) || world.getBlockState(pos).getBlock() instanceof VegetationBlock || world.getBlockState(pos).getBlock() == BOPBlocks.NULL_END_STONE || world.getBlockState(pos).getBlock() == BOPBlocks.UNMAPPED_END_STONE;

    public EndscraperFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel world = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        RandomSource rand = featurePlaceContext.random();
        BlockPos startPos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        while (startPos.getY() >= world.getMinY()+1 && this.replace.matches(world, startPos)) {startPos = startPos.below();}

        int size = 9-(3*rand.nextInt(2))+(3*rand.nextInt(4));
        int stories = 1 + rand.nextInt(6);
        int roomHeight = 8;
        int height = (stories*(roomHeight))+1;

        if (!this.placeOn.matches(world, startPos.offset(size/2, 0, size/2)))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        if (!this.checkSpace(world, startPos.above(), size, height))
        {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos.above();

        //Foundation
        for (int x = -1; x <= size+1; x++)
        {
            for (int z = -1; z <= size+1; z++)
            {
                int depth = 4 + rand.nextInt(4);

                if (!((x == -1 || x == size+1) && (z == -1 || z == size+1)))
                {
                    for (int y = 0; y <= depth; y++)
                    {
                        this.setBlock(world, startPos.offset(x, -y, z), Blocks.END_STONE.defaultBlockState());
                    }
                }
            }
        }

        //Rooms
        for (int i = 0; i < stories; i++)
        {
            int roomType = rand.nextInt(16);
            switch (roomType)
            {
                default: case 0: case 1: case 2: case 3:
                    generateEmptyRoom(world,pos.above(i*roomHeight),size,roomHeight, false);
                    break;

                case 4: case 5: case 6: case 7: case 8:
                    generateChainRoom(world,pos.above(i*roomHeight),size,roomHeight);
                    break;

                case 9: case 10: case 11:
                    generateDoorRoom(world,pos.above(i*roomHeight),size,roomHeight);
                    break;

                case 12: case 13:
                    generateChorusFruitRoom(world,pos.above(i*roomHeight),size,roomHeight);
                    break;

                case 14:
                    generatePoolRoom(world,pos.above(i*roomHeight),size,roomHeight);
                    break;

                case 15:
                    generateBackroom(world,pos.above(i*roomHeight),size,roomHeight);
                    break;
            }
        }

        //Roof
        for (int x = 0; x <= size; x++)
        {
            for (int z = 0; z <= size; z++)
            {
                //Blocks
                if (x == 0 || x == size || z == 0 || z == size)
                {
                    BlockState edgeBlock = Blocks.WAXED_WEATHERED_CUT_COPPER.defaultBlockState();
                    if (world.getRandom().nextInt(2) == 0) { edgeBlock = Blocks.WAXED_WEATHERED_COPPER.defaultBlockState(); }
                    this.setBlock(world, startPos.offset(x,height,z), edgeBlock);
                }
                else
                {
                    this.setBlock(world, startPos.offset(x,height,z), Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB.defaultBlockState());
                }

                //Bars
                if (x == 0 && z == 0)
                {
                    this.setBlock(world, startPos.offset(x,height+1,z), Blocks.COPPER_BARS.waxedWeathered().defaultBlockState().setValue(IronBarsBlock.SOUTH, true).setValue(IronBarsBlock.EAST, true));
                }
                else if (x == 0 && z == size)
                {
                    this.setBlock(world, startPos.offset(x,height+1,z), Blocks.COPPER_BARS.waxedWeathered().defaultBlockState().setValue(IronBarsBlock.NORTH, true).setValue(IronBarsBlock.EAST, true));
                }
                else if (x == size && z == 0)
                {
                    this.setBlock(world, startPos.offset(x,height+1,z), Blocks.COPPER_BARS.waxedWeathered().defaultBlockState().setValue(IronBarsBlock.SOUTH, true).setValue(IronBarsBlock.WEST, true));
                }
                else if (x == size && z == size)
                {
                    this.setBlock(world, startPos.offset(x,height+1,z), Blocks.COPPER_BARS.waxedWeathered().defaultBlockState().setValue(IronBarsBlock.NORTH, true).setValue(IronBarsBlock.WEST, true));
                }
                else
                {
                    if (x == 0 || x == size)
                    {
                        this.setBlock(world, startPos.offset(x,height+1,z), Blocks.COPPER_BARS.waxedWeathered().defaultBlockState().setValue(IronBarsBlock.NORTH, true).setValue(IronBarsBlock.SOUTH, true));
                    }
                    if (z == 0 || z == size)
                    {
                        this.setBlock(world, startPos.offset(x,height+1,z), Blocks.COPPER_BARS.waxedWeathered().defaultBlockState().setValue(IronBarsBlock.EAST, true).setValue(IronBarsBlock.WEST, true));
                    }
                }
            }
        }

        return true;
    }

    ///////////////////////////////////////

    public void generateEmptyRoom(WorldGenLevel world, BlockPos pos, int size, int roomHeight, boolean windowsOpen)
    {
        //Floor
        for (int x = 0; x <= size; x++)
        {
            for (int z = 0; z <= size; z++)
            {
                BlockState separatorBlock = Blocks.WAXED_WEATHERED_CUT_COPPER.defaultBlockState();
                if (world.getRandom().nextInt(2) == 0) { separatorBlock = Blocks.WAXED_WEATHERED_COPPER.defaultBlockState(); }
                this.setBlock(world, pos.offset(x,0,z), separatorBlock);
            }
        }

        pos = pos.above();

        //Walls and Windows
        for (int x = 0; x <= size; x++)
        {
            for (int z = 0; z <= size; z++)
            {
                for (int y = 0; y <= roomHeight-2; y++)
                {
                    BlockState wallBlock = Blocks.AIR.defaultBlockState();

                    //West Wall
                    if (x == 0)
                    {
                        if ((z % 3 == 1 || z % 3 == 2))
                        {
                            //Bottom Window
                            if (y % 3 == 1) { wallBlock = Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR.defaultBlockState().setValue(TrapDoorBlock.FACING, Direction.WEST).setValue(TrapDoorBlock.HALF, Half.BOTTOM).setValue(TrapDoorBlock.OPEN, !windowsOpen); }
                            //Top Window
                            else if (y % 3 == 2) { wallBlock = Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR.defaultBlockState().setValue(TrapDoorBlock.FACING, Direction.WEST).setValue(TrapDoorBlock.HALF, Half.TOP).setValue(TrapDoorBlock.OPEN, !windowsOpen); }
                            //Wall
                            else if (world.getRandom().nextInt(5) == 0) { wallBlock = Blocks.END_STONE.defaultBlockState(); }
                            else { wallBlock = Blocks.END_STONE_BRICKS.defaultBlockState(); }
                        }
                        else
                        {
                            if (world.getRandom().nextInt(5) == 0) { wallBlock = Blocks.END_STONE.defaultBlockState(); }
                            else { wallBlock = Blocks.END_STONE_BRICKS.defaultBlockState(); }
                        }
                    }
                    //East Wall
                    if (x == size)
                    {
                        if ((z % 3 == 1 || z % 3 == 2))
                        {
                            //Bottom Window
                            if (y % 3 == 1) { wallBlock = Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR.defaultBlockState().setValue(TrapDoorBlock.FACING, Direction.EAST).setValue(TrapDoorBlock.HALF, Half.BOTTOM).setValue(TrapDoorBlock.OPEN, !windowsOpen); }
                            //Top Window
                            else if (y % 3 == 2) { wallBlock = Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR.defaultBlockState().setValue(TrapDoorBlock.FACING, Direction.EAST).setValue(TrapDoorBlock.HALF, Half.TOP).setValue(TrapDoorBlock.OPEN, !windowsOpen); }
                            //Wall
                            else if (world.getRandom().nextInt(5) == 0) { wallBlock = Blocks.END_STONE.defaultBlockState(); }
                            else { wallBlock = Blocks.END_STONE_BRICKS.defaultBlockState(); }
                        }
                        else
                        {
                            if (world.getRandom().nextInt(5) == 0) { wallBlock = Blocks.END_STONE.defaultBlockState(); }
                            else { wallBlock = Blocks.END_STONE_BRICKS.defaultBlockState(); }
                        }
                    }
                    //North Wall
                    if (z == 0)
                    {
                        if ((x % 3 == 1 || x % 3 == 2))
                        {
                            //Bottom Window
                            if (y % 3 == 1) { wallBlock = Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR.defaultBlockState().setValue(TrapDoorBlock.FACING, Direction.NORTH).setValue(TrapDoorBlock.HALF, Half.BOTTOM).setValue(TrapDoorBlock.OPEN, !windowsOpen); }
                            //Top Window
                            else if (y % 3 == 2) { wallBlock = Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR.defaultBlockState().setValue(TrapDoorBlock.FACING, Direction.NORTH).setValue(TrapDoorBlock.HALF, Half.TOP).setValue(TrapDoorBlock.OPEN, !windowsOpen); }
                            //Wall
                            else if (world.getRandom().nextInt(5) == 0) { wallBlock = Blocks.END_STONE.defaultBlockState(); }
                            else { wallBlock = Blocks.END_STONE_BRICKS.defaultBlockState(); }
                        }
                        else
                        {
                            if (world.getRandom().nextInt(5) == 0) { wallBlock = Blocks.END_STONE.defaultBlockState(); }
                            else { wallBlock = Blocks.END_STONE_BRICKS.defaultBlockState(); }
                        }
                    }
                    //South Wall
                    if (z == size)
                    {
                        if ((x % 3 == 1 || x % 3 == 2))
                        {
                            //Bottom Window
                            if (y % 3 == 1) { wallBlock = Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR.defaultBlockState().setValue(TrapDoorBlock.FACING, Direction.SOUTH).setValue(TrapDoorBlock.HALF, Half.BOTTOM).setValue(TrapDoorBlock.OPEN, !windowsOpen); }
                            //Top Window
                            else if (y % 3 == 2) { wallBlock = Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR.defaultBlockState().setValue(TrapDoorBlock.FACING, Direction.SOUTH).setValue(TrapDoorBlock.HALF, Half.TOP).setValue(TrapDoorBlock.OPEN, !windowsOpen); }
                            //Wall
                            else if (world.getRandom().nextInt(5) == 0) { wallBlock = Blocks.END_STONE.defaultBlockState(); }
                            else { wallBlock = Blocks.END_STONE_BRICKS.defaultBlockState(); }
                        }
                        else
                        {
                            if (world.getRandom().nextInt(5) == 0) { wallBlock = Blocks.END_STONE.defaultBlockState(); }
                            else { wallBlock = Blocks.END_STONE_BRICKS.defaultBlockState(); }
                        }
                    }

                    if (!wallBlock.isAir()) { this.setBlock(world, pos.offset(x,y,z), wallBlock); }
                }
            }
        }
    }

    public void generateChainRoom(WorldGenLevel world, BlockPos pos, int size, int roomHeight)
    {
        generateEmptyRoom(world,pos,size,roomHeight,false);

        //Chains
        for (int x = 1; x <= (size-1); x++)
        {
            for (int z = 1; z <= (size-1); z++)
            {
                if (world.getRandom().nextInt(4) == 0)
                {
                    int chainHeight = world.getRandom().nextInt(roomHeight);
                    for (int y = 0; y < chainHeight; y++)
                    {
                        this.setBlock(world, pos.offset(x,(roomHeight-1)-y,z), Blocks.COPPER_CHAIN.waxedWeathered().defaultBlockState());
                    }
                }
            }
        }
    }

    public void generateDoorRoom(WorldGenLevel world, BlockPos pos, int size, int roomHeight)
    {
        generateEmptyRoom(world,pos,size,roomHeight,false);

        for (int x = 1; x <= (size-1); x++)
        {
            for (int z = 1; z <= (size-1); z++)
            {
                if ((x % 3 == 1 || x % 3 == 2) && (z % 3 == 1 || z % 3 == 2))
                {
                    this.setBlock(world, pos.offset(x,(roomHeight-1),z), Blocks.WAXED_WEATHERED_CHISELED_COPPER.defaultBlockState());
                }
                if (x % 3 == 0 && z % 3 == 0)
                {
                    Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(world.getRandom());
                    DoorHingeSide hingeSide = DoorHingeSide.LEFT;
                    boolean open = false;

                    if (world.getRandom().nextInt(2) == 0) { hingeSide = DoorHingeSide.RIGHT; }
                    if (world.getRandom().nextInt(2) == 0) { open = true; }

                    this.setBlock(world, pos.offset(x,1,z), Blocks.WAXED_WEATHERED_COPPER_DOOR.defaultBlockState().setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER).setValue(DoorBlock.FACING, direction).setValue(DoorBlock.HINGE, hingeSide).setValue(DoorBlock.OPEN, open));
                    this.setBlock(world, pos.offset(x,2,z), Blocks.WAXED_WEATHERED_COPPER_DOOR.defaultBlockState().setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoorBlock.FACING, direction).setValue(DoorBlock.HINGE, hingeSide).setValue(DoorBlock.OPEN, open));
                }
            }
        }
    }

    public void generateChorusFruitRoom(WorldGenLevel world, BlockPos pos, int size, int roomHeight)
    {
        generateEmptyRoom(world,pos,size,roomHeight,false);

        for (int x = 1; x <= (size-1); x++)
        {
            for (int z = 1; z <= (size-1); z++)
            {
                BlockState floorBlock = Blocks.END_STONE_BRICKS.defaultBlockState();
                if (world.getRandom().nextInt(3) == 0) { floorBlock = Blocks.END_STONE.defaultBlockState(); }

                if (x == 1 || x == (size-1) || z == 1 || z == (size-1))
                {
                    this.setBlock(world, pos.offset(x,1,z), floorBlock);
                }
                else if (x % 3 == 0 && z % 3 == 0)
                {
                    this.setBlock(world, pos.offset(x,1,z), Blocks.END_STONE.defaultBlockState());
                    this.setBlock(world, pos.offset(x,2,z), Blocks.CHORUS_FLOWER.defaultBlockState());
                }
                else if (x % 3 == 0 || z % 3 == 0)
                {
                    this.setBlock(world, pos.offset(x,1,z), floorBlock);
                }
                else
                {
                    this.setBlock(world, pos.offset(x,1,z), Blocks.PURPUR_PILLAR.defaultBlockState());
                }
            }
        }
    }

    public void generatePoolRoom(WorldGenLevel world, BlockPos pos, int size, int roomHeight)
    {
        generateEmptyRoom(world,pos,size,roomHeight,true);

        //Water
        for (int x = 1; x <= (size-1); x++)
        {
            for (int z = 1; z <= (size-1); z++)
            {
                if (x == 1 || x == (size-1) || z == 1 || z == (size-1))
                {
                    this.setBlock(world, pos.offset(x,1,z), Blocks.WAXED_WEATHERED_COPPER_GRATE.defaultBlockState().setValue(WaterloggedTransparentBlock.WATERLOGGED, true));
                }
                else
                {
                    this.setBlock(world, pos.offset(x,1,z), Blocks.WATER.defaultBlockState());
                }
            }
        }
    }

    public void generateBackroom(WorldGenLevel world, BlockPos pos, int size, int roomHeight)
    {
        generateEmptyRoom(world,pos,size,roomHeight,false);

        //Outer Walls
        for (int x = 1; x <= (size-1); x++)
        {
            for (int z = 1; z <= (size-1); z++)
            {
                for (int y = 1; y <= roomHeight-1; y++)
                {
                    if (x == 1 || x == (size-1) || z == 1 || z == (size-1))
                    {
                        this.setBlock(world, pos.offset(x,y,z), Blocks.END_STONE.defaultBlockState());
                    }
                }
            }
        }

        //Floor
        for (int x = 2; x <= (size-2); x++)
        {
            for (int z = 2; z <= (size-2); z++)
            {
                this.setBlock(world, pos.offset(x,1,z), BOPBlocks.ALGAL_END_STONE.defaultBlockState());
            }
        }

        //Ceiling
        for (int x = 2; x <= (size-2); x++)
        {
            for (int z = 2; z <= (size-2); z++)
            {
                if (z % 3 == 0 && x != 2 && x != (size-2))
                {
                    if (x % 3 == 1)
                    {
                        this.setBlock(world, pos.offset(x,(roomHeight-1),z), Blocks.END_ROD.defaultBlockState().setValue(EndRodBlock.FACING, Direction.EAST));
                        this.setBlock(world, pos.offset(x,(roomHeight-2),z), BOPBlocks.WISPJELLY.defaultBlockState());
                    }
                    else if (x % 3 == 2)
                    {
                        this.setBlock(world, pos.offset(x,(roomHeight-1),z), Blocks.END_ROD.defaultBlockState().setValue(EndRodBlock.FACING, Direction.WEST));
                        this.setBlock(world, pos.offset(x,(roomHeight-2),z), BOPBlocks.WISPJELLY.defaultBlockState());
                    }
                    else
                    {
                        this.setBlock(world, pos.offset(x,(roomHeight-1),z), Blocks.END_STONE.defaultBlockState());
                        this.setBlock(world, pos.offset(x,(roomHeight-2),z), Blocks.END_STONE.defaultBlockState());
                    }

                }
                else
                {
                    this.setBlock(world, pos.offset(x,(roomHeight-1),z), Blocks.END_STONE.defaultBlockState());
                    this.setBlock(world, pos.offset(x,(roomHeight-2),z), Blocks.END_STONE.defaultBlockState());
                }
            }
        }

        //Inner Walls
        int wallLength = (size/3)-1;
        int wallDistance = (size/3)-2;

        for (int x = 0; x < wallLength; x++)
        {
            for (int y = 1; y <= roomHeight-3; y++)
            {
                this.setBlock(world, pos.offset(2+x,y,2+wallDistance), BOPBlocks.UNMAPPED_END_STONE.defaultBlockState());
                this.setBlock(world, pos.offset((size-2)-x,y,2+wallDistance), BOPBlocks.UNMAPPED_END_STONE.defaultBlockState());
            }

            for (int y = 1; y <= roomHeight-4; y++)
            {
                this.setBlock(world, pos.offset(2+x,y,(size-2)-wallDistance), BOPBlocks.UNMAPPED_END_STONE.defaultBlockState());
                this.setBlock(world, pos.offset((size-2)-x,y,(size-2)-wallDistance), BOPBlocks.UNMAPPED_END_STONE.defaultBlockState());
            }
        }
    }

    public boolean setBlock(WorldGenLevel world, BlockPos pos, BlockState state)
    {
        if (this.replace.matches(world, pos))
        {
            super.setBlock(world, pos, state);
            return true;
        }
        return false;
    }

    public boolean checkSpace(WorldGenLevel world, BlockPos pos, int size, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            for (int x = -1; x <= size+1; x++)
            {
                for (int z = -1; z <= size+1; z++)
                {
                    BlockPos pos1 = pos.offset(x, y, z);
                    if (pos1.getY() >= 255 || !this.replace.matches(world, pos1) || !respectsCutoff((WorldGenRegion)world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean respectsCutoff(WorldGenRegion region, BlockPos pos)
    {
        int i = SectionPos.blockToSectionCoord(pos.getX());
        int j = SectionPos.blockToSectionCoord(pos.getZ());
        ChunkPos chunkpos = region.getCenter();
        int k = Math.abs(chunkpos.x - i);
        int l = Math.abs(chunkpos.z - j);

        if (k <= region.generatingStep.blockStateWriteRadius() && l <= region.generatingStep.blockStateWriteRadius())
        {
            return true;
        }

        return false;
    }
}
