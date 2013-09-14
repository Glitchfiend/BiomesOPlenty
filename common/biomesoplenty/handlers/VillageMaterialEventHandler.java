package biomesoplenty.handlers;

import net.minecraft.block.Block;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.BiomeEvent;
import biomesoplenty.api.Biomes;

public class VillageMaterialEventHandler
{
	@ForgeSubscribe
	public void getVillageBlockID(BiomeEvent.GetVillageBlockID event)
	{
		if (event.biome == Biomes.savanna.get())
		{
			if (event.original == Block.cobblestone.blockID)
			{
			event.replacement = Block.brick.blockID;
			}
		}
	}
}