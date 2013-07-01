package biomesoplenty.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGrave0 extends ModelBase
{
	//fields
	ModelRenderer Base;
	ModelRenderer Upright;
	ModelRenderer Head_Bottom;
	ModelRenderer Head_Side_1;
	ModelRenderer Head_Side_2;
	ModelRenderer Head_Top;
	ModelRenderer Head_Spike_Top;
	ModelRenderer Head_Spike_Side_1;
	ModelRenderer Head_Spike_Side_2;
	ModelRenderer Head_Cross_Horizontal;
	ModelRenderer Head_Cross_Vertical_Bottom;
	ModelRenderer Head_Cross_Vertical_Top;

	public ModelGrave0()
	{
		textureWidth = 64;
		textureHeight = 32;

		Base = new ModelRenderer(this, 0, 25);
		Base.addBox(0F, 0F, 0F, 4, 3, 4);
		Base.setRotationPoint(-2F, 21F, -2F);
		Base.setTextureSize(64, 32);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		Upright = new ModelRenderer(this, 18, 26);
		Upright.addBox(0F, 0F, 0F, 2, 4, 2);
		Upright.setRotationPoint(-1F, 17F, -1F);
		Upright.setTextureSize(64, 32);
		Upright.mirror = true;
		setRotation(Upright, 0F, 0F, 0F);
		Head_Bottom = new ModelRenderer(this, 0, 17);
		Head_Bottom.addBox(0F, 0F, 0F, 8, 2, 4);
		Head_Bottom.setRotationPoint(-4F, 15F, -2F);
		Head_Bottom.setTextureSize(64, 32);
		Head_Bottom.mirror = true;
		setRotation(Head_Bottom, 0F, 0F, 0F);
		Head_Side_1 = new ModelRenderer(this, 26, 15);
		Head_Side_1.addBox(0F, 0F, 0F, 2, 4, 4);
		Head_Side_1.setRotationPoint(-4F, 11F, -2F);
		Head_Side_1.setTextureSize(64, 32);
		Head_Side_1.mirror = true;
		setRotation(Head_Side_1, 0F, 0F, 0F);
		Head_Side_2 = new ModelRenderer(this, 26, 15);
		Head_Side_2.addBox(0F, 0F, 0F, 2, 4, 4);
		Head_Side_2.setRotationPoint(2F, 11F, -2F);
		Head_Side_2.setTextureSize(64, 32);
		Head_Side_2.mirror = true;
		setRotation(Head_Side_2, 0F, 0F, 0F);
		Head_Top = new ModelRenderer(this, 0, 17);
		Head_Top.addBox(0F, 0F, 0F, 8, 2, 4);
		Head_Top.setRotationPoint(-4F, 9F, -2F);
		Head_Top.setTextureSize(64, 32);
		Head_Top.mirror = true;
		setRotation(Head_Top, 0F, 0F, 0F);
		Head_Spike_Top = new ModelRenderer(this, 12, 10);
		Head_Spike_Top.addBox(0F, 0F, 0F, 2, 3, 2);
		Head_Spike_Top.setRotationPoint(-1F, 6F, -1F);
		Head_Spike_Top.setTextureSize(64, 32);
		Head_Spike_Top.mirror = true;
		setRotation(Head_Spike_Top, 0F, 0F, 0F);
		Head_Spike_Side_1 = new ModelRenderer(this, 0, 11);
		Head_Spike_Side_1.addBox(0F, 0F, 0F, 3, 2, 2);
		Head_Spike_Side_1.setRotationPoint(-7F, 12F, -1F);
		Head_Spike_Side_1.setTextureSize(64, 32);
		Head_Spike_Side_1.mirror = true;
		setRotation(Head_Spike_Side_1, 0F, 0F, 0F);
		Head_Spike_Side_2 = new ModelRenderer(this, 0, 11);
		Head_Spike_Side_2.addBox(0F, 0F, 0F, 3, 2, 2);
		Head_Spike_Side_2.setRotationPoint(4F, 12F, -1F);
		Head_Spike_Side_2.setTextureSize(64, 32);
		Head_Spike_Side_2.mirror = true;
		setRotation(Head_Spike_Side_2, 0F, 0F, 0F);
		Head_Cross_Horizontal = new ModelRenderer(this, 0, 7);
		Head_Cross_Horizontal.addBox(0F, 0F, 0F, 4, 1, 1);
		Head_Cross_Horizontal.setRotationPoint(-2F, 12.5F, -0.5F);
		Head_Cross_Horizontal.setTextureSize(64, 32);
		Head_Cross_Horizontal.mirror = true;
		setRotation(Head_Cross_Horizontal, 0F, 0F, 0F);
		Head_Cross_Vertical_Bottom = new ModelRenderer(this, 12, 6);
		Head_Cross_Vertical_Bottom.addBox(0F, 0F, 0F, 1, 2, 1);
		Head_Cross_Vertical_Bottom.setRotationPoint(-0.5F, 13.5F, -0.5F);
		Head_Cross_Vertical_Bottom.setTextureSize(64, 32);
		Head_Cross_Vertical_Bottom.mirror = true;
		setRotation(Head_Cross_Vertical_Bottom, 0F, 0F, 0F);
		Head_Cross_Vertical_Top = new ModelRenderer(this, 12, 6);
		Head_Cross_Vertical_Top.addBox(0F, 0F, 0F, 1, 2, 1);
		Head_Cross_Vertical_Top.setRotationPoint(-0.5F, 10.5F, -0.5F);
		Head_Cross_Vertical_Top.setTextureSize(64, 32);
		Head_Cross_Vertical_Top.mirror = true;
		setRotation(Head_Cross_Vertical_Top, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity); //add entity here
		Base.render(f5);
		Upright.render(f5);
		Head_Bottom.render(f5);
		Head_Side_1.render(f5);
		Head_Side_2.render(f5);
		Head_Top.render(f5);
		Head_Spike_Top.render(f5);
		Head_Spike_Side_1.render(f5);
		Head_Spike_Side_2.render(f5);
		Head_Cross_Horizontal.render(f5);
		Head_Cross_Vertical_Bottom.render(f5);
		Head_Cross_Vertical_Top.render(f5);
	}


	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) //Add Entity entity here
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity); //Add entity here
	}
}
