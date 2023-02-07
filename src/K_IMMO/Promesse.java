package K_IMMO;

import java.util.Date;

public class Promesse {
    private int id_prom;
    private int id_acq;
    private int id_app;
    private String dateest;
    private int id_avocat;
    private double avance;

    public Promesse(int id_prom, int id_acq,int id_app,String dateest,int id_avocat, double avance)
    {
        this.id_prom = id_prom;
        this.id_acq = id_acq;
        this.id_app = id_app;
        this.dateest = dateest;
        this.id_avocat = id_avocat;
        this.avance = avance;
    }

    public int getId_prom()
    {
        return this.id_prom;
    }
    public int getId_acq()
    {
        return this.id_acq;
    }

    public int getId_app()
    {
        return this.id_app;
    }

    public String getdate()
    {
        return this.dateest;
    }

    public int getId_avocat()
    {
        return this.id_avocat;
    }

    public double getAvance()
    {
        return this.avance;
    }
}
