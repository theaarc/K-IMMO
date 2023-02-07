package K_IMMO;

import java.util.Date;

public class Visite {
    private int id_client;
    private String date;
    private String desision;
    private String remarque;

    public Visite(int i, String da, String d, String r)
    {
        this.id_client = i;
        this.date = da;
        this.desision = d;
        this.remarque = r;
    }

    public int getId_client(){
        return this.id_client;
    }

    public String getDate(){
        return this.date;
    }

    public String getDesision(){
        return this.desision;
    }

    public String getRemarque(){
        return this.remarque;
    }
}
