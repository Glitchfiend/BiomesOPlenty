/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import static biomesoplenty.common.block.BlockGem.VARIANT;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.BlockGem.GemType;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockGemOre extends Block implements IBOPBlock
{
    
    // add properties (note VARIANT is imported statically from the BlockGem class)
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}
    
    // implement IDHBlock
    private Map<String, IBlockState> namedStates = new HashMap<String, IBlockState>();
    public Map<String, IBlockState> getNamedStates() {return this.namedStates;}
    public IBlockState getNamedState(String name) {return this.namedStates.get(name);}
    public Class<? extends ItemBlock> getItemClass() {return ItemBOPBlock.class;}
    public int getItemRenderColor(IBlockState state, int tintIndex) {return this.getRenderColor(state);}

    
    
    public BlockGemOre()
    {
        super(Material.rock);
        
        // set some defaults
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypePiston);
        
        // define named states
        this.namedStates.put("amethyst_ore", this.blockState.getBaseState().withProperty(VARIANT, GemType.AMETHYST) );
        this.namedStates.put("ruby_ore", this.blockState.getBaseState().withProperty(VARIANT, GemType.RUBY) );
        this.namedStates.put("peridot_ore", this.blockState.getBaseState().withProperty(VARIANT, GemType.PERIDOT) );
        this.namedStates.put("topaz_ore", this.blockState.getBaseState().withProperty(VARIANT, GemType.TOPAZ) );
        this.namedStates.put("tanzanite_ore", this.blockState.getBaseState().withProperty(VARIANT, GemType.TANZANITE) );
        this.namedStates.put("malachite_ore", this.blockState.getBaseState().withProperty(VARIANT, GemType.MALACHITE) );
        this.namedStates.put("sapphire_ore", this.blockState.getBaseState().withProperty(VARIANT, GemType.SAPPHIRE) );
        this.namedStates.put("amber_ore", this.blockState.getBaseState().withProperty(VARIANT, GemType.AMBER) );
        
        this.setDefaultState(this.namedStates.get("amethyst_ore"));

        // all variants need pickaxe:2 to harvest, except amethyst which needs pickaxe:3
        for (IBlockState state : this.namedStates.values()) {this.setHarvestLevel("pickaxe", 2, state);}
        this.setHarvestLevel("pickaxe", 3, this.namedStates.get("amethyst_ore"));
    
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

    
    // The 3 functions below, getItemDropped, quantityDroppedWithBonus and damageDropped work together to determine the drops
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return BOPItems.gem;
    }
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        // returns a number between 1 and (fortune+1) - functionally identical to procedure for vanilla diamond drops, but simplified
        return Math.max(0, random.nextInt(fortune + 2) - 1) + 1;
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }
    
}
