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
import biomesoplenty.common.item.ItemBOPBlock;
import biomesoplenty.common.util.block.VariantPagingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockBOPFarmland extends BlockFarmland implements IBOPBlock
{
    public static VariantPagingHelper<BlockBOPFarmland, BlockBOPDirt.BOPDirtType> paging = new VariantPagingHelper<BlockBOPFarmland, BlockBOPDirt.BOPDirtType>(2, BlockBOPDirt.BOPDirtType.class);

    private static IProperty currentVariantProperty;

    public static void createAllPages()
    {
        int numPages = paging.getNumPages();
        for (int i = 0; i < numPages; ++i)
        {
            currentVariantProperty = paging.getVariantProperty(i);
            paging.addBlock(i, new BlockBOPFarmland());
        }
    }

    public IProperty variantProperty;

    @Override
    protected BlockStateContainer createBlockState()
    {
        this.variantProperty = currentVariantProperty;
        return new BlockStateContainer(this, new IProperty[] { MOISTURE, this.variantProperty });
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }

    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] { this.variantProperty }; }

    @Override
    public IProperty[] getNonRenderingProperties() { return null; }

    @Override
    public String getStateName(IBlockState state)
    {
        return ((BlockBOPDirt.BOPDirtType) state.getValue(this.variantProperty)).getName() + "_farmland";
    }
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPFarmland()
    {
        super();
        this.setTickRandomly(true);
        //this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setLightOpacity(255);
        this.useNeighborBrightness = true;
        this.setHardness(0.6F);
        this.setHarvestLevel("shovel", 0);
        this.setSoundType(SoundType.GROUND);
        this.setDefaultState(this.blockState.getBaseState().withProperty(MOISTURE, Integer.valueOf(0)));
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(this.variantProperty, paging.getVariant(this, meta & 1)).withProperty(MOISTURE, Integer.valueOf(meta >> 1));
    }

    public int getMetaFromState(IBlockState state)
    {
        BlockBOPDirt.BOPDirtType dirt = (BlockBOPDirt.BOPDirtType) state.getValue(this.variantProperty);
        int meta = paging.getIndex(dirt);
        meta |= state.getValue(MOISTURE) << 1;
        return meta;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random)
    {
        int i = (state.getValue(MOISTURE)).intValue();

        if (!this.hasWater(world, pos) && !world.isRainingAt(pos.up()))
        {
            if (i > 0)
            {
                world.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(i - 1)), 2);
            }
            else if (!this.hasCrops(world, pos))
            {
                world.setBlockState(pos, this.getDirtBlockState(world.getBlockState(pos)));
            }
        }
        else if (i < 7)
        {
            world.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(7)), 2);
        }
    }

    private boolean hasWater(World world, BlockPos pos)
    {
        for (BlockPos.MutableBlockPos mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)))
        {
            if (world.getBlockState(mutableblockpos).getMaterial() == Material.water)
            {
                return true;
            }
        }

        return false;
    }

    private boolean hasCrops(World world, BlockPos pos)
    {
        Block block = world.getBlockState(pos.up()).getBlock();
        return block instanceof IPlantable && canSustainPlant(world.getBlockState(pos.up()), world, pos, EnumFacing.UP, (IPlantable)block);
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        if (world.getBlockState(pos.up()).getMaterial().isSolid())
        {
            world.setBlockState(pos, this.getDirtBlockState(world.getBlockState(pos)));
        }
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
    {
        if (entity instanceof EntityLivingBase)
        {
            if (!world.isRemote && world.rand.nextFloat() < fallDistance - 0.5F)
            {
                if (!(entity instanceof EntityPlayer) && !world.getGameRules().getBoolean("mobGriefing"))
                {
                    return;
                }

                world.setBlockState(pos, this.getDirtBlockState(world.getBlockState(pos)));
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BOPBlocks.dirt);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return BOPBlocks.dirt.getMetaFromState(this.getDirtBlockState(state));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(BOPBlocks.dirt, 1, BOPBlocks.dirt.getMetaFromState(this.getDirtBlockState(world.getBlockState(pos))));
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        EnumPlantType plantType = plantable.getPlantType(world, pos.up());

        switch (plantType)
        {
            case Crop:
                return true;
            case Plains:
            	return true;
            default:
                return super.canSustainPlant(state, world, pos, direction, plantable);
        }
    }

    public IBlockState getDirtBlockState(IBlockState state)
    {
        switch ((BlockBOPDirt.BOPDirtType) state.getValue(this.variantProperty))
        {
            case LOAMY:
                return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.LOAMY);
            case SANDY:
                return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY);
            case SILTY:
                return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SILTY);
            default:
            return Blocks.dirt.getStateFromMeta(BlockDirt.DirtType.DIRT.getMetadata());
        }
    }
}