package biomesoplenty.configuration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import biomesoplenty.api.Blocks;
import biomesoplenty.blocks.BlockAltar;
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
import biomesoplenty.blocks.BlockBOPGeneric.BlockType;
import biomesoplenty.blocks.BlockBOPGlass;
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
import biomesoplenty.configuration.configfile.BOPConfigurationIDs;
import biomesoplenty.itemblocks.ItemBlockAltar;
import biomesoplenty.itemblocks.ItemBlockAppleLeaves;
import biomesoplenty.itemblocks.ItemBlockBamboo;
import biomesoplenty.itemblocks.ItemBlockBones;
import biomesoplenty.itemblocks.ItemBlockColorizedLeaves;
import biomesoplenty.itemblocks.ItemBlockColorizedSapling;
import biomesoplenty.itemblocks.ItemBlockCoral;
import biomesoplenty.itemblocks.ItemBlockFlower;
import biomesoplenty.itemblocks.ItemBlockFlower2;
import biomesoplenty.itemblocks.ItemBlockFoliage;
import biomesoplenty.itemblocks.ItemBlockGlass;
import biomesoplenty.itemblocks.ItemBlockGrass;
import biomesoplenty.itemblocks.ItemBlockGrave;
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
		Blocks.shearBlockIds.put(Blocks.leavesFruit2.get().blockID, 15.0F);

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
		Blocks.mud = Optional.of((new BlockMud(BOPConfigurationIDs.mudID)).setHardness(0.6F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("bop.mud"));
		Blocks.driedDirt = Optional.of(new BlockBOPGeneric(BOPConfigurationIDs.driedDirtID, Material.rock, BlockType.DRIED_DIRT).setUnlocalizedName("bop.generic"));
		Blocks.redRock = Optional.of((new BlockBOPRedRock(BOPConfigurationIDs.redRockID)).setUnlocalizedName("bop.redRocks"));
		Blocks.ash = Optional.of((new BlockAsh(BOPConfigurationIDs.ashID)).setHardness(0.4F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("bop.ash"));
		Blocks.plants = Optional.of((new BlockBOPPlant(BOPConfigurationIDs.plantsID)).setUnlocalizedName("bop.plants"));
		Blocks.flowers = Optional.of((new BlockBOPFlower(BOPConfigurationIDs.flowersID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.flowers"));
		Blocks.flowers2 = Optional.of((new BlockBOPFlower2(BOPConfigurationIDs.flowers2ID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.flowers2"));
		Blocks.mushrooms = Optional.of((new BlockBOPMushroom(BOPConfigurationIDs.mushroomsID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.mushrooms"));
		Blocks.coral = Optional.of((new BlockBOPCoral(BOPConfigurationIDs.coralID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.coral"));
		Blocks.willow = Optional.of((new BlockWillow(BOPConfigurationIDs.willowID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.willow"));
		Blocks.ivy = Optional.of((new BlockIvy(BOPConfigurationIDs.ivyID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bop.ivy"));
		Blocks.leaves1 = Optional.of((new BlockBOPLeaves(BOPConfigurationIDs.leaves1ID, LeafCategory.CAT1)).setUnlocalizedName("bop.leaves1"));
		Blocks.leaves2 = Optional.of((new BlockBOPLeaves(BOPConfigurationIDs.leaves2ID, LeafCategory.CAT2)).setUnlocalizedName("bop.leaves2"));
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

		Blocks.bones = Optional.of((new BlockBones(BOPConfigurationIDs.bonesID)).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.bones"));
		
		Blocks.glass = Optional.of((new BlockBOPGlass(BOPConfigurationIDs.glassID)).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("bop.glass"));
		
		Blocks.altar = Optional.of((new BlockAltar(BOPConfigurationIDs.altarID)).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bop.altar"));
		
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
		Blocks.leavesColorized = Optional.of((new BlockBOPColorizedLeaves(BOPConfigurationIDs.colourizedLeavesID)).setUnlocalizedName("bop.leavesColorized"));
	}

	private static void registerBlocks()
	{
		// Add block registration

		GameRegistry.registerBlock(Blocks.mud.get(), ItemBlockMud.class, "bop.mud");

        OreDictionary.registerOre("mud", new ItemStack(Blocks.mud.get()));
		GameRegistry.registerBlock(Blocks.driedDirt.get(), "bop.driedDirt");
		OreDictionary.registerOre("dirtDried", new ItemStack(Blocks.driedDirt.get()));
		GameRegistry.registerBlock(Blocks.redRock.get(), ItemBlockRedRock.class, "bop.redRock");
		OreDictionary.registerOre("stoneRed", new ItemStack(Blocks.redRock.get()));
		GameRegistry.registerBlock(Blocks.ash.get(), "bop.ash");
		OreDictionary.registerOre("ash", new ItemStack(Blocks.ash.get()));
		GameRegistry.registerBlock(Blocks.plants.get(), ItemBlockPlant.class, "bop.plants");
		OreDictionary.registerOre("bluemilk", new ItemStack(Blocks.mushrooms.get()));
		
		GameRegistry.registerBlock(Blocks.flowers.get(), ItemBlockFlower.class, "bop.flowers");
		GameRegistry.registerBlock(Blocks.flowers2.get(), ItemBlockFlower2.class, "bop.flowers2");
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
		GameRegistry.registerBlock(Blocks.leavesFruit2.get(), ItemBlockPersimmonLeaves.class, "bop.leavesFruit2");
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

		OreDictionary.registerOre("mushroomBluemilk", new ItemStack(Blocks.mushrooms.get(),2));

		OreDictionary.registerOre("mushroomToadstool", new ItemStack(Blocks.mushrooms.get(),0));

		OreDictionary.registerOre("logWillow", new ItemStack(Blocks.logs3.get(),1));

		OreDictionary.registerOre("mushroomGlowshroom", new ItemStack(Blocks.mushrooms.get(),3));

		OreDictionary.registerOre("bluemilk", new ItemStack(Blocks.mushrooms.get(),2));

		OreDictionary.registerOre("daffodil", new ItemStack(Blocks.flowers.get(),6));

		OreDictionary.registerOre("mushroomDeathbloom", new ItemStack(Blocks.flowers.get(),2));

		OreDictionary.registerOre("bluemilk", new ItemStack(Blocks.mushrooms.get(),2));

		OreDictionary.registerOre("sprout", new ItemStack(Blocks.foliage.get(),5));

		OreDictionary.registerOre("crystal", new ItemStack(Blocks.crystal.get(),0));

		OreDictionary.registerOre("logMagic", new ItemStack(Blocks.logs2.get(),1));

		OreDictionary.registerOre("leavesMagic", new ItemStack(Blocks.leaves1.get(),2));

		OreDictionary.registerOre("grassSmoldering", new ItemStack(Blocks.holyGrass.get(),1));

		OreDictionary.registerOre("flowerRainbow", new ItemStack(Blocks.flowers.get(),11));
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
		MinecraftForge.addGrassPlant(Blocks.foliage.get(), 9, 5);
		MinecraftForge.addGrassPlant(Blocks.foliage.get(), 10, 75);
		MinecraftForge.addGrassPlant(Blocks.foliage.get(), 11, 75);
	}
}
