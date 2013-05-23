package biomesoplenty.blocks;

import java.util.Random;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.blocks.renderers.FoliageRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPCoral extends BlockFlower
{
    private static final String[] coral = new String[] {"kelp"};
    private Icon[] textures;
    
    protected BlockBOPCoral(int blockID, Material material)
    {
        super(blockID, material);
        this.setTickRandomly(true);
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }

    public BlockBOPCoral(int blockID)
    {
        this(blockID, Material.water);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[coral.length];
        
        for (int i = 0; i < coral.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:" + coral[i]);
    }
    
    @Override
    public Icon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= textures.length)
            meta = 0;

        return textures[meta];
    }
    
    public int getRenderType ()
    {
        return 1;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < coral.length; ++i)
		{
			if (i != 14)
			{
				list.add(new ItemStack(blockID, 1, i));
			}
		}
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(int id)
    {
        return id == Block.dirt.blockID || id == Block.sand.blockID;
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
    {
    	return id == Block.dirt.blockID || id == Block.sand.blockID;
    }
    
    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
        int id = world.getBlockId(x, y - 1, z);
        int meta = itemStack.getItemDamage();
        //boolean sky = world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z);
        
        if (itemStack.itemID == this.blockID)
            return id == Block.dirt.blockID || id == Block.sand.blockID;
        else
            return this.canPlaceBlockOnSide(world, x, y, z, side);
    }
	
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
    {
        //super.onNeighborBlockChange(world, x, y, z, neighborID);
        this.checkFlowerChange(world, x, y, z);
    }
    
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        return meta;
    }	
	
    @Override
    public int damageDropped(int meta)
    {
    	return meta & 15;
    }
	
    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
		return 1;
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
    public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
    	//ItemStack itemstack = new ItemStack(Blocks.flowers.get(), 1, 10);
    	
    	if (world.getBlockMetadata(x, y, z) == 10) {
    		//if (!world.isRemote)
    			//world.spawnEntityInWorld(new EntityItem(world, x, y, z, itemstack));
    		return true;
    	}
    	return false;
    }
}
