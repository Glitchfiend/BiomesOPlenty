package biomesoplenty.common.helpers;

import java.util.ArrayList;
import java.util.List;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenPromisedLandPortal;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterPromised extends Teleporter
{
	private final WorldServer worldServerInstance;
	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	@SuppressWarnings("rawtypes")
	private final List destinationCoordinateKeys = new ArrayList();
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

		if (destinationCoordinateCache.containsItem(var17))
		{
			PortalPosition portalposition = (PortalPosition) destinationCoordinateCache.getValueByKey(var17);
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
						if (worldServerInstance.getBlock(var48, var26, var23) == BOPBlockHelper.get("promisedPortal"))
						{
							while (worldServerInstance.getBlock(var48, var26 - 1, var23) == BOPBlockHelper.get("promisedPortal"))
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
				destinationCoordinateCache.add(var17, new PortalPosition(i, j, k, worldServerInstance.getTotalWorldTime()));
				destinationCoordinateKeys.add(Long.valueOf(var17));
			}

			double var49 = i + 0.5D;
			double var25 = j + 4.0D;
			var27 = k + 0.5D;
			int var50 = -1;

			if (worldServerInstance.getBlock(i - 1, j, k) == BOPBlockHelper.get("promisedPortal"))
			{
				var50 = 2;
			}

			if (worldServerInstance.getBlock(i + 1, j, k) == BOPBlockHelper.get("promisedPortal"))
			{
				var50 = 0;
			}

			if (worldServerInstance.getBlock(i, j, k - 1) == BOPBlockHelper.get("promisedPortal"))
			{
				var50 = 3;
			}

			if (worldServerInstance.getBlock(i, j, k + 1) == BOPBlockHelper.get("promisedPortal"))
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
				boolean var36 = !isAirBlock(worldServerInstance, i + var32 + var34, j, k + var33 + var35) || !isAirBlock(worldServerInstance, i + var32 + var34, j + 1, k + var33 + var35);
				boolean var37 = !isAirBlock(worldServerInstance, i + var32, j, k + var33) || !isAirBlock(worldServerInstance, i + var32, j + 1, k + var33);

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
					var36 = !isAirBlock(worldServerInstance, var48 + var32 + var34, j, var22 + var33 + var35) || !isAirBlock(worldServerInstance, var48 + var32 + var34, j + 1, var22 + var33 + var35);
					var37 = !isAirBlock(worldServerInstance, var48 + var32, j, var22 + var33) || !isAirBlock(worldServerInstance, var48 + var32, j + 1, var22 + var33);
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

			par1Entity.setLocationAndAngles(var49 + 4, var25, var27, par1Entity.rotationYaw, par1Entity.rotationPitch);
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
		
		new WorldGenPromisedLandPortal().generate(worldServerInstance, worldServerInstance.rand, i, j, k);
		return true;
	}
	
	boolean isAirBlock(World world, int x, int y, int z) {
		return world.getBlock(x, y, z).isAir(world, x, y, z);
	}
}