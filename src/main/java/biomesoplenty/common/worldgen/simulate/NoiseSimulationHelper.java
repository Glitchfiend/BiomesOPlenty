package biomesoplenty.common.worldgen.simulate;

import net.minecraft.SharedConstants;
import net.minecraft.core.QuartPos;
import net.minecraft.util.Mth;
import net.minecraft.util.VisibleForDebug;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.NoiseSampler;
import net.minecraft.world.level.levelgen.TerrainInfo;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class NoiseSimulationHelper implements Climate.Sampler {
    private static final NormalNoise.NoiseParameters SHIFT = new NormalNoise.NoiseParameters(-3, 1.0, 1.0, 1.0, 0.0);
    private static final NormalNoise.NoiseParameters TEMPERATURE = new NormalNoise.NoiseParameters(-10, 1.5, 0.0, 1.0, 0.0, 0.0, 0.0);
    private static final NormalNoise.NoiseParameters HUMIDITY = new NormalNoise.NoiseParameters(-8, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0);
    private static final NormalNoise.NoiseParameters CONTINENTALNESS = new NormalNoise.NoiseParameters(-9, 1.0, 1.0, 2.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0);
    private static final NormalNoise.NoiseParameters EROSION = new NormalNoise.NoiseParameters(-9, 1.0, 1.0, 0.0, 1.0, 1.0);
    private static final NormalNoise.NoiseParameters WEIRDNESS = new NormalNoise.NoiseParameters( -7, 1.0, 2.0, 1.0, 0.0, 0.0, 0.0);

    private final NormalNoise offsetNoise;
    private final NormalNoise temperatureNoise;
    private final NormalNoise humidityNoise;
    private final NormalNoise continentalnessNoise;
    private final NormalNoise erosionNoise;
    private final NormalNoise weirdnessNoise;
    private final TerrainShaper terrainShaper = TerrainShaper.overworld(false);

    public NoiseSimulationHelper(long seed) {
        LegacyRandomSource random = new LegacyRandomSource(seed);

        this.offsetNoise = NormalNoise.create(random, SHIFT);
        this.temperatureNoise = NormalNoise.create(random, TEMPERATURE);
        this.humidityNoise = NormalNoise.create(random, HUMIDITY);
        this.continentalnessNoise = NormalNoise.create(random, CONTINENTALNESS);
        this.erosionNoise = NormalNoise.create(random, EROSION);
        this.weirdnessNoise = NormalNoise.create(random, WEIRDNESS);
    }

    @Override
    public Climate.TargetPoint sample(int i, int j, int k) {
        return this.target(i, j, k, this.noiseData(i, k, Blender.empty()));
    }

    public double offset(int y, TerrainInfo info) {
        double g = (this.computeBaseDensity(y, info) + 0) * info.factor();
        return g * (double)(g > 0.0 ? 4 : 1);
    }

    public NoiseSampler.FlatNoiseData noiseData(int i, int j, Blender blender) {
        double d = (double)i + this.getOffset(i, 0, j);
        double e = (double)j + this.getOffset(j, i, 0);
        double f = this.getContinentalness(d, 0.0, e);
        double g = this.getWeirdness(d, 0.0, e);
        double h = this.getErosion(d, 0.0, e);
        TerrainInfo terrainInfo = this.terrainInfo(QuartPos.toBlock(i), QuartPos.toBlock(j), (float)f, (float)g, (float)h, blender);
        return new NoiseSampler.FlatNoiseData(d, e, f, g, h, terrainInfo);
    }

    public TerrainInfo terrainInfo(int i, int j, float f, float g, float h, Blender blender) {
        TerrainShaper.Point point = terrainShaper.makePoint(f, h, g);
        float k = terrainShaper.offset(point);
        float l = terrainShaper.factor(point);
        float m = terrainShaper.jaggedness(point);
        TerrainInfo terrainInfo = new TerrainInfo((double)k, (double)l, (double)m);
        return blender.blendOffsetAndFactor(i, j, terrainInfo);
    }

    private double computeBaseDensity(int i, TerrainInfo terrainInfo) {
        double d = 1.0 - (double)i / 128.0;
        return d + terrainInfo.offset();
    }

    @VisibleForDebug
    public Climate.TargetPoint target(int i, int j, int k, NoiseSampler.FlatNoiseData flatNoiseData) {
        double d = flatNoiseData.shiftedX();
        double e = (double)j + this.getOffset(j, k, i);
        double f = flatNoiseData.shiftedZ();
        double baseDensity = this.computeBaseDensity(QuartPos.toBlock(j), flatNoiseData.terrainInfo());
        return Climate.target(
                (float)this.getTemperature(d, e, f),
                (float)this.getHumidity(d, e, f),
                (float)flatNoiseData.continentalness(),
                (float)flatNoiseData.erosion(),
                (float) baseDensity,
                (float)flatNoiseData.weirdness());
    }

    public double getOffset(int i, int j, int k) {
        return this.offsetNoise.getValue((double)i, (double)j, (double)k) * 4.0;
    }

    private double getTemperature(double d, double e, double f) {
        return this.temperatureNoise.getValue(d, 0.0, f);
    }

    private double getHumidity(double d, double e, double f) {
        return this.humidityNoise.getValue(d, 0.0, f);
    }

    @VisibleForDebug
    public double getContinentalness(double d, double e, double f) {
        if (SharedConstants.debugGenerateSquareTerrainWithoutNoise) {
            if (SharedConstants.debugVoidTerrain(new ChunkPos(QuartPos.toSection(Mth.floor(d)), QuartPos.toSection(Mth.floor(f))))) {
                return -1.0;
            } else {
                double g = Mth.frac(d / 2048.0) * 2.0 - 1.0;
                return g * g * (double)(g < 0.0 ? -1 : 1);
            }
        } else if (SharedConstants.debugGenerateStripedTerrainWithoutNoise) {
            double g = d * 0.005;
            return Math.sin(g + 0.5 * Math.sin(g));
        } else {
            return this.continentalnessNoise.getValue(d, e, f);
        }
    }

    @VisibleForDebug
    public double getErosion(double d, double e, double f) {
        if (SharedConstants.debugGenerateSquareTerrainWithoutNoise) {
            if (SharedConstants.debugVoidTerrain(new ChunkPos(QuartPos.toSection(Mth.floor(d)), QuartPos.toSection(Mth.floor(f))))) {
                return -1.0;
            } else {
                double g = Mth.frac(f / 256.0) * 2.0 - 1.0;
                return g * g * (double)(g < 0.0 ? -1 : 1);
            }
        } else if (SharedConstants.debugGenerateStripedTerrainWithoutNoise) {
            double g = f * 0.005;
            return Math.sin(g + 0.5 * Math.sin(g));
        } else {
            return this.erosionNoise.getValue(d, e, f);
        }
    }

    @VisibleForDebug
    public double getWeirdness(double d, double e, double f) {
        return this.weirdnessNoise.getValue(d, e, f);
    }
}
