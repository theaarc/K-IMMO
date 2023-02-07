package K_IMMO;

import java.io.IOException;
import java.text.ParseException;

public interface Acquereur_I extends AcquereurRead_I{
    Acquereur[] read_Acquereur(String filename) throws IOException, ParseException;
    void  add_acquereur(String filename, Acquereur acquereur) throws IOException, ParseException;
}
