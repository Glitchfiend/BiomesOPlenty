package biomesoplenty.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityWasp extends EntityLiving
{
    public EntityWasp(World world)
    {
        super(world); 
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    }
    
    public boolean isAIEnabled()
    {
        return true;
    }
    
    @Override
    public boolean allowLeashing()
    {
        return false;
    }
}
