package biomesoplenty.common.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTurnip extends BlockCrops
{
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    @Override
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 < 7)
        {
            if (par2 == 6)
            {
                par2 = 5;
            }

            return this.iconArray[par2 >> 1];
        }
        else
        {
            return this.iconArray[3];
        }
    }

    @Override
    protected int getSeedItem()
    {
        return Items.turnipseeds.get().itemID;
    }

    @Override
    protected int getCropItem()
    {
        return Items.food.get().itemID;
    }
    
	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta == 7 ? 11 : 0;
	}

    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.iconArray = new IIcon[4];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon("biomesoplenty:" + this.getTextureName() + "_stage_" + i);
        }
    }
}
