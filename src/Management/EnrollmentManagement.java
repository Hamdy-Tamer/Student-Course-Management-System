package Management;

import Domain.Student;
import Domain.Course;
import Domain.Enrollment;

import java.util.HashMap;
import java.util.Map;

public class EnrollmentManagement {
    private Map<Integer, Map<Integer, Enrollment>> studentToCourses = new HashMap<>();
    private Map<Integer, Map<Integer, Enrollment>> courseToStudents = new HashMap<>();

    private StudentManagement studentMg;
    private CourseManagement courseMg;

    public EnrollmentManagement(StudentManagement studentMg, CourseManagement courseMg) {
        this.studentMg = studentMg;
        this.courseMg = courseMg;
    }

    public void enrollStudent(int studentID, int courseID) {
        Student student = studentMg.searchStudentByID(studentID);
        if (student == null) {
            System.out.println("Student Not Found");
            return;
        }

        Course course = courseMg.searchCourseByID(courseID);
        if (course == null) {
            System.out.println("Course Not Found");
            return;
        }

        if (studentToCourses.containsKey(studentID) && studentToCourses.get(studentID).containsKey(courseID)){
            System.out.println("Student is already enrolled in this course.");
            return;
        }

        Enrollment enrollment = new Enrollment(student, course);

        studentToCourses.computeIfAbsent(studentID, k -> new HashMap<>()).put(courseID, enrollment);
        courseToStudents.computeIfAbsent(courseID, k -> new HashMap<>()).put(studentID, enrollment);

        System.out.println("Enrollment Operation is successful.");
    }

    public void viewStudentEnrolledCourses(int studentID) {
        Student student = studentMg.searchStudentByID(studentID);
        if (student == null) {
            System.out.println("Student Not Found");
            return;
        }

        Map<Integer, Enrollment> courses = studentToCourses.get(studentID);
        if (courses == null || courses.isEmpty()) {
            System.out.println("This Student has no enrolled courses.");
            return;
        }

        for (Enrollment enrollment : courses.values()) {
            Course course = enrollment.getCourse();
            course.display();
        }
    }

    public void viewCoursesEnrolledStudents(int courseID) {
        Course course = courseMg.searchCourseByID(courseID);
        if (course == null) {
            System.out.println("Course Not Found");
            return;
        }

        Map<Integer, Enrollment> students = courseToStudents.get(courseID);
        if (students == null || students.isEmpty()) {
            System.out.println("This Course has no enrolled students.");
            return;
        }

        for (Enrollment enrollment : students.values()) {
            Student student = enrollment.getStudent();
            student.display();
        }
    }

    public void removeEnrollment(int studentID, int courseID) {
        Student student = studentMg.searchStudentByID(studentID);
        if (student == null) {
            System.out.println("Student Not Found");
            return;
        }

        Course course = courseMg.searchCourseByID(courseID);
        if (course == null) {
            System.out.println("Course Not Found");
            return;
        }

        Map<Integer, Enrollment> studentCourses = studentToCourses.get(studentID);
        if (studentCourses == null || !studentCourses.containsKey(courseID)) {
            System.out.println("Enrollment not found.");
            return;
        }

        studentCourses.remove(courseID);

        Map<Integer, Enrollment> courseStudents = courseToStudents.get(courseID);
        if (courseStudents != null) {
            courseStudents.remove(studentID);
        }

        System.out.println("Enrollment removed successfully.");
    }
}