package K_IMMO;

public class Personne {
    private int numCNI;
    private String nom;
    private String prenom;
    private String adresse;
    private int tel;
    private String profession;

    public  Personne(int num,String n,String p,String a, int t, String prof)
    {
        this.numCNI = num;
        this.nom = n;
        this.prenom = p;
        this.adresse = a;
        this.tel = t;
        this.profession =prof;
    }

    public int getNumCNI()
    {
        return this.numCNI;
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

    public String getProfession()
    {
        return this.profession;
    }

    public void setNumCNI(int n)
    {
        this.numCNI = n;
    }

    public void setNom(String n)
    {
        this.nom = n;
    }

    public void setPrenom(String p)
    {
        this.prenom = p;
    }

    public void setAdresse(String a)
    {
        this.adresse =a;
    }

    public void setTel(int t)
    {
        this.tel = t;
    }

    public void setProfession(String pr)
    {
        this.profession = pr;
    }
}
