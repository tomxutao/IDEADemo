package asm.t3;

public class HelloWorld {
    public void test(boolean flag, int val) {
        Object obj;
        if (flag) {
            obj = Integer.valueOf(val);
        }
        else {
            obj = Long.valueOf(val);
        }
        System.out.println(obj);
    }
}
