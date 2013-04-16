package com.bopteam.biomesop.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;

public class ItemShroomPowder extends ItemFood
{
	public ItemShroomPowder(int par1, int par2, float par3, boolean par4)
	{
		super(par1, par2, par3, par4);
	}
	
	public void updateIcons(IconRegister iconRegister)
	{
    	iconIndex = iconRegister.registerIcon("BiomesOPlenty:shroompowder");
	}
}
