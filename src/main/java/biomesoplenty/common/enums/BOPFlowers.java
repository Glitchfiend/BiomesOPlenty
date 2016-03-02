/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.enums;

import biomesoplenty.common.util.block.VariantPagingHelper.IPagedVariants;
import net.minecraft.util.IStringSerializable;

public enum BOPFlowers implements IStringSerializable, IPagedVariants
{
    CLOVER, SWAMPFLOWER, DEATHBLOOM, GLOWFLOWER, BLUE_HYDRANGEA, ORANGE_COSMOS, PINK_DAFFODIL, WILDFLOWER, VIOLET, WHITE_ANEMONE, ENDERLOTUS, BROMELIAD, WILTED_LILY, PINK_HIBISCUS, LILY_OF_THE_VALLEY, BURNING_BLOSSOM, LAVENDER, GOLDENROD, BLUEBELLS, MINERS_DELIGHT, ICY_IRIS, ROSE;
    
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