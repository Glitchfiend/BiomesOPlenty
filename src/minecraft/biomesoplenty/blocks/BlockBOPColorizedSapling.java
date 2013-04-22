package biomesoplenty.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.worldgen.WorldGenAcacia;
import biomesoplenty.worldgen.WorldGenMangrove;
import biomesoplenty.worldgen.WorldGenPalmTree1;
import biomesoplenty.worldgen.WorldGenPalmTree3;
import biomesoplenty.worldgen.WorldGenRedwoodTree2;
import biomesoplenty.worldgen.WorldGenWillow;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPColorizedSapling extends BlockSapling
{
    private static final String[] saplings = new String[] {"acacia", "mangrove", "palm", "redwood", "willow"};
    private Icon[] textures;
    private static final int TYPES = 15;
    
    public BlockBOPColorizedSapling(int par1)
    {
        super(par1);
        setHardness(0.0F);
        setStepSound(Block.soundGrassFootstep);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[saplings.length];
        
        for (int i = 0; i < saplings.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:" + saplings[i] + "sapling");

    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= saplings.length)
            meta = 0;

        return textures[meta];
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < saplings.length; ++i)
            list.add(new ItemStack(blockID, 1, i));
    }
    
    @Override
    public void growTree(World world, int x, int y, int z, Random random)
    {
        int meta = world.getBlockMetadata(x, y, z) & TYPES;
        Object obj = null;
        int rnd = random.nextInt(8);

        if (obj == null)
        {
            switch (meta)
            {
                case 0: // Acacia Tree
                    obj = new WorldGenAcacia(false);
                    break;
                    
                case 1: // Mangrove Tree
                    obj = new WorldGenMangrove(false);
                    break;
                    
                case 2: // Palm Tree
                    rnd = random.nextInt(4);
                    
                    if (rnd == 0)
                        obj = new WorldGenPalmTree1();
                    else
                        obj = new WorldGenPalmTree3();
                    break;
                    
                case 3: // Redwood Tree
                    obj = new WorldGenRedwoodTree2(false);
                    break;
                    
                case 4: // Willow Tree
                    obj = new WorldGenWillow();
                    break;
            }
        }

        if (obj != null)
        {
            world.setBlockToAir(x, y, z);

            if (!((WorldGenerator)obj).generate(world, random, x, y, z))
                world.setBlock(x, y, z, this.blockID, meta, 2);
        }
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta & TYPES;
    }
    
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) & TYPES;
    }
}
