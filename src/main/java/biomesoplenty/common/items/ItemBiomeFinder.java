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
        if (!world.isRemote)
        {
            if (!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());

            BiomeGenBase biomeToFind = BOPBiomeHelper.getBOPBiome("lavenderFields");
            
            int radius = 256;

            WorldChunkManager chunkManager = world.getWorldChunkManager();

            //TODO:                                    findBiome()?
            ChunkPosition biomePosition = null;

            for (int x = -10; x <= 10; x++)
            {
                for (int z = -10; z <= 10; z++)
                {
                    ChunkPosition foundPosition = chunkManager.func_150795_a(x * 1024, z * 1024, radius, Arrays.asList(biomeToFind), world.rand);
                    
                    if (foundPosition != null && world.getBiomeGenForCoords(foundPosition.field_151329_a, foundPosition.field_151328_c) == biomeToFind) 
                    {
                        biomePosition = foundPosition;
                        break;
                    }
                }
            }

            if (biomePosition != null)
            {
                System.out.println(biomePosition.field_151329_a + " " + biomePosition.field_151328_c);
                
                NBTTagCompound biomeCompound = new NBTTagCompound();
                
                biomeCompound.setInteger("x", biomePosition.field_151329_a);
                biomeCompound.setInteger("z", biomePosition.field_151328_c);
                
                if (!player.getEntityData().hasKey("biomePositions")) player.getEntityData().setTag("biomePositions", new NBTTagCompound());
                
                NBTTagCompound biomePositions = player.getEntityData().getCompoundTag("biomePositions");
                
                if (!biomePositions.hasKey(biomeToFind.biomeName)) biomePositions.setTag(biomeToFind.biomeName, biomeCompound);
                
                BiomesOPlenty.packetPipeline.sendTo(new PacketBiomePosition(biomeToFind.biomeName, biomePosition.field_151329_a, biomePosition.field_151328_c), (EntityPlayerMP)player);
            }
            
            System.out.println("Done looking");
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
