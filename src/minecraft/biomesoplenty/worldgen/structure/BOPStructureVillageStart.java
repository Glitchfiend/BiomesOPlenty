package biomesoplenty.worldgen.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillageRoadPiece;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;

class BOPStructureVillageStart extends StructureStart
{
    /** well ... thats what it does */
    private boolean hasMoreThanTwoComponents = false;

    public BOPStructureVillageStart(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        ArrayList arraylist = StructureVillagePieces.getStructureVillageWeightedPieceList(par2Random, par5);
        ComponentVillageStartPiece componentvillagestartpiece = new ComponentVillageStartPiece(par1World.getWorldChunkManager(), 0, par2Random, (par3 << 4) + 2, (par4 << 4) + 2, arraylist, par5);
        this.components.add(componentvillagestartpiece);
        componentvillagestartpiece.buildComponent(componentvillagestartpiece, this.components, par2Random);
        ArrayList arraylist1 = componentvillagestartpiece.field_74930_j;
        ArrayList arraylist2 = componentvillagestartpiece.field_74932_i;
        int l;

        while (!arraylist1.isEmpty() || !arraylist2.isEmpty())
        {
            StructureComponent structurecomponent;

            if (arraylist1.isEmpty())
            {
                l = par2Random.nextInt(arraylist2.size());
                structurecomponent = (StructureComponent)arraylist2.remove(l);
                structurecomponent.buildComponent(componentvillagestartpiece, this.components, par2Random);
            }
            else
            {
                l = par2Random.nextInt(arraylist1.size());
                structurecomponent = (StructureComponent)arraylist1.remove(l);
                structurecomponent.buildComponent(componentvillagestartpiece, this.components, par2Random);
            }
        }

        this.updateBoundingBox();
        l = 0;
        Iterator iterator = this.components.iterator();

        while (iterator.hasNext())
        {
            StructureComponent structurecomponent1 = (StructureComponent)iterator.next();

            if (!(structurecomponent1 instanceof ComponentVillageRoadPiece))
            {
                ++l;
            }
        }

        this.hasMoreThanTwoComponents = l > 2;
    }

    /**
     * currently only defined for Villages, returns true if Village has more than 2 non-road components
     */
    public boolean isSizeableStructure()
    {
        return this.hasMoreThanTwoComponents;
    }
}
