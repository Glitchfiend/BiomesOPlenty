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
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShears;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// TODO: Readd eyebulb in as a seperate block
// TODO: Readd dandelion blowing
// TODO: placing lily of the valley.  where is lily flower?
public class BlockBOPFlower1 extends BlockDecoration {
        
    // add properties
    public static enum FlowerType implements IStringSerializable
    {
        CLOVER, SWAMPFLOWER, DEATHBLOOM, GLOWFLOWER, BLUE_HYDRANGEA, ORANGE_COSMOS, PINK_DAFFODIL, WILDFLOWER, VIOLET, WHITE_ANEMONE, ENDERLOTUS, BROMELIAD, DANDELION, PINK_HIBISCUS, LILY_OF_THE_VALLEY, BURNING_BLOSSOM;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", FlowerType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });} 
    
    
    // implement IBOPBlock
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((FlowerType) state.getValue(VARIANT)).getName();
    }
    
    
    public BlockBOPFlower1()
    {
        super();        
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, FlowerType.CLOVER) );
    }
    
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, FlowerType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((FlowerType) state.getValue(VARIANT)).ordinal();
    }
    
    // set the size of the different flowers' bounding boxes
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        switch ((FlowerType) world.getBlockState(pos).getValue(VARIANT))
        {
            case CLOVER:
                this.setBlockBoundsByRadiusAndHeight(0.5F, 0.015625F);
                break;

            case ORANGE_COSMOS:
                this.setBlockBoundsByRadiusAndHeight(0.2F, 0.8F);
                break;

            case PINK_DAFFODIL: case DANDELION:
                this.setBlockBoundsByRadiusAndHeight(0.2F, 0.6F);
                break;

            case WHITE_ANEMONE:
                this.setBlockBoundsByRadiusAndHeight(0.2F, 0.5F);
                break;

            default:
                this.setBlockBoundsByRadiusAndHeight(0.4F, 0.8F);
                break;
        }
    }
    
    
    // some flowers emit light
    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos)
    {
        switch ((FlowerType) world.getBlockState(pos).getValue(VARIANT))
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
            switch ((FlowerType) state.getValue(VARIANT))
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
        switch((FlowerType) state.getValue(VARIANT))
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
        switch((FlowerType) state.getValue(VARIANT))
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
        
        switch ((FlowerType) state.getValue(VARIANT))
        {
            case ENDERLOTUS:
                return onSpectralMoss;
            case BROMELIAD:
                return onDry;
            case BURNING_BLOSSOM:
                return onNetherrack;
            default:
                return onFertile;
        }
        
    }
    
    
    

    
}