package biomesoplenty.biomes;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.REED;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND_PASS2;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIRT;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRAVEL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Fluids;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.worldgen.WorldGenAlgae;
import biomesoplenty.worldgen.WorldGenAsh;
import biomesoplenty.worldgen.WorldGenBOPBush;
import biomesoplenty.worldgen.WorldGenBOPFlowers;
import biomesoplenty.worldgen.WorldGenBoneSpine;
import biomesoplenty.worldgen.WorldGenBoneSpine2;
import biomesoplenty.worldgen.WorldGenBoulder;
import biomesoplenty.worldgen.WorldGenCanyon;
import biomesoplenty.worldgen.WorldGenCanyonGrass;
import biomesoplenty.worldgen.WorldGenCattail;
import biomesoplenty.worldgen.WorldGenCloud;
import biomesoplenty.worldgen.WorldGenCrystal1;
import biomesoplenty.worldgen.WorldGenCrystal2;
import biomesoplenty.worldgen.WorldGenDesertCactus;
import biomesoplenty.worldgen.WorldGenDriedDirt;
import biomesoplenty.worldgen.WorldGenGravel;
import biomesoplenty.worldgen.WorldGenHighCattail;
import biomesoplenty.worldgen.WorldGenHighGrass;
import biomesoplenty.worldgen.WorldGenLilyflower;
import biomesoplenty.worldgen.WorldGenMelon;
import biomesoplenty.worldgen.WorldGenMesa;
import biomesoplenty.worldgen.WorldGenMud;
import biomesoplenty.worldgen.WorldGenMycelium;
import biomesoplenty.worldgen.WorldGenNetherGrass;
import biomesoplenty.worldgen.WorldGenNetherLava;
import biomesoplenty.worldgen.WorldGenNetherVines;
import biomesoplenty.worldgen.WorldGenNetherWart;
import biomesoplenty.worldgen.WorldGenOasis;
import biomesoplenty.worldgen.WorldGenOutback;
import biomesoplenty.worldgen.WorldGenPit;
import biomesoplenty.worldgen.WorldGenPotatoes;
import biomesoplenty.worldgen.WorldGenPromisedWillow;
import biomesoplenty.worldgen.WorldGenQuagmire;
import biomesoplenty.worldgen.WorldGenQuicksand;
import biomesoplenty.worldgen.WorldGenReedBOP;
import biomesoplenty.worldgen.WorldGenShield;
import biomesoplenty.worldgen.WorldGenSmolderingGrass;
import biomesoplenty.worldgen.WorldGenSprout;
import biomesoplenty.worldgen.WorldGenSteppe;
import biomesoplenty.worldgen.WorldGenSunflower;

public class BiomeDecoratorBOP extends BiomeDecorator
{

	/** The world the BiomeDecorator is currently decorating */
	protected World currentWorld;

	/** The Biome Decorator's random number generator. */
	protected Random randomGenerator;

	/** The X-coordinate of the chunk currently being decorated */
	protected int chunk_X;

	/** The Z-coordinate of the chunk currently being decorated */
	protected int chunk_Z;

	/** The biome generator object. */
	protected BiomeGenBase biome;

	/** The clay generator. */
	protected WorldGenerator clayGen = new WorldGenClay(4);

	/** The sand generator. */
	protected WorldGenerator sandGen;
	protected WorldGenerator mudGen;
	protected WorldGenerator oasesGen;

	/** The gravel generator. */
	protected WorldGenerator gravelAsSandGen;

	/** The dirt generator. */
	protected WorldGenerator dirtGen;
	protected WorldGenerator gravelGen;
	protected WorldGenerator gravelShoreGen;
	protected WorldGenerator ashGen;
	protected WorldGenerator grassMesaGen;
	protected WorldGenerator sandMesaGen;
	protected WorldGenerator myceliumGen;
	protected WorldGenerator sandInGrassGen;
	protected WorldGenerator stoneInGrassGen;
	protected WorldGenerator stoneInGrassGen2;
	protected WorldGenerator sandInStoneGen;
	protected WorldGenerator driedDirtInSandGen;
	protected WorldGenerator clayInStoneGen;
	protected WorldGenerator quagmireGen;
	protected WorldGenerator quicksandGen;
	protected WorldGenerator canyonGen;
	protected WorldGenerator cloudGen;
	protected WorldGenerator coalGen;
	protected WorldGenerator ironGen;

	/** Field that holds gold WorldGenMinable */
	protected WorldGenerator goldGen;

	/** Field that holds redstone WorldGenMinable */
	protected WorldGenerator redstoneGen;

	/** Field that holds diamond WorldGenMinable */
	protected WorldGenerator diamondGen;

	/** Field that holds Lapis WorldGenMinable */
	protected WorldGenerator lapisGen;

	/** Field that holds one of the plantYellow WorldGenFlowers */
	protected WorldGenerator plantYellowGen;
	protected WorldGenerator dandelionGen;

	/** Field that holds one of the plantRed WorldGenFlowers */
	protected WorldGenerator plantRedGen;
	protected WorldGenerator plantWhiteGen;
	protected WorldGenerator plantBlueGen;
	protected WorldGenerator plantPurpleGen;
	protected WorldGenerator plantPinkGen;
	protected WorldGenerator plantOrangeGen;
	protected WorldGenerator plantTinyGen;
	protected WorldGenerator plantGlowGen;
	protected WorldGenerator plantDeadGen;
	protected WorldGenerator plantDesertGen;
	protected WorldGenerator cattailGen;
	protected WorldGenerator highCattailGen;
	protected WorldGenerator outbackGen;
	protected WorldGenerator smolderingGrassGen;
	protected WorldGenerator canyonGrassGen;
	protected WorldGenerator netherGrassGen;
	protected WorldGenerator netherWartGen;
	protected WorldGenerator steppeGen;
	protected WorldGenerator thornGen;
	protected WorldGenerator toadstoolGen;
	protected WorldGenerator portobelloGen;
	protected WorldGenerator blueMilkGen;
	protected WorldGenerator glowshroomGen;
	protected WorldGenerator highGrassGen;
	protected WorldGenerator carrotGen;
	protected WorldGenerator potatoGen;
	protected WorldGenerator sproutGen;
	protected WorldGenerator bushGen;
	protected WorldGenerator berryBushGen;
	protected WorldGenerator tinyCactusGen;
	protected WorldGenerator aloeGen;
	protected WorldGenerator deathbloomGen;
	protected WorldGenerator hydrangeaGen;
	protected WorldGenerator violetGen;
	protected WorldGenerator duneGrassGen;
	protected WorldGenerator holyTallGrassGen;
	protected WorldGenerator desertSproutsGen;
	protected WorldGenerator promisedWillowGen;
	protected WorldGenerator netherVineGen;
	protected WorldGenerator poisonIvyGen;
	protected WorldGenerator sunflowerGen;
	protected WorldGenerator rainbowflowerGen;
	protected WorldGenerator crystalGen;
	protected WorldGenerator crystalGen2;

	protected WorldGenerator boneSpineGen;
	protected WorldGenerator boneSpine2Gen;

	/** Field that holds mushroomBrown WorldGenFlowers */
	protected WorldGenerator mushroomBrownGen;

	/** Field that holds mushroomRed WorldGenFlowers */
	protected WorldGenerator mushroomRedGen;

	/** Field that holds big mushroom generator */
	protected WorldGenerator bigMushroomGen;

	/** Field that holds WorldGenReed */
	protected WorldGenerator reedGen;
	protected WorldGenerator reedBOPGen;

	/** Field that holds WorldGenCactus */
	protected WorldGenerator cactusGen;
	protected WorldGenerator desertCactusGen;

	/** The water lily generation! */
	protected WorldGenerator waterlilyGen;
	protected WorldGenerator lilyflowerGen;
	protected WorldGenerator algaeGen;
	protected WorldGenerator pitGen;

	/** Amount of waterlilys per chunk. */
	protected int waterlilyPerChunk;
	protected int lilyflowersPerChunk;
	protected int algaePerChunk;
	protected int crystalsPerChunk;
	protected int crystals2PerChunk;

	/**
	 * The number of trees to attempt to generate per chunk. Up to 10 in forests, none in deserts.
	 */
	protected int treesPerChunk;

	/**
	 * The number of yellow flower patches to generate per chunk. The game generates much less than this number, since
	 * it attempts to generate them at a random altitude.
	 */
	protected int flowersPerChunk;
	protected int whiteFlowersPerChunk;
	protected int blueFlowersPerChunk;
	protected int purpleFlowersPerChunk;
	protected int pinkFlowersPerChunk;
	protected int orangeFlowersPerChunk;
	protected int tinyFlowersPerChunk;
	protected int glowFlowersPerChunk;
	protected int deadGrassPerChunk;
	protected int desertGrassPerChunk;
	protected int cattailsPerChunk;
	protected int highCattailsPerChunk;
	protected int carrotsPerChunk;
	protected int potatoesPerChunk;
	protected int thornsPerChunk;
	protected int toadstoolsPerChunk;
	protected int portobellosPerChunk;
	protected int blueMilksPerChunk;
	protected int glowshroomsPerChunk;
	protected int sproutsPerChunk;
	protected int bushesPerChunk;
	protected int berryBushesPerChunk;
	protected int tinyCactiPerChunk;
	protected int aloePerChunk;
	protected int deathbloomsPerChunk;
	protected int hydrangeasPerChunk;
	protected int violetsPerChunk;
	protected int duneGrassPerChunk;
	protected int holyTallGrassPerChunk;
	protected int desertSproutsPerChunk;
	protected int promisedWillowPerChunk;
	protected int netherVinesPerChunk;
	protected int poisonIvyPerChunk;
	protected int sunflowersPerChunk;
	protected int rainbowflowersPerChunk;

	protected int boneSpinesPerChunk;
	protected int boneSpines2PerChunk;

	/** The amount of tall grass to generate per chunk. */
	protected int grassPerChunk;
	protected int outbackPerChunk;
	protected int smolderingGrassPerChunk;
	protected int netherGrassPerChunk;
	protected int netherWartPerChunk;
	protected int canyonGrassPerChunk;
	protected int steppePerChunk;
	protected int highGrassPerChunk;

	/**
	 * The number of dead bushes to generate per chunk. Used in deserts and swamps.
	 */
	protected int deadBushPerChunk;

	/**
	 * The number of extra mushroom patches per chunk. It generates 1/4 this number in brown mushroom patches, and 1/8
	 * this number in red mushroom patches. These mushrooms go beyond the default base number of mushrooms.
	 */
	protected int mushroomsPerChunk;

	/**
	 * The number of reeds to generate per chunk. Reeds won't generate if the randomly selected placement is unsuitable.
	 */
	protected int reedsPerChunk;
	protected int reedsBOPPerChunk;

	/**
	 * The number of cactus plants to generate per chunk. Cacti only work on sand.
	 */
	protected int cactiPerChunk;
	protected int desertCactiPerChunk;

	/**
	 * The number of sand patches to generate per chunk. Sand patches only generate when part of it is underwater.
	 */
	protected int sandPerChunk;
	protected int oasesPerChunk;
	protected int mudPerChunk;
	protected int gravelPerChunk;

	/**
	 * The number of sand patches to generate per chunk. Sand patches only generate when part of it is underwater. There
	 * appear to be two separate fields for this.
	 */
	protected int sandPerChunk2;
	protected int oasesPerChunk2;
	protected int mudPerChunk2;
	protected int gravelPerChunk2;

	/**
	 * The number of clay patches to generate per chunk. Only generates when part of it is underwater.
	 */
	protected int clayPerChunk;

	/** Amount of big mushrooms per chunk */
	protected int bigMushroomsPerChunk;
	protected int rosesPerChunk;
	protected int pondsPerChunk;
	protected int waterLakesPerChunk;
	protected int lavaLakesPerChunk;
	protected int netherLavaPerChunk;
	protected int hotSpringsPerChunk;
	protected int poisonWaterPerChunk;

	/** True if decorator should generate surface lava & water */
	public boolean generateLakes;
	public boolean generateAsh;
	public boolean generateGrass;
	public boolean generateSand;
	public boolean generateMycelium;
	public boolean generateSandInGrass;
	public boolean generateStoneInGrass;
	public boolean generateStoneInGrass2;
	public boolean generateSandInStone;
	public boolean generateDriedDirtInSand;
	public boolean generateClayInClay;
	public boolean generateClayInClay2;
	public boolean generateClayInStone;
	public boolean generateClayInStone2;
	public boolean generatePits;
	public boolean generateQuagmire;
	public boolean generateCanyon;
	public boolean generatePumpkins;
	public boolean generateMelons;
	public boolean generateBoulders;
	public boolean generateClouds;
	public boolean generateQuicksand;

	public BiomeDecoratorBOP(BiomeGenBase par1BiomeGenBase)
	{
		super(par1BiomeGenBase);
		sandGen = new WorldGenSand(7, Block.sand.blockID);
		oasesGen = new WorldGenOasis(7, Block.grass.blockID);
		mudGen = new WorldGenMud(7, Blocks.mud.get().blockID);
		gravelShoreGen = new WorldGenGravel(7, Block.gravel.blockID);
		gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
		dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
		gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
		ashGen = new WorldGenAsh(Blocks.ash.get().blockID, 32);
		grassMesaGen = new WorldGenMesa(Block.grass.blockID, 48);
		sandMesaGen = new WorldGenMesa(Block.sand.blockID, 32);
		myceliumGen = new WorldGenMycelium(Block.mycelium.blockID, 32);
		sandInGrassGen = new WorldGenMycelium(Block.sand.blockID, 32);
		stoneInGrassGen = new WorldGenMycelium(Block.stone.blockID, 32);
		stoneInGrassGen2 = new WorldGenShield(Block.stone.blockID, 48);
		sandInStoneGen = new WorldGenMinable(Block.sand.blockID, 32);
		clayInStoneGen = new WorldGenMinable(Block.blockClay.blockID, 32);
		quagmireGen = new WorldGenQuagmire(Block.grass.blockID, 48);
		quicksandGen = new WorldGenQuicksand(Blocks.mud.get().blockID, 24);
		canyonGen = new WorldGenCanyon(Blocks.redRock.get().blockID, 48);
		driedDirtInSandGen = new WorldGenDriedDirt(Blocks.driedDirt.get().blockID, 32);
		cloudGen = new WorldGenCloud(Blocks.cloud.get().blockID, 48);
		coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
		ironGen = new WorldGenMinable(Block.oreIron.blockID, 8);
		goldGen = new WorldGenMinable(Block.oreGold.blockID, 8);
		redstoneGen = new WorldGenMinable(Block.oreRedstone.blockID, 7);
		diamondGen = new WorldGenMinable(Block.oreDiamond.blockID, 7);
		lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 6);
		plantYellowGen = new WorldGenBOPFlowers(Block.plantYellow.blockID, 0);
		dandelionGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 15);
		plantRedGen = new WorldGenBOPFlowers(Block.plantRed.blockID, 0);
		plantWhiteGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 9);
		plantBlueGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 1);
		plantPurpleGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 7);
		plantPinkGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 6);
		plantOrangeGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 5);
		rainbowflowerGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 11);
		plantTinyGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 0);
		plantGlowGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 3);
		plantDeadGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 0);
		plantDesertGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 1);
		thornGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 5);
		bushGen = new WorldGenBOPBush(Blocks.foliage.get().blockID, 4);
		berryBushGen = new WorldGenBOPFlowers(Blocks.foliage.get().blockID, 8);
		tinyCactusGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 12);
		aloeGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 12);
		lilyflowerGen = new WorldGenLilyflower();
		deathbloomGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 2);
		hydrangeaGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 4);
		violetGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 8);
		duneGrassGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 3);
		holyTallGrassGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 4);
		desertSproutsGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 2);
		poisonIvyGen = new WorldGenBOPBush(Blocks.foliage.get().blockID, 7);
		sunflowerGen = new WorldGenSunflower(Blocks.flowers.get().blockID, 13);
		promisedWillowGen = new WorldGenPromisedWillow();
		netherVineGen = new WorldGenNetherVines();
		boneSpineGen = new WorldGenBoneSpine();
		boneSpine2Gen = new WorldGenBoneSpine2();
		cattailGen = new WorldGenCattail();
		crystalGen = new WorldGenCrystal1();
		crystalGen2 = new WorldGenCrystal2();
		mushroomBrownGen = new WorldGenBOPFlowers(Block.mushroomBrown.blockID, 0);
		mushroomRedGen = new WorldGenBOPFlowers(Block.mushroomRed.blockID, 0);
		toadstoolGen = new WorldGenBOPFlowers(Blocks.mushrooms.get().blockID, 0);
		portobelloGen = new WorldGenBOPFlowers(Blocks.mushrooms.get().blockID, 1);
		blueMilkGen = new WorldGenBOPFlowers(Blocks.mushrooms.get().blockID, 2);
		glowshroomGen = new WorldGenBOPFlowers(Blocks.mushrooms.get().blockID, 3);
		sproutGen = new WorldGenSprout(Blocks.foliage.get().blockID, 5);
		highGrassGen = new WorldGenHighGrass(Blocks.foliage.get().blockID, 3);
		highCattailGen = new WorldGenHighCattail(Blocks.plants.get().blockID, 9);
		outbackGen = new WorldGenOutback(Blocks.foliage.get().blockID, 2);
		smolderingGrassGen = new WorldGenSmolderingGrass(Blocks.holyGrass.get().blockID, 1);
		netherGrassGen = new WorldGenNetherGrass(Block.tallGrass.blockID, 1);
		netherWartGen = new WorldGenNetherWart(Block.netherStalk.blockID, 0);
		canyonGrassGen = new WorldGenCanyonGrass(Blocks.foliage.get().blockID, 2);
		steppeGen = new WorldGenSteppe(Block.sand.blockID, 0);
		carrotGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 11);
		potatoGen = new WorldGenPotatoes(Block.tallGrass.blockID, 0);
		bigMushroomGen = new WorldGenBigMushroom();
		reedGen = new WorldGenReed();
		reedBOPGen = new WorldGenReedBOP();
		cactusGen = new WorldGenCactus();
		desertCactusGen = new WorldGenDesertCactus();
		waterlilyGen = new WorldGenWaterlily();
		algaeGen = new WorldGenAlgae();
		pitGen = new WorldGenPit(Blocks.ash.get().blockID);
		waterlilyPerChunk = 0;
		lilyflowersPerChunk = 0;
		treesPerChunk = 0;
		flowersPerChunk = 2;
		grassPerChunk = 1;
		deadBushPerChunk = 0;
		mushroomsPerChunk = 0;
		reedsPerChunk = 0;
		reedsBOPPerChunk = 0;
		cactiPerChunk = 0;
		sandPerChunk = 1;
		sandPerChunk2 = 3;
		oasesPerChunk = 0;
		oasesPerChunk2 = 0;
		mudPerChunk = 0;
		mudPerChunk2 = 0;
		gravelPerChunk = 0;
		gravelPerChunk2 = 0;
		clayPerChunk = 1;
		bigMushroomsPerChunk = 0;
		rosesPerChunk = 0;
		whiteFlowersPerChunk = 0;
		blueFlowersPerChunk = 0;
		purpleFlowersPerChunk = 0;
		pinkFlowersPerChunk = 0;
		orangeFlowersPerChunk = 0;
		rainbowflowersPerChunk = 0;
		tinyFlowersPerChunk = 0;
		glowFlowersPerChunk = 0;
		deadGrassPerChunk = 0;
		desertGrassPerChunk = 0;
		cattailsPerChunk = 0;
		highCattailsPerChunk = 0;
		carrotsPerChunk = 0;
		potatoesPerChunk = 0;
		thornsPerChunk = 0;
		toadstoolsPerChunk = 0;
		portobellosPerChunk = 0;
		blueMilksPerChunk = 0;
		glowshroomsPerChunk = 0;
		sunflowersPerChunk = 0;
		sproutsPerChunk = 0;
		bushesPerChunk = 0;
		berryBushesPerChunk = 0;
		tinyCactiPerChunk = 0;
		poisonIvyPerChunk = 0;
		aloePerChunk = 0;
		deathbloomsPerChunk = 0;
		hydrangeasPerChunk = 0;
		violetsPerChunk = 0;
		duneGrassPerChunk = 0;
		holyTallGrassPerChunk = 0;
		desertSproutsPerChunk = 0;
		desertCactiPerChunk = 0;
		highGrassPerChunk = 0;
		outbackPerChunk = 0;
		smolderingGrassPerChunk = 0;
		netherGrassPerChunk = 0;
		netherWartPerChunk = 0;
		canyonGrassPerChunk = 0;
		steppePerChunk = 0;
		promisedWillowPerChunk = 0;
		netherVinesPerChunk = 0;
		algaePerChunk = 0;
		pondsPerChunk = 0;
		waterLakesPerChunk = 0;
		lavaLakesPerChunk = 0;
		netherLavaPerChunk = 0;
		hotSpringsPerChunk = 0;
		poisonWaterPerChunk = 0;
		crystalsPerChunk = 0;
		crystals2PerChunk = 0;
		boneSpinesPerChunk = 0;
		boneSpines2PerChunk = 0;
		generateLakes = true;
		generateAsh = false;
		generateMycelium = false;
		generateSandInGrass = false;
		generateStoneInGrass = false;
		generateStoneInGrass2 = false;
		generateSandInStone = false;
		generateDriedDirtInSand = false;
		generateClayInStone = false;
		generateQuagmire = false;
		generateCanyon = false;
		generatePumpkins = true;
		generateMelons = false;
		generateBoulders = false;
		generateClouds = false;
		generateQuicksand = false;
		biome = par1BiomeGenBase;
	}

	/**
	 * Decorates the world. Calls code that was formerly (pre-1.8) in ChunkProviderGenerate.populate
	 */
	 @Override
	 public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		if (currentWorld != null)
			return ;
		else
		{
			currentWorld = par1World;
			randomGenerator = par2Random;
			chunk_X = par3;
			chunk_Z = par4;
			this.decorate();
			currentWorld = null;
			randomGenerator = null;
		}
	}




	/**
	 * The method that does the work of actually decorating chunks
	 */

	 @Override
	 protected void decorate()
	{
		 MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
		 //Added
		 boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND);

		 this.generateOres();

		 int var1;
		 int var2;
		 int var3;
		 int var4;
		 int var5;

		 for (var2 = 0; var2 < waterLakesPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(randomGenerator.nextInt(240) + 8);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 (new WorldGenLakes(Block.waterMoving.blockID)).generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < lavaLakesPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(112) + 8) + 8);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 (new WorldGenLakes(Block.lavaMoving.blockID)).generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < netherLavaPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(112) + 8) + 8);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 (new WorldGenNetherLava(Block.lavaMoving.blockID)).generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < hotSpringsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(112) + 8) + 8);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 (new WorldGenLakes(Fluids.springWater.get().blockID)).generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < 5; ++var2)
		 {
			 int var9999 = randomGenerator.nextInt(96);

			 if (var9999 == 1)
			 {
				 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				 var4 = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(32) + 8) + 8);
				 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
				 (new WorldGenLakes(Fluids.springWater.get().blockID)).generate(currentWorld, randomGenerator, var3, var4, var5);
			 }
		 }

		 for (var2 = 0; var2 < poisonWaterPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(112) + 8) + 8);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 (new WorldGenLakes(Fluids.liquidPoison.get().blockID)).generate(currentWorld, randomGenerator, var3, var4, var5);
		 }
		 
		 for (var2 = 0; var2 < 5; ++var2)
		 {
			 int var9998 = randomGenerator.nextInt(32);

			 if (var9998 == 1)
			 {
				 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				 var4 = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(32) + 8) + 8);
				 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
				 (new WorldGenLakes(Fluids.liquidPoison.get().blockID)).generate(currentWorld, randomGenerator, var3, var4, var5);
			 }
		 }

		 if (generateAsh)
		 {
			 this.genStandardOre1(10, ashGen, 0, 128);
		 }

		 if (generateGrass)
		 {
			 this.genStandardOre1(20, grassMesaGen, 0, 128);
		 }

		 if (generateSand)
		 {
			 this.genStandardOre1(15, sandMesaGen, 0, 128);
		 }

		 if (generateMycelium)
		 {
			 this.genStandardOre1(10, myceliumGen, 0, 128);
		 }

		 if (generateSandInGrass)
		 {
			 this.genStandardOre1(8, sandInGrassGen, 64, 128);
		 }

		 if (generateStoneInGrass)
		 {
			 this.genStandardOre1(15, stoneInGrassGen, 64, 128);
		 }

		 if (generateStoneInGrass2)
		 {
			 this.genStandardOre1(20, stoneInGrassGen2, 64, 128);
		 }

		 if (generateSandInStone)
		 {
			 this.genStandardOre1(10, sandInStoneGen, 64, 128);
		 }

		 if (generateDriedDirtInSand)
		 {
			 this.genStandardOre1(8, driedDirtInSandGen, 64, 128);
		 }
		 
		 if (generateClayInStone)
		 {
			 this.genStandardOre1(20, clayInStoneGen, 64, 128);
		 }

		 if (generateQuagmire)
		 {
			 this.genStandardOre1(15, quagmireGen, 64, 128);
		 }

		 if (generateCanyon)
		 {
			 this.genStandardOre1(15, canyonGen, 64, 128);
		 }

		 if (generateQuicksand)
		 {
			 this.genStandardOre1(5, quicksandGen, 64, 128);
		 }

		 if (generateClouds)
		 {
			 this.genCloudMain(1, cloudGen, 0, 50);
		 }

		 if (generatePits)
		 {
			 var4 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 int var6 = currentWorld.getTopSolidOrLiquidBlock(var4, var5);

			 if (var6 > 0)
			 {
				 ;
			 }

			 pitGen.generate(currentWorld, randomGenerator, var4, var6, var5);
		 }

		 for (var1 = 0; var1 < sandPerChunk2; ++var1)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 sandGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		 }

		 //Added
		 for (var1 = 0; doGen && var1 < sandPerChunk2; ++var1)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 sandGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		 }

		 for (var1 = 0; var1 < mudPerChunk2; ++var1)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 mudGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		 }

		 for (var1 = 0; var1 < gravelPerChunk2; ++var1)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 gravelShoreGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		 }

		 //Added
		 doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CLAY);
		 for (var1 = 0; doGen && var1 < clayPerChunk; ++var1)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 clayGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		 }

		 //Added
		 doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND_PASS2);
		 for (var1 = 0; doGen && var1 < sandPerChunk; ++var1)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 sandGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		 }

		 for (var1 = 0; var1 < oasesPerChunk; ++var1)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 oasesGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		 }

		 for (var1 = 0; var1 < mudPerChunk; ++var1)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 mudGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		 }

		 for (var1 = 0; var1 < gravelPerChunk; ++var1)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 gravelShoreGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		 }

		 var1 = treesPerChunk;

		 if (randomGenerator.nextInt(10) == 0)
		 {
			 ++var1;
		 }

		 //Added
		 doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, TREE);
		 for (var2 = 0; doGen && var2 < var1; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 WorldGenerator var7 = biome.getRandomWorldGenForTrees(randomGenerator);
			 var7.setScale(1.0D, 1.0D, 1.0D);
			 var7.generate(currentWorld, randomGenerator, var3, currentWorld.getHeightValue(var3, var4), var4);
		 }

		 //Added
		 doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, BIG_SHROOM);
		 for (var2 = 0; doGen && var2 < bigMushroomsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 bigMushroomGen.generate(currentWorld, randomGenerator, var3, currentWorld.getHeightValue(var3, var4), var4);
		 }

		 //Added
		 doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, FLOWERS);
		 for (var2 = 0; doGen && var2 < flowersPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 plantYellowGen.generate(currentWorld, randomGenerator, var3, var4, var5);

			 if (randomGenerator.nextInt(6) == 0)
			 {
				 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				 var4 = randomGenerator.nextInt(256);
				 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
				 dandelionGen.generate(currentWorld, randomGenerator, var3, var4, var5);
			 }

			 if (randomGenerator.nextInt(4) == 0)
			 {
				 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				 var4 = randomGenerator.nextInt(256);
				 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
				 plantRedGen.generate(currentWorld, randomGenerator, var3, var4, var5);
			 }
		 }

		 for (var2 = 0; var2 < rosesPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 plantRedGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < sunflowersPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 sunflowerGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }
		 
		 for (var2 = 0; var2 < rainbowflowersPerChunk; ++var2)
		 {
			 int var956 = randomGenerator.nextInt(10);
			 
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 
			 if (var956 == 0)
			 {
				 rainbowflowerGen.generate(currentWorld, randomGenerator, var3, var4, var5);
			 }
		 }

		 for (var2 = 0; var2 < boneSpinesPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 boneSpineGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < boneSpines2PerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(64) + 64;
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 boneSpine2Gen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < crystalsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(50);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 crystalGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < crystals2PerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(50);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 crystalGen2.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < promisedWillowPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(70);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 promisedWillowGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < netherVinesPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 netherVineGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < whiteFlowersPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 plantWhiteGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < blueFlowersPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 plantBlueGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < hydrangeasPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 hydrangeaGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < violetsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 violetGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < duneGrassPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 duneGrassGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < holyTallGrassPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 holyTallGrassGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < desertSproutsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 desertSproutsGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < purpleFlowersPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 plantPurpleGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < pinkFlowersPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 plantPinkGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < bushesPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 bushGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < berryBushesPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 berryBushGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < poisonIvyPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 poisonIvyGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < orangeFlowersPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 plantOrangeGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < tinyCactiPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 tinyCactusGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < aloePerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 aloeGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < deathbloomsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 deathbloomGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < toadstoolsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 toadstoolGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < portobellosPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 portobelloGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < blueMilksPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 blueMilkGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < glowshroomsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(80);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 glowshroomGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < sproutsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 sproutGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < tinyFlowersPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 plantTinyGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < glowFlowersPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 plantGlowGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < deadGrassPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 plantDeadGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < desertGrassPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 plantDesertGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 //Added
		 doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, GRASS);
		 for (var2 = 0; doGen && var2 < grassPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 WorldGenerator var6 = biome.getRandomWorldGenForGrass(randomGenerator);
			 var6.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < outbackPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 outbackGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < smolderingGrassPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 smolderingGrassGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < netherGrassPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 netherGrassGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 if (BOPConfiguration.netherwartSpawn)
    		 for (var2 = 0; var2 < netherWartPerChunk; ++var2)
    		 {
    			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
    			 var4 = randomGenerator.nextInt(256);
    			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
    			 netherWartGen.generate(currentWorld, randomGenerator, var3, var4, var5);
    		 }

		 for (var2 = 0; var2 < canyonGrassPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 canyonGrassGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < steppePerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 steppeGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < highGrassPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 highGrassGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < carrotsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 carrotGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < potatoesPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 potatoGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < reedsBOPPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 var5 = randomGenerator.nextInt(256);
			 reedBOPGen.generate(currentWorld, randomGenerator, var3, var5, var4);
		 }

		 for (var2 = 0; var2 < thornsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 thornGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < cattailsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 cattailGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < highCattailsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 highCattailGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 //Added
		 doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, DEAD_BUSH);
		 for (var2 = 0; doGen && var2 < deadBushPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 (new WorldGenDeadBush(Block.deadBush.blockID)).generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 //Added
		 doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SHROOM);
		 for (var2 = 0; doGen && var2 < mushroomsPerChunk; ++var2)
		 {
			 if (randomGenerator.nextInt(4) == 0)
			 {
				 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
				 var5 = currentWorld.getHeightValue(var3, var4);
				 mushroomBrownGen.generate(currentWorld, randomGenerator, var3, var5, var4);
			 }

			 if (randomGenerator.nextInt(8) == 0)
			 {
				 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
				 var5 = randomGenerator.nextInt(256);
				 mushroomRedGen.generate(currentWorld, randomGenerator, var3, var5, var4);
			 }
		 }

		 if (randomGenerator.nextInt(4) == 0)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = randomGenerator.nextInt(256);
			 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 mushroomBrownGen.generate(currentWorld, randomGenerator, var2, var3, var4);
		 }

		 if (randomGenerator.nextInt(8) == 0)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = randomGenerator.nextInt(256);
			 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 mushroomRedGen.generate(currentWorld, randomGenerator, var2, var3, var4);
		 }

		 //Added
		 doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, REED);
		 for (var2 = 0; doGen && var2 < reedsPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 var5 = randomGenerator.nextInt(256);
			 reedGen.generate(currentWorld, randomGenerator, var3, var5, var4);
		 }

		 for (var2 = 0; doGen && var2 < 10; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 reedGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 if (generatePumpkins && randomGenerator.nextInt(32) == 0)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = randomGenerator.nextInt(256);
			 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 (new WorldGenPumpkin()).generate(currentWorld, randomGenerator, var2, var3, var4);
		 }

		 if (generateMelons && randomGenerator.nextInt(32) == 0)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = randomGenerator.nextInt(256);
			 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 (new WorldGenMelon()).generate(currentWorld, randomGenerator, var2, var3, var4);
		 }

		 if (generateBoulders && randomGenerator.nextInt(32) == 0)
		 {
			 var2 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var3 = randomGenerator.nextInt(256);
			 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 (new WorldGenBoulder()).generate(currentWorld, randomGenerator, var2, var3, var4);
		 }

		 for (var2 = 0; var2 < cactiPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 cactusGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; var2 < desertCactiPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 desertCactusGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 if (generateLakes)
		 {
			 for (var2 = 0; var2 < 50 + pondsPerChunk; ++var2)
			 {
				 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				 var4 = randomGenerator.nextInt(randomGenerator.nextInt(120) + 8);
				 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
				 (new WorldGenLiquids(Block.waterMoving.blockID)).generate(currentWorld, randomGenerator, var3, var4, var5);
			 }

			 for (var2 = 0; var2 < 20; ++var2)
			 {
				 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				 var4 = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(112) + 8) + 8);
				 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
				 (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(currentWorld, randomGenerator, var3, var4, var5);
			 }
		 }

		 for (var2 = 0; var2 < lilyflowersPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = randomGenerator.nextInt(256);
			 var5 = chunk_Z + randomGenerator.nextInt(16) + 8;
			 lilyflowerGen.generate(currentWorld, randomGenerator, var3, var4, var5);
		 }

		 for (var2 = 0; doGen && var2 < algaePerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;

			 for (var5 = randomGenerator.nextInt(256); var5 > 0 && currentWorld.getBlockId(var3, var5 - 1, var4) == 0; --var5)
			 {
				 ;
			 }

			 algaeGen.generate(currentWorld, randomGenerator, var3, var5, var4);
		 }

		 //Added
		 doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LILYPAD);
		 for (var2 = 0; doGen && var2 < waterlilyPerChunk; ++var2)
		 {
			 var3 = chunk_X + randomGenerator.nextInt(16) + 8;
			 var4 = chunk_Z + randomGenerator.nextInt(16) + 8;

			 for (var5 = randomGenerator.nextInt(256); var5 > 0 && currentWorld.getBlockId(var3, var5 - 1, var4) == 0; --var5)
			 {
				 ;
			 }

			 waterlilyGen.generate(currentWorld, randomGenerator, var3, var5, var4);
		 }

		 MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
	}

	 /**
	  * Standard ore generation helper. Generates most ores.
	  */
	 protected void genCloudMain(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
	 {
		 for (int var5 = 0; var5 < par1; ++var5)
		 {
			 int var6 = chunk_X + randomGenerator.nextInt(8);
			 int var7 = randomGenerator.nextInt(par4 - par3) + par3;
			 int var8 = chunk_Z + randomGenerator.nextInt(8);
			 int var999 = randomGenerator.nextInt(5);
			 if (var999 == 0)
			 {
				 par2WorldGenerator.generate(currentWorld, randomGenerator, var6, var7, var8);
				 par2WorldGenerator.generate(currentWorld, randomGenerator, var6 + 8, var7, var8);
				 par2WorldGenerator.generate(currentWorld, randomGenerator, var6, var7, var8 + 8);
				 par2WorldGenerator.generate(currentWorld, randomGenerator, var6 + 8, var7, var8 + 8);
			 }
		 }
	 }

	 /**
	  * Standard ore generation helper. Generates most ores.
	  */
	 @Override
	 protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
	 {
		 for (int var5 = 0; var5 < par1; ++var5)
		 {
			 int var6 = chunk_X + randomGenerator.nextInt(16);
			 int var7 = randomGenerator.nextInt(par4 - par3) + par3;
			 int var8 = chunk_Z + randomGenerator.nextInt(16);
			 par2WorldGenerator.generate(currentWorld, randomGenerator, var6, var7, var8);
		 }
	 }

	 /**
	  * Standard ore generation helper. Generates Lapis Lazuli.
	  */
	 @Override
	 protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
	 {
		 for (int var5 = 0; var5 < par1; ++var5)
		 {
			 int var6 = chunk_X + randomGenerator.nextInt(16);
			 int var7 = randomGenerator.nextInt(par4) + randomGenerator.nextInt(par4) + (par3 - par4);
			 int var8 = chunk_Z + randomGenerator.nextInt(16);
			 par2WorldGenerator.generate(currentWorld, randomGenerator, var6, var7, var8);
		 }
	 }

	 /**
	  * Generates ores in the current chunk
	  */
	 @Override
	 protected void generateOres()
	 {
		 MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));

		 if (TerrainGen.generateOre(currentWorld, randomGenerator, dirtGen, chunk_X, chunk_Z, DIRT)) {
			 this.genStandardOre1(20, dirtGen, 0, 256);
		 }

		 if (TerrainGen.generateOre(currentWorld, randomGenerator, gravelGen, chunk_X, chunk_Z, GRAVEL)) {
			 this.genStandardOre1(10, gravelGen, 0, 256);
		 }

		 if (TerrainGen.generateOre(currentWorld, randomGenerator, coalGen, chunk_X, chunk_Z, COAL)) {
			 this.genStandardOre1(20, coalGen, 0, 128);
		 }

		 if (TerrainGen.generateOre(currentWorld, randomGenerator, ironGen, chunk_X, chunk_Z, IRON)) {
			 this.genStandardOre1(20, ironGen, 0, 64);
		 }

		 if (TerrainGen.generateOre(currentWorld, randomGenerator, goldGen, chunk_X, chunk_Z, GOLD)) {
			 this.genStandardOre1(2, goldGen, 0, 32);
		 }

		 if (TerrainGen.generateOre(currentWorld, randomGenerator, redstoneGen, chunk_X, chunk_Z, REDSTONE)) {
			 this.genStandardOre1(8, redstoneGen, 0, 16);
		 }

		 if (TerrainGen.generateOre(currentWorld, randomGenerator, diamondGen, chunk_X, chunk_Z, DIAMOND)) {
			 this.genStandardOre1(1, diamondGen, 0, 16);
		 }

		 if (TerrainGen.generateOre(currentWorld, randomGenerator, lapisGen, chunk_X, chunk_Z, LAPIS)) {
			 this.genStandardOre2(1, lapisGen, 16, 16);
		 }

		 MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
	 }
}
