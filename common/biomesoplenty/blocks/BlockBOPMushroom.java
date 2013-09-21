package biomesoplenty.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPMushroom extends BlockFlower
{
	private static final String[] plants = new String[] {"toadstool", "portobello", "bluemilk", "glowshroom", "flatmushroom"};
	private Icon[] textures;

	protected BlockBOPMushroom(int blockID, Material material)
	{
		super(blockID, material);
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	public BlockBOPMushroom(int blockID)
	{
		this(blockID, Material.plants);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[plants.length];

		for (int i = 0; i < plants.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + plants[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	public int getRenderType ()
	{
		return 1;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 3)
			return 6;
		else
			return 0;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
	{
		int meta = world.getBlockMetadata(par2, par3, par4);

		switch (meta)
		{
		case 0:
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
			break;

		default:
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
			break;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < plants.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
	{
		if (metadata == 0) //Toadstool
		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.holyGrass.get().blockID || id == Block.netherrack.blockID;
		if (metadata == 1) //Portobello
			return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID | id == Blocks.holyGrass.get().blockID;
		if (metadata == 2) //Blue Milk Cap
			return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.holyGrass.get().blockID;
		if (metadata == 3) //Glowshroom
			return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Block.stone.blockID || id == Block.netherrack.blockID;
		else
			return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int id = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();
		//boolean sky = world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z);

		if (itemStack.itemID == blockID) {
			switch (meta)
			{
			case 0: // Toadstool
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.holyGrass.get().blockID || id == Block.netherrack.blockID;

			case 1: // Portobello
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.holyGrass.get().blockID;

			case 2: // Blue Milk Cap
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.holyGrass.get().blockID;

			case 3: // Glowshroom
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Block.stone.blockID || id == Block.netherrack.blockID;

			default:
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID;
			}
		} else
			return this.canPlaceBlockOnSide(world, x, y, z, side);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta & 15;
	}
}
