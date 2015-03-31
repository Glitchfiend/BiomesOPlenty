/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import biomesoplenty.api.block.BOPPlant;
import biomesoplenty.common.block.BlockBOPLogOld.LogType;

public class BlockBOPFlowerOld extends BOPPlant
{
    public static PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", FlowerType.class);

    public BlockBOPFlowerOld()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, FlowerType.CLOVER));
    }

    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        FlowerType type = (FlowerType) state.getValue(VARIANT_PROP);

        switch (type)
        {
            case GLOWFLOWER:
                return 9;

            case ENDERLOTUS:
                return 5;

            case BURNING_BLOSSOM:
                return 9;

            default:
                return super.getLightValue();
        }
    }

    // TODO: Make enderlotus require spectral moss
    // TODO: Make bromeliads require hard dirt, hardened clay or sand
    // TODO: Make the burning blossom require netherrack or overgrown netherrack

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileentity)
    {
        super.harvestBlock(world, player, pos, state, tileentity);

        FlowerType type = (FlowerType) state.getValue(VARIANT_PROP);

        if (player.getCurrentEquippedItem() == null || !(player.getCurrentEquippedItem().getItem() instanceof ItemShears))
        {
            switch (type)
            {
                case DEATHBLOOM:
                    player.addPotionEffect(new PotionEffect(Potion.wither.id, 300));
                    break;

                case BURNING_BLOSSOM:
                    player.setFire(5);
                    break;
            }
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        FlowerType type = (FlowerType) state.getValue(VARIANT_PROP);

        if (type == FlowerType.BURNING_BLOSSOM)
        {
            entity.setFire(1);
        }
        else
            if (entity instanceof EntityLivingBase && type == FlowerType.DEATHBLOOM)
            {
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
            }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT_PROP, FlowerType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = ((FlowerType) state.getValue(VARIANT_PROP)).ordinal();

        return meta;
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
        return ((FlowerType) state.getValue(VARIANT_PROP)).getName();
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);

        switch ((FlowerType) state.getValue(VARIANT_PROP))
        {
            case CLOVER:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
                break;

            case ORANGE_COSMOS:
                this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.8F, 0.7F);
                break;

            case PINK_DAFFODIL:
                this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
                break;

            case WHITE_ANEMONE:
                this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.5F, 0.7F);
                break;

            case DANDELION:
                this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
                break;

            default:
                this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
                break;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        FlowerType type = (FlowerType) state.getValue(VARIANT_PROP);

        if (type == FlowerType.DEATHBLOOM)
        {
            if (rand.nextInt(4) != 0)
                world.spawnParticle(EnumParticleTypes.TOWN_AURA, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
            if (rand.nextInt(4) == 0)
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
        }
        else
            if (type == FlowerType.BURNING_BLOSSOM)
            {
                if (rand.nextInt(2) == 0)
                    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
                if (rand.nextInt(4) == 0)
                    world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
            }
    }

    // TODO: Readd eyebulb in as a seperate block
    // TODO: Readd dandelion blowing
    public static enum FlowerType implements IStringSerializable
    {
        CLOVER, SWAMPFLOWER, DEATHBLOOM, GLOWFLOWER, BLUE_HYDRANGEA, ORANGE_COSMOS, PINK_DAFFODIL, WILDFLOWER, VIOLET, WHITE_ANEMONE, ENDERLOTUS, BROMELIAD, DANDELION, PINK_HIBISCUS, LILY_OF_THE_VALLEY, BURNING_BLOSSOM;

        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }

        @Override
        public String toString()
        {
            return getName();
        }
    }
}
