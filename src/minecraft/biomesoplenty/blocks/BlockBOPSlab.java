package biomesoplenty.blocks;

import java.util.List;
import java.util.Random;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPSlab extends BlockHalfSlab
{
    public static enum SlabCategory
    {
      WOOD1, WOOD2, STONE;
    }
    private static final String[] woodTypes = new String[] {"acacia", "cherry", "dark", "fir", "holy", "magic", "mangrove", "palm", "redwood", "willow"};
    private static final String[] rockTypes = new String[] {"redcobble", "redbrick", "mudbrick", "holycobble", "holybrick"};
    private Icon[] textures;
    protected final boolean isDoubleSlab;
    
    private final SlabCategory category;
    
    public BlockBOPSlab(int par1, boolean par2, Material material, SlabCategory cat)
    {
        super(par1, par2, material);
        isDoubleSlab = par2;
        category = cat;
        if (material == Material.wood)
        {
            setBurnProperties(this.blockID, 5, 20);
            setHardness(2.0F);
            setResistance(5.0F);
            setStepSound(Block.soundWoodFootstep);
        }
        else if (material == Material.rock)
            setStepSound(Block.soundStoneFootstep);

        if (!par2)
            this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
        
        useNeighborBrightness[blockID] = true;
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        if (category == SlabCategory.STONE)
        {
            textures = new Icon[rockTypes.length];
            
            for (int i = 0; i < rockTypes.length; ++i)
                textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+rockTypes[i]);
        }
        else
        {
            textures = new Icon[woodTypes.length];
            
            for (int i = 0; i < woodTypes.length; ++i)
                textures[i] = iconRegister.registerIcon("BiomesOPlenty:plank_"+woodTypes[i]);
        }
    }
    
    @Override
    public Icon getIcon(int side, int meta)
    {
        if (category == SlabCategory.STONE)
            return textures[getTypeFromMeta(meta)];
        else
            return textures[(getTypeFromMeta(meta) + this.category.ordinal() * 8)];
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        int max = 0;
        
        if (category == SlabCategory.WOOD1)
            max = 8;
        else if (category == SlabCategory.WOOD2)
            max = 2;
        else if (category == SlabCategory.STONE)
            max = 5;
            
            for (int i = 0; i < max; ++i)
                list.add(new ItemStack(blockID, 1, i));
    }

    @Override
    public String getFullSlabName(int meta)
    {
        if (category == SlabCategory.STONE)
            return (new StringBuilder()).append(rockTypes[getTypeFromMeta(meta)]).append("Slab").toString();
        else
            return (new StringBuilder()).append(woodTypes[getWoodType(meta)]).append("Slab").toString();
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
    
    @Override
    public int idDropped(int meta, Random par2Random, int par3)
    {
        if (isDoubleSlab)
        {
            if (this.blockID == Blocks.woodenDoubleSlab1.get().blockID)
                return Blocks.woodenSingleSlab1.get().blockID;
            if (this.blockID == Blocks.woodenDoubleSlab2.get().blockID)
                return Blocks.woodenSingleSlab2.get().blockID;
            else
                return Blocks.stoneSingleSlab.get().blockID;
        }
        else
            return this.blockID;
    }
    
    @Override
    public float getBlockHardness(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        float hardness = this.blockHardness;
        
        if (category == SlabCategory.STONE)
        {
            switch (getTypeFromMeta(meta))
            {
                case 0:
                    hardness = 1.6F;
                    break;
                    
                case 1:
                    hardness = 1.1F;
                    break;

                case 2:
                    hardness = 1.0F;
                    break;
					
				case 3:
                    hardness = 1.6F;
                    break;
                    
                case 4:
                    hardness = 1.1F;
                    break;
            }
        }

        return hardness;
    }
    
    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        int meta = world.getBlockMetadata(x, y, z);
        float resistance = this.blockHardness;
        
        if (category == SlabCategory.STONE)
        {
            switch (getTypeFromMeta(meta))
            {
                case 0:
                    resistance = 7.0F;
                    break;
                    
                case 1:
                    resistance = 7.5F;
                    break;

                case 2:
                    resistance = 2.0F;
                    break;
					
				case 3:
                    resistance = 7.0F;
                    break;
                    
                case 4:
                    resistance = 7.5F;
                    break;
            }
        }

        return resistance / 5.0F;
    }
    
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 2, par1);
    }
    
    private int getWoodType(int meta)
    {
      return getTypeFromMeta(meta) + category.ordinal() * 8;
    }
    
    private static int getTypeFromMeta(int meta)
    {
      return meta & 7;
    }
}
