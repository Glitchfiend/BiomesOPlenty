/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.command;

import biomesoplenty.common.util.data.DataGenerator;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.commands.CommandRuntimeException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

public class CommandGenData
{
    static ArgumentBuilder<CommandSourceStack, ?> register()
    {
        return Commands.literal("gendata")
                .executes(ctx -> {
                    Level world = ctx.getSource().getLevel();
                    return genData(ctx.getSource(), world);
                });
    }

    private static int genData(CommandSourceStack cs, Level level) throws CommandRuntimeException
    {
        DataGenerator.generateData();
        cs.sendSuccess(new TranslatableComponent("commands.biomesoplenty.gendata.success"), true);
        return 1;
    }
}
