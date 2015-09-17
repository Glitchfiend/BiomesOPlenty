package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.api.content.BOPCItems;
import biomesoplenty.common.entities.EntityWasp;

public class BlockHive extends Block
{
	private static final String[] hiveTypes = new String[] {"honeycomb", "hive", "honeycombempty", "honeycombfilled"};
	private IIcon[] textures;
	
	public BlockHive()
	{
		super(Material.wood);
		
		this.setHardness(0.5F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
    @Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
    {
    	if (world.getBlockMetadata(x, y, z) == 2)
    	{
			EntityWasp wasp = new EntityWasp(world);
			wasp.setLocationAndAngles((double)x + 0.6, (double)y, (double)z + 0.3, 0.0F, 0.0F);
			world.spawnEntityInWorld(wasp);
    	}
    }

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[hiveTypes.length];

		for (int i = 0; i < hiveTypes.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+hiveTypes[i]);
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
        if (meta < 0 || meta >= hiveTypes.length) 
        {
            meta = 0;
        }
        
		return textures[meta];
	}
	
	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < hiveTypes.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		if (metadata == 0)
		{
			return BOPCItems.misc;
		}
		
		if (metadata == 3)
		{
			return BOPCItems.food;
		}
		
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public int damageDropped(int meta)
	{
		if (meta == 0)
		{
			return 2;
		}
		if (meta == 2)
		{
			return 0;
		}
		if (meta == 3)
		{
			return 9;
		}
		
		return meta;
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 0)
		{
			return (random.nextInt(3) + 1);
		}
		if (meta == 2)
		{
			return 0;
		}
		if (meta == 3)
		{
			return random.nextInt(2);
		}
		
		return 1;
	}
}
