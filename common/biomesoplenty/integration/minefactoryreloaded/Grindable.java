package biomesoplenty.integration.minefactoryreloaded;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.IFactoryGrindable;
import powercrystals.minefactoryreloaded.api.MobDrop;

public class Grindable implements IFactoryGrindable
{
    private Class<?> grindableClass;
    private List<MobDrop> drops;
    
    public Grindable(Class<?> entityToGrind, MobDrop[] dropStacks)
    {
        grindableClass = entityToGrind;
        drops = new ArrayList<MobDrop>();
        for(MobDrop d : dropStacks)
        {
            drops.add(d);
        }
    }
    
    @Override
    public Class<?> getGrindableEntity()
    {
        return grindableClass;
    }
    
    @Override
    public List<MobDrop> grind(World world, EntityLiving entity, Random random)
    {
        return drops;
    }
}
