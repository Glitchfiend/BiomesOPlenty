package biomesoplenty.tileentity;

import java.lang.reflect.Field;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityAltar extends TileEntity 
{
    public boolean apatitePresent = false;
    public boolean peridotPresent = false;
    public boolean rubyPresent = false;
    public boolean sapphirePresent = false;
    public boolean tanzanitePresent = false;
    public boolean topazPresent = false;

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
    	super.readFromNBT(nbt);
        this.apatitePresent = nbt.getBoolean("apatitePresent");
        this.peridotPresent = nbt.getBoolean("peridotPresent");
        this.rubyPresent = nbt.getBoolean("rubyPresent");
        this.sapphirePresent = nbt.getBoolean("sapphirePresent");
        this.tanzanitePresent = nbt.getBoolean("tanzanitePresent");
        this.topazPresent = nbt.getBoolean("topazPresent");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
    	super.writeToNBT(nbt);
    	nbt.setBoolean("apatitePresent", apatitePresent);
    	nbt.setBoolean("peridotPresent", peridotPresent);
    	nbt.setBoolean("rubyPresent", rubyPresent);
    	nbt.setBoolean("sapphirePresent", sapphirePresent);
    	nbt.setBoolean("tanzanitePresent", tanzanitePresent);
    	nbt.setBoolean("topazPresent", topazPresent);
    }
    
    public void setPresent(String presentGem, boolean state)
    {	
    	if (presentGem == "apatite")
    	{
    		apatitePresent = state;
    	}
    	else if (presentGem == "peridot")
    	{
    		peridotPresent = state;
    	}
    	else if (presentGem == "ruby")
    	{
    		rubyPresent = state;
    	}
    	else if (presentGem == "sapphire")
    	{
    		sapphirePresent = state;
    	}
    	else if (presentGem == "tanzanite")
    	{
    		tanzanitePresent = state;
    	}
    	else if (presentGem == "topaz")
    	{
    		topazPresent = state;
    	}
    }
    
    public boolean getPresent(String presentGem)
    {
    	if (presentGem == "apatite")
    	{
    		return apatitePresent;
    	}
    	else if (presentGem == "peridot")
    	{
    		return peridotPresent;
    	}
    	else if (presentGem == "ruby")
    	{
    		return rubyPresent;
    	}
    	else if (presentGem == "sapphire")
    	{
    		return sapphirePresent;
    	}
    	else if (presentGem == "tanzanite")
    	{
    		return tanzanitePresent;
    	}
    	else if (presentGem == "topaz")
    	{
    		return topazPresent;
    	}

    	return false;
    }
}
