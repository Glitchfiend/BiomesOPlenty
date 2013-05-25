package biomesoplenty.configuration;

import com.google.common.base.Optional;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import biomesoplenty.api.Liquids;
import biomesoplenty.items.ItemBOPBucket;
import biomesoplenty.liquids.BlockSpringWaterFlowing;
import biomesoplenty.liquids.BlockSpringWaterStill;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BOPLiquids 
{
	public static void init()
	{
		initializeLiquids();
		initializeContainers();
		registerLiquids();
		registerContainerNames();
		registerLiquidNames();
	}

	private static void initializeLiquids()
	{
		Liquids.springWaterFlowing = Optional.of(new BlockSpringWaterFlowing(BOPConfiguration.springWaterStillID - 1).setUnlocalizedName("springWaterFlowing"));
		Liquids.springWaterStill = Optional.of(new BlockSpringWaterStill(BOPConfiguration.springWaterStillID).setUnlocalizedName("springWaterStill"));	
		
		Liquids.springWaterLiquid = Optional.of(LiquidDictionary.getOrCreateLiquid("Spring Water", new LiquidStack(Liquids.springWaterStill.get(), 1)));
	}

	private static void initializeContainers()
	{
		Liquids.bucketSpringWater = Optional.of((new ItemBOPBucket(BOPConfiguration.springWaterBucketID, Liquids.springWaterStill.get().blockID)).setMaxStackSize(1).setUnlocalizedName("bucketSpringWater").setContainerItem(Item.bucketEmpty));
		
		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("Spring Water", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Liquids.bucketSpringWater.get()), new ItemStack(Item.bucketEmpty)));
	}

	private static void registerLiquids()
	{
		GameRegistry.registerBlock(Liquids.springWaterFlowing.get(), "springWaterFlowing");
		GameRegistry.registerBlock(Liquids.springWaterStill.get(), "springWaterStill");
	}

	private static void registerContainerNames()
	{
		LanguageRegistry.addName(Liquids.bucketSpringWater.get(), "Spring Water Bucket");
	}

	private static void registerLiquidNames()
	{
		LanguageRegistry.addName(Liquids.springWaterFlowing.get(), "Spring Water");
		LanguageRegistry.addName(Liquids.springWaterStill.get(), "Spring Water");
	}
}
