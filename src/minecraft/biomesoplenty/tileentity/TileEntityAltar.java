package biomesoplenty.tileentity;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
        //if (this.worldObj.isRemote)
        //{
            if (packet.actionType == 0)
            {
                this.apatitePresent = packet.customParam1.getBoolean("apatitePresent");
                this.peridotPresent = packet.customParam1.getBoolean("peridotPresent");
                this.rubyPresent = packet.customParam1.getBoolean("rubyPresent");
                this.sapphirePresent = packet.customParam1.getBoolean("sapphirePresent");
                this.tanzanitePresent = packet.customParam1.getBoolean("tanzanitePresent");
                this.topazPresent = packet.customParam1.getBoolean("topazPresent");
            }
        //}
        
		this.worldObj.markBlockForUpdate(packet.xPosition, packet.yPosition, packet.zPosition);
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
    	else
    	{
    		return false;
    	}
    }
}
