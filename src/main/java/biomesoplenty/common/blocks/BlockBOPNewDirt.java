package biomesoplenty.common.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.utils.ISubLocalization;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockBOPNewDirt extends BlockDirt implements ISubLocalization
{
	private static final String[] dirtTypes = new String[] { "loamy", "sandy", "silty" };
	private static final IIcon[] dirtIcons = new IIcon[dirtTypes.length * 2];
	
	public BlockBOPNewDirt()
	{
		this.setHardness(0.5F);
		this.setHarvestLevel("shovel", 0);
		
		this.setStepSound(soundTypeGravel);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
    @Override
	public int damageDropped(int meta)
    {
        return meta;
    }
	
	@Override
	public String getUnlocalizedName(String baseName, ItemStack itemStack) 
	{
		int meta = itemStack.getItemDamage();
		
		return baseName + "." + (isCoarseDirt(meta) ? "coarse_dirt_" : "dirt_") + getBaseType(meta);
	}
    
    @Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTab, List blockList)
    {
    	for (int i = 0; i < dirtTypes.length * 2; i++)
    	{
            blockList.add(new ItemStack(block, 1, i));
    	}
    }
	
    @Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	for (int i = 0; i < dirtTypes.length; i++)
    	{
    		String dirtType = dirtTypes[i];
    		
    		dirtIcons[i * 2 + 0] = iconRegister.registerIcon("biomesoplenty:dirt_" + dirtType);
    		dirtIcons[i * 2 + 1] = iconRegister.registerIcon("biomesoplenty:coarse_dirt_" + dirtType);
    	}
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return dirtIcons[meta];
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	
        return this.getIcon(side, meta);
    }
    
    private boolean isCoarseDirt(int meta)
    {
    	return (meta & 1) != 0;
    }
    
    private String getBaseType(int meta)
    {
    	return dirtTypes[(meta - (meta & 1)) / 2];
    }
}
