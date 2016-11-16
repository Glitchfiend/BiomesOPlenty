package biomesoplenty.common.entities.ai;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPPlant;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIEatBOPGrass extends EntityAIEatGrass
{
    private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
    private static final Predicate<IBlockState> IS_SHORT_GRASS = forBoPPlant(BOPPlants.SHORTGRASS);
    private static final Predicate<IBlockState> IS_MEDIUM_GRASS = forBoPPlant(BOPPlants.MEDIUMGRASS);
    private static final Predicate<IBlockState> IS_WHEAT_GRASS = forBoPPlant(BOPPlants.WHEATGRASS);
    private static final Predicate<IBlockState> IS_DAMP_GRASS = forBoPPlant(BOPPlants.DAMPGRASS);
    private EntityLiving sheep;
    private World world;
    int bopEatingGrassTimer;

    public EntityAIEatBOPGrass(EntityLiving entityLiving)
    {
        super(entityLiving);
        this.sheep = entityLiving;
        this.world = entityLiving.world;
        this.setMutexBits(7);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.sheep.getRNG().nextInt(this.sheep.isChild() ? 50 : 1000) != 0)
        {
            return false;
        }
        else
        {
            BlockPos pos = new BlockPos(this.sheep.posX, this.sheep.posY, this.sheep.posZ);
            IBlockState state = this.world.getBlockState(pos);
            return IS_TALL_GRASS.apply(state) || IS_SHORT_GRASS.apply(state) || IS_MEDIUM_GRASS.apply(state) || IS_WHEAT_GRASS.apply(state) || IS_DAMP_GRASS.apply(state)
                    || this.world.getBlockState(pos.down()).getBlock() instanceof BlockGrass;
        }
    }

    @Override
    public void startExecuting()
    {
        this.bopEatingGrassTimer = 40;
        this.world.setEntityState(this.sheep, (byte)10);
        this.sheep.getNavigator().clearPathEntity();
    }

    @Override
    public void resetTask()
    {
        this.bopEatingGrassTimer = 0;
    }

    @Override
    public boolean continueExecuting()
    {
        return this.bopEatingGrassTimer > 0;
    }

    @Override
    public int getEatingGrassTimer()
    {
        return this.bopEatingGrassTimer;
    }

    @Override
    public void updateTask()
    {
        this.bopEatingGrassTimer = Math.max(0, this.bopEatingGrassTimer - 1);

        if (this.bopEatingGrassTimer == 4)
        {
            BlockPos pos = new BlockPos(this.sheep.posX, this.sheep.posY, this.sheep.posZ);
            IBlockState state = this.world.getBlockState(pos);

            if (IS_TALL_GRASS.apply(state) || IS_SHORT_GRASS.apply(state) || IS_MEDIUM_GRASS.apply(state) || IS_WHEAT_GRASS.apply(state) || IS_DAMP_GRASS.apply(state))
            {
                if (this.world.getGameRules().getBoolean("mobGriefing"))
                {
                    this.world.destroyBlock(pos, false);
                }

                this.sheep.eatGrassBonus();
            }
            else {
                BlockPos posDown = pos.down();
                IBlockState stateDown = world.getBlockState(posDown);

                if (stateDown.getBlock() instanceof BlockBOPGrass)
                {
                    BlockBOPGrass grass = (BlockBOPGrass) stateDown.getBlock();
                    Block dirtBlock = grass.getDirtBlockState(stateDown).getBlock();

                    if (dirtBlock instanceof BlockBOPDirt)
                    {
                        if (this.world.getGameRules().getBoolean("mobGriefing"))
                        {
                            this.world.playEvent(2001, posDown, Block.getIdFromBlock(BOPBlocks.grass));
                            this.world.setBlockState(posDown, grass.getDirtBlockState(stateDown), 2);
                        }

                    } else if (stateDown.getValue(BlockBOPGrass.VARIANT) == BlockBOPGrass.BOPGrassType.DAISY)
                    {
                        if (this.world.getGameRules().getBoolean("mobGriefing"))
                        {
                            this.world.playEvent(2001, posDown, Block.getIdFromBlock(BOPBlocks.grass));
                            this.world.setBlockState(posDown, Blocks.DIRT.getDefaultState(), 2);
                        }
                    }

                    this.sheep.eatGrassBonus();
                }
            }
        }
    }

    private static BlockStateMatcher forBoPPlant (BOPPlants plant)
    {
        return BlockStateMatcher.forBlock(BOPBlocks.plant_0).where(BlockBOPPlant.paging.getVariantProperty(BlockBOPPlant.paging.getPageNum(plant)), Predicates.equalTo(plant));
    }
}