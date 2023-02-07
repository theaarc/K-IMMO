package K_IMMO;

public class Avocat {
    private int numauto;
    private String nom;
    private String prenom;
    private String adresse;
    private int tel;

    public  Avocat(int num,String n,String p,String a, int t)
    {
        this.numauto = num;
        this.nom = n;
        this.prenom = p;
        this.adresse = a;
        this.tel = t;
    }

    public int getNumauto()
    {
        return this.numauto;
    }

    public String getNom()
    {
        return this.nom;
    }

    public String getPrenom()
    {
        return this.prenom;
    }

    public String getAdresse()
    {
        return this.adresse;
    }

    public int getTel()
    {
        return this.tel;
    }
}
