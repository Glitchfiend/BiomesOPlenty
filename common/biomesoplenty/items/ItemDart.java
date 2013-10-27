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
	    int baseHeight = 7 + world.rand.nextInt(2);
	    
	    for (int cubeno = 0; cubeno < 3; cubeno++)
	    {
	        float chance = 0.0F;
	        
	        switch (cubeno)
	        {
	            case 0:
	                chance = 0.25F;
	                break;
	                
	            case 1:
	                chance = 1.0F;
	                break;
	                
	            case 2:
	                chance = 0.25F;
	                break;
	        }
	        
	        generateHiveCube(world, x, y + cubeno, z, baseHeight + (cubeno * 2), baseWidth + cubeno, chance);
	    }

	    return itemstack;
	}
	
	public void generateHiveCube(World world, int origx, int origy, int origz, int height, int width, float chance)
	{
        for (int hLayer = 0; hLayer < height; hLayer++)
        {     
            for (int i = -width; i < width; i++)
            {
                for (int j = -width; j < width; j++)
                {
                    if ((hLayer == 0 || hLayer == (height - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID); 
                    else if ((i == -width || i == (width - 1) || j == -width || j == (width - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, Blocks.hive.get().blockID);
                    
                    if (world.getBlockId(origx + i, origy - hLayer, origz + j) != Blocks.hive.get().blockID) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
                }
            }
        }
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
