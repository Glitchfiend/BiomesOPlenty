package com.bopteam.biomesop.blocks;

import com.bopteam.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IconRegister;

@Deprecated
public class BlockFirStairs extends BlockStairs
{
    /** The block that is used as model for the stair. */
    private final Block modelBlock;

    public BlockFirStairs(int par1, Block par2Block)
    {
        super(par1, par2Block, 0);
        this.modelBlock = par2Block;
		this.setBurnProperties(this.blockID, 5, 20);
        this.setLightOpacity(0);
        this.setCreativeTab(mod_BiomesOPlenty.tabBiomesOPlenty);
    }

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("BiomesOPlenty:firplank");
	}    
    
}
