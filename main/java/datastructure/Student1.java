package datastructure;

public class Student1 implements Comparable<Student1> {

    private String lastName;
    private int id;
    private long SSN;

    public Student1(String lastName, int id, long SSN) {
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
        return "NUMBER ::: "+this.SSN+"::: " + this.lastName + " ::: " + this.id+"\n";
    }

    @Override
    public int compareTo(Student1 s) {
        return (int) (this.SSN-s.getSSN());
    }

    /*
    STUDENT DATA SORTED BY SSN

NUMBER:::  1:::Poudel:::4:::1114445557
NUMBER:::  2:::Neupane:::7:::1425874254
NUMBER:::  3:::Dhakal:::8:::1547853652
NUMBER:::  4:::Ghimere:::3:::2154785468
NUMBER:::  5:::Mainali:::5:::2458754158
NUMBER:::  6:::Dahal:::1:::3215478965
NUMBER:::  7:::Pokhrel:::6:::3254158754
NUMBER:::  8:::Rijal:::9:::6587458965
NUMBER:::  9:::Shrestha:::10:::7548526544
NUMBER::: 10:::Acharya:::2:::8547853652
     */

}
