Hi there! Thanks for taking a look at the TE API. Now before you go 
integrate TE with your mod (which is awesome btw), please read this. 
It'll save us both some grief in the future I promise.

When the API changes and other mods have directly included parts/all of 
it, that's bad. :( Sometimes you have to include a file - if it's an 
interface or something. No worries, just please don't include the whole 
API. To interface with the crafting handlers, you don't have to include 
anything. Here's an example of how to do that:

if (Loader.isModLoaded("ThermalExpansion")) {

	try {
	
		<your TE recipe stuff goes here>

	} catch (Exception e) {
	
		System.out.println("<your mod name>: Thermal Expansion integration was 
		unsuccessful - please contact the author of this mod to let them know 
		that the API may have changed."); 
	}
} 

Again, that doesn't have to be exactly what you do, but if you follow 
these two steps: 

1. Use Loader.isModLoaded
2. Use a try/catch block just to be safe. 

then you will NOT have to actually include the TE API in your zip file, 
unless you are directly implementing / testing for an interface. 

This also holds with most other mods as well, so hopefully it may help 
you clean up your own zips and allow you to redistribute on your 
schedule, not when another mod updates their API. ;)

