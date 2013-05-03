package forestry.api.core;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IIconProvider {
	
	@SideOnly(Side.CLIENT)
	Icon getIcon(short texUID);
	
	@SideOnly(Side.CLIENT)
	void registerItemIcons(IconRegister itemMap);
	@SideOnly(Side.CLIENT)
	void registerTerrainIcons(IconRegister terrainMap);

}
