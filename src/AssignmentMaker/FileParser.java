package AssignmentMaker;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class FileParser {
    private String fileName;
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
        lines.remove(0);
        lines.remove(0);
        lines.remove(0);
        lines.remove(0);
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
                    priority.add(new Student(line[1] + " " + line[0],
                            line[2], line[3], line[4]));
                } else {
                    nonpriority.add(new Student(line[1] + " " + line[0],
                            line[2], line[3], line[4]));
                }
            }
        }
    }
    private void assignStudents(Map<String, Activity> activityMap,
                                List<Student> priorityList,
                                List<Student> nonpriorityList) {
        Random gen = new Random();
        List<Activity> activityList = new ArrayList<>(activityMap.values());
        while(!priorityList.isEmpty()) {
            Student student = priorityList.remove(gen.nextInt(priorityList.size()));
            Activity choice = activityMap.get(student.get1());
            if (choice.getStudents().size() < choice.getCapacity()) {
                choice.addStudent(student);
            } else {
                choice = activityMap.get(student.get2());
                if (choice.getStudents().size() < choice.getCapacity()) {
                    choice.addStudent(student);
                } else {
                    choice = activityMap.get(student.get3());
                    if (choice.getStudents().size() < choice.getCapacity()) {
                        choice.addStudent(student);
                    } else {
                        for (Activity activity : activityList) {
                            if (activity.getStudents().size() < activity.getCapacity()) {
                                activity.addStudent(student);
                                break;
                            }
                        }
                    }
                }
            }
        }
        List<Student> extraList1 = new ArrayList<>();
        while(!nonpriorityList.isEmpty()) {
            Student student = nonpriorityList.remove(gen.nextInt(nonpriorityList.size()));
            Activity choice = activityMap.get(student.get1());
            if (choice.getStudents().size() < choice.getCapacity()) {
                choice.addStudent(student);
            } else {
                extraList1.add(student);
            }
        }
        List<Student> extraList2 = new ArrayList<>();
        while(!extraList1.isEmpty()) {
            Student student = extraList1.remove(gen.nextInt(extraList1.size()));
            Activity choice = activityMap.get(student.get2());
            if (choice.getStudents().size() < choice.getCapacity()) {
                choice.addStudent(student);
            } else {
                extraList2.add(student);
            }
        }
        List<Student> extraList3 = new ArrayList<>();
        while(!extraList2.isEmpty()){
            Student student = extraList2.remove(gen.nextInt(extraList2.size()));
            Activity choice = activityMap.get(student.get3());
            if (choice.getStudents().size() < choice.getCapacity()) {
                choice.addStudent(student);
            } else {
                extraList3.add(student);
            }
        }
        while(!extraList3.isEmpty()) {
            Student student = extraList3.remove(gen.nextInt(extraList3.size()));
            for (Activity activity : activityList) {
                if (activity.getStudents().size() < activity.getCapacity()) {
                    activity.addStudent(student);
                    break;
                }
            }
        }
    }
}
