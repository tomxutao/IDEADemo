import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.function.Function;
import java.util.regex.Pattern;

public class test1 {

    public static void main(String[] args) throws MalformedURLException {

        String ggggg = "http://ttc.gdt.qq.com/click?rt=1&os=2&s_lp=101&ch=1000&contract=0&jtype=0&i=1&acttype=1021&appversion=220008&oid=1646176&click_data=EAAoAEAbWhBFQkQxQjIwREQ0OUFCRjhC_viewid_BYZFgUnsfy1VLYR1NJGH2e46WePLelgscNpFhxdxKHZyXaSCBZhspPFJ3iSivrzlC05YdvMT!5JlJcIJQRIZDIJ73xyF_ereAI6DCX9uthJf6leT0vVH_d3QTU!BZUQ86eDxICCzbuqejvIrA8FD71XGid4LwHjjWkozC3YJ3Z4D3MLZYPy!kNU4OsR_j8syZ2mWrRrPtW_t6WGIpv6sPh18ySmeeoufZLL1AOx0GQu!mKI9XBirXoNhsFEMDoKh!2cUj5YOqmFEaY5Yi5MrGdH2CsXySSESQkUwWKEOcYxQ6ZdNQitdwHwyqpSqocXm6xuqCglOt4bHrgjRJZZZPXWcgRaFiXdPge_dpclFm37zmeDWteUq1AuIlObuo5Fwz_8C6CBayBX9tzvaG9GJeHcYqvSuvrFQ4Vhw!1aYk!A&vto=0&aseq=1&s={%22da%22:%221080%22,%22db%22:%221920%22,%22down_x%22:%22405.0%22,%22down_y%22:%221833.0%22,%22up_x%22:%22404.25%22,%22up_y%22:%221833.0%22}&xp=3&tl=1&clklpp={%22click_time%22:1648799107837}&calltype=0&seq=1&cid=1151013&tk=52b18088de99c13d9f1f8a22570861ef&mvr=01&qimei=551bd4fe72580e96087b8b8610001f815414&personalSwitchClose=0&os=android&osvid=29&osVersion=10&appvid=6.7.30.999&appvcode=999&store=180&network=WIFI&width=1080&height=2265&pixelRatio=2.875&guid=551bd4fe72580e96087b8b8610001f815414&omgid=db8de6a3e1ce2c4e85580d38871d69b0d0fa001021700f&omgbizid=f4901ec47484164f3bbbbc984390b184c2a70120217015&cpuArch=arm64-v8a&androidId=f39379624bb64dee&manufacturer=HUAWEI&deviceModel=LYA-AL00&xgToken=04416b9b08d526812a0ae54a49a3cc2a9881&timezone=Asia%2FShanghai";
        String[] gs = ggggg.split("&");


        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("1", "2");
        map.put("2", "3");
        Map<String, String> map1 = Collections.unmodifiableMap(map);

        Map<String, String> map2 = new ConcurrentHashMap<>(map1);
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            if (entry.getValue().equals("2")) {
                map2.put("5", "6");
            }
        }
        for (String aaa : map2.values()) {
            if (aaa.equals("2")) {
                map2.put("5", "6");
            }
        }

        for (Map.Entry<String, String> entry : map1.entrySet()) {
            if (entry.getValue().equals("2")) {
                map1.put("5", "6");
            }
        }
        for (String aaa : map1.values()) {
            if (aaa.equals("2")) {
                map1.put("5", "6");
            }
        }

        Map<String, Person> people = new HashMap<String, Person>();

        Person jim = new Person("Jim1", 25);
        Person jim2 = new Person("Jim2", 25);
        Person scott = new Person("Scott", 28);
        Person anna = new Person("Anna", 23);

        people.put(jim.getName(), jim);
        people.put(jim2.getName(), jim2);
        people.put(scott.getName(), scott);
        people.put(anna.getName(), anna);

        // not yet sorted
        List<Person> peopleByAge = new ArrayList<Person>(people.values());
        Collections.sort(peopleByAge, new Comparator<Person>() {

            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        for (Person p : peopleByAge) {
            System.out.println(p.getName() + "\t" + p.getAge());
        }

        //正则表达式

//        String content = "abc09_-fd sdf";
//        String pattern = "^[a-z_0-9- ]{3,15}$";
//        boolean isMatch = Pattern.matches(pattern, content);
//        System.out.println("isMatch: " + isMatch);

//        String content = "google runoob taobao";
//        String pattern = "^[aeiou]";
//        boolean isMatch = Pattern.matches(pattern, content);
//        System.out.println("isMatch: " + isMatch);

        int[] data = new int[]{0, 1, 2, 3, 4};
        for (int i = 0; i < data.length; i++) {
            final int t = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //init(t);
                    init2(t);
                }
            }).start();
        }
    }


    private static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }


    private static volatile boolean hasInit = false;

    public static void init(final int i) {
        if (hasInit) {
            return;
        }
        hasInit = true;
        System.out.println(Thread.currentThread().getName() + ", hasInit = true, i = " + i);
    }


    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void init2(final int i) {
        if (atomicBoolean.getAndSet(true)) {
            return;
        }
        System.out.println(Thread.currentThread().getName() + ", hasInit = true, i = " + i);



        AtomicReference<String> reference = new AtomicReference<>();

        //AtomicStampedReference 的使用方法:
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<String>("1", 1);
        atomicStampedReference.compareAndSet("1","2",atomicStampedReference.getStamp(),2);

        //AtomicMarkableReference 使用方法
        AtomicMarkableReference<String> atomicMarkableReference = new AtomicMarkableReference<>("1", true);
        atomicMarkableReference.compareAndSet("1","2",atomicMarkableReference.isMarked(),false);
    }

    private static final byte[] LOCK = new byte[0];

    public static void sync1() {
        synchronized (LOCK) {
            //...
        }
    }

    public static synchronized void sync2() {
        //...
    }



}
