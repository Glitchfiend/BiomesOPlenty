/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.config.GameplayConfigurationHandler;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.item.ItemBOPFlower;
import biomesoplenty.common.util.block.VariantPagingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPFlower extends BlockBOPDecoration implements IShearable
{
    
    // setup paged variant property
    
    // All 4 bits are available for the VARIANT which means we can have sixteen per instance
    public static VariantPagingHelper<BlockBOPFlower, BOPFlowers> paging = new VariantPagingHelper<BlockBOPFlower, BOPFlowers>(16, BOPFlowers.class);
    
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
            paging.addBlock(i, new BlockBOPFlower());
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
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPFlower.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] { this.variantProperty }; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((BOPFlowers) state.getValue(this.variantProperty)).getName();
    }
    
    
    public BlockBOPFlower()
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
        BOPFlowers flower = (BOPFlowers) state.getValue(this.variantProperty);
        return paging.getIndex(flower);
    }
    
    // make sure the block drops the right type of flower
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }
    
    // different variants have different sizes
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {   
    	BOPFlowers flower = (BOPFlowers) state.getValue(this.variantProperty);
        switch (flower)
        {
        	case CLOVER:
        		return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.09375D, 0.9375D);
        	case SWAMPFLOWER: case VIOLET: case WHITE_ANEMONE: case BLUEBELLS:
        		return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.4000000357627869D, 0.9375D);
        	case BLUE_HYDRANGEA: case BURNING_BLOSSOM:
        		return new AxisAlignedBB(0.20000001788D, 0.0D, 0.20000001788D, 0.79999998211D, 0.6000000238418579D, 0.79999998211D);
        	case ENDERLOTUS: case BROMELIAD: case PINK_HIBISCUS: case LILY_OF_THE_VALLEY: case LAVENDER:
            	return new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
            default:
            	return new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);
        }        
    }
    
    // some flowers emit light
    @Override
    public int getLightValue(IBlockState state)
    {
        switch ((BOPFlowers) state.getValue(this.variantProperty))
        {
            case GLOWFLOWER:
                return 9;

            case ENDERLOTUS:
                return 5;

            case BURNING_BLOSSOM:
                return 9;

            default:
                return super.getLightValue(state);
        }
    }
    
    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileentity, ItemStack stack)
    {
        super.harvestBlock(world, player, pos, state, tileentity, stack);
        if (player.getHeldItem(EnumHand.MAIN_HAND) == null || !(player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemShears))
        {
            switch ((BOPFlowers) state.getValue(this.variantProperty))
            {
                // suffer wither effect if you harvest deathbloom without shears
                case DEATHBLOOM:
                    player.addPotionEffect(new PotionEffect(MobEffects.wither, 300));
                    break;

                // catch on fire if you harvest burning_blossom without shears
                case BURNING_BLOSSOM:
                    player.setFire(5);
                    break;
                
                default:
                    break;
            }
        }
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        switch((BOPFlowers) state.getValue(this.variantProperty))
        {
            // suffer wither effect if you walk on deathbloom
            case DEATHBLOOM:
                if (entity instanceof EntityLivingBase) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.wither, 200));
                }
                break;
                
            // living entities get set on fire by burning_blossom
            case BURNING_BLOSSOM:
                if (entity instanceof EntityLivingBase) {
                    entity.setFire(1); 
                }
                break;
                
            default:
                break;
        }
    }
    
    
    // add particle effects for deathbloom and burning_blossom
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        switch((BOPFlowers) state.getValue(this.variantProperty))
        {
            case DEATHBLOOM:
                if (rand.nextInt(4) != 0)
                    world.spawnParticle(EnumParticleTypes.TOWN_AURA, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
                if (rand.nextInt(4) == 0)
                    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
                break;
                
            case BURNING_BLOSSOM:
                if (rand.nextInt(2) == 0)
                    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
                if (rand.nextInt(4) == 0)
                    world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
                break;
                
            default:
                break;
        }
    }
    
    
    
    // which types of flower can live on which types of block
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState groundState = world.getBlockState(pos.down());
        Block groundBlock = groundState.getBlock();
        
        boolean onFertile = (groundBlock == Blocks.dirt || groundBlock == Blocks.farmland || groundBlock == BOPBlocks.farmland_0 || groundBlock == BOPBlocks.farmland_1 || groundBlock == BOPBlocks.dirt || groundBlock == Blocks.grass);
        boolean onDry = (groundBlock == Blocks.hardened_clay || groundBlock == BOPBlocks.sand || groundBlock == Blocks.sand);
        boolean onNetherrack = (groundBlock == Blocks.netherrack);
        boolean onStone = (groundBlock == Blocks.stone);
        boolean onDriedSand = (groundBlock == BOPBlocks.dried_sand);
        boolean onSpectralMoss = false;
        
        if (groundBlock instanceof BlockBOPGrass)
        {
            switch ((BlockBOPGrass.BOPGrassType) groundState.getValue(BlockBOPGrass.VARIANT))
            {
            
                case SPECTRAL_MOSS:
                    onSpectralMoss = true;
                    break;
                case OVERGROWN_NETHERRACK:
                    onFertile = true;
                    onNetherrack = true;
                    break;
                case LOAMY: case SANDY: case SILTY: case ORIGIN: default:
                    onFertile = true;
                    break;
            }
        }
        
        switch ((BOPFlowers) state.getValue(this.variantProperty))
        {
            case ENDERLOTUS:
                return onSpectralMoss;
            case BROMELIAD:
                return onDry;
            case WILTED_LILY:
                return onDriedSand;
            case BURNING_BLOSSOM:
                return onNetherrack;
            case MINERS_DELIGHT:
                return onStone;
            default:
                return onFertile;
        }
        
    }
    
    //
    // The below three methods are used for the require shears setting
    //
    
    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) 
    {
        return GameplayConfigurationHandler.flowerDropsNeedShears;
    }
    
    @Override
    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        EntityPlayer player = this.harvesters.get();
        
        //Only check if shears are being using if the appropriate option is enabled, and it's a player harvesting this block
        if (GameplayConfigurationHandler.flowerDropsNeedShears && player != null)
        {
            boolean usingShears = (player.getHeldItem(EnumHand.MAIN_HAND) != null && player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemShears);

            //Player must use shears when flowerDropsNeedShears is enabled
            if (!usingShears)
                return;
        }

        super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);
    }
    
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        BOPFlowers flower = ((BOPFlowers) world.getBlockState(pos).getValue(this.variantProperty));
        switch (flower)
        {
            case BURNING_BLOSSOM:
                return 0;
            default:
                return Blocks.red_flower.getFlammability(world, pos, face);
        }
    }
    
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
    	BOPFlowers flower = ((BOPFlowers) world.getBlockState(pos).getValue(this.variantProperty));
        switch (flower)
        {
            case BURNING_BLOSSOM:
                return 0;
            default:
                return Blocks.red_flower.getFireSpreadSpeed(world, pos, face);
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)  { return new ArrayList<ItemStack>(); }
    
    

    
}