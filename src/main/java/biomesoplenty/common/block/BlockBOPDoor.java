/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.IBOPBlock;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPDoor extends BlockDoor implements IBOPBlock
{  

    // implement IBOPBlock
    // properties inherited from BlockDoor:  FACING, OPEN, HINGE, HALF, POWERED
    @Override
    public Class<? extends ItemBlock> getItemClass() { return null; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] {POWERED}; }
    @Override
    public String getStateName(IBlockState state) {return "";}
    
    private Item doorItem;
    
    public BlockBOPDoor()
    {
        super(Material.wood);
        this.setHardness(3.0F);
        this.setHarvestLevel("axe", 0);
    }
    
    public void setDoorItem(Item doorItem)
    {
        this.doorItem = doorItem;
    }
    
    public Item getDoorItem()
    {
        return this.doorItem == null ? Items.oak_door : this.doorItem;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return this.getDoorItem();
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : this.getDoorItem();
    }
}
    