/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPBlocks.Coloring;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.block.ISustainsPlantType;
import biomesoplenty.common.init.ModBlocks;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPGrass extends BlockGrass implements IBOPBlock, ISustainsPlantType
{
    // add properties (note we also inherit the SNOWY property from BlockGrass)
    public static enum BOPGrassType implements IStringSerializable
    {
        SPECTRAL_MOSS, SMOLDERING, LOAMY, SANDY, SILTY, ORIGIN, OVERGROWN_NETHERRACK, DAISY;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BOPGrassType.class);
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { SNOWY, VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {
        BOPGrassType grassType = (BOPGrassType)state.getValue(VARIANT);
        switch (grassType)
        {
            case SPECTRAL_MOSS: case OVERGROWN_NETHERRACK:
                return grassType.getName();
            default:
                return grassType.getName() + "_grass_block";
        }
    }
    @Override
    public IBlockColor getBlockColor() { return Coloring.GRASS_COLORING; }
    @Override
    public IItemColor getItemColor() { return Coloring.BLOCK_ITEM_COLORING; }
    
    public BlockBOPGrass()
    {
        super();
        
        // set some defaults
        this.setHardness(0.6F);
        this.setHarvestLevel("shovel", 0);
        this.setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, Boolean.valueOf(false)).withProperty(VARIANT, BOPGrassType.LOAMY));
        
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // only one property in meta to worry about, the variant, so just map according to integer index in BOPGrassType
        return this.getDefaultState().withProperty(VARIANT, BOPGrassType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // only one property in meta to worry about, the variant, so just map according to integer index in BOPGrassType
        return ((BOPGrassType) state.getValue(VARIANT)).ordinal();
    }
    
    @Override
    public boolean canSustainPlantType(IBlockAccess world, BlockPos pos, EnumPlantType plantType)
    {
        
        IBlockState state = world.getBlockState(pos);
        
        switch ((BOPGrassType) state.getValue(VARIANT))
        {
            // smoldering grass supports no plants
            case SMOLDERING:
                return false;

                // origin grass supports all plants (including crop type - no need for hoe)
            case ORIGIN:
                return true;

                // overgrown_netherrack supports Nether plants in addition to the defaults
            case OVERGROWN_NETHERRACK:
                if (plantType == net.minecraftforge.common.EnumPlantType.Nether) {return true;}
                break;

            default: break;
        }

        switch (plantType)
        {
            // support desert, plains and cave plants
            case Desert: case Plains: case Cave:
                return true;
            // support beach plants if there's water alongside
            case Beach:
                return (
                        (!world.isAirBlock(pos.east()) && world.getBlockState(pos.east()).getMaterial() == Material.water) ||
                        (!world.isAirBlock(pos.west()) && world.getBlockState(pos.west()).getMaterial() == Material.water) ||
                        (!world.isAirBlock(pos.north()) && world.getBlockState(pos.north()).getMaterial() == Material.water) ||
                        (!world.isAirBlock(pos.south()) && world.getBlockState(pos.south()).getMaterial() == Material.water)
                        );
                // don't support nether plants, water plants, or crops (require farmland), or anything else by default
            default:
                return false;
        }
    }

    
    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {   
        return this.canSustainPlantType(world, pos, plantable.getPlantType(world, pos.offset(direction)));
    }
    
    
    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side)
    {
        IBlockState state = world.getBlockState(pos);
        switch ((BOPGrassType) state.getValue(VARIANT))
        {   
            // smoldering grass is a fire source
            case SMOLDERING:
                return true;
            
            default:
                return false;
        }
    }
    
    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState state = this.getStateFromMeta(meta);
        switch ((BOPGrassType) state.getValue(VARIANT))
        {
            // spectral moss makes a hideous noise and throws a big fuss of particles around when placed in the nether
            case SPECTRAL_MOSS:
                if (world.provider instanceof net.minecraft.world.WorldProviderHell)
                {
                    //TODO: 1.9 world.playSound(pos.getX(), pos.getY(), pos.getZ(), "mob.ghast.death", 20.0F, (float)Math.random() * 0.1F, true);
                    for (int l = 0; l < 8; ++l)
                    {
                        world.spawnParticle(EnumParticleTypes.FLAME, (double)pos.getX() + Math.random(), (double)pos.getY() + 0.5D + Math.random(), (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
                        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)pos.getX() + Math.random(), (double)pos.getY() + 0.5D + Math.random(), (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
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
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        switch ((BOPGrassType) state.getValue(VARIANT))
        {
             
            // smoldering grass throws up random flame and smoke particles
            case SMOLDERING:
                if (rand.nextInt(4)==0)
                {
                    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
                if (rand.nextInt(6)==0)
                {
                    world.spawnParticle(EnumParticleTypes.FLAME, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
                break;
                
            default:
                break;
                    
        }
        super.randomDisplayTick(state, world, pos, rand);
    }
    
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        switch ((BOPGrassType) state.getValue(VARIANT))
        {
            case SPECTRAL_MOSS:
                // spectral moss in the nether catches on fire and turns to smoldering grass
                if (world.provider instanceof net.minecraft.world.WorldProviderHell)
                {
                    world.setBlockState(pos.up(), Blocks.fire.getDefaultState()); // might need to set fire AGE value... not sure
                    world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BOPGrassType.SMOLDERING));
                }
                break;
            
            case SMOLDERING:
                // smoldering grass melts snow
                IBlockState stateAbove = world.getBlockState(pos.up());
                if (stateAbove.getMaterial() == Material.snow)
                {
                    world.setBlockToAir(pos.up());
                }
                break;
                
            default:
                break;
        }
        switch ((BOPGrassType) state.getValue(VARIANT))
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
    public void spreadGrass(World world, BlockPos pos, IBlockState state, Random rand, int tries, int xzSpread, int downSpread, int upSpread)
    {
        
        // if this block is covered, then turn it back to dirt (IE kill the grass)
       if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getBlock().getLightOpacity(world.getBlockState(pos.up())) > 2)
       {
           world.setBlockState(pos, getDirtBlockState(state));
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
                   IBlockState targetGrass = spreadsToGrass(state, target);
                   if (targetGrass == null) {break;}
                   
                   // if there's enough light, turn the block to the relevant grass block
                   if (world.getLightFromNeighbors(pos1.up()) >= 4 && blockAboveTarget.getLightOpacity(target) <= 2)
                   {
                       world.setBlockState(pos1, targetGrass);
                   }                      
               }
           }
       }
    }
    

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        switch ((BOPGrassType) state.getValue(VARIANT))
        {            
            case SPECTRAL_MOSS: case SMOLDERING: case OVERGROWN_NETHERRACK:
                return false;
            default:
                return true;
        }
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        switch ((BOPGrassType) state.getValue(VARIANT))
        {            
            case SPECTRAL_MOSS: case SMOLDERING: case OVERGROWN_NETHERRACK:
                return false;
            default:
                return true;
        }
    }

    // This is called when bonemeal is applied on the block
    // The algorithm is functionally equivalent to the vanilla BlockGrass grow function, but has been rewritten to make its behavior clearer
    // TODO: grows spreads from BOP grass to vanilla grass, need to find a way to make growth on vanilla grass also spread to BOP grass
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
                Block currBlockBelow =  worldIn.getBlockState(currPos.down()).getBlock();
                if ( (currBlockBelow != Blocks.grass && currBlockBelow != BOPBlocks.grass) || worldIn.getBlockState(currPos).isNormalCube())
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
    
/*    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {   
        float heightOffset = 0.0F;
        switch ((BOPGrassType) state.getValue(VARIANT))
        {
             // smoldering grass is a tiny bit lower than usual
            case SMOLDERING:
                heightOffset = 0.02F;
                break;
            
            default:
                break;
        }
        return new AxisAlignedBB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), (double) (pos.getX() + 1), (double) ((float) (pos.getY() + 1) - heightOffset), (double) (pos.getZ() + 1));
    }*/
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        switch ((BOPGrassType) state.getValue(VARIANT))
        {            
            // smoldering grass sets you on fire for 2 seconds
            case SMOLDERING:
                entity.setFire(2);
                break;
        
            default:
                break;

        }       
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean addDestroyEffects(World world, BlockPos pos, EffectRenderer effectRenderer)
    {
        IBlockState state = world.getBlockState(pos);
        
        if (state.getBlock() == this)
        {
            //Fixes grass block break particles
            switch ((BOPGrassType) state.getValue(VARIANT))
            {       
                case LOAMY: case SANDY: case SILTY:
                    effectRenderer.addBlockDestroyEffects(pos, getDirtBlockState(state));
                    return true;
            }
        }
        
        return false;
    }
    
    // by default, getPickBlock uses damageDropped to determine the metadata of the block picked. This
    // doesn't suit our case as the block dropped has a different metadata configuration from this block
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromState(world.getBlockState(pos)));
    }
    
    // you need a silk touch tool to pick up grass-like blocks - by default they drop the corresponding 'dirt' type 
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(getDirtBlock(state));
    }
    
    // goes hand in hand with getItemDropped() above to determine precisely what is dropped
    @Override
    public int damageDropped(IBlockState state)
    {
        return getDirtBlockMeta(state);
    }
 
    
    // get the blockstate which corresponds to the type of dirt which this grass variant grows on
    // this is used to determine what drops when you break the grass block, and the type of dirt it reverts to when covered
    public static IBlockState getDirtBlockState(IBlockState state)
    {
        switch ((BOPGrassType) state.getValue(VARIANT))
        {
            case SPECTRAL_MOSS:
                return Blocks.end_stone.getDefaultState();             
            case LOAMY:
                return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.LOAMY);
            case SANDY:
                return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY);
            case SILTY:
                return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SILTY);
            case OVERGROWN_NETHERRACK:
                return Blocks.netherrack.getDefaultState();
            case SMOLDERING: case ORIGIN: case DAISY:  default:
                return Blocks.dirt.getStateFromMeta(BlockDirt.DirtType.DIRT.getMetadata());
        }
    }
    
    public static Block getDirtBlock(IBlockState state)
    {
        return getDirtBlockState(state).getBlock();
    }
    
    public static int getDirtBlockMeta(IBlockState state)
    {
        return getDirtBlock(state).getMetaFromState(getDirtBlockState(state));
    }
    
    
    // if the source type of grass can spread to the target block, return the grass which it will transform into
    // otherwise return null
    // this affects the grass spreading algorithm above, BlockBOPGrass.spreadGrass()
    public static IBlockState spreadsToGrass(IBlockState source, IBlockState target) {

        switch ((BOPGrassType) source.getValue(VARIANT))
        {
            // spectral moss only spreads to end stone
            case SPECTRAL_MOSS:
                if (target.getBlock()==Blocks.end_stone)
                {
                    return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SPECTRAL_MOSS);
                }
                break;
             
            case OVERGROWN_NETHERRACK:
                if (target.getBlock()==Blocks.netherrack)
                {
                    return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK);
                }
                break;                
                
            // loamy/sandy/silty grasses spread to any kind of dirt
            case LOAMY: case SANDY: case SILTY:
                // vanilla dirt gets vanilla grass spread to it
                if (target.getBlock() == Blocks.dirt && target.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT)
                {
                    return Blocks.grass.getDefaultState();
                }
                // BOP dirt get's the corresponding BOP grass spread to it (unless it's coarse - grass doesn't grow on coarse dirt)
                if (target.getBlock() == BOPBlocks.dirt)
                {
                    return BlockBOPDirt.getGrassBlockState(target);
                }
                break;
            
            // origin grass spreads to any kind of dirt
            case ORIGIN: case DAISY:
                if ((target.getBlock() == Blocks.dirt && target.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT) || (target.getBlock() == BOPBlocks.dirt && Boolean.FALSE.equals(target.getValue(BlockBOPDirt.COARSE))))
                {
                    return BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.ORIGIN);
                }
                break;
                
            // smoldering grass doesn't spread at all
            case SMOLDERING: default:
                break;
        }
        return null;
    }
    
    
  
    
}