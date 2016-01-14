package thaumcraft.api.golems;

import net.minecraft.item.ItemStack;
import thaumcraft.api.golems.seals.ISealEntity;

public class ProvisionRequest { 
	private ISealEntity seal;
	private ItemStack stack;
	
	ProvisionRequest(ISealEntity seal, ItemStack stack) {
		this.seal = seal;
		this.stack = stack.copy();
		this.stack.stackSize=this.stack.getMaxStackSize();
	}
	
	public ISealEntity getSeal() {
		return seal;
	}
	
	public ItemStack getStack() {
		return stack;
	}

	@Override
	public boolean equals(Object p_equals_1_)
    {
        if (this == p_equals_1_)
        {
            return true;
        }
        else if (!(p_equals_1_ instanceof ProvisionRequest))
        {
            return false;
        }
        else
        {
        	ProvisionRequest pr = (ProvisionRequest)p_equals_1_;
            return !this.seal.getSealPos().equals(pr.getSeal().getSealPos()) ? false : this.stack.getIsItemStackEqual(pr.getStack());
        }
    }
}