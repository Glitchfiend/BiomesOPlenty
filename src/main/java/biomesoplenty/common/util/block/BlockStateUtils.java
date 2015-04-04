/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBOPBlock;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class BlockStateUtils
{
    
    public static String getStateInfoAsString(IBlockState state)
    {
        String desc = state.getBlock().getClass().getName() + "[";
        Iterator it = state.getProperties().entrySet().iterator();
        boolean first = true;
        while (it.hasNext())
        {
            if (!first) {desc = desc + ",";}
            Entry entry = (Entry)it.next();
            IProperty iproperty = (IProperty)entry.getKey();
            Comparable comparable = (Comparable)entry.getValue();
            desc = desc + iproperty.getName() + "=" + iproperty.getName(comparable);
            first = false;
        }
        desc = desc + "]";
        return desc;
    }
    
    
    public static ImmutableSet<IBlockState> getBlockPresets(IBOPBlock block)
    {        
        return getStatesSet(block.getDefaultState(), block.getPresetProperties());
    }
    
    public static ImmutableSet<IBlockState> getBlockRenderStates(IBOPBlock block)
    {
        return getStatesSet(block.getDefaultState(), block.getRenderProperties());
    }    
    
    public static ImmutableSet<IBlockState> getStatesSet(IBlockState baseState, IProperty... properties)
    {        
        Stack<IProperty> propStack = new Stack<IProperty>();
        List<IBlockState> states = new ArrayList<IBlockState>();
        for (IProperty prop : properties) {propStack.push(prop);}
        if (!propStack.isEmpty())
        {
            addStatesToList(baseState, states, propStack);
        }
        ImmutableSet<IBlockState> ret = ImmutableSet.copyOf(states);
        return ret;
    }
    
    private static void addStatesToList(IBlockState state, List<IBlockState> list, Stack<IProperty> stack)
    {    
        if (stack.empty())
        {
            list.add(state);
            return;
        }
        else
        {
            IProperty prop = stack.pop();        
            for (Object value : prop.getAllowedValues())
            {
                addStatesToList(state.withProperty(prop, (Comparable)value), list, stack);
            }
            stack.push(prop);
        }
    }
    
    /*
    public static Map<String,IBlockState> getStatesSetNamed(IBlockState baseState, IProperty... properties)
    {        
        Stack<IProperty> propStack = new Stack<IProperty>();
        Map<String,IBlockState> states = new HashMap<String, IBlockState>();
        for (IProperty prop : properties) {propStack.push(prop);}
        AddStatesToMap(baseState, states, propStack);
        return states;
    }
    
    private static void AddStatesToMap(IBlockState state, Map<String, IBlockState> map, Stack<IProperty> stack)
    {    
        if (stack.empty())
        {
            map.put(state.getBlock().getStateName(state), state);
            return;
        }
        else
        {
            IProperty prop = stack.pop();        
            for (Object value : prop.getAllowedValues())
            {
                AddStatesToMap(state.withProperty(prop, (Comparable)value), map, stack);
            }
            stack.push(prop);
        }
    }
    */
    
    
    
    
    
    
    public static IProperty getPropertyByName(IBlockState blockState, String propertyName)
    {
        for (IProperty property : (ImmutableSet<IProperty>) blockState.getProperties().keySet())
        {
            if (property.getName().equals(propertyName))
                return property;
        }

        return null;
    }
    

    public static boolean isValidPropertyName(IBlockState blockState, String propertyName)
    {
        return getPropertyByName(blockState, propertyName) != null;
    }

    public static Comparable getPropertyValueByName(IBlockState blockState, IProperty property, String valueName)
    {
        for (Comparable value : (ImmutableSet<Comparable>) property.getAllowedValues())
        {
            if (value.toString().equals(valueName))
                return value;
        }

        return null;
    }

    public static ImmutableSet<IBlockState> getValidStatesForProperties(IBlockState baseState, IProperty... properties)
    {
        if (properties == null)
            return null;

        Set<IBlockState> validStates = Sets.newHashSet();
        PropertyIndexer propertyIndexer = new PropertyIndexer(properties);

        do
        {
            IBlockState currentState = baseState;

            for (IProperty property : properties)
            {
                IndexedProperty indexedProperty = propertyIndexer.getIndexedProperty(property);

                currentState = currentState.withProperty(property, indexedProperty.getCurrentValue());
            }

            validStates.add(currentState);
        }
        while (propertyIndexer.increment());

        return ImmutableSet.copyOf(validStates);
    }

    private static class PropertyIndexer
    {
        private HashMap<IProperty, IndexedProperty> indexedProperties = new HashMap();

        private IProperty finalProperty;

        private PropertyIndexer(IProperty... properties)
        {
            finalProperty = properties[properties.length - 1];

            IndexedProperty previousIndexedProperty = null;

            for (IProperty property : properties)
            {
                IndexedProperty indexedProperty = new IndexedProperty(property);

                if (previousIndexedProperty != null)
                {
                    indexedProperty.parent = previousIndexedProperty;
                    previousIndexedProperty.child = indexedProperty;
                }

                indexedProperties.put(property, indexedProperty);
                previousIndexedProperty = indexedProperty;
            }
        }

        public boolean increment()
        {
            return indexedProperties.get(finalProperty).increment();
        }

        public IndexedProperty getIndexedProperty(IProperty property)
        {
            return indexedProperties.get(property);
        }
    }

    private static class IndexedProperty
    {
        private ArrayList<Comparable> validValues = new ArrayList();

        private int maxCount;
        private int counter;

        private IndexedProperty parent;
        private IndexedProperty child;

        private IndexedProperty(IProperty property)
        {
            this.validValues.addAll(property.getAllowedValues());
            this.maxCount = this.validValues.size() - 1;
        }

        public boolean increment()
        {
            if (counter < maxCount)
                counter++;
            else
            {
                if (hasParent())
                {
                    resetSelfAndChildren();
                    return this.parent.increment();
                }
                else
                    return false;
            }

            return true;
        }

        public void resetSelfAndChildren()
        {
            counter = 0;
            if (this.hasChild())
                this.child.resetSelfAndChildren();
        }

        public boolean hasParent()
        {
            return parent != null;
        }

        public boolean hasChild()
        {
            return child != null;
        }

        public int getCounter()
        {
            return counter;
        }

        public int getMaxCount()
        {
            return maxCount;
        }

        public Comparable getCurrentValue()
        {
            return validValues.get(counter);
        }
    }
    
}
