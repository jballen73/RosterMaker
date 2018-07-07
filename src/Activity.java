import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;
    private String meetingPlace;
    private List<String> students = new ArrayList<>();
    public Activity(String name, String meetingPlace) {
        this.name = name;
        this.meetingPlace = meetingPlace;
    }
    public String getName() {
        return name;
    }
    public String getMeetingPlace() {
        return meetingPlace;
    }
    public List<String> getStudents() {
        return students;
    }
    public void addStudent(String studentName) {
        students.add(studentName);
    }
    public String toString() {
        return "Name: " + name + " Meeting Place: " + meetingPlace
                + "\n" + students + "\n";
    }
}
