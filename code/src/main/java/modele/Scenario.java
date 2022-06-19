package modele;

import java.io.*;
import java.util.HashMap;
import java.util.*;

public class Scenario {

    private static Villes villes;
    private final ArrayList<String>  listVendeurs ;
    private final ArrayList<String>  listAcheteurs ;
    private final HashMap<String,ArrayList<String>> dicoVA ; // Vendeurs -> Acheteurs
    private final HashMap<String,ArrayList<String>> dicoAV ;  // Acheteurs -> Vendeurs
    private  ArrayList<String> membreScenario ; //membres du scenario sans duplicats
    private HashMap<String,String> membreToVille ; // Membre -> Ville
    private static SuiviScenario suiviScenario;
    private String fileName;



    public Scenario() throws IOException {
        villes = new Villes();
        listVendeurs = new ArrayList<>();
        listAcheteurs= new ArrayList<>();
        dicoVA = new HashMap<>();
        dicoAV = new HashMap<>();
        membreScenario = new ArrayList<>();
        suiviScenario = new SuiviScenario();
        membreToVille = new HashMap<>();
    }

    /**
     * Renvoie un Hashmap avec les membres non présents dans le fichier membres_APLI.txt
     * @return retour : Hashmap<Membre,Ville>
     */
    public HashMap<String, String> getMembreInconnus() {
        HashMap<String,String> retour = villes.getMembreToVilles();
        for (String membre : membreScenario){
            if (! retour.containsKey(membre)){
                retour.put(membre,"Ville non renseignée !");
            }
        }
        return retour;
    }

    public static void ecritureS(String nomFichier, Scenario scenario) throws IOException{
        PrintWriter sortie = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier)));
        int i = 0;
        for (String vendeur : scenario.getVendeurs()) { // boucle changeable car risque de poser des pb
            sortie.println(vendeur + " ->" + scenario.getAcheteurs().get(i));
            i++ ;
        }
    }

    /**Méthode de lecture des fichiers scénario à partir d'un path.
     *
     * @param path (String): Chemin absolu ou relatif (depuis /src) au fichier
     * @param save (boolean) : Sauvegarde le nom du fichier si true.
     * @return Scenario scenario.
     * @throws IOException (FileNotFoundExeption)
     */
    public static Scenario lectureScenario (String path, boolean save) throws IOException {
        try{
            File fichier = new File(path);
            String fileName = fichier.getName();
            FileReader fr =  new FileReader(fichier);
            Scenario scenario = new Scenario();
            BufferedReader bufferEntree = new BufferedReader(fr);
            String ligne;
            StringTokenizer tokenizer;
            do {
                ligne = bufferEntree.readLine();
                if (ligne != null) {
                    tokenizer = new StringTokenizer(ligne, " ->");
                    scenario.ajoutVA(tokenizer.nextToken(), tokenizer.nextToken());
                }
            }
            while (ligne != null);
            bufferEntree.close();
            scenario.updateDico();
            scenario.updateMembreScenario();
            if (save) {
                SuiviScenario.writeSuiviScenario(fichier);
            }
        scenario.fileName = fileName;
            return scenario;
        }
        catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
            System.out.println("Le fichier est introuvable.\nVeuillez vérifier son chemin d'accès_n") ;
            System.exit(5);
        }
       return null;
    }

    /**Méthode de lecture des fichiers scénario à partir d'un fichier.
     *
     * @param fichier (String): Chemin absolu ou relatif (depuis /src) au fichier
     * @param save (boolean) : Sauvegarde le nom du fichier si true.
     * @return Scenario scenario.
     * @throws IOException (FileNotFoundExeption)
     */
    public static Scenario lectureScenario (File fichier,boolean save) throws IOException {
        try{
            String fileName = fichier.getName();
            FileReader fr =  new FileReader(fichier);
            Scenario scenario = new Scenario();
            BufferedReader bufferEntree = new BufferedReader(fr);
            String ligne;
            StringTokenizer tokenizer;
            do {
                ligne = bufferEntree.readLine();
                if (ligne != null) {
                    tokenizer = new StringTokenizer(ligne, " ->");
                    scenario.ajoutVA(tokenizer.nextToken(), tokenizer.nextToken());
                }
            }
            while (ligne != null);
            bufferEntree.close();
            scenario.updateDico();
            scenario.updateMembreScenario();
            if (save) {
                SuiviScenario.writeSuiviScenario(fichier);
            }
            scenario.fileName = fileName ; // bizarre coz encapsulation non ??
            return scenario;
        }
        catch (FileNotFoundException fnfe){
            System.out.println("Le fichier est introuvable.\nVeuillez vérifier son chemin d'accès" ) ;

        }
        return null;
    }
    /**
     * Remplit les listes de vendeurs et d'acheteurs.
     */
    public void ajoutVA(String nVendeur, String nAcheteur){
        listVendeurs.add(nVendeur);
        listAcheteurs.add(nAcheteur);
    }

    /**
     * Remplit les dictionnaires dicoVA et dicoAV à partir des listes de
     * vendeurs et d'acheteurs.
     */
    private void updateDico(){
        for (int i = 0; i< listAcheteurs.size(); i++){
            String a = listAcheteurs.get(i);
            String v = listVendeurs.get(i);
            if (! dicoVA.containsKey(v)){
                dicoVA.put(v,new ArrayList<>());
            }
            if (! dicoAV.containsKey(a)){
                dicoAV.put(a,new ArrayList<>());
            }
            ArrayList<String> listV = dicoVA.get(v);
            ArrayList<String> listA = dicoAV.get(a);

            if (! listV.contains(a)){
                listV.add(a);
            }
            if (! listA.contains(v)){
                listA.add(v);
            }
            if (! listVendeurs.contains(a)){
                dicoVA.put(a, new ArrayList<>());
            }
            if (! listAcheteurs.contains(v)){
                dicoAV.put(v, new ArrayList<>());
            }
        }
    }

    public void updateMembreScenario() {
        /* Tout les membres inclus dans le scenario sans duplicats */
        HashSet<String> membresUnique = new HashSet<>(listAcheteurs);
        membresUnique.addAll(listVendeurs);
        membreScenario = new ArrayList<>(membresUnique);
        Collections.sort(membreScenario); // tri alphabétique.
        membreToVille = getMembreInconnus();
    }

    public ArrayList<String> membresToVilles(ArrayList<String> tab){
        ArrayList<String> villeScenario = new ArrayList<>();
        for (String elem : tab){
            villeScenario.add(membreToVille.get(elem));
        }
        return villeScenario ;
    }

    public String toString() {
        boolean connu;
        String retour = "";
        for (int i = 0; i < listAcheteurs.size(); i++){
            String a = listAcheteurs.get(i);
            String v = listVendeurs.get(i);
            if ( ! villes.getTabVilles().contains(membreToVille.get(a)) ||
                  !  villes.getTabVilles().contains(membreToVille.get(v) )){
                retour += i + " : "+ a + " vends à " + v + " :  " + membreToVille.get(a) +
                        " ----> " +  membreToVille.get(v) + " soit " +
                        "Distance inconnue. " + "\n";
            }
            else {
                int indexVilleA = villes.getTabVilles().indexOf(membreToVille.get(a));
                int indexVilleV = villes.getTabVilles().indexOf(membreToVille.get(v));

                retour += i + " : " + a + " vends à " + v + " :  " + membreToVille.get(a) +
                        " ----> " + membreToVille.get(v) + " soit " +
                        villes.getTabDistances().get(indexVilleA).get(indexVilleV) + " km." + "\n";
            }
        }
        return retour;
    }

    public ArrayList<String> getMembreScenario(){
        return membreScenario;
    }

    //Renvoie un nouvel objet.
    public HashMap<String, ArrayList<String>> getDicoVA() {
        HashMap<String, ArrayList<String>> copie = new HashMap<>();
        for (String key: dicoVA.keySet()){
            copie.put(key, (ArrayList<String>) dicoVA.get(key).clone());
        }
        return copie;
    }

    //Renvoie un nouvel objet.
    public HashMap<String,ArrayList<String>> getDicoAV() {
        HashMap<String, ArrayList<String>> copie = new HashMap<>();
        for (String key: dicoAV.keySet()){
            copie.put(key, (ArrayList<String>) dicoAV.get(key).clone());
        }
        return copie;
    }

    /**
     * Renvoie un str contenant tous les membres et leur ville.
     * @return retour (String)
     */
    public String getMembreToString(){
        String retour = "Président : Vélizy" + "\n";
        for (String membres : membreScenario){
            retour += membres + " : " + membreToVille.get(membres) + "\n";
        }
        return retour;
    }

    public ArrayList<String> getVendeurs(){
        return listVendeurs;
    }

    public ArrayList<String> getAcheteurs(){
        return listAcheteurs;
    }

    public static SuiviScenario getSuiviScenario() {
        return suiviScenario;
    }

    public String getFileName() {
        return fileName;
    }
}