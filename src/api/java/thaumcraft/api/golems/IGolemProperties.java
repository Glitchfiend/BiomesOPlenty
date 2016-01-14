package thaumcraft.api.golems;

import java.util.Set;

import net.minecraft.item.ItemStack;
import thaumcraft.api.golems.parts.GolemAddon;
import thaumcraft.api.golems.parts.GolemArm;
import thaumcraft.api.golems.parts.GolemHead;
import thaumcraft.api.golems.parts.GolemLeg;
import thaumcraft.api.golems.parts.GolemMaterial;

public interface IGolemProperties {

	public abstract Set<EnumGolemTrait> getTraits();

	public abstract boolean hasTrait(EnumGolemTrait tag);
	
	public abstract long toLong();

	public abstract ItemStack[] generateComponents();
	
	
	//material
	public abstract void setMaterial(GolemMaterial mat);

	public abstract GolemMaterial getMaterial();

	//head
	public abstract void setHead(GolemHead mat);

	public abstract GolemHead getHead();

	//arms
	public abstract void setArms(GolemArm mat);

	public abstract GolemArm getArms();

	//legs
	public abstract void setLegs(GolemLeg mat);

	public abstract GolemLeg getLegs();

	//addon
	public abstract void setAddon(GolemAddon mat);

	public abstract GolemAddon getAddon();

	//rank
	public abstract void setRank(int r);

	public abstract int getRank();

	
	
	

}