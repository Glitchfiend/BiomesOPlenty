package biomesoplenty.liquids;

import java.util.Random;

import biomesoplenty.api.Liquids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLiquidPoisonFlowing extends BlockFlowing implements ILiquid
{
	int numAdjacentSources = 0;
	boolean isOptimalFlowDirection[] = new boolean[4];
	int flowCost[] = new int[4];
	
    public BlockLiquidPoisonFlowing(int id) 
    {
        super(id, Material.water);
        
        this.blockHardness = 100F;
        this.setLightOpacity(3);
    }
    
	private void updateFlow(World par1World, int par2, int par3, int par4) 
	{
		int l = par1World.getBlockMetadata(par2, par3, par4);
		par1World.setBlock(par2, par3, par4, this.blockID + 1, l, 2);
	}
	
	@Override
	public int tickRate(World par1World) 
	{
		return 8;
	}

	@Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int l = this.getFlowDecay(par1World, par2, par3, par4);
        byte b0 = 1;

        boolean flag = true;
        int i1;

        if (l > 0)
        {
            byte b1 = -100;
            this.numAdjacentSources = 0;
            int j1 = this.getSmallestFlowDecay(par1World, par2 - 1, par3, par4, b1);
            j1 = this.getSmallestFlowDecay(par1World, par2 + 1, par3, par4, j1);
            j1 = this.getSmallestFlowDecay(par1World, par2, par3, par4 - 1, j1);
            j1 = this.getSmallestFlowDecay(par1World, par2, par3, par4 + 1, j1);
            i1 = j1 + b0;

            if (i1 >= 7 || j1 < 0)
            {
                i1 = -1;
            }

            if (this.getFlowDecay(par1World, par2, par3 + 1, par4) >= 0)
            {
                int k1 = this.getFlowDecay(par1World, par2, par3 + 1, par4);

                if (k1 >= 7)
                {
                    i1 = k1;
                }
                else
                {
                    i1 = k1 + 7;
                }
            }

            if (i1 == l)
            {
                if (flag)
                {
                    this.updateFlow(par1World, par2, par3, par4);
                }
            }
            else
            {
                l = i1;

                if (i1 < 0)
                {
                    par1World.setBlockToAir(par2, par3, par4);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, i1, 2);
                    par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
                    par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
                }
            }
        }
        else
        {
            this.updateFlow(par1World, par2, par3, par4);
        }

        if (this.liquidCanDisplaceBlock(par1World, par2, par3 - 1, par4))
        {
            if (l >= 7)
            {
                this.flowIntoBlock(par1World, par2, par3 - 1, par4, l);
            }
            else
            {
                this.flowIntoBlock(par1World, par2, par3 - 1, par4, l + 7);
            }
        }
        else if (l >= 0 && (l == 0 || this.blockBlocksFlow(par1World, par2, par3 - 1, par4)))
        {
            boolean[] aboolean = this.getOptimalFlowDirections(par1World, par2, par3, par4);
            i1 = l + b0;

            if (l >= 7)
            {
                i1 = 1;
            }

            if (i1 >= 7)
            {
                return;
            }

            if (aboolean[0])
            {
                this.flowIntoBlock(par1World, par2 - 1, par3, par4, i1);
            }

            if (aboolean[1])
            {
                this.flowIntoBlock(par1World, par2 + 1, par3, par4, i1);
            }

            if (aboolean[2])
            {
                this.flowIntoBlock(par1World, par2, par3, par4 - 1, i1);
            }

            if (aboolean[3])
            {
                this.flowIntoBlock(par1World, par2, par3, par4 + 1, i1);
            }
        }
    }

	private void flowIntoBlock(World world, int i, int j, int k, int l) 
	{
		if (liquidCanDisplaceBlock(world, i, j, k)) 
		{
			int blockId = world.getBlockId(i, j, k);
			if (blockId > 0) 
			{
				Block.blocksList[blockId].dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
			}
			world.setBlock(i, j, k, blockID, l, 3);
		}
	}

	private int calculateFlowCost(World world, int i, int j, int k, int l, int i1) 
	{
		int j1 = 1000;
		for (int k1 = 0; k1 < 4; k1++) 
		{
			if (k1 == 0 && i1 == 1 || k1 == 1 && i1 == 0 || k1 == 2 && i1 == 3 || k1 == 3 && i1 == 2) 
			{
				continue;
			}
			int l1 = i;
			int i2 = j;
			int j2 = k;
			if (k1 == 0) 
			{
				l1--;
			}
			if (k1 == 1) 
			{
				l1++;
			}
			if (k1 == 2) 
			{
				j2--;
			}
			if (k1 == 3) 
			{
				j2++;
			}
			if (blockBlocksFlow(world, l1, i2, j2) || world.getBlockMaterial(l1, i2, j2) == blockMaterial && world.getBlockMetadata(l1, i2, j2) == 0) 
			{
				continue;
			}
			if (!blockBlocksFlow(world, l1, i2 - 1, j2)) 
			{
				return l;
			}
			if (l >= 4) 
			{
				continue;
			}
			int k2 = calculateFlowCost(world, l1, i2, j2, l + 1, k1);
			if (k2 < j1) 
			{
				j1 = k2;
			}
		}

		return j1;
	}

	private boolean[] getOptimalFlowDirections(World world, int i, int j, int k) 
	{
		for (int l = 0; l < 4; l++) 
		{
			flowCost[l] = 1000;
			int j1 = i;
			int i2 = j;
			int j2 = k;
			if (l == 0) 
			{
				j1--;
			}
			if (l == 1) 
			{
				j1++;
			}
			if (l == 2) 
			{
				j2--;
			}
			if (l == 3) 
			{
				j2++;
			}
			if (blockBlocksFlow(world, j1, i2, j2) || world.getBlockMaterial(j1, i2, j2) == blockMaterial && world.getBlockMetadata(j1, i2, j2) == 0) 
			{
				continue;
			}
			if (!blockBlocksFlow(world, j1, i2 - 1, j2)) 
			{
				flowCost[l] = 0;
			} 
			else 
			{
				flowCost[l] = calculateFlowCost(world, j1, i2, j2, 1, l);
			}
		}

		int i1 = flowCost[0];
		for (int k1 = 1; k1 < 4; k1++) {
			if (flowCost[k1] < i1) {
				i1 = flowCost[k1];
			}
		}

		for (int l1 = 0; l1 < 4; l1++) {
			isOptimalFlowDirection[l1] = flowCost[l1] == i1;
		}

		return isOptimalFlowDirection;
	}

	private boolean blockBlocksFlow(World par1World, int par2, int par3, int par4) 
	{
		int l = par1World.getBlockId(par2, par3, par4);

		if (l != Block.doorWood.blockID && l != Block.doorIron.blockID && l != Block.signPost.blockID && l != Block.ladder.blockID && l != Block.reed.blockID) 
		{
			if (l == 0) 
			{
				return false;
			} 
			else 
			{
				Material material = Block.blocksList[l].blockMaterial;
				return material == Material.portal ? true : material.blocksMovement();
			}
		} 
		else 
		{
			return true;
		}
	}

	private boolean liquidCanDisplaceBlock(World world, int i, int j, int k) 
	{
		Material material = world.getBlockMaterial(i, j, k);
		if (material == blockMaterial) 
		{
			return false;
		} else 
		{
			return !blockBlocksFlow(world, i, j, k);
		}
	}
    
    @Override
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
    	return 16777215;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity par5Entity)
    {
        int meta = par1World.getBlockMetadata(x, y, z);
        
        if (par5Entity instanceof EntityLiving) 
        {
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 100));
        }  
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) 
	{
		this.theIcon = new Icon[]{iconRegister.registerIcon("BiomesOPlenty:liquid_poison_still"), iconRegister.registerIcon("BiomesOPlenty:liquid_poison_flowing")};
	}
	
	@Override
	public int stillLiquidId() 
	{
		return Liquids.liquidPoisonStill.get().blockID;
	}

	@Override
	public boolean isMetaSensitive() 
	{
		return false;
	}

	@Override
	public int stillLiquidMeta() 
	{
		return 0;
	}
}
