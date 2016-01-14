package thaumcraft.api.golems.seals;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class SealPos  {
		public BlockPos pos;
		public EnumFacing face;
		
		public SealPos(BlockPos pos, EnumFacing face) {
			super();
			this.pos = pos;
			this.face = face;
		}	
		
		@Override
		public int hashCode()
	    {
	        byte b0 = (byte) (face.ordinal()+1);
	        int i = 31 * b0 + this.pos.getX();
	        i = 31 * i + this.pos.getY();
	        i = 31 * i + this.pos.getZ();
	        return i;
	    }
	
		@Override
		public boolean equals(Object p_equals_1_)
	    {
	        if (this == p_equals_1_)
	        {
	            return true;
	        }
	        else if (!(p_equals_1_ instanceof SealPos))
	        {
	            return false;
	        }
	        else
	        {
	        	SealPos sp = (SealPos)p_equals_1_;
	            return !this.pos.equals(sp.pos) ? false : this.face.equals(sp.face);
	        }
	    }
	}