package biomesoplenty.helpers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.PortalPosition;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import biomesoplenty.api.Blocks;

public class TeleporterPromised extends Teleporter
{
	private final WorldServer worldServerInstance;
	private final LongHashMap field_85191_c = new LongHashMap();
	@SuppressWarnings("rawtypes")
	private final List field_85190_d = new ArrayList();
	private final boolean falling;

	public TeleporterPromised(WorldServer worldServer)
	{
		super(worldServer);
		falling = false;
		worldServerInstance = worldServer;
	}

	public TeleporterPromised(WorldServer worldServer, boolean fall)
	{
		super(worldServer);
		falling = fall;
		worldServerInstance = worldServer;
	}

	@Override
	public void placeInPortal(Entity par1Entity, double x, double y, double z, float par8)
	{
		if (!falling)
		{
			if (!this.placeInExistingPortal(par1Entity, x, y, z, par8))
			{
				this.makePortal(par1Entity);
				this.placeInExistingPortal(par1Entity, x, y, z, par8);
			}
		}
		else
		{
			par1Entity.setLocationAndAngles(x, 256.0, z, par1Entity.rotationYaw, 0.0F);
			par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean placeInExistingPortal(Entity par1Entity, double x, double y, double z, float par8)
	{
		short var9 = 128;
		double var10 = -1.0D;
		int i = 0;
		int j = 0;
		int k = 0;
		int var15 = MathHelper.floor_double(par1Entity.posX);
		int var16 = MathHelper.floor_double(par1Entity.posZ);
		long var17 = ChunkCoordIntPair.chunkXZ2Int(var15, var16);
		boolean var19 = true;
		double var27;
		int var48;

		if (field_85191_c.containsItem(var17))
		{
			PortalPosition portalposition = (PortalPosition) field_85191_c.getValueByKey(var17);
			var10 = 0.0D;
			i = portalposition.posX;
			j = 131;
			k = portalposition.posZ;
			portalposition.lastUpdateTime = worldServerInstance.getTotalWorldTime();
			var19 = false;
		}
		else
		{
			for (var48 = var15 - var9; var48 <= var15 + var9; ++var48)
			{
				double var21 = var48 + 0.5D - par1Entity.posX;

				for (int var23 = var16 - var9; var23 <= var16 + var9; ++var23)
				{
					double var24 = var23 + 0.5D - par1Entity.posZ;

					for (int var26 = worldServerInstance.getActualHeight() - 1; var26 >= 0; --var26)
					{
						if (worldServerInstance.getBlockId(var48, var26, var23) == Blocks.promisedPortal.get().blockID)
						{
							while (worldServerInstance.getBlockId(var48, var26 - 1, var23) == Blocks.promisedPortal.get().blockID)
							{
								--var26;
							}

							var27 = var26 + 0.5D - par1Entity.posY;
							double var29 = var21 * var21 + var27 * var27 + var24 * var24;

							if (var10 < 0.0D || var29 < var10)
							{
								var10 = var29;
								i = var48;
								j = var26;
								k = var23;
							}
						}
					}
				}
			}
		}

		if (var10 >= 0.0D)
		{
			if (var19)
			{
				field_85191_c.add(var17, new PortalPosition(this, i, j, k, worldServerInstance.getTotalWorldTime()));
				field_85190_d.add(Long.valueOf(var17));
			}

			double var49 = i + 0.5D;
			double var25 = j + 0.5D;
			var27 = k + 0.5D;
			int var50 = -1;

			if (worldServerInstance.getBlockId(i - 1, j, k) == Blocks.promisedPortal.get().blockID)
			{
				var50 = 2;
			}

			if (worldServerInstance.getBlockId(i + 1, j, k) == Blocks.promisedPortal.get().blockID)
			{
				var50 = 0;
			}

			if (worldServerInstance.getBlockId(i, j, k - 1) == Blocks.promisedPortal.get().blockID)
			{
				var50 = 3;
			}

			if (worldServerInstance.getBlockId(i, j, k + 1) == Blocks.promisedPortal.get().blockID)
			{
				var50 = 1;
			}

			int var30 = par1Entity.getTeleportDirection();

			if (var50 > -1)
			{
				int var31 = Direction.rotateLeft[var50];
				int var32 = Direction.offsetX[var50];
				int var33 = Direction.offsetZ[var50];
				int var34 = Direction.offsetX[var31];
				int var35 = Direction.offsetZ[var31];
				boolean var36 = !worldServerInstance.isAirBlock(i + var32 + var34, j, k + var33 + var35) || !worldServerInstance.isAirBlock(i + var32 + var34, j + 1, k + var33 + var35);
				boolean var37 = !worldServerInstance.isAirBlock(i + var32, j, k + var33) || !worldServerInstance.isAirBlock(i + var32, j + 1, k + var33);

				if (var36 && var37)
				{
					var50 = Direction.rotateOpposite[var50];
					var31 = Direction.rotateOpposite[var31];
					var32 = Direction.offsetX[var50];
					var33 = Direction.offsetZ[var50];
					var34 = Direction.offsetX[var31];
					var35 = Direction.offsetZ[var31];
					var48 = i - var34;
					var49 -= var34;
					int var22 = k - var35;
					var27 -= var35;
					var36 = !worldServerInstance.isAirBlock(var48 + var32 + var34, j, var22 + var33 + var35) || !worldServerInstance.isAirBlock(var48 + var32 + var34, j + 1, var22 + var33 + var35);
					var37 = !worldServerInstance.isAirBlock(var48 + var32, j, var22 + var33) || !worldServerInstance.isAirBlock(var48 + var32, j + 1, var22 + var33);
				}

				float var38 = 0.5F;
				float var39 = 0.5F;

				if (!var36 && var37)
				{
					var38 = 1.0F;
				}
				else if (var36 && !var37)
				{
					var38 = 0.0F;
				}
				else if (var36 && var37)
				{
					var39 = 0.0F;
				}

				var49 += var34 * var38 + var39 * var32;
				var27 += var35 * var38 + var39 * var33;
				float var40 = 0.0F;
				float var41 = 0.0F;
				float var42 = 0.0F;
				float var43 = 0.0F;

				if (var50 == var30)
				{
					var40 = 1.0F;
					var41 = 1.0F;
				}
				else if (var50 == Direction.rotateOpposite[var30])
				{
					var40 = -1.0F;
					var41 = -1.0F;
				}
				else if (var50 == Direction.rotateRight[var30])
				{
					var42 = 1.0F;
					var43 = -1.0F;
				}
				else
				{
					var42 = -1.0F;
					var43 = 1.0F;
				}

				double var44 = par1Entity.motionX;
				double var46 = par1Entity.motionZ;
				par1Entity.motionX = var44 * var40 + var46 * var43;
				par1Entity.motionZ = var44 * var42 + var46 * var41;
				par1Entity.rotationYaw = par8 - var30 * 90 + var50 * 90;
			}
			else
			{
				par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
			}

			par1Entity.setLocationAndAngles(var49 + 1, var25, var27 + 1, par1Entity.rotationYaw, par1Entity.rotationPitch);
			return true;
		} else
			return false;
	}

	@Override
	public boolean makePortal(Entity par1Entity)
	{
		int i = MathHelper.floor_double(par1Entity.posX - 8 );
		int j = 130;
		int k = MathHelper.floor_double(par1Entity.posZ - 8);

		boolean flag;

		worldServerInstance.setBlock(i + 1, j + 6, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 1, j + 7, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 1, j + 7, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 1, j + 7, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 1, j + 8, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 1, j + 8, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 1, j + 8, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 1, j + 9, k + 6, Block.grass.blockID);
		worldServerInstance.setBlock(i + 1, j + 9, k + 7, Block.grass.blockID);
		worldServerInstance.setBlock(i + 1, j + 9, k + 8, Block.grass.blockID);
		worldServerInstance.setBlock(i + 2, j + 3, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 4, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 4, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 5, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 5, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 6, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 6, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 7, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 7, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 7, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 7, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 7, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 7, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 7, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 8, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 8, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 8, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 8, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 8, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 8, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 8, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 2, j + 9, k + 4, Block.grass.blockID);
		worldServerInstance.setBlock(i + 2, j + 9, k + 5, Block.grass.blockID);
		worldServerInstance.setBlock(i + 2, j + 9, k + 6, Block.grass.blockID);
		worldServerInstance.setBlock(i + 2, j + 9, k + 7, Block.grass.blockID);
		worldServerInstance.setBlock(i + 2, j + 9, k + 8, Block.grass.blockID);
		worldServerInstance.setBlock(i + 2, j + 9, k + 9, Block.grass.blockID);
		worldServerInstance.setBlock(i + 2, j + 9, k + 10, Block.grass.blockID);
		worldServerInstance.setBlock(i + 3, j + 3, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 4, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 5, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 6, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 6, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 6, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 6, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 6, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 7, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 7, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 7, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 7, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 7, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 7, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 7, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 7, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 7, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 8, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 8, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 8, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 8, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 8, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 8, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 8, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 8, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 8, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 9, k + 3, Block.grass.blockID);
		worldServerInstance.setBlock(i + 3, j + 9, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 9, k + 5, Block.grass.blockID);
		worldServerInstance.setBlock(i + 3, j + 9, k + 6, Block.grass.blockID);
		worldServerInstance.setBlock(i + 3, j + 9, k + 7, Block.grass.blockID);
		worldServerInstance.setBlock(i + 3, j + 9, k + 8, Block.grass.blockID);
		worldServerInstance.setBlock(i + 3, j + 9, k + 9, Block.grass.blockID);
		worldServerInstance.setBlock(i + 3, j + 9, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 3, j + 9, k + 11, Block.grass.blockID);
		worldServerInstance.setBlock(i + 3, j + 10, k + 4, Block.wood.blockID);
		worldServerInstance.setBlock(i + 3, j + 10, k + 5, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 3, j + 10, k + 6, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 3, j + 10, k + 7, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 3, j + 10, k + 8, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 3, j + 10, k + 9, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 3, j + 10, k + 10, Block.wood.blockID);
		worldServerInstance.setBlock(i + 3, j + 11, k + 4, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 3, j + 11, k + 10, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 3, j + 12, k + 4, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 3, j + 12, k + 10, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 3, j + 14, k + 4, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 3, j + 14, k + 5, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 3, j + 14, k + 6, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 3, j + 14, k + 7, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 3, j + 14, k + 8, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 3, j + 14, k + 9, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 3, j + 14, k + 10, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 4, j + 5, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 5, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 5, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 5, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 6, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 6, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 6, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 6, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 6, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 6, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 6, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 6, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 7, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 7, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 7, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 7, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 7, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 7, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 7, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 7, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 7, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 7, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 7, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 8, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 8, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 8, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 8, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 8, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 8, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 8, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 8, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 8, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 8, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 8, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 9, k + 2, Block.grass.blockID);
		worldServerInstance.setBlock(i + 4, j + 9, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 9, k + 4, Block.grass.blockID);
		worldServerInstance.setBlock(i + 4, j + 9, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 9, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 9, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 9, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 9, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 9, k + 10, Block.grass.blockID);
		worldServerInstance.setBlock(i + 4, j + 9, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 4, j + 9, k + 12, Block.grass.blockID);
		worldServerInstance.setBlock(i + 4, j + 10, k + 3, Block.wood.blockID);
		worldServerInstance.setBlock(i + 4, j + 10, k + 4, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 4, j + 10, k + 5, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 4, j + 10, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 10, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 10, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 10, k + 9, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 4, j + 10, k + 10, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 4, j + 10, k + 11, Block.wood.blockID);
		worldServerInstance.setBlock(i + 4, j + 11, k + 3, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 4, j + 11, k + 5, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 4, j + 11, k + 9, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 4, j + 11, k + 11, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 4, j + 12, k + 3, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 4, j + 12, k + 5, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 4, j + 12, k + 9, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 4, j + 12, k + 11, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 4, j + 13, k + 5, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 4, j + 13, k + 9, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 4, j + 14, k + 3, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 14, k + 4, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 14, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 14, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 14, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 14, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 14, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 14, k + 10, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 4, j + 14, k + 11, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 15, k + 5, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 15, k + 6, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 15, k + 7, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 15, k + 8, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 4, j + 15, k + 9, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 2, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 3, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 4, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 4, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 4, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 4, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 5, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 5, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 5, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 5, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 5, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 5, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 5, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 6, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 6, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 6, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 6, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 6, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 6, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 6, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 6, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 7, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 7, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 7, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 7, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 7, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 7, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 7, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 7, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 7, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 7, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 7, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 8, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 8, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 8, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 8, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 8, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 8, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 8, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 8, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 8, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 8, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 8, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 9, k + 2, Block.grass.blockID);
		worldServerInstance.setBlock(i + 5, j + 9, k + 3, Block.grass.blockID);
		worldServerInstance.setBlock(i + 5, j + 9, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 9, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 9, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 9, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 9, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 9, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 9, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 5, j + 9, k + 11, Block.grass.blockID);
		worldServerInstance.setBlock(i + 5, j + 9, k + 12, Block.grass.blockID);
		worldServerInstance.setBlock(i + 5, j + 10, k + 3, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 5, j + 10, k + 4, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 10, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 10, k + 6, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 5, j + 10, k + 7, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 5, j + 10, k + 8, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 5, j + 10, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 10, k + 10, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 10, k + 11, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 5, j + 11, k + 4, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 11, k + 10, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 12, k + 4, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 12, k + 10, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 13, k + 4, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 13, k + 10, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 14, k + 3, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 14, k + 4, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 14, k + 10, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 14, k + 11, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 5, j + 15, k + 4, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 15, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 15, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 15, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 15, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 15, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 15, k + 10, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 5, j + 16, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 16, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 16, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 16, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 16, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 17, k + 5, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 17, k + 6, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 17, k + 7, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 17, k + 8, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 5, j + 17, k + 9, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 3, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 4, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 4, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 4, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 4, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 5, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 5, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 5, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 5, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 5, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 5, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 5, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 5, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 5, k + 13, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 6, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 6, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 6, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 6, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 6, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 6, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 6, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 6, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 6, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 6, k + 13, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 1, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 7, k + 13, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 1, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 6, Blocks.promisedPortal.get().blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 7, Blocks.promisedPortal.get().blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 8, Blocks.promisedPortal.get().blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 8, k + 13, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 9, k + 1, Block.grass.blockID);
		worldServerInstance.setBlock(i + 6, j + 9, k + 2, Block.grass.blockID);
		worldServerInstance.setBlock(i + 6, j + 9, k + 3, Block.grass.blockID);
		worldServerInstance.setBlock(i + 6, j + 9, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 9, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 9, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 9, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 6, j + 9, k + 11, Block.grass.blockID);
		worldServerInstance.setBlock(i + 6, j + 9, k + 12, Block.grass.blockID);
		worldServerInstance.setBlock(i + 6, j + 9, k + 13, Block.grass.blockID);
		worldServerInstance.setBlock(i + 6, j + 10, k + 3, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 6, j + 10, k + 4, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 10, k + 5, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 6, j + 10, k + 9, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 6, j + 10, k + 10, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 10, k + 11, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 6, j + 14, k + 3, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 6, j + 14, k + 4, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 14, k + 10, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 14, k + 11, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 6, j + 15, k + 4, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 6, j + 15, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 15, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 15, k + 10, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 6, j + 16, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 16, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 17, k + 5, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 6, j + 17, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 17, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 17, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 17, k + 9, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 6, j + 18, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 18, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 18, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 19, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 19, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 19, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 20, k + 6, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 20, k + 7, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 6, j + 20, k + 8, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 7, j + 1, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 2, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 3, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 3, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 3, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 3, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 4, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 4, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 4, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 4, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 4, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 4, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 5, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 5, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 5, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 5, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 5, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 5, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 5, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 6, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 6, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 6, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 6, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 6, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 6, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 6, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 6, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 6, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 6, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 1, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 7, k + 13, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 1, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 6, Blocks.promisedPortal.get().blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 7, Blocks.promisedPortal.get().blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 8, Blocks.promisedPortal.get().blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 8, k + 13, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 9, k + 1, Block.grass.blockID);
		worldServerInstance.setBlock(i + 7, j + 9, k + 2, Block.grass.blockID);
		worldServerInstance.setBlock(i + 7, j + 9, k + 3, Block.grass.blockID);
		worldServerInstance.setBlock(i + 7, j + 9, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 9, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 9, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 9, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 7, j + 9, k + 11, Block.grass.blockID);
		worldServerInstance.setBlock(i + 7, j + 9, k + 12, Block.grass.blockID);
		worldServerInstance.setBlock(i + 7, j + 9, k + 13, Block.grass.blockID);
		worldServerInstance.setBlock(i + 7, j + 10, k + 3, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 7, j + 10, k + 4, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 10, k + 5, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 7, j + 10, k + 9, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 7, j + 10, k + 10, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 10, k + 11, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 7, j + 14, k + 3, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 7, j + 14, k + 4, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 14, k + 10, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 14, k + 11, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 7, j + 15, k + 4, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 7, j + 15, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 15, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 15, k + 10, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 7, j + 16, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 16, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 17, k + 5, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 7, j + 17, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 17, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 17, k + 9, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 7, j + 18, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 18, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 19, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 19, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 20, k + 6, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 7, j + 20, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 20, k + 8, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 7, j + 21, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 22, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 7, j + 23, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 3, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 3, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 4, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 4, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 4, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 4, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 5, k + 1, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 5, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 5, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 5, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 5, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 5, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 5, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 5, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 6, k + 1, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 6, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 6, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 6, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 6, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 6, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 6, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 6, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 6, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 1, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 7, k + 13, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 1, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 6, Blocks.promisedPortal.get().blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 7, Blocks.promisedPortal.get().blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 8, Blocks.promisedPortal.get().blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 8, k + 13, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 9, k + 1, Block.grass.blockID);
		worldServerInstance.setBlock(i + 8, j + 9, k + 2, Block.grass.blockID);
		worldServerInstance.setBlock(i + 8, j + 9, k + 3, Block.grass.blockID);
		worldServerInstance.setBlock(i + 8, j + 9, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 9, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 9, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 9, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 8, j + 9, k + 11, Block.grass.blockID);
		worldServerInstance.setBlock(i + 8, j + 9, k + 12, Block.grass.blockID);
		worldServerInstance.setBlock(i + 8, j + 9, k + 13, Block.grass.blockID);
		worldServerInstance.setBlock(i + 8, j + 10, k + 3, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 8, j + 10, k + 4, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 10, k + 5, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 8, j + 10, k + 9, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 8, j + 10, k + 10, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 10, k + 11, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 8, j + 14, k + 3, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 8, j + 14, k + 4, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 14, k + 10, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 14, k + 11, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 8, j + 15, k + 4, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 8, j + 15, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 15, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 15, k + 10, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 8, j + 16, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 16, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 17, k + 5, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 8, j + 17, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 17, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 17, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 17, k + 9, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 8, j + 18, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 18, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 18, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 19, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 19, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 19, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 8, j + 20, k + 6, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 8, j + 20, k + 7, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 8, j + 20, k + 8, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 9, j + 4, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 5, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 5, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 5, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 5, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 5, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 5, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 5, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 6, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 6, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 6, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 6, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 6, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 6, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 6, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 6, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 6, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 7, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 7, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 7, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 7, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 7, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 7, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 7, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 7, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 7, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 7, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 7, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 8, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 8, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 8, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 8, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 8, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 8, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 8, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 8, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 8, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 8, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 8, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 9, k + 2, Block.grass.blockID);
		worldServerInstance.setBlock(i + 9, j + 9, k + 3, Block.grass.blockID);
		worldServerInstance.setBlock(i + 9, j + 9, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 9, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 9, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 9, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 9, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 9, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 9, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 9, j + 9, k + 11, Block.grass.blockID);
		worldServerInstance.setBlock(i + 9, j + 9, k + 12, Block.grass.blockID);
		worldServerInstance.setBlock(i + 9, j + 10, k + 3, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 9, j + 10, k + 4, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 10, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 10, k + 6, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 10, k + 7, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 10, k + 8, Block.stairsNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 10, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 10, k + 10, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 10, k + 11, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 9, j + 11, k + 4, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 11, k + 10, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 12, k + 4, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 12, k + 10, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 13, k + 4, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 13, k + 10, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 14, k + 3, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 14, k + 4, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 14, k + 10, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 14, k + 11, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 9, j + 15, k + 4, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 15, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 15, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 15, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 15, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 15, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 15, k + 10, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 9, j + 16, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 16, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 16, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 16, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 16, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 9, j + 17, k + 5, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 17, k + 6, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 9, j + 17, k + 7, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 9, j + 17, k + 8, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 9, j + 17, k + 9, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 10, j + 4, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 5, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 5, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 5, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 5, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 6, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 6, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 6, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 6, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 6, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 6, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 7, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 7, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 7, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 7, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 7, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 7, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 7, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 7, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 7, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 7, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 7, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 8, k + 2, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 8, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 8, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 8, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 8, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 8, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 8, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 8, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 8, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 8, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 8, k + 12, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 9, k + 2, Block.grass.blockID);
		worldServerInstance.setBlock(i + 10, j + 9, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 9, k + 4, Block.grass.blockID);
		worldServerInstance.setBlock(i + 10, j + 9, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 9, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 9, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 9, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 9, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 9, k + 10, Block.grass.blockID);
		worldServerInstance.setBlock(i + 10, j + 9, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 10, j + 9, k + 12, Block.grass.blockID);
		worldServerInstance.setBlock(i + 10, j + 10, k + 3, Block.wood.blockID);
		worldServerInstance.setBlock(i + 10, j + 10, k + 4, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 10, j + 10, k + 5, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 10, j + 10, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 10, j + 10, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 10, j + 10, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 10, j + 10, k + 9, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 10, j + 10, k + 10, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 10, j + 10, k + 11, Block.wood.blockID);
		worldServerInstance.setBlock(i + 10, j + 11, k + 3, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 10, j + 11, k + 5, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 10, j + 11, k + 9, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 10, j + 11, k + 11, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 10, j + 12, k + 3, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 10, j + 12, k + 5, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 10, j + 12, k + 9, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 10, j + 12, k + 11, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 10, j + 13, k + 5, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 10, j + 13, k + 9, Block.blockNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 10, j + 14, k + 3, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 10, j + 14, k + 4, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 10, j + 14, k + 5, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 10, j + 14, k + 6, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 10, j + 14, k + 7, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 10, j + 14, k + 8, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 10, j + 14, k + 9, Block.blockNetherQuartz.blockID);
		worldServerInstance.setBlock(i + 10, j + 14, k + 10, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 10, j + 14, k + 11, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 10, j + 15, k + 5, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 10, j + 15, k + 6, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 10, j + 15, k + 7, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 10, j + 15, k + 8, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 10, j + 15, k + 9, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 11, j + 3, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 4, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 4, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 5, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 5, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 6, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 6, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 6, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 6, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 6, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 6, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 7, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 7, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 7, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 7, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 7, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 7, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 7, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 7, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 7, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 8, k + 3, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 8, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 8, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 8, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 8, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 8, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 8, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 8, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 8, k + 11, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 9, k + 3, Block.grass.blockID);
		worldServerInstance.setBlock(i + 11, j + 9, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 9, k + 5, Block.grass.blockID);
		worldServerInstance.setBlock(i + 11, j + 9, k + 6, Block.grass.blockID);
		worldServerInstance.setBlock(i + 11, j + 9, k + 7, Block.grass.blockID);
		worldServerInstance.setBlock(i + 11, j + 9, k + 8, Block.grass.blockID);
		worldServerInstance.setBlock(i + 11, j + 9, k + 9, Block.grass.blockID);
		worldServerInstance.setBlock(i + 11, j + 9, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 11, j + 9, k + 11, Block.grass.blockID);
		worldServerInstance.setBlock(i + 11, j + 10, k + 4, Block.wood.blockID);
		worldServerInstance.setBlock(i + 11, j + 10, k + 5, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 11, j + 10, k + 6, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 11, j + 10, k + 7, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 11, j + 10, k + 8, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 11, j + 10, k + 9, Block.stoneSingleSlab.blockID, 7, 2);
		worldServerInstance.setBlock(i + 11, j + 10, k + 10, Block.wood.blockID);
		worldServerInstance.setBlock(i + 11, j + 11, k + 4, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 11, j + 11, k + 10, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 11, j + 12, k + 4, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 11, j + 12, k + 10, Block.leaves.blockID, 4, 2);
		worldServerInstance.setBlock(i + 11, j + 14, k + 4, Block.stairsNetherQuartz.blockID, 2, 2);
		worldServerInstance.setBlock(i + 11, j + 14, k + 5, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 11, j + 14, k + 6, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 11, j + 14, k + 7, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 11, j + 14, k + 8, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 11, j + 14, k + 9, Block.stairsNetherQuartz.blockID, 1, 2);
		worldServerInstance.setBlock(i + 11, j + 14, k + 10, Block.stairsNetherQuartz.blockID, 3, 2);
		worldServerInstance.setBlock(i + 12, j + 2, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 3, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 4, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 5, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 6, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 6, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 7, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 7, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 7, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 7, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 7, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 7, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 7, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 8, k + 4, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 8, k + 5, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 8, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 8, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 8, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 8, k + 9, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 8, k + 10, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 12, j + 9, k + 4, Block.grass.blockID);
		worldServerInstance.setBlock(i + 12, j + 9, k + 5, Block.grass.blockID);
		worldServerInstance.setBlock(i + 12, j + 9, k + 6, Block.grass.blockID);
		worldServerInstance.setBlock(i + 12, j + 9, k + 7, Block.grass.blockID);
		worldServerInstance.setBlock(i + 12, j + 9, k + 8, Block.grass.blockID);
		worldServerInstance.setBlock(i + 12, j + 9, k + 9, Block.grass.blockID);
		worldServerInstance.setBlock(i + 12, j + 9, k + 10, Block.grass.blockID);
		worldServerInstance.setBlock(i + 13, j + 5, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 13, j + 6, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 13, j + 7, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 13, j + 7, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 13, j + 7, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 13, j + 8, k + 6, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 13, j + 8, k + 7, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 13, j + 8, k + 8, Block.dirt.blockID);
		worldServerInstance.setBlock(i + 13, j + 9, k + 6, Block.grass.blockID);
		worldServerInstance.setBlock(i + 13, j + 9, k + 7, Block.grass.blockID);
		worldServerInstance.setBlock(i + 13, j + 9, k + 8, Block.grass.blockID);
		worldServerInstance.setBlock(i + 0, j + 0, k + 8, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 1, k + 8, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 2, k + 8, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 3, k + 6, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 3, k + 8, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 4, k + 6, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 4, k + 8, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 5, k + 6, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 5, k + 7, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 5, k + 8, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 6, k + 6, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 6, k + 7, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 6, k + 8, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 7, k + 6, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 7, k + 7, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 7, k + 8, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 8, k + 6, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 8, k + 7, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 8, k + 8, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 9, k + 6, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 9, k + 7, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 0, j + 9, k + 8, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 1, j + 10, k + 6, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 1, j + 10, k + 7, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 1, j + 10, k + 8, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 2, j + 2, k + 3, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 2, k + 11, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 3, k + 3, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 3, k + 11, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 4, k + 3, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 4, k + 11, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 5, k + 3, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 5, k + 11, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 6, k + 3, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 6, k + 11, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 7, k + 3, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 7, k + 11, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 8, k + 3, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 8, k + 11, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 9, k + 3, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 9, k + 11, Block.vine.blockID, 8, 2);
		worldServerInstance.setBlock(i + 2, j + 10, k + 4, Block.plantRed.blockID);
		worldServerInstance.setBlock(i + 2, j + 10, k + 5, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 2, j + 10, k + 6, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 2, j + 10, k + 7, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 2, j + 10, k + 8, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 2, j + 10, k + 9, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 2, j + 10, k + 10, Block.plantRed.blockID);
		worldServerInstance.setBlock(i + 3, j + 5, k + 2, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 3, j + 5, k + 12, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 3, j + 6, k + 2, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 3, j + 6, k + 12, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 3, j + 7, k + 2, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 3, j + 7, k + 12, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 3, j + 8, k + 2, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 3, j + 8, k + 12, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 3, j + 9, k + 2, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 3, j + 9, k + 12, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 3, j + 10, k + 3, Block.plantYellow.blockID);
		worldServerInstance.setBlock(i + 3, j + 10, k + 11, Block.plantYellow.blockID);
		worldServerInstance.setBlock(i + 4, j + 10, k + 2, Block.plantRed.blockID);
		worldServerInstance.setBlock(i + 4, j + 10, k + 12, Block.plantRed.blockID);
		worldServerInstance.setBlock(i + 5, j + 10, k + 2, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 5, j + 10, k + 12, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 6, j + 0, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 6, j + 1, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 6, j + 2, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 6, j + 3, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 6, j + 3, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 6, j + 4, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 6, j + 4, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 6, j + 5, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 6, j + 5, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 6, j + 6, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 6, j + 6, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 6, j + 7, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 6, j + 7, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 6, j + 8, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 6, j + 8, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 6, j + 9, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 6, j + 9, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 6, j + 10, k + 1, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 6, j + 10, k + 2, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 6, j + 10, k + 12, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 6, j + 10, k + 13, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 7, j + 1, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 7, j + 1, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 7, j + 2, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 7, j + 2, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 7, j + 3, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 7, j + 3, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 7, j + 4, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 7, j + 4, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 7, j + 5, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 7, j + 5, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 7, j + 6, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 7, j + 6, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 7, j + 7, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 7, j + 7, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 7, j + 8, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 7, j + 8, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 7, j + 9, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 7, j + 9, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 7, j + 10, k + 1, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 7, j + 10, k + 2, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 7, j + 10, k + 12, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 7, j + 10, k + 13, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 8, j + 3, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 8, j + 4, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 8, j + 4, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 8, j + 5, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 8, j + 5, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 8, j + 6, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 8, j + 6, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 8, j + 7, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 8, j + 7, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 8, j + 8, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 8, j + 8, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 8, j + 9, k + 0, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 8, j + 9, k + 14, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 8, j + 10, k + 1, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 8, j + 10, k + 2, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 8, j + 10, k + 12, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 8, j + 10, k + 13, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 10, k + 2, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 9, j + 10, k + 12, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 10, j + 10, k + 2, Block.plantRed.blockID);
		worldServerInstance.setBlock(i + 10, j + 10, k + 12, Block.plantRed.blockID);
		worldServerInstance.setBlock(i + 11, j + 5, k + 2, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 11, j + 5, k + 12, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 11, j + 6, k + 2, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 11, j + 6, k + 12, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 11, j + 7, k + 2, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 11, j + 7, k + 12, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 11, j + 8, k + 2, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 11, j + 8, k + 12, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 11, j + 9, k + 2, Block.vine.blockID, 1, 2);
		worldServerInstance.setBlock(i + 11, j + 9, k + 12, Block.vine.blockID, 4, 2);
		worldServerInstance.setBlock(i + 11, j + 10, k + 3, Block.plantYellow.blockID);
		worldServerInstance.setBlock(i + 11, j + 10, k + 11, Block.plantYellow.blockID);
		worldServerInstance.setBlock(i + 12, j + 2, k + 3, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 2, k + 11, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 3, k + 3, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 3, k + 11, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 4, k + 3, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 4, k + 11, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 5, k + 3, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 5, k + 11, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 6, k + 3, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 6, k + 11, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 7, k + 3, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 7, k + 11, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 8, k + 3, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 8, k + 11, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 9, k + 3, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 9, k + 11, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 10, k + 4, Block.plantRed.blockID);
		worldServerInstance.setBlock(i + 12, j + 10, k + 5, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 10, k + 6, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 10, k + 7, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 10, k + 8, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 10, k + 9, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 12, j + 10, k + 10, Block.plantRed.blockID);
		worldServerInstance.setBlock(i + 13, j + 10, k + 6, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 13, j + 10, k + 7, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 13, j + 10, k + 8, Block.tallGrass.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 0, k + 7, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 1, k + 7, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 2, k + 7, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 2, k + 8, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 3, k + 7, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 3, k + 8, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 4, k + 6, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 4, k + 7, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 4, k + 8, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 5, k + 6, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 5, k + 7, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 5, k + 8, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 6, k + 6, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 6, k + 7, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 6, k + 8, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 7, k + 6, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 7, k + 7, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 7, k + 8, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 8, k + 6, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 8, k + 7, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 8, k + 8, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 9, k + 6, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 9, k + 7, Block.vine.blockID, 2, 2);
		worldServerInstance.setBlock(i + 14, j + 9, k + 8, Block.vine.blockID, 2, 2);

		return true;
	}
}