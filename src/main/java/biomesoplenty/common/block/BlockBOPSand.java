/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.block.ISustainsPlantType;
import biomesoplenty.common.item.ItemBOPBlock;

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
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((SandType) state.getValue(VARIANT)).getName();
    }

    public BlockBOPSand() {
        
        super(Material.sand);
        
        // set some defaults
        this.setHardness(0.6F);
        this.setStepSound(Block.soundTypeSand);
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
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {   
        switch ((SandType) state.getValue(VARIANT))
        {            
            // no bounding box for quicksand - you're supposed to sink into it
            case QUICKSAND:
                return null;
            default:
                return super.getCollisionBoundingBox(world, pos, state);
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
                        world.getBlockState(pos.east()).getBlock().getMaterial() == Material.water ||
                        world.getBlockState(pos.west()).getBlock().getMaterial() == Material.water ||
                        world.getBlockState(pos.north()).getBlock().getMaterial() == Material.water ||
                        world.getBlockState(pos.south()).getBlock().getMaterial() == Material.water
                        );
            // don't support anything else by default
            default:
                return false;
        }
    }
    
    
    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        return this.canSustainPlantType(world, pos, plantable.getPlantType(world, pos.offset(direction)));
    }
    
}