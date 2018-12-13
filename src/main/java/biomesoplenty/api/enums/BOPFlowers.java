/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.enums;

import net.minecraft.util.IStringSerializable;

public enum BOPFlowers implements IStringSerializable, IPagedVariants
{
	ROSE, BLUE_HYDRANGEA, VIOLET, LAVENDER, GOLDENROD, WILDFLOWER, ORANGE_COSMOS, PINK_DAFFODIL, PINK_HIBISCUS, GLOWFLOWER, DEATHBLOOM, WILTED_LILY, BURNING_BLOSSOM;
	
    @Override
    public String getName() {
        return this.name().toLowerCase();
    }
    @Override
    public String toString()
    {
        return this.getName();
    }
    
}