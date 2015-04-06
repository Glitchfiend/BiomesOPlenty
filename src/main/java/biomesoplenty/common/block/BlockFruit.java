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
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import biomesoplenty.api.item.BOPItems;

public class BlockFruit extends BlockDecoration
{
    
    // add properties
    public static enum FruitType implements IStringSerializable
    {
        APPLE, PERSIMMON, PEACH, PEAR;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", FruitType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((FruitType) state.getValue(VARIANT)).getName() + "_block";
    }
    

    // constructor
    public BlockFruit() {
                
        // set some defaults
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockBoundsByRadiusAndHeight(0.25F, 0.25F, true);        
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, FruitType.APPLE) );
    }

    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, FruitType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((FruitType) state.getValue(VARIANT)).ordinal();
    }
    
    // only allow fruit to hang from leaves
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block blockAbove = world.getBlockState(pos.up()).getBlock();
        return blockAbove == Blocks.leaves || blockAbove == Blocks.leaves2; /* TODO: add BOP leave types - maybe check the material instead? */
    }
    
    // map states to the corresponding fruit item
    public static Item getFruitItem(IBlockState state)
    {
        switch ((FruitType) state.getValue(VARIANT))
        {
            case PERSIMMON:
                return BOPItems.persimmon;
            case PEACH:
                return BOPItems.peach;
            case PEAR:
                return BOPItems.pear;
            case APPLE: default:
                return Items.apple;
        }       
    }
    
    // In creative mode, pick block to get the fruit item
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        Item item = getFruitItem(state);
        int meta = damageDropped(state);
        return new ItemStack(item, 1, meta);
    }

    // fruit blocks drop the corresponding fruit item when broken
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return getFruitItem(state);
    }
    
    // prevent fruit block meta value affecting fruit item dropped
    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
    
}
