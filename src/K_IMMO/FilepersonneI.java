package K_IMMO;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FilepersonneI extends FilePersonneRead_I{
    Personne[] readfile(String filename) throws IOException;
    void writefile(String filename, Personne[] p) throws IOException;
}
