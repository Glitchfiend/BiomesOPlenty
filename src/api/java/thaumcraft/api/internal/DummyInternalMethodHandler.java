package thaumcraft.api.internal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.golems.seals.ISeal;
import thaumcraft.api.golems.seals.ISealEntity;
import thaumcraft.api.golems.seals.SealPos;
import thaumcraft.api.golems.tasks.Task;

public class DummyInternalMethodHandler implements IInternalMethodHandler {

	@Override
	public boolean isResearchComplete(String username, String researchkey) {
		return false;
	}	

	@Override
	public AspectList getObjectAspects(ItemStack is) {
		return null;
	}

	@Override
	public AspectList generateTags(Item item, int meta) {
		return null;
	}

	@Override
	public boolean consumeVisFromWand(ItemStack wand, EntityPlayer player,
			AspectList cost, boolean doit, boolean crafting) {
		return false;
	}

	@Override
	public boolean consumeVisFromInventory(EntityPlayer player, AspectList cost) {
		return false;
	}

	@Override
	public void addWarpToPlayer(EntityPlayer player, int amount, EnumWarpType type) {
	}
	
	@Override
	public int getPlayerWarp(EntityPlayer player, EnumWarpType type) {
		return 0;	 	
	}


	@Override
	public void markRunicDirty(Entity entity) { 		
	}

	@Override
	public boolean completeResearch(EntityPlayer player, String researchkey) {
		return false;
	}

	

	@Override
	public boolean drainAura(World world, BlockPos pos, Aspect aspect, int amount) {
		return false;
	}

	@Override
	public void addAura(World world, BlockPos pos, Aspect aspect, int amount) {
		
	}

	@Override
	public void pollute(World world, BlockPos pos, int amount, boolean showEffect) {	}

	@Override
	public int getAura(World world, BlockPos pos, Aspect aspect) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAuraBase(World world, BlockPos pos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int drainAuraAvailable(World world, BlockPos pos, Aspect aspect,
			int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void registerSeal(ISeal seal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISeal getSeal(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISealEntity getSealEntity(int dim, SealPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addGolemTask(int dim, Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean shouldPreserveAura(World world, EntityPlayer player,
			BlockPos pos, Aspect aspect) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getSealStack(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
