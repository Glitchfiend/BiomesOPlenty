package biomesoplenty.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPGeneric extends Block
{
    public enum BlockType 
    {
        ASH_STONE, HARD_SAND, HARD_DIRT, HARD_ICE, HOLY_STONE, AMETHYST_ORE, AMETHYST_BLOCK, BAMBOO_THATCHING, DRIED_DIRT, CRAG_ROCK, MUD_BRICK;
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
            case AMETHYST_BLOCK:
                setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("amethystBlock");
                break;
                
            case AMETHYST_ORE:
                setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("amethystOre");
                break;
                
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
                
            default:
                break;
        }
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        switch (type)
        {
            case AMETHYST_BLOCK:
                texture = iconRegister.registerIcon("BiomesOPlenty:amethystblock");
                break;
                
            case AMETHYST_ORE:
                texture = iconRegister.registerIcon("BiomesOPlenty:amethystore");
                break;
                
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
                
            default:
                break;
        }
    }
    
    @Override
    public Icon getIcon(int side, int meta)
    {
        return texture;
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return type == BlockType.AMETHYST_ORE ? Items.miscItems.get().itemID : this.blockID;
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return type == BlockType.AMETHYST_ORE ? 2 : 0;
    }
    
    @Override
    public int quantityDropped(Random par1Random)
    {
        return type == BlockType.AMETHYST_ORE ? 1 + par1Random.nextInt(2) : 1;
    }
    
    @Override
    public int quantityDroppedWithBonus(int bonus, Random par2Random)
    {
        if (bonus > 0 && this.blockID != this.idDropped(0, par2Random, bonus))
        {
            int rnd = par2Random.nextInt(bonus + 2) - 1;

            if (rnd < 0)
                rnd = 0;

            return this.quantityDropped(par2Random) * (rnd + 1);
        }
        else
            return this.quantityDropped(par2Random);
    }
    
    @Override
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);

        if (this.idDropped(par5, world.rand, par7) != this.blockID)
        {
            int var8 =  MathHelper.getRandomIntegerInRange(world.rand, 1, 4);
            this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
        }
    }
}
