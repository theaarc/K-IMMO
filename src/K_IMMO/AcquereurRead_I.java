package K_IMMO;

import java.io.IOException;
import java.text.ParseException;

public interface AcquereurRead_I {
    Acquereur[] read_Acquereur(String filename) throws IOException, ParseException;
}
