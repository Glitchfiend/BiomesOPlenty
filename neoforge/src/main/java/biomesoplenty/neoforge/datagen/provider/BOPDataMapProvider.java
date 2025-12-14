package biomesoplenty.neoforge.datagen.provider;

import biomesoplenty.init.ModVanillaCompat;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class BOPDataMapProvider extends DataMapProvider {
    Builder<Compostable, Item> compostables = this.builder(NeoForgeDataMaps.COMPOSTABLES);

    public BOPDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider lookup) {
        ModVanillaCompat.compostables.forEach(this::addCompostable);
    }

    protected void addCompostable(ItemLike item, float chance) {
        compostables.add(item.asItem().builtInRegistryHolder(), new Compostable(chance), false);
    }
}