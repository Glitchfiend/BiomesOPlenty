/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.command;

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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class BiomeArgument implements ArgumentType<Biome>
{
    public static final DynamicCommandExceptionType INVALID_BIOME_EXCEPTION = new DynamicCommandExceptionType((biome) -> {
        return new TranslationTextComponent("argument.biomesoplenty.biome.invalid", new Object[]{biome});
    });

    public static BiomeArgument createArgument()
    {
        return new BiomeArgument();
    }

    public static Biome getValue(CommandContext<CommandSource> context, String name) throws CommandSyntaxException
    {
        return context.getArgument(name, Biome.class);
    }

    @Override
    public Biome parse(StringReader reader) throws CommandSyntaxException
    {
        ResourceLocation location = ResourceLocation.read(reader);
        Optional<Biome> optional = Optional.ofNullable(ForgeRegistries.BIOMES.getValue(location));
        return optional.orElseThrow(() ->
        {
            return INVALID_BIOME_EXCEPTION.create(location);
        });
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder suggestionsBuilder)
    {
        return ISuggestionProvider.suggestResource(ForgeRegistries.BIOMES.getKeys(), suggestionsBuilder);
    }
}
