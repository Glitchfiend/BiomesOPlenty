package biomesoplenty.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDart extends Item
{
	private static final String[] dartTypes = new String[] {"dart", "poisondart"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemDart(int par1)
	{
		super(par1);
		setHasSubtypes(true);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
        MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);
        if (pos == null) return itemstack;
	    
        int x = pos.blockX;
        int y = pos.blockY;
        int z = pos.blockZ;
        
	    int baseWidth = 2 + world.rand.nextInt(2);
	    int baseHeight = 10 + world.rand.nextInt(2);
	    
	    for (int wLayer = 0; wLayer < 3; wLayer ++)
	    {
	        for (int hLayer = 0; hLayer < baseHeight; hLayer++)
	        {     
	            int width = (baseWidth + wLayer);
	            if (hLayer > 0 && hLayer < (baseHeight - 1)) width++;

	            for (int i = -width; i < width; i++)
	            {
	                for (int j = -width; j < width; j++)
	                {
	                    if (hLayer == 0)  
	                    {

	                        switch (wLayer)
	                        {
	                            case 0:
	                                if (world.rand.nextInt(2) == 0) world.setBlock(x + i, y, z + j, Blocks.hive.get().blockID);
	                                break;

	                            case 1:
	                                world.setBlock(x + i, y, z + j, Blocks.hive.get().blockID);
	                                break;

	                            case 2:
	                                if (world.rand.nextInt(2) == 0) world.setBlock(x + i, y, z + j, Blocks.hive.get().blockID);
	                                break;
	                        }
	                    }
	                    else if (hLayer == baseHeight - 1) 
	                    {
	                        switch (wLayer)
	                        {
	                            case 0:
	                                if (world.rand.nextInt(2) == 0) world.setBlock(x + i, y + (baseHeight - 1), z + j, Blocks.hive.get().blockID);
	                                break;
	                                
	                            case 1:
	                                world.setBlock(x + i, y + (baseHeight - 1), z + j, Blocks.hive.get().blockID);
	                                break;
	                                
	                            case 2:
	                                if (world.rand.nextInt(2) == 0) world.setBlock(x + i, y + (baseHeight - 1), z + j, Blocks.hive.get().blockID);
	                                break;
	                        }
	                    }
	                    else
	                    {
	                        if (i == -width || i == (width - 1) || j == -width || j == (width - 1))
	                        {
	                            switch (wLayer)
	                            {
	                                case 0:
	                                    if (world.rand.nextInt(2) == 0) world.setBlock(x + i, y + hLayer, z + j, Blocks.hive.get().blockID);
	                                    break;
	                                    
	                                case 1:
	                                    world.setBlock(x + i, y + hLayer, z + j, Blocks.hive.get().blockID);
	                                    break;
	                                    
	                                case 2:
	                                    if (world.rand.nextInt(2) == 0) world.setBlock(x + i, y + hLayer, z + j, Blocks.hive.get().blockID);
	                                    break;
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }

	    return itemstack;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < dartTypes.length; ++i)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= dartTypes.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + dartTypes[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[dartTypes.length];

		for (int i = 0; i < dartTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + dartTypes[i]);
		}
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return textures[meta];
	}
}
