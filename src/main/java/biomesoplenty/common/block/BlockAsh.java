/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.common.util.inventory.CreativeTabBOP;

//TODO: Commented methods and calls
public class BlockAsh extends BOPBlock
{
    public BlockAsh()
    {
        super(Material.sand);

        this.setHardness(0.4F);
        // this.setHarvestLevel("shovel", 0);
        this.setStepSound(Block.soundTypeSand);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        float heightOffset = 0.125F;
        return new AxisAlignedBB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), (double) (pos.getX() + 1), (double) ((float) (pos.getY() + 1) - heightOffset), (double) (pos.getZ() + 1));
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        /*
         * if (entity instanceof EntityPlayer) { InventoryPlayer inventory =
         * ((EntityPlayer)entity).inventory;
         * 
         * if (inventory.armorInventory[0] != null &&
         * inventory.armorInventory[0].getItem() == BOPCItems.wadingBoots) {
         * return; } }
         */

        entity.motionX *= 0.4D;
        entity.motionZ *= 0.4D;
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side)
    {
        if (side == EnumFacing.UP)
        {
            return true;
        }
        return false;
    }

    /*
     * @Override public Item getItemDropped(int metadata, Random random, int
     * fortune) { return BOPCItems.misc; }
     * 
     * @Override public int damageDropped(int meta) { return 1; }
     */

    @Override
    public int quantityDropped(Random random)
    {
        return 4;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random random)
    {
        if (random.nextInt(2) == 0)
        {
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + random.nextFloat(), pos.getY() + 1.1F, pos.getZ() + random.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }

}
