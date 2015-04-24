/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

public class ItemBiomeFinder extends Item
{    
    
    public ItemBiomeFinder()
    {
        this.setMaxStackSize(1);
    }
    

    // sample points in an archimedean spiral starting from startX,startY each one sampleSpace apart
    // stop when the specified biome is found (and return the position it was found at) or when we reach maxDistance (and return null)
    public BlockPos spiralOutwardsLookingForBiome(World world, BiomeGenBase biomeToFind, double startX, double startZ, int maxDist, int sampleSpace)
    {
        if (maxDist <= 0 || sampleSpace <= 0) {throw new IllegalArgumentException("maxDist and sampleSpace must be positive");}
        WorldChunkManager chunkManager = world.getWorldChunkManager();
        double a = sampleSpace / Math.sqrt(Math.PI);
        double b = 2 * Math.sqrt(Math.PI);
        double x = 0;
        double z = 0;
        double dist = 0;
        int n = 0;
        for (n = 0; dist < maxDist; ++n)
        {
            double rootN = Math.sqrt(n);
            dist = a * rootN;
            x = startX + (dist * Math.sin(b * rootN));
            z = startZ + (dist * Math.cos(b * rootN));
            // chunkManager.genBiomes is the first layer returned from initializeAllBiomeGenerators()
            // chunkManager.biomeIndexLayer is the second layer returned from initializeAllBiomeGenerators(), it's zoomed twice from genBiomes (>> 2) this one is actual size
            // chunkManager.getBiomeGenAt uses biomeIndexLayer to get the biome
            BiomeGenBase[] biomesAtSample = chunkManager.getBiomeGenAt(null, (int)x, (int)z, 1, 1, false);
            // System.out.println(n+" At ("+((int)x)+","+((int)z)+") biome is "+biomesAtSample[0].biomeName+" distance "+((int)dist));
            if (biomesAtSample[0] == biomeToFind)
            {
                System.out.println("Found "+biomeToFind.biomeName+" after "+n+" samples at ("+((int)x)+","+((int)z)+") distance "+((int)dist));
                return new BlockPos((int)x, 0, (int)z);
            }
        }
        System.out.println("Failed to find "+biomeToFind.biomeName+" gave up after "+n+" samples distance "+((int)dist));
        return null;
    }
    
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        // carry out the search on the server, not the client
        if (world.isRemote) {return stack;}
        
        if (!stack.hasTagCompound()) {stack.setTagCompound(new NBTTagCompound());}
        NBTTagCompound nbt = stack.getTagCompound();
          
        if (nbt.getBoolean("found"))
        {
            System.out.println("Already found, returning");
            return stack;
        }
        if (nbt.hasKey("searchStarted") && (world.getWorldTime() - nbt.getLong("searchStarted") < 200))
        {
            System.out.println("Still searching, returning");
            return stack;
        }
        if (!nbt.hasKey("biomeIDToFind"))
        {
            System.out.println("No biome to find, returning");
            return stack;
        }

        BiomeGenBase biomeToFind = BiomeGenBase.getBiome(nbt.getInteger("biomeIDToFind")); // returns ocean if biomeIDToFind is out of bounds
        System.out.println("Biome finder looking for "+biomeToFind.biomeName);
        writeNBTSearching(nbt, world);  
        
        // search for biomeToFind, maximum distance 5000 blocks, 64 blocks between samples
        BlockPos pos = this.spiralOutwardsLookingForBiome(world, biomeToFind, player.posX, player.posZ, 5000, 64);
        if (pos == null)
        {
            writeNBTNotFound(nbt);
        }
        else
        {
            writeNBTFound(nbt, pos);
        }
        return stack;
    }
    
    public static void writeNBTSearching(NBTTagCompound nbt, World world)
    {
        nbt.setBoolean("found",false);
        nbt.setLong("searchStarted", world.getWorldTime());
        nbt.removeTag("posX");
        nbt.removeTag("posZ");
    }
    
    public static void writeNBTFound(NBTTagCompound nbt, BlockPos pos)
    {        
        nbt.setBoolean("found",true);
        nbt.removeTag("searchStarted");
        nbt.setInteger("posX", pos.getX());
        nbt.setInteger("posZ", pos.getZ());
    }
    
    public static void writeNBTNotFound(NBTTagCompound nbt)
    {
        nbt.setBoolean("found",false);
        nbt.removeTag("searchStarted");
        nbt.removeTag("posX");
        nbt.removeTag("posZ");
    }

    
    
    
    /* TODO:  all the rendering stuff... not sure how it's going to work
    public class TextureBiomeFinder extends TextureAtlasSprite
    {

        public TextureBiomeFinder(String spriteName) {
            super(spriteName);
        }
        
    }
    */
    
}