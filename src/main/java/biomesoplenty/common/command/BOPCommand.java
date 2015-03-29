/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.util.biome.BiomeUtils;

import com.google.common.collect.Lists;

public class BOPCommand extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "biomesoplenty";
    }
    
    @Override
    public List getCommandAliases()
    {
        return Lists.newArrayList("bop", "biomesop");
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.biomesoplenty.usage";
    }
    
    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 1)
        {
            throw new WrongUsageException("commands.biomesoplenty.usage");
        }
        else if ("biomename".equals(args[0]))
        {
            getBiomeName(sender, args);
        }
        else if ("tpbiome".equals(args[0]))
        {
            teleportFoundBiome(sender, args);
        }
    }
    
    private void getBiomeName(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 2)
        {
            throw new WrongUsageException("commands.biomesoplenty.biomename.usage");
        }
        
        int biomeId = parseInt(args[1], 0, 255);
        BiomeGenBase biome = BiomeGenBase.getBiome(biomeId);
        
        sender.addChatMessage(new ChatComponentTranslation("commands.biomesoplenty.biomename.success", biomeId, biome == null ? "Undefined" : biome.biomeName));
    }
    
    private void teleportFoundBiome(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 2)
        {
            throw new WrongUsageException("commands.biomesoplenty.tpbiome.usage");
        }
        
        int biomeId = parseInt(args[1], 0, 255);
        BiomeGenBase biome = BiomeGenBase.getBiome(biomeId);
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        World world = player.worldObj;
        BlockPos closestBiomePos = biome == null ? null : BiomeUtils.findBiome(world, biome, player.getPosition());
        
        if (closestBiomePos != null)
        {
            double x = (double)closestBiomePos.getX();
            double y = (double)world.getTopSolidOrLiquidBlock(closestBiomePos).getY();
            double z = (double)closestBiomePos.getZ();
            
            player.playerNetServerHandler.setPlayerLocation(x, y, z, player.rotationYaw, player.rotationPitch);
            sender.addChatMessage(new ChatComponentTranslation("commands.biomesoplenty.tpbiome.success", player.getCommandSenderName(), biome.biomeName, x, y, z));
        }
        else
        {
            sender.addChatMessage(new ChatComponentTranslation("commands.biomesoplenty.tpbiome.error", biome == null ? "Undefined" : biome.biomeName));
        }
    }
    
    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, "biomename", "tpbiome");
        }
        
        return null;
    }
}
