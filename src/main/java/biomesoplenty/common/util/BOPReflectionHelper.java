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
        try {
            Class<?>[] argClasses = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++)
            {
                argClasses[i] = args[i].getClass();
            }
            Constructor<T> constructor = clazz.getDeclaredConstructor(argClasses);
            constructor.setAccessible(true);
            return constructor.newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
