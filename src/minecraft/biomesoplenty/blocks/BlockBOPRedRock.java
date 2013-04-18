package biomesoplenty.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import biomesoplenty.mod_BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPRedRock extends Block
{
    private static final String[] types = new String[] {"redrock", "redcobble", "redbrick"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures = {null, null, null};
    
    public BlockBOPRedRock(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
        setStepSound(Block.soundStoneFootstep);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[types.length];

        for (int i = 0; i < types.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+types[i]);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= textures.length)
            meta = 0;

        return textures[meta];
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < types.length; ++i)
            list.add(new ItemStack(blockID, 1, i));
    }
    
    @Override
    public float getBlockHardness(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        float hardness = this.blockHardness;
        
        switch (meta)
        {
            case 0:
                hardness = 1.0F;
                break;
                
            case 1:
                hardness = 1.6F;
                break;

            case 2:
                hardness = 1.1F;
                break;
        }
        
        return hardness;
    }
    
    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        int meta = world.getBlockMetadata(x, y, z);
        float resistance = this.blockResistance;
        
        switch (meta)
        {
            case 1:
                resistance = 7.5F;
                break;

            case 2:
                resistance = 7.0F;
                break;
        }
        
        return resistance / 5.0F;
    }
}
