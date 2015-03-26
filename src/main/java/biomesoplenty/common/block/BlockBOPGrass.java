/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.BOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPGrass extends BOPBlock
{
    public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", BOPGrassType.class);
    
    public BlockBOPGrass()
    {
        super(Material.grass);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, BOPGrassType.SPECTRALMOSS));
        this.setHardness(0.6F);
        this.setHarvestLevel("shovel", 0); // TODO: this means that ONLY a shovel can harvest this block... correct?
        this.setStepSound(Block.soundTypeGrass);
        this.setTickRandomly(true);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // only one property to worry about, the variant, so just map [0 => SPECTRALMOSS, 1 => SMOLDERINGGRASS]
        return this.getDefaultState().withProperty(VARIANT_PROP, BOPGrassType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // only one property to worry about, the variant, so just map [0 => SPECTRALMOSS, 1 => SMOLDERINGGRASS]
        return ((BOPGrassType) state.getValue(VARIANT_PROP)).ordinal();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT_PROP });
    }

    @Override
    public IProperty[] getPresetProperties()
    {
        return new IProperty[] { VARIANT_PROP };
    }

    @Override
    public String getStateName(IBlockState state, boolean fullName)
    {
        return ((BOPGrassType) state.getValue(VARIANT_PROP)).getName();
    }
    
    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side)
    {
        IBlockState state = world.getBlockState(pos);
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
            // spectralmoss burns from below in the end
            // TODO: 1.7 code had dimension=-1 here - check -1 corresponds to end
            case SPECTRALMOSS:
                if ((world.provider instanceof net.minecraft.world.WorldProviderEnd) && side == EnumFacing.UP) {return true;}
                break;
            
            // smolderinggrass always burns
            case SMOLDERINGGRASS:
                return false;
        }
        return super.isFireSource(world, pos, side);
    }
    
    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState state = this.getStateFromMeta(meta);
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
            // spectralmoss makes a hideous noise and throws a big fuss of particles around when placed in the nether
            case SPECTRALMOSS:
                if (world.provider instanceof net.minecraft.world.WorldProviderHell)
                {
                    world.playSound(pos.getX(), pos.getY(), pos.getZ(), "mob.ghast.death", 20.0F, (float)Math.random() * 0.1F, true);
                    for (int l = 0; l < 8; ++l)
                    {
                        world.spawnParticle(EnumParticleTypes.FLAME, (double)pos.getX() + Math.random(), (double)pos.getY() + Math.random(), (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
                        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)pos.getX() + Math.random(), (double)pos.getY() + Math.random(), (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                break;
            
            case SMOLDERINGGRASS:
                break;
        }
        return state;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
            case SPECTRALMOSS:
                break;
            
            // smolderinggrass throws up random flame and smoke particles
            case SMOLDERINGGRASS:
                if (rand.nextInt(4)==0)
                {           
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
                if (rand.nextInt(6)==0)
                {
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
                break;
        }
        super.randomDisplayTick(worldIn, pos, state, rand);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
            case SPECTRALMOSS:
                // spectral moss in the nether catches on fire and turns to smoldering grass
                // elsewhere it should behave like grass spreading to nearby end_stone blocks
                if (world.provider instanceof net.minecraft.world.WorldProviderHell)
                {
                    world.setBlockState(pos.up(), Blocks.fire.getDefaultState()); // might need to set fire AGE value... not sure
                    world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT_PROP, BOPGrassType.SMOLDERINGGRASS));
                }
                else
                {
                     // if this block is covered, then turn it back to end_stone (IE kill the moss)
                    if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getBlock().getLightOpacity(world, pos.up()) > 2)
                    {
                        world.setBlockState(pos, Blocks.end_stone.getDefaultState());
                    }
                    else
                    {
                        // if there's enough light from above, spread the moss randomly to nearby end_stone blocks
                        if (world.getLightFromNeighbors(pos.up()) >= 9)
                        {
                            for (int i = 0; i < 4; ++i) // try 4 times
                            {
                                // pick a random nearby position 
                                BlockPos pos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                                Block blockAbove = world.getBlockState(pos1.up()).getBlock();
                                IBlockState iblockstate1 = world.getBlockState(pos1);
                                // if there's enough light and it isn't covered, turn it to moss
                                if (iblockstate1.getBlock() == Blocks.end_stone && world.getLightFromNeighbors(pos1.up()) >= 4 && blockAbove.getLightOpacity(world, pos1.up()) <= 2)
                                {
                                    world.setBlockState(pos1, this.getDefaultState().withProperty(VARIANT_PROP, BOPGrassType.SPECTRALMOSS) );
                                }
                            }
                        }
                    }
                }
                break;
            
            case SMOLDERINGGRASS:
                break;
        }        
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {   
        float heightOffset = 0.0F;
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
            case SPECTRALMOSS:
                break;
            
            // smoldering grass is a tiny bit lower than usual
            case SMOLDERINGGRASS:
                heightOffset = 0.02F;
                break;
        }
        return new AxisAlignedBB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), (double) (pos.getX() + 1), (double) ((float) (pos.getY() + 1) - heightOffset), (double) (pos.getZ() + 1));
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
            case SPECTRALMOSS:
                break;
            
            // smoldering grass sets you on fire for 2 seconds
            case SMOLDERINGGRASS:
                entity.setFire(2);
                break;
        }       
    }
    
    // you need a silk touch tool to pick up grass-like blocks - by default they drop the corresponding 'dirt' type 
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
            case SPECTRALMOSS:
                return Item.getItemFromBlock(Blocks.end_stone);
            
            case SMOLDERINGGRASS:
                return Item.getItemFromBlock(Blocks.dirt);
        }
        return super.getItemDropped(state, rand, fortune);
    }
    
    // goes hand in hand with getItemDropped() above to determine precisely what is dropped
    @Override
    public int damageDropped(IBlockState state)
    {
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
            // end stone doesn't have any variants
            case SPECTRALMOSS:
                return 0;
            
            // make sure dirt item dropped is plain dirt (not any other variant)
            case SMOLDERINGGRASS:
                return BlockDirt.DirtType.DIRT.getMetadata();                
        }
        return super.damageDropped(state);
    }
    
    
    // enum representing the 2 variants of grass
    public static enum BOPGrassType implements IStringSerializable
    {
        SPECTRALMOSS, SMOLDERINGGRASS;

        @Override
        public String getName()
        {
            switch(this)
            {
                case SPECTRALMOSS:
                    return "spectral_moss";
                case SMOLDERINGGRASS:
                    return "smoldering_grass_block";
            }
            return this.name().toLowerCase();
        }

        @Override
        public String toString()
        {
            return getName();
        }
    }
    
}