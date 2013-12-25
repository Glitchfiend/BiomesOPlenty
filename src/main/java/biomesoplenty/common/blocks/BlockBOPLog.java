package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPLog extends Block
{
	public static enum LogCategory
	{
		CAT1, CAT2, CAT3, CAT4;
	}
	//logs1
	//Acacia			(0)
	//Cherry			(1)
	//Dark				(2)
	//Fir				(3)
	
	//logs2
	//Loftwood			(0)
	//Magic				(1)
	//Mangrove			(2)
	//Palm				(3)
	
	//logs3
	//Redwood			(0)
	//Willow			(1)
	//Dead				(2)
	//Giant Flower Stem (3)
	
	//logs4
	//Pine				(0)
	//Hellbark			(1)
	//Jacaranda			(2)

	private static final String[] types = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow", "dead", "bigflowerstem", "pine", "hellbark", "jacaranda"};
	private Icon[] textures;
	private Icon[] logHearts;

	private final LogCategory category;

	public BlockBOPLog(int blockID, LogCategory cat)
	{
		super(blockID, Material.wood);
		category = cat;
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[types.length];
		logHearts = new Icon[types.length];

		for (int i = 0; i < types.length; ++i)
		{
			if (i != 11)
			{
				textures[i] = iconRegister.registerIcon("biomesoplenty:log_"+types[i]+"_side");
				logHearts[i] = iconRegister.registerIcon("biomesoplenty:log_"+types[i]+"_heart");
			}
		}

		textures[11] = iconRegister.registerIcon("biomesoplenty:bigflowerstem_side");
		logHearts[11] = iconRegister.registerIcon("biomesoplenty:bigflowerstem_heart");
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		int pos = meta & 12;
		if (pos == 0 && (side == 1 || side == 0) || pos == 4 && (side == 5 || side == 4) || pos == 8 && (side == 2 || side == 3))
			return logHearts[(getTypeFromMeta(meta) + category.ordinal() * 4)];
		else
			return textures[(getTypeFromMeta(meta) + category.ordinal() * 4)];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		if (category != LogCategory.CAT4)
		{
			for (int i = 0; i < 4; ++i) {
				list.add(new ItemStack(this, 1, i));
			}
		}
		else
		{
			for (int i = 0; i < 3; ++i) {
				list.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		byte radius = 4;
		int bounds = radius + 1;

		if (world.checkChunksExist(x - bounds, y - bounds, z - bounds, x + bounds, y + bounds, z + bounds)) {
			for (int i = -radius; i <= radius; ++i) {
				for (int j = -radius; j <= radius; ++j) {
					for (int k = -radius; k <= radius; ++k)
					{
						int blockID = world.getBlockId(x + i, y + j, z + k);

						if (Block.blocksList[blockID] != null) {
							Block.blocksList[blockID].beginLeavesDecay(world, x + i, y + j, z + k);
						}
					}
				}
			}
		}
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		int type = getTypeFromMeta(meta);
		byte orientation = 0;

		switch (side)
		{
		case 0:
		case 1:
			orientation = 0;
			break;

		case 2:
		case 3:
			orientation = 8;
			break;

		case 4:
		case 5:
			orientation = 4;
		}

		return type | orientation;
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
	{
		if (category == LogCategory.CAT4 && metadata == 1)
			return 0;
		else
		{
			super.setBurnProperties(blockID, 5, 5);
			return blockFlammability[blockID];
		}
	}

	@Override
	public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face)
	{
		if (category == LogCategory.CAT4 && metadata == 1)
			return 0;
		else
			return blockFireSpreadSpeed[blockID];
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
	{
		if (category == LogCategory.CAT4 && metadata == 1)
			return false;
		else
			return getFlammability(world, x, y, z, metadata, face) > 0;
	}

	@Override
	public int damageDropped(int meta)
	{
		return getTypeFromMeta(meta);
	}

	@Override
	protected ItemStack createStackedBlock(int meta)
	{
		return new ItemStack(blockID, 1, getTypeFromMeta(meta));
	}

	@Override
	public int getRenderType()
	{
		return 31;
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean isWood(World world, int x, int y, int z)
	{
		return true;
	}

	public String getWoodType(int meta)
	{
		return types[getTypeFromMeta(meta) + category.ordinal() * 4];
	}

	private static int getTypeFromMeta(int meta)
	{
		return meta & 3;
	}
}
