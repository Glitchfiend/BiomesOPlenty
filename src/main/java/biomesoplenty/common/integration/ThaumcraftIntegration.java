package biomesoplenty.common.integration;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ThaumcraftIntegration
{
	protected static void init()
	{
		addThaumcraftAspects();
	}

	private static void addThaumcraftAspects()
	{
		//Thaumcraft sets most aspects automatically, just special cases are there

		//Logs

		addAspectsToBlockMeta(BOPCBlocks.logs2, 1, new Aspect[] { Aspect.TREE, Aspect.MAGIC }, new int[] { 4, 2 });
		addAspectsToBlockMeta(BOPCBlocks.logs1, 2, new Aspect[] { Aspect.TREE, Aspect.DARKNESS }, new int[] { 4, 2 });
		addAspectsToBlockMeta(BOPCBlocks.logs2, 0, new Aspect[] { Aspect.TREE, Aspect.LIGHT }, new int[] { 4, 2 });
		addAspectsToBlockMeta(BOPCBlocks.logs4, 1, new Aspect[] { Aspect.TREE, Aspect.FIRE }, new int[] { 4, 2 });
		addAspectsToBlockMeta(BOPCBlocks.logs3, 3, new Aspect[] { Aspect.TREE, Aspect.MAGIC }, new int[] { 4, 1 });
		addAspectsToBlockMeta(BOPCBlocks.logs3, 2, new Aspect[] { Aspect.TREE, Aspect.DEATH }, new int[] { 4, 2 });
		addAspectsToBlockMeta(BOPCBlocks.logs1, 0, new Aspect[] { Aspect.TREE, Aspect.LIGHT }, new int[] { 4, 2 });

		//Planks

		addAspectsToBlockMeta(BOPCBlocks.planks, 0, new Aspect[] { Aspect.TREE, Aspect.LIGHT }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.planks, 5, new Aspect[] { Aspect.TREE, Aspect.MAGIC }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.planks, 2, new Aspect[] { Aspect.TREE, Aspect.DARKNESS }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.planks, 4, new Aspect[] { Aspect.TREE, Aspect.LIGHT }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.planks, 10, new Aspect[] { Aspect.WATER, Aspect.PLANT }, new int[] { 4, 4 });
		addAspectsToBlockMeta(BOPCBlocks.planks, 12, new Aspect[] { Aspect.TREE, Aspect.FIRE }, new int[] { 1, 1 });

		//Leaves

		addAspectsToBlock(BOPCBlocks.appleLeaves, new Aspect[] { Aspect.PLANT, Aspect.LIFE }, new int[] { 1, 1 });
		addAspectsToBlock(BOPCBlocks.persimmonLeaves, new Aspect[] { Aspect.PLANT, Aspect.LIFE }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.leaves2, 0, new Aspect[] { Aspect.PLANT, Aspect.DEATH }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.leaves1, 3, new Aspect[] { Aspect.PLANT, Aspect.DARKNESS }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.leaves2, 2, new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.colorizedLeaves1, 0, new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.leaves1, 2, new Aspect[] { Aspect.PLANT, Aspect.MAGIC }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.leaves4, 0, new Aspect[] { Aspect.PLANT, Aspect.FIRE }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.colorizedLeaves2, 3, new Aspect[] { Aspect.PLANT, Aspect.LIFE }, new int[] { 1, 1 });

		//Saplings

		addAspectsToBlockMeta(BOPCBlocks.saplings, 0, new Aspect[] { Aspect.PLANT, Aspect.TREE, Aspect.LIFE }, new int[] { 1, 2, 2 });
		addAspectsToBlockMeta(BOPCBlocks.saplings, 15, new Aspect[] { Aspect.PLANT, Aspect.TREE, Aspect.LIFE }, new int[] { 1, 1, 2 });
		addAspectsToBlockMeta(BOPCBlocks.saplings, 7, new Aspect[] { Aspect.PLANT, Aspect.TREE, Aspect.LIGHT }, new int[] { 1, 1, 2 });
		addAspectsToBlockMeta(BOPCBlocks.saplings, 3, new Aspect[] { Aspect.PLANT, Aspect.TREE, Aspect.MAGIC }, new int[] { 1, 1, 2 });
		addAspectsToBlockMeta(BOPCBlocks.saplings, 4, new Aspect[] { Aspect.PLANT, Aspect.TREE, Aspect.DARKNESS }, new int[] { 1, 1, 2 });
		addAspectsToBlockMeta(BOPCBlocks.saplings, 5, new Aspect[] { Aspect.PLANT, Aspect.TREE, Aspect.DEATH }, new int[] { 1, 1, 2 });
		addAspectsToBlockMeta(BOPCBlocks.saplings, 13, new Aspect[] { Aspect.PLANT, Aspect.TREE, Aspect.FIRE }, new int[] { 1, 1, 2 });
		addAspectsToBlockMeta(BOPCBlocks.colorizedSaplings, 0, new Aspect[] { Aspect.PLANT, Aspect.TREE, Aspect.LIGHT }, new int[] { 1, 1, 2 });
		addAspectsToBlockMeta(BOPCBlocks.colorizedSaplings, 7, new Aspect[] { Aspect.PLANT, Aspect.TREE, Aspect.LIFE }, new int[] { 1, 1, 2 });

		//Blocks

		addAspectsToBlockMeta(BOPCBlocks.mud, 0, new Aspect[] { Aspect.WATER, Aspect.EARTH }, new int[] { 2, 5 });
		addAspectsToBlock(BOPCBlocks.driedDirt, new Aspect[] { Aspect.ENTROPY, Aspect.EARTH }, new int[] { 1, 1 });
		addAspectsToBlock(BOPCBlocks.hardIce, new Aspect[] { Aspect.EARTH, Aspect.COLD }, new int[] { 2, 2 });
		addAspectsToBlock(BOPCBlocks.originGrass, new Aspect[] { Aspect.EARTH, Aspect.PLANT }, new int[] { 1, 2 });
		addAspectsToBlock(BOPCBlocks.ashStone, new Aspect[] { Aspect.EARTH, Aspect.FIRE }, new int[] { 1, 1 });
		addAspectsToBlock(BOPCBlocks.hardSand, new Aspect[] { Aspect.EARTH, Aspect.ENTROPY }, new int[] { 1, 2 });
		addAspectsToBlock(BOPCBlocks.hardDirt, new Aspect[] { Aspect.EARTH }, new int[] { 2 });
		addAspectsToBlock(BOPCBlocks.crystal, new Aspect[] { Aspect.GREED, Aspect.LIGHT, Aspect.MAGIC, Aspect.CRYSTAL }, new int[] { 5, 5, 2, 5 });
		addAspectsToBlock(BOPCBlocks.cragRock, new Aspect[] { Aspect.EARTH }, new int[] { 2 });
		addAspectsToBlockMeta(BOPCBlocks.mud, 1, new Aspect[] { Aspect.EARTH, Aspect.TRAP }, new int[] { 2, 4 });
		addAspectsToBlockMeta(BOPCBlocks.bopGrass, 1, new Aspect[] { Aspect.EARTH, Aspect.FIRE }, new int[] { 2, 1 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 0, new Aspect[] { Aspect.GREED, Aspect.ORDER, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 3, 7, 3 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 1, new Aspect[] { Aspect.GREED, Aspect.ORDER, Aspect.CRYSTAL }, new int[] { 5, 5, 8 });
		addAspectsToBlockMeta(BOPCBlocks.petals, 0, new Aspect[] { Aspect.PLANT, Aspect.MAGIC }, new int[] { 4, 1 });
		addAspectsToBlockMeta(BOPCBlocks.petals, 1, new Aspect[] { Aspect.PLANT, Aspect.MAGIC }, new int[] { 4, 1 });
		addAspectsToBlockMeta(BOPCBlocks.bones, 0, new Aspect[] { Aspect.FLESH, Aspect.DEATH }, new int[] { 1, 3 });
		addAspectsToBlockMeta(BOPCBlocks.bones, 1, new Aspect[] { Aspect.FLESH, Aspect.DEATH }, new int[] { 2, 5 });
		addAspectsToBlockMeta(BOPCBlocks.bones, 2, new Aspect[] { Aspect.FLESH, Aspect.DEATH }, new int[] { 3, 7 });
		addAspectsToBlockMeta(BOPCBlocks.foliage, 7, new Aspect[] { Aspect.PLANT, Aspect.POISON }, new int[] { 2, 1 });

		//Plants
		addAspectsToBlockMeta(BOPCBlocks.plants, 7, new Aspect[] { Aspect.PLANT, Aspect.WATER }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.plants, 8, new Aspect[] { Aspect.PLANT, Aspect.TREE }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.plants, 14, new Aspect[] { Aspect.PLANT, Aspect.WATER }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.foliage, 5, new Aspect[] { Aspect.PLANT }, new int[] { 2 });
		addAspectsToBlockMeta(BOPCBlocks.foliage, 4, new Aspect[] { Aspect.PLANT }, new int[] { 2 });
		addAspectsToBlockMeta(BOPCBlocks.coral2, 8, new Aspect[] { Aspect.PLANT, Aspect.WATER }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.plants, 15, new Aspect[] { Aspect.PLANT, Aspect.EARTH }, new int[] { 1, 1 });
		addAspectsToBlock(BOPCBlocks.bamboo, new Aspect[] { Aspect.PLANT, Aspect.LIFE }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.mushrooms, 0, new Aspect[] { Aspect.PLANT }, new int[] { 3 });
		addAspectsToBlockMeta(BOPCBlocks.mushrooms, 1, new Aspect[] { Aspect.PLANT }, new int[] { 3 });
		addAspectsToBlockMeta(BOPCBlocks.mushrooms, 2, new Aspect[] { Aspect.PLANT }, new int[] { 3 });
		addAspectsToBlockMeta(BOPCBlocks.mushrooms, 3, new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 3, 1 });
		addAspectsToBlockMeta(BOPCBlocks.plants, 12, new Aspect[] { Aspect.PLANT, Aspect.WEAPON }, new int[] { 2, 1 });
		addAspectsToBlockMeta(BOPCBlocks.plants, 2, new Aspect[] { Aspect.PLANT }, new int[] { 1 });
		addAspectsToBlockMeta(BOPCBlocks.plants, 3, new Aspect[] { Aspect.PLANT, Aspect.AIR }, new int[] { 1, 1 });
		addAspectsToBlockMeta(BOPCBlocks.plants, 5, new Aspect[] { Aspect.PLANT, Aspect.WEAPON }, new int[] { 2, 1 });
		addAspectsToBlockMeta(BOPCBlocks.plants, 1, new Aspect[] { Aspect.PLANT }, new int[] { 1 });
		addAspectsToBlockMeta(BOPCBlocks.plants, 0, new Aspect[] { Aspect.PLANT, Aspect.DEATH }, new int[] { 1, 2 });
		addAspectsToBlock(BOPCBlocks.treeMoss, new Aspect[] { Aspect.PLANT }, new int[] { 2 });
		addAspectsToBlock(BOPCBlocks.moss, new Aspect[] { Aspect.PLANT }, new int[] { 2 });
		addAspectsToBlock(BOPCBlocks.willow, new Aspect[] { Aspect.PLANT }, new int[] { 2 });

		//Flowers
		addAspectsToBlockMeta(BOPCBlocks.flowers, 8, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 4, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 2, new Aspect[] { Aspect.PLANT, Aspect.DEATH }, new int[] { 4, 1 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 3, new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 4, 1 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 9, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 1, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 7, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 5, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 15, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 6, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 12, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 0, new Aspect[] { Aspect.PLANT }, new int[] { 2 });
		addAspectsToBlockMeta(BOPCBlocks.flowers, 10, new Aspect[] { Aspect.PLANT, Aspect.WATER }, new int[] { 4, 1 });

		addAspectsToBlockMeta(BOPCBlocks.flowers2, 0, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers2, 1, new Aspect[] { Aspect.PLANT, Aspect.POISON }, new int[] { 4, 1 });
		addAspectsToBlockMeta(BOPCBlocks.flowers2, 2, new Aspect[] { Aspect.PLANT, Aspect.FIRE }, new int[] { 4, 2 });
		addAspectsToBlockMeta(BOPCBlocks.flowers2, 3, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers2, 4, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers2, 5, new Aspect[] { Aspect.PLANT }, new int[] { 4 });
		addAspectsToBlockMeta(BOPCBlocks.flowers2, 6, new Aspect[] { Aspect.PLANT, Aspect.EARTH }, new int[] { 4, 1 });
		addAspectsToBlockMeta(BOPCBlocks.flowers2, 7, new Aspect[] { Aspect.PLANT, Aspect.COLD }, new int[] { 4, 2 });

		//Gems
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 2, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 3, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL }, new int[] { 5, 7 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 4, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 5, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL }, new int[] { 5, 7 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 6, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 7, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL }, new int[] { 5, 7 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 8, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 9, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL }, new int[] { 5, 7 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 10, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 11, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL }, new int[] { 5, 7 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 12, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 13, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL }, new int[] { 5, 7 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 14, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
		addAspectsToBlockMeta(BOPCBlocks.gemOre, 15, new Aspect[] { Aspect.GREED, Aspect.CRYSTAL }, new int[] { 5, 7 });

		//Items
		addAspectsToItemMeta(BOPCItems.gems, 0, new Aspect[] { Aspect.GREED, Aspect.ORDER, Aspect.CRYSTAL }, new int[] { 4, 4, 4 });
		addAspectsToItemMeta(BOPCItems.misc, 1, new Aspect[] { Aspect.ENTROPY, Aspect.EXCHANGE }, new int[] { 1, 1 });
		addAspectsToItemMeta(BOPCItems.misc, 4, new Aspect[] { Aspect.CRYSTAL, Aspect.LIGHT, Aspect.MAGIC, Aspect.GREED }, new int[] { 4, 2, 2, 3 });
		addAspectsToItemMeta(BOPCItems.misc, 0, new Aspect[] { Aspect.EARTH, Aspect.FIRE }, new int[] { 2, 1 });
		addAspectsToItemMeta(BOPCItems.dart, 0, new Aspect[] { Aspect.WEAPON }, new int[] { 1 });
		addAspectsToItemMeta(BOPCItems.dart, 1, new Aspect[] { Aspect.WEAPON, Aspect.POISON }, new int[] { 1, 2 });
	}

	private static void addAspectsToBlock(Block block, Aspect[] aspects, int[] amounts)
	{
		addAspects(new ItemStack(block), aspects, amounts);
	}

	private static void addAspectsToBlockMeta(Block block, int meta, Aspect[] aspects, int[] amounts)
	{
		addAspects(new ItemStack(block, 1, meta), aspects, amounts);
	}

	private static void addAspectsToItem(Item item, Aspect[] aspects, int[] amounts)
	{
		addAspects(new ItemStack(item), aspects, amounts);
	}

	private static void addAspectsToItemMeta(Item items, int meta, Aspect[] aspects, int[] amounts)
	{
		addAspects(new ItemStack(items, 1, meta), aspects, amounts);
	}

	private static void addAspects(ItemStack stack, Aspect[] aspects, int[] amounts)
	{
		AspectList list = new AspectList();
		for (int i = 0; i < aspects.length; i++)
			list.add(aspects[i], amounts[i]);

		ThaumcraftApi.registerObjectTag(stack, list);
	}

}