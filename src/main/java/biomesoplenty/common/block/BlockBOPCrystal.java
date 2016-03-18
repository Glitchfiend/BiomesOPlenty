/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBOPCrystal extends Block implements IBOPBlock
{
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {return "";}
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPCrystal() {
        super(Material.glass);
        this.setHardness(0.15F);
        this.setResistance(5.0F);
        this.setLightLevel(1.0F);
        this.setSoundType(SoundType.GLASS);
    }
    
    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity)
    {
        //Prevent the ender dragon from destroying this block
        if (entity instanceof EntityDragon)
        {
            return false;
        }

        return true;
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return BOPItems.crystal_shard;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 4;
    }
    
}