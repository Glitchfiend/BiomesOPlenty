package biomesoplenty.integration.tinkersconstruct;

import mods.tinker.tconstruct.library.TConstructRegistry;
import mods.tinker.tconstruct.library.client.TConstructClientRegistry;
import mods.tinker.tconstruct.library.crafting.PatternBuilder;
import mods.tinker.tconstruct.library.crafting.ToolBuilder;
import mods.tinker.tconstruct.library.tools.ToolCore;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPConfiguration;

public class TCItems 
{
    public static Item binding;
    public static Item toughBinding;
    public static Item toughRod;
    public static Item heavyPlate;
    
    public static Item toolRod;
    public static Item toolShard;

    public static Item pickaxeHead;
    public static Item shovelHead;
    public static Item hatchetHead;
    public static Item frypanHead;
    public static Item signHead;
    public static Item chiselHead;
    public static Item scytheBlade;
    public static Item broadAxeHead;
    public static Item excavatorHead;
    public static Item hammerHead;

    public static Item swordBlade;
    public static Item largeSwordBlade;
    public static Item knifeBlade;

    public static Item wideGuard;
    public static Item handGuard;
    public static Item crossbar;
	
	public static void init()
	{
		initializeItems();
		addCrafting();
	}
	
	private static void initializeItems()
	{
        toolRod = new BOPToolPart(BOPConfiguration.toolRodID, "ToolRod", "_rod").setUnlocalizedName("bop.tc.ToolRod");
        toolShard = new BOPToolShard(BOPConfiguration.toolShardID, "ToolShard", "_chunk").setUnlocalizedName("bop.tc.ToolShard");
		
        pickaxeHead = new BOPToolPart(BOPConfiguration.pickaxeHeadID, "PickaxeHead", "_pickaxe_head").setUnlocalizedName("bop.tc.PickaxeHead");
        shovelHead = new BOPToolPart(BOPConfiguration.shovelHeadID, "ShovelHead", "_shovel_head").setUnlocalizedName("bop.tc.ShovelHead");
        hatchetHead = new BOPToolPart(BOPConfiguration.hatchetHeadID, "AxeHead", "_axe_head").setUnlocalizedName("bop.tc.AxeHead");
        binding = new BOPToolPart(BOPConfiguration.bindingID, "Binding", "_binding").setUnlocalizedName("bop.tc.Binding");
        toughBinding = new BOPToolPart(BOPConfiguration.toughBindingID, "ThickBinding", "_toughbind").setUnlocalizedName("bop.tc.ThickBinding");
        toughRod = new BOPToolPart(BOPConfiguration.toughRodID, "ThickRod", "_toughrod").setUnlocalizedName("bop.tc.ThickRod");
        heavyPlate = new BOPToolPart(BOPConfiguration.heavyPlateID, "LargePlate", "_largeplate").setUnlocalizedName("bop.tc.LargePlate");

        swordBlade = new BOPToolPart(BOPConfiguration.swordBladeID, "SwordBlade", "_sword_blade").setUnlocalizedName("bop.tc.SwordBlade");
        wideGuard = new BOPToolPart(BOPConfiguration.wideGuardID, "LargeGuard", "_large_guard").setUnlocalizedName("bop.tc.LargeGuard");
        handGuard = new BOPToolPart(BOPConfiguration.handGuardID, "MediumGuard", "_medium_guard").setUnlocalizedName("bop.tc.MediumGuard");
        crossbar = new BOPToolPart(BOPConfiguration.crossbarID, "Crossbar", "_crossbar").setUnlocalizedName("bop.tc.Crossbar");
        knifeBlade = new BOPToolPart(BOPConfiguration.knifeBladeID, "KnifeBlade", "_knife_blade").setUnlocalizedName("bop.tc.KnifeBlade");

        frypanHead = new BOPToolPart(BOPConfiguration.frypanHeadID, "FrypanHead", "_frypan_head").setUnlocalizedName("bop.tc.FrypanHead");
        signHead = new BOPToolPart(BOPConfiguration.signHeadID, "SignHead", "_battlesign_head").setUnlocalizedName("bop.tc.SignHead");
        chiselHead = new BOPToolPart(BOPConfiguration.chiselHeadID, "ChiselHead", "_chisel_head").setUnlocalizedName("bop.tc.ChiselHead");

        scytheBlade = new BOPToolPart(BOPConfiguration.scytheBladeID, "ScytheBlade", "_scythe_head").setUnlocalizedName("bop.tc.ScytheBlade");
        broadAxeHead = new BOPToolPart(BOPConfiguration.broadAxeHeadID, "LumberHead", "_lumberaxe_head").setUnlocalizedName("bop.tc.LumberHead");
        excavatorHead = new BOPToolPart(BOPConfiguration.excavatorHeadID, "ExcavatorHead", "_excavator_head").setUnlocalizedName("bop.tc.ExcavatorHead");
        largeSwordBlade = new BOPToolPart(BOPConfiguration.largeSwordBladeID, "LargeSwordBlade", "_large_sword_blade").setUnlocalizedName("bop.tc.LargeSwordBlade");
        hammerHead = new BOPToolPart(BOPConfiguration.hammerHeadID, "HammerHead", "_hammer_head").setUnlocalizedName("bop.tc.HammerHead");
	}
	
	private static void addCrafting()
	{
        PatternBuilder pb = PatternBuilder.instance;
        pb.registerFullMaterial(new ItemStack(Items.miscItems.get(), 1, 2), 2, "Amethyst", new ItemStack(toolShard, 1, 0), new ItemStack(toolRod, 1, 0), 150);
		
        Item[] patternOutputs = new Item[] { toolRod, pickaxeHead, shovelHead, hatchetHead, swordBlade, wideGuard, handGuard, crossbar, binding, frypanHead, signHead, knifeBlade, chiselHead, toughRod, toughBinding, heavyPlate, broadAxeHead, scytheBlade, excavatorHead, largeSwordBlade, hammerHead };
        String[] partTypes = { "amethyst" };
        
		for (int mat = 0; mat < 1; mat++)
		{
			for (int meta = 0; meta < patternOutputs.length; meta++)
			{
				TConstructRegistry.addPartMapping(TConstructRegistry.getItem("woodPattern").itemID, meta + 1, 150, new ItemStack(patternOutputs[meta], 1, mat));
			}
		}
		
        for (int partIter = 0; partIter < partTypes.length; partIter++)
        {
            TConstructClientRegistry.addMaterialRenderMapping(150 + partIter, "BiomesOPlenty", partTypes[partIter], true);
        }
		
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("pickaxe"), pickaxeHead, toolRod, binding);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("broadsword"), swordBlade, toolRod, wideGuard);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("hatchet"), hatchetHead, toolRod);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("shovel"), shovelHead, toolRod);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("longsword"), swordBlade, toolRod, handGuard);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("rapier"), swordBlade, toolRod, crossbar);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("frypan"), frypanHead, toolRod);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("battlesign"), signHead, toolRod);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("mattock"), hatchetHead, toolRod, shovelHead);
        //ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("dagger"), knifeBlade, crossbar);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("chisel"), chiselHead, toolRod);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("scythe"), scytheBlade, toughRod, toughBinding, toughRod);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("lumberaxe"), broadAxeHead, toughRod, heavyPlate, toughBinding);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("cleaver"), largeSwordBlade, toughRod, heavyPlate, toughRod);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("excavator"), excavatorHead, toughRod, heavyPlate, toughBinding);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("hammer"), hammerHead, toughRod, heavyPlate, heavyPlate);
        ToolBuilder.addNormalToolRecipe((ToolCore)TConstructRegistry.getItem("battleaxe"), broadAxeHead, toughRod, broadAxeHead, toughBinding);
	}
}
