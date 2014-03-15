package biomesoplenty.common.blocks.utils;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;

public class SubBlock
{
    private Block parent;
    private int metadata;
    private String name;

    private IIcon mainIcon;

    private IIcon[] icons = new IIcon[6];

    public SubBlock(Block parent, int metadata, String name)
    {
        this.parent = parent;
        this.metadata = metadata;
        this.name = name;
    }

    public void setMainIcon(IIcon mainIcon)
    {
        this.mainIcon = mainIcon;
    }

    public void setSidedIcon(IIcon sidedIcon, int side)
    {
        this.icons[side] = sidedIcon;
    }

    public Block getParent()
    {
        return parent;
    }

    public int getMetadata()
    {
        return metadata;
    }

    public String getName()
    {
        return name;
    }

    public IIcon getMainIcon()
    {
        return mainIcon;
    }

    public IIcon[] getIcons()
    {
        return icons;
    }

    public IIcon getIconForSide(int side)
    {
        return getIcons()[side];
    }
}
