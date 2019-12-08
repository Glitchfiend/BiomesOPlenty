/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.command;

import biomesoplenty.common.util.biome.BiomeUtil;
import biomesoplenty.common.util.block.BlockUtil;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandTpBiome
{
    private static final ExecutorService TP_BIOME_THREAD = Executors.newFixedThreadPool(1);

    static ArgumentBuilder<CommandSource, ?> register()
    {
        return Commands.literal("tpbiome")
                .then(Commands.argument("biome", BiomeArgument.createArgument())
                        .executes(ctx -> {
                            ServerPlayerEntity player = ctx.getSource().asPlayer();
                            TP_BIOME_THREAD.execute(() -> {
                                findTeleportBiome(ctx.getSource(), player, BiomeArgument.getValue(ctx, "biome"));
                            });
                            return 1;
                        }));

    }

    private static int findTeleportBiome(CommandSource cs, ServerPlayerEntity player, Biome biome)
    {
        World world = player.world;
        BlockPos closestBiomePos = biome == null ? null : BiomeUtil.spiralOutwardsLookingForBiome(world, biome, player.posX, player.posZ);
        String biomeName = biome != null && world.isRemote ? biome.getDisplayName().toString() : biome.getRegistryName().toString();

        if (closestBiomePos != null)
        {
            double x = (double)closestBiomePos.getX();
            double y = (double) BlockUtil.getTopSolidOrLiquidBlock(world, closestBiomePos.getX(), closestBiomePos.getZ()).getY();
            double z = (double)closestBiomePos.getZ();

            if (!world.getDimension().isSurfaceWorld())
            {
                y = (double)getTopBlockNonOverworld(world, closestBiomePos).getY();
            }

            player.connection.setPlayerLocation(x, y, z, player.rotationYaw, player.rotationPitch);
            cs.sendFeedback(new TranslationTextComponent("commands.biomesoplenty.tpbiome.success", player.getName(), biomeName, x, y, z), true);
        }
        else
        {
            cs.sendFeedback(new TranslationTextComponent("commands.biomesoplenty.tpbiome.error", biomeName), true);
        }

        return 1;
    }

    public static BlockPos getTopBlockNonOverworld(World world, BlockPos pos)
    {
        IChunk chunk = world.getChunk(pos);
        BlockPos blockpos;
        BlockPos blockpos1;
        BlockPos blockpos2 = new BlockPos(pos.getX(), chunk.getTopFilledSegment() + 16, pos.getZ());

        for (blockpos = blockpos2; blockpos.getY() >= 0; blockpos = blockpos1)
        {
            blockpos1 = blockpos.down();
            BlockState state = chunk.getBlockState(blockpos1);

            if (!state.getMaterial().blocksMovement() && !world.isAirBlock(blockpos1.down()) && state.getMaterial() != Material.LEAVES)
            {
                return blockpos1;
            }
        }

        return blockpos2;
    }
}
