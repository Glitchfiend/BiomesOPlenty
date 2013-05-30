package biomesoplenty.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private final Random random;
	private final LongHashMap field_85191_c = new LongHashMap();
	@SuppressWarnings("rawtypes")
	private final List field_85190_d = new ArrayList();
	private final boolean falling;

	public TeleporterPromised(WorldServer worldServer)
	{
	    super(worldServer);
        this.falling = false;
        this.worldServerInstance = worldServer;
        this.random = new Random(worldServer.getSeed());
	}
	
	public TeleporterPromised(WorldServer worldServer, boolean fall)
	{
	    super(worldServer);
	    this.falling = fall;
	    this.worldServerInstance = worldServer;
        this.random = new Random(worldServer.getSeed());
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
        byte b0 = 16;
        double d0 = -1.0D;
        int i = MathHelper.floor_double(par1Entity.posX);
        int j = 130;
        int k = MathHelper.floor_double(par1Entity.posZ);
        int l = i;
        int i1 = j;
        int j1 = k;
        int k1 = 0;
        int l1 = this.random.nextInt(4);
        int i2;
        double d1;
        double d2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        double d3;
        double d4;

        for (i2 = i - b0; i2 <= i + b0; ++i2)
        {
            d1 = (double)i2 + 0.5D - par1Entity.posX;

            for (j2 = k - b0; j2 <= k + b0; ++j2)
            {
                d2 = (double)j2 + 0.5D - par1Entity.posZ;
                label274:

                for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2)
                {
                    if (this.worldServerInstance.isAirBlock(i2, k2, j2))
                    {
                        while (k2 > 0 && this.worldServerInstance.isAirBlock(i2, k2 - 1, j2))
                        {
                            --k2;
                        }

                        for (i3 = l1; i3 < l1 + 4; ++i3)
                        {
                            l2 = i3 % 2;
                            k3 = 1 - l2;

                            if (i3 % 4 >= 2)
                            {
                                l2 = -l2;
                                k3 = -k3;
                            }

                            for (j3 = 0; j3 < 3; ++j3)
                            {
                                for (i4 = 0; i4 < 4; ++i4)
                                {
                                    for (l3 = -1; l3 < 4; ++l3)
                                    {
                                        k4 = i2 + (i4 - 1) * l2 + j3 * k3;
                                        j4 = k2 + l3;
                                        int l4 = j2 + (i4 - 1) * k3 - j3 * l2;

                                        if (l3 < 0 && !this.worldServerInstance.getBlockMaterial(k4, j4, l4).isSolid() || l3 >= 0 && !this.worldServerInstance.isAirBlock(k4, j4, l4))
                                        {
                                            continue label274;
                                        }
                                    }
                                }
                            }

                            d4 = (double)k2 + 0.5D - par1Entity.posY;
                            d3 = d1 * d1 + d4 * d4 + d2 * d2;

                            if (d0 < 0.0D || d3 < d0)
                            {
                                d0 = d3;
                                l = i2;
                                i1 = k2;
                                j1 = j2;
                                k1 = i3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D)
        {
            for (i2 = i - b0; i2 <= i + b0; ++i2)
            {
                d1 = (double)i2 + 0.5D - par1Entity.posX;

                for (j2 = k - b0; j2 <= k + b0; ++j2)
                {
                    d2 = (double)j2 + 0.5D - par1Entity.posZ;
                    label222:

                    for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2)
                    {
                        if (this.worldServerInstance.isAirBlock(i2, k2, j2))
                        {
                            while (k2 > 0 && this.worldServerInstance.isAirBlock(i2, k2 - 1, j2))
                            {
                                --k2;
                            }

                            for (i3 = l1; i3 < l1 + 2; ++i3)
                            {
                                l2 = i3 % 2;
                                k3 = 1 - l2;

                                for (j3 = 0; j3 < 4; ++j3)
                                {
                                    for (i4 = -1; i4 < 4; ++i4)
                                    {
                                        l3 = i2 + (j3 - 1) * l2;
                                        k4 = k2 + i4;
                                        j4 = j2 + (j3 - 1) * k3;

                                        if (i4 < 0 && !this.worldServerInstance.getBlockMaterial(l3, k4, j4).isSolid() || i4 >= 0 && !this.worldServerInstance.isAirBlock(l3, k4, j4))
                                        {
                                            continue label222;
                                        }
                                    }
                                }

                                d4 = (double)k2 + 0.5D - par1Entity.posY;
                                d3 = d1 * d1 + d4 * d4 + d2 * d2;

                                if (d0 < 0.0D || d3 < d0)
                                {
                                    d0 = d3;
                                    l = i2;
                                    i1 = k2;
                                    j1 = j2;
                                    k1 = i3 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i5 = l;
        int j5 = i1;
        j2 = j1;
        int k5 = k1 % 2;
        int l5 = 1 - k5;

        if (k1 % 4 >= 2)
        {
            k5 = -k5;
            l5 = -l5;
        }

        boolean flag;

        for (int iy = -1; iy < 4; iy++)
        {
            for (int ix = -2; ix < 3; ix++)
                for (int iz = -2; iz < 3; iz++)
                {
                    flag = ix == -2 || ix == 2 || iz == -2 || iz == 2 || iy == -1 || iy == 3;
                    this.worldServerInstance.setBlock(i5 + ix, j5 + iy, j2 + iz, flag ? Block.whiteStone.blockID : 0);
                }
            
            for (int ix = -2; ix < 3; ix++)
                for (int iz = -2; iz < 3; iz++)
                {
                    this.worldServerInstance.notifyBlocksOfNeighborChange(i5 + ix, j5 + iy, j2 + iz, this.worldServerInstance.getBlockId(i5 + ix, j5 + iy, j2 + iz));
                }
        }

        this.worldServerInstance.setBlock(i, j + 1, k, Blocks.promisedPortal.get().blockID);

        return true;
    }
}