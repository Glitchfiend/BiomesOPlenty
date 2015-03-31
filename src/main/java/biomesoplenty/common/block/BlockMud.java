/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.HashMap;
import java.util.Map;
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
import net.minecraft.item.ItemBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.item.ItemBOPBlock;

public class BlockMud extends Block implements IBOPBlock
{

    // add properties
    public static enum MudType implements IStringSerializable {MUD, QUICKSAND; public String getName() {return this.name().toLowerCase();}};
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", MudType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}
    
    // implement IDHBlock
    private Map<String, IBlockState> namedStates = new HashMap<String, IBlockState>();
    public Map<String, IBlockState> getNamedStates() {return this.namedStates;}
    public IBlockState getNamedState(String name) {return this.namedStates.get(name);}
    public Class<? extends ItemBlock> getItemClass() {return ItemBOPBlock.class;}

    // constructor
    public BlockMud() {
        
        super(Material.sand);
        
        // set some defaults
        this.setHardness(0.6F);
        this.setStepSound(Block.soundTypeSand);
        
        // define named states
        this.namedStates.put("mud", this.blockState.getBaseState().withProperty(VARIANT, MudType.MUD) );
        this.namedStates.put("quicksand", this.blockState.getBaseState().withProperty(VARIANT, MudType.QUICKSAND) );
        
        this.setDefaultState(this.namedStates.get("mud"));
        
    }    
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, MudType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((MudType) state.getValue(VARIANT)).ordinal();
    }
    
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {   
        float heightOffset = 0.0F;
        switch ((MudType) state.getValue(VARIANT))
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
        
        switch ((MudType) state.getValue(VARIANT))
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
    
    // drop 4 balls of the item instead of one block
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        switch ((MudType) state.getValue(VARIANT))
        {
            case MUD:
                return BOPItems.mudball;
            
            case QUICKSAND:
                break;
        }
        return super.getItemDropped(state,rand,fortune);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 4;
    }    
    
    
}