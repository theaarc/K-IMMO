package K_IMMO;

import java.io.IOException;
import java.text.ParseException;

public interface ClientRead_I {
    Client[] read_client(String filename) throws IOException, ParseException;
}
