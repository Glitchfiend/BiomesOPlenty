## Authors 

- **Adubbz** - "Madman" - General, Technical, Mod Compatibility

- **Amnet** - "Foreign Affairs" - General, Technical, Mod Compatibility

- **Forstride** - "World Painter" - Founder, Art, Biome Design, General

- **ted80** - "Biome Fanatic" - Biome Execution, General

## Credits ##
 
- **gamax92** - Initial Forge Conversion
 
- **enchilado** - Mud Tool and Armour Textures
 
- **Tim Rurkowski** - Music author

## World Conversion##
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
	  Version 0.5.4 (Unreleased)'
	- Added use animation to berry bushes
	- Tweaked apple leaf textures
	- Removed nether tags from overworld biomes
	- Added several Nether Biomes
	- Tweaked dandelion particles
	- Apples and berries from leaves and bushes no longer spawn directly on the player
	- Fixed a bug with trees in the Jade Cliffs
	- Added ivy
	- Nerfed apple tree growth
	- Tweaked algae generation
	- Tweaked algae texture
	- Adjusted the Boreal Forest
	- Added Bone Segments
	- Added kelp
	- Made amethyst tools and armour repairable
	- Overhauled the Bog biome
	- Renamed the Swampwoods to the Sludegpit and made it like the old Bog
	- Adjusted the Deadlands and how Smoldering Grass generates
	- Added the Timber biome
	- Added Spring Water
	- Added Liquid Poison
	- Added the Nourishment potion effect (from Spring Wtaer)
	- Added the Paralysis potion effect (from Poison Darts)
	- Gave the deathbloom a particle effect
	- Made the deathbloom apply the wither effect on contact
	- Added pine trees
	- Tweaked the Canyon
	- Tweaked hard dirt texture
	- Tweaked aloe texture
	- Tweaked fir leaf texture
	- Added pine, hellbark and jacaranda wood, leaves, planks, stairs and slabs
	- Added the Hot Springs biome
	- Added Jacaranda trees
	- Disabled oil generation in the Promised Land
	- Adjusted quicksand generation
	- Allowed tiny cacti and thorns to live in any light level
	- Reworked the Promised Lands portal
	- Improved the Mangrove biome
	- Added steam particles
	- Tweaked grass bounding boxes
	- Added beach variations

	  Version 0.5.3 '17-05-13'
	- Added reeds
	- Added 2 new mushrooms (Portobello and Blue Milk Cap)
	- Adjusted and fixed mod compatibility with Thermal Expansion
	- Adjusted hitboxes for some flowers
	- Added poison ivy
	- Changed default distance between villages
	- Tweaked the Fungi Forest
	- Fixed High Grass generation in the Marsh
	- Removed Barley from blacksmith chests
	- Added white dandelions (they can be blown into the air too!)
	- Improved Redwood Forest Biome and thin Redwood tree generation
	- Replaced the old Ice Sheet Biome with a Polar biome
	- Added random offset for foliage
	- Renamed Yucca to Aloe
	- Changed the moss texture
	- Added the dart blower
	- Fixed a few crafting recipes
	- Added a new Promised Land generator
	- Added Promised Land Sub-Biomes
	- Adjusted which plants grow from bonemeal
	- Made the Promised Lands use holy stone
	- Adjusted Amethyst Ore texture
	- Added hippy flower bands
	- Added cloud blocks
	- Redwood trees now require a 3x3 of redwood saplings
	- Tweaked spider spawn weights in the Ominous Woods
	- Added big tree variants of the red and orange trees to the Seasonal Forest
	- Made the sky colour of the Promised Lands configurable
	- Added Celestial Crystal
	- Fixed a crash bug with mudballs
	- Tweaked apple leaves
	- Adjusted palm tree generation
	- Added alternative blue, brown and green, black and white dyes
	- Added glowshrooms
	- Made Icy Hills hillier
	- Gave barley a random offset
	- Added sunflowers
	- Added lilyflowers
	- Adjusted some achievement descriptions
	- Switched to using BWG4 Acacia Trees
	- Tweaked the Savanna
	- BOP woods now trigger the "Get Wood" achievement
	- Added berry bushes
	- Added sunflower seeds
	- Overhauled the Grove Biome
	- Overhauled the Field Biome
	- Added the "Glob"

      Version 0.5.2 '05-05-13'
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
    - Updated the regular BOP music disc file (courtesy of Forstride), may require deleting the old bopdisc.ogg to take effect
    - Allowed Forestry beehives to spawn on Biomes O Plenty certain blocks
    - Fixed registering biomes for world types - BOP biomes now accessible in Large Biomes World Type
    - Fixed leaf decay
    - Fixed config option for adding biomes to vanilla world types
    - Fixed Red Rock
    - Fixed BOP Items
    - Fixed placing of Mangrove and Holy Saplings
    - Finished the Thaumcraft aspects
    - Changed the styling of Bamboo Forests to that of Forstrides liking
    - Added Yucca flower to desert biomes
    - Added High Cattails
    - Seperated High Grass into two blocks
    - Fire now burns infinitely on ashes
    - Fixed willow colouring
    - Adjust the Canyon Biome slightly
    - Adjusted the Lush Desert biome slightly

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
