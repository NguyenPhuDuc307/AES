
import java.util.*;

// A class to represent a student.
class Student {

    int rollno;
    String name, address;

    // Constructor
    public Student(int rollno, String name, String address) {
        this.rollno = rollno;
        this.name = name;
        this.address = address;
    }

    // Used to print student details in main()
    @Override
    public String toString() {
        return this.rollno + " " + this.name
                + " " + this.address;
    }
}

// Driver class
class Main {

    public static void main(String[] args) {
        ArrayList<Student> ar = new ArrayList<>();
        ar.add(new Student(111, "bbbb", "london"));
        ar.add(new Student(131, "aaaa", "nyc"));
        ar.add(new Student(121, "cccc", "jaipur"));

        System.out.println("Chưa xếp");
        for (int i = 0; i < ar.size(); i++) {
            System.out.println(ar.get(i));
        }

        Collections.sort(ar, (Student a, Student b) -> a.name.compareTo(b.name));
        
        Collections.sort(ar, (Student a, Student b) -> a.rollno - b.rollno);

        System.out.println("\nĐã xếp");
        for (int i = 0; i < ar.size(); i++) {
            System.out.println(ar.get(i));
        }
    }
}
