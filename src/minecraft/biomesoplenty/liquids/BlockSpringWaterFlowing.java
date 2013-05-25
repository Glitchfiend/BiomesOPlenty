package biomesoplenty.liquids;

import net.minecraft.block.BlockFlowing;
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
import biomesoplenty.api.Potions;
import biomesoplenty.configuration.BOPPotions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpringWaterFlowing extends BlockFlowing 
{
    public BlockSpringWaterFlowing(int id) 
    {
        super(id, Material.water);
        
        this.blockHardness = 100F;
        this.setLightOpacity(0);
    }
    
    @Override
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
    	return 16777215;
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
		this.theIcon = new Icon[]{iconRegister.registerIcon("BiomesOPlenty:spring_water_still"), iconRegister.registerIcon("BiomesOPlenty:spring_water_flowing")};
	}
}
