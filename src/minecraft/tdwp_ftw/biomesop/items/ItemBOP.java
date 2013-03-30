package tdwp_ftw.biomesop.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemBOP extends Item
{
	public int boptextureid = 0;
	
	public ItemBOP(int id, int texture)
	{
		super(id);
		boptextureid = texture;
	}
	
	public void updateIcons(IconRegister iconRegister)
	{
    	if(boptextureid==0){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
    	else if(boptextureid==1){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudbrick"); }
    	else if(boptextureid==2){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:moss"); }
    	else if(boptextureid==3){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:ash"); }
    	else if(boptextureid==4){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:amethyst"); }
    	else if(boptextureid==5){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:staffhandle"); }
    	else if(boptextureid==6){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:staffpole"); }
    	else if(boptextureid==7){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:stafftopper"); }
    	else { iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
