package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;

public class BlockLongGrass extends Block
{
	private IIcon[] icons = new IIcon[6];

	public BlockLongGrass()
	{
		super(Material.grass);
		
		this.setHardness(0.6F);
		this.setHarvestLevel("shovel", 0);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.icons[0] = iconRegister.registerIcon("biomesoplenty:longgrass3");
		this.icons[1] = iconRegister.registerIcon("biomesoplenty:longgrass1");
		this.icons[2] = iconRegister.registerIcon("biomesoplenty:longgrass2");
		this.icons[3] = iconRegister.registerIcon("biomesoplenty:longgrass2");
		this.icons[4] = iconRegister.registerIcon("biomesoplenty:longgrass2");
		this.icons[5] = iconRegister.registerIcon("biomesoplenty:longgrass2");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
	    if (side < 0 || side >= this.icons.length) side = 1;
        
		return this.icons[side];
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitVecX, float hitVecY, float hitVecZ)
	{
		if (player.getCurrentEquippedItem() != null)
		{
			if (player.getCurrentEquippedItem().getDisplayName().toLowerCase().contains(" hoe"))
			{
				Block tilledField = Blocks.farmland;

				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, tilledField.stepSound.func_150496_b(), (tilledField.stepSound.getVolume() + 1.0F) / 2.0F, tilledField.stepSound.getPitch() * 0.8F);

				if (!world.isRemote)
				{
					world.setBlock(x, y, z, tilledField, 0, 2);
				}
				
				return true;
			} 
			else
			{
				return false;
			}
		} 
		else
		{
			return false;
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (!world.isRemote)
		{
			if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
			{
				world.setBlock(x, y, z, Blocks.dirt, 0, 2);
			}
			else if (world.getBlockLightValue(x, y + 1, z) >= 9)
			{
				for (int var6 = 0; var6 < 4; ++var6)
				{
					int rX = x + random.nextInt(3) - 1;
					int rY = y + random.nextInt(5) - 3;
					int rZ = z + random.nextInt(3) - 1;
                    Block block = world.getBlock(rX, rY + 1, rZ);

					if (world.getBlock(rX, rY, rZ) == Blocks.dirt && world.getBlockLightValue(rX, rY + 1, rZ) >= 4 && world.getBlockLightOpacity(rX, rY + 1, rZ) <= 2)
					{
						world.setBlock(rX, rY, rZ, this, 0, 2);
					}
				}
			}
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return Blocks.dirt.getItemDropped(0, random, fortune);
	}
}
