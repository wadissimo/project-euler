package chelper.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

public class FileScanner extends FastScanner {

    public FileScanner(InputStream stream) {
        super(stream);
    }

    public void init(String filePath) throws FileNotFoundException {
        in = new BufferedReader(new FileReader(filePath));
    }
}
