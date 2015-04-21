/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import biomesoplenty.api.biome.BOPBiomes;
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
        if (world.isRemote) {return stack;} // TODO: can we make it client only and skip this?
        
        if (!stack.hasTagCompound()) {stack.setTagCompound(new NBTTagCompound());}
        int biomeIDToFind = stack.getTagCompound().getInteger("biomeIDToFind");
        BiomeGenBase biomeToFind = BiomeGenBase.getBiome(biomeIDToFind); // returns ocean if biomeIDToFind is out of bounds
        System.out.println("Using biome finder looking for "+biomeToFind.biomeName);
        if (stack.getTagCompound().getBoolean("foundBiome"))
        {
            System.out.println("Already found, returning");
            return stack;
        }
        biomeToFind = BOPBiomes.originValley.get();
        // search for biomeToFind, maximum distace 5000 blocks, 64 blocks between samples
        BlockPos pos = this.spiralOutwardsLookingForBiome(world, biomeToFind, player.posX, player.posZ, 5000, 64);
        
        if (pos == null)
        {
            //System.out.println("Didn't find it");
        }
        else
        {
            stack.getTagCompound().setInteger("posX", pos.getX());
            stack.getTagCompound().setInteger("posY", pos.getY());
            stack.getTagCompound().setBoolean("foundBiome", true);
            // TODO: BOPPacketHandler.instance.sendTo(new MessageBiomePosition(pos), true);
        }
                
        return stack;
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