/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.generator;

import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.generation.BOPGeneratorBase;
import biomesoplenty.common.util.biome.GeneratorUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class GeneratorHellFossils extends BOPGeneratorBase
{
    public static class Builder extends BOPGeneratorBase.InnerBuilder<GeneratorHellFossils.Builder, GeneratorHellFossils> implements IGeneratorBuilder<GeneratorHellFossils>
    {
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
        }

        @Override
        public GeneratorHellFossils create()
        {
            return new GeneratorHellFossils(this.amountPerChunk);
        }
    }

    public GeneratorHellFossils(float amountPerChunk)
    {
        super(amountPerChunk);
    }

    private static final ResourceLocation STRUCTURE_SPINE_01 = new ResourceLocation("fossils/fossil_spine_01");
    private static final ResourceLocation STRUCTURE_SPINE_02 = new ResourceLocation("fossils/fossil_spine_02");
    private static final ResourceLocation STRUCTURE_SPINE_03 = new ResourceLocation("fossils/fossil_spine_03");
    private static final ResourceLocation STRUCTURE_SPINE_04 = new ResourceLocation("fossils/fossil_spine_04");
    private static final ResourceLocation[] FOSSILS = new ResourceLocation[] {STRUCTURE_SPINE_01, STRUCTURE_SPINE_02, STRUCTURE_SPINE_03, STRUCTURE_SPINE_04};

    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        return GeneratorUtils.ScatterYMethod.NETHER_SURFACE.getBlockPos(world, random, x, z);
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        Random chunkRand = world.getChunkFromBlockCoords(pos).getRandomWithSeed(987234911L);
        MinecraftServer server = world.getMinecraftServer();
        Rotation[] rotationValues = Rotation.values();
        // pick a random rotation
        Rotation rotation = rotationValues[random.nextInt(rotationValues.length)];
        int fossilIndex = chunkRand.nextInt(FOSSILS.length);
        TemplateManager fossilTemplate = world.getSaveHandler().getStructureTemplateManager();

        // choose a random fossil template
        Template template = fossilTemplate.getTemplate(server, FOSSILS[fossilIndex]);
        ChunkPos chunkPos = new ChunkPos(pos);

        StructureBoundingBox chunkBoundingBox = new StructureBoundingBox(chunkPos.getXStart(), 0, chunkPos.getZStart(), chunkPos.getXEnd(), 256, chunkPos.getZEnd());
        PlacementSettings settings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(chunkBoundingBox).setRandom(random);

        // offset the fossil slightly on the x and z axis
        int xOffset = chunkRand.nextInt(4);
        int zOffset = chunkRand.nextInt(4);

        // determine the initial position to build the fossil from, accounting for offsets and rotation
        BlockPos fossilZero = template.getZeroPositionWithTransform(pos.add(xOffset, -random.nextInt(4), zOffset), Mirror.NONE, rotation);
        // allow some blocks to be dropped during rotation to make it look better. only applicable to non 90 degrees angles?
        settings.setIntegrity(0.9F);
        template.addBlocksToWorld(world, fossilZero, settings, 20);
        return true;
    }

    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
    }
}
