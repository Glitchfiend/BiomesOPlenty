/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.handler;

import com.google.common.collect.Maps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.model.SimpleBakedModel;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class LeavesModelHandler
{
    private static final ModelResourceLocation LEAVES_LOC = new ModelResourceLocation("biomesoplenty:leaves_3", "variant=flowering");

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event) throws Exception
    {
        ModelLoader loader = event.getModelLoader();
        IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
        IBakedModel originalBakedModel = registry.getObject(LEAVES_LOC);

        // Load and bake the fast flowering leaves model
        IModel fastModel = ModelLoaderRegistry.getModel(new ResourceLocation("biomesoplenty:block/flowering_leaves_fast"));
        IBakedModel bakedFastModel = fastModel.bake(fastModel.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

        registry.putObject(LEAVES_LOC, new ModelLeaves(originalBakedModel, bakedFastModel));
    }

    public class ModelLeaves extends SimpleBakedModel
    {
        private IBakedModel fastModel;

        public ModelLeaves(IBakedModel fancyModel, IBakedModel fastModel)
        {
            super(fancyModel.getQuads(null, null, 0), generateFacingQuads(fancyModel), fancyModel.isAmbientOcclusion(), fancyModel.isGui3d(), fancyModel.getParticleTexture(), fancyModel.getItemCameraTransforms(), fancyModel.getOverrides());
            this.fastModel = fastModel;
        }

        @Override
        public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand)
        {
            return Blocks.LEAVES.isOpaqueCube(null) ? fastModel.getQuads(state, side, rand) : super.getQuads(state, side, rand);
        }
    }

    private static Map<EnumFacing, List<BakedQuad>> generateFacingQuads(IBakedModel model)
    {
        Map<EnumFacing, List<BakedQuad>> faceQuads = Maps.newEnumMap(EnumFacing.class);
        for (EnumFacing facing : EnumFacing.values())
        {
            faceQuads.put(facing, model.getQuads(null, facing, 0));
        }
        return faceQuads;
    }
}
