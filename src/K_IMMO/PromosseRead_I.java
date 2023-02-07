package K_IMMO;

import java.io.IOException;
import java.text.ParseException;

public interface PromosseRead_I {
    Promesse[] readfilep(String filename) throws IOException, ParseException ;
}
