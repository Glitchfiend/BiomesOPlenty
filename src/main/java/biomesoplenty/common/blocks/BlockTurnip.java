package biomesoplenty.common.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import biomesoplenty.api.BOPItemHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTurnip extends BlockCrops
{
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    @Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
        if (meta < 7)
        {
            if (meta == 6)
            {
                meta = 5;
            }

            return this.iconArray[meta >> 1];
        }
        else
        {
            return this.iconArray[3];
        }
    }

    @Override
    //TODO:			getSeedItem()
    protected Item func_149866_i()
    {
        return BOPItemHelper.get("turnipSeeds");
    }

    @Override
    //TODO:		  getCropItem()
    protected Item func_149865_P()
    {
        return BOPItemHelper.get("food");
    }
    
	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta == 7 ? 11 : 0;
	}

    @Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
        this.iconArray = new IIcon[4];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
        	//TODO:																  getTextureName()
            this.iconArray[i] = iconRegister.registerIcon("biomesoplenty:" + this.func_149641_N() + "_stage_" + i);
        }
    }
}
