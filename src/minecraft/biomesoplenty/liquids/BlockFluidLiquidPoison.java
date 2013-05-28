package biomesoplenty.liquids;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Potions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BlockFluidLiquidPoison extends BlockFluidClassic 
{
	public static Icon liquidPoisonStillIcon;
	public static Icon liquidPoisonFlowingIcon;
	
	public BlockFluidLiquidPoison(int id, Fluid fluid, Material material) 
	{
		super(id, fluid, material);
        stack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		
		this.quantaPerBlock = 4;
        this.setLightOpacity(3);
	}
	
    @Override
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity par5Entity)
    {
        int meta = par1World.getBlockMetadata(x, y, z);
        
        if (par5Entity instanceof EntityLiving) 
        {
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.hunger.id, 100));
        }  
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) 
	{
		this.liquidPoisonStillIcon = iconRegister.registerIcon("BiomesOPlenty:liquid_poison_still");
		this.liquidPoisonFlowingIcon = iconRegister.registerIcon("BiomesOPlenty:liquid_poison_flowing");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        return par1 != 0 && par1 != 1 ? this.liquidPoisonFlowingIcon : this.liquidPoisonStillIcon;
    }
}
