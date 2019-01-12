/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.core;

import biomesoplenty.api.item.BOPItems;
import biomesoplenty.api.particle.BOPParticleTypes;
import biomesoplenty.common.entity.projectile.EntityMudball;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.entity.RenderSprite;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.init.Particles;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    public ClientProxy()
    {

    }

    @Override
    public void preInit()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, manager -> new RenderSprite<Entity>(manager, BOPItems.mudball, Minecraft.getInstance().getItemRenderer()));
    }

    @Override
    public void spawnParticle(BOPParticleTypes type, World parWorld, double x, double y, double z, Object... info)
    {
        Minecraft minecraft = Minecraft.getInstance();
        Particle entityFx = null;
        switch (type)
        {
            case MUD:
                int itemId = Item.getIdFromItem(BOPItems.mudball);
                minecraft.world.addParticle(new ItemParticleData(Particles.ITEM, new ItemStack(BOPItems.mudball)), x, y, z, MathHelper.nextDouble(parWorld.rand, -0.08D, 0.08D), MathHelper.nextDouble(parWorld.rand, -0.08D, 0.08D), MathHelper.nextDouble(parWorld.rand, -0.08D, 0.08D));
                return;
            /*case PLAYER_TRAIL:
                if (info.length < 1)
                    throw new RuntimeException("Missing argument for trail name!");

                entityFx = new EntityTrailFX(parWorld, x, y, z, (String)info[0]);
                break;
            case CURSE:
                entityFx = new EntityCurseFX(parWorld, x, y, z, MathHelper.nextDouble(parWorld.rand, -0.03, 0.03), 0.05D, MathHelper.nextDouble(parWorld.rand, -0.03, 0.03));
                break;*/
            default:
                break;
        }

        //if (entityFx != null) {minecraft.effectRenderer.addEffect(entityFx);}
    }
}
