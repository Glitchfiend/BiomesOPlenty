package biomesoplenty.configuration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.api.Blocks;
import biomesoplenty.blocks.BlockAltar;
import biomesoplenty.blocks.BlockAsh;
import biomesoplenty.blocks.BlockBOPAmethyst;
import biomesoplenty.blocks.BlockBOPAppleLeaves;
import biomesoplenty.blocks.BlockBOPColorizedLeaves;
import biomesoplenty.blocks.BlockBOPColorizedSapling;
import biomesoplenty.blocks.BlockBOPCoral;
import biomesoplenty.blocks.BlockBOPFlower;
import biomesoplenty.blocks.BlockBOPFoliage;
import biomesoplenty.blocks.BlockBOPGeneric;
import biomesoplenty.blocks.BlockBOPGeneric.BlockType;
import biomesoplenty.blocks.BlockBOPGlass;
import biomesoplenty.blocks.BlockBOPGrass;
import biomesoplenty.blocks.BlockBOPLeaves;
import biomesoplenty.blocks.BlockBOPLeaves.LeafCategory;
import biomesoplenty.blocks.BlockBOPLog;
import biomesoplenty.blocks.BlockBOPLog.LogCategory;
import biomesoplenty.blocks.BlockBOPMushroom;
import biomesoplenty.blocks.BlockBOPPetals;
import biomesoplenty.blocks.BlockBOPPlank;
import biomesoplenty.blocks.BlockBOPPlant;
import biomesoplenty.blocks.BlockBOPRedRock;
import biomesoplenty.blocks.BlockBOPSapling;
import biomesoplenty.blocks.BlockBOPSkystone;
import biomesoplenty.blocks.BlockBOPSlab;
import biomesoplenty.blocks.BlockBOPSlab.SlabCategory;
import biomesoplenty.blocks.BlockBOPStairs;
import biomesoplenty.blocks.BlockBOPStairs.Category;
import biomesoplenty.blocks.BlockBamboo;
import biomesoplenty.blocks.BlockBones;
import biomesoplenty.blocks.BlockCloud;
import biomesoplenty.blocks.BlockIvy;
import biomesoplenty.blocks.BlockMoss;
import biomesoplenty.blocks.BlockMud;
import biomesoplenty.blocks.BlockOriginGrass;
import biomesoplenty.blocks.BlockPromisedPortal;
import biomesoplenty.blocks.BlockTreeMoss;
import biomesoplenty.blocks.BlockWillow;
import biomesoplenty.items.ItemBOPAltar;
import biomesoplenty.items.ItemBOPAmethyst;
import biomesoplenty.items.ItemBOPAppleLeaves;
import biomesoplenty.items.ItemBOPBamboo;
import biomesoplenty.items.ItemBOPBones;
import biomesoplenty.items.ItemBOPColorizedLeaves;
import biomesoplenty.items.ItemBOPColorizedSapling;
import biomesoplenty.items.ItemBOPCoral;
import biomesoplenty.items.ItemBOPFlower;
import biomesoplenty.items.ItemBOPFoliage;
import biomesoplenty.items.ItemBOPGlass;
import biomesoplenty.items.ItemBOPGrass;
import biomesoplenty.items.ItemBOPIvy;
import biomesoplenty.items.ItemBOPLeaves;
import biomesoplenty.items.ItemBOPLog;
import biomesoplenty.items.ItemBOPMoss;
import biomesoplenty.items.ItemBOPMud;
import biomesoplenty.items.ItemBOPMushroom;
import biomesoplenty.items.ItemBOPPetals;
import biomesoplenty.items.ItemBOPPlank;
import biomesoplenty.items.ItemBOPPlant;
import biomesoplenty.items.ItemBOPRedRock;
import biomesoplenty.items.ItemBOPSapling;
import biomesoplenty.items.ItemBOPSkystone;
import biomesoplenty.items.ItemBOPSlab;
import biomesoplenty.items.ItemBOPWillow;
import biomesoplenty.tileentity.TileEntityAltar;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BOPBlocks {
	public static void init()
	{
		initializeBlocks();
		registerBlocks();
		registerTileEntities();

		//Shears VS Blocks
		Blocks.shearBlockIds.put(Blocks.leaves1.get().blockID, 15.0F);
		Blocks.shearBlockIds.put(Blocks.leaves2.get().blockID, 15.0F);
		Blocks.shearBlockIds.put(Blocks.leavesColorized.get().blockID, 15.0F);
		Blocks.shearBlockIds.put(Blocks.leavesFruit.get().blockID, 15.0F);

		MinecraftForge.setBlockHarvestLevel(Blocks.holyGrass.get(), 1, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.mud.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.ash.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.originGrass.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.hardSand.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.holyGrass.get(), 0, "pickaxe", 0);
		//		MinecraftForge.setBlockHarvestLevel(Blocks.quicksand.get(), "shovel", 0);

		MinecraftForge.setBlockHarvestLevel(Blocks.driedDirt.get(), "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.altar.get(), "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.amethystOre.get(), 0, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(Blocks.amethystOre.get(), 2, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(Blocks.amethystOre.get(), 4, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(Blocks.amethystOre.get(), 6, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(Blocks.amethystOre.get(), 8, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(Blocks.amethystOre.get(), 10, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(Blocks.amethystOre.get(), 12, "pickaxe", 2);

		addGrassPlants();

		//registerNames();
	}

	private static void initializeBlocks()
	{
		// Block declaration
		Blocks.mud = Optional.of((new BlockMud(BOPConfiguration.mudID)).setHardness(0.6F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("bop.mud"));
		Blocks.driedDirt = Optional.of(new BlockBOPGeneric(BOPConfiguration.driedDirtID, Material.rock, BlockType.DRIED_DIRT).setUnlocalizedName("bop.generic"));
		Blocks.redRock = Optional.of((new BlockBOPRedRock(BOPConfiguration.redRockID)).setUnlocalizedName("bop.redRocks"));
		Blocks.ash = Optional.of((new BlockAsh(BOPConfiguration.ashID)).setHardness(0.4F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("bop.ash"));
		Blocks.plants = Optional.of((new BlockBOPPlant(BOPConfiguration.plantsID)).setUnlocalizedName("bop.plants"));
		Blocks.flowers = Optional.of((new BlockBOPFlower(BOPConfiguration.flowersID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.flowers"));
		Blocks.mushrooms = Optional.of((new BlockBOPMushroom(BOPConfiguration.mushroomsID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.mushrooms"));
		Blocks.coral = Optional.of((new BlockBOPCoral(BOPConfiguration.coralID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.coral"));
		Blocks.willow = Optional.of((new BlockWillow(BOPConfiguration.willowID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.willow"));
		Blocks.ivy = Optional.of((new BlockIvy(BOPConfiguration.ivyID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.ivy"));
		Blocks.leaves1 = Optional.of((new BlockBOPLeaves(BOPConfiguration.leaves1ID, LeafCategory.CAT1)).setUnlocalizedName("bop.leaves1"));
		Blocks.leaves2 = Optional.of((new BlockBOPLeaves(BOPConfiguration.leaves2ID, LeafCategory.CAT2)).setUnlocalizedName("bop.leaves2"));
		Blocks.foliage = Optional.of((new BlockBOPFoliage(BOPConfiguration.foliageID)).setUnlocalizedName("bop.foliage"));
		Blocks.ashStone = Optional.of(new BlockBOPGeneric(BOPConfiguration.ashStoneID, Material.rock, BlockType.ASH_STONE));
		Blocks.hardIce = Optional.of(new BlockBOPGeneric(BOPConfiguration.hardIceID, Material.rock, BlockType.HARD_ICE));
		Blocks.leavesFruit = Optional.of((new BlockBOPAppleLeaves(BOPConfiguration.leavesFruitID)).setUnlocalizedName("bop.leavesFruit"));
		Blocks.bamboo = Optional.of(new BlockBamboo(BOPConfiguration.bambooID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.bamboo"));
		Blocks.mudBrick = Optional.of(new BlockBOPGeneric(BOPConfiguration.mudBrickBlockID, Material.rock, BlockType.MUD_BRICK));
		Blocks.mudBricksStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.mudBrickStairsID, Blocks.redRock.get(), Category.MUD_BRICKS)).setUnlocalizedName("bop.mudBricksStairs"));
		Blocks.stoneDoubleSlab = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.stoneDoubleSlabID, true, Material.rock, SlabCategory.STONE)).setUnlocalizedName("bop.stoneDoubleSlab"));
		Blocks.stoneSingleSlab = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.stoneSingleSlabID, false, Material.rock, SlabCategory.STONE)).setUnlocalizedName("bop.stoneSingleSlab"));
		Blocks.originGrass = Optional.of((new BlockOriginGrass(BOPConfiguration.originGrassID)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.originGrass"));
		Blocks.treeMoss = Optional.of((new BlockTreeMoss(BOPConfiguration.treeMossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.treeMoss"));
		Blocks.logs1 = Optional.of((new BlockBOPLog(BOPConfiguration.logs1ID,LogCategory.CAT1)).setUnlocalizedName("bop.wood1"));
		Blocks.logs2 = Optional.of((new BlockBOPLog(BOPConfiguration.logs2ID,LogCategory.CAT2)).setUnlocalizedName("bop.wood2"));
		Blocks.logs3 = Optional.of((new BlockBOPLog(BOPConfiguration.logs3ID,LogCategory.CAT3)).setUnlocalizedName("bop.wood3"));
		Blocks.logs4 = Optional.of((new BlockBOPLog(BOPConfiguration.logs4ID,LogCategory.CAT4)).setUnlocalizedName("bop.wood4"));
		Blocks.petals = Optional.of((new BlockBOPPetals(BOPConfiguration.petalsID)).setUnlocalizedName("bop.petals"));
		Blocks.saplings = Optional.of((new BlockBOPSapling(BOPConfiguration.saplingsID)).setUnlocalizedName("bop.saplings"));
		Blocks.colorizedSaplings = Optional.of((new BlockBOPColorizedSapling(BOPConfiguration.colourizedSaplingsID)).setUnlocalizedName("bop.colorizedSaplings"));
		Blocks.redCobbleStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.redCobbleStairsID, Blocks.redRock.get(), Category.RED_COBBLE)).setUnlocalizedName("bop.redCobbleStairs"));
		Blocks.redBricksStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.redBrickStairsID, Blocks.redRock.get(), Category.RED_BRICKS)).setUnlocalizedName("bop.redBricksStairs"));
		Blocks.hardSand = Optional.of(new BlockBOPGeneric(BOPConfiguration.hardSandID, Material.sand, BlockType.HARD_SAND));
		Blocks.hardDirt = Optional.of(new BlockBOPGeneric(BOPConfiguration.hardDirtID, Material.rock, BlockType.HARD_DIRT));
		Blocks.holyGrass = Optional.of(new BlockBOPGrass(BOPConfiguration.holyGrassID).setUnlocalizedName("bop.holyGrass"));
		Blocks.holyDirt = Optional.of(new BlockBOPGeneric(BOPConfiguration.holyDirtID, Material.sand, BlockType.HOLY_DIRT));
		Blocks.holyStone = Optional.of((new BlockBOPSkystone(BOPConfiguration.holyStoneID)).setUnlocalizedName("bop.holyStone"));
		Blocks.holyCobbleStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.holyCobbleStairsID, Blocks.holyStone.get(), Category.HOLY_COBBLE)).setUnlocalizedName("bop.holyCobbleStairs"));
		Blocks.holyBricksStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.holyBrickStairsID, Blocks.holyStone.get(), Category.HOLY_BRICKS)).setUnlocalizedName("bop.holyBricksStairs"));
		Blocks.crystal = Optional.of(new BlockBOPGeneric(BOPConfiguration.crystalID, Material.glass, BlockType.CRYSTAL));
		Blocks.promisedPortal = Optional.of(new BlockPromisedPortal(BOPConfiguration.promisedLandPortalID).setUnlocalizedName("bop.promisedPortal").setBlockUnbreakable().setResistance(6000000.0F).setLightValue(1.0F));
		//        Blocks.amethystOre = Optional.of(new BlockBOPGeneric(BOPConfiguration.amethystOreID, Material.rock, BlockType.AMETHYST_ORE));
		//        Blocks.amethystBlock = Optional.of(new BlockBOPGeneric(BOPConfiguration.amethystBlockID, Material.iron, BlockType.AMETHYST_BLOCK));
		Blocks.amethystOre = Optional.of(new BlockBOPAmethyst(BOPConfiguration.amethystOreID, Material.rock).setUnlocalizedName("bop.amethystOre"));
		//        Blocks.bambooThatching = Optional.of(new BlockBOPGeneric(BOPConfiguration.bambooThatchingID, Material.wood, BlockType.BAMBOO_THATCHING));
		Blocks.moss = Optional.of((new BlockMoss(BOPConfiguration.mossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.moss"));
		//        Blocks.smolderingGrass = Optional.of((BlockSmolderingGrass)(new BlockSmolderingGrass(BOPConfiguration.smolderingGrassID)).setHardness(0.6F).setLightValue(0.25F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.smolderingGrass"));
		Blocks.cragRock = Optional.of(new BlockBOPGeneric(BOPConfiguration.cragRockID, Material.rock, BlockType.CRAG_ROCK));
		//        Blocks.quicksand = Optional.of((new BlockQuicksand(BOPConfiguration.quicksandID)).setHardness(0.3F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("bop.quicksand"));
		//        Blocks.grass = Optional.of(new BlockBOPGrass(3000).setUnlocalizedName("bop.holyGrass"));
		Blocks.cloud = Optional.of((new BlockCloud(BOPConfiguration.cloudID)).setHardness(0.1F).setLightOpacity(3).setStepSound(Block.soundClothFootstep).setUnlocalizedName("bop.cloud"));

		Blocks.bones = Optional.of((new BlockBones(BOPConfiguration.bonesID)).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.bones"));
		
		Blocks.glass = Optional.of((new BlockBOPGlass(BOPConfiguration.glassID)).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("bop.glass"));
		
		Blocks.altar = Optional.of((new BlockAltar(BOPConfiguration.altarID)).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.altar"));

		Blocks.planks = Optional.of((new BlockBOPPlank(BOPConfiguration.planksID)).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bop.planks"));

		Blocks.woodenDoubleSlab1 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.woodenDoubleSlab1ID, true, Material.wood, SlabCategory.WOOD1)).setUnlocalizedName("bop.woodenDoubleSlab1"));
		Blocks.woodenSingleSlab1 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.woodenSingleSlab1ID, false, Material.wood, SlabCategory.WOOD1)).setUnlocalizedName("bop.woodenSingleSlab1"));
		Blocks.woodenDoubleSlab2 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.woodenDoubleSlab2ID, true, Material.wood, SlabCategory.WOOD2)).setUnlocalizedName("bop.woodenDoubleSlab2"));
		Blocks.woodenSingleSlab2 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.woodenSingleSlab2ID, false, Material.wood, SlabCategory.WOOD2)).setUnlocalizedName("bop.woodenSingleSlab2"));

		Blocks.acaciaStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.acaciaStairsID, Blocks.planks.get(), Category.ACACIA)).setUnlocalizedName("bop.acaciaStairs"));
		Blocks.cherryStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.cherryStairsID, Blocks.planks.get(), Category.CHERRY)).setUnlocalizedName("bop.cherryStairs"));
		Blocks.darkStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.darkStairsID, Blocks.planks.get(), Category.DARK)).setUnlocalizedName("bop.darkStairs"));
		Blocks.firStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.firStairsID, Blocks.planks.get(), Category.FIR)).setUnlocalizedName("bop.firStairs"));
		Blocks.holyStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.holyStairsID, Blocks.planks.get(), Category.HOLY)).setUnlocalizedName("bop.holyStairs"));
		Blocks.magicStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.magicStairsID, Blocks.planks.get(), Category.MAGIC)).setUnlocalizedName("bop.magicStairs"));
		Blocks.mangroveStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.mangroveStairsID, Blocks.planks.get(), Category.MANGROVE)).setUnlocalizedName("bop.mangroveStairs"));
		Blocks.palmStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.palmStairsID, Blocks.planks.get(), Category.PALM)).setUnlocalizedName("bop.palmStairs"));
		Blocks.redwoodStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.redwoodStairsID, Blocks.planks.get(), Category.REDWOOD)).setUnlocalizedName("bop.redwoodStairs"));
		Blocks.willowStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.willowStairsID, Blocks.planks.get(), Category.WILLOW)).setUnlocalizedName("bop.willowStairs"));
		Blocks.pineStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.pineStairsID, Blocks.planks.get(), Category.PINE)).setUnlocalizedName("bop.pineStairs"));
		Blocks.hellBarkStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.hellBarkStairsID, Blocks.planks.get(), Category.HELL_BARK)).setUnlocalizedName("bop.hellBarkStairs"));
		Blocks.jacarandaStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.jacarandaStairsID, Blocks.planks.get(), Category.JACARANDA)).setUnlocalizedName("bop.jacarandaStairs"));
		Blocks.leavesColorized = Optional.of((new BlockBOPColorizedLeaves(BOPConfiguration.colourizedLeavesID)).setUnlocalizedName("bop.leavesColorized"));
	}

	private static void registerBlocks()
	{
		// Add block registration
		GameRegistry.registerBlock(Blocks.mud.get(), ItemBOPMud.class, "bop.mud");
		GameRegistry.registerBlock(Blocks.driedDirt.get(), "bop.driedDirt");
		GameRegistry.registerBlock(Blocks.redRock.get(), ItemBOPRedRock.class, "bop.redRock");
		GameRegistry.registerBlock(Blocks.ash.get(), "bop.ash");
		GameRegistry.registerBlock(Blocks.plants.get(), ItemBOPPlant.class, "bop.plants");
		GameRegistry.registerBlock(Blocks.flowers.get(), ItemBOPFlower.class, "bop.flowers");
		GameRegistry.registerBlock(Blocks.mushrooms.get(), ItemBOPMushroom.class, "bop.mushrooms");
		GameRegistry.registerBlock(Blocks.coral.get(), ItemBOPCoral.class, "bop.coral");
		GameRegistry.registerBlock(Blocks.willow.get(), ItemBOPWillow.class, "bop.willow");
		GameRegistry.registerBlock(Blocks.ivy.get(), ItemBOPIvy.class, "bop.ivy");
		GameRegistry.registerBlock(Blocks.leaves1.get(), ItemBOPLeaves.class, "bop.leaves1");
		GameRegistry.registerBlock(Blocks.leaves2.get(), ItemBOPLeaves.class, "bop.leaves2");
		GameRegistry.registerBlock(Blocks.foliage.get(), ItemBOPFoliage.class, "bop.foliage");
		GameRegistry.registerBlock(Blocks.ashStone.get(), "bop.ashStone");
		GameRegistry.registerBlock(Blocks.hardIce.get(), "bop.hardIce");
		GameRegistry.registerBlock(Blocks.leavesFruit.get(), ItemBOPAppleLeaves.class, "bop.leavesFruit");
		GameRegistry.registerBlock(Blocks.bamboo.get(), ItemBOPBamboo.class, "bop.bamboo");
		GameRegistry.registerBlock(Blocks.mudBrick.get(), "bop.mudBrick");
		GameRegistry.registerBlock(Blocks.mudBricksStairs.get(), "bop.mudBricksStairs");
		GameRegistry.registerBlock(Blocks.originGrass.get(), "bop.originGrass");
		GameRegistry.registerBlock(Blocks.treeMoss.get(), "bop.treeMoss");
		GameRegistry.registerBlock(Blocks.logs1.get(), ItemBOPLog.class, "bop.wood1");
		GameRegistry.registerBlock(Blocks.logs2.get(), ItemBOPLog.class, "bop.wood2");
		GameRegistry.registerBlock(Blocks.logs3.get(), ItemBOPLog.class, "bop.wood3");
		GameRegistry.registerBlock(Blocks.logs4.get(), ItemBOPLog.class, "bop.wood4");
		GameRegistry.registerBlock(Blocks.petals.get(), ItemBOPPetals.class, "bop.petals");
		GameRegistry.registerBlock(Blocks.saplings.get(), ItemBOPSapling.class, "bop.saplings");
		GameRegistry.registerBlock(Blocks.colorizedSaplings.get(), ItemBOPColorizedSapling.class, "bop.colorizedSaplings");
		GameRegistry.registerBlock(Blocks.redCobbleStairs.get(), "bop.redCobbleStairs");
		GameRegistry.registerBlock(Blocks.redBricksStairs.get(), "bop.redBricksStairs");
		GameRegistry.registerBlock(Blocks.hardSand.get(), "bop.hardSand");
		GameRegistry.registerBlock(Blocks.hardDirt.get(), "bop.hardDirt");
		GameRegistry.registerBlock(Blocks.crystal.get(), "bop.crystal");
		//        GameRegistry.registerBlock(Blocks.holyGrass.get(), "bop.holyGrass");
		GameRegistry.registerBlock(Blocks.holyGrass.get(), ItemBOPGrass.class, "bop.holyGrass");
		GameRegistry.registerBlock(Blocks.holyDirt.get(), "bop.holyDirt");
		GameRegistry.registerBlock(Blocks.holyStone.get(), ItemBOPSkystone.class, "bop.holyStone");
		GameRegistry.registerBlock(Blocks.holyCobbleStairs.get(), "bop.holyCobbleStairs");
		GameRegistry.registerBlock(Blocks.holyBricksStairs.get(), "bop.holyBricksStairs");
		GameRegistry.registerBlock(Blocks.promisedPortal.get(), "bop.promisedPortal");
		GameRegistry.registerBlock(Blocks.amethystOre.get(), ItemBOPAmethyst.class, "bop.amethystOre");
		//        GameRegistry.registerBlock(Blocks.amethystBlock.get(), "bop.amethystBlock");
		//        GameRegistry.registerBlock(Blocks.bambooThatching.get(), "bop.bambooThatching");
		GameRegistry.registerBlock(Blocks.moss.get(), ItemBOPMoss.class, "bop.moss");
		//        GameRegistry.registerBlock(Blocks.smolderingGrass.get(), "bop.smolderingGrass");
		GameRegistry.registerBlock(Blocks.cragRock.get(), "bop.cragRock");
		//        GameRegistry.registerBlock(Blocks.quicksand.get(), "bop.quicksand");
		//        GameRegistry.registerBlock(Blocks.amethyst.get(), ItemBOPAmethyst.class, "bop.amethystOre1");
		GameRegistry.registerBlock(Blocks.cloud.get(), "bop.cloud");

		GameRegistry.registerBlock(Blocks.bones.get(), ItemBOPBones.class, "bop.bones");
		GameRegistry.registerBlock(Blocks.glass.get(), ItemBOPGlass.class, "bop.glass");	
		GameRegistry.registerBlock(Blocks.altar.get(), ItemBOPAltar.class, "bop.altar");	

		ItemBOPSlab.setSlabs(Blocks.stoneSingleSlab.get(), Blocks.stoneDoubleSlab.get());
		GameRegistry.registerBlock(Blocks.stoneDoubleSlab.get(), ItemBOPSlab.class, "bop.stoneDoubleSlab");
		GameRegistry.registerBlock(Blocks.stoneSingleSlab.get(), ItemBOPSlab.class, "bop.stoneSingleSlab");
		ItemBOPSlab.setSlabs(Blocks.woodenSingleSlab1.get(), Blocks.woodenDoubleSlab1.get());
		GameRegistry.registerBlock(Blocks.woodenDoubleSlab1.get(), ItemBOPSlab.class, "bop.woodenDoubleSlab1");
		GameRegistry.registerBlock(Blocks.woodenSingleSlab1.get(), ItemBOPSlab.class, "bop.woodenSingleSlab1");
		ItemBOPSlab.setSlabs(Blocks.woodenSingleSlab2.get(), Blocks.woodenDoubleSlab2.get());
		GameRegistry.registerBlock(Blocks.woodenDoubleSlab2.get(), ItemBOPSlab.class, "bop.woodenDoubleSlab2");
		GameRegistry.registerBlock(Blocks.woodenSingleSlab2.get(), ItemBOPSlab.class, "bop.woodenSingleSlab2");

		GameRegistry.registerBlock(Blocks.planks.get(), ItemBOPPlank.class, "bop.planks");

		GameRegistry.registerBlock(Blocks.acaciaStairs.get(), "bop.acaciaStairs");
		GameRegistry.registerBlock(Blocks.cherryStairs.get(), "bop.cherryStairs");
		GameRegistry.registerBlock(Blocks.darkStairs.get(), "bop.darkStairs");
		GameRegistry.registerBlock(Blocks.firStairs.get(), "bop.firStairs");
		GameRegistry.registerBlock(Blocks.holyStairs.get(), "bop.holyStairs");
		GameRegistry.registerBlock(Blocks.magicStairs.get(), "bop.magicStairs");
		GameRegistry.registerBlock(Blocks.mangroveStairs.get(), "bop.mangroveStairs");
		GameRegistry.registerBlock(Blocks.palmStairs.get(), "bop.palmStairs");
		GameRegistry.registerBlock(Blocks.redwoodStairs.get(), "bop.redwoodStairs");
		GameRegistry.registerBlock(Blocks.willowStairs.get(), "bop.willowStairs");
		GameRegistry.registerBlock(Blocks.pineStairs.get(), "bop.pineStairs");
		GameRegistry.registerBlock(Blocks.hellBarkStairs.get(), "bop.hellBarkStairs");
		GameRegistry.registerBlock(Blocks.jacarandaStairs.get(), "bop.jacarandaStairs");

		GameRegistry.registerBlock(Blocks.leavesColorized.get(), ItemBOPColorizedLeaves.class, "bop.leavesColorized");
	}
	
	private static void registerTileEntities()
	{
        GameRegistry.registerTileEntity(TileEntityAltar.class, "bop.tileEntityAltar");
	}

	/*private static void registerNames()
	{
		// Add block names
		LanguageRegistry.addName(new ItemStack(Blocks.mud.get(), 1, 0), "Mud");
		LanguageRegistry.addName(new ItemStack(Blocks.mud.get(), 1, 1), "Quicksand");

		LanguageRegistry.addName(Blocks.hardSand.get(), "Ash Stone");
		LanguageRegistry.addName(Blocks.hardSand.get(), "Hardened Sand");
		LanguageRegistry.addName(Blocks.hardDirt.get(), "Hardened Dirt");
		LanguageRegistry.addName(Blocks.hardIce.get(), "Hardened Ice");
		LanguageRegistry.addName(Blocks.driedDirt.get(), "Dried Dirt");
		LanguageRegistry.addName(Blocks.cragRock.get(), "Crag Rock");
		LanguageRegistry.addName(Blocks.mudBrick.get(), "Mud Bricks");
		LanguageRegistry.addName(Blocks.holyDirt.get(), "Purified Dirt");
		LanguageRegistry.addName(Blocks.crystal.get(), "Celestial Crystal");

		LanguageRegistry.addName(new ItemStack(Blocks.redRock.get(),1,0), "Red Rock");
		LanguageRegistry.addName(new ItemStack(Blocks.redRock.get(),1,1), "Red Rock Cobblestone");
		LanguageRegistry.addName(new ItemStack(Blocks.redRock.get(),1,2), "Red Rock Bricks");
		
		LanguageRegistry.addName(Blocks.ash.get(), "Ash Block");
		
		LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,0), "Dead Grass");
		LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,1), "Desert Grass");
		LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,2), "Desert Sprouts");
		LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,3), "Dune Grass");
		LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,4), "Purified Tall Grass");
		LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,5), "Thorns");
		LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,6), "Barley");
		LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,7), "Cattail");
		LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,8), "Reed");
		
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,0), "Clover");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,1), "Swampflower");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,2), "Deathbloom");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,3), "Glowflower");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,4), "Hydrangea");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,5), "Daisy");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,6), "Tulip");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,7), "Wildflower");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,8), "Violet");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,9), "Anemone");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,10), "Waterlily");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,11), "Tiny Cactus");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,12), "Aloe");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,13), "Sunflower");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,14), "Sunflower");
		LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,15), "Dandelion");
		
		LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,0), "Algae");
		LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,1), "Short Grass");
		LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,2), "Medium Grass");
		LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,3), "High Grass");
		LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,4), "Bush");
		LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,5), "Sprout");
		LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,6), "High Grass");
		LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,7), "Poison Ivy");
		LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,8), "Berry Bush");
		
		LanguageRegistry.addName(new ItemStack(Blocks.petals.get(),1,0), "Giant Red Flower");
		LanguageRegistry.addName(new ItemStack(Blocks.petals.get(),1,1), "Giant Yellow Flower");
		
		LanguageRegistry.addName(new ItemStack(Blocks.mushrooms.get(),1,0), "Toadstool");
		LanguageRegistry.addName(new ItemStack(Blocks.mushrooms.get(),1,1), "Portobello");
		LanguageRegistry.addName(new ItemStack(Blocks.mushrooms.get(),1,2), "Blue Milk Cap");
		LanguageRegistry.addName(new ItemStack(Blocks.mushrooms.get(),1,3), "Glowshroom");
		
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,0), "Apple Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,1), "Yellow Autumn Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,2), "Bamboo Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,3), "Magic Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,4), "Dark Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,5), "Dying Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,6), "Fir Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,7), "Loftwood Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,8), "Orange Autumn Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,9), "Origin Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,10), "Pink Cherry Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,11), "Maple Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,12), "White Cherry Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(), 1, 13), "Hellbark Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(), 1, 14), "Jacaranda Sapling");
		
		LanguageRegistry.addName(new ItemStack(Blocks.colorizedSaplings.get(),1,0), "Acacia Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.colorizedSaplings.get(),1,1), "Mangrove Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.colorizedSaplings.get(),1,2), "Palm Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.colorizedSaplings.get(),1,3), "Redwood Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.colorizedSaplings.get(),1,4), "Willow Sapling");
		LanguageRegistry.addName(new ItemStack(Blocks.colorizedSaplings.get(),1,5), "Pine Sapling");

		LanguageRegistry.addName(Blocks.willow.get(), "Willow");
		LanguageRegistry.addName(Blocks.ivy.get(), "Ivy");
		LanguageRegistry.addName(Blocks.leavesFruit.get(), "Apple Leaves");
		LanguageRegistry.addName(Blocks.bamboo.get(), "Bamboo");
		LanguageRegistry.addName(Blocks.mudBricksStairs.get(), "Mud Bricks Stairs");
		LanguageRegistry.addName(Blocks.originGrass.get(), "Origin Grass");
		LanguageRegistry.addName(Blocks.treeMoss.get(), "Tree Moss");

		LanguageRegistry.addName(new ItemStack(Blocks.coral.get(),1,0), "Kelp");

		LanguageRegistry.addName(Blocks.redCobbleStairs.get(), "Red Rock Cobblestone Stairs");
		LanguageRegistry.addName(Blocks.redBricksStairs.get(), "Red Rock Bricks Stairs");

		LanguageRegistry.addName(new ItemStack(Blocks.holyGrass.get(), 1, 0), "Purified Grass");
		LanguageRegistry.addName(new ItemStack(Blocks.holyGrass.get(), 1, 1), "Smoldering Grass");
		
		LanguageRegistry.addName(new ItemStack(Blocks.holyStone.get(),1,0), "Skystone");
		LanguageRegistry.addName(new ItemStack(Blocks.holyStone.get(),1,1), "Skystone Cobblestone");
		LanguageRegistry.addName(new ItemStack(Blocks.holyStone.get(),1,2), "Skystone Bricks");
		
		LanguageRegistry.addName(Blocks.holyCobbleStairs.get(), "Skystone Cobblestone Stairs");
		LanguageRegistry.addName(Blocks.holyBricksStairs.get(), "Skystone Bricks Stairs");
				
		LanguageRegistry.addName(Blocks.promisedPortal.get(), "Promised Land Portal");

		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 0), "Amethyst Ore");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 1), "Block of Amethyst");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 2), "Ruby Ore");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 3), "Block of Ruby");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 4), "Peridot Ore");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 5), "Block of Peridot");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 6), "Topaz Ore");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 7), "Block of Topaz");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 8), "Tanzanite Ore");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 9), "Block of Tanzanite");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 10), "Apatite Ore");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 11), "Block of Apatite");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 12), "Sapphire Ore");
		LanguageRegistry.addName(new ItemStack(Blocks.amethystOre.get(), 1, 13), "Block of Sapphire");
		
		LanguageRegistry.addName(Blocks.moss.get(), "Moss");
		
		LanguageRegistry.addName(Blocks.cloud.get(), "Cloud Block");

		LanguageRegistry.addName(new ItemStack(Blocks.bones.get(), 1, 0), "Small Bone Segment");
		LanguageRegistry.addName(new ItemStack(Blocks.bones.get(), 1, 1), "Medium Bone Segment");
		LanguageRegistry.addName(new ItemStack(Blocks.bones.get(), 1, 2), "Large Bone Segment");
		LanguageRegistry.addName(new ItemStack(Blocks.bones.get(), 1, 3), "Small Bone Segment");
		LanguageRegistry.addName(new ItemStack(Blocks.bones.get(), 1, 4), "Small Bone Segment");
		LanguageRegistry.addName(new ItemStack(Blocks.bones.get(), 1, 5), "Medium Bone Segment");
		LanguageRegistry.addName(new ItemStack(Blocks.bones.get(), 1, 6), "Medium Bone Segment");
		
		LanguageRegistry.addName(new ItemStack(Blocks.glass.get(), 1, 0), "Celestial Lens");
		LanguageRegistry.addName(new ItemStack(Blocks.glass.get(), 1, 1), "Sacrificial Focus");
		
		LanguageRegistry.addName(new ItemStack(Blocks.altar.get(), 1, 0), "Altar Frame");
		
		LanguageRegistry.addName(new ItemStack(Blocks.stoneDoubleSlab.get(),1,0), "Red Rock Cobblestone Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.stoneDoubleSlab.get(),1,1), "Red Rock Bricks Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.stoneDoubleSlab.get(),1,2), "Mud Bricks Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.stoneDoubleSlab.get(),1,3), "Skystone Cobblestone Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.stoneDoubleSlab.get(),1,4), "Skystone Bricks Slab");
		
		LanguageRegistry.addName(new ItemStack(Blocks.stoneSingleSlab.get(),1,0), "Red Rock Cobblestone Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.stoneSingleSlab.get(),1,1), "Red Rock Bricks Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.stoneSingleSlab.get(),1,2), "Mud Bricks Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.stoneSingleSlab.get(),1,3), "Skystone Cobblestone Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.stoneSingleSlab.get(),1,4), "Skystone Bricks Slab");

		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 0), "Acacia Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 1), "Cherry Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 2), "Dark Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 3), "Fir Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 4), "Loftwood Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 5), "Magic Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 6), "Mangrove Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 7), "Palm Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 8), "Redwood Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 9), "Willow Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 10), "Bamboo Thatching");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 11), "Pine Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 12), "Hellbark Wood Planks");
		LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 13), "Jacaranda Wood Planks");

		LanguageRegistry.addName(new ItemStack(Blocks.logs1.get(),1,0), "Acacia Wood");
		LanguageRegistry.addName(new ItemStack(Blocks.logs1.get(),1,1), "Cherry Wood");
		LanguageRegistry.addName(new ItemStack(Blocks.logs1.get(),1,2), "Dark Wood");
		LanguageRegistry.addName(new ItemStack(Blocks.logs1.get(),1,3), "Fir Wood");

		LanguageRegistry.addName(new ItemStack(Blocks.logs2.get(),1,0), "Loftwood Wood");
		LanguageRegistry.addName(new ItemStack(Blocks.logs2.get(),1,1), "Magic Wood");
		LanguageRegistry.addName(new ItemStack(Blocks.logs2.get(),1,2), "Mangrove Wood");
		LanguageRegistry.addName(new ItemStack(Blocks.logs2.get(),1,3), "Palm Wood");

		LanguageRegistry.addName(new ItemStack(Blocks.logs3.get(),1,0), "Redwood Wood");
		LanguageRegistry.addName(new ItemStack(Blocks.logs3.get(),1,1), "Willow Wood");
		LanguageRegistry.addName(new ItemStack(Blocks.logs3.get(),1,2), "Dead Wood");
		LanguageRegistry.addName(new ItemStack(Blocks.logs3.get(),1,3), "Giant Flower Stem");

		LanguageRegistry.addName(new ItemStack(Blocks.logs4.get(),1,0), "Pine Wood");
		LanguageRegistry.addName(new ItemStack(Blocks.logs4.get(),1,1), "Hellbark Wood");
		LanguageRegistry.addName(new ItemStack(Blocks.logs4.get(),1,2), "Jacaranda Wood");

		LanguageRegistry.addName(new ItemStack(Blocks.leaves1.get(),1,0), "Yellow Autumn Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves1.get(),1,1), "Bamboo Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves1.get(),1,2), "Magic Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves1.get(),1,3), "Dark Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves1.get(),1,4), "Dying Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves1.get(),1,5), "Fir Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves1.get(),1,6), "Loftwood Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves1.get(),1,7), "Orange Autumn Leaves");

		LanguageRegistry.addName(new ItemStack(Blocks.leaves2.get(),1,0), "Origin Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves2.get(),1,1), "Pink Cherry Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves2.get(),1,2), "Maple Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves2.get(),1,3), "White Cherry Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves2.get(),1,4), "Hellbark Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leaves2.get(),1,5), "Jacaranda Leaves");

		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,0), "Acacia Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,1), "Cherry Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,2), "Dark Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,3), "Fir Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,4), "Loftwood Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,5), "Magic Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,6), "Mangrove Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,7), "Palm Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab2.get(),1,0), "Redwood Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab2.get(),1,1), "Willow Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab2.get(),1,2), "Pine Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab2.get(),1,3), "Hellbark Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab2.get(),1,4), "Jacaranda Wood Slab");

		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,0), "Acacia Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,1), "Cherry Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,2), "Dark Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,3), "Fir Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,4), "Loftwood Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,5), "Magic Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,6), "Mangrove Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,7), "Palm Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab2.get(),1,0), "Redwood Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab2.get(),1,1), "Willow Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab2.get(),1,2), "Pine Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab2.get(),1,3), "Hellbark Wood Slab");
		LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab2.get(),1,4), "Jacaranda Wood Slab");

		LanguageRegistry.addName(Blocks.acaciaStairs.get(), "Acacia Wood Stairs");
		LanguageRegistry.addName(Blocks.cherryStairs.get(), "Cherry Wood Stairs");
		LanguageRegistry.addName(Blocks.darkStairs.get(), "Dark Wood Stairs");
		LanguageRegistry.addName(Blocks.firStairs.get(), "Fir Wood Stairs");
		LanguageRegistry.addName(Blocks.holyStairs.get(), "Loftwood Wood Stairs");
		LanguageRegistry.addName(Blocks.magicStairs.get(), "Magic Wood Stairs");
		LanguageRegistry.addName(Blocks.mangroveStairs.get(), "Mangrove Wood Stairs");
		LanguageRegistry.addName(Blocks.palmStairs.get(), "Palm Wood Stairs");
		LanguageRegistry.addName(Blocks.redwoodStairs.get(), "Redwood Wood Stairs");
		LanguageRegistry.addName(Blocks.willowStairs.get(), "Willow Wood Stairs");
		LanguageRegistry.addName(Blocks.pineStairs.get(), "Pine Wood Stairs");
		LanguageRegistry.addName(Blocks.hellBarkStairs.get(), "Hellbark Wood Stairs");
		LanguageRegistry.addName(Blocks.jacarandaStairs.get(), "Jacaranda Wood Stairs");

		LanguageRegistry.addName(new ItemStack(Blocks.leavesColorized.get(),1,3), "Redwood Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leavesColorized.get(),1,4), "Willow Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leavesColorized.get(),1,5), "Pine Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leavesColorized.get(),1,0), "Acacia Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leavesColorized.get(),1,2), "Palm Leaves");
		LanguageRegistry.addName(new ItemStack(Blocks.leavesColorized.get(),1,1), "Mangrove Leaves");
	}*/

	private static void addGrassPlants()
	{
		MinecraftForge.addGrassPlant(Blocks.flowers.get(), 0, 10);
		MinecraftForge.addGrassPlant(Blocks.flowers.get(), 4, 10);
		MinecraftForge.addGrassPlant(Blocks.flowers.get(), 9, 10);
		MinecraftForge.addGrassPlant(Blocks.flowers.get(), 15, 25);
		MinecraftForge.addGrassPlant(Blocks.foliage.get(), 1, 50);
		MinecraftForge.addGrassPlant(Blocks.foliage.get(), 2, 100);
		MinecraftForge.addGrassPlant(Blocks.foliage.get(), 4, 15);
		MinecraftForge.addGrassPlant(Blocks.foliage.get(), 5, 15);
	}
}
