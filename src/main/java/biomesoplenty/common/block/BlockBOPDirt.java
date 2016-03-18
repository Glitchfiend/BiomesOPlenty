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
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.block.ISustainsPlantType;
import biomesoplenty.common.item.ItemBOPBlock;
import biomesoplenty.common.util.block.VariantPagingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockBOPDirt extends Block implements IBOPBlock, ISustainsPlantType
{
    // add properties
    public static enum BOPDirtType implements IStringSerializable, VariantPagingHelper.IPagedVariants
    {
        LOAMY, SANDY, SILTY;
        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
    };
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BOPDirtType.class);
    public static final PropertyBool COARSE = PropertyBool.create("coarse");
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { COARSE, VARIANT });}

    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {COARSE, VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return (Boolean.TRUE.equals(state.getValue(COARSE)) ? "coarse_" : "") + ((BOPDirtType) state.getValue(VARIANT)).getName() + "_dirt";
    }
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPDirt() {

        super(Material.ground);

        // set some defaults
        this.setTickRandomly(true);
        this.setHardness(0.5F);
        this.setHarvestLevel("shovel", 0);
        this.setSoundType(SoundType.GROUND);
        this.setDefaultState( this.blockState.getBaseState().withProperty(COARSE, Boolean.valueOf(false)).withProperty(VARIANT, BOPDirtType.LOAMY) );

    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // both variant and coarseness saved in meta, first bit coarseness, other bits variant
        return this.getDefaultState().withProperty(COARSE, Boolean.valueOf((meta & 8) > 0)).withProperty(VARIANT, BOPDirtType.values()[Math.min(2, meta & 7)]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // both variant and coarseness saved in meta, first bit coarseness, other bits variant
        return (Boolean.TRUE.equals(state.getValue(COARSE)) ? 8 : 0) | ((BOPDirtType) state.getValue(VARIANT)).ordinal();
    }

    // our blocks usually drop their current state as opposed to a single 'default' state
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }

    @Override
    public boolean canSustainPlantType(IBlockAccess world, BlockPos pos, EnumPlantType plantType)
    {
        switch (plantType)
        {
            // support desert, plains and cave plants
            case Desert: case Plains: case Cave:
                return true;
            // support beach plants if there's water alongside
            case Beach:
                return (
                    world.getBlockState(pos.east()).getMaterial() == Material.water ||
                    world.getBlockState(pos.west()).getMaterial() == Material.water ||
                    world.getBlockState(pos.north()).getMaterial() == Material.water ||
                    world.getBlockState(pos.south()).getMaterial() == Material.water
                );
             // don't support nether plants, water plants, or crops (require farmland), or anything else by default
            default:
                return false;
        }
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        return this.canSustainPlantType(world, pos, plantable.getPlantType(world, pos.offset(direction)));
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        IBlockState grassState = getGrassBlockState(state);

        if (grassState != null)
        {
            pullGrassFromNeighbors(world, pos, grassState, rand, 4, 1, 3, 1);
        }
    }

    // BOPGrass variants spread randomly to BOPDirt on the grass's updateTick
    // However, vanilla grass does not.  This function fixes this by 'pulling' grass from nearby vanilla grass blocks at the same rate as it would spread to vanilla dirt
    public void pullGrassFromNeighbors(World world, BlockPos pos, IBlockState grassState, Random rand, int tries, int xzSpread, int downSpread, int upSpread)
    {
        IBlockState upState = world.getBlockState(pos.up());
        // if there's not enough light then there's no chance of this block becoming grassy
        if (world.getLightFromNeighbors(pos.up()) < 4 || upState.getBlock().getLightOpacity(upState) > 2) {return;}

        int numNearbyGrassSpreadingBlocks = 0;
        BlockPos pos1;
        for (int dy = -downSpread; dy <= upSpread; dy++)
        {
            for (int dx = -xzSpread; dx <= xzSpread; dx++)
            {
                for (int dz = -xzSpread; dz <= xzSpread; dz++)
                {
                    // count only vanilla grass blocks with enough light (BOP Grass 'pushes' itself onto dirt, no need to 'pull' it)
                    pos1 = pos.add(dx, dy, dz);
                    if (world.getBlockState(pos1).getBlock() == Blocks.grass && world.getLightFromNeighbors(pos1.up()) >= 9)
                    {
                        numNearbyGrassSpreadingBlocks++;
                    }
                }
            }
        }
        if (numNearbyGrassSpreadingBlocks == 0) {return;}

        // each grass block gets 4 tries to spread grass, chance of this block being chosen each time is 1 / volume of blocks close enough
        // overall chance of spread = 1 - chance of not spreading
        int vol = (xzSpread * 2 + 1) * (xzSpread * 2 + 1) * (upSpread + downSpread + 1);
        double chanceOfSpread = 1.0D - Math.pow(1.0D - 1.0D / vol, tries * numNearbyGrassSpreadingBlocks);
        if (rand.nextDouble() < chanceOfSpread)
        {
            world.setBlockState(pos, grassState);
        }
    }


    // get the blockstate which corresponds to the type of grass which grows on this dirt
    public static IBlockState getGrassBlockState(IBlockState state)
    {
        // no grass grows on coarse dirt
        if (Boolean.TRUE.equals(state.getValue(COARSE)))
        {
            return null;
        }
        switch ((BOPDirtType) state.getValue(VARIANT))
        {
            case LOAMY:
                return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.LOAMY);
            case SANDY:
                return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SANDY);
            case SILTY:
                return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SILTY);
            default:
                // return vanilla grass as a backup
                return Blocks.grass.getDefaultState();
        }
    }

    public Block getGrassBlock(IBlockState state)
    {
        return getGrassBlockState(state).getBlock();
    }

    public int getGrassBlockMeta(IBlockState state)
    {
        return this.getGrassBlock(state).getMetaFromState(getGrassBlockState(state));
    }

}
