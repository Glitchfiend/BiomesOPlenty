package tdwp_ftw.biomesop.worldgen;

import java.util.Random;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCypress extends WorldGenerator
{
    public WorldGenCypress(boolean var1)
    {
        super(var1);
    }

    public boolean generate(World var1, Random var2, int var3, int var4, int var5)
    {
        int var6 = var2.nextInt(10) + 15;
        int var7 = var2.nextInt(3) + 5;
        int var8 = var6 - var7;
        int var9 = 1;
        boolean var10 = true;

        if (var4 >= 1 && var4 + var6 + 1 <= 256)
        {
            int var11;
            int var13;
            int var15;
            int var21;

            for (var11 = var4; var11 <= var4 + 1 + var6 && var10; ++var11)
            {
                boolean var12 = true;

                if (var11 - var4 < var7)
                {
                    var21 = 0;
                }
                else
                {
                    var21 = var9;
                }

                for (var13 = var3 - var21; var13 <= var3 + var21 && var10; ++var13)
                {
                    for (int var14 = var5 - var21; var14 <= var5 + var21 && var10; ++var14)
                    {
                        if (var11 >= 0 && var11 < 256)
                        {
                            var15 = var1.getBlockId(var13, var11, var14);

                            if (var15 != 0 && var15 != mod_BiomesOPlenty.willowLeaves.blockID)
                            {
                                var10 = false;
                            }
                        }
                        else
                        {
                            var10 = false;
                        }
                    }
                }
            }

            if (!var10)
            {
                return false;
            }
            else
            {
                var11 = var1.getBlockId(var3, var4 - 1, var5);

                if ((var11 == Block.grass.blockID || var11 == Block.dirt.blockID) && var4 < 256 - var6 - 1)
                {
                    var1.setBlock(var3, var4 - 1, var5, Block.dirt.blockID);
                    var21 = var2.nextInt(2);
                    var13 = 1;
                    boolean var22 = false;
                    int var17;
                    int var16;

                    for (var15 = 0; var15 <= var8; ++var15)
                    {
                        var16 = var4 + var6 - var15;

                        for (var17 = var3 - var21; var17 <= var3 + var21; ++var17)
                        {
                            int var18 = var17 - var3;

                            for (int var19 = var5 - var21; var19 <= var5 + var21; ++var19)
                            {
                                int var20 = var19 - var5;

                                if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && !Block.opaqueCubeLookup[var1.getBlockId(var17, var16, var19)])
                                {
									if (var2.nextInt(3) != 0)
										{
										this.setBlockAndMetadata(var1, var17, var16, var19, mod_BiomesOPlenty.willowLeaves.blockID, 0);
										}
                                }
                            }
                        }

                        if (var21 >= var13)
                        {
                            var21 = var22 ? 1 : 0;
                            var22 = true;
                            ++var13;

                            if (var13 > var9)
                            {
                                var13 = var9;
                            }
                        }
                        else
                        {
                            ++var21;
                        }
                    }

                    var15 = var2.nextInt(3);

                    for (var16 = 0; var16 < var6 - var15; ++var16)
                    {
                        var17 = var1.getBlockId(var3, var4 + var16, var5);

                        if (var17 == 0 || var17 == mod_BiomesOPlenty.willowLeaves.blockID)
                        {
                            this.setBlockAndMetadata(var1, var3, var4 + var16, var5, mod_BiomesOPlenty.willowWood.blockID, 0);
							this.setBlockAndMetadata(var1, var3, (var4 + var6), var5, mod_BiomesOPlenty.willowWood.blockID, 0);
							this.setBlockAndMetadata(var1, var3, (var4 + var6) - 2, var5, mod_BiomesOPlenty.willowWood.blockID, 0);
							this.setBlockAndMetadata(var1, var3, (var4 + var6) - 1, var5, mod_BiomesOPlenty.willowWood.blockID, 0);
							this.setBlockAndMetadata(var1, var3, (var4 + var6), var5, mod_BiomesOPlenty.willowWood.blockID, 0);
							this.setBlockAndMetadata(var1, var3, (var4 + var6) + 1, var5, mod_BiomesOPlenty.willowWood.blockID, 0);
							this.setBlockAndMetadata(var1, var3 - 1, (var4 + var6) + 1, var5, mod_BiomesOPlenty.willowLeaves.blockID, 0);
							this.setBlockAndMetadata(var1, var3 + 1, (var4 + var6) + 1, var5, mod_BiomesOPlenty.willowLeaves.blockID, 0);
							this.setBlockAndMetadata(var1, var3, (var4 + var6) + 1, var5 - 1, mod_BiomesOPlenty.willowLeaves.blockID, 0);
							this.setBlockAndMetadata(var1, var3, (var4 + var6) + 1, var5 + 1, mod_BiomesOPlenty.willowLeaves.blockID, 0);
							this.setBlockAndMetadata(var1, var3, (var4 + var6) + 2, var5, mod_BiomesOPlenty.willowLeaves.blockID, 0);
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}
