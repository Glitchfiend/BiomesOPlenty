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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.api.item.BOPItems;

public class BlockMud extends BOPBlock
{
    public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", MudType.class);

    public BlockMud()
    {
        super(Material.sand);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, MudType.MUD));
        this.setHardness(0.6F);
        this.setStepSound(Block.soundTypeSand);
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {   
        float heightOffset = 0.0F;
        switch ((MudType) state.getValue(VARIANT_PROP))
        {
            // sink a little when standing on mud
            case MUD:
                heightOffset = 0.35F;
                break;
            
            // no bounding box at all for quicksand - you're supposed to sink into it
            case QUICKSAND:
                return null;
        }
        return new AxisAlignedBB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), (double) (pos.getX() + 1), (double) ((float) (pos.getY() + 1) - heightOffset), (double) (pos.getZ() + 1));
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        
        if (entity instanceof EntityPlayer) {
            InventoryPlayer inventory = ((EntityPlayer)entity).inventory;
            // TODO: implement wadingBoots
            //if (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == BOPItems.wadingBoots) {
            //    return;
            //}
        }
        
        switch ((MudType) state.getValue(VARIANT_PROP))
        {
            // mud slows you greatly
            case MUD:
                entity.motionX *= 0.1D;
                entity.motionZ *= 0.1D;
                break;
            
            // quicksand behaves like being trapped in a spider web
            case QUICKSAND:
                entity.setInWeb();
                break;
        }       


    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // only one property to worry about, the variant, so just map [0 => MUD, 1 => QUICKSAND]
        return this.getDefaultState().withProperty(VARIANT_PROP, MudType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // only one property to worry about, the variant, so just map [0 => MUD, 1 => QUICKSAND]
        return ((MudType) state.getValue(VARIANT_PROP)).ordinal();
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
        return ((MudType) state.getValue(VARIANT_PROP)).getName();
    }
   
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return BOPItems.mudball;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 4;
    }

    // enum representing the 2 variants of mud
    public static enum MudType implements IStringSerializable
    {
        MUD, QUICKSAND;

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
    }
}
