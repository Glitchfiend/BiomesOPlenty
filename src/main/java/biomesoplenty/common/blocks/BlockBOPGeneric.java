package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;

public class BlockBOPGeneric extends Block
{
	//TODO: FEATURE KILL THIS AWFUL CLASS
	
	public enum BlockType
	{
		ASH_STONE, HARD_SAND, HARD_DIRT, HARD_ICE, DRIED_DIRT, CRAG_ROCK, MUD_BRICK, HOLY_DIRT, CRYSTAL;
	}

	private IIcon texture;
	private BlockType type;

	public BlockBOPGeneric(Material material, BlockType type)
	{
		super(material);
		this.type = type;
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);

		switch (type)
		{
		case ASH_STONE:
			//TODO: this.setHardness
			this.func_149711_c(1.0F);		
			//TODO setStepSound(Block.soundStoneFootstep)
			this.func_149672_a(Block.field_149780_i);
			break;

		case CRAG_ROCK:
			//TODO: this.setHardness
			this.func_149711_c(1.0F);
			//TODO setStepSound(Block.soundGravelFootstep)
			this.func_149672_a(field_149767_g);
			break;

		case DRIED_DIRT:
			//TODO: this.setHardness
			this.func_149711_c(0.1F);		
			//TODO setStepSound(Block.soundStoneFootstep)
			this.func_149672_a(Block.field_149780_i);
			break;

		case HARD_DIRT:
			//TODO: this.setHardness
			this.func_149711_c(0.9F);
			//TODO setStepSound(Block.soundStoneFootstep)
			this.func_149672_a(Block.field_149780_i);
			break;

		case HARD_ICE:
			//TODO: this.setHardness
			this.func_149711_c(0.75F);		
			//TODO setStepSound(Block.soundStoneFootstep)
			this.func_149672_a(Block.field_149780_i);
			break;

		case HARD_SAND:
			//TODO: this.setHardness
			this.func_149711_c(0.7F);
			//TODO setStepSound(Block.soundSandFootstep)
			this.func_149672_a(Block.field_149776_m);
			break;

		case MUD_BRICK:
			//TODO: this.setHardness
			this.func_149711_c(1.0F);
			//TODO: this.setResistance
			this.func_149752_b(2.0F);	
			//TODO setStepSound(Block.soundStoneFootstep)
			this.func_149672_a(Block.field_149780_i);
			break;

		case HOLY_DIRT:
			//TODO: this.setHardness
			this.func_149711_c(0.6F);
			//TODO setStepSound(Block.soundGravelFootstep)
			this.func_149672_a(field_149767_g);
			break;

		case CRYSTAL:
			//TODO: this.setHardness
			this.func_149711_c(0.15F);
			//TODO: this.setResistance
			this.func_149752_b(5.0F);
			//TODO: this.setLightValue
			this.func_149715_a(1.0F); //I think?
			//TODO setStepSound(Block.soundGravelFootstep)
			this.func_149672_a(Block.field_149778_k);
			break;

		default:
			break;
		}
	}
	
	@Override
	//TODO:		  getUnlocalizedName()
	public String func_149739_a() 
	{
		return "tile.bop.generic" + "." + type.toString().toLowerCase();
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		switch (type)
		{
		case ASH_STONE:
			texture = iconRegister.registerIcon("biomesoplenty:ashstone");
			break;

		case CRAG_ROCK:
			texture = iconRegister.registerIcon("biomesoplenty:cragrock");
			break;

		case DRIED_DIRT:
			texture = iconRegister.registerIcon("biomesoplenty:drieddirt");
			break;

		case HARD_DIRT:
			texture = iconRegister.registerIcon("biomesoplenty:harddirt");
			break;

		case HARD_ICE:
			texture = iconRegister.registerIcon("biomesoplenty:hardice");
			break;

		case HARD_SAND:
			texture = iconRegister.registerIcon("biomesoplenty:hardsand");
			break;

		case MUD_BRICK:
			texture = iconRegister.registerIcon("biomesoplenty:mudbrick");
			break;

		case HOLY_DIRT:
			texture = iconRegister.registerIcon("biomesoplenty:holydirt");
			break;

		case CRYSTAL:
			texture = iconRegister.registerIcon("biomesoplenty:crystal");
			break;

		default:
			break;
		}
	}

	@Override
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		switch (type)
		{
		case CRYSTAL:
			return BOPItemHelper.get("misc");

		default:
			//TODO:		getItemForBlock()
			return Item.func_150898_a(this);
		}
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		switch (type)
		{
		case CRYSTAL:
			return 4;

		default:
			return meta;
		}
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		switch (type)
		{
		case CRYSTAL:
			return 4;

		default:
			return 1;
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		return texture;
	}
}
