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

import biomesoplenty.api.block.IBOPBlock;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPDoor extends BlockDoor implements IBOPBlock
{  
    // implement IDHBlock
    protected Map<String, IBlockState> namedStates = new HashMap<String, IBlockState>();
    public Map<String, IBlockState> getNamedStates() {return this.namedStates;}
    public IBlockState getNamedState(String name) {return this.namedStates.get(name);}
    public Class<? extends ItemBlock> getItemClass() {return null;}
    public int getItemRenderColor(IBlockState state, int tintIndex) {return this.getRenderColor(state);}

    
    private Item doorItem;
    
    public BlockBOPDoor()
    {
        super(Material.wood);
    }
    
    public void setDoorItem(Item doorItem)
    {
        this.doorItem = doorItem;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return this.doorItem;

    }
}
    