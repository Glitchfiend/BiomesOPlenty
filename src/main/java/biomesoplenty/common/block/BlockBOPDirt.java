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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;

public class BlockBOPDirt extends Block implements IBOPBlock 
{
    
    // add properties
    public static enum BOPDirtType implements IStringSerializable {LOAMY, SANDY, SILTY; public String getName() {return this.name().toLowerCase();}};
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BOPDirtType.class);
    public static final PropertyBool COARSE = PropertyBool.create("coarse");
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { COARSE, VARIANT });}
    
    // implement IDHBlock
    private Map<String, IBlockState> namedStates = new HashMap<String, IBlockState>();
    public Map<String, IBlockState> getNamedStates() {return this.namedStates;}
    public IBlockState getNamedState(String name) {return this.namedStates.get(name);}
    public Class<? extends ItemBlock> getItemClass() {return ItemBOPBlock.class;}
    

    
    public BlockBOPDirt() {
        
        super(Material.ground);
        
        // set some defaults
        this.setHardness(0.5F);
        this.setHarvestLevel("shovel", 0);
        this.setStepSound(Block.soundTypeGravel);
      
        // define named states
        this.namedStates.put("loamy_dirt", this.blockState.getBaseState().withProperty(COARSE, Boolean.valueOf(false)).withProperty(VARIANT, BOPDirtType.LOAMY) );
        this.namedStates.put("sandy_dirt", this.blockState.getBaseState().withProperty(COARSE, Boolean.valueOf(false)).withProperty(VARIANT, BOPDirtType.SANDY) );
        this.namedStates.put("silty_dirt", this.blockState.getBaseState().withProperty(COARSE, Boolean.valueOf(false)).withProperty(VARIANT, BOPDirtType.SILTY) );
        this.namedStates.put("coarse_loamy_dirt", this.blockState.getBaseState().withProperty(COARSE, Boolean.valueOf(true)).withProperty(VARIANT, BOPDirtType.LOAMY) );
        this.namedStates.put("coarse_sandy_dirt", this.blockState.getBaseState().withProperty(COARSE, Boolean.valueOf(true)).withProperty(VARIANT, BOPDirtType.SANDY) );
        this.namedStates.put("coarse_silty_dirt", this.blockState.getBaseState().withProperty(COARSE, Boolean.valueOf(true)).withProperty(VARIANT, BOPDirtType.SILTY) );
                
        this.setDefaultState(this.namedStates.get("loamy_dirt"));
    
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // both variant and coarseness saved in meta, first bit coarseness, other bits variant
        return this.getDefaultState().withProperty(COARSE, Boolean.valueOf((meta & 8) > 0)).withProperty(VARIANT, BOPDirtType.values()[Math.min(2, meta & 7)]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // both variant and coarseness saved in meta, first bit coarseness, other bits variant
        return (Boolean.TRUE.equals(state.getValue(COARSE)) ? 8 : 0) | ((BOPDirtType) state.getValue(VARIANT)).ordinal();
    }

    
    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));
        
        switch (plantType)
        {
            // support desert and plains plants
            case Desert: case Plains: return true;
            // support cave plants
            case Cave:   return isSideSolid(world, pos, EnumFacing.UP);
            // support beach plants if there's water alongside
            case Beach:
                return (
                    world.getBlockState(pos.east()).getBlock().getMaterial() == Material.water ||
                    world.getBlockState(pos.west()).getBlock().getMaterial() == Material.water ||
                    world.getBlockState(pos.north()).getBlock().getMaterial() == Material.water ||
                    world.getBlockState(pos.south()).getBlock().getMaterial() == Material.water
                );
             // don't support nether plants, water plants, or crops (require farmland), or anything else by default
            default:
                return false;
        }
    }
    
        
    // get the blockstate which corresponds to the type of grass which grows on this dirt
    public static IBlockState getGrassBlockState(IBlockState state)
    {
        // no grass grows on coarse dirt
        if (Boolean.FALSE.equals(state.getValue(COARSE)))
        {
            return null;
        }
        switch ((BOPDirtType) state.getValue(VARIANT))
        {   
            case LOAMY:
                return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.LOAMY);
            case SANDY:
                return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SANDY);
            case SILTY:
                return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SILTY);
            default:
                // return vanilla grass as a backup
                return Blocks.grass.getDefaultState();
        }
    }
    
    public Block getGrassBlock(IBlockState state)
    {
        return getGrassBlockState(state).getBlock();
    }
    
    public int getGrassBlockMeta(IBlockState state)
    {
        return this.getGrassBlock(state).getMetaFromState(getGrassBlockState(state));
    }
    
}
