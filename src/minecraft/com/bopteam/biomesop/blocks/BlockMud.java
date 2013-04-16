package com.bopteam.biomesop.blocks;

import java.util.Random;

import com.bopteam.biomesop.mod_BiomesOPlenty;
import com.bopteam.biomesop.configuration.BOPItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockMud extends Block
{
    public BlockMud(int par1)
    {
        super(par1, Material.sand);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
    
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("BiomesOPlenty:mud");
	}

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float var5 = 0.35F;
        return AxisAlignedBB.getAABBPool().getAABB((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)((float)(par3 + 1) - var5), (double)(par4 + 1));
    }

    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.motionX *= 0.1D;
        par5Entity.motionZ *= 0.1D;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return BOPItems.mudBall.itemID;
    }

    public int quantityDropped(Random par1Random)
    {
        return 4;
    }
}