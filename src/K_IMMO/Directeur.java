package K_IMMO;

public class Directeur {
    private int numaut;
    private String nom;
    private String prenom;

    public Directeur(int num, String n, String p){
        this.numaut = num;
        this.nom = n;
        this.prenom = p;
    }

    public int getNumaut(){
        return this.numaut;
    }

    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }
}
