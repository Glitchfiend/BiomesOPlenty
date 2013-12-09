package tan.api;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerStatRegistry
{
    public static ArrayList<TANStat> tanStatList = new ArrayList<TANStat>();
    
    public static void registerStat(TANStat stat)
    {
        tanStatList.add(stat);
    }
    
    public static <Type extends TANStat> String getStatName(Class<Type> clazz)
    {
        return getStatObject(clazz).getStatName();
    }
    
    public static <Type extends TANStat> TANStat getStatObject(Class<Type> clazz)
    {
        return (TANStat)findObjectOfType(tanStatList, clazz);
    }
    
    public static <Type> Type findObjectOfType(Collection<?> arrayList, Class<Type> clazz)
    {
        for (Object object : arrayList)
        {
            if (object != null && object.getClass() == clazz)
            {
                return clazz.cast(object);
            }
        }

        return null;    
    }
}
