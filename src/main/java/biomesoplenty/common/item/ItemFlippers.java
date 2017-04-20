package biomesoplenty.common.item;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPHive;
import biomesoplenty.common.entities.projectiles.EntityMudball;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemFlippers extends ItemArmor
{
    
    public ItemFlippers(ItemArmor.ArmorMaterial material, int renderIndex)
    {
        // flippers are always on your feet - armorType = 3
        super(material, renderIndex, EntityEquipmentSlot.FEET);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        RayTraceResult rayTrace = this.rayTrace(world, player, false);

       /* if (rayTrace != null && rayTrace.typeOfHit == RayTraceResult.Type.BLOCK)
        {
            int halfHeight = 7;
            int maxRadius = 9;
            int layerSize = 3;
            int bottomExtra = 4;
            boolean empty = true;
            BlockPos hitPos = rayTrace.getBlockPos();

            // create the top and bottom halves of the hive, and a little bit extra on the bottom for the sake of looking
            // slightly more like a hive
            for (int yOffset = halfHeight; yOffset >= -halfHeight - bottomExtra; yOffset--)
            {
                // y is already negative, so add it rather than subtract
                int radius = maxRadius + (yOffset / layerSize) * (yOffset >= 0 ? -1 : 1);

                for (int xOffset = -radius; xOffset <= radius; xOffset++)
                {
                    for (int zOffset = -radius; zOffset <= radius; zOffset++)
                    {
                        int x2_z2 = xOffset * xOffset + zOffset * zOffset;

                        // used to determine where to fill with honey
                        boolean bottomHalf = yOffset <= 0;

                        // check to fill the top and bottom of the two layers
                        boolean outerCap = yOffset == -halfHeight - bottomExtra || yOffset == halfHeight;
                        boolean innerCap = yOffset == -halfHeight - bottomExtra + 1 || yOffset == halfHeight - 1;

                        // create a circular outline for the hive. the bottom and top layers should be filled.
                        // add a bit extra (1) to make the circles nicer too

                        // inner layer. inset by one block
                        if (x2_z2 <= (radius - 1) * (radius - 1) + 1 && (x2_z2 >= (radius - 2) * (radius - 2) || innerCap))
                        {
                            IBlockState honeyComb = BOPBlocks.hive.getDefaultState().withProperty(BlockBOPHive.VARIANT, BlockBOPHive.HiveType.EMPTY_HONEYCOMB);
                            float f = world.rand.nextFloat();

                            if (!empty && f <= 0.95)
                            {
                                honeyComb = BOPBlocks.hive.getDefaultState().withProperty(BlockBOPHive.VARIANT, BlockBOPHive.HiveType.HONEYCOMB);

                                // if on the bottom half of the hive bias more towards filled honeycomb.
                                // the rest of the hive can still have filled blocks though
                                if (f <= 0.2 || (f <= 0.65 && bottomHalf))
                                {
                                    honeyComb = BOPBlocks.hive.getDefaultState().withProperty(BlockBOPHive.VARIANT, BlockBOPHive.HiveType.FILLED_HONEYCOMB);
                                }
                            }
                            else if (empty && f <= 0.2)
                            {
                                honeyComb = Blocks.AIR.getDefaultState();
                            }

                            // offset so we're placing the hive underneath the initial y coordinate
                            world.setBlockState(hitPos.add(xOffset, yOffset - halfHeight, zOffset), honeyComb);
                        }

                        // inner fill
                        if (!empty && bottomHalf && x2_z2 <= (radius - 2) * (radius - 2) + 1)
                        {
                            // fill the centre of the hive with honey blocks honey
                            IBlockState fillBlock = yOffset == 0 ?  BOPBlocks.honey_block.getDefaultState() : BOPBlocks.honey.getDefaultState();
                            world.setBlockState(hitPos.add(xOffset, yOffset - halfHeight, zOffset), fillBlock);
                        }

                        // place a wasp spawner in the middle of the hive
                        if (!empty && yOffset == 0 && xOffset == 0 && zOffset == 0)
                        {
                            BlockPos spawnerPos = hitPos.add(xOffset, yOffset - halfHeight, zOffset);

                            world.setBlockState(spawnerPos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
                            TileEntity tileentity = world.getTileEntity(spawnerPos);

                            if (tileentity instanceof TileEntityMobSpawner)
                            {
                                MobSpawnerBaseLogic spawnerLogic = ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic();

                                NBTTagCompound spawnerConfig = new NBTTagCompound();
                                spawnerConfig.setShort("MinSpawnDelay", (short)100);
                                spawnerConfig.setShort("MaxSpawnDelay", (short)400);
                                spawnerConfig.setShort("SpawnCount", (short)6);
                                spawnerConfig.setShort("MaxNearbyEntities", (short)16);
                                spawnerConfig.setShort("RequiredPlayerRange", (short)24);

                                spawnerLogic.readFromNBT(spawnerConfig);
                                spawnerLogic.setEntityId(new ResourceLocation(BiomesOPlenty.MOD_ID, "wasp"));
                            }
                            else
                            {
                                BiomesOPlenty.logger.error("Failed to fetch mob spawner entity at ({}, {}, {})", new Object[] {Integer.valueOf(spawnerPos.getX()), Integer.valueOf(spawnerPos.getY()), Integer.valueOf(spawnerPos.getZ())});
                            }
                        }

                        // outer layer
                        if (x2_z2 <= radius * radius + 1 && (x2_z2 >= (radius - 1) * (radius - 1) || outerCap))
                        {
                            // offset so we're placing the hive underneath the initial y coordinate
                            world.setBlockState(hitPos.add(xOffset, yOffset - halfHeight, zOffset), BOPBlocks.hive.getDefaultState());
                        }
                    }
                }
            }
        }*/


        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
}

