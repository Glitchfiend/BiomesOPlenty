package biomesoplenty.common.biome;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.biome.decoration.BOPNetherBiomeDecorator;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;

public class BOPNetherBiome extends BOPBiome<BOPNetherBiomeDecorator> {
    public BOPNetherBiome(int id) {
        super(id, BOPNetherBiomeDecorator.class);

        this.setDisableRain();
        this.setTemperatureRainfall(2.0F, 0.0F);

        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();

        this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
    }
}
