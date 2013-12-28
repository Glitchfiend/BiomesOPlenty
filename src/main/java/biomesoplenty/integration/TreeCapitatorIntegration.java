package biomesoplenty.integration;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import cpw.mods.fml.common.event.FMLInterModComms;

public class TreeCapitatorIntegration
{
    public static void init()
    {
        int logs1 = Blocks.logs1.get().blockID;
        int logs2 = Blocks.logs2.get().blockID;
        int logs3 = Blocks.logs3.get().blockID;
        int logs4 = Blocks.logs4.get().blockID;
        int leavesColorized1 = Blocks.leavesColorized1.get().blockID;
        int leavesColorized2 = Blocks.leavesColorized2.get().blockID;
        int leaves1 = Blocks.leaves1.get().blockID;
        int leaves2 = Blocks.leaves2.get().blockID;
        int leaves3 = Blocks.leaves3.get().blockID;
        int leaves4 = Blocks.leaves4.get().blockID;
        
        NBTTagCompound tpModCfg = new NBTTagCompound();
        tpModCfg.setString("modID", "BiomesOPlenty");
        tpModCfg.setString("axeIDList", Items.axeAmethyst.get().itemID + "; " + Items.axeMud.get().itemID);
        
        NBTTagList treeList = new NBTTagList();
        
        /*
         * NOTE: the vanilla trees (any tree that contains a vanilla log) are the only ones where treeName must be one of these values:
         * vanilla_oak, vanilla_spruce, vanilla_birch, vanilla_jungle.
         */
        // Vanilla Oak additions
        NBTTagCompound tree = new NBTTagCompound();
        tree.setString("treeName", "vanilla_oak");
        tree.setString("logs", "");
        tree.setString("leaves", String.format("%d,0; %d,3; %d; %d,0; %d,0; %d,2; 18,1; 18,9",leaves2, leaves2, Blocks.leavesFruit.get().blockID, Blocks.leavesFruit2.get().blockID, leaves2, leaves3));

        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        
        // Vanilla Birch additions
        tree = new NBTTagCompound();
        tree.setString("treeName", "vanilla_birch");
        tree.setString("logs", "");
        tree.setString("leaves", String.format("%d,0", leaves1));
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
        // BoP acacia
        tree = new NBTTagCompound();
        tree.setString("treeName", "acacia");
        tree.setString("logs", String.format("%d,0; %d,4; %d,8", logs1, logs1, logs1));
        tree.setString("leaves", String.format("%d,0; %d,8", leavesColorized1, leavesColorized1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP cherry
        tree = new NBTTagCompound();
        tree.setString("treeName", "cherry");
        tree.setString("logs", String.format("%d,1; %d,5; %d,9", logs1, logs1, logs1));
        tree.setString("leaves", String.format("%d,1; %d,9; %d,3; %d,11", leaves3, leaves3, leaves3, leaves3));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP darkwood
        tree = new NBTTagCompound();
        tree.setString("treeName", "darkwood");
        tree.setString("logs", String.format("%d,2; %d,6; %d,10", logs1, logs1, logs1));
        tree.setString("leaves", String.format("%d,3; %d,11", leaves1, leaves1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP fir
        tree = new NBTTagCompound();
        tree.setString("treeName", "fir");
        tree.setString("logs", String.format("%d,3; %d,7; %d,11", logs1, logs1, logs1));
        tree.setString("leaves", String.format("%d,1; %d,9", leaves2, leaves2));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        
        /*
         * logs2 trees
         */
        // BoP holy
        tree = new NBTTagCompound();
        tree.setString("treeName", "holy");
        tree.setString("logs", String.format("%d,0; %d,4; %d,8", logs2, logs2, logs2));
        tree.setString("leaves", String.format("%d,2; %d,10", leaves2, leaves2));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP magic
        tree = new NBTTagCompound();
        tree.setString("treeName", "magic");
        tree.setString("logs", String.format("%d,1; %d,5; %d,9", logs2, logs2, logs2));
        tree.setString("leaves", String.format("%d,2; %d,10", leaves1, leaves1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP mangrove
        tree = new NBTTagCompound();
        tree.setString("treeName", "mangrove");
        tree.setString("logs", String.format("%d,2; %d,6; %d,10", logs2, logs2, logs2));
        tree.setString("leaves", String.format("%d,1; %d,9", leavesColorized1, leavesColorized1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP palm
        tree = new NBTTagCompound();
        tree.setString("treeName", "palm");
        tree.setString("logs", String.format("%d,3; %d,7; %d,11", logs2, logs2, logs2));
        tree.setString("leaves", String.format("%d,2; %d,10", leavesColorized1, leavesColorized1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        
        /*
         * logs3 trees
         */
        // BoP redwood
        tree = new NBTTagCompound();
        tree.setString("treeName", "redwood");
        tree.setString("logs", String.format("%d,0; %d,4; %d,8", logs3, logs3, logs3));
        tree.setString("leaves", String.format("%d,3; %d,11", leavesColorized1, leavesColorized1));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP willow
        tree = new NBTTagCompound();
        tree.setString("treeName", "willow");
        tree.setString("logs", String.format("%d,1; %d,5; %d,9", logs3, logs3, logs3));
        tree.setString("leaves", String.format("%d,0; %d,8", leavesColorized2, leavesColorized2));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP dead
        tree = new NBTTagCompound();
        tree.setString("treeName", "dead");
        tree.setString("logs", String.format("%d,2; %d,6; %d,10", logs3, logs3, logs3));
        tree.setString("leaves", "");
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP big_flower
        tree = new NBTTagCompound();
        tree.setString("treeName", "big_flower");
        tree.setString("logs", String.format("%d,3; %d,7; %d,11", logs3, logs3, logs3));
        tree.setString("leaves", "" + Blocks.petals.get().blockID);
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        
        /*
         * logs4 trees
         */
        // BoP pine
        tree = new NBTTagCompound();
        tree.setString("treeName", "pine");
        tree.setString("logs", String.format("%d,0; %d,4; %d,8", logs4, logs4, logs4));
        tree.setString("leaves", String.format("%d,1; %d,9", leavesColorized2, leavesColorized2));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP hellbark
        tree = new NBTTagCompound();
        tree.setString("treeName", "hellbark");
        tree.setString("logs", String.format("%d,1; %d,5; %d,9", logs4, logs4, logs4));
        tree.setString("leaves", String.format("%d,0; %d,8", leaves4, leaves4));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        // BoP jacaranda
        tree = new NBTTagCompound();
        tree.setString("treeName", "jacaranda");
        tree.setString("logs", String.format("%d,2; %d,6; %d,10", logs4, logs4, logs4));
        tree.setString("leaves", String.format("%d,1; %d,9", leaves4, leaves4));
        tree.setBoolean("requireLeafDecayCheck", false);
        treeList.appendTag(tree);
        
        tpModCfg.setTag("trees", treeList);
        
        FMLInterModComms.sendMessage("TreeCapitator", "ThirdPartyModConfig", tpModCfg);
    }
}
