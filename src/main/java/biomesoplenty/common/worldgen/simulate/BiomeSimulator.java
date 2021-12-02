package biomesoplenty.common.worldgen.simulate;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.biome.BOPOverworldBiomeBuilder;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.SharedConstants;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.NoiseSampler;
import net.minecraft.world.level.levelgen.blending.Blender;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BiomeSimulator {
    private static final Map<ResourceKey<Biome>, Integer> COLORS = new HashMap<>();
    static {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();

        COLORS.put(Biomes.PLAINS, 0x8DB360);
        COLORS.put(Biomes.SUNFLOWER_PLAINS, 0xa4bf84);
        COLORS.put(Biomes.FOREST, 0x056621);
        COLORS.put(Biomes.DARK_FOREST, 0x40511A);
        COLORS.put(Biomes.BIRCH_FOREST, 0x589C6C);
        COLORS.put(Biomes.SWAMP, 0x2FFFDA);
        COLORS.put(Biomes.SAVANNA, 0xBDB25F);
        COLORS.put(Biomes.SAVANNA_PLATEAU, 0xbda95f);
        COLORS.put(Biomes.BADLANDS, 0xD94515);
        COLORS.put(Biomes.ERODED_BADLANDS, 0xCA8C65);
        COLORS.put(Biomes.WOODED_BADLANDS, 0xB09765);
        COLORS.put(Biomes.BEACH, 0xFADE55);
        COLORS.put(Biomes.SNOWY_BEACH, 0xF7EBD2);
        COLORS.put(Biomes.SNOWY_PLAINS, 0xFFFFFF);
        COLORS.put(Biomes.SNOWY_TAIGA, 0x7CCCB2);
        COLORS.put(Biomes.ICE_SPIKES, 0x8086bf);
        COLORS.put(Biomes.TAIGA, 0x0B6659);
        COLORS.put(Biomes.OLD_GROWTH_PINE_TAIGA, 0x596651);
        COLORS.put(Biomes.OLD_GROWTH_SPRUCE_TAIGA, 0x596651);
        COLORS.put(Biomes.OLD_GROWTH_BIRCH_FOREST, 0x76b889);
        COLORS.put(Biomes.JUNGLE, 0x537B09);
        COLORS.put(Biomes.BAMBOO_JUNGLE, 0x7BA331);
        COLORS.put(Biomes.SPARSE_JUNGLE, 0x628B17);
        COLORS.put(Biomes.DESERT, 0xFA9418);
        COLORS.put(Biomes.RIVER, 0x3030AF);
        COLORS.put(Biomes.FROZEN_RIVER, 0xA0A0FF);
        COLORS.put(Biomes.FLOWER_FOREST, 0x2D8E49);

        COLORS.put(Biomes.OCEAN, 0x000070);
        COLORS.put(Biomes.WARM_OCEAN, 0x3ddbb6);
        COLORS.put(Biomes.LUKEWARM_OCEAN, 0x3dbbdb);
        COLORS.put(Biomes.COLD_OCEAN, 0x573ddb);
        COLORS.put(Biomes.FROZEN_OCEAN, 0x928acf);

        COLORS.put(Biomes.DEEP_OCEAN, 0x000030);
        COLORS.put(Biomes.DEEP_LUKEWARM_OCEAN, 0x2e879e);
        COLORS.put(Biomes.DEEP_COLD_OCEAN, 0x352585);
        COLORS.put(Biomes.DEEP_FROZEN_OCEAN, 0x57527a);

        COLORS.put(Biomes.MUSHROOM_FIELDS, 0x805f8a);

        COLORS.put(Biomes.WINDSWEPT_SAVANNA, 0xe6dc95);
        COLORS.put(Biomes.WINDSWEPT_HILLS, 0x606060);
        COLORS.put(Biomes.WINDSWEPT_FOREST, 0x4d5c4b);
        COLORS.put(Biomes.WINDSWEPT_GRAVELLY_HILLS, 0x4a4a4a);

        COLORS.put(Biomes.STONY_SHORE, 0x6b6b6b);
        COLORS.put(Biomes.STONY_PEAKS, 0x858585);

        COLORS.put(Biomes.MEADOW, 0x4a8f63);
        COLORS.put(Biomes.GROVE, 0x4a8f63);
        COLORS.put(Biomes.SNOWY_SLOPES, 0x8c8ea1);
        COLORS.put(Biomes.FROZEN_PEAKS, 0xe2e1ed);
        COLORS.put(Biomes.JAGGED_PEAKS, 0xababab);

        // Debug- shouldn't exist in visualization!
        COLORS.put(Biomes.LUSH_CAVES, 0x00FF00);
        COLORS.put(Biomes.DRIPSTONE_CAVES, 0xFF0000);

        COLORS.put(BOPBiomes.CONIFEROUS_FOREST, 0x3d7350);
    }

    public static void main(String[] args) {

        init(new NoiseSimulationHelper(new Random().nextLong()));
    }

    public static void init(NoiseSimulationHelper sampler) {
        ImmutableList.Builder<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> builder = ImmutableList.builder();
        (new BOPOverworldBiomeBuilder()).addBiomes(builder::add);

        Climate.ParameterList<ResourceKey<Biome>> params = new Climate.ParameterList<>(builder.build());

        BufferedImage image = new BufferedImage(2048, 2048, BufferedImage.TYPE_INT_RGB);

        Reference2IntOpenHashMap<ResourceKey<Biome>> map = new Reference2IntOpenHashMap<>();
        map.defaultReturnValue(0);

        for (int x = 0; x < 2048; x++) {
            if (x % 256 == 0) {
                System.out.println("Mapping... " + (x / 256));
            }

            for (int z = 0; z < 2048; z++) {
                NoiseSampler.FlatNoiseData data = sampler.noiseData(x, z, Blender.empty());
                int ay = 0;
                for (int y = -8; y < 40; y++) {
                    double offset = sampler.offset(y * 8, data.terrainInfo());

                    if (offset < -4) {
                        ay = y + 3;
                        break;
                    }
                }

                ResourceKey<Biome> value = params.findValue(sampler.sample(x, ay, z), Biomes.THE_VOID);

                if (!COLORS.containsKey(value)) throw new RuntimeException("Resource key not found: " + value);

                map.addTo(value, 1);

                image.setRGB(x, z, COLORS.getOrDefault(value, 0));
            }
        }

        for (Reference2IntMap.Entry<ResourceKey<Biome>> entry : map.reference2IntEntrySet()) {
            System.out.println(entry.getKey().location() + ": " + (entry.getIntValue() / (2048.0 * 2048.0)) * 100 + "%");
        }

        Path p = Paths.get(".", "run", "biomes.png");
        try {
            ImageIO.write(image, "png", p.toAbsolutePath().toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
