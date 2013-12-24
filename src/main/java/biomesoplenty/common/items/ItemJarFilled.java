package biomesoplenty.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemJarFilled extends Item
{
	private static String[] jars = {"jarhoney", "jarpoison", "jarpixie"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemJarFilled()
	{
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
		
		this.setHasSubtypes(true);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[jars.length];

		for (int i = 0; i < jars.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+jars[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= jars.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + jars[meta];
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
    //TODO: public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    public void func_150895_a(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int meta = 0; meta < jars.length; ++meta) 
		{
			list.add(new ItemStack(item, 1, meta));
		}
	}
	
    /* TODO: FEATURE @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float xoffset, float yoffset, float zoffset)
    {
            if (itemStack.getItemDamage() == 2)
            {
                if (entityPlayer.dimension == 0 || entityPlayer.dimension == BOPConfigurationIDs.promisedLandDimID)
                {
                    int i1 = world.getBlockId(x, y, z);
                    x += Facing.offsetsXForSide[side];
                    y += Facing.offsetsYForSide[side];
                    z += Facing.offsetsZForSide[side];
                    double d0 = 0.0D;

                    if (side == 1 && Block.blocksList[i1] != null && Block.blocksList[i1].getRenderType() == 11)
                    {
                        d0 = 0.5D;
                    }

                    if (!world.isRemote)
                    {
                        EntityPixie entity = new EntityPixie(world);
                        entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
                        entity.rotationYawHead = entity.rotationYaw;
                        entity.renderYawOffset = entity.rotationYaw;
                        entity.onSpawnWithEgg((EntityLivingData)null);
                        world.spawnEntityInWorld(entity);
                        entity.playLivingSound();

                        if (itemStack.hasDisplayName())
                        {
                            ((EntityLiving)entity).setCustomNameTag(itemStack.getDisplayName());
                        }
                    }

                    if (!entityPlayer.capabilities.isCreativeMode)
                    {
                        --itemStack.stackSize;

                        if (itemStack.stackSize <= 0)
                        {
                            itemStack = new ItemStack(Items.jarEmpty.get(), 1, 0);
                        }

                        if (!entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.jarEmpty.get(), 1, 0)))
                        {
                            entityPlayer.dropPlayerItem(new ItemStack(Items.jarEmpty.get(), 1, 0));
                        }
                    }

                }
                else
                {
                    if (!world.isRemote)
                    {
                        entityPlayer.addChatMessage("\u00a75Pixies cannot survive in this environment!");
                    }
                }
            }

            return true;
        }*/
    }
