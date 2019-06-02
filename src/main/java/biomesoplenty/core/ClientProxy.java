/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.core;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.api.particle.BOPParticleTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.init.Particles;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColors;

public class ClientProxy extends CommonProxy
{
    public ClientProxy()
    {

    }

    @Override
    public void init()
    {
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();

        //Grass Coloring
        blockColors.register((state, world, pos, tintIndex) ->
            world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D),
            BOPBlocks.watergrass);
        
        //Foliage Coloring
        blockColors.register((state, world, pos, tintIndex) ->
	        world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefault(),
	        BOPBlocks.bush, BOPBlocks.flowering_oak_leaves, BOPBlocks.mahogany_leaves, BOPBlocks.palm_leaves,
	        BOPBlocks.willow_leaves, BOPBlocks.willow_vine);
        
        //Item Coloring
        itemColors.register((stack, tintIndex) -> {
            IBlockState iblockstate = ((ItemBlock)stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(iblockstate, null, null, tintIndex); }, 
        	BOPBlocks.bush, BOPBlocks.flowering_oak_leaves, BOPBlocks.mahogany_leaves,
        	BOPBlocks.palm_leaves, BOPBlocks.willow_leaves, BOPBlocks.willow_vine);
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
