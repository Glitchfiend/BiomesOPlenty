## Authors 

- **Adubbz** - Madman

- **Amnet** - Foreign Affairs

- **ted80** - Biome Fanatic

## Credits ##

- **Forstride** - Original creator
 
- **gamax92**
 
- **enchilado**
 
- **Tim Rurkowski** - Music author

## World Conversion (Don't use yet!)##
As of Biomes O Plenty 0.5.2, changes have been made so that the number of block ids used by the mod are drastically reduced. However, as a side-effect of these changes, existing worlds will no longer work.

However, thanks to a little Java application I (Adubbz) whipped up, as well as the lovely folks who made Midas gold, this should hopefully be easy.


----------

1. Backup your world

2. Grab a copy of the Converter Patch Generator from here:

	[https://github.com/ted80/BiomesOPlenty/tree/master/BOP%20Converter%20Patch%20Generator](https://github.com/ted80/BiomesOPlenty/tree/master/BOP%20Converter%20Patch%20Generator) (Click on the jar file and then 'View Raw' to download it)

3. Grab a copy of Midas Gold from here:

	[https://code.google.com/p/midas-gold/downloads/list](https://code.google.com/p/midas-gold/downloads/list)

4. Extract Midas Gold.

5. Launch the Converter Patch Generator.

6. Click on "Import Config" and select your old Biomes O Plenty config file.

7. Click on "Output Patch" and choose where you want the patch file to be outputted to.

8. Hit "Start".

9. Launch the Midas Gold jar file.

10. Click on "Add Savegame" and select the folder of the world you want converted.

11. Click on "Load Patch File" and select the patch file created by the Converter Patch Generator.

12. Hit start!

13. You're done :)

----------



## Changelog ##
      Version 0.5.2 (Unreleased)
    - Added an API for other mod developers
    - Added support for Biomes O Plenty woods in Thermal Expansion sawmills
    - Significantly compressed the amount of Block IDs used, however breaks existing worlds
    - Updated to the latest Forge
    - Changed various things to use IShearable
    - Changed default biome ids to be compatible with Mo Creatures
    - Made a proper fix for achievements and made them enabled by default (The config option is still there though for those that want it)
    - Added Better World Generation 4 support
    - Made shears instantly destroy Biomes O Plenty leaves
    - Adjusted high grass hitbox to cover both blocks
    - Fixed an issue causing sound files to be created on the desktop
    - Biomes O Plenty saplings now work in the Forestry fermenter
    - Biomes O Plenty flowers can now be used with bees 
    - Gave the different types Autumn and Cherry Leaves their own names
    - Added Biome Dictionary support
    - Added seeds on destroyed Biomes O Plenty grasses
    - Carrots and potatoes now have a chance (1%) to be dropped from sprouts
    - Added a random drop of apples when destroying apple leaves
    - Added flowers to plants created on bonemeal use
    - Tweaked the recipe for bamboo thatching
    - Bamboo now acts as sticks
    - Hopefully fixed saplings once and for all
    - Added hardcoded foliage colours
    - Added configurable spawn distance between villages in the BOP world type
    - Added more biomes for villages to spawn in
    - Tweaked cattail generation
    - Renamed the texture files to reflect the in-game names
    - You can now have different textures for the heart of every log
    - Added Thaumcraft compatibility

      Version 0.5.1 '17-04-2013'
    - Fixed server crash with mudballs 
    - Fixed Forestry beehives spawning, as well as giving biomes appropriate temperatures
    - Removed wrong recipe for mossy cobblestone
    - Added bamboo saplings
    - Bonemeal now creates the appropriate giant flowers when used on red and yellow flowers
    - Changed default biome ids
    - Fixed sapling bugs

      Version 0.5.0 '09-04-2013'
    - Desert sprouts and Dune Grass now require shears to be harvested
    - Added alpha beaches to origin valley biome
    - You can now throw Mudballs to deal 1/2 heart of damage
    - Entities get the slowness potion effect when hit by mudballs
    - Mud balls can now be fired from dispensers
    - Resources now only install client-side
    - Fixed bonemeal on Origin Saplings
    - Bonemeal now only reacts to mangrove saplings when they are on sand
    - Fixed bonemeal and holy grass dependancies on Holy and Magic saplings
    - Changed the leaves blocks to use the IShearable interface
    - Made the enderporter only work in the overworld
    - Fixed the textures for logs to display according to their orientation
    - Adding Leaves, Saplings, Stairs and Slabs to Ore Dictionary
    - Fixed crash on right clicking on slabs with nothing in your hand
    - Fixed trees not generating in the Mystic grove
    - Fixed placing Moss, TreeMoss and Willow
    - Fixed a bug with Promised Lands not using the ID from the config file
    
    Version 0.4.9 '03-04-2013'
    - Tools actually have the properties of the tools they are meant to be (they used to all think they are swords)
    - Ore dictionary support for wood
    - Added a temporary fix for the Ach Flower issue in the form of a config option for achievements
    - Fancier message upon creation of The Promised Lands
    - No more duplicate chat messages on creating the Promised Lands
    - You can no longer create an infinite endstone supply from spamming Promised Lands portals
    - Holy Tall grass no longer drops itself without using shears
    - Fixed things wrongly having wooden footstep sounds
    - Saplings now use the new bonemeal system added by Minecraft 1.5
    - Bonemeal now creates Holy Tall Grass when used on Holy Grass
    - Fixed slabs not stacking
    - Fixed leaf decay
    - Fixed tool effectiveness on various blocks
    - Gave smoldering grass and ash their expected behaviours; burn on contact (smoldering grass) and slowness (ash)
    - Holy and magic saplings will only grow on Holy Grass
    - Holy Grass burns up into Soulsand in the Nether
    - Fixed axe recipes
    - Removed unnecessary recipes for hoes
    
    Version 0.4.8 '31-03-2013' 
    - Fixed smoldering grass texture
    - Fixed flower achievement?
    - Fixed Giant red flower texture

    Version 0.4.7 '30-03-2013' 
    - Updated to Minecraft 1.5.1
