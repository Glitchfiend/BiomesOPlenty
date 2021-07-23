package biomesoplenty.common.world;

import java.util.Random;

public class AlphaOctavePerlinNoise
{
    private AlphaPerlinNoise samplers[];
    private int octaves;

    public AlphaOctavePerlinNoise(Random random, int octaves)
    {
        this.octaves = octaves;
        samplers = new AlphaPerlinNoise[octaves];
        for(int i = 0; i < octaves; i++)
        {
            samplers[i] = new AlphaPerlinNoise(random);
        }

    }

    public double sample(double x, double z)
    {
        double sum = 0.0;
        double amplitude = 1.0;
        for(int i = 0; i < this.octaves; i++)
        {
            sum += this.samplers[i].sample(x * amplitude, z * amplitude) / amplitude;
            amplitude /= 2.0;
        }

        return sum;
    }

    public double sample(double x, double y, double z, double xFreq, double yFreq, double zFreq)
    {
        double sum = 0.0;

        double amplitude = 1.0D;
        for(int i = 0; i < octaves; i++)
        {
            sum += samplers[i].sample(x, y, z, xFreq * amplitude, yFreq * amplitude, zFreq * amplitude, amplitude);
            amplitude /= 2D;
        }

        return sum;
    }
}
