public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private String name;
    private String choice1;
    private String choice2;
    private String choice3;
    public Student(String name, String one, String two, String three) {
        this.name = name;
        choice1 = one.trim();
        choice2 = two.trim();
        choice3 = three.trim();
    }
    public Student(String lastName, String firstName,
                   String one, String two, String three) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        name = firstName + " " + lastName;
        choice1 = one.trim();
        choice2 = two.trim();
        choice3 = three.trim();
    }
    public String get1() {
        return choice1;
    }
    public String get2() {
        return choice2;
    }
    public String get3() {
        return choice3;
    }
    public String getName() {
        return name;
    }
    private String getLastName() {
        return lastName;
    }
    public int compareTo(Student other) {
        return lastName.compareTo(other.getLastName());
    }
}
