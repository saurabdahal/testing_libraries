package datastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        BufferedReader bufferedReader = null;
        String line = "";
        String[] strArr;
//        Student[] lst = new Student[10];
        Student1[] lst = new Student1[10];
        int counter=0;
        try {
            bufferedReader = new BufferedReader(new FileReader("/home/saurab/projects/testing_library/src/main/java/datastructure/input.txt"));
            while ((line = bufferedReader.readLine()) != null) {
                strArr = line.split(",");
                lst[counter++] = new Student1(strArr[0], Integer.parseInt(strArr[1]), Integer.parseInt(strArr[2]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Arrays.sort(lst);
        System.out.println(Arrays.toString(lst));
    }
}
