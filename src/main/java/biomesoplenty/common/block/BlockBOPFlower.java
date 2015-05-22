/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.item.ItemBOPFlower;
import biomesoplenty.common.util.block.VariantPagingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemShears;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// TODO: Readd eyebulb in as a seperate block
// TODO: placing lily of the valley.  where is lily flower?
public class BlockBOPFlower extends BlockDecoration
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
    protected BlockState createBlockState()
    {
        this.variantProperty = currentVariantProperty; // get from static variable
        return new BlockState(this, new IProperty[] { this.variantProperty });
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
    
    
    // set the size of the different flowers' bounding boxes
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        switch ((BOPFlowers) world.getBlockState(pos).getValue(this.variantProperty))
        {
            case CLOVER:
                this.setBlockBoundsByRadiusAndHeightWithXZOffset(0.5F, 0.015625F, pos);
                break;

            case ORANGE_COSMOS:
                this.setBlockBoundsByRadiusAndHeightWithXZOffset(0.2F, 0.8F, pos);
                break;

            case PINK_DAFFODIL: case DANDELION:
                this.setBlockBoundsByRadiusAndHeightWithXZOffset(0.2F, 0.6F, pos);
                break;

            case WHITE_ANEMONE:
                this.setBlockBoundsByRadiusAndHeightWithXZOffset(0.2F, 0.5F, pos);
                break;

            default:
                this.setBlockBoundsByRadiusAndHeightWithXZOffset(0.4F, 0.8F, pos);
                break;
        }
    }
    
    
    // some flowers emit light
    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos)
    {
        switch ((BOPFlowers) world.getBlockState(pos).getValue(this.variantProperty))
        {
            case GLOWFLOWER:
                return 9;

            case ENDERLOTUS:
                return 5;

            case BURNING_BLOSSOM:
                return 9;

            default:
                return super.getLightValue();
        }
    }
    
    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileentity)
    {
        super.harvestBlock(world, player, pos, state, tileentity);
        if (player.getCurrentEquippedItem() == null || !(player.getCurrentEquippedItem().getItem() instanceof ItemShears))
        {
            switch ((BOPFlowers) state.getValue(this.variantProperty))
            {
                // suffer wither effect if you harvest deathbloom without shears
                case DEATHBLOOM:
                    player.addPotionEffect(new PotionEffect(Potion.wither.id, 300));
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
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
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
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
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
        
        boolean onFertile = (groundBlock == Blocks.dirt || groundBlock == Blocks.farmland || groundBlock == BOPBlocks.dirt || groundBlock == Blocks.grass);
        boolean onDry = (groundBlock == BOPBlocks.hard_dirt || groundBlock == Blocks.hardened_clay || groundBlock == Blocks.sand || groundBlock == BOPBlocks.hard_sand);
        boolean onNetherrack = (groundBlock == Blocks.netherrack);
        boolean onStone = (groundBlock == Blocks.stone);
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
            case BURNING_BLOSSOM:
                return onNetherrack;
            case MINERS_DELIGHT:
                return onStone;
            default:
                return onFertile;
        }
        
    }
    
    

    
}