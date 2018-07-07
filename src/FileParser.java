import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                lines.get(1));
        //System.out.println(activityMap.values());
        lines = trimFile(lines);
        //lines.stream().forEach(x -> System.out.println(Arrays.toString(x)));
        //System.out.println(lines.size());
        assignStudents(lines, activityMap);
        //System.out.println(activityMap.values());
        return activityMap;
    }
    private Map<String, Activity> generateActivityMap(String[] names,
                                                     String[] places) {
        Map<String, Activity> activityMap = new HashMap<>();
        for (int i = 1; i <= 6; i++) {
            if (!names[i].equals("")) {
                activityMap.put(names[i], new Activity(names[i], places[i]));
            }
        }
        return activityMap;
    }
    private List<String[]> trimFile(List<String[]> lines) {
        lines.remove(0);
        lines.remove(0);
        lines.remove(0);
        lines.remove(0);
        lines.remove(0);
        return lines.stream().filter(x -> !x[3].equals("")).
                collect(Collectors.toCollection(ArrayList::new));
    }
    private void assignStudents(List<String[]> assignments,
                                Map<String, Activity> activityMap) {
        for (String[] assignment : assignments) {
            activityMap.get(assignment[2]).
                    addStudent(assignment[1] + " " + assignment[0]);
        }
    }
}
