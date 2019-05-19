/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.util.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IProperty;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;

import javax.annotation.Nullable;
import java.util.Collection;

public class BlockUtil
{
    // Given a blockstate for some block which has an axis orientation (such as logs), try to determine the axis property
    public static IProperty getAxisProperty(IBlockState log)
    {
        for (IProperty property : log.getProperties())
        {
            Collection allowedValues = property.getAllowedValues();
            if (allowedValues.contains(EnumFacing.Axis.X) && allowedValues.contains(EnumFacing.Axis.Y) && allowedValues.contains(EnumFacing.Axis.Z))
            {
                return property;
            }
        }
        return null;
    }

    public static BlockPos getTopSolidOrLiquidBlock(IWorld world, int x, int z)
    {
        IChunk chunk = world.getChunk(x >> 4, z >> 4);
        return new BlockPos(x, chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, x & 15, z & 15), z);
    }
}
