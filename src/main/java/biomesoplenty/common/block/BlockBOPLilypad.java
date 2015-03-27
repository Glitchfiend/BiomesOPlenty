/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.List;

import biomesoplenty.api.block.BOPPlant;
import biomesoplenty.common.item.ItemBOPLilypad;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPLilypad extends BOPPlant implements IPlantable
{
    
    public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", LilypadType.class);
    
    public BlockBOPLilypad()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, LilypadType.MEDIUM));
        float f = 0.5F;
        float f1 = 0.015625F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }
    
    // need to use a custom item class because of the unique way lilies are placed
    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return ItemBOPLilypad.class;
    }
    
    // lilies should always be in the centre of the water block
    @Override
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.NONE;
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // only one property to worry about, the variant, so just map [0 => MEDIUM, 1 => SMALL, 2 => TINY]
        return this.getDefaultState().withProperty(VARIANT_PROP, LilypadType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // only one property to worry about, the variant, so just map [0 => MEDIUM, 1 => SMALL, 2 => TINY]
        return ((LilypadType) state.getValue(VARIANT_PROP)).ordinal();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT_PROP });
    }

    @Override
    public IProperty[] getPresetProperties()
    {
        return new IProperty[] { VARIANT_PROP };
    }

    @Override
    public String getStateName(IBlockState state, boolean fullName)
    {
        return ((LilypadType) state.getValue(VARIANT_PROP)).getName();
    }

    // copied from vanilla BlockLilyPad
    // boats can go through lily pads
    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
    {
        if (collidingEntity == null || !(collidingEntity instanceof EntityBoat))
        {
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }
    }

    // copied from vanilla BlockLilyPad
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return new AxisAlignedBB((double)pos.getX() + this.minX, (double)pos.getY() + this.minY, (double)pos.getZ() + this.minZ, (double)pos.getX() + this.maxX, (double)pos.getY() + this.maxY, (double)pos.getZ() + this.maxZ);
    }

    // copied from vanilla BlockLilyPad
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 7455580;
    }

    // copied from vanilla BlockLilyPad
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return 7455580;
    }

    // copied from vanilla BlockLilyPad
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return 2129968;
    }
    
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        
        Boolean y1 = super.canPlaceBlockAt(worldIn, pos);
        Boolean y2 = worldIn.getBlockState(pos.down()).getBlock().canSustainPlant(worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
        
        return y1 && y2;
        //return super.canPlaceBlockAt(worldIn, pos) && worldIn.getBlockState(pos.down()).getBlock().canSustainPlant(worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (pos.getY() >= 0 && pos.getY() < 256)
        {
            IBlockState iblockstate1 = worldIn.getBlockState(pos.down());
            return iblockstate1.getBlock().getMaterial() == Material.water && ((Integer)iblockstate1.getValue(BlockLiquid.LEVEL)).intValue() == 0;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    // This will stop it being placed on anything except water
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Water;
    }
    
    
    // enum representing the 3 variants of lily
    public static enum LilypadType implements IStringSerializable
    {
        MEDIUM, SMALL, TINY;

        @Override
        public String getName()
        {
            return "lily_" + this.name().toLowerCase();
        }

        @Override
        public String toString()
        {
            return getName();
        }
    }
    

    // TODO: copied from BlockBush - not sure exactly what this does... investigate further - possibly put into base class
    @Override
    public IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return getDefaultState();
        return state;
    }
    
}