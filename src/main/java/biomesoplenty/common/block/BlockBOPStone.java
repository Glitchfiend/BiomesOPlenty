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
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockBOPStone extends Block implements IBOPBlock
{
    
    // add properties
    public static enum StoneType implements IStringSerializable {LIMESTONE, SILTSTONE, SHALE; public String getName() {return this.name().toLowerCase();}};
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", StoneType.class);
    public static PropertyBool POLISHED = PropertyBool.create("polished");
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT, POLISHED });}
    
    // implement IDHBlock
    private Map<String, IBlockState> namedStates = new HashMap<String, IBlockState>();
    public Map<String, IBlockState> getNamedStates() {return this.namedStates;}
    public IBlockState getNamedState(String name) {return this.namedStates.get(name);}
    public Class<? extends ItemBlock> getItemClass() {return ItemBOPBlock.class;}

    public BlockBOPStone()
    {
        super(Material.rock);

        // set some defaults
        this.setStepSound(Block.soundTypeStone);
        
        this.setHarvestLevel("pickaxe", 1, this.getDefaultState().withProperty(VARIANT, StoneType.LIMESTONE));
        this.setHarvestLevel("pickaxe", 2, this.getDefaultState().withProperty(VARIANT, StoneType.SILTSTONE));
        this.setHarvestLevel("pickaxe", 3, this.getDefaultState().withProperty(VARIANT, StoneType.SHALE));
        
        // define named states
        this.namedStates.put("limestone", this.blockState.getBaseState().withProperty(VARIANT, StoneType.LIMESTONE).withProperty(POLISHED, Boolean.valueOf(false)) );
        this.namedStates.put("siltstone", this.blockState.getBaseState().withProperty(VARIANT, StoneType.SILTSTONE).withProperty(POLISHED, Boolean.valueOf(false)) );
        this.namedStates.put("shale", this.blockState.getBaseState().withProperty(VARIANT, StoneType.SHALE).withProperty(POLISHED, Boolean.valueOf(false)) );
        this.namedStates.put("polished_limestone", this.blockState.getBaseState().withProperty(VARIANT, StoneType.LIMESTONE).withProperty(POLISHED, Boolean.valueOf(true)) );
        this.namedStates.put("polished_siltstone", this.blockState.getBaseState().withProperty(VARIANT, StoneType.SILTSTONE).withProperty(POLISHED, Boolean.valueOf(true)) );
        this.namedStates.put("polished_shale", this.blockState.getBaseState().withProperty(VARIANT, StoneType.SHALE).withProperty(POLISHED, Boolean.valueOf(true)) );
        
        this.setDefaultState(this.namedStates.get("limestone"));
        
    }

    @Override
    public float getBlockHardness(World world, BlockPos pos)
    {
        return (Boolean)world.getBlockState(pos).getValue(POLISHED) ? 1.5F : 3.0F;
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion)
    {
        return (Boolean)world.getBlockState(pos).getValue(POLISHED) ? 7.0F : 5.0F;
    }

    // map from state to meta and vice verca - use highest bit for polished boolean, use low 2 bits for variant
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        boolean polished = (meta & 8) > 0;
        int type = meta & 3;
        return this.getDefaultState().withProperty(VARIANT, StoneType.values()[type]).withProperty(POLISHED, Boolean.valueOf(polished));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int type = ((StoneType) state.getValue(VARIANT)).ordinal();
        boolean polished = (Boolean) state.getValue(POLISHED);
        return type + (polished ? 8 : 0);
    }

}