package biomesoplenty.common.item;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPHive;
import biomesoplenty.common.entities.projectiles.EntityMudball;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemFlippers extends ItemArmor
{
    
    public ItemFlippers(ItemArmor.ArmorMaterial material, int renderIndex)
    {
        // flippers are always on your feet - armorType = 3
        super(material, renderIndex, EntityEquipmentSlot.FEET);
    }
}

