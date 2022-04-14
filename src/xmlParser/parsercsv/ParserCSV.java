package xmlParser.parsercsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ParserCSV {

    private String TAG = "ParserCSV: ";
    private String csvFile = "/Users/xutao/Downloads/20210401_165452_sql分析_5000条.csv";


//    public static void main(String[] args) {
//        try {
//            readCSV(csvFile);
//            int a = 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    //public String omgid, ftime, modulesession, modulename, activityname, relativetime;
    private List<String> colomns;

    //LinkedHashMap<modulesession,List<FirstComponentCreateBegin>>
    public LinkedHashMap<String, List<String>> userLaunchMap = new LinkedHashMap<>();
    public LinkedHashMap<String, List<String>> userLaunchMap_noHomeActivity = new LinkedHashMap<>();
    public LinkedHashMap<String, List<String>> userLaunchMap_other = new LinkedHashMap<>();

    public void readCSV(String path) {
        try {
            read(path);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private BufferedReader br = null;
    private String line = "";
    private String cvsSplitBy = ",";

    private void read(String path) throws Exception {
        int curline = 0;
        br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null) {
            String[] country = line.split(cvsSplitBy);
            curline++;

            //获取列
            if (colomns == null) {
                colomns = Arrays.asList(country);
                System.out.println(TAG + colomns.toString());
            } else {
                String modulesession = country[2];
                if (!userLaunchMap.containsKey(modulesession)) {
                    userLaunchMap.put(modulesession, new ArrayList<>());
                }
                String modulename = country[3];
                if (modulename != null && modulename.length() > 0 && !modulename.equals("FirstComponentCreateBegin")) {
                    continue;
                }
                String componentName = country[4];
                if (!userLaunchMap.get(modulesession).contains(componentName)) {
                    userLaunchMap.get(modulesession).add(componentName);
                }
            }
            System.out.println(TAG + "curline: " + curline);
        }


        checkHomeActivity();
    }

    //筛选出有SplashHomeActivity和无SplashHomeActivity的map
    private void checkHomeActivity() {
        String activity = "com.tencent.qqlive.ona.activity.SplashHomeActivity";
        for (Map.Entry<String, List<String>> entry : userLaunchMap.entrySet()) {
            List<String> list = entry.getValue();
            if (list.contains(activity)) {
                userLaunchMap_other.put(entry.getKey(), list);
            } else {
                userLaunchMap_noHomeActivity.put(entry.getKey(), list);
            }
        }
    }
}
