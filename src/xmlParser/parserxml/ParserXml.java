package xmlParser.parserxml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import xmlParser.ActivityComponentEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParserXml {

    private static String xmlPath = "/Users/xutao/Desktop/xutao/code/IDEA/demo/src/xmlParser/xml.xml";

    public static void main(String[] args) {
//        start();
        getActivityMap();
    }

    public static void start() {
        ArrayList<ActivityComponentEntity> list = new ArrayList<>();
        list.addAll(checkComponent("activity"));
        list.addAll(checkComponent("service"));
        list.addAll(checkComponent("receiver"));
        list.addAll(checkComponent("provider"));
        System.out.println("主进程list：" + list.size());
    }



    //ComponentName,ComponentType
    public static HashMap<String, String> getExportTrueMap() {
        ArrayList<ActivityComponentEntity> list = new ArrayList<>();
        list.addAll(checkComponent("activity"));
        list.addAll(checkComponent("service"));
        list.addAll(checkComponent("receiver"));
        list.addAll(checkComponent("provider"));
        System.out.println("主进程list：" + list.size());

        HashMap<String, String> map = new HashMap<>();
        for (ActivityComponentEntity entity : list) {
            if (entity.exported) {
                map.put(entity.componentName, entity.componentType);
                System.out.println("可被外部拉起," + entity.componentName + "," + entity.componentType);
            }
        }
        return map;
    }

    public static HashMap<String, String> getActivityMap() {
        ArrayList<ActivityComponentEntity> list = new ArrayList<>();
        list.addAll(checkComponent("activity"));
        System.out.println("主进程list：" + list.size());

        HashMap<String, String> map = new HashMap<>();
        for (ActivityComponentEntity entity : list) {
            map.put(entity.componentName, entity.componentType);
        }
        return map;
    }

    private static ArrayList<ActivityComponentEntity> checkComponent(String compentType) {
        ArrayList<ActivityComponentEntity> list = new ArrayList<>();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(xmlPath));
            Element root = document.getRootElement();
            Element application = root.element("application");
            List<Element> compentList = application.elements(compentType);
            for (Element element : compentList) {
                Attribute androidName = element.attribute("name");
                Attribute processName = element.attribute("process");
                Attribute exportedName = element.attribute("exported");
                String componentName = androidName != null ? androidName.getValue() : null;
                String componentProcess = processName != null ? processName.getValue() : null;
                boolean exported = exportedName != null && Boolean.parseBoolean(exportedName.getValue());

//                if (compentType.equals(receiverComponent) && !exported) {
//                    exported = checkReceiverExported(element);
//                }
                if (!exported) {
                    exported = checkExported(element);
                }
                ActivityComponentEntity entity = new ActivityComponentEntity();
                entity.componentType = compentType;
                entity.componentName = componentName;
                entity.processName = componentProcess;
                entity.exported = exported;
                list.add(entity);
                System.out.println(entity.toString());
            }
            System.out.println(compentType + ", 总量: " + compentList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static boolean checkExported(Element element) {
        Element intentfilter = element.element("intent-filter");
        if (intentfilter != null) {
            List<Element> actionList = intentfilter.elements("action");
            if (actionList != null && actionList.size() > 0) {
                for (Element action : actionList) {
                    if (action != null && action.attribute("name") != null
                            && action.attribute("name").getValue() != null
                            && action.attribute("name").getValue().length() > 0) {
                        System.out.println("checkReceiverExported: " + element.getName() + ", exported = true");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


