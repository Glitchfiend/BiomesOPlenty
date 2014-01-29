package biomesoplenty.common.items;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.network.packet.PacketBiomePosition;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBiomeFinder extends Item
{
	public IIcon[] biomeRadarIcons = new IIcon[32];
	
    private int tickCount = 0;
    private int loopIndex = 0;
    
    public double currentAngle;
    public double angleDelta;
    
    public ItemBiomeFinder()
    {
    	this.setMaxStackSize(1);
    	
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        
        int biomeIDToFind = itemStack.getTagCompound().getInteger("biomeIDToFind");
        
        if (!world.isRemote && !itemStack.getTagCompound().getBoolean("foundBiome"))
        {
            //TODO:                                 getBiomeGenArray()
            BiomeGenBase biomeToFind = BiomeGenBase.func_150565_n()[biomeIDToFind];

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

                //System.out.println((finalFoundPosition1 == null) + " " + (finalFoundPosition2 == null));

                if (finalFoundPosition1 != null && finalFoundPosition2 != null)
                {
                    int f1X = finalFoundPosition1.field_151329_a;
                    int f1Z = finalFoundPosition1.field_151328_c;

                    int f2X = finalFoundPosition2.field_151329_a;
                    int f2Z = finalFoundPosition2.field_151328_c;

                    //System.out.println(f1X + " " + f1Z);
                    //System.out.println(f2X + " " + f2Z);

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

                    itemStack.getTagCompound().setTag("biomePosition", biomeCompound);
                    itemStack.getTagCompound().setBoolean("foundBiome", true);

                    BiomesOPlenty.packetPipeline.sendTo(new PacketBiomePosition(biomePosition.field_151329_a, biomePosition.field_151328_c, true), (EntityPlayerMP)player);
                }

                //System.out.println("Done looking");
            }
        }

        return itemStack;
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
    	for (int i = 0; i < 32; i++)
    	{
    		this.biomeRadarIcons[i] = iconRegister.registerIcon("biomesoplenty:biomeradar/" + i);
    	}
    	
        itemIcon = iconRegister.registerIcon("biomesoplenty:biomefinder");
    }
    
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List infoList, boolean advancedItemTooltips)
    {
        if (itemStack.hasTagCompound())
        {
            if (itemStack.getTagCompound().hasKey("biomeIDToFind")) 
            {
                BiomeGenBase biome = BiomeGenBase.func_150565_n()[itemStack.getTagCompound().getInteger("biomeIDToFind")];

                if (biome != null)
                {
                    infoList.add(biome.biomeName);
                    
                    if (itemStack.getTagCompound().hasKey("foundBiome"))
                    {
                        boolean foundBiome = itemStack.getTagCompound().getBoolean("foundBiome");
                        
                        if (foundBiome) infoList.add(EnumChatFormatting.DARK_GREEN + "Found biome!");
                        else infoList.add(EnumChatFormatting.DARK_GRAY + "Right click to find biome");
                    }
                }
            }
        }
    }
}
