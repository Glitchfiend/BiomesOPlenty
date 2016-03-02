package biomesoplenty.common.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSnail extends ModelBase
{
  //fields
	ModelRenderer Shell;
    ModelRenderer Body;
    ModelRenderer Left_Eye;
    ModelRenderer Right_Eye;
  
    public ModelSnail()
    {
      textureWidth = 64;
      textureHeight = 32;
      
      Shell = new ModelRenderer(this, 0, 0);
      Shell.addBox(-1F, 0F, -3F, 3, 8, 8);
      Shell.setRotationPoint(0F, 14F, 0F);
      Shell.setTextureSize(64, 32);
      Shell.mirror = true;
      setRotation(Shell, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 16);
      Body.addBox(0F, 0F, 0F, 3, 2, 12);
      Body.setRotationPoint(-1F, 22F, -6F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Left_Eye = new ModelRenderer(this, 22, 12);
      Left_Eye.addBox(0F, 0F, 0F, 1, 3, 1);
      Left_Eye.setRotationPoint(1F, 19F, -5F);
      Left_Eye.setTextureSize(64, 32);
      Left_Eye.mirror = true;
      setRotation(Left_Eye, 0F, 0F, 0F);
      Right_Eye = new ModelRenderer(this, 22, 12);
      Right_Eye.addBox(0F, 0F, 0F, 1, 3, 1);
      Right_Eye.setRotationPoint(-1F, 19F, -5F);
      Right_Eye.setTextureSize(64, 32);
      Right_Eye.mirror = true;
      setRotation(Right_Eye, 0F, 0F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
    	super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shell.render(f5);
        Body.render(f5);
        Left_Eye.render(f5);
        Right_Eye.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
    }
  
  @Override
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity); 
  }

}