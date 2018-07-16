import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileParser {
    private String fileName;
    private Random gen = new Random();
    public FileParser(String fileName) {
        this.fileName = fileName;
    }
    public Map<String, Activity> parse() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(fileName));
        List<String[]> lines = reader.readAll();
        //lines.stream().forEach(x -> System.out.println(Arrays.toString(x)));
        Map<String, Activity> activityMap = generateActivityMap(lines.get(0),
                lines.get(1), lines.get(2));
        //System.out.println(activityMap.values());
        lines = trimFile(lines);
        //lines.stream().forEach(x -> System.out.println(Arrays.toString(x)));
        //System.out.println(lines.size());
        List<Student> priorityList = new ArrayList<>();
        List<Student> nonPriorityList = new ArrayList<>();
        sortStudents(lines, priorityList, nonPriorityList);
        assignStudents(activityMap, priorityList, nonPriorityList);
        //groupStudents(lines, activityMap);
        //System.out.println(activityMap.values());
        return activityMap;
    }
    private Map<String, Activity> generateActivityMap(String[] names,
                                                      String[] places,
                                                      String[] caps) {
        Map<String, Activity> activityMap = new HashMap<>();
        for (int i = 1; i <= 6; i++) {
            if (!names[i].equals("")) {
                activityMap.put(names[i], new Activity(names[i], places[i],
                        Integer.parseInt(caps[i])));
            }
        }
        return activityMap;
    }
    private List<String[]> trimFile(List<String[]> lines) {
        for (int i = 0; i < 4; i++) {
            lines.remove(0);
        }
        return lines.stream().filter(x -> !x[2].equals("")).
                collect(Collectors.toCollection(ArrayList::new));
    }
    private void sortStudents(List<String[]> lines, List<Student> priority,
                              List<Student> nonpriority) {
        boolean isPriority = false;
        for (String[] line : lines) {
            if (line[2].equals("RC")) {
                isPriority = line[3].equals("P");
            } else {
                if (isPriority) {
                    priority.add(new Student(line[0], line[1],
                            line[2], line[3], line[4]));
                } else {
                    nonpriority.add(new Student(line[0], line[1],
                            line[2], line[3], line[4]));
                }
            }
        }
    }
    private void assignStudents(Map<String, Activity> activityMap,
                                List<Student> priorityList,
                                List<Student> nonpriorityList) {
        List<Activity> activityList = new ArrayList<>(activityMap.values());
        listAssignment(priorityList, priorityList, x -> getBestChoice(x, activityMap));
        List<Student> extraList1 = new ArrayList<>();
        listAssignment(nonpriorityList, extraList1, x -> activityMap.get(x.get1()));
        List<Student> extraList2 = new ArrayList<>();
        listAssignment(extraList1, extraList2, x -> activityMap.get(x.get2()));
        List<Student> extraList3 = new ArrayList<>();
        listAssignment(extraList2, extraList3, x -> activityMap.get(x.get3()));
        listAssignment(extraList3, extraList3, x -> getOpenActivity(activityList));
    }
    private void listAssignment(List<Student> startingList,
                                List<Student> targetList,
                                Function<Student, Activity> function) {
        while(!startingList.isEmpty()) {
            Student student = startingList.remove(gen.nextInt(startingList.size()));
            Activity choice = function.apply(student);
            if (choice.isOpen()) {
                choice.addStudent(student);
            } else {
                targetList.add(student);
            }
        }
    }
    private Activity getOpenActivity (List<Activity> activityList) {
        for (Activity activity : activityList) {
            if (activity.isOpen()) {
                return activity;
            }
        }
        throw new RuntimeException("All activities full before all students assigned");
    }
    private Activity getBestChoice (Student student, Map<String, Activity> activityMap) {
        if (activityMap.get(student.get1()).isOpen()) {
            return activityMap.get(student.get1());
        } else if (activityMap.get(student.get2()).isOpen()){
            return activityMap.get(student.get2());
        } else if (activityMap.get(student.get3()).isOpen()) {
            return activityMap.get(student.get3());
        } else {
            return getOpenActivity(new ArrayList<>(activityMap.values()));
        }
    }
}
