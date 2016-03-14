/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBOPDoubleDecoration extends BlockBOPDecoration {
    
    // add half property
    public static enum Half implements IStringSerializable
    {
        LOWER, UPPER;
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
    };
    public static final PropertyEnum HALF = PropertyEnum.create("half", Half.class);
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { HALF });}
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }

    public float radius;    
    public float height;
    public boolean fromTop;
    
    public BlockBOPDoubleDecoration()
    {
        this(Material.plants);
    }
    public BlockBOPDoubleDecoration(Material material)
    {
        super(material);
        this.radius = 0.5F;
        this.height = 1.0F;
        this.fromTop = false;
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, Half.LOWER));
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(HALF, Half.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Half) state.getValue(HALF)).ordinal();
    }
    
    
    
    
    // utility functions
    public BlockPos getLowerPos(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) {return pos;}
        return world.getBlockState(pos).getValue(HALF) == Half.UPPER ? pos.down() : pos;       
    }
    public BlockPos getUpperPos(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) {return pos.up();}
        return world.getBlockState(pos).getValue(HALF) == Half.UPPER ? pos : pos.up();       
    }
    public IBlockState getLowerState(IBlockAccess world, BlockPos pos)
    {
        return world.getBlockState(this.getLowerPos(world, pos)); 
    }
    public IBlockState getUpperState(IBlockAccess world, BlockPos pos)
    {
        return world.getBlockState(this.getUpperPos(world, pos)); 
    }
    public boolean isValidDoubleBlock(IBlockAccess world, BlockPos pos)
    {
        IBlockState lowerState = this.getLowerState(world, pos);
        IBlockState upperState = this.getUpperState(world, pos);
        Block lowerBlock = lowerState.getBlock();
        Block upperBlock = upperState.getBlock();
        return (lowerBlock instanceof BlockBOPDoubleDecoration) && (upperBlock instanceof BlockBOPDoubleDecoration) && (lowerBlock == upperBlock) && lowerState.getValue(HALF) == Half.LOWER && upperState.getValue(HALF) == Half.UPPER;
    }
    
    

    // drop block as item if it cannot remain here - return whether on not it could stay
    @Override
    protected boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {        
        if (this.isValidDoubleBlock(worldIn, pos) && this.canBlockStay(worldIn, this.getLowerPos(worldIn, pos), state))
        {
            return true;
        }
        else
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.air.getDefaultState(), 3);
            return false;
        }
    }

    // This DoubleDecoration can replace the block at pos if both the block and the one above it are replaceable and the environment is suitable (canBlockStay)
    @Override
    public boolean canReplace(World world, BlockPos pos, EnumFacing side, ItemStack stack)
    {
       return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && world.getBlockState(pos.up()).getBlock().isReplaceable(world, pos.up()) && this.canBlockStay(world, pos, this.getStateFromMeta(stack.getMetadata()));        
    }
    
    // Called by ItemBlock before the block is placed - the placed block must always be Half.LOWER
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getStateFromMeta(meta).withProperty(HALF, Half.LOWER);
    }
    
    // Called by ItemBlock after the (lower) block has been placed
    // Use it to add the top half of the block
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos.up(), this.getStateFromMeta(stack.getMetadata()).withProperty(HALF, Half.UPPER), 3);
    }
    
    @Override
    // child classes should put code in here which checks the ground is ok
    public boolean canBlockStay(World world, BlockPos lowerPos, IBlockState state)
    {
        return true;
    }
    
    
    
    

/*    @Override
    // utility function for setting the block bounds - 
    public void setBlockBoundsByRadiusAndHeight(float radius, float height, boolean fromTop)
    {
        this.radius = radius;
        this.height = height;
        this.fromTop = fromTop;
    }
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState state = worldIn.getBlockState(pos);
        switch ((Half) state.getValue(HALF))
        {
            case LOWER:
                this.setBlockBoundsByRadiusAndHeightWithXZOffset(this.radius, this.fromTop ? this.height : 1.0F, this.fromTop, pos);
                break;
            case UPPER:
                this.setBlockBoundsByRadiusAndHeightWithXZOffset(this.radius, this.fromTop ? 1.0F : this.height, this.fromTop, pos);
                break;
        }
    }*/
    

    // handle drops from UPPER and LOWER separately
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {        
        // note it is important to use the state as provided and NOT world.getBlockState(pos)
        // because when this function is called, the block may have already been turned to air
        // the state provided is the state before the block was destroyed
        if (state.getValue(HALF) == Half.UPPER)
        {
            return this.getUpperDrops(world, pos, state, fortune);
        }
        else
        {
            return this.getLowerDrops(world, pos, state, fortune);
        }
    }
    
    // default behavior is that UPPER drops nothing, and LOWER drops the default item
    public List<ItemStack> getUpperDrops(IBlockAccess world, BlockPos upperPos, IBlockState upperState, int fortune)
    {
        return new java.util.ArrayList<ItemStack>();
    }
    public List<ItemStack> getLowerDrops(IBlockAccess world, BlockPos lowerPos, IBlockState lowerState, int fortune)
    {
        return super.getDrops(world, lowerPos, lowerState, fortune);
    }
    
    // if a child class chooses to implement IShearable make shearing the upper or lower block act as shearing both
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {      
        List<ItemStack> drops = new java.util.ArrayList<ItemStack>();
        if (!this.isValidDoubleBlock(world, pos)) {return drops;}
        drops.addAll( this.getUpperShearDrops(item, world, this.getUpperPos(world, pos), this.getUpperState(world, pos), fortune) );
        drops.addAll( this.getLowerShearDrops(item, world, this.getLowerPos(world, pos), this.getLowerState(world, pos), fortune) );
        return drops;        
    }
    
    // default behavior is that UPPER drops nothing, and LOWER drops the default item
    public List<ItemStack> getUpperShearDrops(ItemStack item, IBlockAccess world, BlockPos upperPos, IBlockState upperState, int fortune)
    {
        return new java.util.ArrayList<ItemStack>();
    }
    public List<ItemStack> getLowerShearDrops(ItemStack item, IBlockAccess world, BlockPos lowerPos, IBlockState lowerState, int fortune)
    {
        return super.getDrops(world, lowerPos, lowerState, fortune);
    }
    
    
    
    // discard the HALF info in the items dropped - make them all Half.LOWER so that they can stack - keep other state info such as VARIANT
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState( state.withProperty(HALF, Half.LOWER) );
    }
   
    
    
    
    

    
}