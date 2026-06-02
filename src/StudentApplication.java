import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class StudentApplication {
    private static StudentManager manager = new StudentManager();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[]args) throws Exception{
        // Load students from CSV file
        String filename = "students-details.csv";  // CSV file with students data
        loadStudentsFromCSV(filename);

        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = getUserChoice();

            scanner.nextLine();

            switch (choice) {
                case 1: searchStudent();
                    break;
                case 2: addNewStudent();
                    break;
                case 3: showStudentsByAcademicLevel();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you!");

                    break;
                default: System.out.println("Invalid choice, try again.");
                    break;
            }
        }
    }
    private static void loadStudentsFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String studentID = data[0];
                    String lastName = data[1];
                    String firstName = data[2];
                    Date dateOfBirth = null;
                    try {
                        dateOfBirth = sdf.parse(data[3]);  // Parse the date
                    } catch (ParseException e) {
                        System.out.println("Error parsing date: " + data[3]);
                        continue;
                    }
                    String universityLevel = data[4];

                    Student student = new Student(studentID, lastName, firstName, dateOfBirth, universityLevel);
                    manager.addStudent(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void displayMainMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("1. Search Student");
        System.out.println("2. Add New Student");
        System.out.println("3. Show students in an academic level");
        System.out.println("4. Exit");
    }
    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
    private static void searchStudent() {
        System.out.println("\n===== Search Student =====");
        System.out.println("1. Search by Student ID");
        System.out.println("2. Search by Last Name");
        System.out.println("3. Search by First Name");
        System.out.println("4. Search by Academic Level");
        System.out.print("Enter your choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        Set<Student> students = null;
        switch (searchChoice) {
            case 1:
                System.out.print("Enter Student ID: ");
                String studentID = scanner.nextLine();
                Student studentByID = manager.retrieveByID(studentID);
                if (studentByID != null) {
                    System.out.println(studentByID);
                    handleStudentOptions(studentByID);
                } else {
                    System.out.println("Student not found.");
                }
                break;
            case 2:
                System.out.print("Enter Last Name: ");
                String lastName = scanner.nextLine();
                students = manager.retrieveByLastName(lastName);
                break;
            case 3:
                System.out.print("Enter First Name: ");
                String firstName = scanner.nextLine();
                students = manager.retrieveByFirstName(firstName);
                break;
            case 4:
                System.out.print("Enter Academic Level: ");
                String academicLevel = scanner.nextLine();
                students = manager.retrieveByAcademicLevel(academicLevel);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (students != null && !students.isEmpty()) {
            students.forEach(System.out::println);
            System.out.print("\nEnter the Student ID to view details or 0 to return: ");
            String chosenID = scanner.nextLine();
            if (!chosenID.equals("0")) {
                Student selectedStudent = manager.retrieveByID(chosenID);
                if (selectedStudent != null) {
                    handleStudentOptions(selectedStudent);
                } else {
                    System.out.println("Invalid Student ID.");
                }
            }
        }
        else {
            System.out.println("No students found!");
        }
    }
    private static void handleStudentOptions(Student student) {
        System.out.println("1. Edit Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Return to Main Menu");
        System.out.print("Enter your choice: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                editStudent(student); break;
            case 2:
                deleteStudent(student); break;
            case 3:
                return;
            default:
                System.out.println("Invalid option.");
        }
    }
    private static void editStudent(Student student) {
        System.out.println("Editing student " + student.getStudentID());

        System.out.print("Enter new Last Name (or press Enter to keep unchanged): ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) student.setLastName(lastName);

        System.out.print("Enter new First Name (or press Enter to keep unchanged): ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) student.setFirstName(firstName);

        System.out.print("Enter new Date of Birth (dd/MM/yyyy) or press Enter to keep unchanged: ");
        String dobStr = scanner.nextLine();
        if (!dobStr.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dateOfBirth = sdf.parse(dobStr);
                student.setDateOfBirth(dateOfBirth);
            } catch (ParseException e) {
                System.out.println("Invalid date format.");
            }
        }

        System.out.print("Enter new Academic Level (or press Enter to keep unchanged): ");
        String academicLevel = scanner.nextLine();
        if (!academicLevel.isEmpty()) student.setAcademicLevel(academicLevel);

        manager.deleteStudent(student);
        manager.addStudent(student);

        System.out.println("Student updated successfully.");
    }


    private static void deleteStudent(Student student) {
        manager.deleteStudent(student);
        System.out.println("Student deleted.");
    }
    private static void addNewStudent() {
        System.out.println("Enter Student ID: ");
        String studentID = scanner.nextLine();

        System.out.println("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter Date of Birth (dd/MM/yyyy): ");
        String dobStr = scanner.nextLine();
        Date dateOfBirth = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dateOfBirth = sdf.parse(dobStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return;
        }

        System.out.println("Enter Academic Level: ");
        String academicLevel = scanner.nextLine();

        Student newStudent = new Student(studentID, lastName, firstName, dateOfBirth, academicLevel);
        manager.addStudent(newStudent);
        System.out.println("Student added successfully.");
    }

    private static void showStudentsByAcademicLevel() {
        System.out.print("Enter Academic Level to show students: ");
        String academicLevel = scanner.nextLine();
        Set<Student> students = manager.retrieveByAcademicLevel(academicLevel);
        if (students != null && !students.isEmpty()) {
            students.forEach(System.out::println);
        } else {
            System.out.println("No students found in this academic level.");
        }
    }

}
