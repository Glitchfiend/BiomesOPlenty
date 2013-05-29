package biomesoplenty.blocks;

import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraftforge.common.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;

public class BlockBOPGrass extends Block
{
    private Icon[][] blockIcon = new Icon[2][6];

    public BlockBOPGrass(int par1)
    {
        super(par1, Material.grass);
        this.setTickRandomly(true);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
        setStepSound(Block.soundGrassFootstep);
        setHardness(0.6F);
        //setLightValue(0.25F);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon[0][0] = iconRegister.registerIcon("BiomesOPlenty:holydirt");
        this.blockIcon[0][1] = iconRegister.registerIcon("BiomesOPlenty:holygrass_top");
        this.blockIcon[0][2] = iconRegister.registerIcon("BiomesOPlenty:holygrass_side");
        this.blockIcon[0][3] = iconRegister.registerIcon("BiomesOPlenty:holygrass_side");
        this.blockIcon[0][4] = iconRegister.registerIcon("BiomesOPlenty:holygrass_side");
        this.blockIcon[0][5] = iconRegister.registerIcon("BiomesOPlenty:holygrass_side");
        
        this.blockIcon[1][0] = iconRegister.registerIcon("BiomesOPlenty:smolderinggrass_bottom");
        this.blockIcon[1][1] = iconRegister.registerIcon("BiomesOPlenty:smolderinggrass_top");
        this.blockIcon[1][2] = iconRegister.registerIcon("BiomesOPlenty:smolderinggrass_side");
        this.blockIcon[1][3] = iconRegister.registerIcon("BiomesOPlenty:smolderinggrass_side");
        this.blockIcon[1][4] = iconRegister.registerIcon("BiomesOPlenty:smolderinggrass_side");
        this.blockIcon[1][5] = iconRegister.registerIcon("BiomesOPlenty:smolderinggrass_side");
    }
    
    @Override
    public Icon getIcon(int side, int meta)
    {
        return blockIcon[meta][side];
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < 2; ++i)
            list.add(new ItemStack(blockID, 1, i));
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
    
    @Override
    public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
    {
        if (metadata == 0)
        {
            if (blockID == Block.netherrack.blockID && side == UP)
                return true;
    
            if (blockID == this.blockID && side == UP)
                return true;
    
            if ((world.provider instanceof WorldProviderEnd) && blockID == Block.bedrock.blockID && side == UP)
                return true;
        }

        return false;
    }
    
    @Override
    public int onBlockPlaced(World world, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int meta)
    {
        if (meta == 0)
            if (world.provider.isHellWorld)
            {
                world.playSound(par2, par3, par4, "mob.ghast.death", 20.0F, 0.95F + (float)Math.random() * 0.1F, true);
                
                for (int l = 0; l < 8; ++l)
                {
                    world.spawnParticle("flame", (double)par2 + Math.random(), (double)par3 + Math.random(), (double)par4 + Math.random(), 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("smoke", (double)par2 + Math.random(), (double)par3 + Math.random(), (double)par4 + Math.random(), 0.0D, 0.0D, 0.0D);
                }
            }
        return meta;
    }
    
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (!world.isRemote)
            return;

        if (world.getBlockMetadata(x, y, z) == 1)
        {
            if (random.nextInt(4) == 0)
                world.spawnParticle("smoke", (double)((float)x + random.nextFloat()), (double)((float)y + 1.1F), (double)((float)z + random.nextFloat()), 0.0D, 0.0D, 0.0D);

            if (random.nextInt(6) == 0)
                world.spawnParticle("flame", (double)((float)x + random.nextFloat()), (double)((float)y + 1.1F), (double)((float)z + random.nextFloat()), 0.0D, 0.0D, 0.0D);
        }
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (world.getBlockMetadata(x, y, z) == 0)
        {
            if (world.provider.isHellWorld)
            {
                world.setBlock(x, y + 1, z, Block.fire.blockID);
                world.setBlock(x, y, z, Blocks.holyGrass.get().blockID, 1, 2);
            }
            
            if (!world.isRemote)
            {
                if (world.getBlockLightValue(x, y + 1, z) < 4 && Block.lightOpacity[world.getBlockId(x, y + 1, z)] > 2)
                {
                    world.setBlock(x, y, z, Blocks.holyDirt.get().blockID);
                }
                else if (world.getBlockLightValue(x, y + 1, z) >= 9)
                {
                    for (int var6 = 0; var6 < 4; ++var6)
                    {
                        int var7 = x + random.nextInt(3) - 1;
                        int var8 = y + random.nextInt(5) - 3;
                        int var9 = z + random.nextInt(3) - 1;
                        int var10 = world.getBlockId(var7, var8 + 1, var9);
    
                        if (world.getBlockId(var7, var8, var9) == Blocks.holyDirt.get().blockID && world.getBlockLightValue(var7, var8 + 1, var9) >= 4 && Block.lightOpacity[var10] <= 2)
                        {
                            world.setBlock(var7, var8, var9, Blocks.holyGrass.get().blockID, 0, 2);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == 1)
        {
            float f = 0.02F;
            return AxisAlignedBB.getAABBPool().getAABB((double)x, (double)y, (double)z, (double)(x + 1), (double)((float)(y + 1) - f), (double)(z + 1));
        }
        
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);    
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (world.getBlockMetadata(x, y, z) == 1)
            entity.setFire(2);
    }
    
    @Override
    public int idDropped(int meta, Random par2Random, int par3)
    {
        return meta == 0 ? Blocks.holyDirt.get().blockID : Block.dirt.blockID;
    }

}
