package biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderSnowball;

import org.lwjgl.opengl.GL11;

import biomesoplenty.api.Items;
import biomesoplenty.blocks.renderers.FoliageRenderer;
import biomesoplenty.blocks.renderers.PlantsRenderer;
import biomesoplenty.blocks.renderers.SmallBlockRenderer;
import biomesoplenty.ftfluidsapi.FluidRegistry;
import biomesoplenty.ftfluidsapi.RenderBlockFluid;
import biomesoplenty.items.projectiles.EntityDart;
import biomesoplenty.items.projectiles.EntityMudball;
import biomesoplenty.items.projectiles.RenderDart;
import biomesoplenty.mobs.EntityGlob;
import biomesoplenty.mobs.RenderGlob;
import biomesoplenty.particles.EntityDandelionFX;
import biomesoplenty.particles.EntitySteamFX;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	  public static Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void registerRenderers() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(Items.mudball.get(), 0)); 
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart()); 
		//RenderingRegistry.registerEntityRenderingHandler(EntityPoisonDart.class, new RenderPoisonDart()); 
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGlob.class, new RenderGlob(new ModelSlime(16), new ModelSlime(0), 0.25F)); 
		
		RenderingRegistry.registerBlockHandler(new FoliageRenderer());
		RenderingRegistry.registerBlockHandler(new PlantsRenderer());
		RenderingRegistry.registerBlockHandler(new SmallBlockRenderer());
		
		//TODO: Remove upon Fluid API being integrated into Forge
		FluidRegistry.renderIdFluid = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(RenderBlockFluid.instance);
	}
	
	@Override
	public void spawnParticle(String string, double x, double y, double z)
	{
        EntityFX entityfx = null;
        
         if (string == "mud")
             entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.mudball.get(), mc.renderEngine);
         else if (string == "dart")
             entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.dart.get(), mc.renderEngine);
         else if (string == "dandelion")
             entityfx = new EntityDandelionFX(mc.theWorld, x, y, z, 2.0F);
         
		mc.effectRenderer.addEffect(entityfx);
	} 

	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	@Override
    public void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

}