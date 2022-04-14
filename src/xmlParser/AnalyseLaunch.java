package xmlParser;

import xmlParser.parsercsv.LogMap;
import xmlParser.parsercsv.ParserCSV;
import xmlParser.parserxml.ParserXml;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnalyseLaunch {

    public static void main(String[] args) {
//        String csvPath = "/Users/xutao/Downloads/20210402_173209_new_hue.csv";
        String csvPath = "/Users/xutao/Desktop/启动原因/7df6b898-174b-4f21-baf1-14b0c010577c.csv";

        HashMap<String, String> exportTrueMap = ParserXml.getExportTrueMap();
//        if (1 > 0)
//            return;

        ParserCSV parserCSV = new ParserCSV();
        parserCSV.readCSV(csvPath);
        LinkedHashMap<String, List<String>> userLaunchMap = parserCSV.userLaunchMap;
        LinkedHashMap<String, List<String>> userLaunchMap_noHomeActivity = parserCSV.userLaunchMap_noHomeActivity;
        LinkedHashMap<String, List<String>> userLaunchMap_other = parserCSV.userLaunchMap_other;

        //从拉起维度分析
        LinkedHashMap<String, List<String>> userLaunchMap_noHomeActivity_pull = new LinkedHashMap<>();
        LinkedHashMap<String, List<String>> userLaunchMap_noHomeActivity_other = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> entry : userLaunchMap_noHomeActivity.entrySet()) {
            List<String> componentList = entry.getValue();
            if (componentList == null || componentList.size() <= 0) {
                continue;
            }
            if (isPull(componentList, exportTrueMap)) {
                userLaunchMap_noHomeActivity_pull.put(entry.getKey(), componentList);
            } else {
                userLaunchMap_noHomeActivity_other.put(entry.getKey(), componentList);
            }
        }
        LogMap.print(userLaunchMap_noHomeActivity_other, "启动时没有HomeActivity，又没有匹配到外部拉起的组件");

        //从有无Activity分析
        HashMap<String, String> activityMap = ParserXml.getActivityMap();
        LinkedHashMap<String, List<String>> userLaunchMap_noHomeActivity_haveActivity = new LinkedHashMap<>();
        LinkedHashMap<String, List<String>> userLaunchMap_noHomeActivity_haveNoActivity = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> entry : userLaunchMap_noHomeActivity.entrySet()) {
            List<String> componentList = entry.getValue();
            if (componentList == null || componentList.size() <= 0) {
                continue;
            }
            if (isHaveActivity(componentList, activityMap)) {
                userLaunchMap_noHomeActivity_haveActivity.put(entry.getKey(), componentList);
            } else {
                userLaunchMap_noHomeActivity_haveNoActivity.put(entry.getKey(), componentList);
            }
        }
        LogMap.print(userLaunchMap_noHomeActivity_other, "从有无Activity分析");
    }

    //是否外部拉起
    private static boolean isPull(List<String> componentList, HashMap<String, String> exportTrueMap) {
        for (String component : componentList) {
            if (exportTrueMap.containsKey(component)) {
                return true;
            }
        }
        return false;
    }

    //是否含有Activity
    private static boolean isHaveActivity(List<String> componentList, HashMap<String, String> activityMap) {
        for (String component : componentList) {
            if (activityMap.containsKey(component)) {
                return true;
            }
        }
        return false;
    }
}
