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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import biomesoplenty.api.block.BOPPlant;
import biomesoplenty.api.item.BOPItems;

public class BlockFruit extends BOPPlant
{
    public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", FruitType.class);
    
    public BlockFruit()
    {
        super(Material.plants);
        this.setStepSound(soundTypeGrass);
        this.setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 1.0F, 0.75F);
        // TODO: once the mechanism for farming fruit is established: this.setCreativeTab(null);
    }
     
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // only one property to worry about, the variant, so just map [0 => STALAGMITE, 1 => STALACTITE]
        return this.getDefaultState().withProperty(VARIANT_PROP, FruitType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // only one property to worry about, the variant, so just map [0 => STALAGMITE, 1 => STALACTITE]
        return ((FruitType) state.getValue(VARIANT_PROP)).ordinal();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT_PROP });
    }

    @Override
    public IProperty[] getPresetProperties()
    {
        return new IProperty[] { VARIANT_PROP };
    }
    
    @Override
    public String getStateName(IBlockState state, boolean fullName)
    {
        return ((FruitType) state.getValue(VARIANT_PROP)).getName() + "_block";
    }
    
    // only allow fruit to hang from leaves
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block blockAbove = world.getBlockState(pos.up()).getBlock();
        return blockAbove == Blocks.leaves || blockAbove == Blocks.leaves2; /* TODO: add BOP leave types - maybe check the material instead? */
    }
    
    // In creative mode, pick block to get the fruit item
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        Item item = ((FruitType) state.getValue(VARIANT_PROP)).getItem();
        int meta = damageDropped(state);
        return new ItemStack(item, 1, meta);
    }

    // fruit blocks drop the corresponding fruit item when broken
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ((FruitType) state.getValue(VARIANT_PROP)).getItem();
    }
    
    // prevent fruit block meta value affecting fruit item dropped
    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
    
    // enum representing the fruit variants
    // TODO: take outside the class so it can be reused in leaves?
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
            return getName();
        }
        
        // get the item dropped when this type of fruit block is broken/picked
        public Item getItem() {
            switch (this)
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
    }
    
}
