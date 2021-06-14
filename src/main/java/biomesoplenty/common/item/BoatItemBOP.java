package biomesoplenty.common.item;

import biomesoplenty.common.entity.item.BoatEntityBOP;
import biomesoplenty.common.entity.item.BoatEntityBOP.BoatModel;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class BoatItemBOP extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntityPredicates.NO_SPECTATORS.and(Entity::isPickable);
    private final BoatModel model;

    public BoatItemBOP(BoatModel model) {
        super(new Item.Properties().stacksTo(1).tab(ItemGroupBOP.instance));
        this.model = model;
        DispenserBlock.registerBehavior(this, new DispenserBoatBehaviorBOP(model));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        RayTraceResult result = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.ANY);
        if (result.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(heldItem);
        } else {
            Vector3d vector3d = player.getViewVector(1f);
            List<Entity> entities = world.getEntities(player, player.getBoundingBox().expandTowards(vector3d.scale(5d)).inflate(1d), ENTITY_PREDICATE);
            if (!entities.isEmpty()) {
                Vector3d vector3d1 = player.getEyePosition(1f);
                for (Entity entity : entities) {
                    AxisAlignedBB bounds = entity.getBoundingBox().inflate((double) entity.getPickRadius());
                    if (bounds.contains(vector3d1)) {
                        return ActionResult.pass(heldItem);
                    }
                }
            }
            if (result.getType() == RayTraceResult.Type.BLOCK) {
                BoatEntityBOP boat = new BoatEntityBOP(world, result.getLocation().x, result.getLocation().y, result.getLocation().z).withModel(this.model);
                boat.yRot = player.yRot;
                if (!world.noCollision(boat, boat.getBoundingBox().inflate(-0.1d))) {
                    return ActionResult.fail(heldItem);
                } else {
                    if (!world.isClientSide()) {
                        world.addFreshEntity(boat);
                        if (!player.abilities.instabuild) {
                            heldItem.shrink(1);
                        }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                    return ActionResult.sidedSuccess(heldItem, world.isClientSide());
                }
            } else {
                return ActionResult.pass(heldItem);
            }
        }
    }
}
