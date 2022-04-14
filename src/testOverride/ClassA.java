package testOverride;

import java.lang.reflect.Method;

public class ClassA {

    public static void main(String[] args) {
        new ClassA().check();
    }

    void getBig() {

    }

    void check() {
        ClassCheck.checkSuperMethodOverride(getClass(), "getBig");
    }
}
