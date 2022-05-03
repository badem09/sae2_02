

import java.io.*;
import java.util.HashMap;
import java.util.*;
import java.util.Iterator;


public class Scenario {


    private List<String>  listVendeurs ;
    private List<String>  listAcheteurs ;
    //private List<String>  listVilleVendeurs ;
    //private List<String>  listVilleAcheteurs ;
   // private final HashMap<String ,String> villePersonne ; // ville corespondant à une prsn
    //HashMap<String,ArrayList<String>> dicoAcheteurs ; marche but pas tres pertinent
    //HashMap<String,ArrayList<String>> dicoVendeurs ;
    public Scenario() {
        listVendeurs = new ArrayList<String>();
        listAcheteurs= new ArrayList<String>();
        //listVillePersonne = lectureVille();
       // listVilleAcheteurs = getVilleAcheteurs();
        //listVilleVendeurs = getVilleVendeurs();
       // dicoAcheteurs = new HashMap<String,ArrayList<String>>();
       // dicoVendeurs = new HashMap<String,ArrayList<String>>();
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
        //scenario.updateDico();
        return scenario;
    }

    public void ajoutVA(String nVendeur, String nAcheteur){

        listVendeurs.add(nVendeur);
        listAcheteurs.add(nAcheteur);
    }

    public static HashMap<String,String> lectureVille(File fichier) throws IOException {
        /* Récupère toutes les  */
        HashMap<String,String> villePersonne = new HashMap<String,String>();
        BufferedReader bufferEntree = new BufferedReader(new FileReader (fichier));
        String ligne ;

        StringTokenizer tokenizer;
        do{
            ligne = bufferEntree.readLine();
            if(ligne!= null ){
                tokenizer = new StringTokenizer(ligne," ");
                villePersonne.put(tokenizer.nextToken(),tokenizer.nextToken());
            }
        }
        while (ligne != null);
        bufferEntree.close();
        //scenario.updateDico();
        return villePersonne;
    }

    public ArrayList<ArrayList<String>> nomToVille(File fichier) throws IOException{
        ArrayList<String> newListV = new ArrayList<String>() ;
        ArrayList<String> newListA = new ArrayList<>();

        HashMap<String,String> villePersonne = lectureVille(fichier);
        for (int i = 0; i<listVendeurs.size() ; i++){
            newListA.add(villePersonne.get(listVendeurs.get(i)) );
            newListV.add(villePersonne.get(listAcheteurs.get(i)) );
            //listVendeurs.set(i , villePersonne.get(listVendeurs.get(i)))); #possibilité de changer les champs
        //    listAcheteurs.get(i , villePersonne.get(listAcheteurs.get(i)));
        }
        ArrayList<ArrayList<String>> retour = new ArrayList<ArrayList<String>>();
        retour.add(newListA);
        retour.add(newListV);
        return retour;
        //this.listAcheteurs = newListA; possible changement des champs manière 2
        //this.listVendeurs = newListV;

    }
   // public void getVillePersonne(){
        /* dico qui donne ville des personnes*/


  //  }

/*    public void getVilleAcheteurs(){
        //convertit listAcheteur : nom -> vile 
    }

    public void getVilleVendeurs(){
    }
*/
  /*  private void updateDico(){
/*
        Iterator itv = listVendeurs.iterator();
        Iterator ita = listAcheteurs.iterator();
        while(itv.hasNext()){


            String a = ita.next();
            String v = itv.next();
            * /
        for (int i = 0; i<+ listAcheteurs.size();i++){
            String a = listAcheteurs.get(i);
            String v = listVendeurs.get(i);

            if (! dicoVendeurs.containsKey(v)){

                dicoVendeurs.put(v,new ArrayList<String>());
            }
            if (! dicoAcheteurs.containsKey(a)){
                dicoAcheteurs.put(a,new ArrayList<String>());
            }
;
            ArrayList listV = dicoVendeurs.get(v);
            ArrayList listA = dicoAcheteurs.get(a);

            if (! dicoVendeurs.get(v).contains(a)){
                dicoVendeurs.get(v).add(a);
            }
            if (! dicoAcheteurs.get(a).contains(v)){
                dicoAcheteurs.get(a).add(v);
            }
        }
    }*/

        
    

    public List<String> getVendeurs(){
        return listVendeurs;
    }

    public List<String> getAcheteurs(){
        return listAcheteurs;
    }

    public String toString() {
        return listVendeurs +"\n" + listAcheteurs;
       // + "\n"  + dicoVendeurs + "\n" + dicoAcheteurs;
    }


}