package xmlParser.parserexcel;

import xmlParser.parserexcel.ExcelUtils;

public class ParserExcel {

    public static void main(String[] args) {
//        ExcelReader.readExcel("/Users/xutao/Desktop/启动原因/2021032600_2021032608_8335.xlsx", "2021032600_2021032608_8335");
        try {
//            ExcelUtils.readExcelFirstSheet("/Users/xutao/Desktop/启动原因/2021032600_2021032608_8335.xltx");
            ExcelUtils.readExcelFirstSheet("/Users/xutao/Downloads/20210401_165452_sql分析_5000条.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
