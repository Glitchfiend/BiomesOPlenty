package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.content.BOPCBlocks;

public class BlockBOPSlab extends BlockSlab
{
	public static enum SlabCategory
	{
		WOOD1, WOOD2, STONE;
	}
	
	private static final String[] woodTypes = new String[] {"sacredoak", "cherry", "dark", "fir", "ethereal", "magic", "mangrove", "palm", "redwood", "willow", "pine", "hell_bark", "jacaranda", "mahogany"};
	private static final String[] rockTypes = new String[] {"mudbrick"};
	private IIcon[] textures;

	private final SlabCategory category;

	public BlockBOPSlab(boolean isDoubleSlab, Material material, SlabCategory cat)
	{
		super(isDoubleSlab, material);

		category = cat;
		
		//TODO: 		Material.wood
		if (material == Material.wood)
		{
			//TODO: this.setHardness
			this.setHardness(2.0F);
			//TODO: this.setResistance
			this.setResistance(5.0F);
			//TODO setStepSound(Block.soundWoodFootstep)
			this.setStepSound(Block.soundTypeWood);
		}
		//TODO: 			Material.rock
		else if (material == Material.rock) 
		{
			//TODO setStepSound(Block.soundStoneFootstep)
			this.setStepSound(Block.soundTypePiston);
		}

		if (!isDoubleSlab) 
		{
			//TODO: this.setCreativeTab()
			this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		}
		
		//TODO: useNeighborBrightness?
		useNeighborBrightness = true;
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		if (category == SlabCategory.STONE)
		{
			textures = new IIcon[rockTypes.length];

			for (int i = 0; i < rockTypes.length; ++i) 
			{
				textures[i] = iconRegister.registerIcon("biomesoplenty:"+rockTypes[i]);
			}
		}
		else
		{
			textures = new IIcon[woodTypes.length];

			for (int i = 0; i < woodTypes.length; ++i) 
			{
				textures[i] = iconRegister.registerIcon("biomesoplenty:plank_"+woodTypes[i]);
			}
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
		if (category == SlabCategory.STONE)
			return textures[getRockType(meta)];
		else
			return textures[getWoodType(meta)];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	//TODO:		getSubBlocks()
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		int max = 0;

		if (category == SlabCategory.WOOD1) {
			max = 8;
		} else if (category == SlabCategory.WOOD2) {
			max = 5;
		} else if (category == SlabCategory.STONE) {
			max = 1;
		}

		for (int i = 0; i < max; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	//TODO:		  getFullSlabName()
	public String func_150002_b(int meta)
	{
		if (category == SlabCategory.STONE)
			return (new StringBuilder()).append(rockTypes[getRockType(meta)]).append("Slab").toString();
		else
			return (new StringBuilder()).append(woodTypes[getWoodType(meta)]).append("Slab").toString();
	}

	@Override
	//TODO     damageDropped()
	public int damageDropped(int meta)
	{
		return meta & 7;
	}

	@Override
	//TODO:	   getItemDropped()
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		//TODO: isDoubleSlab
		if (field_150004_a)
		{
			if (this == BOPCBlocks.woodenDoubleSlab1)
				//TODO:		getItemFromBlock()
				return Item.getItemFromBlock(BOPCBlocks.woodenSingleSlab1);
			if (this == BOPCBlocks.woodenDoubleSlab2)
				//TODO:		getItemFromBlock()
				return Item.getItemFromBlock(BOPCBlocks.woodenSingleSlab2);
			else
				//TODO:		getItemFromBlock()
				return Item.getItemFromBlock(BOPCBlocks.stoneSingleSlab);
		}
		else
			//TODO:		getItemForBlock()
			return Item.getItemFromBlock(this);
	}

	@Override
	//TODO:		 getBlockHardness()
	public float getBlockHardness(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		//TODO:			 blockHardness
		float hardness = blockHardness;

		if (category == SlabCategory.STONE)
		{
			switch (getTypeFromMeta(meta))
			{
			case 0:
			case 3:
				hardness = 1.6F;
				break;

			case 1:
			case 4:
				hardness = 1.1F;
				break;

			case 2:
				hardness = 1.0F;
				break;
			}
		}

		return hardness;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		//TODO:			   blockResistance
		float resistance = blockResistance;

		if (category == SlabCategory.STONE)
		{
			switch (getTypeFromMeta(meta))
			{
			case 0:
			case 3:
				resistance = 7.0F;
				break;

			case 1:
			case 4:
				resistance = 7.5F;
				break;

			case 2:
				resistance = 2.0F;
				break;
			}
		}

		return resistance / 5.0F;
	}

	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
		//TODO:  	isDoubleSlab		   getItemFromBlock()
		Block block = !field_150004_a ? this : (this == BOPCBlocks.woodenDoubleSlab1 ? BOPCBlocks.woodenSingleSlab1 : (this == BOPCBlocks.woodenDoubleSlab2 ? BOPCBlocks.woodenSingleSlab2 : BOPCBlocks.stoneSingleSlab));
		
		return new ItemStack(block, 1, world.getBlockMetadata(x, y, z));
	}

	@Override
	//TODO:				createStackedBlock()
	protected ItemStack createStackedBlock(int meta)
	{
		return new ItemStack(this, 2, meta);
	}

	private int getWoodType(int meta)
	{
		meta = getTypeFromMeta(meta) + category.ordinal() * 8;
		if (meta < woodTypes.length)
			return meta;

		return 0;
	}
	
	private int getRockType(int meta)
	{
	    meta = getTypeFromMeta(meta);
	    if (meta < rockTypes.length)
	        return meta;
	    
	    return 0;
	}

	private static int getTypeFromMeta(int meta)
	{
		return meta & 7;
	}
}
