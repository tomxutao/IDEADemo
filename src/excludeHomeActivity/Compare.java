package excludeHomeActivity;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Compare {

    /**
     * 比较list1有哪些元素在list2里面
     * list1大
     * @param list1
     * @param list2
     */
    public static ArrayList<String> compareList(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> contain2 = new ArrayList<>();
        if (list2 == null || list2.size() <= 0) {
            return contain2;
        }
        for (String s2 : list2) {
            if (list1 != null && list1.contains(s2)) {
                contain2.add(s2);
                //Log.log("Compare", "" + contain2.size());
                Logger logger = Logger.getGlobal();
            }
        }
        return contain2;
    }

}
