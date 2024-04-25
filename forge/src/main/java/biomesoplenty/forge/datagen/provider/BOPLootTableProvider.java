package biomesoplenty.forge.datagen.provider;

import biomesoplenty.forge.datagen.BOPBlockLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class BOPLootTableProvider
{
    public static LootTableProvider create(PackOutput output)
    {
        return new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(BOPBlockLoot::new, LootContextParamSets.BLOCK)));
    }
}
