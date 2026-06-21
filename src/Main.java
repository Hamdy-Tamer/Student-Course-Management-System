import Domain.Student;
import Domain.Course;
import Management.StudentManagement;

import java.util.Scanner;
import java.util.InputMismatchException;

// Main Class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement sm = new StudentManagement();
        int choice = 1;

        do {
            System.out.println("\n--- Domain.Student Management System ---");
            System.out.println("1. Add Domain.Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Domain.Student by ID");
            System.out.println("4. Search Domain.Student by First Name");
            System.out.println("5. Remove Domain.Student by ID");
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
                    System.out.print("Enter Domain.Student First Name: ");
                    String fname = scanner.nextLine();
                    System.out.print("Enter Domain.Student Last Name: ");
                    String lname = scanner.nextLine();
                    System.out.print("Enter Domain.Student Email: ");
                    String email = scanner.nextLine();
                    sm.addStudent(fname, lname, email);
                    break;

                case 2:
                    sm.viewAllStudents();
                    break;

                case 3:
                    int searchId = readPositiveInt(scanner, "Enter Domain.Student ID to search: ");
                    Student found = sm.searchStudentByID(searchId);
                    if (found != null) {
                        found.display();
                    } else {
                        System.out.println("Domain.Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Domain.Student First Name to search: ");
                    String firstName = scanner.nextLine();
                    sm.searchByFirstName(firstName);
                    break;

                case 5:
                    int removeId = readPositiveInt(scanner, "Enter Domain.Student ID to remove: ");
                    if (removeId == -1) {
                        break;
                    }
                    sm.removeStudent(removeId);
                    break;

                case 6:
                    System.out.println("Good Bye");
                    break;

                default:
                    System.out.println("Unexpected error.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        int number = 1;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                number = scanner.nextInt();
                if (number >= 1) {
                    valid = true;
                }

                else{
                    System.out.println("ID must be a positive.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }
        scanner.nextLine();
        return number;
    }
}