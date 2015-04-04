/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import static net.minecraft.block.BlockLiquid.LEVEL;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// TODO: do we need LEVEL?
public class BlockCoral extends BlockDecoration
{
    
    // TODO: Readd kelp
    public static enum CoralType implements IStringSerializable {PINK, ORANGE, BLUE, GLOWING, ALGAE; public String getName(){return this.name().toLowerCase();}}
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", CoralType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { LEVEL, VARIANT });}  

    // implement IBOPBlock
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    public IProperty[] getRenderProperties() { return new IProperty[] {VARIANT}; }
    public String getStateName(IBlockState state)
    {
        CoralType type = (CoralType) state.getValue(VARIANT);
        switch (type)
        {
            case ALGAE:
                return type.getName();
            default:
                return type.getName()+"_coral";
        }
    }
    
    
    public BlockCoral()
    {
        super(Material.water);
        
        // set some defaults
        this.setHardness(0.6F);
        this.setStepSound(Block.soundTypeSand);
        this.setBlockBoundsByRadiusAndHeight(0.4F, 0.8F);
        this.setDefaultState( this.blockState.getBaseState().withProperty(LEVEL, 15).withProperty(VARIANT, CoralType.PINK) );       

    }
    
    
    // map from state to meta and vice verca - note the LEVEL property is ignored (so the default 15 is always assumed)
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, CoralType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((CoralType) state.getValue(VARIANT)).ordinal();
    }
    
    
    // give the corals a random XZ offset so they're not spaced in a perfect grid
    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

    // glowing_coral emits light
    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos)
    {
        switch ((CoralType) world.getBlockState(pos).getValue(VARIANT))
        {
            case GLOWING:
                return 10;
                
            default:
                return super.getLightValue(); 
        }
    }

    // require water above and earth below
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block ground = world.getBlockState(pos.down()).getBlock();
        Block cover = world.getBlockState(pos.up()).getBlock();
        
        boolean hasWater = (cover == Blocks.water || cover == Blocks.flowing_water);
        boolean hasEarth = (ground == Blocks.dirt || ground == BOPBlocks.dirt || ground == BOPBlocks.mud || ground == Blocks.sand || ground == Blocks.sponge || ground == Blocks.stone || ground == Blocks.clay || ground == Blocks.gravel);
        
        return hasWater && hasEarth;
    }

}
