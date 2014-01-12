package biomesoplenty.common.helpers;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.BiMap;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
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
}
