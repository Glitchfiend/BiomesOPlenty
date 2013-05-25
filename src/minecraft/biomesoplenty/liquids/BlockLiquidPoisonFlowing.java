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

public class BlockLiquidPoisonFlowing extends BlockFlowing 
{
    public BlockLiquidPoisonFlowing(int id) 
    {
        super(id, Material.water);
        
        this.blockHardness = 100F;
        this.setLightOpacity(3);
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
        {
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 100));
        }  
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) 
	{
		this.theIcon = new Icon[]{iconRegister.registerIcon("BiomesOPlenty:liquid_poison_still"), iconRegister.registerIcon("BiomesOPlenty:liquid_poison_flowing")};
	}
}
