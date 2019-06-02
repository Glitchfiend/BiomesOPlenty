/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.command;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.google.common.collect.Streams;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.biome.Biome;

public class BiomeArgument implements ArgumentType<Biome>
{
    private static final Collection<String> EXAMPLES;
    public static final DynamicCommandExceptionType INVALID_BIOME_EXCEPTION;

    public BiomeArgument()
    {
    }

    @Override
    public <S> Biome parse(StringReader reader) throws CommandSyntaxException
    {
        ResourceLocation location = ResourceLocation.read(reader);
        Biome biome = IRegistry.BIOME.get(location);
        if (biome == null)
            throw INVALID_BIOME_EXCEPTION.create(location);
        else return biome;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder suggestionsBuilder)
    {
        return ISuggestionProvider.func_212476_a(Streams.stream(IRegistry.BIOME).map(biome -> IRegistry.BIOME.getKey((Biome)biome)), suggestionsBuilder);
    }

    @Override
    public Collection<String> getExamples()
    {
        return EXAMPLES;
    }

    public static BiomeArgument createArgument()
    {
        return new BiomeArgument();
    }

    public static Biome getValue(CommandContext<CommandSource> context, String name)
    {
        return context.getArgument(name, Biome.class);
    }

    static
    {
        EXAMPLES = (Collection)Streams.stream(IRegistry.BIOME).map(biome -> {
            return IRegistry.BIOME.getKey((Biome)biome).toString();
        }).collect(Collectors.toList());
        INVALID_BIOME_EXCEPTION = new DynamicCommandExceptionType((biome) -> {
            return new TextComponentTranslation("argument.biomesoplenty.biome.invalid", new Object[]{biome});
        });
    }
}
