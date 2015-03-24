package marrontan619.github.com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RedirectTester {

    public static void main(String[] args) {
        BufferedReader bufferedReader;
        String line;
        try {
            // Read file like httpd.conf
            bufferedReader = new BufferedReader(new FileReader("./target/test.txt"));
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
