package graphics.scenery.proteins

import org.biojava.nbio.structure.*

data class AminoAcidBonds(val structure: Structure)  {

    val chains = structure.chains
    val groups = chains.flatMap { it.atomGroups }


    fun aminoBonds(): ArrayList<Bond> {
        val bonds = ArrayList<Bond>()
        /*
        groups.forEach {
            when (it.pdbName) {
                "ACE"-> { val atomsMap0 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HH3"-> atomsMap0.put(it, 0)
                        "3HA"-> atomsMap0.put(it, 0)
                        "HH31"-> atomsMap0.put(it, 0)
                        "1HA"-> atomsMap0.put(it, 1)
                        "2HH3"-> atomsMap0.put(it, 1)
                        "HH32"-> atomsMap0.put(it, 1)
                        "2HA"-> atomsMap0.put(it, 2)
                        "3HH3"-> atomsMap0.put(it, 2)
                        "HH33"-> atomsMap0.put(it, 2)
                        "C"-> atomsMap0.put(it, 3)
                        "CA"-> atomsMap0.put(it, 4)
                        "CH3"-> atomsMap0.put(it, 4)
                        "O"-> atomsMap0.put(it, 5)
                        else -> atomsMap0.put(it, -1)}
                    }



                    atomsMap0.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap0.filterValues { it == 4 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap0.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap0.filterValues { it == 4 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap0.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap0.filterValues { it == 4 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap0.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap0.filterValues { it == 4 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap0.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap0.filterValues { it == 5 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                }
                "ALA"-> { val atomsMap1 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HB"-> atomsMap1.put(it, 0)
                        "HB1"-> atomsMap1.put(it, 0)
                        "1H"-> atomsMap1.put(it, 1)
                        "1HT"-> atomsMap1.put(it, 1)
                        "H1"-> atomsMap1.put(it, 1)
                        "HT1"-> atomsMap1.put(it, 1)
                        "2HB"-> atomsMap1.put(it, 2)
                        "HB2"-> atomsMap1.put(it, 2)
                        "2H"-> atomsMap1.put(it, 3)
                        "2HT"-> atomsMap1.put(it, 3)
                        "H2"-> atomsMap1.put(it, 3)
                        "HT2"-> atomsMap1.put(it, 3)
                        "3HB"-> atomsMap1.put(it, 4)
                        "HB3"-> atomsMap1.put(it, 4)
                        "3H"-> atomsMap1.put(it, 5)
                        "3HT"-> atomsMap1.put(it, 5)
                        "H3"-> atomsMap1.put(it, 5)
                        "HT3"-> atomsMap1.put(it, 5)
                        "C"-> atomsMap1.put(it, 6)
                        "CA"-> atomsMap1.put(it, 7)
                        "CB"-> atomsMap1.put(it, 8)
                        "H"-> atomsMap1.put(it, 9)
                        "HN"-> atomsMap1.put(it, 9)
                        "HA"-> atomsMap1.put(it, 10)
                        "HA2"-> atomsMap1.put(it, 10)
                        "N"-> atomsMap1.put(it, 11)
                        "O"-> atomsMap1.put(it, 12)
                        "O1"-> atomsMap1.put(it, 12)
                        "OT1"-> atomsMap1.put(it, 12)
                        "O2"-> atomsMap1.put(it, 13)
                        "OT"-> atomsMap1.put(it, 13)
                        "OT2"-> atomsMap1.put(it, 13)
                        "OXT"-> atomsMap1.put(it, 13)
                        else -> atomsMap1.put(it, -1)}
                    }



                    atomsMap1.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap1.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap1.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap1.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap1.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap1.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap1.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap1.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap1.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap1.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap1.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap1.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap1.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "ARG"-> { val atomsMap2 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HH1"-> atomsMap2.put(it, 0)
                        "HH11"-> atomsMap2.put(it, 0)
                        "1HH2"-> atomsMap2.put(it, 1)
                        "HH21"-> atomsMap2.put(it, 1)
                        "1H"-> atomsMap2.put(it, 2)
                        "1HT"-> atomsMap2.put(it, 2)
                        "H1"-> atomsMap2.put(it, 2)
                        "HT1"-> atomsMap2.put(it, 2)
                        "2HB"-> atomsMap2.put(it, 3)
                        "HB2"-> atomsMap2.put(it, 3)
                        "2HD"-> atomsMap2.put(it, 4)
                        "HD2"-> atomsMap2.put(it, 4)
                        "2HG"-> atomsMap2.put(it, 5)
                        "HG2"-> atomsMap2.put(it, 5)
                        "2HH1"-> atomsMap2.put(it, 6)
                        "HH12"-> atomsMap2.put(it, 6)
                        "2HH2"-> atomsMap2.put(it, 7)
                        "HH22"-> atomsMap2.put(it, 7)
                        "2H"-> atomsMap2.put(it, 8)
                        "2HT"-> atomsMap2.put(it, 8)
                        "H2"-> atomsMap2.put(it, 8)
                        "HT2"-> atomsMap2.put(it, 8)
                        "1HB"-> atomsMap2.put(it, 9)
                        "3HB"-> atomsMap2.put(it, 9)
                        "HB1"-> atomsMap2.put(it, 9)
                        "HB3"-> atomsMap2.put(it, 9)
                        "1HD"-> atomsMap2.put(it, 10)
                        "3HD"-> atomsMap2.put(it, 10)
                        "HD1"-> atomsMap2.put(it, 10)
                        "HD3"-> atomsMap2.put(it, 10)
                        "1HG"-> atomsMap2.put(it, 11)
                        "3HG"-> atomsMap2.put(it, 11)
                        "HG1"-> atomsMap2.put(it, 11)
                        "HG3"-> atomsMap2.put(it, 11)
                        "3H"-> atomsMap2.put(it, 12)
                        "3HT"-> atomsMap2.put(it, 12)
                        "H3"-> atomsMap2.put(it, 12)
                        "HT3"-> atomsMap2.put(it, 12)
                        "C"-> atomsMap2.put(it, 13)
                        "CA"-> atomsMap2.put(it, 14)
                        "CB"-> atomsMap2.put(it, 15)
                        "CD"-> atomsMap2.put(it, 16)
                        "CG"-> atomsMap2.put(it, 17)
                        "CZ"-> atomsMap2.put(it, 18)
                        "H"-> atomsMap2.put(it, 19)
                        "HN"-> atomsMap2.put(it, 19)
                        "HA"-> atomsMap2.put(it, 20)
                        "HA2"-> atomsMap2.put(it, 20)
                        "HE"-> atomsMap2.put(it, 21)
                        "N"-> atomsMap2.put(it, 22)
                        "NE"-> atomsMap2.put(it, 23)
                        "NH1"-> atomsMap2.put(it, 24)
                        "NH2"-> atomsMap2.put(it, 25)
                        "O"-> atomsMap2.put(it, 26)
                        "O1"-> atomsMap2.put(it, 26)
                        "OT1"-> atomsMap2.put(it, 26)
                        "O2"-> atomsMap2.put(it, 27)
                        "OT"-> atomsMap2.put(it, 27)
                        "OT2"-> atomsMap2.put(it, 27)
                        "OXT"-> atomsMap2.put(it, 27)
                        else -> atomsMap2.put(it, -1)}
                    }



                    atomsMap2.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 24 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 25 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 24 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 25 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 26 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==16 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==16 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 23 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==18 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 23 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==18 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 24 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==18 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 25 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==19 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap2.filterValues { it ==21 }.forEach() {
                        val atom1 = it.key
                        atomsMap2.filterValues { it == 23 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "ASH"-> { val atomsMap3 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1H"-> atomsMap3.put(it, 0)
                        "1HT"-> atomsMap3.put(it, 0)
                        "H1"-> atomsMap3.put(it, 0)
                        "HT1"-> atomsMap3.put(it, 0)
                        "2HB"-> atomsMap3.put(it, 1)
                        "HB2"-> atomsMap3.put(it, 1)
                        "2H"-> atomsMap3.put(it, 2)
                        "2HT"-> atomsMap3.put(it, 2)
                        "H2"-> atomsMap3.put(it, 2)
                        "HT2"-> atomsMap3.put(it, 2)
                        "1HB"-> atomsMap3.put(it, 3)
                        "3HB"-> atomsMap3.put(it, 3)
                        "HB1"-> atomsMap3.put(it, 3)
                        "HB3"-> atomsMap3.put(it, 3)
                        "3H"-> atomsMap3.put(it, 4)
                        "3HT"-> atomsMap3.put(it, 4)
                        "H3"-> atomsMap3.put(it, 4)
                        "HT3"-> atomsMap3.put(it, 4)
                        "C"-> atomsMap3.put(it, 5)
                        "CA"-> atomsMap3.put(it, 6)
                        "CB"-> atomsMap3.put(it, 7)
                        "CG"-> atomsMap3.put(it, 8)
                        "H"-> atomsMap3.put(it, 9)
                        "HN"-> atomsMap3.put(it, 9)
                        "HA"-> atomsMap3.put(it, 10)
                        "HA2"-> atomsMap3.put(it, 10)
                        "HD2"-> atomsMap3.put(it, 11)
                        "N"-> atomsMap3.put(it, 12)
                        "O"-> atomsMap3.put(it, 13)
                        "O1"-> atomsMap3.put(it, 13)
                        "OT1"-> atomsMap3.put(it, 13)
                        "OD1"-> atomsMap3.put(it, 14)
                        "OD2"-> atomsMap3.put(it, 15)
                        "O2"-> atomsMap3.put(it, 16)
                        "OT"-> atomsMap3.put(it, 16)
                        "OT2"-> atomsMap3.put(it, 16)
                        "OXT"-> atomsMap3.put(it, 16)
                        else -> atomsMap3.put(it, -1)}
                    }



                    atomsMap3.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 6 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap3.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap3.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "ASN"-> { val atomsMap4 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HD2"-> atomsMap4.put(it, 0)
                        "HD21"-> atomsMap4.put(it, 0)
                        "1H"-> atomsMap4.put(it, 1)
                        "1HT"-> atomsMap4.put(it, 1)
                        "H1"-> atomsMap4.put(it, 1)
                        "HT1"-> atomsMap4.put(it, 1)
                        "2HB"-> atomsMap4.put(it, 2)
                        "HB2"-> atomsMap4.put(it, 2)
                        "2HD2"-> atomsMap4.put(it, 3)
                        "HD22"-> atomsMap4.put(it, 3)
                        "2H"-> atomsMap4.put(it, 4)
                        "2HT"-> atomsMap4.put(it, 4)
                        "H2"-> atomsMap4.put(it, 4)
                        "HT2"-> atomsMap4.put(it, 4)
                        "1HB"-> atomsMap4.put(it, 5)
                        "3HB"-> atomsMap4.put(it, 5)
                        "HB1"-> atomsMap4.put(it, 5)
                        "HB3"-> atomsMap4.put(it, 5)
                        "3H"-> atomsMap4.put(it, 6)
                        "3HT"-> atomsMap4.put(it, 6)
                        "H3"-> atomsMap4.put(it, 6)
                        "HT3"-> atomsMap4.put(it, 6)
                        "C"-> atomsMap4.put(it, 7)
                        "CA"-> atomsMap4.put(it, 8)
                        "CB"-> atomsMap4.put(it, 9)
                        "CG"-> atomsMap4.put(it, 10)
                        "H"-> atomsMap4.put(it, 11)
                        "HN"-> atomsMap4.put(it, 11)
                        "HA"-> atomsMap4.put(it, 12)
                        "HA2"-> atomsMap4.put(it, 12)
                        "N"-> atomsMap4.put(it, 13)
                        "ND2"-> atomsMap4.put(it, 14)
                        "O"-> atomsMap4.put(it, 15)
                        "O1"-> atomsMap4.put(it, 15)
                        "OT1"-> atomsMap4.put(it, 15)
                        "OD1"-> atomsMap4.put(it, 16)
                        "O2"-> atomsMap4.put(it, 17)
                        "OT"-> atomsMap4.put(it, 17)
                        "OT2"-> atomsMap4.put(it, 17)
                        "OXT"-> atomsMap4.put(it, 17)
                        else -> atomsMap4.put(it, -1)}
                    }



                    atomsMap4.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap4.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap4.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "ASP"-> { val atomsMap5 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1H"-> atomsMap5.put(it, 0)
                        "1HT"-> atomsMap5.put(it, 0)
                        "H1"-> atomsMap5.put(it, 0)
                        "HT1"-> atomsMap5.put(it, 0)
                        "2HB"-> atomsMap5.put(it, 1)
                        "HB2"-> atomsMap5.put(it, 1)
                        "2H"-> atomsMap5.put(it, 2)
                        "2HT"-> atomsMap5.put(it, 2)
                        "H2"-> atomsMap5.put(it, 2)
                        "HT2"-> atomsMap5.put(it, 2)
                        "1HB"-> atomsMap5.put(it, 3)
                        "3HB"-> atomsMap5.put(it, 3)
                        "HB1"-> atomsMap5.put(it, 3)
                        "HB3"-> atomsMap5.put(it, 3)
                        "3H"-> atomsMap5.put(it, 4)
                        "3HT"-> atomsMap5.put(it, 4)
                        "H3"-> atomsMap5.put(it, 4)
                        "HT3"-> atomsMap5.put(it, 4)
                        "C"-> atomsMap5.put(it, 5)
                        "CA"-> atomsMap5.put(it, 6)
                        "CB"-> atomsMap5.put(it, 7)
                        "CG"-> atomsMap5.put(it, 8)
                        "H"-> atomsMap5.put(it, 9)
                        "HN"-> atomsMap5.put(it, 9)
                        "HA"-> atomsMap5.put(it, 10)
                        "HA2"-> atomsMap5.put(it, 10)
                        "N"-> atomsMap5.put(it, 11)
                        "O"-> atomsMap5.put(it, 12)
                        "O1"-> atomsMap5.put(it, 12)
                        "OT1"-> atomsMap5.put(it, 12)
                        "OD1"-> atomsMap5.put(it, 13)
                        "OD2"-> atomsMap5.put(it, 14)
                        "O2"-> atomsMap5.put(it, 15)
                        "OT"-> atomsMap5.put(it, 15)
                        "OT2"-> atomsMap5.put(it, 15)
                        "OXT"-> atomsMap5.put(it, 15)
                        else -> atomsMap5.put(it, -1)}
                    }



                    atomsMap5.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 6 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap5.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap5.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "CYM"-> { val atomsMap6 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "2HB"-> atomsMap6.put(it, 0)
                        "HB2"-> atomsMap6.put(it, 0)
                        "3HB"-> atomsMap6.put(it, 1)
                        "HB3"-> atomsMap6.put(it, 1)
                        "C"-> atomsMap6.put(it, 2)
                        "CA"-> atomsMap6.put(it, 3)
                        "CB"-> atomsMap6.put(it, 4)
                        "H"-> atomsMap6.put(it, 5)
                        "HN"-> atomsMap6.put(it, 5)
                        "HA"-> atomsMap6.put(it, 6)
                        "HA2"-> atomsMap6.put(it, 6)
                        "N"-> atomsMap6.put(it, 7)
                        "O"-> atomsMap6.put(it, 8)
                        "SG"-> atomsMap6.put(it, 9)
                        else -> atomsMap6.put(it, -1)}
                    }



                    atomsMap6.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap6.filterValues { it == 4 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap6.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap6.filterValues { it == 4 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap6.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap6.filterValues { it == 3 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap6.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap6.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap6.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap6.filterValues { it == 4 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap6.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap6.filterValues { it == 6 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap6.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap6.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap6.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap6.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap6.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap6.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "CYS"-> { val atomsMap7 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1H"-> atomsMap7.put(it, 0)
                        "1HT"-> atomsMap7.put(it, 0)
                        "H1"-> atomsMap7.put(it, 0)
                        "HT1"-> atomsMap7.put(it, 0)
                        "2HB"-> atomsMap7.put(it, 1)
                        "HB2"-> atomsMap7.put(it, 1)
                        "2H"-> atomsMap7.put(it, 2)
                        "2HT"-> atomsMap7.put(it, 2)
                        "H2"-> atomsMap7.put(it, 2)
                        "HT2"-> atomsMap7.put(it, 2)
                        "1HB"-> atomsMap7.put(it, 3)
                        "3HB"-> atomsMap7.put(it, 3)
                        "HB1"-> atomsMap7.put(it, 3)
                        "HB3"-> atomsMap7.put(it, 3)
                        "3H"-> atomsMap7.put(it, 4)
                        "3HT"-> atomsMap7.put(it, 4)
                        "H3"-> atomsMap7.put(it, 4)
                        "HT3"-> atomsMap7.put(it, 4)
                        "C"-> atomsMap7.put(it, 5)
                        "CA"-> atomsMap7.put(it, 6)
                        "CB"-> atomsMap7.put(it, 7)
                        "H"-> atomsMap7.put(it, 8)
                        "HN"-> atomsMap7.put(it, 8)
                        "HA"-> atomsMap7.put(it, 9)
                        "HA2"-> atomsMap7.put(it, 9)
                        "HG"-> atomsMap7.put(it, 10)
                        "HG1"-> atomsMap7.put(it, 10)
                        "N"-> atomsMap7.put(it, 11)
                        "O"-> atomsMap7.put(it, 12)
                        "O1"-> atomsMap7.put(it, 12)
                        "OT1"-> atomsMap7.put(it, 12)
                        "O2"-> atomsMap7.put(it, 13)
                        "OT"-> atomsMap7.put(it, 13)
                        "OT2"-> atomsMap7.put(it, 13)
                        "OXT"-> atomsMap7.put(it, 13)
                        "SG"-> atomsMap7.put(it, 14)
                        else -> atomsMap7.put(it, -1)}
                    }



                    atomsMap7.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 6 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap7.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap7.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "CYX"-> { val atomsMap8 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1H"-> atomsMap8.put(it, 0)
                        "1HT"-> atomsMap8.put(it, 0)
                        "H1"-> atomsMap8.put(it, 0)
                        "HT1"-> atomsMap8.put(it, 0)
                        "2HB"-> atomsMap8.put(it, 1)
                        "HB2"-> atomsMap8.put(it, 1)
                        "2H"-> atomsMap8.put(it, 2)
                        "2HT"-> atomsMap8.put(it, 2)
                        "H2"-> atomsMap8.put(it, 2)
                        "HT2"-> atomsMap8.put(it, 2)
                        "1HB"-> atomsMap8.put(it, 3)
                        "3HB"-> atomsMap8.put(it, 3)
                        "HB1"-> atomsMap8.put(it, 3)
                        "HB3"-> atomsMap8.put(it, 3)
                        "3H"-> atomsMap8.put(it, 4)
                        "3HT"-> atomsMap8.put(it, 4)
                        "H3"-> atomsMap8.put(it, 4)
                        "HT3"-> atomsMap8.put(it, 4)
                        "C"-> atomsMap8.put(it, 5)
                        "CA"-> atomsMap8.put(it, 6)
                        "CB"-> atomsMap8.put(it, 7)
                        "H"-> atomsMap8.put(it, 8)
                        "HN"-> atomsMap8.put(it, 8)
                        "HA"-> atomsMap8.put(it, 9)
                        "HA2"-> atomsMap8.put(it, 9)
                        "N"-> atomsMap8.put(it, 10)
                        "O"-> atomsMap8.put(it, 11)
                        "O1"-> atomsMap8.put(it, 11)
                        "OT1"-> atomsMap8.put(it, 11)
                        "O2"-> atomsMap8.put(it, 12)
                        "OT"-> atomsMap8.put(it, 12)
                        "OT2"-> atomsMap8.put(it, 12)
                        "OXT"-> atomsMap8.put(it, 12)
                        "SG"-> atomsMap8.put(it, 13)
                        else -> atomsMap8.put(it, -1)}
                    }



                    atomsMap8.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap8.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap8.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap8.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap8.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap8.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 6 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap8.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap8.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap8.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap8.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap8.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap8.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap8.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "GLN"-> { val atomsMap9 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HE2"-> atomsMap9.put(it, 0)
                        "HE21"-> atomsMap9.put(it, 0)
                        "1H"-> atomsMap9.put(it, 1)
                        "1HT"-> atomsMap9.put(it, 1)
                        "H1"-> atomsMap9.put(it, 1)
                        "HT1"-> atomsMap9.put(it, 1)
                        "2HB"-> atomsMap9.put(it, 2)
                        "HB2"-> atomsMap9.put(it, 2)
                        "2HE2"-> atomsMap9.put(it, 3)
                        "HE22"-> atomsMap9.put(it, 3)
                        "2HG"-> atomsMap9.put(it, 4)
                        "HG2"-> atomsMap9.put(it, 4)
                        "2H"-> atomsMap9.put(it, 5)
                        "2HT"-> atomsMap9.put(it, 5)
                        "H2"-> atomsMap9.put(it, 5)
                        "HT2"-> atomsMap9.put(it, 5)
                        "1HB"-> atomsMap9.put(it, 6)
                        "3HB"-> atomsMap9.put(it, 6)
                        "HB1"-> atomsMap9.put(it, 6)
                        "HB3"-> atomsMap9.put(it, 6)
                        "1HG"-> atomsMap9.put(it, 7)
                        "3HG"-> atomsMap9.put(it, 7)
                        "HG1"-> atomsMap9.put(it, 7)
                        "HG3"-> atomsMap9.put(it, 7)
                        "3H"-> atomsMap9.put(it, 8)
                        "3HT"-> atomsMap9.put(it, 8)
                        "H3"-> atomsMap9.put(it, 8)
                        "HT3"-> atomsMap9.put(it, 8)
                        "C"-> atomsMap9.put(it, 9)
                        "CA"-> atomsMap9.put(it, 10)
                        "CB"-> atomsMap9.put(it, 11)
                        "CD"-> atomsMap9.put(it, 12)
                        "CG"-> atomsMap9.put(it, 13)
                        "H"-> atomsMap9.put(it, 14)
                        "HN"-> atomsMap9.put(it, 14)
                        "HA"-> atomsMap9.put(it, 15)
                        "HA2"-> atomsMap9.put(it, 15)
                        "N"-> atomsMap9.put(it, 16)
                        "NE2"-> atomsMap9.put(it, 17)
                        "O"-> atomsMap9.put(it, 18)
                        "O1"-> atomsMap9.put(it, 18)
                        "OT1"-> atomsMap9.put(it, 18)
                        "OE1"-> atomsMap9.put(it, 19)
                        "O2"-> atomsMap9.put(it, 20)
                        "OT"-> atomsMap9.put(it, 20)
                        "OT2"-> atomsMap9.put(it, 20)
                        "OXT"-> atomsMap9.put(it, 20)
                        else -> atomsMap9.put(it, -1)}
                    }



                    atomsMap9.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap9.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap9.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "GLP"-> { val atomsMap10 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1H"-> atomsMap10.put(it, 0)
                        "1HT"-> atomsMap10.put(it, 0)
                        "H1"-> atomsMap10.put(it, 0)
                        "HT1"-> atomsMap10.put(it, 0)
                        "2HB"-> atomsMap10.put(it, 1)
                        "HB2"-> atomsMap10.put(it, 1)
                        "2HG"-> atomsMap10.put(it, 2)
                        "HG2"-> atomsMap10.put(it, 2)
                        "2H"-> atomsMap10.put(it, 3)
                        "2HT"-> atomsMap10.put(it, 3)
                        "H2"-> atomsMap10.put(it, 3)
                        "HT2"-> atomsMap10.put(it, 3)
                        "1HB"-> atomsMap10.put(it, 4)
                        "3HB"-> atomsMap10.put(it, 4)
                        "HB1"-> atomsMap10.put(it, 4)
                        "HB3"-> atomsMap10.put(it, 4)
                        "1HG"-> atomsMap10.put(it, 5)
                        "3HG"-> atomsMap10.put(it, 5)
                        "HG1"-> atomsMap10.put(it, 5)
                        "HG3"-> atomsMap10.put(it, 5)
                        "3H"-> atomsMap10.put(it, 6)
                        "3HT"-> atomsMap10.put(it, 6)
                        "H3"-> atomsMap10.put(it, 6)
                        "HT3"-> atomsMap10.put(it, 6)
                        "C"-> atomsMap10.put(it, 7)
                        "CA"-> atomsMap10.put(it, 8)
                        "CB"-> atomsMap10.put(it, 9)
                        "CD"-> atomsMap10.put(it, 10)
                        "CG"-> atomsMap10.put(it, 11)
                        "H"-> atomsMap10.put(it, 12)
                        "HN"-> atomsMap10.put(it, 12)
                        "HA"-> atomsMap10.put(it, 13)
                        "HA2"-> atomsMap10.put(it, 13)
                        "HE2"-> atomsMap10.put(it, 14)
                        "N"-> atomsMap10.put(it, 15)
                        "O"-> atomsMap10.put(it, 16)
                        "O1"-> atomsMap10.put(it, 16)
                        "OT1"-> atomsMap10.put(it, 16)
                        "OE1"-> atomsMap10.put(it, 17)
                        "OE2"-> atomsMap10.put(it, 18)
                        "O2"-> atomsMap10.put(it, 19)
                        "OT"-> atomsMap10.put(it, 19)
                        "OT2"-> atomsMap10.put(it, 19)
                        "OXT"-> atomsMap10.put(it, 19)
                        else -> atomsMap10.put(it, -1)}
                    }



                    atomsMap10.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap10.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap10.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "GLU"-> { val atomsMap11 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1H"-> atomsMap11.put(it, 0)
                        "1HT"-> atomsMap11.put(it, 0)
                        "H1"-> atomsMap11.put(it, 0)
                        "HT1"-> atomsMap11.put(it, 0)
                        "2HB"-> atomsMap11.put(it, 1)
                        "HB2"-> atomsMap11.put(it, 1)
                        "2HG"-> atomsMap11.put(it, 2)
                        "HG2"-> atomsMap11.put(it, 2)
                        "2H"-> atomsMap11.put(it, 3)
                        "2HT"-> atomsMap11.put(it, 3)
                        "H2"-> atomsMap11.put(it, 3)
                        "HT2"-> atomsMap11.put(it, 3)
                        "1HB"-> atomsMap11.put(it, 4)
                        "3HB"-> atomsMap11.put(it, 4)
                        "HB1"-> atomsMap11.put(it, 4)
                        "HB3"-> atomsMap11.put(it, 4)
                        "1HG"-> atomsMap11.put(it, 5)
                        "3HG"-> atomsMap11.put(it, 5)
                        "HG1"-> atomsMap11.put(it, 5)
                        "HG3"-> atomsMap11.put(it, 5)
                        "3H"-> atomsMap11.put(it, 6)
                        "3HT"-> atomsMap11.put(it, 6)
                        "H3"-> atomsMap11.put(it, 6)
                        "HT3"-> atomsMap11.put(it, 6)
                        "C"-> atomsMap11.put(it, 7)
                        "CA"-> atomsMap11.put(it, 8)
                        "CB"-> atomsMap11.put(it, 9)
                        "CD"-> atomsMap11.put(it, 10)
                        "CG"-> atomsMap11.put(it, 11)
                        "H"-> atomsMap11.put(it, 12)
                        "HN"-> atomsMap11.put(it, 12)
                        "HA"-> atomsMap11.put(it, 13)
                        "HA2"-> atomsMap11.put(it, 13)
                        "N"-> atomsMap11.put(it, 14)
                        "O"-> atomsMap11.put(it, 15)
                        "O1"-> atomsMap11.put(it, 15)
                        "OT1"-> atomsMap11.put(it, 15)
                        "OE1"-> atomsMap11.put(it, 16)
                        "OE2"-> atomsMap11.put(it, 17)
                        "O2"-> atomsMap11.put(it, 18)
                        "OT"-> atomsMap11.put(it, 18)
                        "OT2"-> atomsMap11.put(it, 18)
                        "OXT"-> atomsMap11.put(it, 18)
                        else -> atomsMap11.put(it, -1)}
                    }



                    atomsMap11.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap11.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap11.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "GLY"-> { val atomsMap12 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1H"-> atomsMap12.put(it, 0)
                        "1HT"-> atomsMap12.put(it, 0)
                        "H1"-> atomsMap12.put(it, 0)
                        "HT1"-> atomsMap12.put(it, 0)
                        "2H"-> atomsMap12.put(it, 1)
                        "2HT"-> atomsMap12.put(it, 1)
                        "H2"-> atomsMap12.put(it, 1)
                        "HT2"-> atomsMap12.put(it, 1)
                        "1HA"-> atomsMap12.put(it, 2)
                        "3HA"-> atomsMap12.put(it, 2)
                        "HA1"-> atomsMap12.put(it, 2)
                        "HA3"-> atomsMap12.put(it, 2)
                        "3H"-> atomsMap12.put(it, 3)
                        "3HT"-> atomsMap12.put(it, 3)
                        "H3"-> atomsMap12.put(it, 3)
                        "HT3"-> atomsMap12.put(it, 3)
                        "C"-> atomsMap12.put(it, 4)
                        "CA"-> atomsMap12.put(it, 5)
                        "H"-> atomsMap12.put(it, 6)
                        "HN"-> atomsMap12.put(it, 6)
                        "2HA"-> atomsMap12.put(it, 7)
                        "HA"-> atomsMap12.put(it, 7)
                        "HA2"-> atomsMap12.put(it, 7)
                        "N"-> atomsMap12.put(it, 8)
                        "O"-> atomsMap12.put(it, 9)
                        "O1"-> atomsMap12.put(it, 9)
                        "OT1"-> atomsMap12.put(it, 9)
                        "O2"-> atomsMap12.put(it, 10)
                        "OT"-> atomsMap12.put(it, 10)
                        "OT2"-> atomsMap12.put(it, 10)
                        "OXT"-> atomsMap12.put(it, 10)
                        else -> atomsMap12.put(it, -1)}
                    }



                    atomsMap12.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap12.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap12.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap12.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap12.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap12.filterValues { it == 5 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap12.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap12.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap12.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap12.filterValues { it == 5 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap12.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap12.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap12.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap12.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap12.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap12.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap12.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap12.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "HID"-> { val atomsMap13 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HD"-> atomsMap13.put(it, 0)
                        "HD1"-> atomsMap13.put(it, 0)
                        "1HE"-> atomsMap13.put(it, 1)
                        "HE1"-> atomsMap13.put(it, 1)
                        "1H"-> atomsMap13.put(it, 2)
                        "1HT"-> atomsMap13.put(it, 2)
                        "H1"-> atomsMap13.put(it, 2)
                        "HT1"-> atomsMap13.put(it, 2)
                        "2HB"-> atomsMap13.put(it, 3)
                        "HB2"-> atomsMap13.put(it, 3)
                        "2HD"-> atomsMap13.put(it, 4)
                        "HD2"-> atomsMap13.put(it, 4)
                        "2H"-> atomsMap13.put(it, 5)
                        "2HT"-> atomsMap13.put(it, 5)
                        "H2"-> atomsMap13.put(it, 5)
                        "HT2"-> atomsMap13.put(it, 5)
                        "1HB"-> atomsMap13.put(it, 6)
                        "3HB"-> atomsMap13.put(it, 6)
                        "HB1"-> atomsMap13.put(it, 6)
                        "HB3"-> atomsMap13.put(it, 6)
                        "3H"-> atomsMap13.put(it, 7)
                        "3HT"-> atomsMap13.put(it, 7)
                        "H3"-> atomsMap13.put(it, 7)
                        "HT3"-> atomsMap13.put(it, 7)
                        "C"-> atomsMap13.put(it, 8)
                        "CA"-> atomsMap13.put(it, 9)
                        "CB"-> atomsMap13.put(it, 10)
                        "CD2"-> atomsMap13.put(it, 11)
                        "CE1"-> atomsMap13.put(it, 12)
                        "CG"-> atomsMap13.put(it, 13)
                        "H"-> atomsMap13.put(it, 14)
                        "HN"-> atomsMap13.put(it, 14)
                        "HA"-> atomsMap13.put(it, 15)
                        "HA2"-> atomsMap13.put(it, 15)
                        "N"-> atomsMap13.put(it, 16)
                        "ND1"-> atomsMap13.put(it, 17)
                        "NE2"-> atomsMap13.put(it, 18)
                        "O"-> atomsMap13.put(it, 19)
                        "O1"-> atomsMap13.put(it, 19)
                        "OT1"-> atomsMap13.put(it, 19)
                        "O2"-> atomsMap13.put(it, 20)
                        "OT"-> atomsMap13.put(it, 20)
                        "OT2"-> atomsMap13.put(it, 20)
                        "OXT"-> atomsMap13.put(it, 20)
                        else -> atomsMap13.put(it, -1)}
                    }



                    atomsMap13.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap13.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap13.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "HIE"-> { val atomsMap14 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HE"-> atomsMap14.put(it, 0)
                        "HE1"-> atomsMap14.put(it, 0)
                        "1H"-> atomsMap14.put(it, 1)
                        "1HT"-> atomsMap14.put(it, 1)
                        "H1"-> atomsMap14.put(it, 1)
                        "HT1"-> atomsMap14.put(it, 1)
                        "2HB"-> atomsMap14.put(it, 2)
                        "HB2"-> atomsMap14.put(it, 2)
                        "2HD"-> atomsMap14.put(it, 3)
                        "HD1"-> atomsMap14.put(it, 3)
                        "HD2"-> atomsMap14.put(it, 3)
                        "2HE"-> atomsMap14.put(it, 4)
                        "HE2"-> atomsMap14.put(it, 4)
                        "2H"-> atomsMap14.put(it, 5)
                        "2HT"-> atomsMap14.put(it, 5)
                        "H2"-> atomsMap14.put(it, 5)
                        "HT2"-> atomsMap14.put(it, 5)
                        "1HB"-> atomsMap14.put(it, 6)
                        "3HB"-> atomsMap14.put(it, 6)
                        "HB1"-> atomsMap14.put(it, 6)
                        "HB3"-> atomsMap14.put(it, 6)
                        "3H"-> atomsMap14.put(it, 7)
                        "3HT"-> atomsMap14.put(it, 7)
                        "H3"-> atomsMap14.put(it, 7)
                        "HT3"-> atomsMap14.put(it, 7)
                        "C"-> atomsMap14.put(it, 8)
                        "CA"-> atomsMap14.put(it, 9)
                        "CB"-> atomsMap14.put(it, 10)
                        "CD2"-> atomsMap14.put(it, 11)
                        "CE1"-> atomsMap14.put(it, 12)
                        "CG"-> atomsMap14.put(it, 13)
                        "H"-> atomsMap14.put(it, 14)
                        "HN"-> atomsMap14.put(it, 14)
                        "HA"-> atomsMap14.put(it, 15)
                        "HA2"-> atomsMap14.put(it, 15)
                        "N"-> atomsMap14.put(it, 16)
                        "ND1"-> atomsMap14.put(it, 17)
                        "NE2"-> atomsMap14.put(it, 18)
                        "O"-> atomsMap14.put(it, 19)
                        "O1"-> atomsMap14.put(it, 19)
                        "OT1"-> atomsMap14.put(it, 19)
                        "O2"-> atomsMap14.put(it, 20)
                        "OT"-> atomsMap14.put(it, 20)
                        "OT2"-> atomsMap14.put(it, 20)
                        "OXT"-> atomsMap14.put(it, 20)
                        else -> atomsMap14.put(it, -1)}
                    }



                    atomsMap14.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap14.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap14.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "HIP"-> { val atomsMap15 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HD"-> atomsMap15.put(it, 0)
                        "HD1"-> atomsMap15.put(it, 0)
                        "1HE"-> atomsMap15.put(it, 1)
                        "HE1"-> atomsMap15.put(it, 1)
                        "1H"-> atomsMap15.put(it, 2)
                        "1HT"-> atomsMap15.put(it, 2)
                        "H1"-> atomsMap15.put(it, 2)
                        "HT1"-> atomsMap15.put(it, 2)
                        "2HB"-> atomsMap15.put(it, 3)
                        "HB2"-> atomsMap15.put(it, 3)
                        "2HD"-> atomsMap15.put(it, 4)
                        "HD2"-> atomsMap15.put(it, 4)
                        "2HE"-> atomsMap15.put(it, 5)
                        "HE2"-> atomsMap15.put(it, 5)
                        "2H"-> atomsMap15.put(it, 6)
                        "2HT"-> atomsMap15.put(it, 6)
                        "H2"-> atomsMap15.put(it, 6)
                        "HT2"-> atomsMap15.put(it, 6)
                        "1HB"-> atomsMap15.put(it, 7)
                        "3HB"-> atomsMap15.put(it, 7)
                        "HB1"-> atomsMap15.put(it, 7)
                        "HB3"-> atomsMap15.put(it, 7)
                        "3H"-> atomsMap15.put(it, 8)
                        "3HT"-> atomsMap15.put(it, 8)
                        "H3"-> atomsMap15.put(it, 8)
                        "HT3"-> atomsMap15.put(it, 8)
                        "C"-> atomsMap15.put(it, 9)
                        "CA"-> atomsMap15.put(it, 10)
                        "CB"-> atomsMap15.put(it, 11)
                        "CD2"-> atomsMap15.put(it, 12)
                        "CE1"-> atomsMap15.put(it, 13)
                        "CG"-> atomsMap15.put(it, 14)
                        "H"-> atomsMap15.put(it, 15)
                        "HN"-> atomsMap15.put(it, 15)
                        "HA"-> atomsMap15.put(it, 16)
                        "HA2"-> atomsMap15.put(it, 16)
                        "N"-> atomsMap15.put(it, 17)
                        "ND1"-> atomsMap15.put(it, 18)
                        "NE2"-> atomsMap15.put(it, 19)
                        "O"-> atomsMap15.put(it, 20)
                        "O1"-> atomsMap15.put(it, 20)
                        "OT1"-> atomsMap15.put(it, 20)
                        "O2"-> atomsMap15.put(it, 21)
                        "OT"-> atomsMap15.put(it, 21)
                        "OT2"-> atomsMap15.put(it, 21)
                        "OXT"-> atomsMap15.put(it, 21)
                        else -> atomsMap15.put(it, -1)}
                    }



                    atomsMap15.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap15.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap15.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "ILE"-> { val atomsMap16 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HD1"-> atomsMap16.put(it, 0)
                        "HD1"-> atomsMap16.put(it, 0)
                        "HD11"-> atomsMap16.put(it, 0)
                        "1HG2"-> atomsMap16.put(it, 1)
                        "HG21"-> atomsMap16.put(it, 1)
                        "1H"-> atomsMap16.put(it, 2)
                        "1HT"-> atomsMap16.put(it, 2)
                        "H1"-> atomsMap16.put(it, 2)
                        "HT1"-> atomsMap16.put(it, 2)
                        "2HD1"-> atomsMap16.put(it, 3)
                        "HD12"-> atomsMap16.put(it, 3)
                        "HD2"-> atomsMap16.put(it, 3)
                        "2HG1"-> atomsMap16.put(it, 4)
                        "HG12"-> atomsMap16.put(it, 4)
                        "2HG2"-> atomsMap16.put(it, 5)
                        "HG22"-> atomsMap16.put(it, 5)
                        "2H"-> atomsMap16.put(it, 6)
                        "2HT"-> atomsMap16.put(it, 6)
                        "H2"-> atomsMap16.put(it, 6)
                        "HT2"-> atomsMap16.put(it, 6)
                        "3HD1"-> atomsMap16.put(it, 7)
                        "HD13"-> atomsMap16.put(it, 7)
                        "HD3"-> atomsMap16.put(it, 7)
                        "1HG1"-> atomsMap16.put(it, 8)
                        "3HG1"-> atomsMap16.put(it, 8)
                        "HG11"-> atomsMap16.put(it, 8)
                        "HG13"-> atomsMap16.put(it, 8)
                        "3HG2"-> atomsMap16.put(it, 9)
                        "HG23"-> atomsMap16.put(it, 9)
                        "3H"-> atomsMap16.put(it, 10)
                        "3HT"-> atomsMap16.put(it, 10)
                        "H3"-> atomsMap16.put(it, 10)
                        "HT3"-> atomsMap16.put(it, 10)
                        "C"-> atomsMap16.put(it, 11)
                        "CA"-> atomsMap16.put(it, 12)
                        "CB"-> atomsMap16.put(it, 13)
                        "CD"-> atomsMap16.put(it, 14)
                        "CD1"-> atomsMap16.put(it, 14)
                        "CG1"-> atomsMap16.put(it, 15)
                        "CG2"-> atomsMap16.put(it, 16)
                        "H"-> atomsMap16.put(it, 17)
                        "HN"-> atomsMap16.put(it, 17)
                        "HA"-> atomsMap16.put(it, 18)
                        "HA2"-> atomsMap16.put(it, 18)
                        "HB"-> atomsMap16.put(it, 19)
                        "N"-> atomsMap16.put(it, 20)
                        "O"-> atomsMap16.put(it, 21)
                        "O1"-> atomsMap16.put(it, 21)
                        "OT1"-> atomsMap16.put(it, 21)
                        "O2"-> atomsMap16.put(it, 22)
                        "OT"-> atomsMap16.put(it, 22)
                        "OT2"-> atomsMap16.put(it, 22)
                        "OXT"-> atomsMap16.put(it, 22)
                        else -> atomsMap16.put(it, -1)}
                    }



                    atomsMap16.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap16.filterValues { it ==17 }.forEach() {
                        val atom1 = it.key
                        atomsMap16.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "LEU"-> { val atomsMap17 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HD1"-> atomsMap17.put(it, 0)
                        "HD11"-> atomsMap17.put(it, 0)
                        "1HD2"-> atomsMap17.put(it, 1)
                        "HD21"-> atomsMap17.put(it, 1)
                        "1H"-> atomsMap17.put(it, 2)
                        "1HT"-> atomsMap17.put(it, 2)
                        "H1"-> atomsMap17.put(it, 2)
                        "HT1"-> atomsMap17.put(it, 2)
                        "2HB"-> atomsMap17.put(it, 3)
                        "HB2"-> atomsMap17.put(it, 3)
                        "2HD1"-> atomsMap17.put(it, 4)
                        "HD12"-> atomsMap17.put(it, 4)
                        "2HD2"-> atomsMap17.put(it, 5)
                        "HD22"-> atomsMap17.put(it, 5)
                        "2H"-> atomsMap17.put(it, 6)
                        "2HT"-> atomsMap17.put(it, 6)
                        "H2"-> atomsMap17.put(it, 6)
                        "HT2"-> atomsMap17.put(it, 6)
                        "1HB"-> atomsMap17.put(it, 7)
                        "3HB"-> atomsMap17.put(it, 7)
                        "HB1"-> atomsMap17.put(it, 7)
                        "HB3"-> atomsMap17.put(it, 7)
                        "3HD1"-> atomsMap17.put(it, 8)
                        "HD13"-> atomsMap17.put(it, 8)
                        "3HD2"-> atomsMap17.put(it, 9)
                        "HD23"-> atomsMap17.put(it, 9)
                        "3H"-> atomsMap17.put(it, 10)
                        "3HT"-> atomsMap17.put(it, 10)
                        "H3"-> atomsMap17.put(it, 10)
                        "HT3"-> atomsMap17.put(it, 10)
                        "C"-> atomsMap17.put(it, 11)
                        "CA"-> atomsMap17.put(it, 12)
                        "CB"-> atomsMap17.put(it, 13)
                        "CD1"-> atomsMap17.put(it, 14)
                        "CD2"-> atomsMap17.put(it, 15)
                        "CG"-> atomsMap17.put(it, 16)
                        "H"-> atomsMap17.put(it, 17)
                        "HN"-> atomsMap17.put(it, 17)
                        "HA"-> atomsMap17.put(it, 18)
                        "HA2"-> atomsMap17.put(it, 18)
                        "HG"-> atomsMap17.put(it, 19)
                        "N"-> atomsMap17.put(it, 20)
                        "O"-> atomsMap17.put(it, 21)
                        "O1"-> atomsMap17.put(it, 21)
                        "OT1"-> atomsMap17.put(it, 21)
                        "O2"-> atomsMap17.put(it, 22)
                        "OT"-> atomsMap17.put(it, 22)
                        "OT2"-> atomsMap17.put(it, 22)
                        "OXT"-> atomsMap17.put(it, 22)
                        else -> atomsMap17.put(it, -1)}
                    }



                    atomsMap17.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==16 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap17.filterValues { it ==17 }.forEach() {
                        val atom1 = it.key
                        atomsMap17.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "LYS"-> { val atomsMap18 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1H"-> atomsMap18.put(it, 0)
                        "1HT"-> atomsMap18.put(it, 0)
                        "H1"-> atomsMap18.put(it, 0)
                        "HT1"-> atomsMap18.put(it, 0)
                        "1HZ"-> atomsMap18.put(it, 1)
                        "HZ1"-> atomsMap18.put(it, 1)
                        "2HB"-> atomsMap18.put(it, 2)
                        "HB2"-> atomsMap18.put(it, 2)
                        "2HD"-> atomsMap18.put(it, 3)
                        "HD2"-> atomsMap18.put(it, 3)
                        "2HE"-> atomsMap18.put(it, 4)
                        "HE2"-> atomsMap18.put(it, 4)
                        "2HG"-> atomsMap18.put(it, 5)
                        "HG2"-> atomsMap18.put(it, 5)
                        "2H"-> atomsMap18.put(it, 6)
                        "2HT"-> atomsMap18.put(it, 6)
                        "H2"-> atomsMap18.put(it, 6)
                        "HT2"-> atomsMap18.put(it, 6)
                        "2HZ"-> atomsMap18.put(it, 7)
                        "HZ2"-> atomsMap18.put(it, 7)
                        "1HB"-> atomsMap18.put(it, 8)
                        "3HB"-> atomsMap18.put(it, 8)
                        "HB1"-> atomsMap18.put(it, 8)
                        "HB3"-> atomsMap18.put(it, 8)
                        "1HD"-> atomsMap18.put(it, 9)
                        "3HD"-> atomsMap18.put(it, 9)
                        "HD1"-> atomsMap18.put(it, 9)
                        "HD3"-> atomsMap18.put(it, 9)
                        "1HE"-> atomsMap18.put(it, 10)
                        "3HE"-> atomsMap18.put(it, 10)
                        "HE1"-> atomsMap18.put(it, 10)
                        "HE3"-> atomsMap18.put(it, 10)
                        "1HG"-> atomsMap18.put(it, 11)
                        "3HG"-> atomsMap18.put(it, 11)
                        "HG1"-> atomsMap18.put(it, 11)
                        "HG3"-> atomsMap18.put(it, 11)
                        "3H"-> atomsMap18.put(it, 12)
                        "3HT"-> atomsMap18.put(it, 12)
                        "H3"-> atomsMap18.put(it, 12)
                        "HT3"-> atomsMap18.put(it, 12)
                        "3HZ"-> atomsMap18.put(it, 13)
                        "HZ3"-> atomsMap18.put(it, 13)
                        "C"-> atomsMap18.put(it, 14)
                        "CA"-> atomsMap18.put(it, 15)
                        "CB"-> atomsMap18.put(it, 16)
                        "CD"-> atomsMap18.put(it, 17)
                        "CE"-> atomsMap18.put(it, 18)
                        "CG"-> atomsMap18.put(it, 19)
                        "H"-> atomsMap18.put(it, 20)
                        "HN"-> atomsMap18.put(it, 20)
                        "HA"-> atomsMap18.put(it, 21)
                        "HA2"-> atomsMap18.put(it, 21)
                        "N"-> atomsMap18.put(it, 22)
                        "NZ"-> atomsMap18.put(it, 23)
                        "O"-> atomsMap18.put(it, 24)
                        "O1"-> atomsMap18.put(it, 24)
                        "OT1"-> atomsMap18.put(it, 24)
                        "O2"-> atomsMap18.put(it, 25)
                        "OT"-> atomsMap18.put(it, 25)
                        "OT2"-> atomsMap18.put(it, 25)
                        "OXT"-> atomsMap18.put(it, 25)
                        else -> atomsMap18.put(it, -1)}
                    }



                    atomsMap18.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 23 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 23 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 23 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 24 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==16 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==17 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==17 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==18 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 23 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap18.filterValues { it ==20 }.forEach() {
                        val atom1 = it.key
                        atomsMap18.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "MET"-> { val atomsMap19 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HE"-> atomsMap19.put(it, 0)
                        "HE1"-> atomsMap19.put(it, 0)
                        "1H"-> atomsMap19.put(it, 1)
                        "1HT"-> atomsMap19.put(it, 1)
                        "H1"-> atomsMap19.put(it, 1)
                        "HT1"-> atomsMap19.put(it, 1)
                        "2HB"-> atomsMap19.put(it, 2)
                        "HB2"-> atomsMap19.put(it, 2)
                        "2HE"-> atomsMap19.put(it, 3)
                        "HE2"-> atomsMap19.put(it, 3)
                        "2HG"-> atomsMap19.put(it, 4)
                        "HG2"-> atomsMap19.put(it, 4)
                        "2H"-> atomsMap19.put(it, 5)
                        "2HT"-> atomsMap19.put(it, 5)
                        "H2"-> atomsMap19.put(it, 5)
                        "HT2"-> atomsMap19.put(it, 5)
                        "1HB"-> atomsMap19.put(it, 6)
                        "3HB"-> atomsMap19.put(it, 6)
                        "HB1"-> atomsMap19.put(it, 6)
                        "HB3"-> atomsMap19.put(it, 6)
                        "3HE"-> atomsMap19.put(it, 7)
                        "HE3"-> atomsMap19.put(it, 7)
                        "1HG"-> atomsMap19.put(it, 8)
                        "3HG"-> atomsMap19.put(it, 8)
                        "HG1"-> atomsMap19.put(it, 8)
                        "HG3"-> atomsMap19.put(it, 8)
                        "3H"-> atomsMap19.put(it, 9)
                        "3HT"-> atomsMap19.put(it, 9)
                        "H3"-> atomsMap19.put(it, 9)
                        "HT3"-> atomsMap19.put(it, 9)
                        "C"-> atomsMap19.put(it, 10)
                        "CA"-> atomsMap19.put(it, 11)
                        "CB"-> atomsMap19.put(it, 12)
                        "CE"-> atomsMap19.put(it, 13)
                        "CG"-> atomsMap19.put(it, 14)
                        "H"-> atomsMap19.put(it, 15)
                        "HN"-> atomsMap19.put(it, 15)
                        "HA"-> atomsMap19.put(it, 16)
                        "HA2"-> atomsMap19.put(it, 16)
                        "N"-> atomsMap19.put(it, 17)
                        "O"-> atomsMap19.put(it, 18)
                        "O1"-> atomsMap19.put(it, 18)
                        "OT1"-> atomsMap19.put(it, 18)
                        "O2"-> atomsMap19.put(it, 19)
                        "OT"-> atomsMap19.put(it, 19)
                        "OT2"-> atomsMap19.put(it, 19)
                        "OXT"-> atomsMap19.put(it, 19)
                        "SD"-> atomsMap19.put(it, 20)
                        else -> atomsMap19.put(it, -1)}
                    }



                    atomsMap19.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap19.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap19.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "NME"-> { val atomsMap20 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HA"-> atomsMap20.put(it, 0)
                        "1HH3"-> atomsMap20.put(it, 0)
                        "H02"-> atomsMap20.put(it, 0)
                        "HH31"-> atomsMap20.put(it, 0)
                        "2HA"-> atomsMap20.put(it, 1)
                        "2HH3"-> atomsMap20.put(it, 1)
                        "H03"-> atomsMap20.put(it, 1)
                        "HH32"-> atomsMap20.put(it, 1)
                        "3HA"-> atomsMap20.put(it, 2)
                        "3HH3"-> atomsMap20.put(it, 2)
                        "H04"-> atomsMap20.put(it, 2)
                        "HH33"-> atomsMap20.put(it, 2)
                        "C01"-> atomsMap20.put(it, 3)
                        "CA"-> atomsMap20.put(it, 3)
                        "CH3"-> atomsMap20.put(it, 3)
                        "H"-> atomsMap20.put(it, 4)
                        "H01"-> atomsMap20.put(it, 4)
                        "HN"-> atomsMap20.put(it, 4)
                        "N"-> atomsMap20.put(it, 5)
                        "N01"-> atomsMap20.put(it, 5)
                        else -> atomsMap20.put(it, -1)}
                    }



                    atomsMap20.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap20.filterValues { it == 3 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap20.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap20.filterValues { it == 3 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap20.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap20.filterValues { it == 3 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap20.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap20.filterValues { it == 5 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap20.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap20.filterValues { it == 5 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "PHE"-> { val atomsMap21 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HD"-> atomsMap21.put(it, 0)
                        "HD1"-> atomsMap21.put(it, 0)
                        "1HE"-> atomsMap21.put(it, 1)
                        "HE1"-> atomsMap21.put(it, 1)
                        "1H"-> atomsMap21.put(it, 2)
                        "1HT"-> atomsMap21.put(it, 2)
                        "H1"-> atomsMap21.put(it, 2)
                        "HT1"-> atomsMap21.put(it, 2)
                        "2HB"-> atomsMap21.put(it, 3)
                        "HB2"-> atomsMap21.put(it, 3)
                        "2HD"-> atomsMap21.put(it, 4)
                        "HD2"-> atomsMap21.put(it, 4)
                        "2HE"-> atomsMap21.put(it, 5)
                        "HE2"-> atomsMap21.put(it, 5)
                        "2H"-> atomsMap21.put(it, 6)
                        "2HT"-> atomsMap21.put(it, 6)
                        "H2"-> atomsMap21.put(it, 6)
                        "HT2"-> atomsMap21.put(it, 6)
                        "1HB"-> atomsMap21.put(it, 7)
                        "3HB"-> atomsMap21.put(it, 7)
                        "HB1"-> atomsMap21.put(it, 7)
                        "HB3"-> atomsMap21.put(it, 7)
                        "3H"-> atomsMap21.put(it, 8)
                        "3HT"-> atomsMap21.put(it, 8)
                        "H3"-> atomsMap21.put(it, 8)
                        "HT3"-> atomsMap21.put(it, 8)
                        "C"-> atomsMap21.put(it, 9)
                        "CA"-> atomsMap21.put(it, 10)
                        "CB"-> atomsMap21.put(it, 11)
                        "CD1"-> atomsMap21.put(it, 12)
                        "CD2"-> atomsMap21.put(it, 13)
                        "CE1"-> atomsMap21.put(it, 14)
                        "CE2"-> atomsMap21.put(it, 15)
                        "CG"-> atomsMap21.put(it, 16)
                        "CZ"-> atomsMap21.put(it, 17)
                        "H"-> atomsMap21.put(it, 18)
                        "HN"-> atomsMap21.put(it, 18)
                        "HA"-> atomsMap21.put(it, 19)
                        "HA2"-> atomsMap21.put(it, 19)
                        "HZ"-> atomsMap21.put(it, 20)
                        "N"-> atomsMap21.put(it, 21)
                        "O"-> atomsMap21.put(it, 22)
                        "O1"-> atomsMap21.put(it, 22)
                        "OT1"-> atomsMap21.put(it, 22)
                        "O2"-> atomsMap21.put(it, 23)
                        "OT"-> atomsMap21.put(it, 23)
                        "OT2"-> atomsMap21.put(it, 23)
                        "OXT"-> atomsMap21.put(it, 23)
                        else -> atomsMap21.put(it, -1)}
                    }



                    atomsMap21.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==17 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap21.filterValues { it ==18 }.forEach() {
                        val atom1 = it.key
                        atomsMap21.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "PRO"-> { val atomsMap22 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "2H"-> atomsMap22.put(it, 0)
                        "H2"-> atomsMap22.put(it, 0)
                        "2HB"-> atomsMap22.put(it, 1)
                        "HB2"-> atomsMap22.put(it, 1)
                        "2HD"-> atomsMap22.put(it, 2)
                        "HD2"-> atomsMap22.put(it, 2)
                        "2HG"-> atomsMap22.put(it, 3)
                        "HG2"-> atomsMap22.put(it, 3)
                        "1H"-> atomsMap22.put(it, 4)
                        "3H"-> atomsMap22.put(it, 4)
                        "H3"-> atomsMap22.put(it, 4)
                        "1HB"-> atomsMap22.put(it, 5)
                        "3HB"-> atomsMap22.put(it, 5)
                        "HB1"-> atomsMap22.put(it, 5)
                        "HB3"-> atomsMap22.put(it, 5)
                        "1HD"-> atomsMap22.put(it, 6)
                        "3HD"-> atomsMap22.put(it, 6)
                        "HD1"-> atomsMap22.put(it, 6)
                        "HD3"-> atomsMap22.put(it, 6)
                        "1HG"-> atomsMap22.put(it, 7)
                        "3HG"-> atomsMap22.put(it, 7)
                        "HG1"-> atomsMap22.put(it, 7)
                        "HG3"-> atomsMap22.put(it, 7)
                        "C"-> atomsMap22.put(it, 8)
                        "CA"-> atomsMap22.put(it, 9)
                        "CB"-> atomsMap22.put(it, 10)
                        "CD"-> atomsMap22.put(it, 11)
                        "CG"-> atomsMap22.put(it, 12)
                        "HA"-> atomsMap22.put(it, 13)
                        "N"-> atomsMap22.put(it, 14)
                        "O"-> atomsMap22.put(it, 15)
                        "O1"-> atomsMap22.put(it, 15)
                        "OT1"-> atomsMap22.put(it, 15)
                        "O2"-> atomsMap22.put(it, 16)
                        "OT"-> atomsMap22.put(it, 16)
                        "OT2"-> atomsMap22.put(it, 16)
                        "OXT"-> atomsMap22.put(it, 16)
                        else -> atomsMap22.put(it, -1)}
                    }



                    atomsMap22.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap22.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap22.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "SER"-> { val atomsMap23 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1H"-> atomsMap23.put(it, 0)
                        "1HT"-> atomsMap23.put(it, 0)
                        "H1"-> atomsMap23.put(it, 0)
                        "HT1"-> atomsMap23.put(it, 0)
                        "2HB"-> atomsMap23.put(it, 1)
                        "HB2"-> atomsMap23.put(it, 1)
                        "2H"-> atomsMap23.put(it, 2)
                        "2HT"-> atomsMap23.put(it, 2)
                        "H2"-> atomsMap23.put(it, 2)
                        "HT2"-> atomsMap23.put(it, 2)
                        "1HB"-> atomsMap23.put(it, 3)
                        "3HB"-> atomsMap23.put(it, 3)
                        "HB1"-> atomsMap23.put(it, 3)
                        "HB3"-> atomsMap23.put(it, 3)
                        "3H"-> atomsMap23.put(it, 4)
                        "3HT"-> atomsMap23.put(it, 4)
                        "H3"-> atomsMap23.put(it, 4)
                        "HT3"-> atomsMap23.put(it, 4)
                        "C"-> atomsMap23.put(it, 5)
                        "CA"-> atomsMap23.put(it, 6)
                        "CB"-> atomsMap23.put(it, 7)
                        "H"-> atomsMap23.put(it, 8)
                        "HN"-> atomsMap23.put(it, 8)
                        "HA"-> atomsMap23.put(it, 9)
                        "HA2"-> atomsMap23.put(it, 9)
                        "HG"-> atomsMap23.put(it, 10)
                        "HG1"-> atomsMap23.put(it, 10)
                        "N"-> atomsMap23.put(it, 11)
                        "O"-> atomsMap23.put(it, 12)
                        "O1"-> atomsMap23.put(it, 12)
                        "OT1"-> atomsMap23.put(it, 12)
                        "OG"-> atomsMap23.put(it, 13)
                        "O2"-> atomsMap23.put(it, 14)
                        "OT"-> atomsMap23.put(it, 14)
                        "OT2"-> atomsMap23.put(it, 14)
                        "OXT"-> atomsMap23.put(it, 14)
                        else -> atomsMap23.put(it, -1)}
                    }



                    atomsMap23.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 6 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 7 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap23.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap23.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "THR"-> { val atomsMap24 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HG"-> atomsMap24.put(it, 0)
                        "HG1"-> atomsMap24.put(it, 0)
                        "1HG2"-> atomsMap24.put(it, 1)
                        "HG21"-> atomsMap24.put(it, 1)
                        "1H"-> atomsMap24.put(it, 2)
                        "1HT"-> atomsMap24.put(it, 2)
                        "H1"-> atomsMap24.put(it, 2)
                        "HT1"-> atomsMap24.put(it, 2)
                        "2HG2"-> atomsMap24.put(it, 3)
                        "HG22"-> atomsMap24.put(it, 3)
                        "2H"-> atomsMap24.put(it, 4)
                        "2HT"-> atomsMap24.put(it, 4)
                        "H2"-> atomsMap24.put(it, 4)
                        "HT2"-> atomsMap24.put(it, 4)
                        "3HG2"-> atomsMap24.put(it, 5)
                        "HG23"-> atomsMap24.put(it, 5)
                        "3H"-> atomsMap24.put(it, 6)
                        "3HT"-> atomsMap24.put(it, 6)
                        "H3"-> atomsMap24.put(it, 6)
                        "HT3"-> atomsMap24.put(it, 6)
                        "C"-> atomsMap24.put(it, 7)
                        "CA"-> atomsMap24.put(it, 8)
                        "CB"-> atomsMap24.put(it, 9)
                        "CG2"-> atomsMap24.put(it, 10)
                        "H"-> atomsMap24.put(it, 11)
                        "HN"-> atomsMap24.put(it, 11)
                        "HA"-> atomsMap24.put(it, 12)
                        "HA2"-> atomsMap24.put(it, 12)
                        "HB"-> atomsMap24.put(it, 13)
                        "N"-> atomsMap24.put(it, 14)
                        "O"-> atomsMap24.put(it, 15)
                        "O1"-> atomsMap24.put(it, 15)
                        "OT1"-> atomsMap24.put(it, 15)
                        "OG1"-> atomsMap24.put(it, 16)
                        "O2"-> atomsMap24.put(it, 17)
                        "OT"-> atomsMap24.put(it, 17)
                        "OT2"-> atomsMap24.put(it, 17)
                        "OXT"-> atomsMap24.put(it, 17)
                        else -> atomsMap24.put(it, -1)}
                    }



                    atomsMap24.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 8 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 9 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap24.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap24.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "TRP"-> { val atomsMap25 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HD"-> atomsMap25.put(it, 0)
                        "HD1"-> atomsMap25.put(it, 0)
                        "1HE"-> atomsMap25.put(it, 1)
                        "HE1"-> atomsMap25.put(it, 1)
                        "1H"-> atomsMap25.put(it, 2)
                        "1HT"-> atomsMap25.put(it, 2)
                        "H1"-> atomsMap25.put(it, 2)
                        "HT1"-> atomsMap25.put(it, 2)
                        "2HB"-> atomsMap25.put(it, 3)
                        "HB2"-> atomsMap25.put(it, 3)
                        "2HH"-> atomsMap25.put(it, 4)
                        "HH2"-> atomsMap25.put(it, 4)
                        "2H"-> atomsMap25.put(it, 5)
                        "2HT"-> atomsMap25.put(it, 5)
                        "H2"-> atomsMap25.put(it, 5)
                        "HT2"-> atomsMap25.put(it, 5)
                        "2HZ"-> atomsMap25.put(it, 6)
                        "HZ2"-> atomsMap25.put(it, 6)
                        "1HB"-> atomsMap25.put(it, 7)
                        "3HB"-> atomsMap25.put(it, 7)
                        "HB1"-> atomsMap25.put(it, 7)
                        "HB3"-> atomsMap25.put(it, 7)
                        "3HE"-> atomsMap25.put(it, 8)
                        "HE3"-> atomsMap25.put(it, 8)
                        "3H"-> atomsMap25.put(it, 9)
                        "3HT"-> atomsMap25.put(it, 9)
                        "H3"-> atomsMap25.put(it, 9)
                        "HT3"-> atomsMap25.put(it, 9)
                        "1HZ"-> atomsMap25.put(it, 10)
                        "3HZ"-> atomsMap25.put(it, 10)
                        "HZ3"-> atomsMap25.put(it, 10)
                        "C"-> atomsMap25.put(it, 11)
                        "CA"-> atomsMap25.put(it, 12)
                        "CB"-> atomsMap25.put(it, 13)
                        "CD1"-> atomsMap25.put(it, 14)
                        "CD2"-> atomsMap25.put(it, 15)
                        "CE2"-> atomsMap25.put(it, 16)
                        "CE3"-> atomsMap25.put(it, 17)
                        "CG"-> atomsMap25.put(it, 18)
                        "CH2"-> atomsMap25.put(it, 19)
                        "CZ2"-> atomsMap25.put(it, 20)
                        "CZ3"-> atomsMap25.put(it, 21)
                        "H"-> atomsMap25.put(it, 22)
                        "HN"-> atomsMap25.put(it, 22)
                        "HA"-> atomsMap25.put(it, 23)
                        "HA2"-> atomsMap25.put(it, 23)
                        "N"-> atomsMap25.put(it, 24)
                        "NE1"-> atomsMap25.put(it, 25)
                        "O"-> atomsMap25.put(it, 26)
                        "O1"-> atomsMap25.put(it, 26)
                        "OT1"-> atomsMap25.put(it, 26)
                        "O2"-> atomsMap25.put(it, 27)
                        "OT"-> atomsMap25.put(it, 27)
                        "OT2"-> atomsMap25.put(it, 27)
                        "OXT"-> atomsMap25.put(it, 27)
                        else -> atomsMap25.put(it, -1)}
                    }



                    atomsMap25.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 25 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 24 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 24 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 24 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 26 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 23 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 24 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 25 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==16 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==16 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 25 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==17 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==19 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 20 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==19 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap25.filterValues { it ==22 }.forEach() {
                        val atom1 = it.key
                        atomsMap25.filterValues { it == 24 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "TYR"-> { val atomsMap26 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HD"-> atomsMap26.put(it, 0)
                        "HD1"-> atomsMap26.put(it, 0)
                        "1HE"-> atomsMap26.put(it, 1)
                        "HE1"-> atomsMap26.put(it, 1)
                        "1H"-> atomsMap26.put(it, 2)
                        "1HT"-> atomsMap26.put(it, 2)
                        "H1"-> atomsMap26.put(it, 2)
                        "HT1"-> atomsMap26.put(it, 2)
                        "2HB"-> atomsMap26.put(it, 3)
                        "HB2"-> atomsMap26.put(it, 3)
                        "2HD"-> atomsMap26.put(it, 4)
                        "HD2"-> atomsMap26.put(it, 4)
                        "2HE"-> atomsMap26.put(it, 5)
                        "HE2"-> atomsMap26.put(it, 5)
                        "2H"-> atomsMap26.put(it, 6)
                        "2HT"-> atomsMap26.put(it, 6)
                        "H2"-> atomsMap26.put(it, 6)
                        "HT2"-> atomsMap26.put(it, 6)
                        "1HB"-> atomsMap26.put(it, 7)
                        "3HB"-> atomsMap26.put(it, 7)
                        "HB1"-> atomsMap26.put(it, 7)
                        "HB3"-> atomsMap26.put(it, 7)
                        "3H"-> atomsMap26.put(it, 8)
                        "3HT"-> atomsMap26.put(it, 8)
                        "H3"-> atomsMap26.put(it, 8)
                        "HT3"-> atomsMap26.put(it, 8)
                        "C"-> atomsMap26.put(it, 9)
                        "CA"-> atomsMap26.put(it, 10)
                        "CB"-> atomsMap26.put(it, 11)
                        "CD1"-> atomsMap26.put(it, 12)
                        "CD2"-> atomsMap26.put(it, 13)
                        "CE1"-> atomsMap26.put(it, 14)
                        "CE2"-> atomsMap26.put(it, 15)
                        "CG"-> atomsMap26.put(it, 16)
                        "CZ"-> atomsMap26.put(it, 17)
                        "H"-> atomsMap26.put(it, 18)
                        "HN"-> atomsMap26.put(it, 18)
                        "HA"-> atomsMap26.put(it, 19)
                        "HA2"-> atomsMap26.put(it, 19)
                        "HH"-> atomsMap26.put(it, 20)
                        "N"-> atomsMap26.put(it, 21)
                        "O"-> atomsMap26.put(it, 22)
                        "O1"-> atomsMap26.put(it, 22)
                        "OT1"-> atomsMap26.put(it, 22)
                        "OH"-> atomsMap26.put(it, 23)
                        "O2"-> atomsMap26.put(it, 24)
                        "OT"-> atomsMap26.put(it, 24)
                        "OT2"-> atomsMap26.put(it, 24)
                        "OXT"-> atomsMap26.put(it, 24)
                        else -> atomsMap26.put(it, -1)}
                    }



                    atomsMap26.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 22 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 19 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 14 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==12 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==13 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==15 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==17 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 23 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==18 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 21 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap26.filterValues { it ==20 }.forEach() {
                        val atom1 = it.key
                        atomsMap26.filterValues { it == 23 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }
                "VAL"-> { val atomsMap27 = HashMap<Atom, Int>()
                    it.atoms.forEach()
                    {when (it.name) {
                        "1HG1"-> atomsMap27.put(it, 0)
                        "HG11"-> atomsMap27.put(it, 0)
                        "1HG2"-> atomsMap27.put(it, 1)
                        "HG21"-> atomsMap27.put(it, 1)
                        "1H"-> atomsMap27.put(it, 2)
                        "1HT"-> atomsMap27.put(it, 2)
                        "H1"-> atomsMap27.put(it, 2)
                        "HT1"-> atomsMap27.put(it, 2)
                        "2HG1"-> atomsMap27.put(it, 3)
                        "HG12"-> atomsMap27.put(it, 3)
                        "2HG2"-> atomsMap27.put(it, 4)
                        "HG22"-> atomsMap27.put(it, 4)
                        "2H"-> atomsMap27.put(it, 5)
                        "2HT"-> atomsMap27.put(it, 5)
                        "H2"-> atomsMap27.put(it, 5)
                        "HT2"-> atomsMap27.put(it, 5)
                        "3HG1"-> atomsMap27.put(it, 6)
                        "HG13"-> atomsMap27.put(it, 6)
                        "3HG2"-> atomsMap27.put(it, 7)
                        "HG23"-> atomsMap27.put(it, 7)
                        "3H"-> atomsMap27.put(it, 8)
                        "3HT"-> atomsMap27.put(it, 8)
                        "H3"-> atomsMap27.put(it, 8)
                        "HT3"-> atomsMap27.put(it, 8)
                        "C"-> atomsMap27.put(it, 9)
                        "CA"-> atomsMap27.put(it, 10)
                        "CB"-> atomsMap27.put(it, 11)
                        "CG1"-> atomsMap27.put(it, 12)
                        "CG2"-> atomsMap27.put(it, 13)
                        "H"-> atomsMap27.put(it, 14)
                        "HN"-> atomsMap27.put(it, 14)
                        "HA"-> atomsMap27.put(it, 15)
                        "HA2"-> atomsMap27.put(it, 15)
                        "HB"-> atomsMap27.put(it, 16)
                        "N"-> atomsMap27.put(it, 17)
                        "O"-> atomsMap27.put(it, 18)
                        "O1"-> atomsMap27.put(it, 18)
                        "OT1"-> atomsMap27.put(it, 18)
                        "O2"-> atomsMap27.put(it, 19)
                        "OT"-> atomsMap27.put(it, 19)
                        "OT2"-> atomsMap27.put(it, 19)
                        "OXT"-> atomsMap27.put(it, 19)
                        else -> atomsMap27.put(it, -1)}
                    }



                    atomsMap27.filterValues { it ==0 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==1 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==2 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==3 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==4 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==5 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==6 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==7 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==8 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 10 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==9 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 18 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,2)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 11 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 15 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==10 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 12 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 13 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==11 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 16 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                    atomsMap27.filterValues { it ==14 }.forEach() {
                        val atom1 = it.key
                        atomsMap27.filterValues { it == 17 }.forEach {
                            val atom2 = it.key
                            val bond = BondImpl(atom1, atom2,1)
                            bonds.add(bond)
                        }     }
                }

            }
        }

     */
        return bonds
    }


}
