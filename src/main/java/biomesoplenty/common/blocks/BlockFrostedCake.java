package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.WorldTypeBOPA;

public class BlockFrostedCake extends Block
{
	private IIcon[] icons = new IIcon[6];

	public BlockFrostedCake()
	{
		//TODO: Material.rock
		super(Material.cake);
		
		//TODO: this.setHardness
		this.setHardness(0.3F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.setStepSound(Block.soundTypeSnow);
		
		//TODO: setTickRandomly()
		this.setTickRandomly(true);
		
		//TODO: this.setCreativeTab()
		if (!BOPConfigurationMisc.behaveNormally) 
		{
			if(WorldTypeBOPA.isTime())
			{
				this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
			}
		}
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.icons[0] = iconRegister.registerIcon("biomesoplenty:cakeblock_bottom");
		this.icons[1] = iconRegister.registerIcon("biomesoplenty:cakeblock_top");
		this.icons[2] = iconRegister.registerIcon("biomesoplenty:cakeblock_side");
		this.icons[3] = iconRegister.registerIcon("biomesoplenty:cakeblock_side");
		this.icons[4] = iconRegister.registerIcon("biomesoplenty:cakeblock_side");
		this.icons[5] = iconRegister.registerIcon("biomesoplenty:cakeblock_side");
	}

	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
	    if (side < 0 || side >= this.icons.length) side = 1;
        
		return this.icons[side];
	}

	@Override
	//TODO:	   getItemDropped()
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return Item.getItemFromBlock(BOPBlockHelper.get("cakeBlock"));
	}
}
