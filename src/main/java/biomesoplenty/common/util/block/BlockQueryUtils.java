/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.block;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

public class BlockQueryUtils
{
    
    public static class BlockQueryParseException extends Exception
    {
        public BlockQueryParseException(String message)
        {
            super(message);
        }
    }
    
    public static interface IBlockQuery
    {        
        public boolean matches(IBlockState state);
    }
    
    public static final Map<String, IBlockQuery> predefined = new HashMap<String, IBlockQuery>();
    

    // Match a block if any of the children match
    public static class BlockQueryAny implements IBlockQuery
    {
        private ArrayList<IBlockQuery> children;
        public BlockQueryAny(IBlockQuery... children)
        {
            this.children = new ArrayList<IBlockQuery>(Arrays.asList(children));
        }
        @Override
        public boolean matches(IBlockState state)
        {
            for (IBlockQuery child : this.children)
            {
                if (child.matches(state))
                {
                    return true;
                }
            }
            return false;
        }
        
        public void add(IBlockQuery child)
        {
            if (child != null) {this.children.add(child);}
        }
        public IBlockQuery instance()
        {
            return this.children.size() == 1 ? this.children.get(0) : this;
        }
    }
    
    // Match a block if all of the children match
    public static class BlockQueryAll implements IBlockQuery
    {
        private ArrayList<IBlockQuery> children;
        public BlockQueryAll(IBlockQuery... children)
        {
            this.children = new ArrayList<IBlockQuery>(Arrays.asList(children));
        }
        @Override
        public boolean matches(IBlockState state)
        {
            for (IBlockQuery child : this.children)
            {
                if (!child.matches(state))
                {
                    return false;
                }
            }
            return true;
        }
        
        public void add(IBlockQuery child)
        {
            if (child != null) {this.children.add(child);}
        }
        public IBlockQuery instance()
        {
            return this.children.size() == 1 ? this.children.get(0) : this;
        }
    }
    
    // Match a block if the child does not match
    public static class BlockQueryNot implements IBlockQuery
    {
        IBlockQuery child;
        public BlockQueryNot(IBlockQuery child)
        {
            this.child = child;
        }
        @Override
        public boolean matches(IBlockState state)
        {
            return !this.child.matches(state);
        }
    }
    
    // Match against a particular block instance
    public static class BlockQueryBlock implements IBlockQuery
    {
        private Block block;
        
        public BlockQueryBlock(Block block)
        {
            this.block = block;
        }
        
        @Override
        public boolean matches(IBlockState state)
        {
            return state.getBlock() == this.block;
        }
        
        public static IBlockQuery of(String blockName, boolean negated) throws BlockQueryParseException
        {
            Block block = Block.getBlockFromName(blockName);
            if (block == null)
            {
                throw new BlockQueryParseException("No block called "+blockName);
            } else {
                IBlockQuery bm = new BlockQueryBlock(block);
                return negated ? new BlockQueryNot(bm) : bm;
            }
        }
    }
    
    // Match against a particular block state instance
    public static class BlockQueryState implements IBlockQuery
    {
        private IBlockState state;
        
        public BlockQueryState(IBlockState state)
        {
            this.state = state;
        }
        
        @Override
        public boolean matches(IBlockState state)
        {
            return state == this.state;
        }
    }
    
    // Match against a block class
    public static class BlockQueryClass implements IBlockQuery
    {
        public static String[] packages = {"","biomesoplenty.common.block.","net.minecraft.block."};
        
        private Class<? extends Block> clazz;
        private boolean strict;

        public BlockQueryClass(Class<? extends Block> clazz)
        {
            this(clazz, false);
        }
        
        public BlockQueryClass(Class<? extends Block> clazz, boolean strict)
        {
            this.clazz = clazz;
            this.strict = strict;
        }
        
        @Override
        public boolean matches(IBlockState state)
        {
            return strict ? (state.getBlock().getClass() == this.clazz) : this.clazz.isInstance(state.getBlock());
        }
        
        public static IBlockQuery of(String className, boolean negated, boolean strict) throws BlockQueryParseException
        {
            Class clazz;
            for (String packageName : packages)
            {
                try {
                    clazz = Class.forName(packageName+className);
                } catch (Exception e) {
                    continue;
                }
                if (Block.class.isAssignableFrom(clazz))
                {
                    IBlockQuery bm = new BlockQueryClass(clazz, strict);
                    return negated ? new BlockQueryNot(bm) : bm;
                }
            }
            throw new BlockQueryParseException("No class found extending from Block called "+className);
        }
    }
    
    // Match against a state property value
    public static class BlockQueryProperty implements IBlockQuery
    {
        private static Pattern propertyNameValueRegex = Pattern.compile("^\\s*((\\w+)\\s*=\\s*)?([\\w\\|]+)\\s*$");
        
        private String propName;
        private String[] propValues;
        
        public BlockQueryProperty(String propName, String... propValues)
        {
            this.propName = propName;
            this.propValues = propValues;
        }
        
        @Override
        public boolean matches(IBlockState state)
        {
            ImmutableMap properties = state.getProperties();
            for (Object property : properties.keySet())
            {
                if (((IProperty)property).getName().equalsIgnoreCase(this.propName))
                {
                    String thisPropValue = ((Comparable)properties.get(property)).toString();
                    for (String value : this.propValues)
                    {
                        if (thisPropValue.equalsIgnoreCase(value))
                        {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        }
        
        public static IBlockQuery of(String nameValuePair, boolean negated) throws BlockQueryParseException
        {
            Matcher m = propertyNameValueRegex.matcher(nameValuePair);
            if (!m.find())
            {
                throw new BlockQueryParseException("Syntax error in "+nameValuePair);
            }
            String propName = m.group(2);
            String[] propValues = m.group(3).split("\\|");          
            if (propName == null) {propName = "variant";}
            IBlockQuery bm = new BlockQueryProperty(propName, propValues);
            return negated ? new BlockQueryNot(bm) : bm;
        }
    }
    
    // Match against a block material
    public static class BlockQueryMaterial implements IBlockQuery
    {
        private Material material;
        public BlockQueryMaterial(Material material)
        {
            this.material = material;
        }
        @Override
        public boolean matches(IBlockState state)
        {
            return state.getBlock().getMaterial() == this.material;
        }
        
        public static IBlockQuery of(String materialName, boolean negated) throws BlockQueryParseException
        {
            try {
                Field f = Material.class.getField(materialName);
                Object mat = f.get(null);
                if (mat instanceof Material)
                {
                    IBlockQuery bm = new BlockQueryMaterial((Material)mat);
                    return negated ? new BlockQueryNot(bm) : bm;
                }
            } catch (Exception e) {;}
            throw new BlockQueryParseException("No block material found called "+materialName);
        }
    }
    
    // regular expression to match a token in a block query - eg  'sand' '%BlockBOPLeaves' '[facing=up]' etc
    private static Pattern nextTokenRegex = Pattern.compile("^(!?([\\w:]+|\\%\\w+|\\$\\w+|~\\w+|\\[.+\\]|@\\w+))");
    // regular expression for splitting up a comma delimited list
    private static Pattern commaDelimitRegex = Pattern.compile("\\s*,\\s*");
    
    // parse the given block query string and return the equivalent IBlockQuery object
    public static IBlockQuery parseQueryString(String spec) throws BlockQueryParseException
    {
        BlockQueryAny bmAny = new BlockQueryAny();
        String[] subspecs = commaDelimitRegex.split(spec);
        for (String subspec : subspecs)
        {
            bmAny.add( parseQueryStringSingle(subspec) );
        }
        return bmAny.instance();
    }
    
    
    private static IBlockQuery parseQueryStringSingle(String spec) throws BlockQueryParseException
    {
        BlockQueryAll bmAll = new BlockQueryAll();
        
        Matcher m = nextTokenRegex.matcher(spec);
        while (spec.length() > 0)
        {
            
            m = nextTokenRegex.matcher(spec);
            if (!m.find())
            {
                throw new BlockQueryParseException("Syntax error in "+spec);
            }
            String token = m.group(0);
            spec = spec.substring(token.length());
            
            boolean negated = false;
            if (token.charAt(0) == '!')
            {
                negated = true;
                token = token.substring(1);
            }
            
            if (token.charAt(0) == '%')
            {
                bmAll.add( BlockQueryClass.of(token.substring(1), negated, false) );
            }
            else if (token.charAt(0) == '$')
            {
                bmAll.add( BlockQueryClass.of(token.substring(1), negated, true) );
            }
            else if (token.charAt(0) == '~')
            {
                bmAll.add( BlockQueryMaterial.of(token.substring(1), negated) );
            }
            else if (token.charAt(0)=='[')
            {
                String[] subtokens = commaDelimitRegex.split(token.substring(1, token.length() - 1));
                for (String subtoken : subtokens)
                {
                    bmAll.add( BlockQueryProperty.of(subtoken, negated) );
                }
            }
            else if (token.charAt(0) == '@')
            {
                IBlockQuery bm = predefined.get(token.substring(1));
                if (bm == null)
                {
                    throw new BlockQueryParseException("No predefined matcher named " + token.substring(1));
                }
                bmAll.add( negated ? new BlockQueryNot(bm) : bm );
            }
            else
            {
                bmAll.add( BlockQueryBlock.of(token, negated) );
            }
        }
        
        return bmAll.instance();
        
    } 
    
    
}