
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
        private String studentID;
        private String lastName;
        private String firstName;
        private Date dateOfBirth;
        private String AcademicLevel;

        public Student(String studentID, String lastName, String firstName, Date dateOfBirth, String academiclevel) {
            this.studentID = studentID;
            this.lastName = lastName;
            this.firstName = firstName;
            this.dateOfBirth = dateOfBirth;
            this.AcademicLevel = academiclevel;
        }

        public String getStudentID() {
            return studentID;
        }
        public void setStudentID(String ID){
            this.studentID = ID;
        }
        public String getLastName() {
            return lastName;
        }

    public void setLastName(String Lastname){
        this.lastName = Lastname;
    }

        public String getFirstName() {
            return firstName;
        }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

        public Date getDateOfBirth() {
            return dateOfBirth;
        }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

        public String getAcademicLevel() {
            return AcademicLevel;
        }

    public void setAcademicLevel(String academicLevel) {
        this.AcademicLevel = academicLevel;
    }

        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return "Student Details:\n" +
                    "ID: " + studentID + "\n" +
                    "Last Name: " + lastName + "\n" +
                    "First Name: " + firstName + "\n" +
                    "Date of Birth: " + sdf.format(dateOfBirth) + "\n" +
                    "Academic Level: " + AcademicLevel + "\n";
        }
}

