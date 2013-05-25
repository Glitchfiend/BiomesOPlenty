package biomesoplenty.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPLog extends Block 
{
    public static enum LogCategory
    {
      CAT1, CAT2, CAT3, CAT4;
    }
    
    private static final String[] types = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow", "dead", "bigflowerstem", "pine", "hellbark", "jacaranda"};
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
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[types.length];
        logHearts = new Icon[types.length];

        for (int i = 0; i < types.length; ++i)
        {
        	if (i != 11)
        	{
        		textures[i] = iconRegister.registerIcon("BiomesOPlenty:log_"+types[i]+"_side");
        		logHearts[i] = iconRegister.registerIcon("BiomesOPlenty:log_"+types[i]+"_heart");
        	}
        }
        
        textures[11] = iconRegister.registerIcon("BiomesOPlenty:bigflowerstem_side");
        logHearts[11] = iconRegister.registerIcon("BiomesOPlenty:bigflowerstem_heart");
    }
    
    @Override
    public Icon getIcon(int side, int meta)
    {
        int pos = meta & 12;
    	if (pos == 0 && (side == 1 || side == 0) || pos == 4 && (side == 5 || side == 4) || pos == 8 && (side == 2 || side == 3))
    		return logHearts[(getTypeFromMeta(meta) + this.category.ordinal() * 4)];
    	else
    		return textures[(getTypeFromMeta(meta) + this.category.ordinal() * 4)];
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        if (category != LogCategory.CAT4)
        {
        	for (int i = 0; i < 4; ++i)
        		list.add(new ItemStack(this, 1, i));
        }
        else
        {
        	for (int i = 0; i < 3; ++i)
        		list.add(new ItemStack(this, 1, i));
        }
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
    
    public String getWoodType(int meta)
    {
  		return types[getTypeFromMeta(meta) + category.ordinal() * 4];
    }
    
    private static int getTypeFromMeta(int meta)
    {
      return meta & 3;
    }
}
