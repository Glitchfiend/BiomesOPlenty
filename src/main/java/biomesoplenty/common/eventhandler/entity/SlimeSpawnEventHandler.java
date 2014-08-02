package biomesoplenty.common.eventhandler.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * A temporary (hopefully) measure to allow slimes to spawn
 * in biomes other than the swampland
 */
public class SlimeSpawnEventHandler 
{
	@SubscribeEvent
	public void onCheckEntitySpawn(LivingSpawnEvent.CheckSpawn event)
	{
		World world = event.world;
		EntityLivingBase entity = event.entityLiving;
		
		if (event.entityLiving instanceof EntitySlime)
		{
            BiomeGenBase biome = world.getBiomeGenForCoords(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posZ));
            
            if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SWAMP))
            {
            	if (canSlimeSpawn((EntitySlime)entity)) event.setResult(Result.ALLOW);
            }
		}
	}
	
	private boolean canSlimeSpawn(EntitySlime slime)
	{
        if (!slime.worldObj.getWorldInfo().getTerrainType().handleSlimeSpawnReduction(slime.getRNG(), slime.worldObj))
        {
            if (slime.getSlimeSize() == 1 || slime.worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
            {
                if (slime.posY > 50.0D && slime.posY < 70.0D && slime.getRNG().nextFloat() < 0.5F && slime.getRNG().nextFloat() < slime.worldObj.getCurrentMoonPhaseFactor() && slime.worldObj.getBlockLightValue(MathHelper.floor_double(slime.posX), MathHelper.floor_double(slime.posY), MathHelper.floor_double(slime.posZ)) <= slime.getRNG().nextInt(8))
                {
                    return getCanSpawnHere(slime);
                }

            }
        }
        
        return false;
	}
	
    private boolean getCanSpawnHere(EntityLiving entity)
    {
        return entity.worldObj.checkNoEntityCollision(entity.boundingBox) && entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox).isEmpty() && !entity.worldObj.isAnyLiquid(entity.boundingBox);
    }
}
