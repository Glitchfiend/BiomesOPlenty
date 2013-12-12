package thaumcraft.api.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ResearchItem 
{
	/**
	 * A short string used as a key for this research. Must be unique
	 */
	public final String key;
	
	/**
	 * A short string used as a reference to the research category to which this must be added.
	 */
	public final String category;

	/**
	 * The aspect tags and their values required to complete this research
	 */
	public final AspectList tags;
	
    /**
     * This links to any research that needs to be completed before this research can be discovered or learnt.
     */
    public String[] parents = null;
    
    /**
     * Like parent above, but a line will not be displayed in the thaumonomicon linking them. Just used to prevent clutter.
     */
    public String[] parentsHidden = null;
    /**
     * any research linked to this that will be unlocked automatically when this research is complete
     */
    public String[] siblings = null;
	
    /**
     * the horizontal position of the research icon
     */
    public final int displayColumn;

    /**
     * the vertical position of the research icon
     */
    public final int displayRow;
    
    /**
     * the icon to be used for this research 
     */
    public final ItemStack icon_item;
    
    /**
     * the icon to be used for this research 
     */
    public final ResourceLocation icon_resource;
    
    /**
     * How large the research grid is. Valid values are 1 to 5.
     */
    private int complexity;

    /**
     * Special research has a spiky border. Used for important research milestones.
     */
    private boolean isSpecial;
    
    /**
     * This indicates if the research should use a circular icon border. Usually used for "passive" research 
     * that doesn't have recipes and grants passive effects, or that unlock automatically.
     */
    private boolean isRound;
    
    /**
     * Stub research cannot be discovered by normal means, but can be unlocked via the sibling system.
     */
    private boolean isStub;
    
    /**
     * This indicated that the research is completely hidden and cannot be discovered by any 
     * player-controlled means. The recipes will never show up in the thaumonomicon.
     * Usually used to unlock "hidden" recipes via sibling unlocking, like 
     * the various cap and rod combos for wands.
     */
    private boolean isVirtual;    
    
    /**
     * Hidden research does not display in the thaumonomicon until discovered
     */
    private boolean isHidden;
    
    /**
     * Concealed research does not display in the thaumonomicon until parent researches are discovered.
     */
    private boolean isConcealed;
    
    /**
     * Lost research can only be discovered via knowledge fragments
     */
    private boolean isLost;
    
    /**
     * These research items will automatically unlock for all players on game start
     */
    private boolean isAutoUnlock;

	private ResearchPage[] pages = null;
	
	public ResearchItem(String par1, String par2)
    {
    	this.key = par1;
    	this.category = par2;
    	this.tags = new AspectList();    	
        this.icon_resource = null;
        this.icon_item = null;
        this.displayColumn = 0;
        this.displayRow = 0;
        this.setVirtual();
        
    }
    
    public ResearchItem(String par1, String par2, AspectList tags, int par3, int par4, int par5, ResourceLocation icon)
    {
    	this.key = par1;
    	this.category = par2;
    	this.tags = tags;    	
        this.icon_resource = icon;
        this.icon_item = null;
        this.displayColumn = par3;
        this.displayRow = par4;
        this.complexity = par5;
        if (complexity < 1) this.complexity = 1;
        if (complexity > 5) this.complexity = 5;
    }
    
    public ResearchItem(String par1, String par2, AspectList tags, int par3, int par4, int par5, ItemStack icon)
    {
    	this.key = par1;
    	this.category = par2;
    	this.tags = tags;    	
        this.icon_item = icon;
        this.icon_resource = null;
        this.displayColumn = par3;
        this.displayRow = par4;
        this.complexity = par5;
        if (complexity <0) this.complexity = 0;
        if (complexity > 5) this.complexity = 5;
    }

    public ResearchItem setSpecial()
    {
        this.isSpecial = true;
        return this;
    }
    
    public ResearchItem setStub()
    {
        this.isStub = true;
        return this;
    }
    
    
    public ResearchItem setHidden()
    {
        this.isHidden = true;
        return this;
    }
    
    public ResearchItem setConcealed()
    {
        this.isConcealed = true;
        return this;
    }
    
    public ResearchItem setLost()
    {
        this.isLost = true;
        return this;
    }
    
    public ResearchItem setVirtual()
    {
        this.isVirtual = true;
        return this;
    }
    
    public ResearchItem setParents(String... par)
    {
        this.parents = par;
        return this;
    }
    
    

	public ResearchItem setParentsHidden(String... par)
    {
        this.parentsHidden = par;
        return this;
    }
    
    public ResearchItem setSiblings(String... sib)
    {
        this.siblings = sib;
        return this;
    }
    
    public ResearchItem setPages(ResearchPage... par)
    {
        this.pages = par;
        return this;
    }
    
    public ResearchPage[] getPages() {
		return pages;
	}

    public ResearchItem registerResearchItem()
    {
        ResearchCategories.addResearch(this);
        return this;
    }

    @SideOnly(Side.CLIENT)
    public String getName()
    {
    	return StatCollector.translateToLocal("tc.research_name."+key);
    }
    
    @SideOnly(Side.CLIENT)
    public String getText()
    {
    	return StatCollector.translateToLocal("tc.research_text."+key);
    }

    @SideOnly(Side.CLIENT)
    public boolean isSpecial()
    {
        return this.isSpecial;
    }
    
    public boolean isStub()
    {
        return this.isStub;
    }
        
    public boolean isHidden()
    {
        return this.isHidden;
    }
    
    public boolean isConcealed()
    {
        return this.isConcealed;
    }
    
    public boolean isLost()
    {
        return this.isLost;
    }
    
    public boolean isVirtual()
    {
        return this.isVirtual;
    }
    
    public boolean isAutoUnlock() {
		return isAutoUnlock;
	}
	
	public ResearchItem setAutoUnlock()
    {
        this.isAutoUnlock = true;
        return this;
    }
	
	public boolean isRound() {
		return isRound;
	}

	public ResearchItem setRound() {
		this.isRound = true;
		return this;
	}
	
	

	public int getComplexity() {
		return complexity;
	}

	public ResearchItem setComplexity(int complexity) {
		this.complexity = complexity;
		return this;
	}

	/**
	 * @return the aspect aspects ordinal with the highest value. Used to determine scroll color and similar things
	 */
	public Aspect getResearchPrimaryTag() {
		Aspect aspect=null;
		int highest=0;
		if (tags!=null)
		for (Aspect tag:tags.getAspects()) {
			if (tags.getAmount(tag)>highest) {
				aspect=tag;
				highest=tags.getAmount(tag);
			};
		}
		return aspect;
	}
	
}
