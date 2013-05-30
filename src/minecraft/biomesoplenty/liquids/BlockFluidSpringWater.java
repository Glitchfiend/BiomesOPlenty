package biomesoplenty.liquids;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Liquids;
import biomesoplenty.api.Potions;
import biomesoplenty.ftfluidsapi.BlockFluidClassic;
import biomesoplenty.ftfluidsapi.Fluid;
import biomesoplenty.ftfluidsapi.FluidContainerRegistry;
import biomesoplenty.ftfluidsapi.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidSpringWater extends BlockFluidClassic 
{
	public static Icon springWaterStillIcon;
	public static Icon springWaterFlowingIcon;
	
	public BlockFluidSpringWater(int id, Fluid fluid, Material material) 
	{
		super(id, fluid, material);
        stack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);
		
		for (int i = 8; i < 11; i++)
		{
			this.displacementIds.put(i, false);
		}
		
		this.displacementIds.put(Liquids.liquidPoison.get().blockID, false);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.randomDisplayTick(par1World, par2, par3, par4, par5Random);

        if (par5Random.nextInt(6) == 0)
        {
            BiomesOPlenty.proxy.spawnParticle("steam", (double)((float)par2 + par5Random.nextFloat()), (double)((float)par3 + 1.0F), (double)((float)par4 + par5Random.nextFloat()));
        }
    }
	
	@Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {

        int bId = world.getBlockId(x, y, z);

        if (bId == 0) {
            return true;
        }
        if (bId == blockID) {
            return false;
        }
        if (displacementIds.containsKey(bId)) {
            return displacementIds.get(bId);
        }
        Material material = Block.blocksList[bId].blockMaterial;
        
        if (material.blocksMovement() || material == Material.water || material == Material.lava || material == Material.portal) {
        	return false;
        }
        return true;
    }
	
    @Override
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity par5Entity)
    {
        int meta = par1World.getBlockMetadata(x, y, z);
        
        if (par5Entity instanceof EntityLiving) 
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.regeneration.id, 1));
        
        if (par5Entity instanceof EntityPlayer)
        	((EntityPlayer)par5Entity).addPotionEffect(new PotionEffect(Potions.nourishment.get().id, 1));
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) 
	{
		this.springWaterStillIcon = iconRegister.registerIcon("BiomesOPlenty:spring_water_still");
		this.springWaterFlowingIcon = iconRegister.registerIcon("BiomesOPlenty:spring_water_flowing");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        return par1 != 0 && par1 != 1 ? this.springWaterFlowingIcon : this.springWaterStillIcon;
    }
}
