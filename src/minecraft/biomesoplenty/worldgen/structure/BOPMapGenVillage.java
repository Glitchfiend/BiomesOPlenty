package biomesoplenty.worldgen.structure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import biomesoplenty.configuration.BOPConfiguration;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureStart;

public class BOPMapGenVillage extends MapGenStructure
{
    /** A list of all the biomes villages can spawn in. */
    public static List villageSpawnBiomes = Arrays.asList(new BiomeGenBase[] {BiomeGenBase.plains, BiomeGenBase.desert});

    /** World terrain type, 0 for normal, 1 for flat map */
    private int terrainType;
    private int maxDistance;
    private int minDistance;

    public BOPMapGenVillage()
    {
        this.terrainType = 0;
        this.maxDistance = BOPConfiguration.villageDistance;
        this.minDistance = BOPConfiguration.villageDistance / 4;
        villageSpawnBiomes = MapGenVillage.villageSpawnBiomes;
    }

    public BOPMapGenVillage(Map par1Map)
    {
        this();
        Iterator iterator = par1Map.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();

            if (((String)entry.getKey()).equals("size"))
            {
                this.terrainType = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.terrainType, 0);
            }
            else if (((String)entry.getKey()).equals("distance"))
            {
                this.maxDistance = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), this.maxDistance, this.minDistance + 1);
            }
        }
    }

    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        int k = par1;
        int l = par2;

        if (par1 < 0)
        {
            par1 -= this.maxDistance - 1;
        }

        if (par2 < 0)
        {
            par2 -= this.maxDistance - 1;
        }

        int i1 = par1 / this.maxDistance;
        int j1 = par2 / this.maxDistance;
        Random random = this.worldObj.setRandomSeed(i1, j1, 10387312);
        i1 *= this.maxDistance;
        j1 *= this.maxDistance;
        i1 += random.nextInt(this.maxDistance - this.minDistance);
        j1 += random.nextInt(this.maxDistance - this.minDistance);

        if (k == i1 && l == j1)
        {
            boolean flag = this.worldObj.getWorldChunkManager().areBiomesViable(k * 16 + 8, l * 16 + 8, 0, villageSpawnBiomes);

            if (flag)
            {
                return true;
            }
        }

        return false;
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
        return new BOPStructureVillageStart(this.worldObj, this.rand, par1, par2, this.terrainType);
    }
}
