/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.block.ISustainsPlantType;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockBOPSand extends BlockFalling implements IBOPBlock, ISustainsPlantType
{

    // add properties
    public static enum SandType implements IStringSerializable
    {
        QUICKSAND;
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
    };
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", SandType.class);
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((SandType) state.getValue(VARIANT)).getName();
    }
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPSand() {
        
        super(Material.sand);
        
        // set some defaults
        this.setHardness(0.6F);
        this.setSoundType(SoundType.SAND);
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, SandType.QUICKSAND) );
        
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, SandType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((SandType) state.getValue(VARIANT)).ordinal();
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos)
    {   
        switch ((SandType) state.getValue(VARIANT))
        {            
            // no bounding box for quicksand - you're supposed to sink into it
            case QUICKSAND:
                return NULL_AABB;
            default:
                return super.getCollisionBoundingBox(state, world, pos);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        switch ((SandType) state.getValue(VARIANT))
        {            
            // quicksand behaves like being trapped in a spider web
            case QUICKSAND:
                entity.setInWeb();
                break;
            default:
                break;
        }
    }
    
    
    @Override
    public boolean canSustainPlantType(IBlockAccess world, BlockPos pos, EnumPlantType plantType)
    {  
        switch (plantType)
        {
            case Desert:
                return true;
            case Beach:
                return (
                        world.getBlockState(pos.east()).getMaterial() == Material.water ||
                        world.getBlockState(pos.west()).getMaterial() == Material.water ||
                        world.getBlockState(pos.north()).getMaterial() == Material.water ||
                        world.getBlockState(pos.south()).getMaterial() == Material.water
                        );
            // don't support anything else by default
            default:
                return false;
        }
    }
    
    
    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        return this.canSustainPlantType(world, pos, plantable.getPlantType(world, pos.offset(direction)));
    }
    
}