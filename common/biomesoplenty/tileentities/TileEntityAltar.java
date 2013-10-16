package biomesoplenty.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAltar extends TileEntity 
{
    private boolean apatitePresent = false;
    private boolean peridotPresent = false;
    private boolean rubyPresent = false;
    private boolean sapphirePresent = false;
    private boolean tanzanitePresent = false;
    private boolean topazPresent = false;

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
    
	@Override
    public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();

		nbt.setBoolean("apatitePresent", apatitePresent);
		nbt.setBoolean("peridotPresent", peridotPresent);
		nbt.setBoolean("rubyPresent", rubyPresent);
		nbt.setBoolean("sapphirePresent", sapphirePresent);
		nbt.setBoolean("tanzanitePresent", tanzanitePresent);
		nbt.setBoolean("topazPresent", topazPresent);

		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(INetworkManager var1, Packet132TileEntityData packet)
	{
		if (packet.actionType == 0)
		{
			this.apatitePresent = packet.data.getBoolean("apatitePresent");
			this.peridotPresent = packet.data.getBoolean("peridotPresent");
			this.rubyPresent = packet.data.getBoolean("rubyPresent");
			this.sapphirePresent = packet.data.getBoolean("sapphirePresent");
			this.tanzanitePresent = packet.data.getBoolean("tanzanitePresent");
			this.topazPresent = packet.data.getBoolean("topazPresent");
		}

		this.worldObj.markBlockForUpdate(packet.xPosition, packet.yPosition, packet.zPosition);
	}
    
    public void setPresent(int presentGem, boolean state)
    {	
    	if (presentGem == 10)
    	{
    		rubyPresent = state;
    	}
    	else if (presentGem == 11)
    	{
    		peridotPresent = state;
    	}
    	else if (presentGem == 12)
    	{
    		topazPresent = state;
    	}
    	else if (presentGem == 13)
    	{
    		tanzanitePresent = state;
    	}
    	else if (presentGem == 14)
    	{
    		apatitePresent = state;
    	}
    	else if (presentGem == 15)
    	{
    		sapphirePresent = state;
    	}
    }
    
    public boolean getPresent(int presentGem)
    {
    	if (presentGem == 10)
    	{
    		return rubyPresent;
    	}
    	else if (presentGem == 11)
    	{
    		return peridotPresent;
    	}
    	else if (presentGem == 12)
    	{
    		return topazPresent;
    	}
    	else if (presentGem == 13)
    	{
    		return tanzanitePresent;
    	}
    	else if (presentGem == 14)
    	{
    		return apatitePresent;
    	}
    	else if (presentGem == 15)
    	{
    		return sapphirePresent;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public boolean getAllPresent()
    {
    	if (rubyPresent && peridotPresent && topazPresent && tanzanitePresent && apatitePresent && sapphirePresent)
    	{
    		return true;
    	}
    	
		return false;
    }
}
