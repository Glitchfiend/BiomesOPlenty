/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.command;

import biomesoplenty.common.util.biome.BiomeUtils;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;
import java.util.List;

public class BOPCommand extends CommandBase
{
    public static int blockCount = 0;
    public static int itemCount = 0;
    public static int entityCount = 0;
    public static int biomeCount = 0;
    
    @Override
    public String getName()
    {
        return "biomesoplenty";
    }
    
    @Override
    public List getAliases()
    {
        return Lists.newArrayList("bop", "biomesop");
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "commands.biomesoplenty.usage";
    }
    
    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException
    {
        if (args.length < 1)
        {
            throw new WrongUsageException("commands.biomesoplenty.usage");
        }
        else if (args[0].equals("biomename"))
        {
            getBiomeName(sender, args);
        }
        else if (args[0].equals("tpbiome"))
        {
            teleportFoundBiome(sender, args);
        }
        else if (args[0].equals("stats"))
        {
            printStats(sender, args);
        }
        else if (args[0].equals("stripchunk"))
        {
            stripChunk(sender, args);
        }
    }
    
    private void getBiomeName(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 2)
        {
            throw new WrongUsageException("commands.biomesoplenty.biomename.usage");
        }
        
        int biomeId = parseInt(args[1], 0, 255);
        Biome biome = Biome.getBiome(biomeId);
        
        sender.sendMessage(new TextComponentTranslation("commands.biomesoplenty.biomename.success", biomeId, biome == null ? "Undefined" : biome.getBiomeName()));
    }
    
    private void teleportFoundBiome(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 2)
        {
            throw new WrongUsageException("commands.biomesoplenty.tpbiome.usage");
        }

        // Parse args[1] to find the biome to search for - search for a string matching the biome identifier, or an integer matching the biome id
        Biome biomeToFind = null;
        if (biomeToFind == null)
        {
            try {
                int biomeId = parseInt(args[1], 0, 255);
                biomeToFind = Biome.getBiome(biomeId);
            } catch (NumberInvalidException e) {
                biomeToFind = BiomeUtils.getBiomeForLoc(new ResourceLocation(args[1]));
            }
        }
        
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        World world = player.world;
        BlockPos closestBiomePos = biomeToFind == null ? null : BiomeUtils.spiralOutwardsLookingForBiome(world, biomeToFind, player.posX, player.posZ);
        String biomeName = biomeToFind != null && FMLCommonHandler.instance().getSide() == Side.CLIENT ? biomeToFind.getBiomeName() : "Undefined";
        
        if (closestBiomePos != null)
        {
        	double x = (double)closestBiomePos.getX();
        	double y = (double)world.getTopSolidOrLiquidBlock(closestBiomePos).getY();
        	double z = (double)closestBiomePos.getZ();
        	
        	if (!world.provider.isSurfaceWorld())
        	{
        		y = (double)getTopBlockNonOverworld(world, closestBiomePos).getY();
        	}

            player.connection.setPlayerLocation(x, y, z, player.rotationYaw, player.rotationPitch);
            sender.sendMessage(new TextComponentTranslation("commands.biomesoplenty.tpbiome.success", player.getName(), biomeName, x, y, z));
        }
        else
        {
            sender.sendMessage(new TextComponentTranslation("commands.biomesoplenty.tpbiome.error", biomeName));
        }
    }
    
    public BlockPos getTopBlockNonOverworld(World world, BlockPos pos)
    {
        Chunk chunk = world.getChunkFromBlockCoords(pos);
        BlockPos blockpos;
        BlockPos blockpos1;
        BlockPos blockpos2 = new BlockPos(pos.getX(), chunk.getTopFilledSegment() + 16, pos.getZ());

        for (blockpos = blockpos2; blockpos.getY() >= 0; blockpos = blockpos1)
        {
            blockpos1 = blockpos.down();
            IBlockState state = chunk.getBlockState(blockpos1);

            if (!state.getMaterial().blocksMovement() && !world.isAirBlock(blockpos1.down()) && !state.getBlock().isLeaves(state, world, blockpos1) && !state.getBlock().isFoliage(world, blockpos1))
            {
                return blockpos1;
            }
        }

        return blockpos2;
    }
    
    private void printStats(ICommandSender sender, String[] args) {
        TextComponentTranslation text = new TextComponentTranslation("commands.biomesoplenty.stats.blocks", blockCount);
        
        text.getStyle().setColor(TextFormatting.GREEN);
        sender.sendMessage(text);
  
        text = new TextComponentTranslation("commands.biomesoplenty.stats.items", itemCount);
        text.getStyle().setColor(TextFormatting.GREEN);
        sender.sendMessage(text);
        
        text = new TextComponentTranslation("commands.biomesoplenty.stats.entities", entityCount);
        text.getStyle().setColor(TextFormatting.GREEN);
        sender.sendMessage(text);
        
        text = new TextComponentTranslation("commands.biomesoplenty.stats.biomes", biomeCount);
        text.getStyle().setColor(TextFormatting.GREEN);
        sender.sendMessage(text);
    }
    
    private void stripChunk(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 5)
        {
            throw new WrongUsageException("commands.biomesoplenty.stripchunk.usage");
        }

        int radius = parseInt(args[1]);
        String mode = args[2];
        boolean blacklist;

        if (mode.equals("include")) blacklist = false;
        else if (mode.equals("exclude")) blacklist = true;
        else
        {
            throw new WrongUsageException("commands.biomesoplenty.stripchunk.usage");
        }

        List<IBlockState> stateList = Lists.newArrayList();

        for (int i = 0; i < (args.length - 3) / 2; i++)
        {
            Block block = getBlockByText(sender, args[i * 2 + 3]);
            int metadata = parseInt(args[i * 2 + 3 + 1]);

            stateList.add(block.getStateFromMeta(metadata));
        }

        BlockPos playerPos = sender.getPosition();
        World world = sender.getEntityWorld();
        
        int playerChunkX = playerPos.getX() >> 4;
        int playerChunkZ = playerPos.getZ() >> 4;

        for (int chunkX = -radius; chunkX < radius; chunkX++)
        {
            for (int chunkZ = -radius; chunkZ < radius; chunkZ++)
            {
                Chunk chunk = world.getChunkFromChunkCoords(playerChunkX + chunkX, (playerChunkZ + chunkZ));
                
                for (ExtendedBlockStorage blockStorage : chunk.getBlockStorageArray())
                {
                    if (blockStorage != null)
                    {
                        for (int x = 0; x < 16; x++)
                        {
                            for (int y = 0; y < 16; y++)
                            {
                                for (int z = 0; z < 16; z++)
                                {
                                    BlockPos pos = new BlockPos(chunk.x * 16 + x, blockStorage.getYLocation() + y, chunk.z * 16 + z);
                                    IBlockState state = blockStorage.get(x, y, z);
                                    
                                    if (((!blacklist && stateList.contains(state)) || (blacklist && !stateList.contains(state))) && pos.getY() > 0)
                                    {
                                        blockStorage.set(x, y, z, Blocks.AIR.getDefaultState());
                                        world.notifyBlockUpdate(pos, state, Blocks.AIR.getDefaultState(), 3);
                                        world.notifyNeighborsRespectDebug(pos, Blocks.AIR, false);
                                    }
                                }
                            }
                        }
                    }
                }
                
                chunk.generateSkylightMap();
            }
        }
    }

    @Override
    @Nonnull
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos)
    {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, "biomename", "tpbiome", "stats", "stripchunk");
        }

        for (String command : args)
        {
            if (command.equals("tpbiome"))
            {
                return getListOfStringsMatchingLastWord(args, Biome.REGISTRY.getKeys());
            }
            else if (command.equals("stripchunk") && args.length == 3)
            {
                return getListOfStringsMatchingLastWord(args, "include", "exclude");
            }
        }
        return super.getTabCompletions(server, sender, args, pos);
    }
}
