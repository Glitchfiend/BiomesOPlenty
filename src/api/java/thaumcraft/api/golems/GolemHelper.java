package thaumcraft.api.golems;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.golems.seals.ISeal;
import thaumcraft.api.golems.seals.ISealEntity;
import thaumcraft.api.golems.seals.SealPos;
import thaumcraft.api.golems.tasks.Task;

public class GolemHelper {

	/**
	 * Make sure to register your seals during the preInit phase.
	 * @param seal
	 */
	public static void registerSeal(ISeal seal) {
		ThaumcraftApi.internalMethods.registerSeal(seal);
	}

	public static ISeal getSeal(String key) {
		return ThaumcraftApi.internalMethods.getSeal(key);
	}
	
	public static ItemStack getSealStack(String key) {
		return ThaumcraftApi.internalMethods.getSealStack(key);
	}

	public static ISealEntity getSealEntity(int dim, SealPos pos) {
		return ThaumcraftApi.internalMethods.getSealEntity(dim, pos);
	}
	
	public static void addGolemTask(int dim, Task task) {
		ThaumcraftApi.internalMethods.addGolemTask(dim, task);
	}
	
	public static HashMap<Integer,ArrayList<ProvisionRequest>> provisionRequests = new HashMap<Integer,ArrayList<ProvisionRequest>>();
	
	public static void requestProvisioning(World world, ISealEntity seal, ItemStack stack) {
		if (!provisionRequests.containsKey(world.provider.getDimensionId()))
			provisionRequests.put(world.provider.getDimensionId(), new ArrayList<ProvisionRequest>());
		ArrayList<ProvisionRequest> list = provisionRequests.get(world.provider.getDimensionId());
		ProvisionRequest pr = new ProvisionRequest(seal,stack);
		if (!list.contains(pr))
			list.add(pr);
	}
	
	/**
	 * This method is used to get a single blockpos from within a designated seal area.
	 * This method is best used if you want to increment through the blocks in the area. 
	 * @param seal
	 * @param count a value used to derive a specific pos 
	 * @return
	 */
	public static BlockPos getPosInArea(ISealEntity seal, int count) {
		int xx = 1 + (seal.getArea().getX()-1) * (seal.getSealPos().face.getFrontOffsetX()==0?2:1);
		int yy = 1 + (seal.getArea().getY()-1) * (seal.getSealPos().face.getFrontOffsetY()==0?2:1);
		int zz = 1 + (seal.getArea().getZ()-1) * (seal.getSealPos().face.getFrontOffsetZ()==0?2:1);
		
		int qx = seal.getSealPos().face.getFrontOffsetX()!=0?seal.getSealPos().face.getFrontOffsetX():1;
		int qy = seal.getSealPos().face.getFrontOffsetY()!=0?seal.getSealPos().face.getFrontOffsetY():1;
		int qz = seal.getSealPos().face.getFrontOffsetZ()!=0?seal.getSealPos().face.getFrontOffsetZ():1;
		
		int y = qy*((count/zz)/xx)%yy + seal.getSealPos().face.getFrontOffsetY();		
		int x = qx*(count/zz)%xx + seal.getSealPos().face.getFrontOffsetX();					
		int z = qz*count%zz + seal.getSealPos().face.getFrontOffsetZ();		
				
		BlockPos p = seal.getSealPos().pos.add(
				x - (seal.getSealPos().face.getFrontOffsetX()==0?xx/2:0), 
				y - (seal.getSealPos().face.getFrontOffsetY()==0?yy/2:0), 
				z - (seal.getSealPos().face.getFrontOffsetZ()==0?zz/2:0));
		
		return p;
	}

	
	/**
	 * Returns the designated seal area as a AxisAlignedBB
	 * @param seal
	 * @return
	 */
	public static AxisAlignedBB getBoundsForArea(ISealEntity seal) {
		return AxisAlignedBB.fromBounds(
				seal.getSealPos().pos.getX(), seal.getSealPos().pos.getY(), seal.getSealPos().pos.getZ(), 
				seal.getSealPos().pos.getX()+1, seal.getSealPos().pos.getY()+1, seal.getSealPos().pos.getZ()+1)
				.offset(
					seal.getSealPos().face.getFrontOffsetX(), 
					seal.getSealPos().face.getFrontOffsetY(), 
					seal.getSealPos().face.getFrontOffsetZ())
				.addCoord(
					seal.getSealPos().face.getFrontOffsetX()!=0?(seal.getArea().getX()-1) * seal.getSealPos().face.getFrontOffsetX():0, 
					seal.getSealPos().face.getFrontOffsetY()!=0?(seal.getArea().getY()-1) * seal.getSealPos().face.getFrontOffsetY():0, 
					seal.getSealPos().face.getFrontOffsetZ()!=0?(seal.getArea().getZ()-1) * seal.getSealPos().face.getFrontOffsetZ():0)
				.expand(
					seal.getSealPos().face.getFrontOffsetX()==0?seal.getArea().getX()-1:0,
					seal.getSealPos().face.getFrontOffsetY()==0?seal.getArea().getY()-1:0,
					seal.getSealPos().face.getFrontOffsetZ()==0?seal.getArea().getZ()-1:0 );
	}
	
	

}
