package tdwp_ftw.biomesop.blocks;

import java.util.List;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPSlab extends BlockHalfSlab
{
    private static final String[] woodTypes = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    
    public BlockBOPSlab(int par1, boolean par2)
    {
        super(par1, par2, Material.wood);
        setBurnProperties(this.blockID, 5, 20);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
        useNeighborBrightness[blockID] = true;
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
    public String getFullSlabName(int meta)
    {
        if (meta < 0 || meta >= textures.length)
            meta = 0;
        return (new StringBuilder()).append(woodTypes[meta]).append("Slab").toString();
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
    
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 2, par1);
    }
}
