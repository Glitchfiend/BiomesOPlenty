/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.api.block;

import biomesoplenty.api.BOPAPI;
import net.minecraft.world.level.block.state.properties.WoodType;

import static net.minecraft.world.level.block.state.properties.WoodType.register;

public class BOPWoodTypes
{
    public static final WoodType FIR = register(new WoodType(BOPAPI.MOD_ID + ":fir", BOPBlockSetTypes.FIR));
    public static final WoodType PINE = register(new WoodType(BOPAPI.MOD_ID + ":pine", BOPBlockSetTypes.PINE));
    public static final WoodType MAPLE = register(new WoodType(BOPAPI.MOD_ID + ":maple", BOPBlockSetTypes.MAPLE));
    public static final WoodType REDWOOD = register(new WoodType(BOPAPI.MOD_ID + ":redwood", BOPBlockSetTypes.REDWOOD));
    public static final WoodType MAHOGANY = register(new WoodType(BOPAPI.MOD_ID + ":mahogany", BOPBlockSetTypes.MAHOGANY));
    public static final WoodType JACARANDA = register(new WoodType(BOPAPI.MOD_ID + ":jacaranda", BOPBlockSetTypes.JACARANDA));
    public static final WoodType PALM = register(new WoodType(BOPAPI.MOD_ID + ":palm", BOPBlockSetTypes.PALM));
    public static final WoodType WILLOW = register(new WoodType(BOPAPI.MOD_ID + ":willow", BOPBlockSetTypes.WILLOW));
    public static final WoodType DEAD = register(new WoodType(BOPAPI.MOD_ID + ":dead", BOPBlockSetTypes.DEAD));
    public static final WoodType MAGIC = register(new WoodType(BOPAPI.MOD_ID + ":magic", BOPBlockSetTypes.MAGIC));
    public static final WoodType UMBRAN = register(new WoodType(BOPAPI.MOD_ID + ":umbran", BOPBlockSetTypes.UMBRAN));
    public static final WoodType HELLBARK = register(new WoodType(BOPAPI.MOD_ID + ":hellbark", BOPBlockSetTypes.HELLBARK));
    public static final WoodType EMPYREAL = register(new WoodType(BOPAPI.MOD_ID + ":empyreal", BOPBlockSetTypes.EMPYREAL));
}
