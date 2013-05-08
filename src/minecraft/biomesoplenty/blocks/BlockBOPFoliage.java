package biomesoplenty.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.blocks.renderers.FoliageRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFoliage extends BlockFlower implements IShearable
{
    private static final String[] foliageTypes = new String[] {"algae", "shortgrass", "mediumgrass", "highgrassbottom", "bush", "sprout", "highgrasstop", "poisonivy"};
    
    private Icon[] textures;

    private static final int GRASSTOP = 6;
    private static final int ALGAE = 0;
    private static final int GRASSBOTTOM = 3;
    
    public BlockBOPFoliage(int blockID)
    {
        super(blockID, Material.vine);
        float f = 0.4F;
        setBurnProperties(this.blockID, 60, 100);
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
        setHardness(0.0F);
        setStepSound(Block.soundGrassFootstep);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[foliageTypes.length];
        
        for (int i = 0; i < textures.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+foliageTypes[i]);
    }
    
    @Override
    public Icon getIcon(int side, int meta)
    {
        if (meta >= textures.length)
            meta = 0;
        
        return textures[meta];
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int blockID, CreativeTabs par2CreativeTabs, List list)
    {
        for (int i = 0; i < foliageTypes.length; ++i)
            if (i != GRASSTOP)
                list.add(new ItemStack(blockID, 1, i));
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        switch (meta)
        {
            case 1:
            case 2:
            case 3:
                if (world.rand.nextInt(8) != 0)
                    return ret;
        
                ItemStack item = ForgeHooks.getGrassSeed(world);
                if (item != null)
                    ret.add(item);
                break;
                
            case 5:
                if (world.rand.nextInt(50) != 0)
                    return ret;
                
                if (world.rand.nextInt(2) == 0)
                    ret.add(new ItemStack(Item.carrot,1));
                else
                    ret.add(new ItemStack(Item.potato,1));
                break;
        }

        return ret;
    }
    
    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
        int id = world.getBlockId(x, y - 1, z);
        int meta = itemStack.getItemDamage();
        
        if (itemStack.itemID == this.blockID)
            switch (meta)
            {
                case ALGAE: // Dead Grass
                    return id == Block.waterStill.blockID;
    
                default:
                    return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID;
            }
        else
            return this.canPlaceBlockOnSide(world, x, y, z, side);
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(int id)
    {
        return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID;
    }

    protected boolean canThisPlantGrowOnThisBlockID(int blockID, int metadata)
    {
        if (metadata == GRASSTOP)
            return blockID == this.blockID;
        else if (metadata == ALGAE)
            return blockID == Block.waterStill.blockID;
        else
            return blockID == Block.grass.blockID || blockID == Block.dirt.blockID || blockID == Block.tilledField.blockID;
    }
       
    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        if (world.getBlockId(x, y, z) != this.blockID)
            return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) 
                    && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
        else
        return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) 
                && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
    }   
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
    {
        super.onNeighborBlockChange(world, x, y, z, neighborID);
        this.checkFlowerChange(world, x, y, z);
        if (world.getBlockMetadata(x, y, z) == GRASSTOP && world.getBlockId(x, y - 1, z) == this.blockID && world.getBlockMetadata(x, y - 1, z) != GRASSBOTTOM)
                world.setBlockToAir(x, y, z);
        if (world.getBlockMetadata(x, y, z) == GRASSBOTTOM && world.getBlockId(x, y + 1, z) != this.blockID)
        		world.setBlock(x, y, z, Block.tallGrass.blockID, 1, 2);
    }
	
    @Override
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity par5Entity)
    {
        int meta = par1World.getBlockMetadata(x, y, z);
        
        if (!par1World.isRemote && meta == 7 && par5Entity instanceof EntityLiving) 
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 200));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerGrass.getGrassColor(var1, var3);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
        return ColorizerFoliage.getFoliageColorBasic();
    }
    
    public int getRenderType ()
    {
        return FoliageRenderer.render;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor();
    }
    
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == GRASSTOP)
            meta = GRASSBOTTOM;
        return meta;
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return -1;
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
    {
        int meta = world.getBlockMetadata(par2, par3, par4);
        
        switch (meta)
        {
                
            case ALGAE:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
                break;
                
            default:
                this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
                break;
        }
    }
    
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        super.harvestBlock(world, player, x, y, z, meta);
    }
    
    @Override
    public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z)
    {
        return true;
    }
    
    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
    {
    	ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

    	if (world.getBlockMetadata(x, y, z) != GRASSTOP)
    		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
    	else
    		ret.add(new ItemStack(Block.tallGrass, 1, 1));

    	return ret;
    }
    
    @Override
    public boolean isBlockFoliage(World world, int x, int y, int z)
    {
        return true;
    }
}
