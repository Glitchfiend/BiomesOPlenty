/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.block.BOPBlocks.*;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.*;
import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.enums.*;
import biomesoplenty.common.fluids.blocks.*;
import biomesoplenty.common.util.BOPReflectionHelper;
import biomesoplenty.common.util.block.BlockStateUtils;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import biomesoplenty.core.BiomesOPlenty;

import com.google.common.collect.ImmutableSet;

public class ModBlocks
{
        
    // TODO: use getDrops() in classes where the drops are very specific, instead of implementing all 3 of quantityDropped() getItemDropped() and damageDropped()
    // TODO: docblocks!
    // TODO: make better use of canSustainPlant() in BlockDecoration and children
    // TODO: implement IShearable on flowers
    // TODO: investigate Block.getRenderType()
    
    public static void init()
    {
        ash_block =             registerBlock( new BlockAsh(), "ash_block" );
        bamboo =                registerBlock( new BlockBamboo(), "bamboo" );
        bone_segment =          registerBlock( new BlockBones(), "bone_segment" );
        coral =                 registerBlock( new BlockCoral(), "coral" );
        gem_block =             registerBlock( new BlockGem(), "gem_block" );
        gem_ore =               registerBlock( new BlockGemOre(), "gem_ore" );
        hive =                  registerBlock( new BlockHive(), "hive" );
        mushroom =              registerBlock( new BlockBOPMushroom(), "mushroom" );
        stone =                 registerBlock( new BlockBOPStone(), "stone" );
        mud =                   registerBlock( new BlockMud(), "mud" );
        turnip_block =          registerBlock( new BlockTurnip(), "turnip_block", null ); // no creative tab
        flesh =                 registerBlock( new BlockFlesh(), "flesh" );
        grass =                 registerBlock( new BlockBOPGrass(), "grass" );
        waterlily =             registerBlock( new BlockBOPLilypad(), "waterlily" );
        dirt =                  registerBlock( new BlockBOPDirt(), "dirt" );
        stone_formations =      registerBlock( new BlockStoneFormations(), "stone_formations" );
        fruit_block =           registerBlock( new BlockFruit(), "fruit_block" /*, creativeTab(null) */); // TODO: once the mechanism for farming fruit is established: remove creative tab
        crystal =               registerBlock( new BlockCrystal(), "crystal" );
        biome_block =           registerBlock( new BlockBiomeBlock(), "biome_block" );
        
        // generics
        bamboo_thatching =      registerBlock( (new BlockBOPGeneric(Material.wood)).setStepSound(Block.soundTypeWood).setHardness(2.0F), "bamboo_thatching"); bamboo_thatching.setHarvestLevel("axe", 0);
        ash_stone =             registerBlock( new BlockBOPGeneric(), "ash_stone" );
        crag_rock =             registerBlock( (new BlockBOPGeneric()).setStepSound(Block.soundTypeStone), "crag_rock" );
        dried_dirt =            registerBlock( new BlockBOPGeneric(), "dried_dirt"); dried_dirt.setHarvestLevel("pickaxe",0);
        hard_dirt =             registerBlock( (new BlockBOPGeneric()).setHardness(0.7F), "hard_dirt" );
        hard_ice =              registerBlock( (new BlockBOPGeneric()).setHardness(0.75F), "hard_ice" );
        hard_sand =             registerBlock( (new BlockBOPGeneric(Material.sand)).setHardness(0.9F).setStepSound(Block.soundTypeSand), "hard_sand" );
        mud_brick_block =       registerBlock( (new BlockBOPGeneric()).setResistance(2.0F), "mud_brick_block" );
        
        mud_brick_stairs =      registerBlock( BOPReflectionHelper.construct(BlockStairs.class, (IBlockState)mud_brick_block.getDefaultState()), "mud_brick_stairs");
 
        // stone and brick slabs
        // need to register items at the same time really so that they can be mapped to each other - bit messy this
        other_slab =            registerBlock( new BlockBOPHalfOtherSlab(), "other_slab");
        double_other_slab =     registerBlock( new BlockBOPDoubleOtherSlab(), "double_other_slab", null ); // no creative tab for double slab
        BOPItems.other_slab =   ModItems.registerItem( new ItemSlab(other_slab, (BlockSlab)other_slab, (BlockSlab)double_other_slab), "other_slab");
        GameData.getBlockItemMap().put(other_slab, BOPItems.other_slab);      
        GameData.getBlockItemMap().put(double_other_slab, BOPItems.other_slab);
        
        // 22 flower types 16 per BlockBOPFlower instance, needs 2 'pages'
        BlockBOPFlower.createAllPages();
        flower_0 =              registerBlock( BlockBOPFlower.paging.getBlock(0), "flower_0" );
        flower_1 =              registerBlock( BlockBOPFlower.paging.getBlock(1), "flower_1" );
                
        // 16 wood types, 4 per BlockBOPLog instance, needs 4 'pages'
        BlockBOPLog.createAllPages();
        log_0 =                 registerBlock( BlockBOPLog.paging.getBlock(0), "log_0" );
        log_1 =                 registerBlock( BlockBOPLog.paging.getBlock(1), "log_1" );
        log_2 =                 registerBlock( BlockBOPLog.paging.getBlock(2), "log_2" );
        log_3 =                 registerBlock( BlockBOPLog.paging.getBlock(3), "log_3" );
        
        // TODO: check if hellbark planks, fence etc can burn
        
        // 16 wood types, 8 per BlockBOPHalfWoodSlab and BlockBOPDoubleWoodSlab intance, needs 2 'pages'
        // need to register items at the same time really so that they can be mapped to each other - bit messy this
        BlockBOPDoubleWoodSlab.createAllPages();
        BlockBOPHalfWoodSlab.createAllPages();
        wood_slab_0 =           registerBlock( BlockBOPHalfWoodSlab.paging.getBlock(0), "wood_slab_0");
        double_wood_slab_0 =    registerBlock( BlockBOPDoubleWoodSlab.paging.getBlock(0), "double_wood_slab_0", null ); // no creative tab for double slab
        BOPItems.wood_slab_0 =  ModItems.registerItem( new ItemSlab(wood_slab_0, (BlockSlab)wood_slab_0, (BlockSlab)double_wood_slab_0), "wood_slab_0");
        GameData.getBlockItemMap().put(wood_slab_0, BOPItems.wood_slab_0);      
        GameData.getBlockItemMap().put(double_wood_slab_0, BOPItems.wood_slab_0);
        wood_slab_1 =           registerBlock( BlockBOPHalfWoodSlab.paging.getBlock(1), "wood_slab_1");
        double_wood_slab_1 =    registerBlock( BlockBOPDoubleWoodSlab.paging.getBlock(1), "double_wood_slab_1", null ); // no creative tab for double slab
        BOPItems.wood_slab_1 =  ModItems.registerItem( new ItemSlab(wood_slab_1, (BlockSlab)wood_slab_1, (BlockSlab)double_wood_slab_1), "wood_slab_1");
        GameData.getBlockItemMap().put(wood_slab_1, BOPItems.wood_slab_1);      
        GameData.getBlockItemMap().put(double_wood_slab_1, BOPItems.wood_slab_1);        
        
        // 16 wood types, 16 per BlockBOPPlanks instance, needs 1 'pages'
        BlockBOPPlanks.createAllPages();
        planks_0 =              registerBlock( BlockBOPPlanks.paging.getBlock(0), "planks_0");
        
        // 22 tree types, 4 per BlockBOPLeaves instance, needs 6 'pages'
        BlockBOPLeaves.createAllPages();
        leaves_0 =              registerBlock( BlockBOPLeaves.paging.getBlock(0), "leaves_0");
        leaves_1 =              registerBlock( BlockBOPLeaves.paging.getBlock(1), "leaves_1" );
        leaves_2 =              registerBlock( BlockBOPLeaves.paging.getBlock(2), "leaves_2" );
        leaves_3 =              registerBlock( BlockBOPLeaves.paging.getBlock(3), "leaves_3" );
        leaves_4 =              registerBlock( BlockBOPLeaves.paging.getBlock(4), "leaves_4" );
        leaves_5 =              registerBlock( BlockBOPLeaves.paging.getBlock(5), "leaves_5" );
        
        // 22 tree types, 8 per BlockBOPSapling instance, needs 3 'pages'
        BlockBOPSapling.createAllPages();
        sapling_0 =             registerBlock( BlockBOPSapling.paging.getBlock(0), "sapling_0");
        sapling_1 =             registerBlock( BlockBOPSapling.paging.getBlock(1), "sapling_1");
        sapling_2 =             registerBlock( BlockBOPSapling.paging.getBlock(2), "sapling_2");
        
        // stairs have no variant metadata, use a new BlockBOPStairs instance for each (note there's no giant_flower_stairs or dead_stairs)
        sacred_oak_stairs =     registerBlock( new BlockBOPWoodStairs(BOPWoods.SACRED_OAK), "sacred_oak_stairs" );
        cherry_stairs =         registerBlock( new BlockBOPWoodStairs(BOPWoods.CHERRY), "cherry_stairs" );
        dark_stairs =           registerBlock( new BlockBOPWoodStairs(BOPWoods.DARK), "dark_stairs" );
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
        
        // fences have no variant metadata, use a new BlockBOPFence instance for each (note there's no giant_flower_fence or dead_fence)
        sacred_oak_fence =      registerBlock( new BlockBOPFence(BOPWoods.SACRED_OAK), "sacred_oak_fence" );
        cherry_fence =          registerBlock( new BlockBOPFence(BOPWoods.CHERRY), "cherry_fence" );
        dark_fence =            registerBlock( new BlockBOPFence(BOPWoods.DARK), "dark_fence" );
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
        
        // fence gates have no variant metadata, use a new BlockBOPFenceGate instance for each (note there's no giant_flower_fence_gate or dead_fence_gate)
        sacred_oak_fence_gate = registerBlock( new BlockBOPFenceGate(BOPWoods.SACRED_OAK), "sacred_oak_fence_gate" );
        cherry_fence_gate =     registerBlock( new BlockBOPFenceGate(BOPWoods.CHERRY), "cherry_fence_gate" );
        dark_fence_gate =       registerBlock( new BlockBOPFenceGate(BOPWoods.DARK), "dark_fence_gate" );
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
        
        // doors have no variant metadata, use a new BlockBOPDoor instance for each (note there's no giant_flower_door or dead_door)
        sacred_oak_door =       registerDoor( new BlockBOPDoor(BOPWoods.SACRED_OAK), "sacred_oak_door", BOPItems.sacred_oak_door );
        cherry_door =           registerDoor( new BlockBOPDoor(BOPWoods.CHERRY), "cherry_door", BOPItems.cherry_door );
        dark_door =             registerDoor( new BlockBOPDoor(BOPWoods.DARK), "dark_door", BOPItems.dark_door );
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
        
        
        //vines
        // TODO: special placement rules?
        flower_vine =       registerBlock( new BlockBOPVine(false), "flower_vine" );
        ivy =               registerBlock( new BlockBOPVine(true), "ivy" );
        moss =              registerBlock( new BlockBOPVine(true), "moss" );
        tree_moss =         registerBlock( new BlockBOPVine(false), "tree_moss" );
        wisteria =          registerBlock( new BlockBOPVine(false), "wisteria" );
        
        BlockBOPPlant.createAllPages();
        plant_0 =           registerBlock( BlockBOPPlant.paging.getBlock(0), "plant_0" );
        plant_1 =           registerBlock( BlockBOPPlant.paging.getBlock(1), "plant_1" );
        double_plant =      registerBlock( new BlockBOPDoublePlant(), "double_plant" );
        
        honey_block =       registerBlock( new BlockHoney(), "honey_block" );
        
        
        // fluids
        // TODO: make the fluids render!  at the moment, no forge fluids are rendering in 1.8, they're invisible
        
        honey_fluid = new Fluid("honey");
        honey_fluid.setViscosity(1500);
        FluidRegistry.registerFluid(honey_fluid);
        honey = registerFluidBlock(honey_fluid, new BlockHoneyFluid(honey_fluid), "honey");
        
        blood_fluid = new Fluid("blood");
        FluidRegistry.registerFluid(blood_fluid);
        blood = registerFluidBlock(blood_fluid, new BlockBloodFluid(blood_fluid), "blood");
        
        poison_fluid = new Fluid("poison");
        FluidRegistry.registerFluid(poison_fluid);
        poison = registerFluidBlock(poison_fluid, new BlockPoisonFluid(poison_fluid), "poison");
        
        
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
            
            BiomesOPlenty.proxy.registerNonRenderingProperties(block);
            
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
