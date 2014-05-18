package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.blocks.BlockBOPCoral;

public class ItemBlockCoral extends ItemBlock
{
	private IIcon[] textures;

	public ItemBlockCoral(Block block)
	{
		super(block);
		
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta < 8 ? meta + 8 : meta;
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if (field_150939_a == BOPBlockHelper.get("coral1") && metadata > 8 && metadata < 12)
		{
			metadata = world.getBlock(x, y - 1, z) == field_150939_a ? 10 : 11;
		}
		
		return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[1];

		textures[0] = iconRegister.registerIcon("biomesoplenty:item_kelp");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return field_150939_a.getIcon(0, meta);
    }

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		BlockBOPCoral block = (BlockBOPCoral)field_150939_a;
		return super.getUnlocalizedName() + "." + block.getCoralType(itemStack.getItemDamage());
	}
}
