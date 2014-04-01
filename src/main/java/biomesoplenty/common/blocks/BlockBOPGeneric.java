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
	public enum BlockType
	{
		ASH_STONE, HARD_SAND, HARD_DIRT, HARD_ICE, DRIED_DIRT, CRAG_ROCK, MUD_BRICK, HOLY_DIRT, CRYSTAL, CAKE;
	}

	private IIcon texture;
	private BlockType type;

	public BlockBOPGeneric(Material material, BlockType type)
	{
		super(material);
		this.type = type;
		
		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);

		switch (type)
		{
		case ASH_STONE:
			//TODO: this.setHardness
			this.setHardness(1.0F);		
			//TODO setStepSound(Block.soundStoneFootstep)
			this.setStepSound(Block.soundTypePiston);
			break;

		case CRAG_ROCK:
			//TODO: this.setHardness
			this.setHardness(1.0F);
			//TODO setStepSound(Block.soundGravelFootstep)
			this.setStepSound(soundTypeGravel);
			break;

		case DRIED_DIRT:
			//TODO: this.setHardness
			this.setHardness(0.1F);		
			this.setHarvestLevel("pickaxe", 0);
			
			//TODO setStepSound(Block.soundStoneFootstep)
			this.setStepSound(Block.soundTypePiston);
			break;

		case HARD_DIRT:
			//TODO: this.setHardness
			this.setHardness(0.9F);
			//TODO setStepSound(Block.soundStoneFootstep)
			this.setStepSound(Block.soundTypePiston);
			break;

		case HARD_ICE:
			//TODO: this.setHardness
			this.setHardness(0.75F);		
			//TODO setStepSound(Block.soundStoneFootstep)
			this.setStepSound(Block.soundTypePiston);
			break;

		case HARD_SAND:
			//TODO: this.setHardness
			this.setHardness(0.7F);
			this.setHarvestLevel("shovel", 0);
			
			//TODO setStepSound(Block.soundSandFootstep)
			this.setStepSound(Block.soundTypeSand);
			break;

		case MUD_BRICK:
			//TODO: this.setHardness
			this.setHardness(1.0F);
			//TODO: this.setResistance
			this.setResistance(2.0F);	
			//TODO setStepSound(Block.soundStoneFootstep)
			this.setStepSound(Block.soundTypePiston);
			break;

		case HOLY_DIRT:
			//TODO: this.setHardness
			this.setHardness(0.6F);
			//TODO setStepSound(Block.soundGravelFootstep)
			this.setStepSound(soundTypeGravel);
			break;

		case CRYSTAL:
			//TODO: this.setHardness
			this.setHardness(0.15F);
			//TODO: this.setResistance
			this.setResistance(5.0F);
			//TODO: this.setLightValue
			this.setLightLevel(1.0F);
			//TODO setStepSound(Block.soundGravelFootstep)
			this.setStepSound(Block.soundTypeGlass);
			break;
			
		case CAKE:
			//TODO: this.setHardness
			this.setHardness(0.3F);
			//TODO setStepSound(Block.soundGravelFootstep)
			this.setStepSound(soundTypeSnow);
			break;

		default:
			break;
		}
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
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
			
		case CAKE:
			texture = iconRegister.registerIcon("biomesoplenty:cakeblock_bottom");
			break;

		default:
			break;
		}
	}

	@Override
	//TODO:	   getItemDropped()
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		switch (type)
		{
		case CRYSTAL:
			return BOPItemHelper.get("misc");

		default:
			//TODO:		getItemForBlock()
			return Item.getItemFromBlock(this);
		}
	}

	@Override
	//TODO     damageDropped()
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
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
		return texture;
	}
}
