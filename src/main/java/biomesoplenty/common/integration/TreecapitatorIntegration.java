package biomesoplenty.common.integration;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TreecapitatorIntegration
{
    public static void init()
    {
        String logs1 = BOPBlockHelper.getUniqueName(BOPBlockHelper.get("logs1"));
        String logs2 = BOPBlockHelper.getUniqueName(BOPBlockHelper.get("logs2"));
        String logs3 = BOPBlockHelper.getUniqueName(BOPBlockHelper.get("logs3"));
        String logs4 = BOPBlockHelper.getUniqueName(BOPBlockHelper.get("logs4"));
        String leavesColorized1 = BOPBlockHelper.getUniqueName(BOPBlockHelper.get("colorizedLeaves1"));
        String leavesColorized2 = BOPBlockHelper.getUniqueName(BOPBlockHelper.get("colorizedLeaves2"));
        String leaves1 = BOPBlockHelper.getUniqueName(BOPBlockHelper.get("leaves1"));
        String leaves2 = BOPBlockHelper.getUniqueName(BOPBlockHelper.get("leaves2"));
        String leaves3 = BOPBlockHelper.getUniqueName(BOPBlockHelper.get("leaves3"));
        String leaves4 = BOPBlockHelper.getUniqueName(BOPBlockHelper.get("leaves4"));
        
        NBTTagCompound tpModCfg = new NBTTagCompound();
        tpModCfg.setString("modID", "BiomesOPlenty");
        tpModCfg.setString("axeIDList", BOPItemHelper.getUniqueName(BOPItemHelper.get("axeAmethyst")) + "; " +
                BOPItemHelper.getUniqueName(BOPItemHelper.get("axeMud")));
        
        NBTTagList treeList = new NBTTagList();
        
        /*
        * NOTE: the vanilla trees (any tree that contains a vanilla log) are the only ones where treeName must be one of these values:
        * vanilla_oak, vanilla_spruce, vanilla_birch, vanilla_jungle.
        */
        // Vanilla Oak additions
        NBTTagCompound tree = new NBTTagCompound();
        tree.setString("treeName", "vanilla_oak");
        tree.setString("logs", "");
        tree.setString("leaves", String.format("%s,0; %s,3; %s; %s,0; %s,0; %s,2; 18,2; 18,10",
                leaves2, leaves2, BOPBlockHelper.getUniqueName(BOPBlockHelper.get("appleLeaves")),
                BOPBlockHelper.getUniqueName(BOPBlockHelper.get("persimmonLeaves")), leaves2, leaves3));
        
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        
        // Vanilla Birch additions
        tree = new NBTTagCompound();
        tree.setString("treeName", "vanilla_birch");
        tree.setString("logs", "");
        tree.setString("leaves", String.format("%s,0", leaves1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        
        // Vanilla Jungle additions
        tree = new NBTTagCompound();
        tree.setString("treeName", "vanilla_jungle");
        tree.setString("logs", "");
        tree.setString("leaves", "");
        tree.setInteger("maxLeafIDDist", 3);
        treeList.appendTag(tree);
        
        /*
        * logs1 trees
        */
        // BoP sacred oak
        tree = new NBTTagCompound();
        tree.setString("treeName", "sacredoak");
        tree.setString("logs", String.format("%s,0; %s,4; %s,8", logs1, logs1, logs1));
        tree.setString("leaves", String.format("%s,0; %s,8", leavesColorized1, leavesColorized1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP cherry
        tree = new NBTTagCompound();
        tree.setString("treeName", "cherry");
        tree.setString("logs", String.format("%s,1; %s,5; %s,9", logs1, logs1, logs1));
        tree.setString("leaves", String.format("%s,1; %s,9; %s,3; %s,11", leaves3, leaves3, leaves3, leaves3));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP darkwood
        tree = new NBTTagCompound();
        tree.setString("treeName", "darkwood");
        tree.setString("logs", String.format("%s,2; %s,6; %s,10", logs1, logs1, logs1));
        tree.setString("leaves", String.format("%s,3; %s,11", leaves1, leaves1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP fir
        tree = new NBTTagCompound();
        tree.setString("treeName", "fir");
        tree.setString("logs", String.format("%s,3; %s,7; %s,11", logs1, logs1, logs1));
        tree.setString("leaves", String.format("%s,2; %s,10", leaves2, leaves2));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        
        /*
        * logs2 trees
        */
        // BoP holy
        tree = new NBTTagCompound();
        tree.setString("treeName", "holy");
        tree.setString("logs", String.format("%s,0; %s,4; %s,8", logs2, logs2, logs2));
        tree.setString("leaves", String.format("%s,2; %s,10", leaves2, leaves2));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP magic
        tree = new NBTTagCompound();
        tree.setString("treeName", "magic");
        tree.setString("logs", String.format("%s,1; %s,5; %s,9", logs2, logs2, logs2));
        tree.setString("leaves", String.format("%s,2; %s,10", leaves1, leaves1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP mangrove
        tree = new NBTTagCompound();
        tree.setString("treeName", "mangrove");
        tree.setString("logs", String.format("%s,2; %s,6; %s,10", logs2, logs2, logs2));
        tree.setString("leaves", String.format("%s,1; %s,9", leavesColorized1, leavesColorized1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP palm
        tree = new NBTTagCompound();
        tree.setString("treeName", "palm");
        tree.setString("logs", String.format("%s,3; %s,7; %s,11", logs2, logs2, logs2));
        tree.setString("leaves", String.format("%s,2; %s,10", leavesColorized1, leavesColorized1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        
        /*
        * logs3 trees
        */
        // BoP redwood
        tree = new NBTTagCompound();
        tree.setString("treeName", "redwood");
        tree.setString("logs", String.format("%s,0; %s,4; %s,8", logs3, logs3, logs3));
        tree.setString("leaves", String.format("%s,3; %s,11", leavesColorized1, leavesColorized1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP willow
        tree = new NBTTagCompound();
        tree.setString("treeName", "willow");
        tree.setString("logs", String.format("%s,1; %s,5; %s,9", logs3, logs3, logs3));
        tree.setString("leaves", String.format("%s,0; %s,8", leavesColorized2, leavesColorized2));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP dead
        tree = new NBTTagCompound();
        tree.setString("treeName", "dead");
        tree.setString("logs", String.format("%s,2; %s,6; %s,10", logs3, logs3, logs3));
        tree.setString("leaves", "");
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP big_flower
        tree = new NBTTagCompound();
        tree.setString("treeName", "big_flower");
        tree.setString("logs", String.format("%s,3; %s,7; %s,11", logs3, logs3, logs3));
        tree.setString("leaves", "" + BOPBlockHelper.getUniqueName(BOPBlockHelper.get("petals")));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        
        /*
        * logs4 trees
        */
        // BoP pine
        tree = new NBTTagCompound();
        tree.setString("treeName", "pine");
        tree.setString("logs", String.format("%s,0; %s,4; %s,8", logs4, logs4, logs4));
        tree.setString("leaves", String.format("%s,1; %s,9", leavesColorized2, leavesColorized2));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP hellbark
        tree = new NBTTagCompound();
        tree.setString("treeName", "hellbark");
        tree.setString("logs", String.format("%s,1; %s,5; %s,9", logs4, logs4, logs4));
        tree.setString("leaves", String.format("%s,0; %s,8", leaves4, leaves4));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP jacaranda
        tree = new NBTTagCompound();
        tree.setString("treeName", "jacaranda");
        tree.setString("logs", String.format("%s,2; %s,6; %s,10", logs4, logs4, logs4));
        tree.setString("leaves", String.format("%s,1; %s,9", leaves4, leaves4));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP mahogany
        tree = new NBTTagCompound();
        tree.setString("treeName", "mahogany");
        tree.setString("logs", String.format("%s,3; %s,7; %s,11", logs4, logs4, logs4));
        tree.setString("leaves", String.format("%s,2; %s,10", leavesColorized2, leavesColorized2));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        
        tpModCfg.setTag("trees", treeList);
        
        // bspkrs: I changed the mod ID, so just send messages to both for now.
        FMLInterModComms.sendMessage("TreeCapitator", "ThirdPartyModConfig", tpModCfg);
        FMLInterModComms.sendMessage("Treecapitator", "ThirdPartyModConfig", tpModCfg);
    }
}
