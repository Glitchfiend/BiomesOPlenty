package powercrystals.minefactoryreloaded.api;

import net.minecraft.item.ItemStack;

import java.lang.reflect.Method;

/**
 * @author PowerCrystals
 *         <p/>
 *         Class used to register plants and other farming-related things with MFR. Will do nothing if MFR does not exist, but your mod should be set to load
 *         after MFR or things may not work properly.
 *         <p/>
 *         To avoid breaking the API, additional FarmingRegistry##s will appear on major MFR versions that contain API additions. On a Minecraft version change,
 *         these will be rolled back into this class.
 */
public class FactoryRegistry27 {
    /**
     * Registers a grindable entity with the Grinder using the new grinder interface. This method will be renamed to the standard "registerGrindable"
     * on MC 1.6.
     *
     * @param grindable The entity to grind.
     */
    public static void registerGrindable(IFactoryGrindable2 grindable) {
        try {
            Class<?> registry = Class.forName("powercrystals.minefactoryreloaded.MFRRegistry");
            if (registry != null) {
                Method reg = registry.getMethod("registerGrindable", IFactoryGrindable2.class);
                reg.invoke(registry, grindable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Registers a grindable entity with the Grinder using the new grinder interface. This method will be renamed to the standard "registerGrindable"
     * on MC 1.6.
     *
     * @param grindable The entity to grind.
     */
    public static void registerGrinderBlacklist(Class<?>... ungrindables) {
        try {
            Class<?> registry = Class.forName("powercrystals.minefactoryreloaded.MFRRegistry");
            if (registry != null) {
                Method reg = registry.getMethod("registerGrinderBlacklist", Class[].class);
                reg.invoke(registry, (Object[]) ungrindables);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Registers a preferred ore with the laser drill. Focuses with the specified color will make the specified ore more likely.
     * Used by MFR itself for vanilla: Black (Coal), Light Blue (Diamond), Lime (Emerald), Yellow (Gold), Brown (Iron), Blue (Lapis),
     * Red (Redstone), and White (nether quartz).
     * <p/>
     * This will replace setLaserPreferredOre on MC 1.6.
     *
     * @param color The color that the preferred ore is being set for. White is 0.
     * @param ore   The ore that will be preferred by the drill when a focus with the specified color is present.
     */
    public static void addLaserPreferredOre(int color, ItemStack ore) {
        try {
            Class<?> registry = Class.forName("powercrystals.minefactoryreloaded.MFRRegistry");
            if (registry != null) {
                Method reg = registry.getMethod("addLaserPreferredOre", int.class, ItemStack.class);
                reg.invoke(registry, color, ore);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
