package thaumcraft.api.golems.seals;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.golems.EnumGolemTrait;
import thaumcraft.api.golems.IGolemAPI;
import thaumcraft.api.golems.tasks.Task;

public interface ISeal {
	
	/**
	 * @return
	 * A unique string identifier for this seal. A good idea would be to append your modid before the identifier. 
	 * For example: "thaumcraft:fetch"
	 * This will also be used to create the item model for the seal placer so you will have to define a json using 
	 * the key with "seal_" added to the front of the key.
	 * For example: models/item/seal_fetch.json 
	 */
	public String getKey();	
	
	public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side);
	
	public void tickSeal(World world, ISealEntity seal);		
	
	public void onTaskStarted(World world, IGolemAPI golem, Task task);
	
	public boolean onTaskCompletion(World world, IGolemAPI golem, Task task);	
	
	public void onTaskSuspension(World world, Task task);
	
	public boolean canGolemPerformTask(IGolemAPI golem, Task task);
	
	public void readCustomNBT(NBTTagCompound nbt);
	
	public void writeCustomNBT(NBTTagCompound nbt);
	
	/**
	 * @return icon used to render the seal in world. Usually the same as your seal placer item icon.
	 * If it is not the same you will have to manually stitch it into the texture atlas.
	 */
	public ResourceLocation getSealIcon();

	public void onRemoval(World world, BlockPos pos, EnumFacing side);	
	
	public Object returnContainer(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal);
	
	@SideOnly(Side.CLIENT)
	public Object returnGui(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal);
	
	public EnumGolemTrait[] getRequiredTags();
	
	public EnumGolemTrait[] getForbiddenTags();
	
}
