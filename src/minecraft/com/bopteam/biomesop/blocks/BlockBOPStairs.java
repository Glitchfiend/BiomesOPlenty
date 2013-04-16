package com.bopteam.biomesop.blocks;

import com.bopteam.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPStairs extends BlockStairs
{
    public static enum WoodCategory
    {
      ACACIA, CHERRY, DARK, FIR, HOLY, MAGIC, MANGROVE, PALM, REDWOOD, WILLOW;
    }
    
    private static final String[] woodTypes = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    private final WoodCategory category;

    public BlockBOPStairs(int blockID, Block model, WoodCategory cat)
    {
        super(blockID, model, 0);
        category = cat;
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
        return textures[category.ordinal()];
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}
