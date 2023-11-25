/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.api.block;

import biomesoplenty.api.BOPAPI;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import static net.minecraft.world.level.block.state.properties.BlockSetType.register;

public class BOPBlockSetTypes
{
    public static final BlockSetType FIR = register(new BlockSetType(BOPAPI.MOD_ID + ":fir"));
    public static final BlockSetType PINE = register(new BlockSetType(BOPAPI.MOD_ID + ":pine"));
    public static final BlockSetType MAPLE = register(new BlockSetType(BOPAPI.MOD_ID + ":maple"));
    public static final BlockSetType REDWOOD = register(new BlockSetType(BOPAPI.MOD_ID + ":redwood"));
    public static final BlockSetType MAHOGANY = register(new BlockSetType(BOPAPI.MOD_ID + ":mahogany"));
    public static final BlockSetType JACARANDA = register(new BlockSetType(BOPAPI.MOD_ID + ":jacaranda"));
    public static final BlockSetType PALM = register(new BlockSetType(BOPAPI.MOD_ID + ":palm"));
    public static final BlockSetType WILLOW = register(new BlockSetType(BOPAPI.MOD_ID + ":willow"));
    public static final BlockSetType DEAD = register(new BlockSetType(BOPAPI.MOD_ID + ":dead"));
    public static final BlockSetType MAGIC = register(new BlockSetType(BOPAPI.MOD_ID + ":magic"));
    public static final BlockSetType UMBRAN = register(new BlockSetType(BOPAPI.MOD_ID + ":umbran"));
    public static final BlockSetType HELLBARK = register(new BlockSetType(BOPAPI.MOD_ID + ":hellbark"));
}
