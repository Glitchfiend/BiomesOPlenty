package biomesoplenty.common.utils;

import java.util.UUID;

public class UUIDUtils 
{
    public static String fromUUID(UUID value) 
    {
        return value.toString().replace("-", "");
    }

    public static UUID fromString(String input) 
    {
        return UUID.fromString(input.replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
    }
}
