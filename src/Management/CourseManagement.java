package Management;

import Domain.Course;
import Domain.Student;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

public class CourseManagement {
    private HashMap<Integer, Course> courses;
    private HashSet<String> course_codes;
    private int counterId = 1;

    private static final Pattern COURSE_NAME_PATTERN = Pattern.compile("^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$");
    private static final Pattern COURSE_CODE_PATTERN = Pattern.compile("^[A-Z]{4}-\\d{3}$");

    public CourseManagement() {
        this.courses = new HashMap<>();
        this.course_codes = new HashSet<>();
    }

    public void addCourse(String course_name, String course_code){
        if(course_codes.contains(course_code)){
            System.out.println("This course is already exists");
            return;
        }

        if(course_name == null || !COURSE_NAME_PATTERN.matcher(course_name).matches()){
            System.out.println("Invalid course name. Use only letters and spaces, and start with a capital letter for each word.");
            return;
        }

        if(course_code == null || !COURSE_CODE_PATTERN.matcher(course_code).matches()){
            System.out.println("Invalid course code. Use four capital letters then \"-\" then three units number.");
            return;
        }

        int courseID = counterId++;
        courses.put(courseID, new Course(courseID, course_name, course_code));
        course_codes.add(course_code);
        System.out.println("Course is added successfully. ID: " + courseID);
    }

    public void viewAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No Courses are found.");
        } else {
            for (Course c : courses.values()) {
                c.display();
            }
        }
    }

    public Course searchCourseByID(int id) {
        return courses.get(id);
    }

    public void searchByCourseName(String courseName) {
        if (courseName == null || !COURSE_NAME_PATTERN.matcher(courseName).matches()) {
            System.out.println("Please enter a valid course name.");
            return;
        }
        boolean found = false;
        for (Course c : courses.values()) {
            if (c.getCourseName().equalsIgnoreCase(courseName.trim())) {
                found = true;
                c.display();
            }
        }
        if (!found) {
            System.out.println("No courses found with name: " + courseName);
        }
    }

    public void searchByCourseCode(String courseCode) {
        if (courseCode == null || !COURSE_CODE_PATTERN.matcher(courseCode).matches()) {
            System.out.println("Please enter a valid course code.");
            return;
        }
        boolean found = false;
        for (Course c : courses.values()) {
            if (c.getCourseCode().equalsIgnoreCase(courseCode.trim())) {
                found = true;
                c.display();
            }
        }
        if (!found) {
            System.out.println("No courses found with code: " + courseCode);
        }
    }

    public void removeCourse(int id) {
        Course c = courses.remove(id);
        if (c != null) {
            course_codes.remove(c.getCourseCode());
            System.out.println("Course with ID " + id + " is removed.");
        } else {
            System.out.println("Course not found.");
        }
    }
}
