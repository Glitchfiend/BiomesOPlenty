package biomesoplenty.blocks;

import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.Random;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.configuration.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraftforge.common.ForgeDirection;

//=========================================

public class BlockHolyGrass extends Block
{
    private Icon[] blockIcon = new Icon[6];
    
    public BlockHolyGrass(int par1)
    {
        super(par1, Material.grass);
        this.setTickRandomly(true);
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon[0] = par1IconRegister.registerIcon("BiomesOPlenty:holystone");
		this.blockIcon[1] = par1IconRegister.registerIcon("BiomesOPlenty:holygrass1");
		this.blockIcon[2] = par1IconRegister.registerIcon("BiomesOPlenty:holygrass2");
		this.blockIcon[3] = par1IconRegister.registerIcon("BiomesOPlenty:holygrass2");
		this.blockIcon[4] = par1IconRegister.registerIcon("BiomesOPlenty:holygrass2");
		this.blockIcon[5] = par1IconRegister.registerIcon("BiomesOPlenty:holygrass2");
	}
	
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	@Override
    public Icon getIcon(int par1, int par2)
    {
        return blockIcon[par1];
    }
    
    /**
     * Currently only called by fire when it is on top of this block.
     * Returning true will prevent the fire from naturally dying during updating.
     * Also prevents firing from dying from rain.
     *
     * @param world The current world
     * @param x The blocks X position
     * @param y The blocks Y position
     * @param z The blocks Z position
     * @param metadata The blocks current metadata
     * @param side The face that the fire is coming from
     * @return True if this block sustains fire, meaning it will never go out.
     */
    @Override
    public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
    {
        if (blockID == Block.netherrack.blockID && side == UP)
        {
            return true;
        }
        if (blockID == this.blockID && side == UP)
        {
            return true;
        }
        if ((world.provider instanceof WorldProviderEnd) && blockID == Block.bedrock.blockID && side == UP)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        if (par1World.provider.isHellWorld)
        {
            par1World.playSound(par2, par3, par4, "mob.ghast.death", 20.0F, 0.95F + (float)Math.random() * 0.1F, true);
			
            for (int l = 0; l < 8; ++l)
            {
                par1World.spawnParticle("flame", (double)par2 + Math.random(), (double)par3 + Math.random(), (double)par4 + Math.random(), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("smoke", (double)par2 + Math.random(), (double)par3 + Math.random(), (double)par4 + Math.random(), 0.0D, 0.0D, 0.0D);
            }
        }
        return par9;
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    /*public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 == 1)
        {
            return 29;
        }
        else if (par5 == 0)
        {
            return 27;
        }
        else
        {
            Material var6 = par1IBlockAccess.getBlockMaterial(par2, par3 + 1, par4);
            return var6 != Material.snow && var6 != Material.craftedSnow ? 28 : 28;
        }
    }*/

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	if (par1World.provider.isHellWorld)
    	{
    		par1World.setBlock(par2, par3 + 1, par4, Block.fire.blockID);
    		par1World.setBlock(par2, par3, par4, BOPBlocks.smolderingGrass.blockID);
    	}
    	
        if (!par1World.isRemote)
        {
            if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && Block.lightOpacity[par1World.getBlockId(par2, par3 + 1, par4)] > 2)
            {
                par1World.setBlock(par2, par3, par4, BOPBlocks.holyStone.blockID);
            }
            else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
            {
                for (int var6 = 0; var6 < 4; ++var6)
                {
                    int var7 = par2 + par5Random.nextInt(3) - 1;
                    int var8 = par3 + par5Random.nextInt(5) - 3;
                    int var9 = par4 + par5Random.nextInt(3) - 1;
                    int var10 = par1World.getBlockId(var7, var8 + 1, var9);

                    if (par1World.getBlockId(var7, var8, var9) == BOPBlocks.holyStone.blockID && par1World.getBlockLightValue(var7, var8 + 1, var9) >= 4 && Block.lightOpacity[var10] <= 2)
                    {
                        par1World.setBlock(var7, var8, var9, BOPBlocks.holyGrass.blockID);
                    }
                }
            }
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return BOPBlocks.holyStone.idDropped(0, par2Random, par3);
    }
}
