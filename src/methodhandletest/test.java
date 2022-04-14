package methodhandletest;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

public class test {

    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }

        public static void main(String[] args) {
            long time = System.currentTimeMillis();
            //System.out.println(time);
            Object obj = time % 2 == 0 ? System.out : new ClassA();
            MethodHandle mh = getPrintlnMH(obj);
            if (mh != null) {
                try {
                    mh.invoke(String.valueOf(time));
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }

        private static MethodHandle getPrintlnMH(Object reveiver)  {
            MethodType mt = MethodType.methodType(void.class, String.class);
            try {
                return MethodHandles.lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
