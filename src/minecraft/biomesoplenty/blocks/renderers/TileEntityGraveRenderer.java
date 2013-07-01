package biomesoplenty.blocks.renderers;
 
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import biomesoplenty.blocks.models.ModelGrave0;
import biomesoplenty.tileentity.TileEntityGrave;
import cpw.mods.fml.client.FMLClientHandler;
 
public class TileEntityGraveRenderer extends TileEntitySpecialRenderer
{
    private ModelGrave0 modelGrave = new ModelGrave0();
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z,float tick)
    {
    	World world = tileentity.worldObj;

    	int meta = world.getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
    			
    	if (meta == 0)
    	{
            renderModelAt((TileEntityGrave)tileentity, x, y, z, tick, 0.0F);
    	}
    	else
    	{
            renderModelAt((TileEntityGrave)tileentity, x, y, z, tick, 1.0F);
    	}
    }
    
    public void renderModelAt(TileEntityGrave tileentity1, double x, double y, double z, float ticks, float rotation)
    {          
    	//The PushMatrix tells the renderer to "start" doing something.
    	GL11.glPushMatrix();
    	
    	//This is setting the initial location.
    	GL11.glTranslatef((float) x + 0.5F, (float) y + 2.25F, (float) z + 0.5F);
    	
    	//This is the texture of your block. It's pathed to be the same place as your other blocks here.
    	FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/BiomesOPlenty/textures/models/grave.png");
    	
    	//This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
    	GL11.glPushMatrix();
    	GL11.glRotatef(180F, rotation, 0.0F, 1.0F);
    	GL11.glScalef(1.5F, 1.5F, 1.5F);
    	
    	//A reference to your Model file. Again, very important.
    	modelGrave.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    	
    	//Tell it to stop rendering for both the PushMatrix's
    	GL11.glPopMatrix();
    	GL11.glPopMatrix();
    }
}
