package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.blocks.templates.BOPBlockWorldDecor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStoneFormations extends BOPBlockWorldDecor
{
	private static final String[] forms = new String[] {"stalagmite", "stalactite"};
	private IIcon[] textures;

	public BlockStoneFormations()
	{
		//TODO:	Material.vine
        super(Material.field_151582_l);

		//TODO: this.setHardness
		this.func_149711_c(0.5F);
        
		//TODO setStepSound(Block.soundStoneFootstep)
		this.func_149672_a(Block.field_149780_i);
        
		//TODO: setTickRandomly()
		this.func_149675_a(true);
		float var4 = 0.2F;
		//TODO: setBlockBounds
		this.func_149676_a(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);

		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[forms.length];

		for (int i = 0; i < forms.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + forms[i]);
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO		getRenderType()
	public int func_149645_b()
	{
		return 1;
	}

	@Override
	//TODO:     setBlockBoundsBasedOnState()
	public void func_149719_a(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		default:
					//TODO: setBlockBounds
		this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			break;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < forms.length; ++i)
		{
			list.add(new ItemStack(block, 1, i));
		}
	}
	
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		//TODO:					  getBlock()
		Block blockBottom = world.func_147439_a(x, y - 1, z);
		//TODO:				   getBlock()
		Block blockTop = world.func_147439_a(x, y + 1, z);
		
		switch (metadata)
		{
		case 0: // Stalagmite
			return blockBottom instanceof BlockStone;
			
		case 1: // Stalactite
		    return blockTop instanceof BlockStone;

		default:
		    return blockBottom instanceof BlockStone;
		}
	}

	@Override
	//TODO:			canReplace()
    public boolean func_149705_a(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		return isValidPosition(world, x, y, z, itemStack.getItemDamage());
	}
	
	@Override
	//TODO:	   getDamageValue()
	public int func_149643_k(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);

		return meta;
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta & 15;
	}

	@Override
	//TODO: 	   isBlockReplaceable
	public boolean func_149742_c(World world, int x, int y, int z)
	{
		return true;
	}
}
