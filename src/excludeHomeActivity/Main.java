package excludeHomeActivity;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        String param1 = "ad_select_id";

        ArrayList<String> list_CallSDK = CSV.readCSV("/Users/xutao/Desktop/xutao/ziliao/Tencent/AD/剔除Acitivty看板排查/molly-1/20210623调用sdk上报.csv");
        list_CallSDK.remove(param1);
        Log.log("list_CallSDK", "" + list_CallSDK.size());

        ArrayList<String> list_SelectOrder = CSV.readCSV("/Users/xutao/Desktop/xutao/ziliao/Tencent/AD/剔除Acitivty看板排查/molly-1/20210623启动选单上报.csv");
        list_SelectOrder.remove(param1);
        Log.log("list_SelectOrder", "" + list_SelectOrder.size());

        ArrayList<String> list_Expose = CSV.readCSV("/Users/xutao/Desktop/xutao/ziliao/Tencent/AD/剔除Acitivty看板排查/molly-1/20210623原始曝光上报.csv");
        list_Expose.remove(param1);
        Log.log("list_Expose", "" + list_Expose.size());

        ArrayList<String> list_HomeActivity = CSV.readCSV("/Users/xutao/Desktop/xutao/ziliao/Tencent/AD/剔除Acitivty看板排查/molly-1/20210623 no componant上报.csv");
        list_HomeActivity.remove(param1);
        Log.log("list_HomeActivity", "" + list_HomeActivity.size());

        ArrayList<String> list_CallSDK_Contains_SelectOrder = Compare.compareList(list_CallSDK, list_SelectOrder);
        Log.log("list_CallSDK_Contains_SelectOrder", "" + list_CallSDK_Contains_SelectOrder.size());

        ArrayList<String> list_CallSDK_Contains_Expose = Compare.compareList(list_CallSDK, list_Expose);
        Log.log("list_CallSDK_Contains_Expose", "" + list_CallSDK_Contains_Expose.size());
        ArrayList<String> list_SelectOrder_Contains_Expose = Compare.compareList(list_SelectOrder, list_Expose);
        Log.log("list_SelectOrder_Contains_Expose", "" + list_SelectOrder_Contains_Expose.size());

        ArrayList<String> list_CallSDK_Contains_HomeActivity = Compare.compareList(list_CallSDK, list_HomeActivity);
        Log.log("list_CallSDK_Contains_HomeActivity", "" + list_CallSDK_Contains_HomeActivity.size());
        ArrayList<String> list_SelectOrder_Contains_HomeActivity = Compare.compareList(list_SelectOrder, list_HomeActivity);
        Log.log("list_SelectOrder_Contains_HomeActivity", "" + list_SelectOrder_Contains_HomeActivity.size());
        ArrayList<String> list_Expose_Contains_HomeActivity = Compare.compareList(list_Expose, list_HomeActivity);
        Log.log("list_Expose_Contains_HomeActivity", "" + list_Expose_Contains_HomeActivity.size());

        System.out.println();
    }

}
