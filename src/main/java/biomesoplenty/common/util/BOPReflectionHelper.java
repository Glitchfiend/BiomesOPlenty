/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util;

import java.lang.reflect.Constructor;

public class BOPReflectionHelper
{    
    
    // Construct an instance of the given class using the given parameters in the constructor
    // Works on constructors which aren't usually accessible
    public static <T> T construct(Class<T> clazz, Object... args)
    {
        Constructor<T> constructor = getConstructor(clazz, args);        
        try {
            constructor.setAccessible(true);
            return constructor.newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> Constructor<T> getConstructor(Class<T> clazz, Object... args)
    {
        int len = args.length;
        for (Constructor constructor : clazz.getDeclaredConstructors())
        {
            Class[] constructorArgTypes = constructor.getParameterTypes();
            if (constructorArgTypes.length == len)
            {
                boolean match = true;
                for (int i = 0; i < len; i++)
                {
                    if (!constructorArgTypes[i].isInstance(args[i]))
                    {
                        match = false;
                    }
                }
                if (match)
                {
                    return (Constructor<T>)constructor;
                }
            }
        }
        return null;
    }
    
}
