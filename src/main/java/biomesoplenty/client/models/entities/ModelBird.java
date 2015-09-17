package biomesoplenty.client.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBird extends ModelBase
{
  //fields
    ModelRenderer Body;
    ModelRenderer Stomach;
    ModelRenderer Head;
    ModelRenderer Beak;
    ModelRenderer TuftBottom;
    ModelRenderer TuftTop;
    ModelRenderer Tail;
    ModelRenderer WingRight;
    ModelRenderer WingLeft;
    ModelRenderer LegLeft;
    ModelRenderer LegRight;
  
  public ModelBird()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Body = new ModelRenderer(this, 0, 0);
      Body.addBox(0F, 0F, 0F, 6, 3, 10);
      Body.setRotationPoint(-3F, 20F, -5F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Stomach = new ModelRenderer(this, 0, 13);
      Stomach.addBox(0F, 0F, 0F, 4, 1, 8);
      Stomach.setRotationPoint(-2F, 23F, -4F);
      Stomach.setTextureSize(64, 32);
      Stomach.mirror = true;
      setRotation(Stomach, 0F, 0F, 0F);
      Head = new ModelRenderer(this, 0, 22);
      Head.addBox(0F, 0F, 0F, 4, 3, 4);
      Head.setRotationPoint(-2F, 19F, -9F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Beak = new ModelRenderer(this, 0, 29);
      Beak.addBox(0F, 0F, 0F, 2, 1, 1);
      Beak.setRotationPoint(-1F, 21F, -10F);
      Beak.setTextureSize(64, 32);
      Beak.mirror = true;
      setRotation(Beak, 0F, 0F, 0F);
      TuftBottom = new ModelRenderer(this, 28, 23);
      TuftBottom.addBox(0F, 0F, 0F, 2, 1, 3);
      TuftBottom.setRotationPoint(-1F, 18F, -7F);
      TuftBottom.setTextureSize(64, 32);
      TuftBottom.mirror = true;
      setRotation(TuftBottom, 0F, 0F, 0F);
      TuftTop = new ModelRenderer(this, 20, 27);
      TuftTop.addBox(0F, 0F, 0F, 4, 0, 5);
      TuftTop.setRotationPoint(-2F, 18F, -5F);
      TuftTop.setTextureSize(64, 32);
      TuftTop.mirror = true;
      setRotation(TuftTop, 0F, 0F, 0F);
      Tail = new ModelRenderer(this, 42, 0);
      Tail.addBox(0F, 0F, 0F, 4, 0, 7);
      Tail.setRotationPoint(-2F, 21F, 5F);
      Tail.setTextureSize(64, 32);
      Tail.mirror = true;
      setRotation(Tail, 0F, 0F, 0F);

      WingLeft = new ModelRenderer(this, 36, 13);
      WingLeft.addBox(0F, 0F, 0F, 8, 0, 6);
      WingLeft.setRotationPoint(3F, 20F, -4F);
      WingLeft.setTextureSize(64, 32);
      
      WingRight = new ModelRenderer(this, 36, 7);
      WingRight.addBox(-8F, 0F, 0F, 8, 0, 6);
      WingRight.setRotationPoint(-3F, 20F, -4F);
      WingRight.setTextureSize(64, 32);

      LegLeft = new ModelRenderer(this, 33, 0);
      LegLeft.addBox(0F, 0F, 0F, 1, 1, 3);
      LegLeft.setRotationPoint(2F, 23F, 0F);
      LegLeft.setTextureSize(64, 32);
      LegLeft.mirror = true;
      setRotation(LegLeft, 0F, 0F, 0F);
      LegRight = new ModelRenderer(this, 33, 0);
      LegRight.addBox(0F, 0F, 0F, 1, 1, 3);
      LegRight.setRotationPoint(-3F, 23F, 0F);
      LegRight.setTextureSize(64, 32);
      LegRight.mirror = true;
      setRotation(LegRight, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Body.render(f5);
    Stomach.render(f5);
    Head.render(f5);
    Beak.render(f5);
    TuftBottom.render(f5);
    TuftTop.render(f5);
    Tail.render(f5);
    WingRight.render(f5);
    WingLeft.render(f5);
    LegLeft.render(f5);
    LegRight.render(f5);
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
    
    int x = MathHelper.floor_double(entity.posX);
    int y = MathHelper.floor_double(entity.posY);
    int z = MathHelper.floor_double(entity.posZ);
    
    if (!(entity.posY <= entity.prevPosY && entity.worldObj.isAirBlock(x, y - 1, z)))
    {
	    WingRight.rotateAngleZ = MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.25F;
	    WingLeft.rotateAngleZ = -WingRight.rotateAngleZ;  
    }
    else
    {
	    WingRight.rotateAngleZ = 0;
	    WingLeft.rotateAngleZ = 0;  
    }
  }

}
