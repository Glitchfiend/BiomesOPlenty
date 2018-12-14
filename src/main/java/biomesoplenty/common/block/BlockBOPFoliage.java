/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.List;
import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.item.ItemBOPBlock;
import biomesoplenty.common.util.block.VariantPagingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.item.IHornHarvestable;

// TODO: pick block?

@Optional.Interface(iface = "vazkii.botania.api.item.IHornHarvestable", modid = "botania")
public class BlockBOPFoliage extends BlockBOPDecoration implements IShearable, IHornHarvestable
{
    
    // setup paged variant property
    
    // All 4 meta bits available for VARIANT which means we can have sixteen per instance
    public static VariantPagingHelper<BlockBOPFoliage, BOPFoliage> paging = new VariantPagingHelper<BlockBOPFoliage, BOPFoliage>(16, BOPFoliage.class);
    
    // Slightly naughty hackery here
    // The constructor of Block() calls createBlockState() which needs to know the particular instance's variant property
    // There is no way to set the individual block instance's variant property before this, because the super() has to be first
    // So, we use the static variable currentVariantProperty to provide each instance access to its variant property during creation
    private static IProperty currentVariantProperty;
    
    // Create an instance for each page
    public static void createAllPages()
    {        
        int numPages = paging.getNumPages();        
        for (int i = 0; i < numPages; ++i)
        {
            currentVariantProperty = paging.getVariantProperty(i);
            paging.addBlock(i, new BlockBOPFoliage());
        }
        
    }
    
    // Each instance has a reference to its own variant property
    public IProperty variantProperty;
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        this.variantProperty = currentVariantProperty; // get from static variable
        return new BlockStateContainer(this, this.variantProperty);
    }

    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] { this.variantProperty }; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        BOPFoliage plant = (BOPFoliage) state.getValue(this.variantProperty);
        switch (plant)
        {
            default:
                return plant.getName();
        }
    }

    public enum ColoringType {PLAIN, LIKE_LEAVES, LIKE_GRASS}

    public static ColoringType getColoringType(BOPFoliage plant)
    {
        switch (plant)
        {
            case BUSH: case BERRYBUSH:
            return ColoringType.LIKE_LEAVES;
            case SHORTGRASS: case KORU: case DEVILWEED:
            return ColoringType.LIKE_GRASS;
            default:
                return ColoringType.PLAIN;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor()
    {
        return new IBlockColor()
        {
            @Override
            public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex)
            {
                boolean inWorld = world != null && pos != null;

                switch (getColoringType((BOPFoliage) state.getValue(BlockBOPFoliage.this.variantProperty)))
                {
                    case LIKE_LEAVES:
                        return inWorld ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic();

                    case LIKE_GRASS:
                        return inWorld ? BiomeColorHelper.getGrassColorAtPos(world, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
                }

                return 0xFFFFFF;
            }
        };
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getItemColor()
    {
        return (stack, tintIndex) -> {
            IBlockState state = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());

            switch ((BOPFoliage) state.getValue(BlockBOPFoliage.this.variantProperty))
            {
                case BUSH: case BERRYBUSH:
                    return 0xFFFFFF;

                default:
                    return BlockBOPFoliage.this.getBlockColor().colorMultiplier(state, null, null, tintIndex);
            }
        };
    }
    
    private BlockBOPFoliage()
    {
        super();
        this.setDefaultState( this.blockState.getBaseState() );        
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(this.variantProperty, paging.getVariant(this, meta));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        BOPFoliage plant = (BOPFoliage) state.getValue(this.variantProperty);
        return paging.getIndex(plant);
    }
    
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
    	switch ((BOPFoliage) state.getValue(this.variantProperty))
    	{
    		case DEADGRASS:
    			return MapColor.BROWN;
    	
    		case DESERTGRASS:
    			return MapColor.ORANGE_STAINED_HARDENED_CLAY;
    			
    		case BARLEY:
    			return MapColor.YELLOW;
    	
    		default:
    			return this.blockMapColor;
    	}
    }
    
    // get the items dropped when you bash the bush
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        
        // start with an empty stack
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        
        // add items based on the VARIANT
        BOPFoliage plant = (BOPFoliage) state.getValue(this.variantProperty);
        switch (plant)
        {
            case SHORTGRASS:
                if (rand.nextInt(8) == 0)
                {
                    // 1 in 8 chance of getting a seed from this grass
                    ret.add(ForgeHooks.getGrassSeed(rand, fortune));
                }
                break;
                
            case BERRYBUSH:
                // BERRYBUSH always drops berries
                ret.add(new ItemStack(BOPItems.berries));
                break;

            case BARLEY:
                if (rand.nextInt(3) == 0)
                {
                    // 1 in 8 chance of getting a seed from this grass
                    ret.add(new ItemStack(Items.WHEAT_SEEDS));
                }
                break;
                
            default:
                // the rest drop nothing
                break;
        }
        return ret;
        
    }
    
    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
    	return true;
    }
    
    // different variants have different sizes
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {   
    	BOPFoliage plant = (BOPFoliage) state.getValue(this.variantProperty);
        switch (plant)
        {
            case SHORTGRASS: case DESERTGRASS: case DESERTSPROUTS:
            	return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.4000000357627869D, 0.8999999761581421D);
            default:
            	return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
        }        
    }

    
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        BOPFoliage plant = ((BOPFoliage) state.getValue(this.variantProperty));
      
        switch (plant)
        {
            case DEADGRASS: case DESERTGRASS:
                return BlockQueries.litDry.matches(world, pos.down()) || BlockQueries.sustainsNether.matches(world, pos.down());
            case DESERTSPROUTS:
                return BlockQueries.litDry.matches(world, pos.down()) || BlockQueries.litFertile.matches(world, pos.down());
            case DUNEGRASS:
                return BlockQueries.litSand.matches(world, pos.down());
            case SPECTRALFERN:
                return BlockQueries.spectralMoss.matches(world, pos.down());
            case DEVILWEED:
                return BlockQueries.fertile.matches(world, pos.down());
            default:
                return BlockQueries.litFertile.matches(world, pos.down());            
        }
    }
    
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);
        switch ((BOPFoliage) state.getValue(this.variantProperty))
        {
            case BUSH:
                // every now and then berries grow on a bush
                if (rand.nextInt(80) == 0 && worldIn.getLightFromNeighbors(pos.up()) >= 9)
                {
                    worldIn.setBlockState(pos, paging.getVariantState(BOPFoliage.BERRYBUSH));
                }
                break;
                
            default:
                break;
        }
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        switch ((BOPFoliage) state.getValue(this.variantProperty))
        {
            case BERRYBUSH:
                // an activated berry bush turns into a regular bush and drops a berry
                worldIn.setBlockState(pos, paging.getVariantState(BOPFoliage.BUSH));
                EntityItem berries = new EntityItem(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), new ItemStack(BOPItems.berries));
                if (!worldIn.isRemote)
                {
                    worldIn.spawnEntity(berries);
                    if (!(playerIn instanceof FakePlayer))
                    {
                        berries.onCollideWithPlayer(playerIn);
                    }
                }
                return true;
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
    }
    

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
    {
    	return true;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        
        // start with an empty stack
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        
        // add items based on the VARIANT
        BOPFoliage plant = ((BOPFoliage) world.getBlockState(pos).getValue(this.variantProperty));
        switch (plant)
        {
            case BERRYBUSH:
                // BERRYBUSH gives a regular bush when sheared (note this is in addition to the berry from getDrops)
                ret.add(paging.getVariantItem(BOPFoliage.BUSH));
                // ret.add(new ItemStack(BOPItems.berries, 1));
                break;
                
            default:
                // for everything else, get the block as an item
                ret.add(paging.getVariantItem(plant));
                break;
        }
        return ret;
    }
    
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        BOPFoliage plant = ((BOPFoliage) world.getBlockState(pos).getValue(this.variantProperty));
        switch (plant)
        {
            case DEVILWEED:
                return 0;
            default:
                return Blocks.TALLGRASS.getFlammability(world, pos, face);
        }
    }
    
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
    	BOPFoliage plant = ((BOPFoliage) world.getBlockState(pos).getValue(this.variantProperty));
        switch (plant)
        {
            case DEVILWEED:
                return 0;
            default:
                return Blocks.TALLGRASS.getFireSpreadSpeed(world, pos, face);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XYZ;
    }

    @Override
    @Optional.Method(modid = "botania")
    public boolean canHornHarvest(World world, BlockPos pos, ItemStack stack, EnumHornType hornType)
    {
        if (hornType != EnumHornType.WILD) return false;
        BOPFoliage plant = ((BOPFoliage) world.getBlockState(pos).getValue(this.variantProperty));
        switch (plant)
        {
            case BUSH:
            case BERRYBUSH:
                return false;
            default:
                return true;
        }
    }

    @Override
    @Optional.Method(modid = "botania")
    public boolean hasSpecialHornHarvest(World world, BlockPos pos, ItemStack stack, EnumHornType hornType)
    {
        return false;
    }

    @Override
    @Optional.Method(modid = "botania")
    public void harvestByHorn(World world, BlockPos pos, ItemStack stack, EnumHornType hornType)
    {
    }
    
}
