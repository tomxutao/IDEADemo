package reflect;

import java.lang.reflect.Field;

public class reflect {

    public static void main(String[] args) {
        Field[] fields = A.class.getFields();
        for (Field field : fields) {
            System.out.println(field.getType());
        }
    }

    public static class A {
        public String configId;
        public Class b;
    }

    public static class B {
    }
}
