package modele;

import java.io.*;
import java.util.HashMap;
import java.util.*;

public class Scenario {

    private static  Villes villes;
    private final ArrayList<String>  listVendeurs ;
    private final ArrayList<String>  listAcheteurs ;
    private final HashMap<String,ArrayList<String>> dicoVA ; // Vendeurs -> Acheteurs
    private final HashMap<String,ArrayList<String>> dicoAV ;  // Acheteurs -> Vendeurs
    private  ArrayList<String> membreScenario ; //membres concernés par le scenario


    private static SuiviScenario suiviScenario;


    public Scenario() throws IOException {
        villes = new Villes();
        listVendeurs = new ArrayList<>();
        listAcheteurs= new ArrayList<>();
        dicoVA = new HashMap<>();
        dicoAV = new HashMap<>();
        membreScenario = new ArrayList<>();
        suiviScenario = new SuiviScenario();
    }

    public static void ecritureS(String nomFichier, Scenario scenario) throws IOException{
        PrintWriter sortie = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier)));
        int i = 0;
        for (String vendeur : scenario.getVendeurs()) { // boucle changeable car risque de poser des pb
            sortie.println(vendeur + " ->" + scenario.getAcheteurs().get(i));
            i++ ;
        }
    }







    /**
     * Ecrit dans un fichier texte les scenarios deja connu du lappli.
     */

    public static Scenario lectureScenario (String path) throws IOException {
        boolean succes = true;
        try{
            File fichier = new File(path);
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
            SuiviScenario.writeSuiviScenario(fichier);
            return scenario;
        }
        catch (Exception e){
            succes = false;
            System.out.println("Le fichier est introuvable.\nVeuillez vérifier son chemin d'accès" ) ;
            System.exit(5);

        }
        return null;
    }

    public static Scenario lectureScenario (File fichier) throws IOException {
        boolean succes = true;
        try{
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
            SuiviScenario.writeSuiviScenario(fichier);
            return scenario;
        }
        catch (FileNotFoundException fnfe){
            succes = false;
            System.out.println("Le fichier est introuvable.\nVeuillez vérifier son chemin d'accès" ) ;
           // System.exit(5);

        }
        return null;
    }



    public void ajoutVA(String nVendeur, String nAcheteur){
        listVendeurs.add(nVendeur);
        listAcheteurs.add(nAcheteur);
    }
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
       // Collections.sort(listVendeurs);
        //Collections.sort(listAcheteurs);

    }

    public void updateMembreScenario() {
        /* Tout les membres inclus dans le scenario sans duplicats*/
        HashSet<String> membresUnique = new HashSet<>(listAcheteurs);
        membresUnique.addAll(listVendeurs);
        membreScenario = new ArrayList<>(membresUnique);
        Collections.sort(membreScenario);
    }

    public ArrayList<String> membresToVilles(ArrayList<String> tab){
        ArrayList<String> villeScenario = new ArrayList<>();
        HashMap<String,String> membresVilles = villes.getMembreToVilles();
        for (String elem : tab){
            villeScenario.add(membresVilles.get(elem));
        }
        return villeScenario ;
    }

    public HashMap<String, ArrayList<String>> membresToVilles(HashMap<String, ArrayList<String>> dico){
        /* ne me semble pas tres pertinent */
        HashMap<String,String> membresVilles = villes.getMembreToVilles();
        HashMap<String, ArrayList<String>> retour = new HashMap<>(dico);
        for (String elem : retour.keySet()){
            ArrayList<String> list = retour.get(elem);
            for (int i = 0; i < list.size() ;i++) {
                list.set(i,membresVilles.get(list.get(i))) ;
            }
        }
        return retour ;
    }

    public String toString() {
        String retour = "";
        for (int i = 0; i<listAcheteurs.size(); i++){
            String a = listAcheteurs.get(i);
            String v = listVendeurs.get(i);
            retour += i + " : "+ a + " vends à " + v +
                    "  " + villes.getMembreToVilles().get(a) +
                    " --- " +
                    "" + villes.getMembreToVilles().get(v) +"\n";
        }
        return retour;
    }

    public ArrayList<String> getMembreScenario(){
        return membreScenario;
    }

    public HashMap<String, ArrayList<String>> getDicoVA() {
        HashMap<String, ArrayList<String>> copie = new HashMap<>();
        for (String key: dicoVA.keySet()){
            copie.put(key, (ArrayList<String>) dicoVA.get(key).clone());
        }
        return copie;
    }


    public HashMap<String,ArrayList<String>> getDicoAV() {
        HashMap<String, ArrayList<String>> copie = new HashMap<>();
        for (String key: dicoAV.keySet()){
            copie.put(key, (ArrayList<String>) dicoAV.get(key).clone());
        }
        return copie;
    }

    public String getMembreToString(){
        String retour = "Président : Vélizy" + "\n";
        for (String membres : membreScenario){
            retour += membres + " : " + villes.getMembreToVilles().get(membres) + "\n";
        }
        return retour;
    }

    public ArrayList<String> getVendeurs(){
        return listVendeurs;
    }

    public ArrayList<String> getAcheteurs(){
        return listAcheteurs;
    }

    }