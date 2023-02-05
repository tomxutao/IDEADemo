package xmlParser.parsercsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * select
 * qimei36,
 * udf_kv,
 * ui_vrsn,
 * os_vrsn	dev_brand,
 * get_json_object(udf_kv, '$.ad_event') as ad_event,
 * get_json_object(udf_kv, '$.ad_cur_time') as ad_cur_time
 * from hive.pcg_video_ymp.dwd_sports_ad_terminal_tech_param_hi
 * where imp_hour >= 2022101600 and imp_hour <= 2022101700
 * and app_vr = '7.1.10.1097'
 * and get_json_object(udf_kv, '$.ad_business') = 'ad_splash'
 * and get_json_object(udf_kv, '$.ad_expid') = '9044183' --过滤骡马实验id
 * and (get_json_object(udf_kv, '$.ad_event') = '187'
 * or get_json_object(udf_kv, '$.ad_event') = '188'
 * or get_json_object(udf_kv, '$.ad_event') = '189'
 * or get_json_object(udf_kv, '$.ad_event') = '190')
 */
public class HotActivityAnalyzeCSV {

    private static String TAG = "HotActivityAnalyzeCSV";
    private static String csvFile = "/Users/xutao/Desktop/xutao/test/1666063672463.csv";

    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> qimei36Map = readFile(csvFile);
        analyze(qimei36Map);
    }

    private static HashMap<String, ArrayList<String>> readFile(String path) {
        HashMap<String, ArrayList<String>> qimei36Map = new HashMap<>();

        int curline = 0;
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] country = line.split(",");
                curline++;
                String qimei36 = country[0];
                if ("qimei36".equals(qimei36)) {
                    continue;
                }
                String udf_kv = country[1];
                String ui_vrsn = country[2];
                String dev_brand = country[3];
                String ad_event = country[10];
                String ad_cur_time = country[5];
                if (qimei36 != null && qimei36.length() > 0) {
                    if (!qimei36Map.containsKey(qimei36)) {
                        qimei36Map.put(qimei36, new ArrayList<>());
                    }
                    ArrayList list = qimei36Map.get(qimei36);
                    if ("187".equals(ad_event) || "189".equals(ad_event)) {
                        list.add(ad_event);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qimei36Map;
    }

    private static void analyze(HashMap<String, ArrayList<String>> qimei36Map) {
        if (qimei36Map == null || qimei36Map.size() <= 0) {
            return;
        }
        for (Map.Entry<String, ArrayList<String>> entry : qimei36Map.entrySet()) {
            String qimei36 = entry.getKey();
            ArrayList<String> values = entry.getValue();
            if (values != null && values.size() > 0 && values.size() % 2 == 1) {
                System.out.println(TAG + " qimei36: " + qimei36);
            }
        }
    }
}
