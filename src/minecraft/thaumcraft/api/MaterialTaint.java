package thaumcraft.api;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialTaint extends Material
{
    public MaterialTaint(MapColor par1MapColor)
    {
        super(par1MapColor);
        this.setNoPushMobility();
        this.setRequiresTool();
    }

    @Override
    public boolean isSolid()
    {
        return false;
    }
    
    @Override
    public boolean isReplaceable()
    {
        return false;
    }

    /**
     * Will prevent grass from growing on dirt underneath and kill any grass below it if it returns true
     */
    @Override
    public boolean getCanBlockGrass()
    {
        return false;
    }

    /**
     * Returns if this material is considered solid or not
     */
    @Override
    public boolean blocksMovement()
    {
        return true;
    }

	@Override
	protected Material setRequiresTool() {
		// TODO Auto-generated method stub
		return super.setRequiresTool();
	}

	@Override
	public int getMaterialMobility() {
		// TODO Auto-generated method stub
		return super.getMaterialMobility();
	}
    
    
}
