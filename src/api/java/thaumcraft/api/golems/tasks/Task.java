package thaumcraft.api.golems.tasks;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import thaumcraft.api.golems.GolemHelper;
import thaumcraft.api.golems.IGolemAPI;
import thaumcraft.api.golems.seals.ISealEntity;
import thaumcraft.api.golems.seals.SealPos;

public class Task {

	private UUID golemUUID;
	private int id;	
	private byte type;
	private SealPos sealPos;	
	private BlockPos pos;	
	private Entity entity; 
	private boolean reserved;
	private boolean suspended;
	private boolean completed;
	private int data; 
	/**
	 * Lifespan in seconds. Default 120 seconds
	 */
	private short lifespan;
	private byte priority=0;
	
	private Task() {}

	public Task(SealPos sealPos, BlockPos pos) {
		this.sealPos = sealPos;
		this.pos = pos;
		if (sealPos==null) {
			this.id = (System.currentTimeMillis()+"/BNPOS/"+pos.toString()).hashCode();
		} else
			this.id = (System.currentTimeMillis()+"/B/"+sealPos.face.toString()+"/"+sealPos.pos.toString()+"/"+pos.toString()).hashCode();
		this.type = 0;
		this.lifespan = 300;
	}
	
	public Task(SealPos sealPos, Entity entity) {
		this.sealPos = sealPos;
		this.entity = entity;
		if (sealPos==null) {
			this.id = (System.currentTimeMillis()+"/ENPOS/"+pos.toString()).hashCode();
		} else
			this.id = (System.currentTimeMillis()+"/E/"+sealPos.face.toString()+"/"+sealPos.pos.toString()+"/"+entity.getEntityId()).hashCode();
		this.type = 1;
		this.lifespan = 300;
	}	

	public byte getPriority() {
		return priority;
	}

	public void setPriority(byte priority) {
		this.priority = priority;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompletion(boolean fulfilled) {
		this.completed = fulfilled;
		this.lifespan += 60;
	}

	public UUID getGolemUUID() {
		return golemUUID;
	}

	public void setGolemUUID(UUID golemUUID) {
		this.golemUUID = golemUUID;
	}

	public BlockPos getPos() {
		return type==1?entity.getPosition():pos;
	}	
	
	public byte getType() {
		return type;
	}

	public Entity getEntity() {
		return entity;
	}

	public int getId() {
		return id;
	}
	
	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean res) {
		this.reserved = res;
		this.lifespan += 60;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public SealPos getSealPos() {
		return sealPos;
	}

	public boolean equals(Object o)
    {
        if (!(o instanceof Task))
        {
            return false;
        }
        else
        {
        	Task t = (Task)o;
            return t.id == this.id;
        }
    }

	public long getLifespan() {
		return lifespan;
	}
	
	public void setLifespan(short ls) {
		this.lifespan = ls;
	}

	public boolean canGolemPerformTask(IGolemAPI golem) {
		ISealEntity se = GolemHelper.getSealEntity(golem.getGolemWorld().provider.getDimensionId(), this.sealPos);
		if (se!=null) {
			return se.getSeal().canGolemPerformTask(golem,this);
		} else {
			return true;
		}
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}		
	
	
	
//	public static Task readNBT(NBTTagCompound nbt)
//  {		
//		Task task = new Task();
//		task.id = nbt.getInteger("id");
//		task.type = nbt.getByte("type");		
//		if (nbt.hasKey("pos", 4)) task.pos = BlockPos.fromLong(nbt.getLong("pos"));	
//		
//		if (nbt.hasKey("GUUIDMost", 4) && nbt.hasKey("GUUIDLeast", 4))
//			task.golemUUID = new UUID(nbt.getLong("GUUIDMost"), nbt.getLong("GUUIDLeast"));
//		
//		if (nbt.hasKey("EUUIDMost", 4) && nbt.hasKey("EUUIDLeast", 4))
//			task.entityUUID = new UUID(nbt.getLong("EUUIDMost"), nbt.getLong("EUUIDLeast"));
//		
//		if (task.pos==null && task.entityUUID==null) return null;
//		
//		task.reserved = nbt.getBoolean("reserved");
//		task.waitOnSuspension = nbt.getBoolean("wos");
//		task.suspended = false;
//		task.completed = nbt.getBoolean("completed");
//		task.expireTime = System.currentTimeMillis() + 300000;		
//		if (nbt.hasKey("sealpos", 10)) {
//			NBTTagCompound sealpos = nbt.getCompoundTag("sealpos");
//			SealPos sp = new SealPos(BlockPos.fromLong(nbt.getLong("pos")), EnumFacing.VALUES[nbt.getByte("face")]);
//			TaskHandler.sealTaskCrossRef.put(task.id, sp);
//		}
//		return task;
//  }
//	
//	public static NBTTagCompound writeNBT(Task task)
//  {
//		NBTTagCompound nbt = new NBTTagCompound();
//		nbt.setInteger("id", task.id);
//		nbt.setByte("type", task.type);
//		if (task.pos!=null) nbt.setLong("pos", task.pos.toLong());
//		if (task.entity!=null) {
//			nbt.setLong("EUUIDMost", task.entity.getUniqueID().getMostSignificantBits());
//          nbt.setLong("EUUIDLeast", task.entity.getUniqueID().getLeastSignificantBits());
//		}
//		if (task.golemUUID!=null) {
//			nbt.setLong("GUUIDMost", task.golemUUID.getMostSignificantBits());
//          nbt.setLong("GUUIDLeast", task.golemUUID.getLeastSignificantBits());
//		}
//		nbt.setBoolean("reserved", task.reserved);
//		nbt.setBoolean("wos", task.waitOnSuspension);
//		nbt.setBoolean("completed", task.completed);
//		
//		SealPos sp = TaskHandler.sealTaskCrossRef.get(task.getId());
//		if (sp!=null) {
//			NBTTagCompound sealpos = new NBTTagCompound();
//			sealpos.setLong("pos", sp.pos.toLong());
//			sealpos.setByte("face", (byte) sp.face.ordinal());
//			nbt.setTag("sealpos", sealpos);
//		}
//		return nbt;
//  }

}