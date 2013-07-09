package biomesoplenty.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.blocks.renderers.RenderUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGrave extends Block
{	
	public BlockGrave(int id)
	{
		super(id, Material.rock);

		setHardness(5f);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon("stone");
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
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
	{
		int meta = world.getBlockMetadata(par2, par3, par4);

		switch (meta)
		{
			case 0:
				this.setBlockBounds(0.0F, 0.0F, 0.31F, 1.0F, 1.625F, 0.69F);
				break;

			case 1:
				this.setBlockBounds(0.0F, -1.0F, 0.31F, 1.0F, 0.625F, 0.69F);
				break;
		}
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
	public int getRenderType()
	{
		return RenderUtils.graveModel;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return true;
	}
}
