/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
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
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
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
        this((EntityType<BoatBOP>)BOPEntities.BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket()
    {
        return new ClientboundAddEntityPacket(this);
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
                this.fallDistance -= (float)y;
            }
        }
    }

    @Override
    public Item getDropItem()
    {
        switch (ModelType.byId(this.entityData.get(DATA_ID_TYPE)))
        {
            case FIR:
                return BOPItems.FIR_BOAT.get();
            case REDWOOD:
                return BOPItems.REDWOOD_BOAT.get();
            case CHERRY:
                return BOPItems.CHERRY_BOAT.get();
            case MAHOGANY:
                return BOPItems.MAHOGANY_BOAT.get();
            case JACARANDA:
                return BOPItems.JACARANDA_BOAT.get();
            case PALM:
                return BOPItems.PALM_BOAT.get();
            case WILLOW:
                return BOPItems.WILLOW_BOAT.get();
            case DEAD:
                return BOPItems.DEAD_BOAT.get();
            case MAGIC:
                return BOPItems.MAGIC_BOAT.get();
            case UMBRAN:
                return BOPItems.UMBRAN_BOAT.get();
            case HELLBARK:
                return BOPItems.HELLBARK_BOAT.get();
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
    public void setVariant(Type vanillaType) {}

    @Deprecated
    @Override
    public Type getVariant()
    {
        return Type.OAK;
    }

    public enum ModelType
    {
        FIR("fir", BOPBlocks.FIR_PLANKS.get()),
        REDWOOD("redwood", BOPBlocks.REDWOOD_PLANKS.get()),
        CHERRY("cherry", BOPBlocks.CHERRY_PLANKS.get()),
        MAHOGANY("mahogany", BOPBlocks.MAHOGANY_PLANKS.get()),
        JACARANDA("jacaranda", BOPBlocks.JACARANDA_PLANKS.get()),
        PALM("palm", BOPBlocks.PALM_PLANKS.get()),
        WILLOW("willow", BOPBlocks.WILLOW_PLANKS.get()),
        DEAD("dead", BOPBlocks.DEAD_PLANKS.get()),
        MAGIC("magic", BOPBlocks.MAGIC_PLANKS.get()),
        UMBRAN("umbran", BOPBlocks.UMBRAN_PLANKS.get()),
        HELLBARK("hellbark", BOPBlocks.HELLBARK_PLANKS.get());

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
