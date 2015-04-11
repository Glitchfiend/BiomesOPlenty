/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPPlantEnums;
import biomesoplenty.api.block.BOPPlantEnums.AllPlants;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.item.ItemBOPPlant;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
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

// TODO: double cattail
// TODO: pick block?

public abstract class BlockBOPPlant extends BlockDecoration implements IShearable
{
    
    
    // setup paged variant property
    
    // All 4 meta bits available for VARIANT which means we can have sixteen per instance
    public static final int VARIANTS_PER_PAGE = 16;
    // child classes must implement to define their page number
    abstract public int getPageNum();
    // fetch the variant property for a given page
    public static PropertyEnum getVariantProperty(int pageNum)
    {
        return BOPPlantEnums.getVariantProperty(pageNum, VARIANTS_PER_PAGE);
    }
    // fetch the current instance's variant property
    public PropertyEnum getMyVariantProperty()
    {
        return getVariantProperty(getPageNum());
    }
    // get the meta bits from the variant
    public int metaFromVariant(AllPlants plant)
    {
        return plant.ordinal() % VARIANTS_PER_PAGE;
    }
    // get the variant from meta bits (safely)
    public AllPlants variantFromMeta(int meta)
    {
        int i = Math.max(0, Math.min(meta + (this.getPageNum() * VARIANTS_PER_PAGE), AllPlants.values().length));
        return AllPlants.values()[i];
    }
    // store reference to each created instance, indexed by page num, so that later we can look up the right BlockFoliage instance for a particular variant
    private static Map<Integer, BlockBOPPlant> instances = new HashMap<Integer, BlockBOPPlant>();
    
    // get the BlockFoliage instance for the given variant
    public static BlockBOPPlant getVariantBlock(AllPlants plant)
    {
        int pageNum = plant.ordinal() / VARIANTS_PER_PAGE;
        BlockBOPPlant block = instances.get(pageNum);
        if (block == null) {throw new IllegalArgumentException("No BlockFoliage instance created yet for page "+pageNum);}
        return block;
    }
    // get the default block state for the given variant
    public static IBlockState getVariantState(AllPlants plant)
    {
        BlockBOPPlant block = getVariantBlock(plant);
        return block.getDefaultState().withProperty(block.getMyVariantProperty() , plant);
    }
    // get the item representation of the given variant
    public static ItemStack getVariantItem(AllPlants plant, int howMany)
    {
        return new ItemStack(getVariantBlock(plant), howMany, getVariantBlock(plant).getMetaFromState(getVariantState(plant)));
    }
    public static ItemStack getVariantItem(AllPlants plant)
    {
        return getVariantItem(plant, 1);
    }
    
    
    
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { getMyVariantProperty() });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPPlant.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] { getMyVariantProperty() }; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        AllPlants plant = (AllPlants) state.getValue(getMyVariantProperty());
        switch (plant)
        {
            case WILDCARROT:
                return plant.getName() + "_block";
            default:
                return plant.getName();
        }
    }
    
    public BlockBOPPlant()
    {
        super();
        // save a reference to this instance so that later we can look up the right BlockFoliage instance for a particular variant
        instances.put(this.getPageNum(), this);
        this.setDefaultState( this.blockState.getBaseState() );        
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(getMyVariantProperty(), variantFromMeta(meta));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return metaFromVariant((AllPlants) state.getValue(getMyVariantProperty()));
    }
    
    
    // get the items dropped when you bash the bush
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        
        // start with an empty stack
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        
        // add items based on the VARIANT
        AllPlants plant = (AllPlants) state.getValue(getMyVariantProperty());
        switch (plant)
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
                break;
                
            case BERRYBUSH:
                // BERRYBUSH always drops berries
                ret.add(new ItemStack(BOPItems.berries));
                break;
                
            case WILDRICE:
                // wildrice drops itself only 1 in 5 times
                if (rand.nextInt(5) == 0)
                {
                    ret.add(getVariantItem(plant));
                }
                break;
            
            case WILDCARROT:
                ret.add(new ItemStack(BOPItems.wildcarrots));
                break;
                
            case CATTAIL: case RIVERCANE: case TINYCACTUS: case WITHERWART: case REED: case ROOT:
                // these variants drop themselves as items
                ret.add(getVariantItem(plant));
                break;
                
            default:
                // the rest drop nothing
                break;
        }
        return ret;
        
    }
    
    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileentity)
    {
        super.harvestBlock(world, player, pos, state, tileentity);
        boolean usingShears = (player.getCurrentEquippedItem() == null || !(player.getCurrentEquippedItem().getItem() instanceof ItemShears));
        switch ((AllPlants) state.getValue(getMyVariantProperty()))
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
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, BlockPos pos, net.minecraft.client.particle.EffectRenderer effectRenderer)
    {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        // make sure the block at pos is actually this block (according to the comments in Block.addDestroyEffects, it might not be...)
        if (block != this) {return false;}
        switch ((AllPlants) state.getValue(getMyVariantProperty()))
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
    
   
    
    public enum ColoringType {PLAIN, LIKE_LEAVES, LIKE_GRASS};
    
    public static ColoringType getColoringType(AllPlants plant)
    {
        switch (plant)
        {
            case SHRUB: case LEAFPILE: case POISONIVY: case BUSH: case BERRYBUSH:
                return ColoringType.LIKE_LEAVES;
            case SHORTGRASS: case MEDIUMGRASS: case SPROUT: case KORU: case CLOVERPATCH: 
                return ColoringType.LIKE_GRASS;
            default:
                return ColoringType.PLAIN;
        }       
    }
 
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 0xFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        switch (getColoringType((AllPlants) state.getValue(getMyVariantProperty())))
        {
            case LIKE_LEAVES:
                return ColorizerFoliage.getFoliageColorBasic();
            case LIKE_GRASS:
                return ColorizerGrass.getGrassColor(0.5D, 1.0D);
            case PLAIN: default:
                return 0xFFFFFF;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        switch (getColoringType((AllPlants) worldIn.getBlockState(pos).getValue(getMyVariantProperty())))
        {
            case LIKE_LEAVES:
                return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
            case LIKE_GRASS:
                return BiomeColorHelper.getGrassColorAtPos(worldIn, pos);
            case PLAIN: default:
                return 0xFFFFFF;
        }
    }
    
    // berrybush / shrub item should not be tinted, even though the model is
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex)
    {
        switch ((AllPlants) state.getValue(getMyVariantProperty()))
        {
            case BERRYBUSH: case SHRUB:
                return 0xFFFFFF;
            default:
                return this.getRenderColor(state);
        }
    }
    
    // different variants have different sizes
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {   
        IBlockState state = worldIn.getBlockState(pos);
        switch ((AllPlants) state.getValue(getMyVariantProperty()))
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
            case CATTAIL:
                this.setBlockBoundsByRadiusAndHeight(0.375F, 1.0F);
                break;
            case TINYCACTUS:
                // TODO: what on earth is this madness? Does it just cause some random scattering?
                long i1 = pos.getX() * 3129871 ^ pos.getZ() * 116129781L ^ pos.getY();
                i1 = i1 * i1 * 42317861L + i1 * 11L;
                float d0 = (float)(((i1 >> 16 & 15L) / 15.0F - 0.5D) * 0.5D);
                float d2 = (float)(((i1 >> 24 & 15L) / 15.0F - 0.5D) * 0.5D);
                this.setBlockBounds(0.3F + d0, 0.0F, 0.3F + d2, 0.7F + d0, 0.4F, 0.7F + d2);
                break;
            case ROOT:
                // roots hang from ceiling
                this.setBlockBoundsByRadiusAndHeight(0.4F, 0.8F, true);
                break;
            case REED:
                // reeds extend one block below
                this.setBlockBounds(0.2F, -1.0F, 0.2F, 0.8F, 0.8F, 0.8F);
                break;
            default:
                this.setBlockBoundsByRadiusAndHeight(0.4F, 0.8F);
                break;
        }        
    }

    
    
    
    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        AllPlants plant = ((AllPlants) state.getValue(getMyVariantProperty()));
        // roots hang down from above, all the others grow up from below
        IBlockState adjacentBlockState = world.getBlockState(plant == AllPlants.ROOT ? pos.up() : pos.down());
        Block adjacentBlock = adjacentBlockState.getBlock();
        
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
        
        boolean onFertile = (adjacentBlock == Blocks.dirt || adjacentBlock == BOPBlocks.dirt || adjacentBlock == Blocks.mycelium || adjacentBlock == Blocks.grass);
        boolean onDry = (adjacentBlock == BOPBlocks.hard_dirt || adjacentBlock == Blocks.hardened_clay || adjacentBlock == Blocks.sand || adjacentBlock == BOPBlocks.hard_sand || adjacentBlock == Blocks.soul_sand);
        boolean onSand = (adjacentBlock == Blocks.sand || adjacentBlock == Blocks.soul_sand);
        boolean onGrass = (adjacentBlock == Blocks.grass);
        boolean onSpectralMoss = false;
        
        if (adjacentBlock instanceof BlockBOPGrass)
        {
            switch ((BlockBOPGrass.BOPGrassType) adjacentBlockState.getValue(BlockBOPGrass.VARIANT))
            {
                case SPECTRAL_MOSS:
                    onSpectralMoss = true;
                    break;
                case SMOLDERING:
                    break;
                case OVERGROWN_NETHERRACK:
                    onFertile = true;
                    break;
                case LOAMY: case SANDY: case SILTY: case ORIGIN: default:
                    onFertile = true;
                    onGrass = true;
                    break;
            }
        }        
        switch (plant)
        {
            case DEADGRASS: case DESERTGRASS: case TINYCACTUS:
                return onDry;
            case DESERTSPROUTS: case DUNEGRASS:
                return onSand;
            case SPECTRALFERN:
                return onSpectralMoss;
            case THORN:
                return onFertile || onSand;
            case CATTAIL:
                boolean hasWater = (world.getBlockState(pos.add(-1, -1, 0)).getBlock().getMaterial() == Material.water || world.getBlockState(pos.add(1,-1,0)).getBlock().getMaterial() == Material.water || world.getBlockState(pos.add(0,-1,-1)).getBlock().getMaterial() == Material.water || world.getBlockState(pos.add(0,-1,1)).getBlock().getMaterial() == Material.water);
                return onGrass && hasWater;
            case RIVERCANE:
                boolean onSelf = ( (adjacentBlock instanceof BlockBOPPlant) && ((AllPlants) adjacentBlockState.getValue(((BlockBOPPlant)adjacentBlock).getMyVariantProperty()) == AllPlants.RIVERCANE) );
                return onSelf || onFertile;
            case WITHERWART:
                return (adjacentBlock == Blocks.soul_sand);
            case REED:
                // reed needs the ground block to be water, but the block below that to NOT be water
                // TODO: reed is gonna have some trickiness with placing, implement as lily variation instead?
                return (adjacentBlock == Blocks.water && world.getBlockState(pos.down().down()).getBlock() != Blocks.water);
            default:
                return onFertile;            
        }
        
    }
    
    
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        switch ((AllPlants) state.getValue(getMyVariantProperty()))
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
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);
        switch ((AllPlants) state.getValue(getMyVariantProperty()))
        {
            case BUSH:
                // every now and then berries grow on a bush
                if (rand.nextInt(80) > 0 && worldIn.getLightFromNeighbors(pos.up()) >= 9)
                {
                    worldIn.setBlockState(pos, getVariantState(AllPlants.BERRYBUSH));
                }
                break;
                
            default:
                break;
        }
    }
    
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        switch ((AllPlants) state.getValue(getMyVariantProperty()))
        {
            case POISONIVY:
                // poison ivy poisons players who walk into it
                if (entity instanceof EntityPlayer) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
                }
                break;
            case THORN: case TINYCACTUS:
                // thorns and tiny cacti harm players like a vanilla cactus unless the player is wearing leather boots and leather leggings
                // TODO: should other types of armor protect from thorns?
                if (entity instanceof EntityPlayer) {
                    InventoryPlayer inventory = ((EntityPlayer)entity).inventory;
                    boolean wearingLeatherBoots = (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == Items.leather_boots);
                    boolean wearingLeatherLeggings = (inventory.armorInventory[1] != null && inventory.armorInventory[1].getItem() == Items.leather_leggings);
                    if (!wearingLeatherBoots && !wearingLeatherLeggings)
                    {
                        entity.attackEntityFrom(DamageSource.cactus, 1);
                    }
                }
                break;
            default:
                break;
        }
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        switch ((AllPlants) state.getValue(getMyVariantProperty()))
        {
            case BERRYBUSH:
                // an activated berry bush turns into a regular bush and drops a berry
                worldIn.setBlockState(pos, getVariantState(AllPlants.BUSH));
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
        return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }
    

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        switch ((AllPlants) state.getValue(getMyVariantProperty()))
        {
            case CATTAIL: case RIVERCANE: case WILDCARROT:
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
        AllPlants plant = ((AllPlants) world.getBlockState(pos).getValue(getMyVariantProperty()));
        switch (plant)
        {
            case CATTAIL: case RIVERCANE: case TINYCACTUS: case WITHERWART: case REED: case ROOT:
                // these items drop themselves as items when the block is broken (from getDrops), so we don't want to add anything else for using shears
                break;
                
            case BERRYBUSH:
                // BERRYBUSH gives a regular bush when sheared (note this is in addition to the berry from getDrops)
                IBlockState bush = getVariantState(AllPlants.BUSH);
                ret.add(new ItemStack(bush.getBlock(), 1, this.getMetaFromState(bush)));
                // ret.add(new ItemStack(BOPItems.berries, 1));
                break;
                
            default:
                // for everything else, get the block as an item
                ret.add(getVariantItem(plant));
                break;
        }
        return ret;
    }
    
    
    // TODO: pickblock on carrot?
    
}
