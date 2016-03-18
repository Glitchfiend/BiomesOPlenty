/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.enums.BOPWoods;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] {POWERED}; }
    @Override
    public String getStateName(IBlockState state) {return "";}
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    // Map from woods to BlockBOPDoor instance and back
    private static Map<BOPWoods, BlockBOPDoor> variantToBlock = new HashMap<BOPWoods, BlockBOPDoor>();
    public static BlockBOPDoor getBlock(BOPWoods wood)
    {
        return variantToBlock.get(wood);
    }
    protected BOPWoods wood;
    public BOPWoods getWood()
    {
        return this.wood;
    }
    
    
    private Item doorItem;
    
    
    public BlockBOPDoor(BOPWoods wood)
    {
        super(Material.wood);
        this.setHardness(3.0F);
        this.setHarvestLevel("axe", 0);
        
        this.wood = wood;
        variantToBlock.put(wood, this);
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
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this.getDoorItem(), 1);
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : this.getDoorItem();
    }
}
    