package graphics.scenery.proteins

import graphics.scenery.Mesh
import org.biojava.nbio.structure.Structure
import org.biojava.nbio.structure.align.util.AtomCache
import org.biojava.nbio.structure.io.FileParsingParameters
import org.biojava.nbio.structure.secstruc.DSSPParser

class SecondaryStructure(pdbID: String): Mesh("SecondaryStructure") {

    fun secondaryStruc() {
        val pdbID = "5pti";
        val params = FileParsingParameters();
        //Only change needed to the normal Structure loading
        params.isParseSecStruc = true; //this is false as DEFAULT

        val cache = AtomCache();
        cache.fileParsingParams = params;

        //The loaded Structure contains the SS assigned
        val s = cache.getStructure(pdbID);

        //If the more detailed DSSP prediction is required call this afterwards
        DSSPParser.fetch(pdbID, s, true); //Second parameter true overrides the previous SS
    }
}