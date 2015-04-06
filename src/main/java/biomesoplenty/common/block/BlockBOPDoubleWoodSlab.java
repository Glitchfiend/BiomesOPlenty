package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPWoodEnums.AllWoods;
import biomesoplenty.api.block.IBOPBlock;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;


public abstract class BlockBOPDoubleWoodSlab extends BlockBOPHalfWoodSlab implements IBOPBlock
{
    
    // HALF isn't used in double slab because both halves are present
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] {HALF}; }
    @Override
    public String getStateName(IBlockState state)
    {
        return "double_" + super.getStateName(state);
    } 
    
    public BlockBOPDoubleWoodSlab()
    {
        super();
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(getMyVariantProperty(), variantFromMeta(meta & 7));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return metaFromVariant((AllWoods) state.getValue(getMyVariantProperty()));
    }

    @Override
    public boolean isDouble() {
        return true;
    }
}