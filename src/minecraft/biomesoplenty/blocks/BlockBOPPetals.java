package biomesoplenty.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biomesoplenty.mod_BiomesOPlenty;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockBOPPetals extends BlockLeavesBase implements IShearable
{
    private static final String[] petals = new String[] {"bigflowerred", "bigfloweryellow"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    
    public BlockBOPPetals(int blockID)
    {
        super(blockID, Material.leaves, false);
        setBurnProperties(this.blockID, 30, 60);
        this.setTickRandomly(true);
        setHardness(0.2F);
        setLightOpacity(1);
        setStepSound(Block.soundGrassFootstep);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[petals.length];
        
        for (int i = 0; i < petals.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:" + petals[i]);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= textures.length)
            meta = 0;

        return textures[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < textures.length; ++i)
            list.add(new ItemStack(blockID, 1, i));
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        if (par1 == 0)
            return Block.plantRed.blockID;
        else
            return Block.plantYellow.blockID;
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta & 15;
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
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 15));
        return ret;
    }
}
