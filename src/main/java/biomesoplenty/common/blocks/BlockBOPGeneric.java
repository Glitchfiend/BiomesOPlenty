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
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);

		switch (type)
		{
		case ASH_STONE:
			this.setHardness(1.0F);		
			this.setStepSound(Block.soundTypePiston);
			break;

		case CRAG_ROCK:
			this.setHardness(1.0F);
			this.setStepSound(soundTypeGravel);
			break;

		case DRIED_DIRT:
			this.setHardness(0.1F);		
			this.setHarvestLevel("pickaxe", 0);
			
			this.setStepSound(Block.soundTypePiston);
			break;

		case HARD_DIRT:
			this.setHardness(0.9F);
			this.setStepSound(Block.soundTypePiston);
			break;

		case HARD_ICE:
			this.setHardness(0.75F);		
			this.setStepSound(Block.soundTypePiston);
			break;

		case HARD_SAND:
			this.setHardness(0.7F);
			this.setHarvestLevel("shovel", 0);
			
			this.setStepSound(Block.soundTypeSand);
			break;

		case MUD_BRICK:
			this.setHardness(1.0F);
			this.setResistance(2.0F);	
			this.setStepSound(Block.soundTypePiston);
			break;

		case BIOME_BLOCK:
			this.setHardness(0.6F);
			this.setStepSound(soundTypeGlass);
			break;

		case CRYSTAL:
			this.setHardness(0.15F);
			this.setResistance(5.0F);
			this.setLightLevel(1.0F);
			this.setStepSound(Block.soundTypeGlass);
			break;

		default:
			break;
		}
	}

	@Override
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
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		switch (type)
		{
		case CRYSTAL:
			return BOPItemHelper.get("misc");

		default:
			return super.getItemDropped(metadata, random, fortune);
		}
	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune)
	{
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
			break;
			
		default:
			super.dropBlockAsItemWithChance(world, x, y, z, metadata, chance, fortune);
			break;
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
	public IIcon getIcon(int side, int meta)
	{
		return texture;
	}
}
