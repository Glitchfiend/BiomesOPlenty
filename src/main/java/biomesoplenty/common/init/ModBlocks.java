/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.block.BOPBlocks.*;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.fml.common.registry.GameRegistry;
import biomesoplenty.api.block.BOPWoodType;
import biomesoplenty.api.block.IBOPBlock;
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
import biomesoplenty.common.block.BlockBOPSapling;
import biomesoplenty.common.block.BlockBOPStairs;
import biomesoplenty.common.block.BlockBOPStone;
import biomesoplenty.common.block.BlockBOPVine;
import biomesoplenty.common.block.BlockBamboo;
import biomesoplenty.common.block.BlockBones;
import biomesoplenty.common.block.BlockCoral;
import biomesoplenty.common.block.BlockCrystal;
import biomesoplenty.common.block.BlockDoubleFoliage;
import biomesoplenty.common.block.BlockFoliage;
import biomesoplenty.common.block.BlockFruit;
import biomesoplenty.common.block.BlockGem;
import biomesoplenty.common.block.BlockGemOre;
import biomesoplenty.common.block.BlockHive;
import biomesoplenty.common.block.BlockMud;
import biomesoplenty.common.block.BlockStoneFormations;
import biomesoplenty.common.block.BlockTurnip;
import biomesoplenty.common.block.BlockFlesh;
import biomesoplenty.common.handler.GuiEventHandler;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import biomesoplenty.core.BiomesOPlenty;

public class ModBlocks
{
    

    // syntactic sugar
    // BlockModifier class encapsulates modifications which can be made to generic blocks in a unified way
    public static enum BlockModifiers {HARDNESS, RESISTANCE, STEP_SOUND, CREATIVE_TAB, HARVEST_LEVEL};
    public static class BlockModifier {
        private BlockModifiers mod;
        public float f;
        public int i;
        public String s;
        public Block.SoundType sound;
        public CreativeTabs tab;
        public BlockModifier(BlockModifiers mod)
        {
            this.mod = mod;
        }
        public void apply(Block block)
        {
            switch (this.mod)
            {
                case HARDNESS:
                    block.setHardness(this.f);
                    break;
                case RESISTANCE:
                    block.setResistance(this.f);
                    break;
                case STEP_SOUND:
                    block.setStepSound(this.sound);
                    break;
                case CREATIVE_TAB:
                    block.setCreativeTab(this.tab);
                    break;
                case HARVEST_LEVEL:
                    block.setHarvestLevel(s, i);
                    break;
            }            
        }   
    }
    // convenience methods for creating BlockModifier instances - eg  hardness(2.5F) creates a BlockModifier which can set a block's hardness to 2.5
    public static BlockModifier hardness(float f) {BlockModifier m = new BlockModifier(BlockModifiers.HARDNESS); m.f = f; return m;}
    public static BlockModifier resistance(float f) {BlockModifier m = new BlockModifier(BlockModifiers.RESISTANCE); m.f = f; return m;}
    public static BlockModifier stepSound(Block.SoundType sound) {BlockModifier m = new BlockModifier(BlockModifiers.STEP_SOUND); m.sound = sound; return m;}
    public static BlockModifier creativeTab(CreativeTabs tab) {BlockModifier m = new BlockModifier(BlockModifiers.CREATIVE_TAB); m.tab = tab; return m;}
    public static BlockModifier harvestLevel(String toolClass, int level) {BlockModifier m = new BlockModifier(BlockModifiers.HARVEST_LEVEL); m.s = toolClass; m.i = level; return m;}
    // result - can now specify lists of modifiers which all have a common type.  eg:
    // BlockModifier[] = {hardness(2.5F), harvestLevel("axe",2), creativeTab(null)};
    // these can be used to quickly add new blocks from generic block classes
    
    
    // TODO: use getDrops() in classes where the drops are very specific, instead of implementing all 3 of quantityDropped() getItemDropped() and damageDropped()
    // TODO: docblocks!
    // TODO: make better use of canSustainPlant() in BlockDecoration and children
    // TODO: rethink named states - not as useful as I thought
    // TODO: implement IShearable on flowers
    // TODO: investigate Block.getRenderType()
    
    public static void init()
    {
        ash_block =         registerBlock( new BlockAsh(), "ash_block" );
        bamboo =            registerBlock( new BlockBamboo(), "bamboo" );
        bone_segment =      registerBlock( new BlockBones(), "bone_segment" );
        coral =             registerBlock( new BlockCoral(), "coral" );
        flower1 =           registerBlock( new BlockBOPFlower1(), "flower1" );
        flower2 =           registerBlock( new BlockBOPFlower2(), "flower2" );
        gem_block =         registerBlock( new BlockGem(), "gem_block" );
        gem_ore =           registerBlock( new BlockGemOre(), "gem_ore" );
        hive =              registerBlock( new BlockHive(), "hive" );
        mushroom =          registerBlock( new BlockBOPMushroom(), "mushroom" );
        stone =             registerBlock( new BlockBOPStone(), "stone" );
        mud =               registerBlock( new BlockMud(), "mud" );
        turnip_block =      registerBlock( new BlockTurnip(), "turnip_block", creativeTab(null) ); // no creative tab
        flesh =             registerBlock( new BlockFlesh(), "flesh" );
        grass =             registerBlock( new BlockBOPGrass(), "grass" );
        waterlily =         registerBlock( new BlockBOPLilypad(), "waterlily" );
        dirt =              registerBlock( new BlockBOPDirt(), "dirt" );
        stone_formations =  registerBlock( new BlockStoneFormations(), "stone_formations" );
        fruit_block =       registerBlock( new BlockFruit(), "fruit_block" /*, creativeTab(null) */); // TODO: once the mechanism for farming fruit is established: remove creative tab
        crystal =           registerBlock( new BlockCrystal(), "crystal" );
        
        // generics
        ash_stone =         registerBlock( new BlockBOPGeneric(), "ash_stone" );
        crag_rock =         registerBlock( new BlockBOPGeneric(), "crag_rock", stepSound(Block.soundTypeStone) );
        dried_dirt =        registerBlock( new BlockBOPGeneric(), "dried_dirt", harvestLevel("pickaxe",0) );
        hard_dirt =         registerBlock( new BlockBOPGeneric(), "hard_dirt", hardness(0.7F) );
        hard_ice =          registerBlock( new BlockBOPGeneric(), "hard_ice", hardness(0.75F) );
        hard_sand =         registerBlock( new BlockBOPGeneric(Material.sand), "hard_sand", hardness(0.9F), stepSound(Block.soundTypeSand) );
        mud_brick =         registerBlock( new BlockBOPGeneric(), "mud_brick", resistance(2.0F) );
 
        // woods
        sacred_oak =        registerWoodType("sacred_oak");
        cherry =            registerWoodType("cherry");
        dark =              registerWoodType("dark");
        fir =               registerWoodType("fir");
        ethereal =          registerWoodType("ethereal");
        magic =             registerWoodType("magic");
        mangrove =          registerWoodType("mangrove");
        palm =              registerWoodType("palm");
        redwood =           registerWoodType("redwood");
        willow =            registerWoodType("willow");
        pine =              registerWoodType("pine");
        hell_bark =         registerWoodType("hell_bark");
        jacaranda =         registerWoodType("jacaranda");
        mahogany =          registerWoodType("mahogany");
        giant_flower_stem = registerBlock( new BlockBOPLog(), "giant_flower_stem" ); // no planks, stairs, etc
        dead_log =          registerBlock( new BlockBOPLog(), "dead_log" ); // no planks, stairs, etc
                
        //vines
        // TODO: special placement rules?
        flower_vine =       registerBlock( new BlockBOPVine(false), "flower_vine" );
        ivy =               registerBlock( new BlockBOPVine(true), "ivy" );
        moss =              registerBlock( new BlockBOPVine(true), "moss" );
        tree_moss =         registerBlock( new BlockBOPVine(false), "tree_moss" );
        wisteria =          registerBlock( new BlockBOPVine(false), "wisteria" );
        
        foliage =           registerBlock( new BlockFoliage(), "foliage" );
        
        
        // saplings
        // TODO: implement the tree generators (at the moment all saplings generate vanilla small oak trees)
        // TODO: check bamboo implementation
        yellow_autumn_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "yellow_autumn_sapling"  );
        orange_autumn_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "orange_autumn_sapling"  );
        bamboo_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "bamboo_sapling"  );
        magic_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "magic_sapling"  );
        dark_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "dark_sapling"  );
        dead_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "dead_sapling"  );
        fir_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "fir_sapling"  );
        ethereal_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "ethereal_sapling"  );
        origin_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "origin_sapling"  );
        pink_cherry_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "pink_cherry_sapling"  );
        white_cherry_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "white_cherry_sapling"  );
        maple_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "maple_sapling"  );
        hellbark_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "hellbark_sapling"  );
        flowering_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "flowering_sapling"  );
        jacaranda_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "jacaranda_sapling"  );
        sacred_oak_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "sacred_oak_sapling"  );
        mangrove_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "mangrove_sapling"  );
        palm_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "palm_sapling"  );
        redwood_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "redwood_sapling"  );
        willow_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "willow_sapling"  );
        pine_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "pine_sapling"  );
        mahogany_sapling = registerBlock( new BlockBOPSapling( new WorldGenTrees(true) ), "mahogany_sapling"  );
        
        
        // leaves
        // TODO: bamboo leaves to grow automatically?
        // TODO: add correct fruit (or change this implementation completely)
        yellow_autumn_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(yellow_autumn_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "yellow_autumn_leaves" );
        orange_autumn_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(orange_autumn_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "orange_autumn_leaves" );
        willow_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(willow_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "willow_leaves" );
        white_cherry_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(white_cherry_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "white_cherry_leaves" );
        pink_cherry_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(pink_cherry_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "pink_cherry_leaves" );
        sacred_oak_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(sacred_oak_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "sacred_oak_leaves" );
        redwood_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(redwood_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "redwood_leaves" );
        pine_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(pine_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "pine_leaves" );
        palm_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(palm_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "palm_leaves" );
        origin_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(origin_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "origin_leaves" );
        maple_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(maple_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "maple_leaves" );
        mangrove_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(mangrove_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "mangrove_leaves" );
        mahogany_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(mahogany_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "mahogany_leaves" );
        magic_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(magic_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "magic_leaves" );
        jacaranda_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(jacaranda_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "jacaranda_leaves" );
        hellbark_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(hellbark_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, false ), "hellbark_leaves" );
        flowering_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(flowering_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "flowering_leaves" );
        fir_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(fir_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "fir_leaves" );
        ethereal_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(ethereal_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "ethereal_leaves" );
        dead_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(dead_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "dead_leaves" );
        dark_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(dark_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "dark_leaves" );
        bamboo_leaves = registerBlock( new BlockBOPLeaves( new ItemStack(bamboo_sapling, 1, 0), new ItemStack(Items.apple, 1, 0), 20, true ), "bamboo_leaves" );
        
        double_foliage = registerBlock( new BlockDoubleFoliage(), "double_foliage" );
        
        
    }
    
    
    // TODO: check if hellbark planks, fence etc can burn
    // TODO: saplings
    public static BOPWoodType registerWoodType(String name)
    {
        BOPWoodType wood = new BOPWoodType();
        wood.log = registerBlock( new BlockBOPLog(), name + "_log" );
        wood.planks = registerBlock( new BlockBOPGeneric(Material.wood), name + "_planks", hardness(2.0F), harvestLevel("axe",0), stepSound(Block.soundTypeWood) );
        wood.stairs = registerBlock( new BlockBOPStairs(wood.planks.getDefaultState()), name + "_stairs", harvestLevel("axe",0) );
        wood.fence = registerBlock( new BlockBOPFence(), name + "_fence", harvestLevel("axe",0) );
        wood.fence_gate = registerBlock( new BlockBOPFenceGate(), name + "_fence_gate", harvestLevel("axe",0) );
        wood.door_block = registerBlock( new BlockBOPDoor(), name + "_door_block", creativeTab(null), harvestLevel("axe",0) );
        wood.door_item = ModItems.registerItem( new ItemDoor(wood.door_block) , name + "_door");
        return wood;
    }
    
    
    
    public static void registerBlockVariant(Block block, String stateName, int stateMeta)
    {
        Item item = Item.getItemFromBlock(block);
        if (item != null){
            BiomesOPlenty.proxy.addVariantName(item, BiomesOPlenty.MOD_ID + ":" + stateName);
        }
        BiomesOPlenty.proxy.registerBlockForMeshing(block, stateMeta, stateName);
        GuiEventHandler.blockCount++;
    }
    
    public static Block registerBlock(Block block, String blockName, BlockModifier... modifiers)
    {

        block.setUnlocalizedName(blockName);
        
        // by default, set the creative tab for all blocks added in BOP to CreativeTabBOP.instance
        // can be overridden with the creativeTab() modifier if necessary
        block.setCreativeTab(CreativeTabBOP.instance);
        
        // apply any modifiers
        for (BlockModifier m : modifiers)
        {
            m.apply(block);
        }
        
        if (block instanceof IBOPBlock)
        {
            // if this block supports the IBOPBlock interface then we can use getNamedStates() and getItemClass()
            IBOPBlock bopBlock = (IBOPBlock)block;
            GameRegistry.registerBlock(block, bopBlock.getItemClass(), blockName);
            if (bopBlock.getNamedStates().isEmpty())
            {
                // block has no named variants to register
                registerBlockVariant(block, blockName, block.getMetaFromState(block.getDefaultState()));
            }
            else
            {
                // register all the named variants
                for (Map.Entry<String,IBlockState> entry : bopBlock.getNamedStates().entrySet())
                {
                    String stateName = entry.getKey();
                    IBlockState state = entry.getValue();
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
