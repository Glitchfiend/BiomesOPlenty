package biomesoplenty.blocks;

import java.util.List;

import biomesoplenty.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPLog extends Block 
{
    public static enum LogCategory
    {
      CAT1, CAT2, CAT3;
    }
    
    private static final String[] woodTypes = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow", "dead"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    private Icon[] logHearts;
    
    private final LogCategory category;
    
    public BlockBOPLog(int blockID, LogCategory cat)
    {
        super(blockID, Material.wood);
        category = cat;
        setBurnProperties(this.blockID, 5, 5);
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(Block.soundWoodFootstep);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[woodTypes.length];
        logHearts = new Icon[woodTypes.length];

        for (int i = 0; i < woodTypes.length; ++i)
        {
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+woodTypes[i]+"log");
            logHearts[i] = iconRegister.registerIcon("BiomesOPlenty:logTopBottum");
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        int pos = meta & 12;
        if (pos == 0 && (side == 1 || side == 0) || pos == 4 && (side == 5 || side == 4) || pos == 8 && (side == 2 || side == 3))
            return logHearts[(getTypeFromMeta(meta) + this.category.ordinal() * 4)];
        return textures[(getTypeFromMeta(meta) + this.category.ordinal() * 4)];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < 4; ++i)
            if (category != LogCategory.CAT3 || i < 3)
//                return;
//            else
                list.add(new ItemStack(this, 1, i));
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
        byte radius = 4;
        int bounds = radius + 1;

        if (world.checkChunksExist(x - bounds, y - bounds, z - bounds, x + bounds, y + bounds, z + bounds))
            for (int i = -radius; i <= radius; ++i)
                for (int j = -radius; j <= radius; ++j)
                    for (int k = -radius; k <= radius; ++k)
                    {
                        int blockID = world.getBlockId(x + i, y + j, z + k);

                        if (Block.blocksList[blockID] != null)
                            Block.blocksList[blockID].beginLeavesDecay(world, x + i, y + j, z + k);
                    }
    }
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        int type = getTypeFromMeta(meta);
        byte orientation = 0;

        switch (side)
        {
            case 0:
            case 1:
                orientation = 0;
                break;

            case 2:
            case 3:
                orientation = 8;
                break;

            case 4:
            case 5:
                orientation = 4;
        }

        return type | orientation;
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return getTypeFromMeta(meta);
    }
    
    @Override
    protected ItemStack createStackedBlock(int meta)
    {
        return new ItemStack(this.blockID, 1, getTypeFromMeta(meta));
    }
    
    @Override
    public int getRenderType()
    {
        return 31;
    }
    
    @Override
    public boolean canSustainLeaves(World world, int x, int y, int z)
    {
      return true;
    }

    @Override
    public boolean isWood(World world, int x, int y, int z)
    {
      return true;
    }
    
    public int getWoodType(int meta)
    {
      return getTypeFromMeta(meta) + category.ordinal() * 4;
    }
    
    private static int getTypeFromMeta(int meta)
    {
      return meta & 3;
    }
}
