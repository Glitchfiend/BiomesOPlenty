package biomesoplenty.common.entity.item;

import java.util.List;

import javax.annotation.Nullable;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Items;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.client.CPacketSteerBoat;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityBoatBOP extends BoatEntity
{
	   private static final DataParameter<Integer> TIME_SINCE_HIT = EntityDataManager.createKey(EntityBoatBOP.class, DataSerializers.VARINT);
	   private static final DataParameter<Integer> FORWARD_DIRECTION = EntityDataManager.createKey(EntityBoatBOP.class, DataSerializers.VARINT);
	   private static final DataParameter<Float> DAMAGE_TAKEN = EntityDataManager.createKey(EntityBoatBOP.class, DataSerializers.FLOAT);
	   private static final DataParameter<Integer> BOAT_TYPE = EntityDataManager.createKey(EntityBoatBOP.class, DataSerializers.VARINT);
	   private static final DataParameter<Boolean> field_199704_e = EntityDataManager.createKey(EntityBoatBOP.class, DataSerializers.BOOLEAN);
	   private static final DataParameter<Boolean> field_199705_f = EntityDataManager.createKey(EntityBoatBOP.class, DataSerializers.BOOLEAN);
	   private static final DataParameter<Integer> ROCKING_TICKS = EntityDataManager.createKey(EntityBoatBOP.class, DataSerializers.VARINT);
	   private final float[] paddlePositions = new float[2];
	   /** How much of current speed to retain. Value zero to one. */
	   private float momentum;
	   private float outOfControlTicks;
	   private float deltaRotation;
	   private int lerpSteps;
	   private double lerpX;
	   private double lerpY;
	   private double lerpZ;
	   private double lerpYaw;
	   private double lerpPitch;
	   private boolean leftInputDown;
	   private boolean rightInputDown;
	   private boolean forwardInputDown;
	   private boolean backInputDown;
	   private double waterLevel;
	   /**
	    * How much the boat should glide given the slippery blocks it's currently gliding over.
	    * Halved every tick.
	    */
	   private float boatGlide;
	   private EntityBoatBOP.Status status;
	   private EntityBoatBOP.Status previousStatus;
	   private double lastYd;
	   private boolean rocking;
	   private boolean field_203060_aN;
	   private float rockingIntensity;
	   private float rockingAngle;
	   private float prevRockingAngle;

	   public EntityBoatBOP(World worldIn) {
	      super(worldIn);
	      this.preventEntitySpawning = true;
	      this.setSize(1.375F, 0.5625F);
	   }

	   public EntityBoatBOP(World worldIn, double x, double y, double z) {
	      this(worldIn);
	      this.setPosition(x, y, z);
	      this.motionX = 0.0D;
	      this.motionY = 0.0D;
	      this.motionZ = 0.0D;
	      this.prevPosX = x;
	      this.prevPosY = y;
	      this.prevPosZ = z;
	   }

	   /**
	    * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	    * prevent them from trampling crops
	    */
	   @Override
	   protected boolean canTriggerWalking() {
	      return false;
	   }

	   @Override
	   protected void registerData() {
	      this.dataManager.register(TIME_SINCE_HIT, 0);
	      this.dataManager.register(FORWARD_DIRECTION, 1);
	      this.dataManager.register(DAMAGE_TAKEN, 0.0F);
	      this.dataManager.register(BOAT_TYPE, EntityBoatBOP.Type.FIR.ordinal());
	      this.dataManager.register(field_199704_e, false);
	      this.dataManager.register(field_199705_f, false);
	      this.dataManager.register(ROCKING_TICKS, 0);
	   }

	   /**
	    * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
	    * pushable on contact, like boats or minecarts.
	    */
	   @Nullable
	   @Override
	   public AxisAlignedBB getCollisionBox(Entity entityIn) {
	      return entityIn.canBePushed() ? entityIn.getBoundingBox() : null;
	   }

	   /**
	    * Returns the <b>solid</b> collision bounding box for this entity. Used to make (e.g.) boats solid. Return null if
	    * this entity is not solid.
	    *  
	    * For general purposes, use {@link #width} and {@link #height}.
	    *  
	    * @see getEntityBoundingBox
	    */
	   @Nullable
	   @Override
	   public AxisAlignedBB getCollisionBoundingBox() {
	      return this.getBoundingBox();
	   }

	   /**
	    * Returns true if this entity should push and be pushed by other entities when colliding.
	    */
	   @Override
	   public boolean canBePushed() {
	      return true;
	   }

	   /**
	    * Returns the Y offset from the entity's position for any entity riding this one.
	    */
	   @Override
	   public double getMountedYOffset() {
	      return -0.1D;
	   }

	   /**
	    * Called when the entity is attacked.
	    */
	   @Override
	   public boolean attackEntityFrom(DamageSource source, float amount) {
	      if (this.isInvulnerableTo(source)) {
	         return false;
	      } else if (!this.world.isRemote && !this.removed) {
	         if (source instanceof EntityDamageSourceIndirect && source.getTrueSource() != null && this.isPassenger(source.getTrueSource())) {
	            return false;
	         } else {
	            this.setForwardDirection(-this.getForwardDirection());
	            this.setTimeSinceHit(10);
	            this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
	            this.markVelocityChanged();
	            boolean flag = source.getTrueSource() instanceof EntityPlayer && ((EntityPlayer)source.getTrueSource()).abilities.isCreativeMode;
	            if (flag || this.getDamageTaken() > 40.0F) {
	               if (!flag && this.world.getGameRules().getBoolean("doEntityDrops")) {
	                  this.entityDropItem(this.getItemBoat());
	               }

	               this.remove();
	            }

	            return true;
	         }
	      } else {
	         return true;
	      }
	   }

	   @Override
	   public void onEnterBubbleColumnWithAirAbove(boolean downwards) {
	      if (!this.world.isRemote) {
	         this.rocking = true;
	         this.field_203060_aN = downwards;
	         if (this.getRockingTicks() == 0) {
	            this.setRockingTicks(60);
	         }
	      }

	      this.world.addParticle(Particles.SPLASH, this.posX + (double)this.rand.nextFloat(), this.posY + 0.7D, this.posZ + (double)this.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
	      if (this.rand.nextInt(20) == 0) {
	         this.world.playSound(this.posX, this.posY, this.posZ, this.getSplashSound(), this.getSoundCategory(), 1.0F, 0.8F + 0.4F * this.rand.nextFloat(), false);
	      }

	   }

	   /**
	    * Applies a velocity to the entities, to push them away from eachother.
	    */
	   @Override
	   public void applyEntityCollision(Entity entityIn) {
	      if (entityIn instanceof EntityBoat) {
	         if (entityIn.getBoundingBox().minY < this.getBoundingBox().maxY) {
	            super.applyEntityCollision(entityIn);
	         }
	      } else if (entityIn.getBoundingBox().minY <= this.getBoundingBox().minY) {
	         super.applyEntityCollision(entityIn);
	      }

	   }

	   @Override
	   public Item getItemBoat() {
	      switch(this.getBOPBoatType()) {
	      case FIR:
	      default:
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
	      case ETHEREAL:
	    	 return BOPItems.ethereal_boat;
	      }
	   }

	   /**
	    * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
	    */
	   @OnlyIn(Dist.CLIENT)
	   @Override
	   public void performHurtAnimation() {
	      this.setForwardDirection(-this.getForwardDirection());
	      this.setTimeSinceHit(10);
	      this.setDamageTaken(this.getDamageTaken() * 11.0F);
	   }

	   /**
	    * Returns true if other Entities should be prevented from moving through this Entity.
	    */
	   @Override
	   public boolean canBeCollidedWith() {
	      return !this.removed;
	   }

	   /**
	    * Sets a target for the client to interpolate towards over the next few ticks
	    */
	   @OnlyIn(Dist.CLIENT)
	   @Override
	   public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
	      this.lerpX = x;
	      this.lerpY = y;
	      this.lerpZ = z;
	      this.lerpYaw = (double)yaw;
	      this.lerpPitch = (double)pitch;
	      this.lerpSteps = 10;
	   }

	   /**
	    * Gets the horizontal facing direction of this Entity, adjusted to take specially-treated entity types into account.
	    */
	   @Override
	   public Direction getAdjustedHorizontalFacing() {
	      return this.getHorizontalFacing().rotateY();
	   }

	   /**
	    * Called to update the entity's position/logic.
	    */
	   @Override
	   public void tick() {
	      this.previousStatus = this.status;
	      this.status = this.getBoatStatus();
	      if (this.status != EntityBoatBOP.Status.UNDER_WATER && this.status != EntityBoatBOP.Status.UNDER_FLOWING_WATER) {
	         this.outOfControlTicks = 0.0F;
	      } else {
	         ++this.outOfControlTicks;
	      }

	      if (!this.world.isRemote && this.outOfControlTicks >= 60.0F) {
	         this.removePassengers();
	      }

	      if (this.getTimeSinceHit() > 0) {
	         this.setTimeSinceHit(this.getTimeSinceHit() - 1);
	      }

	      if (this.getDamageTaken() > 0.0F) {
	         this.setDamageTaken(this.getDamageTaken() - 1.0F);
	      }

	      this.prevPosX = this.posX;
	      this.prevPosY = this.posY;
	      this.prevPosZ = this.posZ;
	      //super.tick();
	      this.tickLerp();
	      if (this.canPassengerSteer()) {
	         if (this.getPassengers().isEmpty() || !(this.getPassengers().get(0) instanceof EntityPlayer)) {
	            this.setPaddleState(false, false);
	         }

	         this.updateMotion();
	         if (this.world.isRemote) {
	            this.controlBoat();
	            this.world.sendPacketToServer(new CPacketSteerBoat(this.getPaddleState(0), this.getPaddleState(1)));
	         }

	         this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	      } else {
	         this.motionX = 0.0D;
	         this.motionY = 0.0D;
	         this.motionZ = 0.0D;
	      }

	      this.updateRocking();

	      for(int i = 0; i <= 1; ++i) {
	         if (this.getPaddleState(i)) {
	            if (!this.isSilent() && (double)(this.paddlePositions[i] % ((float)Math.PI * 2F)) <= (double)((float)Math.PI / 4F) && ((double)this.paddlePositions[i] + (double)((float)Math.PI / 8F)) % (double)((float)Math.PI * 2F) >= (double)((float)Math.PI / 4F)) {
	               SoundEvent soundevent = this.getPaddleSound();
	               if (soundevent != null) {
	                  Vec3d vec3d = this.getLook(1.0F);
	                  double d0 = i == 1 ? -vec3d.z : vec3d.z;
	                  double d1 = i == 1 ? vec3d.x : -vec3d.x;
	                  this.world.playSound((EntityPlayer)null, this.posX + d0, this.posY, this.posZ + d1, soundevent, this.getSoundCategory(), 1.0F, 0.8F + 0.4F * this.rand.nextFloat());
	               }
	            }

	            this.paddlePositions[i] = (float)((double)this.paddlePositions[i] + (double)((float)Math.PI / 8F));
	         } else {
	            this.paddlePositions[i] = 0.0F;
	         }
	      }

	      this.doBlockCollisions();
	      List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow((double)0.2F, (double)-0.01F, (double)0.2F), EntitySelectors.pushableBy(this));
	      if (!list.isEmpty()) {
	         boolean flag = !this.world.isRemote && !(this.getControllingPassenger() instanceof EntityPlayer);

	         for(int j = 0; j < list.size(); ++j) {
	            Entity entity = list.get(j);
	            if (!entity.isPassenger(this)) {
	               if (flag && this.getPassengers().size() < 2 && !entity.isPassenger() && entity.width < this.width && entity instanceof EntityLivingBase && !(entity instanceof EntityWaterMob) && !(entity instanceof EntityPlayer)) {
	                  entity.startRiding(this);
	               } else {
	                  this.applyEntityCollision(entity);
	               }
	            }
	         }
	      }

	   }

	   private void updateRocking() {
	      if (this.world.isRemote) {
	         int i = this.getRockingTicks();
	         if (i > 0) {
	            this.rockingIntensity += 0.05F;
	         } else {
	            this.rockingIntensity -= 0.1F;
	         }

	         this.rockingIntensity = MathHelper.clamp(this.rockingIntensity, 0.0F, 1.0F);
	         this.prevRockingAngle = this.rockingAngle;
	         this.rockingAngle = 10.0F * (float)Math.sin((double)(0.5F * (float)this.world.getGameTime())) * this.rockingIntensity;
	      } else {
	         if (!this.rocking) {
	            this.setRockingTicks(0);
	         }

	         int k = this.getRockingTicks();
	         if (k > 0) {
	            --k;
	            this.setRockingTicks(k);
	            int j = 60 - k - 1;
	            if (j > 0 && k == 0) {
	               this.setRockingTicks(0);
	               if (this.field_203060_aN) {
	                  this.motionY -= 0.7D;
	                  this.removePassengers();
	               } else {
	                  this.motionY = this.isPassenger(EntityPlayer.class) ? 2.7D : 0.6D;
	               }
	            }

	            this.rocking = false;
	         }
	      }

	   }

	   @Nullable
	   @Override
	   protected SoundEvent getPaddleSound() {
	      switch(this.getBoatStatus()) {
	      case IN_WATER:
	      case UNDER_WATER:
	      case UNDER_FLOWING_WATER:
	         return SoundEvents.ENTITY_BOAT_PADDLE_WATER;
	      case ON_LAND:
	         return SoundEvents.ENTITY_BOAT_PADDLE_LAND;
	      case IN_AIR:
	      default:
	         return null;
	      }
	   }

	   private void tickLerp() {
	      if (this.lerpSteps > 0 && !this.canPassengerSteer()) {
	         double d0 = this.posX + (this.lerpX - this.posX) / (double)this.lerpSteps;
	         double d1 = this.posY + (this.lerpY - this.posY) / (double)this.lerpSteps;
	         double d2 = this.posZ + (this.lerpZ - this.posZ) / (double)this.lerpSteps;
	         double d3 = MathHelper.wrapDegrees(this.lerpYaw - (double)this.rotationYaw);
	         this.rotationYaw = (float)((double)this.rotationYaw + d3 / (double)this.lerpSteps);
	         this.rotationPitch = (float)((double)this.rotationPitch + (this.lerpPitch - (double)this.rotationPitch) / (double)this.lerpSteps);
	         --this.lerpSteps;
	         this.setPosition(d0, d1, d2);
	         this.setRotation(this.rotationYaw, this.rotationPitch);
	      }
	   }

	   @Override
	   public void setPaddleState(boolean left, boolean right) {
	      this.dataManager.set(field_199704_e, left);
	      this.dataManager.set(field_199705_f, right);
	   }

	   @OnlyIn(Dist.CLIENT)
	   @Override
	   public float getRowingTime(int side, float limbSwing) {
	      return this.getPaddleState(side) ? (float)MathHelper.clampedLerp((double)this.paddlePositions[side] - (double)((float)Math.PI / 8F), (double)this.paddlePositions[side], (double)limbSwing) : 0.0F;
	   }

	   /**
	    * Determines whether the boat is in water, gliding on land, or in air
	    */
	   private EntityBoatBOP.Status getBoatStatus() {
	      EntityBoatBOP.Status entityboat$status = this.getUnderwaterStatus();
	      if (entityboat$status != null) {
	         this.waterLevel = this.getBoundingBox().maxY;
	         return entityboat$status;
	      } else if (this.checkInWater()) {
	         return EntityBoatBOP.Status.IN_WATER;
	      } else {
	         float f = this.getBoatGlide();
	         if (f > 0.0F) {
	            this.boatGlide = f;
	            return EntityBoatBOP.Status.ON_LAND;
	         } else {
	            return EntityBoatBOP.Status.IN_AIR;
	         }
	      }
	   }

	   @Override
	   public float getWaterLevelAbove() {
	      AxisAlignedBB axisalignedbb = this.getBoundingBox();
	      int i = MathHelper.floor(axisalignedbb.minX);
	      int j = MathHelper.ceil(axisalignedbb.maxX);
	      int k = MathHelper.floor(axisalignedbb.maxY);
	      int l = MathHelper.ceil(axisalignedbb.maxY - this.lastYd);
	      int i1 = MathHelper.floor(axisalignedbb.minZ);
	      int j1 = MathHelper.ceil(axisalignedbb.maxZ);

	      try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain()) {
	         label161:
	         for(int k1 = k; k1 < l; ++k1) {
	            float f = 0.0F;

	            for(int l1 = i; l1 < j; ++l1) {
	               for(int i2 = i1; i2 < j1; ++i2) {
	                  blockpos$pooledmutableblockpos.setPos(l1, k1, i2);
	                  IFluidState ifluidstate = this.world.getFluidState(blockpos$pooledmutableblockpos);
	                  if (ifluidstate.isTagged(FluidTags.WATER)) {
	                     f = Math.max(f, (float)k1 + ifluidstate.getHeight());
	                  }

	                  if (f >= 1.0F) {
	                     continue label161;
	                  }
	               }
	            }

	            if (f < 1.0F) {
	               float f2 = (float)blockpos$pooledmutableblockpos.getY() + f;
	               return f2;
	            }
	         }

	         float f1 = (float)(l + 1);
	         return f1;
	      }
	   }

	   /**
	    * Decides how much the boat should be gliding on the land (based on any slippery blocks)
	    */
	   @Override
	   public float getBoatGlide() {
	      AxisAlignedBB axisalignedbb = this.getBoundingBox();
	      AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY - 0.001D, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
	      int i = MathHelper.floor(axisalignedbb1.minX) - 1;
	      int j = MathHelper.ceil(axisalignedbb1.maxX) + 1;
	      int k = MathHelper.floor(axisalignedbb1.minY) - 1;
	      int l = MathHelper.ceil(axisalignedbb1.maxY) + 1;
	      int i1 = MathHelper.floor(axisalignedbb1.minZ) - 1;
	      int j1 = MathHelper.ceil(axisalignedbb1.maxZ) + 1;
	      VoxelShape voxelshape = VoxelShapes.create(axisalignedbb1);
	      float f = 0.0F;
	      int k1 = 0;

	      try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain()) {
	         for(int l1 = i; l1 < j; ++l1) {
	            for(int i2 = i1; i2 < j1; ++i2) {
	               int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
	               if (j2 != 2) {
	                  for(int k2 = k; k2 < l; ++k2) {
	                     if (j2 <= 0 || k2 != k && k2 != l - 1) {
	                        blockpos$pooledmutableblockpos.setPos(l1, k2, i2);
	                        BlockState BlockState = this.world.getBlockState(blockpos$pooledmutableblockpos);
	                        if (!(BlockState.getBlock() instanceof BlockLilyPad) && VoxelShapes.compare(BlockState.getCollisionShape(this.world, blockpos$pooledmutableblockpos).withOffset((double)l1, (double)k2, (double)i2), voxelshape, IBooleanFunction.AND)) {
	                           f += BlockState.getSlipperiness(world, blockpos$pooledmutableblockpos, this);
	                           ++k1;
	                        }
	                     }
	                  }
	               }
	            }
	         }
	      }

	      return f / (float)k1;
	   }

	   private boolean checkInWater() {
	      AxisAlignedBB axisalignedbb = this.getBoundingBox();
	      int i = MathHelper.floor(axisalignedbb.minX);
	      int j = MathHelper.ceil(axisalignedbb.maxX);
	      int k = MathHelper.floor(axisalignedbb.minY);
	      int l = MathHelper.ceil(axisalignedbb.minY + 0.001D);
	      int i1 = MathHelper.floor(axisalignedbb.minZ);
	      int j1 = MathHelper.ceil(axisalignedbb.maxZ);
	      boolean flag = false;
	      this.waterLevel = Double.MIN_VALUE;

	      try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain()) {
	         for(int k1 = i; k1 < j; ++k1) {
	            for(int l1 = k; l1 < l; ++l1) {
	               for(int i2 = i1; i2 < j1; ++i2) {
	                  blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
	                  IFluidState ifluidstate = this.world.getFluidState(blockpos$pooledmutableblockpos);
	                  if (ifluidstate.isTagged(FluidTags.WATER)) {
	                     float f = (float)l1 + ifluidstate.getHeight();
	                     this.waterLevel = Math.max((double)f, this.waterLevel);
	                     flag |= axisalignedbb.minY < (double)f;
	                  }
	               }
	            }
	         }
	      }

	      return flag;
	   }

	   /**
	    * Decides whether the boat is currently underwater.
	    */
	   @Nullable
	   private EntityBoatBOP.Status getUnderwaterStatus() {
	      AxisAlignedBB axisalignedbb = this.getBoundingBox();
	      double d0 = axisalignedbb.maxY + 0.001D;
	      int i = MathHelper.floor(axisalignedbb.minX);
	      int j = MathHelper.ceil(axisalignedbb.maxX);
	      int k = MathHelper.floor(axisalignedbb.maxY);
	      int l = MathHelper.ceil(d0);
	      int i1 = MathHelper.floor(axisalignedbb.minZ);
	      int j1 = MathHelper.ceil(axisalignedbb.maxZ);
	      boolean flag = false;

	      try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain()) {
	         for(int k1 = i; k1 < j; ++k1) {
	            for(int l1 = k; l1 < l; ++l1) {
	               for(int i2 = i1; i2 < j1; ++i2) {
	                  blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
	                  IFluidState ifluidstate = this.world.getFluidState(blockpos$pooledmutableblockpos);
	                  if (ifluidstate.isTagged(FluidTags.WATER) && d0 < (double)((float)blockpos$pooledmutableblockpos.getY() + ifluidstate.getHeight())) {
	                     if (!ifluidstate.isSource()) {
	                        EntityBoatBOP.Status entityboat$status = EntityBoatBOP.Status.UNDER_FLOWING_WATER;
	                        return entityboat$status;
	                     }

	                     flag = true;
	                  }
	               }
	            }
	         }
	      }

	      return flag ? EntityBoatBOP.Status.UNDER_WATER : null;
	   }

	   /**
	    * Update the boat's speed, based on momentum.
	    */
	   private void updateMotion() {
	      double d0 = (double)-0.04F;
	      double d1 = this.hasNoGravity() ? 0.0D : (double)-0.04F;
	      double d2 = 0.0D;
	      this.momentum = 0.05F;
	      if (this.previousStatus == EntityBoatBOP.Status.IN_AIR && this.status != EntityBoatBOP.Status.IN_AIR && this.status != EntityBoatBOP.Status.ON_LAND) {
	         this.waterLevel = this.getBoundingBox().minY + (double)this.height;
	         this.setPosition(this.posX, (double)(this.getWaterLevelAbove() - this.height) + 0.101D, this.posZ);
	         this.motionY = 0.0D;
	         this.lastYd = 0.0D;
	         this.status = EntityBoatBOP.Status.IN_WATER;
	      } else {
	         if (this.status == EntityBoatBOP.Status.IN_WATER) {
	            d2 = (this.waterLevel - this.getBoundingBox().minY) / (double)this.height;
	            this.momentum = 0.9F;
	         } else if (this.status == EntityBoatBOP.Status.UNDER_FLOWING_WATER) {
	            d1 = -7.0E-4D;
	            this.momentum = 0.9F;
	         } else if (this.status == EntityBoatBOP.Status.UNDER_WATER) {
	            d2 = (double)0.01F;
	            this.momentum = 0.45F;
	         } else if (this.status == EntityBoatBOP.Status.IN_AIR) {
	            this.momentum = 0.9F;
	         } else if (this.status == EntityBoatBOP.Status.ON_LAND) {
	            this.momentum = this.boatGlide;
	            if (this.getControllingPassenger() instanceof EntityPlayer) {
	               this.boatGlide /= 2.0F;
	            }
	         }

	         this.motionX *= (double)this.momentum;
	         this.motionZ *= (double)this.momentum;
	         this.deltaRotation *= this.momentum;
	         this.motionY += d1;
	         if (d2 > 0.0D) {
	            double d3 = 0.65D;
	            this.motionY += d2 * 0.06153846016296973D;
	            double d4 = 0.75D;
	            this.motionY *= 0.75D;
	         }
	      }

	   }

	   private void controlBoat() {
	      if (this.isBeingRidden()) {
	         float f = 0.0F;
	         if (this.leftInputDown) {
	            this.deltaRotation += -1.0F;
	         }

	         if (this.rightInputDown) {
	            ++this.deltaRotation;
	         }

	         if (this.rightInputDown != this.leftInputDown && !this.forwardInputDown && !this.backInputDown) {
	            f += 0.005F;
	         }

	         this.rotationYaw += this.deltaRotation;
	         if (this.forwardInputDown) {
	            f += 0.04F;
	         }

	         if (this.backInputDown) {
	            f -= 0.005F;
	         }

	         this.motionX += (double)(MathHelper.sin(-this.rotationYaw * ((float)Math.PI / 180F)) * f);
	         this.motionZ += (double)(MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F)) * f);
	         this.setPaddleState(this.rightInputDown && !this.leftInputDown || this.forwardInputDown, this.leftInputDown && !this.rightInputDown || this.forwardInputDown);
	      }
	   }

	   @Override
	   public void updatePassenger(Entity passenger) {
	      if (this.isPassenger(passenger)) {
	         float f = 0.0F;
	         float f1 = (float)((this.removed ? (double)0.01F : this.getMountedYOffset()) + passenger.getYOffset());
	         if (this.getPassengers().size() > 1) {
	            int i = this.getPassengers().indexOf(passenger);
	            if (i == 0) {
	               f = 0.2F;
	            } else {
	               f = -0.6F;
	            }

	            if (passenger instanceof EntityAnimal) {
	               f = (float)((double)f + 0.2D);
	            }
	         }

	         Vec3d vec3d = (new Vec3d((double)f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
	         passenger.setPosition(this.posX + vec3d.x, this.posY + (double)f1, this.posZ + vec3d.z);
	         passenger.rotationYaw += this.deltaRotation;
	         passenger.setRotationYawHead(passenger.getRotationYawHead() + this.deltaRotation);
	         this.applyYawToEntity(passenger);
	         if (passenger instanceof EntityAnimal && this.getPassengers().size() > 1) {
	            int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
	            passenger.setRenderYawOffset(((EntityAnimal)passenger).renderYawOffset + (float)j);
	            passenger.setRotationYawHead(passenger.getRotationYawHead() + (float)j);
	         }

	      }
	   }

	   /**
	    * Applies this boat's yaw to the given entity. Used to update the orientation of its passenger.
	    */
	   @Override
	   protected void applyYawToEntity(Entity entityToUpdate) {
	      entityToUpdate.setRenderYawOffset(this.rotationYaw);
	      float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
	      float f1 = MathHelper.clamp(f, -105.0F, 105.0F);
	      entityToUpdate.prevRotationYaw += f1 - f;
	      entityToUpdate.rotationYaw += f1 - f;
	      entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
	   }

	   /**
	    * Applies this entity's orientation (pitch/yaw) to another entity. Used to update passenger orientation.
	    */
	   @OnlyIn(Dist.CLIENT)
	   public void applyOrientationToEntity(Entity entityToUpdate) {
	      this.applyYawToEntity(entityToUpdate);
	   }

	   /**
	    * Writes the extra NBT data specific to this type of entity. Should <em>not</em> be called from outside this class;
	    * use {@link #writeUnlessPassenger} or {@link #writeWithoutTypeId} instead.
	    */
	   @Override
	   protected void writeAdditional(NBTTagCompound compound) {
	      compound.putString("Type", this.getBOPBoatType().getName());
	   }

	   /**
	    * (abstract) Protected helper method to read subclass entity data from NBT.
	    */
	   @Override
	   protected void readAdditional(NBTTagCompound compound) {
	      if (compound.contains("Type", 8)) {
	         this.setBoatType(EntityBoatBOP.Type.getTypeFromString(compound.getString("Type")));
	      }

	   }

	   @Override
	   public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
	      if (player.isSneaking()) {
	         return false;
	      } else {
	         if (!this.world.isRemote && this.outOfControlTicks < 60.0F) {
	            player.startRiding(this);
	         }

	         return true;
	      }
	   }

	   @Override
	   protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	      this.lastYd = this.motionY;
	      if (!this.isPassenger()) {
	         if (onGroundIn) {
	            if (this.fallDistance > 3.0F) {
	               if (this.status != EntityBoatBOP.Status.ON_LAND) {
	                  this.fallDistance = 0.0F;
	                  return;
	               }

	               this.fall(this.fallDistance, 1.0F);
	               if (!this.world.isRemote && !this.removed) {
	                  this.remove();
	                  if (this.world.getGameRules().getBoolean("doEntityDrops")) {
	                     for(int i = 0; i < 3; ++i) {
	                        this.entityDropItem(this.getBOPBoatType().asPlank());
	                     }

	                     for(int j = 0; j < 2; ++j) {
	                        this.entityDropItem(Items.STICK);
	                     }
	                  }
	               }
	            }

	            this.fallDistance = 0.0F;
	         } else if (!this.world.getFluidState((new BlockPos(this)).down()).isTagged(FluidTags.WATER) && y < 0.0D) {
	            this.fallDistance = (float)((double)this.fallDistance - y);
	         }

	      }
	   }

	   @Override
	   public boolean getPaddleState(int side) {
	      return this.dataManager.<Boolean>get(side == 0 ? field_199704_e : field_199705_f) && this.getControllingPassenger() != null;
	   }

	   /**
	    * Sets the damage taken from the last hit.
	    */
	   @Override
	   public void setDamageTaken(float damageTaken) {
	      this.dataManager.set(DAMAGE_TAKEN, damageTaken);
	   }

	   /**
	    * Gets the damage taken from the last hit.
	    */
	   @Override
	   public float getDamageTaken() {
	      return this.dataManager.get(DAMAGE_TAKEN);
	   }

	   /**
	    * Sets the time to count down from since the last time entity was hit.
	    */
	   @Override
	   public void setTimeSinceHit(int timeSinceHit) {
	      this.dataManager.set(TIME_SINCE_HIT, timeSinceHit);
	   }

	   /**
	    * Gets the time since the last hit.
	    */
	   @Override
	   public int getTimeSinceHit() {
	      return this.dataManager.get(TIME_SINCE_HIT);
	   }

	   private void setRockingTicks(int p_203055_1_) {
	      this.dataManager.set(ROCKING_TICKS, p_203055_1_);
	   }

	   private int getRockingTicks() {
	      return this.dataManager.get(ROCKING_TICKS);
	   }

	   @OnlyIn(Dist.CLIENT)
	   @Override
	   public float func_203056_b(float p_203056_1_) {
	      return this.prevRockingAngle + (this.rockingAngle - this.prevRockingAngle) * p_203056_1_;
	   }

	   /**
	    * Sets the forward direction of the entity.
	    */
	   @Override
	   public void setForwardDirection(int forwardDirection) {
	      this.dataManager.set(FORWARD_DIRECTION, forwardDirection);
	   }

	   /**
	    * Gets the forward direction of the entity.
	    */
	   @Override
	   public int getForwardDirection() {
	      return this.dataManager.get(FORWARD_DIRECTION);
	   }

	   public void setBoatType(EntityBoatBOP.Type type) {
	      this.dataManager.set(BOAT_TYPE, type.ordinal());
	   }

	   public EntityBoatBOP.Type getBOPBoatType() {
	      return EntityBoatBOP.Type.byId(this.dataManager.get(BOAT_TYPE));
	   }

	   @Override
	   protected boolean canFitPassenger(Entity passenger) {
	      return this.getPassengers().size() < 2 && !this.areEyesInFluid(FluidTags.WATER);
	   }

	   /**
	    * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
	    * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
	    */
	   @Nullable
	   @Override
	   public Entity getControllingPassenger() {
	      List<Entity> list = this.getPassengers();
	      return list.isEmpty() ? null : list.get(0);
	   }

	   @OnlyIn(Dist.CLIENT)
	   @Override
	   public void updateInputs(boolean p_184442_1_, boolean p_184442_2_, boolean p_184442_3_, boolean p_184442_4_) {
	      this.leftInputDown = p_184442_1_;
	      this.rightInputDown = p_184442_2_;
	      this.forwardInputDown = p_184442_3_;
	      this.backInputDown = p_184442_4_;
	   }

	   // Forge: Fix MC-119811 by instantly completing lerp on board
	   @Override
	   protected void addPassenger(Entity passenger) {
	      super.addPassenger(passenger);
	      if (this.canPassengerSteer() && this.lerpSteps > 0) {
	         this.lerpSteps = 0;
	         this.posX = this.lerpX;
	         this.posY = this.lerpY;
	         this.posZ = this.lerpZ;
	         this.rotationYaw = (float)this.lerpYaw;
	         this.rotationPitch = (float)this.lerpPitch;
	      }
	   }
	   
	   public static enum Status {
	      IN_WATER,
	      UNDER_WATER,
	      UNDER_FLOWING_WATER,
	      ON_LAND,
	      IN_AIR;
	   }

	   public static enum Type {
	      FIR(BOPBlocks.fir_planks, "fir"),
	      REDWOOD(BOPBlocks.redwood_planks, "redwood"),
	      CHERRY(BOPBlocks.cherry_planks, "cherry"),
	      MAHOGANY(BOPBlocks.mahogany_planks, "mahogany"),
	      JACARANDA(BOPBlocks.jacaranda_planks, "jacaranda"),
	      PALM(BOPBlocks.palm_planks, "palm"),
	      WILLOW(BOPBlocks.willow_planks, "willow"),
	      DEAD(BOPBlocks.dead_planks, "dead"),
	      MAGIC(BOPBlocks.magic_planks, "magic"),
	      UMBRAN(BOPBlocks.umbran_planks, "umbran"),
	      HELLBARK(BOPBlocks.hellbark_planks, "hellbark"),
	      ETHEREAL(BOPBlocks.ethereal_planks, "ethereal");

	      private final String name;
	      private final Block block;

	      private Type(Block p_i48146_3_, String p_i48146_4_) {
	         this.name = p_i48146_4_;
	         this.block = p_i48146_3_;
	      }

	      public String getName() {
	         return this.name;
	      }

	      public Block asPlank() {
	         return this.block;
	      }

	      public String toString() {
	         return this.name;
	      }

	      /**
	       * Get a boat type by it's enum ordinal
	       */
	      public static EntityBoatBOP.Type byId(int id) {
	         EntityBoatBOP.Type[] aentityboat$type = values();
	         if (id < 0 || id >= aentityboat$type.length) {
	            id = 0;
	         }

	         return aentityboat$type[id];
	      }

	      public static EntityBoatBOP.Type getTypeFromString(String nameIn) {
	         EntityBoatBOP.Type[] aentityboat$type = values();

	         for(int i = 0; i < aentityboat$type.length; ++i) {
	            if (aentityboat$type[i].getName().equals(nameIn)) {
	               return aentityboat$type[i];
	            }
	         }

	         return aentityboat$type[0];
	      }
	   }
	}