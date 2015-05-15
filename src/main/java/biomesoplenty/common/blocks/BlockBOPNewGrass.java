package biomesoplenty.common.blocks;

import static net.minecraftforge.common.util.ForgeDirection.UP;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.client.render.RenderUtils;
import biomesoplenty.common.utils.ISubLocalization;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBOPNewGrass extends BlockGrass implements ISubLocalization
{
	private static final String[] grassTypes = new String[] { "loamy", "sandy", "silty" };
	private static final IIcon[] grassIcons = new IIcon[grassTypes.length * 2];
	
	public BlockBOPNewGrass()
	{
		this.setHardness(0.6F);
		this.setHarvestLevel("shovel", 0);
		
		this.setStepSound(soundTypeGrass);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
    @Override
	public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (!world.isRemote)
        {
            if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
            {
                world.setBlock(x, y, z, BOPCBlocks.newBopDirt, world.getBlockMetadata(x, y, z) * 2, 2);
            }
            else if (world.getBlockLightValue(x, y + 1, z) >= 9)
            {
                for (int l = 0; l < 4; ++l)
                {
                    int randX = x + random.nextInt(3) - 1;
                    int randY = y + random.nextInt(5) - 3;
                    int randZ = z + random.nextInt(3) - 1;
                    
                    Block block = world.getBlock(randX, randY + 1, randZ);

                    if (world.getBlockLightValue(randX, randY + 1, randZ) >= 4 && world.getBlockLightOpacity(randX, randY + 1, randZ) <= 2)
                    {
                    	if (world.getBlock(randX, randY, randZ) == Blocks.dirt && world.getBlockMetadata(randX, randY, randZ) == 0)
                    	{
                    		world.setBlock(randX, randY, randZ, Blocks.grass);
                    	}
                    	else if (world.getBlock(randX, randY, randZ) == BOPCBlocks.newBopDirt)
                    	{
                    		int dirtMeta = world.getBlockMetadata(randX, randY, randZ);
                    		
                    		world.setBlock(randX, randY, randZ, BOPCBlocks.newBopGrass, (dirtMeta - (dirtMeta & 1)) / 2, 2);
                    	}
                    }
                }
            }
        }
    }

    @Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
        Block plant = plantable.getPlant(world, x, y + 1, z);
        EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

        switch (plantType)
        {
            case Cave:   return isSideSolid(world, x, y, z, UP);
            case Plains: return true;
            case Beach:
                boolean hasWater = (world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
                                    world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
                                    world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
                                    world.getBlock(x,     y, z + 1).getMaterial() == Material.water);
                return hasWater;
        }

        return super.canSustainPlant(world, x, y, z, direction, plantable);
    }
    
    @Override
    //			onApplyBonemeal - Dodgy Name
	public void func_149853_b(World world, Random random, int x, int y, int z)
    {
        int l = 0;

        while (l < 128)
        {
            int i1 = x;
            int j1 = y + 1;
            int k1 = z;
            int l1 = 0;

            while (true)
            {
                if (l1 < l / 16)
                {
                    i1 += random.nextInt(3) - 1;
                    j1 += (random.nextInt(3) - 1) * random.nextInt(3) / 2;
                    k1 += random.nextInt(3) - 1;

                    if (world.getBlock(i1, j1 - 1, k1) == BOPCBlocks.newBopGrass && !world.getBlock(i1, j1, k1).isNormalCube())
                    {
                        ++l1;
                        continue;
                    }
                }
                else if (world.getBlock(i1, j1, k1).getMaterial() == Material.air)
                {
                    if (random.nextInt(8) != 0)
                    {
                        if (Blocks.tallgrass.canBlockStay(world, i1, j1, k1))
                        {
                            world.setBlock(i1, j1, k1, Blocks.tallgrass, 1, 3);
                        }
                    }
                    else
                    {
                        world.getBiomeGenForCoords(i1, k1).plantFlower(world, random, i1, j1, k1);
                    }
                }

                ++l;
                break;
            }
        }
    }


    @Override
	public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
    	world.setBlock(x, y, z, BOPCBlocks.newBopDirt, world.getBlockMetadata(x, y, z) * 2, 2);
    }
	
    @Override
	public Item getItemDropped(int metadata, Random random, int fortune)
    {
        return BOPCBlocks.newBopDirt.getItemDropped(metadata * 2, random, fortune);
    }
    
    @Override
	public int damageDropped(int metadata)
    {
        return metadata * 2;
    }
	
	@Override
	public String getUnlocalizedName(String baseName, ItemStack itemStack) 
	{
		return baseName + "." + grassTypes[itemStack.getItemDamage()];
	}
	
	@Override
	public int getRenderType()
	{
		return RenderUtils.newGrassModel;
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTab, List blockList)
    {
    	for (int i = 0; i < grassTypes.length; i++)
    	{
            blockList.add(new ItemStack(block, 1, i));
    	}
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	for (int i = 0; i < grassTypes.length; i++)
    	{
    		String grassType = grassTypes[i];
    		
    		grassIcons[i * 2 + 0] = iconRegister.registerIcon("biomesoplenty:grass_" + grassType + "_side");
    		grassIcons[i * 2 + 1] = iconRegister.registerIcon("biomesoplenty:grass_" + grassType + "_side_snowed");
    	}
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
    	if (meta > grassTypes.length-1) meta = 0;
        return side == 1 ? Blocks.grass.getIcon(side, meta) : side == 0 ? BOPCBlocks.newBopDirt.getIcon(side, meta * 2) : grassIcons[2 * meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	if (meta > grassTypes.length-1) meta = 0;
    	
    	if (side == 0)
    	{
    		return BOPCBlocks.newBopDirt.getIcon(side, meta * 2);
    	}
    	else if (side == 1)
    	{
            return Blocks.grass.getIcon(world, x, y, z, side);
    	}
    	else
    	{
            Material material = world.getBlock(x, y + 1, z).getMaterial();
            return material != Material.snow && material != Material.craftedSnow ? grassIcons[2 * meta] : grassIcons[2 * meta + 1];
    	}
    }
}
