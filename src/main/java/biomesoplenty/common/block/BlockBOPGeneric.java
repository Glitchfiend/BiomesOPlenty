/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.HashSet;
import java.util.Set;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.block.ISustainsPlantType;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

public class BlockBOPGeneric extends Block implements IBOPBlock, ISustainsPlantType
{
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {return "";}

    
    public BlockBOPGeneric() {
        // use rock as default material
        this(Material.rock);
    }
    
    public BlockBOPGeneric(Material material)
    {
        super(material);
        // set some defaults
        this.setHardness(1.0F);
        this.setStepSound(SoundType.STONE);
    }
    
    
    
    public Set<EnumPlantType> plantTypesICanSustain = new HashSet<EnumPlantType>();
    
    public BlockBOPGeneric addSupportedPlantType(EnumPlantType... plantTypes)
    {
        for (EnumPlantType plantType : plantTypes)
        {
            this.plantTypesICanSustain.add(plantType);
        }
        return this;
    }
    
    @Override
    public boolean canSustainPlantType(IBlockAccess world, BlockPos pos, EnumPlantType plantType)
    {
        return this.plantTypesICanSustain.contains(plantType);
    }
    
    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        return this.canSustainPlantType(world, pos, plantable.getPlantType(world, pos.offset(direction)));
    }
    
}