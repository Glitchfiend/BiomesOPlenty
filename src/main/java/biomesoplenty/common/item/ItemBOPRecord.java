/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ItemBOPRecord extends ItemRecord
{    
    
    public ItemBOPRecord(String name, SoundEvent record)
    {
        super(name, record);
    }
    
    @Override
    public ResourceLocation getRecordResource(String name)
    {
        return new ResourceLocation("biomesoplenty:"+name);
    }

}