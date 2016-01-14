package thaumcraft.api.golems.parts;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.golems.IGolemAPI;

/**
 * 
 * This class is used to define a model used by a part. The model needs to be in the .obj format and you may have to provide a texture. 
 * You can specify which model parts use your texture and which should use the material texture.
 * 
 * You can also specify an attachment point for the entire model. 
 * 	HANDS will render the model twice - one at the end of each arm.
 * 	LEGS will render the model twice (once for each leg) and will apply the normal walking animation tranforms to them
 * 	BODY and HEAD should be fairly obvious
 *
 */
public class PartModel {
	
	private ResourceLocation objModel;
	private ResourceLocation texture;	
	private EnumAttachPoint attachPoint;
	
	public enum EnumAttachPoint { ARMS, LEGS, BODY, HEAD; }
	
	public PartModel(ResourceLocation objModel, ResourceLocation objTexture, EnumAttachPoint attachPoint) {
		this.objModel = objModel;
		this.texture = objTexture;
		this.attachPoint = attachPoint;
	}

	public ResourceLocation getObjModel() {
		return objModel;
	}

	public ResourceLocation getTexture() {
		return texture;
	}

	public EnumAttachPoint getAttachPoint() {
		return attachPoint;
	}

	/**
	 * Returns true if you want this named part of your model to use the golem material texture instead you the part texture. You 
	 * will obviously need to make sure your model fits the material texture template.
	 * By default it simply checks if the object name starts with "bm", but obviously you can do your own thing if you override this.
	 * @param partName the obj model part name
	 * @return
	 */
	public boolean useMaterialTextureForObjectPart(String partName) {
		return partName.startsWith("bm");
	}
	
	/**
	 * This method will be called just before a specific part is rendered allowing you to apply custom transforms to it if you wish.
	 * @param partName the obj model part name
	 */
	public void preRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, EnumLimbSide side) {
		
	}
	
	/**
	 * This method will be called just after a specific part is rendered. Used for cleanup mostly.
	 * @param partName the obj model part name
	 */
	public void postRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, EnumLimbSide side) {
		
	}

	public enum EnumLimbSide {
		LEFT,RIGHT,MIDDLE;
	}
}
