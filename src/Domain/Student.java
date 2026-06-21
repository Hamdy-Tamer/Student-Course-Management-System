package Domain;
import Interface.Searchable;

// Student Class
public class Student implements Searchable {
    private int student_ID;
    private String first_name;
    private String last_name;
    private String email;

    public Student(int student_ID, String first_name, String last_name, String email) {
        this.student_ID = student_ID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    @Override
    public int getID() {
        return student_ID;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public void display() {
        System.out.println();
        System.out.println("Student ID: " + getID());
        System.out.println("Student Name: " + getFirstName() + " " + getLastName());
        System.out.println("Student Email: " + getEmail());
        System.out.println();
    }
}
