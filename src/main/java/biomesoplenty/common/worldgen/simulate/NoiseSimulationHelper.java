package biomesoplenty.common.worldgen.simulate;

import biomesoplenty.common.worldgen.BOPClimate;
import biomesoplenty.common.worldgen.BOPNoiseSampler;
import biomesoplenty.common.worldgen.noise.Area;
import biomesoplenty.common.worldgen.noise.LayeredNoiseUtil;
import net.minecraft.SharedConstants;
import net.minecraft.core.QuartPos;
import net.minecraft.util.Mth;
import net.minecraft.util.VisibleForDebug;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.TerrainInfo;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class NoiseSimulationHelper implements BOPClimate.Sampler
{
    private static final NormalNoise.NoiseParameters SHIFT = new NormalNoise.NoiseParameters(-3, 1.0, 1.0, 1.0, 0.0);
    private static final NormalNoise.NoiseParameters TEMPERATURE = new NormalNoise.NoiseParameters(-10, 1.5, 0.0, 1.0, 0.0, 0.0, 0.0);
    private static final NormalNoise.NoiseParameters HUMIDITY = new NormalNoise.NoiseParameters(-8, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0);
    private static final NormalNoise.NoiseParameters CONTINENTALNESS = new NormalNoise.NoiseParameters(-9, 1.0, 1.0, 2.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0);
    private static final NormalNoise.NoiseParameters EROSION = new NormalNoise.NoiseParameters(-9, 1.0, 1.0, 0.0, 1.0, 1.0);
    private static final NormalNoise.NoiseParameters WEIRDNESS = new NormalNoise.NoiseParameters(-7, 1.0, 2.0, 1.0, 0.0, 0.0, 0.0);
    protected static final NormalNoise.NoiseParameters RARENESS = new NormalNoise.NoiseParameters(-9, 0.6D, 1.5D, 0.6D, 0.0D, 0.0D);

    protected final LegacyRandomSource random;
    private final NormalNoise offsetNoise;
    private final NormalNoise temperatureNoise;
    private final NormalNoise humidityNoise;
    private final NormalNoise continentalnessNoise;
    private final NormalNoise erosionNoise;
    private final NormalNoise weirdnessNoise;
    private final Area uniquenessNoise;
    protected NormalNoise rarenessNoise;
    private final TerrainShaper terrainShaper = TerrainShaper.overworld(false);

    public NoiseSimulationHelper(long seed)
    {
        this.random = new LegacyRandomSource(seed);

        this.offsetNoise = NormalNoise.create(random, SHIFT);
        this.temperatureNoise = NormalNoise.create(random, TEMPERATURE);
        this.humidityNoise = NormalNoise.create(random, HUMIDITY);
        this.continentalnessNoise = NormalNoise.create(random, CONTINENTALNESS);
        this.erosionNoise = NormalNoise.create(random, EROSION);
        this.weirdnessNoise = NormalNoise.create(random, WEIRDNESS);
        this.uniquenessNoise = LayeredNoiseUtil.uniqueness(seed, 3);
        this.rarenessNoise = NormalNoise.create(random, RARENESS);
    }

    @Override
    public BOPClimate.TargetPoint sampleBOP(int i, int j, int k)
    {
        return this.target(i, j, k, this.noiseData(i, k, Blender.empty()));
    }

    public double offset(int y, TerrainInfo info)
    {
        double g = (this.computeBaseDensity(y, info) + 0) * info.factor();
        return g * (double) (g > 0.0 ? 4 : 1);
    }

    public BOPNoiseSampler.BOPFlatNoiseData noiseData(int i, int j, Blender blender)
    {
        double d = (double) i + this.getOffset(i, 0, j);
        double e = (double) j + this.getOffset(j, i, 0);
        double f = this.getContinentalness(d, 0.0, e);
        double g = this.getWeirdness(d, 0.0, e);
        double k = this.getUniqueness(d, 0.0, e);
        double l = this.getRareness(d, 0.0, e);
        double h = this.getErosion(d, 0.0, e);
        TerrainInfo terrainInfo = this.terrainInfo(QuartPos.toBlock(i), QuartPos.toBlock(j), (float) f, (float) g, (float) h, blender);
        return new BOPNoiseSampler.BOPFlatNoiseData(d, e, f, g, k, l, h, terrainInfo);
    }

    public TerrainInfo terrainInfo(int i, int j, float f, float g, float h, Blender blender)
    {
        TerrainShaper.Point point = terrainShaper.makePoint(f, h, g);
        float k = terrainShaper.offset(point);
        float l = terrainShaper.factor(point);
        float m = terrainShaper.jaggedness(point);
        TerrainInfo terrainInfo = new TerrainInfo(k, l, m);
        return blender.blendOffsetAndFactor(i, j, terrainInfo);
    }

    private double computeBaseDensity(int i, TerrainInfo terrainInfo)
    {
        double d = 1.0 - (double) i / 128.0;
        return d + terrainInfo.offset();
    }

    @VisibleForDebug
    public BOPClimate.TargetPoint target(int i, int j, int k, BOPNoiseSampler.BOPFlatNoiseData flatNoiseData)
    {
        double d = flatNoiseData.shiftedX();
        double e = (double) j + this.getOffset(j, k, i);
        double f = flatNoiseData.shiftedZ();
        double baseDensity = this.computeBaseDensity(QuartPos.toBlock(j), flatNoiseData.terrainInfo());
        return BOPClimate.target(
                (float) this.getTemperature(d, e, f),
                (float) this.getHumidity(d, e, f),
                (float) flatNoiseData.continentalness(),
                (float) flatNoiseData.erosion(),
                (float) baseDensity,
                (float) flatNoiseData.weirdness(),
                (float) flatNoiseData.uniqueness(),
                (float) flatNoiseData.rareness());
    }

    public double getOffset(int i, int j, int k)
    {
        return this.offsetNoise.getValue(i, j, k) * 4.0;
    }

    private double getTemperature(double d, double e, double f)
    {
        return this.temperatureNoise.getValue(d, 0.0, f);
    }

    private double getHumidity(double d, double e, double f)
    {
        return this.humidityNoise.getValue(d, 0.0, f);
    }

    @VisibleForDebug
    public double getContinentalness(double d, double e, double f)
    {
        if (SharedConstants.debugGenerateSquareTerrainWithoutNoise)
        {
            if (SharedConstants.debugVoidTerrain(new ChunkPos(QuartPos.toSection(Mth.floor(d)), QuartPos.toSection(Mth.floor(f)))))
            {
                return -1.0;
            } else
            {
                double g = Mth.frac(d / 2048.0) * 2.0 - 1.0;
                return g * g * (double) (g < 0.0 ? -1 : 1);
            }
        } else if (SharedConstants.debugGenerateStripedTerrainWithoutNoise)
        {
            double g = d * 0.005;
            return Math.sin(g + 0.5 * Math.sin(g));
        } else
        {
            return this.continentalnessNoise.getValue(d, e, f);
        }
    }

    @VisibleForDebug
    public double getErosion(double d, double e, double f)
    {
        if (SharedConstants.debugGenerateSquareTerrainWithoutNoise)
        {
            if (SharedConstants.debugVoidTerrain(new ChunkPos(QuartPos.toSection(Mth.floor(d)), QuartPos.toSection(Mth.floor(f)))))
            {
                return -1.0;
            } else
            {
                double g = Mth.frac(f / 256.0) * 2.0 - 1.0;
                return g * g * (double) (g < 0.0 ? -1 : 1);
            }
        } else if (SharedConstants.debugGenerateStripedTerrainWithoutNoise)
        {
            double g = f * 0.005;
            return Math.sin(g + 0.5 * Math.sin(g));
        } else
        {
            return this.erosionNoise.getValue(d, e, f);
        }
    }

    @VisibleForDebug
    public double getWeirdness(double d, double e, double f)
    {
        return this.weirdnessNoise.getValue(d, e, f);
    }

    @VisibleForDebug
    public double getUniqueness(double d, double e, double f)
    {
        return BOPClimate.unquantizeCoord(this.uniquenessNoise.get((int)d, (int)f));
    }

    @VisibleForDebug
    public double getRareness(double d, double e, double f)
    {
        return this.rarenessNoise.getValue(d, e, f);
    }
}
