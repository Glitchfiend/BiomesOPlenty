package thaumcraft.api.research;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import thaumcraft.api.ObjectTags;
import thaumcraft.api.ThaumcraftApi;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ResearchItem 
{
	/**
	 * A short string used as a key for this research. Must be unique
	 */
	public final String key;
	
	/**
	 * The name of the research shown in the thaumonomicon 
	 */
	public String name;
	
	/**
	 * The blurb text shown below the research name in the thaumonomicon
	 */
	public String popupText;
	
	/**
	 * A longer description of the research. This is the text shown in the handheld research scroll and the research table. 
	 */
	public String longText;
	
	/**
	 * The aspect tags and their values required to complete this research
	 */
	public final ObjectTags tags;
	
    /**
     * This links to any research that needs to be completed before this research can be discovered or learnt.
     */
    public ResearchItem[] parents = null;
    
    /**
     * Like parent above, but a line will not be displayed in the thaumonomicon linking them. Just used to prevent clutter.
     */
    public ResearchItem[] parentsHidden = null;
    /**
     * any research linked to this that will be unlocked automatically when this research is complete
     */
    public ResearchItem[] siblings = null;
	
    /**
     * the horizontal position of the research icon
     */
    public final int displayColumn;

    /**
     * the vertical position of the research icon
     */
    public final int displayRow;

    /**
     * the itemstack of an item or block that will be used as the icon for this research
     */
    public final ItemStack itemStack;
    
    /**
     * the index within the research.png file used for this research if it does not use an item icon 
     */
    public final int iconIndex;

    /**
     * Special research has a spiky border. Used for important research milestones.
     */
    private boolean isSpecial;
    /**
     * This indicates if the research should use a circular icon border. Usually used for research that doesn't 
     * have recipes or that unlocks automatically via the sibling system
     */
    private boolean isStub;
    /**
     * Indicates research that cannot be gained by normal means (either via normal or lost research), 
     * but still uses a normal icon. Works much like isStub but is handy for mods that wish to add research 
     * through their own means and keep a normal icon.
     */
    private boolean isAlternate;
    /**
     * Hidden research does not display in the thaumonomicon until discovered
     */
    private boolean isHidden;
    /**
     * Lost research can only be discovered via knowledge fragments
     */
    private boolean isLost;
    /**
     * These research items will automatically unlock for all players on game start
     */
    private boolean isAutoUnlock;
    
    

	public ResearchItem(String par1, ObjectTags tags, int par3, int par4, int icon)
    {
        this(par1, tags, par3, par4, (ItemStack)null, icon);
    }
    
    public ResearchItem(String par1, ObjectTags tags, int par3, int par4, ItemStack par5Item)
    {
        this(par1, tags, par3, par4, par5Item, -1);
    }

    public ResearchItem(String par1, ObjectTags tags, int par3, int par4, Item par5Item)
    {
        this(par1, tags, par3, par4, new ItemStack(par5Item), -1);
    }

    public ResearchItem(String par1, ObjectTags tags, int par3, int par4, Block par5Block)
    {
        this(par1, tags, par3, par4, new ItemStack(par5Block), -1);
    }

    public ResearchItem(String par1, ObjectTags tags, int par3, int par4, ItemStack par5ItemStack, int icon)
    {
    	this.key = par1;
    	this.tags = tags;
    	this.name = "";
    	this.longText = "";
    	this.popupText = "";
    	
    	Element el = ThaumcraftApi.researchDoc.getElementById(key);
        if (el!=null) {
        	NodeList children = el.getChildNodes();
        	for (int a=0;a<children.getLength();a++) {
            	if (children.item(a).getNodeName().equals("name")) {
            		this.name = children.item(a).getTextContent();
            	} else
            	if (children.item(a).getNodeName().equals("longText")) {
            		this.longText = children.item(a).getTextContent();
            	} else
            	if (children.item(a).getNodeName().equals("popupText") ) {
            		this.popupText = children.item(a).getTextContent();
            	}
            }
        }
    	
		this.itemStack = par5ItemStack;
        this.iconIndex = icon;
        this.displayColumn = par3;
        this.displayRow = par4;
        

        if (par3 < ResearchList.minDisplayColumn)
        {
            ResearchList.minDisplayColumn = par3;
        }

        if (par4 < ResearchList.minDisplayRow)
        {
            ResearchList.minDisplayRow = par4;
        }

        if (par3 > ResearchList.maxDisplayColumn)
        {
            ResearchList.maxDisplayColumn = par3;
        }

        if (par4 > ResearchList.maxDisplayRow)
        {
            ResearchList.maxDisplayRow = par4;
        }

        
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
    
    public ResearchItem setAlternate()
    {
        this.isAlternate = true;
        return this;
    }
    
    public ResearchItem setHidden()
    {
        this.isHidden = true;
        return this;
    }
    
    public ResearchItem setLost()
    {
        this.isLost = true;
        return this;
    }
    
    public ResearchItem setParents(ResearchItem... par)
    {
        this.parents = par;
        return this;
    }
    
    public ResearchItem setParentsHidden(ResearchItem... par)
    {
        this.parentsHidden = par;
        return this;
    }
    
    public ResearchItem setSiblings(ResearchItem... sib)
    {
        this.siblings = sib;
        return this;
    }

    public ResearchItem registerResearchItem()
    {
        ResearchList.research.put(key, this);
        return this;
    }

    @SideOnly(Side.CLIENT)
    public String getName()
    {
        return this.name;
    }

    @SideOnly(Side.CLIENT)
    public boolean getSpecial()
    {
        return this.isSpecial;
    }
    
    public boolean getStub()
    {
        return this.isStub;
    }
    
    public boolean getAlternate()
    {
        return this.isAlternate;
    }
    
    public boolean getHidden()
    {
        return this.isHidden;
    }
    
    public boolean getLost()
    {
        return this.isLost;
    }
    
    public boolean getAutoUnlock() {
		return isAutoUnlock;
	}
	
	public ResearchItem setAutoUnlock()
    {
        this.isAutoUnlock = true;
        return this;
    }
}
