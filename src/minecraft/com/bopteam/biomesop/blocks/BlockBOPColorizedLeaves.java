package com.bopteam.biomesop.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bopteam.biomesop.mod_BiomesOPlenty;
import com.bopteam.biomesop.configuration.BOPBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockBOPColorizedLeaves extends BlockLeavesBase implements IShearable
{
    private static final String[] leaves = new String[] {"acacia", "mangrove", "palm", "redwood", "willow"};
    @SideOnly(Side.CLIENT)
    private Icon[][] textures;
    
    public BlockBOPColorizedLeaves(int blockID)
    {
        super(blockID, Material.leaves, false);
        setBurnProperties(this.blockID, 30, 60);
        this.setTickRandomly(true);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[2][leaves.length];
        
        for (int i = 0; i < leaves.length; ++i)
        {
            textures[0][i] = iconRegister.registerIcon("BiomesOPlenty:" + leaves[i] + "leaves1");
            textures[1][i] = iconRegister.registerIcon("BiomesOPlenty:" + leaves[i] + "leaves2");
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        double temperature = 0.5D;
        double humidity = 1.0D;
        return ColorizerFoliage.getFoliageColor(temperature, humidity);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
        // TODO
        return (par1 & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((par1 & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var6 = 0;
        int var7 = 0;
        int var8 = 0;

        for (int var9 = -1; var9 <= 1; ++var9)
        {
            for (int var10 = -1; var10 <= 1; ++var10)
            {
                int var11 = par1IBlockAccess.getBiomeGenForCoords(par2 + var10, par4 + var9).getBiomeFoliageColor();
                var6 += (var11 & 16711680) >> 16;
                var7 += (var11 & 65280) >> 8;
                var8 += var11 & 255;
            }
        }

        return (var6 / 9 & 255) << 16 | (var7 / 9 & 255) << 8 | var8 / 9 & 255;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        if (meta < 0 || meta >= textures[0].length)
            meta = 0;

        return textures[(!isOpaqueCube() ? 0 : 1)][meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < textures[0].length; ++i)
            list.add(new ItemStack(blockID, 1, i));
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return BOPBlocks.yellowSapling.blockID;
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta & textures[0].length;
    }
    
    @Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z) 
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) 
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
        return ret;
    }
}
