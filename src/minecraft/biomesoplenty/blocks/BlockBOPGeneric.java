package biomesoplenty.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;

public class BlockBOPGeneric extends Block
{
	public enum BlockType
	{
		ASH_STONE, HARD_SAND, HARD_DIRT, HARD_ICE, HOLY_STONE, BAMBOO_THATCHING, DRIED_DIRT, CRAG_ROCK, MUD_BRICK, HOLY_DIRT, CRYSTAL;
	}

	private Icon texture;
	private BlockType type;

	public BlockBOPGeneric(int id, Material material, BlockType type)
	{
		super(id, material);
		this.type = type;
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);

		switch (type)
		{
		case ASH_STONE:
			setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ashStone");
			break;

		case BAMBOO_THATCHING:
			setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bambooThatching");
			break;

		case CRAG_ROCK:
			setHardness(1.0F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("cragRock");
			break;

		case DRIED_DIRT:
			setHardness(0.1F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("driedDirt");
			break;

		case HARD_DIRT:
			setHardness(0.9F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("hardDirt");
			break;

		case HARD_ICE:
			setHardness(0.75F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("hardIce");
			break;

		case HARD_SAND:
			setHardness(0.7F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("hardSand");
			break;

		case HOLY_STONE:
			setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("holyStone");
			break;

		case MUD_BRICK:
			setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mudBrick");
			break;

		case HOLY_DIRT:
			setHardness(0.6F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("holyDirt");
			break;

		case CRYSTAL:
			setHardness(0.15F).setResistance(5.0F).setLightValue(1.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("crystal");
			break;

		default:
			break;
		}
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		switch (type)
		{
		case ASH_STONE:
			texture = iconRegister.registerIcon("BiomesOPlenty:ashstone");
			break;

		case BAMBOO_THATCHING:
			texture = iconRegister.registerIcon("BiomesOPlenty:bamboothatching");
			break;

		case CRAG_ROCK:
			texture = iconRegister.registerIcon("BiomesOPlenty:cragrock");
			break;

		case DRIED_DIRT:
			texture = iconRegister.registerIcon("BiomesOPlenty:drieddirt");
			break;

		case HARD_DIRT:
			texture = iconRegister.registerIcon("BiomesOPlenty:harddirt");
			break;

		case HARD_ICE:
			texture = iconRegister.registerIcon("BiomesOPlenty:hardice");
			break;

		case HARD_SAND:
			texture = iconRegister.registerIcon("BiomesOPlenty:hardsand");
			break;

		case HOLY_STONE:
			texture = iconRegister.registerIcon("BiomesOPlenty:holystone");
			break;

		case MUD_BRICK:
			texture = iconRegister.registerIcon("BiomesOPlenty:mudbrick");
			break;

		case HOLY_DIRT:
			texture = iconRegister.registerIcon("BiomesOPlenty:holydirt");
			break;

		case CRYSTAL:
			texture = iconRegister.registerIcon("BiomesOPlenty:crystal");
			break;

		default:
			break;
		}
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		switch (type)
		{
		case CRYSTAL:
			return Items.miscItems.get().itemID;

		default:
			return blockID;
		}
	}

	@Override
	public int damageDropped(int meta)
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
	public Icon getIcon(int side, int meta)
	{
		return texture;
	}
}
