package AssignmentMaker;

import java.util.Map;

public class Runner {
    public static void main(String[] args) throws Exception{
        FileParser parser = new FileParser(args[0]);
        Map<String, Activity> activityMap = parser.parse();
        FileWriter.makeRoster(activityMap);
    }
}
