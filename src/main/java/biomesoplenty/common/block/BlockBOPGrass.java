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
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
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
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// TODO: add snowiness?
public class BlockBOPGrass extends BOPBlock implements IGrowable
{
    public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", BOPGrassType.class);
    public static final PropertyBool SNOWY = PropertyBool.create("snowy");
    
    public BlockBOPGrass()
    {
        super(Material.grass);
        this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, Boolean.valueOf(false)).withProperty(VARIANT_PROP, BOPGrassType.SPECTRALMOSS));
        this.setHardness(0.6F);
        this.setHarvestLevel("shovel", 0); // TODO: I think this just determines which tool speeds up digging - need to investigate more
        this.setStepSound(Block.soundTypeGrass);
        this.setTickRandomly(true);
    }
    
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.up()).getBlock();
        return state.withProperty(SNOWY, Boolean.valueOf(block == Blocks.snow || block == Blocks.snow_layer));
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // only one property in meta to worry about, the variant, so just map according to integer index in BOPGrassType
        return this.getDefaultState().withProperty(VARIANT_PROP, BOPGrassType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // only one property in meta to worry about, the variant, so just map according to integer index in BOPGrassType
        return ((BOPGrassType) state.getValue(VARIANT_PROP)).ordinal();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT_PROP, SNOWY });
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
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return ColorizerGrass.getGrassColor(0.5D, 1.0D);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return this.getBlockColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return BiomeColorHelper.getGrassColorAtPos(worldIn, pos);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT_MIPPED;
    }
    
    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        
        IBlockState state = world.getBlockState(pos);
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));
        
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
             // smoldering grass supports no plants
            case SMOLDERING:
                return false;
            
            default:
                switch (plantType)
                {
                    // support desert and plains plants
                    case Desert: case Plains: return true;
                    // support cave plants
                    case Cave:   return isSideSolid(world, pos, EnumFacing.UP);
                    // support beach plants if there's water alongside
                    case Beach:
                        return (
                            world.getBlockState(pos.east()).getBlock().getMaterial() == Material.water ||
                            world.getBlockState(pos.west()).getBlock().getMaterial() == Material.water ||
                            world.getBlockState(pos.north()).getBlock().getMaterial() == Material.water ||
                            world.getBlockState(pos.south()).getBlock().getMaterial() == Material.water
                        );
                     // don't support nether plants, water plants, or crops (require farmland), or anything else by default
                    default:
                        return false;
                }
        }
    }
    
    
    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side)
    {
        IBlockState state = world.getBlockState(pos);
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
            // spectral moss burns from below in the end
            // TODO: 1.7 code had dimension=-1 here - check -1 corresponds to end
            case SPECTRALMOSS:
                if ((world.provider instanceof net.minecraft.world.WorldProviderEnd) && side == EnumFacing.UP) {return true;}
                break;
            
            // smoldering grass always burns
            case SMOLDERING:
                return false;
            
            default:
                break;
        }
        return super.isFireSource(world, pos, side);
    }
    
    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState state = this.getStateFromMeta(meta);
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
            // spectral moss makes a hideous noise and throws a big fuss of particles around when placed in the nether
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
            
            default:
                break;
        }
        return state;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
             
            // smoldering grass throws up random flame and smoke particles
            case SMOLDERING:
                if (rand.nextInt(4)==0)
                {           
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
                if (rand.nextInt(6)==0)
                {
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
                break;
                
            default:
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
                if (world.provider instanceof net.minecraft.world.WorldProviderHell)
                {
                    world.setBlockState(pos.up(), Blocks.fire.getDefaultState()); // might need to set fire AGE value... not sure
                    world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT_PROP, BOPGrassType.SMOLDERING));
                }
                break;
                
            default:
                break;
        }
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
            case SMOLDERING:
                // smoldering grass doesn't spread to nearby dirt
                break;
                
            default:
                // the other BOP grass types all do
                this.spreadGrass(world, pos, state, rand, 4, 1, 3, 1);
                break;
        }       
 
    }
    
    // spread grass to suitable nearby blocks
    // tries - number of times to try and spread to a random nearby block
    // xzSpread - how far can the grass spread in the x and z directions
    // downSpread - how far can the grass spread downwards
    // upSpread - how far can the grass spread upwards
    // TODO: find a way to get vanilla grass to spread to BOP dirt types
    @SideOnly(Side.CLIENT)
    public void spreadGrass(World world, BlockPos pos, IBlockState state, Random rand, int tries, int xzSpread, int downSpread, int upSpread)
    {
               
        // the type of grass which is spreading
        BOPGrassType grassType = (BOPGrassType)state.getValue(VARIANT_PROP);
        // the type of dirt this grass grows on
        IBlockState dirtBlockState = grassType.getDirtBlockState();
        
        // if this block is covered, then turn it back to dirt (IE kill the grass)
       if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getBlock().getLightOpacity(world, pos.up()) > 2)
       {
           world.setBlockState(pos, dirtBlockState);
       }
       else
       {
           // if there's enough light from above, spread the grass randomly to nearby blocks of the correct dirt type
           if (world.getLightFromNeighbors(pos.up()) >= 9)
           {
               for (int i = 0; i < tries; ++i)
               {
                   // pick a random nearby position, and get the block, block state, and block above
                   BlockPos pos1 = pos.add(rand.nextInt(xzSpread * 2 + 1) - xzSpread, rand.nextInt(downSpread + upSpread + 1) - downSpread, rand.nextInt(xzSpread * 2 + 1) - xzSpread);
                   IBlockState target = world.getBlockState(pos1);
                   Block blockAboveTarget = world.getBlockState(pos1.up()).getBlock();                   
                   
                   // see if this type of grass can spread to the target block
                   IBlockState targetGrass = grassType.spreadsToGrass(target);
                   if (targetGrass == null) {break;}
                   
                   // if there's enough light, turn the block to the relevant grass block
                   if (world.getLightFromNeighbors(pos1.up()) >= 4 && blockAboveTarget.getLightOpacity(world, pos1.up()) <= 2)
                   {
                       world.setBlockState(pos1, targetGrass);
                   }                      
               }
           }
       }
    }
    

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {            
            case SPECTRALMOSS: case SMOLDERING:
                return false;
            default:
                return true;
        }
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {            
            case SPECTRALMOSS: case SMOLDERING:
                return false;
            default:
                return true;
        }
    }

    // This is called when bonemeal is applied on the block
    // The algorithm is functionally exactly the same as in the vanilla BlockGrass grow function, but has been rewritten to make its behavior clearer
    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        
        BlockPos startPos = pos.up();
        BlockPos currPos;
        
        // 128 looks like a lot of tries, but many locations will be duplicated and many tries will be abandoned because the random walk hits a snag
        for (int i = 0; i < 128; i++) {
            
            // go on a random walk away from the start position - abandon if we hit a block which can't spread the growth
            // note walkLength increases gradually with i - so we attempt walks of different lengths
            int walkLength = i / 16;
            currPos = startPos;
            boolean walkOk = true;
            for (int j = 0; j < walkLength; j++)
            {
                // shift a random distance
                currPos = currPos.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (worldIn.getBlockState(currPos.down()).getBlock() != BOPBlocks.grass || worldIn.getBlockState(currPos).getBlock().isNormalCube())
                {
                    // this block can't spread the growth
                    walkOk = false;
                    break;
                }
            }

            // try and grow something at currPos
            if (walkOk && worldIn.isAirBlock(currPos)) {
                if (rand.nextInt(8)==0) {
                    // with 1/8 probability, plant a flower
                    worldIn.getBiomeGenForCoords(currPos).plantFlower(worldIn, rand, currPos);
                }
                else
                {
                    // otherwise plant tall grass
                    IBlockState tallgrassState = Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);
                    if (Blocks.tallgrass.canBlockStay(worldIn, currPos, tallgrassState))
                    {
                        worldIn.setBlockState(currPos, tallgrassState);
                    }
                }
            }
        }
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {   
        float heightOffset = 0.0F;
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {
             // smoldering grass is a tiny bit lower than usual
            case SMOLDERING:
                heightOffset = 0.02F;
                break;
            
            default:
                break;
        }
        return new AxisAlignedBB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), (double) (pos.getX() + 1), (double) ((float) (pos.getY() + 1) - heightOffset), (double) (pos.getZ() + 1));
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        switch ((BOPGrassType) state.getValue(VARIANT_PROP))
        {            
            // smoldering grass sets you on fire for 2 seconds
            case SMOLDERING:
                entity.setFire(2);
                break;
        
            default:
                break;

        }       
    }
    
    // you need a silk touch tool to pick up grass-like blocks - by default they drop the corresponding 'dirt' type 
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock( ((BOPGrassType) state.getValue(VARIANT_PROP)).getDirtBlock() );
    }
    
    // goes hand in hand with getItemDropped() above to determine precisely what is dropped
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((BOPGrassType) state.getValue(VARIANT_PROP)).getDirtBlockMeta();
    }
    
    
    // enum representing the variants of grass
    public static enum BOPGrassType implements IStringSerializable
    {
        SPECTRALMOSS, SMOLDERING, LOAMY, SANDY, SILTY;

        @Override
        public String getName()
        {
            switch(this)
            {
                case SPECTRALMOSS:
                    return "spectral_moss";                
                default:
                    return this.name().toLowerCase() + "_grass_block";
            }
        }

        @Override
        public String toString()
        {
            return getName();
        }
        
        // get the blockstate which corresponds to the type of dirt which this grass variant grows on
        // this is used to determine what drops when you break the grass block, and the type of dirt it reverts to when covered
        public IBlockState getDirtBlockState()
        {
            switch(this)
            {
                case SPECTRALMOSS:
                    return Blocks.end_stone.getDefaultState();             
                case LOAMY:
                    return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT_PROP, BlockBOPDirt.BOPDirtType.LOAMY);
                case SANDY:
                    return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT_PROP, BlockBOPDirt.BOPDirtType.SANDY);
                case SILTY:
                    return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT_PROP, BlockBOPDirt.BOPDirtType.SILTY);
                case SMOLDERING: default:
                    return Blocks.dirt.getStateFromMeta(BlockDirt.DirtType.DIRT.getMetadata());
            }
        }
        
        public Block getDirtBlock()
        {
            return this.getDirtBlockState().getBlock();
        }
        
        public int getDirtBlockMeta()
        {
            return this.getDirtBlock().getMetaFromState(this.getDirtBlockState());
        }
        
        // if this type of grass can spread to the target block, return the grass which it will transform into
        // otherwise return null
        // this affects the grass spreading algorithm above, BlockBOPGrass.spreadGrass()
        public IBlockState spreadsToGrass(IBlockState target) {
 
            switch(this)
            {
                // spectral moss only spreads to end stone
                case SPECTRALMOSS:
                    if (target.getBlock()==Blocks.end_stone)
                    {
                        return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT_PROP, BlockBOPGrass.BOPGrassType.SPECTRALMOSS);
                    }
                    else
                    {
                        return null;
                    }
                    
                // loamy/sandy/silty grasses spread to any kind of dirt
                case LOAMY: case SANDY: case SILTY:
                    // vanilla dirt gets vanilla grass spread to it
                    if (target.getBlock() == Blocks.dirt && target.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT)
                    {
                        return Blocks.grass.getDefaultState();
                    }
                    // BOP dirt get's the corresponding BOP grass spread to it (unless it's coarse - grass doesn't grow on coarse dirt)
                    else if (target.getBlock() == BOPBlocks.dirt && Boolean.FALSE.equals(target.getValue(BlockBOPDirt.COARSE)) )
                    {
                        BlockBOPDirt.BOPDirtType targetDirtType = (BlockBOPDirt.BOPDirtType)target.getValue(BlockBOPDirt.VARIANT_PROP);
                        switch (targetDirtType)
                        {
                            case LOAMY: case SANDY: case SILTY:
                                return targetDirtType.getGrassBlockState();
                            default:
                                return null;
                        }
                    }
                    else
                    {
                        return null;
                    }
                    
                // smoldering grass doesn't spread at all
                case SMOLDERING: default:
                    return null;
            }
        }
        
    }
    
}