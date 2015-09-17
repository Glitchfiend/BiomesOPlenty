package biomesoplenty.common.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import biomesoplenty.api.content.BOPCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTurnip extends BlockCrops
{
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
    
    public BlockTurnip()
    {
    	this.setBlockTextureName("turnip");
    }

    @Override
	public IIcon getIcon(int side, int meta)
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
    protected Item func_149866_i()
    {
        return BOPCItems.turnipSeeds;
    }

    @Override
    protected Item func_149865_P()
    {
        return BOPCItems.food;
    }
    
	@Override
	public int damageDropped(int meta)
	{
		return meta == 7 ? 11 : 0;
	}

    @Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
        this.iconArray = new IIcon[4];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = iconRegister.registerIcon("biomesoplenty:" + this.getTextureName() + "_stage_" + i);
        }
    }
}
