package modele;
import java.io.*;
import java.util.*;


public class Villes {

    private  File fileDistances ;
    private  File fileMembre ;

    private ArrayList<String> tabVilles ; // tableau des villes (indice important pour la suite)
    private ArrayList<ArrayList<Integer>> tabDistances ; // tableau 2d des distances
    private HashMap<String,String> dicoDistances; // pertinent? pas défini !
    private HashMap<String,ArrayList<String>> villesMembre; // ville = liste de membres
    private HashMap<String,String> membreVilles; // membre = ville

    public Villes() throws IOException {

        fileDistances =new File(
                "/Users/badem/Documents/INFI1/SAE/algos2/Documentation/distances.txt");
        fileMembre = new File(
                "/Users/badem/Documents/INFI1/SAE/algos2/Documentation/membres_APLI.txt");
        tabVilles = new ArrayList<String>();
        tabDistances = new  ArrayList<ArrayList<Integer>>();
        dicoDistances = new HashMap<>();
        membreVilles = new HashMap<>();
        villesMembre = new HashMap<>();

        updateTabVilles();
        updateTabDistance();
        updateVillesMembres();
        updateMembreVilles();
    }

    private void updateMembreVilles() throws IOException {
        BufferedReader bufferEntree = new BufferedReader(new FileReader (fileMembre));
        String ligne ;
        StringTokenizer tokenizer;
        do{
            ligne = bufferEntree.readLine();
            if(ligne!= null ){
                tokenizer = new StringTokenizer(ligne," ");
                String membre = tokenizer.nextToken();
                String ville = tokenizer.nextToken();
                membreVilles.put(membre,ville)
;            }
        }
        while (ligne != null);
        bufferEntree.close();
    }

    public void updateTabVilles() throws IOException {
        BufferedReader bufferEntree = new BufferedReader(new FileReader (fileDistances));
        String ligne ;
        StringTokenizer tokenizer;
        do{
            ligne = bufferEntree.readLine();
            if(ligne!= null ){
                tokenizer = new StringTokenizer(ligne," ");
                tabVilles.add(tokenizer.nextToken());
            }
        }
        while (ligne != null);
        bufferEntree.close();
    }

    public void updateTabDistance() throws IOException { //good
        BufferedReader bufferEntree = new BufferedReader(new FileReader (fileDistances));
        String ligne ;
        StringTokenizer tokenizer;
        int nbVilles = tabVilles.size();

        do{
            ligne = bufferEntree.readLine();
            if(ligne!= null ){
                tokenizer = new StringTokenizer(ligne," ");
                tokenizer.nextToken(); // pour pas prendre la ville
                ArrayList<Integer> listLigne = new ArrayList<>();

                int i = 0;
                while (i < nbVilles){
                    listLigne.add(Integer.valueOf(tokenizer.nextToken()));
                    i+=1;
                }
               tabDistances.add(listLigne);
            }
        }
        while (ligne != null);
        bufferEntree.close();
    }

    public void updateVillesMembres() throws IOException {
        BufferedReader bufferEntree = new BufferedReader(new FileReader (fileMembre));
        String ligne ;
        StringTokenizer tokenizer;
        do{
            ligne = bufferEntree.readLine();
            if(ligne!= null ){
                tokenizer = new StringTokenizer(ligne," ");
                String membre = tokenizer.nextToken();
                String ville = tokenizer.nextToken();
                if (! villesMembre.containsKey(ville)) {
                    villesMembre.put(ville, new ArrayList<String>());
                    villesMembre.get(ville).add(membre);
                }
                else {
                    villesMembre.get(ville).add(membre);
                }

            }
        }
        while (ligne != null);
        bufferEntree.close();
    }
    
    public ArrayList<String> getTabVilles(){
        return tabVilles;
    }
    public ArrayList<ArrayList<Integer>> getTabDistances(){
        return tabDistances;
    }

    public HashMap<String,String> getMembreVilles(){
        return membreVilles;
    }
    public HashMap<String,ArrayList<String>> getVillesMembre(){
        return villesMembre;
    }

}
