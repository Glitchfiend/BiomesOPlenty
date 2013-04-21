package biomesoplenty.blocks;

import java.util.List;

import biomesoplenty.BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPPlank extends Block
{
    private static final String[] woodTypes = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;

    public BlockBOPPlank(int blockID)
    {
        super(blockID, Material.wood); 
        setBurnProperties(this.blockID, 5, 20);
        setHardness(2.0F);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[woodTypes.length];
        
        for (int i = 0; i < woodTypes.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+woodTypes[i]+"plank");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= textures.length)
            meta = 0;

        return textures[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < woodTypes.length; ++i)
            list.add(new ItemStack(blockID, 1, i));
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}
