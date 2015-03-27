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
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import biomesoplenty.api.block.BOPBlock;
import biomesoplenty.common.block.BlockAsh;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPFlower2;
import biomesoplenty.common.block.BlockBOPGeneric;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPLog2;
import biomesoplenty.common.block.BlockBOPLog3;
import biomesoplenty.common.block.BlockBOPLog4;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlanks;
import biomesoplenty.common.block.BlockBOPStone;
import biomesoplenty.common.block.BlockBamboo;
import biomesoplenty.common.block.BlockBones;
import biomesoplenty.common.block.BlockCoral;
import biomesoplenty.common.block.BlockCrystal;
import biomesoplenty.common.block.BlockFruit;
import biomesoplenty.common.block.BlockGem;
import biomesoplenty.common.block.BlockGemOre;
import biomesoplenty.common.block.BlockHive;
import biomesoplenty.common.block.BlockMud;
import biomesoplenty.common.block.BlockStoneFormations;
import biomesoplenty.common.block.BlockTurnip;
import biomesoplenty.common.block.BlockFlesh;
import biomesoplenty.common.handler.GuiEventHandler;
import biomesoplenty.common.util.block.BlockStateUtils;
import biomesoplenty.core.BiomesOPlenty;

public class ModBlocks
{
    public static void init()
    {
        ash_block = registerBlock(new BlockAsh(), "ash_block");
        bamboo = registerBlock(new BlockBamboo(), "bamboo");
        bone_segment = registerBlock(new BlockBones(), "bone_segment");
        coral = registerBlock(new BlockCoral(), "coral");
        flower = registerBlock(new BlockBOPFlower(), "flower");
        flower2 = registerBlock(new BlockBOPFlower2(), "flower2");
        gem = registerBlock(new BlockGem(), "gem");
        gem_ore = registerBlock(new BlockGemOre(), "gem_ore");
        hive = registerBlock(new BlockHive(), "hive");
        log = registerBlock(new BlockBOPLog(), "log");
        log2 = registerBlock(new BlockBOPLog2(), "log2");
        log3 = registerBlock(new BlockBOPLog3(), "log3");
        log4 = registerBlock(new BlockBOPLog4(), "log4");
        mushroom = registerBlock(new BlockBOPMushroom(), "mushroom");
        planks = registerBlock(new BlockBOPPlanks(), "planks");
        stone = registerBlock(new BlockBOPStone(), "stone");
        mud = registerBlock(new BlockMud(), "mud");
        turnip_block = registerBlock(new BlockTurnip(), "turnip_block");
        flesh = registerBlock(new BlockFlesh(), "flesh");
        grass = registerBlock(new BlockBOPGrass(), "grass");
        waterlily = registerBlock(new BlockBOPLilypad(), "waterlily");
        dirt = registerBlock(new BlockBOPDirt(), "dirt");
        stone_formations = registerBlock(new BlockStoneFormations(),"stone_formations");
        fruit_block = registerBlock(new BlockFruit(), "fruit_block");
        crystal = registerBlock(new BlockCrystal(), "crystal");
        
        // generics
        ash_stone = registerBlock(new BlockBOPGeneric(), "ash_stone");
        crag_rock = registerBlock((new BlockBOPGeneric()).setStepSound(Block.soundTypeStone), "crag_rock");
        dried_dirt = registerBlock(new BlockBOPGeneric(), "dried_dirt"); dried_dirt.setHarvestLevel("pickaxe",0);
        hard_dirt = registerBlock((new BlockBOPGeneric()).setHardness(0.7F), "hard_dirt");
        hard_ice = registerBlock((new BlockBOPGeneric()).setHardness(0.75F), "hard_ice");
        hard_sand = registerBlock((new BlockBOPGeneric(Material.sand)).setHardness(0.9F).setStepSound(Block.soundTypeSand), "hard_sand");
        mud_brick = registerBlock((new BlockBOPGeneric()).setResistance(2.0F), "mud_brick");
                
        
    }
    
    private static Block registerBlock(Block block, String name) {
        return registerBlock((BOPBlock)block,name);
    }
    
    private static Block registerBlock(BOPBlock block, String name)
    {
        if (block.presetStates == null)
            block.presetStates = BlockStateUtils.getValidStatesForProperties(block.getDefaultState(), block.getPresetProperties());

        block.setUnlocalizedName(name);

        if (block.hasPresetProperties())
        {
            GameRegistry.registerBlock(block, block.getItemClass(), name);

            for (IBlockState state : block.presetStates)
            {
                String stateName = block.getStateName(state, true);
                int stateMeta = block.getMetaFromState(state);

                BiomesOPlenty.proxy.addVariantName(Item.getItemFromBlock(block), BiomesOPlenty.MOD_ID + ":" + stateName);
                BiomesOPlenty.proxy.registerBlockForMeshing(block, stateMeta, stateName);

                GuiEventHandler.blockCount++;
            }
        }
        else
        {
            GameRegistry.registerBlock(block, name);

            BiomesOPlenty.proxy.addVariantName(Item.getItemFromBlock(block), BiomesOPlenty.MOD_ID + ":" + name);
            BiomesOPlenty.proxy.registerBlockForMeshing(block, 0, name);

            GuiEventHandler.blockCount++;
        }

        return block;
    }
    
}
