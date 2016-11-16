package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.item.ItemBOPBlock;
import biomesoplenty.common.util.block.VariantPagingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrassPath;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockBOPGrassPath extends BlockGrassPath implements IBOPBlock
{
    public static VariantPagingHelper<BlockBOPGrassPath, BlockBOPDirt.BOPDirtType> paging = new VariantPagingHelper<BlockBOPGrassPath, BlockBOPDirt.BOPDirtType>(3, BlockBOPDirt.BOPDirtType.class);

    private static IProperty currentVariantProperty;

    public static void createAllPages()
    {
        int numPages = paging.getNumPages();
        for (int i = 0; i < numPages; ++i)
        {
            currentVariantProperty = paging.getVariantProperty(i);
            paging.addBlock(i, new BlockBOPGrassPath());
        }
    }

    public IProperty variantProperty;

    @Override
    protected BlockStateContainer createBlockState()
    {
        this.variantProperty = currentVariantProperty;
        return new BlockStateContainer(this, new IProperty[] { this.variantProperty });
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] { this.variantProperty }; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {
        return "grass_" + ((BlockBOPDirt.BOPDirtType) state.getValue(this.variantProperty)).getName() + "_path";
    }
    @Override
    @SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor() { return null; }
    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getItemColor() { return null; }

    public BlockBOPGrassPath() {
        super();
        this.setHardness(0.65F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
        this.useNeighborBrightness = true;
        this.setHarvestLevel("shovel", 0);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(this.variantProperty, paging.getVariant(this, Math.min(2, meta & 7)));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        BlockBOPDirt.BOPDirtType dirt = (BlockBOPDirt.BOPDirtType) state.getValue(this.variantProperty);
        return paging.getIndex(dirt);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BOPBlocks.dirt);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return BOPBlocks.dirt.getMetaFromState(this.getDirtBlockState(state));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, BOPBlocks.dirt.getMetaFromState(this.getDirtBlockState(world.getBlockState(pos))));
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos neighborPos)
    {
        if (world.getBlockState(pos.up()).getMaterial().isSolid())
        {
            world.setBlockState(pos, this.getDirtBlockState(world.getBlockState(pos)));
        }
    }

    public IBlockState getDirtBlockState(IBlockState state)
    {
        switch ((BlockBOPDirt.BOPDirtType) state.getValue(this.variantProperty))
        {
            case LOAMY:
                return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.LOAMY);
            case SANDY:
                return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY);
            case SILTY:
                return BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SILTY);
            default:
                return Blocks.DIRT.getStateFromMeta(BlockDirt.DirtType.DIRT.getMetadata());
        }
    }
}