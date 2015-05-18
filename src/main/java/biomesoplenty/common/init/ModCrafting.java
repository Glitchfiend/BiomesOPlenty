/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPPlantEnums.AllPlants;
import biomesoplenty.api.block.BOPWoodEnums.AllWoods;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.BlockBOPDoor;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPFlower1;
import biomesoplenty.common.block.BlockBOPFlower2;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPHalfWoodSlab;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockGem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModCrafting
{
    
    public static void init()
    {
        addCraftingRecipies();
    }
    
    
    
    private static void addCraftingRecipies()
    {
        
        /*** Dyes ***/
        
        // Flower1
        // CLOVER missing
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.CYAN.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.SWAMPFLOWER.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.black_dye), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.DEATHBLOOM.ordinal())});
        // GLOWFLOWER missing
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.LIGHT_BLUE.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.BLUE_HYDRANGEA.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.ORANGE.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.ORANGE_COSMOS.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.PINK.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.PINK_DAFFODIL.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.MAGENTA.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.WILDFLOWER.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.PURPLE.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.VIOLET.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.white_dye), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.WHITE_ANEMONE.ordinal())});
        // ENDERLOTUS missing
        // BROMELIAD missing
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.SILVER.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.DANDELION.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.PINK.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.PINK_HIBISCUS.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.white_dye), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.LILY_OF_THE_VALLEY.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.ORANGE.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower1, 1, BlockBOPFlower1.FlowerType.BURNING_BLOSSOM.ordinal())});

        // Flower 2
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.PURPLE.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower2, 1, BlockBOPFlower2.FlowerType.LAVENDER.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.YELLOW.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower2, 1, BlockBOPFlower2.FlowerType.GOLDENROD.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.blue_dye), new Object[] {new ItemStack(BOPBlocks.flower2, 1, BlockBOPFlower2.FlowerType.BLUEBELLS.ordinal())});
        // MINERS_DELIGHT missing
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.LIGHT_BLUE.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower2, 1, BlockBOPFlower2.FlowerType.ICY_IRIS.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.flower2, 1, BlockBOPFlower2.FlowerType.ROSE.ordinal())});

        // Others
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.GRAY.getDyeDamage()), new Object[] {new ItemStack(BOPItems.ash)});
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.green_dye), new Object[] {new ItemStack(BOPBlocks.moss)});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.LIME.getDyeDamage()), new Object[] {new ItemStack(BOPBlocks.mushroom, 1, BlockBOPMushroom.MushroomType.GLOWSHROOM.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.brown_dye), new Object[] {new ItemStack(BOPBlocks.mushroom, 1, BlockBOPMushroom.MushroomType.FLAT_MUSHROOM.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.blue_dye), new Object[] {new ItemStack(BOPBlocks.mushroom, 1, BlockBOPMushroom.MushroomType.BLUE_MILK_CAP.ordinal())});
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.brown_dye), new Object[] {BlockBOPPlant.getVariantItem(AllPlants.CATTAIL)});
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.brown_dye), new Object[] {((BlockBOPDoublePlant)BOPBlocks.double_plant).getVariantItem(BlockBOPDoublePlant.FoliageType.TALL_CATTAIL)});

        
        /*** Brick stairs and slabs ***/
        
        // TODO: implement these blocks
        // GameRegistry.addRecipe(new ItemStack(BOPCBlocks.stoneSingleSlab, 6, 2), new Object[] {"RRR", 'R', BOPCBlocks.mudBricks});
        // GameRegistry.addRecipe(new ItemStack(BOPCBlocks.mudBricksStairs, 4), new Object[] {"  R", " RR", "RRR", 'R', BOPCBlocks.mudBricks});
        // GameRegistry.addRecipe(new ItemStack(BOPCBlocks.mudBricksStairs, 4), new Object[] {"R  ", "RR ", "RRR", 'R', BOPCBlocks.mudBricks});
        
        /*** Wood stairs and slabs ***/
        
        // Make sticks from any BOP plank
        GameRegistry.addShapedRecipe(new ItemStack(Items.stick, 4), new Object[] {"#", "#", '#', BOPBlocks.planks_0});
        
        // Note you can't make planks (and therefore doors, fences etc) from GIANT_FLOWER and DEAD logs
        
        // SACRED_OAK
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.SACRED_OAK.ordinal()), BlockBOPLog.getVariantItem(AllWoods.SACRED_OAK));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.sacred_oak_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.SACRED_OAK.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.sacred_oak_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.SACRED_OAK.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.sacred_oak_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.SACRED_OAK.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.sacred_oak_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.SACRED_OAK.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.SACRED_OAK, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.SACRED_OAK.ordinal())});        

        // CHERRY
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.CHERRY.ordinal()), BlockBOPLog.getVariantItem(AllWoods.CHERRY));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.cherry_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.CHERRY.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.cherry_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.CHERRY.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.cherry_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.CHERRY.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.cherry_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.CHERRY.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.CHERRY, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.CHERRY.ordinal())});        

        // DARK
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.DARK.ordinal()), BlockBOPLog.getVariantItem(AllWoods.DARK));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.dark_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.DARK.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.dark_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.DARK.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.dark_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.DARK.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.dark_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.DARK.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.DARK, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.DARK.ordinal())});        

        // FIR
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.FIR.ordinal()), BlockBOPLog.getVariantItem(AllWoods.FIR));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.fir_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.FIR.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.fir_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.FIR.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.fir_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.FIR.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.fir_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.FIR.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.FIR, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.FIR.ordinal())});        

        // ETHEREAL
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.ETHEREAL.ordinal()), BlockBOPLog.getVariantItem(AllWoods.ETHEREAL));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.ethereal_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.ETHEREAL.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.ethereal_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.ETHEREAL.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.ethereal_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.ETHEREAL.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.ethereal_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.ETHEREAL.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.ETHEREAL, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.ETHEREAL.ordinal())});        

        // MAGIC
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.MAGIC.ordinal()), BlockBOPLog.getVariantItem(AllWoods.MAGIC));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.magic_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MAGIC.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.magic_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MAGIC.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.magic_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MAGIC.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.magic_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MAGIC.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.MAGIC, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MAGIC.ordinal())});        

        // MANGROVE
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.MANGROVE.ordinal()), BlockBOPLog.getVariantItem(AllWoods.MANGROVE));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.mangrove_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MANGROVE.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.mangrove_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MANGROVE.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.mangrove_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MANGROVE.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.mangrove_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MANGROVE.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.MANGROVE, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MANGROVE.ordinal())});        
        
        // PALM
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.PALM.ordinal()), BlockBOPLog.getVariantItem(AllWoods.PALM));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.palm_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.PALM.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.palm_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.PALM.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.palm_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.PALM.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.palm_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.PALM.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.PALM, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.PALM.ordinal())});        
        
        // REDWOOD
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.REDWOOD.ordinal()), BlockBOPLog.getVariantItem(AllWoods.REDWOOD));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.redwood_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.REDWOOD.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.redwood_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.REDWOOD.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.redwood_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.REDWOOD.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.redwood_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.REDWOOD.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.REDWOOD, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.REDWOOD.ordinal())});        

        // WILLOW
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.WILLOW.ordinal()), BlockBOPLog.getVariantItem(AllWoods.WILLOW));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.willow_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.WILLOW.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.willow_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.WILLOW.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.willow_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.WILLOW.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.willow_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.WILLOW.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.WILLOW, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.WILLOW.ordinal())});        

        // PINE
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.PINE.ordinal()), BlockBOPLog.getVariantItem(AllWoods.PINE));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.pine_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.PINE.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.pine_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.PINE.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.pine_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.PINE.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.pine_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.PINE.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.PINE, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.PINE.ordinal())});        

        // HELLBARK
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.HELLBARK.ordinal()), BlockBOPLog.getVariantItem(AllWoods.HELLBARK));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.hellbark_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.HELLBARK.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.hellbark_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.HELLBARK.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.hellbark_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.HELLBARK.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.hellbark_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.HELLBARK.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.HELLBARK, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.HELLBARK.ordinal())});        

        // JACARANDA
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.JACARANDA.ordinal()), BlockBOPLog.getVariantItem(AllWoods.JACARANDA));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.jacaranda_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.JACARANDA.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.jacaranda_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.JACARANDA.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.jacaranda_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.JACARANDA.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.jacaranda_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.JACARANDA.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.JACARANDA, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.JACARANDA.ordinal())});        

        // MAHOGANY
        GameRegistry.addShapelessRecipe(new ItemStack(BOPBlocks.planks_0, 4, AllWoods.MAHOGANY.ordinal()), BlockBOPLog.getVariantItem(AllWoods.MAHOGANY));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.mahogany_stairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MAHOGANY.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockBOPDoor)BOPBlocks.mahogany_door).getDoorItem(), 3), new Object[] {"##", "##", "##", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MAHOGANY.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.mahogany_fence, 3), new Object[] {"W#W", "W#W", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MAHOGANY.ordinal())});       
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.mahogany_fence_gate, 1), new Object[] {"#W#", "#W#", '#', Items.stick, 'W', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MAHOGANY.ordinal())});
        GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.getVariantItem(AllWoods.MAHOGANY, 6), new Object[] {"###", '#', new ItemStack(BOPBlocks.planks_0, 1, AllWoods.MAHOGANY.ordinal())});        

        
        
        

        
        
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.gem_block, 1, BlockGem.GemType.AMETHYST.ordinal()), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gem, 1, BlockGem.GemType.AMETHYST.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.gem_block, 1, BlockGem.GemType.RUBY.ordinal()), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gem, 1, BlockGem.GemType.RUBY.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.gem_block, 1, BlockGem.GemType.PERIDOT.ordinal()), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gem, 1, BlockGem.GemType.PERIDOT.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.gem_block, 1, BlockGem.GemType.TOPAZ.ordinal()), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gem, 1, BlockGem.GemType.TOPAZ.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.gem_block, 1, BlockGem.GemType.TANZANITE.ordinal()), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gem, 1, BlockGem.GemType.TANZANITE.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.gem_block, 1, BlockGem.GemType.MALACHITE.ordinal()), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gem, 1, BlockGem.GemType.MALACHITE.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.gem_block, 1, BlockGem.GemType.SAPPHIRE.ordinal()), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gem, 1, BlockGem.GemType.SAPPHIRE.ordinal())});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.gem_block, 1, BlockGem.GemType.AMBER.ordinal()), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gem, 1, BlockGem.GemType.AMBER.ordinal())});
       
        
        
        
        GameRegistry.addShapedRecipe(new ItemStack(Items.string), new Object[] {"FFF", "FFF", "FFF", 'F', ((BlockBOPDoublePlant)BOPBlocks.double_plant).getVariantItem(BlockBOPDoublePlant.FoliageType.FLAX)});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.grass, 1, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK.ordinal()), new Object[] {"SSS", "SNS", "SSS", 'S', Items.wheat_seeds, 'N', Blocks.netherrack});
        GameRegistry.addShapedRecipe(new ItemStack(Blocks.wool), new Object[] {"CCC", "CCC", "CCC", 'C', BlockBOPPlant.getVariantItem(AllPlants.CATTAIL)});
        GameRegistry.addShapedRecipe(new ItemStack(Blocks.wool), new Object[] {"CCC", "CCC", "CCC", 'C', ((BlockBOPDoublePlant)BOPBlocks.double_plant).getVariantItem(BlockBOPDoublePlant.FoliageType.TALL_CATTAIL)});
        GameRegistry.addShapedRecipe(new ItemStack(Items.coal), new Object[] {"AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.ash)});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.ash_block), new Object[] {"AA", "AA", 'A', new ItemStack(BOPItems.ash)});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.mud, 1), new Object[] {"MM", "MM", 'M', BOPItems.mudball});
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.mud_brick_block), new Object[] {"MM", "MM", 'M', new ItemStack(BOPItems.mud_brick)});        
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.crystal), new Object[] {"CC", "CC", 'C', new ItemStack(BOPItems.crystal_shard)});
        GameRegistry.addShapedRecipe(new ItemStack(Blocks.mossy_cobblestone, 1, 0), new Object[] {"MMM", "MCM", "MMM", 'M', BOPBlocks.moss, 'C', Blocks.cobblestone});
        GameRegistry.addShapedRecipe(new ItemStack(Blocks.stonebrick, 1, 1), new Object[] {"MMM", "MSM", "MMM", 'M', BOPBlocks.moss, 'S', Blocks.stonebrick});

        
        
        //Scythes
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.wood_scythe), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), "plankWood", Character.valueOf('S'), "stickWood" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.stone_scythe), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Blocks.cobblestone, Character.valueOf('S'), "stickWood" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.iron_scythe), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Items.iron_ingot, Character.valueOf('S'), "stickWood" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.gold_scythe), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Items.gold_ingot, Character.valueOf('S'), "stickWood" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.diamond_scythe), new Object [] {" MM", "M S", "  S", Character.valueOf('M'), Items.diamond, Character.valueOf('S'), "stickWood" }));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.wood_scythe), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), "plankWood", Character.valueOf('S'), "stickWood" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.stone_scythe), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Blocks.cobblestone, Character.valueOf('S'), "stickWood" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.iron_scythe), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Items.iron_ingot, Character.valueOf('S'), "stickWood" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.gold_scythe), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Items.gold_ingot, Character.valueOf('S'), "stickWood" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.diamond_scythe), new Object [] {"MM ", "S M", "S  ", Character.valueOf('M'), Items.diamond, Character.valueOf('S'), "stickWood" }));   

        
    }
    
}
