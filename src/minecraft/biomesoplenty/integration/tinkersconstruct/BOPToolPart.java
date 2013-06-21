package biomesoplenty.integration.tinkersconstruct;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mods.tinker.tconstruct.library.TConstructRegistry;
import mods.tinker.tconstruct.library.util.IToolPart;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class BOPToolPart extends BOPCraftingItem implements IToolPart
{
	public BOPToolPart(int id, String partType, String textureType)
	{
		super(id, toolMaterialNames, buildTextureNames(textureType), "parts/");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	private static String[] buildTextureNames (String textureType)
	{
		String[] names = new String[toolMaterialNames.length];
		for (int i = 0; i < toolMaterialNames.length; i++)
			names[i] = toolTextureNames[i]+textureType;
		return names;
	}
	
	public static final String[] toolMaterialNames = new String[] { "Amethyst" };
	
	public static final String[] toolTextureNames = new String[] { "amethyst" };

	@Override
	public int getMaterialID (ItemStack stack)
	{
		return 150 + stack.getItemDamage();
	}
}
