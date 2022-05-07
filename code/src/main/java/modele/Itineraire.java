package modele;

import java.io.IOException;
import java.util.*;

public class Itineraire {

    private static Villes villes;
    private Graphes graphe;
    public HashMap<String, ArrayList> listeAdj;
    private ArrayList<String> sources;
    private ArrayList<ArrayList<String>> itineraire;
    static ArrayList<ArrayList<String>> chAllPath;

    public Itineraire(Scenario scenario) throws IOException {
        villes = new Villes();
        graphe = new Graphes(scenario);
        listeAdj = new HashMap<>(graphe.getListeAdj());
        itineraire = new ArrayList<>();
        chAllPath = new ArrayList<>();
        rechercheItineraire();
        System.out.println(itineraire);
        setAllItineraire();
        System.out.println(this.listeAdj);
    }

    public ArrayList<ArrayList<String>> rechercheItineraire() {
        /* Itinéraire generale ou les possibilitées de choix entre 2 villes sont dans des listes*/
        // itineraire.add(sources);
        //sources = this.supprSource(sources);
        sources = graphe.setSource(); //premiere source
        while (listeAdj.size() != 0) { //recherche des autres  autre source
            for (String elem : listeAdj.keySet()) {
                if (listeAdj.get(elem).size() == 0) {
                    sources.add(elem);
                }
            }
            if (sources.size() > 0) {
                itineraire.add(sources);
                sources = this.supprSource(sources);
            }
        }
        // this.itineraire = itineraire;
        String v1 = "PrésidentDébut";
        String v2 = "PrésidentFin";
        ArrayList<String> l1 = new ArrayList<>();
        l1.add(v1);
        ArrayList<String> l2 = new ArrayList<>();
        l2.add(v2);
        if (!itineraire.contains(l1)) {
            itineraire.add(0, l1);
        }
        if (!itineraire.contains(l2)) {
            itineraire.add(itineraire.size(), l2);
        }
       // System.out.println(itineraire);
        return itineraire;
    }
    public boolean conflitSource(ArrayList<String> sources, ArrayList<ArrayList<String>> itineraire) {
        HashMap<String, ArrayList> listAjdOriginal = new HashMap<>(graphe.getListeAdj());
        for (String currentSource : sources) {
            for (String previousSource : itineraire.get(itineraire.size() - 1)) {
                if (! graphe.setSource().contains(previousSource) && ! listAjdOriginal.get(previousSource).contains(currentSource))
                    return true;
            }
        }
        return false;
    }

    public ArrayList<String> supprSource(ArrayList<String> sources) {

        ArrayList<String> sourcetmp = new ArrayList<>();
       /* for (String elem : listeAdj.keySet()) {
            if (listeAdj.get(elem).size() == 0) {
                sourcetmp.add(elem);
            }
        }*/
        for (String s : sources) { // enleve les source de la liste d'adjacence
            if (listeAdj.containsKey(s)) {
                listeAdj.remove(s);
            }
        }
        for (String elem : listeAdj.keySet()) {
            for (String s : sources) {
                if (listeAdj.get(elem).contains(s)) // enleve la/les source des autres sommets {
                    listeAdj.get(elem).remove(s);
                }
            }


        return new ArrayList<>();
    }

    public ArrayList<String> getSources() {
        return sources;
    }

    public TreeMap<String, ArrayList<String>> itineraireToListAdj() {
        TreeMap<String, ArrayList<String>> itineraireGen = new TreeMap<>();
        HashMap<String, ArrayList<String>> sameLevel = listSameLevel();

        for (int i = 0; i < itineraire.size() - 1; i++) {
            if (itineraire.get(i).size() == 1) {
                itineraireGen.put(itineraire.get(i).get(0), itineraire.get(i + 1));
            } else {
                for (String s : itineraire.get(i)) {
                    itineraireGen.put(s, itineraire.get(i + 1));
                }
            }
        }
        for (String e : itineraire.get(itineraire.size() - 1)) {
            itineraireGen.put(e, new ArrayList<>());
        }

        // ajout de ceux de meme niveau
    /*    for(String s : sameLevel.keySet()){
            System.out.println(itineraireGen.get(s));
            System.out.println(sameLevel.get(s));
            ArrayList<String> r =  new ArrayList<>( itineraireGen.get(s));
            r.addAll(sameLevel.get(s));
            itineraireGen.put(s, r) ;*/

        //System.out.println(itineraireGen);
        //System.out.println(itineraire);
        return itineraireGen;
        //Graphes g = Graphes();
    }

    public void allItineraire(TreeMap<String, ArrayList<String>> itineraireGen) {
        itineraireGen = itineraireToListAdj();
        ArrayList<ArrayList<String>> r = new ArrayList<>();
        for (ArrayList<String> s : itineraire) {
            ArrayList<String> liste = new ArrayList<>();
            for (String elem : s) {
                liste.add(s.get(0));
            }
            r.add(boucle1(itineraireGen, liste, itineraireGen.get(s)));
        }
       // System.out.println(r);

    }

    public ArrayList<String> boucle1(TreeMap<String, ArrayList<String>> listAjd2, ArrayList<String> liste, ArrayList<String> listProchain) {

        if (listAjd2.get(listProchain).size() == 0) {
            return liste;
        }
        if (listProchain.size() == 1) {
            liste.add(listProchain.get(0));
        }
        for (String elem : listProchain) {
            boucle2(listAjd2, liste, elem);
        }

        return liste;
    }

    public ArrayList<String> boucle2(TreeMap<String, ArrayList<String>> listAjd2, ArrayList<String> liste, String prochain) {

        liste.add(prochain);
        if (listAjd2.get(prochain).size() == 0) {
            return liste;
        }
        return liste;
    }


    public void allItineraire() {
        TreeMap<String, ArrayList<String>> itineraireGen = itineraireToListAdj();
        ArrayList<ArrayList<String>> listeP = new ArrayList<>();
        int nbChemins = 1;
        for (ArrayList<String> s : itineraire) {
            nbChemins *= s.size();
        }
        for (int i = 0; i < nbChemins; i++) {
            ArrayList<String> l = new ArrayList<>();
            listeP.add(chemin(l, itineraireGen, nbChemins, listeP, i));
            //System.out.println(listeP);
           // System.out.println(i);


        }
    }

    public ArrayList<String> chemin(ArrayList<String> l, TreeMap<String, ArrayList<String>> itineraireGen, int nbChemins,
                                    ArrayList<ArrayList<String>> listeP, int i) {
        for (ArrayList<String> s : itineraire) {
            if (s.size() == 1) {
                l.add(s.get(0));
            } else if (s.size() > 1) {
                for (String s1 : s) {
                    chemin2(itineraireGen, s1, i, listeP);
                }
                l.add(s.get(0));
            }
        }
        return l;
    }

    public void chemin2(TreeMap<String, ArrayList<String>> itineraireGen, String s, int i, ArrayList<ArrayList<String>> listeP) {
        listeP.get(i).add(s);
        //(restdeDuChemin(itineraireGen.get(s),listeP,i));
    }

    public void setAllItineraire() {
        System.out.println(itineraire);
        TreeMap<String, Boolean> visite = new TreeMap<>();
        ArrayList<ArrayList<String>> allPath = new ArrayList<>();
        ArrayList<String> currentPath = new ArrayList<>();
        TreeMap<String, ArrayList<String>> itineraireGen = itineraireToListAdj();
        String source;
        source = itineraire.get(0).get(0);
        currentPath.add(source);
        dfs(itineraireGen, source, visite, currentPath, listSameLevel());
    }

    // que 2 permutations sur 3; tout recommencer !
    // https://www.baeldung.com/cs/simple-paths-between-two-vertices
    // https://www.geeksforgeeks.org/find-paths-given-source-destination/?ref=lbp
    private void dfs(TreeMap<String, ArrayList<String>> itineraireGen, String source, TreeMap<String,
            Boolean> visite , ArrayList<String> currentPath, HashMap<String, ArrayList<String>> sameLevel) {
        boolean montee = true;
        visite.put(source,true);
       // System.out.println(source + " dddd");
        if (itineraireGen.get(source).size()==0 && !chAllPath.contains(currentPath)) {
            //if(itineraire.size() == currentPath.size()){
            ArrayList<String> pathAjoute = new ArrayList<>(currentPath); // copie car sinon problème.
            chAllPath.add(pathAjoute);
            //System.out.println(("\n"));
           // System.out.println(pathAjoute);
        }
        if (sameLevel.containsKey(source)) {
            Integer i;
            i=0;
            ArrayList<String> reste = this.tousPresent(currentPath, source, sameLevel);
          //  while(  reste.size() != 0){
                for ( String prochain : sameLevel.get(source) ) {
                    if (!visite.containsKey(prochain) || !visite.get(prochain) == true) {

                        source = prochain;
                        if (!currentPath.contains(prochain)) {
                            currentPath.add(source);
                            visite.put(source, true);

                            dfs(itineraireGen, source, visite, currentPath, sameLevel);
                            currentPath.remove(prochain);
                            visite.put(prochain, false);
                            montee = false;

                            //dfs(itineraireGen, source, visite, currentPath, sameLevel);
                        }
                    }
                }
              if (montee){
                    for (String s : sameLevel.get(source)) {

                        //currentPath.add(s);
                        source = s;
                        for (String l : itineraireGen.get(source)) {
                           // System.out.println(source);
                            //System.out.println(l);
                            if (!visite.containsKey(l) || ! visite.get(l)==true) {
                                if (!currentPath.contains(l)) {
                                    currentPath.add(l);

                                    source = l;
                                    visite.put(source, true);
                                    dfs(itineraireGen, source, visite, currentPath, sameLevel);
                                    currentPath.remove(l);
                                    montee = false;
                                    visite.put(l, false);
                                    // dfs(itineraireGen, source, visite, currentPath, sameLevel);
                                }
                            }
                        }
                    }
                }

        }
        else {
            sameLevel = listSameLevel();
            for (String l : itineraireGen.get(source)){
                if ( ! visite.containsKey(l ) || ! visite.get(l)==true ){
                    if (! currentPath.contains(l)) {
                        currentPath.add(l);

                        source = l;
                        visite.put(source, true);
                        dfs(itineraireGen, source, visite, currentPath, sameLevel);
                        currentPath.remove(l);
                        visite.put(source, false);
                       // dfs(itineraireGen, source, visite, currentPath, sameLevel);


                    }
                }
            }
        }
       visite.put(source,false);
    }
    public String allItineraireToString() {

        String r = new String();
        for (ArrayList<String> l : chAllPath) {
            r += l.toString() + "\n";
        }
        return r + "\n" + String.format("en tout %s chemins possibles", chAllPath.size());
    }

    public ArrayList<ArrayList<String>> getAllPath() {
        return chAllPath;
    }
    public HashMap<String, ArrayList<String>> listSameLevel() {
        HashMap<String, ArrayList<String>> r = new HashMap<>();
        for (ArrayList a : itineraire) {
            for (Object s1 : a) {
                for (Object s2 : a) {
                    if (s1 != s2) {
                        if (r.containsKey((String) s1)){// && listeAdj.get(s1)) {
                            r.get((String) s1).add((String) s2);
                        } else {
                            r.put((String) s1, new ArrayList<>());
                            r.get((String) s1).add((String) s2);
                        }
                    }
                }
            }
        }
        return r;
    }

    public ArrayList<String> tousPresent(ArrayList<String> currentPath, String source, HashMap<String, ArrayList<String>> sameLevel){
        ArrayList<String> retour = new ArrayList<>();
        ArrayList<String> currentLevel = sameLevel.get(source);
        for (String s : currentLevel) {
            if (! currentPath.contains(s)){
                retour.add(s);
            }
        }
       // System.out.println(source + " : "+sameLevel.get(source));
        return retour;
    }
    public int nbCheminSource(String source, ArrayList<ArrayList<String>> chAllPath, int index){
            /*nb de chemin commençant par source*/
        int i = 0;
        for (ArrayList<String> path : chAllPath){
             if ( path.get(index).compareTo(source) == 0){
                 i+=1;
             }
        }
        return i;
    }

}




