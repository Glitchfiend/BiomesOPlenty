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
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.api.block.BOPPlant;
import biomesoplenty.api.block.IBOPVariant;
import biomesoplenty.common.util.inventory.CreativeTabBOP;

public class BlockBOPFlower extends BOPPlant
{
	public static PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", FlowerType.class);

    public BlockBOPFlower()
    {
	    super(VARIANT_PROP);
    }
    
    //TODO: Add light for glowflowers (Requires Forge)

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT_PROP });
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
    	IBlockState state = world.getBlockState(pos);
    	
    	switch ((FlowerType)state.getValue(VARIANT_PROP))
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
    			
    		default:
    			this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
    			break;
    	}
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
    	return Block.EnumOffsetType.XZ;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
    	if ((FlowerType)state.getValue(VARIANT_PROP) == FlowerType.DEATHBLOOM)
    	{
    		if (rand.nextInt(4) != 0) world.spawnParticle(EnumParticleTypes.TOWN_AURA, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
    		if (rand.nextInt(4) == 0) world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
    	}
    }
	
	public static enum FlowerType implements IBOPVariant
	{
		CLOVER,
		SWAMPFLOWER,
		DEATHBLOOM,
		GLOWFLOWER,
		BLUE_HYDRANGEA,
		ORANGE_COSMOS,
		PINK_DAFFODIL,
		WILDFLOWER,
		VIOLET,
		WHITE_ANEMONE;

        @Override
        public String getBaseName()
        {
	        return null;
        }
		
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
        
        @Override
        public int getDefaultMetadata()
        {
	        return this.ordinal();
        }
	}
}
