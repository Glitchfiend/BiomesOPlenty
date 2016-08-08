package biomesoplenty.common.entities.ai;

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
    private EntityLiving sheep;
    private World world;
    int bopEatingGrassTimer;

    public EntityAIEatBOPGrass(EntityLiving grassEaterEntityIn)
    {
        super(grassEaterEntityIn);
        this.sheep = grassEaterEntityIn;
        this.world = grassEaterEntityIn.worldObj;
        this.setMutexBits(7);
    }

    public boolean shouldExecute()
    {
        if (this.sheep.getRNG().nextInt(this.sheep.isChild() ? 50 : 1000) != 0)
        {
            return false;
        }
        else
        {
            BlockPos blockpos = new BlockPos(this.sheep.posX, this.sheep.posY, this.sheep.posZ);
            return IS_TALL_GRASS.apply(this.world.getBlockState(blockpos)) ? true : this.world.getBlockState(blockpos.down()).getBlock() instanceof BlockGrass;
        }
    }

    public void startExecuting()
    {
        this.bopEatingGrassTimer = 40;
        this.world.setEntityState(this.sheep, (byte)10);
        this.sheep.getNavigator().clearPathEntity();
    }

    public void resetTask()
    {
        this.bopEatingGrassTimer = 0;
    }

    public boolean continueExecuting()
    {
        return this.bopEatingGrassTimer > 0;
    }

    public int getEatingGrassTimer()
    {
        return this.bopEatingGrassTimer;
    }

    public void updateTask()
    {
        this.bopEatingGrassTimer = Math.max(0, this.bopEatingGrassTimer - 1);

        if (this.bopEatingGrassTimer == 4)
        {
            BlockPos blockpos = new BlockPos(this.sheep.posX, this.sheep.posY, this.sheep.posZ);

            if (IS_TALL_GRASS.apply(this.world.getBlockState(blockpos)))
            {
                if (this.world.getGameRules().getBoolean("mobGriefing"))
                {
                    this.world.destroyBlock(blockpos, false);
                }

                this.sheep.eatGrassBonus();
            }
            else
            {
                BlockPos blockpos1 = blockpos.down();

                if (this.world.getBlockState(blockpos1).getBlock() == Blocks.GRASS)
                {
                    if (this.world.getGameRules().getBoolean("mobGriefing"))
                    {
                        this.world.playEvent(2001, blockpos1, Block.getIdFromBlock(Blocks.GRASS));
                        this.world.setBlockState(blockpos1, Blocks.DIRT.getDefaultState(), 2);
                    }

                    this.sheep.eatGrassBonus();
                }
            }
        }
    }
}