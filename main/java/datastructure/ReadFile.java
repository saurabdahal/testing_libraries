package datastructure;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.HashSet;

public class ReadFile {
    String bname = "/home/saurab/Downloads/boynames.txt";
    String gname = "/home/saurab/Downloads/girlnames.txt";

    public static void main(String[] args) {
        new ReadFile();
    }

    public ReadFile() {
        HashSet<String> hashSet = new HashSet<>();
        try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(bname))) {
            String line;
            while ((line = lineNumberReader.readLine()) != null) {
                System.out.println(lineNumberReader.getLineNumber());
                hashSet.add(line.split(",")[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(hashSet.size());
    }
}
