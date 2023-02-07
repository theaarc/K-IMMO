package K_IMMO;

import java.io.IOException;
import java.text.ParseException;

public interface DesistementI {
    void deletepromesse(String filename, Promesse promesse) throws IOException, ParseException;
    void deleteacq(Promesse promesse);
}
