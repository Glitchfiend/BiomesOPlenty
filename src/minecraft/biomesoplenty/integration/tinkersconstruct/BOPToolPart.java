package biomesoplenty.integration.tinkersconstruct;

import mods.tinker.tconstruct.library.util.IToolPart;
import net.minecraft.item.ItemStack;

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
