//package asm_old;
//
//
//import jdk.internal.org.objectweb.asm.AnnotationVisitor;
//import org.objectweb.asm.AnnotationVisitor;
//import org.objectweb.asm.ClassVisitor;
//import org.objectweb.asm.FieldVisitor;
//import org.objectweb.asm.MethodVisitor;
//import org.objectweb.asm.Opcodes;
//
///**
// * Created by Carl on 2018/6/11.
// */
//
//public class CarlClassVisitor extends ClassVisitor {
//    private String mClassName;
//    private String[] mInterfaces;
//    private String superName;
//    private boolean isHintClass = false;
//
//
//    public CarlClassVisitor() {
//        super(Opcodes.ASM4);
//    }
//
//    public CarlClassVisitor(ClassVisitor classVisitor) {
//        super(Opcodes.ASM4, classVisitor);
//    }
//
//    //先调visit
//    @Override
//    public void visit(int i, int i1, String name, String signature, String superName, String[] interfaces) {
//        mClassName = name;
//        mInterfaces = interfaces;
//        isHintClass = ASMUtil.isMatchingClass(name, interfaces);
//        this.superName = superName;
//        System.out.println("xutaoxutao123 CarlClassVisitor.visit() isHintClass: " + isHintClass
//                + ", name: " + name + ", signature: " + signature
//                + ", superName: " + superName + ", interfaces: " + interfaces);
//        super.visit(i, i1, name, signature, superName, interfaces);
//    }
//
//    @Override
//    public AnnotationVisitor visitAnnotation(String s, boolean b) {
//        // TODO: 2018/6/11 可以获取annotation 并且做出判断
//        System.out.println("xutaoxutao123 CarlClassVisitor.visitAnnotation() s: " + s + ", b: " + b);
//        return super.visitAnnotation(s, b);
//    }
//
//    @Override
//    public void visitInnerClass(String s, String s1, String s2, int i) {
//        System.out.println("xutaoxutao123 CarlClassVisitor.visitInnerClass() s: " + s + ", s1: " + s1
//                + ", s2: " + s2 + ", i: " + i);
//        super.visitInnerClass(s, s1, s2, i);
//    }
//
//    @Override
//    public void visitOuterClass(String s, String s1, String s2) {
//        System.out.println("xutaoxutao123 CarlClassVisitor.visitOuterClass() s: " + s + ", s1: " + s1 + ", s2: " + s2);
//        super.visitOuterClass(s, s1, s2);
//    }
//
//
//    @Override
//    public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {
//        System.out.println("xutaoxutao123 CarlClassVisitor.visitField() i: " + i + ", s1: " + s1 + ", s2: " + s2 + ", o: " + o);
//        return super.visitField(i, s, s1, s2, o);
//    }
//
//    @Override
//    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//        System.out.println("xutaoxutao123 CarlClassVisitor.visitMethod() access: " + access + ", name: " + name
//                + ", desc: " + desc + ", signature: " + signature + ", exceptions: " + exceptions);
//        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
//        MethodVisitor adapter = null;
//
//        if ((isHintClass && ASMUtil.isMatchingMethod(name, desc))) {
//            //指定方法名，根据满足的类条件和方法名
//            try {
//                adapter = ASMUtil.getMethodVisitor(mInterfaces, mClassName, superName, methodVisitor, access, name, desc);
//            } catch (Exception e) {
//                e.printStackTrace();
//                adapter = null;
//            }
//        } else {
//            System.out.println("xutaoxutao123 CarlClassVisitor.visitMethod() 查看修改后方法 " + name);
//            adapter = new CarlMethodVisitor(methodVisitor, access, name, desc);
//        }
//        if (adapter != null) {
//            return adapter;
//        }
//        return methodVisitor;
//    }
//
//
//    @Override
//    public void visitEnd() {
//        System.out.println("xutaoxutao123 CarlClassVisitor.visitEnd() 结束遍历类 ");
//        super.visitEnd();
//    }
//}
