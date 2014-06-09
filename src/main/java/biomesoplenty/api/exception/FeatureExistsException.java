package biomesoplenty.api.exception;

public class FeatureExistsException extends RuntimeException
{
    public FeatureExistsException(String name)
    {
        super("Feature " + name + " already exists!");
    }
}
