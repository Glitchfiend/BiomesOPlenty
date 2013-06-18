package biomesoplenty.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCloth;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPConfiguration;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSoulManipulator extends Item
{
	private static String[] manipulatorTypes = {"soulmanipulator_empty", "soulmanipulator_ghastlysoul", "soulmanipulator_villager"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemSoulManipulator(int par1)
	{
		super(par1);
		maxStackSize = 1;
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
    public boolean isFull3D()
    {
		return true;
    }
	
	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving)
	{
		int itemDamage = par1ItemStack.getItemDamage(); 

		if (itemDamage == 2)    
		{
			if (par2EntityLiving instanceof EntityEnderman)
			{
				EntityEnderman entityenderman = (EntityEnderman)par2EntityLiving;

				if (entityenderman.worldObj.rand.nextInt(2) == 0)
				{
					entityenderman.attackEntityFrom(DamageSource.causePlayerDamage(Minecraft.getMinecraft().thePlayer), 1);
				}
				
				entityenderman.attackEntityFrom(DamageSource.causePlayerDamage(Minecraft.getMinecraft().thePlayer), 0);
				
				if (entityenderman.worldObj.rand.nextInt(6) == 0)
				{
                    EntityVillager entityvillager = new EntityVillager(entityenderman.worldObj);
                    entityvillager.setLocationAndAngles(entityenderman.posX, entityenderman.posY, entityenderman.posZ, MathHelper.wrapAngleTo180_float(entityenderman.worldObj.rand.nextFloat() * 360.0F), 0.0F);
                    entityvillager.rotationYawHead = entityvillager.rotationYaw;
                    entityvillager.renderYawOffset = entityvillager.rotationYaw;
                    
                    if (!entityenderman.worldObj.isRemote)
                    {                        	
                    	entityenderman.worldObj.spawnEntityInWorld(entityvillager);

    					FMLClientHandler.instance().getClient().sndManager.playSound("mob.endermen.death", (float) entityvillager.posX + 0.5F, (float) entityvillager.posY + 0.5F, (float) entityvillager.posZ + 0.5F, 5.0F, -8.0F);
                    	entityenderman.setDead();
                    }
                    
					if (!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode)
					{
						par1ItemStack.setItemDamage(0);
					}
                  
                	//entityvillager.playLivingSound();
					
					return true;
				}
			}
			else
			{
				return false;
			}
		}

		return false;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[manipulatorTypes.length];

		for (int i = 0; i < manipulatorTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+manipulatorTypes[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= manipulatorTypes.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + manipulatorTypes[meta];
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes)
	{
		for(int meta = 0; meta < manipulatorTypes.length; ++meta) {
			subTypes.add(new ItemStack(itemId, 1, meta));
		}
	}
}