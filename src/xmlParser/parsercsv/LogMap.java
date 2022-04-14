package xmlParser.parsercsv;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LogMap {

    public static void print(LinkedHashMap<String, List<String>> map, String tag) {
        System.out.println(tag);
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> componentList = entry.getValue();
            for (String component : componentList) {
                System.out.println("key: " + entry.getKey() + ", component: " + component);
            }
        }
    }
}
