package AssignmentMaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Activity {
    private String name;
    private String meetingPlace;
    private int capacity;
    private List<String> students = new ArrayList<>();
    public Activity(String name, String meetingPlace, int capacity) {
        this.name = name;
        this.meetingPlace = meetingPlace;
        this.capacity = capacity;
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
        sortList();
    }
    public void addStudent(Student student) {
        students.add(student.getName());
        sortList();
    }
    public int getCapacity() {
        return capacity;
    }
    public String toString() {
        return "Name: " + name + " Meeting Place: " + meetingPlace
                + "\n" + students + "\n";
    }
    private void sortList() {
        Collections.sort(students, (x, y) -> x.substring(x.indexOf(" ") + 1).trim().
                compareTo(y.substring(y.indexOf(" ") + 1).trim()));
    }
}
