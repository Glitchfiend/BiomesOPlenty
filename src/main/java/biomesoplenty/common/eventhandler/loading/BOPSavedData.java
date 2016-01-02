package biomesoplenty.common.eventhandler.loading;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class BOPSavedData extends WorldSavedData
{
    public static final String DATA_IDENTIFIER = "biomesoplenty";
    
    public int lastLoadVersion;
    
    public BOPSavedData(String identifier) 
    {
        super(identifier);
        
        this.lastLoadVersion = LabelHandler.CURRENT_LOAD_VERSION;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) 
    {
        this.lastLoadVersion = nbt.getInteger("LastLoadVersion");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) 
    {
        nbt.setInteger("LastLoadVersion", this.lastLoadVersion);
    }
}
