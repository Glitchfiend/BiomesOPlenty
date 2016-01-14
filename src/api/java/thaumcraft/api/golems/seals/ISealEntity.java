package thaumcraft.api.golems.seals;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface ISealEntity {

	public void tickSealEntity(World world);

	public ISeal getSeal();

	public SealPos getSealPos();

	public byte getPriority();

	public void setPriority(byte priority);

	public void readNBT(NBTTagCompound nbt);

	public NBTTagCompound writeNBT();

	public void syncToClient(World world);

	public BlockPos getArea();

	public void setArea(BlockPos v);

	boolean isLocked();

	void setLocked(boolean locked);

	String getOwner();

	void setOwner(String owner);

}