/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import static biomesoplenty.common.block.BlockBOPGem.VARIANT;

import java.util.Random;

import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.item.ItemBOPBlock;
import biomesoplenty.common.util.block.BlockStateUtils;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPGemOre extends Block implements IBOPBlock
{
    
    // add properties (note VARIANT is imported statically from the BlockGem class)
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { VARIANT });}

    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((BOPGems) state.getValue(VARIANT)).getName() + "_ore";
    }
    @Override
    @SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor() { return null; }
    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPGemOre()
    {
        super(Material.ROCK);
        
        // set some defaults
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.STONE);
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, BOPGems.AMETHYST) );

        // all variants need pickaxe:2 to harvest, except amethyst which needs pickaxe:3
        for (IBlockState state : BlockStateUtils.getBlockPresets(this))
        {
            this.setHarvestLevel("pickaxe", 2, state);
        }
        this.setHarvestLevel("pickaxe", 3, this.blockState.getBaseState().withProperty(VARIANT, BOPGems.AMETHYST));
    
    }

    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BOPGems.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BOPGems) state.getValue(VARIANT)).ordinal();
    }

    
    // The 3 functions below, getItemDropped, quantityDroppedWithBonus and damageDropped work together to determine the drops
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return BOPItems.gem;
    }
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        // returns a number between 1 and (fortune+1) - functionally identical to procedure for vanilla diamond drops, but simplified
        return Math.max(0, random.nextInt(fortune + 2) - 1) + 1;
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }

    // Drop some experience when gems are mined
    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        return MathHelper.getInt(rand, 3, 7);
    }
    
}
