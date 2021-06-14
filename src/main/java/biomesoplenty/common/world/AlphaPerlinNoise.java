package biomesoplenty.common.world;

import java.util.Random;

public class AlphaPerlinNoise
{
    private final int[] permutations;
    public final double offsetX;
    public final double offsetY;
    public final double offsetZ;

    public AlphaPerlinNoise(Random random)
    {
        permutations = new int[512];
        offsetX = random.nextDouble() * 256D;
        offsetY = random.nextDouble() * 256D;
        offsetZ = random.nextDouble() * 256D;
        for(int i = 0; i < 256; i++)
        {
            permutations[i] = i;
        }

        for(int j = 0; j < 256; j++)
        {
            int k = random.nextInt(256 - j) + j;
            int l = permutations[j];
            permutations[j] = permutations[k];
            permutations[k] = l;
            permutations[j + 256] = permutations[j];
        }

    }

    public double sample(double x, double y, double z)
    {
        double localX = x + offsetX;
        double localY = y + offsetY;
        double localZ = z + offsetZ;
        int floorX = (int) localX;
        int floorY = (int) localY;
        int floorZ = (int) localZ;
        if(localX < (double) floorX)
        {
            floorX--;
        }
        if(localY < (double) floorY)
        {
            floorY--;
        }
        if(localZ < (double) floorZ)
        {
            floorZ--;
        }
        int maskedX = floorX & 0xff;
        int maskedY = floorY & 0xff;
        int maskedZ = floorZ & 0xff;
        localX -= floorX;
        localY -= floorY;
        localZ -= floorZ;
        // Apply smoothstep on all axes
        double smoothedX = localX * localX * localX * (localX * (localX * 6 - 15) + 10);
        double smoothedY = localY * localY * localY * (localY * (localY * 6 - 15) + 10);
        double smoothedZ = localZ * localZ * localZ * (localZ * (localZ * 6 - 15) + 10);
        int perm1 = permutations[maskedX] + maskedY;
        int perm2 = permutations[perm1] + maskedZ;
        int perm3 = permutations[perm1 + 1] + maskedZ;
        int perm4 = permutations[maskedX + 1] + maskedY;
        int perm5 = permutations[perm4] + maskedZ;
        int perm6 = permutations[perm4 + 1] + maskedZ;
        // Apply trilinear interpolation on the noise to get the final result
        return lerp(smoothedZ,
                    lerp(smoothedY,
                        lerp(smoothedX,
                                grad(permutations[perm2], localX, localY, localZ),
                                grad(permutations[perm5], localX - 1.0D, localY, localZ)),
                        lerp(smoothedX,
                                grad(permutations[perm3], localX, localY - 1.0D, localZ),
                                grad(permutations[perm6], localX - 1.0D, localY - 1.0D, localZ))),
                    lerp(smoothedY,
                            lerp(smoothedX,
                                    grad(permutations[perm2 + 1], localX, localY, localZ - 1.0D),
                                    grad(permutations[perm5 + 1], localX - 1.0D, localY, localZ - 1.0D)),
                            lerp(smoothedX,
                                    grad(permutations[perm3 + 1], localX, localY - 1.0D, localZ - 1.0D),
                                    grad(permutations[perm6 + 1], localX - 1.0D, localY - 1.0D, localZ - 1.0D))));
    }

    public double lerp(double delta, double start, double end)
    {
        return start + delta * (end - start);
    }

    public double grad(int i, double d, double d1, double d2)
    {
        int j = i & 0xf;
        double d3 = j >= 8 ? d1 : d;
        double d4 = j >= 4 ? j != 12 && j != 14 ? d2 : d : d1;
        return ((j & 1) != 0 ? -d3 : d3) + ((j & 2) != 0 ? -d4 : d4);
    }

    // Note: Passing in the z value as the y coordinate is intended behavior here.
    public double sample(double x, double z)
    {
        return sample(x, z, 0.0);
    }

    // Alternate sample method that provides frequency and amplitude modification functionality
    public double sample(double x, double y, double z, double freqX, double freqY, double freqZ, double amplitude)
    {
        double noiseAmplitude = 1.0 / amplitude;
        double localX = (x + (double) 0) * freqX + offsetX;
        int floorX = (int) localX;
        if(localX < (double) floorX)
        {
            floorX--;
        }
        int maskX = floorX & 0xff;
        localX -= floorX;
        double smoothedX = localX * localX * localX * (localX * (localX * 6D - 15D) + 10D);
        double localZ = (z + (double) 0) * freqZ + offsetZ;
        int floorZ = (int) localZ;
        if(localZ < (double) floorZ)
        {
            floorZ--;
        }
        int maskZ = floorZ & 0xff;
        localZ -= floorZ;
        double smoothZ = localZ * localZ * localZ * (localZ * (localZ * 6D - 15D) + 10D);
        double localY = (y + (double) 0) * freqY + offsetY;
        int floorY = (int) localY;
        if(localY < (double) floorY)
        {
            floorY--;
        }
        int maskY = floorY & 0xff;
        localY -= floorY;
        double smoothY = localY * localY * localY * (localY * (localY * 6D - 15D) + 10D);
        int perm1 = permutations[maskX] + maskY;
        int perm2 = permutations[perm1] + maskZ;
        int perm3 = permutations[perm1 + 1] + maskZ;
        int perm4 = permutations[maskX + 1] + maskY;
        int perm5 = permutations[perm4] + maskZ;
        int perm6 = permutations[perm4 + 1] + maskZ;
        double lerp1 = lerp(smoothedX, grad(permutations[perm2], localX, localY, localZ), grad(permutations[perm5], localX - 1.0D, localY, localZ));
        double lerp2 = lerp(smoothedX, grad(permutations[perm3], localX, localY - 1.0D, localZ), grad(permutations[perm6], localX - 1.0D, localY - 1.0D, localZ));
        double lerp3 = lerp(smoothedX, grad(permutations[perm2 + 1], localX, localY, localZ - 1.0D), grad(permutations[perm5 + 1], localX - 1.0D, localY, localZ - 1.0D));
        double lerp4 = lerp(smoothedX, grad(permutations[perm3 + 1], localX, localY - 1.0D, localZ - 1.0D), grad(permutations[perm6 + 1], localX - 1.0D, localY - 1.0D, localZ - 1.0D));
        double biLerp1 = lerp(smoothY, lerp1, lerp2);
        double biLerp2 = lerp(smoothY, lerp3, lerp4);
        double finalNoise = lerp(smoothZ, biLerp1, biLerp2);

        return finalNoise * noiseAmplitude;
    }
}