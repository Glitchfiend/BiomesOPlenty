package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.api.content.BOPCBiomes;

public class BlockBOPGeneric extends Block
{
	public enum BlockType
	{
		ASH_STONE, HARD_SAND, HARD_DIRT, HARD_ICE, DRIED_DIRT, CRAG_ROCK, MUD_BRICK, BIOME_BLOCK, CRYSTAL;
	}

	private IIcon texture;
	private BlockType type;

	public BlockBOPGeneric(Material material, BlockType type)
	{
		super(material);
		this.setHarvestLevel("pickaxe", 3, 7);
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

		case BIOME_BLOCK:
			//TODO: this.setHardness
			this.setHardness(0.6F);
			//TODO setStepSound(Block.soundGravelFootstep)
			this.setStepSound(soundTypeGlass);
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

		case BIOME_BLOCK:
			texture = iconRegister.registerIcon("biomesoplenty:biomeblock");
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
	//TODO: 	dropBlockAsItemWithChance()
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune)
	{
		if (world.isRemote)
			return;

		switch (type)
		{
		case BIOME_BLOCK:
			for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
			{
			    if (biome != null)
			    {
			    	if (biome != BOPCBiomes.boneyard && biome != BOPCBiomes.visceralHeap && biome != BOPCBiomes.undergarden && biome != BOPCBiomes.corruptedSands && biome != BOPCBiomes.phantasmagoricInferno && biome != BOPCBiomes.lushRiver && biome != BOPCBiomes.dryRiver && biome != BiomeGenBase.beach && biome != BiomeGenBase.coldBeach && biome != BiomeGenBase.stoneBeach && biome != BiomeGenBase.frozenOcean && biome != BiomeGenBase.frozenRiver && biome != BiomeGenBase.hell && biome != BiomeGenBase.river && biome != BiomeGenBase.sky && biome != BiomeGenBase.ocean && biome != BiomeGenBase.deepOcean)
			    	{
				        ItemStack biomeEssence = new ItemStack(BOPItemHelper.get("biomeEssence"));
	
				        biomeEssence.setTagCompound(new NBTTagCompound());
	
				        biomeEssence.getTagCompound().setInteger("biomeID", biome.biomeID);
				        
				        if (world.rand.nextInt(75) == 0)
				        {
				        	this.dropBlockAsItem(world, x, y, z, biomeEssence);
				        }
			    	}
			    }
			}
			
		default:
			break;
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
