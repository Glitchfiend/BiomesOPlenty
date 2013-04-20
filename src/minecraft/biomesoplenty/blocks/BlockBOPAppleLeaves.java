package biomesoplenty.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockBOPAppleLeaves extends BlockLeavesBase implements IShearable
{
    @SideOnly(Side.CLIENT)
    private Icon[][] textures;
    
    public BlockBOPAppleLeaves(int blockID)
    {
        super(blockID, Material.leaves, false);
        setBurnProperties(this.blockID, 30, 60);
        this.setTickRandomly(true);
        setHardness(0.2F);
        setLightOpacity(1);
        setStepSound(Block.soundGrassFootstep);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[2][4];
        
        for (int i = 0; i < 4; ++i)
        {
            textures[0][i] = iconRegister.registerIcon("BiomesOPlenty:appleleaves" + i + "_fancy");
            textures[1][i] = iconRegister.registerIcon("BiomesOPlenty:appleleaves" + i + "_fast");
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return textures[(!isOpaqueCube() ? 0 : 1)][meta & 7];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(blockID, 1, 0));        
    }
    
    @Override
    public void updateTick (World world, int x, int y, int z, Random random)
    {
        if (world.isRemote)
            return;

//        if (random1.nextInt(20) == 0 && world.getBlockLightValue(x, y, z) >= 8)
//        {
            int meta = world.getBlockMetadata(x, y, z);
            if ((meta & 3) < 3)
                world.setBlock(x, y, z, blockID, meta + 1, 3);
//        }
    }
    
    @Override
    public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (world.isRemote)
            return false;

        int meta = world.getBlockMetadata(x, y, z);
        if ((meta & 3) == 3)
        {
            world.setBlock(x, y, z, blockID, meta - 3, 3);
            EntityItem entityitem = new EntityItem(world, player.posX, player.posY - 1.0D, player.posZ, new ItemStack(Item.appleRed, 1, 0));
            world.spawnEntityInWorld(entityitem);
            entityitem.onCollideWithPlayer(player);
            return true;
        }
        else
            return false;
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Blocks.saplings.get().blockID;
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return 0;
    }
    
    @Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(20) == 0 ? 1 : 0;
    }
    
//    @Override
//    public int getDamageValue(World par1World, int par2, int par3, int par4)
//    {
//        return par1World.getBlockMetadata(par2, par3, par4) / 4;
//    }
    
    @Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z) 
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) 
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, 0));
        return ret;
    }
    
    @SideOnly(Side.CLIENT)
    public void setGraphicsLevel(boolean par1)
    {
        this.graphicsLevel = par1;
    }
    
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
}
