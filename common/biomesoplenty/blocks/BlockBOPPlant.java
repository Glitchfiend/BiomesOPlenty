package biomesoplenty.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.blocks.renderers.RenderUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPPlant extends BlockFlower implements IShearable
{
	private static final String[] plants = new String[] {"deadgrass", "desertgrass", "desertsprouts", "dunegrass", "holytallgrass", "thorn", "barley", "cattail", "reed", "cattailtop", "cattailbottom", "wildcarrot", "cactus", "witherwart"};
	private Icon[] textures;

	private static final int CATTAILTOP = 9;
	private static final int CATTAILBOTTOM = 10;

	public BlockBOPPlant(int par1)
	{
		super(par1, Material.vine);
		setTickRandomly(true);
		float var3 = 0.4F;
		setBurnProperties(blockID, 60, 100);
		setHardness(0.0F);
		setStepSound(Block.soundGrassFootstep);
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
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
		return RenderUtils.plantsModel;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
	{
		int meta = world.getBlockMetadata(par2, par3, par4);

		switch (meta)
		{
		case 6:
		case 7:
			this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.00F, 0.875F);
			break;

		default:
			this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
			break;
		}
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < plants.length; ++i) {
			if (i != CATTAILTOP && i!= CATTAILBOTTOM && i!= 11)
			{
				list.add(new ItemStack(blockID, 1, i));
			}
		}
	}

	protected boolean canThisPlantGrowOnThisBlockID(int blockID, int metadata)
	{
		//case 2: // Desert Sprouts

		if (metadata == 0) //Dead Grass
			return blockID == Blocks.driedDirt.get().blockID || blockID == Block.sand.blockID;
		else if (metadata == 1) //Desert Grass
			return blockID == Blocks.redRock.get().blockID;
		else if (metadata == 3) //Dune Grass
			return blockID == Block.sand.blockID;
		else if (metadata == 4) //Holy Tall Grass
			return blockID == Blocks.holyGrass.get().blockID;
		else if (metadata == 5)
			return blockID == Block.grass.blockID || blockID == Block.dirt.blockID || blockID == Block.slowSand.blockID;
		else if (metadata == 6)
			return blockID == Block.grass.blockID || blockID == Block.dirt.blockID;
		else if (metadata == 7)
			return blockID == Block.grass.blockID;
		else if (metadata == 8)
			return blockID == this.blockID || blockID == Block.grass.blockID;
		else if (metadata == 9)
			return blockID == this.blockID;
		else if (metadata == 12)
			return blockID == Block.sand.blockID || blockID == Blocks.redRock.get().blockID || blockID == Block.slowSand.blockID;
		else if (metadata == 13)
			return blockID == Block.slowSand.blockID;
		else
			return blockID == Block.grass.blockID || blockID == Block.dirt.blockID || blockID == Block.tilledField.blockID || blockID == Blocks.longGrass.get().blockID;
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == Blocks.driedDirt.get().blockID || id == Block.sand.blockID || id == Blocks.redRock.get().blockID || id == Blocks.holyGrass.get().blockID
				|| id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int id = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();

		if (itemStack.itemID == blockID) {
			switch (meta)
			{
			case 0: // Dead Grass
			return id == Blocks.driedDirt.get().blockID || id == Block.sand.blockID;

			case 1: // Desert Grass
				return id == Blocks.redRock.get().blockID;

			case 2: // Desert Sprouts
			case 3: // Dune Grass
				return id == Block.sand.blockID;

			case 4: // Holy Tall Grass
				return id == Blocks.holyGrass.get().blockID;

			case 5: // Thorns
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.slowSand.blockID;
				
			case 6: // Barley
				return id == Block.grass.blockID || id == Block.dirt.blockID;

			case 7: // Cattail
				return id != Block.grass.blockID ? false : (world.getBlockMaterial(x - 1, y - 1, z) == Material.water ? true : (world.getBlockMaterial(x + 1, y - 1, z) == Material.water ? true : (world.getBlockMaterial(x, y - 1, z - 1) == Material.water ? true : world.getBlockMaterial(x, y - 1, z + 1) == Material.water)));

			case 8: // Reed
				return id == blockID || id == Block.grass.blockID;

			case 10: // High Cattail Bottom
				return id != Block.grass.blockID ? false : (world.getBlockMaterial(x - 1, y - 1, z) == Material.water ? true : (world.getBlockMaterial(x + 1, y - 1, z) == Material.water ? true : (world.getBlockMaterial(x, y - 1, z - 1) == Material.water ? true : world.getBlockMaterial(x, y - 1, z + 1) == Material.water)));

			case 12: // Tiny Cactus
				return id == Block.sand.blockID || id == Blocks.redRock.get().blockID || id == Block.slowSand.blockID;
				
			case 13: // Antiwart
				return id == Block.slowSand.blockID;
				
			default:
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID;
			}
		} else
			return this.canPlaceBlockOnSide(world, x, y, z, side);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		int id = world.getBlockId(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
        Block block = Block.blocksList[id];

		if (world.getBlockId(x, y, z) != blockID)
		{
			if (meta == 5)
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
			else if (meta == 8)
				return block == null || block.isBlockReplaceable(world, x, y, z);
			else
				return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
		}
		else
		{
			if (meta == 5)
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
			else
				return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
	{
		super.onNeighborBlockChange(world, x, y, z, neighborID);
		this.checkFlowerChange(world, x, y, z);
		if (world.getBlockMetadata(x, y, z) == CATTAILTOP && world.getBlockId(x, y - 1, z) == blockID && world.getBlockMetadata(x, y - 1, z) != CATTAILBOTTOM) 
		{
			world.setBlockToAir(x, y, z);
		}
		else if (world.getBlockMetadata(x, y, z) == CATTAILBOTTOM && world.getBlockId(x, y + 1, z) != blockID) 
		{
			world.setBlockToAir(x, y, z);
		}
		else if (world.getBlockId(x, y, z) != blockID || world.getBlockMetadata(x, y, z) != 8) 
		{
			this.checkBlockCoordValid(world, x, y, z);
		}
	}
	
	protected final void checkBlockCoordValid(World world, int x, int y, int z)
	{
		for (int i = 1; world.getBlockId(x, y + i, z) == blockID; i++)
		{
			if (!this.canBlockStay(world, x, y + i, z))
			{
				this.dropBlockAsItem(world, x, y + i, z, world.getBlockMetadata(x, y + i, z), 0);
				world.setBlock(x, y + i, z, 0);
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 5)
		{
			entity.attackEntityFrom(DamageSource.cactus, 1);
		}
		if (meta == 12)
		{
			entity.attackEntityFrom(DamageSource.cactus, 1);
		}
	}
	
	@Override
    public int idPicked(World world, int x, int y, int z)
    {
		int blockID = world.getBlockId(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 11)
		{
			return Items.food.get().itemID;
		}
		
        return this.blockID;
    }

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == CATTAILTOP || meta == CATTAILBOTTOM) 
		{
			meta = 7;
		}
		else if (meta == 11) 
		{
			meta = 2;
		}
		
		return meta;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		if (par1 > 5 && par1 != 11)
		{
			return blockID;
		}
		else if (par1 == 11)
		{
			return Items.food.get().itemID;
		}
		else
		{
			return -1;
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 9)
		{
			return 7;
		}
		else if (meta == 11)
		{
			return 2;
		}
		else
		{
			return meta;
		}
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 6)
			return random.nextInt(5) == 0 ? 1 : 0;
		else if (meta == 7 || meta == 8 || meta == 9)
			return 1;
		else if (meta == 11)
			return random.nextInt(7) == 0 ? 2 : 1;
		else if (meta == 13)
			return 1;
		else
			return 0;
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		super.harvestBlock(world, player, x, y, z, meta);
		
		if (meta == 13)
		{
			player.addPotionEffect(new PotionEffect(Potion.wither.id, 250, 1));
		}
		
		ItemStack equippedItem = player.getCurrentEquippedItem();
		
		if (equippedItem != null)
		{
			if (equippedItem.itemID != Item.shears.itemID)
			{
				if (meta == 5)
				{
					player.attackEntityFrom(DamageSource.cactus, 2);
				}
			}
		}
		else
		{
			if (meta == 5)
			{
				player.attackEntityFrom(DamageSource.cactus, 2);
			}
		}
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
		if (meta == 13)
		{
            byte b0 = 3;

            for (int j1 = 0; j1 < b0; ++j1)
            {
                for (int k1 = 0; k1 < b0; ++k1)
                {
                    for (int l1 = 0; l1 < b0; ++l1)
                    {
                        double d0 = (double)x + ((double)j1 + 0.5D) / (double)b0;
                        double d1 = (double)y + ((double)k1 + 0.5D) / (double)b0;
                        double d2 = (double)z + ((double)l1 + 0.5D) / (double)b0;
                        world.spawnParticle("smoke", d0, d1, d2, d0 - (double)x - 0.5D, d1 - (double)y - 0.5D, d2 - (double)z - 0.5D);
                    }
                }
            }
		}
		
		return false;
    }

	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 5 || meta == 8)
			return false;
		return true;
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 7 || world.getBlockMetadata(x, y, z) == 8 || world.getBlockMetadata(x, y, z) == 9)
			return false;
		else
			return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		return ret;
	}

	@Override
	public boolean isBlockFoliage(World world, int x, int y, int z)
	{
		return true;
	}
}
