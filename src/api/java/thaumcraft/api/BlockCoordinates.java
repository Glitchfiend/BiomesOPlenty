package thaumcraft.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class BlockCoordinates implements Comparable
{
    /** the x coordinate */
    public int x;

    /** the y coordinate */
    public int y;

    /** the z coordinate */
    public int z;

    public BlockCoordinates() {}

    public BlockCoordinates(int par1, int par2, int par3)
    {
        this.x = par1;
        this.y = par2;
        this.z = par3;
    }
    
    public BlockCoordinates(TileEntity tile)
    {
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;
    }

    public BlockCoordinates(BlockCoordinates par1ChunkCoordinates)
    {
        this.x = par1ChunkCoordinates.x;
        this.y = par1ChunkCoordinates.y;
        this.z = par1ChunkCoordinates.z;
    }

    public boolean equals(Object par1Obj)
    {
        if (!(par1Obj instanceof BlockCoordinates))
        {
            return false;
        }
        else
        {
        	BlockCoordinates coordinates = (BlockCoordinates)par1Obj;
            return this.x == coordinates.x && this.y == coordinates.y && this.z == coordinates.z ;
        }
    }

    public int hashCode()
    {
        return this.x + this.y << 8 + this.z << 16;
    }

    /**
     * Compare the coordinate with another coordinate
     */
    public int compareWorldCoordinate(BlockCoordinates par1)
    {
        return this.y == par1.y ? (this.z == par1.z ? this.x - par1.x : this.z - par1.z) : this.y - par1.y;
    }

    public void set(int par1, int par2, int par3, int d)
    {
        this.x = par1;
        this.y = par2;
        this.z = par3;
    }

    /**
     * Returns the squared distance between this coordinates and the coordinates given as argument.
     */
    public float getDistanceSquared(int par1, int par2, int par3)
    {
        float f = (float)(this.x - par1);
        float f1 = (float)(this.y - par2);
        float f2 = (float)(this.z - par3);
        return f * f + f1 * f1 + f2 * f2;
    }

    /**
     * Return the squared distance between this coordinates and the ChunkCoordinates given as argument.
     */
    public float getDistanceSquaredToWorldCoordinates(BlockCoordinates par1ChunkCoordinates)
    {
        return this.getDistanceSquared(par1ChunkCoordinates.x, par1ChunkCoordinates.y, par1ChunkCoordinates.z);
    }

    public int compareTo(Object par1Obj)
    {
        return this.compareWorldCoordinate((BlockCoordinates)par1Obj);
    }
    
    public void readNBT(NBTTagCompound nbt) {
    	this.x = nbt.getInteger("b_x");
    	this.y = nbt.getInteger("b_y");
    	this.z = nbt.getInteger("b_z");
    }
    
    public void writeNBT(NBTTagCompound nbt) {
    	nbt.setInteger("b_x",x);
    	nbt.setInteger("b_y",y);
    	nbt.setInteger("b_z",z);
    }
    
}
