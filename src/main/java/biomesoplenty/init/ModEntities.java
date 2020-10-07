/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.common.entity.DeerEntity;
import biomesoplenty.common.entity.DeerRenderer;
import biomesoplenty.common.entity.TurkeyEntity;
import biomesoplenty.common.entity.TurkeyRenderer;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.Lists;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities
{
    private static List<EntityType> entities = Lists.newArrayList();
    private static List<Item> spawnEggs = Lists.newArrayList();

    public static EntityType<DeerEntity> deer = addEntity(DeerEntity::new, EntityClassification.CREATURE, 1.0F, 1.6F, "deer", 0x765134, 0xF7EFE6);
    public static EntityType<TurkeyEntity> turkey = addEntity(TurkeyEntity::new, EntityClassification.CREATURE, 0.9F, 1.4F, "turkey", 0x6B492E, 0xE23131);

    public static <T extends AnimalEntity> EntityType<T> addEntity(EntityType.IFactory<T> type, EntityClassification classification, float width, float height, String name, int eggPrimary, int eggSecondary)
    {
        EntityType<T> entity = EntityType.Builder.of(type, classification).sized(width, height).build(BiomesOPlenty.MOD_ID + ":" + name);
        entity.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, name));
        entities.add(entity);

        Item spawnEgg = new SpawnEggItem(entity, eggPrimary, eggSecondary, (new Item.Properties()).tab(ItemGroupBOP.instance));
        spawnEgg.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, name + "_spawn_egg"));
        spawnEggs.add(spawnEgg);

        return entity;
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event)
    {
        for (EntityType entity : entities)
        {
            ForgeRegistries.ENTITIES.register(entity);
        }
    }

    @SubscribeEvent
    public static void registerSpawnEggs(RegistryEvent.Register<Item> event)
    {
        for (Item spawnEgg : spawnEggs)
        {
            ForgeRegistries.ITEMS.register(spawnEgg);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler(deer, DeerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(turkey, TurkeyRenderer::new);
    }

    public static void setup()
    {
        EntitySpawnPlacementRegistry.register(deer, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DeerEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(turkey, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, TurkeyEntity::checkAnimalSpawnRules);

        GlobalEntityTypeAttributes.put(deer, DeerEntity.createAttributes().build());
        GlobalEntityTypeAttributes.put(turkey, TurkeyEntity.createAttributes().build());
    }
}
