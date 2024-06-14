package biomesoplenty.neoforge.datagen.provider;

import biomesoplenty.neoforge.datagen.BOPBlockLoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BOPLootTableProvider
{
    public static LootTableProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> holderLookup)
    {
        return new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(BOPBlockLoot::new, LootContextParamSets.BLOCK)), holderLookup);
    }
}
