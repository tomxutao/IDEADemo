package asm.t1;

//生成一个类
//package sample;
//
//public class HelloWorld {
//    @Override
//    public String toString() {
//        return "This is a HelloWorld object.";
//    }
//}
public class Main {

    public static void main(String[] args) {
        try {
            MyClassLoader myClassLoader = new MyClassLoader();
            Class<?> clazz = myClassLoader.loadClass("asm.t1.HelloWorld");
            System.out.println(clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
