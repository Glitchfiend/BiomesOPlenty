/*******************************************************************************
 * Copyright 2023, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.glitch.event;

public abstract class Event
{
  private boolean cancelled = false;

  public boolean isCancellable()
  {
    return false;
  }

  public boolean isCancelled()
  {
    return this.cancelled;
  }

  public void setCancelled(boolean value)
  {
    if (!this.isCancellable())
      throw new UnsupportedOperationException("Attempted to cancel event which cannot be cancelled!");

    this.cancelled = value;
  }
}