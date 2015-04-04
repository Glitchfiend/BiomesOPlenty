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
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.item.ItemBOPBlock;

public class BlockHive extends Block implements IBOPBlock
{
    
    // add properties
    public static enum HiveType implements IStringSerializable
    {
        HIVE, HONEYCOMB, EMPTY_HONEYCOMB, FILLED_HONEYCOMB;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", HiveType.class);
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
    public IProperty[] getRenderProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((HiveType) state.getValue(VARIANT)).getName() + "_block";
    }
    
    public BlockHive()
    {
        super(Material.wood);
                
        // set some defaults
        this.setHardness(0.5F);
        this.setStepSound(Block.soundTypeGrass);
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, HiveType.HIVE) );
    }

    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, HiveType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((HiveType) state.getValue(VARIANT)).ordinal();
    }
    
    // The 3 functions below, getItemDropped, quantityDropped and damageDropped work together to determine the drops
    // HIVE:                1 hive block
    // HONEYCOMB:           1-4 x honeycomb item
    // EMPTY_HONEYCOMB:     nothing (and a wasp is spawned)
    // FILLED_HONEYCOMB:    0-3 x filled_coneycomb item
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        switch ((HiveType) state.getValue(VARIANT))
        {
           case HONEYCOMB:
                return BOPItems.honeycomb;
            
            case FILLED_HONEYCOMB:
                return BOPItems.filled_honeycomb;
                
            case HIVE: case EMPTY_HONEYCOMB: default:
                return super.getItemDropped(state,rand,fortune);
        } 
    }
    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        switch ((HiveType) state.getValue(VARIANT))
        {
            case HONEYCOMB:
                return 1 + random.nextInt(3);
            
            case FILLED_HONEYCOMB:
                return random.nextInt(2);
                
            case EMPTY_HONEYCOMB:
                return 0;
                
            case HIVE: default:
                return 1;
        }
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        switch ((HiveType) state.getValue(VARIANT))
        {
            case HONEYCOMB: case FILLED_HONEYCOMB:
                return 0;
                
            case EMPTY_HONEYCOMB: case HIVE: default:
                return this.getMetaFromState(state);
        }
    }
    
    
    // spawn a wasp when empty honeycomb is broken
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        switch ((HiveType) state.getValue(VARIANT))
        {
            case EMPTY_HONEYCOMB:
                // TODO: EntityWasp wasp = new EntityWasp(worldIn);
                EntityChicken wasp = new EntityChicken(worldIn);
                wasp.setLocationAndAngles((double)pos.getX() + 0.6D, (double)pos.getY(), (double)pos.getZ() + 0.3D, 0.0F, 0.0F);
                worldIn.spawnEntityInWorld(wasp);
                break;
                
            default:
                break;
        }
        super.breakBlock(worldIn, pos, state);
    }
    

    

}
