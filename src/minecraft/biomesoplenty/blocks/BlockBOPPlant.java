package biomesoplenty.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.blocks.renderers.PlantsRenderer;
import biomesoplenty.configuration.BOPBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPPlant extends BlockFlower implements IShearable
{
    private static final String[] plants = new String[] {"deadgrass", "desertgrass", "desertsprouts", "dunegrass", "holytallgrass", "thorn", "barley", "cattail"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    
    public BlockBOPPlant(int par1)
    {
        super(par1, Material.vine);
        setTickRandomly(true);
        float var3 = 0.4F;
        setBurnProperties(this.blockID, 60, 100);
        setHardness(0.0F);
        setStepSound(Block.soundGrassFootstep);
        setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
        setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[plants.length];
        
        for (int i = 0; i < plants.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:" + plants[i]);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= textures.length)
            meta = 0;

        return textures[meta];
    }
    
    public int getRenderType ()
    {
        return PlantsRenderer.render;
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
    {
        int meta = world.getBlockMetadata(par2, par3, par4);
        
        switch (meta)
        {
            case 6:
            case 7:
                this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.00F, 0.875F);
                break;
                
            default:
                this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
                break;
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < plants.length; ++i)
            list.add(new ItemStack(blockID, 1, i));
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return super.canPlaceBlockAt(world, x, y, z) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(int id, int meta)
    {
        // TODO 
        if (meta == 0)
            return id == Blocks.driedDirt.get().blockID || id == Block.sand.blockID;
        else if (meta == 1)    
            return id == Blocks.redRock.get().blockID;
        else if (meta == 2 || meta == 3)
            return id == Block.sand.blockID;
        else if (meta == 4)
            return id == Blocks.holyGrass.get().blockID;
        else
            return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) 
                && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 5)
            entity.attackEntityFrom(DamageSource.cactus, 1);
    }
    
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        return meta;
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return -1;
    }
    
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        super.harvestBlock(world, player, x, y, z, meta);
    }
    
    @Override
    public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 5)
            return false;
        return true;
    }
    
    @Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return ret;
    }
    
    @Override
    public boolean isBlockFoliage(World world, int x, int y, int z)
    {
        return true;
    }
}
