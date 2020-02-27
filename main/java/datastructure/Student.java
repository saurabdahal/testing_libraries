package datastructure;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Student implements Comparable<Student> {

    private String lastName;
    private int id;
    private long SSN;

    public Student(String lastName, int id, long SSN) {
        this.lastName = lastName;
        this.id = id;
        this.SSN = SSN;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSSN() {
        return SSN;
    }

    public void setSSN(long SSN) {
        this.SSN = SSN;
    }

    @Override
    public String toString() {
        return "sorted order : lastName = " + this.lastName + ", id=" + this.id + ", SSN=" + this.SSN+"\n";
    }

    @Override
    public int compareTo(Student s) {
        return this.lastName.compareTo(s.getLastName());
    }

}
