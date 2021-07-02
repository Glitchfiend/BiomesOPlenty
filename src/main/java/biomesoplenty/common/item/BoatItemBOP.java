package biomesoplenty.common.item;

import biomesoplenty.common.entity.item.BoatEntityBOP;
import biomesoplenty.common.entity.item.BoatEntityBOP.BoatModel;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Predicate;

public class BoatItemBOP extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final BoatModel model;

    public BoatItemBOP(BoatModel model) {
        super(new Item.Properties().stacksTo(1).tab(ItemGroupBOP.instance));
        this.model = model;
        DispenserBlock.registerBehavior(this, new DispenserBoatBehaviorBOP(model));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        HitResult result = getPlayerPOVHitResult(world, player, ClipContext.Fluid.ANY);
        if (result.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(heldItem);
        } else {
            Vec3 vector3d = player.getViewVector(1f);
            List<Entity> entities = world.getEntities(player, player.getBoundingBox().expandTowards(vector3d.scale(5d)).inflate(1d), ENTITY_PREDICATE);
            if (!entities.isEmpty()) {
                Vec3 vector3d1 = player.getEyePosition(1f);
                for (Entity entity : entities) {
                    AABB bounds = entity.getBoundingBox().inflate((double) entity.getPickRadius());
                    if (bounds.contains(vector3d1)) {
                        return InteractionResultHolder.pass(heldItem);
                    }
                }
            }
            if (result.getType() == HitResult.Type.BLOCK) {
                BoatEntityBOP boat = new BoatEntityBOP(world, result.getLocation().x, result.getLocation().y, result.getLocation().z).withModel(this.model);
                boat.yRot = player.yRot;
                if (!world.noCollision(boat, boat.getBoundingBox().inflate(-0.1d))) {
                    return InteractionResultHolder.fail(heldItem);
                } else {
                    if (!world.isClientSide()) {
                        world.addFreshEntity(boat);
                        if (!player.abilities.instabuild) {
                            heldItem.shrink(1);
                        }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(heldItem, world.isClientSide());
                }
            } else {
                return InteractionResultHolder.pass(heldItem);
            }
        }
    }
}
