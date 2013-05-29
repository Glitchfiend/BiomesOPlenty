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
import biomesoplenty.api.Liquids;
import biomesoplenty.worldgen.WorldGenAlgae;
import biomesoplenty.worldgen.WorldGenAsh;
import biomesoplenty.worldgen.WorldGenBOPFlowers;
import biomesoplenty.worldgen.WorldGenBoulder;
import biomesoplenty.worldgen.WorldGenBush;
import biomesoplenty.worldgen.WorldGenCanyon;
import biomesoplenty.worldgen.WorldGenCanyonGrass;
import biomesoplenty.worldgen.WorldGenCarrots;
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
	protected WorldGenerator crystalGen;
	protected WorldGenerator crystalGen2;

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
    public boolean generateClayInStone;
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
        this.sandGen = new WorldGenSand(7, Block.sand.blockID);
        this.oasesGen = new WorldGenOasis(7, Block.grass.blockID);
        this.mudGen = new WorldGenMud(7, Blocks.mud.get().blockID);
		this.gravelShoreGen = new WorldGenGravel(7, Block.gravel.blockID);
        this.gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
        this.dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
        this.gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
        this.ashGen = new WorldGenAsh(Blocks.ash.get().blockID, 32);
        this.grassMesaGen = new WorldGenMesa(Block.grass.blockID, 48);
        this.sandMesaGen = new WorldGenMesa(Block.sand.blockID, 32);
        this.myceliumGen = new WorldGenMycelium(Block.mycelium.blockID, 32);
        this.sandInGrassGen = new WorldGenMycelium(Block.sand.blockID, 32);
        this.stoneInGrassGen = new WorldGenMycelium(Block.stone.blockID, 32);
		this.stoneInGrassGen2 = new WorldGenShield(Block.stone.blockID, 48);
        this.sandInStoneGen = new WorldGenMinable(Block.sand.blockID, 32);
        this.clayInStoneGen = new WorldGenMinable(Block.blockClay.blockID, 32);
        this.quagmireGen = new WorldGenQuagmire(Block.grass.blockID, 48);
		this.quicksandGen = new WorldGenQuicksand(Blocks.mud.get().blockID, 24);
		this.canyonGen = new WorldGenCanyon(Blocks.redRock.get().blockID, 48);
        this.driedDirtInSandGen = new WorldGenDriedDirt(Blocks.driedDirt.get().blockID, 32);
		this.cloudGen = new WorldGenCloud(Blocks.cloud.get().blockID, 48);
        this.coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
        this.ironGen = new WorldGenMinable(Block.oreIron.blockID, 8);
        this.goldGen = new WorldGenMinable(Block.oreGold.blockID, 8);
        this.redstoneGen = new WorldGenMinable(Block.oreRedstone.blockID, 7);
        this.diamondGen = new WorldGenMinable(Block.oreDiamond.blockID, 7);
        this.lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 6);
        this.plantYellowGen = new WorldGenBOPFlowers(Block.plantYellow.blockID, 0);
		this.dandelionGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 15);
        this.plantRedGen = new WorldGenBOPFlowers(Block.plantRed.blockID, 0);
        this.plantWhiteGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 9);
        this.plantBlueGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 1);
        this.plantPurpleGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 7);
		this.plantPinkGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 6);
        this.plantOrangeGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 5);
        this.plantTinyGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 0);
        this.plantGlowGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 3);
        this.plantDeadGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 0);
        this.plantDesertGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 1);
        this.thornGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 5);
        this.bushGen = new WorldGenBush(Blocks.foliage.get().blockID, 4);
		this.berryBushGen = new WorldGenBOPFlowers(Blocks.foliage.get().blockID, 8);
		this.tinyCactusGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 11);
		this.aloeGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 12);
		this.lilyflowerGen = new WorldGenLilyflower();
		this.deathbloomGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 2);
		this.hydrangeaGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 4);
		this.violetGen = new WorldGenBOPFlowers(Blocks.flowers.get().blockID, 8);
		this.duneGrassGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 3);
		this.holyTallGrassGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 4);
		this.desertSproutsGen = new WorldGenBOPFlowers(Blocks.plants.get().blockID, 2);
		this.poisonIvyGen = new WorldGenBush(Blocks.foliage.get().blockID, 7);
		this.sunflowerGen = new WorldGenSunflower(Blocks.flowers.get().blockID, 13);
		this.promisedWillowGen = new WorldGenPromisedWillow();
		this.netherVineGen = new WorldGenNetherVines();
        this.cattailGen = new WorldGenCattail();
		this.crystalGen = new WorldGenCrystal1();
		this.crystalGen2 = new WorldGenCrystal2();
        this.mushroomBrownGen = new WorldGenBOPFlowers(Block.mushroomBrown.blockID, 0);
        this.mushroomRedGen = new WorldGenBOPFlowers(Block.mushroomRed.blockID, 0);
        this.toadstoolGen = new WorldGenBOPFlowers(Blocks.mushrooms.get().blockID, 0);
		this.portobelloGen = new WorldGenBOPFlowers(Blocks.mushrooms.get().blockID, 1);
		this.blueMilkGen = new WorldGenBOPFlowers(Blocks.mushrooms.get().blockID, 2);
		this.glowshroomGen = new WorldGenBOPFlowers(Blocks.mushrooms.get().blockID, 3);
        this.sproutGen = new WorldGenSprout(Blocks.foliage.get().blockID, 5);
        this.highGrassGen = new WorldGenHighGrass(Blocks.foliage.get().blockID, 3);
		this.highCattailGen = new WorldGenHighCattail(Blocks.plants.get().blockID, 9);
		this.outbackGen = new WorldGenOutback(Blocks.foliage.get().blockID, 2);
		this.smolderingGrassGen = new WorldGenSmolderingGrass(Blocks.holyGrass.get().blockID, 1);
		this.netherGrassGen = new WorldGenNetherGrass(Block.tallGrass.blockID, 1);
		this.netherWartGen = new WorldGenNetherWart(Block.netherStalk.blockID, 0);
		this.canyonGrassGen = new WorldGenCanyonGrass(Blocks.foliage.get().blockID, 2);
		this.steppeGen = new WorldGenSteppe(Block.sand.blockID, 0);
        this.carrotGen = new WorldGenCarrots(Block.tallGrass.blockID, 0);
        this.potatoGen = new WorldGenPotatoes(Block.tallGrass.blockID, 0);
        this.bigMushroomGen = new WorldGenBigMushroom();
        this.reedGen = new WorldGenReed();
		this.reedBOPGen = new WorldGenReedBOP();
        this.cactusGen = new WorldGenCactus();
        this.desertCactusGen = new WorldGenDesertCactus();
        this.waterlilyGen = new WorldGenWaterlily();
		this.algaeGen = new WorldGenAlgae();
        this.pitGen = new WorldGenPit(Blocks.ash.get().blockID);
        this.waterlilyPerChunk = 0;
		this.lilyflowersPerChunk = 0;
        this.treesPerChunk = 0;
        this.flowersPerChunk = 2;
        this.grassPerChunk = 1;
        this.deadBushPerChunk = 0;
        this.mushroomsPerChunk = 0;
        this.reedsPerChunk = 0;
		this.reedsBOPPerChunk = 0;
        this.cactiPerChunk = 0;
        this.sandPerChunk = 1;
        this.sandPerChunk2 = 3;
        this.oasesPerChunk = 0;
        this.oasesPerChunk2 = 0;
        this.mudPerChunk = 0;
        this.mudPerChunk2 = 0;
		this.gravelPerChunk = 0;
        this.gravelPerChunk2 = 0;
        this.clayPerChunk = 1;
        this.bigMushroomsPerChunk = 0;
        this.rosesPerChunk = 0;
        this.whiteFlowersPerChunk = 0;
        this.blueFlowersPerChunk = 0;
        this.purpleFlowersPerChunk = 0;
		this.pinkFlowersPerChunk = 0;
        this.orangeFlowersPerChunk = 0;
        this.tinyFlowersPerChunk = 0;
        this.glowFlowersPerChunk = 0;
        this.deadGrassPerChunk = 0;
        this.desertGrassPerChunk = 0;
        this.cattailsPerChunk = 0;
		this.highCattailsPerChunk = 0;
        this.carrotsPerChunk = 0;
        this.potatoesPerChunk = 0;
        this.thornsPerChunk = 0;
        this.toadstoolsPerChunk = 0;
		this.portobellosPerChunk = 0;
		this.blueMilksPerChunk = 0;
		this.glowshroomsPerChunk = 0;
		this.sunflowersPerChunk = 0;
        this.sproutsPerChunk = 0;
        this.bushesPerChunk = 0;
		this.berryBushesPerChunk = 0;
		this.tinyCactiPerChunk = 0;
		this.poisonIvyPerChunk = 0;
		this.aloePerChunk = 0;
		this.deathbloomsPerChunk = 0;
		this.hydrangeasPerChunk = 0;
		this.violetsPerChunk = 0;
		this.duneGrassPerChunk = 0;
		this.holyTallGrassPerChunk = 0;
		this.desertSproutsPerChunk = 0;
        this.desertCactiPerChunk = 0;
        this.highGrassPerChunk = 0;
		this.outbackPerChunk = 0;
		this.smolderingGrassPerChunk = 0;
		this.netherGrassPerChunk = 0;
		this.netherWartPerChunk = 0;
		this.canyonGrassPerChunk = 0;
		this.steppePerChunk = 0;
		this.promisedWillowPerChunk = 0;
		this.netherVinesPerChunk = 0;
		this.algaePerChunk = 0;
        this.pondsPerChunk = 0;
		this.waterLakesPerChunk = 0;
		this.lavaLakesPerChunk = 0;
		this.netherLavaPerChunk = 0;
		this.hotSpringsPerChunk = 0;
		this.poisonWaterPerChunk = 0;
		this.crystalsPerChunk = 0;
		this.crystals2PerChunk = 0;
        this.generateLakes = true;
        this.generateAsh = false;
        this.generateMycelium = false;
        this.generateSandInGrass = false;
        this.generateStoneInGrass = false;
		this.generateStoneInGrass2 = false;
        this.generateSandInStone = false;
        this.generateDriedDirtInSand = false;
        this.generateClayInStone = false;
        this.generateQuagmire = false;
		this.generateCanyon = false;
        this.generatePumpkins = true;
        this.generateMelons = false;
        this.generateBoulders = false;
		this.generateClouds = false;
		this.generateQuicksand = false;
        this.biome = par1BiomeGenBase;
    }

    /**
     * Decorates the world. Calls code that was formerly (pre-1.8) in ChunkProviderGenerate.populate
     */
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
		if (this.currentWorld != null)
		{
			return ;
		}
		else
		{
			this.currentWorld = par1World;
			this.randomGenerator = par2Random;
			this.chunk_X = par3;
			this.chunk_Z = par4;
			this.decorate();
			this.currentWorld = null;
			this.randomGenerator = null;
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
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(120) + 8);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			(new WorldGenLakes(Block.waterMoving.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < lavaLakesPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			(new WorldGenLakes(Block.lavaMoving.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}
		
		for (var2 = 0; var2 < netherLavaPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			(new WorldGenNetherLava(Block.lavaMoving.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}
		
		for (var2 = 0; var2 < hotSpringsPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			(new WorldGenLakes(Liquids.springWater.get().blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}
		
		for (var2 = 0; var2 < 5; ++var2)
		{
			int var9999 = this.randomGenerator.nextInt(32);
			
			if (var9999 == 1)
				{
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(32) + 8) + 8);
				var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				(new WorldGenLakes(Liquids.springWater.get().blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
				}
		}
		
		for (var2 = 0; var2 < poisonWaterPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			(new WorldGenLakes(Liquids.liquidPoison.get().blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

        if (this.generateAsh)
        {
            this.genStandardOre1(10, this.ashGen, 0, 128);
        }

        if (this.generateGrass)
        {
            this.genStandardOre1(20, this.grassMesaGen, 0, 128);
        }

        if (this.generateSand)
        {
            this.genStandardOre1(15, this.sandMesaGen, 0, 128);
        }

        if (this.generateMycelium)
        {
            this.genStandardOre1(10, this.myceliumGen, 0, 128);
        }

        if (this.generateSandInGrass)
        {
            this.genStandardOre1(8, this.sandInGrassGen, 64, 128);
        }

        if (this.generateStoneInGrass)
        {
            this.genStandardOre1(15, this.stoneInGrassGen, 64, 128);
        }
		
		if (this.generateStoneInGrass2)
        {
            this.genStandardOre1(20, this.stoneInGrassGen2, 64, 128);
        }

        if (this.generateSandInStone)
        {
            this.genStandardOre1(10, this.sandInStoneGen, 64, 128);
        }

        if (this.generateDriedDirtInSand)
        {
            this.genStandardOre1(8, this.driedDirtInSandGen, 64, 128);
        }

        if (this.generateClayInStone)
        {
            this.genStandardOre1(15, this.clayInStoneGen, 64, 128);
        }

        if (this.generateQuagmire)
        {
            this.genStandardOre1(15, this.quagmireGen, 64, 128);
        }
		
		if (this.generateCanyon)
        {
            this.genStandardOre1(15, this.canyonGen, 64, 128);
        }
		
		if (this.generateQuicksand)
        {
            this.genStandardOre1(5, this.quicksandGen, 64, 128);
        }
		
		if (this.generateClouds)
        {
            this.genCloudMain(1, this.cloudGen, 0, 50);
        }

        if (this.generatePits)
        {
            var4 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            int var6 = this.currentWorld.getTopSolidOrLiquidBlock(var4, var5);

            if (var6 > 0)
            {
                ;
            }

            this.pitGen.generate(this.currentWorld, this.randomGenerator, var4, var6, var5);
        }

        for (var1 = 0; var1 < this.sandPerChunk2; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.sandGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }

        //Added
        for (var1 = 0; doGen && var1 < this.sandPerChunk2; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.sandGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }

        for (var1 = 0; var1 < this.mudPerChunk2; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.mudGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }
		
        for (var1 = 0; var1 < this.gravelPerChunk2; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.gravelShoreGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }

        //Added
        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CLAY);
        for (var1 = 0; doGen && var1 < this.clayPerChunk; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.clayGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }

        //Added
        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND_PASS2);
        for (var1 = 0; doGen && var1 < this.sandPerChunk; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.sandGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }

        for (var1 = 0; var1 < this.oasesPerChunk; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.oasesGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }

        for (var1 = 0; var1 < this.mudPerChunk; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.mudGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }
		
        for (var1 = 0; var1 < this.gravelPerChunk; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.gravelShoreGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }

        var1 = this.treesPerChunk;

        if (this.randomGenerator.nextInt(10) == 0)
        {
            ++var1;
        }

        //Added
        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, TREE);
        for (var2 = 0; doGen && var2 < var1; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            WorldGenerator var7 = this.biome.getRandomWorldGenForTrees(this.randomGenerator);
            var7.setScale(1.0D, 1.0D, 1.0D);
            var7.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
        }

        //Added
        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, BIG_SHROOM);
        for (var2 = 0; doGen && var2 < this.bigMushroomsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
        }

        //Added
        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, FLOWERS);
        for (var2 = 0; doGen && var2 < this.flowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantYellowGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
			
			if (this.randomGenerator.nextInt(6) == 0)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.randomGenerator.nextInt(128);
                var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                this.dandelionGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
            }

            if (this.randomGenerator.nextInt(4) == 0)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.randomGenerator.nextInt(128);
                var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                this.plantRedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
            }
        }

        for (var2 = 0; var2 < this.rosesPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantRedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.sunflowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.sunflowerGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.crystalsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(50);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.crystalGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
		for (var2 = 0; var2 < this.crystals2PerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(50);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.crystalGen2.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
		for (var2 = 0; var2 < this.promisedWillowPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(70);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.promisedWillowGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
		for (var2 = 0; var2 < this.netherVinesPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.netherVineGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.whiteFlowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantWhiteGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.blueFlowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantBlueGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.hydrangeasPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.hydrangeaGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.violetsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.violetGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.duneGrassPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.duneGrassGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
		for (var2 = 0; var2 < this.holyTallGrassPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.holyTallGrassGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
	
        for (var2 = 0; var2 < this.desertSproutsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.desertSproutsGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.purpleFlowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantPurpleGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.pinkFlowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantPinkGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.bushesPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.bushGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.berryBushesPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.berryBushGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
		for (var2 = 0; var2 < this.poisonIvyPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.poisonIvyGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.orangeFlowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantOrangeGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.tinyCactiPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.tinyCactusGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
		for (var2 = 0; var2 < this.aloePerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.aloeGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.deathbloomsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.deathbloomGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.toadstoolsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.toadstoolGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
		for (var2 = 0; var2 < this.portobellosPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.portobelloGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
		for (var2 = 0; var2 < this.blueMilksPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.blueMilkGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
		for (var2 = 0; var2 < this.glowshroomsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(80);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.glowshroomGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.sproutsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.sproutGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.tinyFlowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantTinyGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.glowFlowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantGlowGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.deadGrassPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantDeadGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.desertGrassPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantDesertGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        //Added
        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, GRASS);
        for (var2 = 0; doGen && var2 < this.grassPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            WorldGenerator var6 = this.biome.getRandomWorldGenForGrass(this.randomGenerator);
            var6.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.outbackPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.outbackGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.smolderingGrassPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.smolderingGrassGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.netherGrassPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.netherGrassGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.netherWartPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.netherWartGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
		for (var2 = 0; var2 < this.canyonGrassPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.canyonGrassGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.steppePerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.steppeGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.highGrassPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.highGrassGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.carrotsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.carrotGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.potatoesPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.potatoGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.reedsBOPPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            var5 = this.randomGenerator.nextInt(128);
            this.reedBOPGen.generate(this.currentWorld, this.randomGenerator, var3, var5, var4);
        }

        for (var2 = 0; var2 < this.thornsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.thornGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.cattailsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.cattailGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; var2 < this.highCattailsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.highCattailGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        //Added
        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, DEAD_BUSH);
        for (var2 = 0; doGen && var2 < this.deadBushPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            (new WorldGenDeadBush(Block.deadBush.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        //Added
        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SHROOM);
        for (var2 = 0; doGen && var2 < this.mushroomsPerChunk; ++var2)
        {
            if (this.randomGenerator.nextInt(4) == 0)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                var5 = this.currentWorld.getHeightValue(var3, var4);
                this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var3, var5, var4);
            }

            if (this.randomGenerator.nextInt(8) == 0)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                var5 = this.randomGenerator.nextInt(128);
                this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var3, var5, var4);
            }
        }

        if (this.randomGenerator.nextInt(4) == 0)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128);
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        if (this.randomGenerator.nextInt(8) == 0)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128);
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        //Added
        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, REED);
        for (var2 = 0; doGen && var2 < this.reedsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            var5 = this.randomGenerator.nextInt(128);
            this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var5, var4);
        }

        for (var2 = 0; doGen && var2 < 10; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        if (this.generatePumpkins && this.randomGenerator.nextInt(32) == 0)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128);
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        if (this.generateMelons && this.randomGenerator.nextInt(32) == 0)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128);
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            (new WorldGenMelon()).generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        if (this.generateBoulders && this.randomGenerator.nextInt(32) == 0)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128);
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            (new WorldGenBoulder()).generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        for (var2 = 0; var2 < this.cactiPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.cactusGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }

        for (var2 = 0; var2 < this.desertCactiPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.desertCactusGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
        
        if (this.generateLakes)
        {
            for (var2 = 0; var2 < 50 + pondsPerChunk; ++var2)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(120) + 8);
                var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Block.waterMoving.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
            }

            for (var2 = 0; var2 < 20; ++var2)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
                var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
            }
        }
		
        for (var2 = 0; var2 < this.lilyflowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.lilyflowerGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
        }
		
        for (var2 = 0; doGen && var2 < this.algaePerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;

            for (var5 = this.randomGenerator.nextInt(128); var5 > 0 && this.currentWorld.getBlockId(var3, var5 - 1, var4) == 0; --var5)
            {
                ;
            }

            this.algaeGen.generate(this.currentWorld, this.randomGenerator, var3, var5, var4);
        }
		
        //Added
        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LILYPAD);
        for (var2 = 0; doGen && var2 < this.waterlilyPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;

            for (var5 = this.randomGenerator.nextInt(128); var5 > 0 && this.currentWorld.getBlockId(var3, var5 - 1, var4) == 0; --var5)
            {
                ;
            }

            this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, var3, var5, var4);
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
            int var6 = this.chunk_X + this.randomGenerator.nextInt(8);
            int var7 = this.randomGenerator.nextInt(par4 - par3) + par3;
            int var8 = this.chunk_Z + this.randomGenerator.nextInt(8);
			int var999 = this.randomGenerator.nextInt(5);
			if (var999 == 0)
			{
				par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
				par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6 + 8, var7, var8);
				par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8 + 8);
				par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6 + 8, var7, var8 + 8);
			}
        }
    }

    /**
     * Standard ore generation helper. Generates most ores.
     */
    protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
    {
        for (int var5 = 0; var5 < par1; ++var5)
        {
            int var6 = this.chunk_X + this.randomGenerator.nextInt(16);
            int var7 = this.randomGenerator.nextInt(par4 - par3) + par3;
            int var8 = this.chunk_Z + this.randomGenerator.nextInt(16);
            par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
        }
    }

    /**
     * Standard ore generation helper. Generates Lapis Lazuli.
     */
    protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
    {
        for (int var5 = 0; var5 < par1; ++var5)
        {
            int var6 = this.chunk_X + this.randomGenerator.nextInt(16);
            int var7 = this.randomGenerator.nextInt(par4) + this.randomGenerator.nextInt(par4) + (par3 - par4);
            int var8 = this.chunk_Z + this.randomGenerator.nextInt(16);
            par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
        }
    }

    /**
     * Generates ores in the current chunk
     */
    protected void generateOres()
    {
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
        
		if (TerrainGen.generateOre(currentWorld, randomGenerator, dirtGen, chunk_X, chunk_Z, DIRT))
		this.genStandardOre1(20, this.dirtGen, 0, 128);
        
        if (TerrainGen.generateOre(currentWorld, randomGenerator, gravelGen, chunk_X, chunk_Z, GRAVEL))
        this.genStandardOre1(10, this.gravelGen, 0, 128);
        
        if (TerrainGen.generateOre(currentWorld, randomGenerator, coalGen, chunk_X, chunk_Z, COAL))
        this.genStandardOre1(20, this.coalGen, 0, 128);
        
        if (TerrainGen.generateOre(currentWorld, randomGenerator, ironGen, chunk_X, chunk_Z, IRON))
        this.genStandardOre1(20, this.ironGen, 0, 64);
        
        if (TerrainGen.generateOre(currentWorld, randomGenerator, goldGen, chunk_X, chunk_Z, GOLD))
        this.genStandardOre1(2, this.goldGen, 0, 32);
        
        if (TerrainGen.generateOre(currentWorld, randomGenerator, redstoneGen, chunk_X, chunk_Z, REDSTONE))
        this.genStandardOre1(8, this.redstoneGen, 0, 16);
        
        if (TerrainGen.generateOre(currentWorld, randomGenerator, diamondGen, chunk_X, chunk_Z, DIAMOND))
        this.genStandardOre1(1, this.diamondGen, 0, 16);
        
        if (TerrainGen.generateOre(currentWorld, randomGenerator, lapisGen, chunk_X, chunk_Z, LAPIS))
        this.genStandardOre2(1, this.lapisGen, 16, 16);
        
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }
}
