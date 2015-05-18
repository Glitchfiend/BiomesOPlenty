/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.List;

import biomesoplenty.common.entities.EntityPixie;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemJarFilled extends Item
{
    
    public enum JarContents implements IStringSerializable
    {
        HONEY, POISON, PIXIE;
        
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
    }

    public ItemJarFilled()
    {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
    }
    
    // add all the contents types as separate items in the creative tab
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        for (JarContents contents : JarContents.values())
        {
            subItems.add(new ItemStack(itemIn, 1, contents.ordinal()));
        }
    }

    // default behavior in Item is to return 0, but the meta value is important here because it determines the jar contents
    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }
    
    public JarContents getContentsType(ItemStack stack)
    {
        int meta = stack.getMetadata();
        try {
            return JarContents.values()[meta];
        } catch (Exception e) {
            // if metadata is out of bounds return honey as a default (should never happen)
            return JarContents.HONEY;
        }
    }
    
    // get the correct name for this item by looking up the meta value in the JarContents enum
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "_" + this.getContentsType(stack).getName();
    }
    
    // TODO: should you be able to release a pixie into the air (as opposed to clicking to release it against the side of a block)
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote) {return true;}
                
        switch (this.getContentsType(stack))
        {
            case PIXIE:
                if (world.provider.isSurfaceWorld())
                {
                    BlockPos pos1 = pos.offset(side);
                    EntityPixie pixie = new EntityPixie(world);
                    pixie.setLocationAndAngles((double)pos1.getX() + 0.5F, (double)pos1.getY() + 0.3F, (double)pos1.getZ() + 0.5F, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
                    world.spawnEntityInWorld(pixie);
                    pixie.playLivingSound();
                    if (stack.hasDisplayName()) {pixie.setCustomNameTag(stack.getDisplayName());}
                    if (!player.capabilities.isCreativeMode) {--stack.stackSize;}
                } else {
                    player.addChatComponentMessage(new ChatComponentText("\u00a75Pixies cannot survive in this environment!"));
                }
                break;
                
            // TODO: are you supposed to be able to pour out honey?
            case HONEY: case POISON: default:
                break;
        }
        return true;
    }
    
    
    
    
    
}
   