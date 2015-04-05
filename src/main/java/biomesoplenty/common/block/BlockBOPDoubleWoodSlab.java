package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPWoodEnums.EightWoods;
import biomesoplenty.api.block.IBOPBlock;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;


public class BlockBOPDoubleWoodSlab extends BlockBOPHalfWoodSlab implements IBOPBlock
{
    
    // HALF isn't used in double slab because both halves are present
    @Override
    public IProperty[] getRenderProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public String getStateName(IBlockState state)
    {
        return "double_" + ((EightWoods) state.getValue(VARIANT)).map(this.pageNum).toString() + "_wood_slab";
    }   
    
    public BlockBOPDoubleWoodSlab(int pageNum)
    {
        super(pageNum);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EightWoods.values()[meta & 7]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EightWoods) state.getValue(VARIANT)).ordinal();
    }

    @Override
    public boolean isDouble() {
        return true;
    }
}