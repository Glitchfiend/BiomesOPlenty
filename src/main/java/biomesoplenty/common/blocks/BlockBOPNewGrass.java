package biomesoplenty.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.client.render.RenderUtils;
import biomesoplenty.common.utils.ISubLocalization;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockBOPNewGrass extends BlockGrass implements ISubLocalization
{
	private static final String[] grassTypes = new String[] { "loamy", "sandy", "silty" };
	private static final IIcon[] grassIcons = new IIcon[grassTypes.length * 2];
	
	public BlockBOPNewGrass()
	{
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

    @Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
	public int getRenderType()
	{
		return RenderUtils.newGrassModel;
	}
	
	@Override
	public String getUnlocalizedName(String baseName, ItemStack itemStack) 
	{
		return baseName + "." + grassTypes[itemStack.getItemDamage()];
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	for (int i = 0; i < grassTypes.length; i++)
    	{
    		String grassType = grassTypes[i];
    		
    		grassIcons[i * 2 + 0] = iconRegister.registerIcon("biomesoplenty:grass_" + grassType + "_side");
    		grassIcons[i * 2 + 1] = iconRegister.registerIcon("biomesoplenty:grass_" + grassType + "_side_snowed");
    	}
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? Blocks.grass.getIcon(side, meta) : grassIcons[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	
    	if (side == 1)
    	{
            return Blocks.grass.getIcon(world, x, y, z, side);
    	}
    	else
    	{
            Material material = world.getBlock(x, y + 1, z).getMaterial();
            return material != Material.snow && material != Material.craftedSnow ? grassIcons[meta] : grassIcons[meta + 1];
    	}
    }

}
