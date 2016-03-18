/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPFlesh extends Block implements IBOPBlock
{
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {return "";}
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPFlesh() {
        super(Material.sponge);
        this.setHardness(0.4F);
        this.setSoundType(SoundType.GROUND);
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos)
    {
        // 7/8 height
        float heightOffset = 0.125F;
        return new AxisAlignedBB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), (double) (pos.getX() + 1), (double) ((float) (pos.getY() + 1) - heightOffset), (double) (pos.getZ() + 1));
    }
   
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        // randomly throw up some particles so it looks like the flesh is bubbling
        super.randomDisplayTick(state, world, pos, rand);
        if (rand.nextInt(4)==0)
        {           
            world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D, new int[] {Block.getStateId(state)});
        }
        if (rand.nextInt(12)==0)
        {
            world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D, new int[] {Block.getStateId(state)});
        }
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
        
        // slow you slightly
        entity.motionX *= 0.9D;
        entity.motionZ *= 0.9D;

    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return BOPItems.fleshchunk;
    }
    
    
    @Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(3);
    }

    
}