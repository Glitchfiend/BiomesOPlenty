package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.WorldTypeBOPA;

public class BlockCandy extends Block
{
	public enum BlockTypeCandy
	{
		CAKE, COOKIE, WAFER;
	}

	private IIcon texture;
	private BlockTypeCandy type;

	public BlockCandy(Material material, BlockTypeCandy type)
	{
		super(material);
		this.type = type;
		
		//TODO: this.setCreativeTab()
		if (!BOPConfigurationMisc.behaveNormally) 
		{
			if(WorldTypeBOPA.isTime())
			{
				this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
			}
		}
		

		switch (type)
		{
		case CAKE:
			this.setHardness(0.3F);
			this.setStepSound(soundTypeSnow);
			break;
			
		case COOKIE:
			this.setHardness(0.8F);
			this.setStepSound(soundTypeGravel);
			break;

		default:
			break;
		}
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		switch (type)
		{
		case CAKE:
			texture = iconRegister.registerIcon("biomesoplenty:cakeblock_bottom");
			break;
			
		case COOKIE:
			texture = iconRegister.registerIcon("biomesoplenty:cookieblock");
			break;

		default:
			break;
		}
	}

	@Override
	//TODO:	   getItemDropped()
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		switch (type)
		{
		case COOKIE:
			return Items.cookie;

		default:
			//TODO:		getItemForBlock()
			return Item.getItemFromBlock(this);
		}
	}

	@Override
	//TODO     damageDropped()
	public int damageDropped(int meta)
	{
		switch (type)
		{
		case COOKIE:
			return 0;

		default:
			return meta;
		}
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		switch (type)
		{
		case COOKIE:
			return 4;

		default:
			return 1;
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
		return texture;
	}
}
