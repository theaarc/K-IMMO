package K_IMMO;

import java.io.IOException;

public interface FilePersonneRead_I {
    Personne[] readfile(String filename) throws IOException;
}
