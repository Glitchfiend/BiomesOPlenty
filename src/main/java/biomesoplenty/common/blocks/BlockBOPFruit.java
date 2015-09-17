package biomesoplenty.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCItems;
import biomesoplenty.client.render.RenderUtils;
import biomesoplenty.common.blocks.templates.BOPBlockWorldDecor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFruit extends BOPBlockWorldDecor
{
	private static final String[] fruit = new String[] {"apple_block", "persimmon_block", "peach_block", "pear_block", "pinecone_block"};
	private IIcon[] textures;

	public BlockBOPFruit()
	{
		super(Material.plants);
		
		this.setHardness(0.0F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);
		this.setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 1.0F, 0.75F);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[fruit.length];

		for (int i = 0; i < fruit.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + fruit[i]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		//for (int i = 0; i < fruit.length; ++i)
		//{
			//list.add(new ItemStack(block, 1, i));
		//}
	}

	@Override
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		Block block = world.getBlock(x, y + 1, z);
		
		switch (metadata)
		{
		default:
			return block == Blocks.leaves || block == Blocks.leaves2 || block == BOPCBlocks.leaves1 || block == BOPCBlocks.leaves2 || block == BOPCBlocks.leaves3 || block == BOPCBlocks.leaves4;
		}
	}

	@Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int metadata = itemStack != null ? itemStack.getItemDamage() : 0;
		
        return this.isValidPosition(world, x, y, z, metadata);
	}
	
	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 0:
			return new ItemStack(Items.apple, 1, 0);

		case 1:
			return new ItemStack(BOPCItems.food, 1, 8);
			
		case 2:
			return new ItemStack(BOPCItems.food, 1, 3);
		
		case 3:
			return new ItemStack(BOPCItems.food, 1, 12);
			
		case 4:
			return new ItemStack(BOPCItems.misc, 1, 13);
		}

		return new ItemStack(this, 1, meta);
    }

	@Override
	public int getDamageValue(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		return meta;
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		if (metadata == 0)
		{
			return Items.apple;
		}
		else if (metadata == 1)
		{
			return BOPCItems.food;
		}
		else if (metadata == 2)
		{
			return BOPCItems.food;
		}
		else if (metadata == 3)
		{
			return BOPCItems.food;
		}
		else if (metadata == 4)
		{
			return BOPCItems.misc;
		}
		else
		{
			return null;
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 1)
		{
			return 8;
		}
		else if (meta == 2)
		{
			return 3;
		}
		else if (meta == 3)
		{
			return 12;
		}
		else if (meta == 4)
		{
			return 13;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 1;
	}
}
