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
		//TODO:	Material.grass
		super(Material.field_151577_b);
		
		//TODO: this.setHardness
		this.func_149711_c(0.6F);
		this.setHarvestLevel("shovel", 0);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.func_149672_a(Block.field_149779_h);
		
		//TODO: setTickRandomly()
		this.func_149675_a(true);

		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		this.icons[0] = iconRegister.registerIcon("biomesoplenty:longgrass3");
		this.icons[1] = iconRegister.registerIcon("biomesoplenty:longgrass1");
		this.icons[2] = iconRegister.registerIcon("biomesoplenty:longgrass2");
		this.icons[3] = iconRegister.registerIcon("biomesoplenty:longgrass2");
		this.icons[4] = iconRegister.registerIcon("biomesoplenty:longgrass2");
		this.icons[5] = iconRegister.registerIcon("biomesoplenty:longgrass2");
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
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
	//TODO:			onBlockActivated()
	public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitVecX, float hitVecY, float hitVecZ)
	{
		if (player.getCurrentEquippedItem() != null)
		{
			if (player.getCurrentEquippedItem().getDisplayName().toLowerCase().contains(" hoe"))
			{
				Block tilledField = Blocks.farmland;

				//TODO:													  stepSound.getPlaceSound()				stepSound.getVolume()						stepSound.getPitch()
				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, tilledField.field_149762_H.func_150496_b(), (tilledField.field_149762_H.func_150497_c() + 1.0F) / 2.0F, tilledField.field_149762_H.func_150494_d() * 0.8F);

				if (!world.isRemote)
				{
					//TODO: setBlock()
					world.func_147465_d(x, y, z, tilledField, 0, 2);
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
	//TODO:		updateTick()
	public void func_149674_a(World world, int x, int y, int z, Random random)
	{
		if (!world.isRemote)
		{
			if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
			{
				//TODO: setBlock()
				world.func_147465_d(x, y, z, Blocks.dirt, 0, 2);
			}
			else if (world.getBlockLightValue(x, y + 1, z) >= 9)
			{
				for (int var6 = 0; var6 < 4; ++var6)
				{
					int rX = x + random.nextInt(3) - 1;
					int rY = y + random.nextInt(5) - 3;
					int rZ = z + random.nextInt(3) - 1;
					//TODO:			    getBlock()
                    Block block = world.func_147439_a(rX, rY + 1, rZ);

                    //TODO:	  getBlock()
					if (world.func_147439_a(rX, rY, rZ) == Blocks.dirt && world.getBlockLightValue(rX, rY + 1, rZ) >= 4 && world.getBlockLightOpacity(rX, rY + 1, rZ) <= 2)
					{
						//TODO: setBlock()
						world.func_147465_d(rX, rY, rZ, this, 0, 2);
					}
				}
			}
		}
	}

	@Override
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		//TODO:			   getItemDropped()
		return Blocks.dirt.func_149650_a(0, random, fortune);
	}
}
