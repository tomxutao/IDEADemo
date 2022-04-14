package excludeHomeActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSV {

    private static String TAG = "CSV";
    private static String StringSplit = ",";

    public static ArrayList<String> readCSV(String path) {
        ArrayList<String> list = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            list = read(br, path);
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
        return list;
    }

    private static ArrayList<String> read(BufferedReader br, String path) throws Exception {
        ArrayList<String> lineTextList = new ArrayList<>();
        String line = "";
        int curline = 0;
        while ((line = br.readLine()) != null) {
            String curLineText = line;
            lineTextList.add(curLineText);
            //System.out.println(TAG + "curline: " + curline);
        }
        return lineTextList;
    }

}
