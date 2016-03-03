/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world;

import biomesoplenty.client.gui.GuiBOPConfigureWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldTypeBOP extends WorldType
{
    public WorldTypeBOP()
    {
        super("BIOMESOP");

        this.setNotificationData();
    }
    
    @Override
    public BiomeProvider getBiomeProvider(World world)
    {
        return new BiomeProviderBOP(world);
    }
    
    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderGenerateBOP(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
        //return new ChunkProviderGenerateVanilla(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    }
    
    @Override
    public boolean isCustomizable()
    {
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void onCustomizeButton(Minecraft mc, GuiCreateWorld guiCreateWorld)
    {
        mc.displayGuiScreen(new GuiBOPConfigureWorld(guiCreateWorld, guiCreateWorld.chunkProviderSettingsJson));
    }
}
