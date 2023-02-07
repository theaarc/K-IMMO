package K_IMMO;

import java.io.IOException;
import java.text.ParseException;

public interface Filepromesse_I extends PromosseRead_I {
    void writefilep(String filename, Promesse p[]) throws IOException, ParseException;
    Promesse[] readfilep(String filename) throws IOException, ParseException ;
}
