package biomesoplenty.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import biomesoplenty.BiomesOPlenty;

public class BlockHive extends Block
{
	private static final String[] hiveTypes = new String[] {"honeycomb", "honeycombspawner", "hive", "hivespawner"};
	private Icon[] textures;
	
	public BlockHive(int par1)
	{
		super(par1, Material.wood);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
    	return new TileEntityMobSpawner();
    }

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[hiveTypes.length];

		for (int i = 0; i < hiveTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+hiveTypes[i]);
		}
	}
	
	@Override
	public Icon getIcon(int side, int meta)
	{
		return textures[meta];
	}
	
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < hiveTypes.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
	
	@Override
	public int damageDropped(int meta)
	{
		if (meta == 1) {
			meta = 0;
		}
		if (meta == 3) {
			meta = 2;
		}
		
		return meta;
	}
	
	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
	{
		super.setBurnProperties(blockID, 2, 4);
		return blockFlammability[blockID];
	}

	@Override
	public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face)
	{
		return blockFireSpreadSpeed[blockID];
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
	{
		return getFlammability(world, x, y, z, metadata, face) > 0;
	}
}