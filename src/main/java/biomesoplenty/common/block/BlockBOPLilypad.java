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
import biomesoplenty.common.item.ItemBOPLilypad;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;

public class BlockBOPLilypad extends BlockLilyPad implements IBOPBlock
{
    
    // add properties
    public static enum LilypadType implements IStringSerializable {MEDIUM, SMALL, TINY, DUCKWEED; public String getName() {return this.name().toLowerCase();}};
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", LilypadType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}
    
    // implement IDHBlock
    private Map<String, IBlockState> namedStates = new HashMap<String, IBlockState>();
    public Map<String, IBlockState> getNamedStates() {return this.namedStates;}
    public IBlockState getNamedState(String name) {return this.namedStates.get(name);}
    // need to use a custom item class because of the unique way lilies are placed
    public Class<? extends ItemBlock> getItemClass() {return ItemBOPLilypad.class;}
    
    
    public BlockBOPLilypad()
    {        
        // define named states
        this.namedStates.put("lily_medium", this.blockState.getBaseState().withProperty(VARIANT, LilypadType.MEDIUM) );
        this.namedStates.put("lily_small", this.blockState.getBaseState().withProperty(VARIANT, LilypadType.SMALL) );
        this.namedStates.put("lily_tiny", this.blockState.getBaseState().withProperty(VARIANT, LilypadType.TINY) );
        this.namedStates.put("duckweed", this.blockState.getBaseState().withProperty(VARIANT, LilypadType.DUCKWEED) );
        
        this.setDefaultState(this.namedStates.get("lily_medium"));
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // only one property to worry about, the variant, so just map [0 => MEDIUM, 1 => SMALL, 2 => TINY]
        return this.getDefaultState().withProperty(VARIANT, LilypadType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // only one property to worry about, the variant, so just map [0 => MEDIUM, 1 => SMALL, 2 => TINY]
        return ((LilypadType) state.getValue(VARIANT)).ordinal();
    }
    
    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return net.minecraftforge.common.EnumPlantType.Water;
    }
    
}