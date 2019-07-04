/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum BOPGems implements IStringSerializable
{
    AMETHYST, RUBY, PERIDOT, TOPAZ, TANZANITE, MALACHITE, SAPPHIRE, AMBER;
    @Override
    public String getName()
    {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
    @Override
    public String toString()
    {
        return this.getName();
    }
}