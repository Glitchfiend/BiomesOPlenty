/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;

import com.google.common.collect.ImmutableSet;

import biomesoplenty.api.block.IBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

public class BlockStateUtils
{
    
    // utility function for dumping block state info to a string
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
    

    // returns a set of states, one for every possible combination of values from the provided properties
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
    
    // recursively add state values to a list
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
    
    // return all of the different 'preset' variants of a block
    // works by looping through all the different values of the properties specified in block.getPresetProperties()
    // only works on blocks supporting IBOPBlock - returns an empty set for vanilla blocks
    public static ImmutableSet<IBlockState> getBlockPresets(Block block)
    {
        if (!(block instanceof IBOPBlock)) {return ImmutableSet.<IBlockState>of();}
        IBlockState defaultState = block.getDefaultState();
        if (defaultState == null) {defaultState = block.getBlockState().getBaseState();}
        return getStatesSet(defaultState, ((IBOPBlock)block).getPresetProperties());        
    }    
    
    /**Discards additional block information to retrieve a state equivalent to those in the inventory**/
    public static IBlockState getPresetState(IBlockState state)
    {
        IBlockState outState = state.getBlock().getDefaultState();
        
        if (state.getBlock() instanceof IBOPBlock)
        {
            IBOPBlock bopBlock = (IBOPBlock)state.getBlock();
            
            for (IProperty property : bopBlock.getPresetProperties())
            {
                outState = outState.withProperty(property, state.getValue(property));
            }
        }
        
        return outState;
    }
    
    /*  no use for this yet - but left here because it might be useful later
     * 
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
        for (IProperty property : (ImmutableSet<IProperty<?>>) blockState.getProperties().keySet())
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
    

}