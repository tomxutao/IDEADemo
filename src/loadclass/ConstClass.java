package loadclass;

public class ConstClass {
    static {
        System.out.println("ConstClass init");
    }

    public static final String HELLO = "Hello world";
}
