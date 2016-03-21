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

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.item.ItemBOPPlant;
import biomesoplenty.common.util.block.VariantPagingHelper;

// TODO: pick block?

public class BlockBOPPlant extends BlockBOPDecoration implements IShearable
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
        return new BlockStateContainer(this, new IProperty[] { this.variantProperty });
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

    public enum ColoringType {PLAIN, LIKE_LEAVES, LIKE_GRASS};

    public static ColoringType getColoringType(BOPPlants plant)
    {
        switch (plant)
        {
            case SHRUB: case LEAFPILE: case POISONIVY: case BUSH: case BERRYBUSH:
            return ColoringType.LIKE_LEAVES;
            case SHORTGRASS: case MEDIUMGRASS: case SPROUT: case KORU: case CLOVERPATCH: case WHEATGRASS: case DAMPGRASS:
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
        return new IItemColor()
        {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex) 
            {
                IBlockState state = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
                
                switch ((BOPPlants) state.getValue(BlockBOPPlant.this.variantProperty))
                {
                    case BERRYBUSH: case SHRUB:
                        return 0xFFFFFF;
                    
                    default:
                        return BlockBOPPlant.this.getBlockColor().colorMultiplier(state, null, null, tintIndex);
                }
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
            case SHORTGRASS: case MEDIUMGRASS: case WHEATGRASS: case DAMPGRASS:
                if (rand.nextInt(8) == 0)
                {
                    // 1 in 8 chance of getting a seed from this grass
                    ret.add(ForgeHooks.getGrassSeed(rand, fortune));
                }
                break;
                
            case SPROUT:
                if (rand.nextInt(50) == 0)
                {
                    // in in 50 chance of getting a carrot or potato from SPROUT
                    ret.add(new ItemStack(rand.nextInt(2) == 0 ? Items.carrot : Items.potato));
                }
                break;
                
            case KORU:
                if (rand.nextInt(64) == 0)
                {
                    // 1 in 64 change of getting a turnip seed from KORU
                    ret.add(new ItemStack(BOPItems.turnip_seeds));
                }
                break;
                
            case BERRYBUSH:
                // BERRYBUSH always drops berries
                ret.add(new ItemStack(BOPItems.berries));
                break;
                
            case WILDRICE:
                // wildrice drops itself only 1 in 5 times
                if (rand.nextInt(5) == 0)
                {
                    ret.add(paging.getVariantItem(plant));
                }
                break;
                
            case CATTAIL: case RIVERCANE: case TINYCACTUS: case WITHERWART: case REED: case ROOT: case RAFFLESIA:
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
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileentity, ItemStack stack)
    {
        super.harvestBlock(world, player, pos, state, tileentity, stack);
        boolean usingShears = (stack != null && stack.getItem() instanceof ItemShears);
        switch ((BOPPlants) state.getValue(this.variantProperty))
        {
            // suffer cactus damage if you harvest thorn without shears
            case THORN:
                if (!usingShears)
                {
                    player.attackEntityFrom(DamageSource.cactus, 2);
                }
                break;
            
            default:
                break;
        }
    }
    
    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        BOPPlants plant = (BOPPlants) state.getValue(this.variantProperty);
        
        switch (plant)
        {
            case THORN: case WILDRICE: case CATTAIL: case RIVERCANE: case TINYCACTUS: case WITHERWART: case RAFFLESIA:
                return false;
            
            default:
                return true;
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, BlockPos pos, net.minecraft.client.particle.EffectRenderer effectRenderer)
    {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        // make sure the block at pos is actually this block (according to the comments in Block.addDestroyEffects, it might not be...)
        if (block != this) {return false;}
        switch ((BOPPlants) state.getValue(this.variantProperty))
        {
            case WITHERWART:
                byte n = 3;
                for (byte i = 0; i < n; i++)
                {
                    for (byte j = 0; j < n; j++)
                    {
                        for (byte k = 0; k < n; k++)
                        {
                            double x = pos.getX() + (i + 0.5D) / n;
                            double y = pos.getY() + (j + 0.5D) / n;
                            double z = pos.getZ() + (k + 0.5D) / n;                            
                            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, 0.0D, 0.0D);
                        }
                    }
                }
                break;
            
            default:
                break;
                
                
        }
        return false;
    }
    
    // different variants have different sizes
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {   
    	BOPPlants plant = (BOPPlants) state.getValue(this.variantProperty);
        switch (plant)
        {
	        case CLOVERPATCH: case RAFFLESIA:
	        	return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.4000000357627869D, 0.9375D);
            case LEAFPILE: case DEADLEAFPILE:
            	return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.09375D, 0.9375D);
            case SHORTGRASS: case DESERTGRASS: case DESERTSPROUTS:
            	return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.4000000357627869D, 0.8999999761581421D);
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
        Block blockAbove = world.getBlockState(pos.up()).getBlock();
      
        switch (plant)
        {
            case DEADGRASS: case DESERTGRASS: case TINYCACTUS:
                return BlockQueries.litDry.matches(world, pos.down());
            case DESERTSPROUTS: case DUNEGRASS:
                return BlockQueries.litSand.matches(world, pos.down());
            case SPECTRALFERN:
                return BlockQueries.spectralMoss.matches(world, pos.down());
            case THORN:
                return BlockQueries.litFertileOrDry.matches(world, pos.down());
            case CATTAIL:
                return BlockQueries.litFertileWaterside.matches(world, pos.down());
            case RIVERCANE:
                // river cane can also be placed on top of itself
                return BlockQueries.litFertileWaterside.matches(world, pos.down()) || (world.getBlockState(pos.down()) == state);
            case WITHERWART:
                return BlockQueries.sustainsNether.matches(world, pos.down());
            case REED:
                return BlockQueries.suitableForReed.matches(world, pos.down());
            case ROOT:
                // roots hang down - check against block above
                return BlockQueries.fertile.matches(world, pos.up());
            default:
                return BlockQueries.litFertile.matches(world, pos.down());            
        }
    }
    
    
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) 
    {
        switch ((BOPPlants) state.getValue(this.variantProperty))
        {
            // poison ivy throws up occasional spell particles
            case POISONIVY:
                if (rand.nextInt(32)==0)
                {           
                    world.spawnParticle(EnumParticleTypes.SPELL_MOB, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
                break;
                
            default:
                break;
        }
        super.randomDisplayTick(state, world, pos, rand);
    }
    
    
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);
        switch ((BOPPlants) state.getValue(this.variantProperty))
        {
            case BUSH:
                // every now and then berries grow on a bush
                if (rand.nextInt(80) == 0 && worldIn.getLightFromNeighbors(pos.up()) >= 9)
                {
                    worldIn.setBlockState(pos, paging.getVariantState(BOPPlants.BERRYBUSH));
                }
                break;
                
            default:
                break;
        }
    }
    
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        switch ((BOPPlants) state.getValue(this.variantProperty))
        {
            case POISONIVY:
                // poison ivy poisons players who walk into it, unless they're wearing boots and pants
                if (entity instanceof EntityPlayer) {
                	InventoryPlayer inventory = ((EntityPlayer)entity).inventory;
                    if (inventory.armorInventory[0] != null && inventory.armorInventory[1] != null) {
                        break;
                    }
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.poison, 100));
                }
                break;
            case THORN: case TINYCACTUS:
            	// thorns and tiny cacti damage players who walk into them, unless they're wearing boots and pants
                if (entity instanceof EntityPlayer) {
                	InventoryPlayer inventory = ((EntityPlayer)entity).inventory;
                    if (inventory.armorInventory[0] != null && inventory.armorInventory[1] != null) {
                        break;
                    }
                    entity.attackEntityFrom(DamageSource.cactus, 1);
                }
                break;
            default:
                break;
        }
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        switch ((BOPPlants) state.getValue(this.variantProperty))
        {
            case BERRYBUSH:
                // an activated berry bush turns into a regular bush and drops a berry
                worldIn.setBlockState(pos, paging.getVariantState(BOPPlants.BUSH));
                EntityItem berries = new EntityItem(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), new ItemStack(BOPItems.berries));
                if (!worldIn.isRemote)
                {
                    worldIn.spawnEntityInWorld(berries);
                    if (!(playerIn instanceof FakePlayer))
                    {
                        berries.onCollideWithPlayer(playerIn);
                    }
                    return true;
                }
                break;
                
            default:
                break;
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
    }
    

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        switch ((BOPPlants) state.getValue(this.variantProperty))
        {
            case CATTAIL: case RIVERCANE:
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
            case CATTAIL: case RIVERCANE: case TINYCACTUS: case WITHERWART: case REED: case ROOT:
                // these items drop themselves as items when the block is broken (from getDrops), so we don't want to add anything else for using shears
                break;
                
            case BERRYBUSH:
                // BERRYBUSH gives a regular bush when sheared (note this is in addition to the berry from getDrops)
                ret.add(paging.getVariantItem(BOPPlants.BUSH));
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
        BOPPlants plant = ((BOPPlants) world.getBlockState(pos).getValue(this.variantProperty));
        switch (plant)
        {
            case WILDRICE:
                return 0;
            case CATTAIL:
                return 0;
            case RIVERCANE:
                return 0;
            case WITHERWART:
                return 0;
            case REED:
                return 0;
            case ROOT:
                return 0;
            case RAFFLESIA:
                return 0;
            default:
                return Blocks.tallgrass.getFlammability(world, pos, face);
        }
    }
    
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
    	BOPPlants plant = ((BOPPlants) world.getBlockState(pos).getValue(this.variantProperty));
        switch (plant)
        {
            case WILDRICE:
                return 0;
            case CATTAIL:
                return 0;
            case RIVERCANE:
                return 0;
            case WITHERWART:
                return 0;
            case REED:
                return 0;
            case ROOT:
                return 0;
            case RAFFLESIA:
                return 0;
            default:
                return Blocks.tallgrass.getFireSpreadSpeed(world, pos, face);
        }
    }
    
    
    // TODO: pickblock on carrot?
    
}
