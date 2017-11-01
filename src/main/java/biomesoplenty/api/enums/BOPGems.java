/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.enums;

import net.minecraft.util.IStringSerializable;

public enum BOPGems implements IStringSerializable
{
    AMETHYST, RUBY, PERIDOT, TOPAZ, TANZANITE, MALACHITE, SAPPHIRE, AMBER;
    @Override
    public String getName()
    {
        return this.name().toLowerCase();
    }
    @Override
    public String toString()
    {
        return this.getName();
    }
};