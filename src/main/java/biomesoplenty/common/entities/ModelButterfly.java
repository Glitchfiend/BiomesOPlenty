package biomesoplenty.common.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelButterfly extends ModelBase
{
  //fields
	public ModelRenderer Body;
    public ModelRenderer RightWing;
    public ModelRenderer LeftWing;
    public ModelRenderer Antennae;
  
    public ModelButterfly()
    {
      this.textureWidth = 64;
      this.textureHeight = 32;
      
      this.LeftWing = new ModelRenderer(this, -9, 6);
      this.LeftWing.setRotationPoint(1.0F, 23.0F, 5.0F);
      this.LeftWing.addBox(0.0F, 0.0F, 0.0F, 6, 0, 9, 0.0F);
      this.setRotation(LeftWing, -3.141592653589793F, 0.0F, 0.0F);
      this.RightWing = new ModelRenderer(this, -9, 6);
      this.RightWing.setRotationPoint(0.0F, 23.0F, 5.0F);
      this.RightWing.addBox(0.0F, 0.0F, 0.0F, 6, 0, 9, 0.0F);
      this.setRotation(RightWing, 0.0F, -3.141592653589793F, 0.0F);
      this.Antennae = new ModelRenderer(this, 10, 0);
      this.Antennae.setRotationPoint(-1.0F, 23.0F, -2.0F);
      this.Antennae.addBox(0.0F, 0.0F, 0.0F, 3, 0, 2, 0.0F);
      this.setRotation(Antennae, -3.141592653589793F, 0.0F, 0.0F);
      this.Body = new ModelRenderer(this, 0, 0);
      this.Body.setRotationPoint(0.0F, 23.0F, -2.0F);
      this.Body.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
      super.render(entity, f, f1, f2, f3, f4, f5);
      setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.LeftWing.render(f5);
      this.RightWing.render(f5);
      this.Antennae.render(f5);
      this.Body.render(f5);
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
      
      RightWing.rotateAngleZ = -(MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.2F);
      LeftWing.rotateAngleZ = MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.2F;  
  }

}