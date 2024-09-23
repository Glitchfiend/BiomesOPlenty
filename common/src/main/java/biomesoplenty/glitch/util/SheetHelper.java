/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.glitch.util;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

public class SheetHelper
{
  private static Material createSignMaterial(WoodType type)
  {
    ResourceLocation location = new ResourceLocation(type.name());
    return new Material(Sheets.SIGN_SHEET, new ResourceLocation(location.getNamespace(), "entity/signs/" + location.getPath()));
  }

  private static Material createHangingSignMaterial(WoodType type)
  {
    ResourceLocation location = new ResourceLocation(type.name());
    return new Material(Sheets.SIGN_SHEET, new ResourceLocation(location.getNamespace(), "entity/signs/hanging/" + location.getPath()));
  }

  public static void addWoodType(WoodType woodType)
  {
    Sheets.SIGN_MATERIALS.put(woodType, createSignMaterial(woodType));
    Sheets.HANGING_SIGN_MATERIALS.put(woodType, createHangingSignMaterial(woodType));
  }
}
