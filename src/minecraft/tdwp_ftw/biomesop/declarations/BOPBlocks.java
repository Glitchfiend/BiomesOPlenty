package tdwp_ftw.biomesop.declarations;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.blocks.*;

public class BOPBlocks {

	// Block declaration
	public static Block mud;
	public static Block driedDirt;
	public static Block redRock;
	public static Block ash;
	public static Block deadGrass;
	public static Block desertGrass;
	public static Block whiteFlower;
	public static Block blueFlower;
	public static Block purpleFlower;
	public static Block orangeFlower;
	public static Block tinyFlower;
	public static Block glowFlower;
	public static Block cattail;
	public static Block willow;
	public static Block autumnLeaves;
	public static Block thorn;
	public static Block toadstool;
	public static BlockHighGrassBottom highGrassBottom;
	public static BlockHighGrassTop highGrassTop;
	public static Block ashStone;
	public static Block hardIce;
	public static Block redLeaves;
	public static Block orangeLeaves;
	public static Block pinkLeaves;
	public static Block blueLeaves;
	public static Block whiteLeaves;
	public static Block deadLeaves;
	public static BlockShortGrass shortGrass;
	public static Block appleLeaves;
	public static BlockSprout sprout;
	public static BlockBush bush;
	public static Block bamboo;
	public static Block bambooLeaves;
	public static Block mudBrickBlock;
	public static BlockHalfSlab mudBrickDoubleSlab;
	public static BlockHalfSlab mudBrickSingleSlab;
	public static Block mudBrickStairs;
	public static Block originGrass;
	public static Block originLeaves;
	public static Block pinkFlower;
	public static Block treeMoss;
	public static Block deadWood;
	public static Block appleLeavesFruitless;
	public static Block barley;
	public static Block giantFlowerStem;
	public static Block giantFlowerRed;
	public static Block giantFlowerYellow;
	public static Block tinyCactus;
	public static Block firSapling;
	public static Block redwoodSapling;
	public static Block palmSapling;
	public static Block redSapling;
	public static Block orangeSapling;
	public static Block yellowSapling;
	public static Block brownSapling;
	public static Block willowSapling;
	public static Block appleSapling;
	public static Block originSapling;
	public static Block pinkSapling;
	public static Block whiteSapling;
	public static Block darkSapling;
	public static Block magicSapling;
	public static Block deathbloom;
	public static Block redRockCobble;
	public static BlockHalfSlab redRockCobbleDoubleSlab;
	public static BlockHalfSlab redRockCobbleSingleSlab;
	public static Block redRockCobbleStairs;
	public static Block redRockBrick;
	public static BlockHalfSlab redRockBrickDoubleSlab;
	public static BlockHalfSlab redRockBrickSingleSlab;
	public static Block redRockBrickStairs;
	public static Block hydrangea;
	public static Block violet;
	public static BlockMediumGrass mediumGrass;
	public static Block duneGrass;
	public static Block desertSprouts;
	public static Block mangroveSapling;
	public static Block hardSand;
	public static Block acaciaSapling;
	public static Block hardDirt;
	public static Block holyGrass;
	public static Block holyStone;
	public static Block holyTallGrass;
	public static Block promisedPortal;
	public static Block holySapling;
	public static Block amethystOre;
	public static Block amethystBlock;
	public static Block bambooThatching;
	public static Block moss;
	public static Block algae;
	public static Block smolderingGrass;
	public static Block cragRock;
	public static Block quicksand;

	//Redwood
	public static Block redwoodPlank;
	public static Block redwoodWood;
	public static Block redwoodLeaves;
	public static BlockHalfSlab redwoodDoubleSlab;
	public static BlockHalfSlab redwoodSingleSlab;
	public static Block redwoodStairs;

	//Willow
	public static Block willowPlank;
	public static Block willowWood;
	public static Block willowLeaves;
	public static BlockHalfSlab willowDoubleSlab;
	public static BlockHalfSlab willowSingleSlab;
	public static Block willowStairs;

	//Fir
	public static Block firPlank;
	public static Block firWood;
	public static Block firLeaves;
	public static BlockHalfSlab firDoubleSlab;
	public static BlockHalfSlab firSingleSlab;
	public static Block firStairs;

	//Acacia
	public static Block acaciaPlank;
	public static Block acaciaWood;
	public static Block acaciaLeaves;
	public static BlockHalfSlab acaciaDoubleSlab;
	public static BlockHalfSlab acaciaSingleSlab;
	public static Block acaciaStairs;

	//Cherry
	public static Block cherryPlank;
	public static Block cherryWood;
	public static BlockHalfSlab cherryDoubleSlab;
	public static BlockHalfSlab cherrySingleSlab;
	public static Block cherryStairs;

	//Dark
	public static Block darkPlank;
	public static Block darkWood;
	public static Block darkLeaves;
	public static BlockHalfSlab darkDoubleSlab;
	public static BlockHalfSlab darkSingleSlab;
	public static Block darkStairs;

	//Magic
	public static Block magicPlank;
	public static Block magicWood;
	public static BlockHalfSlab magicDoubleSlab;
	public static BlockHalfSlab magicSingleSlab;
	public static Block magicStairs;

	//Palm
	public static Block palmPlank;
	public static Block palmWood;
	public static Block palmLeaves;
	public static BlockHalfSlab palmDoubleSlab;
	public static BlockHalfSlab palmSingleSlab;
	public static Block palmStairs;

	//Mangrove
	public static Block mangrovePlank;
	public static Block mangroveWood;
	public static Block mangroveLeaves;
	public static BlockHalfSlab mangroveDoubleSlab;
	public static BlockHalfSlab mangroveSingleSlab;
	public static Block mangroveStairs;

	//Holy
	public static Block holyPlank;
	public static Block holyWood;
	public static Block holyLeaves;
	public static BlockHalfSlab holyDoubleSlab;
	public static BlockHalfSlab holySingleSlab;
	public static Block holyStairs;
	
	public static void init()
	{
		// Block declaration
		mud = (new BlockMud(mod_BiomesOPlenty.mudID)).setHardness(0.6F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("mud");
		driedDirt = (new BlockDriedDirt(mod_BiomesOPlenty.driedDirtID)).setHardness(0.1F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("driedDirt");
		redRock = (new BlockRedRock(mod_BiomesOPlenty.redRockID)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRock");
		ash = (new BlockAsh(mod_BiomesOPlenty.ashID)).setHardness(0.4F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("ash");
		deadGrass = (new BlockDeadGrass(mod_BiomesOPlenty.deadGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("deadGrass");
		desertGrass = (new BlockDesertGrass(mod_BiomesOPlenty.desertGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("desertGrass");
		whiteFlower = (new BlockWhiteFlower(mod_BiomesOPlenty.whiteFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("whiteFlower");
		blueFlower = (new BlockBlueFlower(mod_BiomesOPlenty.blueFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("blueFlower");
		purpleFlower = (new BlockPurpleFlower(mod_BiomesOPlenty.purpleFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("purpleFlower");
		orangeFlower = (new BlockOrangeFlower(mod_BiomesOPlenty.orangeFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("orangeFlower");
		tinyFlower = (new BlockTinyFlower(mod_BiomesOPlenty.tinyFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("tinyFlower");
		glowFlower = (new BlockGlowFlower(mod_BiomesOPlenty.glowFlowerID)).setHardness(0.0F).setLightValue(0.65F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("glowFlower");
		cattail = (new BlockCattail(mod_BiomesOPlenty.cattailID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("cattail");
		willow = (new BlockWillow(mod_BiomesOPlenty.willowID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("willow");
		autumnLeaves = (BlockAutumnLeaves)(new BlockAutumnLeaves(mod_BiomesOPlenty.autumnLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("autumnLeaves");
		thorn = (new BlockThorn(mod_BiomesOPlenty.thornID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("thorn");
		toadstool = (new BlockToadstool(mod_BiomesOPlenty.toadstoolID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("toadstool");
		highGrassBottom = (BlockHighGrassBottom)(new BlockHighGrassBottom(mod_BiomesOPlenty.highGrassBottomID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("highGrassBottom");
		highGrassTop = (BlockHighGrassTop)(new BlockHighGrassTop(mod_BiomesOPlenty.highGrassTopID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("highGrassTop");
		ashStone = (new BlockAshStone(mod_BiomesOPlenty.ashStoneID)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ashStone");
		hardIce = (new BlockHardIce(mod_BiomesOPlenty.hardIceID)).setHardness(0.75F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("hardIce");
		redLeaves = (BlockRedLeaves)(new BlockRedLeaves(mod_BiomesOPlenty.redLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redLeaves");
		orangeLeaves = (BlockOrangeLeaves)(new BlockOrangeLeaves(mod_BiomesOPlenty.orangeLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("orangeLeaves");
		pinkLeaves = (BlockPinkLeaves)(new BlockPinkLeaves(mod_BiomesOPlenty.pinkLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("pinkLeaves");
		blueLeaves = (BlockBlueLeaves)(new BlockBlueLeaves(mod_BiomesOPlenty.blueLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("blueLeaves");
		whiteLeaves = (BlockWhiteLeaves)(new BlockWhiteLeaves(mod_BiomesOPlenty.whiteLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("whiteLeaves");
		deadLeaves = (BlockDeadLeaves)(new BlockDeadLeaves(mod_BiomesOPlenty.deadLeavesID)).setHardness(0.1F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("deadLeaves");
		shortGrass = (BlockShortGrass)(new BlockShortGrass(mod_BiomesOPlenty.shortGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("shortGrass");
		appleLeaves = (BlockAppleLeaves)(new BlockAppleLeaves(mod_BiomesOPlenty.appleLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("appleLeaves");
		sprout = (BlockSprout)(new BlockSprout(mod_BiomesOPlenty.sproutID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("sprout");
		bush = (BlockBush)(new BlockBush(mod_BiomesOPlenty.bushID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bush");
		bamboo = new BlockBamboo(mod_BiomesOPlenty.bambooID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bamboo");
		bambooLeaves = (BlockBambooLeaves)(new BlockBambooLeaves(mod_BiomesOPlenty.bambooLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("bambooLeaves");
		mudBrickBlock = (new BlockMudBrick(mod_BiomesOPlenty.mudBrickBlockID)).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mudBrickBlock");
		originGrass = (BlockOriginGrass)(new BlockOriginGrass(mod_BiomesOPlenty.originGrassID)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("originGrass");
		originLeaves = (BlockOriginLeaves)(new BlockOriginLeaves(mod_BiomesOPlenty.originLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("originLeaves");
		pinkFlower = (new BlockPinkFlower(mod_BiomesOPlenty.pinkFlowerID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("pinkFlower");
		treeMoss = (new BlockTreeMoss(mod_BiomesOPlenty.treeMossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("treeMoss");
		deadWood = (new BlockDeadLog(mod_BiomesOPlenty.deadWoodID)).setHardness(0.8F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("deadWood");
		appleLeavesFruitless = (BlockAppleLeavesFruitless)(new BlockAppleLeavesFruitless(mod_BiomesOPlenty.appleLeavesFruitlessID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("appleLeavesFruitless");
		barley = (new BlockBarley(mod_BiomesOPlenty.barleyID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("barley");
		giantFlowerStem = (new BlockGiantFlowerStem(mod_BiomesOPlenty.giantFlowerStemID)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("giantFlowerStem");
		giantFlowerRed = (BlockGiantFlowerRed)(new BlockGiantFlowerRed(mod_BiomesOPlenty.giantFlowerRedID)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("giantFlowerRed");
		giantFlowerYellow = (BlockGiantFlowerYellow)(new BlockGiantFlowerYellow(mod_BiomesOPlenty.giantFlowerYellowID)).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("giantFlowerYellow");
		tinyCactus = (new BlockTinyCactus(mod_BiomesOPlenty.tinyCactusID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("tinyCactus");
		firSapling = (new BlockFirSapling(mod_BiomesOPlenty.firSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("firSapling");
		redwoodSapling = (new BlockRedwoodSapling(mod_BiomesOPlenty.redwoodSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redwoodSapling");
		palmSapling = (new BlockPalmSapling(mod_BiomesOPlenty.palmSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("palmSapling");
		redSapling = (new BlockRedSapling(mod_BiomesOPlenty.redSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redSapling");
		orangeSapling = (new BlockOrangeSapling(mod_BiomesOPlenty.orangeSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("orangeSapling");
		yellowSapling = (new BlockYellowSapling(mod_BiomesOPlenty.yellowSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("yellowSapling");
		brownSapling = (new BlockBrownSapling(mod_BiomesOPlenty.brownSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("brownSapling");
		willowSapling = (new BlockWillowSapling(mod_BiomesOPlenty.willowSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("willowSapling");
		appleSapling = (new BlockAppleSapling(mod_BiomesOPlenty.appleSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("appleSapling");
		originSapling = (new BlockOriginSapling(mod_BiomesOPlenty.originSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("originSapling");
		pinkSapling = (new BlockPinkSapling(mod_BiomesOPlenty.pinkSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("pinkSapling");
		whiteSapling = (new BlockWhiteSapling(mod_BiomesOPlenty.whiteSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("whiteSapling");
		darkSapling = (new BlockDarkSapling(mod_BiomesOPlenty.darkSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("darkSapling");
		magicSapling = (new BlockMagicSapling(mod_BiomesOPlenty.magicSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("magicSapling");
		deathbloom = (new BlockDeathbloom(mod_BiomesOPlenty.deathbloomID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("deathbloom");
		redRockCobble = (new BlockRedRockCobble(mod_BiomesOPlenty.redRockCobbleID)).setHardness(1.6F).setResistance(7.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockCobble");
		redRockBrick = (new BlockRedRockBrick(mod_BiomesOPlenty.redRockBrickID)).setHardness(1.1F).setResistance(7.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockBrick");
		hydrangea = (new BlockHydrangea(mod_BiomesOPlenty.hydrangeaID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("hydrangea");
		violet = (new BlockViolet(mod_BiomesOPlenty.violetID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("violet");
		mediumGrass = (BlockMediumGrass)(new BlockMediumGrass(mod_BiomesOPlenty.mediumGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("mediumGrass");
		duneGrass = (new BlockDuneGrass(mod_BiomesOPlenty.duneGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("duneGrass");
		desertSprouts = (new BlockDesertSprouts(mod_BiomesOPlenty.desertSproutsID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("desertSprouts");
		redRockCobbleDoubleSlab = (BlockHalfSlab)(new BlockRedRockCobbleSlab(mod_BiomesOPlenty.redRockCobbleDoubleSlabID, true)).setHardness(1.6F).setResistance(7.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockCobbleSlab");
		redRockCobbleSingleSlab = (BlockHalfSlab)(new BlockRedRockCobbleSlab(mod_BiomesOPlenty.redRockCobbleSingleSlabID, false)).setHardness(1.6F).setResistance(7.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockCobbleSlab");
		redRockCobbleStairs = (new BlockRedRockCobbleStairs(mod_BiomesOPlenty.redRockCobbleStairsID, redRockCobble)).setUnlocalizedName("redRockCobbleStairs");
		redRockBrickDoubleSlab = (BlockHalfSlab)(new BlockRedRockBrickSlab(mod_BiomesOPlenty.redRockBrickDoubleSlabID, true)).setHardness(1.1F).setResistance(7.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockBrickSlab");
		redRockBrickSingleSlab = (BlockHalfSlab)(new BlockRedRockBrickSlab(mod_BiomesOPlenty.redRockBrickSingleSlabID, false)).setHardness(1.1F).setResistance(7.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("redRockBrickSlab");
		redRockBrickStairs = (new BlockRedRockBrickStairs(mod_BiomesOPlenty.redRockBrickStairsID, redRockBrick)).setUnlocalizedName("redRockBrickStairs");					
		mudBrickDoubleSlab = (BlockHalfSlab)(new BlockMudBrickSlab(mod_BiomesOPlenty.mudBrickDoubleSlabID, true)).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mudBrickSlab");
		mudBrickSingleSlab = (BlockHalfSlab)(new BlockMudBrickSlab(mod_BiomesOPlenty.mudBrickSingleSlabID, false)).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mudBrickSlab");
		mudBrickStairs = (new BlockMudBrickStairs(mod_BiomesOPlenty.mudBrickStairsID, mudBrickBlock)).setUnlocalizedName("mudBrickStairs");
		mangroveSapling = (new BlockMangroveSapling(mod_BiomesOPlenty.mangroveSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("mangroveSapling");
		hardSand = (new BlockHardSand(mod_BiomesOPlenty.hardSandID)).setHardness(0.7F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("hardSand");
		acaciaSapling = (new BlockAcaciaSapling(mod_BiomesOPlenty.acaciaSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("acaciaSapling");
		hardDirt = (new BlockHardDirt(mod_BiomesOPlenty.hardDirtID)).setHardness(0.9F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("hardDirt");
		holyGrass = (BlockHolyGrass)(new BlockHolyGrass(mod_BiomesOPlenty.holyGrassID)).setHardness(1.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holyGrass");
		holyStone = (new BlockHolyStone(mod_BiomesOPlenty.holyStoneID)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("holyStone");
		holyTallGrass = (new BlockHolyTallGrass(mod_BiomesOPlenty.holyTallGrassID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holyTallGrass");
		promisedPortal = new BlockPromisedPortal(mod_BiomesOPlenty.promisedLandPortalID).setUnlocalizedName("promisedPortal").setBlockUnbreakable().setResistance(6000000.0F).setLightValue(1.0F);	
		holySapling = (new BlockHolySapling(mod_BiomesOPlenty.holySaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holySapling");
		amethystOre = (new BlockAmethystOre(mod_BiomesOPlenty.amethystOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("amethystOre");
		amethystBlock = (new BlockAmethystBlock(mod_BiomesOPlenty.amethystBlockID)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("amethystBlock");
		bambooThatching = (new BlockBambooThatching(mod_BiomesOPlenty.bambooThatchingID)).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bambooThatching");
		moss = (new BlockMoss(mod_BiomesOPlenty.mossID)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("moss");
		algae = (new BlockAlgae(mod_BiomesOPlenty.algaeID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("algae");
		smolderingGrass = (BlockSmolderingGrass)(new BlockSmolderingGrass(mod_BiomesOPlenty.smolderingGrassID)).setHardness(0.6F).setLightValue(0.25F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("smolderingGrass");
		cragRock = (new BlockCragRock(mod_BiomesOPlenty.cragRockID)).setHardness(1.0F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("cragRock");
		quicksand = (new BlockQuicksand(mod_BiomesOPlenty.quicksandID)).setHardness(0.3F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("quicksand");

		//Redwood
		redwoodPlank = (new BlockRedwoodPlank(mod_BiomesOPlenty.redwoodPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodPlank");
		redwoodWood = (new BlockRedwoodLog(mod_BiomesOPlenty.redwoodWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodWood");
		redwoodLeaves = (BlockRedwoodLeaves)(new BlockRedwoodLeaves(mod_BiomesOPlenty.redwoodLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("redwoodLeaves");
		redwoodDoubleSlab = (BlockHalfSlab)(new BlockRedwoodSlab(mod_BiomesOPlenty.redwoodDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodSlab");
		redwoodSingleSlab = (BlockHalfSlab)(new BlockRedwoodSlab(mod_BiomesOPlenty.redwoodSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("redwoodSlab");
		redwoodStairs = (new BlockRedwoodStairs(mod_BiomesOPlenty.redwoodStairsID, redwoodPlank)).setUnlocalizedName("redwoodStairs");

		//Willow
		willowPlank = (new BlockWillowPlank(mod_BiomesOPlenty.willowPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowPlank");
		willowWood = (new BlockWillowLog(mod_BiomesOPlenty.willowWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowWood");
		willowLeaves = (BlockWillowLeaves)(new BlockWillowLeaves(mod_BiomesOPlenty.willowLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("willowLeaves");
		willowDoubleSlab = (BlockHalfSlab)(new BlockWillowSlab(mod_BiomesOPlenty.willowDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowSlab");
		willowSingleSlab = (BlockHalfSlab)(new BlockWillowSlab(mod_BiomesOPlenty.willowSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("willowSlab");
		willowStairs = (new BlockWillowStairs(mod_BiomesOPlenty.willowStairsID, willowPlank)).setUnlocalizedName("willowStairs");

		//Fir
		firPlank = (new BlockFirPlank(mod_BiomesOPlenty.firPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firPlank");
		firWood = (new BlockFirLog(mod_BiomesOPlenty.firWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firWood");
		firLeaves = (BlockFirLeaves)(new BlockFirLeaves(mod_BiomesOPlenty.firLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("firLeaves");
		firDoubleSlab = (BlockHalfSlab)(new BlockFirSlab(mod_BiomesOPlenty.firDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firSlab");
		firSingleSlab = (BlockHalfSlab)(new BlockFirSlab(mod_BiomesOPlenty.firSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("firSlab");
		firStairs = (new BlockFirStairs(mod_BiomesOPlenty.firStairsID, firPlank)).setUnlocalizedName("firStairs");

		//Acacia
		acaciaPlank = (new BlockAcaciaPlank(mod_BiomesOPlenty.acaciaPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaPlank");
		acaciaWood = (new BlockAcaciaLog(mod_BiomesOPlenty.acaciaWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaWood");
		acaciaLeaves = (BlockAcaciaLeaves)(new BlockAcaciaLeaves(mod_BiomesOPlenty.acaciaLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("acaciaLeaves");
		acaciaDoubleSlab = (BlockHalfSlab)(new BlockAcaciaSlab(mod_BiomesOPlenty.acaciaDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaSlab");
		acaciaSingleSlab = (BlockHalfSlab)(new BlockAcaciaSlab(mod_BiomesOPlenty.acaciaSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("acaciaSlab");
		acaciaStairs = (new BlockAcaciaStairs(mod_BiomesOPlenty.acaciaStairsID, acaciaPlank)).setUnlocalizedName("acaciaStairs");

		//Cherry
		cherryPlank = (new BlockCherryPlank(mod_BiomesOPlenty.cherryPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherryPlank");
		cherryWood = (new BlockCherryLog(mod_BiomesOPlenty.cherryWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherryWood");
		cherryDoubleSlab = (BlockHalfSlab)(new BlockCherrySlab(mod_BiomesOPlenty.cherryDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherrySlab");
		cherrySingleSlab = (BlockHalfSlab)(new BlockCherrySlab(mod_BiomesOPlenty.cherrySingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherrySlab");
		cherryStairs = (new BlockCherryStairs(mod_BiomesOPlenty.cherryStairsID, cherryPlank)).setUnlocalizedName("cherryStairs");

		//Dark
		darkPlank = (new BlockDarkPlank(mod_BiomesOPlenty.darkPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkPlank");
		darkWood = (new BlockDarkLog(mod_BiomesOPlenty.darkWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkWood");
		darkLeaves = (BlockDarkLeaves)(new BlockDarkLeaves(mod_BiomesOPlenty.darkLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("darkLeaves");
		darkDoubleSlab = (BlockHalfSlab)(new BlockDarkSlab(mod_BiomesOPlenty.darkDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkSlab");
		darkSingleSlab = (BlockHalfSlab)(new BlockDarkSlab(mod_BiomesOPlenty.darkSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("darkSlab");
		darkStairs = (new BlockDarkStairs(mod_BiomesOPlenty.darkStairsID, darkPlank)).setUnlocalizedName("darkStairs");

		//Magic
		magicPlank = (new BlockMagicPlank(mod_BiomesOPlenty.magicPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicPlank");
		magicWood = (new BlockMagicLog(mod_BiomesOPlenty.magicWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicWood");
		magicDoubleSlab = (BlockHalfSlab)(new BlockMagicSlab(mod_BiomesOPlenty.magicDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicSlab");
		magicSingleSlab = (BlockHalfSlab)(new BlockMagicSlab(mod_BiomesOPlenty.magicSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("magicSlab");
		magicStairs = (new BlockMagicStairs(mod_BiomesOPlenty.magicStairsID, magicPlank)).setUnlocalizedName("magicStairs");

		//Palm
		palmPlank = (new BlockPalmPlank(mod_BiomesOPlenty.palmPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmPlank");
		palmWood = (new BlockPalmLog(mod_BiomesOPlenty.palmWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmWood");
		palmLeaves = (BlockPalmLeaves)(new BlockPalmLeaves(mod_BiomesOPlenty.palmLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("palmLeaves");
		palmDoubleSlab = (BlockHalfSlab)(new BlockPalmSlab(mod_BiomesOPlenty.palmDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmSlab");
		palmSingleSlab = (BlockHalfSlab)(new BlockPalmSlab(mod_BiomesOPlenty.palmSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("palmSlab");
		palmStairs = (new BlockPalmStairs(mod_BiomesOPlenty.palmStairsID, palmPlank)).setUnlocalizedName("palmStairs");

		//Mangrove
		mangrovePlank = (new BlockMangrovePlank(mod_BiomesOPlenty.mangrovePlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangrovePlank");
		mangroveWood = (new BlockMangroveLog(mod_BiomesOPlenty.mangroveWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangroveWood");
		mangroveLeaves = (BlockMangroveLeaves)(new BlockMangroveLeaves(mod_BiomesOPlenty.mangroveLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("mangroveLeaves");
		mangroveDoubleSlab = (BlockHalfSlab)(new BlockMangroveSlab(mod_BiomesOPlenty.mangroveDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangroveSlab");
		mangroveSingleSlab = (BlockHalfSlab)(new BlockMangroveSlab(mod_BiomesOPlenty.mangroveSingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mangroveSlab");
		mangroveStairs = (new BlockMangroveStairs(mod_BiomesOPlenty.mangroveStairsID, mangrovePlank)).setUnlocalizedName("mangroveStairs");

		//Holy
		holyPlank = (new BlockHolyPlank(mod_BiomesOPlenty.holyPlankID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holyPlank");
		holyWood = (new BlockHolyLog(mod_BiomesOPlenty.holyWoodID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holyWood");
		holyLeaves = (BlockHolyLeaves)(new BlockHolyLeaves(mod_BiomesOPlenty.holyLeavesID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("holyLeaves");
		holyDoubleSlab = (BlockHalfSlab)(new BlockHolySlab(mod_BiomesOPlenty.holyDoubleSlabID, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holySlab");
		holySingleSlab = (BlockHalfSlab)(new BlockHolySlab(mod_BiomesOPlenty.holySingleSlabID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("holySlab");
		holyStairs = (new BlockHolyStairs(mod_BiomesOPlenty.holyStairsID, holyPlank)).setUnlocalizedName("holyStairs");
		
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
		GameRegistry.registerBlock(mudBrickDoubleSlab, "mudBrickDoubleSlab");
		GameRegistry.registerBlock(mudBrickSingleSlab, "mudBrickSingleSlab");
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
		GameRegistry.registerBlock(redRockCobbleDoubleSlab, "redRockCobbleDoubleSlab");
		GameRegistry.registerBlock(redRockCobbleSingleSlab, "redRockCobbleSingleSlab");
		GameRegistry.registerBlock(redRockCobbleStairs, "redRockCobbleStairs");
		GameRegistry.registerBlock(redRockBrick, "redRockBrick");
		GameRegistry.registerBlock(redRockBrickDoubleSlab, "redRockBrickDoubleSlab");
		GameRegistry.registerBlock(redRockBrickSingleSlab, "redRockBrickSingleSlab");
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

		GameRegistry.registerBlock(redwoodPlank, "redwoodPlank");
		GameRegistry.registerBlock(redwoodWood, "redwoodWood");
		GameRegistry.registerBlock(redwoodLeaves, "redwoodLeaves");
		GameRegistry.registerBlock(redwoodDoubleSlab, "redwoodDoubleSlab");
		GameRegistry.registerBlock(redwoodSingleSlab, "redwoodSingleSlab");
		GameRegistry.registerBlock(redwoodStairs, "redwoodStairs");

		GameRegistry.registerBlock(willowPlank, "willowPlank");
		GameRegistry.registerBlock(willowWood, "willowWood");
		GameRegistry.registerBlock(willowLeaves, "willowLeaves");
		GameRegistry.registerBlock(willowDoubleSlab, "willowDoubleSlab");
		GameRegistry.registerBlock(willowSingleSlab, "willowSingleSlab");
		GameRegistry.registerBlock(willowStairs, "willowStairs");

		GameRegistry.registerBlock(firPlank, "firPlank");
		GameRegistry.registerBlock(firWood, "firWood");
		GameRegistry.registerBlock(firLeaves, "firLeaves");
		GameRegistry.registerBlock(firDoubleSlab, "firDoubleSlab");
		GameRegistry.registerBlock(firSingleSlab, "firSingleSlab");
		GameRegistry.registerBlock(firStairs, "firStairs");

		GameRegistry.registerBlock(acaciaPlank, "acaciaPlank");
		GameRegistry.registerBlock(acaciaWood, "acaciaWood");
		GameRegistry.registerBlock(acaciaLeaves, "acaciaLeaves");
		GameRegistry.registerBlock(acaciaDoubleSlab, "acaciaDoubleSlab");
		GameRegistry.registerBlock(acaciaSingleSlab, "acaciaSingleSlab");
		GameRegistry.registerBlock(acaciaStairs, "acaciaStairs");

		GameRegistry.registerBlock(cherryPlank, "cherryPlank");
		GameRegistry.registerBlock(cherryWood, "cherryWood");
		GameRegistry.registerBlock(cherryDoubleSlab, "cherryDoubleSlab");
		GameRegistry.registerBlock(cherrySingleSlab, "cherrySingleSlab");
		GameRegistry.registerBlock(cherryStairs, "cherryStairs");

		GameRegistry.registerBlock(darkPlank, "darkPlank");
		GameRegistry.registerBlock(darkWood, "darkWood");
		GameRegistry.registerBlock(darkLeaves, "darkLeaves");
		GameRegistry.registerBlock(darkDoubleSlab, "darkDoubleSlab");
		GameRegistry.registerBlock(darkSingleSlab, "darkSingleSlab");
		GameRegistry.registerBlock(darkStairs, "darkStairs");

		GameRegistry.registerBlock(magicPlank, "magicPlank");
		GameRegistry.registerBlock(magicWood, "magicWood");
		GameRegistry.registerBlock(magicDoubleSlab, "magicDoubleSlab");
		GameRegistry.registerBlock(magicSingleSlab, "magicSingleSlab");
		GameRegistry.registerBlock(magicStairs, "magicStairs");

		GameRegistry.registerBlock(palmPlank, "palmPlank");
		GameRegistry.registerBlock(palmWood, "palmWood");
		GameRegistry.registerBlock(palmLeaves, "palmLeaves");
		GameRegistry.registerBlock(palmDoubleSlab, "palmDoubleSlab");
		GameRegistry.registerBlock(palmSingleSlab, "palmSingleSlab");
		GameRegistry.registerBlock(palmStairs, "palmStairs");

		GameRegistry.registerBlock(mangrovePlank, "mangrovePlank");
		GameRegistry.registerBlock(mangroveWood, "mangroveWood");
		GameRegistry.registerBlock(mangroveLeaves, "mangroveLeaves");
		GameRegistry.registerBlock(mangroveDoubleSlab, "mangroveDoubleSlab");
		GameRegistry.registerBlock(mangroveSingleSlab, "mangroveSingleSlab");
		GameRegistry.registerBlock(mangroveStairs, "mangroveStairs");

		GameRegistry.registerBlock(holyPlank, "holyPlank");
		GameRegistry.registerBlock(holyWood, "holyWood");
		GameRegistry.registerBlock(holyLeaves, "holyLeaves");
		GameRegistry.registerBlock(holyDoubleSlab, "holyDoubleSlab");
		GameRegistry.registerBlock(holySingleSlab, "holySingleSlab");
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

		GameRegistry.addShapelessRecipe(new ItemStack(redwoodPlank, 4), new Object[] {redwoodWood});
		GameRegistry.addRecipe(new ItemStack(redwoodSingleSlab, 6), new Object[] {"RRR", 'R', redwoodPlank});
		GameRegistry.addRecipe(new ItemStack(redwoodStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', redwoodPlank});
		GameRegistry.addRecipe(new ItemStack(redwoodStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', redwoodPlank});

		//Willow
		OreDictionary.registerOre("plankWood", new ItemStack(willowPlank));
		OreDictionary.registerOre("logWood", new ItemStack(willowWood));

		GameRegistry.addShapelessRecipe(new ItemStack(willowPlank, 4), new Object[] {willowWood});
		GameRegistry.addRecipe(new ItemStack(willowSingleSlab, 6), new Object[] {"RRR", 'R', willowPlank});
		GameRegistry.addRecipe(new ItemStack(willowStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', willowPlank});
		GameRegistry.addRecipe(new ItemStack(willowStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', willowPlank});

		//Acacia
		OreDictionary.registerOre("plankWood", new ItemStack(acaciaPlank));
		OreDictionary.registerOre("logWood", new ItemStack(acaciaWood));

		GameRegistry.addShapelessRecipe(new ItemStack(acaciaPlank, 4), new Object[] {acaciaWood});
		GameRegistry.addRecipe(new ItemStack(acaciaSingleSlab, 6), new Object[] {"RRR", 'R', acaciaPlank});
		GameRegistry.addRecipe(new ItemStack(acaciaStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', acaciaPlank});
		GameRegistry.addRecipe(new ItemStack(acaciaStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', acaciaPlank});

		//Fir
		OreDictionary.registerOre("plankWood", new ItemStack(firPlank));
		OreDictionary.registerOre("logWood", new ItemStack(firWood));

		GameRegistry.addShapelessRecipe(new ItemStack(firPlank, 4), new Object[] {firWood});
		GameRegistry.addRecipe(new ItemStack(firSingleSlab, 6), new Object[] {"RRR", 'R', firPlank});
		GameRegistry.addRecipe(new ItemStack(firStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', firPlank});
		GameRegistry.addRecipe(new ItemStack(firStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', firPlank});

		//Cherry
		OreDictionary.registerOre("plankWood", new ItemStack(cherryPlank));
		OreDictionary.registerOre("logWood", new ItemStack(cherryWood));

		GameRegistry.addShapelessRecipe(new ItemStack(cherryPlank, 4), new Object[] {cherryWood});
		GameRegistry.addRecipe(new ItemStack(cherrySingleSlab, 6), new Object[] {"RRR", 'R', cherryPlank});
		GameRegistry.addRecipe(new ItemStack(cherryStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', cherryPlank});
		GameRegistry.addRecipe(new ItemStack(cherryStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', cherryPlank});

		//Dark
		OreDictionary.registerOre("plankWood", new ItemStack(darkPlank));
		OreDictionary.registerOre("logWood", new ItemStack(darkWood));

		GameRegistry.addShapelessRecipe(new ItemStack(darkPlank, 4), new Object[] {darkWood});
		GameRegistry.addRecipe(new ItemStack(darkSingleSlab, 6), new Object[] {"RRR", 'R', darkPlank});
		GameRegistry.addRecipe(new ItemStack(darkStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', darkPlank});
		GameRegistry.addRecipe(new ItemStack(darkStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', darkPlank});

		//Magic
		OreDictionary.registerOre("plankWood", new ItemStack(magicPlank));
		OreDictionary.registerOre("logWood", new ItemStack(magicWood));

		GameRegistry.addShapelessRecipe(new ItemStack(magicPlank, 4), new Object[] {magicWood});
		GameRegistry.addRecipe(new ItemStack(magicSingleSlab, 6), new Object[] {"RRR", 'R', magicPlank});
		GameRegistry.addRecipe(new ItemStack(magicStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', magicPlank});
		GameRegistry.addRecipe(new ItemStack(magicStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', magicPlank});

		//Palm
		OreDictionary.registerOre("plankWood", new ItemStack(palmPlank));
		OreDictionary.registerOre("logWood", new ItemStack(palmWood));

		GameRegistry.addShapelessRecipe(new ItemStack(palmPlank, 4), new Object[] {palmWood});
		GameRegistry.addRecipe(new ItemStack(palmSingleSlab, 6), new Object[] {"RRR", 'R', palmPlank});
		GameRegistry.addRecipe(new ItemStack(palmStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', palmPlank});
		GameRegistry.addRecipe(new ItemStack(palmStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', palmPlank});

		//Mangrove
		OreDictionary.registerOre("plankWood", new ItemStack(mangrovePlank));
		OreDictionary.registerOre("logWood", new ItemStack(mangroveWood));

		GameRegistry.addShapelessRecipe(new ItemStack(mangrovePlank, 4), new Object[] {mangroveWood});
		GameRegistry.addRecipe(new ItemStack(mangroveSingleSlab, 6), new Object[] {"RRR", 'R', mangrovePlank});
		GameRegistry.addRecipe(new ItemStack(mangroveStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', mangrovePlank});
		GameRegistry.addRecipe(new ItemStack(mangroveStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', mangrovePlank});

		//Holy
		OreDictionary.registerOre("plankWood", new ItemStack(holyPlank));
		OreDictionary.registerOre("logWood", new ItemStack(holyWood));

		GameRegistry.addShapelessRecipe(new ItemStack(holyPlank, 4), new Object[] {holyWood});
		GameRegistry.addRecipe(new ItemStack(holySingleSlab, 6), new Object[] {"RRR", 'R', holyPlank});
		GameRegistry.addRecipe(new ItemStack(holyStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', holyPlank});
		GameRegistry.addRecipe(new ItemStack(holyStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', holyPlank});

		//Other
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
		GameRegistry.addShapelessRecipe(new ItemStack(Block.cobblestoneMossy, 1), new Object[] {Block.cobblestone, BOPItems.mossItem});
		GameRegistry.addRecipe(new ItemStack(Block.cobblestoneMossy, 1, 0), new Object[] {"MMM", "MCM", "MMM", 'M', BOPItems.mossItem, 'C', Block.cobblestone});
		GameRegistry.addRecipe(new ItemStack(Block.stoneBrick, 1, 1), new Object[] {"MMM", "MSM", "MMM", 'M', BOPItems.mossItem, 'S', Block.stoneBrick});
	}
}
