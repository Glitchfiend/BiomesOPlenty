/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.item.BOPItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPAsh extends BlockBOPGeneric
{
    public BlockBOPAsh()
    {
        super(Material.sand);

        this.setHardness(0.4F);
        this.setHarvestLevel("shovel", 0);
        this.setStepSound(SoundType.SAND);
    }

    // ash blocks are slightly lower
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos)
    {
        float heightOffset = 0.125F;
        return new AxisAlignedBB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), (double) (pos.getX() + 1), (double) ((float) (pos.getY() + 1) - heightOffset), (double) (pos.getZ() + 1));
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (entity instanceof EntityPlayer) {
            InventoryPlayer inventory = ((EntityPlayer)entity).inventory;
            if (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == BOPItems.wading_boots) {
                return;
            }
        }
        entity.motionX *= 0.8D;
        entity.motionZ *= 0.8D;
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side)
    {
        return (side == EnumFacing.UP);
    }
    
    // drop 4 balls of ash instead of one block 
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return BOPItems.ash;
    }
    @Override
    public int quantityDropped(Random random)
    {
        return 4;
    }

    // randomly show some smoke particles
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random)
    {
        if (random.nextInt(2) == 0)
        {
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + random.nextFloat(), pos.getY() + 1.1F, pos.getZ() + random.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }

}
