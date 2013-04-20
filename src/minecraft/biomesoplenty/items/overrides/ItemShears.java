package biomesoplenty.items.overrides;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.LanguageRegistry;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.oredict.OreDictionary;

public class ItemShears extends net.minecraft.item.ItemShears
{
    public ItemShears(int var1)
    {
        super(var1);
        this.maxStackSize = 1;
    }
   
    
    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
    	Float Strength = null;

    	if (Blocks.shearBlockIds.get(par2Block.blockID) != null)
    	{
    		Strength = Float.parseFloat(Blocks.shearBlockIds.get(par2Block.blockID).toString()); 
    	}
    	else if (par2Block.blockID == Block.web.blockID | par2Block.blockID == Block.leaves.blockID)
    	{
    		Strength = 15.0F;
    	}
    	else if (par2Block.blockID == Block.cloth.blockID)
    	{
    		Strength = 5.0F;
    	}
    	else	
    	{
    		Strength = super.getStrVsBlock(par1ItemStack, par2Block);
    	}

    	return Strength;
    }
}
