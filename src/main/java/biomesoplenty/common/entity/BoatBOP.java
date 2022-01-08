/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.entity;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.api.item.BOPItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

import java.util.Arrays;

public class BoatBOP extends Boat
{
    public BoatBOP(EntityType<? extends BoatBOP> type, Level level)
    {
        super(type, level);
        this.blocksBuilding = true;
    }

    public BoatBOP(Level level, double x, double y, double z)
    {
        this((EntityType<BoatBOP>)BOPEntities.BOAT, level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt)
    {
        nbt.putString("model", getModel().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt)
    {
        if (nbt.contains("model", Tag.TAG_STRING))
        {
            this.entityData.set(DATA_ID_TYPE, ModelType.byName(nbt.getString("model")).ordinal());
        }
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos)
    {
        this.lastYd = this.getDeltaMovement().y;
        if (!this.isPassenger())
        {
            if (onGround)
            {
                if (this.fallDistance > 3.0F)
                {
                    if (this.status != Boat.Status.ON_LAND)
                    {
                        this.resetFallDistance();
                        return;
                    }

                    this.causeFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
                    if (!this.level.isClientSide && !this.isRemoved())
                    {
                        this.kill();
                        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
                        {
                            for (int i = 0; i < 3; ++i)
                            {
                                this.spawnAtLocation(this.getModel().getPlanks());
                            }

                            for (int j = 0; j < 2; ++j)
                            {
                                this.spawnAtLocation(Items.STICK);
                            }
                        }
                    }
                }

                this.resetFallDistance();
            }
            else if (!this.level.getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0.0D)
            {
                this.fallDistance = (float) ((double) this.fallDistance - y);
            }

        }
    }

    @Override
    public Item getDropItem()
    {
        switch (ModelType.byId(this.entityData.get(DATA_ID_TYPE)))
        {
            case FIR:
                return BOPItems.FIR_BOAT;
            case REDWOOD:
                return BOPItems.REDWOOD_BOAT;
            case CHERRY:
                return BOPItems.CHERRY_BOAT;
            case MAHOGANY:
                return BOPItems.MAHOGANY_BOAT;
            case JACARANDA:
                return BOPItems.JACARANDA_BOAT;
            case PALM:
                return BOPItems.PALM_BOAT;
            case WILLOW:
                return BOPItems.WILLOW_BOAT;
            case DEAD:
                return BOPItems.DEAD_BOAT;
            case MAGIC:
                return BOPItems.MAGIC_BOAT;
            case UMBRAN:
                return BOPItems.UMBRAN_BOAT;
            case HELLBARK:
                return BOPItems.HELLBARK_BOAT;
        }
        return Items.OAK_BOAT;
    }

    public void setModel(ModelType type)
    {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    public ModelType getModel()
    {
        return ModelType.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Deprecated
    @Override
    public void setType(Type vanillaType) {}

    @Deprecated
    @Override
    public Type getBoatType()
    {
        return Type.OAK;
    }

    public enum ModelType
    {
        FIR("fir", BOPBlocks.FIR_PLANKS),
        REDWOOD("redwood", BOPBlocks.REDWOOD_PLANKS),
        CHERRY("cherry", BOPBlocks.CHERRY_PLANKS),
        MAHOGANY("mahogany", BOPBlocks.MAHOGANY_PLANKS),
        JACARANDA("jacaranda", BOPBlocks.JACARANDA_PLANKS),
        PALM("palm", BOPBlocks.PALM_PLANKS),
        WILLOW("willow", BOPBlocks.WILLOW_PLANKS),
        DEAD("dead", BOPBlocks.DEAD_PLANKS),
        MAGIC("magic", BOPBlocks.MAGIC_PLANKS),
        UMBRAN("umbran", BOPBlocks.UMBRAN_PLANKS),
        HELLBARK("hellbark", BOPBlocks.HELLBARK_PLANKS);

        private final String name;
        private final Block planks;

        ModelType(String name, Block planks)
        {
            this.name = name;
            this.planks = planks;
        }

        public String getName()
        {
            return this.name;
        }

        public Block getPlanks()
        {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        public static ModelType byId(int id)
        {
            ModelType[] type = values();
            return type[id < 0 || id >= type.length ? 0 : id];
        }

        public static ModelType byName(String aName)
        {
            ModelType[] type = values();
            return Arrays.stream(type).filter(t -> t.getName().equals(aName)).findFirst().orElse(type[0]);
        }
    }
}
