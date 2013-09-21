package powercrystals.minefactoryreloaded.api;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * @author PowerCrystals
 *         <p/>
 *         Defines a grindable entity for the Grinder.
 */
@SuppressWarnings("deprecation")
public interface IFactoryGrindable2 extends IFactoryGrindable {
    /**
     * @return The class that this grindable instance is handling. This must be a subtype of EntityLiving or the entity will never
     * be noticed by the Grinder.
     */
    @Override
    public Class<?> getGrindableEntity();

    /**
     * @param world  The world this entity is in.
     * @param entity The entity instance being ground.
     * @param random A Random instance.
     * @return The drops generated when this entity is killed.
     */
    @Override
    public List<MobDrop> grind(World world, EntityLiving entity, Random random);

    /**
     * @param entity The entity instance being ground.
     * @return Whether this entity has been fully processed or not.
     */
    public boolean processEntity(EntityLiving entity);
}
