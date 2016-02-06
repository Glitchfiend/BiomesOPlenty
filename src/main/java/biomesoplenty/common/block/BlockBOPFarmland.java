package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.ArrayList;
import java.util.Random;

public class BlockBOPFarmland extends BlockFarmland implements IBOPBlock
{
    public static enum BOPFarmlandType implements IStringSerializable
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
    }

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BOPFarmlandType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { MOISTURE, VARIANT });}

    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }

    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }

    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] { VARIANT }; }

    @Override
    public IProperty[] getNonRenderingProperties() { return null; }

    @Override
    public String getStateName(IBlockState state) {
        BOPFarmlandType farmlandType = (BOPFarmlandType)state.getValue(VARIANT);

        return farmlandType + "_farmland";
    }

    public BlockBOPFarmland()
    {
        super();
        this.setHardness(0.6F);
        this.setHarvestLevel("shovel", 0);
        this.setStepSound(soundTypeGravel);
        this.setDefaultState(this.blockState.getBaseState().withProperty(MOISTURE, Integer.valueOf(0)).withProperty(VARIANT, BOPFarmlandType.LOAMY));
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(MOISTURE, Integer.valueOf(meta & 7)).withProperty(VARIANT, BOPFarmlandType.values()[Math.min(2, meta & 7)]);
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((BOPFarmlandType) state.getValue(VARIANT)).ordinal();
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random)
    {
        int i = (state.getValue(MOISTURE)).intValue();

        if (!this.hasWater(world, pos) && !world.canLightningStrike(pos.up()))
        {
            if (i > 0)
            {
                world.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(i - 1)), 2);
            }
            else if (!this.hasCrops(world, pos))
            {
                world.setBlockState(pos, BOPBlocks.dirt.getDefaultState());
            }
        }
        else if (i < 7)
        {
            world.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(0)), 2);
        }
    }

    private boolean hasWater(World world, BlockPos pos)
    {
        for (BlockPos.MutableBlockPos mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)))
        {
            if (world.getBlockState(mutableblockpos).getBlock().getMaterial() == Material.water)
            {
                return true;
            }
        }

        return false;
    }

    private boolean hasCrops(World world, BlockPos pos)
    {
        Block block = world.getBlockState(pos.up()).getBlock();
        return block instanceof IPlantable && canSustainPlant(world, pos, EnumFacing.UP, (IPlantable)block);
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        super.onNeighborBlockChange(world, pos, state, neighborBlock);

        if (world.getBlockState(pos.up()).getBlock().getMaterial().isSolid())
        {
            world.setBlockState(pos, BOPBlocks.dirt.getDefaultState());
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

                world.setBlockState(pos, BOPBlocks.dirt.getDefaultState()); //TODO Check, was setBlock(pos, BOPBlocks.dirt, (world.getBlockMetadata(pos) / 2) * 2, 2);
            }
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(BOPBlocks.dirt, 1, (state.getBlock().getMetaFromState(state) / 2) * 2)); //TODO Check
        return ret;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(BOPBlocks.dirt, 1, this.getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
    {
        EnumPlantType plantType = plantable.getPlantType(world, pos.up());

        switch (plantType)
        {
            case Crop:
                return true;
            default:
                return super.canSustainPlant(world, pos, direction, plantable);
        }
    }
}