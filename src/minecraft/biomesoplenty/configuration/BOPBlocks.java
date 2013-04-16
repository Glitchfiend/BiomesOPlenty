package biomesoplenty.configuration;

import biomesoplenty.api.Blocks;
import biomesoplenty.blocks.*;
import biomesoplenty.blocks.BlockBOPLog.LogCategory;
import biomesoplenty.blocks.BlockBOPSlab.SlabCategory;
import biomesoplenty.blocks.BlockBOPStairs.WoodCategory;
import biomesoplenty.items.ItemBOPColorizedLeaves;
import biomesoplenty.items.ItemBOPColorizedSapling;
import biomesoplenty.items.ItemBOPFlower;
import biomesoplenty.items.ItemBOPFoliage;
import biomesoplenty.items.ItemBOPLeaves;
import biomesoplenty.items.ItemBOPLog;
import biomesoplenty.items.ItemBOPPetals;
import biomesoplenty.items.ItemBOPPlank;
import biomesoplenty.items.ItemBOPPlant;
import biomesoplenty.items.ItemBOPSlab;
import biomesoplenty.items.ItemBOPSapling;

import com.google.common.base.Optional;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BOPBlocks {

	// Block declaration
	public static Block mud;                               // Worldgen
	public static Block driedDirt;                         // Worldgen
	public static Block redRock;                           // Worldgen
	public static Block ash;                               // Worldgen
	public static Block deadGrass;                         // DONE
	public static Block desertGrass;                       // DONE
	public static Block whiteFlower;                       // DONE
	public static Block blueFlower;                        // DONE
	public static Block purpleFlower;                      // DONE
	public static Block orangeFlower;                      // DONE
	public static Block tinyFlower;                        // DONE
	public static Block glowFlower;                        // DONE
	public static Block cattail;                           // DONE
	public static Block willow;                            // Leave
	public static Block autumnLeaves;                      // DONE
	public static Block thorn;                             // DONE
	public static Block toadstool;                         // DONE
	public static BlockHighGrassBottom highGrassBottom;    // DONE
	public static BlockHighGrassTop highGrassTop;          // DONE
	public static Block ashStone;                          // Worldgen
	public static Block hardIce;                           // DONE
	public static Block redLeaves;                         // DONE
	public static Block orangeLeaves;                      // DONE
	public static Block pinkLeaves;                        // DONE
	public static Block blueLeaves;                        // DONE
	public static Block whiteLeaves;                       // DONE
	public static Block deadLeaves;                        // DONE
	public static BlockShortGrass shortGrass;              // DONE
	public static Block appleLeaves;
	public static BlockSprout sprout;                      // DONE
	public static BlockBush bush;                          // DONE
	public static Block bamboo;
	public static Block bambooLeaves;                      // DONE
	public static Block mudBrickBlock;
	public static BlockHalfSlab mudBrickDoubleSlab;        // DONE
	public static BlockHalfSlab mudBrickSingleSlab;        // DONE
	public static Block mudBrickStairs;
	public static Block originGrass;                       // Worldgen
	public static Block originLeaves;                      // DONE
	public static Block pinkFlower;                        // DONE
	public static Block treeMoss;                          // Leave
	public static Block deadWood;                          // DONE
	public static Block appleLeavesFruitless;
	public static Block barley;                            // DONE
	public static Block giantFlowerStem;
	public static Block giantFlowerRed;                    // DONE
	public static Block giantFlowerYellow;                 // DONE
	public static Block tinyCactus;                        // DONE
	public static Block firSapling;                        // DONE
	public static Block redwoodSapling;                    // DONE
	public static Block palmSapling;                       // DONE
	public static Block redSapling;                        // DONE
	public static Block orangeSapling;                     // DONE
	public static Block yellowSapling;                     // DONE
	public static Block brownSapling;                      // DONE
	public static Block willowSapling;                     // DONE
	public static Block appleSapling;                      
	public static Block originSapling;                     // DONE
	public static Block pinkSapling;                       // DONE
	public static Block whiteSapling;                      // DONE
	public static Block darkSapling;                       // DONE
	public static Block magicSapling;                      // DONE
	public static Block deathbloom;                        // DONE
	public static Block redRockCobble;
	public static BlockHalfSlab redRockCobbleDoubleSlab;   // DONE
	public static BlockHalfSlab redRockCobbleSingleSlab;   // DONE
	public static Block redRockCobbleStairs;
	public static Block redRockBrick;
	public static BlockHalfSlab redRockBrickDoubleSlab;    // DONE
	public static BlockHalfSlab redRockBrickSingleSlab;    // DONE
	public static Block redRockBrickStairs;
	public static Block hydrangea;                         // DONE
	public static Block violet;                            // DONE
	public static BlockMediumGrass mediumGrass;            // DONE
	public static Block duneGrass;                         // DONE
	public static Block desertSprouts;                     // DONE
	public static Block mangroveSapling;                   // DONE
	public static Block hardSand;                          // Worldgen
	public static Block acaciaSapling;                     // DONE
	public static Block hardDirt;                          // Worldgen
	public static Block holyGrass;                         // Worldgen
	public static Block holyStone;                         // Worldgen
	public static Block holyTallGrass;                     // DONE
	public static Block promisedPortal;                    // Leave
	public static Block holySapling;                       // DONE
	public static Block amethystOre;
	public static Block amethystBlock;
	public static Block bambooThatching;
	public static Block moss;                              // Leave
	public static Block algae;                             // DONE
	public static Block smolderingGrass;
	public static Block cragRock;                          // Worldgen
	public static Block quicksand;
	public static Block bambooSapling;                     // DONE

	//Redwood
	public static Block redwoodPlank;                      // DONE
	public static Block redwoodWood;                       // DONE
	public static Block redwoodLeaves;                     // DONE
	public static BlockHalfSlab redwoodDoubleSlab;         // DONE
	public static BlockHalfSlab redwoodSingleSlab;         // DONE
	public static Block redwoodStairs;                     // DONE

	//Willow
	public static Block willowPlank;                       // DONE
	public static Block willowWood;                        // DONE
	public static Block willowLeaves;                      // DONE
	public static BlockHalfSlab willowDoubleSlab;          // DONE
	public static BlockHalfSlab willowSingleSlab;          // DONE
	public static Block willowStairs;                      // DONE

	//Fir
	public static Block firPlank;                          // DONE
	public static Block firWood;                           // DONE
	public static Block firLeaves;                         // DONE
	public static BlockHalfSlab firDoubleSlab;             // DONE
	public static BlockHalfSlab firSingleSlab;             // DONE
	public static Block firStairs;                         // DONE

	//Acacia
	public static Block acaciaPlank;                       // DONE
	public static Block acaciaWood;                        // DONE
	public static Block acaciaLeaves;                      // DONE
	public static BlockHalfSlab acaciaDoubleSlab;          // DONE
	public static BlockHalfSlab acaciaSingleSlab;          // DONE
	public static Block acaciaStairs;                      // DONE

	//Cherry
	public static Block cherryPlank;                       // DONE
	public static Block cherryWood;                        // DONE
	public static BlockHalfSlab cherryDoubleSlab;          // DONE
	public static BlockHalfSlab cherrySingleSlab;          // DONE
	public static Block cherryStairs;                      // DONE

	//Dark
	public static Block darkPlank;                         // DONE
	public static Block darkWood;                          // DONE
	public static Block darkLeaves;                        // DONE
	public static BlockHalfSlab darkDoubleSlab;            // DONE
	public static BlockHalfSlab darkSingleSlab;            // DONE
	public static Block darkStairs;                        // DONE

	//Magic
	public static Block magicPlank;                        // DONE
	public static Block magicWood;                         // DONE
	public static BlockHalfSlab magicDoubleSlab;           // DONE
	public static BlockHalfSlab magicSingleSlab;           // DONE
	public static Block magicStairs;                       // DONE

	//Palm
	public static Block palmPlank;                         // DONE
	public static Block palmWood;                          // DONE
	public static Block palmLeaves;                        // DONE
	public static BlockHalfSlab palmDoubleSlab;            // DONE
	public static BlockHalfSlab palmSingleSlab;            // DONE
	public static Block palmStairs;                        // DONE

	//Mangrove
	public static Block mangrovePlank;                     // DONE
	public static Block mangroveWood;                      // DONE
	public static Block mangroveLeaves;                    // DONE
	public static BlockHalfSlab mangroveDoubleSlab;        // DONE
	public static BlockHalfSlab mangroveSingleSlab;        // DONE
	public static Block mangroveStairs;                    // DONE

	//Holy
	public static Block holyPlank;                         // DONE
	public static Block holyWood;                          // DONE
	public static Block holyLeaves;                        // DONE
	public static BlockHalfSlab holyDoubleSlab;            // DONE
	public static BlockHalfSlab holySingleSlab;            // DONE
	public static Block holyStairs;                        // DONE
	
	public static void init()
	{
		// Block declaration
		mud = (new BlockMud(BOPConfiguration.mudID)).setHardness(0.6F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("mud");
		driedDirt = (new BlockDriedDirt(BOPConfiguration.driedDirtID)).setHardness(0.1F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("driedDirt");
		redRock = (new BlockRedRock(BOPConfiguration.redRockID)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRock");
		ash = (new BlockAsh(BOPConfiguration.ashID)).setHardness(0.4F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("ash");
		deadGrass = (new BlockDeadGrass(BOPConfiguration.deadGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("deadGrass");
		desertGrass = (new BlockDesertGrass(BOPConfiguration.desertGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("desertGrass");
		whiteFlower = (new BlockWhiteFlower(BOPConfiguration.whiteFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("whiteFlower");
		blueFlower = (new BlockBlueFlower(BOPConfiguration.blueFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("blueFlower");
		purpleFlower = (new BlockPurpleFlower(BOPConfiguration.purpleFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("purpleFlower");
		orangeFlower = (new BlockOrangeFlower(BOPConfiguration.orangeFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("orangeFlower");
		tinyFlower = (new BlockTinyFlower(BOPConfiguration.tinyFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("tinyFlower");
		glowFlower = (new BlockGlowFlower(BOPConfiguration.glowFlowerID)).setHardness(0.0F).setLightValue(0.65F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("glowFlower");
		cattail = (new BlockCattail(BOPConfiguration.cattailID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("cattail");
		willow = (new BlockWillow(BOPConfiguration.willowID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("willow");
		autumnLeaves = (BlockAutumnLeaves)(new BlockAutumnLeaves(BOPConfiguration.autumnLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("autumnLeaves");
		thorn = (new BlockThorn(BOPConfiguration.thornID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("thorn");
		toadstool = (new BlockToadstool(BOPConfiguration.toadstoolID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("toadstool");
		highGrassBottom = (BlockHighGrassBottom)(new BlockHighGrassBottom(BOPConfiguration.highGrassBottomID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("highGrassBottom");
		highGrassTop = (BlockHighGrassTop)(new BlockHighGrassTop(BOPConfiguration.highGrassTopID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("highGrassTop");
		ashStone = (new BlockAshStone(BOPConfiguration.ashStoneID)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ashStone");
		hardIce = (new BlockHardIce(BOPConfiguration.hardIceID)).setHardness(0.75F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("hardIce");
		redLeaves = (BlockRedLeaves)(new BlockRedLeaves(BOPConfiguration.redLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redLeaves");
		orangeLeaves = (BlockOrangeLeaves)(new BlockOrangeLeaves(BOPConfiguration.orangeLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("orangeLeaves");
		pinkLeaves = (BlockPinkLeaves)(new BlockPinkLeaves(BOPConfiguration.pinkLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("pinkLeaves");
		blueLeaves = (BlockBlueLeaves)(new BlockBlueLeaves(BOPConfiguration.blueLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("blueLeaves");
		whiteLeaves = (BlockWhiteLeaves)(new BlockWhiteLeaves(BOPConfiguration.whiteLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("whiteLeaves");
		deadLeaves = (BlockDeadLeaves)(new BlockDeadLeaves(BOPConfiguration.deadLeavesID)).setHardness(0.1F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("deadLeaves");
		shortGrass = (BlockShortGrass)(new BlockShortGrass(BOPConfiguration.shortGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("shortGrass");
		appleLeaves = (BlockAppleLeaves)(new BlockAppleLeaves(BOPConfiguration.appleLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("appleLeaves");
		sprout = (BlockSprout)(new BlockSprout(BOPConfiguration.sproutID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("sprout");
		bush = (BlockBush)(new BlockBush(BOPConfiguration.bushID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bush");
		bamboo = new BlockBamboo(BOPConfiguration.bambooID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bamboo");
		bambooLeaves = (BlockBambooLeaves)(new BlockBambooLeaves(BOPConfiguration.bambooLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bambooLeaves");
		mudBrickBlock = (new BlockMudBrick(BOPConfiguration.mudBrickBlockID)).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mudBrickBlock");
		originGrass = (BlockOriginGrass)(new BlockOriginGrass(BOPConfiguration.originGrassID)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("originGrass");
		originLeaves = (BlockOriginLeaves)(new BlockOriginLeaves(BOPConfiguration.originLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("originLeaves");
		pinkFlower = (new BlockPinkFlower(BOPConfiguration.pinkFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("pinkFlower");
		treeMoss = (new BlockTreeMoss(BOPConfiguration.treeMossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("treeMoss");
		deadWood = (new BlockDeadLog(BOPConfiguration.deadWoodID)).setHardness(0.8F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("deadWood");
		appleLeavesFruitless = (BlockAppleLeavesFruitless)(new BlockAppleLeavesFruitless(BOPConfiguration.appleLeavesFruitlessID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("appleLeavesFruitless");
		barley = (new BlockBarley(BOPConfiguration.barleyID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("barley");
		giantFlowerStem = (new BlockGiantFlowerStem(BOPConfiguration.giantFlowerStemID)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("giantFlowerStem");
		giantFlowerRed = (BlockGiantFlowerRed)(new BlockGiantFlowerRed(BOPConfiguration.giantFlowerRedID)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("giantFlowerRed");
		giantFlowerYellow = (BlockGiantFlowerYellow)(new BlockGiantFlowerYellow(BOPConfiguration.giantFlowerYellowID)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("giantFlowerYellow");
		tinyCactus = (new BlockTinyCactus(BOPConfiguration.tinyCactusID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("tinyCactus");
		firSapling = (new BlockFirSapling(BOPConfiguration.firSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("firSapling");
		redwoodSapling = (new BlockRedwoodSapling(BOPConfiguration.redwoodSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redwoodSapling");
		palmSapling = (new BlockPalmSapling(BOPConfiguration.palmSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("palmSapling");
		redSapling = (new BlockRedSapling(BOPConfiguration.redSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redSapling");
		orangeSapling = (new BlockOrangeSapling(BOPConfiguration.orangeSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("orangeSapling");
		yellowSapling = (new BlockYellowSapling(BOPConfiguration.yellowSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("yellowSapling");
		brownSapling = (new BlockBrownSapling(BOPConfiguration.brownSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("brownSapling");
		willowSapling = (new BlockWillowSapling(BOPConfiguration.willowSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("willowSapling");
		appleSapling = (new BlockAppleSapling(BOPConfiguration.appleSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("appleSapling");
		originSapling = (new BlockOriginSapling(BOPConfiguration.originSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("originSapling");
		pinkSapling = (new BlockPinkSapling(BOPConfiguration.pinkSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("pinkSapling");
		whiteSapling = (new BlockWhiteSapling(BOPConfiguration.whiteSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("whiteSapling");
		darkSapling = (new BlockDarkSapling(BOPConfiguration.darkSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("darkSapling");
		magicSapling = (new BlockMagicSapling(BOPConfiguration.magicSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("magicSapling");
		deathbloom = (new BlockDeathbloom(BOPConfiguration.deathbloomID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("deathbloom");
		redRockCobble = (new BlockRedRockCobble(BOPConfiguration.redRockCobbleID)).setHardness(1.6F).setResistance(7.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockCobble");
		redRockBrick = (new BlockRedRockBrick(BOPConfiguration.redRockBrickID)).setHardness(1.1F).setResistance(7.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockBrick");
		hydrangea = (new BlockHydrangea(BOPConfiguration.hydrangeaID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hydrangea");
		violet = (new BlockViolet(BOPConfiguration.violetID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("violet");
		mediumGrass = (BlockMediumGrass)(new BlockMediumGrass(BOPConfiguration.mediumGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("mediumGrass");
		duneGrass = (new BlockDuneGrass(BOPConfiguration.duneGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("duneGrass");
		desertSprouts = (new BlockDesertSprouts(BOPConfiguration.desertSproutsID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("desertSprouts");
		redRockCobbleDoubleSlab = (BlockHalfSlab)(new BlockRedRockCobbleSlab(BOPConfiguration.redRockCobbleDoubleSlabID, true)).setHardness(1.6F).setResistance(7.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockCobbleSlab");
		redRockCobbleSingleSlab = (BlockHalfSlab)(new BlockRedRockCobbleSlab(BOPConfiguration.redRockCobbleSingleSlabID, false)).setHardness(1.6F).setResistance(7.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockCobbleSlab");
		redRockCobbleStairs = (new BlockRedRockCobbleStairs(BOPConfiguration.redRockCobbleStairsID, redRockCobble)).setUnlocalizedName("redRockCobbleStairs");
		redRockBrickDoubleSlab = (BlockHalfSlab)(new BlockRedRockBrickSlab(BOPConfiguration.redRockBrickDoubleSlabID, true)).setHardness(1.1F).setResistance(7.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockBrickSlab");
		redRockBrickSingleSlab = (BlockHalfSlab)(new BlockRedRockBrickSlab(BOPConfiguration.redRockBrickSingleSlabID, false)).setHardness(1.1F).setResistance(7.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockBrickSlab");
		redRockBrickStairs = (new BlockRedRockBrickStairs(BOPConfiguration.redRockBrickStairsID, redRockBrick)).setUnlocalizedName("redRockBrickStairs");					
		mudBrickDoubleSlab = (BlockHalfSlab)(new BlockMudBrickSlab(BOPConfiguration.mudBrickDoubleSlabID, true)).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mudBrickSlab");
		mudBrickSingleSlab = (BlockHalfSlab)(new BlockMudBrickSlab(BOPConfiguration.mudBrickSingleSlabID, false)).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mudBrickSlab");
		mudBrickStairs = (new BlockMudBrickStairs(BOPConfiguration.mudBrickStairsID, mudBrickBlock)).setUnlocalizedName("mudBrickStairs");
		mangroveSapling = (new BlockMangroveSapling(BOPConfiguration.mangroveSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("mangroveSapling");
		hardSand = (new BlockHardSand(BOPConfiguration.hardSandID)).setHardness(0.7F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("hardSand");
		acaciaSapling = (new BlockAcaciaSapling(BOPConfiguration.acaciaSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("acaciaSapling");
		hardDirt = (new BlockHardDirt(BOPConfiguration.hardDirtID)).setHardness(0.9F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("hardDirt");
		holyGrass = (BlockHolyGrass)(new BlockHolyGrass(BOPConfiguration.holyGrassID)).setHardness(1.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holyGrass");
		holyStone = (new BlockHolyStone(BOPConfiguration.holyStoneID)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("holyStone");
		holyTallGrass = (new BlockHolyTallGrass(BOPConfiguration.holyTallGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holyTallGrass");
		promisedPortal = new BlockPromisedPortal(BOPConfiguration.promisedLandPortalID).setUnlocalizedName("promisedPortal").setBlockUnbreakable().setResistance(6000000.0F).setLightValue(1.0F);	
		holySapling = (new BlockHolySapling(BOPConfiguration.holySaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holySapling");
		amethystOre = (new BlockAmethystOre(BOPConfiguration.amethystOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("amethystOre");
		amethystBlock = (new BlockAmethystBlock(BOPConfiguration.amethystBlockID)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("amethystBlock");
		bambooThatching = (new BlockBambooThatching(BOPConfiguration.bambooThatchingID)).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bambooThatching");
		moss = (new BlockMoss(BOPConfiguration.mossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("moss");
		algae = (new BlockAlgae(BOPConfiguration.algaeID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("algae");
		smolderingGrass = (BlockSmolderingGrass)(new BlockSmolderingGrass(BOPConfiguration.smolderingGrassID)).setHardness(0.6F).setLightValue(0.25F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("smolderingGrass");
		cragRock = (new BlockCragRock(BOPConfiguration.cragRockID)).setHardness(1.0F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("cragRock");
		quicksand = (new BlockQuicksand(BOPConfiguration.quicksandID)).setHardness(0.3F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("quicksand");
		bambooSapling = (new BlockBambooSapling(BOPConfiguration.bambooSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bambooSapling");

		//Redwood
		redwoodPlank = (new BlockRedwoodPlank(BOPConfiguration.redwoodPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodPlank");
		redwoodWood = (new BlockRedwoodLog(BOPConfiguration.redwoodWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodWood");
		redwoodLeaves = (BlockRedwoodLeaves)(new BlockRedwoodLeaves(BOPConfiguration.redwoodLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redwoodLeaves");
		redwoodDoubleSlab = (BlockHalfSlab)(new BlockRedwoodSlab(BOPConfiguration.redwoodDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodSlab");
		redwoodSingleSlab = (BlockHalfSlab)(new BlockRedwoodSlab(BOPConfiguration.redwoodSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodSlab");
		redwoodStairs = (new BlockRedwoodStairs(BOPConfiguration.redwoodStairsID, redwoodPlank)).setUnlocalizedName("redwoodStairs");

		//Willow
		willowPlank = (new BlockWillowPlank(BOPConfiguration.willowPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowPlank");
		willowWood = (new BlockWillowLog(BOPConfiguration.willowWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowWood");
		willowLeaves = (BlockWillowLeaves)(new BlockWillowLeaves(BOPConfiguration.willowLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("willowLeaves");
		willowDoubleSlab = (BlockHalfSlab)(new BlockWillowSlab(BOPConfiguration.willowDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowSlab");
		willowSingleSlab = (BlockHalfSlab)(new BlockWillowSlab(BOPConfiguration.willowSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowSlab");
		willowStairs = (new BlockWillowStairs(BOPConfiguration.willowStairsID, willowPlank)).setUnlocalizedName("willowStairs");

		//Fir
		firPlank = (new BlockFirPlank(BOPConfiguration.firPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firPlank");
		firWood = (new BlockFirLog(BOPConfiguration.firWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firWood");
		firLeaves = (BlockFirLeaves)(new BlockFirLeaves(BOPConfiguration.firLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("firLeaves");
		firDoubleSlab = (BlockHalfSlab)(new BlockFirSlab(BOPConfiguration.firDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firSlab");
		firSingleSlab = (BlockHalfSlab)(new BlockFirSlab(BOPConfiguration.firSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firSlab");
		firStairs = (new BlockFirStairs(BOPConfiguration.firStairsID, firPlank)).setUnlocalizedName("firStairs");

		//Acacia
		acaciaPlank = (new BlockAcaciaPlank(BOPConfiguration.acaciaPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaPlank");
		acaciaWood = (new BlockAcaciaLog(BOPConfiguration.acaciaWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaWood");
		acaciaLeaves = (BlockAcaciaLeaves)(new BlockAcaciaLeaves(BOPConfiguration.acaciaLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("acaciaLeaves");
		acaciaDoubleSlab = (BlockHalfSlab)(new BlockAcaciaSlab(BOPConfiguration.acaciaDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaSlab");
		acaciaSingleSlab = (BlockHalfSlab)(new BlockAcaciaSlab(BOPConfiguration.acaciaSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaSlab");
		acaciaStairs = (new BlockAcaciaStairs(BOPConfiguration.acaciaStairsID, acaciaPlank)).setUnlocalizedName("acaciaStairs");

		//Cherry
		cherryPlank = (new BlockCherryPlank(BOPConfiguration.cherryPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherryPlank");
		cherryWood = (new BlockCherryLog(BOPConfiguration.cherryWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherryWood");
		cherryDoubleSlab = (BlockHalfSlab)(new BlockCherrySlab(BOPConfiguration.cherryDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherrySlab");
		cherrySingleSlab = (BlockHalfSlab)(new BlockCherrySlab(BOPConfiguration.cherrySingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherrySlab");
		cherryStairs = (new BlockCherryStairs(BOPConfiguration.cherryStairsID, cherryPlank)).setUnlocalizedName("cherryStairs");

		//Dark
		darkPlank = (new BlockDarkPlank(BOPConfiguration.darkPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkPlank");
		darkWood = (new BlockDarkLog(BOPConfiguration.darkWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkWood");
		darkLeaves = (BlockDarkLeaves)(new BlockDarkLeaves(BOPConfiguration.darkLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("darkLeaves");
		darkDoubleSlab = (BlockHalfSlab)(new BlockDarkSlab(BOPConfiguration.darkDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkSlab");
		darkSingleSlab = (BlockHalfSlab)(new BlockDarkSlab(BOPConfiguration.darkSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkSlab");
		darkStairs = (new BlockDarkStairs(BOPConfiguration.darkStairsID, darkPlank)).setUnlocalizedName("darkStairs");

		//Magic
		magicPlank = (new BlockMagicPlank(BOPConfiguration.magicPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicPlank");
		magicWood = (new BlockMagicLog(BOPConfiguration.magicWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicWood");
		magicDoubleSlab = (BlockHalfSlab)(new BlockMagicSlab(BOPConfiguration.magicDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicSlab");
		magicSingleSlab = (BlockHalfSlab)(new BlockMagicSlab(BOPConfiguration.magicSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicSlab");
		magicStairs = (new BlockMagicStairs(BOPConfiguration.magicStairsID, magicPlank)).setUnlocalizedName("magicStairs");

		//Palm
		palmPlank = (new BlockPalmPlank(BOPConfiguration.palmPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmPlank");
		palmWood = (new BlockPalmLog(BOPConfiguration.palmWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmWood");
		palmLeaves = (BlockPalmLeaves)(new BlockPalmLeaves(BOPConfiguration.palmLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("palmLeaves");
		palmDoubleSlab = (BlockHalfSlab)(new BlockPalmSlab(BOPConfiguration.palmDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmSlab");
		palmSingleSlab = (BlockHalfSlab)(new BlockPalmSlab(BOPConfiguration.palmSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmSlab");
		palmStairs = (new BlockPalmStairs(BOPConfiguration.palmStairsID, palmPlank)).setUnlocalizedName("palmStairs");

		//Mangrove
		mangrovePlank = (new BlockMangrovePlank(BOPConfiguration.mangrovePlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangrovePlank");
		mangroveWood = (new BlockMangroveLog(BOPConfiguration.mangroveWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangroveWood");
		mangroveLeaves = (BlockMangroveLeaves)(new BlockMangroveLeaves(BOPConfiguration.mangroveLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("mangroveLeaves");
		mangroveDoubleSlab = (BlockHalfSlab)(new BlockMangroveSlab(BOPConfiguration.mangroveDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangroveSlab");
		mangroveSingleSlab = (BlockHalfSlab)(new BlockMangroveSlab(BOPConfiguration.mangroveSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangroveSlab");
		mangroveStairs = (new BlockMangroveStairs(BOPConfiguration.mangroveStairsID, mangrovePlank)).setUnlocalizedName("mangroveStairs");

		//Holy
		holyPlank = (new BlockHolyPlank(BOPConfiguration.holyPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holyPlank");
		holyWood = (new BlockHolyLog(BOPConfiguration.holyWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holyWood");
		holyLeaves = (BlockHolyLeaves)(new BlockHolyLeaves(BOPConfiguration.holyLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holyLeaves");
		holyDoubleSlab = (BlockHalfSlab)(new BlockHolySlab(BOPConfiguration.holyDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holySlab");
		holySingleSlab = (BlockHalfSlab)(new BlockHolySlab(BOPConfiguration.holySingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holySlab");
		holyStairs = (new BlockHolyStairs(BOPConfiguration.holyStairsID, holyPlank)).setUnlocalizedName("holyStairs");
		
/*
		// Planks - WORKING!
		Blocks.planks = Optional.of((new BlockBOPPlank(1989)).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("planks"));
        
        GameRegistry.registerBlock(Blocks.planks.get(), ItemBOPPlank.class, "planks");
        LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 0), "Acacia Wood Planks");
        LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 1), "Cherry Wood Planks");
        LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 2), "Dark Wood Planks");
        LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 3), "Fir Wood Planks");
        LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 4), "Holy Wood Planks");
        LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 5), "Magic Wood Planks");
        LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 6), "Mangrove Wood Planks");
        LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 7), "Palm Wood Planks");
        LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 8), "Redwood Wood Planks");
        LanguageRegistry.addName(new ItemStack(Blocks.planks.get(), 1, 9), "Willow Wood Planks");
        
        // Stairs - WORKING!
        Blocks.acaciaStairs = Optional.of((new BlockBOPStairs(1990, Blocks.planks.get(), WoodCategory.ACACIA)).setUnlocalizedName("acaciaStairs"));
        Blocks.cherryStairs = Optional.of((new BlockBOPStairs(1991, Blocks.planks.get(), WoodCategory.CHERRY)).setUnlocalizedName("cherryStairs"));
        Blocks.darkStairs = Optional.of((new BlockBOPStairs(1992, Blocks.planks.get(), WoodCategory.DARK)).setUnlocalizedName("darkStairs"));
        Blocks.firStairs = Optional.of((new BlockBOPStairs(1993, Blocks.planks.get(), WoodCategory.FIR)).setUnlocalizedName("firStairs"));
        Blocks.holyStairs = Optional.of((new BlockBOPStairs(1994, Blocks.planks.get(), WoodCategory.HOLY)).setUnlocalizedName("holyStairs"));
        Blocks.magicStairs = Optional.of((new BlockBOPStairs(1995, Blocks.planks.get(), WoodCategory.MAGIC)).setUnlocalizedName("magicStairs"));
        Blocks.mangroveStairs = Optional.of((new BlockBOPStairs(1996, Blocks.planks.get(), WoodCategory.MANGROVE)).setUnlocalizedName("mangroveStairs"));
        Blocks.palmStairs = Optional.of((new BlockBOPStairs(1997, Blocks.planks.get(), WoodCategory.PALM)).setUnlocalizedName("palmStairs"));
        Blocks.redwoodStairs = Optional.of((new BlockBOPStairs(1998, Blocks.planks.get(), WoodCategory.REDWOOD)).setUnlocalizedName("redwoodStairs"));
        Blocks.willowStairs = Optional.of((new BlockBOPStairs(1999, Blocks.planks.get(), WoodCategory.WILLOW)).setUnlocalizedName("willowStairs"));
        
        GameRegistry.registerBlock(Blocks.acaciaStairs.get(), "acaciaStairs1");
        GameRegistry.registerBlock(Blocks.cherryStairs.get(), "cherryStairs1");
        GameRegistry.registerBlock(Blocks.darkStairs.get(), "darkStairs1");
        GameRegistry.registerBlock(Blocks.firStairs.get(), "firStairs1");
        GameRegistry.registerBlock(Blocks.holyStairs.get(), "holyStairs1");
        GameRegistry.registerBlock(Blocks.magicStairs.get(), "magicStairs1");
        GameRegistry.registerBlock(Blocks.mangroveStairs.get(), "mangroveStairs1");
        GameRegistry.registerBlock(Blocks.palmStairs.get(), "palmStairs1");
        GameRegistry.registerBlock(Blocks.redwoodStairs.get(), "redwoodStairs1");
        GameRegistry.registerBlock(Blocks.willowStairs.get(), "stairs1");
        
        LanguageRegistry.addName(Blocks.acaciaStairs.get(), "Acacia Wood Stairs");
        LanguageRegistry.addName(Blocks.cherryStairs.get(), "Cherry Wood Stairs");
        LanguageRegistry.addName(Blocks.darkStairs.get(), "Dark Wood Stairs");
        LanguageRegistry.addName(Blocks.firStairs.get(), "Fir Wood Stairs");
        LanguageRegistry.addName(Blocks.holyStairs.get(), "Holy Wood Stairs");
        LanguageRegistry.addName(Blocks.magicStairs.get(), "Magic Wood Stairs");
        LanguageRegistry.addName(Blocks.mangroveStairs.get(), "Mangrove Wood Stairs");
        LanguageRegistry.addName(Blocks.palmStairs.get(), "Palm Wood Stairs");
        LanguageRegistry.addName(Blocks.redwoodStairs.get(), "Redwood Wood Stairs");
        LanguageRegistry.addName(Blocks.willowStairs.get(), "Willow Wood Stairs");

        Blocks.woodenDoubleSlab1 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(2007, true, Material.wood, SlabCategory.WOOD1)).setUnlocalizedName("woodenDoubleSlab1"));
        Blocks.woodenSingleSlab1 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(2006, false, Material.wood, SlabCategory.WOOD1)).setUnlocalizedName("woodenSingleSlab1"));
        ItemBOPSlab.setSlabs(Blocks.woodenSingleSlab1.get(), Blocks.woodenDoubleSlab1.get());
        
        // Slabs - WORKING!
        GameRegistry.registerBlock(Blocks.woodenDoubleSlab1.get(), ItemBOPSlab.class, "woodenDoubleSlab1");
        GameRegistry.registerBlock(Blocks.woodenSingleSlab1.get(), ItemBOPSlab.class, "woodenSingleSlab1");
        
        LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,0), "Acacia Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,1), "Cherry Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,2), "Dark Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,3), "Fir Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,4), "Holy Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,5), "Magic Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,6), "Mangrove Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab1.get(),1,7), "Palm Wood Slab");
        
        LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,0), "Acacia Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,1), "Cherry Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,2), "Dark Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,3), "Fir Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,4), "Holy Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,5), "Magic Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,6), "Mangrove Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab1.get(),1,7), "Palm Wood Slab");

        Blocks.woodenDoubleSlab2 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(2009, true, Material.wood, SlabCategory.WOOD2)).setUnlocalizedName("woodenDoubleSlab2"));
        Blocks.woodenSingleSlab2 = Optional.of((BlockHalfSlab)(new BlockBOPSlab(2008, false, Material.wood, SlabCategory.WOOD2)).setUnlocalizedName("woodenSingleSlab2"));
        ItemBOPSlab.setSlabs(Blocks.woodenSingleSlab2.get(), Blocks.woodenDoubleSlab2.get());

        GameRegistry.registerBlock(Blocks.woodenDoubleSlab2.get(), ItemBOPSlab.class, "woodenDoubleSlab2");
        GameRegistry.registerBlock(Blocks.woodenSingleSlab2.get(), ItemBOPSlab.class, "woodenSingleSlab2");
        
        LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab2.get(),1,0), "Redwood Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenDoubleSlab2.get(),1,1), "Willow Wood Slab");
        
        LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab2.get(),1,0), "Redwood Wood Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.woodenSingleSlab2.get(),1,1), "Willow Wood Slab");
        
        Blocks.stoneDoubleSlab = Optional.of((BlockHalfSlab)(new BlockBOPSlab(2011, true, Material.rock, SlabCategory.STONE)).setUnlocalizedName("stoneDoubleSlab"));
        Blocks.stoneSingleSlab = Optional.of((BlockHalfSlab)(new BlockBOPSlab(2010, false, Material.rock, SlabCategory.STONE)).setUnlocalizedName("stoneSingleSlab"));
        ItemBOPSlab.setSlabs(Blocks.stoneSingleSlab.get(), Blocks.stoneDoubleSlab.get());

        GameRegistry.registerBlock(Blocks.stoneDoubleSlab.get(), ItemBOPSlab.class, "stoneDoubleSlab2");
        GameRegistry.registerBlock(Blocks.stoneSingleSlab.get(), ItemBOPSlab.class, "stoneSingleSlab");
        
        LanguageRegistry.addName(new ItemStack(Blocks.stoneDoubleSlab.get(),1,0), "Red Rock Bricks Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.stoneDoubleSlab.get(),1,1), "Red Rock Cobblestone Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.stoneDoubleSlab.get(),1,2), "Mud Bricks Slab");
        
        LanguageRegistry.addName(new ItemStack(Blocks.stoneSingleSlab.get(),1,0), "Red Rock Bricks Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.stoneSingleSlab.get(),1,1), "Red Rock Cobblestone Slab");
        LanguageRegistry.addName(new ItemStack(Blocks.stoneSingleSlab.get(),1,2), "Mud Bricks Slab");

		// Flowers - WORKING! - need a fix for tiny cactus
        Blocks.flowers = Optional.of((new BlockBOPFlower(2002)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("flowers"));
		GameRegistry.registerBlock(Blocks.flowers.get(), ItemBOPFlower.class, "flowers");
		
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
        LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,10), "Toadstool");
        LanguageRegistry.addName(new ItemStack(Blocks.flowers.get(),1,11), "Tiny Cactus");

        // Leaves - WORKING!
        Blocks.leaves = Optional.of((new BlockBOPLeaves(2000)).setUnlocalizedName("leaves"));
		GameRegistry.registerBlock(Blocks.leaves.get(), ItemBOPLeaves.class, "leaves");
		
		LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,0), "Autumn Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,1), "Bamboo Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,2), "Magic Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,3), "Dark Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,4), "Dying Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,5), "Fir Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,6), "Holy Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,7), "Autumn Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,8), "Origin Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,9), "Cherry Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,10), "Maple Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leaves.get(),1,11), "Cherry Leaves");
        
        Blocks.leavesColorized = Optional.of((new BlockBOPColorizedLeaves(2001)).setUnlocalizedName("leavesColorized"));
        GameRegistry.registerBlock(Blocks.leavesColorized.get(), ItemBOPColorizedLeaves.class, "leavesColorized");
        
        LanguageRegistry.addName(new ItemStack(Blocks.leavesColorized.get(),1,0), "Acacia Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leavesColorized.get(),1,1), "Mangrove Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leavesColorized.get(),1,2), "Palm Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leavesColorized.get(),1,3), "Redwood Leaves");
        LanguageRegistry.addName(new ItemStack(Blocks.leavesColorized.get(),1,4), "Willow Leaves");
        
        // Logs - WORKING!
        Blocks.logs1 = Optional.of((new BlockBOPLog(2003,LogCategory.CAT1)).setUnlocalizedName("wood1"));
        Blocks.logs2 = Optional.of((new BlockBOPLog(2004,LogCategory.CAT2)).setUnlocalizedName("wood2"));
        Blocks.logs3 = Optional.of((new BlockBOPLog(2005,LogCategory.CAT3)).setUnlocalizedName("wood3"));
        GameRegistry.registerBlock(Blocks.logs1.get(), ItemBOPLog.class, "wood1");
        GameRegistry.registerBlock(Blocks.logs2.get(), ItemBOPLog.class, "wood2");
        GameRegistry.registerBlock(Blocks.logs3.get(), ItemBOPLog.class, "wood3");

        LanguageRegistry.addName(new ItemStack(Blocks.logs1.get(),1,0), "Acacia Wood");
        LanguageRegistry.addName(new ItemStack(Blocks.logs1.get(),1,1), "Cherry Wood");
        LanguageRegistry.addName(new ItemStack(Blocks.logs1.get(),1,2), "Dark Wood");
        LanguageRegistry.addName(new ItemStack(Blocks.logs1.get(),1,3), "Fir Wood");
        
        LanguageRegistry.addName(new ItemStack(Blocks.logs2.get(),1,0), "Holy Wood");
        LanguageRegistry.addName(new ItemStack(Blocks.logs2.get(),1,1), "Magic Wood");
        LanguageRegistry.addName(new ItemStack(Blocks.logs2.get(),1,2), "Mangrove Wood");
        LanguageRegistry.addName(new ItemStack(Blocks.logs2.get(),1,3), "Palm Wood");
        
        LanguageRegistry.addName(new ItemStack(Blocks.logs3.get(),1,0), "Redwood Wood");
        LanguageRegistry.addName(new ItemStack(Blocks.logs3.get(),1,1), "Willow Wood");
        LanguageRegistry.addName(new ItemStack(Blocks.logs3.get(),1,2), "Dead Wood");

		// Foliage - WORKIMG!
		Blocks.foliage = Optional.of((new BlockBOPFoliage(1988)).setUnlocalizedName("foliage"));
		GameRegistry.registerBlock(Blocks.foliage.get(), ItemBOPFoliage.class, "foliage");
		
		LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,1), "Short Grass");
        LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,2), "Medium Grass");
        LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,3), "High Grass");
        LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,4), "Bush");
        LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,5), "Sprout");
        LanguageRegistry.addName(new ItemStack(Blocks.foliage.get(),1,0), "Algae");

		// Plants - Semi working
		Blocks.plants = Optional.of((new BlockBOPPlant(1987)).setUnlocalizedName("plants"));
        GameRegistry.registerBlock(Blocks.plants.get(), ItemBOPPlant.class, "plants");
        
        LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,0), "Dead Grass");
        LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,1), "Desert Grass");
        LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,2), "Desert Sprouts");
        LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,3), "Dune Grass");
        LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,4), "Holy Tall Grass");
        LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,5), "Thorns");
        LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,6), "Barley");
        LanguageRegistry.addName(new ItemStack(Blocks.plants.get(),1,7), "Cattail");

		// Saplings - WORKING!
		Blocks.saplings = Optional.of((new BlockBOPSapling(1986)).setUnlocalizedName("saplings"));
        GameRegistry.registerBlock(Blocks.saplings.get(), ItemBOPSapling.class, "saplings");
        
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,0), "Autumn Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,1), "Bamboo Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,2), "Magic Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,3), "Dark Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,4), "Dying Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,5), "Fir Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,6), "Holy Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,7), "Autumn Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,8), "Origin Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,9), "Cherry Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,10), "Maple Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.saplings.get(),1,11), "Cherry Sapling");
        
        Blocks.colorizedSaplings = Optional.of((new BlockBOPColorizedSapling(1985)).setUnlocalizedName("colorizedSaplings"));
        GameRegistry.registerBlock(Blocks.colorizedSaplings.get(), ItemBOPColorizedSapling.class, "colorizedSaplings");
        
        LanguageRegistry.addName(new ItemStack(Blocks.colorizedSaplings.get(),1,0), "Acacia Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.colorizedSaplings.get(),1,1), "Mangrove Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.colorizedSaplings.get(),1,2), "Palm Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.colorizedSaplings.get(),1,3), "Redwood Sapling");
        LanguageRegistry.addName(new ItemStack(Blocks.colorizedSaplings.get(),1,4), "Willow Sapling");

        Blocks.petals = Optional.of((new BlockBOPPetals(2084)).setUnlocalizedName("petals"));
        GameRegistry.registerBlock(Blocks.petals.get(), ItemBOPPetals.class, "petals");
        
        LanguageRegistry.addName(new ItemStack(Blocks.petals.get(),1,0), "Giant Red Flower");
        LanguageRegistry.addName(new ItemStack(Blocks.petals.get(),1,1), "Giant Yellow Flower");
*/
        
		// Add block registration
		GameRegistry.registerBlock(mud, "mud");
		GameRegistry.registerBlock(driedDirt, "driedDirt");
		GameRegistry.registerBlock(redRock, "redRock");
		GameRegistry.registerBlock(ash, "ash");
		GameRegistry.registerBlock(deadGrass, "deadGrass");
		GameRegistry.registerBlock(desertGrass, "desertGrass");
		GameRegistry.registerBlock(whiteFlower, "whiteFlower");
		GameRegistry.registerBlock(blueFlower, "blueFlower");
		GameRegistry.registerBlock(purpleFlower, "purpleFlower");
		GameRegistry.registerBlock(orangeFlower, "orangeFlower");
		GameRegistry.registerBlock(tinyFlower, "tinyFlower");
		GameRegistry.registerBlock(glowFlower, "glowFlower");
		GameRegistry.registerBlock(cattail, "cattail");
		GameRegistry.registerBlock(willow, "willow");
		GameRegistry.registerBlock(autumnLeaves, "autumnLeaves");
		GameRegistry.registerBlock(thorn, "thorn");
		GameRegistry.registerBlock(toadstool, "toadstool");
		GameRegistry.registerBlock(highGrassBottom, "highGrassBottom");
		GameRegistry.registerBlock(highGrassTop, "highGrassTop");
		GameRegistry.registerBlock(ashStone, "ashStone");
		GameRegistry.registerBlock(hardIce, "hardIce");
		GameRegistry.registerBlock(redLeaves, "redLeaves");
		GameRegistry.registerBlock(orangeLeaves, "orangeLeaves");
		GameRegistry.registerBlock(pinkLeaves, "pinkLeaves");
		GameRegistry.registerBlock(blueLeaves, "blueLeaves");
		GameRegistry.registerBlock(whiteLeaves, "whiteLeaves");
		GameRegistry.registerBlock(deadLeaves, "deadLeaves");
		GameRegistry.registerBlock(shortGrass, "shortGrass");
		GameRegistry.registerBlock(appleLeaves, "appleLeaves");
		GameRegistry.registerBlock(sprout, "sprout");
		GameRegistry.registerBlock(bush, "bush");
		GameRegistry.registerBlock(bamboo, "bamboo");
		GameRegistry.registerBlock(bambooLeaves, "bambooLeaves");
		GameRegistry.registerBlock(mudBrickBlock, "mudBrickBlock");
		ItemBOPSlab.setSlabs(mudBrickSingleSlab, mudBrickDoubleSlab);
		GameRegistry.registerBlock(mudBrickDoubleSlab, ItemBOPSlab.class, "mudBrickDoubleSlab");
		GameRegistry.registerBlock(mudBrickSingleSlab, ItemBOPSlab.class, "mudBrickSingleSlab");
		GameRegistry.registerBlock(mudBrickStairs, "mudBrickStairs");
		GameRegistry.registerBlock(originGrass, "originGrass");
		GameRegistry.registerBlock(originLeaves, "originLeaves");
		GameRegistry.registerBlock(pinkFlower, "pinkFlower");
		GameRegistry.registerBlock(treeMoss, "treeMoss");
		GameRegistry.registerBlock(deadWood, "deadWood");
		GameRegistry.registerBlock(appleLeavesFruitless, "appleLeavesFruitless");
		GameRegistry.registerBlock(barley, "barley");
		GameRegistry.registerBlock(giantFlowerStem, "giantFlowerStem");
		GameRegistry.registerBlock(giantFlowerRed, "giantFlowerRed");
		GameRegistry.registerBlock(giantFlowerYellow, "giantFlowerYellow");
		GameRegistry.registerBlock(tinyCactus, "tinyCactus");
		GameRegistry.registerBlock(firSapling, "firSapling");
		GameRegistry.registerBlock(redwoodSapling, "redwoodSapling");
		GameRegistry.registerBlock(palmSapling, "palmSapling");
		GameRegistry.registerBlock(redSapling, "redSapling");
		GameRegistry.registerBlock(orangeSapling, "orangeSapling");
		GameRegistry.registerBlock(yellowSapling, "yellowSapling");
		GameRegistry.registerBlock(brownSapling, "brownSapling");
		GameRegistry.registerBlock(willowSapling, "willowSapling");
		GameRegistry.registerBlock(appleSapling, "appleSapling");
		GameRegistry.registerBlock(originSapling, "originSapling");
		GameRegistry.registerBlock(pinkSapling, "pinkSapling");
		GameRegistry.registerBlock(whiteSapling, "whiteSapling");
		GameRegistry.registerBlock(darkSapling, "darkSapling");
		GameRegistry.registerBlock(magicSapling, "magicSapling");
		GameRegistry.registerBlock(deathbloom, "deathbloom");
		GameRegistry.registerBlock(redRockCobble, "redRockCobble");
		ItemBOPSlab.setSlabs(redRockCobbleSingleSlab, redRockCobbleDoubleSlab);
		GameRegistry.registerBlock(redRockCobbleDoubleSlab, ItemBOPSlab.class, "redRockCobbleDoubleSlab");
		GameRegistry.registerBlock(redRockCobbleSingleSlab, ItemBOPSlab.class, "redRockCobbleSingleSlab");
		GameRegistry.registerBlock(redRockCobbleStairs, "redRockCobbleStairs");
		GameRegistry.registerBlock(redRockBrick, "redRockBrick");
		ItemBOPSlab.setSlabs(redRockBrickSingleSlab, redRockBrickDoubleSlab);
		GameRegistry.registerBlock(redRockBrickDoubleSlab, ItemBOPSlab.class, "redRockBrickDoubleSlab");
		GameRegistry.registerBlock(redRockBrickSingleSlab, ItemBOPSlab.class, "redRockBrickSingleSlab");
		GameRegistry.registerBlock(redRockBrickStairs, "redRockBrickStairs");
		GameRegistry.registerBlock(hydrangea, "hydrangea");
		GameRegistry.registerBlock(violet, "violet");
		GameRegistry.registerBlock(mediumGrass, "mediumGrass");
		GameRegistry.registerBlock(duneGrass, "duneGrass");
		GameRegistry.registerBlock(desertSprouts, "desertSprouts");
		GameRegistry.registerBlock(mangroveSapling, "mangroveSapling");
		GameRegistry.registerBlock(hardSand, "hardSand");
		GameRegistry.registerBlock(acaciaSapling, "acaciaSapling");
		GameRegistry.registerBlock(hardDirt, "hardDirt");
		GameRegistry.registerBlock(holyGrass, "holyGrass");
		GameRegistry.registerBlock(holyStone, "holyStone");
		GameRegistry.registerBlock(holyTallGrass, "holyTallGrass");
		GameRegistry.registerBlock(promisedPortal, "promisedPortal");
		GameRegistry.registerBlock(holySapling, "holySapling");
		GameRegistry.registerBlock(amethystOre, "amethystOre");
		GameRegistry.registerBlock(amethystBlock, "amethystBlock");
		GameRegistry.registerBlock(bambooThatching, "bambooThatching");
		GameRegistry.registerBlock(moss, "moss");
		GameRegistry.registerBlock(algae, "algae");
		GameRegistry.registerBlock(smolderingGrass, "smolderingGrass");
		GameRegistry.registerBlock(cragRock, "cragRock");
		GameRegistry.registerBlock(quicksand, "quicksand");
		GameRegistry.registerBlock(bambooSapling, "bambooSapling");

		GameRegistry.registerBlock(redwoodPlank, "redwoodPlank");
		GameRegistry.registerBlock(redwoodWood, "redwoodWood");
		GameRegistry.registerBlock(redwoodLeaves, "redwoodLeaves");
		ItemBOPSlab.setSlabs(redwoodSingleSlab, redwoodDoubleSlab);
		GameRegistry.registerBlock(redwoodDoubleSlab, ItemBOPSlab.class, "redwoodDoubleSlab");
		GameRegistry.registerBlock(redwoodSingleSlab, ItemBOPSlab.class, "redwoodSingleSlab");
		GameRegistry.registerBlock(redwoodStairs, "redwoodStairs");

		GameRegistry.registerBlock(willowPlank, "willowPlank");
		GameRegistry.registerBlock(willowWood, "willowWood");
		GameRegistry.registerBlock(willowLeaves, "willowLeaves");
		ItemBOPSlab.setSlabs(willowSingleSlab, willowDoubleSlab);
		GameRegistry.registerBlock(willowDoubleSlab, ItemBOPSlab.class, "willowDoubleSlab");
		GameRegistry.registerBlock(willowSingleSlab, ItemBOPSlab.class, "willowSingleSlab");
		GameRegistry.registerBlock(willowStairs, "willowStairs");

		GameRegistry.registerBlock(firPlank, "firPlank");
		GameRegistry.registerBlock(firWood, "firWood");
		GameRegistry.registerBlock(firLeaves, "firLeaves");
		ItemBOPSlab.setSlabs(firSingleSlab, firDoubleSlab);
		GameRegistry.registerBlock(firDoubleSlab, ItemBOPSlab.class, "firDoubleSlab");
		GameRegistry.registerBlock(firSingleSlab, ItemBOPSlab.class, "firSingleSlab");
		GameRegistry.registerBlock(firStairs, "firStairs");

		GameRegistry.registerBlock(acaciaPlank, "acaciaPlank");
		GameRegistry.registerBlock(acaciaWood, "acaciaWood");
		GameRegistry.registerBlock(acaciaLeaves, "acaciaLeaves");
		ItemBOPSlab.setSlabs(acaciaSingleSlab, acaciaDoubleSlab);
		GameRegistry.registerBlock(acaciaDoubleSlab, ItemBOPSlab.class, "acaciaDoubleSlab");
		GameRegistry.registerBlock(acaciaSingleSlab, ItemBOPSlab.class, "acaciaSingleSlab");
		GameRegistry.registerBlock(acaciaStairs, "acaciaStairs");

		GameRegistry.registerBlock(cherryPlank, "cherryPlank");
		GameRegistry.registerBlock(cherryWood, "cherryWood");
		ItemBOPSlab.setSlabs(cherrySingleSlab, cherryDoubleSlab);
		GameRegistry.registerBlock(cherryDoubleSlab, ItemBOPSlab.class, "cherryDoubleSlab");
		GameRegistry.registerBlock(cherrySingleSlab, ItemBOPSlab.class, "cherrySingleSlab");
		GameRegistry.registerBlock(cherryStairs, "cherryStairs");

		GameRegistry.registerBlock(darkPlank, "darkPlank");
		GameRegistry.registerBlock(darkWood, "darkWood");
		GameRegistry.registerBlock(darkLeaves, "darkLeaves");
		ItemBOPSlab.setSlabs(darkSingleSlab, darkDoubleSlab);
		GameRegistry.registerBlock(darkDoubleSlab, ItemBOPSlab.class, "darkDoubleSlab");
		GameRegistry.registerBlock(darkSingleSlab, ItemBOPSlab.class, "darkSingleSlab");
		GameRegistry.registerBlock(darkStairs, "darkStairs");

		GameRegistry.registerBlock(magicPlank, "magicPlank");
		GameRegistry.registerBlock(magicWood, "magicWood");
		ItemBOPSlab.setSlabs(magicSingleSlab, magicDoubleSlab);
		GameRegistry.registerBlock(magicDoubleSlab, ItemBOPSlab.class, "magicDoubleSlab");
		GameRegistry.registerBlock(magicSingleSlab, ItemBOPSlab.class, "magicSingleSlab");
		GameRegistry.registerBlock(magicStairs, "magicStairs");

		GameRegistry.registerBlock(palmPlank, "palmPlank");
		GameRegistry.registerBlock(palmWood, "palmWood");
		GameRegistry.registerBlock(palmLeaves, "palmLeaves");
		ItemBOPSlab.setSlabs(palmSingleSlab, palmDoubleSlab);
		GameRegistry.registerBlock(palmDoubleSlab, ItemBOPSlab.class, "palmDoubleSlab");
		GameRegistry.registerBlock(palmSingleSlab, ItemBOPSlab.class, "palmSingleSlab");
		GameRegistry.registerBlock(palmStairs, "palmStairs");

		GameRegistry.registerBlock(mangrovePlank, "mangrovePlank");
		GameRegistry.registerBlock(mangroveWood, "mangroveWood");
		GameRegistry.registerBlock(mangroveLeaves, "mangroveLeaves");
		ItemBOPSlab.setSlabs(mangroveSingleSlab, mangroveDoubleSlab);
		GameRegistry.registerBlock(mangroveDoubleSlab, ItemBOPSlab.class, "mangroveDoubleSlab");
		GameRegistry.registerBlock(mangroveSingleSlab, ItemBOPSlab.class, "mangroveSingleSlab");
		GameRegistry.registerBlock(mangroveStairs, "mangroveStairs");

		GameRegistry.registerBlock(holyPlank, "holyPlank");
		GameRegistry.registerBlock(holyWood, "holyWood");
		GameRegistry.registerBlock(holyLeaves, "holyLeaves");
		ItemBOPSlab.setSlabs(holySingleSlab, holyDoubleSlab);
		GameRegistry.registerBlock(holyDoubleSlab, ItemBOPSlab.class, "holyDoubleSlab");
		GameRegistry.registerBlock(holySingleSlab, ItemBOPSlab.class, "holySingleSlab");
		GameRegistry.registerBlock(holyStairs, "holyStairs");

		// Add crafting recipes.

		//Plants
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 6), new Object[] {blueFlower});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 13), new Object[] {purpleFlower});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 14), new Object[] {orangeFlower});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 9), new Object[] {pinkFlower});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 7), new Object[] {whiteFlower});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {hydrangea});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 5), new Object[] {violet});

		//Brick stairs and slabs
		GameRegistry.addRecipe(new ItemStack(redRockCobbleSingleSlab, 6), new Object[] {"RRR", 'R', redRockCobble});
		GameRegistry.addRecipe(new ItemStack(redRockCobbleStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', redRockCobble});
		GameRegistry.addRecipe(new ItemStack(redRockCobbleStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', redRockCobble});
		GameRegistry.addRecipe(new ItemStack(redRockBrickSingleSlab, 6), new Object[] {"RRR", 'R', redRockBrick});
		GameRegistry.addRecipe(new ItemStack(redRockBrickStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', redRockBrick});
		GameRegistry.addRecipe(new ItemStack(redRockBrickStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', redRockBrick});
		GameRegistry.addRecipe(new ItemStack(mudBrickSingleSlab, 6), new Object[] {"RRR", 'R', mudBrickBlock});
		GameRegistry.addRecipe(new ItemStack(mudBrickStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', mudBrickBlock});
		GameRegistry.addRecipe(new ItemStack(mudBrickStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', mudBrickBlock});

		//Redwood
		OreDictionary.registerOre("plankWood", new ItemStack(redwoodPlank));
		OreDictionary.registerOre("logWood", new ItemStack(redwoodWood));
		OreDictionary.registerOre("slabWood", new ItemStack(redwoodSingleSlab));
		OreDictionary.registerOre("stairWood", new ItemStack(redwoodStairs));
		OreDictionary.registerOre("treeLeaves", new ItemStack(redwoodLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(redwoodSapling));

		GameRegistry.addShapelessRecipe(new ItemStack(redwoodPlank, 4), new Object[] {redwoodWood});
		GameRegistry.addRecipe(new ItemStack(redwoodSingleSlab, 6), new Object[] {"RRR", 'R', redwoodPlank});
		GameRegistry.addRecipe(new ItemStack(redwoodStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', redwoodPlank});
		GameRegistry.addRecipe(new ItemStack(redwoodStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', redwoodPlank});

		//Willow
		OreDictionary.registerOre("plankWood", new ItemStack(willowPlank));
		OreDictionary.registerOre("logWood", new ItemStack(willowWood));
		OreDictionary.registerOre("slabWood", new ItemStack(willowSingleSlab));
		OreDictionary.registerOre("stairWood", new ItemStack(willowStairs));
		OreDictionary.registerOre("treeLeaves", new ItemStack(willowLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(willowSapling));

		GameRegistry.addShapelessRecipe(new ItemStack(willowPlank, 4), new Object[] {willowWood});
		GameRegistry.addRecipe(new ItemStack(willowSingleSlab, 6), new Object[] {"RRR", 'R', willowPlank});
		GameRegistry.addRecipe(new ItemStack(willowStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', willowPlank});
		GameRegistry.addRecipe(new ItemStack(willowStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', willowPlank});

		//Acacia
		OreDictionary.registerOre("plankWood", new ItemStack(acaciaPlank));
		OreDictionary.registerOre("logWood", new ItemStack(acaciaWood));
		OreDictionary.registerOre("slabWood", new ItemStack(acaciaSingleSlab));
		OreDictionary.registerOre("stairWood", new ItemStack(acaciaStairs));
		OreDictionary.registerOre("treeLeaves", new ItemStack(acaciaLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(acaciaSapling));

		GameRegistry.addShapelessRecipe(new ItemStack(acaciaPlank, 4), new Object[] {acaciaWood});
		GameRegistry.addRecipe(new ItemStack(acaciaSingleSlab, 6), new Object[] {"RRR", 'R', acaciaPlank});
		GameRegistry.addRecipe(new ItemStack(acaciaStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', acaciaPlank});
		GameRegistry.addRecipe(new ItemStack(acaciaStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', acaciaPlank});

		//Fir
		OreDictionary.registerOre("plankWood", new ItemStack(firPlank));
		OreDictionary.registerOre("logWood", new ItemStack(firWood));
		OreDictionary.registerOre("slabWood", new ItemStack(firSingleSlab));
		OreDictionary.registerOre("stairWood", new ItemStack(firStairs));
		OreDictionary.registerOre("treeLeaves", new ItemStack(firLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(firSapling));

		GameRegistry.addShapelessRecipe(new ItemStack(firPlank, 4), new Object[] {firWood});
		GameRegistry.addRecipe(new ItemStack(firSingleSlab, 6), new Object[] {"RRR", 'R', firPlank});
		GameRegistry.addRecipe(new ItemStack(firStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', firPlank});
		GameRegistry.addRecipe(new ItemStack(firStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', firPlank});

		//Cherry
		OreDictionary.registerOre("plankWood", new ItemStack(cherryPlank));
		OreDictionary.registerOre("logWood", new ItemStack(cherryWood));
		OreDictionary.registerOre("slabWood", new ItemStack(cherrySingleSlab));
		OreDictionary.registerOre("stairWood", new ItemStack(cherryStairs));
		OreDictionary.registerOre("treeLeaves", new ItemStack(pinkLeaves));
		OreDictionary.registerOre("treeLeaves", new ItemStack(whiteLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(pinkSapling));
		OreDictionary.registerOre("treeSapling", new ItemStack(whiteSapling));

		GameRegistry.addShapelessRecipe(new ItemStack(cherryPlank, 4), new Object[] {cherryWood});
		GameRegistry.addRecipe(new ItemStack(cherrySingleSlab, 6), new Object[] {"RRR", 'R', cherryPlank});
		GameRegistry.addRecipe(new ItemStack(cherryStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', cherryPlank});
		GameRegistry.addRecipe(new ItemStack(cherryStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', cherryPlank});

		//Dark
		OreDictionary.registerOre("plankWood", new ItemStack(darkPlank));
		OreDictionary.registerOre("logWood", new ItemStack(darkWood));
		OreDictionary.registerOre("slabWood", new ItemStack(darkSingleSlab));
		OreDictionary.registerOre("stairWood", new ItemStack(darkStairs));
		OreDictionary.registerOre("treeLeaves", new ItemStack(darkLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(darkSapling));

		GameRegistry.addShapelessRecipe(new ItemStack(darkPlank, 4), new Object[] {darkWood});
		GameRegistry.addRecipe(new ItemStack(darkSingleSlab, 6), new Object[] {"RRR", 'R', darkPlank});
		GameRegistry.addRecipe(new ItemStack(darkStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', darkPlank});
		GameRegistry.addRecipe(new ItemStack(darkStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', darkPlank});

		//Magic
		OreDictionary.registerOre("plankWood", new ItemStack(magicPlank));
		OreDictionary.registerOre("logWood", new ItemStack(magicWood));
		OreDictionary.registerOre("slabWood", new ItemStack(magicSingleSlab));
		OreDictionary.registerOre("stairWood", new ItemStack(magicStairs));
		OreDictionary.registerOre("treeLeaves", new ItemStack(blueLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(magicSapling));

		GameRegistry.addShapelessRecipe(new ItemStack(magicPlank, 4), new Object[] {magicWood});
		GameRegistry.addRecipe(new ItemStack(magicSingleSlab, 6), new Object[] {"RRR", 'R', magicPlank});
		GameRegistry.addRecipe(new ItemStack(magicStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', magicPlank});
		GameRegistry.addRecipe(new ItemStack(magicStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', magicPlank});

		//Palm
		OreDictionary.registerOre("plankWood", new ItemStack(palmPlank));
		OreDictionary.registerOre("logWood", new ItemStack(palmWood));
		OreDictionary.registerOre("slabWood", new ItemStack(palmSingleSlab));
		OreDictionary.registerOre("stairWood", new ItemStack(palmStairs));
		OreDictionary.registerOre("treeLeaves", new ItemStack(palmLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(palmSapling));

		GameRegistry.addShapelessRecipe(new ItemStack(palmPlank, 4), new Object[] {palmWood});
		GameRegistry.addRecipe(new ItemStack(palmSingleSlab, 6), new Object[] {"RRR", 'R', palmPlank});
		GameRegistry.addRecipe(new ItemStack(palmStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', palmPlank});
		GameRegistry.addRecipe(new ItemStack(palmStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', palmPlank});

		//Mangrove
		OreDictionary.registerOre("plankWood", new ItemStack(mangrovePlank));
		OreDictionary.registerOre("logWood", new ItemStack(mangroveWood));
		OreDictionary.registerOre("slabWood", new ItemStack(mangroveSingleSlab));
		OreDictionary.registerOre("stairWood", new ItemStack(mangroveStairs));
		OreDictionary.registerOre("treeLeaves", new ItemStack(mangroveLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(mangroveSapling));

		GameRegistry.addShapelessRecipe(new ItemStack(mangrovePlank, 4), new Object[] {mangroveWood});
		GameRegistry.addRecipe(new ItemStack(mangroveSingleSlab, 6), new Object[] {"RRR", 'R', mangrovePlank});
		GameRegistry.addRecipe(new ItemStack(mangroveStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', mangrovePlank});
		GameRegistry.addRecipe(new ItemStack(mangroveStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', mangrovePlank});

		//Holy
		OreDictionary.registerOre("plankWood", new ItemStack(holyPlank));
		OreDictionary.registerOre("logWood", new ItemStack(holyWood));
		OreDictionary.registerOre("slabWood", new ItemStack(holySingleSlab));
		OreDictionary.registerOre("stairWood", new ItemStack(holyStairs));
		OreDictionary.registerOre("treeLeaves", new ItemStack(holyLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(holySapling));

		GameRegistry.addShapelessRecipe(new ItemStack(holyPlank, 4), new Object[] {holyWood});
		GameRegistry.addRecipe(new ItemStack(holySingleSlab, 6), new Object[] {"RRR", 'R', holyPlank});
		GameRegistry.addRecipe(new ItemStack(holyStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', holyPlank});
		GameRegistry.addRecipe(new ItemStack(holyStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', holyPlank});

		//Autumn
		OreDictionary.registerOre("treeLeaves", new ItemStack(autumnLeaves));
		OreDictionary.registerOre("treeLeaves", new ItemStack(redLeaves));
		OreDictionary.registerOre("treeLeaves", new ItemStack(orangeLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(yellowSapling));
		OreDictionary.registerOre("treeSapling", new ItemStack(redSapling));
		OreDictionary.registerOre("treeSapling", new ItemStack(orangeSapling));
		
		//Dead
		OreDictionary.registerOre("woodLog", new ItemStack(deadWood));
		OreDictionary.registerOre("treeLeaves", new ItemStack(deadLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(brownSapling));
		
		//Apple
		OreDictionary.registerOre("treeLeaves", new ItemStack(appleLeaves));
		OreDictionary.registerOre("treeLeaves", new ItemStack(appleLeavesFruitless));
		OreDictionary.registerOre("treeSapling", new ItemStack(appleSapling));
		
		//Origin
		OreDictionary.registerOre("treeLeaves", new ItemStack(originLeaves));
		OreDictionary.registerOre("treeSapling", new ItemStack(originSapling));
		
		//Other
		OreDictionary.registerOre("treeLeaves", new ItemStack(bambooLeaves));
		
		GameRegistry.addRecipe(new ItemStack(redRockBrick, 4), new Object[] {"RR", "RR", 'R', redRock});

		GameRegistry.addSmelting(Block.dirt.blockID, new ItemStack(driedDirt, 1), 0F);
		GameRegistry.addSmelting(redRockCobble.blockID, new ItemStack(redRock, 1), 0.1F);
		GameRegistry.addSmelting(tinyCactus.blockID, new ItemStack(Item.dyePowder, 1, 2), 0.2F);
		
		//Block tool strength, 0 is Wood and Gold, 1 is Stone, 2 is Iron and 3 is Diamond
		//Leaves can be obtained from using shears, however they arn't instantly broken by them (unsure how to do this)

		MinecraftForge.setBlockHarvestLevel(smolderingGrass, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(mud, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(ash, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(originGrass, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(hardSand, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(holyGrass, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(quicksand, "shovel", 0);

		MinecraftForge.setBlockHarvestLevel(driedDirt, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(amethystOre, "pickaxe", 3);		
		MinecraftForge.setBlockHarvestLevel(amethystBlock, "pickaxe", 3);
		
		MinecraftForge.setBlockHarvestLevel(acaciaLeaves, "shears", 0);
		
		// Add block names
		LanguageRegistry.addName(mud, "Mud");
		LanguageRegistry.addName(driedDirt, "Dried Dirt");
		LanguageRegistry.addName(redRock, "Red Rock");
		LanguageRegistry.addName(ash, "Ash Block");
		LanguageRegistry.addName(deadGrass, "Dead Grass");
		LanguageRegistry.addName(desertGrass, "Desert Grass");
		LanguageRegistry.addName(whiteFlower, "Anenome");
		LanguageRegistry.addName(blueFlower, "Swampflower");
		LanguageRegistry.addName(purpleFlower, "Wildflower");
		LanguageRegistry.addName(orangeFlower, "Daisy");
		LanguageRegistry.addName(tinyFlower, "Clover");
		LanguageRegistry.addName(glowFlower, "Glowflower");
		LanguageRegistry.addName(cattail, "Cattail");
		LanguageRegistry.addName(willow, "Willow");
		LanguageRegistry.addName(autumnLeaves, "Autumn Leaves");
		LanguageRegistry.addName(thorn, "Thorns");
		LanguageRegistry.addName(toadstool, "Toadstool");
		LanguageRegistry.addName(highGrassBottom, "High Grass");
		LanguageRegistry.addName(highGrassTop, "High Grass");
		LanguageRegistry.addName(ashStone, "Ashy Stone");
		LanguageRegistry.addName(hardIce, "Hard Ice");
		LanguageRegistry.addName(redLeaves, "Maple Leaves");
		LanguageRegistry.addName(orangeLeaves, "Autumn Leaves");
		LanguageRegistry.addName(pinkLeaves, "Cherry Leaves");
		LanguageRegistry.addName(blueLeaves, "Magic Leaves");
		LanguageRegistry.addName(whiteLeaves, "Cherry Leaves");
		LanguageRegistry.addName(deadLeaves, "Dying Leaves");
		LanguageRegistry.addName(shortGrass, "Short Grass");
		LanguageRegistry.addName(appleLeaves, "Apple Leaves");
		LanguageRegistry.addName(sprout, "Sprout");
		LanguageRegistry.addName(bush, "Bush");
		LanguageRegistry.addName(bamboo, "Bamboo");
		LanguageRegistry.addName(bambooLeaves, "Bamboo Leaves");
		LanguageRegistry.addName(mudBrickBlock, "Mud Bricks");
		LanguageRegistry.addName(mudBrickSingleSlab, "Mud Bricks Slab");
		LanguageRegistry.addName(mudBrickDoubleSlab, "Mud Bricks Slab");
		LanguageRegistry.addName(mudBrickStairs, "Mud Brick Stairs");
		LanguageRegistry.addName(originGrass, "Origin Grass");
		LanguageRegistry.addName(originLeaves, "Origin Leaves");
		LanguageRegistry.addName(pinkFlower, "Tulip");
		LanguageRegistry.addName(treeMoss, "Tree Moss");
		LanguageRegistry.addName(deadWood, "Dead Wood");
		LanguageRegistry.addName(appleLeavesFruitless, "Apple Leaves");
		LanguageRegistry.addName(barley, "Barley");
		LanguageRegistry.addName(giantFlowerStem, "Giant Flower Stem");
		LanguageRegistry.addName(giantFlowerRed, "Giant Red Flower");
		LanguageRegistry.addName(giantFlowerYellow, "Giant Yellow Flower");
		LanguageRegistry.addName(tinyCactus, "Tiny Cactus");
		LanguageRegistry.addName(firSapling, "Fir Sapling");
		LanguageRegistry.addName(redwoodSapling, "Redwood Sapling");
		LanguageRegistry.addName(palmSapling, "Palm Sapling");
		LanguageRegistry.addName(redSapling, "Maple Sapling");
		LanguageRegistry.addName(orangeSapling, "Autumn Sapling");
		LanguageRegistry.addName(yellowSapling, "Autumn Sapling");
		LanguageRegistry.addName(brownSapling, "Dying Sapling");
		LanguageRegistry.addName(willowSapling, "Willow Sapling");
		LanguageRegistry.addName(appleSapling, "Apple Sapling");
		LanguageRegistry.addName(originSapling, "Origin Sapling");
		LanguageRegistry.addName(pinkSapling, "Cherry Sapling");
		LanguageRegistry.addName(whiteSapling, "Cherry Sapling");
		LanguageRegistry.addName(darkSapling, "Dark Sapling");
		LanguageRegistry.addName(magicSapling, "Magic Sapling");
		LanguageRegistry.addName(deathbloom, "Deathbloom");
		LanguageRegistry.addName(redRockCobble, "Red Rock Cobblestone");
		LanguageRegistry.addName(redRockCobbleSingleSlab, "Red Rock Cobblestone Slab");
		LanguageRegistry.addName(redRockCobbleDoubleSlab, "Red Rock Cobblestone Slab");
		LanguageRegistry.addName(redRockCobbleStairs, "Red Rock Cobblestone Stairs");
		LanguageRegistry.addName(redRockBrick, "Red Rock Bricks");
		LanguageRegistry.addName(redRockBrickSingleSlab, "Red Rock Bricks Slab");
		LanguageRegistry.addName(redRockBrickDoubleSlab, "Red Rock Bricks Slab");
		LanguageRegistry.addName(redRockBrickStairs, "Red Rock Brick Stairs");
		LanguageRegistry.addName(hydrangea, "Hydrangea");
		LanguageRegistry.addName(violet, "Violet");
		LanguageRegistry.addName(mediumGrass, "Medium Grass");
		LanguageRegistry.addName(duneGrass, "Dune Grass");
		LanguageRegistry.addName(desertSprouts, "Desert Sprouts");
		LanguageRegistry.addName(mangroveSapling, "Mangrove Sapling");
		LanguageRegistry.addName(hardSand, "Hard Sand");
		LanguageRegistry.addName(acaciaSapling, "Acacia Sapling");
		LanguageRegistry.addName(hardDirt, "Hard Dirt");
		LanguageRegistry.addName(holyGrass, "Holy Grass");
		LanguageRegistry.addName(holyStone, "Holy Stone");
		LanguageRegistry.addName(holyTallGrass, "Holy Tall Grass");
		LanguageRegistry.addName(promisedPortal, "Promised Land Portal");
		LanguageRegistry.addName(holySapling, "Holy Sapling");
		LanguageRegistry.addName(amethystOre, "Amethyst Ore");
		LanguageRegistry.addName(amethystBlock, "Block of Amethyst");
		LanguageRegistry.addName(bambooThatching, "Bamboo Thatching");
		LanguageRegistry.addName(moss, "Moss");
		LanguageRegistry.addName(algae, "Algae");
		LanguageRegistry.addName(smolderingGrass, "Smoldering Grass");
		LanguageRegistry.addName(cragRock, "Crag Rock");
		LanguageRegistry.addName(quicksand, "Quicksand");
		LanguageRegistry.addName(bambooSapling, "Bamboo Sapling");

		LanguageRegistry.addName(redwoodPlank, "Redwood Wood Planks");
		LanguageRegistry.addName(redwoodWood, "Redwood Wood");
		LanguageRegistry.addName(redwoodLeaves, "Redwood Leaves");
		LanguageRegistry.addName(redwoodSingleSlab, "Redwood Wood Slab");
		LanguageRegistry.addName(redwoodDoubleSlab, "Redwood Wood Slab");
		LanguageRegistry.addName(redwoodStairs, "Redwood Wood Stairs");
		LanguageRegistry.addName(willowPlank, "Willow Wood Planks");
		LanguageRegistry.addName(willowWood, "Willow Wood");
		LanguageRegistry.addName(willowLeaves, "Willow Leaves");
		LanguageRegistry.addName(willowSingleSlab, "Willow Wood Slab");
		LanguageRegistry.addName(willowDoubleSlab, "Willow Wood Slab");
		LanguageRegistry.addName(willowStairs, "Willow Wood Stairs");
		LanguageRegistry.addName(firPlank, "Fir Wood Planks");
		LanguageRegistry.addName(firWood, "Fir Wood");
		LanguageRegistry.addName(firLeaves, "Fir Leaves");
		LanguageRegistry.addName(firSingleSlab, "Fir Wood Slab");
		LanguageRegistry.addName(firDoubleSlab, "Fir Wood Slab");
		LanguageRegistry.addName(firStairs, "Fir Wood Stairs");
		LanguageRegistry.addName(acaciaPlank, "Acacia Wood Planks");
		LanguageRegistry.addName(acaciaWood, "Acacia Wood");
		LanguageRegistry.addName(acaciaLeaves, "Acacia Leaves");
		LanguageRegistry.addName(acaciaSingleSlab, "Acacia Wood Slab");
		LanguageRegistry.addName(acaciaDoubleSlab, "Acacia Wood Slab");
		LanguageRegistry.addName(acaciaStairs, "Acacia Wood Stairs");
		LanguageRegistry.addName(cherryPlank, "Cherry Wood Planks");
		LanguageRegistry.addName(cherryWood, "Cherry Wood");
		LanguageRegistry.addName(cherrySingleSlab, "Cherry Wood Slab");
		LanguageRegistry.addName(cherryDoubleSlab, "Cherry Wood Slab");
		LanguageRegistry.addName(cherryStairs, "Cherry Wood Stairs");
		LanguageRegistry.addName(darkPlank, "Dark Wood Planks");
		LanguageRegistry.addName(darkWood, "Dark Wood");
		LanguageRegistry.addName(darkLeaves, "Dark Leaves");
		LanguageRegistry.addName(darkSingleSlab, "Dark Wood Slab");
		LanguageRegistry.addName(darkDoubleSlab, "Dark Wood Slab");
		LanguageRegistry.addName(darkStairs, "Dark Wood Stairs");
		LanguageRegistry.addName(magicPlank, "Magic Wood Planks");
		LanguageRegistry.addName(magicWood, "Magic Wood");
		LanguageRegistry.addName(magicSingleSlab, "Magic Wood Slab");
		LanguageRegistry.addName(magicDoubleSlab, "Magic Wood Slab");
		LanguageRegistry.addName(magicStairs, "Magic Wood Stairs");
		LanguageRegistry.addName(palmPlank, "Palm Wood Planks");
		LanguageRegistry.addName(palmWood, "Palm Wood");
		LanguageRegistry.addName(palmLeaves, "Palm Leaves");
		LanguageRegistry.addName(palmSingleSlab, "Palm Wood Slab");
		LanguageRegistry.addName(palmDoubleSlab, "Palm Wood Slab");
		LanguageRegistry.addName(palmStairs, "Palm Wood Stairs");
		LanguageRegistry.addName(mangrovePlank, "Mangrove Wood Planks");
		LanguageRegistry.addName(mangroveWood, "Mangrove Wood");
		LanguageRegistry.addName(mangroveLeaves, "Mangrove Leaves");
		LanguageRegistry.addName(mangroveSingleSlab, "Mangrove Wood Slab");
		LanguageRegistry.addName(mangroveDoubleSlab, "Mangrove Wood Slab");
		LanguageRegistry.addName(mangroveStairs, "Mangrove Wood Stairs");
		LanguageRegistry.addName(holyPlank, "Holy Wood Planks");
		LanguageRegistry.addName(holyWood, "Holy Wood");
		LanguageRegistry.addName(holyLeaves, "Holy Leaves");
		LanguageRegistry.addName(holySingleSlab, "Holy Wood Slab");
		LanguageRegistry.addName(holyDoubleSlab, "Holy Wood Slab");
		LanguageRegistry.addName(holyStairs, "Holy Wood Stairs");
	}
	
	public static void dependantinit()
	{
		//Plants
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 8), new Object[] {BOPItems.ashes});
		
		//Other
		GameRegistry.addRecipe(new ItemStack(Block.cloth, 1, 0), new Object[] {"CCC", "CCC", "CCC", 'C', BOPItems.cattailItem});
		GameRegistry.addRecipe(new ItemStack(Item.coal, 1), new Object[] {"AAA", "AAA", "AAA", 'A', BOPItems.ashes});
		GameRegistry.addRecipe(new ItemStack(mud, 1), new Object[] {"MM", "MM", 'M', BOPItems.mudBall});
		GameRegistry.addRecipe(new ItemStack(amethystBlock, 1), new Object[] {"AAA", "AAA", "AAA", 'A', BOPItems.amethyst});
		GameRegistry.addRecipe(new ItemStack(ash, 1), new Object[] {"AA", "AA", 'A', BOPItems.ashes});
		GameRegistry.addRecipe(new ItemStack(mudBrickBlock, 1), new Object[] {"MM", "MM", 'M', BOPItems.mudBrick});
		GameRegistry.addRecipe(new ItemStack(bambooThatching, 1), new Object[] {"###", "###", "###", '#', BOPItems.bambooItem});
		GameRegistry.addRecipe(new ItemStack(Block.cobblestoneMossy, 1, 0), new Object[] {"MMM", "MCM", "MMM", 'M', BOPItems.mossItem, 'C', Block.cobblestone});
		GameRegistry.addRecipe(new ItemStack(Block.stoneBrick, 1, 1), new Object[] {"MMM", "MSM", "MMM", 'M', BOPItems.mossItem, 'S', Block.stoneBrick});
	}
}
