package biomesoplenty.world.map;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenBase;

public class MapGenCavesBOP extends MapGenBase
{
    public MapGenCavesBOP()
    {
    }

    protected void generateLargeCaveNode(long l, int i, int j, byte abyte0[], double d, double d1, double d2)
    {
        generateCaveNode(l, i, j, abyte0, d, d1, d2, 1.0F + rand.nextFloat() * 6F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void generateCaveNode(long l, int i, int j, byte abyte0[], double d, double d1, double d2, float f, float f1, float f2, int k, int i1, double d3)
    {
        double d4 = i * 16 + 8;
        double d5 = j * 16 + 8;
        float f3 = 0.0F;
        float f4 = 0.0F;
        Random random = new Random(l);

        if (i1 <= 0)
        {
            int j1 = range * 16 - 16;
            i1 = j1 - random.nextInt(j1 / 4);
        }

        boolean flag = false;

        if (k == -1)
        {
            k = i1 / 2;
            flag = true;
        }

        int k1 = random.nextInt(i1 / 2) + i1 / 4;
        boolean flag1 = random.nextInt(6) == 0;

        for (; k < i1; k++)
        {
            double d6 = 1.5D + (double)(MathHelper.sin(((float)k * (float)Math.PI) / (float)i1) * f * 1.0F);
            double d7 = d6 * d3;
            float f5 = MathHelper.cos(f2);
            float f6 = MathHelper.sin(f2);
            d += MathHelper.cos(f1) * f5;
            d1 += f6;
            d2 += MathHelper.sin(f1) * f5;

            if (flag1)
            {
                f2 *= 0.92F;
            }
            else
            {
                f2 *= 0.7F;
            }

            f2 += f4 * 0.1F;
            f1 += f3 * 0.1F;
            f4 *= 0.9F;
            f3 *= 0.75F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4F;

            if (!flag && k == k1 && f > 1.0F && i1 > 0)
            {
                generateCaveNode(random.nextLong(), i, j, abyte0, d, d1, d2, random.nextFloat() * 0.5F + 0.5F, f1 - ((float)Math.PI / 2F), f2 / 3F, k, i1, 1.0D);
                generateCaveNode(random.nextLong(), i, j, abyte0, d, d1, d2, random.nextFloat() * 0.5F + 0.5F, f1 + ((float)Math.PI / 2F), f2 / 3F, k, i1, 1.0D);
                return;
            }

            if (!flag && random.nextInt(4) == 0)
            {
                continue;
            }

            double d8 = d - d4;
            double d9 = d2 - d5;
            double d10 = i1 - k;
            double d11 = f + 2.0F + 16F;

            if ((d8 * d8 + d9 * d9) - d10 * d10 > d11 * d11)
            {
                return;
            }

            if (d < d4 - 16D - d6 * 2D || d2 < d5 - 16D - d6 * 2D || d > d4 + 16D + d6 * 2D || d2 > d5 + 16D + d6 * 2D)
            {
                continue;
            }

            int l1 = MathHelper.floor_double(d - d6) - i * 16 - 1;
            int i2 = (MathHelper.floor_double(d + d6) - i * 16) + 1;
            int j2 = MathHelper.floor_double(d1 - d7) - 1;
            int k2 = MathHelper.floor_double(d1 + d7) + 1;
            int l2 = MathHelper.floor_double(d2 - d6) - j * 16 - 1;
            int i3 = (MathHelper.floor_double(d2 + d6) - j * 16) + 1;

            if (l1 < 0)
            {
                l1 = 0;
            }

            if (i2 > 16)
            {
                i2 = 16;
            }

            if (j2 < 1)
            {
                j2 = 1;
            }

            if (k2 > 248)
            {
                k2 = 248;
            }

            if (l2 < 0)
            {
                l2 = 0;
            }

            if (i3 > 16)
            {
                i3 = 16;
            }

            boolean flag2 = false;

            for (int j3 = l1; !flag2 && j3 < i2; j3++)
            {
                for (int l3 = l2; !flag2 && l3 < i3; l3++)
                {
                    for (int i4 = k2 + 1; !flag2 && i4 >= j2 - 1; i4--)
                    {
                        int j4 = (j3 * 16 + l3) * 256 + i4;

                        if (i4 < 0 || i4 >= 256)
                        {
                            continue;
                        }

                        if (abyte0[j4] == Block.waterMoving.blockID || abyte0[j4] == Block.waterStill.blockID)
                        {
                            flag2 = true;
                        }

                        if (i4 != j2 - 1 && j3 != l1 && j3 != i2 - 1 && l3 != l2 && l3 != i3 - 1)
                        {
                            i4 = j2;
                        }
                    }
                }
            }

            if (flag2)
            {
                continue;
            }

            for (int k3 = l1; k3 < i2; k3++)
            {
                double d12 = (((double)(k3 + i * 16) + 0.5D) - d) / d6;
                label0:

                for (int k4 = l2; k4 < i3; k4++)
                {
                    double d13 = (((double)(k4 + j * 16) + 0.5D) - d2) / d6;
                    int l4 = (k3 * 16 + k4) * 256 + k2;
                    boolean flag3 = false;

                    if (d12 * d12 + d13 * d13 >= 1.0D)
                    {
                        continue;
                    }

                    int i5 = k2 - 1;

                    do
                    {
                        if (i5 < j2)
                        {
                            continue label0;
                        }

                        double d14 = (((double)i5 + 0.5D) - d1) / d7;

                        if (d14 > -0.69999999999999996D && d12 * d12 + d14 * d14 + d13 * d13 < 1.0D)
                        {
                            byte byte0 = abyte0[l4];

                            if (byte0 == Block.grass.blockID)
                            {
                                flag3 = true;
                            }

                            if (byte0 == Block.stone.blockID || byte0 == Block.dirt.blockID || byte0 == Block.grass.blockID)
                            {
                                if (i5 < 10)
                                {
                                    abyte0[l4] = (byte)Block.lavaMoving.blockID;
                                }
                                else
                                {
                                    abyte0[l4] = 0;

                                    if (flag3 && abyte0[l4 - 1] == Block.dirt.blockID)
                                    {
                                        abyte0[l4 - 1] = worldObj.getBiomeGenForCoords(k3 + i * 16, k4 + j * 16).topBlock;
                                    }
                                }
                            }
                        }

                        l4--;
                        i5--;
                    }
                    while (true);
                }
            }

            if (flag)
            {
                break;
            }
        }
    }

    /**
     * Recursively called by generate() (generate) and optionally by itself.
     */
    protected void recursiveGenerate(World world, int i, int j, int k, int l, byte abyte0[])
    {
        int i1 = rand.nextInt(rand.nextInt(rand.nextInt(40) + 1) + 1);

        if (rand.nextInt(15) != 0)
        {
            i1 = 0;
        }

        for (int j1 = 0; j1 < i1; j1++)
        {
            double d = i * 16 + rand.nextInt(16);
            double d1 = rand.nextInt(rand.nextInt(248) + 8);
            double d2 = j * 16 + rand.nextInt(16);
            int k1 = 1;

            if (rand.nextInt(4) == 0)
            {
                generateLargeCaveNode(rand.nextLong(), k, l, abyte0, d, d1, d2);
                k1 += rand.nextInt(4);
            }

            for (int l1 = 0; l1 < k1; l1++)
            {
                float f = rand.nextFloat() * (float)Math.PI * 2.0F;
                float f1 = ((rand.nextFloat() - 0.5F) * 2.0F) / 8F;
                float f2 = rand.nextFloat() * 2.0F + rand.nextFloat();

                if (rand.nextInt(10) == 0)
                {
                    f2 *= rand.nextFloat() * rand.nextFloat() * 3F + 1.0F;
                }

                generateCaveNode(rand.nextLong(), k, l, abyte0, d, d1, d2, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }
}
