package powercrystals.minefactoryreloaded.api;

import net.minecraft.world.World;

import java.util.List;


public interface IRandomMobProvider {
    public List<RandomMob> getRandomMobs(World world);
}