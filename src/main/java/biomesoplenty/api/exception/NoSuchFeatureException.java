package biomesoplenty.api.exception;

public class NoSuchFeatureException extends RuntimeException
{
    public NoSuchFeatureException(String name)
    {
        super("Feature " + name + " does not exist!");
    }
}
