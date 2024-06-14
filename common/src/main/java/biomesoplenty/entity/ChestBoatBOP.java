/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.entity;

import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.api.item.BOPItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ChestBoatBOP extends ChestBoat
{
    public ChestBoatBOP(EntityType<? extends ChestBoatBOP> type, Level level)
    {
        super(type, level);
        this.blocksBuilding = true;
    }

    public ChestBoatBOP(Level level, double x, double y, double z)
    {
        this((EntityType<ChestBoatBOP>) BOPEntities.CHEST_BOAT, level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity serverEntity)
    {
        return new ClientboundAddEntityPacket(this, serverEntity);
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
            this.entityData.set(DATA_ID_TYPE, BoatBOP.ModelType.byName(nbt.getString("model")).ordinal());
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
                    if (this.status != Status.ON_LAND)
                    {
                        this.resetFallDistance();
                        return;
                    }

                    this.causeFallDamage(this.fallDistance, 1.0F, this.damageSources().fall());
                    if (!this.level().isClientSide && !this.isRemoved())
                    {
                        this.kill();
                        if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
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
            else if (!this.level().getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0.0D)
            {
                this.fallDistance -= (float)y;
            }
        }
    }

    @Override
    public Item getDropItem()
    {
        switch (BoatBOP.ModelType.byId(this.entityData.get(DATA_ID_TYPE)))
        {
            case FIR:
                return BOPItems.FIR_CHEST_BOAT;
            case PINE:
                return BOPItems.PINE_CHEST_BOAT;
            case MAPLE:
                return BOPItems.MAPLE_CHEST_BOAT;
            case REDWOOD:
                return BOPItems.REDWOOD_CHEST_BOAT;
            case MAHOGANY:
                return BOPItems.MAHOGANY_CHEST_BOAT;
            case JACARANDA:
                return BOPItems.JACARANDA_CHEST_BOAT;
            case PALM:
                return BOPItems.PALM_CHEST_BOAT;
            case WILLOW:
                return BOPItems.WILLOW_CHEST_BOAT;
            case DEAD:
                return BOPItems.DEAD_CHEST_BOAT;
            case MAGIC:
                return BOPItems.MAGIC_CHEST_BOAT;
            case UMBRAN:
                return BOPItems.UMBRAN_CHEST_BOAT;
            case HELLBARK:
                return BOPItems.HELLBARK_CHEST_BOAT;
            case EMPYREAL:
                return BOPItems.EMPYREAL_CHEST_BOAT;
        }
        return Items.OAK_CHEST_BOAT;
    }

    public void setModel(BoatBOP.ModelType type)
    {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    public BoatBOP.ModelType getModel()
    {
        return BoatBOP.ModelType.byId(this.entityData.get(DATA_ID_TYPE));
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
}
