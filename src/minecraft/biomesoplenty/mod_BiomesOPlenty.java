package biomesoplenty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.api.Biomes;
import biomesoplenty.configuration.BOPBiomes;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.configuration.BOPItems;
import biomesoplenty.helpers.AchievementHelper;
import biomesoplenty.helpers.BonemealUse;
import biomesoplenty.helpers.CreativeTabsBOP;
import biomesoplenty.helpers.WorldProviderPromised;
import biomesoplenty.helpers.WorldTypeSize;
import biomesoplenty.integration.BOPCrossIntegration;
import biomesoplenty.items.projectiles.DispenserBehaviorMudball;
import biomesoplenty.items.projectiles.EntityMudball;
import biomesoplenty.mobs.EntityJungleSpider;
import biomesoplenty.mobs.EntityRosester;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="BiomesOPlenty", name="Biomes O' Plenty", version="0.5.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class mod_BiomesOPlenty
{	    
	// The instance of your mod that Forge uses.
	@Instance("BiomesOPlenty")
	public static mod_BiomesOPlenty instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="biomesoplenty.ClientProxy", serverSide="biomesoplenty.CommonProxy")
	public static CommonProxy proxy;

//	public static int promisedLandDim = 20;

	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		boolean isClient = proxy instanceof ClientProxy;
		
		String[] soundFiles = { "bopdisc.ogg", "bopdiscmud.ogg"};

		if (isClient)
		{
			for (String soundFile : soundFiles) try
			{
				File file = new File("resources/mod/streaming/" + soundFile);
				if (!file.exists()) {
					System.out.println("[BiomesOPlenty] " + soundFile + " doesn't exist, creating...");
					file.getParentFile().mkdirs();
					file.createNewFile();
					InputStream istream = getClass().getResourceAsStream("/mods/BiomesOPlenty/audio/" + soundFile);
					OutputStream out = new FileOutputStream(file);
					byte[] buf = new byte[1024];
					int size = 0;
					int len;
					while ((len = istream.read(buf)) > 0) {
						out.write(buf, 0, len);
						size += len;
					}
					out.close();
					istream.close();
					if (size == 0) file.delete();
				}
			}
			catch (Exception e)
			{
				FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[BiomesOPlenty] Failed to load sound file: " + soundFile);
				e.printStackTrace();
			}
		}
		
		BOPConfiguration.init(event.getSuggestedConfigurationFile());

		tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(),"tabBiomesOPlenty");
		
		BOPBlocks.init();
		
		BOPItems.init();
		
		BOPBlocks.dependantinit();
		
		BOPBiomes.init();
		
		// Achievement declaration
        if (BOPConfiguration.achievements == true)
        {
            AchievementHelper.init();
        }
	}

	@Init
	public void load(FMLInitializationEvent event)
	{
		
		BOPCrossIntegration.init();

		LanguageRegistry.instance().addStringLocalization("itemGroup.tabBiomesOPlenty", "en_US", "Biomes O\' Plenty");
		LanguageRegistry.instance().addStringLocalization("generator.BIOMESOP", "en_US", "Biomes O\' Plenty");

		// Add helpers for compatibility
		MinecraftForge.TERRAIN_GEN_BUS.register(new WorldTypeSize());
		MinecraftForge.EVENT_BUS.register(new AchievementHelper());
		MinecraftForge.EVENT_BUS.register(new BonemealUse());

		proxy.registerRenderers();

		EntityRegistry.registerModEntity(EntityJungleSpider.class, "JungleSpider", BOPConfiguration.jungleSpiderID, this, 80, 3, true);
		LanguageRegistry.instance().addStringLocalization("entity.BiomesOPlenty.JungleSpider.name", "en_US", "Jungle Spider");
		if (Biomes.jungleNew.isPresent() && Biomes.tropicalRainforest.isPresent() && Biomes.oasis.isPresent() && Biomes.tropics.isPresent())
			EntityRegistry.addSpawn(EntityJungleSpider.class, 8, 1, 3, EnumCreatureType.monster, Biomes.jungleNew.get(), Biomes.tropicalRainforest.get(), Biomes.oasis.get(), Biomes.tropics.get());
		registerEntityEgg(EntityJungleSpider.class, 5147192, 11013646);

		EntityRegistry.registerModEntity(EntityRosester.class, "Rosester", BOPConfiguration.rosesterID, this, 80, 3, true);
		LanguageRegistry.instance().addStringLocalization("entity.BiomesOPlenty.Rosester.name", "en_US", "Rosester");
		if (Biomes.garden.isPresent())
			EntityRegistry.addSpawn(EntityRosester.class, 10, 2, 4, EnumCreatureType.creature, Biomes.garden.get());    
		registerEntityEgg(EntityRosester.class, 14831439, 16756224);
		
		EntityRegistry.registerModEntity(EntityMudball.class, "MudBall", EntityRegistry.findGlobalUniqueEntityId(), this, 80, 3, true); 
		
		// Dispenser behavior for mud balls
		BlockDispenser.dispenseBehaviorRegistry.putObject(BOPItems.mudBall, new DispenserBehaviorMudball());

		DimensionManager.registerProviderType(BOPConfiguration.promisedLandDimID, WorldProviderPromised.class, false);

		DimensionManager.registerDimension(BOPConfiguration.promisedLandDimID, BOPConfiguration.promisedLandDimID);

		dungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
		mineshaft = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
		strongholdCorridor = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR);
		strongholdCrossing = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING);
		village = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);

		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.bopDisc), 1, 1, 2));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.mossItem), 2, 8, 50));
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 2), 4, 12, 75));

		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.ashes), 2, 8, 25));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.thorn), 4, 6, 15));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.mudBall), 2, 8, 10));
		mineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 3), 4, 12, 75));

		strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.mossItem), 2, 8, 50));
		strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.glowFlower), 1, 4, 25));
		strongholdCorridor.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.deathbloom), 1, 4, 25));

		strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.mossItem), 2, 8, 50));
		strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.glowFlower), 1, 4, 25));
		strongholdCrossing.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.deathbloom), 1, 4, 25));

		village.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.barleyItem), 4, 10, 75));
		village.addItem(new WeightedRandomChestContent(new ItemStack(BOPItems.shroomPowder), 1, 5, 50));
		village.addItem(new WeightedRandomChestContent(new ItemStack(BOPBlocks.thorn), 2, 6, 25));
		village.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 2), 4, 12, 75));
		village.addItem(new WeightedRandomChestContent(new ItemStack(Item.dyePowder, 1, 3), 4, 12, 75));
	}

	//Find the first available egg ID after our egg ID counter
	public static int getUniqueEntityEggId() {
		do {
			eggIdCounter++;
		} while (EntityList.getStringFromID(eggIdCounter) != null);

		return eggIdCounter;
	}

	@SuppressWarnings("unchecked")
	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) {
		int id = getUniqueEntityEggId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}

	//Eggs
	public static int eggIdCounter = 300;

	public static CreativeTabs tabBiomesOPlenty;

	public static ChestGenHooks dungeon;
	public static ChestGenHooks mineshaft;
	public static ChestGenHooks strongholdCorridor;
	public static ChestGenHooks strongholdCrossing;
	public static ChestGenHooks village;

	public static int getLastBiomeID()
	{
		int x;
		for(x = 255; x >= 0; x--) {
			if (BiomeGenBase.biomeList[x] == null) 
			{
				break;
			}
		}
		return x;
	}
}