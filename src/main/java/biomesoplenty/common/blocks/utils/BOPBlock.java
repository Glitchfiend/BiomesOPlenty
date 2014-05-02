package biomesoplenty.common.blocks.utils;

import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public abstract class BOPBlock extends Block
{
    public BOPBlock(Material material)
    {
        super(material);

        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}
