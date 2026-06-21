package Management;

import Domain.Student;

import java.util.HashMap;
import java.util.regex.Pattern;

public class StudentManagement {
    private HashMap<Integer, Student> students;
    private int counterId = 1;

    private static final Pattern NAME_PATTERN = Pattern.compile("^(?=^.{3,15}$)[A-Z][a-z]*(\\s[A-Z][a-z]*)*$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z._%+-]+[0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{1,3}$");

    public StudentManagement() {
        this.students = new HashMap<>();
    }

    public void addStudent(String first_name, String last_name, String email) {
        if (first_name == null || !NAME_PATTERN.matcher(first_name).matches()) {
            System.out.println("Invalid first name. Use only letters and spaces, name in range 3 to 15 and start with a capital letter.");
            return;
        }

        if (last_name == null || !NAME_PATTERN.matcher(last_name).matches()) {
            System.out.println("Invalid last name. Use only letters and spaces, name in range 3 to 15 and start with a capital letter.");
            return;
        }

        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("Invalid email format.");
            return;
        }

        int studentID = counterId++;
        students.put(studentID, new Student(studentID, first_name, last_name, email));
        System.out.println("Student is added successfully. ID: " + studentID);
    }

    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students are found.");
        } else {
            for (Student s : students.values()) {
                s.display();
            }
        }
    }

    public Student searchStudentByID(int id) {
        return students.get(id);
    }

    public void searchByFirstName(String firstName) {
        if (firstName == null || !NAME_PATTERN.matcher(firstName).matches()) {
            System.out.println("Please enter a valid first name.");
            return;
        }
        boolean found = false;
        for (Student s : students.values()) {
            if (s.getFirstName().equalsIgnoreCase(firstName.trim())) {
                found = true;
                s.display();
            }
        }
        if (!found) {
            System.out.println("No students found with first name: " + firstName);
        }
    }

    public void removeStudent(int id) {
        if (students.containsKey(id)) {
            students.remove(id);
            System.out.println("Student with ID " + id + " is removed.");
        } else {
            System.out.println("Student not found.");
        }
    }
}