/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.api.block;

import biomesoplenty.api.BOPAPI;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;

import static net.minecraft.world.level.block.state.properties.WoodType.register;

public class BOPWoodTypes
{
    public static final WoodType ORIGIN_OAK = register(new WoodType(BOPAPI.MOD_ID + "_origin_oak", BOPBlockSetTypes.ORIGIN_OAK));
    public static final WoodType FIR = register(new WoodType(BOPAPI.MOD_ID + "_fir", BOPBlockSetTypes.FIR));
    public static final WoodType PINE = register(new WoodType(BOPAPI.MOD_ID + "_pine", BOPBlockSetTypes.PINE));
    public static final WoodType MAPLE = register(new WoodType(BOPAPI.MOD_ID + "_maple", BOPBlockSetTypes.MAPLE, SoundType.CHERRY_WOOD, SoundType.CHERRY_WOOD_HANGING_SIGN, SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN));
    public static final WoodType REDWOOD = register(new WoodType(BOPAPI.MOD_ID + "_redwood", BOPBlockSetTypes.REDWOOD));
    public static final WoodType MAHOGANY = register(new WoodType(BOPAPI.MOD_ID + "_mahogany", BOPBlockSetTypes.MAHOGANY));
    public static final WoodType JACARANDA = register(new WoodType(BOPAPI.MOD_ID + "_jacaranda", BOPBlockSetTypes.JACARANDA, SoundType.CHERRY_WOOD, SoundType.CHERRY_WOOD_HANGING_SIGN, SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN));
    public static final WoodType PALM = register(new WoodType(BOPAPI.MOD_ID + "_palm", BOPBlockSetTypes.PALM));
    public static final WoodType WILLOW = register(new WoodType(BOPAPI.MOD_ID + "_willow", BOPBlockSetTypes.WILLOW));
    public static final WoodType DEAD = register(new WoodType(BOPAPI.MOD_ID + "_dead", BOPBlockSetTypes.DEAD));
    public static final WoodType MAGIC = register(new WoodType(BOPAPI.MOD_ID + "_magic", BOPBlockSetTypes.MAGIC, SoundType.CHERRY_WOOD, SoundType.CHERRY_WOOD_HANGING_SIGN, SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN));
    public static final WoodType UMBRAN = register(new WoodType(BOPAPI.MOD_ID + "_umbran", BOPBlockSetTypes.UMBRAN, SoundType.NETHER_WOOD, SoundType.NETHER_WOOD_HANGING_SIGN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN));
    public static final WoodType HELLBARK = register(new WoodType(BOPAPI.MOD_ID + "_hellbark", BOPBlockSetTypes.HELLBARK, SoundType.NETHER_WOOD, SoundType.NETHER_WOOD_HANGING_SIGN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN));
    public static final WoodType EMPYREAL = register(new WoodType(BOPAPI.MOD_ID + "_empyreal", BOPBlockSetTypes.EMPYREAL, SoundType.NETHER_WOOD, SoundType.NETHER_WOOD_HANGING_SIGN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN));
}
