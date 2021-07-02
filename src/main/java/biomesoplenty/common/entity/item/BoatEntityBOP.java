package biomesoplenty.common.entity.item;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.init.ModEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Arrays;
import java.util.function.Supplier;

import net.minecraft.world.entity.vehicle.Boat.Status;
import net.minecraft.world.entity.vehicle.Boat.Type;

public class BoatEntityBOP extends Boat {
    public BoatEntityBOP(EntityType<? extends BoatEntityBOP> type, Level world) {
        super(type, world);
    }

    public BoatEntityBOP(Level world, double x, double y, double z) {
        super(ModEntities.boat, world);
        setPos(x, y, z);
        setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public BoatEntityBOP(FMLPlayMessages.SpawnEntity spawnEntity, Level world) {
        this(world, spawnEntity.getPosX(), spawnEntity.getPosY(), spawnEntity.getPosZ());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putString("model", getModel().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        if (nbt.contains("model", Constants.NBT.TAG_STRING)) {
            this.entityData.set(DATA_ID_TYPE, BoatModel.byName(nbt.getString("model")).ordinal());
        }
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.lastYd = getDeltaMovement().y;
        if (!isPassenger()) {
            if (onGround) {
                if (this.fallDistance > 3f) {
                    if (this.status != Status.ON_LAND) {
                        this.fallDistance = 0f;
                        return;
                    }
                    causeFallDamage(this.fallDistance, 1f);
                    if (!this.level.isClientSide && !this.removed) {
                        this.remove();
                        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            for (int i = 0; i < 3; ++i) {
                                spawnAtLocation(getModel().getPlanks());
                            }
                            for (int j = 0; j < 2; ++j) {
                                spawnAtLocation(Items.STICK);
                            }
                        }
                    }
                }
                this.fallDistance = 0f;
            } else if (!this.level.getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0d) {
                this.fallDistance = (float) ((double) this.fallDistance - y);
            }
        }
    }

    @Override
    public Item getDropItem() {
        switch (BoatModel.byId(this.entityData.get(DATA_ID_TYPE))) {
            case FIR:
                return BOPItems.fir_boat;
            case REDWOOD:
                return BOPItems.redwood_boat;
            case CHERRY:
                return BOPItems.cherry_boat;
            case MAHOGANY:
                return BOPItems.mahogany_boat;
            case JACARANDA:
                return BOPItems.jacaranda_boat;
            case PALM:
                return BOPItems.palm_boat;
            case WILLOW:
                return BOPItems.willow_boat;
            case DEAD:
                return BOPItems.dead_boat;
            case MAGIC:
                return BOPItems.magic_boat;
            case UMBRAN:
                return BOPItems.umbran_boat;
            case HELLBARK:
                return BOPItems.hellbark_boat;
        }
        return Items.OAK_BOAT;
    }

    public BoatEntityBOP withModel(BoatModel type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
        return this;
    }

    public BoatModel getModel() {
        return BoatModel.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Deprecated
    @Override
    public void setType(Type vanillaType) {
    }

    @Deprecated
    @Override
    public Type getBoatType() {
        return Type.OAK;
    }

    public enum BoatModel {
        FIR("fir", () -> BOPBlocks.fir_planks),
        REDWOOD("redwood", () -> BOPBlocks.redwood_planks),
        CHERRY("cherry", () -> BOPBlocks.cherry_planks),
        MAHOGANY("mahogany", () -> BOPBlocks.mahogany_planks),
        JACARANDA("jacaranda", () -> BOPBlocks.jacaranda_planks),
        PALM("palm", () -> BOPBlocks.palm_planks),
        WILLOW("willow", () -> BOPBlocks.willow_planks),
        DEAD("dead", () -> BOPBlocks.dead_planks),
        MAGIC("magic", () -> BOPBlocks.magic_planks),
        UMBRAN("umbran", () -> BOPBlocks.umbran_planks),
        HELLBARK("hellbark", () -> BOPBlocks.hellbark_planks);

        private final String name;
        private final Supplier<Block> supplierPlanks;

        BoatModel(String name, Supplier<Block> supplierPlanks) {
            this.name = name;
            this.supplierPlanks = supplierPlanks;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.supplierPlanks.get();
        }

        public String toString() {
            return this.name;
        }

        public static BoatModel byId(int id) {
            BoatModel[] type = values();
            return type[id < 0 || id >= type.length ? 0 : id];
        }

        public static BoatModel byName(String aName) {
            BoatModel[] type = values();
            return Arrays.stream(type).filter(t -> t.getName().equals(aName)).findFirst().orElse(type[0]);
        }
    }
}
