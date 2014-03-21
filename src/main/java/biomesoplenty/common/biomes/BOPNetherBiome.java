package biomesoplenty.common.biomes;

import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;

public class BOPNetherBiome extends BOPBiome
{
    public BOPNetherBiome(int id)
    {
        super(id);

        this.setDisableRain();
        this.setTemperatureRainfall(2.0F, 0.0F);

        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();

        this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));

        this.bopWorldFeatures.setFeature("stalagmitesPerChunk", 0);
        this.bopWorldFeatures.setFeature("stalactitesPerChunk", 0);
        this.bopWorldFeatures.setFeature("minersDelightPerChunk", 0);
        this.bopWorldFeatures.setFeature("rootsPerChunk", 0);
    }
}
