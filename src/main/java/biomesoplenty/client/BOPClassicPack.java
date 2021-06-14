/**Copied from Twilight Forest and modified with permission**/

package biomesoplenty.client;

import biomesoplenty.core.BiomesOPlenty;
import com.google.common.base.Joiner;
import net.minecraft.resources.ResourcePack;
import net.minecraft.resources.ResourcePackFileNotFoundException;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.resources.data.IMetadataSectionSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public class BOPClassicPack extends ResourcePack
{
    private final ModFile modFile;
    private static final String subDir = "classic/";

    public BOPClassicPack(ModFile modFile)
    {
        super(modFile.getFilePath().toFile());
        this.modFile = modFile;
    }

    @Override
    public Set<String> getNamespaces(ResourcePackType type)
    {
        try
        {
            Path root = modFile.getLocator().findPath(modFile, subDir + type.getDirectory()).toAbsolutePath();
            return Files.walk(root,1).map(path -> root.relativize(path.toAbsolutePath())).filter(path -> path.getNameCount() > 0).map(p->p.toString().replaceAll("/$","")).filter(s -> !s.isEmpty()).collect(Collectors.toSet());
        }
        catch (Throwable t)
        {
            BiomesOPlenty.logger.error("BOPClassicPack failed to collect resource namespaces!", t);
            return Collections.emptySet();
        }
    }

    @Override
    protected InputStream getResource(String location) throws IOException
    {
        final Path path = modFile.getLocator().findPath(modFile, subDir + location);

        if (!Files.exists(path))
        {
            BiomesOPlenty.logger.error("File does not exist!");
            throw new ResourcePackFileNotFoundException(path.toFile(), location);
        }

        return Files.newInputStream(path, StandardOpenOption.READ);
    }

    @Override
    protected boolean hasResource(String resourcePath)
    {
        return Files.exists(modFile.getLocator().findPath(modFile, subDir + resourcePath));
    }

    @Override
    public Collection<ResourceLocation> getResources(ResourcePackType type, String namespaceIn, String pathIn, int maxDepthIn, Predicate<String> filterIn)
    {
        try
        {
            Path root = modFile.getLocator().findPath(modFile, subDir + type.getDirectory()).toAbsolutePath();
            Path inputPath = root.getFileSystem().getPath(pathIn);

            return Files.walk(root).map(path -> root.relativize(path.toAbsolutePath())).filter(path -> path.getNameCount() > 1 && path.getNameCount() - 1 <= maxDepthIn).filter(path -> !path.toString().endsWith(".mcmeta")).filter(path -> path.subpath(1, path.getNameCount()).startsWith(inputPath)).filter(path -> filterIn.test(path.getFileName().toString())).map(path -> new ResourceLocation(path.getName(0).toString(), Joiner.on('/').join(path.subpath(1, Math.min(maxDepthIn, path.getNameCount()))))).collect(Collectors.toList());
        }
        catch (IOException e)
        {
            return Collections.emptyList();
        }
    }

    @Override
    public void close() { }

    @Override
    public String getName()
    {
        return "BOP Programmer Art";
    }

    @Nullable
    @Override
    public <T> T getMetadataSection(IMetadataSectionSerializer<T> serializer) throws IOException
    {
        InputStream inputStream = getResource("pack.mcmeta");
        Throwable throwable = null;
        T resourceMetaData;

        try
        {
            resourceMetaData = getMetadataFromStream(serializer, inputStream);
        }
        catch (Throwable t)
        {
            throwable = t;
            throw t;
        }
        finally
        {
            if (inputStream != null)
            {
                if (throwable != null)
                {
                    try
                    {
                        inputStream.close();
                    }
                    catch (Throwable t)
                    {
                        throwable.addSuppressed(t);
                    }
                }
                else
                {
                    inputStream.close();
                }
            }
        }

        return resourceMetaData;
    }
}