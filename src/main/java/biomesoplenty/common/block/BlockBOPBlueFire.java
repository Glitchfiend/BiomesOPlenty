/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import biomesoplenty.api.item.BOPItems;
import biomesoplenty.api.particle.BOPParticleTypes;
import biomesoplenty.api.potion.BOPPotions;
import biomesoplenty.common.item.ItemBOPBlock;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPBlueFire extends Block implements IBOPBlock
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
    @SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor() { return null; }
    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPBlueFire()
    {
        super(Material.FIRE);
        this.setHardness(0.0F);
        this.setLightLevel(0.8F);
        this.setSoundType(SoundType.CLOTH);
        this.disableStats();
    }
	
    @Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
	    return NULL_AABB;
	}

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(BOPPotions.curse, 200));
            
            if (world.rand.nextInt(5) == 0)
            {
            	double d0 = (double)pos.getX() + world.rand.nextDouble();
	            double d1 = (double)pos.getY() + world.rand.nextDouble() * 0.5D + 0.5D;
	            double d2 = (double)pos.getZ() + world.rand.nextDouble();
	            BiomesOPlenty.proxy.spawnParticle(BOPParticleTypes.CURSE, world, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
	    return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
	    return false;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
	    return 0;
	}
	
	/*@Override
	public boolean isCollidable()
	{
	    return false;
	}*/
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
	    return worldIn.getBlockState(pos.down()).isTopSolid();
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
	    if (!worldIn.getBlockState(pos.down()).isTopSolid())
	    {
	        worldIn.setBlockToAir(pos);
	    }
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
	    if (worldIn.provider.getDimensionType().getId() > 0)
	    {
	        if (!worldIn.getBlockState(pos.down()).isTopSolid())
	        {
	            worldIn.setBlockToAir(pos);
	        }
	        else
	        {
	            worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(10));
	        }
	    }
	}
	
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
    	return null;
    }
	
	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
	    return MapColor.LIGHT_BLUE;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
	    if (rand.nextInt(32) == 0)
	    {
	        worldIn.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
	    }
	    
	    if (rand.nextInt(5) == 0)
        {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double)pos.getZ() + rand.nextDouble();
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
	    
	    if (rand.nextInt(15) == 0)
        {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double)pos.getZ() + rand.nextDouble();
            BiomesOPlenty.proxy.spawnParticle(BOPParticleTypes.CURSE, worldIn, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
        }
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
	    return BlockRenderLayer.CUTOUT;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
	    return BlockFaceShape.UNDEFINED;
	}
}