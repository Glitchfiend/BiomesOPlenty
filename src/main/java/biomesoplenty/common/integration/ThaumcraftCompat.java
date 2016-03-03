package biomesoplenty.common.integration;

import com.sun.jna.platform.unix.X11.XSizeHints.Aspect;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.BlockBOPBones;
import biomesoplenty.common.block.BlockBOPBones.BoneType;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPGem;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPGrass.BOPGrassType;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPMushroom.MushroomType;
import biomesoplenty.common.block.BlockBOPPlanks;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockBOPSand;
import biomesoplenty.common.block.BlockBOPSapling;
import biomesoplenty.common.block.BlockBOPSeaweed;
import biomesoplenty.common.block.BlockBOPSeaweed.SeaweedType;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

//TODO: Re-add this
public class ThaumcraftCompat 
{
    public static void init()
    {
        //addThaumcraftAspects();
        //addThaumcraftGolemsSupport();
    }

    /*private static void addThaumcraftAspects()
    {
        //Thaumcraft sets most aspects automatically, just special cases are there

        //Logs

        addAspectsToState(BlockBOPLog.paging.getVariantState(BOPWoods.UMBRAN), new Aspect[] { Aspect.PLANT, Aspect.DARKNESS }, new int[] { 4, 2 });
        addAspectsToState(BlockBOPLog.paging.getVariantState(BOPWoods.ETHEREAL), new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 4, 2 });
        addAspectsToState(BlockBOPLog.paging.getVariantState(BOPWoods.HELLBARK), new Aspect[] { Aspect.PLANT, Aspect.FIRE }, new int[] { 4, 2 });
        addAspectsToState(BlockBOPLog.paging.getVariantState(BOPWoods.DEAD), new Aspect[] { Aspect.PLANT, Aspect.DEATH }, new int[] { 4, 2 });
        addAspectsToState(BlockBOPLog.paging.getVariantState(BOPWoods.SACRED_OAK), new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 4, 2 });

        //Planks

        addAspectsToState(BlockBOPPlanks.paging.getVariantState(BOPWoods.SACRED_OAK), new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPPlanks.paging.getVariantState(BOPWoods.MAGIC), new Aspect[] { Aspect.PLANT }, new int[] { 1 });
        addAspectsToState(BlockBOPPlanks.paging.getVariantState(BOPWoods.UMBRAN), new Aspect[] { Aspect.PLANT, Aspect.DARKNESS }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPPlanks.paging.getVariantState(BOPWoods.ETHEREAL), new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 1, 1 });
        addAspectsToBlock(BOPBlocks.bamboo_thatching, new Aspect[] { Aspect.WATER, Aspect.PLANT }, new int[] { 4, 4 });
        addAspectsToState(BlockBOPPlanks.paging.getVariantState(BOPWoods.HELLBARK), new Aspect[] { Aspect.PLANT, Aspect.FIRE }, new int[] { 1, 1 });

        //Leaves

        //TODO: addAspectsToBlock(BOPCBlocks.appleLeaves, new Aspect[] { Aspect.PLANT, Aspect.LIFE }, new int[] { 1, 1 });
        //TODO: addAspectsToBlock(BOPCBlocks.persimmonLeaves, new Aspect[] { Aspect.PLANT, Aspect.LIFE }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPLeaves.paging.getVariantState(BOPTrees.DEAD), new Aspect[] { Aspect.PLANT, Aspect.DEATH }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPLeaves.paging.getVariantState(BOPTrees.UMBRAN), new Aspect[] { Aspect.PLANT, Aspect.DARKNESS }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPLeaves.paging.getVariantState(BOPTrees.ETHEREAL), new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPLeaves.paging.getVariantState(BOPTrees.SACRED_OAK), new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPLeaves.paging.getVariantState(BOPTrees.MAGIC), new Aspect[] { Aspect.PLANT }, new int[] { 1 });
        addAspectsToState(BlockBOPLeaves.paging.getVariantState(BOPTrees.HELLBARK), new Aspect[] { Aspect.PLANT, Aspect.FIRE }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPLeaves.paging.getVariantState(BOPTrees.FLOWERING), new Aspect[] { Aspect.PLANT, Aspect.LIFE }, new int[] { 1, 1 });

        //Saplings

        //TODO: Apple addAspectsToState(BlockBOPSapling.paging.getVariantState(BOPTrees), new Aspect[] { Aspect.PLANT, Aspect.PLANT, Aspect.LIFE }, new int[] { 1, 2, 2 });
        //TODO: Persimmon addAspectsToState(BOPCBlocks.saplings, 15, new Aspect[] { Aspect.PLANT, Aspect.PLANT, Aspect.LIFE }, new int[] { 1, 1, 2 });
        addAspectsToState(BlockBOPSapling.paging.getVariantState(BOPTrees.ETHEREAL), new Aspect[] { Aspect.PLANT, Aspect.PLANT, Aspect.LIGHT }, new int[] { 1, 1, 2 });
        addAspectsToState(BlockBOPSapling.paging.getVariantState(BOPTrees.MAGIC), new Aspect[] { Aspect.PLANT, Aspect.PLANT }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPSapling.paging.getVariantState(BOPTrees.UMBRAN), new Aspect[] { Aspect.PLANT, Aspect.PLANT, Aspect.DARKNESS }, new int[] { 1, 1, 2 });
        addAspectsToState(BlockBOPSapling.paging.getVariantState(BOPTrees.DEAD), new Aspect[] { Aspect.PLANT, Aspect.PLANT, Aspect.DEATH }, new int[] { 1, 1, 2 });
        addAspectsToState(BlockBOPSapling.paging.getVariantState(BOPTrees.HELLBARK), new Aspect[] { Aspect.PLANT, Aspect.PLANT, Aspect.FIRE }, new int[] { 1, 1, 2 });
        addAspectsToState(BlockBOPSapling.paging.getVariantState(BOPTrees.SACRED_OAK), new Aspect[] { Aspect.PLANT, Aspect.PLANT, Aspect.LIGHT }, new int[] { 1, 1, 2 });
        addAspectsToState(BlockBOPSapling.paging.getVariantState(BOPTrees.FLOWERING), new Aspect[] { Aspect.PLANT, Aspect.PLANT, Aspect.LIFE }, new int[] { 1, 1, 2 });

        //Blocks

        addAspectsToBlock(BOPBlocks.mud, new Aspect[] { Aspect.WATER, Aspect.EARTH }, new int[] { 2, 5 });
        addAspectsToBlock(BOPBlocks.dried_sand, new Aspect[] { Aspect.ENTROPY, Aspect.EARTH }, new int[] { 1, 1 });
        addAspectsToBlock(BOPBlocks.hard_ice, new Aspect[] { Aspect.EARTH, Aspect.COLD }, new int[] { 2, 2 });
        addAspectsToState(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BOPGrassType.ORIGIN), new Aspect[] { Aspect.EARTH, Aspect.PLANT }, new int[] { 1, 2 });
        addAspectsToBlock(BOPBlocks.crystal, new Aspect[] { Aspect.DESIRE, Aspect.LIGHT, Aspect.CRYSTAL }, new int[] { 5, 5, 5 });
        addAspectsToBlock(BOPBlocks.crag_rock, new Aspect[] { Aspect.EARTH }, new int[] { 2 });
        addAspectsToState(BOPBlocks.sand.getDefaultState().withProperty(BlockBOPSand.VARIANT, BlockBOPSand.SandType.QUICKSAND), new Aspect[] { Aspect.EARTH, Aspect.TRAP }, new int[] { 2, 4 });
        addAspectsToState(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BOPGrassType.SMOLDERING), new Aspect[] { Aspect.EARTH, Aspect.FIRE }, new int[] { 2, 1 });
        addAspectsToState(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.AMETHYST), new Aspect[] { Aspect.DESIRE, Aspect.ORDER, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 3, 7, 3 });
        addAspectsToState(BOPBlocks.gem_block.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.AMETHYST), new Aspect[] { Aspect.DESIRE, Aspect.ORDER, Aspect.CRYSTAL }, new int[] { 5, 5, 8 });
        addAspectsToState(BlockBOPLeaves.paging.getVariantState(BOPTrees.RED_BIG_FLOWER), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPLeaves.paging.getVariantState(BOPTrees.YELLOW_BIG_FLOWER), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BOPBlocks.bone_segment.getDefaultState().withProperty(BlockBOPBones.VARIANT, BoneType.SMALL), new Aspect[] { Aspect.DEATH }, new int[] { 3 });
        addAspectsToState(BOPBlocks.bone_segment.getDefaultState().withProperty(BlockBOPBones.VARIANT, BoneType.MEDIUM), new Aspect[] { Aspect.DEATH }, new int[] { 5 });
        addAspectsToState(BOPBlocks.bone_segment.getDefaultState().withProperty(BlockBOPBones.VARIANT, BoneType.LARGE), new Aspect[] { Aspect.DEATH }, new int[] { 7 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.POISONIVY), new Aspect[] { Aspect.PLANT }, new int[] { 2 });

        //Plants
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.CATTAIL), new Aspect[] { Aspect.PLANT, Aspect.WATER }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.REED), new Aspect[] { Aspect.PLANT, Aspect.PLANT }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.CATTAIL), new Aspect[] { Aspect.PLANT, Aspect.WATER }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.SPROUT), new Aspect[] { Aspect.PLANT }, new int[] { 2 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.BUSH), new Aspect[] { Aspect.PLANT }, new int[] { 2 });
        addAspectsToState(BOPBlocks.seaweed.getDefaultState().withProperty(BlockBOPSeaweed.VARIANT, SeaweedType.KELP), new Aspect[] { Aspect.PLANT, Aspect.WATER }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.ROOT), new Aspect[] { Aspect.PLANT, Aspect.EARTH }, new int[] { 1, 1 });
        addAspectsToBlock(BOPBlocks.bamboo, new Aspect[] { Aspect.PLANT, Aspect.LIFE }, new int[] { 1, 1 });
        addAspectsToState(BOPBlocks.mushroom.getDefaultState().withProperty(BlockBOPMushroom.VARIANT, MushroomType.TOADSTOOL), new Aspect[] { Aspect.PLANT }, new int[] { 3 });
        addAspectsToState(BOPBlocks.mushroom.getDefaultState().withProperty(BlockBOPMushroom.VARIANT, MushroomType.PORTOBELLO), new Aspect[] { Aspect.PLANT }, new int[] { 3 });
        addAspectsToState(BOPBlocks.mushroom.getDefaultState().withProperty(BlockBOPMushroom.VARIANT, MushroomType.BLUE_MILK_CAP), new Aspect[] { Aspect.PLANT }, new int[] { 3 });
        addAspectsToState(BOPBlocks.mushroom.getDefaultState().withProperty(BlockBOPMushroom.VARIANT, MushroomType.GLOWSHROOM), new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 3, 1 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.TINYCACTUS), new Aspect[] { Aspect.PLANT, Aspect.AVERSION }, new int[] { 2, 1 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.DESERTSPROUTS), new Aspect[] { Aspect.PLANT }, new int[] { 1 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.DUNEGRASS), new Aspect[] { Aspect.PLANT, Aspect.AIR }, new int[] { 1, 1 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.THORN), new Aspect[] { Aspect.PLANT, Aspect.AVERSION }, new int[] { 2, 1 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.DESERTGRASS), new Aspect[] { Aspect.PLANT }, new int[] { 1 });
        addAspectsToState(BlockBOPPlant.paging.getVariantState(BOPPlants.DEADGRASS), new Aspect[] { Aspect.PLANT, Aspect.DEATH }, new int[] { 1, 2 });
        addAspectsToBlock(BOPBlocks.tree_moss, new Aspect[] { Aspect.PLANT }, new int[] { 2 });
        //TODO: addAspectsToBlock(BOPBlocks.willow, new Aspect[] { Aspect.PLANT }, new int[] { 2 });

        //Flowers
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.VIOLET), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.BLUE_HYDRANGEA), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.DEATHBLOOM), new Aspect[] { Aspect.PLANT, Aspect.DEATH }, new int[] { 4, 1 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.GLOWFLOWER), new Aspect[] { Aspect.PLANT, Aspect.LIGHT }, new int[] { 4, 1 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.WHITE_ANEMONE), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.SWAMPFLOWER), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.WILDFLOWER), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.ORANGE_COSMOS), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.WILTED_LILY), new Aspect[] { Aspect.PLANT }, new int[] { 2 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.PINK_DAFFODIL), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.BROMELIAD), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.CLOVER), new Aspect[] { Aspect.PLANT }, new int[] { 2 });

        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.PINK_HIBISCUS), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.LILY_OF_THE_VALLEY), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.BURNING_BLOSSOM), new Aspect[] { Aspect.PLANT, Aspect.FIRE }, new int[] { 4, 2 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.LAVENDER), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.GOLDENROD), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.BLUEBELLS), new Aspect[] { Aspect.PLANT }, new int[] { 4 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.MINERS_DELIGHT), new Aspect[] { Aspect.PLANT, Aspect.EARTH }, new int[] { 4, 1 });
        addAspectsToState(BlockBOPFlower.paging.getVariantState(BOPFlowers.ICY_IRIS), new Aspect[] { Aspect.PLANT, Aspect.COLD }, new int[] { 4, 2 });
        
        //Gems
        addAspectsToState(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.RUBY), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
        addAspectsToState(BOPBlocks.gem_block.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.RUBY), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL }, new int[] { 5, 7 });
        addAspectsToState(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.PERIDOT), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
        addAspectsToState(BOPBlocks.gem_block.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.PERIDOT), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL }, new int[] { 5, 7 });
        addAspectsToState(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.TOPAZ), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
        addAspectsToState(BOPBlocks.gem_block.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.TOPAZ), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL }, new int[] { 5, 7 });
        addAspectsToState(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.TANZANITE), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
        addAspectsToState(BOPBlocks.gem_block.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.TANZANITE), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL }, new int[] { 5, 7 });
        addAspectsToState(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.MALACHITE), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
        addAspectsToState(BOPBlocks.gem_block.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.MALACHITE), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL }, new int[] { 5, 7 });
        addAspectsToState(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.SAPPHIRE), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
        addAspectsToState(BOPBlocks.gem_block.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.SAPPHIRE), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL }, new int[] { 5, 7 });
        addAspectsToState(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.AMBER), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL, Aspect.EARTH }, new int[] { 3, 5, 3 });
        addAspectsToState(BOPBlocks.gem_block.getDefaultState().withProperty(BlockBOPGem.VARIANT, BOPGems.AMBER), new Aspect[] { Aspect.DESIRE, Aspect.CRYSTAL }, new int[] { 5, 7 });

        //Items
        addAspectsToItem(BOPItems.gem, new Aspect[] { Aspect.DESIRE, Aspect.ORDER, Aspect.CRYSTAL }, new int[] { 4, 4, 4 });
        addAspectsToItem(BOPItems.ash, new Aspect[] { Aspect.ENTROPY, Aspect.EXCHANGE }, new int[] { 1, 1 });
        addAspectsToItem(BOPItems.crystal_shard, new Aspect[] { Aspect.CRYSTAL, Aspect.LIGHT, Aspect.DESIRE }, new int[] { 4, 2, 3 });
        addAspectsToItem(BOPItems.mud_brick, new Aspect[] { Aspect.EARTH, Aspect.FIRE }, new int[] { 2, 1 });
        addAspectsToItemMeta(BOPItems.dart, 0, new Aspect[] { Aspect.AVERSION }, new int[] { 1 });
        addAspectsToItemMeta(BOPItems.dart, 1, new Aspect[] { Aspect.AVERSION }, new int[] { 1 });
    }

    //Allows Thaumcraft golems to harvest BoP crops
    private static void addThaumcraftGolemsSupport()
    {
        addClickableCrop(BlockBOPPlant.paging.getVariantState(BOPPlants.BERRYBUSH));
    }

    private static void addAspectsToBlock(Block block, Aspect[] aspects, int[] amounts)
    {
        addAspects(new ItemStack(block), aspects, amounts);
    }

    private static void addAspectsToState(IBlockState state, Aspect[] aspects, int[] amounts)
    {
        addAspects(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), aspects, amounts);
    }

    private static void addAspectsToItem(Item item, Aspect[] aspects, int[] amounts)
    {
        addAspects(new ItemStack(item), aspects, amounts);
    }

    private static void addAspectsToItemMeta(Item items, int meta, Aspect[] aspects, int[] amounts)
    {
        addAspects(new ItemStack(items, 1, meta), aspects, amounts);
    }
    
    private static void addClickableCrop(IBlockState state)
    {
        FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)));
    }

    private static void addAspects(ItemStack stack, Aspect[] aspects, int[] amounts)
    {
        AspectList list = new AspectList();
        for (int i = 0; i < aspects.length; i++)
            list.add(aspects[i], amounts[i]);

        ThaumcraftApi.registerObjectTag(stack, list);
    }*/
}
