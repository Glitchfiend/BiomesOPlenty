package biomesoplenty.blocks;

import java.util.Random;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.ClientProxy;
import biomesoplenty.blocks.renderers.PuddleRender;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPuddle extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon field_94441_a;
    @SideOnly(Side.CLIENT)
    private Icon field_94440_b;

    public BlockPuddle(int par1)
    {
        super(par1, Material.ground);
        this.setTickRandomly(true);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8135F, 1.0F);
        this.setLightOpacity(0);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)(par2 + 0), (double)(par3 + 0), (double)(par4 + 0), (double)(par2 + 1), (double)(par3 + 0.8135F), (double)(par4 + 1));
    }

    public boolean isOpaqueCube()
    {
       return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public void updateTick(World world, int x, int y, int z, Random par5Random)
    {
    	if (!world.isRaining() && world.rand.nextInt(750) == 0)
    	{
    		world.setBlock(x, y, z, Block.dirt.blockID);
    	}
    }
    
    @Override
    public int getRenderBlockPass()
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
        return PuddleRender.puddleID;
    }

    @SideOnly(Side.CLIENT)

    public Icon getIcon(int par1, int par2)
    {
        return Block.dirt.getBlockTextureFromSide(par1);
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.dirt.idDropped(0, par2Random, par3);
    }

    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Block.dirt.blockID;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    }
}
