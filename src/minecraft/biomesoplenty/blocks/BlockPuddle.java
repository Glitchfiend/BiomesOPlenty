package biomesoplenty.blocks;

import java.util.Random;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.blocks.renderers.PuddleRender;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
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
    
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 1);
    	
        return par9;
    }
    
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        Material material1 = par1World.getBlockMaterial(par2 + 1, par3, par4);
        Material material2 = par1World.getBlockMaterial(par2 - 1, par3, par4);
        Material material3 = par1World.getBlockMaterial(par2, par3, par4 + 1);
        Material material4 = par1World.getBlockMaterial(par2, par3, par4 - 1);
        
        if (!material1.isSolid() || !material2.isSolid() || !material3.isSolid() || !material1.isSolid())
        {
            par1World.setBlock(par2, par3, par4, Block.dirt.blockID);
        }
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
    

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        Material material = par1World.getBlockMaterial(par2, par3 + 1, par4);

        if (material.isSolid())
        {
            par1World.setBlock(par2, par3, par4, Block.dirt.blockID);
        }
        
        par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 1);
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
