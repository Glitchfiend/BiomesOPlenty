package biomesoplenty.common.items;

import java.util.Arrays;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBiomeHelper;
import biomesoplenty.client.textures.TextureBiomeFinder;
import biomesoplenty.common.network.packet.PacketBiomePosition;

public class ItemBiomeFinder extends Item
{
    public ItemBiomeFinder()
    {
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        
        itemStack.getTagCompound().setInteger("biomeIdToFind", BOPBiomeHelper.getBOPBiome("lavenderFields").biomeID);
        
        int biomeIdToFind = itemStack.getTagCompound().getInteger("biomeIdToFind");
        
        if (!world.isRemote && !player.getEntityData().getBoolean("foundBiome"))
        {
            //TODO:                                 getBiomeGenArray()
            BiomeGenBase biomeToFind = BiomeGenBase.func_150565_n()[biomeIdToFind];

            if (biomeToFind != null)
            {
                int radius = 256;

                WorldChunkManager chunkManager = world.getWorldChunkManager();

                ChunkPosition finalFoundPosition1 = null;

                int playerX = MathHelper.floor_double(player.posX);
                int playerZ = MathHelper.floor_double(player.posZ);

                for (int x = 10; x <= 10; x++)
                {
                    for (int z = -10; z <= 10; z++)
                    {
                        ChunkPosition foundPosition = chunkManager.func_150795_a(playerX + (x * 1024), playerZ + (z * 1024), radius, Arrays.asList(biomeToFind), world.rand);

                        if (foundPosition != null && world.getBiomeGenForCoords(foundPosition.field_151329_a, foundPosition.field_151328_c) == biomeToFind) 
                        {
                            finalFoundPosition1 = foundPosition;
                            break;
                        }
                    }
                }

                ChunkPosition finalFoundPosition2 = null;

                for (int x = -10; x <= 10; x++)
                {
                    for (int z = 10; z >= -10; z--)
                    {
                        ChunkPosition foundPosition = chunkManager.func_150795_a(playerX + (x * 1024), playerZ + (z * 1024), radius, Arrays.asList(biomeToFind), world.rand);

                        if (foundPosition != null && world.getBiomeGenForCoords(foundPosition.field_151329_a, foundPosition.field_151328_c) == biomeToFind) 
                        {
                            finalFoundPosition2 = foundPosition;
                            break;
                        }
                    }
                }

                ChunkPosition biomePosition = null;

                System.out.println((finalFoundPosition1 == null) + " " + (finalFoundPosition2 == null));

                if (finalFoundPosition1 != null && finalFoundPosition2 != null)
                {
                    int f1X = finalFoundPosition1.field_151329_a;
                    int f1Z = finalFoundPosition1.field_151328_c;

                    int f2X = finalFoundPosition2.field_151329_a;
                    int f2Z = finalFoundPosition2.field_151328_c;

                    System.out.println(f1X + " " + f1Z);
                    System.out.println(f2X + " " + f2Z);

                    if (Math.sqrt((f1X * f1X) + (f1Z * f1Z)) > Math.sqrt((f2X * f2X) + (f2Z * f2Z))) biomePosition = finalFoundPosition2;
                    else biomePosition = finalFoundPosition1;
                }
                else
                {
                    if (finalFoundPosition1 == null) biomePosition = finalFoundPosition2;
                    else if (finalFoundPosition2 == null) biomePosition = finalFoundPosition1;
                }

                if (biomePosition != null)
                {
                    System.out.println(biomePosition.field_151329_a + " " + biomePosition.field_151328_c);

                    NBTTagCompound biomeCompound = new NBTTagCompound();

                    biomeCompound.setInteger("x", biomePosition.field_151329_a);
                    biomeCompound.setInteger("z", biomePosition.field_151328_c);

                    if (!player.getEntityData().hasKey("biomePosition")) player.getEntityData().setTag("biomePosition", biomeCompound);
                    
                    player.getEntityData().setBoolean("foundBiome", true);

                    BiomesOPlenty.packetPipeline.sendTo(new PacketBiomePosition(biomePosition.field_151329_a, biomePosition.field_151328_c, true), (EntityPlayerMP)player);
                }

                System.out.println("Done looking");
            }
        }

        return itemStack;
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        if (iconRegister instanceof TextureMap)
        {
            TextureAtlasSprite texture = new TextureBiomeFinder("biomesoplenty:biomefinder");
            ((TextureMap)iconRegister).setTextureEntry("biomesoplenty:biomefinder", texture);
            this.itemIcon = texture;
        }
    }
    
    /*@Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advancedItemTooltips) 
    {
        if (!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
    }*/
}
