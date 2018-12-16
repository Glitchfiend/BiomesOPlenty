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

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.common.item.ItemBOPPlant;
import biomesoplenty.common.util.block.VariantPagingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.item.IHornHarvestable;

@Optional.Interface(iface = "vazkii.botania.api.item.IHornHarvestable", modid = "botania")
public class BlockBOPPlant extends BlockBOPDecoration implements IShearable, IHornHarvestable
{
    
    // setup paged variant property
    
    // All 4 meta bits available for VARIANT which means we can have sixteen per instance
    public static VariantPagingHelper<BlockBOPPlant, BOPPlants> paging = new VariantPagingHelper<BlockBOPPlant, BOPPlants>(16, BOPPlants.class);
    
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
            paging.addBlock(i, new BlockBOPPlant());
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
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPPlant.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] { this.variantProperty }; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        BOPPlants plant = (BOPPlants) state.getValue(this.variantProperty);
        switch (plant)
        {
            default:
                return plant.getName();
        }
    }

    public enum ColoringType {PLAIN, LIKE_LEAVES, LIKE_GRASS}

    public static ColoringType getColoringType(BOPPlants plant)
    {
        switch (plant)
        {
            case WATERGRASS:
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

                switch (getColoringType((BOPPlants) state.getValue(BlockBOPPlant.this.variantProperty)))
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

            switch ((BOPPlants) state.getValue(BlockBOPPlant.this.variantProperty))
            {
                case WATERGRASS:
                    return 0xFFFFFF;

                default:
                    return BlockBOPPlant.this.getBlockColor().colorMultiplier(state, null, null, tintIndex);
            }
        };
    }
    
    private BlockBOPPlant()
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
        BOPPlants plant = (BOPPlants) state.getValue(this.variantProperty);
        return paging.getIndex(plant);
    }
    
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
    	switch ((BOPPlants) state.getValue(this.variantProperty))
    	{
    		case THORN:
    			return MapColor.SILVER_STAINED_HARDENED_CLAY;
    	
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
        BOPPlants plant = (BOPPlants) state.getValue(this.variantProperty);
        switch (plant)
        {
            case CATTAIL: case TINYCACTUS: case ROOT:
                // these variants drop themselves as items
                ret.add(paging.getVariantItem(plant));
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
        IBlockState state = world.getBlockState(pos);
        BOPPlants plant = (BOPPlants) state.getValue(this.variantProperty);
        
        switch (plant)
        {
            case THORN: case CATTAIL: case TINYCACTUS:
                return false;
            
            default:
                return true;
        }
    }
    
    // different variants have different sizes
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {   
    	BOPPlants plant = (BOPPlants) state.getValue(this.variantProperty);
        switch (plant)
        {
            case TINYCACTUS:
            	return new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);
            case ROOT:
            	return new AxisAlignedBB(0.09999999403953552D, 0.199999988079071D, 0.09999999403953552D, 0.8999999761581421D, 1.0D, 0.8999999761581421D);
            default:
            	return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
        }        
    }

    
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        BOPPlants plant = ((BOPPlants) state.getValue(this.variantProperty));
      
        switch (plant)
        {
            case TINYCACTUS:
                return BlockQueries.litDry.matches(world, pos.down()) || BlockQueries.litFertile.matches(world, pos.down());
            case THORN:
                return BlockQueries.fertileOrNetherrack.matches(world, pos.down()) || BlockQueries.sustainsNether.matches(world, pos.down());
            case CATTAIL:
                return BlockQueries.litFertileWaterside.matches(world, pos.down());
            case REED: case WATERGRASS:
                return BlockQueries.suitableForReed.matches(world, pos.down());
            case ROOT:
                // roots hang down - check against block above
                return BlockQueries.fertile.matches(world, pos.up());
            default:
                return BlockQueries.litFertile.matches(world, pos.down());            
        }
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        switch ((BOPPlants) state.getValue(this.variantProperty))
        {
            case THORN: case TINYCACTUS:
            	// thorns and tiny cacti damage players who walk into them, unless they're wearing boots and pants
                if (entity instanceof EntityPlayer) {
                	InventoryPlayer inventory = ((EntityPlayer)entity).inventory;
                    if (inventory.armorInventory.get(0) != ItemStack.EMPTY && inventory.armorInventory.get(1) != ItemStack.EMPTY) {
                        break;
                    }
                    entity.attackEntityFrom(DamageSource.CACTUS, 1);
                }
                break;
                
            default:
                break;
        }
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        switch ((BOPPlants) state.getValue(this.variantProperty))
        {
            case CATTAIL: case TINYCACTUS: case ROOT:
                return false;
            default:
                return true;
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        
        // start with an empty stack
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        
        // add items based on the VARIANT
        BOPPlants plant = ((BOPPlants) world.getBlockState(pos).getValue(this.variantProperty));
        switch (plant)
        {
            case CATTAIL: case TINYCACTUS: case ROOT:
                // these items drop themselves as items when the block is broken (from getDrops), so we don't want to add anything else for using shears
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
        BOPPlants plant = ((BOPPlants) world.getBlockState(pos).getValue(this.variantProperty));
        switch (plant)
        {
            case CATTAIL:
            case REED:
            case WATERGRASS:
            case ROOT:
                return 0;
            default:
                return Blocks.TALLGRASS.getFlammability(world, pos, face);
        }
    }
    
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
    	BOPPlants plant = ((BOPPlants) world.getBlockState(pos).getValue(this.variantProperty));
        switch (plant)
        {
            case CATTAIL:
            case REED:
            case WATERGRASS:
            case ROOT:
                return 0;
            default:
                return Blocks.TALLGRASS.getFireSpreadSpeed(world, pos, face);
        }
    }

    @Override
    @Optional.Method(modid = "botania")
    public boolean canHornHarvest(World world, BlockPos pos, ItemStack stack, EnumHornType hornType)
    {
        if (hornType != EnumHornType.WILD) return false;
        BOPPlants plant = ((BOPPlants) world.getBlockState(pos).getValue(this.variantProperty));
        switch (plant)
        {
            case TINYCACTUS:
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
