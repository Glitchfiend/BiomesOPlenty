package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import biomesoplenty.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPuddle extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon field_94441_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_94440_b;

    public BlockPuddle(int par1)
    {
        super(par1, Material.ground);
        this.setTickRandomly(true);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8135F, 1.0F);
        this.setLightOpacity(0);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)(par2 + 0), (double)(par3 + 0), (double)(par4 + 0), (double)(par2 + 1), (double)(par3 + 0.8135F), (double)(par4 + 1));
    }
    
	@Override
	public boolean isBlockNormalCube(World world, int x, int y, int z)
	{
		return false;
	}

	@Override
    public boolean isOpaqueCube()
    {
       return false;
    }

	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
	@Override
    public void updateTick(World world, int x, int y, int z, Random par5Random)
    {
    	if (!world.isRaining() && world.rand.nextInt(2) == 0)
    	{
    		world.setBlock(x, y, z, Block.dirt.blockID);
    	}
    }
    
    @Override
	//TODO:	   getRenderBlockPass()
	public int func_149701_w()
    {
    	return 1;
    }
    
    @Override
    public boolean canRenderInPass(int pass)
    {
    	ClientProxy.puddleRenderPass = pass;
    	return true;
    }
    
    @Override
    public int getRenderType()
    {
        return RenderUtils.puddleModel;
    }

    @Override
	@SideOnly(Side.CLIENT)

    public IIcon getIcon(int par1, int par2)
    {
        return Block.dirt.getBlockTextureFromSide(par1);
    }

    @Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.dirt.idDropped(0, par2Random, par3);
    }

    @Override
	@SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Block.dirt.blockID;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
    }
}
