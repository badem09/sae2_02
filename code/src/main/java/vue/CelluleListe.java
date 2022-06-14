package vue;

public class CelluleListe {

    private String membre;
   private String infos;

    public CelluleListe(String membre, String infos){
        this.infos = infos;
        this.membre = membre;
    }

    @Override
    public String toString() {
        return membre + " " + infos;
    }

    public String getMembre() {
        return membre;
    }
}