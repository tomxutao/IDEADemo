package asm.t2;

import asm.utils.FileUtils;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class Main {

    public static void main(String[] args) {
//        t2_1();
//        t2_2();
        t2_3();
    }

    //生成一个接口
    //public interface HelloWorld {
    //}
    private static void t2_1() {
        String relative_path = "asm/t2/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relative_path);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "asm/t2/HelloWorld",
                null,
                "java/lang/Object",
                null
        );
        cw.visitEnd();
        byte[] data = cw.toByteArray();
        FileUtils.writeBytes(filepath, data);

        try {
            Class<?> clazz = Class.forName("asm.t2.HelloWorld");
            System.out.println(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //    生成预期的代码
//    public interface HelloWorld extends Cloneable {
//        int LESS = -1;
//        int EQUAL = 0;
//        int GREATER = 1;
//        int compareTo(Object o);
//    }
    private static void t2_2() {
        String relative_path = "asm/t2/HelloWorld2.class";
        String filepath = FileUtils.getFilePath(relative_path);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "asm/t2/HelloWorld2",
                null,
                "java/lang/Object",
                new String[]{"java/lang/Cloneable"});

        FieldVisitor fv1 = cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "LESS", "I", null, -1);
        fv1.visitEnd();

        FieldVisitor fv2 = cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "EQUAL", "I", null, 0);
        fv2.visitEnd();

        FieldVisitor fv3 = cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "GREATER", "I", null, 1);
        fv3.visitEnd();

        MethodVisitor mv1 = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT,
                "compareTo", "(Ljava/lang/Object;Ljava/lang/Object;)I", null,
                new String[]{"java/io/FileNotFoundException", "java/io/IOException"});
        mv1.visitEnd();

        cw.visitEnd();

        FileUtils.writeBytes(filepath, cw.toByteArray());


        try {
            Class<?> clazz = Class.forName("asm.t2.HelloWorld2");
            System.out.println(clazz);

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.printf("\nfield: " + field);
            }

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.printf("\nmethod: " + method);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    生成类
//    public class HelloWorld {
//    }
    private static void t2_3() {
        String relative_path = "asm/t2/HelloWorld3.class";
        String filepath = FileUtils.getFilePath(relative_path);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
                "asm/t2/HelloWorld3",
                null,
                "java/lang/Object",
                null);

        //这是构造方法
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        //如果在.class文件中包含静态代码块，那么就会有一个<clinit>()方法
        //visitMethod(ACC_STATIC, "<clinit>", "()V", null, null)

        cw.visitEnd();

        FileUtils.writeBytes(filepath, cw.toByteArray());

        try {
            Class<?> clazz = Class.forName("asm.t2.HelloWorld3");
            System.out.println(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
