package biomesoplenty.neoforge.datagen;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.block.HugeLilyPadBlock;
import biomesoplenty.block.properties.QuarterProperty;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class BOPBlockLoot extends BlockLootSubProvider
{
    protected static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.SHEARS));
    private static final Set<Item> EXPLOSION_RESISTANT = Set.of();

    public BOPBlockLoot(HolderLookup.Provider lookup)
    {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), lookup);
    }

    @Override
    protected void generate()
    {
        HolderLookup.RegistryLookup<Enchantment> lookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        // Sandstone
        this.dropSelf(BOPBlocks.WHITE_SAND);
        this.dropSelf(BOPBlocks.WHITE_SANDSTONE);
        this.dropSelf(BOPBlocks.CUT_WHITE_SANDSTONE);
        this.add(BOPBlocks.CUT_WHITE_SANDSTONE_SLAB, (p_251501_) -> { return this.createSlabItemTable(p_251501_); });
        this.dropSelf(BOPBlocks.CHISELED_WHITE_SANDSTONE);
        this.dropSelf(BOPBlocks.SMOOTH_WHITE_SANDSTONE);
        this.dropSelf(BOPBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS);
        this.add(BOPBlocks.SMOOTH_WHITE_SANDSTONE_SLAB, (p_249500_) -> { return this.createSlabItemTable(p_249500_); });
        this.add(BOPBlocks.WHITE_SANDSTONE_SLAB, (p_249500_) -> { return this.createSlabItemTable(p_249500_); });
        this.dropSelf(BOPBlocks.WHITE_SANDSTONE_STAIRS);
        this.dropSelf(BOPBlocks.WHITE_SANDSTONE_WALL);

        this.dropSelf(BOPBlocks.ORANGE_SAND);
        this.dropSelf(BOPBlocks.ORANGE_SANDSTONE);
        this.dropSelf(BOPBlocks.CUT_ORANGE_SANDSTONE);
        this.add(BOPBlocks.CUT_ORANGE_SANDSTONE_SLAB, (p_251501_) -> { return this.createSlabItemTable(p_251501_); });
        this.dropSelf(BOPBlocks.CHISELED_ORANGE_SANDSTONE);
        this.dropSelf(BOPBlocks.SMOOTH_ORANGE_SANDSTONE);
        this.dropSelf(BOPBlocks.SMOOTH_ORANGE_SANDSTONE_STAIRS);
        this.add(BOPBlocks.SMOOTH_ORANGE_SANDSTONE_SLAB, (p_249500_) -> { return this.createSlabItemTable(p_249500_); });
        this.add(BOPBlocks.ORANGE_SANDSTONE_SLAB, (p_249500_) -> { return this.createSlabItemTable(p_249500_); });
        this.dropSelf(BOPBlocks.ORANGE_SANDSTONE_STAIRS);
        this.dropSelf(BOPBlocks.ORANGE_SANDSTONE_WALL);

        this.add(BOPBlocks.MOSSY_BLACK_SAND, (p_249779_) -> { return this.createSingleItemTableWithSilkTouch(p_249779_, BOPBlocks.BLACK_SAND); });
        this.dropSelf(BOPBlocks.BLACK_SAND);
        this.dropSelf(BOPBlocks.BLACK_SANDSTONE);
        this.dropSelf(BOPBlocks.CUT_BLACK_SANDSTONE);
        this.add(BOPBlocks.CUT_BLACK_SANDSTONE_SLAB, (p_251501_) -> { return this.createSlabItemTable(p_251501_); });
        this.dropSelf(BOPBlocks.CHISELED_BLACK_SANDSTONE);
        this.dropSelf(BOPBlocks.SMOOTH_BLACK_SANDSTONE);
        this.dropSelf(BOPBlocks.SMOOTH_BLACK_SANDSTONE_STAIRS);
        this.add(BOPBlocks.SMOOTH_BLACK_SANDSTONE_SLAB, (p_249500_) -> { return this.createSlabItemTable(p_249500_); });
        this.add(BOPBlocks.BLACK_SANDSTONE_SLAB, (p_249500_) -> { return this.createSlabItemTable(p_249500_); });
        this.dropSelf(BOPBlocks.BLACK_SANDSTONE_STAIRS);
        this.dropSelf(BOPBlocks.BLACK_SANDSTONE_WALL);

        // Misc Terrain Blocks
        this.dropSelf(BOPBlocks.THERMAL_CALCITE);
        this.dropSelf(BOPBlocks.THERMAL_CALCITE_VENT);
        this.dropSelf(BOPBlocks.DRIED_SALT);

        // Flesh Blocks
        this.dropSelf(BOPBlocks.FLESH);
        this.dropSelf(BOPBlocks.POROUS_FLESH);
        this.addStrandPlantDropTable(BOPBlocks.FLESH_TENDONS, BOPBlocks.FLESH_TENDONS_STRAND);
        this.add(BOPBlocks.EYEBULB, (p_250741_) -> { return this.createSinglePropConditionTable(p_250741_, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER); });
        this.add(BOPBlocks.HAIR, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.dropWhenSilkTouch(BOPBlocks.PUS_BUBBLE);

        // Brimstone
        this.dropSelf(BOPBlocks.BRIMSTONE);
        this.dropSelf(BOPBlocks.BRIMSTONE_BRICKS);
        this.add(BOPBlocks.BRIMSTONE_BRICK_SLAB, (p_251501_) -> { return this.createSlabItemTable(p_251501_); });
        this.dropSelf(BOPBlocks.BRIMSTONE_BRICK_STAIRS);
        this.dropSelf(BOPBlocks.BRIMSTONE_BRICK_WALL);
        this.dropSelf(BOPBlocks.CHISELED_BRIMSTONE_BRICKS);
        this.dropWhenSilkTouch(BOPBlocks.BRIMSTONE_FUMAROLE);
        this.dropWhenSilkTouch(BOPBlocks.BRIMSTONE_CLUSTER);
        this.dropWhenSilkTouch(BOPBlocks.BRIMSTONE_BUD);

        this.dropWhenSilkTouch(BOPBlocks.BLACKSTONE_SPINES);
        this.dropWhenSilkTouch(BOPBlocks.BLACKSTONE_BULB);

        // Rose Quartz
        this.dropSelf(BOPBlocks.ROSE_QUARTZ_BLOCK);
        this.add(BOPBlocks.ROSE_QUARTZ_CLUSTER, (p_252201_) -> { return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(BOPItems.ROSE_QUARTZ_CHUNK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(lookup.getOrThrow(Enchantments.FORTUNE))).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(this.applyExplosionDecay(p_252201_, LootItem.lootTableItem(BOPItems.ROSE_QUARTZ_CHUNK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))); });
        this.dropWhenSilkTouch(BOPBlocks.SMALL_ROSE_QUARTZ_BUD);
        this.dropWhenSilkTouch(BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD);
        this.dropWhenSilkTouch(BOPBlocks.LARGE_ROSE_QUARTZ_BUD);

        // End Blocks
        this.dropWhenSilkTouch(BOPBlocks.BARNACLES);
        this.dropWhenSilkTouch(BOPBlocks.WISPJELLY);
        this.add(BOPBlocks.ALGAL_END_STONE, (p_249779_) -> { return this.createSingleItemTableWithSilkTouch(p_249779_, Blocks.END_STONE); });
        this.add(BOPBlocks.UNMAPPED_END_STONE, (p_249779_) -> { return this.createSingleItemTableWithSilkTouch(p_249779_, Blocks.END_STONE); });
        this.add(BOPBlocks.NULL_END_STONE, (p_249779_) -> { return this.createSingleItemTableWithSilkTouch(p_249779_, Blocks.END_STONE); });
        this.dropWhenSilkTouch(BOPBlocks.NULL_BLOCK);
        this.dropWhenSilkTouch(BOPBlocks.NULL_LEAVES);
        this.dropWhenSilkTouch(BOPBlocks.NULL_PLANT);
        this.dropWhenSilkTouch(BOPBlocks.ANOMALY);

        // Mushrooms
        this.dropSelf(BOPBlocks.TOADSTOOL);
        this.add(BOPBlocks.TOADSTOOL_BLOCK, (p_248785_) -> { return this.createMushroomBlockDrop(p_248785_, BOPBlocks.TOADSTOOL); });
        this.dropSelf(BOPBlocks.GLOWSHROOM);
        this.add(BOPBlocks.GLOWSHROOM_BLOCK, (p_248785_) -> { return this.createMushroomBlockDrop(p_248785_, BOPBlocks.GLOWSHROOM); });
        this.dropSelf(BOPBlocks.GLOWING_MOSS_BLOCK);
        this.dropSelf(BOPBlocks.GLOWING_MOSS_CARPET);
        this.addStrandPlantDropTable(BOPBlocks.GLOWWORM_SILK, BOPBlocks.GLOWWORM_SILK_STRAND);

        // Webbing Blocks
        this.dropWhenSilkTouch(BOPBlocks.SPIDER_EGG);
        this.addStrandPlantDropTable(BOPBlocks.HANGING_COBWEB, BOPBlocks.HANGING_COBWEB_STRAND);
        this.add(BOPBlocks.WEBBING, (p_249543_) -> { return this.createMultifaceBlockDrops(p_249543_, HAS_SHEARS); });

        this.add(BOPBlocks.ORIGIN_GRASS_BLOCK, (p_249779_) -> { return this.createSingleItemTableWithSilkTouch(p_249779_, Blocks.DIRT); });

        // Woodless Trees
        this.dropSelf(BOPBlocks.ORIGIN_SAPLING);
        this.add(BOPBlocks.ORIGIN_LEAVES, (p_280934_) -> { return this.createOakLeavesDrops(p_280934_, BOPBlocks.ORIGIN_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.FLOWERING_OAK_SAPLING);
        this.add(BOPBlocks.FLOWERING_OAK_LEAVES, (p_280934_) -> { return this.createOakLeavesDrops(p_280934_, BOPBlocks.FLOWERING_OAK_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.CYPRESS_SAPLING);
        this.add(BOPBlocks.CYPRESS_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.CYPRESS_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.SNOWBLOSSOM_SAPLING);
        this.add(BOPBlocks.SNOWBLOSSOM_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.SNOWBLOSSOM_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.RAINBOW_BIRCH_SAPLING);
        this.add(BOPBlocks.RAINBOW_BIRCH_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.RAINBOW_BIRCH_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });

        // Wood
        this.dropSelf(BOPBlocks.FIR_SAPLING);
        this.add(BOPBlocks.FIR_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.FIR_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.FIR_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_FIR_LOG);
        this.dropSelf(BOPBlocks.FIR_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_FIR_WOOD);
        this.dropSelf(BOPBlocks.FIR_PLANKS);
        this.add(BOPBlocks.FIR_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.FIR_STAIRS);
        this.dropSelf(BOPBlocks.FIR_FENCE);
        this.dropSelf(BOPBlocks.FIR_FENCE_GATE);
        this.add(BOPBlocks.FIR_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.FIR_TRAPDOOR);
        this.dropSelf(BOPBlocks.FIR_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.FIR_BUTTON);
        this.dropSelf(BOPBlocks.FIR_SIGN);
        this.dropSelf(BOPBlocks.FIR_HANGING_SIGN);

        this.dropSelf(BOPBlocks.PINE_SAPLING);
        this.add(BOPBlocks.PINE_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.PINE_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.PINE_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_PINE_LOG);
        this.dropSelf(BOPBlocks.PINE_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_PINE_WOOD);
        this.dropSelf(BOPBlocks.PINE_PLANKS);
        this.add(BOPBlocks.PINE_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.PINE_STAIRS);
        this.dropSelf(BOPBlocks.PINE_FENCE);
        this.dropSelf(BOPBlocks.PINE_FENCE_GATE);
        this.add(BOPBlocks.PINE_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.PINE_TRAPDOOR);
        this.dropSelf(BOPBlocks.PINE_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.PINE_BUTTON);
        this.dropSelf(BOPBlocks.PINE_SIGN);
        this.dropSelf(BOPBlocks.PINE_HANGING_SIGN);

        this.dropSelf(BOPBlocks.RED_MAPLE_SAPLING);
        this.add(BOPBlocks.RED_MAPLE_LEAF_PILE, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.add(BOPBlocks.RED_MAPLE_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.RED_MAPLE_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.ORANGE_MAPLE_SAPLING);
        this.add(BOPBlocks.ORANGE_MAPLE_LEAF_PILE, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.add(BOPBlocks.ORANGE_MAPLE_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.ORANGE_MAPLE_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.YELLOW_MAPLE_SAPLING);
        this.add(BOPBlocks.YELLOW_MAPLE_LEAF_PILE, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.add(BOPBlocks.YELLOW_MAPLE_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.YELLOW_MAPLE_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.MAPLE_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_MAPLE_LOG);
        this.dropSelf(BOPBlocks.MAPLE_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_MAPLE_WOOD);
        this.dropSelf(BOPBlocks.MAPLE_PLANKS);
        this.add(BOPBlocks.MAPLE_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.MAPLE_STAIRS);
        this.dropSelf(BOPBlocks.MAPLE_FENCE);
        this.dropSelf(BOPBlocks.MAPLE_FENCE_GATE);
        this.add(BOPBlocks.MAPLE_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.MAPLE_TRAPDOOR);
        this.dropSelf(BOPBlocks.MAPLE_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.MAPLE_BUTTON);
        this.dropSelf(BOPBlocks.MAPLE_SIGN);
        this.dropSelf(BOPBlocks.MAPLE_HANGING_SIGN);

        this.dropSelf(BOPBlocks.REDWOOD_SAPLING);
        this.add(BOPBlocks.REDWOOD_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.REDWOOD_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.REDWOOD_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_REDWOOD_LOG);
        this.dropSelf(BOPBlocks.REDWOOD_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_REDWOOD_WOOD);
        this.dropSelf(BOPBlocks.REDWOOD_PLANKS);
        this.add(BOPBlocks.REDWOOD_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.REDWOOD_STAIRS);
        this.dropSelf(BOPBlocks.REDWOOD_FENCE);
        this.dropSelf(BOPBlocks.REDWOOD_FENCE_GATE);
        this.add(BOPBlocks.REDWOOD_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.REDWOOD_TRAPDOOR);
        this.dropSelf(BOPBlocks.REDWOOD_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.REDWOOD_BUTTON);
        this.dropSelf(BOPBlocks.REDWOOD_SIGN);
        this.dropSelf(BOPBlocks.REDWOOD_HANGING_SIGN);

        this.dropSelf(BOPBlocks.MAHOGANY_SAPLING);
        this.add(BOPBlocks.MAHOGANY_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.MAHOGANY_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.MAHOGANY_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_MAHOGANY_LOG);
        this.dropSelf(BOPBlocks.MAHOGANY_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_MAHOGANY_WOOD);
        this.dropSelf(BOPBlocks.MAHOGANY_PLANKS);
        this.add(BOPBlocks.MAHOGANY_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.MAHOGANY_STAIRS);
        this.dropSelf(BOPBlocks.MAHOGANY_FENCE);
        this.dropSelf(BOPBlocks.MAHOGANY_FENCE_GATE);
        this.add(BOPBlocks.MAHOGANY_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.MAHOGANY_TRAPDOOR);
        this.dropSelf(BOPBlocks.MAHOGANY_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.MAHOGANY_BUTTON);
        this.dropSelf(BOPBlocks.MAHOGANY_SIGN);
        this.dropSelf(BOPBlocks.MAHOGANY_HANGING_SIGN);

        this.dropSelf(BOPBlocks.JACARANDA_SAPLING);
        this.add(BOPBlocks.JACARANDA_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.JACARANDA_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.JACARANDA_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_JACARANDA_LOG);
        this.dropSelf(BOPBlocks.JACARANDA_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_JACARANDA_WOOD);
        this.dropSelf(BOPBlocks.JACARANDA_PLANKS);
        this.add(BOPBlocks.JACARANDA_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.JACARANDA_STAIRS);
        this.dropSelf(BOPBlocks.JACARANDA_FENCE);
        this.dropSelf(BOPBlocks.JACARANDA_FENCE_GATE);
        this.add(BOPBlocks.JACARANDA_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.JACARANDA_TRAPDOOR);
        this.dropSelf(BOPBlocks.JACARANDA_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.JACARANDA_BUTTON);
        this.dropSelf(BOPBlocks.JACARANDA_SIGN);
        this.dropSelf(BOPBlocks.JACARANDA_HANGING_SIGN);

        this.dropSelf(BOPBlocks.PALM_SAPLING);
        this.add(BOPBlocks.PALM_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.PALM_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.PALM_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_PALM_LOG);
        this.dropSelf(BOPBlocks.PALM_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_PALM_WOOD);
        this.dropSelf(BOPBlocks.PALM_PLANKS);
        this.add(BOPBlocks.PALM_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.PALM_STAIRS);
        this.dropSelf(BOPBlocks.PALM_FENCE);
        this.dropSelf(BOPBlocks.PALM_FENCE_GATE);
        this.add(BOPBlocks.PALM_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.PALM_TRAPDOOR);
        this.dropSelf(BOPBlocks.PALM_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.PALM_BUTTON);
        this.dropSelf(BOPBlocks.PALM_SIGN);
        this.dropSelf(BOPBlocks.PALM_HANGING_SIGN);

        this.dropSelf(BOPBlocks.WILLOW_SAPLING);
        this.add(BOPBlocks.WILLOW_VINE, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.addStrandPlantDropTable(BOPBlocks.SPANISH_MOSS, BOPBlocks.SPANISH_MOSS_PLANT);
        this.add(BOPBlocks.WILLOW_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.WILLOW_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.WILLOW_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_WILLOW_LOG);
        this.dropSelf(BOPBlocks.WILLOW_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_WILLOW_WOOD);
        this.dropSelf(BOPBlocks.WILLOW_PLANKS);
        this.add(BOPBlocks.WILLOW_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.WILLOW_STAIRS);
        this.dropSelf(BOPBlocks.WILLOW_FENCE);
        this.dropSelf(BOPBlocks.WILLOW_FENCE_GATE);
        this.add(BOPBlocks.WILLOW_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.WILLOW_TRAPDOOR);
        this.dropSelf(BOPBlocks.WILLOW_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.WILLOW_BUTTON);
        this.dropSelf(BOPBlocks.WILLOW_SIGN);
        this.dropSelf(BOPBlocks.WILLOW_HANGING_SIGN);

        this.dropSelf(BOPBlocks.DEAD_SAPLING);
        this.add(BOPBlocks.DEAD_BRANCH, (p_249226_) -> { return createShearsDispatchTable(p_249226_, this.applyExplosionDecay(p_249226_, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))); });
        this.add(BOPBlocks.DEAD_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.DEAD_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.DEAD_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_DEAD_LOG);
        this.dropSelf(BOPBlocks.DEAD_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_DEAD_WOOD);
        this.dropSelf(BOPBlocks.DEAD_PLANKS);
        this.add(BOPBlocks.DEAD_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.DEAD_STAIRS);
        this.dropSelf(BOPBlocks.DEAD_FENCE);
        this.dropSelf(BOPBlocks.DEAD_FENCE_GATE);
        this.add(BOPBlocks.DEAD_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.DEAD_TRAPDOOR);
        this.dropSelf(BOPBlocks.DEAD_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.DEAD_BUTTON);
        this.dropSelf(BOPBlocks.DEAD_SIGN);
        this.dropSelf(BOPBlocks.DEAD_HANGING_SIGN);

        this.dropSelf(BOPBlocks.MAGIC_SAPLING);
        this.add(BOPBlocks.MAGIC_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.MAGIC_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.MAGIC_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_MAGIC_LOG);
        this.dropSelf(BOPBlocks.MAGIC_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_MAGIC_WOOD);
        this.dropSelf(BOPBlocks.MAGIC_PLANKS);
        this.add(BOPBlocks.MAGIC_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.MAGIC_STAIRS);
        this.dropSelf(BOPBlocks.MAGIC_FENCE);
        this.dropSelf(BOPBlocks.MAGIC_FENCE_GATE);
        this.add(BOPBlocks.MAGIC_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.MAGIC_TRAPDOOR);
        this.dropSelf(BOPBlocks.MAGIC_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.MAGIC_BUTTON);
        this.dropSelf(BOPBlocks.MAGIC_SIGN);
        this.dropSelf(BOPBlocks.MAGIC_HANGING_SIGN);

        this.dropSelf(BOPBlocks.UMBRAN_SAPLING);
        this.add(BOPBlocks.UMBRAN_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.UMBRAN_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.UMBRAN_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_UMBRAN_LOG);
        this.dropSelf(BOPBlocks.UMBRAN_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_UMBRAN_WOOD);
        this.dropSelf(BOPBlocks.UMBRAN_PLANKS);
        this.add(BOPBlocks.UMBRAN_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.UMBRAN_STAIRS);
        this.dropSelf(BOPBlocks.UMBRAN_FENCE);
        this.dropSelf(BOPBlocks.UMBRAN_FENCE_GATE);
        this.add(BOPBlocks.UMBRAN_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.UMBRAN_TRAPDOOR);
        this.dropSelf(BOPBlocks.UMBRAN_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.UMBRAN_BUTTON);
        this.dropSelf(BOPBlocks.UMBRAN_SIGN);
        this.dropSelf(BOPBlocks.UMBRAN_HANGING_SIGN);

        this.dropSelf(BOPBlocks.HELLBARK_SAPLING);
        this.add(BOPBlocks.HELLBARK_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.HELLBARK_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.HELLBARK_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_HELLBARK_LOG);
        this.dropSelf(BOPBlocks.HELLBARK_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_HELLBARK_WOOD);
        this.dropSelf(BOPBlocks.HELLBARK_PLANKS);
        this.add(BOPBlocks.HELLBARK_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.HELLBARK_STAIRS);
        this.dropSelf(BOPBlocks.HELLBARK_FENCE);
        this.dropSelf(BOPBlocks.HELLBARK_FENCE_GATE);
        this.add(BOPBlocks.HELLBARK_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.HELLBARK_TRAPDOOR);
        this.dropSelf(BOPBlocks.HELLBARK_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.HELLBARK_BUTTON);
        this.dropSelf(BOPBlocks.HELLBARK_SIGN);
        this.dropSelf(BOPBlocks.HELLBARK_HANGING_SIGN);

        this.dropSelf(BOPBlocks.EMPYREAL_SAPLING);
        this.add(BOPBlocks.EMPYREAL_LEAVES, (p_280940_) -> { return this.createLeavesDrops(p_280940_, BOPBlocks.EMPYREAL_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES); });
        this.dropSelf(BOPBlocks.EMPYREAL_LOG);
        this.dropSelf(BOPBlocks.STRIPPED_EMPYREAL_LOG);
        this.dropSelf(BOPBlocks.EMPYREAL_WOOD);
        this.dropSelf(BOPBlocks.STRIPPED_EMPYREAL_WOOD);
        this.dropSelf(BOPBlocks.EMPYREAL_PLANKS);
        this.add(BOPBlocks.EMPYREAL_SLAB, (p_251629_) -> { return this.createSlabItemTable(p_251629_); });
        this.dropSelf(BOPBlocks.EMPYREAL_STAIRS);
        this.dropSelf(BOPBlocks.EMPYREAL_FENCE);
        this.dropSelf(BOPBlocks.EMPYREAL_FENCE_GATE);
        this.add(BOPBlocks.EMPYREAL_DOOR, (p_272365_) -> { return this.createDoorTable(p_272365_); });
        this.dropSelf(BOPBlocks.EMPYREAL_TRAPDOOR);
        this.dropSelf(BOPBlocks.EMPYREAL_PRESSURE_PLATE);
        this.dropSelf(BOPBlocks.EMPYREAL_BUTTON);
        this.dropSelf(BOPBlocks.EMPYREAL_SIGN);
        this.dropSelf(BOPBlocks.EMPYREAL_HANGING_SIGN);

        // Flowers
        this.dropSelf(BOPBlocks.ROSE);
        this.dropSelf(BOPBlocks.VIOLET);
        this.dropSelf(BOPBlocks.LAVENDER);
        this.add(BOPBlocks.TALL_LAVENDER, (p_250918_) -> { return this.createSinglePropConditionTable(p_250918_, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER); });
        this.dropSelf(BOPBlocks.WHITE_LAVENDER);
        this.add(BOPBlocks.TALL_WHITE_LAVENDER, (p_250918_) -> { return this.createSinglePropConditionTable(p_250918_, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER); });
        this.add(BOPBlocks.BLUE_HYDRANGEA, (p_250918_) -> { return this.createSinglePropConditionTable(p_250918_, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER); });
        this.add(BOPBlocks.GOLDENROD, (p_250918_) -> { return this.createSinglePropConditionTable(p_250918_, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER); });
        this.dropSelf(BOPBlocks.ORANGE_COSMOS);
        this.dropSelf(BOPBlocks.PINK_DAFFODIL);
        this.dropSelf(BOPBlocks.PINK_HIBISCUS);
        this.add(BOPBlocks.WILDFLOWER, this.createPetalsDrops(BOPBlocks.WILDFLOWER));
        this.add(BOPBlocks.WHITE_PETALS, this.createPetalsDrops(BOPBlocks.WHITE_PETALS));
        this.add(BOPBlocks.ICY_IRIS, (p_250918_) -> { return this.createSinglePropConditionTable(p_250918_, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER); });
        this.dropSelf(BOPBlocks.GLOWFLOWER);
        this.dropSelf(BOPBlocks.WILTED_LILY);
        this.dropSelf(BOPBlocks.BURNING_BLOSSOM);
        this.dropSelf(BOPBlocks.ENDBLOOM);

        // Foliage
        this.add(BOPBlocks.SPROUT, (p_249038_) -> { return createGrassDrops(p_249038_); });
        this.add(BOPBlocks.BUSH, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.addStrandPlantDropTable(BOPBlocks.HIGH_GRASS, BOPBlocks.HIGH_GRASS_PLANT);
        this.add(BOPBlocks.CLOVER, createCloverDrops(BOPBlocks.CLOVER));
        this.add(BOPBlocks.HUGE_CLOVER_PETAL, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.add(BOPBlocks.HUGE_LILY_PAD, (p_250918_) -> { return this.createSinglePropConditionTable(p_250918_, HugeLilyPadBlock.QUARTER, QuarterProperty.SOUTH_WEST); });
        this.dropSelf(BOPBlocks.WATERLILY);

        this.add(BOPBlocks.DUNE_GRASS, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.add(BOPBlocks.DESERT_GRASS, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.add(BOPBlocks.DEAD_GRASS, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.add(BOPBlocks.TUNDRA_SHRUB, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.add(BOPBlocks.ENDERPHYTE, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.addStrandPlantDropTable(BOPBlocks.LUMALOOP, BOPBlocks.LUMALOOP_PLANT);

        this.add(BOPBlocks.BARLEY, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.add(BOPBlocks.SEA_OATS, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.add(BOPBlocks.CATTAIL, (p_250918_) -> { return this.createSinglePropConditionTable(p_250918_, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER); });
        this.add(BOPBlocks.REED, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });
        this.add(BOPBlocks.WATERGRASS, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });

        this.dropSelf(BOPBlocks.TINY_CACTUS);
        this.dropSelf(BOPBlocks.BRAMBLE);
        this.add(BOPBlocks.BRAMBLE_LEAVES, (p_251652_) -> { return createShearsOnlyDrop(p_251652_); });

        // Potted Plants
        this.dropPottedContents(BOPBlocks.POTTED_ORIGIN_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_FLOWERING_OAK_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_CYPRESS_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_SNOWBLOSSOM_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_RAINBOW_BIRCH_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_FIR_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_PINE_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_RED_MAPLE_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_ORANGE_MAPLE_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_YELLOW_MAPLE_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_REDWOOD_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_MAHOGANY_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_JACARANDA_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_PALM_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_WILLOW_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_DEAD_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_MAGIC_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_UMBRAN_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_HELLBARK_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_EMPYREAL_SAPLING);
        this.dropPottedContents(BOPBlocks.POTTED_ROSE);
        this.dropPottedContents(BOPBlocks.POTTED_VIOLET);
        this.dropPottedContents(BOPBlocks.POTTED_LAVENDER);
        this.dropPottedContents(BOPBlocks.POTTED_WHITE_LAVENDER);
        this.dropPottedContents(BOPBlocks.POTTED_ORANGE_COSMOS);
        this.dropPottedContents(BOPBlocks.POTTED_PINK_DAFFODIL);
        this.dropPottedContents(BOPBlocks.POTTED_PINK_HIBISCUS);
        this.dropPottedContents(BOPBlocks.POTTED_GLOWFLOWER);
        this.dropPottedContents(BOPBlocks.POTTED_WILTED_LILY);
        this.dropPottedContents(BOPBlocks.POTTED_BURNING_BLOSSOM);
        this.dropPottedContents(BOPBlocks.POTTED_ENDBLOOM);
        this.dropPottedContents(BOPBlocks.POTTED_SPROUT);
        this.dropPottedContents(BOPBlocks.POTTED_TINY_CACTUS);
        this.dropPottedContents(BOPBlocks.POTTED_TOADSTOOL);
        this.dropPottedContents(BOPBlocks.POTTED_GLOWSHROOM);
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return BuiltInRegistries.BLOCK.entrySet().stream().filter(e -> e.getKey().location().getNamespace().equals(BiomesOPlenty.MOD_ID)).map(Map.Entry::getValue).toList();
    }

    protected LootTable.Builder createGrassDrops(Block p_252139_)
    {
        HolderLookup.RegistryLookup<Enchantment> lookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return createShearsDispatchTable(p_252139_, this.applyExplosionDecay(p_252139_, LootItem.lootTableItem(Items.WHEAT_SEEDS).when(LootItemRandomChanceCondition.randomChance(0.125F)).apply(ApplyBonusCount.addUniformBonusCount(lookup.getOrThrow(Enchantments.FORTUNE), 2))));
    }

    protected LootTable.Builder createCloverDrops(Block p_273240_)
    {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS).add(this.applyExplosionDecay(p_273240_, LootItem.lootTableItem(p_273240_).apply(IntStream.rangeClosed(1, 4).boxed().toList(), (p_272348_) -> {
            return SetItemCountFunction.setCount(ConstantValue.exactly((float)p_272348_.intValue())).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_273240_).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PinkPetalsBlock.AMOUNT, p_272348_)));
        }))));
    }

    protected void addStrandPlantDropTable(Block p_252269_, Block p_250696_) {
        LootTable.Builder loottable$builder = createShearsOnlyDrop(p_252269_);
        this.add(p_252269_, loottable$builder);
        this.add(p_250696_, loottable$builder);
    }

    protected static LootTable.Builder createShearsOnlyDrop(ItemLike p_250684_)
    {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS).add(LootItem.lootTableItem(p_250684_)));
    }
}
