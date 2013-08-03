package biomesoplenty.configuration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
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
import biomesoplenty.blocks.BlockGrave;
import biomesoplenty.blocks.BlockIvy;
import biomesoplenty.blocks.BlockLongGrass;
import biomesoplenty.blocks.BlockMoss;
import biomesoplenty.blocks.BlockMud;
import biomesoplenty.blocks.BlockOriginGrass;
import biomesoplenty.blocks.BlockPromisedPortal;
import biomesoplenty.blocks.BlockPuddle;
import biomesoplenty.blocks.BlockTreeMoss;
import biomesoplenty.blocks.BlockWillow;
import biomesoplenty.itemblocks.ItemBlockAltar;
import biomesoplenty.itemblocks.ItemBlockAppleLeaves;
import biomesoplenty.itemblocks.ItemBlockBamboo;
import biomesoplenty.itemblocks.ItemBlockBones;
import biomesoplenty.itemblocks.ItemBlockColorizedLeaves;
import biomesoplenty.itemblocks.ItemBlockColorizedSapling;
import biomesoplenty.itemblocks.ItemBlockCoral;
import biomesoplenty.itemblocks.ItemBlockFlower;
import biomesoplenty.itemblocks.ItemBlockFoliage;
import biomesoplenty.itemblocks.ItemBlockGlass;
import biomesoplenty.itemblocks.ItemBlockGrass;
import biomesoplenty.itemblocks.ItemBlockGrave;
import biomesoplenty.itemblocks.ItemBlockLeaves;
import biomesoplenty.itemblocks.ItemBlockLog;
import biomesoplenty.itemblocks.ItemBlockMoss;
import biomesoplenty.itemblocks.ItemBlockMud;
import biomesoplenty.itemblocks.ItemBlockMushroom;
import biomesoplenty.itemblocks.ItemBlockPetals;
import biomesoplenty.itemblocks.ItemBlockPlank;
import biomesoplenty.itemblocks.ItemBlockPlant;
import biomesoplenty.itemblocks.ItemBlockRedRock;
import biomesoplenty.itemblocks.ItemBlockSapling;
import biomesoplenty.itemblocks.ItemBlockSkystone;
import biomesoplenty.itemblocks.ItemBlockSlab;
import biomesoplenty.items.ItemBOPAmethyst;
import biomesoplenty.items.ItemBOPIvy;
import biomesoplenty.items.ItemBOPWillow;
import biomesoplenty.tileentities.TileEntityAltar;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBlocks 
{
	public static void init()
	{
		initializeBlocks();
		registerBlocks();
		registerTileEntities();

		//Shears VS Blocks
		Blocks.shearBlockIds.put(Block.leaves.blockID, 15.0F);
		Blocks.shearBlockIds.put(Blocks.leaves1.get().blockID, 15.0F);
		Blocks.shearBlockIds.put(Blocks.leaves2.get().blockID, 15.0F);
		Blocks.shearBlockIds.put(Blocks.leavesColorized.get().blockID, 15.0F);
		Blocks.shearBlockIds.put(Blocks.leavesFruit.get().blockID, 15.0F);

		MinecraftForge.setBlockHarvestLevel(Blocks.holyGrass.get(), 1, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.mud.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.ash.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.originGrass.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.longGrass.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.hardSand.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.holyGrass.get(), 0, "pickaxe", 0);

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
	}

	private static void initializeBlocks()
	{
		// Block declaration
		Blocks.mud = Optional.of((new BlockMud(BOPConfiguration.IDs.mudID)).setHardness(0.6F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("bop.mud"));
		Blocks.driedDirt = Optional.of(new BlockBOPGeneric(BOPConfiguration.IDs.driedDirtID, Material.rock, BlockType.DRIED_DIRT).setUnlocalizedName("bop.generic"));
		Blocks.redRock = Optional.of((new BlockBOPRedRock(BOPConfiguration.IDs.redRockID)).setUnlocalizedName("bop.redRocks"));
		Blocks.ash = Optional.of((new BlockAsh(BOPConfiguration.IDs.ashID)).setHardness(0.4F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("bop.ash"));
		Blocks.plants = Optional.of((new BlockBOPPlant(BOPConfiguration.IDs.plantsID)).setUnlocalizedName("bop.plants"));
		Blocks.flowers = Optional.of((new BlockBOPFlower(BOPConfiguration.IDs.flowersID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.flowers"));
		Blocks.mushrooms = Optional.of((new BlockBOPMushroom(BOPConfiguration.IDs.mushroomsID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.mushrooms"));
		Blocks.coral = Optional.of((new BlockBOPCoral(BOPConfiguration.IDs.coralID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.coral"));
		Blocks.willow = Optional.of((new BlockWillow(BOPConfiguration.IDs.willowID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.willow"));
		Blocks.ivy = Optional.of((new BlockIvy(BOPConfiguration.IDs.ivyID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.ivy"));
		Blocks.leaves1 = Optional.of((new BlockBOPLeaves(BOPConfiguration.IDs.leaves1ID, LeafCategory.CAT1)).setUnlocalizedName("bop.leaves1"));
		Blocks.leaves2 = Optional.of((new BlockBOPLeaves(BOPConfiguration.IDs.leaves2ID, LeafCategory.CAT2)).setUnlocalizedName("bop.leaves2"));
		Blocks.foliage = Optional.of((new BlockBOPFoliage(BOPConfiguration.IDs.foliageID)).setUnlocalizedName("bop.foliage"));
		Blocks.ashStone = Optional.of(new BlockBOPGeneric(BOPConfiguration.IDs.ashStoneID, Material.rock, BlockType.ASH_STONE));
		Blocks.hardIce = Optional.of(new BlockBOPGeneric(BOPConfiguration.IDs.hardIceID, Material.rock, BlockType.HARD_ICE));
		Blocks.leavesFruit = Optional.of((new BlockBOPAppleLeaves(BOPConfiguration.IDs.leavesFruitID)).setUnlocalizedName("bop.leavesFruit"));
		Blocks.bamboo = Optional.of(new BlockBamboo(BOPConfiguration.IDs.bambooID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.bamboo"));
		Blocks.mudBrick = Optional.of(new BlockBOPGeneric(BOPConfiguration.IDs.mudBrickBlockID, Material.rock, BlockType.MUD_BRICK));
		Blocks.mudBricksStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.mudBrickStairsID, Blocks.redRock.get(), Category.MUD_BRICKS)).setUnlocalizedName("bop.mudBricksStairs"));
		Blocks.stoneDoubleSlab = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.IDs.stoneDoubleSlabID, true, Material.rock, SlabCategory.STONE)).setUnlocalizedName("bop.stoneDoubleSlab"));
		Blocks.stoneSingleSlab = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.IDs.stoneSingleSlabID, false, Material.rock, SlabCategory.STONE)).setUnlocalizedName("bop.stoneSingleSlab"));
		Blocks.originGrass = Optional.of((new BlockOriginGrass(BOPConfiguration.IDs.originGrassID)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.originGrass"));
		Blocks.longGrass = Optional.of((new BlockLongGrass(BOPConfiguration.IDs.longGrassID)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.longGrass"));
		Blocks.treeMoss = Optional.of((new BlockTreeMoss(BOPConfiguration.IDs.treeMossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.treeMoss"));
		Blocks.logs1 = Optional.of((new BlockBOPLog(BOPConfiguration.IDs.logs1ID,LogCategory.CAT1)).setUnlocalizedName("bop.wood1"));
		Blocks.logs2 = Optional.of((new BlockBOPLog(BOPConfiguration.IDs.logs2ID,LogCategory.CAT2)).setUnlocalizedName("bop.wood2"));
		Blocks.logs3 = Optional.of((new BlockBOPLog(BOPConfiguration.IDs.logs3ID,LogCategory.CAT3)).setUnlocalizedName("bop.wood3"));
		Blocks.logs4 = Optional.of((new BlockBOPLog(BOPConfiguration.IDs.logs4ID,LogCategory.CAT4)).setUnlocalizedName("bop.wood4"));
		Blocks.petals = Optional.of((new BlockBOPPetals(BOPConfiguration.IDs.petalsID)).setUnlocalizedName("bop.petals"));
		Blocks.saplings = Optional.of((new BlockBOPSapling(BOPConfiguration.IDs.saplingsID)).setUnlocalizedName("bop.saplings"));
		Blocks.colorizedSaplings = Optional.of((new BlockBOPColorizedSapling(BOPConfiguration.IDs.colourizedSaplingsID)).setUnlocalizedName("bop.colorizedSaplings"));
		Blocks.redCobbleStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.redCobbleStairsID, Blocks.redRock.get(), Category.RED_COBBLE)).setUnlocalizedName("bop.redCobbleStairs"));
		Blocks.redBricksStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.redBrickStairsID, Blocks.redRock.get(), Category.RED_BRICKS)).setUnlocalizedName("bop.redBricksStairs"));
		Blocks.hardSand = Optional.of(new BlockBOPGeneric(BOPConfiguration.IDs.hardSandID, Material.sand, BlockType.HARD_SAND));
		Blocks.hardDirt = Optional.of(new BlockBOPGeneric(BOPConfiguration.IDs.hardDirtID, Material.rock, BlockType.HARD_DIRT));
		Blocks.holyGrass = Optional.of(new BlockBOPGrass(BOPConfiguration.IDs.holyGrassID).setUnlocalizedName("bop.holyGrass"));
		Blocks.holyDirt = Optional.of(new BlockBOPGeneric(BOPConfiguration.IDs.holyDirtID, Material.sand, BlockType.HOLY_DIRT));
		Blocks.holyStone = Optional.of((new BlockBOPSkystone(BOPConfiguration.IDs.holyStoneID)).setUnlocalizedName("bop.holyStone"));
		Blocks.holyCobbleStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.holyCobbleStairsID, Blocks.holyStone.get(), Category.HOLY_COBBLE)).setUnlocalizedName("bop.holyCobbleStairs"));
		Blocks.holyBricksStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.holyBrickStairsID, Blocks.holyStone.get(), Category.HOLY_BRICKS)).setUnlocalizedName("bop.holyBricksStairs"));
		Blocks.crystal = Optional.of(new BlockBOPGeneric(BOPConfiguration.IDs.crystalID, Material.glass, BlockType.CRYSTAL));
		Blocks.promisedPortal = Optional.of(new BlockPromisedPortal(BOPConfiguration.IDs.promisedLandPortalID).setUnlocalizedName("bop.promisedPortal").setBlockUnbreakable().setResistance(6000000.0F).setLightValue(1.0F));

		Blocks.amethystOre = Optional.of(new BlockBOPAmethyst(BOPConfiguration.IDs.amethystOreID, Material.rock).setUnlocalizedName("bop.amethystOre"));

		Blocks.moss = Optional.of((new BlockMoss(BOPConfiguration.IDs.mossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.moss"));
		
		Blocks.cragRock = Optional.of(new BlockBOPGeneric(BOPConfiguration.IDs.cragRockID, Material.rock, BlockType.CRAG_ROCK));
		
		Blocks.cloud = Optional.of((new BlockCloud(BOPConfiguration.IDs.cloudID)).setHardness(0.1F).setLightOpacity(3).setStepSound(Block.soundClothFootstep).setUnlocalizedName("bop.cloud"));

		Blocks.bones = Optional.of((new BlockBones(BOPConfiguration.IDs.bonesID)).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.bones"));
		
		Blocks.glass = Optional.of((new BlockBOPGlass(BOPConfiguration.IDs.glassID)).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("bop.glass"));
		
		Blocks.altar = Optional.of((new BlockAltar(BOPConfiguration.IDs.altarID)).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.altar"));
		
		Blocks.puddle = Optional.of((new BlockPuddle(BOPConfiguration.IDs.puddleID)).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("bop.puddle"));
		Blocks.grave = Optional.of((new BlockGrave(BOPConfiguration.IDs.graveID)).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.grave"));

		Blocks.planks = Optional.of((new BlockBOPPlank(BOPConfiguration.IDs.planksID)).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bop.planks"));

		Blocks.woodenDoubleSlab1 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.IDs.woodenDoubleSlab1ID, true, Material.wood, SlabCategory.WOOD1)).setUnlocalizedName("bop.woodenDoubleSlab1"));
		Blocks.woodenSingleSlab1 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.IDs.woodenSingleSlab1ID, false, Material.wood, SlabCategory.WOOD1)).setUnlocalizedName("bop.woodenSingleSlab1"));
		Blocks.woodenDoubleSlab2 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.IDs.woodenDoubleSlab2ID, true, Material.wood, SlabCategory.WOOD2)).setUnlocalizedName("bop.woodenDoubleSlab2"));
		Blocks.woodenSingleSlab2 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfiguration.IDs.woodenSingleSlab2ID, false, Material.wood, SlabCategory.WOOD2)).setUnlocalizedName("bop.woodenSingleSlab2"));

		Blocks.acaciaStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.acaciaStairsID, Blocks.planks.get(), Category.ACACIA)).setUnlocalizedName("bop.acaciaStairs"));
		Blocks.cherryStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.cherryStairsID, Blocks.planks.get(), Category.CHERRY)).setUnlocalizedName("bop.cherryStairs"));
		Blocks.darkStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.darkStairsID, Blocks.planks.get(), Category.DARK)).setUnlocalizedName("bop.darkStairs"));
		Blocks.firStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.firStairsID, Blocks.planks.get(), Category.FIR)).setUnlocalizedName("bop.firStairs"));
		Blocks.holyStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.holyStairsID, Blocks.planks.get(), Category.HOLY)).setUnlocalizedName("bop.holyStairs"));
		Blocks.magicStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.magicStairsID, Blocks.planks.get(), Category.MAGIC)).setUnlocalizedName("bop.magicStairs"));
		Blocks.mangroveStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.mangroveStairsID, Blocks.planks.get(), Category.MANGROVE)).setUnlocalizedName("bop.mangroveStairs"));
		Blocks.palmStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.palmStairsID, Blocks.planks.get(), Category.PALM)).setUnlocalizedName("bop.palmStairs"));
		Blocks.redwoodStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.redwoodStairsID, Blocks.planks.get(), Category.REDWOOD)).setUnlocalizedName("bop.redwoodStairs"));
		Blocks.willowStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.willowStairsID, Blocks.planks.get(), Category.WILLOW)).setUnlocalizedName("bop.willowStairs"));
		Blocks.pineStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.pineStairsID, Blocks.planks.get(), Category.PINE)).setUnlocalizedName("bop.pineStairs"));
		Blocks.hellBarkStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.hellBarkStairsID, Blocks.planks.get(), Category.HELL_BARK)).setUnlocalizedName("bop.hellBarkStairs"));
		Blocks.jacarandaStairs = Optional.of((new BlockBOPStairs(BOPConfiguration.IDs.jacarandaStairsID, Blocks.planks.get(), Category.JACARANDA)).setUnlocalizedName("bop.jacarandaStairs"));
		Blocks.leavesColorized = Optional.of((new BlockBOPColorizedLeaves(BOPConfiguration.IDs.colourizedLeavesID)).setUnlocalizedName("bop.leavesColorized"));
	}

	private static void registerBlocks()
	{
		// Add block registration
		GameRegistry.registerBlock(Blocks.mud.get(), ItemBlockMud.class, "bop.mud");
		GameRegistry.registerBlock(Blocks.driedDirt.get(), "bop.driedDirt");
		GameRegistry.registerBlock(Blocks.redRock.get(), ItemBlockRedRock.class, "bop.redRock");
		GameRegistry.registerBlock(Blocks.ash.get(), "bop.ash");
		GameRegistry.registerBlock(Blocks.plants.get(), ItemBlockPlant.class, "bop.plants");
		GameRegistry.registerBlock(Blocks.flowers.get(), ItemBlockFlower.class, "bop.flowers");
		GameRegistry.registerBlock(Blocks.mushrooms.get(), ItemBlockMushroom.class, "bop.mushrooms");
		GameRegistry.registerBlock(Blocks.coral.get(), ItemBlockCoral.class, "bop.coral");
		GameRegistry.registerBlock(Blocks.willow.get(), ItemBOPWillow.class, "bop.willow");
		GameRegistry.registerBlock(Blocks.ivy.get(), ItemBOPIvy.class, "bop.ivy");
		GameRegistry.registerBlock(Blocks.leaves1.get(), ItemBlockLeaves.class, "bop.leaves1");
		GameRegistry.registerBlock(Blocks.leaves2.get(), ItemBlockLeaves.class, "bop.leaves2");
		GameRegistry.registerBlock(Blocks.foliage.get(), ItemBlockFoliage.class, "bop.foliage");
		GameRegistry.registerBlock(Blocks.ashStone.get(), "bop.ashStone");
		GameRegistry.registerBlock(Blocks.hardIce.get(), "bop.hardIce");
		GameRegistry.registerBlock(Blocks.leavesFruit.get(), ItemBlockAppleLeaves.class, "bop.leavesFruit");
		GameRegistry.registerBlock(Blocks.bamboo.get(), ItemBlockBamboo.class, "bop.bamboo");
		GameRegistry.registerBlock(Blocks.mudBrick.get(), "bop.mudBrick");
		GameRegistry.registerBlock(Blocks.mudBricksStairs.get(), "bop.mudBricksStairs");
		GameRegistry.registerBlock(Blocks.originGrass.get(), "bop.originGrass");
		GameRegistry.registerBlock(Blocks.longGrass.get(), "bop.longGrass");
		GameRegistry.registerBlock(Blocks.treeMoss.get(), "bop.treeMoss");
		GameRegistry.registerBlock(Blocks.logs1.get(), ItemBlockLog.class, "bop.wood1");
		GameRegistry.registerBlock(Blocks.logs2.get(), ItemBlockLog.class, "bop.wood2");
		GameRegistry.registerBlock(Blocks.logs3.get(), ItemBlockLog.class, "bop.wood3");
		GameRegistry.registerBlock(Blocks.logs4.get(), ItemBlockLog.class, "bop.wood4");
		GameRegistry.registerBlock(Blocks.petals.get(), ItemBlockPetals.class, "bop.petals");
		GameRegistry.registerBlock(Blocks.saplings.get(), ItemBlockSapling.class, "bop.saplings");
		GameRegistry.registerBlock(Blocks.colorizedSaplings.get(), ItemBlockColorizedSapling.class, "bop.colorizedSaplings");
		GameRegistry.registerBlock(Blocks.redCobbleStairs.get(), "bop.redCobbleStairs");
		GameRegistry.registerBlock(Blocks.redBricksStairs.get(), "bop.redBricksStairs");
		GameRegistry.registerBlock(Blocks.hardSand.get(), "bop.hardSand");
		GameRegistry.registerBlock(Blocks.hardDirt.get(), "bop.hardDirt");
		GameRegistry.registerBlock(Blocks.crystal.get(), "bop.crystal");
		GameRegistry.registerBlock(Blocks.holyGrass.get(), ItemBlockGrass.class, "bop.holyGrass");
		GameRegistry.registerBlock(Blocks.holyDirt.get(), "bop.holyDirt");
		GameRegistry.registerBlock(Blocks.holyStone.get(), ItemBlockSkystone.class, "bop.holyStone");
		GameRegistry.registerBlock(Blocks.holyCobbleStairs.get(), "bop.holyCobbleStairs");
		GameRegistry.registerBlock(Blocks.holyBricksStairs.get(), "bop.holyBricksStairs");
		GameRegistry.registerBlock(Blocks.promisedPortal.get(), "bop.promisedPortal");
		GameRegistry.registerBlock(Blocks.amethystOre.get(), ItemBOPAmethyst.class, "bop.amethystOre");
		GameRegistry.registerBlock(Blocks.moss.get(), ItemBlockMoss.class, "bop.moss");
		GameRegistry.registerBlock(Blocks.cragRock.get(), "bop.cragRock");
		GameRegistry.registerBlock(Blocks.cloud.get(), "bop.cloud");

		GameRegistry.registerBlock(Blocks.bones.get(), ItemBlockBones.class, "bop.bones");
		GameRegistry.registerBlock(Blocks.glass.get(), ItemBlockGlass.class, "bop.glass");	
		GameRegistry.registerBlock(Blocks.altar.get(), ItemBlockAltar.class, "bop.altar");	
		GameRegistry.registerBlock(Blocks.puddle.get(), "bop.puddle");	
		GameRegistry.registerBlock(Blocks.grave.get(), ItemBlockGrave.class, "bop.grave");	

		ItemBlockSlab.setSlabs(Blocks.stoneSingleSlab.get(), Blocks.stoneDoubleSlab.get());
		GameRegistry.registerBlock(Blocks.stoneDoubleSlab.get(), ItemBlockSlab.class, "bop.stoneDoubleSlab");
		GameRegistry.registerBlock(Blocks.stoneSingleSlab.get(), ItemBlockSlab.class, "bop.stoneSingleSlab");
		ItemBlockSlab.setSlabs(Blocks.woodenSingleSlab1.get(), Blocks.woodenDoubleSlab1.get());
		GameRegistry.registerBlock(Blocks.woodenDoubleSlab1.get(), ItemBlockSlab.class, "bop.woodenDoubleSlab1");
		GameRegistry.registerBlock(Blocks.woodenSingleSlab1.get(), ItemBlockSlab.class, "bop.woodenSingleSlab1");
		ItemBlockSlab.setSlabs(Blocks.woodenSingleSlab2.get(), Blocks.woodenDoubleSlab2.get());
		GameRegistry.registerBlock(Blocks.woodenDoubleSlab2.get(), ItemBlockSlab.class, "bop.woodenDoubleSlab2");
		GameRegistry.registerBlock(Blocks.woodenSingleSlab2.get(), ItemBlockSlab.class, "bop.woodenSingleSlab2");

		GameRegistry.registerBlock(Blocks.planks.get(), ItemBlockPlank.class, "bop.planks");

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

		GameRegistry.registerBlock(Blocks.leavesColorized.get(), ItemBlockColorizedLeaves.class, "bop.leavesColorized");
	}
	
	private static void registerTileEntities()
	{
        GameRegistry.registerTileEntity(TileEntityAltar.class, "tileEntityAltar");
	}

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
