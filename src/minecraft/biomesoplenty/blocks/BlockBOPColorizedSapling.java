package biomesoplenty.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenAcacia;
import biomesoplenty.worldgen.WorldGenMangrove;
import biomesoplenty.worldgen.WorldGenNetherBush;
import biomesoplenty.worldgen.WorldGenPalmTree1;
import biomesoplenty.worldgen.WorldGenPalmTree3;
import biomesoplenty.worldgen.WorldGenPineTree;
import biomesoplenty.worldgen.WorldGenRedwoodTree2;
import biomesoplenty.worldgen.WorldGenWillow;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPColorizedSapling extends BlockSapling
{
    private static final String[] saplings = new String[] {"acacia", "mangrove", "palm", "redwood", "willow", "pine"};
    private Icon[] textures;
    private static final int TYPES = 15;
    
    public BlockBOPColorizedSapling(int par1)
    {
        super(par1);
        setHardness(0.0F);
        setStepSound(Block.soundGrassFootstep);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[saplings.length];
        
        for (int i = 0; i < saplings.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:sapling_" + saplings[i]);

    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= saplings.length)
            meta = 0;

        return textures[meta];
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < saplings.length; ++i)
            list.add(new ItemStack(blockID, 1, i));
    }
    
    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
    {
        int id = world.getBlockId(x, y - 1, z);
        int meta = itemStack.getItemDamage();
        
        if (itemStack.itemID == this.blockID)
            switch (meta)
            {
                case 1: // Mangrove
                    return id == Block.sand.blockID;

                default:
                    return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID;
            }
        else
            return this.canPlaceBlockOnSide(world, x, y, z, side);
    }
	
    protected boolean canThisPlantGrowOnThisBlockID(int blockID, int metadata)
    {     
        if (metadata == 1) //Mangrove
            return blockID == Block.sand.blockID;
        else
            return blockID == Block.grass.blockID || blockID == Block.dirt.blockID || blockID == Block.tilledField.blockID;
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (world.isRemote)
            return;
        
        this.checkFlowerChange(world, x, y, z);

        if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0)
            this.growTree(world, x, y, z, random);
    }
    
    @Override
    public void growTree(World world, int x, int y, int z, Random random)
    {
        int meta = world.getBlockMetadata(x, y, z) & TYPES;
        Object obj = null;
        int rnd = random.nextInt(8);

        if (obj == null)
        {
            switch (meta)
            {
                case 0: // Acacia Tree
                    obj = new WorldGenAcacia(false);
                    break;
                    
                case 1: // Mangrove Tree
                    obj = new WorldGenMangrove(false);
                    break;
                    
                case 2: // Palm Tree
                    rnd = random.nextInt(4);
                    
                    if (rnd == 0)
                        obj = new WorldGenPalmTree1();
                    else
                        obj = new WorldGenPalmTree3();
                    break;
                    
                case 3: // Redwood Tree
                	if (this.isSameSapling(world, x + 1, y, z, 3) && this.isSameSapling(world, x - 1, y, z, 3) && this.isSameSapling(world, x, y, z + 1, 3) && this.isSameSapling(world, x, y, z - 1, 3) && this.isSameSapling(world, x + 1, y, z + 1, 3) && this.isSameSapling(world, x + 1, y, z - 1, 3) && this.isSameSapling(world, x - 1, y, z + 1, 3) && this.isSameSapling(world, x - 1, y, z - 1, 3))
                		obj = new WorldGenRedwoodTree2(false);
                	break;
                    
                case 4: // Willow Tree
                    obj = new WorldGenWillow();
                    break;
					
				case 5: // Pine Tree
                    obj = new WorldGenPineTree();
                    break;
					
				case 6: // Hellbark
                    obj = new WorldGenNetherBush();
                    break;
            }
        }

        if (obj != null)
        {
            world.setBlockToAir(x, y, z);

            if (!((WorldGenerator)obj).generate(world, random, x, y, z))
                world.setBlock(x, y, z, this.blockID, meta, 2);
        }
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta & TYPES;
    }
    
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) & TYPES;
    }
}
