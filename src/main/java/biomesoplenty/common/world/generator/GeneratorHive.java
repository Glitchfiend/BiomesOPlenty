/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.generator;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.common.block.BlockBOPHive;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Random;

public class GeneratorHive extends GeneratorReplacing
{
    public static class Builder extends GeneratorReplacing.InnerBuilder<GeneratorHive.Builder, GeneratorHive> implements IGeneratorBuilder<GeneratorHive>
    {
        protected int halfHeight;
        protected int maxRadius;
        protected int layerSize;
        protected int bottomExtra;
        protected float emptyChance;

        public GeneratorHive.Builder halfHeight(int a) {this.halfHeight = a; return this.self();}
        public GeneratorHive.Builder maxRadius(int a) {this.maxRadius = a; return this.self();}
        public GeneratorHive.Builder layerSize(int a) {this.layerSize = a; return this.self();}
        public GeneratorHive.Builder bottomExtra(int a) {this.bottomExtra = a; return this.self();}
        public GeneratorHive.Builder emptyChance(float a) {this.emptyChance = a; return this.self();}

        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQuery.buildAnd().states(Blocks.NETHERRACK.getDefaultState()).withAirBelow().create();;
            this.replace = new BlockQuery.BlockQueryMaterial(Material.AIR);
            this.with = null;
            this.scatterYMethod = GeneratorUtils.ScatterYMethod.NETHER_ROOF;
            this.halfHeight = 7;
            this.maxRadius = 9;
            this.layerSize = 3;
            this.bottomExtra = 4;
            this.emptyChance = 0.25F;
        }

        @Override
        public GeneratorHive create()
        {
            return new GeneratorHive(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.halfHeight, this.maxRadius, this.layerSize, this.bottomExtra, this.emptyChance);
        }
    }

    protected int halfHeight;
    protected int maxRadius;
    protected int layerSize;
    protected int bottomExtra;
    protected float emptyChance;

    public GeneratorHive(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, GeneratorUtils.ScatterYMethod scatterYMethod, int halfHeight, int maxRadius, int layerSize, int bottomExtra, float emptyChance)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.halfHeight = halfHeight;
        this.maxRadius = maxRadius;
        this.layerSize = layerSize;
        this.bottomExtra = bottomExtra;
        this.emptyChance = emptyChance;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        // check that there's room and if the blocks below are suitable
        if (!this.canPlaceHere(world, position, this.halfHeight * 2 + this.bottomExtra, this.maxRadius)) {return false;}

        boolean empty = rand.nextFloat() <= this.emptyChance;

        // create the top and bottom halves of the hive, and a little bit extra on the bottom for the sake of looking
        // slightly more like a hive
        for (int yOffset = this.halfHeight; yOffset >= -this.halfHeight - this.bottomExtra; yOffset--)
        {
            // y is already negative, so add it rather than subtract
            int radius = this.maxRadius + (yOffset / this.layerSize) * (yOffset >= 0 ? -1 : 1);

            for (int xOffset = -radius; xOffset <= radius; xOffset++)
            {
                for (int zOffset = -radius; zOffset <= radius; zOffset++)
                {
                    int x2_z2 = xOffset * xOffset + zOffset * zOffset;

                    // used to determine where to fill with honey
                    boolean bottomHalf = yOffset <= 0;

                    // check to fill the top and bottom of the two layers
                    boolean outerCap = yOffset == -this.halfHeight - this.bottomExtra || yOffset == this.halfHeight;
                    boolean innerCap = yOffset == -this.halfHeight - this.bottomExtra + 1 || yOffset == this.halfHeight - 1;

                    // create a circular outline for the hive. the bottom and top layers should be filled.
                    // add a bit extra (1) to make the circles nicer too

                    // inner layer. inset by one block
                    if (x2_z2 <= (radius - 1) * (radius - 1) + 1 && (x2_z2 >= (radius - 2) * (radius - 2) || innerCap))
                    {
                        IBlockState honeyComb = BOPBlocks.hive.getDefaultState().withProperty(BlockBOPHive.VARIANT, BlockBOPHive.HiveType.EMPTY_HONEYCOMB);
                        float f = rand.nextFloat();

                        if (!empty && f <= 0.95)
                        {
                            honeyComb = BOPBlocks.hive.getDefaultState().withProperty(BlockBOPHive.VARIANT, BlockBOPHive.HiveType.HONEYCOMB);

                            // if on the bottom half of the hive bias more towards filled honeycomb.
                            // the rest of the hive can still have filled blocks though
                            if (f <= 0.2 || (f <= 0.65 && bottomHalf))
                            {
                                honeyComb = BOPBlocks.hive.getDefaultState().withProperty(BlockBOPHive.VARIANT, BlockBOPHive.HiveType.FILLED_HONEYCOMB);
                            }
                        }
                        else if (empty && f <= 0.2)
                        {
                            honeyComb = Blocks.AIR.getDefaultState();
                        }

                        // offset so we're placing the hive underneath the initial y coordinate
                        world.setBlockState(position.add(xOffset, yOffset - this.halfHeight, zOffset), honeyComb);
                    }

                    // inner fill
                    if (!empty && bottomHalf && x2_z2 <= (radius - 2) * (radius - 2) + 1)
                    {
                        // fill the centre of the hive with honey blocks honey
                        IBlockState fillBlock = yOffset == 0 ?  BOPBlocks.honey_block.getDefaultState() : BOPBlocks.honey.getDefaultState();
                        world.setBlockState(position.add(xOffset, yOffset - this.halfHeight, zOffset), fillBlock);
                    }

                    // place a wasp spawner in the middle of the hive
                    if (!empty && yOffset == 0 && xOffset == 0 && zOffset == 0)
                    {
                        BlockPos spawnerPos = position.add(xOffset, yOffset - this.halfHeight, zOffset);

                        world.setBlockState(spawnerPos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
                        TileEntity tileentity = world.getTileEntity(spawnerPos);

                        if (tileentity instanceof TileEntityMobSpawner)
                        {
                            MobSpawnerBaseLogic spawnerLogic = ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic();

                            NBTTagCompound spawnerConfig = new NBTTagCompound();
                            spawnerConfig.setShort("MinSpawnDelay", (short)100);
                            spawnerConfig.setShort("MaxSpawnDelay", (short)400);
                            spawnerConfig.setShort("SpawnCount", (short)6);
                            spawnerConfig.setShort("MaxNearbyEntities", (short)16);
                            spawnerConfig.setShort("RequiredPlayerRange", (short)24);

                            spawnerLogic.readFromNBT(spawnerConfig);
                            spawnerLogic.setEntityId(new ResourceLocation(BiomesOPlenty.MOD_ID, "wasp"));
                        }
                        else
                        {
                            BiomesOPlenty.logger.error("Failed to fetch mob spawner entity at ({}, {}, {})", new Object[] {Integer.valueOf(spawnerPos.getX()), Integer.valueOf(spawnerPos.getY()), Integer.valueOf(spawnerPos.getZ())});
                        }
                    }

                    // outer layer
                    if (x2_z2 <= radius * radius + 1 && (x2_z2 >= (radius - 1) * (radius - 1) || outerCap))
                    {
                        // offset so we're placing the hive underneath the initial y coordinate
                        world.setBlockState(position.add(xOffset, yOffset - halfHeight, zOffset), BOPBlocks.hive.getDefaultState());
                    }
                }
            }
        }

        return true;
    }

    public boolean canPlaceHere(World world, BlockPos pos, int height, int radius)
    {


        if (world.getBiome(pos) == Biomes.HELL)
        {
            System.out.println(world.getBlockState(pos).getBlock() == Blocks.NETHERRACK);
        }

        if (pos.getY() <= 1 || pos.getY() > 255)
        {
            return false;
        }
        for (int y = pos.getY(); y >= pos.getY() - height; --y)
        {
            for (int x = pos.getX() - radius; x <= pos.getX() + radius; ++x)
            {
                for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z)
                {
                    if (y == pos.getY() && !this.placeOn.matches(world, new BlockPos(x, y + 1, z)))
                    {
                        return false;
                    }

                    if (!this.replace.matches(world, new BlockPos(x, y, z)))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.placeOn = conf.getBlockPosQuery("placeUnder", this.placeOn);
        this.replace = conf.getBlockPosQuery("replace", this.replace);
        this.scatterYMethod = conf.getEnum("scatterYMethod", this.scatterYMethod, GeneratorUtils.ScatterYMethod.class);

        this.halfHeight = conf.getInt("halfHeight", this.halfHeight);
        this.maxRadius = conf.getInt("maxRadius", this.maxRadius);
        this.layerSize = conf.getInt("layerSize", this.layerSize);
        this.bottomExtra = conf.getInt("bottomExtra", this.bottomExtra);
        this.emptyChance = conf.getFloat("emptyChance", this.emptyChance);
    }
}
