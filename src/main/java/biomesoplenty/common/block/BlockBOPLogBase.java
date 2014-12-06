/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.common.util.inventory.CreativeTabBOP;

public abstract class BlockBOPLogBase extends BOPBlock
{
    public static final PropertyEnum AXIS_PROP = PropertyEnum.create("axis", EnumFacing.Axis.class);
    
    protected BlockBOPLogBase()
    {
    	super(Material.wood);
    	
		this.setHarvestLevel("axe", 0);
    	
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypeWood);
    }
    
    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int metadata, EntityLivingBase entity)
    {
        return super.onBlockPlaced(world, pos, side, hitX, hitY, hitZ, metadata, entity).withProperty(AXIS_PROP, side.getAxis());
    }
}
