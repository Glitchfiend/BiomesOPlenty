package biomesoplenty.configuration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.api.Blocks;
import biomesoplenty.blocks.BlockAsh;
import biomesoplenty.blocks.BlockBOPAmethyst;
import biomesoplenty.blocks.BlockBOPAppleLeaves;
import biomesoplenty.blocks.BlockBOPColorizedLeaves;
import biomesoplenty.blocks.BlockBOPColorizedSapling;
import biomesoplenty.blocks.BlockBOPCoral;
import biomesoplenty.blocks.BlockBOPFlower;
import biomesoplenty.blocks.BlockBOPFlower2;
import biomesoplenty.blocks.BlockBOPFoliage;
import biomesoplenty.blocks.BlockBOPGeneric;
import biomesoplenty.blocks.BlockBOPColorizedLeaves.ColourizedLeafCategory;
import biomesoplenty.blocks.BlockBOPGeneric.BlockType;
import biomesoplenty.blocks.BlockBOPGrass;
import biomesoplenty.blocks.BlockBOPLeaves;
import biomesoplenty.blocks.BlockBOPLeaves.LeafCategory;
import biomesoplenty.blocks.BlockBOPLog;
import biomesoplenty.blocks.BlockBOPLog.LogCategory;
import biomesoplenty.blocks.BlockBOPMushroom;
import biomesoplenty.blocks.BlockBOPPersimmonLeaves;
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
import biomesoplenty.blocks.BlockFlesh;
import biomesoplenty.blocks.BlockGrave;
import biomesoplenty.blocks.BlockHive;
import biomesoplenty.blocks.BlockHoney;
import biomesoplenty.blocks.BlockIvy;
import biomesoplenty.blocks.BlockLongGrass;
import biomesoplenty.blocks.BlockMoss;
import biomesoplenty.blocks.BlockMud;
import biomesoplenty.blocks.BlockOriginGrass;
import biomesoplenty.blocks.BlockPromisedPortal;
import biomesoplenty.blocks.BlockPuddle;
import biomesoplenty.blocks.BlockStoneFormations;
import biomesoplenty.blocks.BlockTreeMoss;
import biomesoplenty.blocks.BlockWillow;
import biomesoplenty.configuration.configfile.BOPConfigurationIDs;
import biomesoplenty.itemblocks.ItemBlockAppleLeaves;
import biomesoplenty.itemblocks.ItemBlockBamboo;
import biomesoplenty.itemblocks.ItemBlockBones;
import biomesoplenty.itemblocks.ItemBlockColorizedLeaves;
import biomesoplenty.itemblocks.ItemBlockColorizedSapling;
import biomesoplenty.itemblocks.ItemBlockCoral;
import biomesoplenty.itemblocks.ItemBlockFlower;
import biomesoplenty.itemblocks.ItemBlockFlower2;
import biomesoplenty.itemblocks.ItemBlockFoliage;
import biomesoplenty.itemblocks.ItemBlockGrass;
import biomesoplenty.itemblocks.ItemBlockGrave;
import biomesoplenty.itemblocks.ItemBlockHive;
import biomesoplenty.itemblocks.ItemBlockLeaves;
import biomesoplenty.itemblocks.ItemBlockLog;
import biomesoplenty.itemblocks.ItemBlockMoss;
import biomesoplenty.itemblocks.ItemBlockMud;
import biomesoplenty.itemblocks.ItemBlockMushroom;
import biomesoplenty.itemblocks.ItemBlockPersimmonLeaves;
import biomesoplenty.itemblocks.ItemBlockPetals;
import biomesoplenty.itemblocks.ItemBlockPlank;
import biomesoplenty.itemblocks.ItemBlockPlant;
import biomesoplenty.itemblocks.ItemBlockRedRock;
import biomesoplenty.itemblocks.ItemBlockSapling;
import biomesoplenty.itemblocks.ItemBlockSkystone;
import biomesoplenty.itemblocks.ItemBlockSlab;
import biomesoplenty.itemblocks.ItemBlockStoneFormations;
import biomesoplenty.items.ItemBOPAmethyst;
import biomesoplenty.items.ItemBOPIvy;
import biomesoplenty.items.ItemBOPWillow;

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
		Blocks.shearBlockIds.put(Blocks.leavesColorized1.get().blockID, 15.0F);
		Blocks.shearBlockIds.put(Blocks.leavesColorized2.get().blockID, 15.0F);
		Blocks.shearBlockIds.put(Blocks.leavesFruit.get().blockID, 15.0F);
		Blocks.shearBlockIds.put(Blocks.leavesFruit2.get().blockID, 15.0F);

		MinecraftForge.setBlockHarvestLevel(Blocks.holyGrass.get(), 1, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.mud.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.ash.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.originGrass.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.longGrass.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.hardSand.get(), "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(Blocks.holyGrass.get(), 0, "pickaxe", 0);

		MinecraftForge.setBlockHarvestLevel(Blocks.driedDirt.get(), "pickaxe", 0);
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
		Blocks.mud = Optional.of((new BlockMud(BOPConfigurationIDs.mudID)).setHardness(0.6F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("bop.mud"));
		Blocks.driedDirt = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.driedDirtID, Material.rock, BlockType.DRIED_DIRT).setUnlocalizedName("bop.generic"));
		Blocks.redRock = Optional.of((new BlockBOPRedRock(BOPConfigurationIDs.redRockID)).setUnlocalizedName("bop.redRocks"));
		Blocks.ash = Optional.of((new BlockAsh(BOPConfigurationIDs.ashID)).setHardness(0.4F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("bop.ash"));
		Blocks.flesh = Optional.of((new BlockFlesh(BOPConfigurationIDs.fleshID)).setHardness(0.4F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("bop.flesh"));
		Blocks.plants = Optional.of((new BlockBOPPlant(BOPConfigurationIDs.plantsID)).setUnlocalizedName("bop.plants"));
		Blocks.flowers = Optional.of((new BlockBOPFlower(BOPConfigurationIDs.flowersID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.flowers"));
		Blocks.flowers2 = Optional.of((new BlockBOPFlower2(BOPConfigurationIDs.flowers2ID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.flowers2"));
		Blocks.stoneFormations = Optional.of((new BlockStoneFormations(BOPConfigurationIDs.stoneFormationsID)).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.stoneFormations"));
		Blocks.mushrooms = Optional.of((new BlockBOPMushroom(BOPConfigurationIDs.mushroomsID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.mushrooms"));
		Blocks.coral = Optional.of((new BlockBOPCoral(BOPConfigurationIDs.coralID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.coral"));
		Blocks.willow = Optional.of((new BlockWillow(BOPConfigurationIDs.willowID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.willow"));
		Blocks.ivy = Optional.of((new BlockIvy(BOPConfigurationIDs.ivyID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.ivy"));
		Blocks.leaves1 = Optional.of((new BlockBOPLeaves(BOPConfigurationIDs.leaves1ID, LeafCategory.CAT1)).setUnlocalizedName("bop.leaves1"));
		Blocks.leaves2 = Optional.of((new BlockBOPLeaves(BOPConfigurationIDs.leaves2ID, LeafCategory.CAT2)).setUnlocalizedName("bop.leaves2"));
		Blocks.leaves3 = Optional.of((new BlockBOPLeaves(BOPConfigurationIDs.leaves3ID, LeafCategory.CAT3)).setUnlocalizedName("bop.leaves3"));
	    Blocks.leaves4 = Optional.of((new BlockBOPLeaves(BOPConfigurationIDs.leaves4ID, LeafCategory.CAT4)).setUnlocalizedName("bop.leaves4"));
		Blocks.foliage = Optional.of((new BlockBOPFoliage(BOPConfigurationIDs.foliageID)).setUnlocalizedName("bop.foliage"));
		Blocks.ashStone = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.ashStoneID, Material.rock, BlockType.ASH_STONE));
		Blocks.hardIce = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.hardIceID, Material.rock, BlockType.HARD_ICE));
		Blocks.leavesFruit = Optional.of((new BlockBOPAppleLeaves(BOPConfigurationIDs.leavesFruitID)).setUnlocalizedName("bop.leavesFruit"));
		Blocks.leavesFruit2 = Optional.of((new BlockBOPPersimmonLeaves(BOPConfigurationIDs.leavesFruit2ID)).setUnlocalizedName("bop.leavesFruit2"));
		Blocks.bamboo = Optional.of(new BlockBamboo(BOPConfigurationIDs.bambooID).setHardness(0.2F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bop.bamboo"));
		Blocks.mudBrick = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.mudBrickBlockID, Material.rock, BlockType.MUD_BRICK));
		Blocks.mudBricksStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.mudBrickStairsID, Blocks.redRock.get(), Category.MUD_BRICKS)).setHardness(1.0F).setUnlocalizedName("bop.mudBricksStairs"));
		Blocks.stoneDoubleSlab = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.stoneDoubleSlabID, true, Material.rock, SlabCategory.STONE)).setUnlocalizedName("bop.stoneDoubleSlab"));
		Blocks.stoneSingleSlab = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.stoneSingleSlabID, false, Material.rock, SlabCategory.STONE)).setUnlocalizedName("bop.stoneSingleSlab"));
		Blocks.originGrass = Optional.of((new BlockOriginGrass(BOPConfigurationIDs.originGrassID)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.originGrass"));
		Blocks.longGrass = Optional.of((new BlockLongGrass(BOPConfigurationIDs.longGrassID)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.longGrass"));
		Blocks.treeMoss = Optional.of((new BlockTreeMoss(BOPConfigurationIDs.treeMossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.treeMoss"));
		Blocks.logs1 = Optional.of((new BlockBOPLog(BOPConfigurationIDs.logs1ID,LogCategory.CAT1)).setUnlocalizedName("bop.wood1"));
		Blocks.logs2 = Optional.of((new BlockBOPLog(BOPConfigurationIDs.logs2ID,LogCategory.CAT2)).setUnlocalizedName("bop.wood2"));
		Blocks.logs3 = Optional.of((new BlockBOPLog(BOPConfigurationIDs.logs3ID,LogCategory.CAT3)).setUnlocalizedName("bop.wood3"));
		Blocks.logs4 = Optional.of((new BlockBOPLog(BOPConfigurationIDs.logs4ID,LogCategory.CAT4)).setUnlocalizedName("bop.wood4"));
		Blocks.petals = Optional.of((new BlockBOPPetals(BOPConfigurationIDs.petalsID)).setUnlocalizedName("bop.petals"));
		Blocks.saplings = Optional.of((new BlockBOPSapling(BOPConfigurationIDs.saplingsID)).setUnlocalizedName("bop.saplings"));
		Blocks.colorizedSaplings = Optional.of((new BlockBOPColorizedSapling(BOPConfigurationIDs.colourizedSaplingsID)).setUnlocalizedName("bop.colorizedSaplings"));
		Blocks.redCobbleStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.redCobbleStairsID, Blocks.redRock.get(), Category.RED_COBBLE)).setHardness(1.6F).setUnlocalizedName("bop.redCobbleStairs"));
		Blocks.redBricksStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.redBrickStairsID, Blocks.redRock.get(), Category.RED_BRICKS)).setHardness(1.1F).setUnlocalizedName("bop.redBricksStairs"));
		Blocks.hardSand = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.hardSandID, Material.sand, BlockType.HARD_SAND));
		Blocks.hardDirt = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.hardDirtID, Material.rock, BlockType.HARD_DIRT));
		Blocks.holyGrass = Optional.of(new BlockBOPGrass(BOPConfigurationIDs.holyGrassID).setUnlocalizedName("bop.holyGrass"));
		Blocks.holyDirt = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.holyDirtID, Material.sand, BlockType.HOLY_DIRT));
		Blocks.holyStone = Optional.of((new BlockBOPSkystone(BOPConfigurationIDs.holyStoneID)).setUnlocalizedName("bop.holyStone"));
		Blocks.holyCobbleStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.holyCobbleStairsID, Blocks.holyStone.get(), Category.HOLY_COBBLE)).setHardness(1.6F).setUnlocalizedName("bop.holyCobbleStairs"));
		Blocks.holyBricksStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.holyBrickStairsID, Blocks.holyStone.get(), Category.HOLY_BRICKS)).setHardness(1.1F).setUnlocalizedName("bop.holyBricksStairs"));
		Blocks.crystal = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.crystalID, Material.glass, BlockType.CRYSTAL));
		Blocks.promisedPortal = Optional.of(new BlockPromisedPortal(BOPConfigurationIDs.promisedLandPortalID).setUnlocalizedName("bop.promisedPortal").setBlockUnbreakable().setResistance(6000000.0F).setLightValue(1.0F));

		Blocks.amethystOre = Optional.of(new BlockBOPAmethyst(BOPConfigurationIDs.amethystOreID, Material.rock).setUnlocalizedName("bop.amethystOre"));

		Blocks.moss = Optional.of((new BlockMoss(BOPConfigurationIDs.mossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.moss"));
		
		Blocks.cragRock = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.cragRockID, Material.rock, BlockType.CRAG_ROCK));
		
		Blocks.cloud = Optional.of((new BlockCloud(BOPConfigurationIDs.cloudID)).setHardness(0.1F).setLightOpacity(3).setStepSound(Block.soundClothFootstep).setUnlocalizedName("bop.cloud"));
		
		Blocks.hive = Optional.of((new BlockHive(BOPConfigurationIDs.hiveID)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.hive"));
		Blocks.honeyBlock = Optional.of((new BlockHoney(BOPConfigurationIDs.honeyBlockID)).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.honeyBlock"));

		Blocks.bones = Optional.of((new BlockBones(BOPConfigurationIDs.bonesID)).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.bones"));
		
		Blocks.puddle = Optional.of((new BlockPuddle(BOPConfigurationIDs.puddleID)).setHardness(0.6F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("bop.puddle"));
		Blocks.grave = Optional.of((new BlockGrave(BOPConfigurationIDs.graveID)).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.grave"));

		Blocks.planks = Optional.of((new BlockBOPPlank(BOPConfigurationIDs.planksID)).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bop.planks"));

		Blocks.woodenDoubleSlab1 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.woodenDoubleSlab1ID, true, Material.wood, SlabCategory.WOOD1)).setUnlocalizedName("bop.woodenDoubleSlab1"));
		Blocks.woodenSingleSlab1 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.woodenSingleSlab1ID, false, Material.wood, SlabCategory.WOOD1)).setUnlocalizedName("bop.woodenSingleSlab1"));
		Blocks.woodenDoubleSlab2 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.woodenDoubleSlab2ID, true, Material.wood, SlabCategory.WOOD2)).setUnlocalizedName("bop.woodenDoubleSlab2"));
		Blocks.woodenSingleSlab2 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(BOPConfigurationIDs.woodenSingleSlab2ID, false, Material.wood, SlabCategory.WOOD2)).setUnlocalizedName("bop.woodenSingleSlab2"));

		Blocks.acaciaStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.acaciaStairsID, Blocks.planks.get(), Category.ACACIA)).setUnlocalizedName("bop.acaciaStairs"));
		Blocks.cherryStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.cherryStairsID, Blocks.planks.get(), Category.CHERRY)).setUnlocalizedName("bop.cherryStairs"));
		Blocks.darkStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.darkStairsID, Blocks.planks.get(), Category.DARK)).setUnlocalizedName("bop.darkStairs"));
		Blocks.firStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.firStairsID, Blocks.planks.get(), Category.FIR)).setUnlocalizedName("bop.firStairs"));
		Blocks.holyStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.holyStairsID, Blocks.planks.get(), Category.HOLY)).setUnlocalizedName("bop.holyStairs"));
		Blocks.magicStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.magicStairsID, Blocks.planks.get(), Category.MAGIC)).setUnlocalizedName("bop.magicStairs"));
		Blocks.mangroveStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.mangroveStairsID, Blocks.planks.get(), Category.MANGROVE)).setUnlocalizedName("bop.mangroveStairs"));
		Blocks.palmStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.palmStairsID, Blocks.planks.get(), Category.PALM)).setUnlocalizedName("bop.palmStairs"));
		Blocks.redwoodStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.redwoodStairsID, Blocks.planks.get(), Category.REDWOOD)).setUnlocalizedName("bop.redwoodStairs"));
		Blocks.willowStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.willowStairsID, Blocks.planks.get(), Category.WILLOW)).setUnlocalizedName("bop.willowStairs"));
		Blocks.pineStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.pineStairsID, Blocks.planks.get(), Category.PINE)).setUnlocalizedName("bop.pineStairs"));
		Blocks.hellBarkStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.hellBarkStairsID, Blocks.planks.get(), Category.HELL_BARK)).setUnlocalizedName("bop.hellBarkStairs"));
		Blocks.jacarandaStairs = Optional.of((new BlockBOPStairs(BOPConfigurationIDs.jacarandaStairsID, Blocks.planks.get(), Category.JACARANDA)).setUnlocalizedName("bop.jacarandaStairs"));
		
		Blocks.leavesColorized1 = Optional.of((new BlockBOPColorizedLeaves(BOPConfigurationIDs.colourizedLeavesID, ColourizedLeafCategory.CAT1)).setUnlocalizedName("bop.leavesColorized1"));
	    Blocks.leavesColorized2 = Optional.of((new BlockBOPColorizedLeaves(BOPConfigurationIDs.colourizedLeaves2ID, ColourizedLeafCategory.CAT2)).setUnlocalizedName("bop.leavesColorized2"));
	    //Blocks.leavesColorized3 = Optional.of((new BlockBOPColorizedLeaves(BOPConfigurationIDs.colourizedLeaves3ID, ColourizedLeafCategory.CAT3)).setUnlocalizedName("bop.leavesColorized3"));
	    //Blocks.leavesColorized4 = Optional.of((new BlockBOPColorizedLeaves(BOPConfigurationIDs.colourizedLeaves4ID, ColourizedLeafCategory.CAT4)).setUnlocalizedName("bop.leavesColorized4"));
	}

	private static void registerBlocks()
	{
		// Add block registration
		registerBlock(Blocks.mud.get(), ItemBlockMud.class);
		registerBlock(Blocks.driedDirt.get());
		registerBlock(Blocks.redRock.get(), ItemBlockRedRock.class);
		registerBlock(Blocks.ash.get());
		registerBlock(Blocks.flesh.get());
		registerBlock(Blocks.plants.get(), ItemBlockPlant.class);
		registerBlock(Blocks.flowers.get(), ItemBlockFlower.class);
		registerBlock(Blocks.flowers2.get(), ItemBlockFlower2.class);
		registerBlock(Blocks.stoneFormations.get(), ItemBlockStoneFormations.class);
		registerBlock(Blocks.mushrooms.get(), ItemBlockMushroom.class);
		registerBlock(Blocks.coral.get(), ItemBlockCoral.class);
		registerBlock(Blocks.willow.get(), ItemBOPWillow.class);
		registerBlock(Blocks.ivy.get(), ItemBOPIvy.class);
		registerBlock(Blocks.leaves1.get(), ItemBlockLeaves.class);
		registerBlock(Blocks.leaves2.get(), ItemBlockLeaves.class);
	    registerBlock(Blocks.leaves3.get(), ItemBlockLeaves.class);
	    registerBlock(Blocks.leaves4.get(), ItemBlockLeaves.class);
		registerBlock(Blocks.foliage.get(), ItemBlockFoliage.class);
		registerBlock(Blocks.ashStone.get());
		registerBlock(Blocks.hardIce.get());
		registerBlock(Blocks.leavesFruit.get(), ItemBlockAppleLeaves.class);
		registerBlock(Blocks.leavesFruit2.get(), ItemBlockPersimmonLeaves.class);
		registerBlock(Blocks.bamboo.get(), ItemBlockBamboo.class);
		registerBlock(Blocks.mudBrick.get());
		registerBlock(Blocks.mudBricksStairs.get());
		registerBlock(Blocks.originGrass.get());
		registerBlock(Blocks.longGrass.get());
		registerBlock(Blocks.treeMoss.get());
		registerBlock(Blocks.logs1.get(), ItemBlockLog.class);
		registerBlock(Blocks.logs2.get(), ItemBlockLog.class);
		registerBlock(Blocks.logs3.get(), ItemBlockLog.class);
		registerBlock(Blocks.logs4.get(), ItemBlockLog.class);
		registerBlock(Blocks.petals.get(), ItemBlockPetals.class);
		registerBlock(Blocks.saplings.get(), ItemBlockSapling.class);
		registerBlock(Blocks.colorizedSaplings.get(), ItemBlockColorizedSapling.class);
		registerBlock(Blocks.redCobbleStairs.get());
		registerBlock(Blocks.redBricksStairs.get());
		registerBlock(Blocks.hardSand.get());
		registerBlock(Blocks.hardDirt.get());
		registerBlock(Blocks.crystal.get());
		registerBlock(Blocks.holyGrass.get(), ItemBlockGrass.class);
		registerBlock(Blocks.holyDirt.get());
		registerBlock(Blocks.holyStone.get(), ItemBlockSkystone.class);
		registerBlock(Blocks.holyCobbleStairs.get());
		registerBlock(Blocks.holyBricksStairs.get());
		registerBlock(Blocks.promisedPortal.get());
		registerBlock(Blocks.amethystOre.get(), ItemBOPAmethyst.class);
		registerBlock(Blocks.moss.get(), ItemBlockMoss.class);
		registerBlock(Blocks.cragRock.get());
		registerBlock(Blocks.cloud.get());
		registerBlock(Blocks.hive.get(), ItemBlockHive.class);
		registerBlock(Blocks.honeyBlock.get());

		registerBlock(Blocks.bones.get(), ItemBlockBones.class);
		registerBlock(Blocks.puddle.get());	
		registerBlock(Blocks.grave.get(), ItemBlockGrave.class);	

		ItemBlockSlab.setSlabs(Blocks.stoneSingleSlab.get(), Blocks.stoneDoubleSlab.get());
		
		registerBlock(Blocks.stoneDoubleSlab.get(), ItemBlockSlab.class);
		registerBlock(Blocks.stoneSingleSlab.get(), ItemBlockSlab.class);
		
		ItemBlockSlab.setSlabs(Blocks.woodenSingleSlab1.get(), Blocks.woodenDoubleSlab1.get());
		
		registerBlock(Blocks.woodenDoubleSlab1.get(), ItemBlockSlab.class);
		registerBlock(Blocks.woodenSingleSlab1.get(), ItemBlockSlab.class);
		
		ItemBlockSlab.setSlabs(Blocks.woodenSingleSlab2.get(), Blocks.woodenDoubleSlab2.get());
		
		registerBlock(Blocks.woodenDoubleSlab2.get(), ItemBlockSlab.class);
		registerBlock(Blocks.woodenSingleSlab2.get(), ItemBlockSlab.class);

		registerBlock(Blocks.planks.get(), ItemBlockPlank.class);

		registerBlock(Blocks.acaciaStairs.get());
		registerBlock(Blocks.cherryStairs.get());
		registerBlock(Blocks.darkStairs.get());
		registerBlock(Blocks.firStairs.get());
		registerBlock(Blocks.holyStairs.get());
		registerBlock(Blocks.magicStairs.get());
		registerBlock(Blocks.mangroveStairs.get());
		registerBlock(Blocks.palmStairs.get());
		registerBlock(Blocks.redwoodStairs.get());
		registerBlock(Blocks.willowStairs.get());
		registerBlock(Blocks.pineStairs.get());
		registerBlock(Blocks.hellBarkStairs.get());
		registerBlock(Blocks.jacarandaStairs.get());

		registerBlock(Blocks.leavesColorized1.get(), ItemBlockColorizedLeaves.class);
	    registerBlock(Blocks.leavesColorized2.get(), ItemBlockColorizedLeaves.class);
	    //registerBlock(Blocks.leavesColorized3.get(), ItemBlockColorizedLeaves.class);
	    //registerBlock(Blocks.leavesColorized4.get(), ItemBlockColorizedLeaves.class);
	}
	
	private static void registerTileEntities()
	{
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
		MinecraftForge.addGrassPlant(Blocks.foliage.get(), 9, 5);
		MinecraftForge.addGrassPlant(Blocks.foliage.get(), 10, 75);
		MinecraftForge.addGrassPlant(Blocks.foliage.get(), 11, 75);
	}

	private static void registerBlock(Block block)
	{
	    GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
	}

	private static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass)
	{
	    GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}
}
