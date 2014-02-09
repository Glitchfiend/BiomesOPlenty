package biomesoplenty.common.helpers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.CoreModManager;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class BOPReflectionHelper
{
	public static boolean isDeobfuscated = ReflectionHelper.getPrivateValue(CoreModManager.class, null, "deobfuscatedEnvironment");
	
    public static <T, E> T getPrivateValue(Class <? super E > classToAccess, E instance, String fieldName, String obfFieldName)
    {
    	if (isDeobfuscated)
    	{
            return ReflectionHelper.getPrivateValue(classToAccess, instance, fieldName);
        } 
    	else
        {
            return ObfuscationReflectionHelper.getPrivateValue(classToAccess, instance, obfFieldName);
        }
    }
    
    public static <T, E> void setPrivateValue(Class <? super T > classToAccess, T instance, E value, String fieldName, String obfFieldName)
    {
        if (isDeobfuscated)
        {
            ReflectionHelper.setPrivateValue(classToAccess, instance, value, fieldName);
        } 
        else
        {
            ObfuscationReflectionHelper.setPrivateValue(classToAccess, instance, value, obfFieldName);
        }
    }
    
    public static <T, E> void setPrivateFinalValue(Class <? super T > classToAccess, T instance, E value, String... fieldNames)
    {
    	Field field = ReflectionHelper.findField(classToAccess, ObfuscationReflectionHelper.remapFieldNames(classToAccess.getName(), fieldNames));

    	try
    	{
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
