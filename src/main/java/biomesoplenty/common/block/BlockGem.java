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
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;

//TODO: Add gem ore drops, make gem item seperate
public class BlockGem extends Block implements IBOPBlock
{
    // add properties
    public static enum GemType implements IStringSerializable {AMETHYST, RUBY, PERIDOT, TOPAZ, TANZANITE, MALACHITE, SAPPHIRE, AMBER; public String getName() {return this.name().toLowerCase();}};
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", GemType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}
    
    // implement IDHBlock
    private Map<String, IBlockState> namedStates = new HashMap<String, IBlockState>();
    public Map<String, IBlockState> getNamedStates() {return this.namedStates;}
    public IBlockState getNamedState(String name) {return this.namedStates.get(name);}
    public Class<? extends ItemBlock> getItemClass() {return ItemBOPBlock.class;}
    public int getItemRenderColor(IBlockState state, int tintIndex) {return this.getRenderColor(state);}

    

    public BlockGem()
    {        
        super(Material.rock);
        
        // set some defaults
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setHarvestLevel("pickaxe", 2);
        
        // define named states
        this.namedStates.put("amethyst_block", this.blockState.getBaseState().withProperty(VARIANT, GemType.AMETHYST) );
        this.namedStates.put("ruby_block", this.blockState.getBaseState().withProperty(VARIANT, GemType.RUBY) );
        this.namedStates.put("peridot_block", this.blockState.getBaseState().withProperty(VARIANT, GemType.PERIDOT) );
        this.namedStates.put("topaz_block", this.blockState.getBaseState().withProperty(VARIANT, GemType.TOPAZ) );
        this.namedStates.put("tanzanite_block", this.blockState.getBaseState().withProperty(VARIANT, GemType.TANZANITE) );
        this.namedStates.put("malachite_block", this.blockState.getBaseState().withProperty(VARIANT, GemType.MALACHITE) );
        this.namedStates.put("sapphire_block", this.blockState.getBaseState().withProperty(VARIANT, GemType.SAPPHIRE) );
        this.namedStates.put("amber_block", this.blockState.getBaseState().withProperty(VARIANT, GemType.AMBER) );
        
        this.setDefaultState(this.namedStates.get("amethyst_block"));
        
    }

    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, GemType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((GemType) state.getValue(VARIANT)).ordinal();
    }
    

}
