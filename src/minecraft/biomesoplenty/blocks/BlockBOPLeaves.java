package biomesoplenty.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPLeaves extends BlockLeavesBase implements IShearable
{
    public static enum LeafCategory
    {
      CAT1, CAT2;
    }
    
    private static final String[] leaves = new String[] {"autumn", "bamboo", "blue", "dark", "dead", "fir", "holy", "orange", "origin", "pink", "red", "white"};
    @SideOnly(Side.CLIENT)
    private Icon[][] textures;
    private final LeafCategory category;
    
    public BlockBOPLeaves(int blockID, LeafCategory cat)
    {
        super(blockID, Material.leaves, false);
        category = cat;
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
        textures = new Icon[2][leaves.length];
        
        for (int i = 0; i < leaves.length; ++i)
        {
            textures[0][i] = iconRegister.registerIcon("BiomesOPlenty:" + leaves[i] + "leaves1");
            textures[1][i] = iconRegister.registerIcon("BiomesOPlenty:" + leaves[i] + "leaves2");
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return textures[(!isOpaqueCube() ? 0 : 1)][getTypeFromMeta(meta) + (this.category.ordinal() * 8)];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < 8; ++i)
            if (category != LeafCategory.CAT2 || i < 4)
                list.add(new ItemStack(blockID, 1, i));
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Blocks.saplings.get().blockID;
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return (getTypeFromMeta(meta) + this.category.ordinal() * 8) + 1;
    }
    
    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return getTypeFromMeta(par1World.getBlockMetadata(par2, par3, par4));
    }
    
    @Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(20) == 0 ? 1 : 0;
    }
    
    @Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z) 
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) 
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, getTypeFromMeta(world.getBlockMetadata(x, y, z))));
        return ret;
    }
    
    public String getLeafType(int meta)
    {
      return leaves[getTypeFromMeta(meta) + category.ordinal() * 8];
    }
    
    private static int getTypeFromMeta(int meta)
    {
      return meta & 7;
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
