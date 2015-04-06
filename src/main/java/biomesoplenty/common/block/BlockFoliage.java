/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.List;
import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


// TODO use a different class for flax (FLAXBOTTOM and FLAXTOP) as it's so unusual
public class BlockFoliage extends BlockDecoration implements IShearable
{
    
    // add properties
    public static enum FoliageType implements IStringSerializable
    {
        SHORTGRASS, MEDIUMGRASS, BUSH, SPROUT, POISONIVY, BERRYBUSH, SHRUB, WHEATGRASS, DAMPGRASS, KORU, CLOVERPATCH, LEAFPILE, DEADLEAFPILE;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", FoliageType.class);
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
        return ((FoliageType) state.getValue(VARIANT)).getName();
    }
    
    
    public BlockFoliage()
    {
        super();
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, FoliageType.SHORTGRASS) );        
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, FoliageType.values()[meta < FoliageType.values().length ? meta : 0]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((FoliageType) state.getValue(VARIANT)).ordinal();
    }
    
    
    // get the items dropped when you bash the bush
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        
        // start with an empty stack
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        
        // add items based on the VARIANT
        switch ((FoliageType) state.getValue(VARIANT))
        {
            case SHORTGRASS: case MEDIUMGRASS: case WHEATGRASS: case DAMPGRASS:
                if (rand.nextInt(8) == 0)
                {
                    // 1 in 8 chance of getting a seed from this grass
                    ret.add(ForgeHooks.getGrassSeed(rand));
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
                
            case BERRYBUSH:
                // BERRYBUSH always drops berries
                // TODO: change from peach to berries once item is implemented
                ret.add(new ItemStack(BOPItems.peach));
                
            default:
                break;
        }
        return ret;
        
    }
    


    // TODO: comment these
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
        switch ((FoliageType) worldIn.getBlockState(pos).getValue(VARIANT))
        {
            case SHRUB: case LEAFPILE:
                return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
            case DEADLEAFPILE:
                return 0xFFFFFF;
            default:
                return BiomeColorHelper.getGrassColorAtPos(worldIn, pos);
        }
    }
    
    // berrybush item should not be tinted, even though the model is
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex)
    {
        switch ((FoliageType) state.getValue(VARIANT))
        {
            case BERRYBUSH:
                return 0xFFFFFF;
            default:
                return this.getRenderColor(state);
        }
    }

        
    
    // different variants have different sizes
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {    
        switch ((FoliageType) worldIn.getBlockState(pos).getValue(VARIANT))
        {
            case CLOVERPATCH: case LEAFPILE: case DEADLEAFPILE:
                this.setBlockBoundsByRadiusAndHeight(0.5F, 0.015625F);
                break;
            case SHORTGRASS:
                this.setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 0.25F, 0.9F);
                break; 
            case MEDIUMGRASS:
                this.setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 0.6F, 0.9F);
                break;
            default:
                this.setBlockBoundsByRadiusAndHeight(0.4F, 0.8F);
                break;
        }        
    }

    
    
    
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState groundState = world.getBlockState(pos.down());
        Block groundBlock = groundState.getBlock();
        
        // TODO: the 1.7 code contained this line:
        // if (block == Blocks.air && world.provider.dimensionId != -1 ? (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) : false) return false;
        // which can be expressed a bit more clearly as this:
        //
        // boolean notInNether = !(world.provider instanceof net.minecraft.world.WorldProviderHell);
        // boolean inAir = (groundBlock == Blocks.air);
        // boolean wellLit = (world.getLight(pos) >= 8 || world.canSeeSky(pos));
        // if (inAir && notInNether && wellLit) {return false;}
        // 
        // That looks bonkers to me, so I'm ignoring it for now - need to ask the others
        
        boolean onFertile = (groundBlock == Blocks.dirt || groundBlock == BOPBlocks.dirt || groundBlock == Blocks.mycelium || groundBlock == Blocks.grass);
        if (groundBlock instanceof BlockBOPGrass)
        {
            switch ((BlockBOPGrass.BOPGrassType) groundState.getValue(BlockBOPGrass.VARIANT))
            {
                case SPECTRAL_MOSS: case SMOLDERING: case ORIGIN: // origin should only support roses  TODO: really?
                    break;
                case OVERGROWN_NETHERRACK: case LOAMY: case SANDY: case SILTY: default:
                    onFertile = true;
                    break;
            }
        }
        return onFertile;
    }
    
    
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        switch ((FoliageType) state.getValue(VARIANT))
        {
            // poison ivy throws up occasional spell particles
            case POISONIVY:
                if (rand.nextInt(32)==0)
                {           
                    worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
                break;
                
            default:
                break;
        }
        super.randomDisplayTick(worldIn, pos, state, rand);
    }
    
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        switch ((FoliageType) state.getValue(VARIANT))
        {
            case POISONIVY:
                // poison ivy poisons players who walk into it
                if (entity instanceof EntityPlayer) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
                }
                break;
                
            default:
                break;
        }
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        switch ((FoliageType) state.getValue(VARIANT))
        {
            case BERRYBUSH:
                // an activated berry bush turns into a regular bush and drops a berry
                worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, FoliageType.BUSH));
                EntityItem berries = new EntityItem(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), new ItemStack(BOPItems.peach)); // TODO implement berry instead of peach
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
        return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }
    

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        
        // start with an empty stack
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        
        // add items based on the VARIANT
        IBlockState state = world.getBlockState(pos);
        switch ((FoliageType) state.getValue(VARIANT))
        {       
            case BERRYBUSH:
                // BERRYBUSH gives a regular bush when sheared
                ret.add(new ItemStack(this, 1, this.getMetaFromState(this.getDefaultState().withProperty(VARIANT, FoliageType.BUSH))));
                
            default:
                // default is to get the block unaltered
                ret.add(new ItemStack(this, 1, this.getMetaFromState(state)));
        }
        return ret;
    }
    
}