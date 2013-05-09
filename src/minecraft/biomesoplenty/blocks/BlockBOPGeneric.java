package biomesoplenty.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPGeneric extends Block
{
    public enum BlockType 
    {
        ASH_STONE, HARD_SAND, HARD_DIRT, HARD_ICE, HOLY_STONE, BAMBOO_THATCHING, DRIED_DIRT, CRAG_ROCK, MUD_BRICK, HOLY_DIRT, HOLY_SAND;
    }
    
    private Icon texture;
    private BlockType type;

    public BlockBOPGeneric(int id, Material material, BlockType type)
    {
        super(id, material);
        this.type = type;
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
        
        switch (type)
        {      
            case ASH_STONE:
                setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ashStone");
                break;
                
            case BAMBOO_THATCHING:
                setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bambooThatching");
                break;
                
            case CRAG_ROCK:
                setHardness(1.0F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("cragRock");
                break;
                
            case DRIED_DIRT:
                setHardness(0.1F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("driedDirt");
                break;
                
            case HARD_DIRT:
                setHardness(0.9F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("hardDirt");
                break;
                
            case HARD_ICE:
                setHardness(0.75F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("hardIce");
                break;
                
            case HARD_SAND:
                setHardness(0.7F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("hardSand");
                break;
                
            case HOLY_STONE:
                setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("holyStone");
                break;
                
            case MUD_BRICK:
                setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mudBrick");
                break;
				
			case HOLY_DIRT:
                setHardness(0.6F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("holyDirt");
                break;
				
			case HOLY_SAND:
                setHardness(0.3F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("holySand");
                break;
                
            default:
                break;
        }
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        switch (type)
        { 
            case ASH_STONE:
                texture = iconRegister.registerIcon("BiomesOPlenty:ashstone");
                break;
                
            case BAMBOO_THATCHING:
                texture = iconRegister.registerIcon("BiomesOPlenty:bamboothatching");
                break;
                
            case CRAG_ROCK:
                texture = iconRegister.registerIcon("BiomesOPlenty:cragrock");
                break;
                
            case DRIED_DIRT:
                texture = iconRegister.registerIcon("BiomesOPlenty:drieddirt");
                break;
                
            case HARD_DIRT:
                texture = iconRegister.registerIcon("BiomesOPlenty:harddirt");
                break;
                
            case HARD_ICE:
                texture = iconRegister.registerIcon("BiomesOPlenty:hardice");
                break;
                
            case HARD_SAND:
                texture = iconRegister.registerIcon("BiomesOPlenty:hardsand");
                break;
                
            case HOLY_STONE:
                texture = iconRegister.registerIcon("BiomesOPlenty:holystone");
                break;
                
            case MUD_BRICK:
                texture = iconRegister.registerIcon("BiomesOPlenty:mudbrick");
                break;
				
			case HOLY_DIRT:
                texture = iconRegister.registerIcon("BiomesOPlenty:holydirt");
                break;
				
			case HOLY_SAND:
                texture = iconRegister.registerIcon("BiomesOPlenty:holysand");
                break;
                
            default:
                break;
        }
    }
    
    @Override
    public Icon getIcon(int side, int meta)
    {
        return texture;
    }
}
