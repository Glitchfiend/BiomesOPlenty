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
    private SubBlock[] subBlocks = new SubBlock[16];

    public BOPBlock(Material material)
    {
        super(material);

        initializeSubBlocks();

        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }

    protected void initializeSubBlocks()
    {

    }

    public SubBlock registerSubBlock(int metadata, String name)
    {
        if (subBlocks[metadata] == null)
        {
            SubBlock subBlock = new SubBlock(this, metadata, name);

            return subBlocks[metadata] = subBlock;
        }
        else
        {
            throw new RuntimeException("Metadata " + metadata + " already occupied");
        }
    }

    public SubBlock[] getSubBlocks()
    {
        return subBlocks;
    }

    public SubBlock getSubBlock(int metadata)
    {
        return getSubBlocks()[metadata];
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        SubBlock subBlock = getSubBlock(metadata);

        if (subBlock.getIcons()[side] != null) return subBlock.getIcons()[side];
        else if (subBlock.getMainIcon() != null) return subBlock.getMainIcon();
        else return super.getIcon(side, metadata);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (SubBlock subBlock : subBlocks)
        {
            if (subBlock != null) list.add(new ItemStack(block, 1, subBlock.getMetadata()));
        }
    }
}
