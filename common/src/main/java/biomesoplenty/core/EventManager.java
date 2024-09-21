package biomesoplenty.core;

import com.google.common.collect.ImmutableSet;
import net.jodah.typetools.TypeResolver;

import java.util.*;
import java.util.function.Consumer;

public class EventManager
{
  private static Map<Class<? extends Event>, Consumer<? extends Event>[]> listeners = new HashMap<>();
  private static final Object lock = new Object();

  public static <T extends Event> void addListener(Consumer<T> listener)
  {
    Class<T> eventClass = getEventClass(listener);

    synchronized (lock)
    {
      if (listeners.containsKey(eventClass))
      {
        var arr = listeners.get(eventClass);
        int len = arr.length;
        arr = Arrays.copyOf(arr, len + 1);
        arr[len] = listener;
        listeners.replace(eventClass, arr);
      }
      else
      {
        listeners.computeIfAbsent(eventClass, (key) -> new Consumer[]{listener});
      }
    }
  }

  public static <T extends Event> void fire(T event)
  {
    var eventClass = event.getClass();

    for (var listener : Optional.ofNullable(listeners.get(eventClass)).orElse(new Consumer[0]))
    {
      ((Consumer<T>)listener).accept(event);

      if (event.isCancellable() && event.isCancelled())
        break;
    }
  }

  public static ImmutableSet<Class<? extends Event>> getRequiredEvents()
  {
    return ImmutableSet.copyOf(listeners.keySet());
  }

  private static <T extends Event> Class<T> getEventClass(Consumer<T> consumer)
  {
    final Class<T> eventClass = (Class<T>) TypeResolver.resolveRawArgument(Consumer.class, consumer.getClass());
    if ((Class<?>)eventClass == TypeResolver.Unknown.class)
    {
      throw new IllegalStateException("Failed to resolve consumer event type: " + consumer.toString());
    }
    return eventClass;
  }
}

