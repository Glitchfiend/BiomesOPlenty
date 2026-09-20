package biomesoplenty.neoforge.datagen.provider;

import biomesoplenty.neoforge.datagen.BOPBlockLoot;
import net.minecraft.core.registries.SingleRegistryBootstrap;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class BOPLootTableProvider
{
    public static SingleRegistryBootstrap<LootTable> create()
    {
        return new LootTableProvider(Set.of(), List.of(new LootTableProvider.SubProviderEntry(BOPBlockLoot::new, LootContextParamSets.BLOCK)));
    }
}
