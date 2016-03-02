/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWasp extends ModelBase
{
    //fields
    
    /*Head*/
    ModelRenderer Head;
    ModelRenderer Right_Antenna;
    ModelRenderer Left_Antenna;
    ModelRenderer Nose;
    
    /*Wings*/
    ModelRenderer Left_Wing;
    ModelRenderer Right_Wing;
    
    /*Body*/
    ModelRenderer Thorax;
    ModelRenderer Left_Leg_Back;
    ModelRenderer Left_Leg_Middle;
    ModelRenderer Left_Leg_Front;
    ModelRenderer Right_Leg_Back;
    ModelRenderer Right_Leg_Middle;
    ModelRenderer Right_Leg_Front;
    
    /*Stinger*/
    ModelRenderer Abdomen;
    ModelRenderer Stinger;

    public ModelWasp()
    {
        textureWidth = 64;
        textureHeight = 32;
        
        /*Head*/
        Head = new ModelRenderer(this, 46, 0);
        Head.addBox(0F, 0F, 0F, 5, 5, 4);
        Head.setRotationPoint(0F, -2F, 8F);
        Head.setTextureSize(64, 32);
        
        Right_Antenna = new ModelRenderer(this, 54, 27);
        Right_Antenna.addBox(0F, 2F, -8F, 1, 1, 4);
        Right_Antenna.setRotationPoint(3F, -3F, 10F);
        Right_Antenna.setTextureSize(64, 32);
        Right_Antenna.mirror = true;
        Head.addChild(Right_Antenna);
        
        Left_Antenna = new ModelRenderer(this, 54, 27);
        Left_Antenna.addBox(0F, 2F, -8F, 1, 1, 4);
        Left_Antenna.setRotationPoint(1F, -3F, 10F);
        Left_Antenna.setTextureSize(64, 32);
        Left_Antenna.mirror = true;
        Head.addChild(Left_Antenna);
        
        Nose = new ModelRenderer(this, 54, 9);
        Nose.addBox(0F, 2F, -8F, 3, 4, 2);
        Nose.setRotationPoint(1F, 0F, 11F);
        Nose.setTextureSize(64, 32);
        Nose.mirror = true;
        Head.addChild(Nose);
        
        /*Wings*/
        Left_Wing = new ModelRenderer(this, 24, 26);
        Left_Wing.addBox(-7F, 0F, 0F, 8, 1, 5);
        Left_Wing.setRotationPoint(0F, -1F, 2F);
        Left_Wing.setTextureSize(64, 32);

        Right_Wing = new ModelRenderer(this, 24, 20);
        Right_Wing.addBox(0F, 0F, 0F, 8, 1, 5);
        Right_Wing.setRotationPoint(4F, -1F, 2F);
        Right_Wing.setTextureSize(64, 32);

        /*Body*/
        Thorax = new ModelRenderer(this, 0, 0);
        Thorax.addBox(0F, 0F, 0F, 5, 5, 8);
        Thorax.setRotationPoint(0F, 0F, 0F);
        Thorax.setTextureSize(64, 32);
        
        Left_Leg_Back = new ModelRenderer(this, 13, 23);
        Left_Leg_Back.addBox(0F, 0F, 0F, 1, 4, 1);
        Left_Leg_Back.setRotationPoint(-1F, 4F, 1F);
        Left_Leg_Back.setTextureSize(64, 32);
        Thorax.addChild(Left_Leg_Back);
        
        Left_Leg_Middle = new ModelRenderer(this, 13, 23);
        Left_Leg_Middle.addBox(0F, 0F, 0F, 1, 3, 1);
        Left_Leg_Middle.setRotationPoint(-1F, 4F, 4F);
        Left_Leg_Middle.setTextureSize(64, 32);
        Thorax.addChild(Left_Leg_Middle);
        
        Left_Leg_Front = new ModelRenderer(this, 13, 23);
        Left_Leg_Front.addBox(0F, 0F, 0F, 1, 4, 1);
        Left_Leg_Front.setRotationPoint(-1F, 4F, 6F);
        Left_Leg_Front.setTextureSize(64, 32);
        Thorax.addChild(Left_Leg_Front);
        
        Right_Leg_Back = new ModelRenderer(this, 13, 23);
        Right_Leg_Back.addBox(0F, 0F, 0F, 1, 4, 1);
        Right_Leg_Back.setRotationPoint(5F, 4F, 1F);
        Right_Leg_Back.setTextureSize(64, 32);
        Thorax.addChild(Right_Leg_Back);
        
        Right_Leg_Middle = new ModelRenderer(this, 13, 23);
        Right_Leg_Middle.addBox(0F, 0F, 0F, 1, 3, 1);
        Right_Leg_Middle.setRotationPoint(5F, 4F, 4F);
        Right_Leg_Middle.setTextureSize(64, 32);
        Thorax.addChild(Right_Leg_Middle);
        
        Right_Leg_Front = new ModelRenderer(this, 13, 23);
        Right_Leg_Front.addBox(0F, 0F, 0F, 1, 4, 1);
        Right_Leg_Front.setRotationPoint(5F, 4F, 6F);
        Right_Leg_Front.setTextureSize(64, 32);
        Thorax.addChild(Right_Leg_Front);
        
        /*Stinger*/
        Abdomen = new ModelRenderer(this, 0, 13);
        Abdomen.addBox(0F, 0F, 0F, 3, 3, 2);
        Abdomen.setRotationPoint(1F, 2F, -2F);
        Abdomen.setTextureSize(64, 32);

        Stinger = new ModelRenderer(this, 0, 18);
        Stinger.addBox(-1F, -3F, 2F, 1, 1, 3);
        Stinger.setRotationPoint(2F, 4F, -5F);
        Stinger.setTextureSize(64, 32);
        Abdomen.addChild(Stinger);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        
        /*Head*/
        Head.render(f5);
        
        /*Wings*/        
        Left_Wing.render(f5);
        Right_Wing.render(f5);
        
        /*Body*/        
        Thorax.render(f5);
        
        /*float nosespeed = 0.5F * (float)(entity.entityId % 10);
        Nose.rotateAngleX = MathHelper.sin((float)entity.ticksExisted * nosespeed) * 2.5F * (float)Math.PI / 180.0F;
        Nose.rotateAngleY = 0.0F;
        Nose.rotateAngleZ = MathHelper.cos((float)entity.ticksExisted * nosespeed) * 1.5F * (float)Math.PI / 180.0F;*/
        
        /*Stinger*/
        Abdomen.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity); 
        
        float headspeed = 0.1F * (float)(entity.ticksExisted % 10);
        Head.rotateAngleX = MathHelper.sin(f2 * headspeed) * 2.5F * (float)Math.PI / 180.0F;
        Head.rotateAngleZ = MathHelper.cos(f2 * headspeed) * 1.5F * (float)Math.PI / 180.0F;
        
        float thoraxspeed = 0.075F * (float)(entity.ticksExisted % 10);
        Thorax.rotateAngleX = MathHelper.sin(f2 * thoraxspeed) * 2.5F * (float)Math.PI / 180.0F;
        Thorax.rotateAngleZ = MathHelper.cos(f2 * thoraxspeed) * 1.5F * (float)Math.PI / 180.0F;
        
        Right_Wing.rotateAngleY = MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.25F;
        Left_Wing.rotateAngleY = -Right_Wing.rotateAngleY;  
        Right_Wing.rotateAngleZ = Right_Wing.rotateAngleY;
        Left_Wing.rotateAngleZ = -Right_Wing.rotateAngleY;
        
        float abdomenspeed = 0.6F * (float)(entity.ticksExisted % 10);
        Abdomen.rotateAngleX = MathHelper.sin(f2 * abdomenspeed) * 2.5F * (float)Math.PI / 180.0F;
        Abdomen.rotateAngleZ = MathHelper.cos(f2 * abdomenspeed) * 1.5F * (float)Math.PI / 180.0F;
    }
}