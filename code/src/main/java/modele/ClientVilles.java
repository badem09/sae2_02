package modele;

import java.io.File;

public class ClientVilles {
    public static void main(String[] args) throws Exception {

        Villes v1 = new Villes();
        System.out.println(v1.getTabVilles());
        System.out.println(v1.getTabDistances());
        System.out.println(v1.getMembreVilles());
    }
}
