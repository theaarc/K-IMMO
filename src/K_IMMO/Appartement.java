package K_IMMO;

public class Appartement {
    private  int num;
    private  double superficie;
    private int nbr_chambre;
    private  double prix;
    private boolean isvendu;

    public Appartement(int n, double s, int nbr, double p, boolean i)
    {
        this.num = n;
        this.superficie = s;
        this.nbr_chambre = nbr;
        this.prix = p;
        this.isvendu = i;
    }

    public int getNum()
    {
        return this.num;
    }

    public double getSuperficie()
    {
        return this.superficie;
    }

    public int getNbr_chambre()
    {
        return this.nbr_chambre;
    }

    public double getPrix()
    {
        return this.prix;
    }

    public boolean getisvendu()
    {
        return this.isvendu;
    }

    public void setIsvendu(boolean b){
        this.isvendu = b;
    }
}
