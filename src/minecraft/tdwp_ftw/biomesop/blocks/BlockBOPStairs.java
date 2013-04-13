package tdwp_ftw.biomesop.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockBOPStairs extends BlockStairs
{
    private static final String[] woodTypes = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;

    public BlockBOPStairs(int blockID, Block model)
    {
        super(blockID, model, 0);
        setBurnProperties(this.blockID, 5, 20);
        this.setLightOpacity(0);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
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
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        if (meta < 0 || meta >= textures.length)
            meta = 0;

        return textures[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
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
