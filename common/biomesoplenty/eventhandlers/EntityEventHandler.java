package biomesoplenty.eventhandlers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPAchievements;
import biomesoplenty.configuration.configfile.BOPConfigurationIDs;
import biomesoplenty.entities.ai.EntityAITemptArmour;
import biomesoplenty.helpers.TeleporterPromised;
import biomesoplenty.world.WorldTypeBOP;
import cpw.mods.fml.client.FMLClientHandler;

public class EntityEventHandler
{
	@ForgeSubscribe
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{
		Entity entity = event.entity;

		if (!(entity instanceof EntityLiving))
			return;

		if (entity instanceof EntityChicken) {
			((EntityLiving)entity).tasks.addTask(3, new EntityAITemptArmour((EntityCreature) entity, 0.25F, Items.flowerBand.get().itemID, 0, false));
		}

		if (entity instanceof EntitySheep) {
			((EntityLiving)entity).tasks.addTask(3, new EntityAITemptArmour((EntityCreature) entity, 0.25F, Items.flowerBand.get().itemID, 1, false));
		}

		if (entity instanceof EntityPig) {
			((EntityLiving)entity).tasks.addTask(4, new EntityAITemptArmour((EntityCreature) entity, 0.25F, Items.flowerBand.get().itemID, 2, false));
		}

		if (entity instanceof EntityCow) {
			((EntityLiving)entity).tasks.addTask(3, new EntityAITemptArmour((EntityCreature) entity, 0.25F, Items.flowerBand.get().itemID, 3, false));
		}
	}

	//Currently unused, as mobs don't spawn in the Promised Lands *yet*
	/*@ForgeSubscribe
	public void canEntitySpawn(CheckSpawn event)
	{
		if (event.entityLiving instanceof EntityAnimal)
		{
			int i = MathHelper.floor_double(event.entityLiving.posX);
			int j = MathHelper.floor_double(event.entityLiving.boundingBox.minY);
			int k = MathHelper.floor_double(event.entityLiving.posZ);

			if (event.entityLiving. <= event.world.getEntitiesWithinAABB(event.entityLiving.getClass(), AxisAlignedBB.getAABBPool().getAABB(i - 16, j - 4, k - 16, i + 17, j + 5, k + 17)).size())
				return;

			if (event.entityLiving.worldObj.getBlockId(i, j - 1, k) == Blocks.holyGrass.get().blockID && event.entityLiving.worldObj.getFullBlockLightValue(i, j, k) > 8
					&& event.entityLiving.worldObj.checkNoEntityCollision(event.entity.boundingBox) && !event.entityLiving.worldObj.isAnyLiquid(event.entityLiving.boundingBox)
					&& event.entityLiving.worldObj.getCollidingBoundingBoxes(event.entityLiving, event.entityLiving.boundingBox).isEmpty()) {
				event.setResult(Result.ALLOW);
			}
		}
	}*/

	@ForgeSubscribe
	public void fallingFromPromisedLand(LivingHurtEvent event)
	{
		if (event.source == DamageSource.outOfWorld && event.entityLiving.dimension == BOPConfigurationIDs.promisedLandDimID)
		{
			event.setCanceled(true);

			if (!event.entityLiving.worldObj.isRemote && !event.entityLiving.isDead)
			{
				if (event.entityLiving instanceof EntityPlayerMP)
				{
					EntityPlayerMP thePlayer = (EntityPlayerMP) event.entityLiving;
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterPromised(thePlayer.mcServer.worldServerForDimension(0), true));
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void entityInteract(EntityInteractEvent event)
	{
		ItemStack itemstack = event.entityPlayer.getCurrentEquippedItem();
		Entity entity = event.target;
		EntityPlayer player = event.entityPlayer;
		
		if (itemstack != null)
		{
			int itemDamage = itemstack.getItemDamage();
			
			if (itemstack.itemID == Items.miscItems.get().itemID && (itemDamage == 5 || itemDamage == 6 || itemDamage == 7 || itemDamage == 8 || itemDamage == 9))    
			{
				int dyeMeta = convertToDyeMeta(itemDamage);      
				int i = BlockColored.getBlockFromDye(dyeMeta);

				if (entity instanceof EntityWolf)
				{
					EntityWolf entitywolf = (EntityWolf)entity;

					if (i != entitywolf.getCollarColor())
					{
						entitywolf.setCollarColor(i)
						;
						if (!event.entityPlayer.capabilities.isCreativeMode)
						{
							--itemstack.stackSize;
						}

						event.setResult(Result.ALLOW);
					}
				}
				else if (entity instanceof EntitySheep)
				{
					EntitySheep entitysheep = (EntitySheep)entity;

					if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != i)
					{
						entitysheep.setFleeceColor(i);

						if (!event.entityPlayer.capabilities.isCreativeMode)
						{
							--itemstack.stackSize;
						}
					}

					event.setResult(Result.ALLOW);
				}
			}
		}
	}

	private int convertToDyeMeta(int meta)
	{
		switch (meta)
		{
		case 5:
			return 4;

		case 6:
			return 3;

		case 7:
			return 2;

		case 8:
			return 15;

		case 9:
			return 0;

		default:
			return 0;
		}
	}
	
    public boolean isBlockInBB(World world, AxisAlignedBB par1AxisAlignedBB, int blockID, int blockMeta)
    {
        int i = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int j = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
        int k = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int l = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
        int i1 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int j1 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);

        for (int k1 = i; k1 < j; ++k1)
        {
            for (int l1 = k; l1 < l; ++l1)
            {
                for (int i2 = i1; i2 < j1; ++i2)
                {
                    Block block = Block.blocksList[world.getBlockId(k1, l1, i2)];

                    if (block != null && block.blockID == blockID && world.getBlockMetadata(k1, l1, i2) == blockMeta)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
