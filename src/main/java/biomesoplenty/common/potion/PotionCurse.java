/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.potion;

import net.minecraft.entity.EntityLivingBase;

public class PotionCurse extends BOPPotion
{
    public PotionCurse()
    {
        super(true, 0x04A5EA, 0, 0);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return duration >= 3;
    }
}