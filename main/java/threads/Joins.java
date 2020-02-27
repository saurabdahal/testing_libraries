package threads;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.TreeSet;

public class Joins implements I1,I2{
    @Override
    public void same() {

    }

    String s1 = "me";
    String s2 = "me";

    public static void main(String[] args) {
        new Joins();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joins)) return false;
        Joins joins = (Joins) o;
        return s1.equals(joins.s1) &&
                s2.equals(joins.s2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s1, s2);
    }

    Joins() throws NullPointerException{

        TreeSet hm = new TreeSet<>();

        throw new NullPointerException();
    }

}
