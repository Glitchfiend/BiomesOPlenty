package biomesoplenty.common.helpers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class BOPReflectionHelper
{
    public static <T, E> T getPrivateValue(Class <? super E > classToAccess, E instance, String fieldName, String obfFieldName)
    {
        try
        {
            Class.forName("net.minecraft.world.World");
            
            return ReflectionHelper.getPrivateValue(classToAccess, instance, fieldName);
        } 
        catch (ClassNotFoundException e)
        {
            return ObfuscationReflectionHelper.getPrivateValue(classToAccess, instance, obfFieldName);
        }
    }
    
    public static <T, E> void setPrivateFinalValue(Class <? super T > classToAccess, T instance, E value, String fieldName, String obfFieldName)
    {
        try
        {
            Field field = null;
            
            try
            {
                Class.forName("net.minecraft.world.World");
                
                field = ReflectionHelper.findField(classToAccess, fieldName);
            } 
            catch (ClassNotFoundException e)
            {
                field = ReflectionHelper.findField(classToAccess, ObfuscationReflectionHelper.remapFieldNames(classToAccess.getName(), obfFieldName));
            }
            
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            
            field.set(instance, value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
