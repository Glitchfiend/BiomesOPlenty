/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.*;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.*;
import biomesoplenty.common.crafting.BiomeEssenceRecipe;
import biomesoplenty.common.handler.FurnaceFuelHandler;
import biomesoplenty.common.item.ItemJarFilled;
import com.google.common.base.CaseFormat;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModCrafting
{
    
    public static void init()
    {
        addOreRegistration();
        addCraftingRecipies();
        addSmeltingRecipes();
    }
    
    
    private static void addCraftingRecipies()
    {
        
        /*** Dyes ***/
        
        // Flower1
    	GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.SILVER.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.CLOVER));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.CYAN.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.SWAMPFLOWER));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.black_dye, 2), BlockBOPFlower.paging.getVariantItem(BOPFlowers.DEATHBLOOM));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.CYAN.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.GLOWFLOWER));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.LIGHT_BLUE.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.BLUE_HYDRANGEA));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.ORANGE_COSMOS));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.PINK_DAFFODIL));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.MAGENTA.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.WILDFLOWER));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.PURPLE.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.VIOLET));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.white_dye, 2), BlockBOPFlower.paging.getVariantItem(BOPFlowers.WHITE_ANEMONE));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.black_dye, 2), BlockBOPFlower.paging.getVariantItem(BOPFlowers.ENDERLOTUS));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.BROMELIAD));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.GRAY.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.WILTED_LILY));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.PINK_HIBISCUS));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.white_dye, 2), BlockBOPFlower.paging.getVariantItem(BOPFlowers.LILY_OF_THE_VALLEY));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.BURNING_BLOSSOM));

        // Flower 2
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.PURPLE.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.LAVENDER));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.YELLOW.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.GOLDENROD));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.blue_dye, 2), BlockBOPFlower.paging.getVariantItem(BOPFlowers.BLUEBELLS));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.MINERS_DELIGHT));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.LIGHT_BLUE.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.ICY_IRIS));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.ROSE));
        
        // Others
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.GRAY.getDyeDamage()), new ItemStack(BOPItems.ash));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.LIME.getDyeDamage()), new ItemStack(BOPBlocks.mushroom, 1, BlockBOPMushroom.MushroomType.GLOWSHROOM.ordinal()));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.brown_dye, 2), new ItemStack(BOPBlocks.mushroom, 1, BlockBOPMushroom.MushroomType.FLAT_MUSHROOM.ordinal()));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.blue_dye, 2), new ItemStack(BOPBlocks.mushroom, 1, BlockBOPMushroom.MushroomType.BLUE_MILK_CAP.ordinal()));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.brown_dye, 2), BlockBOPPlant.paging.getVariantItem(BOPPlants.CATTAIL));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.brown_dye, 2), ((BlockBOPDoublePlant)BOPBlocks.double_plant).getVariantItem(BlockBOPDoublePlant.DoublePlantType.TALL_CATTAIL));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.LIGHT_BLUE.getDyeDamage()), ((BlockBOPDoublePlant)BOPBlocks.double_plant).getVariantItem(BlockBOPDoublePlant.DoublePlantType.FLAX));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage()), BlockBOPPlant.paging.getVariantItem(BOPPlants.RAFFLESIA));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.brown_dye, 2), new ItemStack(BOPItems.pinecone));
        
        /*** BOP brick and stone stairs and slabs ***/
        
        for (BlockBOPHalfOtherSlab.SlabType slabType : BlockBOPHalfOtherSlab.SlabType.values())
        {
            BlockBOPHalfOtherSlab otherSlab = (BlockBOPHalfOtherSlab)BOPBlocks.other_slab;
            GameRegistry.addShapedRecipe(otherSlab.getVariantItem(slabType, 6), "RRR", 'R', otherSlab.getFullBlockVariantItem(slabType));
        }
        
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.mud_brick_stairs, 4), "  R", " RR", "RRR", 'R', BOPBlocks.mud_brick_block);
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.mud_brick_stairs, 4), "R  ", "RR ", "RRR", 'R', BOPBlocks.mud_brick_block);
        
        
        /*** Wood stairs and slabs ***/
        
        // Make sticks from any BOP plank
        GameRegistry.addShapedRecipe(new ItemStack(Items.STICK, 4), "#", "#", '#', BOPBlocks.planks_0);
        
        // Note you can't make planks (and therefore doors, fences etc) from GIANT_FLOWER and DEAD logs
        
        for (BOPWoods wood : BOPWoods.values())
        {
            if (!wood.hasPlanks()) {continue;}
            GameRegistry.addShapelessRecipe(BlockBOPPlanks.paging.getVariantItem(wood, 4), BlockBOPLog.paging.getVariantItem(wood));
            GameRegistry.addShapedRecipe(BlockBOPHalfWoodSlab.paging.getVariantItem(wood, 6), "###", '#', BlockBOPPlanks.paging.getVariantItem(wood));
            GameRegistry.addShapedRecipe(new ItemStack(BlockBOPWoodStairs.getBlock(wood), 4), "#  ", "## ", "###", '#', BlockBOPPlanks.paging.getVariantItem(wood));
            GameRegistry.addShapedRecipe(new ItemStack(BlockBOPDoor.getBlock(wood).getDoorItem(), 3), "##", "##", "##", '#', BlockBOPPlanks.paging.getVariantItem(wood));
            GameRegistry.addShapedRecipe(new ItemStack(BlockBOPFence.getBlock(wood), 3), "W#W", "W#W", '#', Items.STICK, 'W', BlockBOPPlanks.paging.getVariantItem(wood));
            GameRegistry.addShapedRecipe(new ItemStack(BlockBOPFenceGate.getBlock(wood), 1), "#W#", "#W#", '#', Items.STICK, 'W', BlockBOPPlanks.paging.getVariantItem(wood));
        }
 
        
        /*** Gems and Gem Blocks ***/
        
        for (BOPGems gem : BOPGems.values())
        {
            GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.gem, 9, gem.ordinal()), new ItemStack(BOPBlocks.gem_block, 1, gem.ordinal()));
            GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.gem_block, 1, gem.ordinal()), "AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.gem, 1, gem.ordinal()));
        }             
        
        /*** Tools, weapons, armor ***/
        
        // Scythes
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.wood_scythe), " MM", "M S", "  S", 'M', "plankWood", 'S', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.stone_scythe), " MM", "M S", "  S", 'M', Blocks.COBBLESTONE, 'S', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.iron_scythe), " MM", "M S", "  S", 'M', Items.IRON_INGOT, 'S', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.gold_scythe), " MM", "M S", "  S", 'M', Items.GOLD_INGOT, 'S', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.diamond_scythe), " MM", "M S", "  S", 'M', Items.DIAMOND, 'S', "stickWood"));
        // and in reverse..
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.wood_scythe), "MM ", "S M", "S  ", 'M', "plankWood", 'S', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.stone_scythe), "MM ", "S M", "S  ", 'M', Blocks.COBBLESTONE, 'S', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.iron_scythe), "MM ", "S M", "S  ", 'M', Items.IRON_INGOT, 'S', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.gold_scythe), "MM ", "S M", "S  ", 'M', Items.GOLD_INGOT, 'S', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.diamond_scythe), "MM ", "S M", "S  ", 'M', Items.DIAMOND, 'S', "stickWood"));
        
        // Mud Tools and Armor
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.mud_pickaxe), "###", " X ", " X ", '#', "ballMud", 'X', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.mud_shovel), "#", "X", "X", '#', "ballMud", 'X', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.mud_sword), "#", "#", "X", '#', "ballMud", 'X', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.mud_axe), "##", "#X", " X", '#', "ballMud", 'X', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.mud_hoe), "##", " X", " X", '#', "ballMud", 'X', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.mud_helmet), "###", "# #", '#', "ballMud"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.mud_chestplate), "# #", "###", "###", '#', "ballMud"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.mud_leggings), "###", "# #", "# #", '#', "ballMud"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.mud_boots), "# #", "# #", '#', "ballMud"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.mud_scythe), " MM", "M S", "  S", 'M', "ballMud", 'S', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.mud_scythe), "MM ", "S M", "S  ", 'M', "ballMud", 'S', "stickWood"));

        // Flower Bands
        GameRegistry.addShapedRecipe(new ItemStack(BOPItems.dull_flower_band), "CCC", "C C", "CCC", 'C', BlockBOPFlower.paging.getVariantItem(BOPFlowers.CLOVER));
        GameRegistry.addShapedRecipe(new ItemStack(BOPItems.plain_flower_band), "CDC", "D D", "CDC", 'C', BlockBOPFlower.paging.getVariantItem(BOPFlowers.CLOVER), 'D', BlockBOPFlower.paging.getVariantItem(BOPFlowers.ORANGE_COSMOS));
        GameRegistry.addShapedRecipe(new ItemStack(BOPItems.exotic_flower_band), "CDC", "V V", "CDC", 'C', BlockBOPFlower.paging.getVariantItem(BOPFlowers.CLOVER),'D', BlockBOPFlower.paging.getVariantItem(BOPFlowers.ORANGE_COSMOS), 'V', BlockBOPFlower.paging.getVariantItem(BOPFlowers.VIOLET));
        
        /*** Biome Finder ***/
        
        GameRegistry.addShapedRecipe(new ItemStack(BOPItems.biome_finder), " A ", "AOA", " A ", 'A', new ItemStack(BOPItems.gem, 1, BOPGems.AMETHYST.ordinal()), 'O', new ItemStack(BOPItems.terrestrial_artifact));
        GameRegistry.addRecipe(new BiomeEssenceRecipe());
        
        /*** Flower Basket ***/
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.flower_basket), " S ", "S S", "SSS", 'S', "stickWood"));
        
        /*** Misc Others ***/
        
        // Flax
        GameRegistry.addShapedRecipe(new ItemStack(BOPItems.flax_string), "FFF", "FFF", "FFF", 'F', ((BlockBOPDoublePlant)BOPBlocks.double_plant).getVariantItem(BlockBOPDoublePlant.DoublePlantType.FLAX));
        
        // Flax String
        GameRegistry.addShapedRecipe(new ItemStack(Items.STRING), "S", "S", "S", 'S', new ItemStack(BOPItems.flax_string));
        
        // Overgrown Netherrack
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.grass, 1, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK.ordinal()), "SSS", "SNS", "SSS", 'S', Items.WHEAT_SEEDS, 'N', Blocks.NETHERRACK);
        
        // Cattail Wool
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.WOOL), "CCC", "CCC", "CCC", 'C', "plantCattail"));
        
        // Ash Coal
        GameRegistry.addShapedRecipe(new ItemStack(Items.COAL), "AAA", "AAA", "AAA", 'A', new ItemStack(BOPItems.ash));
        
        // Ash Block
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.ash_block), "AA", "AA", 'A', new ItemStack(BOPItems.ash));
        
        // Mud Block
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPBlocks.mud, 1), "MM", "MM", 'M', "ballMud"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.mud), "dirt", new ItemStack(Items.WATER_BUCKET)));
        
        // Mud Brick Blcok
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.mud_brick_block), "MM", "MM", 'M', new ItemStack(BOPItems.mud_brick));
        
        // Celestial Crystal Block
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.crystal), "CC", "CC", 'C', new ItemStack(BOPItems.crystal_shard));
        
        //Coarse Dirts
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.dirt, 4, BOPBlocks.dirt.getMetaFromState(BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.LOAMY).withProperty(BlockBOPDirt.COARSE, true))), "GD", "DG", 'G', Blocks.GRAVEL, 'D', new ItemStack(BOPBlocks.dirt, 4, BlockBOPDirt.BOPDirtType.LOAMY.ordinal()));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.dirt, 4, BOPBlocks.dirt.getMetaFromState(BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.LOAMY).withProperty(BlockBOPDirt.COARSE, true))), "DG", "GD", 'G', Blocks.GRAVEL, 'D', new ItemStack(BOPBlocks.dirt, 4, BlockBOPDirt.BOPDirtType.LOAMY.ordinal()));
        
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.dirt, 4, BOPBlocks.dirt.getMetaFromState(BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY).withProperty(BlockBOPDirt.COARSE, true))), "GD", "DG", 'G', Blocks.GRAVEL, 'D', new ItemStack(BOPBlocks.dirt, 4, BlockBOPDirt.BOPDirtType.SANDY.ordinal()));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.dirt, 4, BOPBlocks.dirt.getMetaFromState(BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY).withProperty(BlockBOPDirt.COARSE, true))), "DG", "GD", 'G', Blocks.GRAVEL, 'D', new ItemStack(BOPBlocks.dirt, 4, BlockBOPDirt.BOPDirtType.SANDY.ordinal()));
        
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.dirt, 4, BOPBlocks.dirt.getMetaFromState(BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SILTY).withProperty(BlockBOPDirt.COARSE, true))), "GD", "DG", 'G', Blocks.GRAVEL, 'D', new ItemStack(BOPBlocks.dirt, 4, BlockBOPDirt.BOPDirtType.SILTY.ordinal()));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.dirt, 4, BOPBlocks.dirt.getMetaFromState(BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SILTY).withProperty(BlockBOPDirt.COARSE, true))), "DG", "GD", 'G', Blocks.GRAVEL, 'D', new ItemStack(BOPBlocks.dirt, 4, BlockBOPDirt.BOPDirtType.SILTY.ordinal()));
        
        // Enderporter
        GameRegistry.addShapedRecipe(new ItemStack(BOPItems.enderporter), "IAI", "ATA", "IAI", 'I', Items.ENDER_EYE, 'A', new ItemStack(BOPItems.gem, 1, BOPGems.AMETHYST.ordinal()), 'T', BOPItems.terrestrial_artifact);
        
        // Records
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.record_wanderer), "TTT", "TRT", "TTT", 'T', BOPItems.terrestrial_artifact, 'R', "record"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BOPItems.record_corruption), "MMM", "MRM", "MMM", 'M', "ballMud", 'R', BOPItems.record_wanderer));
        
        // Bamboo Thatching
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.bamboo_thatching), "##", "##", '#', BOPBlocks.bamboo);
        
        // Bamboo
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.bamboo, 8), " #", "# ", '#', new ItemStack(BOPBlocks.bamboo_thatching));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.bamboo, 8), "# ", " #", '#', new ItemStack(BOPBlocks.bamboo_thatching));
        
        // Empty Jar
        GameRegistry.addShapedRecipe(new ItemStack(BOPItems.jar_empty, 3, 0), "# #", "# #", "###", '#', Blocks.GLASS);
        
        // Ambrosia
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.ambrosia), new ItemStack(BOPItems.pixie_dust), new ItemStack(Items.POTIONITEM, 1, 0), new ItemStack(BOPItems.ichor), new ItemStack(BOPBlocks.seaweed, 1, BlockBOPSeaweed.SeaweedType.KELP.ordinal()), BlockBOPPlant.paging.getVariantItem(BOPPlants.ROOT), new ItemStack(BOPItems.crystal_shard), new ItemStack(BOPItems.jar_filled, 1, ItemJarFilled.JarContents.HONEY.ordinal()), new ItemStack(BOPItems.berries), Items.SUGAR);
        
        // Oranmental Artifact
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.terrestrial_artifact), new ItemStack(BOPItems.gem, 1, BOPGems.RUBY.ordinal()), new ItemStack(BOPItems.gem, 1, BOPGems.TOPAZ.ordinal()), new ItemStack(BOPItems.gem, 1, BOPGems.AMBER.ordinal()), new ItemStack(BOPItems.gem, 1, BOPGems.PERIDOT.ordinal()), new ItemStack(BOPItems.gem, 1, BOPGems.MALACHITE.ordinal()), new ItemStack(BOPItems.gem, 1, BOPGems.SAPPHIRE.ordinal()), new ItemStack(BOPItems.gem, 1, BOPGems.TANZANITE.ordinal()), Items.EMERALD);
        
        // Flesh Block
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.flesh), "##", "##", '#', new ItemStack(BOPItems.fleshchunk));
        
        // Honeycombs
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.hive, 1, BlockBOPHive.HiveType.HONEYCOMB.ordinal()), "##", "##", '#', new ItemStack(BOPItems.honeycomb));
        GameRegistry.addShapedRecipe(new ItemStack(BOPBlocks.hive, 1, BlockBOPHive.HiveType.FILLED_HONEYCOMB.ordinal()), "##", "##", '#', new ItemStack(BOPItems.filled_honeycomb));

        // Food
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.shroompowder), new ItemStack(BOPBlocks.mushroom, 1, BlockBOPMushroom.MushroomType.TOADSTOOL.ordinal()));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.ricebowl), Items.BOWL, BlockBOPPlant.paging.getVariantItem(BOPPlants.WILDRICE));
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.saladfruit), Items.BOWL, BOPItems.peach, Items.APPLE, BOPItems.pear);
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.saladveggie), Items.BOWL, BOPItems.turnip, Items.CARROT, Items.POTATO);
        GameRegistry.addShapelessRecipe(new ItemStack(BOPItems.saladshroom), Items.BOWL, new ItemStack(BOPBlocks.mushroom, 1, BlockBOPMushroom.MushroomType.TOADSTOOL.ordinal()), new ItemStack(BOPBlocks.mushroom, 1, BlockBOPMushroom.MushroomType.PORTOBELLO.ordinal()), new ItemStack(BOPBlocks.mushroom, 1, BlockBOPMushroom.MushroomType.BLUE_MILK_CAP.ordinal()));

        // Terrariums
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.FERN.ordinal()), "grass", new ItemStack(Blocks.TALLGRASS, 1, 2), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.MUSHROOM.ordinal()), "grass", new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.CACTUS.ordinal()), "grass", BlockBOPPlant.paging.getVariantItem(BOPPlants.TINYCACTUS), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.FLAX.ordinal()), "grass", ((BlockBOPDoublePlant)BOPBlocks.double_plant).getVariantItem(BlockBOPDoublePlant.DoublePlantType.FLAX), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.SAPLING.ordinal()), "grass", "treeSapling", new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.KORU.ordinal()), "grass", BlockBOPPlant.paging.getVariantItem(BOPPlants.KORU), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.FLOWER.ordinal()), "grass", new ItemStack(Blocks.RED_FLOWER), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.FLOWER.ordinal()), "grass", new ItemStack(Blocks.YELLOW_FLOWER), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.BAMBOO.ordinal()), new ItemStack(Blocks.DIRT, 1, 2), new ItemStack(BOPBlocks.bamboo), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.DEAD.ordinal()), "grass", new ItemStack(Blocks.DEADBUSH), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.GLOWSHROOM.ordinal()), new ItemStack(BOPBlocks.grass, 1, BlockBOPGrass.BOPGrassType.OVERGROWN_STONE.ordinal()), new ItemStack(BOPBlocks.mushroom, 1, BlockBOPMushroom.MushroomType.GLOWSHROOM.ordinal()), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.MYSTIC.ordinal()), "grass", BlockBOPFlower.paging.getVariantItem(BOPFlowers.GLOWFLOWER), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.OMINOUS.ordinal()), "grass", BlockBOPFlower.paging.getVariantItem(BOPFlowers.DEATHBLOOM), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.ORIGIN.ordinal()), new ItemStack(BOPBlocks.grass, 1, BlockBOPGrass.BOPGrassType.ORIGIN.ordinal()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.ROSE), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.WASTELAND.ordinal()), new ItemStack(BOPBlocks.dried_sand), BlockBOPFlower.paging.getVariantItem(BOPFlowers.WILTED_LILY), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.NETHER.ordinal()), new ItemStack(BOPBlocks.grass, 1, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK.ordinal()), BlockBOPFlower.paging.getVariantItem(BOPFlowers.BURNING_BLOSSOM), new ItemStack(BOPItems.jar_empty)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.terrarium, 1, BlockBOPTerrarium.TerrariumType.ENDER.ordinal()), new ItemStack(BOPBlocks.grass, 1, BlockBOPGrass.BOPGrassType.SPECTRAL_MOSS.ordinal()), BlockBOPPlant.paging.getVariantItem(BOPPlants.SPECTRALFERN), new ItemStack(BOPItems.jar_empty)));

        // Vanilla convenience recipes
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAND), new ItemStack(BOPBlocks.dried_sand), new ItemStack(Items.WATER_BUCKET));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BOPBlocks.plant_0, 1, BOPPlants.LEAFPILE.ordinal()), "treeLeaves"));
    }
    
    
    private static void addSmeltingRecipes()
    {
        
        // Register smelting recipes
        GameRegistry.addSmelting(new ItemStack(BOPBlocks.mud), new ItemStack(Blocks.DIRT), 0F);
        GameRegistry.addSmelting(BlockBOPPlant.paging.getVariantItem(BOPPlants.TINYCACTUS), new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()), 0.2F);
        GameRegistry.addSmelting(BOPItems.mudball, new ItemStack(BOPItems.mud_brick), 0F);
        for (BOPWoods wood : BOPWoods.values())
        {
            if (wood.canMakeCharcoal())
            {
                GameRegistry.addSmelting(BlockBOPLog.paging.getVariantItem(wood), new ItemStack(Items.COAL, 1, 1), 0.15F);
            }
        }
        
        // Register items which can be used as fuel
        FurnaceFuelHandler bopFuel = new FurnaceFuelHandler();
        GameRegistry.registerFuelHandler(bopFuel);

        bopFuel.addFuel(BOPBlocks.sapling_0, 100);
        bopFuel.addFuel(BOPBlocks.sapling_1, 100);
        bopFuel.addFuel(BOPBlocks.sapling_2, 100);
        bopFuel.addFuel(BOPBlocks.wood_slab_0, 150);
        bopFuel.addFuel(BOPBlocks.wood_slab_1, 150);
        // Note, we don't have to add all the other wood blocks - by default any block with Material = wood burns with value of 300
        // See TileEntityFurnace.getItemBurnTime()

        bopFuel.addFuel(BOPItems.ash, 400); // TODO: really? 400?  Ash is already burnt but burns better than wooden planks?
        
    }
    
    
    private static void addOreRegistration()
    {
        //Registration in Ore Dictionary

        OreDictionary.registerOre("stickWood", new ItemStack(BOPBlocks.bamboo));
        OreDictionary.registerOre("stickWood", BlockBOPPlant.paging.getVariantItem(BOPPlants.RIVERCANE));

        OreDictionary.registerOre("ballMud", new ItemStack(BOPItems.mudball));

        OreDictionary.registerOre("blockMeatRaw", new ItemStack(BOPBlocks.flesh, 1, 0));
        
        OreDictionary.registerOre("grass", new ItemStack(BOPBlocks.grass, 1, BlockBOPGrass.BOPGrassType.LOAMY.ordinal()));
        OreDictionary.registerOre("grass", new ItemStack(BOPBlocks.grass, 1, BlockBOPGrass.BOPGrassType.SILTY.ordinal()));
        OreDictionary.registerOre("grass", new ItemStack(BOPBlocks.grass, 1, BlockBOPGrass.BOPGrassType.SANDY.ordinal()));
        OreDictionary.registerOre("grass", new ItemStack(BOPBlocks.grass, 1, BlockBOPGrass.BOPGrassType.DAISY.ordinal()));
        OreDictionary.registerOre("grass", new ItemStack(BOPBlocks.grass, 1, BlockBOPGrass.BOPGrassType.ORIGIN.ordinal()));

        OreDictionary.registerOre("dirt", new ItemStack(BOPBlocks.dirt, 1, BlockBOPDirt.BOPDirtType.LOAMY.ordinal()));
        OreDictionary.registerOre("dirt", new ItemStack(BOPBlocks.dirt, 1, BlockBOPDirt.BOPDirtType.SILTY.ordinal()));
        OreDictionary.registerOre("dirt", new ItemStack(BOPBlocks.dirt, 1, BlockBOPDirt.BOPDirtType.SANDY.ordinal()));
        
        OreDictionary.registerOre("blockMud", new ItemStack(BOPBlocks.mud));
        
        OreDictionary.registerOre("foodMushroompowder", new ItemStack(BOPItems.shroompowder));
        OreDictionary.registerOre("foodFruitsalad", new ItemStack(BOPItems.saladfruit));
        OreDictionary.registerOre("foodVeggiesalad", new ItemStack(BOPItems.saladveggie));
        OreDictionary.registerOre("foodMushroomsalad", new ItemStack(BOPItems.saladshroom));
        OreDictionary.registerOre("foodFilledhoneycomb", new ItemStack(BOPItems.filled_honeycomb));
        OreDictionary.registerOre("foodAmbrosia", new ItemStack(BOPItems.ambrosia));
        OreDictionary.registerOre("foodBowlofrice", new ItemStack(BOPItems.ricebowl));
        
        OreDictionary.registerOre("cropPeach", new ItemStack(BOPItems.peach));
        OreDictionary.registerOre("cropPersimmon", new ItemStack(BOPItems.persimmon));
        OreDictionary.registerOre("cropTurnip", new ItemStack(BOPItems.turnip));
        OreDictionary.registerOre("cropPear", new ItemStack(BOPItems.pear));
        
        OreDictionary.registerOre("listAllfruit", new ItemStack(BOPItems.peach));
        OreDictionary.registerOre("listAllfruit", new ItemStack(BOPItems.persimmon));
        OreDictionary.registerOre("listAllrootveggie", new ItemStack(BOPItems.turnip));
        OreDictionary.registerOre("listAllveggie", new ItemStack(BOPItems.turnip));
        OreDictionary.registerOre("listAllfruit", new ItemStack(BOPItems.pear));
        
        OreDictionary.registerOre("seedTurnip", new ItemStack(BOPItems.turnip_seeds));
        OreDictionary.registerOre("listAllseed", new ItemStack(BOPItems.turnip_seeds));

        OreDictionary.registerOre("dyeBlue", new ItemStack(BOPItems.blue_dye));
        OreDictionary.registerOre("dyeBrown", new ItemStack(BOPItems.brown_dye));
        OreDictionary.registerOre("dyeGreen", new ItemStack(BOPItems.green_dye));
        OreDictionary.registerOre("dyeWhite", new ItemStack(BOPItems.white_dye));
        OreDictionary.registerOre("dyeBlack", new ItemStack(BOPItems.black_dye));
        
        OreDictionary.registerOre("record", new ItemStack(BOPItems.record_corruption));
        OreDictionary.registerOre("record", new ItemStack(BOPItems.record_wanderer));
        
        for (BOPGems gem : BOPGems.values())
        {
            String gemName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, gem.name());
            OreDictionary.registerOre("gem"+gemName, new ItemStack(BOPItems.gem, 1, gem.ordinal()));
            OreDictionary.registerOre("ore"+gemName, new ItemStack(BOPBlocks.gem_ore , 1, gem.ordinal()));
        }
        
        for (BOPFlowers flower : BOPFlowers.values())
        {
            String flowerName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flower.name());
            OreDictionary.registerOre("flower"+flowerName, BlockBOPFlower.paging.getVariantItem(flower));
        }
        
        for (BOPPlants plant : BOPPlants.values())
        {
            String plantName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, plant.name());
            OreDictionary.registerOre("plant"+plantName, BlockBOPPlant.paging.getVariantItem(plant));
        }
        OreDictionary.registerOre("plantFlax", ((BlockBOPDoublePlant)BOPBlocks.double_plant).getVariantItem(BlockBOPDoublePlant.DoublePlantType.FLAX));
        OreDictionary.registerOre("plantCattail", ((BlockBOPDoublePlant)BOPBlocks.double_plant).getVariantItem(BlockBOPDoublePlant.DoublePlantType.TALL_CATTAIL));
        
        for (BOPTrees tree : BOPTrees.values())
        {
            OreDictionary.registerOre("treeLeaves", BlockBOPLeaves.paging.getVariantItem(tree));
            if (tree.hasSapling())
            {
                OreDictionary.registerOre("treeSapling", BlockBOPSapling.paging.getVariantItem(tree));
            }
        }
        // TODO: implement fruit trees
        // OreDictionary.registerOre("treeLeaves", new ItemStack(BOPCBlocks.appleLeaves, 1, OreDictionary.WILDCARD_VALUE));
        // OreDictionary.registerOre("treeLeaves", new ItemStack(BOPCBlocks.persimmonLeaves, 1, OreDictionary.WILDCARD_VALUE));
        
        for (BOPWoods wood : BOPWoods.values())
        {
            OreDictionary.registerOre("logWood", BlockBOPLog.paging.getVariantItem(wood));   
            if (wood.hasPlanks())
            {
                OreDictionary.registerOre("plankWood", BlockBOPPlanks.paging.getVariantItem(wood));
                OreDictionary.registerOre("slabWood", BlockBOPHalfWoodSlab.paging.getVariantItem(wood));
                OreDictionary.registerOre("stairWood", new ItemStack(BlockBOPWoodStairs.getBlock(wood)));
                OreDictionary.registerOre("fenceWood", new ItemStack(BlockBOPFence.getBlock(wood)));
                OreDictionary.registerOre("fenceGateWood", new ItemStack(BlockBOPFenceGate.getBlock(wood)));
            }
        }

    }
}
