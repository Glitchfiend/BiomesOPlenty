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
import biomesoplenty.api.BOPItemHelper;

public class BlockHive extends Block
{
	private static final String[] hiveTypes = new String[] {"honeycomb", "hive", "honeycombempty", "honeycombfilled"};
	private IIcon[] textures;
	
	public BlockHive()
	{
		//TODO: Material.wood
		super(Material.field_151575_d);
		
		//TODO: this.setHardness
		this.func_149711_c(0.5F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.func_149672_a(Block.field_149779_h);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}
	
    @Override
	//TODO:		breakBlock()
	public void func_149749_a(World world, int x, int y, int z, Block par5, int par6)
    {
    	if (world.getBlockMetadata(x, y, z) == 2)
    	{
			/*TODO: FEATURE EntityWasp wasp = new EntityWasp(world);
			wasp.setLocationAndAngles((double)x + 0.6, (double)y, (double)z + 0.3, 0.0F, 0.0F);
			world.spawnEntityInWorld(wasp);*/
    	}
    }

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[hiveTypes.length];

		for (int i = 0; i < hiveTypes.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+hiveTypes[i]);
		}
	}
	
	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
        if (meta < 0 || meta >= hiveTypes.length) 
        {
            meta = 0;
        }
        
		return textures[meta];
	}
	
	@Override
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < hiveTypes.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}
	
	@Override
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		if (metadata == 0)
		{
			return BOPItemHelper.get("misc");
		}
		
		if (metadata == 3)
		{
			return BOPItemHelper.get("food");
		}
		
		//TODO:     getItemFromBlock()
		return Item.func_150898_a(this);
	}
	
	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
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