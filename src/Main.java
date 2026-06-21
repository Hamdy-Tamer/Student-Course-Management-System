import Domain.Student;
import Domain.Course;
import Management.CourseManagement;
import Management.StudentManagement;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement sm = new StudentManagement();
        CourseManagement cm = new CourseManagement();

        int choice = 1;

        do {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Exit");

            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("\nEnter your choice: ");
                try {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 3) {
                        validChoice = true;
                    } else {
                        System.out.println("Invalid choice. Enter a number between 1 and 3.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer (1–3).");
                    scanner.next();
                }
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    studentMenu(scanner, sm);
                    break;
                case 2:
                    courseMenu(scanner, cm);
                    break;
                case 3:
                    System.out.println("Good Bye");
                    break;
                default:
                    System.out.println("Unexpected error.");
            }
        } while (choice != 3);

        scanner.close();
    }

    // Student Management Menu
    private static void studentMenu(Scanner scanner, StudentManagement sm) {
        int choice = 0;
        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by First Name");
            System.out.println("5. Remove Student by ID");
            System.out.println("6. Exit");

            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("\nEnter your choice: ");
                try {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 6) {
                        validChoice = true;
                    } else {
                        System.out.println("Invalid choice. Enter a number between 1 and 6.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer (1–6).");
                    scanner.next();
                }
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student First Name: ");
                    String fname = scanner.nextLine();
                    System.out.print("Enter Student Last Name: ");
                    String lname = scanner.nextLine();
                    System.out.print("Enter Student Email: ");
                    String email = scanner.nextLine();
                    sm.addStudent(fname, lname, email);
                    break;

                case 2:
                    sm.viewAllStudents();
                    break;

                case 3:
                    int searchId = readPositiveInt(scanner, "Enter Student ID to search: ");
                    Student found = sm.searchStudentByID(searchId);
                    if (found != null) {
                        found.display();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student First Name to search: ");
                    String firstName = scanner.nextLine();
                    sm.searchByFirstName(firstName);
                    break;

                case 5:
                    int removeId = readPositiveInt(scanner, "Enter Student ID to remove: ");
                    sm.removeStudent(removeId);
                    break;

                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Unexpected error.");
            }
        } while (choice != 6);
    }

    // Course Management Menu
    private static void courseMenu(Scanner scanner, CourseManagement cm) {
        int choice = 0;
        do {
            System.out.println("\n--- Course Management System ---");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course by ID");
            System.out.println("4. Search Course by Course Name");
            System.out.println("5. Search Course by Course Code");
            System.out.println("6. Remove Course by ID");
            System.out.println("7. Exit");

            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("\nEnter your choice: ");
                try {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 7) {
                        validChoice = true;
                    } else {
                        System.out.println("Invalid choice. Enter a number between 1 and 7.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer (1–7).");
                    scanner.next();
                }
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Course Name: ");
                    String course_name = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String course_code = scanner.nextLine();
                    cm.addCourse(course_name, course_code);
                    break;

                case 2:
                    cm.viewAllCourses();
                    break;

                case 3:
                    int searchId = readPositiveInt(scanner, "Enter Course ID to search: ");
                    Course found = cm.searchCourseByID(searchId);
                    if (found != null) {
                        found.display();
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Course Name to search: ");
                    String courseName = scanner.nextLine();
                    cm.searchByCourseName(courseName);
                    break;

                case 5:
                    System.out.print("Enter Course Code to search: ");
                    String courseCode = scanner.nextLine();
                    cm.searchByCourseCode(courseCode);
                    break;

                case 6:
                    int removeId = readPositiveInt(scanner, "Enter Course ID to remove: ");
                    cm.removeCourse(removeId);
                    break;

                case 7:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Unexpected error.");
            }
        } while (choice != 7);
    }

    // Read a positive integer
    private static int readPositiveInt(Scanner scanner, String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            try {
                number = scanner.nextInt();
                if (number >= 1) {
                    scanner.nextLine();
                    return number;
                } else {
                    System.out.println("ID must be positive.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }
    }
}