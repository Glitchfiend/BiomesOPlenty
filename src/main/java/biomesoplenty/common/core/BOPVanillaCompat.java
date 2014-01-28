package biomesoplenty.common.core;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.BlockDispenser;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ChestGenHooks;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.entities.projectiles.dispenser.DispenserBehaviourDart;
import biomesoplenty.common.entities.projectiles.dispenser.DispenserBehaviourMudball;
import biomesoplenty.common.world.decoration.ForcedDecorators;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class BOPVanillaCompat
{
    private static Random random = new Random();
    
	public static void init()
	{
		registerDispenserBehaviours();
		addDungeonLoot();
		addBonemealFlowers();
	}
	
	private static void registerDispenserBehaviours()
	{
		//TODO:			dispenseBehaviourRegistry
		BlockDispenser.field_149943_a.putObject(BOPItemHelper.get("mudball"), new DispenserBehaviourMudball());
		//TODO:			dispenseBehaviourRegistry
		BlockDispenser.field_149943_a.putObject(BOPItemHelper.get("dart"), new DispenserBehaviourDart());
	}
	
	private static void addDungeonLoot()
	{
		ChestGenHooks desertTemple = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
		ChestGenHooks dungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
		ChestGenHooks jungleTemple = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
		ChestGenHooks mineshaft = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
		ChestGenHooks strongholdCorridor = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR);
		ChestGenHooks strongholdCrossing = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING);
		ChestGenHooks strongholdLibrary = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
		ChestGenHooks village = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);
		ChestGenHooks bonusChest = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);

		if (BOPConfigurationMisc.dungeonLoot == true)
		{
			mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPItemHelper.get("misc"), 1, 1), 2, 8, 25));
			mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlockHelper.get("plants"), 1, 5), 4, 6, 15));

			strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlockHelper.get("flowers"), 1, 3), 1, 4, 25));
			strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlockHelper.get("flowers"), 1, 2), 1, 4, 25));

			strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlockHelper.get("flowers"), 1, 3), 1, 4, 25));
			strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlockHelper.get("flowers"), 1, 2), 1, 4, 25));

			village.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlockHelper.get("plants"), 1, 5), 2, 6, 25));
			village.addItem(new WeightedRandomChestContent(new ItemStack(BOPItemHelper.get("misc"), 1, 1), 2, 8, 25));
			village.addItem(new WeightedRandomChestContent(new ItemStack(BOPItemHelper.get("wadingBoots"), 1, 0), 1, 1, 5));
			village.addItem(new WeightedRandomChestContent(new ItemStack(BOPItemHelper.get("flippers"), 1, 0), 1, 1, 5));
			
			bonusChest.addItem(new WeightedRandomChestContent(new ItemStack(BOPItemHelper.get("wadingBoots"), 1, 0), 1, 1, 5));
			bonusChest.addItem(new WeightedRandomChestContent(new ItemStack(BOPItemHelper.get("flippers"), 1, 0), 1, 1, 5));
			
			for (BiomeGenBase biome : BiomeGenBase.func_150565_n())
			{
			    if (biome != null)
			    {
			        ItemStack biomeEssence = new ItemStack(BOPItemHelper.get("biomeEssence"));

			        biomeEssence.setTagCompound(new NBTTagCompound());

			        biomeEssence.getTagCompound().setInteger("biomeID", biome.biomeID);

			        desertTemple.addItem(new WeightedRandomChestContent(biomeEssence, 1, 1, 1));
			        dungeon.addItem(new WeightedRandomChestContent(biomeEssence, 1, 1, 1));
			        jungleTemple.addItem(new WeightedRandomChestContent(biomeEssence, 1, 1, 2));
			        mineshaft.addItem(new WeightedRandomChestContent(biomeEssence, 1, 1, 1));
			        strongholdCorridor.addItem(new WeightedRandomChestContent(biomeEssence, 1, 1, 1));
			        strongholdCrossing.addItem(new WeightedRandomChestContent(biomeEssence, 1, 1, 1));
			        strongholdLibrary.addItem(new WeightedRandomChestContent(biomeEssence, 1, 1, 3));
			        bonusChest.addItem(new WeightedRandomChestContent(biomeEssence, 1, 1, 1));
			    }
			}
		}
	}
	
	private static void addBonemealFlowers()
	{
	    //TODO:                                getBiomeGenArray()
	    for (BiomeGenBase biome : BiomeGenBase.func_150565_n())
	    {
	        if (biome != null)
	        {
	            IBOPDecoration bopDecoration = null;

	            if (biome instanceof IBOPDecoration)
	            {
	                bopDecoration = (IBOPDecoration)biome;
	            }
	            else if (ForcedDecorators.biomeHasForcedDecorator(biome.biomeID))
	            {
	                bopDecoration = ForcedDecorators.getForcedDecorator(biome.biomeID);
	            }

	            if (bopDecoration != null)
	            {
	                if (bopDecoration.getWeightedWorldGenForBOPFlowers() != null && !bopDecoration.getWeightedWorldGenForBOPFlowers().isEmpty())
	                {
	                    HashMap<WorldGenBOPFlora, Integer> flowerMap = bopDecoration.getWeightedWorldGenForBOPFlowers();

	                    for (Entry<WorldGenBOPFlora, Integer> entry : flowerMap.entrySet())
	                    {
	                        if (!(entry.getKey() instanceof WorldGenBOPDoubleFlora))
	                        {
	                            WorldGenBOPFlora flowerGenerator = entry.getKey();
	                            int weight = entry.getValue();

	                            biome.addFlower(flowerGenerator.flora, flowerGenerator.floraMeta, weight);
	                        }
	                    }
	                }
	            }
	        }
	    }
	}
}
