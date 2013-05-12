package biomesoplenty.blocks;

import biomesoplenty.BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPStairs extends BlockStairs
{
    public static enum Category
    {
      ACACIA, CHERRY, DARK, FIR, HOLY, MAGIC, MANGROVE, PALM, REDWOOD, WILLOW, RED_COBBLE, RED_BRICKS, MUD_BRICKS, HOLY_COBBLE, HOLY_BRICKS;
    }
    
    private static final String[] types = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow", "redcobble", "redbrick", "mudbrick", "holycobble", "holybrick"};
    private Icon[] textures;
    private final Category category;

    public BlockBOPStairs(int blockID, Block model, Category cat)
    {
        super(blockID, model, 0);
        category = cat;
        setBurnProperties(this.blockID, 5, 20);
        this.setLightOpacity(0);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[types.length];
        
        for (int i = 0; i < types.length; ++i)
            if (i < types.length - 3)
                textures[i] = iconRegister.registerIcon("BiomesOPlenty:plank_"+types[i]);
            else
                textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+types[i]);
        
    }
    
    @Override
    public Icon getIcon(int side, int meta)
    {
        return textures[category.ordinal()];
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}
