package K_IMMO;

import java.io.IOException;
import java.text.ParseException;

public interface Clientadd_I {
    void add_client(String filename,Client client) throws IOException, ParseException;
    void add_visite(String filename, Visite visite) throws IOException, ParseException;
}
