package biomesoplenty.integration.tinkersconstruct;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BOPToolShard extends BOPToolPart
{
	public BOPToolShard(int id, String part, String tex)
	{
		super(id, part, tex);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	private static String[] buildTextureNames(String textureType)
    {
        String[] names = new String[toolMaterialNames.length];
        for (int i = 0; i < toolMaterialNames.length; i++)
            names[i] = toolTextureNames[i]+textureType;
        return names;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.icons = new Icon[textureNames.length];

        for (int i = 0; i < textureNames.length; ++i)
        {
            this.icons[i] = iconRegister.registerIcon("biomesoplenty:"+folder+textureNames[i]);
        }
    }
	
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list)
	{
		for (int i = 0; i < toolMaterialNames.length; i++)
			list.add(new ItemStack(id, 1, i));
	}
}
