/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.api.block;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.level.block.state.properties.WoodType;

import static net.minecraft.world.level.block.state.properties.WoodType.register;

public class BOPWoodTypes
{
    public static final WoodType FIR = register(WoodType.create(BiomesOPlenty.MOD_ID + ":fir"));
    public static final WoodType REDWOOD = register(WoodType.create(BiomesOPlenty.MOD_ID + ":redwood"));
    public static final WoodType CHERRY = register(WoodType.create(BiomesOPlenty.MOD_ID + ":cherry"));
    public static final WoodType MAHOGANY = register(WoodType.create(BiomesOPlenty.MOD_ID + ":mahogany"));
    public static final WoodType JACARANDA = register(WoodType.create(BiomesOPlenty.MOD_ID + ":jacaranda"));
    public static final WoodType PALM = register(WoodType.create(BiomesOPlenty.MOD_ID + ":palm"));
    public static final WoodType WILLOW = register(WoodType.create(BiomesOPlenty.MOD_ID + ":willow"));
    public static final WoodType DEAD = register(WoodType.create(BiomesOPlenty.MOD_ID + ":dead"));
    public static final WoodType MAGIC = register(WoodType.create(BiomesOPlenty.MOD_ID + ":magic"));
    public static final WoodType UMBRAN = register(WoodType.create(BiomesOPlenty.MOD_ID + ":umbran"));
    public static final WoodType HELLBARK = register(WoodType.create(BiomesOPlenty.MOD_ID + ":hellbark"));
}
