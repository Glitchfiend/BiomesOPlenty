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
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import biomesoplenty.api.block.BOPWoodEnums.AllWoods;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.BlockAsh;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPDoor;
import biomesoplenty.common.block.BlockBOPFence;
import biomesoplenty.common.block.BlockBOPFenceGate;
import biomesoplenty.common.block.BlockBOPFlower1;
import biomesoplenty.common.block.BlockBOPFlower2;
import biomesoplenty.common.block.BlockBOPGeneric;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlanks;
import biomesoplenty.common.block.BlockBOPSapling;
import biomesoplenty.common.block.BlockBOPStairs;
import biomesoplenty.common.block.BlockBOPStone;
import biomesoplenty.common.block.BlockBOPVine;
import biomesoplenty.common.block.BlockBamboo;
import biomesoplenty.common.block.BlockBones;
import biomesoplenty.common.block.BlockCoral;
import biomesoplenty.common.block.BlockCrystal;
import biomesoplenty.common.block.BlockDoubleFoliage;
import biomesoplenty.common.block.BlockFlesh;
import biomesoplenty.common.block.BlockFoliage;
import biomesoplenty.common.block.BlockFruit;
import biomesoplenty.common.block.BlockGem;
import biomesoplenty.common.block.BlockGemOre;
import biomesoplenty.common.block.BlockHive;
import biomesoplenty.common.block.BlockMud;
import biomesoplenty.common.block.BlockStoneFormations;
import biomesoplenty.common.block.BlockTurnip;
import biomesoplenty.common.handler.GuiEventHandler;
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
        flower1 =               registerBlock( new BlockBOPFlower1(), "flower1" );
        flower2 =               registerBlock( new BlockBOPFlower2(), "flower2" );
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
        
        // generics
        ash_stone =             registerBlock( new BlockBOPGeneric(), "ash_stone" );
        crag_rock =             registerBlock( (new BlockBOPGeneric()).setStepSound(Block.soundTypeStone), "crag_rock" );
        dried_dirt =            registerBlock( new BlockBOPGeneric(), "dried_dirt"); dried_dirt.setHarvestLevel("pickaxe",0);
        hard_dirt =             registerBlock( (new BlockBOPGeneric()).setHardness(0.7F), "hard_dirt" );
        hard_ice =              registerBlock( (new BlockBOPGeneric()).setHardness(0.75F), "hard_ice" );
        hard_sand =             registerBlock( (new BlockBOPGeneric(Material.sand)).setHardness(0.9F).setStepSound(Block.soundTypeSand), "hard_sand" );
        mud_brick =             registerBlock( (new BlockBOPGeneric()).setResistance(2.0F), "mud_brick" );
 
        // 16 wood types, 4 per BlockBOPLog instance, needs 4 'pages'
        log_0 =                 registerBlock( new BlockBOPLog(0), "log_0" );
        log_1 =                 registerBlock( new BlockBOPLog(1), "log_1" );
        log_2 =                 registerBlock( new BlockBOPLog(2), "log_2" );
        log_3 =                 registerBlock( new BlockBOPLog(3), "log_3" );
        // 22 tree types, 4 per BlockBOPLeaves instance, needs 6 'pages'
        leaves_0 =              registerBlock( new BlockBOPLeaves(0), "leaves_0");
        leaves_1 =              registerBlock( new BlockBOPLeaves(1), "leaves_1" );
        leaves_2 =              registerBlock( new BlockBOPLeaves(2), "leaves_2" );
        leaves_3 =              registerBlock( new BlockBOPLeaves(3), "leaves_3" );
        leaves_4 =              registerBlock( new BlockBOPLeaves(4), "leaves_4" );
        leaves_5 =              registerBlock( new BlockBOPLeaves(5), "leaves_5" );
        // 22 tree types, 8 per BlockBOPSapling instance, needs 3 'pages'
        sapling_0 =             registerBlock( new BlockBOPSapling(0), "sapling_0");
        sapling_1 =             registerBlock( new BlockBOPSapling(1), "sapling_1");
        sapling_2 =             registerBlock( new BlockBOPSapling(2), "sapling_2");
        // TODO: check if hellbark planks, fence etc can burn
        // TODO: wooden slabs
        // 16 wood types, 16 per BlockBOPPlanks instance, needs 1 'pages'
        planks_0 =              registerBlock( new BlockBOPPlanks(0), "planks_0");
        // stairs have no variant metadata, use a new BlockBOPStairs instance for each (note there's no giant_flower_stairs or dead_stairs)
        sacred_oak_stairs =     registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.SACRED_OAK)), "sacred_oak_stairs" );
        cherry_stairs =         registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.CHERRY)), "cherry_stairs" );
        dark_stairs =           registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.DARK)), "dark_stairs" );
        fir_stairs =            registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.FIR)), "fir_stairs" );
        ethereal_stairs =       registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.ETHEREAL)), "ethereal_stairs" );
        magic_stairs =          registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.MAGIC)), "magic_stairs" );
        mangrove_stairs =       registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.MANGROVE)), "mangrove_stairs" );
        palm_stairs =           registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.PALM)), "palm_stairs" );
        redwood_stairs =        registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.REDWOOD)), "redwood_stairs" );
        willow_stairs =         registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.WILLOW)), "willow_stairs" );
        pine_stairs =           registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.PINE)), "pine_stairs" );
        hell_bark_stairs =      registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.HELL_BARK)), "hell_bark_stairs" );
        jacaranda_stairs =      registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.JACARANDA)), "jacaranda_stairs" );
        mahogany_stairs =       registerBlock( new BlockBOPStairs(((BlockBOPPlanks)planks_0).getStateByWood(AllWoods.MAHOGANY)), "mahogany_stairs" );
        // fences have no variant metadata, use a new BlockBOPFence instance for each (note there's no giant_flower_fence or dead_fence)
        sacred_oak_fence =      registerBlock( new BlockBOPFence(), "sacred_oak_fence" );
        cherry_fence =          registerBlock( new BlockBOPFence(), "cherry_fence" );
        dark_fence =            registerBlock( new BlockBOPFence(), "dark_fence" );
        fir_fence =             registerBlock( new BlockBOPFence(), "fir_fence" );
        ethereal_fence =        registerBlock( new BlockBOPFence(), "ethereal_fence" );
        magic_fence =           registerBlock( new BlockBOPFence(), "magic_fence" );
        mangrove_fence =        registerBlock( new BlockBOPFence(), "mangrove_fence" );
        palm_fence =            registerBlock( new BlockBOPFence(), "palm_fence" );
        redwood_fence =         registerBlock( new BlockBOPFence(), "redwood_fence" );
        willow_fence =          registerBlock( new BlockBOPFence(), "willow_fence" );
        pine_fence =            registerBlock( new BlockBOPFence(), "pine_fence" );
        hell_bark_fence =       registerBlock( new BlockBOPFence(), "hell_bark_fence" );
        jacaranda_fence =       registerBlock( new BlockBOPFence(), "jacaranda_fence" );
        mahogany_fence =        registerBlock( new BlockBOPFence(), "mahogany_fence" );
        // fence gates have no variant metadata, use a new BlockBOPFenceGate instance for each (note there's no giant_flower_fence_gate or dead_fence_gate)
        sacred_oak_fence_gate = registerBlock( new BlockBOPFenceGate(), "sacred_oak_fence_gate" );
        cherry_fence_gate =     registerBlock( new BlockBOPFenceGate(), "cherry_fence_gate" );
        dark_fence_gate =       registerBlock( new BlockBOPFenceGate(), "dark_fence_gate" );
        fir_fence_gate =        registerBlock( new BlockBOPFenceGate(), "fir_fence_gate" );
        ethereal_fence_gate =   registerBlock( new BlockBOPFenceGate(), "ethereal_fence_gate" );
        magic_fence_gate =      registerBlock( new BlockBOPFenceGate(), "magic_fence_gate" );
        mangrove_fence_gate =   registerBlock( new BlockBOPFenceGate(), "mangrove_fence_gate" );
        palm_fence_gate =       registerBlock( new BlockBOPFenceGate(), "palm_fence_gate" );
        redwood_fence_gate =    registerBlock( new BlockBOPFenceGate(), "redwood_fence_gate" );
        willow_fence_gate =     registerBlock( new BlockBOPFenceGate(), "willow_fence_gate" );
        pine_fence_gate =       registerBlock( new BlockBOPFenceGate(), "pine_fence_gate" );
        hell_bark_fence_gate =  registerBlock( new BlockBOPFenceGate(), "hell_bark_fence_gate" );
        jacaranda_fence_gate =  registerBlock( new BlockBOPFenceGate(), "jacaranda_fence_gate" );
        mahogany_fence_gate =   registerBlock( new BlockBOPFenceGate(), "mahogany_fence_gate" );
        // doors have no variant metadata, use a new BlockBOPDoor instance for each (note there's no giant_flower_door or dead_door)
        sacred_oak_door =       registerDoor( new BlockBOPDoor(), "sacred_oak_door", BOPItems.sacred_oak_door );
        cherry_door =           registerDoor( new BlockBOPDoor(), "cherry_door", BOPItems.cherry_door );
        dark_door =             registerDoor( new BlockBOPDoor(), "dark_door", BOPItems.dark_door );
        fir_door =              registerDoor( new BlockBOPDoor(), "fir_door", BOPItems.fir_door );
        ethereal_door =         registerDoor( new BlockBOPDoor(), "ethereal_door", BOPItems.ethereal_door );
        magic_door =            registerDoor( new BlockBOPDoor(), "magic_door", BOPItems.magic_door );
        mangrove_door =         registerDoor( new BlockBOPDoor(), "mangrove_door", BOPItems.mangrove_door );
        palm_door =             registerDoor( new BlockBOPDoor(), "palm_door", BOPItems.palm_door );
        redwood_door =          registerDoor( new BlockBOPDoor(), "redwood_door", BOPItems.redwood_door );
        willow_door =           registerDoor( new BlockBOPDoor(), "willow_door", BOPItems.willow_door );
        pine_door =             registerDoor( new BlockBOPDoor(), "pine_door", BOPItems.pine_door );
        hell_bark_door =        registerDoor( new BlockBOPDoor(), "hell_bark_door", BOPItems.hell_bark_door );
        jacaranda_door =        registerDoor( new BlockBOPDoor(), "jacaranda_door", BOPItems.jacaranda_door );
        mahogany_door =         registerDoor( new BlockBOPDoor(), "mahogany_door", BOPItems.mahogany_door );
 
        
        //vines
        // TODO: special placement rules?
        flower_vine =       registerBlock( new BlockBOPVine(false), "flower_vine" );
        ivy =               registerBlock( new BlockBOPVine(true), "ivy" );
        moss =              registerBlock( new BlockBOPVine(true), "moss" );
        tree_moss =         registerBlock( new BlockBOPVine(false), "tree_moss" );
        wisteria =          registerBlock( new BlockBOPVine(false), "wisteria" );
        
        foliage =           registerBlock( new BlockFoliage(), "foliage" );
        double_foliage = registerBlock( new BlockDoubleFoliage(), "double_foliage" );
        
        
    }
    
    
    public static Block registerDoor(Block door_block, String name, Item door_item)
    {
        door_block = registerBlock( new BlockBOPDoor(), name + "_block", null );
        door_item = ModItems.registerItem( new ItemDoor(door_block), name );
        ((BlockBOPDoor)door_block).setDoorItem(door_item);
        return door_block;
    }
    
    public static void registerBlockVariant(Block block, String stateName, int stateMeta)
    {
        Item item = Item.getItemFromBlock(block);
        if (item != null && FMLCommonHandler.instance().getSide() == Side.CLIENT) { 
            ModelBakery.addVariantName(item, BiomesOPlenty.MOD_ID + ":" + stateName);
            ModelLoader.setCustomModelResourceLocation(item, stateMeta, new ModelResourceLocation(BiomesOPlenty.MOD_ID + ":" + stateName, "inventory"));
        }

        GuiEventHandler.blockCount++;
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
                registerBlockVariant(block, blockName, block.getMetaFromState(defaultState));
            }
            else
            {
                // register all the sub-blocks
                for (IBlockState state : presets)
                {
                    String stateName = bopBlock.getStateName(state);
                    if (stateName.startsWith("unused")) {continue;} // TODO: find better way to filter unwanted presets
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
