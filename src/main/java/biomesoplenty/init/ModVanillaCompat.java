/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ModVanillaCompat
{
    public static void setup()
    {
        //Dispenser Behavior
        DispenseItemBehavior dispenseBucketBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
                DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem)p_123562_.getItem();
                BlockPos blockpos = p_123561_.pos().relative(p_123561_.state().getValue(DispenserBlock.FACING));
                Level level = p_123561_.level();
                if (dispensiblecontaineritem.emptyContents((Player)null, level, blockpos, (BlockHitResult)null)) {
                    dispensiblecontaineritem.checkExtraContent((Player)null, level, p_123562_, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultDispenseItemBehavior.dispense(p_123561_, p_123562_);
                }
            }
        };

        DispenserBlock.registerBehavior(BOPItems.BLOOD_BUCKET.get(), dispenseBucketBehavior);

        //Flammability
        registerFlammable(BOPBlocks.ORIGIN_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.FLOWERING_OAK_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.SNOWBLOSSOM_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.RAINBOW_BIRCH_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.YELLOW_AUTUMN_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.ORANGE_AUTUMN_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.MAPLE_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.FIR_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.FIR_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.FIR_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_FIR_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_FIR_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.FIR_PLANKS.get(), 5, 20);
        registerFlammable(BOPBlocks.FIR_SLAB.get(), 5, 20);
        registerFlammable(BOPBlocks.FIR_STAIRS.get(), 5, 20);
        registerFlammable(BOPBlocks.FIR_FENCE.get(), 5, 20);
        registerFlammable(BOPBlocks.FIR_FENCE_GATE.get(), 5, 20);
        registerFlammable(BOPBlocks.PINE_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.PINE_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.PINE_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_PINE_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_PINE_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.PINE_PLANKS.get(), 5, 20);
        registerFlammable(BOPBlocks.PINE_SLAB.get(), 5, 20);
        registerFlammable(BOPBlocks.PINE_STAIRS.get(), 5, 20);
        registerFlammable(BOPBlocks.PINE_FENCE.get(), 5, 20);
        registerFlammable(BOPBlocks.PINE_FENCE_GATE.get(), 5, 20);
        registerFlammable(BOPBlocks.REDWOOD_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.REDWOOD_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.REDWOOD_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_REDWOOD_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_REDWOOD_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.REDWOOD_PLANKS.get(), 5, 20);
        registerFlammable(BOPBlocks.REDWOOD_SLAB.get(), 5, 20);
        registerFlammable(BOPBlocks.REDWOOD_STAIRS.get(), 5, 20);
        registerFlammable(BOPBlocks.REDWOOD_FENCE.get(), 5, 20);
        registerFlammable(BOPBlocks.REDWOOD_FENCE_GATE.get(), 5, 20);
        registerFlammable(BOPBlocks.MAHOGANY_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.MAHOGANY_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.MAHOGANY_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_MAHOGANY_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_MAHOGANY_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.MAHOGANY_PLANKS.get(), 5, 20);
        registerFlammable(BOPBlocks.MAHOGANY_SLAB.get(), 5, 20);
        registerFlammable(BOPBlocks.MAHOGANY_STAIRS.get(), 5, 20);
        registerFlammable(BOPBlocks.MAHOGANY_FENCE.get(), 5, 20);
        registerFlammable(BOPBlocks.MAHOGANY_FENCE_GATE.get(), 5, 20);
        registerFlammable(BOPBlocks.JACARANDA_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.JACARANDA_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.JACARANDA_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_JACARANDA_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_JACARANDA_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.JACARANDA_PLANKS.get(), 5, 20);
        registerFlammable(BOPBlocks.JACARANDA_SLAB.get(), 5, 20);
        registerFlammable(BOPBlocks.JACARANDA_STAIRS.get(), 5, 20);
        registerFlammable(BOPBlocks.JACARANDA_FENCE.get(), 5, 20);
        registerFlammable(BOPBlocks.JACARANDA_FENCE_GATE.get(), 5, 20);
        registerFlammable(BOPBlocks.PALM_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.PALM_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.PALM_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_PALM_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_PALM_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.PALM_PLANKS.get(), 5, 20);
        registerFlammable(BOPBlocks.PALM_SLAB.get(), 5, 20);
        registerFlammable(BOPBlocks.PALM_STAIRS.get(), 5, 20);
        registerFlammable(BOPBlocks.PALM_FENCE.get(), 5, 20);
        registerFlammable(BOPBlocks.PALM_FENCE_GATE.get(), 5, 20);
        registerFlammable(BOPBlocks.WILLOW_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.WILLOW_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.WILLOW_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_WILLOW_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_WILLOW_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.WILLOW_PLANKS.get(), 5, 20);
        registerFlammable(BOPBlocks.WILLOW_SLAB.get(), 5, 20);
        registerFlammable(BOPBlocks.WILLOW_STAIRS.get(), 5, 20);
        registerFlammable(BOPBlocks.WILLOW_FENCE.get(), 5, 20);
        registerFlammable(BOPBlocks.WILLOW_FENCE_GATE.get(), 5, 20);
        registerFlammable(BOPBlocks.DEAD_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.DEAD_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.DEAD_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_DEAD_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_DEAD_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.DEAD_PLANKS.get(), 5, 20);
        registerFlammable(BOPBlocks.DEAD_SLAB.get(), 5, 20);
        registerFlammable(BOPBlocks.DEAD_STAIRS.get(), 5, 20);
        registerFlammable(BOPBlocks.DEAD_FENCE.get(), 5, 20);
        registerFlammable(BOPBlocks.DEAD_FENCE_GATE.get(), 5, 20);
        registerFlammable(BOPBlocks.MAGIC_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.MAGIC_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.MAGIC_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_MAGIC_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_MAGIC_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.MAGIC_PLANKS.get(), 5, 20);
        registerFlammable(BOPBlocks.MAGIC_SLAB.get(), 5, 20);
        registerFlammable(BOPBlocks.MAGIC_STAIRS.get(), 5, 20);
        registerFlammable(BOPBlocks.MAGIC_FENCE.get(), 5, 20);
        registerFlammable(BOPBlocks.MAGIC_FENCE_GATE.get(), 5, 20);
        registerFlammable(BOPBlocks.UMBRAN_LEAVES.get(), 30, 60);
        registerFlammable(BOPBlocks.UMBRAN_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.UMBRAN_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_UMBRAN_LOG.get(), 5, 5);
        registerFlammable(BOPBlocks.STRIPPED_UMBRAN_WOOD.get(), 5, 5);
        registerFlammable(BOPBlocks.UMBRAN_PLANKS.get(), 5, 20);
        registerFlammable(BOPBlocks.UMBRAN_SLAB.get(), 5, 20);
        registerFlammable(BOPBlocks.UMBRAN_STAIRS.get(), 5, 20);
        registerFlammable(BOPBlocks.UMBRAN_FENCE.get(), 5, 20);
        registerFlammable(BOPBlocks.UMBRAN_FENCE_GATE.get(), 5, 20);
        registerFlammable(BOPBlocks.ROSE.get(), 60, 100);
        registerFlammable(BOPBlocks.VIOLET.get(), 60, 100);
        registerFlammable(BOPBlocks.LAVENDER.get(), 60, 100);
        registerFlammable(BOPBlocks.WILDFLOWER.get(), 60, 100);
        registerFlammable(BOPBlocks.PINK_DAFFODIL.get(), 60, 100);
        registerFlammable(BOPBlocks.PINK_HIBISCUS.get(), 60, 100);
        registerFlammable(BOPBlocks.WHITE_PETALS.get(), 60, 100);
        registerFlammable(BOPBlocks.GLOWFLOWER.get(), 60, 100);
        registerFlammable(BOPBlocks.WILTED_LILY.get(), 60, 100);
        registerFlammable(BOPBlocks.TALL_LAVENDER.get(), 60, 100);
        registerFlammable(BOPBlocks.BLUE_HYDRANGEA.get(), 60, 100);
        registerFlammable(BOPBlocks.GOLDENROD.get(), 60, 100);
        registerFlammable(BOPBlocks.ICY_IRIS.get(), 60, 100);
        registerFlammable(BOPBlocks.WILLOW_VINE.get(), 15, 100);
        registerFlammable(BOPBlocks.SPANISH_MOSS.get(), 60, 100);
        registerFlammable(BOPBlocks.SPANISH_MOSS_PLANT.get(), 60, 100);
        registerFlammable(BOPBlocks.GLOWWORM_SILK.get(), 60, 100);
        registerFlammable(BOPBlocks.GLOWWORM_SILK_STRAND.get(), 60, 100);
        registerFlammable(BOPBlocks.HANGING_COBWEB.get(), 60, 100);
        registerFlammable(BOPBlocks.HANGING_COBWEB_STRAND.get(), 60, 100);
        registerFlammable(BOPBlocks.STRINGY_COBWEB.get(), 60, 100);
        registerFlammable(BOPBlocks.WEBBING.get(), 60, 100);
        registerFlammable(BOPBlocks.SPROUT.get(), 60, 100);
        registerFlammable(BOPBlocks.BUSH.get(), 60, 100);
        registerFlammable(BOPBlocks.HIGH_GRASS.get(), 60, 100);
        registerFlammable(BOPBlocks.HIGH_GRASS_PLANT.get(), 60, 100);
        registerFlammable(BOPBlocks.CLOVER.get(), 60, 100);
        registerFlammable(BOPBlocks.HUGE_CLOVER_PETAL.get(), 60, 100);
        registerFlammable(BOPBlocks.DUNE_GRASS.get(), 60, 100);
        registerFlammable(BOPBlocks.DESERT_GRASS.get(), 60, 100);
        registerFlammable(BOPBlocks.DEAD_GRASS.get(), 60, 100);
        registerFlammable(BOPBlocks.TUNDRA_SHRUB.get(), 60, 100);
        registerFlammable(BOPBlocks.DEAD_BRANCH.get(), 60, 100);
        registerFlammable(BOPBlocks.BARLEY.get(), 60, 100);

        //Log Stripping
        registerStrippable(BOPBlocks.FIR_LOG.get(), BOPBlocks.STRIPPED_FIR_LOG.get());
        registerStrippable(BOPBlocks.FIR_WOOD.get(), BOPBlocks.STRIPPED_FIR_WOOD.get());
        registerStrippable(BOPBlocks.PINE_LOG.get(), BOPBlocks.STRIPPED_PINE_LOG.get());
        registerStrippable(BOPBlocks.PINE_WOOD.get(), BOPBlocks.STRIPPED_PINE_WOOD.get());
        registerStrippable(BOPBlocks.REDWOOD_LOG.get(), BOPBlocks.STRIPPED_REDWOOD_LOG.get());
        registerStrippable(BOPBlocks.REDWOOD_WOOD.get(), BOPBlocks.STRIPPED_REDWOOD_WOOD.get());
        registerStrippable(BOPBlocks.MAHOGANY_LOG.get(), BOPBlocks.STRIPPED_MAHOGANY_LOG.get());
        registerStrippable(BOPBlocks.MAHOGANY_WOOD.get(), BOPBlocks.STRIPPED_MAHOGANY_WOOD.get());
        registerStrippable(BOPBlocks.JACARANDA_LOG.get(), BOPBlocks.STRIPPED_JACARANDA_LOG.get());
        registerStrippable(BOPBlocks.JACARANDA_WOOD.get(), BOPBlocks.STRIPPED_JACARANDA_WOOD.get());
        registerStrippable(BOPBlocks.PALM_LOG.get(), BOPBlocks.STRIPPED_PALM_LOG.get());
        registerStrippable(BOPBlocks.PALM_WOOD.get(), BOPBlocks.STRIPPED_PALM_WOOD.get());
        registerStrippable(BOPBlocks.WILLOW_LOG.get(), BOPBlocks.STRIPPED_WILLOW_LOG.get());
        registerStrippable(BOPBlocks.WILLOW_WOOD.get(), BOPBlocks.STRIPPED_WILLOW_WOOD.get());
        registerStrippable(BOPBlocks.DEAD_LOG.get(), BOPBlocks.STRIPPED_DEAD_LOG.get());
        registerStrippable(BOPBlocks.DEAD_WOOD.get(), BOPBlocks.STRIPPED_DEAD_WOOD.get());
        registerStrippable(BOPBlocks.MAGIC_LOG.get(), BOPBlocks.STRIPPED_MAGIC_LOG.get());
        registerStrippable(BOPBlocks.MAGIC_WOOD.get(), BOPBlocks.STRIPPED_MAGIC_WOOD.get());
        registerStrippable(BOPBlocks.UMBRAN_LOG.get(), BOPBlocks.STRIPPED_UMBRAN_LOG.get());
        registerStrippable(BOPBlocks.UMBRAN_WOOD.get(), BOPBlocks.STRIPPED_UMBRAN_WOOD.get());
        registerStrippable(BOPBlocks.HELLBARK_LOG.get(), BOPBlocks.STRIPPED_HELLBARK_LOG.get());
        registerStrippable(BOPBlocks.HELLBARK_WOOD.get(), BOPBlocks.STRIPPED_HELLBARK_WOOD.get());

        // Flattening
        registerFlattenable(BOPBlocks.ORIGIN_GRASS_BLOCK.get(), Blocks.DIRT_PATH.defaultBlockState());

        //Compostable Blocks
        registerCompostable(0.85F, BOPBlocks.GLOWSHROOM_BLOCK.get());
        registerCompostable(0.85F, BOPBlocks.TOADSTOOL_BLOCK.get());

        registerCompostable(0.3F, BOPBlocks.ORIGIN_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.ORIGIN_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.FLOWERING_OAK_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.FLOWERING_OAK_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.SNOWBLOSSOM_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.SNOWBLOSSOM_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.RAINBOW_BIRCH_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.RAINBOW_BIRCH_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.YELLOW_AUTUMN_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.YELLOW_AUTUMN_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.ORANGE_AUTUMN_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.ORANGE_AUTUMN_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.MAPLE_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.MAPLE_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.FIR_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.FIR_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.PINE_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.PINE_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.REDWOOD_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.REDWOOD_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.MAHOGANY_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.MAHOGANY_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.JACARANDA_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.JACARANDA_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.PALM_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.PALM_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.WILLOW_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.WILLOW_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.DEAD_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.DEAD_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.MAGIC_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.MAGIC_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.UMBRAN_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.UMBRAN_LEAVES.get());
        registerCompostable(0.3F, BOPBlocks.HELLBARK_SAPLING.get());
        registerCompostable(0.3F, BOPBlocks.HELLBARK_LEAVES.get());
        registerCompostable(0.65F, BOPBlocks.GLOWING_MOSS_CARPET.get());

        registerCompostable(0.65F, BOPBlocks.ROSE.get());
        registerCompostable(0.65F, BOPBlocks.VIOLET.get());
        registerCompostable(0.65F, BOPBlocks.LAVENDER.get());
        registerCompostable(0.65F, BOPBlocks.WILDFLOWER.get());
        registerCompostable(0.65F, BOPBlocks.ORANGE_COSMOS.get());
        registerCompostable(0.65F, BOPBlocks.PINK_DAFFODIL.get());
        registerCompostable(0.65F, BOPBlocks.PINK_HIBISCUS.get());
        registerCompostable(0.65F, BOPBlocks.WATERLILY.get());
        registerCompostable(0.3F, BOPBlocks.WHITE_PETALS.get());
        registerCompostable(0.65F, BOPBlocks.GLOWFLOWER.get());
        registerCompostable(0.65F, BOPBlocks.WILTED_LILY.get());
        registerCompostable(0.65F, BOPBlocks.BURNING_BLOSSOM.get());

        registerCompostable(0.65F, BOPBlocks.TALL_LAVENDER.get());
        registerCompostable(0.65F, BOPBlocks.BLUE_HYDRANGEA.get());
        registerCompostable(0.65F, BOPBlocks.GOLDENROD.get());
        registerCompostable(0.65F, BOPBlocks.ICY_IRIS.get());

        registerCompostable(0.5F, BOPBlocks.WILLOW_VINE.get());
        registerCompostable(0.5F, BOPBlocks.SPANISH_MOSS.get());
        registerCompostable(0.5F, BOPBlocks.SPANISH_MOSS_PLANT.get());

        registerCompostable(0.5F, BOPBlocks.SPROUT.get());
        registerCompostable(0.5F, BOPBlocks.BUSH.get());
        registerCompostable(0.5F, BOPBlocks.HIGH_GRASS.get());
        registerCompostable(0.5F, BOPBlocks.HIGH_GRASS_PLANT.get());
        registerCompostable(0.5F, BOPBlocks.CLOVER.get());
        registerCompostable(0.5F, BOPBlocks.HUGE_CLOVER_PETAL.get());
        registerCompostable(0.5F, BOPBlocks.DUNE_GRASS.get());
        registerCompostable(0.5F, BOPBlocks.DESERT_GRASS.get());
        registerCompostable(0.5F, BOPBlocks.DEAD_GRASS.get());
        registerCompostable(0.5F, BOPBlocks.TUNDRA_SHRUB.get());

        registerCompostable(0.5F, BOPBlocks.CATTAIL.get());
        registerCompostable(0.5F, BOPBlocks.BARLEY.get());
        registerCompostable(0.5F, BOPBlocks.REED.get());
        registerCompostable(0.5F, BOPBlocks.WATERGRASS.get());

        registerCompostable(0.3F, BOPBlocks.DEAD_BRANCH.get());
        registerCompostable(0.3F, BOPBlocks.BRAMBLE.get());
        registerCompostable(0.3F, BOPBlocks.BRAMBLE_LEAVES.get());

        registerCompostable(0.65F, BOPBlocks.TOADSTOOL.get());
        registerCompostable(0.65F, BOPBlocks.GLOWSHROOM.get());
        registerCompostable(0.65F, BOPBlocks.GLOWING_MOSS_BLOCK.get());
        registerCompostable(0.65F, BOPBlocks.HUGE_LILY_PAD.get());
    }

    public static void registerStrippable(Block log, Block stripped_log) {
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(log, stripped_log);
    }

    public static void registerFlattenable(Block block, BlockState flattened_block) {
        ShovelItem.FLATTENABLES = Maps.newHashMap(ShovelItem.FLATTENABLES);
        ShovelItem.FLATTENABLES.put(block, flattened_block);
    }

    public static void registerCompostable(float chance, ItemLike itemIn) {
        ComposterBlock.COMPOSTABLES.put(itemIn.asItem(), chance);
    }

    public static void registerFlammable(Block blockIn, int encouragement, int flammability)
    {
        FireBlock fireblock = (FireBlock)Blocks.FIRE;
        fireblock.setFlammable(blockIn, encouragement, flammability);
    }
}
