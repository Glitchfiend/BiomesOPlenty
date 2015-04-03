/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

// TODO: sort out proper base color when using fast graphics
// TODO: flowers look tinted when using fast graphics
public class BlockBOPLeaves extends BlockLeaves implements IBOPBlock
{
    
    // add properties - note CHECK_DECAY and DECAYABLE are both inherited from BlockLeaves
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] {CHECK_DECAY, DECAYABLE});}
    
    // implement IDHBlock
    private Map<String, IBlockState> namedStates = new HashMap<String, IBlockState>();
    public Map<String, IBlockState> getNamedStates() {return this.namedStates;}
    public IBlockState getNamedState(String name) {return this.namedStates.get(name);}
    public Class<? extends ItemBlock> getItemClass() {return ItemBOPBlock.class;}
 
    private ItemStack sapling;
    private ItemStack fruit;
    private int saplingDropChance;
    private boolean canBurn;
    
    public BlockBOPLeaves(ItemStack sapling, ItemStack fruit, int saplingDropChance, boolean canBurn)
    {
        super();
        
        this.sapling = sapling;
        this.fruit = fruit;
        this.saplingDropChance = saplingDropChance;
        this.canBurn = canBurn;
        
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
    
    @Override
    protected int getSaplingDropChance(IBlockState state)
    {
        return this.saplingDropChance;
    }
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this.sapling.getItem();
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.sapling.getMetadata();
    }
    @Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(this.saplingDropChance) == 0 ? this.sapling.stackSize : 0;
    }
    
    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
        if (this.fruit != null) {
            spawnAsEntity(worldIn, pos, this.fruit.copy());
        }
    }
    

    @Override
    public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {       
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        ret.add(new ItemStack(this));
        return ret;
    }
    
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return this.canBurn ? Blocks.leaves.getFlammability(world, pos, face) : 0;
    }
    
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return this.canBurn ? Blocks.leaves.getFireSpreadSpeed(world, pos, face) : 0;
    }
    
    
    // We are forced to implement the method below in order to extend the BlockLeaves abstract class
    // ...however, we don't actually use it anywhere so it's safe to just return null
    @Override
    public EnumType getWoodType(int meta) {return null;}
    
    
}
    
  