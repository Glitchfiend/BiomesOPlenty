package biomesoplenty.core;

import biomesoplenty.blocks.BlockMud;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBlocks 
{
	public static void init()
	{
		initializeBlocks();
		/*registerBlocks();
		registerTileEntities();

		//Shears VS Blocks
		BOPBlocks.shearBlockIds.put(Block.leaves.blockID, 15.0F);
		BOPBlocks.shearBlockIds.put(BOPBlocks.leaves1.get().blockID, 15.0F);
		BOPBlocks.shearBlockIds.put(BOPBlocks.leaves2.get().blockID, 15.0F);
		BOPBlocks.shearBlockIds.put(BOPBlocks.leavesColorized1.get().blockID, 15.0F);
		BOPBlocks.shearBlockIds.put(BOPBlocks.leavesColorized2.get().blockID, 15.0F);
		BOPBlocks.shearBlockIds.put(BOPBlocks.leavesFruit.get().blockID, 15.0F);
		BOPBlocks.shearBlockIds.put(BOPBlocks.leavesFruit2.get().blockID, 15.0F);

		MinecraftForge.setBlockHarvestLevel(BOPBlocks.holyGrass.get(), 1, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.mud.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.ash.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.originGrass.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.longGrass.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.hardSand.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.holyGrass.get(), 0, "pickaxe", 0);

		MinecraftForge.setBlockHarvestLevel(BOPBlocks.driedDirt.get(), "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.amethystOre.get(), 0, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.amethystOre.get(), 2, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.amethystOre.get(), 4, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.amethystOre.get(), 6, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.amethystOre.get(), 8, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.amethystOre.get(), 10, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BOPBlocks.amethystOre.get(), 12, "pickaxe", 2);

		addGrassPlants();*/
	}

	private static void initializeBlocks()
	{
		// Block declaration
		//TODO:						setBlockName
		registerBlock(new BlockMud().func_149663_c("mud"));
		/*BOPBlocks.driedDirt = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.driedDirtID, Material.rock, BlockType.DRIED_DIRT).setUnlocalizedName("bop.generic"));
		BOPBlocks.redRock = Optional.of((new BlockBOPRedRock(BOPConfigurationIDs.redRockID)).setUnlocalizedName("bop.redRocks"));
		BOPBlocks.ash = Optional.of((new BlockAsh(BOPConfigurationIDs.ashID)).setHardness(0.4F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("bop.ash"));
		BOPBlocks.flesh = Optional.of((new BlockFlesh(BOPConfigurationIDs.fleshID)).setHardness(0.4F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("bop.flesh"));
		BOPBlocks.plants = Optional.of((new BlockBOPPlant(BOPConfigurationIDs.plantsID)).setUnlocalizedName("bop.plants"));
		BOPBlocks.flowers = Optional.of((new BlockBOPFlower(BOPConfigurationIDs.flowersID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.flowers"));
		BOPBlocks.flowers2 = Optional.of((new BlockBOPFlower2(BOPConfigurationIDs.flowers2ID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.flowers2"));
		BOPBlocks.stoneFormations = Optional.of((new BlockStoneFormations(BOPConfigurationIDs.stoneFormationsID)).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.stoneFormations"));
		BOPBlocks.mushrooms = Optional.of((new BlockBOPMushroom(BOPConfigurationIDs.mushroomsID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.mushrooms"));
		BOPBlocks.coral = Optional.of((new BlockBOPCoral(BOPConfigurationIDs.coralID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.coral"));
		BOPBlocks.willow = Optional.of((new BlockWillow(BOPConfigurationIDs.willowID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.willow"));
		BOPBlocks.ivy = Optional.of((new BlockIvy(BOPConfigurationIDs.ivyID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.ivy"));
		BOPBlocks.leaves1 = Optional.of((new BlockBOPLeaves(BOPConfigurationIDs.leaves1ID, LeafCategory.CAT1)).setUnlocalizedName("bop.leaves1"));
		BOPBlocks.leaves2 = Optional.of((new BlockBOPLeaves(BOPConfigurationIDs.leaves2ID, LeafCategory.CAT2)).setUnlocalizedName("bop.leaves2"));
		BOPBlocks.leaves3 = Optional.of((new BlockBOPLeaves(BOPConfigurationIDs.leaves3ID, LeafCategory.CAT3)).setUnlocalizedName("bop.leaves3"));
	    BOPBlocks.leaves4 = Optional.of((new BlockBOPLeaves(BOPConfigurationIDs.leaves4ID, LeafCategory.CAT4)).setUnlocalizedName("bop.leaves4"));
		BOPBlocks.foliage = Optional.of((new BlockBOPFoliage(BOPConfigurationIDs.foliageID)).setUnlocalizedName("bop.foliage"));
		BOPBlocks.turnip = Optional.of((new BlockTurnip(BOPConfigurationIDs.turnipID)).setUnlocalizedName("bop.turnip").setTextureName("turnip"));
		BOPBlocks.ashStone = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.ashStoneID, Material.rock, BlockType.ASH_STONE));
		BOPBlocks.hardIce = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.hardIceID, Material.rock, BlockType.HARD_ICE));
		BOPBlocks.leavesFruit = Optional.of((new BlockBOPAppleLeaves(BOPConfigurationIDs.leavesFruitID)).setUnlocalizedName("bop.leavesFruit"));
		BOPBlocks.leavesFruit2 = Optional.of((new BlockBOPPersimmonLeaves(BOPConfigurationIDs.leavesFruit2ID)).setUnlocalizedName("bop.leavesFruit2"));
		BOPBlocks.bamboo = Optional.of(new BlockBamboo(BOPConfigurationIDs.bambooID).setHardness(0.2F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bop.bamboo"));
		BOPBlocks.mudBrick = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.mudBrickBlockID, Material.rock, BlockType.MUD_BRICK));
		BOPBlocks.mudBricksStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.mudBrickStairsID, BOPBlocks.redRock.get(), Category.MUD_BRICKS)).setHardness(1.0F).setUnlocalizedName("bop.mudBricksStairs"));
		BOPBlocks.stoneDoubleSlab = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.stoneDoubleSlabID, true, Material.rock, SlabCategory.STONE)).setUnlocalizedName("bop.stoneDoubleSlab"));
		BOPBlocks.stoneSingleSlab = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.stoneSingleSlabID, false, Material.rock, SlabCategory.STONE)).setUnlocalizedName("bop.stoneSingleSlab"));
		BOPBlocks.originGrass = Optional.of((new BlockOriginGrass(BOPConfigurationIDs.originGrassID)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.originGrass"));
		BOPBlocks.longGrass = Optional.of((new BlockLongGrass(BOPConfigurationIDs.longGrassID)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.longGrass"));
		BOPBlocks.overgrownNetherrack = Optional.of((new BlockOvergrownNetherrack(BOPConfigurationIDs.overgrownNetherrackID)).setHardness(0.4F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.overgrownNetherrack"));
		BOPBlocks.treeMoss = Optional.of((new BlockTreeMoss(BOPConfigurationIDs.treeMossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.treeMoss"));
		BOPBlocks.logs1 = Optional.of((new BlockBOPLog(BOPConfigurationIDs.logs1ID,LogCategory.CAT1)).setUnlocalizedName("bop.wood1"));
		BOPBlocks.logs2 = Optional.of((new BlockBOPLog(BOPConfigurationIDs.logs2ID,LogCategory.CAT2)).setUnlocalizedName("bop.wood2"));
		BOPBlocks.logs3 = Optional.of((new BlockBOPLog(BOPConfigurationIDs.logs3ID,LogCategory.CAT3)).setUnlocalizedName("bop.wood3"));
		BOPBlocks.logs4 = Optional.of((new BlockBOPLog(BOPConfigurationIDs.logs4ID,LogCategory.CAT4)).setUnlocalizedName("bop.wood4"));
		BOPBlocks.petals = Optional.of((new BlockBOPPetals(BOPConfigurationIDs.petalsID)).setUnlocalizedName("bop.petals"));
		BOPBlocks.saplings = Optional.of((new BlockBOPSapling(BOPConfigurationIDs.saplingsID)).setUnlocalizedName("bop.saplings"));
		BOPBlocks.colorizedSaplings = Optional.of((new BlockBOPColorizedSapling(BOPConfigurationIDs.colourizedSaplingsID)).setUnlocalizedName("bop.colorizedSaplings"));
		BOPBlocks.redCobbleStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.redCobbleStairsID, BOPBlocks.redRock.get(), Category.RED_COBBLE)).setHardness(1.6F).setUnlocalizedName("bop.redCobbleStairs"));
		BOPBlocks.redBricksStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.redBrickStairsID, BOPBlocks.redRock.get(), Category.RED_BRICKS)).setHardness(1.1F).setUnlocalizedName("bop.redBricksStairs"));
		BOPBlocks.hardSand = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.hardSandID, Material.sand, BlockType.HARD_SAND));
		BOPBlocks.hardDirt = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.hardDirtID, Material.rock, BlockType.HARD_DIRT));
		BOPBlocks.holyGrass = Optional.of(new BlockBOPGrass(BOPConfigurationIDs.holyGrassID).setUnlocalizedName("bop.holyGrass"));
		BOPBlocks.holyDirt = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.holyDirtID, Material.sand, BlockType.HOLY_DIRT));
		BOPBlocks.holyStone = Optional.of((new BlockBOPSkystone(BOPConfigurationIDs.holyStoneID)).setUnlocalizedName("bop.holyStone"));
		BOPBlocks.holyCobbleStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.holyCobbleStairsID, BOPBlocks.holyStone.get(), Category.HOLY_COBBLE)).setHardness(1.6F).setUnlocalizedName("bop.holyCobbleStairs"));
		BOPBlocks.holyBricksStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.holyBrickStairsID, BOPBlocks.holyStone.get(), Category.HOLY_BRICKS)).setHardness(1.1F).setUnlocalizedName("bop.holyBricksStairs"));
		BOPBlocks.crystal = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.crystalID, Material.glass, BlockType.CRYSTAL));
		BOPBlocks.promisedPortal = Optional.of(new BlockPromisedPortal(BOPConfigurationIDs.promisedLandPortalID).setUnlocalizedName("bop.promisedPortal").setBlockUnbreakable().setResistance(6000000.0F).setLightValue(1.0F));

		BOPBlocks.amethystOre = Optional.of(new BlockBOPAmethyst(BOPConfigurationIDs.amethystOreID, Material.rock).setUnlocalizedName("bop.amethystOre"));

		BOPBlocks.moss = Optional.of((new BlockMoss(BOPConfigurationIDs.mossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.moss"));
		
		BOPBlocks.cragRock = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.cragRockID, Material.rock, BlockType.CRAG_ROCK));
		
		BOPBlocks.cloud = Optional.of((new BlockCloud(BOPConfigurationIDs.cloudID)).setHardness(0.1F).setLightOpacity(3).setStepSound(Block.soundClothFootstep).setUnlocalizedName("bop.cloud"));
		
		BOPBlocks.hive = Optional.of((new BlockHive(BOPConfigurationIDs.hiveID)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.hive"));
		BOPBlocks.honeyBlock = Optional.of((new BlockHoney(BOPConfigurationIDs.honeyBlockID)).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.honeyBlock"));

		BOPBlocks.bones = Optional.of((new BlockBones(BOPConfigurationIDs.bonesID)).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.bones"));
		
		BOPBlocks.puddle = Optional.of((new BlockPuddle(BOPConfigurationIDs.puddleID)).setHardness(0.6F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("bop.puddle"));
		BOPBlocks.grave = Optional.of((new BlockGrave(BOPConfigurationIDs.graveID)).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.grave"));

		BOPBlocks.planks = Optional.of((new BlockBOPPlank(BOPConfigurationIDs.planksID)).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bop.planks"));

		BOPBlocks.woodenDoubleSlab1 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.woodenDoubleSlab1ID, true, Material.wood, SlabCategory.WOOD1)).setUnlocalizedName("bop.woodenDoubleSlab1"));
		BOPBlocks.woodenSingleSlab1 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.woodenSingleSlab1ID, false, Material.wood, SlabCategory.WOOD1)).setUnlocalizedName("bop.woodenSingleSlab1"));
		BOPBlocks.woodenDoubleSlab2 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.woodenDoubleSlab2ID, true, Material.wood, SlabCategory.WOOD2)).setUnlocalizedName("bop.woodenDoubleSlab2"));
		BOPBlocks.woodenSingleSlab2 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.woodenSingleSlab2ID, false, Material.wood, SlabCategory.WOOD2)).setUnlocalizedName("bop.woodenSingleSlab2"));

		BOPBlocks.acaciaStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.acaciaStairsID, BOPBlocks.planks.get(), Category.ACACIA)).setUnlocalizedName("bop.acaciaStairs"));
		BOPBlocks.cherryStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.cherryStairsID, BOPBlocks.planks.get(), Category.CHERRY)).setUnlocalizedName("bop.cherryStairs"));
		BOPBlocks.darkStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.darkStairsID, BOPBlocks.planks.get(), Category.DARK)).setUnlocalizedName("bop.darkStairs"));
		BOPBlocks.firStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.firStairsID, BOPBlocks.planks.get(), Category.FIR)).setUnlocalizedName("bop.firStairs"));
		BOPBlocks.holyStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.holyStairsID, BOPBlocks.planks.get(), Category.HOLY)).setUnlocalizedName("bop.holyStairs"));
		BOPBlocks.magicStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.magicStairsID, BOPBlocks.planks.get(), Category.MAGIC)).setUnlocalizedName("bop.magicStairs"));
		BOPBlocks.mangroveStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.mangroveStairsID, BOPBlocks.planks.get(), Category.MANGROVE)).setUnlocalizedName("bop.mangroveStairs"));
		BOPBlocks.palmStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.palmStairsID, BOPBlocks.planks.get(), Category.PALM)).setUnlocalizedName("bop.palmStairs"));
		BOPBlocks.redwoodStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.redwoodStairsID, BOPBlocks.planks.get(), Category.REDWOOD)).setUnlocalizedName("bop.redwoodStairs"));
		BOPBlocks.willowStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.willowStairsID, BOPBlocks.planks.get(), Category.WILLOW)).setUnlocalizedName("bop.willowStairs"));
		BOPBlocks.pineStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.pineStairsID, BOPBlocks.planks.get(), Category.PINE)).setUnlocalizedName("bop.pineStairs"));
		BOPBlocks.hellBarkStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.hellBarkStairsID, BOPBlocks.planks.get(), Category.HELL_BARK)).setUnlocalizedName("bop.hellBarkStairs"));
		BOPBlocks.jacarandaStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.jacarandaStairsID, BOPBlocks.planks.get(), Category.JACARANDA)).setUnlocalizedName("bop.jacarandaStairs"));
		
		BOPBlocks.leavesColorized1 = Optional.of((new BlockBOPColorizedLeaves(BOPConfigurationIDs.colourizedLeaves1ID, ColourizedLeafCategory.CAT1)).setUnlocalizedName("bop.leavesColorized1"));
	    BOPBlocks.leavesColorized2 = Optional.of((new BlockBOPColorizedLeaves(BOPConfigurationIDs.colourizedLeaves2ID, ColourizedLeafCategory.CAT2)).setUnlocalizedName("bop.leavesColorized2"));
	    //Blocks.leavesColorized3 = Optional.of((new BlockBOPColorizedLeaves(BOPConfigurationIDs.colourizedLeaves3ID, ColourizedLeafCategory.CAT3)).setUnlocalizedName("bop.leavesColorized3"));
	    //Blocks.leavesColorized4 = Optional.of((new BlockBOPColorizedLeaves(BOPConfigurationIDs.colourizedLeaves4ID, ColourizedLeafCategory.CAT4)).setUnlocalizedName("bop.leavesColorized4"));*/
	}

	/*private static void registerBlocks()
	{
		// Add block registration
		registerBlock(BOPBlocks.mud.get(), ItemBlockMud.class);
		registerBlock(BOPBlocks.driedDirt.get());
		registerBlock(BOPBlocks.redRock.get(), ItemBlockRedRock.class);
		registerBlock(BOPBlocks.ash.get());
		registerBlock(BOPBlocks.flesh.get());
		registerBlock(BOPBlocks.plants.get(), ItemBlockPlant.class);
		registerBlock(BOPBlocks.flowers.get(), ItemBlockFlower.class);
		registerBlock(BOPBlocks.flowers2.get(), ItemBlockFlower2.class);
		registerBlock(BOPBlocks.stoneFormations.get(), ItemBlockStoneFormations.class);
		registerBlock(BOPBlocks.mushrooms.get(), ItemBlockMushroom.class);
		registerBlock(BOPBlocks.coral.get(), ItemBlockCoral.class);
		registerBlock(BOPBlocks.willow.get(), ItemBOPWillow.class);
		registerBlock(BOPBlocks.ivy.get(), ItemBOPIvy.class);
		registerBlock(BOPBlocks.leaves1.get(), ItemBlockLeaves.class);
		registerBlock(BOPBlocks.leaves2.get(), ItemBlockLeaves.class);
	    registerBlock(BOPBlocks.leaves3.get(), ItemBlockLeaves.class);
	    registerBlock(BOPBlocks.leaves4.get(), ItemBlockLeaves.class);
		registerBlock(BOPBlocks.foliage.get(), ItemBlockFoliage.class);
		registerBlock(BOPBlocks.turnip.get());
		registerBlock(BOPBlocks.ashStone.get());
		registerBlock(BOPBlocks.hardIce.get());
		registerBlock(BOPBlocks.leavesFruit.get(), ItemBlockAppleLeaves.class);
		registerBlock(BOPBlocks.leavesFruit2.get(), ItemBlockPersimmonLeaves.class);
		registerBlock(BOPBlocks.bamboo.get(), ItemBlockBamboo.class);
		registerBlock(BOPBlocks.mudBrick.get());
		registerBlock(BOPBlocks.mudBricksStairs.get());
		registerBlock(BOPBlocks.originGrass.get());
		registerBlock(BOPBlocks.longGrass.get());
		registerBlock(BOPBlocks.overgrownNetherrack.get());
		registerBlock(BOPBlocks.treeMoss.get());
		registerBlock(BOPBlocks.logs1.get(), ItemBlockLog.class);
		registerBlock(BOPBlocks.logs2.get(), ItemBlockLog.class);
		registerBlock(BOPBlocks.logs3.get(), ItemBlockLog.class);
		registerBlock(BOPBlocks.logs4.get(), ItemBlockLog.class);
		registerBlock(BOPBlocks.petals.get(), ItemBlockPetals.class);
		registerBlock(BOPBlocks.saplings.get(), ItemBlockSapling.class);
		registerBlock(BOPBlocks.colorizedSaplings.get(), ItemBlockColorizedSapling.class);
		registerBlock(BOPBlocks.redCobbleStairs.get());
		registerBlock(BOPBlocks.redBricksStairs.get());
		registerBlock(BOPBlocks.hardSand.get());
		registerBlock(BOPBlocks.hardDirt.get());
		registerBlock(BOPBlocks.crystal.get());
		registerBlock(BOPBlocks.holyGrass.get(), ItemBlockGrass.class);
		registerBlock(BOPBlocks.holyDirt.get());
		registerBlock(BOPBlocks.holyStone.get(), ItemBlockSkystone.class);
		registerBlock(BOPBlocks.holyCobbleStairs.get());
		registerBlock(BOPBlocks.holyBricksStairs.get());
		registerBlock(BOPBlocks.promisedPortal.get());
		registerBlock(BOPBlocks.amethystOre.get(), ItemBOPAmethyst.class);
		registerBlock(BOPBlocks.moss.get(), ItemBlockMoss.class);
		registerBlock(BOPBlocks.cragRock.get());
		registerBlock(BOPBlocks.cloud.get());
		registerBlock(BOPBlocks.hive.get(), ItemBlockHive.class);
		registerBlock(BOPBlocks.honeyBlock.get());

		registerBlock(BOPBlocks.bones.get(), ItemBlockBones.class);
		registerBlock(BOPBlocks.puddle.get());	
		registerBlock(BOPBlocks.grave.get(), ItemBlockGrave.class);	

		ItemBlockSlab.setSlabs(BOPBlocks.stoneSingleSlab.get(), BOPBlocks.stoneDoubleSlab.get());
		
		registerBlock(BOPBlocks.stoneDoubleSlab.get(), ItemBlockSlab.class);
		registerBlock(BOPBlocks.stoneSingleSlab.get(), ItemBlockSlab.class);
		
		ItemBlockSlab.setSlabs(BOPBlocks.woodenSingleSlab1.get(), BOPBlocks.woodenDoubleSlab1.get());
		
		registerBlock(BOPBlocks.woodenDoubleSlab1.get(), ItemBlockSlab.class);
		registerBlock(BOPBlocks.woodenSingleSlab1.get(), ItemBlockSlab.class);
		
		ItemBlockSlab.setSlabs(BOPBlocks.woodenSingleSlab2.get(), BOPBlocks.woodenDoubleSlab2.get());
		
		registerBlock(BOPBlocks.woodenDoubleSlab2.get(), ItemBlockSlab.class);
		registerBlock(BOPBlocks.woodenSingleSlab2.get(), ItemBlockSlab.class);

		registerBlock(BOPBlocks.planks.get(), ItemBlockPlank.class);

		registerBlock(BOPBlocks.acaciaStairs.get());
		registerBlock(BOPBlocks.cherryStairs.get());
		registerBlock(BOPBlocks.darkStairs.get());
		registerBlock(BOPBlocks.firStairs.get());
		registerBlock(BOPBlocks.holyStairs.get());
		registerBlock(BOPBlocks.magicStairs.get());
		registerBlock(BOPBlocks.mangroveStairs.get());
		registerBlock(BOPBlocks.palmStairs.get());
		registerBlock(BOPBlocks.redwoodStairs.get());
		registerBlock(BOPBlocks.willowStairs.get());
		registerBlock(BOPBlocks.pineStairs.get());
		registerBlock(BOPBlocks.hellBarkStairs.get());
		registerBlock(BOPBlocks.jacarandaStairs.get());

		registerBlock(BOPBlocks.leavesColorized1.get(), ItemBlockColorizedLeaves.class);
	    registerBlock(BOPBlocks.leavesColorized2.get(), ItemBlockColorizedLeaves.class);
	    //registerBlock(Blocks.leavesColorized3.get(), ItemBlockColorizedLeaves.class);
	    //registerBlock(Blocks.leavesColorized4.get(), ItemBlockColorizedLeaves.class);
	}*/

	/*private static void addGrassPlants()
	{
		MinecraftForge.addGrassPlant(BOPBlocks.flowers.get(), 0, 10);
		MinecraftForge.addGrassPlant(BOPBlocks.flowers.get(), 4, 10);
		MinecraftForge.addGrassPlant(BOPBlocks.flowers.get(), 9, 10);
		MinecraftForge.addGrassPlant(BOPBlocks.flowers.get(), 15, 25);
		MinecraftForge.addGrassPlant(BOPBlocks.foliage.get(), 1, 50);
		MinecraftForge.addGrassPlant(BOPBlocks.foliage.get(), 2, 100);
		MinecraftForge.addGrassPlant(BOPBlocks.foliage.get(), 4, 15);
		MinecraftForge.addGrassPlant(BOPBlocks.foliage.get(), 5, 15);
		MinecraftForge.addGrassPlant(BOPBlocks.foliage.get(), 9, 5);
		MinecraftForge.addGrassPlant(BOPBlocks.foliage.get(), 10, 75);
		MinecraftForge.addGrassPlant(BOPBlocks.foliage.get(), 11, 75);
	}*/

	public static void registerBlock(Block block)
	{
		//TODO: 								getUnlocalizedName()
	    GameRegistry.registerBlock(block, block.func_149739_a().replace("tile.", ""));
	}
}
