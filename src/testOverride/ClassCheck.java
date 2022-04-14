package testOverride;

import java.lang.reflect.Method;

public class ClassCheck {

    /**
     * 判断c中name方法是否重写父类的name方法
     * 不管父类有没有name方法，只要子类没有，就算没有Override
     * 子类有name方法且父类也有，就认为是Override
     * @param c
     * @param name
     * @param parameterTypes
     * @return
     */
    public static boolean checkSuperMethodOverride(Class<?> c, String name, Class<?>... parameterTypes) {
        Method method = getMethod(c, name, parameterTypes);
        if (method == null) {
            return false;
        }
        Method superMethod = getSuperMethod(c, name, parameterTypes);
        return superMethod != null;
    }

    private static Method getMethod(Class<?> c, String name, Class<?>... parameterTypes) {
        Method curMethod = null;
        try {
            if (c != null) {
                curMethod = c.getDeclaredMethod(name, parameterTypes);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return curMethod;
    }

    /**
     * 一直往上找superClass方法，直到class为null停止
     * @param c
     * @param name
     * @param parameterTypes
     * @return
     */
    private static Method getSuperMethod(Class<?> c, String name, Class<?>... parameterTypes) {
        Class<?> superC = c != null ? c.getSuperclass() : null;
        if (superC == null) {
            return null;
        }
        Method superMethod = getMethod(superC, name, parameterTypes);
        return superMethod != null ? superMethod : getSuperMethod(superC, name, parameterTypes);
    }

}
