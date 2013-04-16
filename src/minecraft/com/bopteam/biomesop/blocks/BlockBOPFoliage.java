package com.bopteam.biomesop.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bopteam.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFoliage extends BlockFlower implements IShearable
{
    private static final String[] foliageTypes = new String[] {"shortgrass", "mediumgrass", "highgrassbottom", "bush", "sprout", "highgrasstop"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;

    
    public BlockBOPFoliage(int blockID)
    {
        super(blockID, Material.vine);
        float f = 0.4F;
        setBurnProperties(this.blockID, 60, 100);
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
        setHardness(0.0F);
        setStepSound(Block.soundGrassFootstep);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[foliageTypes.length];
        
        for (int i = 0; i < textures.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+foliageTypes[i]);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        if (meta >= textures.length)
        {
            meta = 0;
        }

        return textures[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs par2CreativeTabs, List list)
    {
        for (int i = 0; i < textures.length-1; ++i)
            list.add(new ItemStack(blockID, 1, i));
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return super.canPlaceBlockAt(world, x, y, z) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(int blockID, int metadata)
    {
        if (metadata == 5)
            return blockID == this.blockID;
        else
            return blockID == Block.grass.blockID || blockID == Block.dirt.blockID || blockID == Block.tilledField.blockID;
    }
       
    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
    }   
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
    {
        super.onNeighborBlockChange(world, x, y, z, neighborID);
        this.checkFlowerChange(world, x, y, z);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerGrass.getGrassColor(var1, var3);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
        return ColorizerFoliage.getFoliageColorBasic();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor();
    }
    
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 5)
            meta = 2;
        return meta;
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return -1;
    }
    
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        super.harvestBlock(world, player, x, y, z, meta);
    }
    
    @Override
    public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == 5)
            return false;
        return true;
    }
    
    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return ret;
    }
    
    @Override
    public boolean isBlockFoliage(World world, int x, int y, int z)
    {
        return true;
    }
}
