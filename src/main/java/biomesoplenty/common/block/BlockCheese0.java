package biomesoplenty.common.block;

import net.minecraft.block.properties.PropertyEnum;

public class BlockCheese0 extends BlockCheese
{
    
    public static final int PAGENUM = 0;
    
    // create a static reference to this block's variant property
    // this is for convenience, and for consistency with other Block classes
    public static final PropertyEnum VARIANT = BlockCheese.getVariantProperty(PAGENUM);
    
    @Override
    public int getPageNum() {return PAGENUM;}

}
