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
        this.falling = false;
        this.worldServerInstance = worldServer;
	}
	
	public TeleporterPromised(WorldServer worldServer, boolean fall)
	{
	    super(worldServer);
	    this.falling = fall;
	    this.worldServerInstance = worldServer;
	}

	@Override
	public void placeInPortal(Entity par1Entity, double x, double y, double z, float par8)
	{
		if (!this.falling)
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

		if (this.field_85191_c.containsItem(var17))
		{
			PortalPosition portalposition = (PortalPosition) this.field_85191_c.getValueByKey(var17);
			var10 = 0.0D;
			i = portalposition.posX;
			j = 131;
			k = portalposition.posZ;
			portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
			var19 = false;
		}
		else
		{
			for (var48 = var15 - var9; var48 <= var15 + var9; ++var48)
			{
				double var21 = (double) var48 + 0.5D - par1Entity.posX;

				for (int var23 = var16 - var9; var23 <= var16 + var9; ++var23)
				{
					double var24 = (double) var23 + 0.5D - par1Entity.posZ;

					for (int var26 = this.worldServerInstance.getActualHeight() - 1; var26 >= 0; --var26)
					{
						if (this.worldServerInstance.getBlockId(var48, var26, var23) == Blocks.promisedPortal.get().blockID)
						{
							while (this.worldServerInstance.getBlockId(var48, var26 - 1, var23) == Blocks.promisedPortal.get().blockID)
								{
								--var26;
								}

							var27 = (double) var26 + 0.5D - par1Entity.posY;
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
			this.field_85191_c.add(var17, new PortalPosition(this, i, j, k, this.worldServerInstance.getTotalWorldTime()));
			this.field_85190_d.add(Long.valueOf(var17));
			}

			double var49 = (double) i + 0.5D;
			double var25 = (double) j + 0.5D;
			var27 = (double) k + 0.5D;
			int var50 = -1;

			if (this.worldServerInstance.getBlockId(i - 1, j, k) == Blocks.promisedPortal.get().blockID)
			{
				var50 = 2;
			}

			if (this.worldServerInstance.getBlockId(i + 1, j, k) == Blocks.promisedPortal.get().blockID)
			{
				var50 = 0;
			}

			if (this.worldServerInstance.getBlockId(i, j, k - 1) == Blocks.promisedPortal.get().blockID)
			{
				var50 = 3;
			}

			if (this.worldServerInstance.getBlockId(i, j, k + 1) == Blocks.promisedPortal.get().blockID)
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
			boolean var36 = !this.worldServerInstance.isAirBlock(i + var32 + var34, j, k + var33 + var35) || !this.worldServerInstance.isAirBlock(i + var32 + var34, j + 1, k + var33 + var35);
			boolean var37 = !this.worldServerInstance.isAirBlock(i + var32, j, k + var33) || !this.worldServerInstance.isAirBlock(i + var32, j + 1, k + var33);

			if (var36 && var37)
			{
				var50 = Direction.rotateOpposite[var50];
				var31 = Direction.rotateOpposite[var31];
				var32 = Direction.offsetX[var50];
				var33 = Direction.offsetZ[var50];
				var34 = Direction.offsetX[var31];
				var35 = Direction.offsetZ[var31];
				var48 = i - var34;
				var49 -= (double) var34;
				int var22 = k - var35;
				var27 -= (double) var35;
				var36 = !this.worldServerInstance.isAirBlock(var48 + var32 + var34, j, var22 + var33 + var35) || !this.worldServerInstance.isAirBlock(var48 + var32 + var34, j + 1, var22 + var33 + var35);
				var37 = !this.worldServerInstance.isAirBlock(var48 + var32, j, var22 + var33) || !this.worldServerInstance.isAirBlock(var48 + var32, j + 1, var22 + var33);
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

			var49 += (double) ((float) var34 * var38 + var39 * (float) var32);
			var27 += (double) ((float) var35 * var38 + var39 * (float) var33);
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
			par1Entity.motionX = var44 * (double) var40 + var46 * (double) var43;
			par1Entity.motionZ = var44 * (double) var42 + var46 * (double) var41;
			par1Entity.rotationYaw = par8 - (float) (var30 * 90) + (float) (var50 * 90);
		}
		else
		{
			par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
		}

			par1Entity.setLocationAndAngles(var49 + 1, var25, var27 + 1, par1Entity.rotationYaw, par1Entity.rotationPitch);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean makePortal(Entity par1Entity)
    {
        int i = MathHelper.floor_double(par1Entity.posX);
        int j = 130;
        int k = MathHelper.floor_double(par1Entity.posZ);

        boolean flag;

        for (int iy = -1; iy < 4; iy++)
        {
            for (int ix = -2; ix < 3; ix++)
                for (int iz = -2; iz < 3; iz++)
                {
                    flag = ix == -2 || ix == 2 || iz == -2 || iz == 2 || iy == -1 || iy == 3;
                    this.worldServerInstance.setBlock(i + ix, j + iy, k + iz, flag ? Block.whiteStone.blockID : 0);
                }
            
            for (int ix = -2; ix < 3; ix++)
                for (int iz = -2; iz < 3; iz++)
                {
                    this.worldServerInstance.notifyBlocksOfNeighborChange(i + ix, j + iy, k + iz, this.worldServerInstance.getBlockId(i + ix, j + iy, k + iz));
                }
        }

        this.worldServerInstance.setBlock(i, j + 1, k, Blocks.promisedPortal.get().blockID);

        return true;
    }
}