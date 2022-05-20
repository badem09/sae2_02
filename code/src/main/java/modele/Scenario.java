package modele;



import java.io.*;
import java.util.HashMap;
import java.util.*;
import java.util.stream.Collectors;


public class Scenario {

    private Villes villes;
    private ArrayList<String>  listVendeurs ;
    private ArrayList<String>  listAcheteurs ;
    private HashMap<String,ArrayList<String>> dicoVA ; // Vendeurs -> Acheteurs
    private HashMap<String,ArrayList<String>> dicoAV ;  // Acheteurs - Vendeurs
    private ArrayList<String> membreScenario ; //membres concernés par le scenario

    public Scenario() throws IOException {
        villes = new Villes();
        listVendeurs = new ArrayList<>();
        listAcheteurs= new ArrayList<>();
        dicoVA = new HashMap<>();
        dicoAV = new HashMap<>();
        membreScenario = new ArrayList<>();
    }

    public static void ecritureS(String nomFichier, Scenario scenario) throws IOException{
        PrintWriter sortie = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier)));
        int i = 0;
        for (String vendeur : scenario.getVendeurs()) { // boucle changeable car risque de poser des pb
            sortie.println(vendeur + " ->" + scenario.getAcheteurs().get(i));
            i++ ;
        }
    }

    public static Scenario lectureScenario (File fichier) throws IOException{
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
        scenario.updateMembreScenario();
        return scenario;
    }


    public void ajoutVA(String nVendeur, String nAcheteur){
        listVendeurs.add(nVendeur);
        listAcheteurs.add(nAcheteur);
    }

    public void ajoutSourcesDico(){
        for( String a : listAcheteurs){
            if (! dicoVA.containsKey(a)){
                ArrayList<String> president = new ArrayList<>();
                president.add("PresidentDébut");
                dicoVA.put(a,president);
            }
        }

        for( String v : listVendeurs){
            if (! dicoAV.containsKey(v)){
                ArrayList<String> president = new ArrayList<>();
                president.add("PresidentFin");
                dicoAV.put(v,new ArrayList<>());
            }
        }
    }
    private void updateDico(){
        for (int i = 0; i< listAcheteurs.size(); i++){
            String a = listAcheteurs.get(i);
            String v = listVendeurs.get(i);
            if (! dicoVA.containsKey(v)){
                dicoVA.put(v,new ArrayList());
            }
            if (! dicoAV.containsKey(a)){
                dicoAV.put(a,new ArrayList());
            }
            ArrayList listV = dicoVA.get(v);
            ArrayList listA = dicoAV.get(a);

            if (! listV.contains(a)){
                listV.add(a);
            }
            if (! listA.contains(v)){
                listA.add(v);
            }
            if (! listVendeurs.contains(a)){
                dicoVA.put(a, new ArrayList<String>());
            }
            if (! listAcheteurs.contains(v)){
                dicoAV.put(v, new ArrayList<String>());
            }
        }
        //ajoutSourcesDico();
    }

    public void updateMembreScenario() {
        /* Tout les membres inclus dans le scenario sans duplicats*/
        ArrayList<String> l = new ArrayList<>(listAcheteurs);
        l.addAll(listVendeurs);
        ArrayList<String> membresUnique = (ArrayList<String>) l.stream()
                .distinct().collect(Collectors.toList());
        membreScenario = membresUnique;
    }

    public ArrayList<String> membresToVilles(ArrayList<String> tab){
        ArrayList<String> villeScenario = new ArrayList<>();
        HashMap<String,String> membresVilles = villes.getMembreToVilles();
        for (String elem : tab){
            villeScenario.add(membresVilles.get(elem));
        }
        return villeScenario ;
    }

    public HashMap<String, ArrayList> membresToVilles(HashMap<String, ArrayList> dico){
        /* ne me semble pas tres pertinent */
        //HashMap<String, ArrayList> villeScenario = new ArrayList<>();
        HashMap<String,String> membresVilles = villes.getMembreToVilles();
        HashMap<String, ArrayList> retour = new HashMap<>(dico);

        for (String elem : retour.keySet()){
            ArrayList<String> list = retour.get(elem);
            for (int i = 0; i < list.size() ;i++) {
                list.set(i,membresVilles.get(list.get(i))) ;
            }
        }
        return retour ;
    }

    public ArrayList<String> getVendeurs(){
        return listVendeurs;
    }

    public ArrayList<String> getAcheteurs(){
        return listAcheteurs;
    }

    public String toString() {
        return listVendeurs +"\n" + listAcheteurs + "\n" + dicoAV + "\n" + dicoVA ;
    }
    public Villes getVilles(){
        return villes;
    }
    public ArrayList<String> getMembreScenario(){
        return membreScenario;
    }

    public HashMap<String, ArrayList<String>> getDicoVA() {
        HashMap<String, ArrayList<String>> copie = new HashMap<>();
        for (String key: dicoVA.keySet()){
            copie.put(new String (key),new ArrayList<>(dicoVA.get(key)));
        }
        return copie;
    }


    public HashMap<String,ArrayList<String>> getDicoAV() {
        HashMap<String, ArrayList<String>> copie = new HashMap<>();
        for (String key: dicoAV.keySet()){
            copie.put(new String (key),new ArrayList<>(dicoAV.get(key)));
        }
        return copie;
    }


}