/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.block.BOPBlocks.*;
import static biomesoplenty.api.item.BOPItems.blood_bucket;
import static biomesoplenty.api.item.BOPItems.honey_bucket;
import static biomesoplenty.api.item.BOPItems.hot_spring_water_bucket;
import static biomesoplenty.api.item.BOPItems.poison_bucket;

import com.google.common.collect.ImmutableSet;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.BlockBOPAsh;
import biomesoplenty.common.block.BlockBOPBamboo;
import biomesoplenty.common.block.BlockBOPBiomeBlock;
import biomesoplenty.common.block.BlockBOPBones;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPCrystal;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPDoor;
import biomesoplenty.common.block.BlockBOPDoubleOtherSlab;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPDoubleWoodSlab;
import biomesoplenty.common.block.BlockBOPFarmland;
import biomesoplenty.common.block.BlockBOPFence;
import biomesoplenty.common.block.BlockBOPFenceGate;
import biomesoplenty.common.block.BlockBOPFlesh;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPGem;
import biomesoplenty.common.block.BlockBOPGemOre;
import biomesoplenty.common.block.BlockBOPGeneric;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPHalfOtherSlab;
import biomesoplenty.common.block.BlockBOPHalfWoodSlab;
import biomesoplenty.common.block.BlockBOPHive;
import biomesoplenty.common.block.BlockBOPHoney;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPMud;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlanks;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockBOPSand;
import biomesoplenty.common.block.BlockBOPSapling;
import biomesoplenty.common.block.BlockBOPSeaweed;
import biomesoplenty.common.block.BlockBOPStone;
import biomesoplenty.common.block.BlockBOPTerrarium;
import biomesoplenty.common.block.BlockBOPTurnip;
import biomesoplenty.common.block.BlockBOPVine;
import biomesoplenty.common.block.BlockBOPWoodStairs;
import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.fluids.BloodFluid;
import biomesoplenty.common.fluids.HoneyFluid;
import biomesoplenty.common.fluids.HotSpringWaterFluid;
import biomesoplenty.common.fluids.PoisonFluid;
import biomesoplenty.common.fluids.blocks.BlockBloodFluid;
import biomesoplenty.common.fluids.blocks.BlockHoneyFluid;
import biomesoplenty.common.fluids.blocks.BlockHotSpringWaterFluid;
import biomesoplenty.common.fluids.blocks.BlockPoisonFluid;
import biomesoplenty.common.util.BOPReflectionHelper;
import biomesoplenty.common.util.block.BlockStateUtils;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks
{
    // TODO: use getDrops() in classes where the drops are very specific, instead of implementing all 3 of quantityDropped() getItemDropped() and damageDropped()
    // TODO: docblocks!
    // TODO: make better use of canSustainPlant() in BlockDecoration and children
    // TODO: implement IShearable on flowers
    // TODO: investigate Block.getRenderType()
    
    public static void init()
    {
    	//Terrain Blocks
        grass =                 registerBlock( new BlockBOPGrass(), "grass" );
        dirt =                  registerBlock( new BlockBOPDirt(), "dirt" );
        
        BlockBOPFarmland.createAllPages();
        farmland_0 =              registerBlock( BlockBOPFarmland.paging.getBlock(0), "farmland_0", null);
        farmland_1 =              registerBlock( BlockBOPFarmland.paging.getBlock(1), "farmland_1", null);
        
        stone =                 registerBlock( new BlockBOPStone(), "stone" );
        crag_rock =             registerBlock( (new BlockBOPGeneric()), "crag_rock" );
        dried_sand =            registerBlock( (new BlockBOPGeneric()).addSupportedPlantType(EnumPlantType.Desert), "dried_sand"); dried_sand.setHarvestLevel("pickaxe",0);
        hard_ice =              registerBlock( (new BlockBOPGeneric(Material.packedIce, SoundType.STONE)).setHardness(0.75F), "hard_ice" );
        ash_block =             registerBlock( new BlockBOPAsh(), "ash_block" );
        mud =                   registerBlock( new BlockBOPMud(), "mud" );
        sand =                  registerBlock( new BlockBOPSand(), "sand" );
        flesh =                 registerBlock( new BlockBOPFlesh(), "flesh" );
        
        //Building Blocks
        crystal =               registerBlock( new BlockBOPCrystal(), "crystal" );
        biome_block =           registerBlock( new BlockBOPBiomeBlock(), "biome_block" );
        gem_ore =               registerBlock( new BlockBOPGemOre(), "gem_ore" );
        gem_block =             registerBlock( new BlockBOPGem(), "gem_block" );
        hive =                  registerBlock( new BlockBOPHive(), "hive" );
        honey_block =       registerBlock( new BlockBOPHoney(), "honey_block" );
        bone_segment =          registerBlock( new BlockBOPBones(), "bone_segment" );
        
        //Material Blocks
        bamboo_thatching =      registerBlock( (new BlockBOPGeneric(Material.wood, SoundType.WOOD)).setHardness(2.0F), "bamboo_thatching"); bamboo_thatching.setHarvestLevel("axe", 0);
        mud_brick_block =       registerBlock( (new BlockBOPGeneric()).setResistance(2.0F), "mud_brick_block" );   
        mud_brick_stairs =      registerBlock( BOPReflectionHelper.construct(BlockStairs.class, (IBlockState)mud_brick_block.getDefaultState()), "mud_brick_stairs");
 
        //Stone Slabs
        // need to register items at the same time really so that they can be mapped to each other - bit messy this
        other_slab =            registerBlock( new BlockBOPHalfOtherSlab(), "other_slab");
        double_other_slab =     registerBlock( new BlockBOPDoubleOtherSlab(), "double_other_slab", null ); // no creative tab for double slab
        BOPItems.other_slab =   ModItems.registerItem( new ItemSlab(other_slab, (BlockSlab)other_slab, (BlockSlab)double_other_slab), "other_slab");
        
        // Logs
        // 16 wood types, 4 per BlockBOPLog instance, needs 4 'pages'
        BlockBOPLog.createAllPages();
        log_0 =                 registerBlock( BlockBOPLog.paging.getBlock(0), "log_0" );
        log_1 =                 registerBlock( BlockBOPLog.paging.getBlock(1), "log_1" );
        log_2 =                 registerBlock( BlockBOPLog.paging.getBlock(2), "log_2" );
        log_3 =                 registerBlock( BlockBOPLog.paging.getBlock(3), "log_3" );
        log_4 =                 registerBlock( BlockBOPLog.paging.getBlock(4), "log_4" );    
        
        //Leaves
        // 22 tree types, 4 per BlockBOPLeaves instance, needs 6 'pages'
        BlockBOPLeaves.createAllPages();
        leaves_0 =              registerBlock( BlockBOPLeaves.paging.getBlock(0), "leaves_0");
        leaves_1 =              registerBlock( BlockBOPLeaves.paging.getBlock(1), "leaves_1" );
        leaves_2 =              registerBlock( BlockBOPLeaves.paging.getBlock(2), "leaves_2" );
        leaves_3 =              registerBlock( BlockBOPLeaves.paging.getBlock(3), "leaves_3" );
        leaves_4 =              registerBlock( BlockBOPLeaves.paging.getBlock(4), "leaves_4" );
        leaves_5 =              registerBlock( BlockBOPLeaves.paging.getBlock(5), "leaves_5" );
        leaves_6 =              registerBlock( BlockBOPLeaves.paging.getBlock(6), "leaves_6" );
        
        // 16 wood types, 16 per BlockBOPPlanks instance, needs 1 'pages'
        BlockBOPPlanks.createAllPages();
        planks_0 =              registerBlock( BlockBOPPlanks.paging.getBlock(0), "planks_0");
        
        // stairs have no variant metadata, use a new BlockBOPStairs instance for each (note there's no giant_flower_stairs or dead_stairs)
        sacred_oak_stairs =     registerBlock( new BlockBOPWoodStairs(BOPWoods.SACRED_OAK), "sacred_oak_stairs" );
        cherry_stairs =         registerBlock( new BlockBOPWoodStairs(BOPWoods.CHERRY), "cherry_stairs" );
        umbran_stairs =           registerBlock( new BlockBOPWoodStairs(BOPWoods.UMBRAN), "umbran_stairs" );
        fir_stairs =            registerBlock( new BlockBOPWoodStairs(BOPWoods.FIR), "fir_stairs" );
        ethereal_stairs =       registerBlock( new BlockBOPWoodStairs(BOPWoods.ETHEREAL), "ethereal_stairs" );
        magic_stairs =          registerBlock( new BlockBOPWoodStairs(BOPWoods.MAGIC), "magic_stairs" );
        mangrove_stairs =       registerBlock( new BlockBOPWoodStairs(BOPWoods.MANGROVE), "mangrove_stairs" );
        palm_stairs =           registerBlock( new BlockBOPWoodStairs(BOPWoods.PALM), "palm_stairs" );
        redwood_stairs =        registerBlock( new BlockBOPWoodStairs(BOPWoods.REDWOOD), "redwood_stairs" );
        willow_stairs =         registerBlock( new BlockBOPWoodStairs(BOPWoods.WILLOW), "willow_stairs" );
        pine_stairs =           registerBlock( new BlockBOPWoodStairs(BOPWoods.PINE), "pine_stairs" );
        hellbark_stairs =       registerBlock( new BlockBOPWoodStairs(BOPWoods.HELLBARK), "hellbark_stairs" );
        jacaranda_stairs =      registerBlock( new BlockBOPWoodStairs(BOPWoods.JACARANDA), "jacaranda_stairs" );
        mahogany_stairs =       registerBlock( new BlockBOPWoodStairs(BOPWoods.MAHOGANY), "mahogany_stairs" );
        ebony_stairs =       	registerBlock( new BlockBOPWoodStairs(BOPWoods.EBONY), "ebony_stairs" );
        eucalyptus_stairs =     registerBlock( new BlockBOPWoodStairs(BOPWoods.EUCALYPTUS), "eucalyptus_stairs" );
        
        // 16 wood types, 8 per BlockBOPHalfWoodSlab and BlockBOPDoubleWoodSlab intance, needs 2 'pages'
        // need to register items at the same time really so that they can be mapped to each other - bit messy this
        BlockBOPDoubleWoodSlab.createAllPages();
        BlockBOPHalfWoodSlab.createAllPages();
        wood_slab_0 =           registerBlock( BlockBOPHalfWoodSlab.paging.getBlock(0), "wood_slab_0");
        double_wood_slab_0 =    registerBlock( BlockBOPDoubleWoodSlab.paging.getBlock(0), "double_wood_slab_0", null ); // no creative tab for double slab
        BOPItems.wood_slab_0 =  ModItems.registerItem( new ItemSlab(wood_slab_0, (BlockSlab)wood_slab_0, (BlockSlab)double_wood_slab_0), "wood_slab_0");

        wood_slab_1 =           registerBlock( BlockBOPHalfWoodSlab.paging.getBlock(1), "wood_slab_1");
        double_wood_slab_1 =    registerBlock( BlockBOPDoubleWoodSlab.paging.getBlock(1), "double_wood_slab_1", null ); // no creative tab for double slab
        BOPItems.wood_slab_1 =  ModItems.registerItem( new ItemSlab(wood_slab_1, (BlockSlab)wood_slab_1, (BlockSlab)double_wood_slab_1), "wood_slab_1"); 
        
        // fences have no variant metadata, use a new BlockBOPFence instance for each (note there's no giant_flower_fence or dead_fence)
        sacred_oak_fence =      registerBlock( new BlockBOPFence(BOPWoods.SACRED_OAK), "sacred_oak_fence" );
        cherry_fence =          registerBlock( new BlockBOPFence(BOPWoods.CHERRY), "cherry_fence" );
        umbran_fence =            registerBlock( new BlockBOPFence(BOPWoods.UMBRAN), "umbran_fence" );
        fir_fence =             registerBlock( new BlockBOPFence(BOPWoods.FIR), "fir_fence" );
        ethereal_fence =        registerBlock( new BlockBOPFence(BOPWoods.ETHEREAL), "ethereal_fence" );
        magic_fence =           registerBlock( new BlockBOPFence(BOPWoods.MAGIC), "magic_fence" );
        mangrove_fence =        registerBlock( new BlockBOPFence(BOPWoods.MANGROVE), "mangrove_fence" );
        palm_fence =            registerBlock( new BlockBOPFence(BOPWoods.PALM), "palm_fence" );
        redwood_fence =         registerBlock( new BlockBOPFence(BOPWoods.REDWOOD), "redwood_fence" );
        willow_fence =          registerBlock( new BlockBOPFence(BOPWoods.WILLOW), "willow_fence" );
        pine_fence =            registerBlock( new BlockBOPFence(BOPWoods.PINE), "pine_fence" );
        hellbark_fence =        registerBlock( new BlockBOPFence(BOPWoods.HELLBARK), "hellbark_fence" );
        jacaranda_fence =       registerBlock( new BlockBOPFence(BOPWoods.JACARANDA), "jacaranda_fence" );
        mahogany_fence =        registerBlock( new BlockBOPFence(BOPWoods.MAHOGANY), "mahogany_fence" );
        ebony_fence =        	registerBlock( new BlockBOPFence(BOPWoods.EBONY), "ebony_fence" );
        eucalyptus_fence =      registerBlock( new BlockBOPFence(BOPWoods.EUCALYPTUS), "eucalyptus_fence" );
        
        // fence gates have no variant metadata, use a new BlockBOPFenceGate instance for each (note there's no giant_flower_fence_gate or dead_fence_gate)
        sacred_oak_fence_gate = registerBlock( new BlockBOPFenceGate(BOPWoods.SACRED_OAK), "sacred_oak_fence_gate" );
        cherry_fence_gate =     registerBlock( new BlockBOPFenceGate(BOPWoods.CHERRY), "cherry_fence_gate" );
        umbran_fence_gate =       registerBlock( new BlockBOPFenceGate(BOPWoods.UMBRAN), "umbran_fence_gate" );
        fir_fence_gate =        registerBlock( new BlockBOPFenceGate(BOPWoods.FIR), "fir_fence_gate" );
        ethereal_fence_gate =   registerBlock( new BlockBOPFenceGate(BOPWoods.ETHEREAL), "ethereal_fence_gate" );
        magic_fence_gate =      registerBlock( new BlockBOPFenceGate(BOPWoods.MAGIC), "magic_fence_gate" );
        mangrove_fence_gate =   registerBlock( new BlockBOPFenceGate(BOPWoods.MANGROVE), "mangrove_fence_gate" );
        palm_fence_gate =       registerBlock( new BlockBOPFenceGate(BOPWoods.PALM), "palm_fence_gate" );
        redwood_fence_gate =    registerBlock( new BlockBOPFenceGate(BOPWoods.REDWOOD), "redwood_fence_gate" );
        willow_fence_gate =     registerBlock( new BlockBOPFenceGate(BOPWoods.WILLOW), "willow_fence_gate" );
        pine_fence_gate =       registerBlock( new BlockBOPFenceGate(BOPWoods.PINE), "pine_fence_gate" );
        hellbark_fence_gate =   registerBlock( new BlockBOPFenceGate(BOPWoods.HELLBARK), "hellbark_fence_gate" );
        jacaranda_fence_gate =  registerBlock( new BlockBOPFenceGate(BOPWoods.JACARANDA), "jacaranda_fence_gate" );
        mahogany_fence_gate =   registerBlock( new BlockBOPFenceGate(BOPWoods.MAHOGANY), "mahogany_fence_gate" );
        ebony_fence_gate =   	registerBlock( new BlockBOPFenceGate(BOPWoods.EBONY), "ebony_fence_gate" );
        eucalyptus_fence_gate = registerBlock( new BlockBOPFenceGate(BOPWoods.EUCALYPTUS), "eucalyptus_fence_gate" );
        
        // doors have no variant metadata, use a new BlockBOPDoor instance for each (note there's no giant_flower_door or dead_door)
        sacred_oak_door =       registerDoor( new BlockBOPDoor(BOPWoods.SACRED_OAK), "sacred_oak_door", BOPItems.sacred_oak_door );
        cherry_door =           registerDoor( new BlockBOPDoor(BOPWoods.CHERRY), "cherry_door", BOPItems.cherry_door );
        umbran_door =             registerDoor( new BlockBOPDoor(BOPWoods.UMBRAN), "umbran_door", BOPItems.umbran_door );
        fir_door =              registerDoor( new BlockBOPDoor(BOPWoods.FIR), "fir_door", BOPItems.fir_door );
        ethereal_door =         registerDoor( new BlockBOPDoor(BOPWoods.ETHEREAL), "ethereal_door", BOPItems.ethereal_door );
        magic_door =            registerDoor( new BlockBOPDoor(BOPWoods.MAGIC), "magic_door", BOPItems.magic_door );
        mangrove_door =         registerDoor( new BlockBOPDoor(BOPWoods.MANGROVE), "mangrove_door", BOPItems.mangrove_door );
        palm_door =             registerDoor( new BlockBOPDoor(BOPWoods.PALM), "palm_door", BOPItems.palm_door );
        redwood_door =          registerDoor( new BlockBOPDoor(BOPWoods.REDWOOD), "redwood_door", BOPItems.redwood_door );
        willow_door =           registerDoor( new BlockBOPDoor(BOPWoods.WILLOW), "willow_door", BOPItems.willow_door );
        pine_door =             registerDoor( new BlockBOPDoor(BOPWoods.PINE), "pine_door", BOPItems.pine_door );
        hellbark_door =         registerDoor( new BlockBOPDoor(BOPWoods.HELLBARK), "hellbark_door", BOPItems.hellbark_door );
        jacaranda_door =        registerDoor( new BlockBOPDoor(BOPWoods.JACARANDA), "jacaranda_door", BOPItems.jacaranda_door );
        mahogany_door =         registerDoor( new BlockBOPDoor(BOPWoods.MAHOGANY), "mahogany_door", BOPItems.mahogany_door );
        ebony_door =         	registerDoor( new BlockBOPDoor(BOPWoods.EBONY), "ebony_door", BOPItems.ebony_door );
        eucalyptus_door =       registerDoor( new BlockBOPDoor(BOPWoods.EUCALYPTUS), "eucalyptus_door", BOPItems.eucalyptus_door );
        
        // Plants
        
        coral =                 registerBlock( new BlockBOPCoral(), "coral" );
        seaweed =               registerBlock( new BlockBOPSeaweed(), "seaweed" );
        waterlily =             registerBlock( new BlockBOPLilypad(), "waterlily" );
        bamboo =                registerBlock( new BlockBOPBamboo(), "bamboo" );
        turnip_block =          registerBlock( new BlockBOPTurnip(), "turnip_block", null ); // no creative tab

        // 22 tree types, 8 per BlockBOPSapling instance, needs 3 'pages'
        BlockBOPSapling.createAllPages();
        sapling_0 =             registerBlock( BlockBOPSapling.paging.getBlock(0), "sapling_0");
        sapling_1 =             registerBlock( BlockBOPSapling.paging.getBlock(1), "sapling_1");
        sapling_2 =             registerBlock( BlockBOPSapling.paging.getBlock(2), "sapling_2");
        
        //Plants
        BlockBOPPlant.createAllPages();
        plant_0 =           registerBlock( BlockBOPPlant.paging.getBlock(0), "plant_0" );
        plant_1 =           registerBlock( BlockBOPPlant.paging.getBlock(1), "plant_1" );
        double_plant =      registerBlock( new BlockBOPDoublePlant(), "double_plant" );
        mushroom =              registerBlock( new BlockBOPMushroom(), "mushroom" );
        
        // 22 flower types 16 per BlockBOPFlower instance, needs 2 'pages'
        BlockBOPFlower.createAllPages();
        flower_0 =              registerBlock( BlockBOPFlower.paging.getBlock(0), "flower_0" );
        flower_1 =              registerBlock( BlockBOPFlower.paging.getBlock(1), "flower_1" );
        
        //vines
        // TODO: special placement rules?
        flower_vine =       registerBlock( new BlockBOPVine(false), "flower_vine" );
        ivy =               registerBlock( new BlockBOPVine(true), "ivy" );
        tree_moss =         registerBlock( new BlockBOPVine(false), "tree_moss" );
        
        terrarium =        registerBlock( new BlockBOPTerrarium(), "terrarium" );
        
        
        // fluids
        
        honey_fluid = HoneyFluid.instance;
        honey_fluid.setViscosity(1500);
        FluidRegistry.registerFluid(honey_fluid);
        honey = registerFluidBlock(honey_fluid, new BlockHoneyFluid(honey_fluid), "honey");
        
        blood_fluid = BloodFluid.instance;
        FluidRegistry.registerFluid(blood_fluid);
        blood = registerFluidBlock(blood_fluid, new BlockBloodFluid(blood_fluid), "blood");
        
        poison_fluid = PoisonFluid.instance;
        FluidRegistry.registerFluid(poison_fluid);
        poison = registerFluidBlock(poison_fluid, new BlockPoisonFluid(poison_fluid), "poison");
        
        hot_spring_water_fluid = HotSpringWaterFluid.instance;
        FluidRegistry.registerFluid(hot_spring_water_fluid);
        hot_spring_water = registerFluidBlock(hot_spring_water_fluid, new BlockHotSpringWaterFluid(hot_spring_water_fluid), "hot_spring_water");
        
        honey_bucket = ModItems.registerItem((new ItemBucket(honey)).setContainerItem(Items.bucket), "honey_bucket");
        blood_bucket = ModItems.registerItem((new ItemBucket(blood)).setContainerItem(Items.bucket), "blood_bucket");
        poison_bucket = ModItems.registerItem((new ItemBucket(poison)).setContainerItem(Items.bucket), "poison_bucket");
        hot_spring_water_bucket = ModItems.registerItem((new ItemBucket(hot_spring_water)).setContainerItem(Items.bucket), "hot_spring_water_bucket");
    
        FluidContainerRegistry.registerFluidContainer(honey_fluid, new ItemStack(honey_bucket));
        FluidContainerRegistry.registerFluidContainer(blood_fluid, new ItemStack(blood_bucket));
        FluidContainerRegistry.registerFluidContainer(poison_fluid, new ItemStack(poison_bucket));
        FluidContainerRegistry.registerFluidContainer(hot_spring_water_fluid, new ItemStack(hot_spring_water_bucket));
    }

    
    public static Block registerFluidBlock(Fluid fluid, BlockFluidBase fluidBlock, String name)
    {
        Block block = GameRegistry.registerBlock(fluidBlock, null, name);
        BiomesOPlenty.proxy.registerFluidBlockRendering(block, name);
        fluid.setBlock(fluidBlock);
        return block;
    }
    
    
    // use a separate function for registering doors because the door block and item need to be registered together
    public static Block registerDoor(BlockBOPDoor door_block, String name, Item door_item)
    {
        Block block = registerBlock( door_block, name + "_block", null );
        door_item = ModItems.registerItem( new ItemDoor(block), name );
        door_block.setDoorItem(door_item);
        return block;
    }
    
    
    public static void registerBlockVariant(Block block, String stateName, int stateMeta)
    {
        Item item = Item.getItemFromBlock(block);
        BiomesOPlenty.proxy.registerItemVariantModel(item, stateName, stateMeta);

        BOPCommand.blockCount++;
    }
    
    public static Block registerBlock(Block block, String blockName)
    {
        // by default, set the creative tab for all blocks added in BOP to CreativeTabBOP.instance
        return registerBlock(block, blockName, CreativeTabBOP.instance);
    }
    
    public static Block registerBlock(Block block, String blockName, CreativeTabs tab)
    {

        block.setUnlocalizedName(blockName);        
        block.setCreativeTab(tab);
        
        if (block instanceof IBOPBlock)
        {
            // if this block supports the IBOPBlock interface then we can determine the item block class, and sub-blocks automatically
            IBOPBlock bopBlock = (IBOPBlock)block;
            GameRegistry.registerBlock(block, bopBlock.getItemClass(), blockName);
            
            BiomesOPlenty.proxy.registerBlockSided(block);
            
            // check for missing default states
            IBlockState defaultState = block.getDefaultState();
            if (defaultState == null)
            {
                defaultState = block.getBlockState().getBaseState();
                BiomesOPlenty.logger.error("missing default state for " + block.getUnlocalizedName());
            }
            
            // get the preset blocks variants
            ImmutableSet<IBlockState> presets = BlockStateUtils.getBlockPresets(block);
            if (presets.isEmpty())
            {
                // block has no sub-blocks to register
                registerBlockVariant(block, blockName, 0);
            }
            else
            {
                // register all the sub-blocks
                for (IBlockState state : presets)
                {
                    String stateName = bopBlock.getStateName(state);
                    int stateMeta = block.getMetaFromState(state);
                    registerBlockVariant(block, stateName, stateMeta);
                }
            }
        }
        else
        {
            // for vanilla blocks, just register a single variant with meta=0 and assume ItemBlock for the item class
            GameRegistry.registerBlock(block, ItemBlock.class , blockName);
            registerBlockVariant(block, blockName, 0);
        }

        return block;
    }
    
}
