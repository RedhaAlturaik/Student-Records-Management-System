import java.util.*;

public class StudentManager {
    private Map<String, Student> studentIDIndex = new HashMap<>();
    private Map<String, Set<Student>> lastNameIndex = new HashMap<>();
    private Map<String, Set<Student>> firstNameIndex = new HashMap<>();
    private Map<String, Set<Student>> academicLevelIndex = new HashMap<>();

    // Add student to the system and update indices
    public void addStudent(Student student) {
        studentIDIndex.put(student.getStudentID(), student);
        lastNameIndex.computeIfAbsent(student.getLastName(), k -> new HashSet<>()).add(student);
        firstNameIndex.computeIfAbsent(student.getFirstName(), k -> new HashSet<>()).add(student);
        academicLevelIndex.computeIfAbsent(student.getAcademicLevel(), k -> new HashSet<>()).add(student);
    }
    // Retrieve student by ID
    public Student retrieveByID(String studentID) {
        return studentIDIndex.get(studentID);
    }

    // Retrieve students by last name
    public Set<Student> retrieveByLastName(String lastName) {
        return lastNameIndex.getOrDefault(lastName, new HashSet<>());
    }

    // Retrieve students by first name
    public Set<Student> retrieveByFirstName(String firstName) {
        return firstNameIndex.getOrDefault(firstName, new HashSet<>());
    }

    // Retrieve students by academic level
    public Set<Student> retrieveByAcademicLevel(String academicLevel) {
        return academicLevelIndex.getOrDefault(academicLevel, new HashSet<>());
    }

    // Delete student from all indices
    public void deleteStudent(Student student) {
        studentIDIndex.remove(student.getStudentID());
        lastNameIndex.getOrDefault(student.getLastName(), new HashSet<>()).remove(student);
        firstNameIndex.getOrDefault(student.getFirstName(), new HashSet<>()).remove(student);
        academicLevelIndex.getOrDefault(student.getAcademicLevel(), new HashSet<>()).remove(student);
    }
}
