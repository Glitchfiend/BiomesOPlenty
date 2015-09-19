/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.core;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.api.particle.BOPParticleTypes;
import biomesoplenty.client.particle.*;
import biomesoplenty.common.config.MiscConfigurationHandler;
import biomesoplenty.common.entities.*;
import biomesoplenty.common.entities.projectiles.*;

public class ClientProxy extends CommonProxy
{
    public static ResourceLocation[] bopTitlePanoramaPaths = new ResourceLocation[] {new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_0.png"), new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_1.png"), new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_2.png"), new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_3.png"), new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_4.png"), new ResourceLocation("biomesoplenty:textures/gui/title/background/panorama_5.png")};
    public static ResourceLocation particleTexturesLocation = new ResourceLocation("biomesoplenty:textures/particles/particles.png");
    
    @Override
    public void registerRenderers()
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        
        if (MiscConfigurationHandler.overrideTitlePanorama)
            GuiMainMenu.titlePanoramaPaths = bopTitlePanoramaPaths;
            
        //Entity rendering and other stuff will go here in future
        RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart(minecraft.getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityWasp.class, new RenderWasp(minecraft.getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityPixie.class, new RenderPixie(minecraft.getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderMudball(minecraft.getRenderManager(), BOPItems.mudball, minecraft.getRenderItem()));
        //RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, new RenderSnowball(minecraft.getRenderManager(), BOPItems.mudball, minecraft.getRenderItem()));


        ITextureObject particleTextures = (ITextureObject)(new SimpleTexture(particleTexturesLocation));
        minecraft.renderEngine.loadTexture(particleTexturesLocation, particleTextures);
    }
    
    @Override
    public void registerItemVariantModel(Item item, String name, int metadata) 
    {
        if (item != null) 
        { 
            ModelBakery.addVariantName(item, BiomesOPlenty.MOD_ID + ":" + name);
            ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(BiomesOPlenty.MOD_ID + ":" + name, "inventory"));
        }
    }
    
    @Override
    public void registerNonRenderingProperties(Block block) 
    {
        if (block instanceof IBOPBlock)
        {
            IBOPBlock bopBlock = (IBOPBlock)block;
            IProperty[] nonRenderingProperties = bopBlock.getNonRenderingProperties();
            
            if (nonRenderingProperties != null)
            {
                // use a custom state mapper which will ignore the properties specified in the block as being non-rendering
                IStateMapper custom_mapper = (new StateMap.Builder()).addPropertiesToIgnore(nonRenderingProperties).build();
                ModelLoader.setCustomStateMapper(block, custom_mapper);
            }
        }
    }
    
    @Override
    public void registerFluidBlockRendering(Block block, String name) 
    {
        final ModelResourceLocation fluidLocation = new ModelResourceLocation(BiomesOPlenty.MOD_ID.toLowerCase() + ":fluids", name);
        
        // use a custom state mapper which will ignore the LEVEL property
        ModelLoader.setCustomStateMapper(block, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return fluidLocation;
            }
        });
    }
    
    @Override
    public void spawnParticle(BOPParticleTypes type, double x, double y, double z)
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityFX entityFx = null;
        switch (type)
        {
            case PIXIETRAIL:
                entityFx = new EntityPixieTrailFX(minecraft.theWorld, x, y, z, MathHelper.getRandomDoubleInRange(minecraft.theWorld.rand, -0.03, 0.03), -0.02D, MathHelper.getRandomDoubleInRange(minecraft.theWorld.rand, -0.03, 0.03));
                break;
            case DANDELION:
                entityFx = new EntityDandelionFX(minecraft.theWorld, x, y, z, 2.0F);
                break;
            case MUD:
                int itemId = Item.getIdFromItem(BOPItems.mudball);
                minecraft.theWorld.spawnParticle(EnumParticleTypes.ITEM_CRACK, x, y, z, MathHelper.getRandomDoubleInRange(minecraft.theWorld.rand, -0.08D, 0.08D), MathHelper.getRandomDoubleInRange(minecraft.theWorld.rand, -0.08D, 0.08D), MathHelper.getRandomDoubleInRange(minecraft.theWorld.rand, -0.08D, 0.08D), new int[] {itemId});
                return;
            case PLAYER_TRAIL:
                entityFx = new EntityTrailFX(minecraft.theWorld, x, y, z);
                break;
            default:
                break;
        }
        
        if (entityFx != null) {minecraft.effectRenderer.addEffect(entityFx);}
    }

}
