package biomesoplenty.blocks.renderers;

import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import biomesoplenty.blocks.models.ModelGrave0;
import cpw.mods.fml.client.FMLClientHandler;

public class ItemGraveRenderer implements IItemRenderer
{
    private ModelGrave0 modelGrave;
     
    public ItemGraveRenderer()
    {
        modelGrave = new ModelGrave0();
    }
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }
     
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }
     
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch(type)
        {
            case ENTITY:
            {
                renderGrave(-0.5f, 1.5f, -0.5f, 2.5f);
                return;
            }
             
            case EQUIPPED:
            {
                renderGrave(0f, 2.5f, 0f, 3f);
                return;
            }
            
            case EQUIPPED_FIRST_PERSON:
            {
                renderGrave(0f, 1.25f, 0f, 2f);
                return;
            }
                 
            case INVENTORY:
            {
                renderGrave(0f, 0f, 0f, 1.45f);
                return;
            }
             
            default:return;
        }
    }
    
    private void renderGrave(float x, float y, float z, float scale)
    {
    	//The PushMatrix tells the renderer to "start" doing something.
    	GL11.glPushMatrix();
    	//This is setting the initial location.
    	GL11.glTranslatef((float) x + 0.5F, (float) y + 1.80F, (float) z + 0.5F);
    	//This is the texture of your block. It's pathed to be the same place as your other blocks here.
    	FMLClientHandler.instance().getClient().renderEngine.func_110577_a(new ResourceLocation("/mods/BiomesOPlenty/textures/models/grave.png"));
    	//This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
    	GL11.glPushMatrix();
    	GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
    	GL11.glScalef(scale, scale, scale);
    	//A reference to your Model file. Again, very important.
    	modelGrave.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    	//Tell it to stop rendering for both the PushMatrix's
    	GL11.glPopMatrix();
    	GL11.glPopMatrix();    
    }
}

