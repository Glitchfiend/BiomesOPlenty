package thaumcraft.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * @author azanor
 *
 * Custom tile entity class I use for most of my tile entities. Setup in such a way that only 
 * the nbt data within readCustomNBT / writeCustomNBT will be sent to the client when the tile
 * updates. Apart from all the normal TE data that gets sent that is.
 * 
 */
public class TileThaumcraft extends TileEntity {
		
	//NBT stuff
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        readCustomNBT(nbttagcompound);
    }
	
	public void readCustomNBT(NBTTagCompound nbttagcompound)
    {
        //TODO
    }

	@Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        writeCustomNBT(nbttagcompound);
    }
	
	public void writeCustomNBT(NBTTagCompound nbttagcompound)
    {
		//TODO
    }
	
	//Client Packet stuff
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, -999, nbttagcompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);		
		this.readCustomNBT(pkt.func_148857_g());
	}
	
	
	

}
