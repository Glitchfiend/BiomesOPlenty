package biomesoplenty.neoforge.datagen.provider;

import biomesoplenty.api.BOPAPI;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.AdvancedDataMapType;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class BOPDataMapProvider extends DataMapProvider {
    Builder<Compostable, Item> compostables = this.builder(NeoForgeDataMaps.COMPOSTABLES);

    public BOPDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        addCompostable(Items.ACACIA_BOAT, 0.5F);
        addCompostable(Items.ACACIA_BUTTON, 0.3F);
    }

    protected void addCompostable(Item item, float chance) {
        compostables.add(item.builtInRegistryHolder(), new Compostable(chance), false);
    }
}