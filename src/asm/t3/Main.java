package asm.t3;

import asm.utils.FileUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.*;

import java.util.List;
import java.util.function.Function;

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
        String classPath = "asm/t3/HelloWorld.class";
        String filePath = FileUtils.getFilePath(classPath);
        byte[] bytes = FileUtils.readBytes(filePath);

        ClassReader cr = new ClassReader(bytes);

        ClassNode cn = new ClassNode();
        cr.accept(cn, ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);

        String owner = cn.name;
        List<MethodNode> methodNodes = cn.methods;
        for (MethodNode mn : methodNodes) {

        }
    }

    private static void dsf() {
    }
}
