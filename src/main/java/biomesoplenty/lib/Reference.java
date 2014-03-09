package biomesoplenty.lib;

import java.io.InputStream;
import java.util.Properties;

import com.google.common.base.Throwables;

public class Reference
{
    static 
    { 
        Properties prop = new Properties();
        
        try 
        {
            InputStream stream = Reference.class.getClassLoader().getResourceAsStream("version.properties");
            prop.load(stream);
            stream.close();
        }
        catch(Exception e) 
        {
            Throwables.propagate(e); // just throw it...
        }
        
        VERSION = prop.getProperty("version");
    }
    
    public static final String VERSION;
    public static final String REMOTE_VERSION_FILE = "https://raw.github.com/Glitchfiend/BiomesOPlenty/master/version.txt";
    public static final String REMOTE_CHANGELOG_ROOT = "https://raw.github.com/Glitchfiend/BiomesOPlenty/master/changelog/";
}
