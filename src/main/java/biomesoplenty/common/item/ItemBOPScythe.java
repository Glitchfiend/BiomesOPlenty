/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import biomesoplenty.api.block.BOPPlantEnums.AllPlants;
import biomesoplenty.api.item.BOPItemHelper;
import biomesoplenty.common.block.BlockBOPFlower1;
import biomesoplenty.common.block.BlockBOPFlower2;
import biomesoplenty.common.block.BlockBOPPlant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBOPScythe extends Item
{
    
    protected Item.ToolMaterial toolMaterial;

    public ItemBOPScythe(Item.ToolMaterial material)
    {
        this.toolMaterial = material;
        this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
    }
    
    // Scythe is strong against leaves
    @Override
    public float getStrVsBlock(ItemStack stack, Block block)
    {
        return block.getMaterial() == Material.leaves ? 15.0F : super.getStrVsBlock(stack, block);
    }
    
    @Override
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }
    
    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = this.toolMaterial.getRepairItemStack();
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
    {

        if (blockIn == null || worldIn.getBlockState(pos).getBlock() == Blocks.air) {return false;}
                        
        boolean isLeaves = blockIn.isLeaves(worldIn, pos);        
        
        int radius = isLeaves ? 0 : 3;
        int height = isLeaves ? 0 : 4;
        if (toolMaterial == ToolMaterial.IRON || toolMaterial == ToolMaterial.GOLD)
        {
            radius = 4;
            height = 4;
        }
        else if (toolMaterial == ToolMaterial.EMERALD)
        {
            radius = 5;
            height = 5;
        }
        else if (toolMaterial == BOPItemHelper.amethyst_tool_material)
        {
            radius = 6;
            height = 6;
        }
        
        // automatically damage the item once - for the block originally destroyed
        stack.damageItem(1, playerIn);
        
        int numberTrimmed = 0;
        if (isLeaves)
        {
            numberTrimmed += trim(stack, playerIn, worldIn, pos, height, radius, TrimType.TRIM_LEAVES, false, 40);
        }
        else
        {
            // trim once with the corners cut
            numberTrimmed += trim(stack, playerIn, worldIn, pos, height, radius, TrimType.TRIM_GRASS_AND_FLOWERS, true, 70);
            if (worldIn.rand.nextInt(3) == 0)
            {
                // with one in 3 chance, also do another 'free' trim of a smaller radius, without the corners cut
                // ('free' in the sense that it does not damage the scythe)
                numberTrimmed += trim(stack, playerIn, worldIn, pos, height, radius - 1, TrimType.TRIM_GRASS_AND_FLOWERS, false, 0);
            }
        }
        return numberTrimmed > 0;
    }
    
    
    public int trim(ItemStack stack, EntityLivingBase entity, World world, BlockPos pos, int height, int radius, TrimType trimType, boolean cutCorners, int damagePercentChance)
    {
        int numberTrimmed = 0;
        int fortune = 0; // TODO fortune of scythe ?
        
        // apply to every block in a rectangle around pos
        for (int dx = -radius; dx <= radius; dx++) { for (int dy = -radius; dy <= radius; dy++) { for (int dz = -radius; dz <= radius; dz++) {
            if (cutCorners && (Math.abs(dx) + Math.abs(dz) >= 2 * radius))
            {
                continue;
            }
            if (trimType.trimAtPos(world, pos.add(dx,dy,dz), fortune))
            {
                numberTrimmed++;
                if (world.rand.nextInt(100) < damagePercentChance)
                {
                    stack.damageItem(1, entity);
                }
            }
        }}}
        return numberTrimmed;
    }
    
    
    public enum TrimType {
        
        TRIM_GRASS_AND_FLOWERS, TRIM_LEAVES;
        
        // return true if a block was trimmed, false otherwise
        public boolean trimAtPos(World world, BlockPos pos, int fortune)
        {
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            
            switch (this) {
            
                case TRIM_LEAVES:
                    
                    // remove leaves
                    if (block.isLeaves(world, pos))
                    {
                        block.dropBlockAsItem(world, pos, state, fortune);
                        world.setBlockToAir(pos);
                        return true;
                    }
                    return false;
                    
            
                case TRIM_GRASS_AND_FLOWERS: default:
            
                    // TODO: remove other plants?
                    // shorten grass and destroy flowers
                    if (block instanceof BlockBOPPlant)
                    {
                        switch ((AllPlants) state.getValue(((BlockBOPPlant)block).variantProperty))
                        {
                            case SHORTGRASS:
                                block.dropBlockAsItem(world, pos, state, fortune);
                                world.setBlockToAir(pos);
                                return true;
                            case MEDIUMGRASS:
                                block.dropBlockAsItem(world, pos, state, fortune);
                                world.setBlockState(pos, BlockBOPPlant.paging.getVariantState(AllPlants.SHORTGRASS));
                                return true;
                            default:
                                return false;
                        }
                    }
                    else if (block == Blocks.tallgrass)
                    {
                        block.dropBlockAsItem(world, pos, state, fortune);
                        world.setBlockState(pos, BlockBOPPlant.paging.getVariantState(AllPlants.MEDIUMGRASS));
                        return true;
                    }
                    else if ((block instanceof BlockFlower) || (block instanceof BlockBOPFlower1) || (block instanceof BlockBOPFlower2))
                    {
                        block.dropBlockAsItem(world, pos, state, fortune);
                        world.setBlockToAir(pos);
                        return true;
                    }
                    return false;
            
            }
        }
    }
 
  
    
    
}