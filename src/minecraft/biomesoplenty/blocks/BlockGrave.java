package biomesoplenty.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.tileentity.TileEntityGrave;
import cpw.mods.fml.client.FMLClientHandler;

public class BlockGrave extends Block
{
	public BlockGrave(int id)
	{
		super(id, Material.rock);

		setHardness(5f);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	

    @Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
    {
        int o = ((MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
        int fO;
        
        if (o == 0 || o == 2)
        {
        	fO = 0;
        }
        else
        {
        	fO = 1;
        }
        
        if (!world.isRemote)
        {
        	world.setBlockMetadataWithNotify(x, y, z, fO, 2);
        }
    }
    
    @Override
    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
        byte b0 = 4;

        for (int j1 = 0; j1 < b0; ++j1)
        {
            for (int k1 = 0; k1 < b0; ++k1)
            {
                for (int l1 = 0; l1 < b0; ++l1)
                {
                    double d0 = (double)x + ((double)j1 + 0.5D) / (double)b0;
                    double d1 = (double)y + ((double)k1 + 0.5D) / (double)b0;
                    double d2 = (double)z + ((double)l1 + 0.5D) / (double)b0;
                    int i2 = world.rand.nextInt(6);
                    effectRenderer.addEffect(new EntityDiggingFX(world, d0, d1, d2, d0 - (double)x - 0.5D, d1 - (double)y - 0.5D, d2 - (double)z - 0.5D, Block.stone, i2, meta).func_70596_a(x, y, z));
                }
            }
        }
    	
        return true;
    }
    
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
	{
		int meta = world.getBlockMetadata(par2, par3, par4);

		switch (meta)
		{
			case 0:
				this.setBlockBounds(0.0F, 0.0F, 0.31F, 1.0F, 1.5F, 0.69F);
				break;

			case 1:
				this.setBlockBounds(0.31F, 0.0F, 0.0F, 0.69F, 1.5F, 1.0F);
				break;
		}
	}

	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityGrave();
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}
}
