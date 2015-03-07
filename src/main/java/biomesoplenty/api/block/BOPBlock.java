/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.block;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import biomesoplenty.common.util.block.BlockStateUtils;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BOPBlock extends Block
{
    public ImmutableSet<IBlockState> presetStates;

    protected BOPBlock(Material material)
    {
        super(material);

        this.setCreativeTab(CreativeTabBOP.instance);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        if (this.hasPresetProperties())
        {
            for (IBlockState state : presetStates)
            {
                list.add(new ItemStack(item, 1, this.getMetaFromState(state)));
            }
        }
        else
        {
            list.add(new ItemStack(item, 1, 0));
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }

    public IProperty[] getPresetProperties()
    {
        return null;
    }

    public boolean hasPresetProperties()
    {
        return getPresetProperties() != null;
    }

    public String getStateName(IBlockState state, boolean fullName)
    {
        String unlocalizedName = state.getBlock().getUnlocalizedName();

        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
