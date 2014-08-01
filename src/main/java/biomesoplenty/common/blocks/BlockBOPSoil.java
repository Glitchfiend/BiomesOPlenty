package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPSoil extends Block
{
	public static final String[] types = new String[] {"grass_silty", "dirt_silty", "grass_sandy", "dirt_sandy", "grass_loamy", "dirt_loamy"};
	private IIcon[][] icon = new IIcon[6][6];

	public BlockBOPSoil()
	{
		//TODO:	Material.grass
		super(Material.grass);
		
		//TODO: this.setHardness
		this.setHardness(0.6F);
		this.setHarvestLevel("shovel", 0);
		
		//TODO: setTickRandomly()
		this.setTickRandomly(true);
		//TODO setStepSound(Block.soundGrassFootstep)
		this.setStepSound(Block.soundTypeGrass);

		//setLightValue(0.25F);

		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.icon[0][0] = iconRegister.registerIcon("biomesoplenty:dirt_silty");
		this.icon[0][1] = iconRegister.registerIcon("minecraft:grass_top");
		this.icon[0][2] = iconRegister.registerIcon("biomesoplenty:grass_silty");
		this.icon[0][3] = iconRegister.registerIcon("biomesoplenty:grass_silty");
		this.icon[0][4] = iconRegister.registerIcon("biomesoplenty:grass_silty");
		this.icon[0][5] = iconRegister.registerIcon("biomesoplenty:grass_silty");
		
		this.icon[1][0] = iconRegister.registerIcon("biomesoplenty:dirt_silty");
		this.icon[1][1] = iconRegister.registerIcon("biomesoplenty:dirt_silty");
		this.icon[1][2] = iconRegister.registerIcon("biomesoplenty:dirt_silty");
		this.icon[1][3] = iconRegister.registerIcon("biomesoplenty:dirt_silty");
		this.icon[1][4] = iconRegister.registerIcon("biomesoplenty:dirt_silty");
		this.icon[1][5] = iconRegister.registerIcon("biomesoplenty:dirt_silty");
		
		this.icon[2][0] = iconRegister.registerIcon("biomesoplenty:dirt_sandy");
		this.icon[2][1] = iconRegister.registerIcon("minecraft:grass_top");
		this.icon[2][2] = iconRegister.registerIcon("biomesoplenty:grass_sandy");
		this.icon[2][3] = iconRegister.registerIcon("biomesoplenty:grass_sandy");
		this.icon[2][4] = iconRegister.registerIcon("biomesoplenty:grass_sandy");
		this.icon[2][5] = iconRegister.registerIcon("biomesoplenty:grass_sandy");
		
		this.icon[3][0] = iconRegister.registerIcon("biomesoplenty:dirt_sandy");
		this.icon[3][1] = iconRegister.registerIcon("biomesoplenty:dirt_sandy");
		this.icon[3][2] = iconRegister.registerIcon("biomesoplenty:dirt_sandy");
		this.icon[3][3] = iconRegister.registerIcon("biomesoplenty:dirt_sandy");
		this.icon[3][4] = iconRegister.registerIcon("biomesoplenty:dirt_sandy");
		this.icon[3][5] = iconRegister.registerIcon("biomesoplenty:dirt_sandy");
		
		this.icon[4][0] = iconRegister.registerIcon("biomesoplenty:dirt_loamy");
		this.icon[4][1] = iconRegister.registerIcon("minecraft:grass_top");
		this.icon[4][2] = iconRegister.registerIcon("biomesoplenty:grass_loamy");
		this.icon[4][3] = iconRegister.registerIcon("biomesoplenty:grass_loamy");
		this.icon[4][4] = iconRegister.registerIcon("biomesoplenty:grass_loamy");
		this.icon[4][5] = iconRegister.registerIcon("biomesoplenty:grass_loamy");
		
		this.icon[5][0] = iconRegister.registerIcon("biomesoplenty:dirt_loamy");
		this.icon[5][1] = iconRegister.registerIcon("biomesoplenty:dirt_loamy");
		this.icon[5][2] = iconRegister.registerIcon("biomesoplenty:dirt_loamy");
		this.icon[5][3] = iconRegister.registerIcon("biomesoplenty:dirt_loamy");
		this.icon[5][4] = iconRegister.registerIcon("biomesoplenty:dirt_loamy");
		this.icon[5][5] = iconRegister.registerIcon("biomesoplenty:dirt_loamy");
	}

	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= this.icon.length) meta = 1;
		if (side < 0 || side >= this.icon[meta].length) side = 1;
	    
		return this.icon[meta][side];
	}

	@Override
	//TODO:		getSubBlocks()
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < types.length; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	//TODO     damageDropped()
	public int damageDropped(int meta)
	{
		return (meta % 2 == 0) ? meta + 1 : meta;
	}
    
	@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		//TODO:		getCollisionBoundingBoxFromPool()
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

}
