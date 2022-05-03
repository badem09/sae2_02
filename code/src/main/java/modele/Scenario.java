package modele;

import java.io.*;
import java.util.HashMap;
import java.util.*;


public class Scenario {

    private Villes villes;
    private List<String>  listVendeurs ;
    private List<String>  listAcheteurs ;
    private HashMap<String,ArrayList> dicoAcheteurs ;
    private HashMap<String,ArrayList> dicoVendeurs ;
    public Scenario() throws IOException {
        villes = new Villes();
        listVendeurs = new ArrayList<>();
        listAcheteurs= new ArrayList<>();
        dicoAcheteurs = new HashMap<>();
        dicoVendeurs = new HashMap<>();

    }

    public static void ecritureS(String nomFichier, Scenario scenario) throws IOException{
        PrintWriter sortie = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier)));
        int i = 0;
        for (String vendeur : scenario.getVendeurs()) { // boucle changeable car risque de poser des pb
            sortie.println(vendeur + " ->" + scenario.getAcheteurs().get(i));
            i++ ;
        }
    }

    public static Scenario lectureSenario (File fichier) throws IOException{
        Scenario scenario = new Scenario();
        BufferedReader bufferEntree = new BufferedReader(new FileReader (fichier));
        String ligne ;
        StringTokenizer tokenizer;
        do{
            ligne = bufferEntree.readLine();
            if(ligne!= null ){
                tokenizer = new StringTokenizer(ligne," ->");
                scenario.ajoutVA(tokenizer.nextToken(),tokenizer.nextToken());
            }
        }
        while (ligne != null);
        bufferEntree.close();
        scenario.updateDico();
        return scenario;
    }

    public void ajoutVA(String nVendeur, String nAcheteur){
        listVendeurs.add(nVendeur);
        listAcheteurs.add(nAcheteur);
    }

    private void updateDico(){

        for (int i = 0; i< listAcheteurs.size(); i++){
            String a = listAcheteurs.get(i);
            String v = listVendeurs.get(i);
            if (! dicoVendeurs.containsKey(v)){
                dicoVendeurs.put(v,new ArrayList());
            }
            if (! dicoAcheteurs.containsKey(a)){
                dicoAcheteurs.put(a,new ArrayList());
            }
            ArrayList listV = dicoVendeurs.get(v);
            ArrayList listA = dicoAcheteurs.get(a);

            if (! listV.contains(a)){
                listV.add(a);
            }
            if (! listA.contains(v)){
                listA.add(v);
            }
        }
    }


    public List<String> getVendeurs(){
        return listVendeurs;
    }

    public List<String> getAcheteurs(){
        return listAcheteurs;
    }

    public String toString() {
        return listVendeurs +"\n" + listAcheteurs + "\n" + dicoVendeurs + "\n" + dicoAcheteurs ;
    }
    public Villes getVilles(){
        return villes;
    }


}