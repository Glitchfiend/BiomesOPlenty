package biomesoplenty.liquids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import biomesoplenty.BiomesOPlenty;

public class BlockSpringWaterStill extends BlockStationary 
{
    public BlockSpringWaterStill(int id) 
    {
        super(id, Material.water);
        
        this.blockHardness = 100F;
        this.setLightOpacity(3);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
        this.disableStats();
    }
    
    @Override
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity par5Entity)
    {
        int meta = par1World.getBlockMetadata(x, y, z);
        
        if (par5Entity instanceof EntityLiving) 
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.regeneration.id, 1));
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) 
	{
		this.theIcon = new Icon[]{iconRegister.registerIcon("BiomesOPlenty:spring_water_still"), iconRegister.registerIcon("BiomesOPlenty:spring_water_flowing")};
	}
}
